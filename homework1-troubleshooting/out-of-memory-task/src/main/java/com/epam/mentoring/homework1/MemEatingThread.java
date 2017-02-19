package com.epam.mentoring.homework1;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Runnable that consumes memory by creating other instances of {@link MemEatingThread}.
 * <p/>
 * Date: 02/19/2017
 *
 * @author Raman Kuchynski
 */
public class MemEatingThread implements Runnable {

    private final int copiesToCreate;
    private final int memoryToOccupy;
    private List<String> occupiedMemory;

    public MemEatingThread(int copiesToCreate, int memoryToOccupy) {
        this.copiesToCreate = copiesToCreate;
        this.memoryToOccupy = memoryToOccupy;
    }

    @Override
    public void run() {
        occupiedMemory = new ArrayList<>(memoryToOccupy);
        for(int c = 0; c < copiesToCreate; c++) {
            Thread copyThread = new Thread(new MemEatingThread(copiesToCreate, memoryToOccupy));
            copyThread.start();
        }
        for(int m = 0; m < memoryToOccupy; m++) {
            occupiedMemory.add(UUID.randomUUID().toString());
        }
    }
}
