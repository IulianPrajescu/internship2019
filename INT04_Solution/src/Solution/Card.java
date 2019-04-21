package Solution;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.time.LocalDate;

public class Card {
    private String type;
    private double fee;
    private double withdrawLimit;
    private LocalDate expirationDate;
    private double availableAmount;
    private double retractedAmount;

    public Card(String type,double fee, int withdrawLimit, LocalDate expirationDate, double availableAmount)
    {
        this.type=type;
        this.fee=fee;
        this.withdrawLimit=withdrawLimit;
        this.expirationDate=expirationDate;
        this.availableAmount=availableAmount;
        this.retractedAmount=0;
    }

    public double getRetractedAmount() {
        return retractedAmount;
    }

    public String getType()
    {
        return this.type;
    }
    public double getFee()
    {
        return this.fee;
    }
    public double getWithdrawLimit()
    {
        return this.withdrawLimit;
    }
    public double getAvailableAmount()
    {
        return this.availableAmount;
    }
    public LocalDate getExpirationDate()
    {
        return this.expirationDate;
    }

    public void setType(String type)
    {
        this.type=type;
    }
    public void setFee(double fee)
    {
        this.fee=fee;
    }
    public void setWithdrawLimit(int withdrawLimit)
    {
        this.withdrawLimit=withdrawLimit;
    }
    public void setAvailableAmount(double availableAmount)
    {
        this.availableAmount=availableAmount;
    }
    public void setExpirationDate(LocalDate expirationDate)
    {
        this.expirationDate=expirationDate;
    }

    public void setRetractedAmount(double retractedAmount) {
        this.retractedAmount = retractedAmount;
    }

    @Override
    public String toString() {
        return "Card: "+this.type+" | ";
    }
}
