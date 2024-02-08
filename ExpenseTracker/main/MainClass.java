//package main;
//
//import java.util.Date;
//
//import java.util.List;
//
//import expense.Expense;
//import expense.ExpenseManager;
//import expense.FileHandler;
//import useraccountmanager.UserAccountManager;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//
//		
//		
//
//
//		public class MainClass {
//		    public static void main(String[] args) {
//		        Scanner scanner = new Scanner(System.in);
//		        UserAccountManager userAccountManager = new UserAccountManager();
//		        boolean exit = false;
//
//		        while (!exit) {
//		            System.out.println("\n1. Create User");
//		            System.out.println("2. Login");
//		            System.out.println("3. Exit");
//
//		            System.out.print("Enter your choice: ");
//		            int choice = scanner.nextInt();
//		            scanner.nextLine(); // Consume the newline character
//
//		            switch (choice) {
//		                case 1:
//		                    System.out.print("Enter username: ");
//		                    String username = scanner.nextLine();
//		                    System.out.print("Enter password: ");
//		                    String password = scanner.nextLine();
//		                    userAccountManager.createUser(username, password);
//		                    break;
//		                case 2:
//		                    System.out.print("Enter username: ");
//		                    String loginUsername = scanner.nextLine();
//		                    System.out.print("Enter password: ");
//		                    String loginPassword = scanner.nextLine();
//		                    boolean isAuthenticated = userAccountManager.authenticateUser(loginUsername, loginPassword);
//		                    if (isAuthenticated) {
//		                        ExpenseManager expenseManager = new ExpenseManager();
//
//		                        // Prompt user to add expenses
//		                        System.out.println("Enter details for each expense (press 'q' to quit):");
//		                        while (true) {
//		                            System.out.print("Date (yyyy-MM-dd): ");
//		                            String dateString = scanner.nextLine();
//		                            if (dateString.equalsIgnoreCase("q")) break;
//		                            System.out.print("Category: ");
//		                            String category = scanner.nextLine();
//		                            System.out.print("Amount: ");
//		                            double amount = scanner.nextDouble();
//		                            scanner.nextLine(); // Consume the newline character
//
//		                            // Convert date string to Date object
//		                            Date date = null;
//		                            try {
//		                                date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
//		                            } catch (ParseException e) {
//		                                e.printStackTrace();
//		                                System.out.println("Invalid date format.");
//		                            }
//
//		                            expenseManager.addExpense(new Expense(date, category, amount));
//		                        }
//
//		                        List<Expense> expenses = expenseManager.listExpenses();
//		                        System.out.println("Expenses: " + expenses);
//
//		                        System.out.println(expenseManager.getCategoryWiseSummation());
//
//		                        FileHandler.saveExpenses(expenses, "EXPENSE_DATA_FILE");
//		                        List<Expense> loadedExpenses = FileHandler.loadExpenses("EXPENSE_DATA_FILE");
//		                        System.out.println("Loaded expenses: " + loadedExpenses);
//		                    } else {
//		                        System.out.println("Invalid username or password.");
//		                    }
//		                    break;
//		                case 3:
//		                    exit = true;
//		                    break;
//		                default:
//		                    System.out.println("Invalid choice. Please enter a valid option.");
//		            }
//		        }
//
//		        scanner.close();
//		    }
//		}
//
////	}
////
////}

package main;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import expense.Expense;
import expense.ExpenseManager;
import expense.FileHandler;
import useraccountmanager.UserAccountManager;

public class MainClass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserAccountManager userAccountManager = new UserAccountManager();
        ExpenseManager expenseManager = null;
        boolean isAuthenticated=false;
        boolean exit = false;

        while (!exit) {
            System.out.println("\n1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    userAccountManager.createUser(username, password);
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();
                    isAuthenticated = userAccountManager.authenticateUser(loginUsername, loginPassword);
                    if (isAuthenticated) {
                        String expenseFileName = "expenses_" + loginUsername + ".txt";
                        File expenseFile = new File(expenseFileName);
                        if (!expenseFile.exists()) {
                            try {
                                expenseFile.createNewFile();
                                System.out.println("New expense file created for user: " + loginUsername);
                            } catch (IOException e) {
                                System.out.println("Error creating expense file: " + e.getMessage());
                            }
                        }
                        expenseManager = new ExpenseManager();
                        List<Expense> loadedExpenses = FileHandler.loadExpenses(expenseFileName);
                        if (loadedExpenses != null) {
                            for (Expense expense : loadedExpenses) {
                                expenseManager.addExpense(expense);
                            }
                        }
                        System.out.println("Login successful.");
                    } 
                    else {
                        System.out.println("Invalid username or password.");
                    }
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }

            if (expenseManager != null && isAuthenticated) {
                while (true) {
                    System.out.println("\n1. Add Expense");
                    System.out.println("2. View Expenses");
                    System.out.println("3. Save Expenses to File");
                    System.out.println("4. Exit");

                    System.out.print("Enter your choice: ");
                    int menuChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    switch (menuChoice) {
                        case 1:
                            // Add Expense
                            System.out.println("Enter details for the expense:");
                            System.out.print("Date (yyyy-MM-dd): ");
                            String dateString = scanner.nextLine();
                            System.out.print("Category: ");
                            String category = scanner.nextLine();
                            System.out.print("Amount: ");
                            double amount = scanner.nextDouble();
                            scanner.nextLine(); // Consume the newline character

                            // Convert date string to Date object
                            Date date = null;
                            try {
                                date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
                            } catch (ParseException e) {
                                e.printStackTrace();
                                System.out.println("Invalid date format.");
                                continue;
                            }

                            expenseManager.addExpense(new Expense(date, category, amount));
                            break;
                        case 2:
                            // View Expenses
                            List<Expense> expenses = expenseManager.listExpenses();
                            System.out.println("Expenses: " + expenses);
                            System.out.println(expenseManager.getCategoryWiseSummation());
                            break;
                        case 3:
                            // Save Expenses to File
                            FileHandler.saveExpenses(expenseManager.listExpenses(), "expenses.txt");
                            System.out.println("Expenses saved to file.");
                            break;
                        case 4:
                            exit = true;
                            break;
                        default:
                            System.out.println("Invalid choice. Please enter a valid option.");
                    }

                    if (exit) break;
                }
            }
        }

        scanner.close();
    }
}

