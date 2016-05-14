import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class AssignFlight extends HttpServlet {
    void processRequest(HttpServletRequest request, HttpServletResponse response)
    										throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
	  
	  String acode = request.getParameter("assigncode");
	  String free = request.getParameter("isFree");
	  String notFree = request.getParameter("notFree");
	  
	  
	  if (free == null){
		  String statement = ("UPDATE Departures SET ISFREE");
	  }
	  
	  out.println(acode + free + "\n" + notFree);
	  
	  
	  //String statementString = ("UPDATE Departures SET GATE= ''  ");
	  
	  
	  Connection conn = ConnectionManager.getInstance().getConnection();
	  try { 
    	  Statement stmt = conn.createStatement();
    	//  ResultSet rset = stmt.executeQuery(statementString);
     /* 
    	out.println("<table>");
		  out.println(
		 "<table class=" + '"' + "table" +'"' + ">" +
			"<thead class=" + '"' + "thead-default" + '"' + ">" +
			"<tr> " +
			"<th>#</th> " +
				"<th>rnum</th> " +
			"</thread> " +
			"<tbody>"
				);
		  int i =0;
          while (rset.next()) {
			  out.println(
					"<th" +
						" scope=" + '"' + "row" + '"' + ">" +i+ "</th>" +
				  "<td>"+rset.getString("rnum")+"</td>"+ 
              "</tr>");
			i++;
          }
          out.println("</table>");
          stmt.close(); */
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
