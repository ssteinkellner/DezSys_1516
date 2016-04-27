package ssteinkellner;

import java.util.Map;
import java.util.TreeMap;

public class UserRepository {
	public static final int INITIALIZED = 0, SAVED = 1, USER_EXISTS = 2, NO_SUCH_USER = 3, DB_ERROR = 4;
	private int lastState = INITIALIZED;
	Map<String,User> users;

	public UserRepository(){
		users = new TreeMap<String,User>();
		
		users.put("test@test.test",new User("test@test.test","test","test"));
	}
	
	public User getUser(String email){
		if(users.containsKey(email)){
			lastState = USER_EXISTS;
			return users.get(email);
		}

		lastState = NO_SUCH_USER;
		return null;
	}
	
	public int putUser(User user){
		if(users.containsKey(user.getEmail())){
			lastState = USER_EXISTS;
			return USER_EXISTS;
		}else{
			users.put(user.getEmail(), user);
			lastState = SAVED;
			return SAVED;
		}
	}
	
	public int getLastState(){
		return lastState;
	}
}
