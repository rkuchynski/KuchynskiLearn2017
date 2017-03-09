package com.epam.mentoring.homework3.data.wrapper;

import java.util.Comparator;

/**
 * Class that sorts {@link ArrayWrapper}.
 * <p/>
 * Date: 09.03.2017
 *
 * @author Raman Kuchynski
 */
public class ArrayWrapperSorter<T> {

    private Comparator<T> comparator;

    public ArrayWrapperSorter(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public void sortWrapper(ArrayWrapper<T> wrapper) {
        if (null == wrapper || wrapper.size() < 2) {
            return;
        }
        quicksort(wrapper, 0, wrapper.size() - 1);
    }

    private void quicksort(ArrayWrapper<T> wrapper, int lowIndex, int highIndex) {
        int i = lowIndex, k = highIndex;
        T pivot = wrapper.get(lowIndex + (highIndex-lowIndex)/2);

        while (i <= k) {
            while (comparator.compare(wrapper.get(i), pivot) < 0) {
                i++;
            }
            while (comparator.compare(wrapper.get(k), pivot) > 0) {
                k--;
            }
            if (i <= k) {
                exchange(wrapper, i, k);
                i++;
                k--;
            }
        }
        // Recursion
        if (lowIndex < k)
            quicksort(wrapper, lowIndex, k);
        if (i < highIndex)
            quicksort(wrapper, i, highIndex);
    }

    private void exchange(ArrayWrapper<T> wrapper, int i, int k) {
        T temp = wrapper.get(i);
        wrapper.set(i, wrapper.get(k));
        wrapper.set(k, temp);
    }
}
