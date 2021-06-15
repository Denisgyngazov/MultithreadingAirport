package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Airport airport = new Airport(4);

        ArrayList<Plane> planes = new ArrayList<>();

        planes.add((new Passenger("1",airport,5000)));
        planes.add((new Cargo("2", airport, 10000)));
        planes.add((new Passenger("3", airport, 5000)));
        planes.add((new Cargo("4", airport, 10000)));
        planes.add((new Cargo("5", airport, 10000)));


        planes.forEach(Thread::start);

        planes.forEach(plane -> {
            try {
               plane.join();
            } catch (InterruptedException ignored) {}
        });
    }
}
