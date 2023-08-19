/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Daos.RutaDAO;
import Daos.TanqueoDAO;
import Daos.VehiculoDAO;
import Modelo.Motor;
import Modelo.Ruta;
import Modelo.Tanqueo;
import Modelo.Vehiculo;
import java.util.List;

/**
 *
 * @author Arice
 */
public class Controlador_Ruta {
     VehiculoDAO vehiculoDAO=new VehiculoDAO();
    RutaDAO rutaDAO=new RutaDAO();
    TanqueoDAO tanqueoDAO=new TanqueoDAO();
    
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
        Vehiculo vehiculo=new Vehiculo
                .Builder()
                .id_vehiculo(Integer.parseInt(vehiculoDAO.id_vehiculoByNombreVehiculo(nombrevehiculo)))
                .build();
        Tanqueo tanqueo=new Tanqueo
                .Builder()
                .ruta(ruta)
                .vehiculo(vehiculo)
                .lugar("no")
                .pagoTotal(0.0)
                .fecha(fecha)
                .cantLlenada(0.0)
                .temperatura(24.0)
                .humedad(71)
                .presion(1.01)
                .build();
        tanqueoDAO.insert(tanqueo);
    }
    
    public Double proyeccion(String id_usuario, String vehiculo,String distancia,String trafico){
        Ruta aux=new Ruta();
        Double Combustible_Actual=Math.round(vehiculoDAO.cantidadCombustibleRestante(id_usuario, vehiculo) * 10.0) / 10.0;
        Double consumo=vehiculoDAO.cosumoByIdUsuarioIdVehiculo(id_usuario, vehiculoDAO.id_vehiculoByNombreVehiculo(vehiculo));
        
        Motor motor=new Motor
            .Builder()
            .consumo(consumo)
            .build();
        Ruta ruta=new Ruta
            .Builder()
            .distancia(Float.parseFloat(distancia))
            .tiempoTrafico(Integer.parseInt(trafico))
            .temperatura(24.0)
            .humedad(71)
            .presion(1.01)
            .build();
        Double proyeccion=Math.round(aux.calcularConsumoFinal(ruta,motor) * 10.0) / 10.0;
        return proyeccion;
    }
    
    public void añadirRuta(String id_usuario,String Nombre_Ruta,String origen,String Destino,String distancia,String trafico){
        Ruta ruta=new Ruta
                .Builder()
                .idRuta(Integer.parseInt(id_usuario))
                .nombre(Nombre_Ruta)
                .distancia(Float.parseFloat(distancia))
                .origen(origen)
                .destino(Destino)
                .tiempoTrafico(Integer.parseInt(trafico))
                .temperatura(23.0)
                .humedad(71)
                .presion(1.01)
                .build();
        rutaDAO.insert(ruta);
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
