import java.util.Scanner;

public class Point2d
{
    private double xCoord;								// Координата X
    private double yCoord;


    public Point2d(double x, double y)					// Конструктор инициализации
    {
        xCoord = x;
        yCoord = y;
    }
    public Point2d()                                    //Конструктор по умолчанию
    {
        this(0,0);                                 //Вызовите конструктор с двумя параметрами и определите источник
    }
    public double getX()								// Возвращение координаты X
    {
        return xCoord;
    }
    public double getY()								// Возвращение координаты Y
    {
        return yCoord;
    }
    public void setX(double val)						// Установка значения координаты X
    {
        xCoord = val;
    }
    public void setY(double val)						// Установка значения координаты Y
    {
        yCoord = val;
    }
    public static boolean compare(Point2d a, Point2d b)	// Сравнение значений точек
    {
        if ((a.getX() == b.getX()) && (a.getY() == b.getY()))
            return true;
        else
            return false;
    }

}

class test extends Point2d{

    public static void test1(Point2d a){
        System.out.println(a.getX()+" "+ a.getY());
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double a = in.nextInt();
        double b = in.nextInt();
        //double с = in.nextInt();
        double a1 = in.nextInt();
        double b1 = in.nextInt();
        //double с1 = in.nextInt();
        Point2d point1 = new Point2d(a, b);
        Point2d point2 = new Point2d(a1, b1);
        point1.setX(a);
        point1.setY(b);
        //point1.setY(c);
        point2.setX(a1);
        point2.setY(b1);
        //point2.setY(c1);
        if(compare(point1,point2)){
            System.out.println("да");
        }
        else System.out.println("нет");

        test1(point1);
    }
}