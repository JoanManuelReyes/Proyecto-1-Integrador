package Modelo;

public class Usuario {
    private String codUsuario;
    private String nombre;
    private String correo;
    private String contraseña;

    public Usuario() {
    }
    
    public static class Builder{
        private Usuario usuario;

        public Builder(){
            usuario = new Usuario();
        }
        
        public Builder(Usuario usuario) {
            usuario = new Usuario();
        }       
                
        public Builder nombre(String nombre){
            this.usuario.nombre = nombre;
            return this;
        }
        
        public Builder codUsuario(String codUsuario){
            this.usuario.codUsuario = codUsuario;
            return this;
        }

        public Builder correo(String correo){
            this.usuario.correo = correo;
            return this;
        }
        
        public Builder contraseña(String contraseña){
            this.usuario.contraseña = contraseña;
            return this;
        }
        
        public Usuario build(){
            return usuario;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + ", correo=" + correo + ", contrase\u00f1a=" + contraseña + '}';
    }
    
    
}
