package labs.lab2;

/**
 * Created by alexandr on 18.10.16.
 */
public class Vector {

    /*Вектор: полярні координати, конструктор, методи обчислення коор-
динат кінця вектора, виведення об'єкта
Ділення Координата
X*/

    private double length;
    private float angle;

    public Vector(double length, float angle) {
        this.length = length;
        this.angle = angle;
    }

    public Vector() {
        this.length = Math.random() * 100;
        this.angle = (float) (Math.random() * 360);
    }

    public double getX() {
        return length * Math.cos(angle);
    }

    public double getY() {
        return length * Math.sin(angle);
    }

    public double getLength() {
        return length;
    }

    public float getAngle() {
        return angle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector vector = (Vector) o;

        if (Double.compare(vector.length, length) != 0) return false;
        return Float.compare(vector.angle, angle) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        result = (int) (getX());
        return result;
    }

    @Override
    public String toString() {
        return "Vector{" +
                "length=" + length +
                ", angle=" + angle +
                ", X=" + getX() +
                ", Y=" + getY() +
                '}';
    }


}
