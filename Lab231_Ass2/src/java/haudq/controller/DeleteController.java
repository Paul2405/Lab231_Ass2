/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haudq.controller;

import haudq.dao.ActivityDao;
import haudq.dao.UserDao;
import haudq.dto.ActivityDto;
import haudq.dto.UserDto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
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
public class DeleteController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "error.jsp";
        try {
            UserDao dao = new UserDao();
            String[] ids = request.getParameterValues("listUserDelete");
            List<String> listId = Arrays.asList(ids);
            List<Integer> idList = new ArrayList<>();
            listId.stream().forEach(id -> {
                idList.add(Integer.parseInt(id));
            });
            String btnAction = "";
            try {
                btnAction = request.getParameter("btnAction").trim();
            } catch (Exception e) {
                btnAction = "";
            }
            if (dao.updateStatusById(idList, "inactive")) {
                ActivityDao aDao = new ActivityDao();
                HttpSession session = request.getSession();
                UserDto byUser = (UserDto) session.getAttribute("INFO");
                for (Integer i : idList) {
                    ActivityDto activityDto = new ActivityDto(i, "Deleted", byUser.getId(), byUser.getName(), byUser.getEmail());
                    aDao.insertActivity(activityDto);
                }
                url = "LoadController";
            }
        } catch (Exception e) {
            log("ERROR at DeleteController: " + e.getMessage());
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
