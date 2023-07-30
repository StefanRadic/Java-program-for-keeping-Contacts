package servleti;

import data.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Stefan
 * 
 * DBServlet class used to make queries and add info to the mySQL table.
 */
public class DBServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
        Statement stmt = null;
        Connection con = null;
        String poruka = "";
        
        /*
            Making a connection on DB.java
        */
        try {
            con = DB.getInstance().getConnection();
            if(con == null) {
                request.setAttribute("errormsg", "Konekcija nije uspostavljena, proverite da li mySQL tabela postoji i da je pod pravim imenom");
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            }
            
            stmt = con.createStatement();
            try {
                stmt.executeUpdate("DROP TABLE KONTAKTI");
            } 
            catch(SQLException e) { 
            }
            finally {
                stmt.close();
            }
            
            stmt = con.createStatement();
            String query = "CREATE TABLE KONTAKTI("
                    + "ID INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
                    + "IME VARCHAR(30) NOT NULL,"
                    + "PREZIME VARCHAR(30) NOT NULL,"
                    + "EMAIL VARCHAR(30) NOT NULL,"
                    + "TELEFON VARCHAR(30) NOT NULL)";
            
            stmt.executeUpdate(query);
            /*
            Using PreparedStatement to avoid potential SQL attacks.
            */
            PreparedStatement ps = con.prepareStatement("INSERT INTO kontakti (ime, prezime, email, telefon) VALUES (?,?,?,?)");
            ps.setString(1, "Marko");
            ps.setString(2, "Markovic");
            ps.setString(3, "marko@markovic.com");
            ps.setString(4, "0631567892");
            ps.executeUpdate();
            
            ps.setString(1, "Petar");
            ps.setString(2, "Petrovic");
            ps.setString(3, "pera@peric.com");
            ps.setString(4, "0631567892");                    
            ps.executeUpdate();
            
            ps.setString(1, "Ivana");
            ps.setString(2, "Ivanovic");
            ps.setString(3, "iva@ivanovic.com");
            ps.setString(4, "0631567892");                     
            ps.executeUpdate();
            
            ps.setString(1, "Jelena");
            ps.setString(2, "Jelic");
            ps.setString(3, "jelena@jelic.com");
            ps.setString(4, "0631567892");                    
            ps.executeUpdate(); 
            
            ps.setString(1, "Ana");
            ps.setString(2, "Nikolic");
            ps.setString(3, "ana@nikolic.com");
            ps.setString(4, "0631567892");                     
            ps.executeUpdate();
            
            ps.setString(1, "Ivan");
            ps.setString(2, "Pavlovic");
            ps.setString(3, "ivan@pavlovic.com");
            ps.setString(4, "0631567892");                     
            ps.executeUpdate(); 
            
            ps.setString(1, "Miodrag");
            ps.setString(2, "Ivic");
            ps.setString(3, "miodrag@ivic.com");
            ps.setString(4, "0631567892");                     
            ps.executeUpdate(); 
            
            ps.close();
            request.getRequestDispatcher("pocetna.jsp").forward(request, response);  
            
        } catch(SQLException e) {
            poruka = e.getLocalizedMessage();
            request.setAttribute("errormsg", poruka);
            request.getRequestDispatcher("error.jsp").forward(request, response);
            e.printStackTrace();
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
