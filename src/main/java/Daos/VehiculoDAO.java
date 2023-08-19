package Daos;
import Coneccion.Coneccion;
import Modelo.Vehiculo;
import Interfaces.IVehiculo;
import Modelo.Motor;
import Modelo.Ruta;
import Modelo.Tanqueo;
import Modelo.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class VehiculoDAO implements IVehiculo{
    
    private static Coneccion con;
    private static PreparedStatement pstm;
    private static ResultSet res;
    
    public VehiculoDAO(){
        con=Coneccion.estado();
    }
    
    @Override
    public List<Vehiculo> selectByCorreoUser(String correo) {
        List<Vehiculo> vehiculos=new ArrayList<>();
        final String SQL_SelectAll="select v.marca, v.modelo, v.capacidad_combusitble_gal,v.potencia_kw,v.anio_adquision,v.anio_fabricacion,v.velocidad_maxima,m.tipo,m.SitemaCombustible from Vehiculo v INNER JOIN Motor m on v.id_motor=m.id_motor INNER JOIN Asignacion a on a.id_vehiculo=v.id_vehiculo INNER JOIN Usuario u on u.id_usuario=a.id_usuario where u.correo=?";
        try{
            pstm=con.getConectar().prepareStatement(SQL_SelectAll);
            pstm.setString(1, correo);
            
            res=pstm.executeQuery();
            while(res.next()){
                Motor motor=new Motor
                        .Builder()
                        .tipo(res.getString("tipo"))
                        .SistCom(res.getString("SitemaCombustible"))
                        .build();
            vehiculos.add(new Vehiculo.Builder().marca(res.getString("marca")).modelo(res.getString("modelo")).capacidad(res.getDouble("capacidad_combusitble_gal")).potencia(res.getString("potencia_kw")).anioAdqui(res.getInt("anio_adquision")).anioFabri(res.getInt("anio_fabricacion")).velocidad(res.getDouble("velocidad_maxima")).motor(motor).build());
            }
        }catch(Exception e){
            System.out.println("Error al obtener datos de los vehiculos por usuario: "+e.getMessage());
            e.printStackTrace();
        }finally{
            cerrarConexion();
        }
        return vehiculos;
    }

    @Override
    public boolean insert(Vehiculo t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Vehiculo t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Vehiculo selectById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Vehiculo> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public String id_vehiculoByNombreVehiculo(String nombre) {
        String id_vehiculo = null;
        final String SQL_Query="select id_vehiculo from Vehiculo where CONCAT(marca, ' ', modelo) = ?";
      try{
          pstm=con.getConectar().prepareStatement(SQL_Query);
          pstm.setString(1, nombre);
          res=pstm.executeQuery();
          if(res.next()){
              id_vehiculo=res.getString("id_vehiculo");
          }
      }catch(Exception e){
            System.out.println("Error al obtener el id del vehiculo: "+e.getMessage());
            e.printStackTrace();
        }finally{
            cerrarConexion();
        }
        return id_vehiculo;
    }

    @Override
    public Vehiculo vehiculoByIdVehiculo(String idvehiculo) {
        Vehiculo vehiculo=new Vehiculo();
        final String SQL_SelectAll="select v.marca, v.modelo, v.capacidad_combusitble_gal,v.potencia_kw,v.anio_adquision,v.anio_fabricacion,v.velocidad_maxima,m.tipo,m.SitemaCombustible from Vehiculo v INNER JOIN Motor m on v.id_motor=m.id_motor where id_vehiculo = ?";
        try{
            pstm=con.getConectar().prepareStatement(SQL_SelectAll);
            pstm.setString(1, idvehiculo);
            res=pstm.executeQuery();
            while(res.next()){
                vehiculo.setMarca(res.getString("marca"));
                vehiculo.setModelo(res.getString("modelo"));
                vehiculo.setCapacidad(res.getDouble("capacidad_combusitble_gal"));               
                vehiculo.setPotencia(res.getString("potencia_kw"));
                vehiculo.setAnioAdqui(res.getInt("anio_adquision"));
                vehiculo.setAnioFabri(res.getInt("anio_fabricacion"));
                vehiculo.setVelocidad(res.getDouble("velocidad_maxima"));
                vehiculo.setTipoaxu(res.getString("tipo"));
                vehiculo.setSistemaaux(res.getString("SitemaCombustible"));
            }
        }catch(Exception e){
            System.out.println("Error al obtener datos del vehiculo por id: "+e.getMessage());
            e.printStackTrace();
        }finally{
            cerrarConexion();
        }
        return vehiculo;
    }

    @Override
    public Double ahorroHoy(String id_usuario) {
        Double ahorro=0.0;
        final String SQL_SelectAll="SELECT ISNULL((SELECT SUM(pago_total) FROM Tanqueo t INNER JOIN Ruta r on t.id_ruta=r.id_ruta INNER JOIN Usuario u on u.id_usuario=r.id_usuario where u.id_usuario=? and  fecha_llenado = CONVERT(date, GETDATE())),0) - ISNULL((SELECT SUM(pago_total) FROM Tanqueo t INNER JOIN Ruta r on t.id_ruta=r.id_ruta INNER JOIN Usuario u on u.id_usuario=r.id_usuario where u.id_usuario=? and  fecha_llenado = CONVERT(date, DATEADD(day, -1, GETDATE()))),0) AS ahorro FROM Usuario u WHERE u.id_usuario =?";
        try{
            pstm=con.getConectar().prepareStatement(SQL_SelectAll);
            pstm.setString(1, id_usuario);
            pstm.setString(2, id_usuario);
            pstm.setString(3, id_usuario);
            res=pstm.executeQuery();
            while(res.next()){
                ahorro=res.getDouble("ahorro");
            }
        }catch(Exception e){
            System.out.println("Error al obtener el ahorro de hoy: "+e.getMessage());
            e.printStackTrace();
        }finally{
            cerrarConexion();
        }
        return ahorro;
    }
    
//    public Double Listahorro(String id_usuario) {
//        Vehiculo vehiculo = new Vehiculo();
//        Double ahorro=0.0;
//        final String SQL_SelectAll="SELECT u.id_usuario, v.cod_vehiculo, ISNULL((SELECT SUM(pago_total) FROM Tanqueo t INNER JOIN Ruta r on t.id_ruta=r.id_ruta INNER JOIN Usuario u on u.id_usuario=r.id_usuario where u.id_usuario=? and  fecha_llenado = CONVERT(date, GETDATE())),0) - ISNULL((SELECT SUM(pago_total) FROM Tanqueo t INNER JOIN Ruta r on t.id_ruta=r.id_ruta INNER JOIN Usuario u on u.id_usuario=r.id_usuario where u.id_usuario=? and  fecha_llenado = CONVERT(date, DATEADD(day, -1, GETDATE()))),0) AS ahorro FROM Usuario u INNER JOIN Asignacion a ON u.id_usuario=a.id_usuario INNER JOIN Vehiculo v ON a.id_vehiculo=v.id_vehiculo WHERE u.id_usuario =?";
//        try{
//            pstm=con.getConectar().prepareStatement(SQL_SelectAll);
//            pstm.setString(1, id_usuario);
//            pstm.setString(2, id_usuario);
//            pstm.setString(3, id_usuario);
//            res=pstm.executeQuery();
//            while(res.next()){
//                ahorro=res.getDouble("ahorro");
//                vehiculo.getCodVehiculo(
//                
//                
//            }
//        }catch(Exception e){
//            System.out.println("Error al obtener el ahorro de hoy: "+e.getMessage());
//            e.printStackTrace();
//        }finally{
//            cerrarConexion();
//        }
//        return ahorro;
//    }
    

    @Override
    public Double ahorroSemanaPasada(String id_usuario) {
        Double ahorro=0.0;
        final String SQL_SelectAll="SELECT ISNULL((SELECT SUM(pago_total) FROM Tanqueo t INNER JOIN Ruta r on t.id_ruta=r.id_ruta INNER JOIN Usuario u on u.id_usuario=r.id_usuario where u.id_usuario=? and DATEPART(week, fecha_llenado) = DATEPART(week, GETDATE())),0) - ISNULL((SELECT SUM(pago_total) FROM Tanqueo t INNER JOIN Ruta r on t.id_ruta=r.id_ruta INNER JOIN Usuario u on u.id_usuario=r.id_usuario where u.id_usuario=? and DATEPART(week, fecha_llenado) = DATEPART(week, DATEADD(week, -1, GETDATE()))),0) AS ahorro FROM Usuario u WHERE u.id_usuario =?";
        try{
            pstm=con.getConectar().prepareStatement(SQL_SelectAll);
            pstm.setString(1, id_usuario);
            pstm.setString(2, id_usuario);
            pstm.setString(3, id_usuario);
            res=pstm.executeQuery();
            while(res.next()){
                ahorro=res.getDouble("ahorro");
            }
        }catch(Exception e){
            System.out.println("Error al obtener el ahorro de la semana pasada: "+e.getMessage());
            e.printStackTrace();
        }finally{
            cerrarConexion();
        }
        return ahorro;
    }

    @Override
    public Double gastoHoy(String id_usuario) {
        Double gasto=0.0;
        final String SQL_SelectAll="select ISNULL(SUM(pago_total), 0) AS gasto from Tanqueo t INNER JOIN Ruta r on t.id_ruta=r.id_ruta INNER JOIN Usuario u on u.id_usuario=r.id_usuario where u.id_usuario=? and t.fecha_llenado=CONVERT(date, GETDATE())";
        try{
            pstm=con.getConectar().prepareStatement(SQL_SelectAll);
            pstm.setString(1, id_usuario);
            res=pstm.executeQuery();
            while(res.next()){
                gasto=res.getDouble("gasto");
            }
        }catch(Exception e){
            System.out.println("Error al obtener el gasto de hoy: "+e.getMessage());
            e.printStackTrace();
        }finally{
            cerrarConexion();
        }
        return gasto;
    }
    
    @Override
    public Double gastoAyer(String id_usuario) {
        Double gasto=0.0;
        final String SQL_SelectAll="select ISNULL(SUM(pago_total), 0) AS gasto from Tanqueo t INNER JOIN Ruta r on t.id_ruta=r.id_ruta INNER JOIN Usuario u on u.id_usuario=r.id_usuario where u.id_usuario=? and t.fecha_llenado=CONVERT(date, DATEADD(day, -1, GETDATE()))";
        try{
            pstm=con.getConectar().prepareStatement(SQL_SelectAll);
            pstm.setString(1, id_usuario);
            res=pstm.executeQuery();
            while(res.next()){
                gasto=res.getDouble("gasto");
            }
        }catch(Exception e){
            System.out.println("Error al obtener el gasto de ayer: "+e.getMessage());
            e.printStackTrace();
        }finally{
            cerrarConexion();
        }
        return gasto;
    }
    
    @Override
    public Double gastoSemanaPasada(String id_usuario) {
        Double gasto=0.0;
        final String SQL_SelectAll="select ISNULL(SUM(pago_total), 0) AS gasto from Tanqueo t INNER JOIN Ruta r on t.id_ruta=r.id_ruta INNER JOIN Usuario u on u.id_usuario=r.id_usuario where u.id_usuario=? and DATEPART(week, fecha_llenado) = DATEPART(week, DATEADD(week, -1, GETDATE()))";
        try{
            pstm=con.getConectar().prepareStatement(SQL_SelectAll);
            pstm.setString(1, id_usuario);
            res=pstm.executeQuery();
            while(res.next()){
                gasto=res.getDouble("gasto");
            }
        }catch(Exception e){
            System.out.println("Error al obtener el gasto de la semana pasada: "+e.getMessage());
            e.printStackTrace();
        }finally{
            cerrarConexion();
        }
        return gasto;
    }

    @Override
    public List<Vehiculo> datosViaje(String id_usuario, String id_vehiculo) {
        List <Vehiculo> datos=new ArrayList<>();
        final String SQL_SelectAll="SELECT t.id_tanqueo, u.nombre, COUNT(*) OVER (PARTITION BY u.nombre, r.origen, r.destino) AS veces,SUBSTRING(r.origen, CHARINDEX(', ', r.origen) + 2, LEN(r.origen)) AS distritoori,SUBSTRING(r.destino, CHARINDEX(', ', r.destino) + 2, LEN(r.destino)) AS distritodesti,t.pago_total FROM Ruta r INNER JOIN Usuario u ON r.id_usuario = u.id_usuario INNER JOIN Tanqueo t ON t.id_ruta = r.id_ruta WHERE u.id_usuario = ? AND t.id_vehiculo=?";
        try{
            pstm=con.getConectar().prepareStatement(SQL_SelectAll);
            pstm.setString(1, id_usuario);
            pstm.setString(2, id_vehiculo);
            res=pstm.executeQuery();
            while(res.next()){
                Tanqueo tanqueo=new Tanqueo
                        .Builder()
                        .id_tanqueo(res.getString("id_tanqueo"))
                        .pagoTotal(res.getDouble("pago_total"))
                        .build();
                Usuario usuario=new Usuario
                        .Builder()
                        .nombre(res.getString("nombre"))
                        .build();
                Ruta ruta=new Ruta
                        .Builder()
                        .origen(res.getString("distritoori"))
                        .destino(res.getString("distritodesti"))
                        .build();
            datos.add(new Vehiculo.Builder().tanqueo(tanqueo).usuario(usuario).ruta(ruta).anioAdqui(res.getInt("veces")).build());
            }
        }catch(Exception e){
            System.out.println("Error al obtener el gasto de la semana pasada: "+e.getMessage());
            e.printStackTrace();
        }finally{
            cerrarConexion();
        }
        return datos;
    }

    @Override
    public List<Vehiculo> datosViajeMayorveces(String id_usuario, String id_vehiculo) {
        List <Vehiculo> datos=new ArrayList<>();
        final String SQL_SelectAll="SELECT t.id_tanqueo, u.nombre, COUNT(*) OVER (PARTITION BY u.nombre, r.origen, r.destino) AS veces,SUBSTRING(r.origen, CHARINDEX(', ', r.origen) + 2, LEN(r.origen)) AS distritoori,SUBSTRING(r.destino, CHARINDEX(', ', r.destino) + 2, LEN(r.destino)) AS distritodesti,t.pago_total FROM Ruta r INNER JOIN Usuario u ON r.id_usuario = u.id_usuario INNER JOIN Tanqueo t ON t.id_ruta = r.id_ruta WHERE u.id_usuario = ? AND t.id_vehiculo=? ORDER BY veces DESC";
        try{
            pstm=con.getConectar().prepareStatement(SQL_SelectAll);
            pstm.setString(1, id_usuario);
            pstm.setString(2, id_vehiculo);
            res=pstm.executeQuery();
            while(res.next()){
                Tanqueo tanqueo=new Tanqueo
                        .Builder()
                        .id_tanqueo(res.getString("id_tanqueo"))
                        .pagoTotal(res.getDouble("pago_total"))
                        .build();
                Usuario usuario=new Usuario
                        .Builder()
                        .nombre(res.getString("nombre"))
                        .build();
                Ruta ruta=new Ruta
                        .Builder()
                        .origen(res.getString("distritoori"))
                        .destino(res.getString("distritodesti"))
                        .build();
            datos.add(new Vehiculo.Builder().tanqueo(tanqueo).usuario(usuario).ruta(ruta).anioAdqui(res.getInt("veces")).build());
            }
        }catch(Exception e){
            System.out.println("Error al obtener el gasto de la semana pasada: "+e.getMessage());
            e.printStackTrace();
        }finally{
            cerrarConexion();
        }
        return datos;
    }

    @Override
    public List<Vehiculo> datosViajeMayorGasto(String id_usuario, String id_vehiculo) {
        List <Vehiculo> datos=new ArrayList<>();
        final String SQL_SelectAll="SELECT t.id_tanqueo, u.nombre, COUNT(*) OVER (PARTITION BY u.nombre, r.origen, r.destino) AS veces,SUBSTRING(r.origen, CHARINDEX(', ', r.origen) + 2, LEN(r.origen)) AS distritoori,SUBSTRING(r.destino, CHARINDEX(', ', r.destino) + 2, LEN(r.destino)) AS distritodesti,t.pago_total FROM Ruta r INNER JOIN Usuario u ON r.id_usuario = u.id_usuario INNER JOIN Tanqueo t ON t.id_ruta = r.id_ruta WHERE u.id_usuario = ? AND t.id_vehiculo=? ORDER BY t.pago_total DESC";
        try{
            pstm=con.getConectar().prepareStatement(SQL_SelectAll);
            pstm.setString(1, id_usuario);
            pstm.setString(2, id_vehiculo);
            res=pstm.executeQuery();
            while(res.next()){
                Tanqueo tanqueo=new Tanqueo
                        .Builder()
                        .id_tanqueo(res.getString("id_tanqueo"))
                        .pagoTotal(res.getDouble("pago_total"))
                        .build();
                Usuario usuario=new Usuario
                        .Builder()
                        .nombre(res.getString("nombre"))
                        .build();
                Ruta ruta=new Ruta
                        .Builder()
                        .origen(res.getString("distritoori"))
                        .destino(res.getString("distritodesti"))
                        .build();
            datos.add(new Vehiculo.Builder().tanqueo(tanqueo).usuario(usuario).ruta(ruta).anioAdqui(res.getInt("veces")).build());
            }
        }catch(Exception e){
            System.out.println("Error al obtener el gasto de la semana pasada: "+e.getMessage());
            e.printStackTrace();
        }finally{
            cerrarConexion();
        }
        return datos;
    }

    @Override
    public List<Vehiculo> datosViajeMenorGasto(String id_usuario, String id_vehiculo) {
        List <Vehiculo> datos=new ArrayList<>();
        final String SQL_SelectAll="SELECT t.id_tanqueo, u.nombre, COUNT(*) OVER (PARTITION BY u.nombre, r.origen, r.destino) AS veces,SUBSTRING(r.origen, CHARINDEX(', ', r.origen) + 2, LEN(r.origen)) AS distritoori,SUBSTRING(r.destino, CHARINDEX(', ', r.destino) + 2, LEN(r.destino)) AS distritodesti,t.pago_total FROM Ruta r INNER JOIN Usuario u ON r.id_usuario = u.id_usuario INNER JOIN Tanqueo t ON t.id_ruta = r.id_ruta WHERE u.id_usuario = ? AND t.id_vehiculo=? ORDER BY t.pago_total ASC";
        try{
            pstm=con.getConectar().prepareStatement(SQL_SelectAll);
            pstm.setString(1, id_usuario);
            pstm.setString(2, id_vehiculo);
            res=pstm.executeQuery();
            while(res.next()){
                Tanqueo tanqueo=new Tanqueo
                        .Builder()
                        .id_tanqueo(res.getString("id_tanqueo"))
                        .pagoTotal(res.getDouble("pago_total"))
                        .build();
                Usuario usuario=new Usuario
                        .Builder()
                        .nombre(res.getString("nombre"))
                        .build();
                Ruta ruta=new Ruta
                        .Builder()
                        .origen(res.getString("distritoori"))
                        .destino(res.getString("distritodesti"))
                        .build();
            datos.add(new Vehiculo.Builder().tanqueo(tanqueo).usuario(usuario).ruta(ruta).anioAdqui(res.getInt("veces")).build());
            }
        }catch(Exception e){
            System.out.println("Error al obtener el gasto de la semana pasada: "+e.getMessage());
            e.printStackTrace();
        }finally{
            cerrarConexion();
        }
        return datos;
    }

    @Override
    public Double cantidadCombustibleRestante(String id_usuario, String vehiculo) {
        Double cantidad = 0.0;
        Vehiculo aux=new Vehiculo();
        final String SQL_Query="select t.cantidad_llenada_gal,m.consumo, r.distancia_km,r.tiempo_trafico_min,r.temperatura_C,r.humedad,r.presion_atm from Ruta R inner Join Tanqueo t on t.id_ruta=r.id_ruta INNER JOIN Vehiculo v on t.id_vehiculo=v.id_vehiculo INNER JOIN Motor m on m.id_motor=v.id_motor INNER JOIN Asignacion a on a.id_vehiculo=v.id_vehiculo INNER JOIN Usuario u on a.id_usuario=u.id_usuario where u.id_usuario=? AND CONCAT(v.marca, ' ', v.modelo) = ?";
        try{
            pstm=con.getConectar().prepareStatement(SQL_Query);
            pstm.setString(1, id_usuario);
            pstm.setString(2, vehiculo);
            res=pstm.executeQuery();
            while(res.next()){
                Motor motor=new Motor
                        .Builder()
                        .consumo(res.getDouble("consumo"))
                        .build();
                Tanqueo tanqueo=new Tanqueo
                        .Builder()
                        .cantLlenada(res.getDouble("cantidad_llenada_gal"))
                        .build();
                Ruta ruta=new Ruta
                        .Builder()
                        .distancia(res.getFloat("distancia_km"))
                        .tiempoTrafico(res.getInt("tiempo_trafico_min"))
                        .temperatura(res.getDouble("temperatura_C"))
                        .humedad(res.getInt("humedad"))
                        .presion(res.getDouble("presion_atm"))
                        .build();
                Vehiculo vehiculores=new Vehiculo
                        .Builder()
                        .motor(motor)
                        .ruta(ruta)
                        .tanqueo(tanqueo)
                        .build();
                cantidad+=aux.calcularGalsolinaRestante(vehiculores);
            }
        }catch(Exception e){
            System.out.println("Error al obtener el id del vehiculo: "+e.getMessage());
            e.printStackTrace();
        }finally{
            cerrarConexion();
        }
        return cantidad;
    }
    @Override
    public List<Vehiculo> datosViajeInicioPersona(String id_usuario) {
        List <Vehiculo> datos=new ArrayList<>();
        final String SQL_SelectAll="select t.id_tanqueo,r.nombre_ruta,u.nombre,v.marca,v.modelo,t.pago_total from Ruta r INNER JOIN Tanqueo t on r.id_ruta=t.id_ruta INNER JOIN Vehiculo v on v.id_vehiculo=t.id_vehiculo INNER JOIN Asignacion a on v.id_vehiculo=a.id_vehiculo INNER JOIN Usuario u on a.id_usuario=u.id_usuario where a.id_usuario=?";
        try{
            pstm=con.getConectar().prepareStatement(SQL_SelectAll);
            pstm.setString(1, id_usuario);
            res=pstm.executeQuery();
            while(res.next()){
                Tanqueo tanqueo=new Tanqueo
                        .Builder()
                        .id_tanqueo(res.getString("id_tanqueo"))
                        .pagoTotal(res.getDouble("pago_total"))
                        .build();
                Usuario usuario=new Usuario
                        .Builder()
                        .nombre(res.getString("nombre"))
                        .build();
                Ruta ruta=new Ruta
                        .Builder()
                        .nombre(res.getString("nombre_ruta"))
                        .build();
            datos.add(new Vehiculo.Builder().tanqueo(tanqueo).usuario(usuario).ruta(ruta).marca(res.getString("marca")).modelo(res.getString("modelo")).build());
            }
        }catch(Exception e){
            System.out.println("Error al datos del viaje de persona: "+e.getMessage());
            e.printStackTrace();
        }finally{
            cerrarConexion();
        }
        return datos;
    }

    @Override
    public List<Vehiculo> datosViajeInicioPersonaMenorPrecio(String id_usuario) {
        List <Vehiculo> datos=new ArrayList<>();
        final String SQL_SelectAll="select t.id_tanqueo,r.nombre_ruta,u.nombre,v.marca,v.modelo,t.pago_total from Ruta r INNER JOIN Tanqueo t on r.id_ruta=t.id_ruta INNER JOIN Vehiculo v on v.id_vehiculo=t.id_vehiculo INNER JOIN Asignacion a on v.id_vehiculo=a.id_vehiculo INNER JOIN Usuario u on a.id_usuario=u.id_usuario where a.id_usuario=? ORDER BY t.pago_total ASC";
        try{
            pstm=con.getConectar().prepareStatement(SQL_SelectAll);
            pstm.setString(1, id_usuario);
            res=pstm.executeQuery();
            while(res.next()){
                Tanqueo tanqueo=new Tanqueo
                        .Builder()
                        .id_tanqueo(res.getString("id_tanqueo"))
                        .pagoTotal(res.getDouble("pago_total"))
                        .build();
                Usuario usuario=new Usuario
                        .Builder()
                        .nombre(res.getString("nombre"))
                        .build();
                Ruta ruta=new Ruta
                        .Builder()
                        .nombre(res.getString("nombre_ruta"))
                        .build();
            datos.add(new Vehiculo.Builder().tanqueo(tanqueo).usuario(usuario).ruta(ruta).marca(res.getString("marca")).modelo(res.getString("modelo")).build());
            }
        }catch(Exception e){
            System.out.println("Error al datos del viaje de persona: "+e.getMessage());
            e.printStackTrace();
        }finally{
            cerrarConexion();
        }
        return datos;
    }

    @Override
    public List<Vehiculo> datosViajeInicioPersonaMayorPrecio(String id_usuario) {
        List <Vehiculo> datos=new ArrayList<>();
        final String SQL_SelectAll="select t.id_tanqueo,r.nombre_ruta,u.nombre,v.marca,v.modelo,t.pago_total from Ruta r INNER JOIN Tanqueo t on r.id_ruta=t.id_ruta INNER JOIN Vehiculo v on v.id_vehiculo=t.id_vehiculo INNER JOIN Asignacion a on v.id_vehiculo=a.id_vehiculo INNER JOIN Usuario u on a.id_usuario=u.id_usuario where a.id_usuario=? ORDER BY t.pago_total DESC";
        try{
            pstm=con.getConectar().prepareStatement(SQL_SelectAll);
            pstm.setString(1, id_usuario);
            res=pstm.executeQuery();
            while(res.next()){
                Tanqueo tanqueo=new Tanqueo
                        .Builder()
                        .id_tanqueo(res.getString("id_tanqueo"))
                        .pagoTotal(res.getDouble("pago_total"))
                        .build();
                Usuario usuario=new Usuario
                        .Builder()
                        .nombre(res.getString("nombre"))
                        .build();
                Ruta ruta=new Ruta
                        .Builder()
                        .nombre(res.getString("nombre_ruta"))
                        .build();
            datos.add(new Vehiculo.Builder().tanqueo(tanqueo).usuario(usuario).ruta(ruta).marca(res.getString("marca")).modelo(res.getString("modelo")).build());
            }
        }catch(Exception e){
            System.out.println("Error al datos del viaje de persona: "+e.getMessage());
            e.printStackTrace();
        }finally{
            cerrarConexion();
        }
        return datos;
    }

    @Override
    public List<Vehiculo> datosViajeInicioEmpresa(String id_empresa) {
        List <Vehiculo> datos=new ArrayList<>();
        final String SQL_SelectAll="select t.id_tanqueo,r.nombre_ruta,u.nombre,v.marca,v.modelo,t.pago_total from Ruta r INNER JOIN Tanqueo t on r.id_ruta=t.id_ruta INNER JOIN Vehiculo v on v.id_vehiculo=t.id_vehiculo INNER JOIN Asignacion a on v.id_vehiculo=a.id_vehiculo INNER JOIN Usuario u on a.id_usuario=u.id_usuario Inner Join Persona_natural p on u.id_usuario=p.id_usuario INNER JOIN EMPRESA e on e.id_empresa=p.id_empresa Where e.id_empresa=?";
        try{
            pstm=con.getConectar().prepareStatement(SQL_SelectAll);
            pstm.setString(1, id_empresa);
            res=pstm.executeQuery();
            while(res.next()){
                Tanqueo tanqueo=new Tanqueo
                        .Builder()
                        .id_tanqueo(res.getString("id_tanqueo"))
                        .pagoTotal(res.getDouble("pago_total"))
                        .build();
                Usuario usuario=new Usuario
                        .Builder()
                        .nombre(res.getString("nombre"))
                        .build();
                Ruta ruta=new Ruta
                        .Builder()
                        .nombre(res.getString("nombre_ruta"))
                        .build();
            datos.add(new Vehiculo.Builder().tanqueo(tanqueo).usuario(usuario).ruta(ruta).marca(res.getString("marca")).modelo(res.getString("modelo")).build());
            }
        }catch(Exception e){
            System.out.println("Error al datos del viaje de empresa: "+e.getMessage());
            e.printStackTrace();
        }finally{
            cerrarConexion();
        }
        return datos;
    }

    @Override
    public List<Vehiculo> datosViajeInicioEmpresaMenorPrecio(String id_empresa) {
        List <Vehiculo> datos=new ArrayList<>();
        final String SQL_SelectAll="select t.id_tanqueo,r.nombre_ruta,u.nombre,v.marca,v.modelo,t.pago_total from Ruta r INNER JOIN Tanqueo t on r.id_ruta=t.id_ruta INNER JOIN Vehiculo v on v.id_vehiculo=t.id_vehiculo INNER JOIN Asignacion a on v.id_vehiculo=a.id_vehiculo INNER JOIN Usuario u on a.id_usuario=u.id_usuario Inner Join Persona_natural p on u.id_usuario=p.id_usuario INNER JOIN EMPRESA e on e.id_empresa=p.id_empresa Where e.id_empresa=?  ORDER BY t.pago_total ASC";
        try{
            pstm=con.getConectar().prepareStatement(SQL_SelectAll);
            pstm.setString(1, id_empresa);
            res=pstm.executeQuery();
            while(res.next()){
                Tanqueo tanqueo=new Tanqueo
                        .Builder()
                        .id_tanqueo(res.getString("id_tanqueo"))
                        .pagoTotal(res.getDouble("pago_total"))
                        .build();
                Usuario usuario=new Usuario
                        .Builder()
                        .nombre(res.getString("nombre"))
                        .build();
                Ruta ruta=new Ruta
                        .Builder()
                        .nombre(res.getString("nombre_ruta"))
                        .build();
            datos.add(new Vehiculo.Builder().tanqueo(tanqueo).usuario(usuario).ruta(ruta).marca(res.getString("marca")).modelo(res.getString("modelo")).build());
            }
        }catch(Exception e){
            System.out.println("Error al datos del viaje de empresa: "+e.getMessage());
            e.printStackTrace();
        }finally{
            cerrarConexion();
        }
        return datos;
    }

    @Override
    public List<Vehiculo> datosViajeInicioEmpresaMayorPrecio(String id_empresa) {
        List <Vehiculo> datos=new ArrayList<>();
        final String SQL_SelectAll="select t.id_tanqueo,r.nombre_ruta,u.nombre,v.marca,v.modelo,t.pago_total from Ruta r INNER JOIN Tanqueo t on r.id_ruta=t.id_ruta INNER JOIN Vehiculo v on v.id_vehiculo=t.id_vehiculo INNER JOIN Asignacion a on v.id_vehiculo=a.id_vehiculo INNER JOIN Usuario u on a.id_usuario=u.id_usuario Inner Join Persona_natural p on u.id_usuario=p.id_usuario INNER JOIN EMPRESA e on e.id_empresa=p.id_empresa Where e.id_empresa=?  ORDER BY t.pago_total DESC";
        try{
            pstm=con.getConectar().prepareStatement(SQL_SelectAll);
            pstm.setString(1, id_empresa);
            res=pstm.executeQuery();
            while(res.next()){
                Tanqueo tanqueo=new Tanqueo
                        .Builder()
                        .id_tanqueo(res.getString("id_tanqueo"))
                        .pagoTotal(res.getDouble("pago_total"))
                        .build();
                Usuario usuario=new Usuario
                        .Builder()
                        .nombre(res.getString("nombre"))
                        .build();
                Ruta ruta=new Ruta
                        .Builder()
                        .nombre(res.getString("nombre_ruta"))
                        .build();
            datos.add(new Vehiculo.Builder().tanqueo(tanqueo).usuario(usuario).ruta(ruta).marca(res.getString("marca")).modelo(res.getString("modelo")).build());
            }
        }catch(Exception e){
            System.out.println("Error al datos del viaje de empresa: "+e.getMessage());
            e.printStackTrace();
        }finally{
            cerrarConexion();
        }
        return datos;
    }

    @Override
    public Double cosumoByIdUsuarioIdVehiculo(String id_usuario, String id_vehiculo) {
        Double consumo = 0.0;
        final String SQL_Query="select m.consumo from Motor m INNER JOIN Vehiculo v on m.id_motor=v.id_motor INNER JOIN Asignacion a on a.id_vehiculo=v.id_vehiculo Where a.id_usuario=? and v.id_vehiculo=?";
      try{
          pstm=con.getConectar().prepareStatement(SQL_Query);
          pstm.setString(1, id_usuario);
          pstm.setString(2, id_vehiculo);
          res=pstm.executeQuery();
          if(res.next()){
              consumo=res.getDouble("consumo");
          }
      }catch(Exception e){
            System.out.println("Error al obtener el consumo del motor del vehiculo: "+e.getMessage());
            e.printStackTrace();
        }finally{
            cerrarConexion();
        }
        return consumo;
    }

    @Override
    public Double gastoAño(String id_usuario) {
        Double gasto=0.0;
        final String SQL_SelectAll="SELECT ISNULL(SUM(t.pago_total), 0) AS pago FROM Tanqueo t INNER JOIN Ruta r ON t.id_ruta = r.id_ruta INNER JOIN Usuario u ON u.id_usuario = r.id_usuario WHERE u.id_usuario = ? AND YEAR(t.fecha_llenado) = YEAR(GETDATE())";
        try{
            pstm=con.getConectar().prepareStatement(SQL_SelectAll);
            pstm.setString(1, id_usuario);
            res=pstm.executeQuery();
            while(res.next()){
                gasto=res.getDouble("pago");
            }
        }catch(Exception e){
            System.out.println("Error al obtener el gasto del año: "+e.getMessage());
            e.printStackTrace();
        }finally{
            cerrarConexion();
        }
        return gasto;
    }

    @Override
    public Double ahorroAño(String id_usuario) {
        Double ahorro=0.0;
        final String SQL_SelectAll="SELECT SUM(diferencia) AS ahorro FROM(SELECT SUM(pago_total) - LAG(SUM(pago_total)) OVER (ORDER BY MONTH(fecha_llenado)) AS diferencia FROM Tanqueo t INNER JOIN Ruta r on t.id_ruta=r.id_ruta INNER JOIN Usuario u on u.id_usuario=r.id_usuario where u.id_usuario=? and YEAR(fecha_llenado) = YEAR(GETDATE()) GROUP BY MONTH(fecha_llenado)) AS subconsulta";
        try{
            pstm=con.getConectar().prepareStatement(SQL_SelectAll);
            pstm.setString(1, id_usuario);
            res=pstm.executeQuery();
            while(res.next()){
                ahorro=res.getDouble("ahorro");
            }
        }catch(Exception e){
            System.out.println("Error al obtener el ahorro del año: "+e.getMessage());
            e.printStackTrace();
        }finally{
            cerrarConexion();
        }
        return ahorro;
    }
}


