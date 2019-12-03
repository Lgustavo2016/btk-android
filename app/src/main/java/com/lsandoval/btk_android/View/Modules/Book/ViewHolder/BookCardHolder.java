package com.lsandoval.btk_android.View.Modules.Book.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lsandoval.btk_android.R;

public class BookCardHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public TextView author;
    public TextView gender;
    public TextView publishingCompany;
    public TextView quantity;
    public TextView description;
    public Button btnEdit;
    public Button btnDelete;

    public BookCardHolder(View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.book_card_title);
        author = itemView.findViewById(R.id.book_card_author);
        gender = itemView.findViewById(R.id.book_card_gender);
        publishingCompany = itemView.findViewById(R.id.book_card_publishingCompany);
        quantity = itemView.findViewById(R.id.book_card_quantity);
        description = itemView.findViewById(R.id.book_card_description);
        btnEdit = itemView.findViewById(R.id.book_card_editBtn);
        btnDelete = itemView.findViewById(R.id.book_card_deleteBtn);

    }
}
