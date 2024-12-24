# Subscription Management System
A Java-based subscription management system that handles different types of memberships with varying benefits and reward structures.

# Overview
This system manages three types of subscribers:
1. Basic Subscribers
2. Gold Members
3. Platinum Members

Each membership tier offers different benefits and features, including point systems and discount opportunities.

# Features 
### Basic Subscribers 
* Track number of purchases
* Maintain payment history
* Basic reporting functionality

### Gold Members 
* Earn 1 point per dollar spent
* Redeem points for discounts (10 points = $1)
* Maximum point balance of 300
* Points can only be redeemed in increments of 10
* Points apply before new points are earned on a purchase

### Platinum Mambers
* Earn 5 points per dollar spent
* Redeem points for discounts (10 points = $1)
* Special coupon system offering 50% discounts
* Rechargeable coupon status
* Points can only be redeemed in increments of 10
* No maximum point balance

# File Structure
* [Subscriber](Subscriber.java): Base class for all subscriber types
* [GoldMember](GoldMember.java): Implementation of Gold membership tier
* [PlatinumMember](PlatinumMember.java): Implementation of Platinum membership tier
* [SubscriberMgr](SubscriberMgr.java): Main management class handling file I/O and subscriber operations

# Usage
The system reads subscriber data and transactions from a file named subscribers.txt. The file should be formatted as follows:
```
<number_of_subscribers>
<subscriber_type> <name> [points] [coupon_status]
...
<transaction_type> <subscriber_name> [amount]
...
```

Where:
* subscriber_type: 'S' for basic Subscriber, 'G' for Gold, 'P' for Platinum
* transaction_type: 'B' for buy/payment, 'C' for coupon recharge
* points: Initial point balance (for Gold and Platinum members)
* coupon_status: 't' or 'f' for Platinum members' coupon status

### Example Input File
```
3
S John
G Mary 100
P Bob 200 t
B Mary 50.00
C Bob
B Bob 100.00
```
# Running the Program
1. Ensure all Java files are compiled:
```
javac *.java
```
2. Create a subscribers.txt file with the appropriate format
3. Run the program:
```
java SubscriberMgr
```

# Output 
The program will print a summary of all subscribers, including:
* Subscriber name
* Number of times used
* Total amount paid to date
* Current points balance (for Gold and Platinum members)
* Coupon status (for Platinum members)

# Authors 
* Yana Yerokhina
* Davis Carson


