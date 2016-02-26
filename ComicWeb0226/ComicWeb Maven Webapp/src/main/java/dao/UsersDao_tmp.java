package dao;

import java.util.List;

import model.Users;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

public class UsersDao_tmp {
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<Users> getAllUser(){
		String hsql="from users";
		Session session=sessionFactory.getCurrentSession();
		Transaction transaction=session.beginTransaction();
		Query query=session.createQuery(hsql);
		 List<Users> tmp=query.list();
		 transaction.commit();
		return tmp;
		
	}
	public void addOneUser(){
		//String hsql="from users";
		Users users=new Users();
		users.setName("long");
		users.setId(25);
		users.setAge(12);
		Session session=sessionFactory.getCurrentSession();
		Transaction transaction=session.beginTransaction();
		session.save(users);
		transaction.commit();
		
	}
}
