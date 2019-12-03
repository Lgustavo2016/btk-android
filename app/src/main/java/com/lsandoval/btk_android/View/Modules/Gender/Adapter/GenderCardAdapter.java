package com.lsandoval.btk_android.View.Modules.Gender.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.lsandoval.btk_android.Controller.GenderController;
import com.lsandoval.btk_android.Model.GenderBean;
import com.lsandoval.btk_android.R;
import com.lsandoval.btk_android.View.Modules.Gender.GenderFragmentDirections;
import com.lsandoval.btk_android.View.Modules.Gender.ViewHolder.GenderCardHolder;

import java.util.ArrayList;
import java.util.List;

public class GenderCardAdapter extends RecyclerView.Adapter<GenderCardHolder> {

    private final List<GenderBean> mGenders;

    public GenderCardAdapter(ArrayList Genders) {
        mGenders = Genders;
    }

    @Override
    public GenderCardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GenderCardHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gender_card_view, parent, false));
    }

    @Override
    public void onBindViewHolder(GenderCardHolder holder, int position) {
        holder.name.setText(mGenders.get(position).getName());
        holder.description.setText(mGenders.get(position).getDescription());

        holder.btnEdit.setOnClickListener(view -> _editGender(position, view));
        holder.btnDelete.setOnClickListener(view -> _removeGender(position, view));
    }

    @Override
    public int getItemCount() {
        return mGenders != null ? mGenders.size() : 0;
    }

    private void _insertGender(GenderBean gender) {
        mGenders.add(gender);
        notifyItemInserted(getItemCount());
    }

    private void _editGender(int position, View view) {
        final GenderFragmentDirections.ActionNext keepGender = GenderFragmentDirections.actionNext().setGender(mGenders.get(position));
        Navigation.findNavController(view).navigate(keepGender);
    }

    private void _removeGender(int position, View view) {
        if (new GenderController(view.getContext()).deleteGender(mGenders.get(position))) {
            mGenders.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, mGenders.size());
            Snackbar.make(view, "Gênero excluído com sucesso!", Snackbar.LENGTH_LONG);
        } else {
            Snackbar.make(view, "Erro ao excluir Gênero!", Snackbar.LENGTH_LONG);
        }
    }

    public void updateList(GenderBean gender) {
        _insertGender(gender);
    }
}
