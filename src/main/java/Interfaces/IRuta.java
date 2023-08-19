package Interfaces;

import Modelo.Ruta;
import java.util.ArrayList;
import java.util.List;

public interface IRuta {
    List<Ruta> selectRutasByIdUsuario(String id_usuario);
    Double proyeccionConsumo(String id_usuario,String nombreVehiculo, String nombreRuta);
    String getIdByNombreRutaYIdUsuario(String Nombre_Ruta,String id_usuario);
}
