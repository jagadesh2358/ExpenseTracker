package expense;

import java.io.*;
import java.util.*;

public class FileHandler
{
	public static void saveExpenses(List<Expense> expenses, String filename) {
        // Implement saving expenses to a file
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(expenses);
            System.out.println("Expenses saved to file: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static List<Expense> loadExpenses(String filename) {
//        // Implement loading expenses from a file
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
//            return (List<Expense>) ois.readObject();
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//            return new ArrayList<>(); // Dummy return for now
//        }
//    }
	
	public static List<Expense> loadExpenses(String filename) {
	    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
	        return (List<Expense>) ois.readObject();
	    } catch (EOFException e) {
	        // Handle EOFException gracefully
	        System.err.println("End of file reached unexpectedly.");
	    } catch (IOException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	    return new ArrayList<>();

}
}
