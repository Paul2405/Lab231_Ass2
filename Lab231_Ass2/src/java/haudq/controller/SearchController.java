/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haudq.controller;

import haudq.dao.UserDao;
import haudq.dto.UserDto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author haudq
 */
public class SearchController extends HttpServlet {

   private static final String ALL = "all.jsp";
    private static final String ADMIN = "admin.jsp";
    private static final String USER = "user.jsp";

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
        String url = "error.jsp";
        try {
            String search = request.getParameter("txtSearch");
            HttpSession session = request.getSession();
            UserDto userDto = (UserDto) session.getAttribute("INFO");

            UserDao dao = new UserDao();
            String btnAction = "";
            try {
                btnAction = request.getParameter("btnAction").trim();
            } catch (Exception e) {
                btnAction ="";
            }

            if (userDto.getRole().equals("admin")) {
                List<UserDto> listUser = dao.getListUserByName(search);
                request.setAttribute("USER", listUser);
                switch (btnAction) {
                    case "all":
                        url = ALL;
                        break;
                    case "admin":
                        url = ADMIN;
                        break;
                    case "user":
                        url = USER;
                        break;
                    default:
                        url = ALL;
                        break;
                }
            } else if (userDto.getRole().equals("user")) {
                List<UserDto> listUser = dao.getListUserByName(search);
                request.setAttribute("USER", listUser);
                url = USER;
            }

        } catch (Exception e) {
            log("ERROR AT SearchController :" + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
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
