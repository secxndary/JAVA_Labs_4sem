package by.valdaitsevv.taxitypes;
import by.valdaitsevv.cars.Taxi;
import by.valdaitsevv.cars.carType;
import by.valdaitsevv.taxiexception.TaxiException;

public class Truck extends Taxi
{
    private final carType type = carType.truck;
    private double taxiFare = 1.6;

    public Truck() {}

    public Truck(String name, double fuel, int speed) throws TaxiException {
        super(name, fuel, speed);
    }


    @Override
    public carType getType() {
        return type;
    }
    @Override
    public double getTaxiFare() {
        return taxiFare;
    }
    @Override
    public double ride(double km) throws TaxiException {
        if (km < 0 || km > 200)
            throw new TaxiException("Invalid input of kilometres");
        double income = taxiFare * km;
        String priceForRide = String.format("%.2f", income);
        double loss = getFuelRate() * km;
        this.setProfit(getProfit() + income - loss);
        System.out.println("Вы проехали " + km + " км в " + getName() + " (Грузовой). С Вас " + priceForRide + " р. Спасибо за поездку!");
        return getProfit();
    }
}
