package com.lsandoval.btk_android.View.Modules.Author;

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
import com.lsandoval.btk_android.Controller.AuthorController;
import com.lsandoval.btk_android.Model.AuthorBean;
import com.lsandoval.btk_android.R;

public class KeepAuthorFragment extends Fragment implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();

    public KeepAuthorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_keep_author, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.mViewHolder.txtName = this.getView().findViewById(R.id.editTxtAuthorName);
        this.mViewHolder.txtCountry = this.getView().findViewById(R.id.editTxtCountry);
        this.mViewHolder.txtAge = this.getView().findViewById(R.id.editTxtAge);

        this.mViewHolder.btnSaveAuthor = this.getView().findViewById(R.id.btnKeepAuthor);

        this.mViewHolder.btnSaveAuthor.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnKeepAuthor) {
            if (mViewHolder.txtName.getText() == null || "".equals(mViewHolder.txtName.getText().toString())) {
                Snackbar.make(v, "Digite o nome do Autor!", Snackbar.LENGTH_LONG).show();
            } else {
                this._keepAuthor(v);
            }
        }
    }

    private static class ViewHolder {
        TextInputEditText txtName;
        TextInputEditText txtCountry;
        TextInputEditText txtAge;
        Button btnSaveAuthor;
    }

    private void _keepAuthor(View v) {
        final AuthorBean author = new AuthorBean();
        final AuthorController authorController = new AuthorController(this.getContext());

        if (this.mViewHolder.txtName.getText() != null)  author.setName(this.mViewHolder.txtName.getText().toString());
        if (this.mViewHolder.txtAge.getText() != null) author.setAge(Integer.parseInt(this.mViewHolder.txtAge.getText().toString()));
        if (this.mViewHolder.txtCountry.getText() != null) author.setCountry(this.mViewHolder.txtCountry.getText().toString());

        if (authorController.registerAuthor(author)) {
            Navigation.findNavController(v).popBackStack();
            Snackbar.make(v, "Autor salvo com sucesso!", Snackbar.LENGTH_LONG).show();
        }

    }

}
