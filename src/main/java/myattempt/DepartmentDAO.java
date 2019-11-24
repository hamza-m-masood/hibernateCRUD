package myattempt;

import org.hibernate.Transaction;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


public class DepartmentDAO {
	Configuration configObj;
	SessionFactory sessionFactory;

	public DepartmentDAO() {
		this.configObj = new Configuration();
		configObj.configure("hibernate.cfg.xml");
		this.sessionFactory = configObj.buildSessionFactory();
		

	}

	public void saveDepartment(Department department) {
		try {
			Session sessionObj = sessionFactory.openSession();
			Transaction transactionObj = sessionObj.beginTransaction();
			sessionObj.save(department);
			transactionObj.commit();
			sessionObj.close();
			System.out.println("Saved Successfully");
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	public List<Department> getDepartments(){
		Session session=sessionFactory.openSession();
		Query query = session.createQuery("from Department");
		List<Department> books = query.list();
		session.close();
		return books;
				
	}
	
	public int updateDepartment(Department department) {
		if (department.getDeptid() <= 0)
			return 0;
		Session session=sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String hql = "update Department set deptname = :name where id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", department.getDeptid());
		query.setParameter("name", department.getDeptname());
		int rowCount = query.executeUpdate();

		System.out.println("Rows affected: " + rowCount);
		tx.commit();
		session.close();
		return rowCount;
	}
	
	public int deleteBook(int id) {
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		String hql="delete from Department where id= :id";
		Query query=session.createQuery(hql);
		query.setParameter("id", id);
		int rowCount=query.executeUpdate();
		System.out.println("Rows affected: " +rowCount);
		transaction.commit();
		session.close();
		return rowCount;
		
	}
}
