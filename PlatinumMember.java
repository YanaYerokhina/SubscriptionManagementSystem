/* 
 * Yana Yerokhina, Davis Carson
 * 11/15/2024
 *
 * This class represents a Platinum Member in the subscription management system. 
 *
 * Platinum Members have additional features compared to standard subscribers: 
 * - They earn reward points on purchases.
 * - They can use points or coupons to reduce the amount they need to pay.
 * - Coupons provide a 50% discount when available.
 * 
 * This class overrides the `pay` method to handle payments uniquely for Platinum Members, 
 * considering their coupon status and reward points. It also includes a method 
 * to recharge their coupon and overrides the `toString` method to include additional 
 * Platinum Member details like points and coupon status.
 */


public class PlatinumMember extends Subscriber {

    private int numPoints;
    private boolean couponStatus;

    // Constructor
    public PlatinumMember(String name, int numPoints, boolean couponStatus) {
        super(name);
        this.numPoints = numPoints;
        this.couponStatus = couponStatus;
    }

    // Getters and Setters
    public int getNumPoints() {
        return numPoints;
    }

    public void setNumPoints(int numPoints) {
        this.numPoints = numPoints;
    }

    public boolean getCouponStatus() {
        return couponStatus;
    }

    public void setCouponStatus(boolean couponStatus) {
        this.couponStatus = couponStatus;
    }

    // Overridden pay method for Platinum Members
    @Override
    public void pay(double amount) {
        double amountToPay = amount;

        if (couponStatus) {
            // Use coupon for 50% off
            amountToPay = amount / 2;
            couponStatus = false;

            setAmtPaidToDate(getAmtPaidToDate() + amountToPay);

            // Earn 5 points per dollar on the discounted amount
            numPoints += (int) (amountToPay * 5);
        } else {
            // Calculate dollar value of points in groups of 10
            int convertiblePoints = (numPoints / 10) * 10; // Only use points in multiples of 10
            double pointsValue = convertiblePoints / 10.0; // Convert those points to dollar value

            if (pointsValue >= amount) {
                // Points cover the entire purchase
                int pointsToDeduct = (int) (amount * 10); // Convert amount to points needed
                numPoints -= pointsToDeduct; // Deduct only the points needed
                amountToPay = 0;
            } else {
                // Points cover part of the purchase
                amountToPay = amount - pointsValue;
                numPoints -= convertiblePoints; // Deduct only the convertible points
            }

            // Add the actual amount paid to total and earn points on that amount
            setAmtPaidToDate(getAmtPaidToDate() + amountToPay);

            // Earn 5 points per dollar on the amount actually paid
            numPoints += (int) (amountToPay * 5);
        }

        setNumTimes(getNumTimes() + 1);
    }

    // Recharge coupon status
    public void rechargeCoupon() {
        if (!couponStatus) {
            couponStatus = true;
        }
    }

    // Overriden toString method to print Platinum Member data
    @Override
    public String toString() {
        return super.toString() + String.format(" Points: %-5d Coupon: %-5s", this.numPoints, this.couponStatus);
    }
}
