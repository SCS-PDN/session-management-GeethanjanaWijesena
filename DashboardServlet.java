import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.html");
            return;
        }

        List<Course> courses = List.of(
            new Course("101", "Math", "Dr. Sumith"),
            new Course("102", "Physics", "Dr. Malsha"),
            new Course("103", "Chemistry", "Dr. Sewmi")
        );

        request.setAttribute("courses", courses);

        List<Course> enrolled = (List<Course>) session.getAttribute("enrolled");
        if (enrolled == null) {
            enrolled = new ArrayList<>();
        }
        request.setAttribute("enrolled", enrolled);

        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }
}
