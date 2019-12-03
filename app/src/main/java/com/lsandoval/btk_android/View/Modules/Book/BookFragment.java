package com.lsandoval.btk_android.View.Modules.Book;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lsandoval.btk_android.Controller.BookController;
import com.lsandoval.btk_android.Model.BookBean;
import com.lsandoval.btk_android.R;
import com.lsandoval.btk_android.View.Modules.Book.Adapter.BookCardAdapter;
import com.lsandoval.btk_android.View.Modules.Home.HomeFragmentDirections;

import java.util.ArrayList;
import java.util.List;

public class BookFragment extends Fragment implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();

    private RecyclerView mRecyclerView;

    private BookCardAdapter mBookCardAdapter;

    public BookFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this._setupView();
        this._setupRecycler();
        this._getBooks();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floating_action_button: {
                final BookFragmentDirections.ActionNext keepBook = BookFragmentDirections.actionNext();
                Navigation.findNavController(v).navigate(keepBook);
            }
        }
    }

    private static class ViewHolder {
        FloatingActionButton fab;
    }

    private void _getBooks() {
        final List<BookBean> books = new BookController(this.getContext()).listAllBooks();

        if (books.size() > 0) for (BookBean book : books) mBookCardAdapter.updateList(book);
    }

    private void _setupView() {
        mViewHolder.fab = getView().findViewById(R.id.floating_action_button);
        mViewHolder.fab.setOnClickListener(this);

        mRecyclerView = getView().findViewById(R.id.recycler_view_layout_book);
    }

    private void _setupRecycler() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(layoutManager);

        mBookCardAdapter = new BookCardAdapter(new ArrayList<BookBean>(0));
        mRecyclerView.setAdapter(mBookCardAdapter);
    }


}
