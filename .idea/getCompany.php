<?php
if($_SERVER["REQUEST_METHOD"]=='POST') {
    require 'dbclass.php';
    getData();
}
function getData() {
    global $connect;

    $userid = $_POST['userid'];
    $email = $_POST['email'];

    $company_sql = "SELECT * FROM company WHERE Com_Id='$userid' AND Com_Email_Address='$email'";
    $company_result = mysqli_query($connect,$company_sql);
    $company_check = mysqli_fetch_array($company_result);
    
    echo json_encode(array('responseid'=>$company_check['Com_Id'],'responsef'=>$company_check['Com_First_Name'],'responsem'=>$company_check['Com_Middle_Name'],'responsel'=>$company_check['Com_Last_Name'],'responsec'=>$company_check['Com_Name'],'responsed'=>$company_check['Com_Designation'],'responsez'=>$company_check['Com_Zip_Code'],'responsep'=>$company_check['Com_Phone'],'responsefax'=>$company_check['Com_Fax'],'responsew'=>$company_check['Com_Website'],'responsey'=>$company_check['Com_Year_Establishment']));
}
?>