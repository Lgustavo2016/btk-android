package com.lsandoval.btk_android.View.Modules.Loan.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lsandoval.btk_android.R;

public class LoanCardHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public TextView status;
    public TextView lendDate;
    public TextView returnDate;
    public TextView renter;
    public TextView lessee;
    public TextView book;
    public Button btnReturnBook;

    public LoanCardHolder(View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.loan_card_title);
        status = itemView.findViewById(R.id.loan_card_status);
        lendDate = itemView.findViewById(R.id.loan_card_lend_date);
        returnDate = itemView.findViewById(R.id.loan_card_return_date);
        renter = itemView.findViewById(R.id.loan_card_renter);
        lessee = itemView.findViewById(R.id.loan_card_lessee);
        book = itemView.findViewById(R.id.loan_card_book);
        btnReturnBook = itemView.findViewById(R.id.loan_card_return_btn);
    }
}
