package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DataServelet
 */
@WebServlet("/DataServelet")
public class DataServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataServelet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DataInfo datainfo = new DataInfo();
		String successcheck = "Data was unable to parse properly";
		try {
			//data verification and setting is done through the setter methods
			datainfo.setName(request.getParameter("Name"));
			datainfo.setEmail(request.getParameter("Email"));
			datainfo.setPhoneNumber(Integer.parseInt(request.getParameter("PhoneNumber")));
			String Gender = request.getParameter("Gender");
			Date about = new SimpleDateFormat("MM/dd/yyyy").parse(request.getParameter("datepicker"));
			
			successcheck = datainfo.DataInserter(datainfo);  
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (successcheck.length() == 0) {
			response.sendError(304, successcheck);
        	response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);	
		}
		else
            response.setStatus(HttpServletResponse.SC_OK);
	}

}
