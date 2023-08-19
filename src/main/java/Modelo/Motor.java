package Modelo;

public class Motor {
    String SistCom;
    int año;
    double consumo;
    String tipo;
    String modelo;
    double cilindros;
    String torque;
    String sistIgnici; 

    public Motor() {
    }
    
   public static class Builder{
        private Motor motor;
        
        public Builder(){
            motor=new Motor();
        }
        
        public Builder SistCom(String SistCom){
            this.motor.SistCom = SistCom;
            return this;
        }
        
        public Builder año(int año){
            this.motor.año = año;
            return this;
        }
        
        public Builder consumo(double consumo){
            this.motor.consumo = consumo;
            return this;
        }
        
        public Builder tipo(String tipo){
            this.motor.tipo = tipo;
            return this;
        }
        
        public Builder modelo(String modelo){
            this.motor.modelo = modelo;
            return this;
        }
        
        public Builder cilindros(double cilindros){
            this.motor.cilindros = cilindros;
            return this;
        }
        
        public Builder torque(String torque){
            this.motor.torque = torque;
            return this;
        }
        
        public Builder sistIgnici(String sistIgnici){
            this.motor.sistIgnici = sistIgnici;
            return this;
        }
        
        public Motor build(){
            return  motor;
        }
        
   } 

    public String getSistCom() {
        return SistCom;
    }

    public void setSistCom(String SistCom) {
        this.SistCom = SistCom;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public double getConsumo() {
        return consumo;
    }

    public void setConsumo(double consumo) {
        this.consumo = consumo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getCilindros() {
        return cilindros;
    }

    public void setCilindros(double cilindros) {
        this.cilindros = cilindros;
    }

    public String getTorque() {
        return torque;
    }

    public void setTorque(String torque) {
        this.torque = torque;
    }

    public String getSistIgnici() {
        return sistIgnici;
    }

    public void setSistIgnici(String sistIgnici) {
        this.sistIgnici = sistIgnici;
    }
   
   
    
}
