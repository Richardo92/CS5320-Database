package cs5300proj1b;

import java.io.*;

import javax.servlet.http.*;
import javax.servlet.*;

public class HelloWorld extends HttpServlet {
    public void doGet (HttpServletRequest req,
                       HttpServletResponse res)
        throws ServletException, IOException
    {
        PrintWriter out = res.getWriter();
        Cookie[] CS = req.getCookies();

        for (int i = 0; CS != null && i < CS.length; ++i) {
            if (CS[i].getName().equals("EXAMPLE")) { // cannot do == "EXAMPLE" because Java
                out.println("GOT COOKIE");
                return;
            }
            else
            {
                out.println(CS[i].getName());
            }
        }

        Cookie c = new Cookie("EXAMPLE", "this is the value");
        c.setPath("/hello");
        res.addCookie(c);
        out.println("NO COOKIE FOR ME? :-(");
        out.close();
    }
}
