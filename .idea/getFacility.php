<?php
require 'dbclass.php';
global $connect;

$facility_sql = "SELECT * FROM facility WHERE Fac_Status='Active' ORDER BY Fac_Title ASC";
$res = mysqli_query($connect,$facility_sql);
$arr = array();
while($row = mysqli_fetch_array($res)) {
	array_push($arr,array("facilityid"=>$row['Fac_Id'],"facilityname"=>$row['Fac_Title']));
}
echo json_encode(array("facilityresult"=>$arr));

?>