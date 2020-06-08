/** A client that uses the synthesizer package to replicate a plucked guitar string sound */
import es.datastructur.synthesizer.GuitarString;

public class GuitarHeroLite {
    private static final double CONCERT_A = 440.0;
    private static final double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);

    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        GuitarString stringA = new GuitarString(CONCERT_A);
        GuitarString stringC = new GuitarString(CONCERT_C);
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarString stringKey = new GuitarString(CONCERT_C);;
        while (true) {
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                System.out.println(index);
                if (key == 'a') {
                    stringA.pluck();
                } else if (key == 'c') {
                    stringC.pluck();
                } else if (index != -1) {
                    stringKey = new GuitarString(440.0 * Math.pow(2, (index - 24.0) / 12.0));
                    stringKey.pluck();
                }
            }

        /* compute the superposition of samples */
            double sample = stringA.sample() + stringC.sample() + stringKey.sample();

        /* play the sample on standard audio */
            StdAudio.play(sample);

        /* advance the simulation of each guitar string by one step */
            stringA.tic();
            stringC.tic();
            stringKey.tic();
        }
    }
}

