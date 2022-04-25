package by.valdaitsevv.taxipark;

import by.valdaitsevv.taxiexception.TaxiException;

public interface IManager       /// интерфейс для менеджера
{
    public double getGeneralProfit();
    public double getCompanyProfit();
    public void searchSpeed(int min, int max) throws TaxiException;
    public void sortFuel();
}
