package DbNewTest;

import org.hibernate.Session;

public class TestWork {
    public static void main(String[] args) {
        System.out.println("Hibernate перевірка");

        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        LibraryEntity contactEntity = new LibraryEntity();


        contactEntity.setName("dffgg");
        contactEntity.setAuthor("dfffffff");
        contactEntity.setNumofpage(12);


        session.save(contactEntity);
        session.getTransaction().commit();

        session.close();


    }
}
