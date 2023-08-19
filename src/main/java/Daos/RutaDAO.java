package Daos;

import Coneccion.Coneccion;
import Interfaces.ICrud;
import Interfaces.IRuta;
import Modelo.Motor;
import Modelo.Ruta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class RutaDAO implements IRuta, ICrud {
    private static Coneccion con;
    private static PreparedStatement pstm;
    private static ResultSet res;
    
    public RutaDAO(){
        con=Coneccion.estado();
    }
    
    @Override
    public List<Ruta> selectRutasByIdUsuario(String id_usuario) {
        List<Ruta> rutas=new ArrayList<>();
        final String SQL_SelectAll="select nombre_ruta from Ruta where id_usuario=?";
        try{
            pstm=con.getConectar().prepareStatement(SQL_SelectAll);
            pstm.setString(1, id_usuario);
            res=pstm.executeQuery();
            while(res.next()){
            rutas.add(new Ruta.Builder().nombre(res.getString("nombre_ruta")).build());
            }
        }catch(Exception e){
            System.out.println("Error al obtener datos de las rutas por usuario: "+e.getMessage());
            e.printStackTrace();
        }finally{
            cerrarConexion();
        }
        return rutas;
    }
    
    private void cerrarConexion(){
        try{
            if(res!=null)res.close();
            if(pstm!=null)pstm.close();
            if(con!=null)con.cerrarConexion();
        }catch(Exception e){
            System.out.println("Error al cerrrar"+e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Double proyeccionConsumo(String id_usuario, String nombreVehiculo, String nombreRuta) {
        Double proyeccion = 0.0;
        Ruta aux=new Ruta();
        final String SQL_Query="select m.consumo, r.distancia_km,r.tiempo_trafico_min,r.temperatura_C,r.humedad,r.presion_atm from Ruta R inner Join Usuario u on u.id_usuario=r.id_usuario INNER JOIN Asignacion a on a.id_usuario=u.id_usuario INNER JOIN Vehiculo v on v.id_vehiculo=a.id_vehiculo INNER JOIN Motor m on v.id_motor=m.id_motor  where u.id_usuario=? AND CONCAT(v.marca, ' ', v.modelo) =? AND r.nombre_ruta=?";
        try{
            pstm=con.getConectar().prepareStatement(SQL_Query);
            pstm.setString(1, id_usuario);
            pstm.setString(2, nombreVehiculo);
            pstm.setString(3, nombreRuta);
            res=pstm.executeQuery();
            if(res.next()){
                Motor motor=new Motor
                        .Builder()
                        .consumo(res.getDouble("consumo"))
                        .build();
                Ruta ruta=new Ruta
                        .Builder()
                        .distancia(res.getFloat("distancia_km"))
                        .tiempoTrafico(res.getInt("tiempo_trafico_min"))
                        .temperatura(res.getDouble("temperatura_C"))
                        .humedad(res.getInt("humedad"))
                        .presion(res.getDouble("presion_atm"))
                        .build();
                proyeccion=aux.calcularConsumoFinal(ruta,motor);
            }
        }catch(Exception e){
            System.out.println("Error al obtener el id del vehiculo: "+e.getMessage());
            e.printStackTrace();
        }finally{
            cerrarConexion();
        }
        return proyeccion;
    }

    @Override
    public String getIdByNombreRutaYIdUsuario(String Nombre_Ruta,String id_usuario) {
        String id=null;
        final String SQL_Query="select id_ruta from Ruta where nombre_ruta=? and id_usuario=?";
        try{
            pstm=con.getConectar().prepareStatement(SQL_Query);
            pstm.setString(1, Nombre_Ruta);
            pstm.setString(2, id_usuario);
            res=pstm.executeQuery();
            if(res.next()){
                id=""+res.getInt("id_ruta");
            }
        }catch(Exception e){
            System.out.println("Error al obtener el id de la ruta: "+e.getMessage());
            e.printStackTrace();
        }finally{
            cerrarConexion();
        }
        return id;
    }

   
    public boolean insert(Ruta t) {
        boolean resultFlag=false;
        final String SQL_INSERT="INSERT INTO Ruta (id_usuario,nombre_ruta,distancia_km,estado,origen,destino,tiempo_trafico_min,temperatura_C,humedad,presion_atm) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try{
            pstm=con.getConectar().prepareStatement(SQL_INSERT);
            pstm.setInt(1, t.getIdRuta());
            pstm.setString(2, t.getNombre());
            pstm.setDouble(3, (Math.round(t.getDistancia()) * 10.0) / 10.0);
            pstm.setString(4, "Hecho");
            pstm.setString(5, t.getOrigen());
            pstm.setString(6, t.getDestino());
            pstm.setInt(7, t.getTiempoTrafico());
            pstm.setDouble(8, t.getTemperatura());
            pstm.setInt(9, t.getHumedad());
            pstm.setDouble(10, t.getPresion());
            if(pstm.executeUpdate()>0){
                resultFlag=true;
            }
        }catch(Exception e){
            System.out.println("Error al insertar ruta: "+e.getMessage());
            e.printStackTrace();
        }finally{
            cerrarConexion();
        }
        return resultFlag;
    }

    public boolean update(Ruta t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Ruta selectById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Ruta> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean insert(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
