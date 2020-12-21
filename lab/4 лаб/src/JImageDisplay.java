import javax.swing.*;
import java.awt.image.*;
import java.awt.*;

/**
 * T
 * его класс позволяет нам отображать наши фракталы.
 *  * Он является производным от javax.swing.JComponent.
 */
class JImageDisplay extends javax.swing.JComponent {
    /**
     * Экземпляр буферизованного изображения.
     * Управляет изображением, содержимое которого мы можем изменять.
     */ 
    private BufferedImage displayImage;//Класса должен иметь одно поле с типом доступа
    //private, экземпляп java.awt.image.BufferedImage.
    
    /**
     * Конструктор принимает целые значения ширины и высоты и инициализирует
      * его объект BufferedImage должен быть новым изображением с такой шириной и высотой
      * типа изображения TYPE_INT_RGB.
      */
    public JImageDisplay(int width, int height) {
        //Класс Java BufferedImage является подклассом класса Image. Он используется для обработки и управления данными изображения.
        //добавление нового изображения
        displayImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);//Представляет изображение с 8-битными цветовыми компонентами RGB, упакованными в целые пиксели.

        /**
         * Вызвать метод setPreferredSize () родительского класса
         * с заданной шириной и высотой.
         */
        //Dimension класс инкапсулирует ширину и высоту компонента (в целочисленной точности) в единственном объекте.
        Dimension imageDimension = new Dimension(width, height);
        //устанавливает предпочтительный размер
        super.setPreferredSize(imageDimension);//вызвать метод setPreferredSize()родительского класса метод с указанной шириной и высотой
        //super - Для вызова конструктора суперкласса
    }
    /**
     * Реализация суперкласса paintComponent (g) называется границей и
     * черты нарисованы правильно. Затем изображение втягивается в компонент
     */
    @Override
    public void paintComponent(Graphics g) {
        //вызов метода супер класса paintComponent (g)
        super.paintComponent(g);
        //super - Для вызова конструктора суперкласса
        g.drawImage(displayImage, 0, 0, displayImage.getWidth(), displayImage.getHeight(), null);//дано в условии
        //x,y - отступы
    }
    /**
     * Устанавливает все пиксели в данных изображения черными.
     */

    public void clearImage() {
        //Устанавливает массив целочисленных пикселей в цветовой модели
        // RGB по умолчанию (TYPE_INT_ARGB) и цветовом пространстве sRGB по умолчанию в часть данных изображения.
        int[] blankArray = new int[getWidth() * getHeight()];//[размер сколько на сколько пикселей]
        //Устанавливает массив целочисленных пикселей в цветовой модели RGB по умолчанию (TYPE_INT_ARGB)
        // и цветовом пространстве sRGB по умолчанию в часть данных изображения.
        displayImage.setRGB(0, 0, getWidth(), getHeight(), blankArray, 0, 1);
        //scansize - шаг развертки для rgbArray
        //offset - смещение в rgbArray
    }
    /**
     * Устанавливает пиксель определенного цвета.
     */
    public void drawPixel(int x, int y, int rgbColor) {
        displayImage.setRGB(x, y, rgbColor);
    }
}