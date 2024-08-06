
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conectaDAO {

    public static Connection conectar() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11", "root", "roger85ww");
            return conn;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao estabelecer a conexão: " + e.getMessage());
            return null;
        }
    }

    public static void encerrar (Connection connection){
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Falha ao fechar a conexão: " + e.getMessage());
            }
        }
    }
}
