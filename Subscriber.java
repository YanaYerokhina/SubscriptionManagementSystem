/*
 * Yana Yerokhina, Davis Carson
 * 11/15/2024
 *
 * This borrowed class is part of a project that manages subscriber information and 
 * tracks their payment history.  
 *
 * The class provides methods to update these values, including 
 * a pay method to record a payment, increment the number of times 
 * a subscriber has paid, and add to the total amount paid.  It also includes
 * getter and setter methods for each attribute, and a toString method 
 * that returns a string representation of the subscriber's details.
*/


public class Subscriber {
  private String name;
  private int numTimes;
  private double amtPaidToDate;


  // constructor 
  public Subscriber(String n) {
    this.name = n;
    this.numTimes = 0;
    this.amtPaidToDate = 0.0;
  }


  // getters
  public String getName() {
    return this.name;
  }


  public int getNumTimes() {
    return this.numTimes;
  }


  public double getAmtPaidToDate() {
    return amtPaidToDate;
  }


  //setters
  public void setName(String name) {
    this.name = name;
  }


  public void setNumTimes (int numTimes) {
    this.numTimes = numTimes;
  }


  public void setAmtPaidToDate (double amtPaidToDate) {
    this.amtPaidToDate = amtPaidToDate;
  }


  // pay method: adds payment and increments count
  public void pay(double p) {
    this.amtPaidToDate += p;
    numTimes++;
  }


  // toString method: prints out subscriber's information
  public String toString() {
    return String.format("Subscriber Name: %-10s Times Used: %-5d Amt Paid to Date: %-10.2f", this.name, this.numTimes, this.amtPaidToDate);
  }

}
