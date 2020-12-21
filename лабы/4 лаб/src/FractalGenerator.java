import java.awt.geom.Rectangle2D;


/**
 * Этот класс предоставляет общий интерфейс и операции для фракталов.
 * генераторы, которые можно просмотреть в Fractal Explorer.
 */
public abstract class FractalGenerator {

    /**
     * Эта статическая вспомогательная функция принимает целочисленную координату и преобразует ее
     * в значение двойной точности, соответствующее определенному диапазону. это
     * используется для преобразования координат пикселей в значения двойной точности для
     * вычисление фракталов и др.
     *
     * @param rangeMin минимальное значение диапазона с плавающей запятой
     * @param rangeMax максимальное значение диапазона с плавающей запятой
     *
     * @param size размер измерения, от которого происходит координата пикселя.
     * Например, это может быть ширина изображения или высота изображения.
     *
     * @param coord - координата, для которой вычисляется значение двойной точности.
     * Координата должна находиться в диапазоне [0, размер].
     */
    public static double getCoord(double rangeMin, double rangeMax, int size, int coord) {

        assert size > 0;
        assert coord >= 0 && coord < size;

        double range = rangeMax - rangeMin;
        return rangeMin + (range * (double) coord / (double) size);
    }


    /**
     * Устанавливает указанный прямоугольник, чтобы он содержал начальный диапазон, подходящий для
     * генерируемый фрактал.
     */
    public abstract void getInitialRange(Rectangle2D.Double range);


    /**
     * Обновляет текущий диапазон, чтобы он был центрирован по указанным координатам,
     * и для увеличения или уменьшения с указанным коэффициентом масштабирования.
     */
    public void recenterAndZoomRange(Rectangle2D.Double range, double centerX, double centerY, double scale) {

        double newWidth = range.width * scale;
        double newHeight = range.height * scale;

        range.x = centerX - newWidth / 2;//Координата X этого Rectangle2D
        range.y = centerY - newHeight / 2;//Координата Y этого Rectangle2D
        range.width = newWidth;
        range.height = newHeight;
    }


    /**
     * Учитывая координату <em> x </em> + <em> iy </em> в комплексной плоскости,
     * вычисляет и возвращает количество итераций перед фракталом
     * функция выходит за пределы ограничивающей области для этой точки. Дело в том, что
     * не исчезает, пока не будет указан предел итераций
     * с результатом -1.
     */
    public abstract int numIterations(double x, double y);
    
}

