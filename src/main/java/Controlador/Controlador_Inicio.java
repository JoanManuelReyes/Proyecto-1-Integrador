/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Daos.RutaDAO;
import Daos.TanqueoDAO;
import Daos.VehiculoDAO;
import Modelo.Ruta;
import Modelo.Tanqueo;
import Modelo.Vehiculo;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author Arice
 */
public class Controlador_Inicio {
    VehiculoDAO vehiculoDAO=new VehiculoDAO();
    RutaDAO rutaDAO=new RutaDAO();
    TanqueoDAO tanqueoDAO=new TanqueoDAO();
    
    Calendar  fecha_actual=new GregorianCalendar();
    
    public Double ahorrohoy(String id_usuario){
        return vehiculoDAO.ahorroHoy(id_usuario);
    }
    
    public Double ahorrosemanapasada(String id_usuario){
        return vehiculoDAO.ahorroSemanaPasada(id_usuario);
    }
    
    public Double ahorroaño(String id_usuario){
        return vehiculoDAO.ahorroAño(id_usuario);
    }
    
    public Double gastohoy(String id_usuario){
        return vehiculoDAO.gastoHoy(id_usuario);
    }

    public Double gastosemanapasada(String id_usuario){
        return vehiculoDAO.gastoSemanaPasada(id_usuario);
    }
    
    public Double gastoaño(String id_usuario){
        return vehiculoDAO.gastoAño(id_usuario);
    }
    
    public List<Ruta> llenarrutas(String id_usuario){
        return rutaDAO.selectRutasByIdUsuario(id_usuario);
    }
    
    public List<Vehiculo> nombres_vehiculo(String correo){
        return vehiculoDAO.selectByCorreoUser(correo);
    }
    
    public Double cantidadRestante(String id_usuario, String vehiculo){
        return Math.round(vehiculoDAO.cantidadCombustibleRestante(id_usuario, vehiculo) * 10.0) / 10.0;
    }
    
    public Double cantidadHaConsumir(String id_usuario, String nombrevehiculo, String nombreruta){
        return Math.round(rutaDAO.proyeccionConsumo(id_usuario, nombrevehiculo,nombreruta) * 10.0) / 10.0;
    }
    
    public String obteneridruta(String Nombre_Ruta,String id_usuario){
        return rutaDAO.getIdByNombreRutaYIdUsuario(Nombre_Ruta, id_usuario);
    }
    
    public void comenzarviaje(String Nombre_Ruta,String id_usuario,String nombrevehiculo,String fecha){
        Ruta ruta=new Ruta
                .Builder()
                .idRuta(Integer.parseInt(obteneridruta(Nombre_Ruta,id_usuario)))
                .build();
        System.out.println(""+Integer.parseInt(obteneridruta(Nombre_Ruta,id_usuario)));
        Vehiculo vehiculo=new Vehiculo
                .Builder()
                .id_vehiculo(Integer.parseInt(vehiculoDAO.id_vehiculoByNombreVehiculo(nombrevehiculo)))
                .build();
        System.out.println(""+Integer.parseInt(vehiculoDAO.id_vehiculoByNombreVehiculo(nombrevehiculo)));
        Tanqueo tanqueo=new Tanqueo
                .Builder()
                .ruta(ruta)
                .vehiculo(vehiculo)
                .lugar("no")
                .pagoTotal(0.0)
                .fecha(fecha)
                .cantLlenada(0.0)
                .temperatura(23.0)
                .humedad(71)
                .presion(1.01)
                .build();
        tanqueoDAO.insert(tanqueo);
    }
    
    public List<Vehiculo> llenartablaPersona(String id_usuario){
        return vehiculoDAO.datosViajeInicioPersona(id_usuario);
    }
    
    public List<Vehiculo> llenartablaPersonaMenor(String id_usuario){
        return vehiculoDAO.datosViajeInicioPersonaMenorPrecio(id_usuario);
    }
    
    public List<Vehiculo> llenartablaPersonaMayor(String id_usuario){
        return vehiculoDAO.datosViajeInicioPersonaMayorPrecio(id_usuario);
    }
    
    public List<Vehiculo> llenartablaEmpresa(String id_empresa){
        return vehiculoDAO.datosViajeInicioEmpresa(id_empresa);
    }
    
    public List<Vehiculo> llenartablaEmpresaMenor(String id_empresa){
        return vehiculoDAO.datosViajeInicioEmpresaMenorPrecio(id_empresa);
    }
    
    public List<Vehiculo> llenartablaEmpresaMayor(String id_empresa){
        return vehiculoDAO.datosViajeInicioEmpresaMayorPrecio(id_empresa);
    }
}
