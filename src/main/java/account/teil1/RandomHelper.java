package account.teil1;

import java.util.Random;

public class RandomHelper {
    public static double getRandomDouble(double min, double max) {
        Random random = new Random();
        return min + (max-min) * random.nextDouble();
    }
}
