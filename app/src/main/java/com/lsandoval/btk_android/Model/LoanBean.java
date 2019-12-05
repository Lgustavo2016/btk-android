package com.lsandoval.btk_android.Model;

import java.io.Serializable;

public class LoanBean implements Serializable {

    private int id;
    private String loanDate;
    private String loanStatus;
    private String returnDate;
    private int idRenter;
    private String nameRenter;
    private int idBook;
    private String nameBook;
    private int idLessee;
    private String nameLessee;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public int getIdRenter() {
        return idRenter;
    }

    public void setIdRenter(int idRenter) {
        this.idRenter = idRenter;
    }

    public String getNameRenter() {
        return nameRenter;
    }

    public void setNameRenter(String nameRenter) {
        this.nameRenter = nameRenter;
    }

    public int getIdLessee() {
        return idLessee;
    }

    public void setIdLessee(int idLessee) {
        this.idLessee = idLessee;
    }

    public String getNameLessee() {
        return nameLessee;
    }

    public void setNameLessee(String nameLessee) {
        this.nameLessee = nameLessee;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

}
