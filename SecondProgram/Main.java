import java.sql.*;


public class Main {

    /**
     * Funkcja wczytująca użytownika  z bazy danych po id
     * @param conn
     * @param id
     * @return
     * @throws SQLException
     */

    static	public	User	loadUserById(Connection	conn,	int	id)	throws	SQLException	{
        String	sql	=	"SELECT	*	FROM	Users	where	id=?";
        PreparedStatement	preparedStatement;
        preparedStatement	=	conn.prepareStatement(sql);
        preparedStatement.setInt(1,	id);
        ResultSet resultSet	=	preparedStatement.executeQuery();
        if	(resultSet.next())	{
            User	loadedUser	=	new	User();
            loadedUser.id	=	resultSet.getInt("id");
            loadedUser.login	=	resultSet.getString("login");
            loadedUser.password	=	resultSet.getString("password");
            loadedUser.email	=	resultSet.getString("email");
            return	loadedUser;}
        return	null;}

    private static final String QUERY1 = "CREATE TABLE User( id int auto_increment, login VARCHAR(60), email VARCHAR(60) unique, password VARCHAR(60), PRIMARY KEY(id));";

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/OsinskiMySQL?autoReconnect=true&useSSL=false";

        try (Connection conn = DriverManager.getConnection(url, "root", "coderslab")) {

            try(PreparedStatement stmt=conn.prepareStatement(QUERY1)){
                stmt.execute();
            }catch(SQLException e){
                System.out.println("TAbele juz stworzono");
            }



            /*
            User usr1 = new User();
            usr1.setLogin("osinXDDD");
            usr1.setEmail("osnXDDD@gmail.com");
            usr1.setPassword("alamakota");
            usr1.saveToDB(conn);
            */
            User usr = new User();

            usr = loadUserById(conn , 1);
            System.out.println(usr.login+" "+usr.email);

        } catch (SQLException e) {



            e.printStackTrace();
        }

    }
}
