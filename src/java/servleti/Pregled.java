package servleti;

import beans.Kontakt;
import data.DB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.TreeSet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Stefan
 */
public class Pregled extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      /*
            Making a connection on DB.java
        */
        try {
            Connection con = DB.getInstance().getConnection();
            Statement stmt = con.createStatement();
            
            ResultSet rs = null;
            
            String query = "SELECT * FROM KONTAKTI ORDER BY ime DESC";
            rs = stmt.executeQuery(query);
            
            TreeSet<Kontakt> ts = new TreeSet<>();
            
            while(rs.next()) {
                ts.add(new Kontakt(rs.getString("ime"), rs.getString("prezime"), rs.getString("email"), rs.getString("telefon")));
            }
            
            request.setAttribute("kontakti", ts);
            request.getRequestDispatcher("pregled.jsp").forward(request, response);

        } catch(Exception e) {
            request.setAttribute("errormsg", e.toString());
            request.getRequestDispatcher("error.jsp").forward(request, response);
            
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
