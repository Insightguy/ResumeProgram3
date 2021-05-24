<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="ISO-8859-1">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#datepicker" ).datepicker();
  } );
	
	function setInfo(){
		var Name = document.getElementById('Name').value;
		var Email = document.getElementById('Email').value;
		var PhoneNumber = document.getElementById('PhoneNumber').value;
		var Gender = document.getElementById('Gender').value;
		var datepicker = document.getElementById('datepicker').value;
		
		var postData = 'Name=' + Name;
		postData += '&Email=' + Email;
		postData += '&PhoneNumber=' + PhoneNumber;
		postData += '&datepicker=' + datepicker;
		postData += '&Gender=' + Gender;
		
		var ajaxRequest = new XMLHttpRequest();
		ajaxRequest.open('POST', 'http://localhost:8080/Data/');
		ajaxRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		ajaxRequest.send(postData);
	}
  </script>
  
<title>Customer info</title>
</head>
<body>
	<fieldset>
		<p>Enter Name :<input type = 'text' name = 'Name' id = 'Name' size = '50' required="required"/></p>
		<p>Enter email address :<input type = 'text' name = 'Email' id = 'Email'size = '50' required="required"/></p>
		<p>phone number : <input type="number" name='PhoneNumber' id='PhoneNumber'min="09170000000" max="09189999999" required='required'/></p>
		<p>Select Gender :
			<select name = 'Gender' id = 'Gender'>
				<option value="M">Male</option>
				<option value="F">Female</option>
			</select>
		</p>
		<p>Birthday : <input name = 'Birthday' Name = 'datepicker' id="datepicker"></p>
		<p><button type="button" onclick="setInfo()">Save</button></p>
	</fieldset>
</body>
</html>