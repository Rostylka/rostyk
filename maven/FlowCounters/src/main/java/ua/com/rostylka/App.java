package ua.com.rostylka;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ua.com.rostylka.models.FlowCounter;

public class App {
    public static void main(String[] args) {
        FlowCounter counter = new FlowCounter();
        counter.setMode("G100");
        counter.setType("KURS-01");
        counter.setqMin(0.65);
        counter.setqMax(160);

        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(FlowCounter.class);

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        SessionFactory sf = configuration.buildSessionFactory();

        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        //session.delete();
        transaction.commit();
        session.close();

    }
}
