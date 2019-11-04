package com.lsandoval.btk_android.View.Modules.Book;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lsandoval.btk_android.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class KeepBookFragment extends Fragment {


    public KeepBookFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_keep_book, container, false);
    }

}
