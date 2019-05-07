//============================================================================
// Author		: SE Department
// Date			: 3/28/2019
// Assignment	: HomeWork #2
// Class		: CEN 4072
// University	: Florida Gulf Coast University
//============================================================================

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Integer.valueOf;

public class IntegerSet {

    /**
     * The only instance variable in the class.
     * No other variables allowed.
     */
    private List<Integer> list;

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Initializes the empty set.
     */
    public IntegerSet() {
        list = new LinkedList<Integer>();
    }

    /**
     * Initializes a set with the numbers in the argument.
     */
    public IntegerSet(Integer[] integers) {
        this();
        if (integers == null) {
            throw new NullPointerException("The argument cannot be null");

        } else {
            for (Integer i : integers) {
                if (!exists(i)) {
                    this.insertElement(i);
                }
            }
        }
        Collections.sort(list);
    }

    /**
     * Inserts an integer to the set if the integer does
     * not exist in the set
     */
    public void insertElement(int i) {
        if (!list.contains(i)) {
            list.add(i);
        }
    }

    /**
     * Inserts to the set all the integers in the argument which
     * do not exist in the set.
     */
    public void insertAll(Integer[] data) {
        if (data == null) {
            throw new NullPointerException("The argument cannot be null");
            // throw new IllegalArgumentException("The argument cannot be null");
        } else {
            Arrays.sort(data);
            for (Integer num : data) {
                if (!exists(num)) {
                    insertElement(num);
                }
            }
        }
    }

    /**
     * Deletes an integer from the set if it exists in the set.
     */
    public void deleteElement(int i) {
        list.remove(i);
    }

    /**
     * Deletes all the elements in the set.
     */
    public void deleteAll() {
        list.clear();
    }

    /**
     * Returns true if the argument exists in the set, false otherwise.
     */
    public boolean exists(int i) {
        return list.contains(i);
    }

    public String toString() {
        char delimiter = ' ';
        String str = "[";
        for (Integer i : list) {
            str += i;
            str += delimiter;
        }
        return str + "]";
    }

    /**
     * Finds the union of two sets. A null pointer is considered an empty set.
     * <p/>
     * Return "new IntegerSet()" when the result is an empty set.
     */
    public static IntegerSet union(IntegerSet set1, IntegerSet set2) {
        IntegerSet union = new IntegerSet();

        if (set1.isEmpty() && set2.isEmpty()) {
            return union;
        }

        if (!set1.list.isEmpty()) {
            for (Integer num : set1.list) {
                if (!union.list.contains(num)) {
                    union.list.add(num);
                }
            }
        }

        if (!set2.isEmpty()) {
            for (Integer num : set2.list) {
                if (!union.list.contains(num)) {
                    union.list.add(num);
                }
            }
        }

        return union;
    }

    /**
     * Finds the intersection of two sets. A null pointer is considered an empty set.
     * <p/>
     * Return "new IntegerSet()" when the result is an empty set.
     */
    public static IntegerSet intersection(IntegerSet set1, IntegerSet set2) {
        IntegerSet intersection = new IntegerSet();
        //check for null pointers
        if (set1.list.isEmpty() || set2.list.isEmpty()) {
            return intersection;
        }

        for (Integer num : set1.list) {
            if ((!intersection.list.contains(num)) && set2.list.contains(num)) {
                intersection.list.add(num);
            }
        }

        return intersection;
    }

    /**
     * Builds an array with the elements in the set.
     */
    public Integer[] toArray() {
        Integer[] alist;
        Object[] s = list.toArray();
        alist = new Integer[s.length];
        for (int i = 0; i < s.length; i++) {
            alist[i] = valueOf((Integer) s[i]);
        }
        Arrays.sort(alist);
        return alist;
    }
}
