package com.lsandoval.btk_android.Controller;

import android.content.Context;

import com.lsandoval.btk_android.DAO.GenderDAO;
import com.lsandoval.btk_android.Model.GenderBean;

import java.util.List;

public class GenderController {
    private final GenderDAO GENDER_DAO;

    public GenderController(Context context) {
        this.GENDER_DAO = new GenderDAO(context);
    }

    /*
     * Creates an Gender.
     * */
    public boolean registerGender(GenderBean gender) {
        return this.GENDER_DAO.create(gender);
    }

    /*
     * Edits an Gender.
     * */
    public boolean editGender(GenderBean gender) {
        return this.GENDER_DAO.update(gender);
    }

    /*
     * Deletes an Gender.
     * */
    public boolean deleteGender(GenderBean gender) {
        return this.GENDER_DAO.delete(gender.getId());
    }

    /*
     * List all registered genders.
     * */
    public List<GenderBean> listAllGenders() {
        return this.GENDER_DAO.listAll();
    }

}
