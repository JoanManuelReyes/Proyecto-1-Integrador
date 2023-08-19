/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Daos.UsuarioDAO;
import Daos.VehiculoDAO;
import Modelo.Usuario;
import Modelo.Vehiculo;
import java.util.List;

/**
 *
 * @author Arice
 */
public class Controlador_Usuario {
    VehiculoDAO vehiculoDAO=new VehiculoDAO();
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    
    public Double ahorrohoy(String id_usuario){
        return vehiculoDAO.ahorroHoy(id_usuario);
    }
    
    public Double ahorrosemanapasada(String id_usuario){
        return vehiculoDAO.ahorroSemanaPasada(id_usuario);
    }
    
    public Double gastohoy(String id_usuario){
        return vehiculoDAO.gastoHoy(id_usuario);
    }
    
    public Double gastoayer(String id_usuario){
        return vehiculoDAO.gastoAyer(id_usuario);
    }
    
    public Double gastosemanapasada(String id_usuario){
        return vehiculoDAO.gastoSemanaPasada(id_usuario);
    }
    
    public List<Vehiculo> datos_generales_vehiculo(String correo){
        return vehiculoDAO.selectByCorreoUser(correo);
    }
    
    public String idvehiculo(String nombre){
        return vehiculoDAO.id_vehiculoByNombreVehiculo(nombre);
    }
    
    public List<Vehiculo> datos_viajes_mayor_veces(String id_usuario, String id_vehiculo){
        return vehiculoDAO.datosViajeMayorveces(id_usuario,id_vehiculo);
    }
    
    public List<Vehiculo> datos_viajes_menor_gasto(String id_usuario, String id_vehiculo){
        return vehiculoDAO.datosViajeMenorGasto(id_usuario,id_vehiculo);
    }
    
    public List<Vehiculo> datos_viajes_mayor_gasto(String id_usuario, String id_vehiculo){
        return vehiculoDAO.datosViajeMayorGasto(id_usuario,id_vehiculo);
    }
    
    public Vehiculo datos_generales_cambio(String id_vehiculo){
        return vehiculoDAO.vehiculoByIdVehiculo(id_vehiculo);
    }
    
    public List<Vehiculo> datos_viajes(String id_usuario, String id_vehiculo){
        return vehiculoDAO.datosViaje(id_usuario,id_vehiculo);
    }
    public boolean insert(Usuario usuario){
        return usuarioDAO.insert(this);
    }
}
