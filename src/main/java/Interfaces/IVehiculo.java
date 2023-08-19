package Interfaces;
import Modelo.Vehiculo;
import java.util.List;
public interface IVehiculo extends ICrud<Vehiculo>{
    List<Vehiculo> selectByCorreoUser(String correo);
    String id_vehiculoByNombreVehiculo(String nombre);
    Vehiculo vehiculoByIdVehiculo(String idvehiculo);
    Double ahorroHoy(String id_usuario);
    Double ahorroSemanaPasada(String id_usuario);
    Double ahorroAño(String id_usuario);
    Double gastoHoy(String id_usuario);
    Double gastoAyer(String id_usuario);
    Double gastoSemanaPasada(String id_usuario);   
    Double gastoAño(String id_usuario);List<Vehiculo> datosViaje(String id_usuario,String id_vehiculo);
    List<Vehiculo> datosViajeMayorveces(String id_usuario,String id_vehiculo);
    List<Vehiculo> datosViajeMayorGasto(String id_usuario,String id_vehiculo);
    List<Vehiculo> datosViajeMenorGasto(String id_usuario,String id_vehiculo);
    Double cantidadCombustibleRestante(String id_usuario,String vehiculo);
    
    List<Vehiculo> datosViajeInicioPersona(String id_usuario);
    List<Vehiculo> datosViajeInicioPersonaMenorPrecio(String id_usuario);
    List<Vehiculo> datosViajeInicioPersonaMayorPrecio(String id_usuario);
    
    List<Vehiculo> datosViajeInicioEmpresa(String id_empresa);
    List<Vehiculo> datosViajeInicioEmpresaMenorPrecio(String id_empresa);
    List<Vehiculo> datosViajeInicioEmpresaMayorPrecio(String id_empresa);
    
    Double cosumoByIdUsuarioIdVehiculo(String id_usuario,String id_vehiculo);
}
