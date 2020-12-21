import java.io.IOException;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) throws IOException {

        String[] arg = new String[] {"https://www.twitch.tv/modestal","1","1"};
        int depth = 0;
        int numThreads = 0;
        if (arg.length != 3) {

            System.out.println("usage: java Crawler <URL> <depth> "
                    + "<number of crawler threads>");
            System.exit(1);
        }
        try {
            depth = Integer.parseInt(arg[1]);
            numThreads = Integer.parseInt(arg[2]);
        }
        catch (NumberFormatException nfe) {
            System.out.println("usage: java Crawler <URL> <depth> "
                    + "<number of crawler threads>");
            System.exit(1);
        }
        URLDepthPair currentDepthPair = new URLDepthPair(arg[0], 0);
        URLPool pool = new URLPool(depth);
        pool.put(currentDepthPair);
        int initialActiveThreads = Thread.activeCount();
        while (pool.getWaitThreads() != numThreads) {
            if (Thread.activeCount() - initialActiveThreads < numThreads) {
                CrawlerTask crawler = new CrawlerTask(pool);
                new Thread(crawler).start();
            }
            else {
                try {
                    Thread.sleep(500); // 0,5 секунды
                }
                catch (InterruptedException ie) {
                    System.out.println("Caught unexpected InterruptedException,"
                            + " ignoring...");
                }
            }
        }
        Iterator<URLDepthPair> iter = pool.processedURLs.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
        System.exit(0);
    }
}
