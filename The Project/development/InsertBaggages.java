import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class InsertBaggages extends HttpServlet {
    void processRequest(HttpServletRequest request, HttpServletResponse response) 
    						throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
		
		String pfile = request.getParameter("bfile");
		
		BufferedReader br = null;
		String sCurrentLine;
		String array[] = new String [3];
		String statementString = null;
		
		br = new BufferedReader(new FileReader(pfile));
		while ((sCurrentLine = br.readLine()) != null) {
				//System.out.println(sCurrentLine);
				array = sCurrentLine.split(",");
				for(int i=0; i<array.length; i++){
				array[i] = array[i].trim();
				
				out.println(array[i] + '\n');
				
				}
				
		statementString = 
		"INSERT INTO BAGGAGE(bid, weight, pid) " +
		"VALUES( '" + array[0] + "', '"+ array[1] +"', '"+ array[2] + "')";
				
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
