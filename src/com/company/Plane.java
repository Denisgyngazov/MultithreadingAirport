package com.company;

public abstract class Plane extends Thread {
    public final String name;
    private final Airport airport;
    private final int delay;
    Airport.Band band;

    Plane(String name, Airport airport, int delay) {
        this.name = name;
        this.airport = airport;
        this.delay = delay;
    }

    public int getDelay() {
        return delay;
    }

    public static int rnd(int min, int max) {
        max -=min;
        return (int) (Math.random() * ++ max) + min;
    }

    public void takeOff() throws InterruptedException {
        try {
            System.out.println("Взлетаем " + name + " с полосы " + band.name);
            Thread.sleep(getDelay());
            System.out.println("Взлетел: " + name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
           airport.releaseBand(band);
        }
    }

    public void warmUp() throws InterruptedException {
        int time = 1000;
        int time2 = 10000;
        int timeEver = rnd(time,time2);
        System.out.println("Прогрев начался " + name);
        Thread.sleep(timeEver);
        System.out.println("Прогрев завершился: " + name + " " + timeEver);
        airport.registerPlaneForTakeoff(this);
        System.out.println("Резервируем полосу для: " + name);
        band = airport.waitForAvailableBand();
    }


    @Override
    public void run() {
        try {
            warmUp();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            takeOff();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

