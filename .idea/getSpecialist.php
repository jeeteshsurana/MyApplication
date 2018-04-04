<?php
require 'dbclass.php';
global $connect;

$specialist_sql = "SELECT * FROM specialist WHERE Sp_Status='Active' ORDER BY Sp_Title ASC";
$res = mysqli_query($connect,$specialist_sql);
$arr = array();
while($row = mysqli_fetch_array($res)) {
	array_push($arr,array("specialistid"=>$row['Sp_Id'],"specialistname"=>$row['Sp_Title']));
}
echo json_encode(array("specialistresult"=>$arr));

?>