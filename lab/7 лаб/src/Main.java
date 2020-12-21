import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
//http://www.mstuca.ru/
//http://mtuci.ru/
//http://www.rudn.ru/
//http://ege.edu.ru/ru/
public class Main {
//Двусвязная реализация списка из List интерфейсов
//каждый элемент структуры содержит указатели на предыдущий и следующий элементы
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        String a = in.nextLine();
        String b = in.nextLine();
        String[] arg = new String[] {a,b};
        int depth = 0;
        try {
            depth = Integer.parseInt(arg[1]);
            //System.out.println(depth);
        }
        catch (NumberFormatException nfe) {
            System.out.println("usage: java Crawler <URL> <depth>");
            System.exit(1);
        }
        LinkedList<URLDepthPair> pendingURLs = new LinkedList<URLDepthPair>();//в ожидании urls
        LinkedList<URLDepthPair> processedURLs = new LinkedList<URLDepthPair>();//обработанные urls
        URLDepthPair currentDepthPair = new URLDepthPair(arg[0], 0);//
        pendingURLs.add(currentDepthPair);//добавление в ожидание новой ссылки(arg)
        ArrayList<String> seenURLs = new ArrayList<String>();//просмотренные urls
        seenURLs.add(currentDepthPair.getURL());//запись url

        while (pendingURLs.size() != 0) {//пока есть urls
            URLDepthPair depthPair = pendingURLs.pop();//Метод pop() в Java используется для удаления объекта в верхней части
            // этого стека и возвращает этот объект в качестве значения этой функции
            processedURLs.add(depthPair);//добавляем url к обработанным
            int myDepth = depthPair.getDepth();//определение глубины
            LinkedList<String> linksList = Crawler.getAllLinks(depthPair);//все ссылки доступные у обрабатываемой ссылки
            if (myDepth < depth) {
                for (int i=0;i<linksList.size();i++) {//перебор linksList
                    String newURL = linksList.get(i);
                    if (seenURLs.contains(newURL)) {//contains()- метод чтобы проверить, содержит ли String указанную последовательность символов
                        continue;
                    }
                    else {
                        URLDepthPair newDepthPair = new URLDepthPair(newURL, myDepth + 1);//запись уникального url с увеличением глубины
                        pendingURLs.add(newDepthPair);//(сохраняется сама ссылка)
                        seenURLs.add(newURL);//url добавляется к просмотренным(сохраняется назваение)
                    }
                }
            }
        }
        //Используем итератор для перебора списка processedURLs
        Iterator<URLDepthPair> iter = processedURLs.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}
