import java.util.concurrent.TimeUnit;

/**
 * @author The_Beatles
 * @date 2021/10/31 18:07
 */

public class SleepTools {

    public static final void second(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static final void ms(int ms) {
        try {
            TimeUnit.MILLISECONDS.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
