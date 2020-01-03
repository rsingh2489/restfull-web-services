package com.wbt.restfullwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

private static List<User> users=new ArrayList<>();
private static int usesCount=2;

static {
	
	users.add(new User(1,"Rahul",new Date("24-jan-1989")));
	users.add(new User(2,"Mohit",new Date("24-jan-1989")));
}
	public List<User> findAllUser()
	{ 
		return users;
	}
	public User save(User user)
	{
		if(user.getId()==null)
		{
			user.setId(++usesCount);
		}
		users.add(user);
		return user;	
	}
	
	public User findById(int id)
	{ 
		for(User user:users)
		{
			if(user.getId()==id)
			{
				return user;
			}
		}	
		return null;
	}
	
	public User deleteById(int id)
	{
		Iterator<User> itr=users.iterator();
		if(itr.hasNext())
		{
			User user=itr.next();
			if(user.getId()==id)
			{
				itr.remove();
				return user;
			}
		}
		
		return null;
		
	}
	
}
