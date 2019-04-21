package Solution;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import Solution.*;

public class Solution {
    private List<Card> cards;
    private List<ATM> atms;
    private List<Walk> walks;
    private LocalDateTime currentTime;
    private LocalDate currentDate;
    private String currentPoint;

    public Solution(List<Card> cards, List<ATM> atms, List<Walk> walks, String currentPoint,LocalDate currentDate, LocalDateTime currentTime)
    {
        this.cards=cards;
        this.atms=atms;
        this.walks=walks;
        this.currentPoint = currentPoint;
        this.currentDate=currentDate;
        this.currentTime=currentTime;
    }

    public String getCurrentPoint() {
        return currentPoint;
    }

    public List<Card> getCards() {
        return cards;
    }

    public List<ATM> getAtms() {
        return atms;
    }

    public List<Walk> getWalks() {
        return walks;
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }

    public LocalDateTime getCurrentTime() {
        return currentTime;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public void setAtms(List<ATM> atms) {
        this.atms = atms;
    }

    public void setWalks(List<Walk> walks) {
        this.walks = walks;
    }

    public void setCurrentDate(LocalDate currentDate) {
        this.currentDate = currentDate;
    }

    public void setCurrentTime(LocalDateTime currentTime) {
        this.currentTime = currentTime;
    }

    public void setCurrentPoint(String currentPoint) {
        this.currentPoint = currentPoint;
    }

    public Map<Card,Cost> getCardsCost(){
        int amountNeeded=10000;
        Map<Card,Cost> result=new HashMap<>();
        List<Card> sortedByFee=this.cards;
        List<ATM> availableATMS=this.atms;

        for(int i=0;i<sortedByFee.size();i++) {
            if (sortedByFee.get(i).getExpirationDate().isBefore(this.currentDate)||sortedByFee.get(i).getAvailableAmount()==0)
                sortedByFee.remove(i);
        }
        sortedByFee= sortedByFee.stream()
                .sorted(Comparator.comparing(Card::getFee))
                .collect(Collectors.toList());

        List<Card>sortedByMoney=sortedByFee.stream()
                .sorted(Comparator.comparing(Card::getAvailableAmount))
                .collect(Collectors.toList());
        Card theCardForTheTransaction=sortedByMoney.get(sortedByFee.size()-1);
        sortedByFee.remove(theCardForTheTransaction);

        if(theCardForTheTransaction.getAvailableAmount()>=amountNeeded)
        {
            double TVA=(10000*19)/119;
            double fee=0;
            Cost finalCost=new Cost(TVA,fee);
            result.put(theCardForTheTransaction,finalCost);
            return result;
        }
        amountNeeded-=theCardForTheTransaction.getAvailableAmount();
        double firstTVA=(theCardForTheTransaction.getAvailableAmount()*19)/119;
        double fee=0;
        Cost firstCost=new Cost(firstTVA,fee);
        result.put(theCardForTheTransaction,firstCost);
        while(amountNeeded>0) {
            for(int k=0;k<availableATMS.size();k++)
                if(availableATMS.get(k).getAmount()==0)
                    availableATMS.remove(k);
            for (int i = 0; i < availableATMS.size() && amountNeeded>0; i++) {
                int duration=-1;
                for (int j = 0; j < this.walks.size(); j++) {
                    if ((this.walks.get(j).getStartPoint() == this.currentPoint && this.walks.get(j).getEndPoint() ==availableATMS.get(i).getName())||(this.walks.get(j).getEndPoint() == this.currentPoint && this.walks.get(j).getStartPoint() ==availableATMS.get(i).getName()))
                    {
                        duration = this.walks.get(j).getDuration();
                        break;
                    }
                }
                if(duration!=-1) {
                    if (availableATMS.get(i).getOpeningTime().isBefore(this.currentTime.plusMinutes(duration)) || availableATMS.get(i).getOpeningTime().isEqual(this.currentTime.plusMinutes(duration)))
                    {    if (availableATMS.get(i).getClosingTime().isAfter(this.currentTime.plusMinutes(duration)))
                        {
                            this.currentPoint = availableATMS.get(i).getName();
                            for(int m=0;m<sortedByFee.size() && amountNeeded>0;m++)
                            {
                                double feeAmount=0;
                                double amountTransfered=0;
                                double TVA=0;

                                //if (sortedByFee.get(m).getRetractedAmount() < sortedByFee.get(m).getWithdrawLimit())//daca suma retractana pana acum e mai mica decat limita(daca mai pot sa scot bani de pe card)
                                if (sortedByFee.get(m).getWithdrawLimit() + (sortedByFee.get(m).getWithdrawLimit() * sortedByFee.get(m).getFee()) <= sortedByFee.get(m).getAvailableAmount())//daca limita cardului +comision mai mic decat soldul de pe card(daca pot sa scot limita)
                                {
                                    if (availableATMS.get(i).getAmount() >= sortedByFee.get(m).getWithdrawLimit())//daca in bancomat sunt mai multi bani decat limita cardului
                                    {
                                        feeAmount = sortedByFee.get(m).getWithdrawLimit() * sortedByFee.get(m).getFee();
                                        amountTransfered = sortedByFee.get(m).getWithdrawLimit();
                                        TVA = (amountTransfered * 19) / 119;

                                    } else {//daca in bancomat sunt mai putini bani decat limita cardului (tre sa scot maxim suma din bancomat)
                                        feeAmount = availableATMS.get(i).getAmount() * sortedByFee.get(m).getFee();
                                        amountTransfered = availableATMS.get(i).getAmount();
                                        TVA = (amountTransfered * 19) / 119;

                                    }
                                }
                                else{
                                    if(availableATMS.get(i).getAmount()>=sortedByFee.get(m).getAvailableAmount()-(sortedByFee.get(m).getAvailableAmount()*sortedByFee.get(m).getFee()*100)/(100+(sortedByFee.get(m).getFee()*100)))
                                    {
                                        feeAmount=(sortedByFee.get(m).getAvailableAmount()*sortedByFee.get(m).getFee()*100)/(100+(sortedByFee.get(m).getFee()*100));
                                        amountTransfered=sortedByFee.get(m).getAvailableAmount()-(sortedByFee.get(m).getAvailableAmount()*sortedByFee.get(m).getFee()*100)/(100+(sortedByFee.get(m).getFee()*100));
                                        TVA=(amountTransfered*19)/119;
                                    }
                                    else{
                                        feeAmount=availableATMS.get(i).getAmount()*sortedByFee.get(m).getFee();
                                        amountTransfered=availableATMS.get(i).getAmount();
                                        TVA=(amountTransfered*19)/119;
                                    }
                                }

                                if(amountNeeded>amountTransfered)
                                {
                                    sortedByFee.get(m).setAvailableAmount(sortedByFee.get(m).getAvailableAmount() - amountTransfered - feeAmount);
                                    availableATMS.get(i).setAmount(availableATMS.get(i).getAmount()-amountTransfered);

                                    theCardForTheTransaction.setAvailableAmount(theCardForTheTransaction.getAvailableAmount()+amountTransfered);
                                    amountNeeded-=amountTransfered;


                                }
                                else{
                                    feeAmount=amountNeeded*sortedByFee.get(m).getFee();
                                    amountTransfered=amountNeeded;
                                    TVA=(amountTransfered*19)/119;



                                    sortedByFee.get(m).setAvailableAmount(sortedByFee.get(m).getAvailableAmount() - amountTransfered - feeAmount);
                                    availableATMS.get(i).setAmount(availableATMS.get(i).getAmount()-amountTransfered);

                                    theCardForTheTransaction.setAvailableAmount(theCardForTheTransaction.getAvailableAmount()+amountTransfered);
                                    amountNeeded=0;


                                }
                                Cost transactionCost=new Cost(TVA,feeAmount);
                                result.put(sortedByFee.get(m), transactionCost);
                            }
                        }
                    }
                }
            }
        }

        return result;

    }


}
