package expense;

import java.io.Serializable;
import java.util.Date;

public class Expense implements Serializable
{
	private Date date;
    private String category;
    private double amount;
   
    public Expense(Date date, String category, double amount)
    {
        this.date = date;
        this.category = category;
        this.amount = amount;
       
    }

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Expense [date=" + date + ", category=" + category + ", amount=" + amount + "]";
	}
    
    
	
}
