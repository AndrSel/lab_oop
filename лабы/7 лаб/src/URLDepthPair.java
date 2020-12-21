import java.net.*;
public class URLDepthPair {

    private final String URL;
    private final int depth;

    public URLDepthPair(String URL, int depth) {
        this.URL = URL;
        this.depth = depth;
    }
    /**
     * Возвращает объект класса типа URL(полный путь до сайта)
     */
    public String getURL() {
        return URL;
    }
    /**
     * Возвращает глубину сайта, относительно сайта введёного пользователем.
     */
    public int getDepth() {
        return depth;
    }
    /**
     * Возвращает строку состаящую из адреса сайта и его глубины.
     */
    public String toString() {
        String stringDepth = Integer.toString(depth);//перевод из int в String
        return stringDepth + '\t' + URL;
    }

    //В случае, если вы найдете URL-адрес, который не начинается с «http: //»,
    //вы должны выдать исключение MalformedURLException, которое является
    //частью Java API.

    public String getDocPath() {
        try {
            URL url = new URL(URL);
            return url.getPath();//Получает часть пути URL.
        }
        catch (MalformedURLException e) {//исключение
            return null;
        }
    }

    public String getWebHost() {
        try {
            URL url = new URL(URL);
            return url.getHost();//Функция getHost () возвращает Host указанного URL
        }
        catch (MalformedURLException e) {
            return null;
        }
    }
}