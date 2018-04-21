

import org.hibernate.Session;

import com.pavikumbhar.dto.PortCharacterstic;
import com.pavikumbhar.entity.PortTable;

public class App {
	public static void main(String[] args) {
		System.out.println("Hibernate UserDefinedType");
		Session session = HibernateUtil.getSessionFactory().openSession();
		
	/*	PortCharacterstic portCharacterstic=new PortCharacterstic();
		PortCharacterstic portCharacterstic1=new PortCharacterstic();
		portCharacterstic.setPortName("Pravin");
		portCharacterstic.setPortValue("Kumbhar");
		portCharacterstic1.setPortName("Mangesh");
		portCharacterstic1.setPortValue("Kumbhar-Patil");
		PortTable portTable=new PortTable();
		portTable.setPortChar(new PortCharacterstic[]{portCharacterstic,portCharacterstic1} );
		session.getTransaction().begin();
		session.save(portTable);
		session.getTransaction().commit();
		*/
		PortTable portTableObj = session.get(PortTable.class, 1);
		

		for (int i = 0; i < portTableObj.getPortChar().length; i++) {
			
			System.out.println(portTableObj.getPortChar()[i]);
			
		}
		HibernateUtil.shutdown();
		
		System.out.println("Done");
	}
}
