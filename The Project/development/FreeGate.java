import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class FreeGate extends HttpServlet {
    void processRequest(HttpServletRequest request, HttpServletResponse response)
    										throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
        
	  Connection conn = ConnectionManager.getInstance().getConnection();
      
	  String IDinput = request.getParameter("arrID/depID");
	  String statementString = ("SELECT GATES.gate " +
								"FROM GATES, ARRIVALS " +
								"WHERE arrID = " + "'" + IDinput + "' " + "AND ISFREE = 'Y' " +
								
								"UNION " +

								"SELECT GATES.gate " +
								"FROM GATES, DEPARTURES " +
								"WHERE depID = " + "'" + IDinput + "'" + " AND ISFREE = 'Y'"
								);
				
	out.println(statementString);						
								
	  try { 
    	  Statement stmt = conn.createStatement();
    	  ResultSet rset = stmt.executeQuery(statementString);
      
    	out.println("<table>");
		out.println(
		  "<table class=" + '"' + "table" +'"' + ">" +
			"<thead class=" + '"' + "thead-default" + '"' + ">" +
				"<tr> " +
				"<th>#</th> " +
				"<th>Gate</th> " +
				"<th>Status</th> " +
			"</thread> " +
			"<tbody>"
				);
		  
		  int i=0;
          while (rset.next()) {
        	  out.println(
			  "<th" + " scope=" + '"' + "row" + '"' + ">" +i+ "</th>" +
				  "<td>"+rset.getString("gate")+"</td>" +
				  "<td>Free</td>" +
              "</tr>");
			i++;
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
