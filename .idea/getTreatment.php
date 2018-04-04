<?php
require 'dbclass.php';
global $connect;

$treatment_sql = "SELECT * FROM treatment WHERE Tm_Status='Active' ORDER BY Tm_Title ASC";
$res = mysqli_query($connect,$treatment_sql);
$arr = array();
while($row = mysqli_fetch_array($res)) {
	array_push($arr,array("treatmentid"=>$row['Tm_Id'],"treatmentname"=>$row['Tm_Title']));
}
echo json_encode(array("treatmentresult"=>$arr));

?>