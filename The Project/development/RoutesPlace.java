import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class RoutesPlace extends HttpServlet {
    void processRequest(HttpServletRequest request, HttpServletResponse response)
    										throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
        
	  Connection conn = ConnectionManager.getInstance().getConnection();
      
	  String input = request.getParameter("findc");
	  String statementString = ("SELECT rnum, acode " + 
								"FROM OUTGOINGROUTES " + 
								"WHERE Destination = " + "'" + input + "' " + 
								"UNION " + 
								"SELECT rnum, acode " + 
								"FROM INCOMINGROUTES " +
								"WHERE source = " + "'" + input + "'");
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
				"<th>rnum</th> " +
				"<th>acode</th> " +
			"</thread> " +
			"<tbody>"
				);
			int i=0;
          while (rset.next()) {
        	  out.println(
        	  
			  "<th" +
						" scope=" + '"' + "row" + '"' + ">" +i+ "</th>" +
				  "<td>"+rset.getString("rnum")+"</td>"+ 
				  "<td>"+rset.getString("acode")+"</td>" +
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
