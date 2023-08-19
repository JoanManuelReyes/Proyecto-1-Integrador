package Daos;
import Coneccion.Coneccion;
import Modelo.Tanqueo;
import Interfaces.ITanqueo;
import Modelo.Ruta;
import Modelo.Usuario;
import Modelo.Vehiculo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class TanqueoDAO implements ITanqueo{
    private static Coneccion con;
    private static PreparedStatement pstm;
    private static ResultSet res;
    
    public TanqueoDAO(){
        con=Coneccion.estado();
    }

    @Override
    public List<Tanqueo> selectByVehiculo(String marca, String modelo,String correo) {
        List<Tanqueo> tanqueos=new ArrayList<>();
        final String SQL_SelectAll="SELECT u.Nombre, COUNT(t.id_ruta)as Veces, r.origen, SUM(t.pago_total) as Gasto FROM Tanqueo t INNER JOIN Ruta r ON t.id_ruta=r.id_ruta INNER JOIN VEHICULO v ON t.id_vehiculo=v.id_vehiculo INNER JOIN Usuario u ON u.id_usuario=v.id_usuario WHERE u.correo=? AND v.marca=? AND v.modelo=? GROUP BY u.Nombre, r.origen";
        try{
            pstm=con.getConectar().prepareStatement(SQL_SelectAll);
            pstm.setString(1, correo);
            pstm.setString(2, marca);
            pstm.setString(3, modelo);
            
            res=pstm.executeQuery();
            while(res.next()){
                Usuario usuario=new Usuario
                        .Builder()
                        .nombre(res.getString("Nombre"))
                        .build();
                Ruta ruta=new Ruta
                        .Builder()
                        .origen(res.getString("origen"))
                        .build();
            tanqueos.add(new Tanqueo.Builder().usuario(usuario).nveces(res.getString("Veces")).pagoTotal(res.getDouble("Gasto")).build());
            }
        }catch(Exception e){
            System.out.println("Error al obtener datos de los vehiculos por usuario: "+e.getMessage());
            e.printStackTrace();
        }finally{
            cerrarConexion();
        }
        return tanqueos;
    }

    @Override
    public boolean insert(Tanqueo t) {
       boolean resultFlag=false;
        final String SQL_INSERT="INSERT INTO Tanqueo (id_vehiculo,id_ruta,lugar_llenado,pago_total,fecha_llenado,cantidad_llenada_gal,temperatura_C,humedad,presion_atm) VALUES (?,?,?,?,?,?,?,?,?)";
        try{
            pstm=con.getConectar().prepareStatement(SQL_INSERT);
            pstm.setInt(1, t.getVehiculo().getId_vehiculo());
            pstm.setInt(2, t.getRuta().getIdRuta());
            pstm.setString(3, t.getLugar());
            String a=""+t.getPagoTotal();
            pstm.setString(4, a);
            pstm.setString(5, t.getFecha());
            String b=""+t.getCantLlenada();
            pstm.setString(6, b);
            pstm.setDouble(7, 24.0);
            pstm.setInt(8, 71);
            pstm.setDouble(9,1.01);
            if(pstm.executeUpdate()>0){                
                resultFlag=true;
            }
        }catch(Exception e){
            System.out.println("Error al insertar tanqueo: "+e.getMessage());
            e.printStackTrace();
        }finally{
            cerrarConexion();
        }
        return resultFlag;       

    }

    @Override
    public boolean update(Tanqueo t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tanqueo selectById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public List<Tanqueo> selectAll() {
        List<Tanqueo> tanqueos=new ArrayList<>();
        final String SQL_SelectAll="SELECT * FROM Tanqueo t INNER JOIN Ruta r ON t.id_ruta=r.id_ruta INNER JOIN VEHICULO v ON t.id_vehiculo=v.id_vehiculo INNER JOIN Usuario u ON u.id_usuario=v.id_usuario WHERE u.correo=? AND v.marca=? AND v.modelo=? GROUP BY u.Nombre, r.origen";
        try{
            pstm=con.getConectar().prepareStatement(SQL_SelectAll);
            
            
            res=pstm.executeQuery();
            while(res.next()){
                Usuario usuario=new Usuario
                        .Builder()
                        .nombre(res.getString("Nombre"))
                        .build();
                Ruta ruta=new Ruta
                        .Builder()
                        .origen(res.getString("origen"))
                        .build();
            tanqueos.add(new Tanqueo.Builder().usuario(usuario).nveces(res.getString("Veces")).pagoTotal(res.getDouble("Gasto")).build());
            }
        }catch(Exception e){
            System.out.println("Error al obtener datos de los vehiculos por usuario: "+e.getMessage());
            e.printStackTrace();
        }finally{
            cerrarConexion();
        }
        return tanqueos;
    }

    @Override
    public boolean delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public List<Tanqueo> listarTanqueos(String id_usuario, String idvehiculo) {
        List<Tanqueo> tanqueos=new ArrayList<>();
        final String SQL_SelectAll="select cod_tanqueo, cod_vehiculo, lugar_llenado, cantidad_llenada_gal, pago_total,fecha_llenado from Tanqueo t INNER JOIN  Vehiculo v ON t.id_vehiculo=v.id_vehiculo INNER JOIN Motor m on v.id_motor=m.id_motor INNER JOIN Asignacion a on a.id_vehiculo=v.id_vehiculo INNER JOIN Usuario u on u.id_usuario=a.id_usuario where u.id_usuario=? and t.id_vehiculo=?";
        try{
            pstm=con.getConectar().prepareStatement(SQL_SelectAll);
            pstm.setString(1, id_usuario);
            pstm.setString(2, idvehiculo);            
            res=pstm.executeQuery();
            
            while(res.next()){
                Vehiculo vehiculo=new Vehiculo
                        .Builder()
                        .codVehiculo(res.getString("cod_vehiculo"))
                        .build();
            tanqueos.add(new Tanqueo.Builder().codTanqueo(res.getString("cod_tanqueo")).vehiculo(vehiculo).lugar(res.getString("lugar_llenado")).cantLlenada(res.getInt("cantidad_llenada_gal")).pagoTotal(res.getDouble("pago_total")).fecha(res.getString("fecha_llenado")).build());
            }
        }catch(Exception e){
            System.out.println("Error al obtener datos de Tanqueo: "+e.getMessage());
            e.printStackTrace();
        }finally{
            cerrarConexion();
        }
        return tanqueos;
    }
    
    
    public List<Tanqueo> ListGrifo() {
        List<Tanqueo>tanqueo = new ArrayList<>();
        final String SQL_Query="select lugar_llenado from Tanqueo  WHERE lugar_llenado <> 'no' GROUP BY lugar_llenado;";
      try{
          pstm=con.getConectar().prepareStatement(SQL_Query);
          res=pstm.executeQuery();
          while(res.next()){
            tanqueo.add(new Tanqueo.Builder().lugar(res.getString("lugar_llenado")).build());
          }
      }catch(Exception e){
            System.out.println("Error al obtener los grifos: "+e.getMessage());
            e.printStackTrace();
        }finally{
            cerrarConexion();
        }
        return tanqueo;
    }

    public Double mostarAhorro(String id_usuario) {
            Double ahorro=0.0;
            final String SQL_SelectAll="SELECT SUM(diferencia) AS ahorro FROM ( SELECT SUM(pago_total) - LAG(SUM(pago_total)) OVER (ORDER BY MONTH(fecha_llenado)) AS diferencia FROM Tanqueo t INNER JOIN Ruta r on t.id_ruta=r.id_ruta INNER JOIN Usuario u on u.id_usuario=r.id_usuario where u.id_usuario=? and YEAR(fecha_llenado) = YEAR(GETDATE()) GROUP BY MONTH(fecha_llenado)) AS subconsulta";
            try{
                pstm=con.getConectar().prepareStatement(SQL_SelectAll);
                pstm.setString(1, id_usuario);
                res=pstm.executeQuery();
                if(res.next()){
                    ahorro=res.getDouble("ahorro");
                }
            }catch(Exception e){
                System.out.println("Error al obtener el ahorro del año pasado: "+e.getMessage());
                e.printStackTrace();
            }finally{
                cerrarConexion();
            }
            return ahorro;
        }
     public Double mostarGasto(String id_usuario) {
            Double pago=0.0;
            final String SQL_SelectAll="SELECT ISNULL(SUM(t.pago_total), 0) AS pago FROM Tanqueo t INNER JOIN Ruta r ON t.id_ruta = r.id_ruta INNER JOIN Usuario u ON u.id_usuario = r.id_usuario WHERE  u.id_usuario = ?   AND YEAR(t.fecha_llenado) = YEAR(GETDATE())";
            try{
                pstm=con.getConectar().prepareStatement(SQL_SelectAll);
                pstm.setString(1, id_usuario);
                res=pstm.executeQuery();
                if(res.next()){
                    pago=res.getDouble("pago");
                }
            }catch(Exception e){
                System.out.println("Error al obtener el gasto del año pasado: "+e.getMessage());
                e.printStackTrace();
            }finally{
                cerrarConexion();
            }
            return pago;
        }
     
}

    