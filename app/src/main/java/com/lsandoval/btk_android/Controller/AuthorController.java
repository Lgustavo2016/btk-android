package com.lsandoval.btk_android.Controller;

import android.content.Context;

import com.lsandoval.btk_android.DAO.AuthorDAO;
import com.lsandoval.btk_android.Model.AuthorBean;

import java.util.List;

public class AuthorController {

    private final AuthorDAO AUTHOR_DAO;

    public AuthorController(Context context) {
        this.AUTHOR_DAO = new AuthorDAO(context);
    }

    /*
     * Creates an Author.
     * */
    public boolean registerAuthor(AuthorBean author) {
        return this.AUTHOR_DAO.create(author);
    }

    /*
     * Edits an Author.
     * */
    public boolean editAuthor(AuthorBean author) {
        return this.AUTHOR_DAO.update(author);
    }

    /*
     * Deletes an Author.
     * */
    public boolean deleteAuthor(AuthorBean author) {
        return this.AUTHOR_DAO.delete(author.getId());
    }

    /*
     * List all registered authors.
     * */
    public List<AuthorBean> listAllAuthors() {
        return this.AUTHOR_DAO.listAll();
    }

}
