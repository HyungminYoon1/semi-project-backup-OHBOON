package com.ohboon.ohboon.controller.user;


import com.ohboon.ohboon.dao.UserDAO;
import com.ohboon.ohboon.dto.UserDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/user/info")
public class UserInfo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String email = (String) session.getAttribute("sessionEmail");

        UserDAO userDao = new UserDAO();
        UserDTO infoUserDTO = userDao.findUserByEmail(email);

        if (infoUserDTO == null) {
            resp.sendRedirect("../index/index");

        } else {
            req.setAttribute("infoUserDTO", infoUserDTO);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/user/info.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
