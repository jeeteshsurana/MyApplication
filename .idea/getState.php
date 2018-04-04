<?php
if($_SERVER["REQUEST_METHOD"]=='POST') {
    require 'dbclass.php';
    getData();
}
function getData() {
    //require 'dbclass.php';
    global $connect;

    $get_country = $_POST['sel_country'];

    if($get_country!="") {
        $state_sql = "SELECT * FROM state WHERE Sta_Status='Active' AND Sta_Country='$get_country' ORDER BY Sta_Title ASC";
        $res = mysqli_query($connect,$state_sql);
        $arr = array();
        while($row = mysqli_fetch_array($res)) {
            array_push($arr,array("stateid"=>$row['Sta_Id'],"statename"=>$row['Sta_Title']));
        }
        echo json_encode(array("stateresult"=>$arr));
    }
}
?>