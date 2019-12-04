package com.lsandoval.btk_android.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lsandoval.btk_android.Helper.DatabaseHelper;
import com.lsandoval.btk_android.Model.LoanBean;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class LoanDAO extends DatabaseHelper {

    private final String TABLE_NAME = "loan";

    public LoanDAO(Context context) {
        super(context);
    }

    public boolean lend(LoanBean loan) {
        try {
            final String sql = "INSERT INTO loan"
                    + "(renter_id, lessee_id, book_id, loanStatus, loanDate, returnDate)"
                    + " VALUES(?,?,?,'Em Aberto', date('now'), date('now', '+7 day'))";

            final SQLiteDatabase db = this.getWritableDatabase();

            final boolean hasCreated = db.rawQuery(sql, new String[]{ String.valueOf(loan.getIdRenter()), String.valueOf(loan.getIdLessee()), String.valueOf(loan.getIdBook())}).moveToFirst();

            db.close();

            return hasCreated;
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public boolean closeLoan(LoanBean loan) {
        try {
            final String sql = "UPDATE loan SET returnDate = DATE(NOW()), loanStatus = 'Finalizado' WHERE id = ?";
            final SQLiteDatabase db = this.getWritableDatabase();

            final boolean hasClosed =db.rawQuery(sql, new String[]{ String.valueOf(loan.getId()) }).moveToFirst();

            db.close();

            return hasClosed;
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public boolean delete(int loanId) {
        try {
            final SQLiteDatabase db = this.getWritableDatabase();

            final boolean hasDeleted = db.delete(this.TABLE_NAME, "id = ?", new String[]{String.valueOf(loanId)}) > 0;

            db.close();

            return hasDeleted;
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public List<LoanBean> listAll() {
        try {
            final String sql = "SELECT loan.id, renter.name AS renter, lessee.name AS lessee, book.name AS book,"
                    + " loan.loanDate, loan.returnDate, loan.loanStatus"
                    + " FROM loan AS loan"
                    + " INNER JOIN user AS renter"
                    + " ON loan.renter_id = renter.id"
                    + " INNER JOIN user AS lessee"
                    + " ON loan.lessee_id = lessee.id"
                    + " INNER JOIN book AS book"
                    + " ON loan.book_id = book.id"
                    + " ORDER BY loan.id";

            final SQLiteDatabase db = this.getReadableDatabase();

            final Cursor cursor = db.rawQuery(sql, new String[]{});
            final List<LoanBean> loans = new ArrayList<>();

            if (cursor.moveToFirst()) {
                do {
                    final LoanBean loan = new LoanBean();

                    loan.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    loan.setNameBook(cursor.getString(cursor.getColumnIndex("book")));
                    loan.setNameRenter(cursor.getString(cursor.getColumnIndex("renter")));
                    loan.setNameLessee(cursor.getString(cursor.getColumnIndex("lessee")));
                    loan.setLoanDate(Date.valueOf(cursor.getString(cursor.getColumnIndex("loanDate"))));
                    loan.setReturnDate(Date.valueOf(cursor.getString(cursor.getColumnIndex("returnDate"))));
                    loan.setLoanStatus(cursor.getString(cursor.getColumnIndex("loanStatus")));

                    loans.add(loan);

                } while (cursor.moveToNext());
            }

            cursor.close();
            db.close();

            return loans;

        } catch (Exception e) {
            throw new Error(e);
        }
    }
}
