import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private final Map<String, String> users = Map.of("student1", "pass1", "student2", "pass2");

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("username");
        String pass = request.getParameter("password");

        if (users.containsKey(user) && users.get(user).equals(pass)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", user);

            Cookie cookie = new Cookie("username", user);
            cookie.setMaxAge(60 * 60);
            response.addCookie(cookie);

            response.sendRedirect("DashboardServlet");
        } else {
            response.getWriter().println("Invalid login!");
        }
    }
}
