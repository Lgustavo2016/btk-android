package com.lsandoval.btk_android.View.Modules.Book.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.lsandoval.btk_android.Controller.BookController;
import com.lsandoval.btk_android.Model.BookBean;
import com.lsandoval.btk_android.R;
import com.lsandoval.btk_android.View.Modules.Book.BookFragmentDirections;
import com.lsandoval.btk_android.View.Modules.Book.ViewHolder.BookCardHolder;

import java.util.ArrayList;
import java.util.List;

public class BookCardAdapter extends RecyclerView.Adapter<BookCardHolder> {

    private final List<BookBean> mBooks;

    public BookCardAdapter(ArrayList books) {
        mBooks = books;
    }

    @Override
    public BookCardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BookCardHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_card_view, parent, false));
    }

    @Override
    public void onBindViewHolder(BookCardHolder holder, int position) {
        holder.title.setText(mBooks.get(position).getName());
        holder.author.setText(mBooks.get(position).getAuthorName());
        holder.gender.setText(mBooks.get(position).getGenderName());
        holder.publishingCompany.setText(mBooks.get(position).getPublishingCompany());
        holder.quantity.setText(String.valueOf(mBooks.get(position).getQuantity()));
        holder.description.setText(mBooks.get(position).getDescription());

        holder.btnEdit.setOnClickListener(view -> _editBook(position, view));
        holder.btnDelete.setOnClickListener(view -> _removeBook(position, view));
    }

    @Override
    public int getItemCount() {
        return mBooks != null ? mBooks.size() : 0;
    }

    private void _insertBook(BookBean book) {
        mBooks.add(book);
        notifyItemInserted(getItemCount());
    }

    private void _editBook(int position, View view) {
        final BookFragmentDirections.ActionNext keepBook = BookFragmentDirections.actionNext().setBook(mBooks.get(position));
        Navigation.findNavController(view).navigate(keepBook);
    }

    private void _removeBook(int position, View view) {
        if (new BookController(view.getContext()).deleteBook(mBooks.get(position))) {
            mBooks.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, mBooks.size());
            Snackbar.make(view, "Livro excluído com sucesso!", Snackbar.LENGTH_LONG);
        } else {
            Snackbar.make(view, "Erro ao excluir Livro!", Snackbar.LENGTH_LONG);
        }
    }

    public void updateList(BookBean book) {
        _insertBook(book);
    }

}
