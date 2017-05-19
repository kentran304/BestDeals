package com.company;

/**
 * Created on 9/5/17.
 */

public class PremiumVehicle extends Vehicle {

    private double dailyMileage;
    private double serviceLength;
    private double odoLastService;

    public PremiumVehicle() {
        this("000000", "Default", 0, 0, 0,0,0 );
    }

    public PremiumVehicle(String ID, String description, double dailyRate, double odometer) {
        super(ID, description, dailyRate, odometer);
    }

    public PremiumVehicle(String ID, String description, double dailyRate, double odometer, int dailyMileage, int serviceLength, int odoLastService) {
        super(ID, description, dailyRate, odometer);
        this.dailyMileage = dailyMileage;
        this.serviceLength = serviceLength;
        this.odoLastService = odoLastService;
    }

    @Override
    public boolean hire(String hirerID) throws StatusException, OdometerException {
        if (this.serviceLength > super.getOdometer() - this.odoLastService) {
            super.hire(hirerID);
        } else {
            throw new OdometerException("Vehicle should be serviced, not for hired");
        }
        return true;
    }

    @Override
    public boolean serviceComplete(double odo) throws StatusException, OdometerException {
        this.odoLastService = odo;
        return super.serviceComplete(odo);
    }

    @Override
    public double hireComplete(double odo) throws StatusException, OdometerException {
        double extraCharge = 0.10 * (odo - super.getOdometer() - (this.dailyMileage * DateTime.diffDays(DateTime.getCurrentTime(), getDateHire())));
        return extraCharge + super.hireComplete(odo);
    }

    @Override
    public void print() {
        super.print();
        System.out.println("Mileage Allowance: " + this.dailyMileage + "   " + "Service Length: " + this.serviceLength + "   " + "Last Service: " + this.odoLastService);
    }

    public double getDailyMileage() {
        return dailyMileage;
    }

    public double getServiceLength() {
        return serviceLength;
    }

    public double getOdoLastService() {
        return odoLastService;
    }
}

