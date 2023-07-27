package accessgoogle;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/google-login")
public class LoginGoogleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginGoogleServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");
        if (code == null || code.isEmpty()) {
            RequestDispatcher dis = request.getRequestDispatcher("/views/dangnhap/index.jsp");
            dis.forward(request, response);
        } else {

            String accessToken = GoogleUtils.getToken(code);

            GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);

            request.setAttribute("email", googlePojo.getEmail());

            response
                    .sendRedirect("http://localhost:8080/Java4_Demo_war_exploded/home/index");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
