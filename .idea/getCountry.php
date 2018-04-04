<?php
require 'dbclass.php';
global $connect;

$country_sql = "SELECT * FROM country WHERE Cou_Status='Active' ORDER BY Cou_Title ASC";
$res = mysqli_query($connect,$country_sql);
$arr = array();
while($row = mysqli_fetch_array($res)) {
	array_push($arr,array("countryid"=>$row['Cou_Id'],"countryname"=>$row['Cou_Title']));
}
echo json_encode(array("countryresult"=>$arr));

?>