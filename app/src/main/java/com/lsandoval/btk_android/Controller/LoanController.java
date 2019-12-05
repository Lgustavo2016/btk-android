package com.lsandoval.btk_android.Controller;

import android.content.Context;

import com.lsandoval.btk_android.DAO.LoanDAO;
import com.lsandoval.btk_android.Model.LoanBean;

import java.util.List;

public class LoanController {

    private final LoanDAO LOAN_DAO;

    public LoanController(Context context) {
        this.LOAN_DAO = new LoanDAO(context);
    }

    /*
     * Lends a book.
     * */
    public boolean lend(LoanBean loan) {
        return this.LOAN_DAO.lend(loan);
    }

    /*
     * Closes a Loan.
     * */
    public boolean closeLoan(LoanBean loan) {
        return this.LOAN_DAO.closeLoan(loan);
    }


    /*
     * List all registered loans.
     * */
    public List<LoanBean> listAllLoans() {
        return this.LOAN_DAO.listAll();
    }
}
