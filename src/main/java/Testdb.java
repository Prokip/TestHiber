import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.util.UUID;

public class Testdb {

    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Testdb lib = new Testdb();
        try {
            lib.createDbTable();
            lib.addInTable();
            lib.selectBook();
            lib.selectOneBook();
            lib.updateBook();
            lib.deletBook();
        } catch (Exception e) { e.printStackTrace(); }
    }

     private void createDbTable() throws Exception {

         Connection conect = DBConnection.getDBConnectin();
         Statement statem = null;

        String createTableSQL = "CREATE TABLE LIBRARY("
                + "book_id INTEGER NOT NULL PRIMARY KEY, "
                + "name VARCHAR(20) NOT NULL, "
                + "author VARCHAR(20) NOT NULL, "
                + "numofpage INT NOT NULL)";
        try {
            /* Class.forName("org.postgresql.Driver");
            Connection conect = null;
            String URL = "jdbc:postgresql://localhost:5432/dbtest";
            String LOGIN = "postgres";
            String PASSWORD = "postgres";
            conect = DriverManager.getConnection(URL, LOGIN, PASSWORD);*/

            statem = conect.createStatement();

            statem.execute(createTableSQL);
            System.out.println("Table \"LIBRARY\" is created!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (statem != null) { statem.close(); }
            if (conect != null) { conect.close(); }
        }
    }

    private void addInTable() throws Exception {
              try {
            DBConnection.getDBConnectin();

            System.out.println("зв'язок налагоджено");

            String insertIntoTable = "INSERT INTO LIBRARY"
                    + "(name , author, numofpage, book_id)"
                    + "VALUES" + "(?, ?, ?, ?)";

            PreparedStatement  pSt=DBConnection.getDBConnectin().prepareStatement(insertIntoTable);

            System.out.println("введіть назву книги:");
            String name = scanner.nextLine();

            System.out.println("введіть автора книги:");
            String author = scanner.nextLine();

            System.out.println("введіть кількість сторінок книги:");
            int numOfPage = scanner.nextInt();

            int book_id= UUID.randomUUID().hashCode();

            pSt.setString(1, name);
            pSt.setString(2, author);
            pSt.setInt(3, numOfPage);
            pSt.setInt(4, book_id);

            pSt.executeUpdate();
            System.out.println(""+ name +" "+ author +" "+ numOfPage+" "+book_id);

             pSt.close();
        }catch (Exception e) { System.out.println(e.getMessage()); }
     finally {
            if (DBConnection.getDBConnectin() != null) { DBConnection.getDBConnectin().close(); }
    }
    }

    private void selectBook() throws Exception{
       try {
           DBConnection.getDBConnectin();

           String selectBook="SELECT * FROM LIBRARY";
           PreparedStatement pSt=DBConnection.getDBConnectin().prepareStatement(selectBook);

           ResultSet resalt=pSt.executeQuery();
           while (resalt.next()){
               Integer bookId=resalt.getInt("book_id");
               String name=resalt.getString("name");
               String author=resalt.getString("author");
               Integer numOfPage=resalt.getInt("numOfPage");

               System.out.println("name of book is "+name);
               System.out.println("author of book is "+author);
               System.out.println("number of page of this book is "+numOfPage);
               System.out.println("id this of book is "+bookId);
           }
            pSt.close();
       }catch (Exception e){ System.out.println(e.getMessage());}
       finally {
           if (DBConnection.getDBConnectin() != null) { DBConnection.getDBConnectin().close(); }
       }
    }

    private void selectOneBook() throws Exception{
        try {
            DBConnection.getDBConnectin();

            String selectBook="SELECT * FROM LIBRARY WHERE name = ?";
            PreparedStatement pSt=DBConnection.getDBConnectin().prepareStatement(selectBook);
            System.out.println("введіть назву книги:");
            String name = scanner.nextLine();
            pSt.setString(1, name);
            ResultSet resalt=pSt.executeQuery();

            while (resalt.next()){
                Integer bookId=resalt.getInt("book_id");
                name=resalt.getString("name");
                String author=resalt.getString("author");
                int numOfPage=resalt.getInt("numOfPage");

                System.out.println("name of book is "+name);
                System.out.println("author of book is "+author);
                System.out.println("number of page of this book is "+numOfPage);
                System.out.println("id this of book is "+bookId);
            }
             pSt.close();
        }catch (Exception e){ System.out.println(e.getMessage());}
        finally {
            if (DBConnection.getDBConnectin() != null) { DBConnection.getDBConnectin().close(); }
        }
    }

    private void updateBook() throws Exception{
        try{
            DBConnection.getDBConnectin();

            String updateBook="UPDATE LIBRARY SET name = ? WHERE author = ?";
            PreparedStatement pSt=DBConnection.getDBConnectin().prepareStatement(updateBook);
            System.out.println("введіть назву книги:");
            String name = scanner.nextLine();
            pSt.setString(1, name);

            System.out.println("введіть автора книги:");
            String author = scanner.nextLine();
            pSt.setString(2, author);

            pSt.executeUpdate();
            System.out.println("the book updated");

             pSt.close();
        }catch (Exception e) { System.out.println(e.getMessage()); }
        finally {
            if (DBConnection.getDBConnectin() != null) { DBConnection.getDBConnectin().close(); }
        }
    }

    private void deletBook() throws Exception{
        try{
            DBConnection.getDBConnectin();

            String deletBook="DELETE FROM LIBRARY WHERE name = ?";
            PreparedStatement pSt=DBConnection.getDBConnectin().prepareStatement(deletBook);
            System.out.println("введіть назву книги:");
            String name = scanner.nextLine();
            pSt.setString(1, name);

            pSt.executeUpdate();
            System.out.println("the book deleted");

            pSt.close();
        }catch (Exception e){ System.out.println(e.getMessage()); }
        finally {
            if (DBConnection.getDBConnectin() != null) { DBConnection.getDBConnectin().close(); }
        }
    }
}
