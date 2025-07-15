import java.util.*;
class Transaction
{
    Date date;
    double amount;
    public Transaction(Date date, double amount)
    {
        this.date = date;
        this.amount = amount;
    }
    public Date getDate()
    {
        return new Date();
    }
    public double getAmount()
    {
        return amount;
    }
}
class Customer
{
    String name;
    double balance;
    public Customer(String name, double balance)
    {
        this.name = name;
        this.balance = balance;
    }
    public String getName()
    {
        return name;
    }
    public double getBalance()
    {
        return balance;
    }
}
public class BankingApplication
{
    // Tim Sort for Transaction History (sorting by date)
    public static void sortTransactionsByDate(List<Transaction> transactions)
    {
        transactions.sort(Comparator.comparing(Transaction::getDate));
    }
    // Tim Sort for Account Listings (sorting by balance)
    public static void sortAccountsByBalance(List<Customer> customers)
    {
        customers.sort(Comparator.comparingDouble(Customer::getBalance));
    }
    // Intro Sort for Sorting Customers by Name
    public static void sortCustomersByName(List<Customer> customers)
    {
        customers.sort(Comparator.comparing(Customer::getName));
    }
    // Two-pointer technique for detecting duplicate transactions (assuming sorted by date)
    public static List<Transaction> detectDuplicateTransactions(List<Transaction> transactions)
    {
        List<Transaction> duplicates = new ArrayList<>();
        int left = 0, right = 1;
        while (right < transactions.size())
        {
            if (transactions.get(left).getDate().equals(transactions.get(right).getDate()) &&
                    transactions.get(left).getAmount() == transactions.get(right).getAmount())
            {
                duplicates.add(transactions.get(right));
                right++;
            }
            else
            {
                left = right;
                right++;
            }
        }
        return duplicates;
    }
    public static void main(String[] args)
    {
        List<Transaction> transactions = Arrays.asList(
                new Transaction(new Date(2025, 3, 15), 100.0),
                new Transaction(new Date(2025, 1, 15), 100.0),
                new Transaction(new Date(2025, 1, 16), 200.0)
        );
        List<Customer> customers = Arrays.asList(
                new Customer("Alice", 1500.0),
                new Customer("Bob", 2500.0),
                new Customer("Charlie", 1200.0)
        );
        System.out.println("Original Transactions:");
        for (Transaction t : transactions)
        {
            System.out.println(t.getDate() + " - " + t.getAmount());
        }
        sortTransactionsByDate(transactions);
        System.out.println("\nSorted Transactions by Date:");
        for (Transaction t : transactions)
        {
            System.out.println(t.getDate() + " - " + t.getAmount());
        }
        System.out.println("\nDuplicate Transactions:");
        List<Transaction> duplicates = detectDuplicateTransactions(transactions);
        for (Transaction t : duplicates)
        {
            System.out.println(t.getDate() + " - " + t.getAmount());
        }
        System.out.println("\nOriginal Customers:");
        for (Customer c : customers)
        {
            System.out.println(c.getName() + " - " + c.getBalance());
        }
        sortCustomersByName(customers);
        System.out.println("\nSorted Customers by Name:");
        for (Customer c : customers)
        {
            System.out.println(c.getName() + " - " + c.getBalance());
        }
    }
}