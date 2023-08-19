/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Daos.TanqueoDAO;
import Modelo.Ruta;
import Modelo.Tanqueo;
import Modelo.Vehiculo;
import java.util.List;

/**
 *
 * @author Arice
 */
public class Controlador_Tanqueo {
    TanqueoDAO tanqueoDAO = new TanqueoDAO();
    
    public List<Tanqueo> listarTanqueos(String idUsuario, String id_vehiculo){
        return tanqueoDAO.listarTanqueos(idUsuario, id_vehiculo);
    }
    
    public List<Tanqueo> ListGrifo(){
        return tanqueoDAO.ListGrifo();
    }
    
    public void añadirTanqueo(String id_vehiculo,String id_ruta,String lugar,String pago,String fecha, String cantidad){
        Vehiculo vehiculo = new Vehiculo.Builder().id_vehiculo(Integer.parseInt(id_vehiculo)).build();
        Ruta ruta = new Ruta.Builder().idRuta(Integer.parseInt(id_ruta)).build();
        Tanqueo tanqueo=new Tanqueo
                .Builder()
                .vehiculo(vehiculo)
                .ruta(ruta)
                .lugar(lugar)
                .pagoTotal(Double.parseDouble(pago))
                .fecha(fecha)
                .cantLlenada(Double.parseDouble(cantidad))
                .temperatura(24.0)
                .humedad(71)
                .presion(1.01)
                .build();
        tanqueoDAO.insert(tanqueo);
    }
    public Double ahorroanio(String id_usuario){
        return tanqueoDAO.mostarAhorro(id_usuario);
    }
    public Double ahorrogasto(String id_usuario){
        return tanqueoDAO.mostarGasto(id_usuario);
    }
    
}
