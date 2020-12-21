import java.awt.*;
import javax.swing.*;
import java.awt.geom.Rectangle2D;
import java.awt.event.*;

/**
 * Этот класс позволяет исследовать различные части фрактала с помощью
 * создание и отображение графического интерфейса Swing и обработка событий, вызванных различными
 * взаимодействия с пользователем.
 */
public class FractalExplorer {
    /** Целочисленный размер отображения - это ширина и высота отображения в пикселях. **/
    //1) Целое число «размер экрана», которое является шириной и высотой
    //отображения в пикселях. (Отображение фрактала будет квадратным.)
    //2) Ссылка JImageDisplay, для обновления отображения в разных
    //методах в процессе вычисления фрактала.
    //3) Объект FractalGenerator. Будет использоваться ссылка на базовый
    //класс для отображения других видов фракталов в будущем.
    //4) Объект Rectangle2D.Double, указывающий диапазона комплексной
    //плоскости, которая выводится на экран.
    private int displaySize;
    
    /**
     * Ссылка JImageDisplay для обновления отображения с помощью различных методов как
     * фрактал вычислен.
     */
    private JImageDisplay display;
    
    /**
     * Объект FractalGenerator, использующий ссылку на базовый класс для отображения
     * других типов фракталов в будущем.
     */
    private FractalGenerator fractal;
    
    /**
     * Объект Rectangle2D.Double, который определяет диапазон
     * то, что мы сейчас показываем.
     */
    private Rectangle2D.Double range;
    
    /**
     * Конструктор, который принимает размер дисплея, сохраняет его и
     * инициализирует объекты диапазона и фрактал-генератора.
     */
    public FractalExplorer(int size) {
        /** Stores display-size **/
        displaySize = size;
        
        /** Инициализирует фрактальный генератор и объекты диапазона. **/
        fractal = new Mandelbrot();//создаем переменную с типо Mandelbrot(подкласс FractalGenerator)
        range = new Rectangle2D.Double();//Создает новый объект Rectangle2D, инициализируемый местоположением (0, 0) и размером (0, 0).
        fractal.getInitialRange(range);//Устанавливает указанный прямоугольник, чтобы он содержал начальный диапазон, подходящий для генерируемый фрактал.
        //Эта реализация устанавливает начальный диапазон в (-2 - 1.5i) - (1 + 1.5i)
        //или x = -2, y = -1,5, width = height = 3.
        display = new JImageDisplay(displaySize, displaySize);//создаем новое изображение с назвением display с определенным размером
    
    }
    /**
     * Этот метод инициализирует графический интерфейс Swing с помощью JFrame, содержащего
     * JImageDisplay объект и кнопка для сброса дисплея.
     */
    public void createAndShowGUI() {
        /**Установите для фрейма использование java.awt.BorderLayout для его содержимого. **/
        //отображения элементов в виде таблицы с одинаковыми размерами ячеек(BorderLayout)
        //саом изображение
        display.setLayout(new BorderLayout());//установка менеджера расположения, который нужне для того, чтобы
        // можно было через BorderLayout устонавливать расположение
        JFrame myframe = new JFrame("Fractal Explorer");//главное окно приложения
        
        /**
         * Добавьте объект отображения изображения в BorderLayout.CENTER
         * позиция.
         */
        myframe.add(display, BorderLayout.CENTER);//позиция myframe
        
        /**Создайте кнопку сброса. */
        JButton resetButton = new JButton("Reset Display");//создание кнопки сброса
        
        /** Экземпляр ResetHandler на кнопке сброса.
         * Созданный ниже*/
        ResetHandler handler = new ResetHandler();
        resetButton.addActionListener(handler);//установка действия при нажатии кнопки сброса
        
        /** Добавьте кнопку сброса в позицию BorderLayout.SOUTH. */
        myframe.add(resetButton, BorderLayout.SOUTH);//позиция кнопки сброса
        
        /** Экземпляр MouseHandler в компоненте фрактального отображения. */
        MouseHandler click = new MouseHandler();
        display.addMouseListener(click);//установка действия при нажатии
        
        /** Установите операцию закрытия фрейма по умолчанию на «выход». */
        myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//чтобы процесс завершался после закрытия окна
        
        /**
         * Разместите содержимое фрейма так, чтобы оно было видно, и
         * запретить изменение размера окна.
         */
        myframe.pack();
        myframe.setVisible(true);
        myframe.setResizable(false);
    }
    
