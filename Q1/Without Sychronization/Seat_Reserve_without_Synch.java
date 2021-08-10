package koustav_java;
import java.util.ArrayList;
import java.util.Scanner;

class TicketCounter1{
    private int availableSeats=10;
    public void bookTicket(String name,int seats){          //without synchrnization giving wrong output
        if(availableSeats>seats && availableSeats>0){
            System.out.println("Hi "+name+" Your "+seats+"ticket is successfully booked");
        }else{
            System.out.println("No seats are available");
        }
        availableSeats=availableSeats-seats;
        System.out.println("Available seats are : "+availableSeats);
    }
}
class TicketBookingThread1 extends Thread{
    private TicketCounter1 tc;
    private String name;
    private int seats;
    TicketBookingThread1(TicketCounter1 tc,String name,int seats){
        this.tc=tc;
        this.name=name;
        this.seats=seats;
    }

    public void run(){
        tc.bookTicket(name,seats);
    }
}
public class Seat_Reserve_without_Synch {
    public static void main(String[] args){
        System.out.println("Total seats available are 10");
        Scanner sc = new Scanner(System.in);
        TicketCounter1 tc = new TicketCounter1();
        System.out.print("Enter Number of person who are trying to book tickets: ");
        int n=sc.nextInt();
        ArrayList<TicketBookingThread1> arr = new ArrayList<>();
        for(int i=1;i<n+1;i++){
            System.out.print("Name of Person"+i+":");
            sc.nextLine();
            String name=sc.nextLine();
            System.out.print("Enter no of seats to book:");
            int seats=sc.nextInt();
            arr.add(new TicketBookingThread1(tc,name,seats));
        }
        for(int i=0;i<n;i++){
            arr.get(i).start();
        }
    }
}
