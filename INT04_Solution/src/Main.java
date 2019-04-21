import Solution.*;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] argc)
    {
        List<Walk> walks=new ArrayList<Walk>();
        List<ATM> atms=new ArrayList<ATM>();
        List<Card> cards=new ArrayList<Card>();


        Walk walk1=new Walk("User starting point","ATM 1", 5);
        Walk walk2=new Walk("User starting point","ATM 2", 60);
        Walk walk3=new Walk("User starting point","ATM 3", 30);
        Walk walk4=new Walk("User starting point","ATM 4", 45);
        Walk walk5=new Walk("ATM 1","ATM 2", 40);
        Walk walk6=new Walk("ATM 1","ATM 4", 45);
        Walk walk7=new Walk("ATM 2","ATM 3", 15);
        Walk walk8=new Walk("ATM 3","ATM 1", 40);
        Walk walk9=new Walk("ATM 3","ATM 4", 15);
        Walk walk10=new Walk("ATM 4","ATM 2", 30);

        walks.add(walk1);
        walks.add(walk2);
        walks.add(walk3);
        walks.add(walk4);
        walks.add(walk5);
        walks.add(walk6);
        walks.add(walk7);
        walks.add(walk8);
        walks.add(walk9);
        walks.add(walk10);

        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        String OpenTime_1="2019-03-19 12:00";
        String CloseTime_1="2019-03-19 18:00";
        LocalDateTime OpenTime1=LocalDateTime.parse(OpenTime_1,formatter);
        LocalDateTime CloseTime1=LocalDateTime.parse(CloseTime_1,formatter);
        ATM atm1=new ATM("ATM 1", OpenTime1,CloseTime1,5000);

        String OpenTime_2="2019-03-19 10:00";
        String CloseTime_2="2019-03-19 17:00";
        LocalDateTime OpenTime2=LocalDateTime.parse(OpenTime_2,formatter);
        LocalDateTime CloseTime2=LocalDateTime.parse(CloseTime_2,formatter);
        ATM atm2=new ATM("ATM 2",OpenTime2,CloseTime2,5000);

        String OpenTime_3="2019-03-19 22:00";
        String CloseTime_3="2019-03-20 13:00";
        LocalDateTime OpenTime3=LocalDateTime.parse(OpenTime_3,formatter);
        LocalDateTime CloseTime3=LocalDateTime.parse(CloseTime_3,formatter);
        ATM atm3=new ATM("ATM 3",OpenTime3,CloseTime3,5000);

        String OpenTime_4="2019-03-19 10:00";
        String CloseTime_4="2019-03-19 17:00";
        LocalDateTime OpenTime4=LocalDateTime.parse(OpenTime_4,formatter);
        LocalDateTime CloseTime4=LocalDateTime.parse(CloseTime_4,formatter);
        ATM atm4=new ATM("ATM 2",OpenTime4,CloseTime4,5000);

        atms.add(atm1);
        atms.add(atm2);
        atms.add(atm3);
        atms.add(atm4);

        //DateFormatter formatter2=DateFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date1=LocalDate.parse("2020-05-23");
        Card card1=new Card("SILVER", 0.002,2000,date1, 4000);

        LocalDate date2=LocalDate.parse("2018-08-15");
        Card card2=new Card("GOLD", 0.001, 2000, date2,2000);

        LocalDate date3=LocalDate.parse("2019-03-20");
        Card card3=new Card("PLATINUM", 0.003, 2000, date3,3000);

        LocalDate date4=LocalDate.parse("2020-06-23");
        Card card4=new Card("IRIDIUM", 0.002, 2000, date4,5000);

        LocalDate date5=LocalDate.parse("2019-07-15");
        Card card5=new Card("BRONZE", 0.005, 2000, date5,2500);

        LocalDate date6=LocalDate.parse("2019-08-20");
        Card card6=new Card("PREMIUM", 0.0015, 2000, date6,2000);

        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        cards.add(card6);


        LocalDate currentDate=LocalDate.parse("2019-03-19");
        String currentT="2019-03-19 11:30";
        LocalDateTime curentTime=LocalDateTime.parse(currentT,formatter);
        String currentPoint="User starting point";
        Solution solution=new Solution(cards,atms,walks,currentPoint,currentDate,curentTime);

        Map<Card,Cost> result=solution.getCardsCost();
        for(Card card:result.keySet())
        {
            System.out.println(card.toString()+result.get(card).toString());
        }

    }
}
