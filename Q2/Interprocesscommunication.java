package koustav_java;
class Person{
    int amount=1000;
    synchronized void withdraw(int amount){
        if(this.amount<amount){
            System.out.println("LOW BALANCE!");
            System.out.println("withdrawal amount is: "+amount+" avalilable balance is: "+this.amount);
            try{
                wait();                                //wait() forces the current thread to wait until some other thread invokes notify()
            }catch (Exception e){
                System.out.println(e);
            }
        }
        this.amount=this.amount-amount;
        System.out.println(amount+" amount is deducted current balance is : "+this.amount);
    }
    synchronized void deposit(int amount){
        this.amount+=amount;
        System.out.println("amount added is: "+amount+"current amount"+this.amount);
        notify();                                       //waking up thread that is waiting for an access to this object's monitor
    }
}
public class Interprocesscommunication {
    public static void main(String[] args) throws Exception {
        Person p = new Person();
        new Thread(){
            public void run(){
                p.withdraw(1500);
            }
        }.start();
        new Thread(){
            public void run(){
                p.deposit(1500);
            }
        }.start();
    }
}
