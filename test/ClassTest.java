import org.springframework.util.StopWatch;

/**
 * Description
 *
 * @author: Administrator
 * @date: 2018/11/10 0010
 */
public class ClassTest {

    public static void main(String[] args) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("hello");
        Thread.sleep(1000);
        stopWatch.stop();
        stopWatch.start("watch");
        Thread.sleep(500);
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }
}
