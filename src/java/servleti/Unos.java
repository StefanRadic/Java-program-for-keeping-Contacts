/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servleti;

import data.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Stefan
 */
public class Unos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     
        try {
            String ime = request.getParameter("ime");
            String prezime = request.getParameter("prezime");
            String email = request.getParameter("email");
            String telefon = request.getParameter("telefon");
            
            Connection con = DB.getInstance().getConnection();
            Statement stmt = con.createStatement();
            
            if(telefon.matches("[a-zA-Z]+[0-9]*") || telefon.matches("[0-9]*[a-zA-Z]+")){
                
                request.setAttribute("msg", "Telefon moze sadrzati samo brojeve!");
                request.getRequestDispatcher("pocetna.jsp").forward(request, response);
            }

            else if(ime.isEmpty() || prezime.isEmpty() || email.isEmpty() || telefon.isEmpty()){
                
                request.setAttribute("msg", "Morate popuniti sva polja!");
                request.getRequestDispatcher("pocetna.jsp").forward(request, response);
            }
            
            else if(ime.length() < 3){
                
                request.setAttribute("msg", "Ime mora sadrzati minimum 3 karaktera!");
                request.getRequestDispatcher("pocetna.jsp").forward(request, response);
            }
            
            else if(email.contains("@")) {
            /*
            Using PreparedStatement to avoid potential SQL attacks.
            */
                PreparedStatement ps = con.prepareStatement("INSERT INTO kontakti (ime, prezime, email, telefon) VALUES (?,?,?,?)");
                ps.setString(1, ime);
                ps.setString(2, prezime);
                ps.setString(3, email);
                ps.setString(4, telefon);
                ps.executeUpdate();
                ps.close();
                
                request.setAttribute("msg", "Uspesno ste dodali novi kontakt!");
                request.getRequestDispatcher("pocetna.jsp").forward(request, response);
                
            }
            else {
                request.setAttribute("msg", "Email mora sadrzati karakter '@' !");
                request.getRequestDispatcher("pocetna.jsp").forward(request, response);
            }
            
        } catch(StringIndexOutOfBoundsException ex) {
            request.setAttribute("errormsg", ex.toString());
            request.getRequestDispatcher("error.jsp").forward(request, response);
            
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
