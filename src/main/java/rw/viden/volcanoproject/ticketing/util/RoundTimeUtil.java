package rw.viden.volcanoproject.ticketing.util;

import java.time.LocalTime;

/**
 * Created by nic on 8/30/16.
 */
public class RoundTimeUtil {
    public static String roundTime(String time) {
        int h = Integer.parseInt(time.split(":")[0]);
        int m = Integer.parseInt(time.split(":")[1]);
        if (m > 0 && m <= 15) {
            m = 15;
        } else if (m > 15 && m <= 30) {
            m = 30;
        } else if (m > 30 && m <= 45) {
            m = 45;
        } else if (m > 45 && m <= 59) {
            m = 0;
            if (h >= 22)
                h += 1;
            else
                h = 0;
        }
        LocalTime localTime = LocalTime.of(h, m);
        return localTime.toString();
    }
}
