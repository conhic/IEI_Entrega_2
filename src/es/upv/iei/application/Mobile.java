package es.upv.iei.application;

/**
 * Created by Connor on 25/11/2016.
 */
public class Mobile {
    String name;
    String price;

    public Mobile(String name, String price){
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
