import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class InsertArrivals extends HttpServlet {
    void processRequest(HttpServletRequest request, HttpServletResponse response) 
    						throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
		
		String code = request.getParameter("code");
		String rnum = request.getParameter("route_number");
		String arrID = request.getParameter("dep_ID");
		String gate = request.getParameter("Gate");
		String arrT = request.getParameter("a_time");
		
        String statementString = 
		"INSERT INTO ARRIVALS(acode, rnum, arrID, gate, arrT) " +
		"VALUES( '" + code + "', "+ rnum +" , '"+ arrID +"' , '"+ gate +"' , TO_DATE('" + arrT + "', 'YYYY-MM-DD\"T\"HH24:mi'))";
		
		out.println(statementString);
		
        Connection conn = ConnectionManager.getInstance().getConnection();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(statementString);
            stmt.close();
            out.println("Insertion Successful!");
        }
        catch(SQLException e) { out.println(e); }
        ConnectionManager.getInstance().returnConnection(conn);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    			throws ServletException, IOException {
        processRequest(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    public String getServletInfo() {  return "Insert"; }
}
