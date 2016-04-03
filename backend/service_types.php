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
	
	$sql = "SELECT * FROM service ORDER BY name ASC";
				
	$result = mysqli_query($conn, $sql);
	$rows = array();

	while($row = mysqli_fetch_assoc($result)) {
		$rows[] = $row;
	}
	echo  json_encode($rows);
	mysqli_close($conn);
?>