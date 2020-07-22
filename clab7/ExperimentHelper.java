/**
 * Created by hug.
 */
public class ExperimentHelper {

    /** Returns the internal path length for an optimum binary search tree of
     *  size N. Examples:
     *  N = 1, OIPL: 0
     *  N = 2, OIPL: 1
     *  N = 3, OIPL: 2
     *  N = 4, OIPL: 4
     *  N = 5, OIPL: 6
     *  N = 6, OIPL: 8
     *  N = 7, OIPL: 10
     *  N = 8, OIPL: 13
     */
    public static int optimalIPL(int N) {
        int time = 0;
        int l = 0;
        int OIPL = 0;
        while (N > 0) {
            N--;
            time++;
            OIPL += l;
            if (time == (Math.pow(2, l+1) - 1)) {
                l++;
            }
        }
        return OIPL;
    }

    /** Returns the average depth for nodes in an optimal BST of
     *  size N.
     *  Examples:
     *  N = 1, OAD: 0
     *  N = 5, OAD: 1.2
     *  N = 8, OAD: 1.625
     * @return
     */
    public static double optimalAverageDepth(int N) {

        return (double)optimalIPL(N)/N;
    }

    public static void main(String[] args) {
        System.out.println(optimalIPL(1));
        System.out.println(optimalIPL(2));
        System.out.println(optimalIPL(3));
        System.out.println(optimalIPL(4));
        System.out.println(optimalIPL(5));
        System.out.println(optimalIPL(6));
        System.out.println(optimalIPL(7));
        System.out.println(optimalIPL(8));

        System.out.println(optimalAverageDepth(1));
        System.out.println(optimalAverageDepth(5));
        System.out.println(optimalAverageDepth(8));

    }
}
