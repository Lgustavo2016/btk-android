package com.lsandoval.btk_android.View.Modules.Loan.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.lsandoval.btk_android.Model.LoanBean;
import com.lsandoval.btk_android.R;
import com.lsandoval.btk_android.View.Modules.Home.HomeFragmentDirections;
import com.lsandoval.btk_android.View.Modules.Loan.ViewHolder.LoanCardHolder;

import java.util.ArrayList;
import java.util.List;

public class LoanCardAdapter extends RecyclerView.Adapter<LoanCardHolder> {

    private final List<LoanBean> mLoans;

    public LoanCardAdapter(ArrayList loans) {
        mLoans = loans;
    }

    @Override
    public LoanCardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LoanCardHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.loan_card_view, parent, false));
    }

    @Override
    public void onBindViewHolder(LoanCardHolder holder, int position) {
        holder.title.setText(String.format("%s #%d ", "Empréstimo", mLoans.get(position).getId()));
        holder.lendDate.setText(mLoans.get(position).getLoanDate());
        holder.returnDate.setText(mLoans.get(position).getReturnDate());
        holder.renter.setText(mLoans.get(position).getNameRenter());
        holder.lessee.setText(mLoans.get(position).getNameLessee());

        holder.btnReturnBook.setOnClickListener(view -> _closeLoan(position, view));
    }

    @Override
    public int getItemCount() {
        return mLoans != null ? mLoans.size() : 0;
    }

    private void _insertLoan(LoanBean loan) {
        mLoans.add(loan);
        notifyItemInserted(getItemCount());
    }

    private void _closeLoan(int position, View view) {
        final HomeFragmentDirections.ActionNext keepLoan = HomeFragmentDirections.actionNext().setLoan(mLoans.get(position));
        Navigation.findNavController(view).navigate(keepLoan);
    }

    public void updateList(LoanBean loan) {
        _insertLoan(loan);
    }

}
