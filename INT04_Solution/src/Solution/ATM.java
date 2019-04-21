package Solution;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ATM {
    private String name;
    private LocalDateTime openingTime;
    private LocalDateTime closingTime;
    private double amount;

    public ATM(String name, LocalDateTime openingTime, LocalDateTime closingTime,double amount) {
        this.name = name;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.amount=amount;

    }

    public String getName() {
        return this.name;
    }

    public LocalDateTime getOpeningTime()
    {
        return this.openingTime;
    }
    public  LocalDateTime getClosingTime()
    {
        return this.closingTime;
    }

    public double getAmount() {
        return amount;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setOpeningTime(LocalDateTime openingTime){
        this.openingTime=openingTime;
    }

    public void setClosingTime(LocalDateTime closingTime){
        this.closingTime=closingTime;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
