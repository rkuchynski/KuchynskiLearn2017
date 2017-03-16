package com.epam.mentoring.homework3.data.wrapper;

import java.util.Comparator;

/**
 * Class that sorts {@link IDataContainer}.
 * <p/>
 * Date: 09.03.2017
 *
 * @param <T> container element type.
 *
 * @author Raman Kuchynski
 */
public class DataContainerSorter<T> {

    private Comparator<T> comparator;

    public DataContainerSorter(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public void sortContainer(IDataContainer<T> container) {
        if (null == container || container.getSize() < 2) {
            return;
        }
        quicksort(container, 0, container.getSize() - 1);
    }

    private void quicksort(IDataContainer<T> container, int lowIndex, int highIndex) {
        int i = lowIndex, k = highIndex;
        T pivot = container.get(lowIndex + (highIndex-lowIndex)/2);

        while (i <= k) {
            while (comparator.compare(container.get(i), pivot) < 0) {
                i++;
            }
            while (comparator.compare(container.get(k), pivot) > 0) {
                k--;
            }
            if (i <= k) {
                exchange(container, i, k);
                i++;
                k--;
            }
        }
        // Recursion
        if (lowIndex < k)
            quicksort(container, lowIndex, k);
        if (i < highIndex)
            quicksort(container, i, highIndex);
    }

    private void exchange(IDataContainer<T> container, int i, int k) {
        T temp = container.get(i);
        container.set(container.get(k), i);
        container.set(temp, k);
    }
}
