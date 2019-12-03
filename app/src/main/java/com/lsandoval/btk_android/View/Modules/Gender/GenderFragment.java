package com.lsandoval.btk_android.View.Modules.Gender;


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
import com.lsandoval.btk_android.Controller.GenderController;
import com.lsandoval.btk_android.Model.GenderBean;
import com.lsandoval.btk_android.R;
import com.lsandoval.btk_android.View.Modules.Gender.Adapter.GenderCardAdapter;

import java.util.ArrayList;
import java.util.List;


public class GenderFragment extends Fragment implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();

    private RecyclerView mRecyclerView;

    private GenderCardAdapter mGenderCardAdapter;


    public GenderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gender, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this._setupView();
        this._setupRecycler();
        this._getGenders();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.floating_action_button) {
            final GenderFragmentDirections.ActionNext keepGender = GenderFragmentDirections.actionNext();
            Navigation.findNavController(v).navigate(keepGender);
        }
    }

    private static class ViewHolder {
        FloatingActionButton fab;
    }

    private void _getGenders() {
        final List<GenderBean> genders = new GenderController(this.getContext()).listAllGenders();

        if (genders.size() > 0)
            for (GenderBean gender : genders) mGenderCardAdapter.updateList(gender);
    }

    private void _setupView() {
        mViewHolder.fab = getView().findViewById(R.id.floating_action_button);
        mViewHolder.fab.setOnClickListener(this);

        mRecyclerView = getView().findViewById(R.id.recycler_view_layout_gender);
    }

    private void _setupRecycler() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(layoutManager);

        mGenderCardAdapter = new GenderCardAdapter(new ArrayList<GenderBean>(0));
        mRecyclerView.setAdapter(mGenderCardAdapter);
    }
}
