/* 
 * Yana Yerokhina, Davis Carson
 * 11/15/2024
 * 
 * The class provides methods to create an array of subscribers from a file, 
 * process transactions for each subscriber, and print subscriber details.  
 *
 * The `makeSubscriberArray` method reads subscriber information and initializes 
 * corresponding objects based on their type (GoldMember, PlatinumMember, or Subscriber).
 *
 * The `processTransactions` method handles payment and coupon recharges 
 * for each subscriber.
 *
 * Finally, the `printSubscriberData` method prints a summary of each 
 * subscriber's details, including payment history and status.
 *
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SubscriberMgr {

    // Method to create an array of Subscribers from the file
    public static Subscriber[] makeSubscriberArray(int numSubs, Scanner s) {
        Subscriber[] subscribers = new Subscriber[numSubs];

        for (int i = 0; i < numSubs; i++) {
            String type = s.next();
            String name = s.next();
            int points = 0;
            boolean hasCoupon = false;

            if (type.equals("G")) {
                points = s.nextInt();
                subscribers[i] = new GoldMember(name, points);
            } else if (type.equals("P")) {
                points = s.nextInt();
                hasCoupon = s.next().equals("t");
                subscribers[i] = new PlatinumMember(name, points, hasCoupon);
            } else if (type.equals("S")) {
                subscribers[i] = new Subscriber(name);
            }
        }

        return subscribers;
    }

    // Method to process transactions from the file
    public static void processTransactions(Scanner s, Subscriber[] subscribers) {
        while (s.hasNext()) {
            String action = s.next();
            String subscriberName = s.next();

            // Find the subscriber based on name
            Subscriber subscriber = null;
            for (Subscriber sub : subscribers) {
                if (sub.getName().equals(subscriberName)) {
                    subscriber = sub;
                    break;
                }
            }

            if (subscriber == null) {
                System.out.println("Subscriber not found: " + subscriberName);
                continue; // Skip if subscriber is not found
            }

            if (action.equals("B")) {
                double amount = s.nextDouble();

                if (subscriber instanceof GoldMember) {
                    ((GoldMember) subscriber).pay(amount);
                } else if (subscriber instanceof PlatinumMember) {
                    ((PlatinumMember) subscriber).pay(amount);
                } else {
                    subscriber.pay(amount);
                }
            } else if (action.equals("C")) {
                if (subscriber instanceof PlatinumMember) {
                    ((PlatinumMember) subscriber).rechargeCoupon();
                }
            }
        }
    }

    // Method to print the data of each subscriber
    public static void printSubscriberData(Subscriber[] subscribers) {
        for (Subscriber subscriber : subscribers) {
            System.out.println(subscriber);
        }
    }

    // Main method
    public static void main(String[] args) {
        try {
            File file = new File("subscribers.txt");
            Scanner scanner = new Scanner(file);

            int numSubscribers = scanner.nextInt(); // First line contains number of subscribers
            Subscriber[] subscribers = makeSubscriberArray(numSubscribers, scanner);

            processTransactions(scanner, subscribers);

            printSubscriberData(subscribers);

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
}
