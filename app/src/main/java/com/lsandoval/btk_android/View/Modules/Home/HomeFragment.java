package com.lsandoval.btk_android.View.Modules.Home;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lsandoval.btk_android.Controller.UserController;
import com.lsandoval.btk_android.Model.UserBean;
import com.lsandoval.btk_android.R;

import java.util.List;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private final ViewHolder mViewHolder = new ViewHolder();

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

        mViewHolder.floatingActionButton = getView().findViewById(R.id.floating_action_button);
        mViewHolder.floatingActionButton.setOnClickListener(this);

        List<UserBean> users = new UserController(getContext()).listAllUsers();

        if (users.size() > 0) {
            LinearLayout linearLayout = getView().findViewById(R.id.usersList);
            for (UserBean user : users) {
                int id = user.getId();
                String nome = user.getName();
                String email = user.getEmail();

                String txtViewContatos = nome + " - " + email;

                TextView textViewContatoItem = new TextView(getContext());
                textViewContatoItem.setPadding(0, 10, 0, 10);
                textViewContatoItem.setText(txtViewContatos);
                textViewContatoItem.setTag(Integer.toString(id));

                linearLayout.addView(textViewContatoItem);

            }

        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.floating_action_button) {
            final HomeFragmentDirections.ActionNext next_action = HomeFragmentDirections.actionNext();
            Navigation.findNavController(v).navigate(next_action);
        }
    }

    private static class ViewHolder {
        FloatingActionButton floatingActionButton;
    }
}
