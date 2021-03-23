package pedigree;

import java.util.Comparator;

public class BirthComparator<T> implements Comparator<Sim> {
    public int compare(Sim s1, Sim s2) {
        return s2.compareBirth(s1);
    }
}
