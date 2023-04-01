package in.ineuron.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import in.ineuron.model.Product;
import in.ineuron.util.HibernateUtil;

public class Select2 {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Session session = null;
		// without adding the entity, So the objects will be stored a Object[] type
		try {

			session = HibernateUtil.getSession();

			NativeQuery<Product> nativeQuery = session.createSQLQuery("SELECT * FROM Product WHERE pcost>=:cost1 AND pcost<=:cost2");
			
			// Setting the parameter
			nativeQuery.setParameter("cost1", 5000);
			nativeQuery.setParameter("cost2", 16000);
			
			// Mapping result with Entity class
			nativeQuery.addEntity(Product.class);
			
			// Executing to get the result
			List<Product> list = nativeQuery.getResultList();
			
			// Processing the result
			list.forEach(System.out::println);

		} catch (HibernateException e) {
			e.printStackTrace();
		}

		finally {
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
	}

}
