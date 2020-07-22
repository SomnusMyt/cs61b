public class RabinKarpAlgorithm {


    /**
     * This algorithm returns the starting index of the matching substring.
     * This method will return -1 if no matching substring is found, or if the input is invalid.
     */
    public static int rabinKarp(String input, String pattern) {
        int inputLength = input.length();
        int patternLength = pattern.length();
        if (patternLength > inputLength) {
            return -1;
        }

        String tinput = input.substring(0, patternLength);
        RollingString inputRS = new RollingString(tinput, patternLength);
        RollingString patternRS = new RollingString(pattern, patternLength);

        int hpattern = patternRS.hashCode();

        for (int i = 0; i < inputLength - patternLength; i++) {
            int hs = inputRS.hashCode();
            if (hs == hpattern) {
                if (inputRS.equals(patternRS)) {
                    return i;
                }
            }
            inputRS.addChar(input.charAt(i + patternLength));
        }

        return -1;
    }

}
