package in.ineuron.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import in.ineuron.util.HibernateUtil;

public class Insert {

	public static void main(String[] args) {

		Session session = null;
		Transaction transaction = null;
		boolean flag = false;

		try {

			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			NativeQuery nativeQuery = session.createSQLQuery("INSERT INTO Product(pname, pcost, pqty) values(?,?,?)");

			nativeQuery.setParameter(1, "fastrack");
			nativeQuery.setParameter(2, 5000);
			nativeQuery.setParameter(3, 15);

			int row = nativeQuery.executeUpdate();
			flag = true;

		} catch (HibernateException e) {
			e.printStackTrace();
		}

		finally {
			if (flag) {
				transaction.commit();
				System.out.println("Record Inserted Successfully.");
			} else {
				transaction.rollback();
				System.out.println("Failed to insert.");
			}
		}

	}

}
