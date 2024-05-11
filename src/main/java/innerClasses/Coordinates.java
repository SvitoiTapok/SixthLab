package innerClasses;

import java.io.Serializable;

/**
 * Класс содержащий координаты продукта
 */

public class Coordinates implements Serializable {
    /**
     * Значение поля должно быть больше -627
     */
    private double x;
    /**
     * Максимальное значение поля: 677, Поле не может быть null
     */
    private Float y; //

    public Coordinates(double x, Float y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates() {}

    /**
     * устанавливает данное значение x и возвращает true если переданное значение удовлетворяет ОДЗ
     */
    public void setX(double x) {
        this.x = x;
    }

    public static boolean checkX(double x){
        return x>-627;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public static boolean checkY(Float y){
        return y<677;
    }

    public double getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}
