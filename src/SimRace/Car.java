package SimRace;

import java.util.Random;

public class Car extends Thread {
    private Random _rand = new Random();
    private int _wholeTime = 0;
    private int _rounds = 0;
    private int _carNumber = 0;

    Car(int carNumber, int rounds) {
        this._rounds = rounds;
        this._carNumber = carNumber;
    }

    public int getWholeTime() {
        return this._wholeTime;
    }

    public int getNumber() {
        return this._carNumber;
    }

    public void start() {
        for (int i = 0; i < this._rounds; ++i) {
            int randomNum = this._rand.nextInt((100) + 1);
            try {
                Thread.sleep(randomNum);
            } catch (InterruptedException e) {
                // nichts
            }
            this._wholeTime += randomNum;
        }
    }
}
