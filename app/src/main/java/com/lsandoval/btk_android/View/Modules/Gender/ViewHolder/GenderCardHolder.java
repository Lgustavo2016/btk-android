package com.lsandoval.btk_android.View.Modules.Gender.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lsandoval.btk_android.R;

public class GenderCardHolder extends RecyclerView.ViewHolder {

    public TextView name;
    public TextView description;
    public Button btnEdit;
    public Button btnDelete;

    public GenderCardHolder(View itemView) {
        super(itemView);

        this.name = itemView.findViewById(R.id.gender_card_title);
        this.description = itemView.findViewById(R.id.gender_card_description);
        this.btnEdit = itemView.findViewById(R.id.gender_card_editBtn);
        this.btnDelete = itemView.findViewById(R.id.gender_card_deleteBtn);
    }
}
