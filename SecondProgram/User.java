
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
public class User {




    public int id;
    public String login;
    public String password;
    public String email;

    public User(String login, String password, String email) {
        this.login = login;
        this.setPassword(password);
        this.email = email;
    }

    public User(){}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {

        return id;
    }

    /**
     * Funkcja zapisująca  nowy obiekt user do bazy danych
     * @param conn
     * @throws SQLException
     */


    public	void	saveToDB(Connection	conn)	throws	SQLException	{
        if	(this.id	==	0)	{                           //Zapisujem obiekt tylkogdy id=0
            String	sql	=	"INSERT	INTO	Users(login,	email,	password)	VALUES	(?,	?,	?)";
            String	generatedColumns[]	=	{	"ID"	};
            PreparedStatement	preparedStatement;
            preparedStatement	=	conn.prepareStatement(sql,	generatedColumns);
            preparedStatement.setString(1,	this.login);
            preparedStatement.setString(2,	this.email);
            preparedStatement.setString(3,	this.password);
            preparedStatement.executeUpdate();
            ResultSet	rs	=	preparedStatement.getGeneratedKeys();
            if	(rs.next())	{
                this.id	=	rs.getInt(1);       //Pobieramy wstawion do bazy identyfikator a nastepnie wstawiamy id obiektu
            }
        }
    }




}
