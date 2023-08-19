package Interfaces;
import Modelo.Tanqueo;
import java.util.List;
public interface ITanqueo extends ICrud<Tanqueo>{
    List<Tanqueo> selectByVehiculo(String marca,String modelo,String correo);
    List<Tanqueo> listarTanqueos (String id_usuario, String idvehiculo);
    List<Tanqueo> ListGrifo();
}
