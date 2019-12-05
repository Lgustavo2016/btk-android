package com.lsandoval.btk_android.View.Modules.Home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lsandoval.btk_android.Controller.LoanController;
import com.lsandoval.btk_android.Model.LoanBean;
import com.lsandoval.btk_android.R;
import com.lsandoval.btk_android.View.Modules.Loan.Adapter.LoanCardAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView mRecyclerView;

    private LoanCardAdapter mLoanCardAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this._setupView();
        this._setupRecycler();
        this._getLoans();
    }

    private void _setupView() {
        this.mRecyclerView = getView().findViewById(R.id.recycler_view_layout_loan);
    }

    private void _setupRecycler() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(layoutManager);

        this.mLoanCardAdapter = new LoanCardAdapter(new ArrayList<LoanBean>(0));
        mRecyclerView.setAdapter(this.mLoanCardAdapter);
    }

    private void _getLoans() {
        final List<LoanBean> loans = new LoanController(this.getContext()).listAllLoans();

        if (loans.size() > 0)
            for (LoanBean loan : loans) mLoanCardAdapter.updateList(loan);
    }
}
