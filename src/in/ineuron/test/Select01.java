package in.ineuron.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import in.ineuron.util.HibernateUtil;

public class Select01 {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Session session = null;
		//without adding the entity, So the objects will be stored a Object[] type  
		try {

			session = HibernateUtil.getSession();

			NativeQuery<Object[]> nativeQuery = session.createSQLQuery("SELECT * FROM Product WHERE pcost<=:cost");

			nativeQuery.setParameter("cost", 15000);

			List<Object[]> list = nativeQuery.getResultList();

			System.out.println("ID\tNAME\tCOST\tQTY");
			list.forEach(row -> {
				for (Object object : row) {
					System.out.print(object + "\t");
				}
				System.out.println();
			});

		} catch (HibernateException e) {
			e.printStackTrace();
		}

		finally {
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
	}

}
