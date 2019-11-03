package com.lsandoval.btk_android.Controller;

import android.content.Context;

import com.lsandoval.btk_android.DAO.UserDAO;
import com.lsandoval.btk_android.Model.UserBean;

public class UserController {

    private final UserDAO USER_DAO;

    public UserController(Context context) {
        this.USER_DAO = new UserDAO(context);
    }

    /*
    * Creates an User
    */
    public boolean registerUser(UserBean user) {
        return this.USER_DAO.create(user);
    }

    /*
     * Authenticates an User
     */
    public UserBean authenticateUser(UserBean user) {
        return this.USER_DAO.authenticate(user);
    }
}
