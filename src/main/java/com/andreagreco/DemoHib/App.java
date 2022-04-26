package com.andreagreco.DemoHib;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class App 
{

	public static void main( String[] args )
    {
        Alien al = new Alien();
        AlienName alName = new AlienName();
        alName.setFname("Frederick");
        alName.setMname("Alfred");
        alName.setLname("Richardson");

        al.setAid(101);
        al.setAname(alName);
        al.setColor("green");
        
        Configuration con = new Configuration().configure()
        		.addAnnotatedClass(Alien.class);
        ServiceRegistry reg = new ServiceRegistryBuilder()
        		.applySettings(con.getProperties()).buildServiceRegistry();
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        session.save(al);
        tx.commit();
        Alien readAl = new Alien();
        readAl = (Alien) session.get(Alien.class, 101);
        System.out.println(readAl);
        
    }
}
