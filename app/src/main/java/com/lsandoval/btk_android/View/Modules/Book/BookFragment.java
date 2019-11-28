package com.lsandoval.btk_android.View.Modules.Book;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lsandoval.btk_android.R;
import com.lsandoval.btk_android.View.Modules.Home.HomeFragmentDirections;

public class BookFragment extends Fragment implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();

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

        mViewHolder.fab = getView().findViewById(R.id.floating_action_button);

        mViewHolder.fab.setOnClickListener(this);
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


}
