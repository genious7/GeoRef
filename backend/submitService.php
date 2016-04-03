<?php
	$servername = "localhost";
	$username = "georef";
	$password = "georef";
	$dbname = "georef";

	// Create connection
	$conn = new mysqli($servername, $username, $password, $dbname);

	// Check connection
	if ($conn->connect_error) {
		die("Connection failed: " . $conn->connect_error);
	} 
	
	$user = mysqli_real_escape_string($conn, (String) $_POST["user"]);
	$name = mysqli_real_escape_string($conn, (String) $_POST["name"]);
	$desc = mysqli_real_escape_string($conn, (String) $_POST["desc"]);
	$fee = mysqli_real_escape_string($conn, (String) $_POST["fee"]);
	$type = mysqli_real_escape_string($conn, (String) $_POST["type"]);

	// First obtain the student's team
	$sql = "INSERT answer (instanceId, studentId, answer)
			VALUES($questionId, $studentId, $answer)";
	$result = mysqli_query($conn, $sql);
	
	mysqli_close($conn);
?>