package by.valdaitsevv.cars;
import by.valdaitsevv.taxiexception.TaxiException;

import java.io.Serializable;

public class Taxi extends Car implements Comparable <Taxi>, Serializable  /// наследуем от Comparable, чтобы перегрузить compareTo()
{
    private String name;            // поля из класса Car
    private carType type;
    private double fuelRate;
    private int speed;

    private double taxiFare = 1;    // новые поля для класса Taxi
    private double profit = 0;

    public Taxi() {}

    public Taxi(String name, double fuel, int speed) throws TaxiException
    {
        if (name == null || name.length() < 3 || name.length() > 30 ||fuel < 0 || fuel > 3 || speed > 290 || speed < 0)
            throw new TaxiException("Error in Taxi constructor");
        this.name = name;
        this.fuelRate = fuel;
        this.speed = speed;
    }

    @Override
    public int compareTo(Taxi o)    /// override compareTo для сравнения по скорости (см. TaxiPark.Manager.sortSpeed())
    {
        return Integer.compare(this.getSpeed(), o.getSpeed());
    }

    public double ride(double km) throws TaxiException       /// метод для поездок, который будем переопределять в каждом классе
    {
        if (km < 0)
            throw new TaxiException("Kilometres are less than zero");
        double income = taxiFare * km;
        String priceForRide = String.format("%.2f", income);
        double loss = getFuelRate() * km;
        this.setProfit(getProfit() + income - loss);
        System.out.println("Вы проехали " + km + " км в " + getName() + ". С Вас " + priceForRide + " р. Спасибо за поездку!");
        return getProfit();
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public carType getType() {
        return type;
    }

    public void setType(carType type) {
        this.type = type;
    }

    public double getFuelRate() {
        return fuelRate;
    }

    public void setFuelRate(double fuelRate) {
        this.fuelRate = fuelRate;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getTaxiFare() {
        return taxiFare;
    }

    public void setTaxiFare(int taxiFare) {
        this.taxiFare = taxiFare;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }


}
