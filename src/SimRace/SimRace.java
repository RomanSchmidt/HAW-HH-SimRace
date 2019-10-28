package SimRace;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * start several car instances in own thread, wait for the threads to be done, print out result
 */
public class SimRace {
    /**
     * number of cars for joining the race
     */
    private static final int _numberOfCars = 4;

    /**
     * number of rounds to drive
     */
    private static final int _rounds = 4;

    /**
     * list of cars instances
     */
    private ArrayList<Car> _cars = new ArrayList<>();

    /**
     * logical flow
     */
    private SimRace() {
        this._createCars();
        this._startRace();
        this._waitForCars();
        this._sortCars();
        this._printResult();
    }

    /**
     * just create the instance
     */
    public static void main(String[] args) {
        new SimRace();
    }

    /**
     * print out the head and each cars info depending on sorted list entries
     */
    private void _printResult() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("**** Endstand ****");
        for (int i = 0; i < this._cars.size(); ++i) {
            Car car = this._cars.get(i);
            buffer
                    .append(i + 1)
                    .append(". Platz: Wagen ")
                    .append(car.getNumber())
                    .append(" Zeit: ")
                    .append(car.getRaceTime())
                    .append("\n");
        }
        System.out.println(buffer);
    }

    /**
     * lambda sorting for the build in sort function of the car list. sort by time
     */
    private void _sortCars() {
        this._cars.sort(Comparator.comparingInt(o -> o.getRaceTime()));
    }

    /**
     * make sure all cars arrived (wait for threads)
     */
    private void _waitForCars() {
        this._cars.forEach(car -> {
            try {
                car.join();
            } catch (InterruptedException e) {
                // ignore
            }
        });
    }

    /**
     * call the start function of each car
     */
    private void _startRace() {
        this._cars.forEach(Thread::start);
    }

    /**
     * create as many instances of cars in the list as _numberOfCars
     */
    private void _createCars() {
        for (int i = 0; i < SimRace._numberOfCars; ++i) {
            this._cars.add(new Car(i + 1, SimRace._rounds));
        }
    }
}
