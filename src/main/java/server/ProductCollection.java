package server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ProductCollection {
    private final LinkedHashSet<Product> products = new LinkedHashSet<>();
    private final HashSet<Long> ID = new HashSet<>();
    private final Date Creation_date;

    //чтение пути из переменной окружения envName
    private String readPath() {
        String path = System.getenv("FIFTH_LABA_PATH");
        if (path == null)
            System.out.println("системной переменной с именем FIFTH_LABA_PATH не было найдено, создана пустая коллекция");
        else
            System.out.println("Создание коллекции на основе файла " + path);
        return path;
    }

    //чтение json в строку с помощью fileReader
    private String readFile(String path) {
        try (FileReader fileReader = new FileReader(path)) {
            int st;
            String json = "";
            while ((st = fileReader.read()) != -1)
                json += (char) st;
            return json;
        } catch (IOException e) {
            System.out.println("Файл не найден");
            return null;
        }
    }

    //конвертация строки в коллекцию
    private List<Product> convertStringToCollection(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            CollectionSaver collectionSaver = objectMapper.readValue(json, CollectionSaver.class);
            return collectionSaver.getProducts();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Некорректный json файл");
            return null;
        }
    }


    public ProductCollection() {
        Creation_date = new Date();
        //чтение из файла
        String path = readPath();
        if (path != null) {
            String json = readFile(path);
            if (json != null) {
                List<Product> products1 = convertStringToCollection(json);
                if (products1 != null) {
                    for (Product product : products1) {
                        if (Collections.frequency(ID, product.getId()) > 0 || product.getId() < 1) {
                            System.out.println("Некорректный ID в файле Data\\ProductsData.json");
                        } else {
                            if (!Product.checkProduct(product))
                                System.out.println("Некорректное поле в продукте с ID: " + product.getId());
                            else {
                                ID.add(product.getId());
                                products.add(product);
                            }
                        }
                    }
                }
            }
        }
    }
    //System.out.println(products1);

    public List<Product> getProducts() {
        return products.stream().sorted().collect(Collectors.toList());
    }

    public void addProduct(Product p) {
        products.removeIf(x -> x.getId() == p.getId() && ID.contains(x.getId()));
        products.add(p);
        ID.add(p.getId());
        //Product.ID.add(p.getId());
    }

    public void removeProduct(long id) {
        products.removeIf(x -> x.getId() == id && ID.contains(x.getId()));
        ID.remove(id);
    }

    public void removeProducts(Collection<Product> product) {
        products.removeAll(product);
        for (Product p : product)
            ID.remove(p.getId());
    }

    public Date getDate() {
        return Creation_date;
    }

    public int getLen() {
        return products.size();
    }


    public void clearCollection() {
        products.clear();
        ID.clear();
    }

    public Product getMinProduct() {
        return products.stream().sorted().toList().get(0);
    }

    public long MakeId() {
        Long id = 1L;
        while (ID.contains(id)) {
            id++;
        }
        return id;
    }

    public HashSet<Long> getID() {
        return ID;
    }
}


