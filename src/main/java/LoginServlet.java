import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        request.getRequestDispatcher("main.html").include(request, response);

        String name=request.getParameter("name");
        String password=request.getParameter("password");

        if(password.equals("123")){
            out.print("<header class=\"d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm\">\n" +
                    "    <p class=\"h5 my-0 me-md-auto fw-normal\">Company name</p>\n" +
                    "    <nav class=\"my-2 my-md-0 me-md-3\">\n" +
                    "        <a class=\"p-2 text-dark\" href=\"login.html\">Login</a> |\n" +
                    "        <a class=\"p-2 text-dark\" href=\"LogoutServlet\">Logout</a> |\n" +
                    "    </nav>\n" +
                    "    <a class=\"btn btn-outline-primary\" href=\"register.html\">Sign up</a>\n" +
                    "</header>");
            out.print("Welcome, "+name);
            HttpSession session=request.getSession();
            session.setAttribute("name",name);
        }
        else{
            out.print("Sorry, username or password error!");
            request.getRequestDispatcher("login.html").include(request, response);
        }
        out.close();
    }
}  