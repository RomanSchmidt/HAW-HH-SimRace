package SimRace;

import java.util.ArrayList;
import java.util.Comparator;

public class SimRace {
    private final int _numberOfCars = 4;
    private final int _rounds = 4;

    private ArrayList<Car> _cars = new ArrayList<>();

    private SimRace() {
        this._createCars();
        this._startRace();
        this._waitForCars();
        this._sortCars();
        this._printResult();
    }

    public static void main(String[] args) {
        new SimRace();
    }

    private void _printResult() {
        System.out.println("**** Endstand ****");
        for (int i = 0; i < this._cars.size(); ++i) {
            Car car = this._cars.get(i);
            StringBuffer buffer = new StringBuffer();
            buffer
                    .append(i + 1)
                    .append(". Platz: Wagen ")
                    .append(car.getNumber())
                    .append(" Zeit: ")
                    .append(car.getWholeTime());
            System.out.println(buffer);
        }
    }

    private void _sortCars() {
        this._cars.sort(Comparator.comparingInt(o -> o.getWholeTime()));
    }

    private void _waitForCars() {
        this._cars.forEach(car -> {
            try {
                car.join();
            } catch (InterruptedException e) {
                // nichts
            }
        });
    }

    private void _startRace() {
        this._cars.forEach(Thread::start);
    }

    private void _createCars() {
        for (int i = 0; i < this._numberOfCars; ++i) {
            this._cars.add(new Car(i + 1, this._rounds));
        }
    }
}
