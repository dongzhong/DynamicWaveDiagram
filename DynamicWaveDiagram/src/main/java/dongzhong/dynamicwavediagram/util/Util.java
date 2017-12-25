package dongzhong.dynamicwavediagram.util;

/**
 * Created by dongzhong on 2017/12/25.
 */

public class Util {
    public static Number max(Number... numbers) {
        if (numbers.length <= 0) {
            return null;
        }
        Number max = numbers[0];
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i].floatValue() > max.floatValue()) {
                max = numbers[i];
            }
        }

        return max;
    }

    public static Number min(Number... numbers) {
        if (numbers.length <= 0) {
            return null;
        }
        Number min = numbers[0];
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i].floatValue() < min.floatValue()) {
                min = numbers[i];
            }
        }

        return min;
    }
}
