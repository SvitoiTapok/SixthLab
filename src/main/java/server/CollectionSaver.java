package server;

import java.util.LinkedHashSet;

public class CollectionSaver {
    private LinkedHashSet<Product> products = new LinkedHashSet<>();

    public CollectionSaver(LinkedHashSet<Product> products) {
        this.products = products;
    }
    public CollectionSaver(){};

    public LinkedHashSet<Product> getProducts() {
        return products;
    }
}
