/* 
 * Yana Yerokhina, Davis Carson
 * 11/15/2024
 *
 * This class represents a Gold Member in the subscription management system. 
 *
 * Gold Members have a point system that allows them to earn and use points 
 * for discounts on their purchases. Points can be redeemed in increments of 10, 
 * where each 10 points provides a $1 discount.  
 *
 * Key Features:
 * - Points cannot exceed a set maximum (300 points by default).
 * - The `pay` method applies available points to reduce the payment amount, 
 *   updating the subscriber's payment history and point balance.
 * - Earns 1 point for every dollar spent.
 *
 * This class also overrides the `toString` method to include the current point 
 * balance in the subscriber's details.
 */

public class GoldMember extends Subscriber {

    private int numPoints;
    private static int maxPoints = 300; 

    // Constructor
    public GoldMember(String name, int numPoints) {
        super(name);
        this.numPoints = Math.min(numPoints, maxPoints); // Ensure points don't exceed max
    }

    // Getters and Setters
    public int getNumPoints() {
        return numPoints;
    }

    public void setNumPoints(int numPoints) {
        this.numPoints = Math.min(numPoints, maxPoints);
    }

    // Overridden pay method for Gold Members
    @Override
    public void pay(double amount) {
        // Calculate dollar value of points that can be used, only using groups of 10 points
        int convertiblePoints = (numPoints / 10) * 10; // Only use points in multiples of 10
        double pointsValue = convertiblePoints / 10.0; // Convert those points to dollar value

        double amountAfterPoints;

        if (pointsValue >= amount) {
            // If points can cover the entire purchase
            int pointsToDeduct = (int) (amount * 10); // Convert amount to points needed
            numPoints -= pointsToDeduct; // Deduct only the points needed
            amountAfterPoints = 0;
        } else {
            // If points can only cover part of the purchase
            amountAfterPoints = amount - pointsValue;
            numPoints -= convertiblePoints; // Deduct the convertible points
        }

        // Add the actual amount paid to total and earn points on that amount
        setAmtPaidToDate(getAmtPaidToDate() + amountAfterPoints);

        // Earn 1 point per dollar spent, up to maximum
        int newPoints = (int) (amountAfterPoints);
        numPoints = Math.min(numPoints + newPoints, maxPoints);

        setNumTimes(getNumTimes() + 1);
    }

    // Overriden toString method to print Gold Member data
    @Override
    public String toString() {
        return super.toString() + String.format(" Points: %-5d", this.numPoints);
    }
}
