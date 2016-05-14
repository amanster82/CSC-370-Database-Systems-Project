import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class FindPassengers extends HttpServlet {
    void processRequest(HttpServletRequest request, HttpServletResponse response)
    										throws ServletException, IOException {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
        
	  Connection conn = ConnectionManager.getInstance().getConnection();
      
	  String input = request.getParameter("arr/depid");
	  String statementString = ("Select * " +
								"From Passengers " +
								"Where depID = " + "'" + input + "'"+ " OR arrID = " + "'"+ input + "'"
								);
								
	out.println(statementString);						
								
	  try { 
    	  Statement stmt = conn.createStatement();
    	  ResultSet rset = stmt.executeQuery(statementString);
		int i = 0;
    	  out.println("<table>");
		  out.println("<table class=" + '"' + "table" +'"' + ">" +
				"<thead class=" + '"' + "thead-default" + '"' + ">" +
			  "<tr> " +
					"<th>#</th> " +
					"<th>PID</th> " +
					"<th>name</th> " +
					"<th>Gov_Issued_ID</th> " +
					"<th>DOB</th> " +
					"<th>POB</th> " +
					"<th>DEPID</th> " +
					"<th>ARRID</th> " +
				"</thread> " +
				"<tbody>");
          while (rset.next()) {
        	  out.println(
			  "<th"+" scope=" + '"' + "row" + '"' + ">"+ i +"</th>" +
               	  "<td>"+rset.getString("PID")+"</td>" + 
               	  "<td>"+rset.getString("name")+"</td>" + 
				  "<td>"+rset.getString("Gov_Issued_ID")+"</td>" + 
				  "<td>"+rset.getString("DOB")+"</td>" + 
				  "<td>"+rset.getString("POB")+"</td>" +
				  "<td>"+rset.getString("DEPID")+"</td>" +
				  "<td>"+rset.getString("ARRID")+"</td>" +
              "</tr>");
			i++;
		  }
		  
          out.println("</tbody> </table>");
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
