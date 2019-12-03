package com.lsandoval.btk_android.View.Modules.Author.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.lsandoval.btk_android.Controller.AuthorController;
import com.lsandoval.btk_android.Model.AuthorBean;
import com.lsandoval.btk_android.R;
import com.lsandoval.btk_android.View.Modules.Author.ViewHolder.AuthorCardHolder;
import com.lsandoval.btk_android.View.Modules.Author.AuthorFragmentDirections;

import java.util.ArrayList;
import java.util.List;

public class AuthorCardAdapter extends RecyclerView.Adapter<AuthorCardHolder> {

    private final List<AuthorBean> mAuthors;

    public AuthorCardAdapter(ArrayList authors) {
        mAuthors = authors;
    }

    @Override
    public AuthorCardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AuthorCardHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.author_card_view, parent, false));
    }

    @Override
    public void onBindViewHolder(AuthorCardHolder holder, int position) {
        holder.title.setText(mAuthors.get(position).getName());
        holder.age.setText(String.valueOf(mAuthors.get(position).getAge()));
        holder.nationality.setText(mAuthors.get(position).getCountry());

        holder.btnEdit.setOnClickListener(view -> _editAuthor(position, view));
        holder.btnDelete.setOnClickListener(view -> _removeAuthor(position, view));
    }

    @Override
    public int getItemCount() {
        return mAuthors != null ? mAuthors.size() : 0;
    }

    private void _insertAuthor(AuthorBean author) {
        mAuthors.add(author);
        notifyItemInserted(getItemCount());
    }

    private void _editAuthor(int position, View view) {
        final AuthorFragmentDirections.ActionNext keepAuthor = AuthorFragmentDirections.actionNext().setAuthor(mAuthors.get(position));
        Navigation.findNavController(view).navigate(keepAuthor);
    }

    private void _removeAuthor(int position, View view) {
        if (new AuthorController(view.getContext()).deleteAuthor(mAuthors.get(position))) {
            mAuthors.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, mAuthors.size());
            Snackbar.make(view, "Autor excluído com sucesso!", Snackbar.LENGTH_LONG);
        } else {
            Snackbar.make(view, "Erro ao excluir autor!", Snackbar.LENGTH_LONG);
        }
    }

    public void updateList(AuthorBean author) {
        _insertAuthor(author);
    }

}

