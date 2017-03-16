package com.epam.mentoring.homework3.data.wrapper;

import com.google.common.collect.Lists;

import java.security.SecureRandom;
import java.util.*;

/**
 * Application entry point.
 * <p/>
 * Date: 09.03.2017
 *
 * @author Raman Kuchynski
 */
public class DataContainerApplication {

    private static final Random RANDOM = new SecureRandom();

    public static void main(String[] args) {

        System.out.println("Generating random ArrayList...");
        testDataContainer(fillContainer(new ListBackedContainer<>(Lists.newArrayListWithExpectedSize(20))));
        System.out.println("Generating random LinkedList...");
        testDataContainer(fillContainer(new ListBackedContainer<>(Lists.newLinkedList())));
        System.out.println("Generating random ArrayBackedContainer...");
        ArrayBackedContainer<Integer> wrapper = new ArrayBackedContainer<>(20);
        testDataContainer(fillContainer(wrapper));
    }

    private static IDataContainer<Integer> fillContainer(IDataContainer<Integer> wrapper) {
        for(int i = 0; i < 20; i++) {
            wrapper.set(RANDOM.nextInt(1000), i);
        }
        System.out.println("Generated container contents: ");
        wrapper.print(System.out);
        return wrapper;
    }

    private static void testDataContainer(IDataContainer<Integer> container) {
        DataContainerSorter<Integer> sorter = new DataContainerSorter<>(Integer::compareTo);
        sorter.sortContainer(container);
        System.out.println("Sorted container: ");
        container.print(System.out);
        System.out.println("Set 5-th value (" + container.get(4) + ") to 0");
        container.set(0, 4);
        container.print(System.out);
        System.out.println("Container's 10-th value is " + container.get(9) + "\n");
    }

}
