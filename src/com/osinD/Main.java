package com.osinD;

import java.sql.*;

public class Main {

    /**
     * Poniższe String służa do tworzenia Tabeli
     * Pomiędzy tabelami  Users Messages mamy relację jeden do wielu
     * Pomiędzy tabelami Users a tabelą adress mamy relację jeden do jednego
     * Pomiędzy tabelami Messages Gifs mamy relację wiele do wielu
     */
    private static final String QUERY1 = "CREATE TABLE Users( id int auto_increment, name VARCHAR(60), email VARCHAR(60) unique, password VARCHAR(60), PRIMARY KEY(id));";
    private static final String QUERY2 = "CREATE TABLE Messages( id int auto_increment, user_id int, message text, PRIMARY KEY(id), foreign key(user_id) references Users(id));";
    private static final String QUERY3 = "CREATE TABLE Adress(user_id int NOT NULL, street varchar(255), PRIMARY KEY(user_id), FOREIGN KEY(user_id) REFERENCES Users(id) ON DELETE CASCADE );";
    private static final String QUERY4 = "CREATE TABLE Gifs(id int NOT NULL AUTO_INCREMENT , descryption varchar(255), PRIMARY KEY(id));";
    private static final String QUERY5 = "CREATE TABLE Messages_Gifs(id int AUTO_INCREMENT , message_id int NOT NULL, gif_id int NOT NULL, PRIMARY KEY(id) ,FOREIGN KEY(message_id) REFERENCES Messages(id), FOREIGN KEY(gif_id) REFERENCES Gifs(id));";
    public static void main(String[] args) {
        String url ="jdbc:mysql://localhost:3306/osinski_intelij?autoReconnect=true&useSSL=false";

        try	(Connection	conn =	DriverManager.getConnection(url, "root", "coderslab")) {

            /**
             * Tworzenie tabeli w bazie danych Users i Messages
             */
            try(PreparedStatement stmt=conn.prepareStatement(QUERY1)){
            stmt.execute();
            }catch(SQLException e){
            System.out.println("TAbele juz stworzono");
            }

            try(PreparedStatement stmt=conn.prepareStatement(QUERY3)){
                stmt.execute();
            }catch(SQLException e){
                System.out.println("TAbele juz stworzono");
            }

            try(PreparedStatement stmt=conn.prepareStatement(QUERY2)) {
                stmt.execute();
            }catch(SQLException e){
                System.out.println("TAbele juz stworzono");
            }
            try(PreparedStatement stmt=conn.prepareStatement(QUERY4)) {
                stmt.execute();
            }catch(SQLException e){
                System.out.println("TAbele juz stworzono");
            }
            try(PreparedStatement stmt=conn.prepareStatement(QUERY5)) {
                stmt.execute();
            }catch(SQLException e){
                System.out.println("TAbele juz stworzono");
            }

            /**
             *Wypełnianie tabeli Users
             */
            try(PreparedStatement stmt =conn.prepareStatement(Inserts.sql1)){
                stmt.execute();
            }catch(SQLException e){
                System.out.println("Błędne wypełnianie tabeli");
            }

            try(PreparedStatement stmt =conn.prepareStatement(Inserts.sql2)){
                stmt.execute();
            }catch(SQLException e){
                System.out.println("Błędne wypełnianie tabeli");
            }
            try(PreparedStatement stmt =conn.prepareStatement(Inserts.sql3)){
                stmt.execute();
            }catch(SQLException e){
                System.out.println("Błędne wypełnianie tabeli");
            }
            try(PreparedStatement stmt =conn.prepareStatement(Inserts.sql4)){
                stmt.execute();
            }catch(SQLException e){
                System.out.println("Błędne wypełnianie tabeli");
            }




            /**
             * Prepared Statement
             * Stworzenie prostego zapytania do bazy danch
             * do zapytania jeżeli  dane byłyby wprowadzane przez użytkownika
             * dane przesyłąne do bazy są odporne na ataki SQL Injection
             *
             * Następnie za pomocą ResultSet wyswitlamy wniki naszego zapytania
             */

            PreparedStatement	preStm	=	conn.prepareStatement("SELECT	*	FROM	Users	WHERE	id=	?");
            int	userId	= 1	;
            preStm.setInt(1,	userId);

            ResultSet rs =preStm.executeQuery();

            while(rs.next()){   //tutaj sprawdzamy cz w zaptania są jeszxcze jakieś elementy
                String firstName = rs.getString("name");
                String email = rs.getString("email");
                System.out.println(firstName+ " "+email);
            }

            /**
             * Zamiana zawartości w bazie danch
             * wyświetlenie zmienionej danej w konsoli
             *
             */
            String changeVal = "UPDATE Users SET name='RZADZE' WHERE id=1";
            try(PreparedStatement stmt=conn.prepareStatement(changeVal)){
                stmt.execute();
            }catch(SQLException e){
                System.out.println("Bad update");
            }
            PreparedStatement	preStm1	=	conn.prepareStatement("SELECT  * FROM  Users Where id=1");
            rs= preStm1.executeQuery();
            while(rs.next()){   //tutaj sprawdzamy cz w zaptania są jeszxcze jakieś elementy
                String firstName = rs.getString("name");
                String email = rs.getString("email");
                System.out.println(firstName+ " "+email);
            }

            /**
             * Dodamy kolumnę do tabeli Messages
             *
             */
            String updateTable ="ALTER TABLE  Messages ADD created DATE ";

            try(PreparedStatement stmt=conn.prepareStatement(updateTable)){
                stmt.execute();
            }catch(SQLException e){
                System.out.println("Bad update");
            }



        }catch(SQLException e){
            e.printStackTrace();
        }

    }
}
