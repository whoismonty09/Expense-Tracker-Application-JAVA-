import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Expense {
    String title;
    double amount;

    Expense(String title, double amount) {
        this.title = title;
        this.amount = amount;
    }

    void displayExpense() {
        System.out.println("Title: " + title + ", Amount: " + amount);
    }
}

public class Main {

    static ArrayList<Expense> expenses = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static final String FILE_NAME = "expenses.txt";

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n💸 Expense Tracker Application developed by Monty");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Save Expenses to File");
            System.out.println("4. Load Expenses from File");
            System.out.println("5. Calculate Total Expense");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addExpense();
                    break;
                case 2:
                    viewExpenses();
                    break;
                case 3:
                    saveExpensesToFile();
                    break;
                case 4:
                    loadExpensesFromFile();
                    break;
                case 5:
                    calculateTotal();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 6);
    }

    static void addExpense() {
        System.out.print("Enter expense title: ");
        String title = scanner.nextLine();

        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();

        expenses.add(new Expense(title, amount));
        System.out.println("✅ Expense added successfully!");
    }

    static void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses found.");
        } else {
            for (Expense e : expenses) {
                e.displayExpense();
            }
        }
    }

    static void saveExpensesToFile() {
        try {
            FileWriter writer = new FileWriter(FILE_NAME);
            for (Expense e : expenses) {
                writer.write(e.title + "," + e.amount + "\n");
            }
            writer.close();
            System.out.println("💾 Expenses saved to file!");
        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }

    static void loadExpensesFromFile() {
        try {
            File file = new File(FILE_NAME);
            Scanner fileScanner = new Scanner(file);
            expenses.clear();

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                expenses.add(new Expense(parts[0], Double.parseDouble(parts[1])));
            }

            fileScanner.close();
            System.out.println("📂 Expenses loaded from file!");
        } catch (Exception e) {
            System.out.println("No file found.");
        }
    }

    static void calculateTotal() {
        double total = 0;
        for (Expense e : expenses) {
            total += e.amount;
        }
        System.out.println("💰 Total Expense: " + total);
    }
}
