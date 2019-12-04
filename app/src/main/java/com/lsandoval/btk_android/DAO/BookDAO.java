package com.lsandoval.btk_android.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lsandoval.btk_android.Helper.DatabaseHelper;
import com.lsandoval.btk_android.Model.BookBean;

import java.util.ArrayList;
import java.util.List;

public class BookDAO extends DatabaseHelper {

    private final String TABLE_NAME = "book";

    public BookDAO(Context context) {
        super(context);
    }

    public boolean create(BookBean book) {
        try {
            final ContentValues values = new ContentValues();

            values.put("name", book.getName());
            values.put("author_id", book.getAuthorId());
            values.put("gender_id", book.getGenderId());
            values.put("quantity", book.getQuantity());
            values.put("publishingCompany", book.getPublishingCompany());
            values.put("description", book.getDescription());

            final SQLiteDatabase db = this.getWritableDatabase();

            final boolean hasCreated = db.insert(this.TABLE_NAME, null, values) > 0;

            db.close();

            return hasCreated;
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public boolean update(BookBean book) {
        try {
            final SQLiteDatabase db = this.getWritableDatabase();
            final ContentValues values = new ContentValues();

            values.put("name", book.getName());
            values.put("author_id", book.getAuthorId());
            values.put("gender_id", book.getGenderId());
            values.put("quantity", book.getQuantity());
            values.put("publishingCompany", book.getPublishingCompany());
            values.put("description", book.getDescription());

            final boolean hasUpdated = db.update(this.TABLE_NAME, values, "id = ?", new String[]{String.valueOf(book.getId())}) > 0;

            db.close();

            return hasUpdated;
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public boolean delete(int bookId) {
        try {
            final SQLiteDatabase db = this.getWritableDatabase();

            final boolean hasDeleted = db.delete(this.TABLE_NAME, "id = ?", new String[]{String.valueOf(bookId)}) > 0;

            db.close();

            return hasDeleted;
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public List<BookBean> listAll() {
        try {
            final String sql = "SELECT book.id, book.name, book.publishingCompany, book.quantity, book.description, "
                    + " gender.name as genderName, gender.id as genderId, author.id as authorId, author.name as authorName "
                    + " FROM book as book INNER JOIN author as author ON book.author_id = author.id "
                    + " INNER JOIN gender as gender ON book.gender_id = gender.id ORDER BY -book.id";

            final SQLiteDatabase db = this.getReadableDatabase();

            final Cursor cursor = db.rawQuery(sql, new String[]{});
            final List<BookBean> books = new ArrayList<>();

            if (cursor.moveToFirst()) {
                do {
                    final BookBean book = new BookBean();

                    book.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    book.setName(cursor.getString(cursor.getColumnIndex("name")));
                    book.setQuantity(cursor.getInt(cursor.getColumnIndex("quantity")));
                    book.setPublishingCompany(cursor.getString(cursor.getColumnIndex("publishingCompany")));
                    book.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                    book.setAuthorId(cursor.getInt(cursor.getColumnIndex("authorId")));
                    book.setAuthorName(cursor.getString(cursor.getColumnIndex("authorName")));
                    book.setGenderId(cursor.getInt(cursor.getColumnIndex("genderId")));
                    book.setGenderName(cursor.getString(cursor.getColumnIndex("genderName")));

                    books.add(book);

                } while (cursor.moveToNext());
            }

            cursor.close();
            db.close();

            return books;

        } catch (Exception e) {
            throw new Error(e);
        }
    }
}
