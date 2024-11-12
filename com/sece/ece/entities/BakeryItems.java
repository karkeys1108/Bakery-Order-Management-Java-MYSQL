package com.sece.ece.entities;

public abstract class BakeryItems {
    private int item_id;
    private String item_name;
    private String item_amount;


    public BakeryItems(String item_name, String item_amount) {
        this.item_name = item_name;
        this.item_amount = item_amount;
    }
    public abstract String getItem_name();
}

class Cake extends BakeryItems {

    public Cake(String item_name, String item_amount) {
        super(item_name, item_amount);
    }
    public String getItem_name() {
        return "Cake";
    }

}

class Bread extends BakeryItems {

    public Bread(String item_name, String item_amount) {
        super(item_name, item_amount);
    }
    public String getItem_name() {
        return "Bread";
    }

}

class Pastry extends BakeryItems {
    public Pastry(String item_name, String item_amount) {
        super(item_name, item_amount);
    }
    public String getItem_name() {
        return "Pastry";
    }
}
