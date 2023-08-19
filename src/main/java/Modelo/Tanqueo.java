package Modelo;

public class Tanqueo {
    Usuario usuario;
    Vehiculo vehiculo;
    private String id_tanqueo;
    private String codTanqueo;
    String nveces;
    double pagoTotal;
    String fecha;
    double cantLlenada;
    double temperatura=24;
    String lugar;
    int humedad=75;
    double presion=1; 
    Ruta ruta;

    public Tanqueo() {
    }
    
    public static class Builder{
        private Tanqueo tanqueo;
        
        public Builder(){
            tanqueo = new Tanqueo();
        }
        
        public Builder usuario(Usuario usuario){
            this.tanqueo.usuario=usuario;
            return this;
        }
        
        public Builder vehiculo(Vehiculo vehiculo){
            this.tanqueo.vehiculo=vehiculo;
            return this;
        }
        public Builder id_tanqueo(String id_tanqueo){
            this.tanqueo.id_tanqueo =id_tanqueo;
            return this;
        }
        
        public Builder codTanqueo(String codTanqueo){
            this.tanqueo.codTanqueo =codTanqueo;
            return this;
        }
        
        public Builder nveces(String nveces){
            this.tanqueo.nveces=nveces;
            return this;
        }
        
        public Builder lugar(String lugar){
            this.tanqueo.lugar=lugar;
            return this;
        }
        
        public Builder pagoTotal(double pagoTotal){
            this.tanqueo.pagoTotal=pagoTotal;
            return this;
        }
        
        public Builder fecha(String fecha){
            this.tanqueo.fecha=fecha;
            return this;
        }
        
        public Builder cantLlenada(double cantLlenada){
            this.tanqueo.cantLlenada=cantLlenada;
            return this;
        }
        
        public Builder temperatura(double temperatura){
            this.tanqueo.temperatura=temperatura;
            return this;
        }
        
        public Builder humedad(int humedad){
            this.tanqueo.humedad=humedad;
            return this;
        }
        
        public Builder presion(double presion){
            this.tanqueo.presion=presion;
            return this;
        }
        public Builder ruta(Ruta ruta){
            this.tanqueo.ruta = ruta;
                    return this;
        }
                
        public Tanqueo build(){
            return tanqueo;
        }
        
    }

    public double getPagoTotal() {
        return pagoTotal;
    }

    public void setPagoTotal(double pagoTotal) {
        this.pagoTotal = pagoTotal;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getCantLlenada() {
        return cantLlenada;
    }

    public void setCantLlenada(double cantLlenada) {
        this.cantLlenada = cantLlenada;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }


    public String getCodTanqueo() {
        return codTanqueo;
    }

    public void setCodTanqueo(String codTanqueo) {
        this.codTanqueo = codTanqueo;
    }

    public String getNveces() {
        return nveces;
    }

    public void setNveces(String nveces) {
        this.nveces = nveces;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getId_tanqueo() {
        return id_tanqueo;
    }

    public void setId_tanqueo(String id_tanqueo) {
        this.id_tanqueo = id_tanqueo;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }
}
