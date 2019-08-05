package com.company;

import java.util.concurrent.CountDownLatch;

public class Main {

    private static final int PEOPLES_COUNT = 15;
    private static CountDownLatch LATCH;

    public static void main(String[] args) throws InterruptedException {

        LATCH = new CountDownLatch(PEOPLES_COUNT);

        for (int i = 1; i <= PEOPLES_COUNT; i++) {
            People p = new People(i);
            p.start();
            Thread.sleep(1000);
        }
        System.out.println("Люди собрались на экскурсию");
    }

    public static class People extends Thread {
        private int peopleNumber;

        public People(int peopleNumber) {
            this.peopleNumber = peopleNumber;
        }

        @Override
        public void run() {
            try {
                System.out.printf("Человек %d пришел на место\n", peopleNumber);

                LATCH.countDown();

                LATCH.await();


            } catch (InterruptedException e) {
            }
        }
    }

}
