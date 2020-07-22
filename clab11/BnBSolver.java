import java.util.ArrayList;
import java.util.List;

/**
 * BnBSolver for the Bears and Beds problem. Each Bear can only be compared to Bed objects and each Bed
 * can only be compared to Bear objects. There is a one-to-one mapping between Bears and Beds, i.e.
 * each Bear has a unique size and has exactly one corresponding Bed with the same size.
 * Given a list of Bears and a list of Beds, create lists of the same Bears and Beds where the ith Bear is the same
 * size as the ith Bed.
 */
public class BnBSolver {

    public List<Bear> sortedBears;
    public List<Bed> sortedBeds;

    public BnBSolver(List<Bear> bears, List<Bed> beds) {
        // TODO: Fix me.
        quickSort(bears, beds);
    }

    /**
     * Returns List of Bears such that the ith Bear is the same size as the ith Bed of solvedBeds().
     */
    public List<Bear> solvedBears() {
        // TODO: Fix me.
        return sortedBears;
    }

    /**
     * Returns List of Beds such that the ith Bear is the same size as the ith Bear of solvedBears().
     */
    public List<Bed> solvedBeds() {
        // TODO: Fix me.
        return sortedBeds;
    }

    private List<Bear> catenateBear(List<Bear> l1, List<Bear> l2) {
        List<Bear> catenated = new ArrayList<>();
        for (Bear bear : l1) {
            catenated.add(bear);
        }
        for (Bear bear : l2) {
            catenated.add(bear);
        }
        return catenated;
    }

    private List<Bed> catenateBed(List<Bed> l1, List<Bed> l2) {
        List<Bed> catenated = new ArrayList<>();
        for (Bed bed : l1) {
            catenated.add(bed);
        }
        for (Bed bed : l2) {
            catenated.add(bed);
        }
        return catenated;
    }

    private void partition(List<Bear> unsorted, Bed pivot, List<Bear> less, List<Bear> equal, List<Bear> greater) {
        for (Bear bear : unsorted) {
            int cmp = bear.compareTo(pivot);
            if (cmp == 1) {
                less.add(bear);
            }
            else if (cmp == 0) {
                equal.add(bear);
            }
            else {
                greater.add(bear);
            }
        }
    }
    private void partition(List<Bed> unsorted, Bear pivot, List<Bed> less, List<Bed> equal, List<Bed> greater) {
        for (Bed bed : unsorted) {
            int cmp = bed.compareTo(pivot);
            if (cmp == 1) {
                less.add(bed);
            }
            else if (cmp == 0) {
                equal.add(bed);
            }
            else {
                greater.add(bed);
            }
        }
    }

    private Pair<List<Bear>, List<Bed>> quickSort(List<Bear> bears, List<Bed> beds) {
        if (bears.size() <= 1 || beds.size() <= 1) {
            return new Pair<>(bears, beds);
        }
        Bed pivotBed = beds.get(0);
        List<Bear> lessBear = new ArrayList<>();
        List<Bear> equalBear = new ArrayList<>();
        List<Bear> greaterBear = new ArrayList<>();
        partition(bears, pivotBed, lessBear, equalBear, greaterBear);

        Bear pivotBear = equalBear.get(0);
        List<Bed> lessBed = new ArrayList<>();
        List<Bed> equalBed = new ArrayList<>();
        List<Bed> greaterBed = new ArrayList<>();
        partition(beds, pivotBear, lessBed, equalBed, greaterBed);

        Pair<List<Bear>, List<Bed>> less = quickSort(lessBear, lessBed);
        lessBear = less.first();
        lessBed = less.second();

        Pair<List<Bear>, List<Bed>> greater = quickSort(greaterBear, greaterBed);
        greaterBear = greater.first();
        greaterBed = greater.second();

        List<Bear> sortedBear = new ArrayList<>();
        List<Bed> sortedBed = new ArrayList<>();
        sortedBears = catenateBear(lessBear, equalBear);
        sortedBears = catenateBear(sortedBears, greaterBear);
        sortedBeds = catenateBed(lessBed, equalBed);
        sortedBeds = catenateBed(sortedBeds, greaterBed);

        return new Pair<>(sortedBear, sortedBed);

    }
}
