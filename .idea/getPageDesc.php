<?php
if($_SERVER["REQUEST_METHOD"]=='POST') {
    require_once '../system/config/configure.php';
    require_once CLASS_PATH.'admin_page_content.php';
    
    $obj_page = new admin_page_content;
    $rs_page = $obj_page -> get_page_content_single_detail(3);    
    $page_content = $rs_page[0]['PC_Description'];
    $pagename = $_POST['aboutus'];
    
    if($pagename=='About'){
        $page_title = $rs_page[0]['PC_Title'];
        $page_content = $rs_page[0]['PC_Description'];
        echo json_encode(array('page_title'=>$page_title,'page_content'=>$page_content));
    }    

}
?>