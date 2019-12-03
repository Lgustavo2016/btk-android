package com.lsandoval.btk_android.Controller;

import android.content.Context;

import com.lsandoval.btk_android.DAO.BookDAO;
import com.lsandoval.btk_android.Model.BookBean;

import java.util.List;

public class BookController {

    private final BookDAO BOOK_DAO;

    public BookController(Context context) {
        this.BOOK_DAO = new BookDAO(context);
    }

    /*
     * Creates an Book.
     * */
    public boolean registerBook(BookBean book) {
        return this.BOOK_DAO.create(book);
    }

    /*
     * Edits an Book.
     * */
    public boolean editBook(BookBean book) {
        return this.BOOK_DAO.update(book);
    }

    /*
     * Deletes an Book.
     * */
    public boolean deleteBook(BookBean book) {
        return this.BOOK_DAO.delete(book.getId());
    }

    /*
     * List all registered books.
     * */
    public List<BookBean> listAllBooks() {
        return this.BOOK_DAO.listAll();
    }
}
