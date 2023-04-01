package in.ineuron.test;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.type.StandardBasicTypes;


import in.ineuron.model.Product;
import in.ineuron.util.HibernateUtil;

public class Select3 {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Session session = null;
		// without adding the entity, So the objects will be stored a Object[] type
		try {

			session = HibernateUtil.getSession();

			NativeQuery<Object[]> nativeQuery = session
					.createSQLQuery("SELECT pname,pcost FROM Product WHERE pcost>=:cost1 AND pcost<=:cost2");

			// Setting the parameter
			nativeQuery.setParameter("cost1", 5000);
			nativeQuery.setParameter("cost2", 16000);

			// Binding the datatype of output parameters
			nativeQuery.addScalar("pname", StandardBasicTypes.STRING);
			nativeQuery.addScalar("pcost", StandardBasicTypes.INTEGER);

			List<Object[]> list = nativeQuery.getResultList();

			// Processing the result
			System.out.println("NAME\tCOST");
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
