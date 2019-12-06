package com.lsandoval.btk_android.View.Modules.Loan;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textview.MaterialAutoCompleteTextView;
import com.lsandoval.btk_android.Controller.BookController;
import com.lsandoval.btk_android.Controller.LoanController;
import com.lsandoval.btk_android.Controller.UserController;
import com.lsandoval.btk_android.Helper.SessionManager;
import com.lsandoval.btk_android.Model.BookBean;
import com.lsandoval.btk_android.Model.LoanBean;
import com.lsandoval.btk_android.Model.UserBean;
import com.lsandoval.btk_android.R;

import java.util.List;

public class KeepLoanFragment extends Fragment implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();

    private int mSelectedBookId;
    private int mSelectedLesseeId;
    private int mRenterId;
    private int mLoanId;

    public KeepLoanFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_keep_loan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this._setupView();
        this._setupBookAutoComplete();
        this._setupLesseeAutoComplete();
    }

    private void _setupView() {
        this.mViewHolder.bookAutoComplete = getView().findViewById(R.id.dropdown_book);
        this.mViewHolder.lesseeAutoComplete = getView().findViewById(R.id.dropdown_lessee);
        this.mViewHolder.btnLendOrReturn = getView().findViewById(R.id.btnLendOrReturn);

        this.mViewHolder.btnLendOrReturn.setOnClickListener(this);

        if (getArguments() != null && KeepLoanFragmentArgs.fromBundle(getArguments()).getLoan() != null) {
            final LoanBean loan = KeepLoanFragmentArgs.fromBundle(getArguments()).getLoan();

            this.mViewHolder.bookAutoComplete.setText(loan.getNameBook());
            this.mViewHolder.lesseeAutoComplete.setText(loan.getNameLessee());

            this.mRenterId = loan.getIdRenter();
            this.mLoanId = loan.getId();

            this.mSelectedBookId = loan.getIdBook();
            this.mSelectedLesseeId = loan.getIdLessee();

            this.mViewHolder.btnLendOrReturn.setHint("Finalizar Empréstimo");
        }
    }

    private void _setupBookAutoComplete() {
        final List<BookBean> books = new BookController(getContext()).listAllBooks();
        final ArrayAdapter<BookBean> booksAdapter = new ArrayAdapter<>(getContext(), android.R.layout.select_dialog_item, books);

        this.mViewHolder.bookAutoComplete.setAdapter(booksAdapter);

        this.mViewHolder.bookAutoComplete.setThreshold(1);

        this.mViewHolder.bookAutoComplete.setOnItemClickListener((arg0, arg1, arg2, arg3) -> {
            final BookBean selectedBook = (BookBean) arg0.getAdapter().getItem(arg2);
            this.mSelectedBookId = selectedBook.getId();
        });

    }

    private void _setupLesseeAutoComplete() {
        final List<UserBean> users = new UserController(getContext()).listAllUsers();
        final ArrayAdapter<UserBean> usersAdapter = new ArrayAdapter<>(getContext(), android.R.layout.select_dialog_item, users);

        this.mViewHolder.lesseeAutoComplete.setAdapter(usersAdapter);

        this.mViewHolder.lesseeAutoComplete.setThreshold(1);

        this.mViewHolder.lesseeAutoComplete.setOnItemClickListener((arg0, arg1, arg2, arg3) -> {
            final UserBean selectedLessee = (UserBean) arg0.getAdapter().getItem(arg2);
            this.mSelectedLesseeId = selectedLessee.getId();
        });

    }

    @Override
    public void onClick(View v) {
        if (this.mSelectedLesseeId > 0 && this.mSelectedBookId > 0) {
            this._keepLoan(v);
        } else {
            Snackbar.make(v, "Por favor, selecione um Livro/Locatário!", Snackbar.LENGTH_LONG);
        }
    }

    private static class ViewHolder {
        MaterialAutoCompleteTextView bookAutoComplete;
        MaterialAutoCompleteTextView lesseeAutoComplete;
        MaterialButton btnLendOrReturn;
    }

    private void _keepLoan(View v) {
        final LoanBean loan = new LoanBean();
        final LoanController loanController = new LoanController(getContext());

        if (!(this.mRenterId > 0))
            loan.setIdRenter(new SessionManager(getContext()).getUserDetails().getId());

        loan.setIdBook(this.mSelectedBookId);
        loan.setIdLessee(this.mSelectedLesseeId);

        if (this.mLoanId > 0) {
            loan.setId(this.mLoanId);
            loanController.closeLoan(loan);
        } else {
            loanController.lend(loan);
        }

        Snackbar.make(v, "Empréstimo realizado com sucesso!", Snackbar.LENGTH_LONG).show();
        Navigation.findNavController(v).popBackStack();
    }

}
