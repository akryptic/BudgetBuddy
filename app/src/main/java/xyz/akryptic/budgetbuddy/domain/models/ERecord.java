package xyz.akryptic.budgetbuddy.domain.models;

import androidx.annotation.NonNull;

import java.util.Date;

public class ERecord {
    private int id;
    private double amount;
    private String category;
    private String type;
    private Date date;

    public ERecord(int id, double amount, String category, String type, Date date) {
        this.id = id;
        this.amount = amount;
        this.category = category;
        this.type = type;
        this.date = date;
    }

    // Constructor without ID (used when adding a new record)
    public ERecord(double amount, String category, String type, Date date) {
        this.amount = amount;
        this.category = category;
        this.type = type;
        this.date = date;
    }

    // Getters
    public int getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @NonNull
    @Override
    public String toString() {
        return "Record{" + "id=" + id + ", amount=" + amount + ", category='" + category + '\'' + ", type='" + type + '\'' + ", date=" + date + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        ERecord eRecord = (ERecord) obj;
        return id == eRecord.id &&
                Double.compare(eRecord.amount, amount) == 0 &&
                category.equals(eRecord.category) &&
                type.equals(eRecord.type) &&
                date.equals(eRecord.date);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id, amount, category, type, date);
    }
}
