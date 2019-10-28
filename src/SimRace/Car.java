package SimRace;

import java.util.Random;

/**
 * thread class to sleep randomly on each round.
 */
public class Car extends Thread {
    /**
     * make it possible to create random numbers
     */
    private Random _rand = new Random();

    /**
     * taken time for the whole race
     */
    private int _raceTime = 0;

    /**
     * remember the number of rounds you have to drive
     */
    private int _rounds = 0;

    /**
     * remember your own number
     */
    private int _carNumber = 0;

    Car(int carNumber, int rounds) {
        this._rounds = rounds;
        this._carNumber = carNumber;
    }

    /**
     * returns the taken time of the whole race
     */
    public int getRaceTime() {
        return this._raceTime;
    }

    /**
     * return your number
     */
    public int getNumber() {
        return this._carNumber;
    }

    /**
     * sleep for 0 - 100 ms each round. add sleep time to the race time.
     */
    public void start() {
        for (int i = 0; i < this._rounds; ++i) {
            int randomNum = this._rand.nextInt(100 + 1);
            try {
                Thread.sleep(randomNum);
            } catch (InterruptedException e) {
                // ni
            }
            this._raceTime += randomNum;
        }
    }
}
