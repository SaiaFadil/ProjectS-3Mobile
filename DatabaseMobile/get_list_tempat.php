<?php
require('Koneksi.php');

header("Content-Type: application/json");

if ($_SERVER['REQUEST_METHOD'] === 'GET') {
    
    $sql = "SELECT * FROM list_tempat";
    $result = $konek->query($sql);
 
    if ($result->num_rows >= 1) {
        $data = array();
        while ($row = $result->fetch_assoc()) {
            $row['foto_tempat'] = 'uploads/tempat/' . $row['foto_tempat'];
            $data[] = $row;
        }
        $response = array("status"=>"success", "message"=>"data berhasil didapatkan", "data"=>$data);
        
    } else {
        $response = array("status"=>"error", "messaeg"=>"error get dat");
    }

}else{
    $response = array("status"=>"error", "messaeg"=>"the method is not get");
}

echo json_encode($response);
mysqli_close($konek);
?>
