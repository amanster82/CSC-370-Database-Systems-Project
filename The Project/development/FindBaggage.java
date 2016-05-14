import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class FindBaggage extends HttpServlet {
    void processRequest(HttpServletRequest request, HttpServletResponse response)
    										throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
        
	  Connection conn = ConnectionManager.getInstance().getConnection();
      
	  String passInput = request.getParameter("passenger");
	  String statementString = ("SELECT bid " + 
								"FROM BAGGAGE " + 
								"WHERE pid = " + "'" + passInput + "'"
								);
				
	out.println(statementString);						
								
	  try { 
    	  Statement stmt = conn.createStatement();
    	  ResultSet rset = stmt.executeQuery(statementString);
      
    	  out.println("<table>");
          while (rset.next()) {
        	  out.println(
        	  "<tr>" +
               	  "<td>"+rset.getString("bID")+"</td>" +
              "</tr>");
          }
          out.println("</table>");
          stmt.close();
      }
      catch(SQLException e) { out.println(e); }
      ConnectionManager.getInstance().returnConnection(conn);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
                   						throws ServletException, IOException { 
    	processRequest(request, response);     }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
                   						throws ServletException, IOException {
    	processRequest(request, response);     }
    
    public String getServletInfo() {  return "Movie Servlet 1"; }
}
