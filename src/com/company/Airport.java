package com.company;

import java.util.concurrent.ArrayBlockingQueue;

public class Airport {
    private final ArrayBlockingQueue<Band> bands;
    private final ArrayBlockingQueue<Plane> planes;
    private final int capaticyQueue  = 5;

    Airport(int bandCount) {
        this.bands = new ArrayBlockingQueue<>(bandCount,true);
        planes = new ArrayBlockingQueue<>(capaticyQueue);
        for (int i = 0; i < bandCount; i++) {
            bands.add(new Band("Полоса " + i));
        }
    }

    public void registerPlaneForTakeoff(Plane plane) {
        planes.add(plane);
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Самолеты в очереди: " + planes.size());

                try {
                    Thread.sleep(2000);
                } catch (Exception e) {}
            }
        }).start();
    }

    public Band waitForAvailableBand() throws InterruptedException {
        Band result = bands.take();
        System.out.println(result.name + "  встал в очередь ");
        return result;
    }

    public void releaseBand(Band band) throws InterruptedException {
        bands.add(band);
        System.out.println("Полоса освободилась: " + band.name);
        planes.take();
        System.out.println("Самолеты в очереди: " + planes.size());
    }

    static class Band  {
        public final String name;
        Band(String name) {this.name = name;}
    }
}

