package base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import domain.RateDomainModel;
import util.HibernateUtil;



public class RateDAL {
	public static ArrayList<RateDomainModel> getTable(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		ArrayList<RateDomainModel> rates = new ArrayList<RateDomainModel>();
		
		
		try {
			tx = session.beginTransaction();	
			
			List holdrates = session.createQuery("FROM RateDomainModel").list();
			for (Iterator iterator = holdrates.iterator(); iterator.hasNext();) {
				RateDomainModel rate = (RateDomainModel) iterator.next();
				rates.add(rate); 
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rates;
	}
	public static double getRate(int GivenCreditScore) {

		ArrayList<RateDomainModel> rates = getTable();
		double returnRate = 0;
		
		// rates are organized by minimum credit score
		
		for (RateDomainModel r : rates){
			if (GivenCreditScore >= r.getMinCreditScore()){
				returnRate = r.getInterestRate();
			}
			else{
				continue;
			}
		}
		return returnRate;
	}
}