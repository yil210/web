/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yil210;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
/**
 *
 * @author lyx
 */
public class Servlet1 extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
        HttpSession session=request.getSession(true);
        myBean ub = new myBean();
        session.setAttribute("ub",ub);
        //depending on which form on index.jsp was used we login or register
        //failure returns to index.jsp, success spawns cw
        //the userbean is used to transfer information
//        if(request.getParameter("log")!=null)
        try (PrintWriter out = response.getWriter()) {
            String _username = request.getParameter("name");
            String _password = request.getParameter("password");
            if (_username != null && _password != null) {
                String connectionURL = "jdbc:derby://localhost:1527/yil210";
//                ub.setName(_username);
                try{
                    Connection conn = DriverManager.getConnection(connectionURL, "IS2560", "IS2560");
                    String sql = "SELECT * FROM login";
                    Statement st = conn.createStatement();
                    ResultSet rs = null;
                    rs = st.executeQuery(sql);
                    
                    while (rs.next()) {
                        System.out.println(rs.getString("uid") + "\t" + rs.getString("upwd"));
                        if (rs.getString("uid").equals(_username) && rs.getString("upwd").equals(_password)) {
                            //        response.sendRedirect("welcome.jsp");
                            ub.setStatus("LOGIN SUCCESS");
                            ub.setName(_username);
                            ub.setId(1);
                            RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");
                            rd.forward(request, response);

                        } else {
                            //  out.println("invalid user or password");
                            ub.setStatus("LOGIN FAILURE RETRY");
                            RequestDispatcher rd = request.getRequestDispatcher("newjsp.jsp");
                            rd.forward(request, response);
                        }
                    }
                    rs.close();
                    st.close();
                    conn.close();       
                } catch (SQLException ex) {
                    System.out.println("Connect failed ! ");
                }
            }else {
                out.println("Empty Username or password");
            }
        }

            
//            if(request.getParameter("name").equals("Spring"))
//            {
//            ub.setStatus("LOGIN SUCCESS");
//            ub.setName(request.getParameter("name"));
//            ub.setId(1);
//            RequestDispatcher rd = getServletContext().getRequestDispatcher("/JSP2.jsp");
//            rd.forward(request, response);
//            }
//            else{
//            ub.setStatus("LOGIN FAILURE RETRY");
//            RequestDispatcher rd = getServletContext().getRequestDispatcher("/JSP0.jsp");
//            rd.forward(request, response);
//            }
        /*
        if(request.getParameter("reg")!=null)
            {
            if (!request.getParameter("password2").equals(request.getParameter("password1")))
                {
                ub.setloginStatus("Registration Failed, passwords didn't match");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
                }
            else
                {int status = dbm.registerUser(
                request.getParameter("fname"), request.getParameter("lname"),
                request.getParameter("email"), request.getParameter("uname"),
                request.getParameter("password1"));
                if(status==1)
                    {
                    ub.setloginStatus(dbm.getUserName(request.getParameter("username"))+" is now registered and logged in.");
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/cw.jsp");
                    rd.forward(request, response);
                    }
                else{
                    ub.setloginStatus("Registration Failed, existing username, please try again");
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                    rd.forward(request, response);
                    }
                }
            }
        */
        
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
