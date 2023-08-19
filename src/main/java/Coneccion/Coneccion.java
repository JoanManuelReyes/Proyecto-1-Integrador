/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Coneccion;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Arice
 */
public class Coneccion {
    private static Coneccion instance;
    private Connection conectar;
    
    String usuario = "sa";
    String contra = "2311r5";
    String db = "bdcombustible";
    String ip = "localhost";    
    String port = "1433";
    
    
    public Coneccion(){
        
        try {
         String cadena = "jdbc:sqlserver://localhost:"+port+";"+"databaseName="+db;
            conectar=DriverManager.getConnection(cadena, usuario, contra);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public synchronized static Coneccion estado(){
        if(instance == null){
            instance = new Coneccion();
        }
        return instance;
    }
    
    public Connection getConectar(){
        return conectar;
    }
    
    public void cerrarConexion(){
        instance = null;
    }
}
