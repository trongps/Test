package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import registration.registrationDAO;
import Controller.ERROR;
import javax.servlet.http.HttpSession;
import org.eclipse.jdt.internal.compiler.parser.Parser;

/**
 *
 * @author TrongPS
 */
public class CreateAccountServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "";
        try {
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            String confirmPassword = request.getParameter("txtConfirmPassword");
            String fullname = request.getParameter("txtFullname");

            registrationDAO dao = new registrationDAO();

            ERROR error = new ERROR();

            if (username.isEmpty() || username.length() < 5) {
                error.setAccountError("Username must be not empty and greater than 5!");
                url = "createAccount.jsp";
            }
            if (password.isEmpty() || password.length() < 6) {
                error.setPasswordError("Password must be not empty and greater than 6!");
                url = "createAccount.jsp";
            }
            if (!confirmPassword.endsWith(password)) {
                error.setConfirmPasswordError("Password must be the same!");
                url = "createAccount.jsp";
            }
            if (fullname.isEmpty() || fullname.length() < 6 || fullname.length() > 30) {
                error.setFullnameError("Full name must be not empty and in range [6;30]");
                url = "createAccount.jsp";
            }
            System.out.println(dao.checkAccount(username));
            if (dao.checkAccount(username)) {
                error.setAccountError("Your account alrealdy exists!");
                url = "createAccount.jsp";
            } else {
                if (!error.getAccountError().isEmpty() || !error.getPasswordError().isEmpty()
                        || !error.getConfirmPasswordError().isEmpty() || !error.getFullnameError().isEmpty()) {
                    //do nothing
                } else {
                    boolean result = dao.createAccount(username, password, fullname);
                    if (result) {
                        dao.createAccount(username, password, fullname);
                        url = "login.html";
                    } else {
                        url = "createFail.html";
                    }
                }
            }
            request.setAttribute("Error", error);
        } catch (Exception e) {
        }
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
