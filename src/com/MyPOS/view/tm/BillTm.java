package com.MyPOS.view.tm;

public class BillTm {
    private int id;
    private String item;
    private int qty;
    private double Price;

    public BillTm() {
    }

    public BillTm(int id, String item, int qty, double price) {
        this.id=id;
        this.item=item;
        this.qty=qty;
        this.Price=price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }
    @Override
    public String toString() {
        return "BillTm{" +
                "id=" + id +
                ", item='" + item + '\'' +
                ", qty=" + qty +
                ", Price=" + Price +
                '}';
    }
}
