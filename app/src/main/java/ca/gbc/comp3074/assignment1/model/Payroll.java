package ca.gbc.comp3074.assignment1.model;

public class Payroll {
    private double hoursWorked;
    private double hourlyRate;
    private double taxRate;
    private final int OVERTIME_THRESHOLD = 40;
    private final double OVERTIME_RATE = 1.5;

    public Payroll(double hoursWorked, double hourlyRate, double taxRate) {
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
        this.taxRate = taxRate;
    }

    public Payroll(double hoursWorked, double hourlyRate) {
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
        this.taxRate = 0.18;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public double calculatePay(){
        double hoursWorked = Math.min(this.hoursWorked, this.OVERTIME_THRESHOLD);
        return hoursWorked * this.hourlyRate;
    }

    public double calculateOvertimePay(){
        return (this.hoursWorked - this.OVERTIME_THRESHOLD) * this.hourlyRate * OVERTIME_RATE;
    }

    public double calculateTotalPay(){
        double totalPay = this.calculatePay();

        if(this.getHoursWorked() > this.OVERTIME_THRESHOLD){
            totalPay += this.calculateOvertimePay();
        }
        return totalPay;
    }

    public double calculateTax(){
        return this.calculateTotalPay() * this.taxRate;
    }

    @Override
    public String toString() {
        return "Payroll{" +
                "hoursWorked=" + hoursWorked +
                ", hourlyRate=" + hourlyRate +
                ", taxRate=" + taxRate +
                '}';
    }
}
