package com.example.usingpreferences.DataModel;
import com.example.usingpreferences.DataModel.ModelUsers;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class ModelUsersTest {
    @Test
    public void testGetSetIdUser() {
        ModelUsers user = new ModelUsers();
        user.setId_user(1);
        assertEquals(1, user.getId_user());
    }
    @Test
    public void testGetSetNamaLengkap() {
        ModelUsers user = new ModelUsers();
        user.setNama_lengkap("Kelompok A1");
        assertEquals("Kelompok A1", user.getNama_lengkap());
    }
    @Test
    public void testGetSetNoTelpon() {
        ModelUsers user = new ModelUsers();
        user.setNo_telpon("081514041197");
        assertEquals("081514041197", user.getNo_telpon());
    }
    @Test
    public void testGetSetTanggalLahir() {
        ModelUsers user = new ModelUsers();
        user.setTanggal_lahir("2023-05-05");
        assertEquals("2023-05-05", user.getTanggal_lahir());
    }
    @Test
    public void testGetSetTempatLahir() {
        ModelUsers user = new ModelUsers();
        user.setTempat_lahir("Nganjuk");
        assertEquals("Nganjuk", user.getTempat_lahir());
    }
    @Test
    public void testGetSetRole() {
        ModelUsers user = new ModelUsers();
        user.setRole("masyarakat");
        assertEquals("masyarakat", user.getRole());
    }
    @Test
    public void testGetSetEmail() {
        ModelUsers user = new ModelUsers();
        user.setEmail("fadillahwahyunugraha@gmail.com");
        assertEquals("fadillahwahyunugraha@gmail.com", user.getEmail());
    }
    @Test
    public void testGetSetPassword() {
        ModelUsers user = new ModelUsers();
        user.setPassword("kelompoka1");
        assertEquals("kelompoka1", user.getPassword());
    }

    @Test
    public void testGetSetVerifikasi() {
        ModelUsers user = new ModelUsers();
        user.setVerifikasi("0");
        assertEquals("0", user.getVerifikasi());
    }
}








