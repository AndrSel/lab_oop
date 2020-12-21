import java.util.Scanner;

public class Point3d extends Point2d {

      static  double sqr (double a ) {                          //метод для возведения числа в квадрат
        double result = a*a;
        return result;
    }
    public static void main(String[] args)

    {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите по очереди координаты для первой точки: " + "\n");
        double a = in.nextInt();
        double b = in.nextInt();
        double c = in.nextInt();
        System.out.print("Введите по очереди координаты для второй точки: " + "\n");
        double a1 = in.nextInt();
        double b1 = in.nextInt();
        double c1 = in.nextInt();
        Point3d Point = new Point3d(a,b,c);
        Point3d Point1 = new Point3d(a1,b1,c1);

        Point.setX(a);
        Point1.setX(a1);

        Point.setY(b);
        Point1.setY(b1);

        in.close();                                             //Останавливаем работу Scanner

        if(compare(Point, Point1))                              //Сравниваем координаты точек
            System.out.println("Точки имеют одинаковые координаты.");
        else {
            System.out.println("Точки имеют разные координаты.");//Если расстояние между точками разное
            System.out.println("Расстояние между точками: ");   //Рассчитываем расстояние между точками
            System.out.print(distanceTo(Point, Point1));
        }
    }

    private double zCoord;                                          // Координата Z
    private double xCoord1;                                         // Координата x
    private double yCoord1;                                         // Координата y

    public Point3d(double x, double y, double z)					// Конструктор инициализации
    {
        xCoord1 = x;
        yCoord1 = y;
        // Координата X
        // Координата Y
        zCoord = z;
    }
    public Point3d()
    {
        this(0, 0, 0);
    }

   public double getZ()											    // Возвращение координаты Z
   {
       return zCoord;
   }
    public void setZ(double val)								    // Установка значения координаты Z
    {
        zCoord = val;
    }
    public static boolean compare(Point3d t, Point3d i)				// Сравнение значений точек
    {
        return (t.getX() == i.getX()) && (t.getY() == i.getY()) && (t.getZ() == i.getZ());
    }

    public static double distanceTo(Point3d a, Point3d b)			// Нахождение расстояния между точками
    {
        double Round = Math.sqrt(sqr((b.getX()-a.getX())) + sqr((b.getY()-a.getY())) + sqr((b.getZ()-a.getZ())));
        Round *= 100;
        Round = (int) Round;
        return (Round / 100);
    }
}

