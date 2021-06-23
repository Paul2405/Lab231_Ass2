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
import haudq.util.MyConnection;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

/**
 *
 * @author haudq
 */
public class ConfirmController extends HttpServlet {

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
            int id = -1;
            String userId = null;
            String oldPass = null;
            String pass = null;
            String confirmPass = null;
            String email = null;
            String phone = null;
            String name = null;
            String role = null;
            String avatar = null;
            boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
            if (isMultiPart) {
                List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(new ServletRequestContext(request));
                for (FileItem item : items) {
                    String contentType = item.getContentType();
                    if (contentType != null) {
                        String fileName = item.getName().substring(item.getName().lastIndexOf("\\") + 1);
                        if (fileName.isEmpty()) {
                            avatar = fileName;
                        } else {
                            String realPath = getServletContext().getRealPath("/") + "asset\\" + fileName;
                            File saveFile = new File(realPath);
                            item.write(saveFile);

                            avatar = "asset/" + fileName;
                        }
                    }
                    if (item.getFieldName().equals("id")) {
                        id = Integer.parseInt(item.getString());
                    }
                    if (item.getFieldName().equals("userId")) {
                        userId = item.getString();
                    }
                    if (item.getFieldName().equals("oldPass")) {
                        oldPass = item.getString();
                    }
                    if (item.getFieldName().equals("pass")) {
                        pass = item.getString();
                    }
                    if (item.getFieldName().equals("confirmPass")) {
                        confirmPass = item.getString();
                    }
                    if (item.getFieldName().equals("email")) {
                        email = item.getString();
                    }
                    if (item.getFieldName().equals("phone")) {
                        phone = item.getString();
                    }
                    if (item.getFieldName().equals("role")) {
                        role = item.getString();
                    }
                    if (item.getFieldName().equals("name")) {
                        name = item.getString();
                    }
                }

                UserDao dao = new UserDao();

                UserDto backup = dao.getUserById(id);

                if (!oldPass.equals(backup.getPassword())) {
                    if (pass.equals(confirmPass)) {
                        MessageDigest digest = MessageDigest.getInstance("SHA-256");
                        byte[] encodedhash = digest.digest(pass.getBytes(StandardCharsets.UTF_8));
                        pass = MyConnection.bytesToHex(encodedhash);
                        UserDto dto = new UserDto(id, userId, pass, name, role, email, phone, avatar);

                        ActivityDao aDao = new ActivityDao();
                        HttpSession session = request.getSession();
                        UserDto byUser = (UserDto) session.getAttribute("INFO");
                        ActivityDto activityDto = new ActivityDto(id, "Update", byUser.getId(), byUser.getName(), byUser.getEmail());
                        boolean activity = aDao.insertActivity(activityDto);
                        
                        boolean update = dao.updateUserById(dto);
                        if (update && activity) {
                            request.setAttribute("NOTIFY", "Success");
                            url = "LoadController";
                        }
                    } else {
                        request.setAttribute("ERROR", "Password and confirm password not match!");
                        request.setAttribute("USER", backup);
                        url = "update.jsp";
                    }
                } else {
                    request.setAttribute("ERROR", "Old password not correct!");
                    request.setAttribute("USER", backup);
                    url = "update.jsp";
                }
            }

        } catch (Exception e) {
            log("ERROR at ConfirmController: " + e.getMessage());
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
