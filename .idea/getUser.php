<?php
if($_SERVER["REQUEST_METHOD"]=='POST') {
    require 'dbclass.php';
    getData();
}
function getData() {
    global $connect;
    
    $userid = $_POST['userid'];
    $email = $_POST['email'];
    $pwd = $_POST['old_pwd'];
    $from_req = $_POST['change_req'];
    
    //Getting Old Password for Changing Password for Patient and Doctor
    if($from_req=='change'){
        $patient_sql = "SELECT * FROM patient WHERE Patient_Id='$userid' AND Patient_Email='$email' AND Patient_Password='$pwd'";
        $patient_result = mysqli_query($connect,$patient_sql);
        $patient_check = mysqli_fetch_array($patient_result);
        if(isset($patient_check)){ echo "success"; }
        
        $doctor_sql = "SELECT * FROM doctors WHERE Doc_Id='$userid' AND Doc_Email_Address='$email' AND Doc_Password='$pwd'";
        $doctor_result = mysqli_query($connect,$doctor_sql);
        $doctor_check = mysqli_fetch_array($doctor_result);
        if(isset($doctor_check)){ echo "success"; }
        
        $company_sql = "SELECT * FROM company WHERE Com_Id='$userid' AND Com_Email_Address='$email' AND Com_Password='$pwd'";
        $company_result = mysqli_query($connect,$company_sql);
        $company_check = mysqli_fetch_array($company_result);
        if(isset($company_check)){ echo "success"; }
    }
    //Getting User for Forgot Password and Getting User Name for My Profile
    else {
        $patient_sql = "SELECT * FROM patient WHERE Patient_Email='$email'";
        $patient_result = mysqli_query($connect,$patient_sql);
        $patient_check = mysqli_fetch_array($patient_result);

        $doctor_sql = "SELECT * FROM doctors WHERE Doc_Email_Address='$email'";
        $doctor_result = mysqli_query($connect,$doctor_sql);
        $doctor_check = mysqli_fetch_array($doctor_result);
        
        $company_sql = "SELECT * FROM company WHERE Com_Email_Address='$email'";
        $company_result = mysqli_query($connect,$company_sql);
        $company_check = mysqli_fetch_array($company_result);

        if(isset($patient_check)){
            $upload_path = "../admintool/upload/patient_image/".$patient_check['Patient_Id']."_".$patient_check['Patient_Image'];
            $get_image = base64_encode(file_get_contents($upload_path));
            echo json_encode(array('responseid'=>$patient_check['Patient_Id'],'responsef'=>$patient_check['Patient_FName'],'responsel'=>$patient_check['Patient_LName'],'responseimage'=>$get_image));
        }
        else if(isset($doctor_check)){
            $upload_path = "../admintool/upload/doctor_image/".$doctor_check['Doc_Id']."_".$doctor_check['Doc_Image'];
            $get_image = base64_encode(file_get_contents($upload_path));
            echo json_encode(array('responseid'=>$doctor_check['Doc_Id'],'responsef'=>$doctor_check['Doc_FName'],'responsel'=>$doctor_check['Doc_LName'],'responseimage'=>$get_image));
        }
        else if(isset($company_check)){
            $upload_path = "../admintool/upload/company_image/".$company_check['Com_Id']."_".$company_check['Com_Image'];
            $get_image = base64_encode(file_get_contents($upload_path));
            echo json_encode(array('responseid'=>$company_check['Com_Id'],'responsef'=>$company_check['Com_First_Name'],'responsel'=>$company_check['Com_Last_Name'],'responseimage'=>$get_image));
        }
        else { 
            echo 'Null';
        }
    }
}
?>