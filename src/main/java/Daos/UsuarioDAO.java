package Daos;
import Coneccion.Coneccion;
import Interfaces.ICrud;
import Interfaces.IUsuario;
import Modelo.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
public class UsuarioDAO  implements IUsuario, ICrud{

    private static Coneccion con;
    private static PreparedStatement pstm;
    private static ResultSet res;
    
    public UsuarioDAO(){
        con=Coneccion.estado();
    }
    
    @Override
    public String ValidacionInicioSesion(String correo, String pass) {
        String busqueda_empleado = null;
        final String SQL_Query="select correo,contrasenia from Usuario where correo=? AND contrasenia=?";
      try{
          pstm=con.getConectar().prepareStatement(SQL_Query);
          pstm.setString(1, correo);
          pstm.setString(2, pass);
          res=pstm.executeQuery();
          if(res.next()){
              busqueda_empleado = "s";
          }else{
              busqueda_empleado = "n";
          }
      }catch(Exception e){
            System.out.println("Error al validar sesión: "+e.getMessage());
            e.printStackTrace();
        }finally{
            cerrarConexion();
        }
        return busqueda_empleado;
    }

    @Override
    public String tipoUsuario(String correo) {
        String tipo_usuario = null;
        final String SQL_SelectAll="select p.trabajo_empresa from Persona_natural p INNER JOIN Usuario u on p.id_usuario=u.id_usuario where u.correo=?";
        try{
          pstm=con.getConectar().prepareStatement(SQL_SelectAll);
          pstm.setString(1, correo);
          res=pstm.executeQuery();
          if(res.next()){
              if(res.getString(1).equals("si")){
                  tipo_usuario="empresa";
              }else{
                  tipo_usuario="persona";
              };
          }
        }catch(Exception e){
            System.out.println("Error al obtener el tipo de persona: "+e.getMessage());
            e.printStackTrace();
        }finally{
            cerrarConexion();
        }
        return tipo_usuario;
    }

    @Override
    public String id_usuario(String correo, String pass) {
        String id_usuario = null;
        final String SQL_SelectAll="select id_usuario from Usuario where correo=? AND contrasenia=?";
        try{
          pstm=con.getConectar().prepareStatement(SQL_SelectAll);
          pstm.setString(1, correo);
          pstm.setString(2, pass);
          res=pstm.executeQuery();
          if(res.next()){
                  id_usuario=res.getString("id_usuario");
          }
        }catch(Exception e){
            System.out.println("Error al obtener el id del usuario: "+e.getMessage());
            e.printStackTrace();
        }finally{
            cerrarConexion();
        }
        return id_usuario;
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
    public boolean insert(Object t) {
        Usuario usuario = new Usuario();
       boolean resultFlag=false;
        final String SQL_INSERT="INSERT INTO USUARIO (nombre,correo,contrasenia) VALUES (?, ?,?);";
        try{
            pstm=con.getConectar().prepareStatement(SQL_INSERT);
            pstm.setString(1,usuario.getNombre());
            pstm.setString(2, usuario.getCorreo());
            pstm.setString(3, usuario.getContraseña());
            if(pstm.executeUpdate()>0){                
                resultFlag=true;
            }
        }catch(Exception e){
            System.out.println("Error al insertar usuario: "+e.getMessage());
            e.printStackTrace();
        }finally{
            cerrarConexion();
        }
        return resultFlag;
    }

    @Override
    public boolean update(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object selectById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
   @Override
    public String id_empresa(String correo, String pass) {
        String id_empresa = null;
        final String SQL_SelectAll="select id_empresa from Persona_natural p Inner Join Usuario u on p.id_usuario=u.id_usuario where u.correo=? and u.contrasenia=?";
        try{
          pstm=con.getConectar().prepareStatement(SQL_SelectAll);
          pstm.setString(1, correo);
          pstm.setString(2, pass);
          res=pstm.executeQuery();
          if(res.next()){
                  id_empresa=res.getString("id_empresa");
          }
        }catch(Exception e){
            System.out.println("Error al obtener el id de la empresa: "+e.getMessage());
            e.printStackTrace();
        }finally{
            cerrarConexion();
        }
        return id_empresa;
    }
}


