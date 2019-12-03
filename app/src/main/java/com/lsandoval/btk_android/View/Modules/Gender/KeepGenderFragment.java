package com.lsandoval.btk_android.View.Modules.Gender;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.lsandoval.btk_android.Controller.GenderController;
import com.lsandoval.btk_android.Model.GenderBean;
import com.lsandoval.btk_android.R;

public class KeepGenderFragment extends Fragment implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();

    private int mGenderId;

    public KeepGenderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_keep_gender, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.mViewHolder.txtName = this.getView().findViewById(R.id.editTxtGenderName);
        this.mViewHolder.txtDescription = this.getView().findViewById(R.id.editTxtDescription);

        this.mViewHolder.btnSaveGender = this.getView().findViewById(R.id.btnKeepGender);

        this.mViewHolder.btnSaveGender.setOnClickListener(this);

        if (getArguments() != null && KeepGenderFragmentArgs.fromBundle(getArguments()).getGender() != null) {
            final GenderBean gender = KeepGenderFragmentArgs.fromBundle(getArguments()).getGender();
            this.mGenderId = gender.getId();

            this.mViewHolder.txtName.setText(gender.getName());
            this.mViewHolder.txtDescription.setText(gender.getDescription());
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnKeepGender) {
            if (mViewHolder.txtName.getText() == null || "".equals(mViewHolder.txtName.getText().toString())) {
                Snackbar.make(v, "Digite o nome do Gênero!", Snackbar.LENGTH_LONG).show();
            } else {
                this._keepGender(v);
            }
        }
    }

    private static class ViewHolder {
        TextInputEditText txtName;
        TextInputEditText txtDescription;
        Button btnSaveGender;
    }

    private void _keepGender(View v) {
        final GenderBean gender = new GenderBean();
        final GenderController genderController = new GenderController(this.getContext());

        if (this.mViewHolder.txtName.getText() != null)
            gender.setName(this.mViewHolder.txtName.getText().toString());

        if (this.mViewHolder.txtDescription.getText() != null)
            gender.setDescription(this.mViewHolder.txtDescription.getText().toString());

        if (this.mGenderId != 0) {
            gender.setId(this.mGenderId);
            genderController.editGender(gender);
        } else {
            genderController.registerGender(gender);
        }
        Snackbar.make(v, "Gênero salvo com sucesso!", Snackbar.LENGTH_LONG).show();
        Navigation.findNavController(v).popBackStack();

    }

}
