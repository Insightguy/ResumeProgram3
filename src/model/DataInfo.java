package model;

import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Pattern;

import static java.util.Calendar.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class DataInfo {
	private String Name;
	private String Email;
	private Integer PhoneNumber;
	private Date BDay;
	private int Age;
	private String Gender;
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		boolean hasNonAlpha = name.matches("^.*[^a-zA-Z ].*$");

		if(hasNonAlpha){
			Name = "VOID NAME";
		} else{
			Name = name;
		}
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
                  
		Pattern pat = Pattern.compile(emailRegex);
		if (email == null)
			Email = "VOID EMAIL";
		else
			Email = email;
	}
	public Integer getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(Integer phoneNumber) {
		//checks if the last couple of digits are either 0918 or 0917
        if(phoneNumber % 1000000000 == 9 && 
        	phoneNumber % 100000000 == 1 && 
         	(phoneNumber % 10000000 == 7 || phoneNumber % 10000000 == 8) )
        	PhoneNumber = phoneNumber;
        else
        	PhoneNumber = -1;
	}
	public Date getBDay() {
		return BDay;
	}
	public void setBDay(Date bDay) {
		Calendar a = getCalendar(bDay);
	    Calendar b = getCalendar(new Date());
	    int diff = b.get(YEAR) - a.get(YEAR);
	    if (a.get(MONTH) > b.get(MONTH) || 
	        (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
	        diff--;
	    }
	    Age = diff;
		BDay = bDay;
	}
	public static Calendar getCalendar(Date date) {
	    Calendar cal = Calendar.getInstance(Locale.US);
	    cal.setTime(date);
	    return cal;
	}
	
	public int getAge() {
		return Age;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public String DataInserter(DataInfo datainfoinsert) {
		DataInfo datainfo = datainfoinsert;
		//insert all the items from the API
		
		boolean errorcheck = false;
		String errorReasons = "Data invalid for the following reasons:";
		if (datainfo.getName() == "VOID NAME") {
			errorcheck = true;
			errorReasons = errorReasons + "\n-Invalid name";
		} if (datainfo.getEmail() == "VOID EMAIL") {
			errorcheck = true;
			errorReasons = errorReasons + "\n-Invalid email";
		} if (datainfo.getPhoneNumber() == -1) {
			errorcheck = true;
			errorReasons = errorReasons + "\n-Invalid phone number";
		}
		
		
		if (!errorcheck){
			//insert into database and return OK message
			Connection conn= ws.DBUtility.getConnection();
			try {
				String query = "INSERT INTO `resumedata`(`Name`, `Email`, `Mobile_Number`, `Birth_Date`, `Age`, `Gender`) VALUES (?,?,?,?,?,?)";
				PreparedStatement preparedStatement = conn.prepareStatement( query );
				preparedStatement.setString( 1, datainfo.getName() );
				preparedStatement.setString( 2, datainfo.getEmail() );
				preparedStatement.setInt( 2, datainfo.getPhoneNumber() );
				preparedStatement.executeUpdate();
				preparedStatement.close();
				return "";
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}

		return errorReasons;
	}
}
