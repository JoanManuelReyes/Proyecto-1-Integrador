package Modelo;

public class  Ruta{
    private int idRuta;
    float distancia;
    String origen;
    String destino;
    private String nombre;
    int  horaFinal;
    int  tiempoTrafico;
    int  horaInicio;    
    double temperatura=24.0;
    int humedad=71;
    double presion=1.01; 
    double gasto;
    Motor motor;

    public Ruta() {
    }
    
    public static class Builder{
        private Ruta ruta;
        
        public Builder(){
            ruta= new Ruta();
        }
        public Builder idRuta(int idRuta){
            this.ruta.setIdRuta(idRuta);
            return this;
        }
        
        public Builder distancia(float distancia){
            this.ruta.distancia=distancia;
            return this;
        }
        
        public Builder origen(String origen){
            this.ruta.origen=origen;
            return this;
        }
        
        public Builder destino(String destino){
            this.ruta.destino=destino;
            return this;
        }
        
        public Builder nombre(String nombre){
            this.ruta.nombre=nombre;
            return this;
        }
        
        public Builder horaFinal(int horaFinal){
            this.ruta.horaFinal=horaFinal;
            return this;
        }
        
        public Builder tiempoTrafico(int tiempoTrafico){
            this.ruta.tiempoTrafico=tiempoTrafico;
            return this;
        }
        
        public Builder horaInicio(int horaInicio){
            this.ruta.horaInicio=horaInicio;
            return this;
        }
        
        public Builder temperatura(double temperatura){
            this.ruta.temperatura=temperatura;
            return this;
        }
        
        public Builder humedad(int humedad){
            this.ruta.humedad=humedad;
            return this;
        }
        
        public Builder presion(double presion){
            this.ruta.presion=presion;
            return this;
        }
        
        public Builder gasto(double gasto){
            this.ruta.gasto=gasto;
            return this;
        }
        
        public Builder motor(Motor motor){
            this.ruta.motor=motor;
            return this;
        }
        
        public Ruta build(){
            return ruta;
        }
        
    }

    public float getDistancia() {
        return distancia;
    }

    public void setDistancia(float distancia) {
        this.distancia = distancia;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(int horaFinal) {
        this.horaFinal = horaFinal;
    }

    public int getTiempoTrafico() {
        return tiempoTrafico;
    }

    public void setTiempoTrafico(int tiempoTrafico) {
        this.tiempoTrafico = tiempoTrafico;
    }

    public int getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(int horaInicio) {
        this.horaInicio = horaInicio;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public int getHumedad() {
        return humedad;
    }

    public void setHumedad(int humedad) {
        this.humedad = humedad;
    }

    public double getPresion() {
        return presion;
    }

    public void setPresion(double presion) {
        this.presion = presion;
    }

    public Motor getMotor() {
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getGasto() {
        return gasto;
    }

    public void setGasto(double gasto) {
        this.gasto = gasto;
    }
    
    public double calcularConsumoIdeal(Ruta ruta, Motor motor) {
        return (motor.getConsumo() * (ruta.getDistancia()*0.01)) + (ruta.getTiempoTrafico() * 1/90);
    }
    
    public double consumo_temperatura(Ruta ruta, Motor motor){
        Double consumo=0.0;
        if(ruta.getTemperatura()<20 || ruta.getTemperatura()>30){
            consumo=calcularConsumoIdeal(ruta,motor)*0.07;
        }
        return consumo;
    }
    
    public double consumo_humedad(Ruta ruta, Motor motor){
        Double consumo=0.0;
        if(ruta.getHumedad()>30){
            consumo=calcularConsumoIdeal(ruta,motor)*0.01;
        }
        return consumo;
    }
    
    public double consumo_presion(Ruta ruta, Motor motor){
        Double consumo=0.0;
        if(ruta.getPresion()>1){
            consumo=calcularConsumoIdeal(ruta,motor)*0.05;
        }
        return consumo;
    }
    
    public double calcularConsumoFinal(Ruta ruta, Motor motor) {
        return calcularConsumoIdeal(ruta,motor)+consumo_temperatura(ruta,motor)+consumo_humedad(ruta,motor)+consumo_presion(ruta,motor);
    }
    
    public int calcularTiempoTotal() {
       return (horaFinal -horaInicio);
    }   

    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }
            
}
