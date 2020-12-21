import java.util.Objects;

public class Location
{
    public int xCoord;
    public int yCoord;

    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }

    public Location() {
        this(0, 0);
    }
    @Override
    public boolean equals(Object o) {//определяет, равен ли один объект другому
        if (this == o) return true;//Любой объект должен быть equals() самому себе.
        if (o == null || getClass() != o.getClass()) return false;//сравнение объектов двух одинаковых классов
        Location location = (Location) o;
        return this.xCoord == location.xCoord && this.yCoord == location.yCoord;
    }
    @Override
    public int hashCode() {
        return Objects.hash(xCoord, yCoord);//Возвращает хеш-код, связанный с вызывающим объектом
    }
}
