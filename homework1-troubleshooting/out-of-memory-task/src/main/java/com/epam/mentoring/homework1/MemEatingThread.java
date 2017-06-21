package com.epam.mentoring.homework1;

/**
 * Runnable that consumes memory by creating other instances of {@link MemEatingThread}.
 * <p/>
 * Date: 02/19/2017
 *
 * @author Raman Kuchynski
 */
public class MemEatingThread implements Runnable {

    private static final int MB = 1024 * 1024;

    private final int copiesToCreate;
    private final int memoryToOccupy;
    private byte[][] occupiedMemory;

    public MemEatingThread(int copiesToCreate, int memoryToOccupy) {
        this.copiesToCreate = copiesToCreate;
        this.memoryToOccupy = memoryToOccupy;
    }

    @Override
    public void run() {
        occupiedMemory = new byte[MB][memoryToOccupy];
        try {
            Thread.sleep(200L);
        } catch (InterruptedException e) { }
        for(int c = 0; c < copiesToCreate; c++) {
            Thread copyThread = new Thread(new MemEatingThread(copiesToCreate, memoryToOccupy));
            copyThread.start();
        }
    }
}
