import java.awt.*;
import javax.swing.*;
import java.awt.geom.Rectangle2D;
import java.awt.event.*;
import javax.swing.JFileChooser.*;
import javax.swing.filechooser.*;
import javax.imageio.ImageIO.*;
import java.awt.image.*;

public class FractalExplorer {
    private int displaySize;
    private JImageDisplay display;
    private FractalGenerator fractal;
    private Rectangle2D.Double range;
    public FractalExplorer(int size) {
        displaySize = size;
        fractal = new Mandelbrot();
        range = new Rectangle2D.Double();
        fractal.getInitialRange(range);
        display = new JImageDisplay(displaySize, displaySize);
        
    }
    public void createAndShowGUI() {
        display.setLayout(new BorderLayout());//установка менеджера расположения, который нужне для того, чтобы
        // можно было через BorderLayout устонавливать расположение
        JFrame myFrame = new JFrame("Fractal Explorer");//главное окно приложения
        myFrame.add(display, BorderLayout.CENTER);

        JButton resetButton = new JButton("Reset");//создание кнопки сброса
        ButtonHandler resetHandler = new ButtonHandler();

        resetButton.addActionListener(resetHandler);//установка действия при нажатии на кнопкук

        MouseHandler click = new MouseHandler();
        display.addMouseListener(click);//установка действия при нажатии

        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//чтобы процесс завершался после закрытия окна

        JComboBox myComboBox = new JComboBox();//выпадающий список

        //Добавление пунктов списка

        FractalGenerator mandelbrotFractal = new Mandelbrot();
        myComboBox.addItem(mandelbrotFractal);

        FractalGenerator tricornFractal = new Tricorn();
        myComboBox.addItem(tricornFractal);

        FractalGenerator burningShipFractal = new BurningShip();
        myComboBox.addItem(burningShipFractal);

        ButtonHandler fractalChooser = new ButtonHandler();//действие при выборе типа фрактала
        myComboBox.addActionListener(fractalChooser);//установка действия при нажатии

        JPanel myPanel = new JPanel();//создание верхней панели
        JLabel myLabel = new JLabel("Fractal:");//добавление пояснения к выпадающему списку
        myPanel.add(myLabel);//добавление к панели пояснения
        myPanel.add(myComboBox);//добавление к панели выплывающего списка
        myFrame.add(myPanel, BorderLayout.NORTH);//расположение панели

        JButton saveButton = new JButton("Save");//добавление кнопки сохранения
        ButtonHandler saveHandler = new ButtonHandler();//вызов класса, который обрабатывает действия(сохранение)
        saveButton.addActionListener(saveHandler);//установка действия при нажатии на кнопку сохранения

        JPanel myBottomPanel = new JPanel();//панель для кнопок
        //добавление кнопок
        myBottomPanel.add(saveButton);
        myBottomPanel.add(resetButton);
        myFrame.add(myBottomPanel, BorderLayout.SOUTH);//расположение панели


/**
 * Разместите содержимое фрейма так, чтобы оно было видно, и
 * запретить изменение размера окна.
 */
        myFrame.pack();
        myFrame.setVisible(true);
        myFrame.setResizable(false);
    }
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

    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();//получить входную команду в виде String
            //Оператор instanceof нужен, чтобы проверить, был ли объект, на который ссылается переменная X, создан на основе какого-либо класса Y.
            if (e.getSource() instanceof JComboBox) {//(getSource)Возврат: Объект, на котором изначально произошло Событие.
                JComboBox mySource = (JComboBox) e.getSource();//меняем тип объекта с ActionEvent на JComboBox
                fractal = (FractalGenerator) mySource.getSelectedItem();//Возвращает текущий выбранный элемент(из выпадающего списка)
                fractal.getInitialRange(range);//установка диапазона
                drawFractal();//рисование фрактала
            }

            else if (command.equals("Reset")) {//если Reset, выполняем сброс
                fractal.getInitialRange(range);//задается диапазон(начальный)
                drawFractal();//рисуется фрактал
            }

            else if (command.equals("Save")) {//если Save, выполняем сохранение

                JFileChooser myFileChooser = new JFileChooser();//в какой файл он будет сохранять изображение
                FileFilter filter = new FileNameExtensionFilter("PNG Images", "png");//сохранять изображения только в формате PNG
                myFileChooser.setFileFilter(filter);//установка "фильтра" для "поисковика файлов"
                myFileChooser.setAcceptAllFileFilterUsed(false);//строка гарантирует, что средство выбора не разрешит
                //пользователю использование отличных от png форматов.

                int userSelection = myFileChooser.showSaveDialog(display);//метод showSaveDialog() открывает
                //диалоговое окно «Save file», позволяя тем самым пользователю выбрать
                //директорию для сохранения.

                if (userSelection == JFileChooser.APPROVE_OPTION) {//Если метод возвращает
                    //значение JfileChooser.APPROVE_OPTION, тогда можно продолжить операцию
                    //сохранения файлов, в противном случае, пользователь отменил операцию

                    java.io.File file = myFileChooser.getSelectedFile();//Если пользователь выбрал директорию для
                    //сохранения файла, вы можете ее узнать, используя метод getSelectedFile(), который возвращает объект типа File.
                    String file_name = file.toString();//записывается название файла

                    try {//Класс javax.imageio.ImageIO обеспечивает простые операции загрузки и сохранения изображения
                        BufferedImage displayImage = display.getImage();//создание переменную типа BufferedImage и присвоить ей изображение
                        javax.imageio.ImageIO.write(displayImage, "png", file);//сохранение изображения
                    }

                    catch (Exception exception) {//обработака исключения
                        JOptionPane.showMessageDialog(display, exception.getMessage(), "Cannot Save Image", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else return;
            }
        }
    }

    private class MouseHandler extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            double xCoord = fractal.getCoord(range.x, range.x + range.width, displaySize, x);
            
            int y = e.getY();
            double yCoord = fractal.getCoord(range.y, range.y + range.height, displaySize, y);

            fractal.recenterAndZoomRange(range, xCoord, yCoord, 0.5);

            drawFractal();
        }
    }

    public static void main(String[] args) {
        FractalExplorer displayExplorer = new FractalExplorer(800);
        displayExplorer.createAndShowGUI();
        displayExplorer.drawFractal();
    }
}