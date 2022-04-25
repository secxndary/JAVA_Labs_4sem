package by.valdaitsevv.taxitypes;
import by.valdaitsevv.cars.Taxi;
import by.valdaitsevv.cars.carType;
import by.valdaitsevv.taxiexception.TaxiException;

public class Business extends Taxi
{
    private final carType type = carType.business;
    private double taxiFare = 2;

    public Business() {}
    public Business(String name, double fuel, int speed) throws TaxiException {
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
        System.out.println("Вы проехали " + km + " км в " + getName() + " (Бизнес). С Вас " + priceForRide + " р. Спасибо за поездку!");
        return getProfit();
    }
}
