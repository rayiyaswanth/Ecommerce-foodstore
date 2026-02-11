package db.servlet;

import db.DBConnection;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class ProductServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        String name = req.getParameter("name");
        String price = req.getParameter("price");
        String category = req.getParameter("category");

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO products(name,price,category) VALUES(?,?,?)"
            );
            ps.setString(1, name);
            ps.setDouble(2, Double.parseDouble(price));
            ps.setString(3, category);

            ps.executeUpdate();
            res.sendRedirect("admin.html");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
