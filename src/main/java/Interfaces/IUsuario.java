package Interfaces;
public interface IUsuario {
    String ValidacionInicioSesion (String correo, String pass);
    String tipoUsuario(String correo);
    String id_usuario (String correo, String pass);
    String id_empresa(String correo, String pass);
}
