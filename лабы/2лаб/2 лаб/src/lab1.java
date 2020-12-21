import java.text.DecimalFormat;
import java.util.Scanner;
public class lab1 extends Point3d
{
    public static void main(String[] args)
    {
        int check = 0;


        Scanner in = new Scanner(System.in);

        System.out.print("Введите по очереди координаты для первой точки: " + "\n");
        double a = in.nextInt();
        double b = in.nextInt();
        double c = in.nextInt();
        System.out.print("Введите по очереди координаты для второй точки: " + "\n");
        double a1 = in.nextInt();
        double b1 = in.nextInt();
        double c1 = in.nextInt();
        System.out.print("Введите по очереди координаты для третьей точки: " + "\n");
        double a2 = in.nextInt();
        double b2 = in.nextInt();
        double c2 = in.nextInt();

        Point3d Point = new Point3d(a,b,c);
        Point3d Point1 = new Point3d(a1,b1,c1);
        Point3d Point2 = new Point3d(a2,b2,c2);
        in.close();

        Point.setX(a);
        Point1.setX(a1);
        Point2.setX(a2);

        Point.setY(b);
        Point1.setY(b1);
        Point2.setY(b2);

        Point.setZ(c);
        Point1.setZ(c1);
        Point2.setZ(c2);


        if (Area(Point, Point1, Point2) == 0 || compare(Point, Point2)||compare(Point2, Point1)||compare(Point, Point1)||!Exist(Point, Point1, Point2))	// Проверка координат точек
        {
            if (Area(Point, Point1, Point2) == 0){
                System.out.println("Все точки лежат на одной прямой.");
            }
            System.out.println("Треугольник не существует.");
            check ++;
        }

        if (check == 0)
        {
                System.out.println("Площадь треугольника: ");
                double otvet = (Area(Point, Point1, Point2));
                otvet *= 100;
                otvet = (int) otvet;
                System.out.println(otvet/100);//Ответ с точностью до 2-го знака после запятой
        }
    }

    public static double Area(Point3d a, Point3d b, Point3d c)				// Нахождение площади
    {
        double Side1 = distanceTo(a, b), Side2 = distanceTo(a, c), Side3 = distanceTo(c, b);
        double per = (Side1 + Side2 + Side3) / 2;
        return Math.sqrt(per * (per - Side1) * (per - Side2) * (per - Side3));
    }

    public static boolean Exist(Point3d a, Point3d b, Point3d c){
        if (distanceTo(a, b)> distanceTo(a, c)+distanceTo(b, c) ){
            return false;
        }
        else if (distanceTo(a, c)> distanceTo(a, b)+distanceTo(b, c)){
            return false;
        }
        else return !(distanceTo(b, c) > distanceTo(a, c) + distanceTo(a, b));
    }
}