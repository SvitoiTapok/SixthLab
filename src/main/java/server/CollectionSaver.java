package server;

import java.util.LinkedHashSet;
import java.util.List;

public class CollectionSaver {
    private List<Product> products;

    public CollectionSaver(List<Product> products) {
        this.products = products;
    }
    public CollectionSaver(){};

    public List<Product> getProducts() {
        return products;
    }
}
