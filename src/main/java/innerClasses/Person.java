package innerClasses;

import enums.Country;
import enums.EyeColor;
import enums.HairColor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Person implements Comparable<Person>, Serializable {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String passportID; //Поле не может быть null
    private EyeColor eyeColor; //Поле может быть null
    private HairColor hairColor; //Поле может быть null
    private Country nationality; //Поле не может быть null

    public Person() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public static boolean checkName(String name) {
        return !(name == null || name.isEmpty());
    }

    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }

    public static boolean checkPassportId(String passportID) {
        return !(passportID == null || passportID.isEmpty());
    }

    public void setEyeColor(EyeColor eyeColor) {
        this.eyeColor = eyeColor;
    }

    public void setHairColor(HairColor hairColor) {
        this.hairColor = hairColor;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public Country getNationality() {
        return nationality;
    }

    public EyeColor getEyeColor() {
        return eyeColor;
    }

    public HairColor getHairColor() {
        return hairColor;
    }

    public String getPassportID() {
        return passportID;
    }

    @Override
    public String toString() {
        return "Person{" + "\n" +
                "\t\t" + "name='" + name + '\'' + ",\n" +
                "\t\t" + "passportID='" + passportID + '\'' + ",\n" +
                "\t\t" + "eyeColor=" + eyeColor + ",\n" +
                "\t\t" + "hairColor=" + hairColor + ",\n" +
                "\t\t" + "nationality=" + nationality + "\n" +
                "\t" + '}';
    }

    @Override
    public int compareTo(Person o) {
        return this.name.toLowerCase().compareTo(o.getName().toLowerCase());
    }

    public static Person createPerson(Scanner sc, boolean isFileReading) {
        Person productOwner = new Person();
        int step = 0;
        List<String> messages = new ArrayList<>();
        messages.add("Введите имя владельца(не может быть пустым)");
        messages.add("Введите ID паспорта владельца");
        messages.add("Введите цвет глаз владельца(введите одно из 3-х слов: green, white, brown. Цвет глаз может быть null)");
        messages.add("Введите цвет волос владельца(введите одно из 4-х слов: green, black, orange, white. Цвет волос может быть null)");
        messages.add("Введите национальность владельца(введите одно из 5 слов: united_kingdom, usa, spain, italy, north_korea");

        if (!isFileReading) {
            System.out.println("Пожалуйста, следуйте указаниям по вводу, чтобы создать нового человека(владельца товара).\n Если вы хотите прервать ввод и вернуться к другим командам, введите exit. \nТакже вы можете вернуться к предыдущему параметру, введя z (но будьте осторожны! предыдущее значение будет стерто). \nДля введения в поле значения null просто введите пустую строку");
        }
        String input = "";
        while (step != -1 && step != 5) {
            if (!isFileReading) {
                System.out.println(messages.get(step));
            }
            try {
                input = sc.nextLine();
            } catch (NoSuchElementException e) {
                System.out.println("файл кончился, процесс создания продукта не завершен");
                return null;
            }
            if (input.equals("exit")) {
                step = -1;
                break;
            }
            if (input.equals("z")) {
                step--;
                continue;
            }
            switch (step) {
                //product name
                case (0) -> {
                    if (!Person.checkName(input)) {
                        System.out.println("Некорректное имя! Пожалуйста, следуйте указаниям по вводу");
                    } else {
                        step++;
                        productOwner.setName(input);
                    }
                }
                case (1) -> {
                    if (!Person.checkPassportId(input)) {
                        System.out.println("Некорректное ID паспорта! Пожалуйста, следуйте указаниям по вводу");
                    } else {
                        step++;
                        productOwner.setPassportID(input);
                    }
                }
                case (2) -> {
                    if(!input.isEmpty()) {
                        try {
                            productOwner.setEyeColor(EyeColor.valueOf(input.toUpperCase()));
                        } catch (Exception e) {
                            System.out.println("Некорректный цвет глаз! Пожалуйста, следуйте указаниям по вводу");
                            break;
                        }
                    }
                    step++;
                }
                case (3) -> {
                    if(!input.isEmpty()) {
                        try {
                            productOwner.setHairColor(HairColor.valueOf(input.toUpperCase()));
                        } catch (Exception e) {
                            System.out.println("Некорректный цвет волос! Пожалуйста, следуйте указаниям по вводу");
                            break;
                        }
                    }
                    step++;
                }
                case (4) -> {
                    try {
                        productOwner.setNationality(Country.valueOf(input.toUpperCase()));
                    } catch (Exception e) {
                        System.out.println("Некорректная национальность! Пожалуйста, следуйте указаниям по вводу");
                        break;
                    }
                    step++;
                }
                //ProductCollection.PRODUCT_COLLECTION.addProducts(addedProduct);
            }
        }
        if (step == -1) {
            System.out.println("Процесс создания Person был прерван");
            return null;
        } else {
            System.out.println("человек был успешно создан");
            return productOwner;
        }
    }
}
