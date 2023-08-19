package Modelo;

public class Vehiculo {
    private int id_vehiculo;
    private String codVehiculo;
    private String marca;
    private String modelo;   
    private String potencia;
    private int anioFabri;
    private int anioAdqui;
    private double velocidad;
    private double capacidad;
    private Tanqueo tanqueo;
    private Ruta ruta;
    private Motor motor;
    private Usuario usuario;
    private String tipoaxu;
    private String sistemaaux;

    public Vehiculo() {
    }
    
    public static class Builder{
        private Vehiculo vehiculo;
        
        public Builder(){
            vehiculo = new Vehiculo();
        }
        
        public Builder(Vehiculo vehiculo){
           vehiculo = new Vehiculo();
        }
        
        public Builder id_vehiculo(int id_vehiculo){
            this.vehiculo.id_vehiculo=id_vehiculo;
            return this;
        }
        
        public Builder marca(String marca){
            this.vehiculo.marca=marca;
            return this;
        }
        
        
        public Builder codVehiculo(String codVehiculo){
            this.vehiculo.codVehiculo =codVehiculo;
            return this;
        }
        
        public Builder modelo(String modelo){
            this.vehiculo.modelo=modelo;
            return this;
        }
        
        public Builder potencia(String potencia){
            this.vehiculo.potencia=potencia;
            return this;
        }
        
        public Builder anioFabri(int anioFabri){
            this.vehiculo.anioFabri=anioFabri;
            return this;
        }
        
        public Builder anioAdqui(int anioAdqui){
            this.vehiculo.anioAdqui=anioAdqui;
            return this;
        }
        
        public Builder velocidad(double velocidad){
            this.vehiculo.velocidad=velocidad;
            return this;
        }
        
        public Builder capacidad(double capacidad){
            this.vehiculo.capacidad=capacidad;
            return this;
        }
        
        public Builder tanqueo(Tanqueo tanqueo){
            this.vehiculo.tanqueo=tanqueo;
            return this;
        }
        public Builder ruta(Ruta ruta){
            this.vehiculo.ruta=ruta;
            return this;
        }
        
        public Builder motor(Motor motor){
            this.vehiculo.motor=motor;
            return this;
        }
        
         public Builder usuario(Usuario usuario){
            this.vehiculo.usuario=usuario;
            return this;
        }
        
        public Builder tipoaxu(String tipoaxu){
            this.vehiculo.tipoaxu=tipoaxu;
            return this;
        }
        
        public Builder sistemaaux(String sistemaaux){
            this.vehiculo.sistemaaux=sistemaaux;
            return this;
        }
        
        public Vehiculo build(){
            return vehiculo;
        }
        
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPotencia() {
        return potencia;
    }

    public void setPotencia(String potencia) {
        this.potencia = potencia;
    }

    public int getAnioFabri() {
        return anioFabri;
    }

    public void setAnioFabri(int anioFabri) {
        this.anioFabri = anioFabri;
    }

    public double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(double velocidad) {
        this.velocidad = velocidad;
    }

    public double getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(double capacidad) {
        this.capacidad = capacidad;
    }

    public Tanqueo getTanqueo() {
        return tanqueo;
    }

    public void setTanqueo(Tanqueo tanqueo) {
        this.tanqueo = tanqueo;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }
    
           
    public double calcularGalsolinaRestante(Vehiculo vehiculo) {
        return(vehiculo.getTanqueo().getCantLlenada() - vehiculo.ruta.calcularConsumoFinal(vehiculo.getRuta(),vehiculo.getMotor()));
    }  

   

    public String getCodVehiculo() {
        return codVehiculo;
    }

    public void setCodVehiculo(String codVehiculo) {
        this.codVehiculo = codVehiculo;
    }

    public Motor getMotor() {
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    

    public String getTipoaxu() {
        return tipoaxu;
    }

    public void setTipoaxu(String tipoaxu) {
        this.tipoaxu = tipoaxu;
    }

    public String getSistemaaux() {
        return sistemaaux;
    }

    public void setSistemaaux(String sistemaaux) {
        this.sistemaaux = sistemaaux;
    }

    public int getAnioAdqui() {
        return anioAdqui;
    }

    public void setAnioAdqui(int anioAdqui) {
        this.anioAdqui = anioAdqui;
    }

    public int getId_vehiculo() {
        return id_vehiculo;
    }

    public void setId_vehiculo(int id_vehiculo) {
        this.id_vehiculo = id_vehiculo;
    }
}

