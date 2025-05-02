import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/EnrollServlet")
public class EnrollServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String courseId = request.getParameter("courseId");

        List<Course> allCourses = List.of(
            new Course("101", "Math", "Dr. Smith"),
            new Course("102", "Physics", "Dr. Einstein"),
            new Course("103", "Chemistry", "Dr. Curie")
        );

        HttpSession session = request.getSession();
        List<Course> enrolled = (List<Course>) session.getAttribute("enrolled");
        if (enrolled == null) enrolled = new ArrayList<>();

        for (Course c : allCourses) {
            if (c.getCourseId().equals(courseId)) {
                if (!enrolled.contains(c)) enrolled.add(c);
                break;
            }
        }

        session.setAttribute("enrolled", enrolled);
        response.sendRedirect("DashboardServlet");
    }
}
