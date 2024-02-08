package expense;

import java.util.*;

public class ExpenseManager
{
	private List<Expense> expenses;
	

    public ExpenseManager() {
        this.expenses = new ArrayList<>();
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public List<Expense> listExpenses() {
        return expenses;
    }

    public Map<String, Double> getCategoryWiseSummation() {
        Map<String, Double> categoryWiseSum = new HashMap<>();
        for (Expense expense : expenses) {
            String category = expense.getCategory();
            double amount = expense.getAmount();
            categoryWiseSum.put(category, categoryWiseSum.getOrDefault(category, 0.0) + amount);
        }
        return categoryWiseSum;
    }
}
