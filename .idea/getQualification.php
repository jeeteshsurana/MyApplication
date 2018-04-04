<?php
require 'dbclass.php';
global $connect;

$qualification_sql = "SELECT * FROM qualification WHERE Q_Status='Active' ORDER BY Q_Title ASC";
$res = mysqli_query($connect,$qualification_sql);
$arr = array();
while($row = mysqli_fetch_array($res)) {
	array_push($arr,array("qualificationid"=>$row['Q_Id'],"qualificationname"=>$row['Q_Title']));
}
echo json_encode(array("qualificationresult"=>$arr));

?>