    /**
     * Приватный вспомогательный метод для отображения фрактала. Этот метод зацикливается
     * через каждый пиксель на дисплее и вычисляет количество
     * итераций для соответствующих координат во фрактале
     * Область отображения. Если количество итераций равно -1, установите цвет пикселя
     * в черный. В противном случае выберите значение, основанное на количестве итераций.
     * Обновите дисплей цветом для каждого пикселя и перекрасите
     * JImageDisplay, когда все пиксели нарисованы.
     */
    private void drawFractal() {
        /**Просматривайте каждый пиксель на дисплее */
        for (int x=0; x<displaySize; x++){
            for (int y=0; y<displaySize; y++){
                
                /**
                 * Найдите соответствующие координаты xCoord и yCoord
                 * в области отображения фрактала.
                 */
                double xCoord = fractal.getCoord(range.x, range.x + range.width, displaySize, x);//дано в задании
                //x - пиксельная координата; xCoord - координата в пространстве фрактала
                double yCoord = fractal.getCoord(range.y, range.y + range.height, displaySize, y);
                
                /**
                 * Вычислить количество итераций для координат в
                 * область отображения фрактала.
                 */
                /**
                 * numIterations - Этот метод реализует итерационную функцию для фрактала Мандельброта.
                 * Требуется два дубля для действительной и мнимой частей комплекса
                 * plane и возвращает количество итераций для соответствующего
                 * координата.
                 */
                int iteration = fractal.numIterations(xCoord, yCoord);
                
                /** Если количество итераций равно -1, установите черный пиксель. */
                if (iteration == -1){
                    display.drawPixel(x, y, 0);
                }
                
                else {
                    /**
                     * В противном случае выберите значение оттенка на основе числа
                     * итераций.
                     */
                    float hue = 0.7f + (float) iteration / 200f;//дано в задании
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                
                    /** Обновите дисплей цветом для каждого пикселя. */
                    display.drawPixel(x, y, rgbColor);
                }
                
            }
        }
        /**
         * Когда все пиксели будут нарисованы, перекрасить JImageDisplay в соответствии с
         * текущее содержимое его изображения.
         */
        display.repaint();//Перерисовывает этот компонент
    }
    /**
     * Внутренний класс для обработки событий ActionListener от кнопки сброса.
     */
    private class ResetHandler implements ActionListener {
        /**
         Обработчик сбрасывает диапазон до начального диапазона, заданного
         * генератор, а затем рисует фрактал.
         */
        public void actionPerformed(ActionEvent e) {
            fractal.getInitialRange(range);//установка изначального диапазона
            //range.x = -2;
            //range.y = -1.5;
            //range.width = 3;
            //range.height = 3;
            drawFractal();//вызов метода для рисования фрактала
        }
    }
    /**
     * Внутренний класс для обработки событий MouseListener с дисплея.
     */
    private class MouseHandler extends MouseAdapter {
        /**
         * Когда обработчик получает событие щелчка мыши, он отображает пиксель-
         * координаты клика в области фрактала, который
         * отображается, а затем вызывает функцию RecenterAndZoomRange () генератора
         * метод с координатами, по которым был выполнен щелчок, и шкалой 0,5.
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            /** Получить координату x области отображения щелчка мыши. */
            int x = e.getX();
            double xCoord = fractal.getCoord(range.x, range.x + range.width, displaySize, x);//дано в задании

            
            /** Получить координату y области отображения щелчка мышью. */
            int y = e.getY();
            double yCoord = fractal.getCoord(range.y, range.y + range.height, displaySize, y);//дано в задании
            
            /**
             * Вызвать метод генератора RecenterAndZoomRange () с
             * координаты, по которым был выполнен щелчок, и масштаб 0,5.
             */
            fractal.recenterAndZoomRange(range, xCoord, yCoord, 0.5);
            
            /**
             * Redraw the fractal after the area being 
             * displayed has changed.
             */
            drawFractal();//вызов метода для рисования фрактала
        }
    }
    
    /**
     * Статический метод main () для запуска FractalExplorer. Инициализирует новый
     * Экземпляр FractalExplorer с размером дисплея 800, вызывает
     * createAndShowGUI () в объекте проводника, а затем вызывает
     * drawFractal () в проводнике, чтобы увидеть исходный вид.
     */
    public static void main(String[] args)
    {
        FractalExplorer displayExplorer = new FractalExplorer(800);
        displayExplorer.createAndShowGUI();
        displayExplorer.drawFractal();//вызов метода для рисования фрактала
    }
}