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
public class PromotionController extends HttpServlet {

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
            String action = request.getParameter("action");

            UserDao dao = new UserDao();

            if (action.equals("add")) {
                String userId = request.getParameter("id");
                int uId = Integer.parseInt(userId);
                if (!dao.checkUserId(uId)) {
                    ActivityDao aDao = new ActivityDao();
                    HttpSession session = request.getSession();
                    UserDto byUser = (UserDto) session.getAttribute("INFO");
                    ActivityDto activityDto = new ActivityDto(uId, "Add to promotion list", byUser.getId(), byUser.getName(), byUser.getEmail());
                    boolean activity = aDao.insertActivity(activityDto);

                    boolean add = dao.insertUserIntoPromotion(uId, 5);

                    if (add && activity) {

                        request.setAttribute("NOTIFY", "Sucess to add promotion list");
                    } else {
                        request.setAttribute("NOTIFY", "Fail to add promotion list");
                    }
                } else {
                    request.setAttribute("NOTIFY", "User is already in promotion list");
                }
                url = "LoadController";
            }
            if (action.equals("change")) {
                String userId = request.getParameter("id");
                int uId = Integer.parseInt(userId);
                int rank = Integer.parseInt(request.getParameter("rank"));
                if (dao.updateRankByUserId(uId, rank)) {
                    request.setAttribute("NOTIFY", "Update success");
                } else {
                    request.setAttribute("NOTIFY", "Update Fail");
                }
                url = "LoadPromotionController";
            }
            if (action.equals("delete")) {
                String[] listUserId = request.getParameterValues("listUserDelete");
                List<String> listId = Arrays.asList(listUserId);
                List<Integer> idList = new ArrayList<>();
                listId.stream().forEach(id -> {
                    idList.add(Integer.parseInt(id));
                });
                if (dao.deletePromotionByUserId(idList)) {
                    request.setAttribute("NOTIFY", "Delete success");
                } else {
                    request.setAttribute("NOTIFY", "Delete fail");
                }
                url = "LoadPromotionController";
            }
        } catch (Exception e) {
            log("ERROR at PromotionController: " + e.getMessage());
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
