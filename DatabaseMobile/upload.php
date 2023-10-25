<?php
 $action = htmlspecialchars($_POST['action']);

 $response = array("success" => FALSE);

 if($action == "multipart") {
     if ($_FILES["photo"]["error"] > 0) {
      $response["success"] = FALSE;
   $response["message"] = "Upload Failed";
     } else {
   $name_file=htmlspecialchars($_FILES['photo']['name']);
   
         if (@getimagesize($_FILES["photo"]["tmp_name"]) !== false) {

    move_uploaded_file($_FILES["photo"]["tmp_name"], $name_file);

    $response["success"] = TRUE;
       $response["message"] = "Upload Successfull";
    
   }else{
    $response["success"] = FALSE;
    $response["message"] = "Upload Failed";
   }

   echo json_encode($response);
     }
 }

?>