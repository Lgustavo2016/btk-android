package com.lsandoval.btk_android.View.Modules.Author;


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
import com.lsandoval.btk_android.Controller.AuthorController;
import com.lsandoval.btk_android.Model.AuthorBean;
import com.lsandoval.btk_android.R;
import com.lsandoval.btk_android.View.Modules.Author.Adapter.AuthorCardAdapter;

import java.util.ArrayList;
import java.util.List;

public class AuthorFragment extends Fragment implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();

    private RecyclerView mRecyclerView;

    private AuthorCardAdapter mAuthorCardAdapter;

    public AuthorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_author, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this._setupView();
        this._setupRecycler();
        this._getAuthors();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.floating_action_button) {
            final AuthorFragmentDirections.ActionNext keepAuthor = AuthorFragmentDirections.actionNext();
            Navigation.findNavController(v).navigate(keepAuthor);
        }
    }

    private static class ViewHolder {
        FloatingActionButton fab;
    }

    private void _getAuthors() {
        final List<AuthorBean> authors = new AuthorController(this.getContext()).listAllAuthors();

        if (authors.size() > 0) for (AuthorBean author : authors) mAuthorCardAdapter.updateList(author);
    }

    private void _setupView() {
        mViewHolder.fab = getView().findViewById(R.id.floating_action_button);
        mViewHolder.fab.setOnClickListener(this);

        mRecyclerView = getView().findViewById(R.id.recycler_view_layout_author);
    }

    private void _setupRecycler() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(layoutManager);

        mAuthorCardAdapter = new AuthorCardAdapter(new ArrayList<AuthorBean>(0));
        mRecyclerView.setAdapter(mAuthorCardAdapter);
    }


}
