import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.LinkedHashMap;

public class Expense {
   
    private static final String EXPENSE_FILE_PATH = "expenses.csv";
    private static final String BUDGET_FILE_PATH = "budgets.txt";
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
       
        initializeFiles();
        
        System.out.println("--- Expense Tracker CLI Application ---");
        
        boolean running = true;
        while (running) {
            displayMenu();
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    addNewExpense();
                    break;
                case "2":
                    performAnalysis(EXPENSE_FILE_PATH);
                    break;
                case "3":
                    manageBudgets();
                    break;
                case "4":
                    running = false;
                    System.out.println("Exiting Expense Tracker. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, 3, or 4.");
            }
        }
        sc.close();
    }
    
  
    
    private static void initializeFiles() {
        try {
          
            java.io.File expenseFile = new java.io.File(EXPENSE_FILE_PATH);
            if (!expenseFile.exists()) {
                try (PrintWriter pw = new PrintWriter(new FileWriter(expenseFile))) {
                    pw.println("Date,Category,Amount");
                }
                System.out.println("Created new expense file: " + EXPENSE_FILE_PATH);
            }
            
           
            java.io.File budgetFile = new java.io.File(BUDGET_FILE_PATH);
            if (!budgetFile.exists()) {
                budgetFile.createNewFile();
                System.out.println("Created new budget file: " + BUDGET_FILE_PATH);
            }
        } catch (IOException e) {
            System.err.println("CRITICAL ERROR: Could not initialize data files: " + e.getMessage());
        }
    }

    private static void displayMenu() {
        System.out.println("\n-------------------------------------");
        System.out.println("Select an option:");
        System.out.println("1. Data Management: Add New Expense");
        System.out.println("2. Analysis: View Total Spend & Category Breakdown");
        System.out.println("3. Budgeting: Set and Track Budgets");
        System.out.println("4. Exit");
        System.out.print("Enter choice (1-4): ");
    }
    
    
    private static void addNewExpense() {
        System.out.println("\n--- Add New Expense ---");
        System.out.print("Enter Date (YYYY-MM-DD): ");
        String date = sc.nextLine().trim();
        System.out.print("Enter Category (e.g., Food, Transport): ");
        String category = sc.nextLine().trim();
        System.out.print("Enter Amount: ");
        String amountStr = sc.nextLine().trim();

        try {
            double amount = Double.parseDouble(amountStr);
            if (amount <= 0) {
                System.out.println("Amount must be positive.");
                return;
            }
            
            
            String newRecord = String.format("%s,%s,%.2f", date, category, amount);
            
            try (PrintWriter pw = new PrintWriter(new FileWriter(EXPENSE_FILE_PATH, true))) {
                pw.println(newRecord);
                System.out.println("SUCCESS: Expense added: " + newRecord);
            }
        } catch (NumberFormatException e) {
            System.out.println("ERROR: Invalid amount format. Please enter a valid number.");
        } catch (IOException e) {
            System.out.println("ERROR writing to file: " + e.getMessage());
        }
    }

    
    private static Map<String, Double> loadExpenseData(String filePath) {
      
        Map<String, Double> categoryMap = new HashMap<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true;
            
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                
                if (firstLine) { 
                    firstLine = false;
                    continue; 
                }
                
                String[] parts = line.split(",");
                
                if (parts.length < 3) {
                    
                    continue; 
                }
                
                String category = parts[1].trim();
                
                double amount = 0.0;
                try {
                    amount = Double.parseDouble(parts[2].trim());
                } catch (NumberFormatException e) {
                    System.out.println("NFR WARNING: Skipping invalid amount in file: " + parts[2]);
                    continue; 
                }
                
                categoryMap.put(category, categoryMap.getOrDefault(category, 0.0) + amount);
            }
        } catch (IOException e) {
            System.out.println("ERROR: File reading failed: " + e.getMessage());
            return null; 
        }
        return categoryMap;
    }

    private static void performAnalysis(String filePath) {
        System.out.println("\n--- Expense Analysis ---");
        Map<String, Double> categoryMap = loadExpenseData(filePath);
        
        if (categoryMap == null) {
            return; 
        }
        
        double totalSpend = categoryMap.values().stream().mapToDouble(Double::doubleValue).sum();

        if (totalSpend == 0.0) {
            System.out.println("No valid expenses found in the file.");
        } else {
            System.out.printf("Total Spend: %.2f\n", totalSpend);
            System.out.println("Category Breakdown:");
           
            Map<String, Double> sortedCategories = new LinkedHashMap<>();
            categoryMap.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .forEachOrdered(x -> sortedCategories.put(x.getKey(), x.getValue()));

            for (Map.Entry<String, Double> entry : sortedCategories.entrySet()) {
                double percent = (entry.getValue() / totalSpend) * 100;
                System.out.printf("  - %s: %.2f (%.2f%%)\n", entry.getKey(), entry.getValue(), percent);
            }
        }
    }
    
   
    
    private static Map<String, Double> loadBudgets() {
        Map<String, Double> budgets = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(BUDGET_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String category = parts[0].trim();
                    try {
                        double budget = Double.parseDouble(parts[1].trim());
                        budgets.put(category, budget);
                    } catch (NumberFormatException e) {
                        System.out.println("Budget file warning: Invalid amount for category " + category);
                    }
                }
            }
        } catch (IOException e) {
         
        }
        return budgets;
    }
    
    private static void saveBudget(String category, double budget) {
        
        Map<String, Double> budgets = loadBudgets();
        budgets.put(category, budget);
        
        try (PrintWriter pw = new PrintWriter(new FileWriter(BUDGET_FILE_PATH))) {
            for (Map.Entry<String, Double> entry : budgets.entrySet()) {
                pw.printf("%s: %.2f\n", entry.getKey(), entry.getValue());
            }
            System.out.println("SUCCESS: Budget set for " + category + " at " + budget);
        } catch (IOException e) {
            System.out.println("ERROR saving budget: " + e.getMessage());
        }
    }
    
    private static void manageBudgets() {
        System.out.println("\n--- Budget Management ---");
        System.out.println("1. Set Category Budget");
        System.out.println("2. View Budget Status Report");
        System.out.print("Enter choice (1-2): ");
        String choice = sc.nextLine().trim();
        
        if ("1".equals(choice)) {
            setCategoryBudget();
        } else if ("2".equals(choice)) {
            viewBudgetReport();
        } else {
            System.out.println("Invalid choice.");
        }
    }
    
    private static void setCategoryBudget() {
        System.out.print("Enter Category to set budget for: ");
        String category = sc.nextLine().trim();
        System.out.print("Enter Budget amount for " + category + ": ");
        String amountStr = sc.nextLine().trim();
        
        try {
            double amount = Double.parseDouble(amountStr);
            if (amount < 0) {
                System.out.println("Budget cannot be negative.");
                return;
            }
            saveBudget(category, amount);
        } catch (NumberFormatException e) {
            System.out.println("ERROR: Invalid budget amount format.");
        }
    }
    
    private static void viewBudgetReport() {
        System.out.println("\n--- Budget Status Report ---");
        Map<String, Double> currentSpending = loadExpenseData(EXPENSE_FILE_PATH);
        Map<String, Double> budgets = loadBudgets();
        
        if (currentSpending == null || currentSpending.isEmpty()) {
            System.out.println("No spending data available to compare against budgets.");
            return;
        }
        
        if (budgets.isEmpty()) {
            System.out.println("No budgets have been set yet.");
            return;
        }
        
        System.out.printf("%-15s %-12s %-12s %s\n", "CATEGORY", "BUDGET", "ACTUAL", "STATUS");
        System.out.println("-------------------------------------------------");
        
        for (Map.Entry<String, Double> budgetEntry : budgets.entrySet()) {
            String category = budgetEntry.getKey();
            double budget = budgetEntry.getValue();
            double actualSpend = currentSpending.getOrDefault(category, 0.0);
            
            String status;
            if (budget == 0.0) {
                status = "N/A";
            } else if (actualSpend > budget) {
                status = String.format("OVER (%.2f)", actualSpend - budget);
            } else {
                status = String.format("Under (%.2f)", budget - actualSpend);
            }
            
            System.out.printf("%-15s %-12.2f %-12.2f %s\n", category, budget, actualSpend, status);
        }
    }
}