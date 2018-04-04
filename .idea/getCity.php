<?php
if($_SERVER["REQUEST_METHOD"]=='POST') {
    require 'dbclass.php';
    getData();
}
function getData() {
    //require 'dbclass.php';
    global $connect;

    $get_state = $_POST['sel_state'];

    if($get_state!="") {
        $city_sql = "SELECT * FROM city WHERE Ct_Status='Active' AND Ct_State='$get_state' ORDER BY Ct_Title ASC";
        $res = mysqli_query($connect,$city_sql);
        $arr = array();
        while($row = mysqli_fetch_array($res)) {
            array_push($arr,array("cityid"=>$row['Ct_Id'],"cityname"=>$row['Ct_Title']));
        }
        echo json_encode(array("cityresult"=>$arr));
    }
}
?>