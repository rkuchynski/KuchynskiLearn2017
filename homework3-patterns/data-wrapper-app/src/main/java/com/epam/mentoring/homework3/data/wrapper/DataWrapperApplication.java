package com.epam.mentoring.homework3.data.wrapper;

import java.security.SecureRandom;
import java.util.*;

/**
 * Application entry point.
 * <p/>
 * Date: 09.03.2017
 *
 * @author Raman Kuchynski
 */
public class DataWrapperApplication {

    private static final Random RANDOM = new SecureRandom();

    public static void main(String[] args) {

        System.out.println("Generating random ArrayList...");
        testList(new ArrayList<>());
        System.out.println("Generating random LinkedList...");
        testList(new LinkedList<>());

    }

    private static void testList(List<Integer> list) {
        for(int i = 0; i < 20; i++) {
            list.add(RANDOM.nextInt(1000));
        }
        System.out.println("Generated list contents: ");
        System.out.println(Arrays.toString(list.toArray()));
        ArrayWrapperFactory<Integer, List<Integer>> wrapperFactory = new ListArrayWrapperFactory<>();
        ArrayWrapper<Integer> wrapper = wrapperFactory.createWrapper(list);
        ArrayWrapperSorter<Integer> sorter = new ArrayWrapperSorter<>(Integer::compareTo);
        sorter.sortWrapper(wrapper);
        System.out.println("Sorted list: ");
        wrapper.printToConsole();
    }

}
