<%@ page import="java.util.*, your.package.name.Course" %>
<%
    String username = (String) session.getAttribute("username");
    if (username == null) {
        response.sendRedirect("login.html");
        return;
    }
%>
<h2>Welcome, <%= username %>!</h2>
<a href="LogoutServlet">Logout</a>

<h3>Available Courses</h3>
<table border="1">
  <tr><th>ID</th><th>Name</th><th>Instructor</th><th>Action</th></tr>
  <%
    List<Course> courses = (List<Course>) request.getAttribute("courses");
    for (Course c : courses) {
  %>
    <tr>
      <td><%= c.getCourseId() %></td>
      <td><%= c.getCourseName() %></td>
      <td><%= c.getInstructor() %></td>
      <td><a href="EnrollServlet?courseId=<%= c.getCourseId() %>">Enroll</a></td>
    </tr>
  <% } %>
</table>

<h3>Enrolled Courses</h3>
<ul>
  <%
    List<Course> enrolled = (List<Course>) request.getAttribute("enrolled");
    for (Course ec : enrolled) {
  %>
    <li><%= ec.getCourseName() %> - <%= ec.getInstructor() %></li>
  <% } %>
</ul>
