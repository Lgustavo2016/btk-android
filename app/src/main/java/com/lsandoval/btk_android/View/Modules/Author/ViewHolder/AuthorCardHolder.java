package com.lsandoval.btk_android.View.Modules.Author.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lsandoval.btk_android.R;

public class AuthorCardHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public TextView age;
    public TextView nationality;
    public Button btnEdit;
    public Button btnDelete;

    public AuthorCardHolder(View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.author_card_title);
        age = itemView.findViewById(R.id.author_card_age);
        nationality = itemView.findViewById(R.id.author_card_nationality);
        btnEdit = itemView.findViewById(R.id.author_card_editBtn);
        btnDelete = itemView.findViewById(R.id.author_card_deleteBtn);

    }
}
