package useraccountmanager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import user.User;

public class UserAccountManager
{
	private Map<String, User> users;
	private static final String USER_DATA_FILE = "user_data.txt";
	

	public UserAccountManager()
	{
        this.users = loadUserData();
    }
	
	
	public boolean createUser(String username,String password)
	{
		if(users.containsKey(username))
		{
			 System.out.println("Username already exists. Please choose a different one.");
	           return false;
		}
		

		
		User newUser = new User(username, password);
        users.put(username, newUser);
        saveUserData();
        System.out.println("User account created successfully.");
        return true;
	}
	
	
	public boolean authenticateUser(String username,String password)
	{
		User user = users.get(username);
		if(user!=null && user.getPassword().equals(password))
		{
			System.out.println("Authentication successful. Welcome, " + username + "!");
            return true;
		}
		else
		{
			 System.out.println("Invalid username or password.");
	            return false;
		}
	}
	
	
	private void saveUserData()
	{
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_DATA_FILE))) {
            oos.writeObject(users);
            System.out.println("User data saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	private Map<String,User> loadUserData()
	{
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USER_DATA_FILE))) {
            return (Map<String, User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No existing user data found. Creating new user database.");
            return new HashMap<>();
        }
	}
	
	
	
}
