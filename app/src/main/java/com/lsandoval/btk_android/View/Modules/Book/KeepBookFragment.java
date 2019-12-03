package com.lsandoval.btk_android.View.Modules.Book;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialAutoCompleteTextView;
import com.lsandoval.btk_android.Controller.AuthorController;
import com.lsandoval.btk_android.Controller.BookController;
import com.lsandoval.btk_android.Controller.GenderController;
import com.lsandoval.btk_android.Model.AuthorBean;
import com.lsandoval.btk_android.Model.BookBean;
import com.lsandoval.btk_android.Model.GenderBean;
import com.lsandoval.btk_android.R;

import java.util.List;

public class KeepBookFragment extends Fragment implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private int mSelectedAuthorId;
    private int mSelectedGenderId;
    private int mBookId;

    public KeepBookFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_keep_book, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this._setupView();
        this._setupAuthorAutoComplete();
        this._setupGenderAutoComplete();
    }

    private void _setupView() {
        this.mViewHolder.txtName = getView().findViewById(R.id.editTxtBookName);
        this.mViewHolder.txtPublishingCompany = getView().findViewById(R.id.editTxtPublishingCompany);
        this.mViewHolder.txtSummary = getView().findViewById(R.id.editTxtSummary);
        this.mViewHolder.txtQuantity = getView().findViewById(R.id.editTxtQuantity);
        this.mViewHolder.authorAutoComplete = getView().findViewById(R.id.dropdownAuthor);
        this.mViewHolder.genderAutoComplete = getView().findViewById(R.id.dropdownGender);

        this.mViewHolder.btnKeepBook = getView().findViewById(R.id.btnKeepBook);
        this.mViewHolder.btnKeepBook.setOnClickListener(this);

        if (getArguments() != null && KeepBookFragmentArgs.fromBundle(getArguments()).getBook() != null) {
            final BookBean book = KeepBookFragmentArgs.fromBundle(getArguments()).getBook();
            this.mBookId = book.getId();

            this.mViewHolder.txtName.setText(book.getName());
            this.mViewHolder.txtSummary.setText(book.getDescription());
            this.mViewHolder.txtPublishingCompany.setText(book.getPublishingCompany());
            this.mViewHolder.txtQuantity.setText(String.valueOf(book.getQuantity()));
            this.mViewHolder.authorAutoComplete.setText(book.getAuthorName());
            this.mViewHolder.genderAutoComplete.setText(book.getGenderName());

            this.mSelectedAuthorId = book.getAuthorId();
            this.mSelectedGenderId = book.getGenderId();
        }
    }

    private void _setupAuthorAutoComplete() {
        final List<AuthorBean> authors = new AuthorController(getContext()).listAllAuthors();
        final ArrayAdapter<AuthorBean> authorsAdapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, authors);

        this.mViewHolder.authorAutoComplete.setAdapter(authorsAdapter);

        this.mViewHolder.authorAutoComplete.setThreshold(1);

        this.mViewHolder.authorAutoComplete.setOnItemClickListener((arg0, arg1, arg2, arg3) -> {
            final AuthorBean selectedAuthor = (AuthorBean) arg0.getAdapter().getItem(arg2);
            this.mSelectedAuthorId = selectedAuthor.getId();
        });

    }

    private void _setupGenderAutoComplete() {
        final List<GenderBean> genders = new GenderController(getContext()).listAllGenders();
        final ArrayAdapter<GenderBean> gendersAdapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, genders);

        this.mViewHolder.authorAutoComplete.setAdapter(gendersAdapter);

        this.mViewHolder.authorAutoComplete.setThreshold(1);

        this.mViewHolder.authorAutoComplete.setOnItemClickListener((arg0, arg1, arg2, arg3) -> {
            final GenderBean selectedGender = (GenderBean) arg0.getAdapter().getItem(arg2);
            this.mSelectedGenderId = selectedGender.getId();
        });
    }

    private boolean _fullfilledFields() {
        return !("".equals(mViewHolder.txtName.getText().toString()) ||
                "".equals(mViewHolder.txtPublishingCompany.getText().toString()) ||
                "".equals(mViewHolder.authorAutoComplete.getText().toString()) ||
                "".equals(mViewHolder.genderAutoComplete.getText().toString()));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnKeepBook) {
            if (!this._fullfilledFields()) {
                Snackbar.make(v, "Por favor, preencha todos os campos obrigatórios!", Snackbar.LENGTH_LONG).show();
            } else {
                this._keepBook(v);
            }
        }
    }

    private void _keepBook(View v) {
        final BookBean bookBean = new BookBean();
        final BookController bookController = new BookController(getContext());

        bookBean.setName(this.mViewHolder.txtName.getText().toString());
        bookBean.setAuthorId(Integer.parseInt(this.mViewHolder.authorAutoComplete.getText().toString()));
        bookBean.setGenderId(Integer.parseInt(this.mViewHolder.genderAutoComplete.getText().toString()));
        bookBean.setPublishingCompany(this.mViewHolder.txtPublishingCompany.getText().toString());

        if (!this.mViewHolder.txtQuantity.getText().toString().isEmpty())
            bookBean.setQuantity(Integer.parseInt(this.mViewHolder.txtQuantity.getText().toString()));
        if (!this.mViewHolder.txtSummary.getText().toString().isEmpty())
            bookBean.setDescription(this.mViewHolder.txtSummary.getText().toString());

        if (mBookId > 0) {
            bookBean.setId(this.mBookId);
            bookController.editBook(bookBean);
        } else {
            bookController.registerBook(bookBean);
        }
        Snackbar.make(v, "Livro salvo com sucesso!", Snackbar.LENGTH_LONG).show();
        Navigation.findNavController(v).popBackStack();
    }

    public static class ViewHolder {
        TextInputEditText txtName;
        TextInputEditText txtPublishingCompany;
        TextInputEditText txtSummary;
        TextInputEditText txtQuantity;
        Button btnKeepBook;
        MaterialAutoCompleteTextView authorAutoComplete;
        MaterialAutoCompleteTextView genderAutoComplete;
    }
}
