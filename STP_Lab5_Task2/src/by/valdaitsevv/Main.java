package by.valdaitsevv;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {

        Semaphore sem = new Semaphore(3);
        Exchanger<Boolean> exDir = new Exchanger<>();
        Exchanger<Integer> exCtr = new Exchanger<>();


        new Car(sem, true, 1, exDir, exCtr).start();
        new Car(sem, true, 2, exDir, exCtr).start();
        new Car(sem, true, 3, exDir, exCtr).start();
        new Car(sem, false, 4, exDir, exCtr).start();
        new Car(sem, false, 5, exDir, exCtr).start();
    }
}