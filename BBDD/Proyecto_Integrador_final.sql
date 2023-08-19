use master;
Go
IF EXISTS (SELECT * FROM SYS.SYSDATABASES WHERE NAME='bdcombustible')
   begin
	DROP DATABASE bdcombustible
	--GO
    end
create database bdcombustible;
Go
use bdcombustible;

-- tables
-- Table: Empresa
CREATE TABLE Empresa (
	id_empresa int  NOT NULL PRIMARY KEY IDENTITY(1,1) ,
	cod_empresa AS ('E'+ RIGHT('0000'+ CONVERT(VARCHAR, id_empresa), (4))),
    nombre_empresa varchar(50)  NOT NULL,
    RUC char(11)  NOT NULL,
);

-- Table: Motor
CREATE TABLE Motor (
	id_motor int  NOT NULL PRIMARY KEY IDENTITY(1,1) ,
	cod_motor AS ('M'+ RIGHT('0000'+ CONVERT(VARCHAR, id_motor), (4))),
    SitemaCombustible varchar(50)  NOT NULL,
    anio_fabricacion int  NOT NULL DEFAULT null,
    consumo decimal(6,2)  NOT NULL DEFAULT null,
    tipo varchar(20)  NOT NULL DEFAULT null,
    modelo varchar(20)  NOT NULL DEFAULT null,
    cilindraje_cc decimal(5,1)  NOT NULL DEFAULT null,
    torque_maximo_rpm int  NOT NULL DEFAULT null,
    sistema_ignicion varchar(50)  NOT NULL DEFAULT null,
);

-- Table: Persona_natural
CREATE TABLE Persona_natural (
    id_usuario int  NOT NULL,
    apellido varchar(20)  NOT NULL,
    telefono char(9)  NOT NULL,
    trabajo_empresa char(2)  NOT NULL,
    id_empresa int  NOT NULL,
    CONSTRAINT Persona_natural_pk PRIMARY KEY  (id_usuario)
);

-- Table: Ruta
CREATE TABLE Ruta (
	id_ruta int  NOT NULL PRIMARY KEY IDENTITY(1,1) ,
	cod_ruta AS ('R'+ RIGHT('0000'+ CONVERT(VARCHAR, id_ruta), (4))),
	id_usuario int  NOT NULL,
	nombre_ruta varchar(30) NOT NULL DEFAULT null,
    distancia_km decimal(5,2)  NOT NULL DEFAULT null,
	estado varchar(15) NOT NULL DEFAULT null,
    origen varchar(120)  NOT NULL DEFAULT null,
    destino varchar(120)  NOT NULL DEFAULT null,
    tiempo_trafico_min int  NOT NULL DEFAULT null,
    temperatura_C decimal(3,1)  NOT NULL DEFAULT null,
    humedad int  NOT NULL DEFAULT null,
    presion_atm decimal(3,2)  NOT NULL DEFAULT null,
);

-- Table: Tanqueo
CREATE TABLE Tanqueo (
	id_tanqueo int  NOT NULL PRIMARY KEY IDENTITY(1,1) ,
	cod_tanqueo AS ('T'+ RIGHT('0000'+ CONVERT(VARCHAR, id_tanqueo), (4))),
    id_vehiculo int  NOT NULL DEFAULT null,
    id_ruta int  NOT NULL DEFAULT null,
	lugar_llenado varchar(30) NOT NULL DEFAULT null,
    pago_total decimal(6,2)  NOT NULL DEFAULT null,
    fecha_llenado datetime  NOT NULL DEFAULT null,
    cantidad_llenada_gal decimal(3,1)  NOT NULL DEFAULT null,
    temperatura_C decimal(3,1)  NOT NULL DEFAULT null,
    humedad int  NOT NULL DEFAULT null,
    presion_atm decimal(3,2)  NOT NULL DEFAULT null,
);

-- Table: Usuario
CREATE TABLE Usuario (
	id_usuario int  NOT NULL PRIMARY KEY IDENTITY(1,1) ,
	cod_usuario AS ('U'+ RIGHT('0000'+ CONVERT(VARCHAR, id_usuario), (4))),
    nombre varchar(50)  NOT NULL,
    correo varchar(80)  NOT NULL,
    contrasenia varchar(40)  NOT NULL,
);

-- Table: Vehiculo
CREATE TABLE Vehiculo (
	id_vehiculo int  NOT NULL PRIMARY KEY IDENTITY(1,1) ,
	cod_vehiculo AS ('V'+ RIGHT('0000'+ CONVERT(VARCHAR, id_vehiculo), (4))),
    id_motor int  NOT NULL DEFAULT null,
    marca varchar(20)  NOT NULL DEFAULT null,
    modelo varchar(20)  NOT NULL DEFAULT null,
    capacidad_combusitble_gal decimal(3,1)  NOT NULL DEFAULT null,
    potencia_kw int  NOT NULL DEFAULT null,
	anio_adquision int  NOT NULL DEFAULT null,
    anio_fabricacion int  NOT NULL DEFAULT null,
    velocidad_maxima int  NOT NULL DEFAULT null,
);

-- Table: Asignacion
CREATE TABLE Asignacion (
    id_usuario int  NOT NULL,
	id_vehiculo int  NOT NULL  ,
	CONSTRAINT Asignacion_pk PRIMARY KEY  (id_usuario,id_vehiculo)
);

-- foreign keys
-- Reference: Tanqueo_Ruta (table: Tanqueo)
ALTER TABLE Tanqueo ADD CONSTRAINT Tanqueo_Ruta
    FOREIGN KEY (id_ruta)
    REFERENCES Ruta (id_ruta);

-- Reference: Tanqueo_Vehiculo (table: Tanqueo)
ALTER TABLE Tanqueo ADD CONSTRAINT Tanqueo_Vehiculo
    FOREIGN KEY (id_vehiculo)
    REFERENCES Vehiculo (id_vehiculo);

-- Reference: Persona_natural_Empresa (table: Persona_natural)
ALTER TABLE Persona_natural ADD CONSTRAINT Persona_natural_Empresa
    FOREIGN KEY (id_empresa)
    REFERENCES Empresa (id_empresa);

-- Reference: Persona_natural_Usuario (table: Persona_natural)
ALTER TABLE Persona_natural ADD CONSTRAINT Persona_natural_Usuario
    FOREIGN KEY (id_usuario)
    REFERENCES Usuario (id_usuario);
	
-- Reference: Vehiculo_Motor (table: Vehiculo)
ALTER TABLE Vehiculo ADD CONSTRAINT Vehiculo_Motor
    FOREIGN KEY (id_motor)
    REFERENCES Motor (id_motor);

-- Reference: Asignacion_natural (tablee: Asignacion)
ALTER TABLE Asignacion ADD CONSTRAINT Asignacion_natural
    FOREIGN KEY (id_usuario)
    REFERENCES Persona_natural (id_usuario);

-- Reference: Asignacion_Vehiculo (tablee: Asignacion)
ALTER TABLE Asignacion ADD CONSTRAINT Asignacion_Vehiculo
    FOREIGN KEY (id_vehiculo)
    REFERENCES Vehiculo (id_vehiculo);
	
-- Reference: Ruta_Persona_natural (table: Ruta)
ALTER TABLE Ruta ADD CONSTRAINT Ruta_Persona_natural
    FOREIGN KEY (id_usuario)
    REFERENCES Persona_natural (id_usuario);

-- End of file.


-- Create Users
INSERT INTO USUARIO (nombre,correo,contrasenia) VALUES ('Luis', 'luisgarcia@gmail.com', 'user1');
INSERT INTO USUARIO (nombre,correo,contrasenia) VALUES ('Clara', 'claramartinez@gmail.com', 'user2');
INSERT INTO USUARIO (nombre,correo,contrasenia) VALUES ('Ana', 'anarodriguez@gmail.com', 'user3');
INSERT INTO USUARIO (nombre,correo,contrasenia) VALUES ('Javier', 'javierfernandez@gmail.com', 'user4');
INSERT INTO USUARIO (nombre,correo,contrasenia) VALUES ('Carmen', 'carmenlopez@gmail.com', 'user5');

-- Create Empresa Axuliar
INSERT INTO Empresa (nombre_empresa,RUC) VALUES ('No use', '00000000000');
INSERT INTO Empresa (nombre_empresa,RUC) VALUES ('Transportes Express del Perú S.A.C.', '20123456789');

-- Create Persona_natural
INSERT INTO Persona_natural VALUES ('1', 'García', '987654321', 'no', '1');
INSERT INTO Persona_natural VALUES ('2', 'Martínez', '956789012', 'no', '1');
INSERT INTO Persona_natural VALUES ('3', 'Rodríguez', '984567890', 'si', '2');
INSERT INTO Persona_natural VALUES ('4', 'Fernández', '950123456', 'si', '2');
INSERT INTO Persona_natural VALUES ('5', 'López', '971234567', 'si', '2');

-- Create Motor
INSERT INTO Motor (SitemaCombustible,anio_fabricacion,consumo,tipo,modelo,cilindraje_cc,torque_maximo_rpm,sistema_ignicion) VALUES ('Inyección electrónica multipunto','2012','6.9','Gasolina 90','HR16DE','1598','4400','Electrónico');
INSERT INTO Motor (SitemaCombustible,anio_fabricacion,consumo,tipo,modelo,cilindraje_cc,torque_maximo_rpm,sistema_ignicion) VALUES ('Inyección electrónica multipunto','2015','4.8','Gasolina 95','K10B','998','3500','Electrónico');
INSERT INTO Motor (SitemaCombustible,anio_fabricacion,consumo,tipo,modelo,cilindraje_cc,torque_maximo_rpm,sistema_ignicion) VALUES ('Inyección electrónica multipunto','2020','7.3','Gasolina 95','HR18DE','1798','4000','Por chispa');
INSERT INTO Motor (SitemaCombustible,anio_fabricacion,consumo,tipo,modelo,cilindraje_cc,torque_maximo_rpm,sistema_ignicion) VALUES ('Inyección electrónica multipunto','2019','7.5','Gasolina 95','SKYACTIV-G','1998','4000','Por chispa');
INSERT INTO Motor (SitemaCombustible,anio_fabricacion,consumo,tipo,modelo,cilindraje_cc,torque_maximo_rpm,sistema_ignicion) VALUES ('Inyección electrónica multipunto','2018','6.5','Gasolina 95','K15B','1462','4400','Electrónico');
INSERT INTO Motor (SitemaCombustible,anio_fabricacion,consumo,tipo,modelo,cilindraje_cc,torque_maximo_rpm,sistema_ignicion) VALUES ('Inyección electrónica multipunto','2019','6.0','Gasolina 95','1.0 TSI','999','3500','Electrónico');
INSERT INTO Motor (SitemaCombustible,anio_fabricacion,consumo,tipo,modelo,cilindraje_cc,torque_maximo_rpm,sistema_ignicion) VALUES ('Inyección electrónica multipunto','2015','5.5','Gasolina 95','1.2L Fire EVO','1242','3000','Electrónico');
INSERT INTO Motor (SitemaCombustible,anio_fabricacion,consumo,tipo,modelo,cilindraje_cc,torque_maximo_rpm,sistema_ignicion) VALUES ('Inyección electrónica multipunto','2015','6.4','Gasolina 95','N13B16A','1598','4300','Por chispa');

-- Create Vehiculo
INSERT INTO Vehiculo (id_motor,marca,modelo,capacidad_combusitble_gal,potencia_kw,anio_adquision,anio_fabricacion,velocidad_maxima) VALUES ('1','Nissan','Tiida 1.6 XE','13.7','82','2016','2012','190');
INSERT INTO Vehiculo (id_motor,marca,modelo,capacidad_combusitble_gal,potencia_kw,anio_adquision,anio_fabricacion,velocidad_maxima) VALUES ('2','Suzuki','Celerio 1.0','9.2','50','2018','2015','160');
INSERT INTO Vehiculo (id_motor,marca,modelo,capacidad_combusitble_gal,potencia_kw,anio_adquision,anio_fabricacion,velocidad_maxima) VALUES ('3','Nissan','Sentra 1,8','13.7','104','2021','2020','200');
INSERT INTO Vehiculo (id_motor,marca,modelo,capacidad_combusitble_gal,potencia_kw,anio_adquision,anio_fabricacion,velocidad_maxima) VALUES ('4','Mazda','Mazda 3 Sedan','13.4','121','2019','2019','208');
INSERT INTO Vehiculo (id_motor,marca,modelo,capacidad_combusitble_gal,potencia_kw,anio_adquision,anio_fabricacion,velocidad_maxima) VALUES ('5','Suzuki','Ciaz','11.3','82','2019','2018','185');
INSERT INTO Vehiculo (id_motor,marca,modelo,capacidad_combusitble_gal,potencia_kw,anio_adquision,anio_fabricacion,velocidad_maxima) VALUES ('6','Volkswagen','Polo','10.6','85','2020','2019','187');
INSERT INTO Vehiculo (id_motor,marca,modelo,capacidad_combusitble_gal,potencia_kw,anio_adquision,anio_fabricacion,velocidad_maxima) VALUES ('7','FIAT','500','13.4','51','2017','2015','160');
INSERT INTO Vehiculo (id_motor,marca,modelo,capacidad_combusitble_gal,potencia_kw,anio_adquision,anio_fabricacion,velocidad_maxima) VALUES ('8','BMW','316i','26.4','100','2018','2015','210');

-- Create Asignacion
INSERT INTO Asignacion VALUES ('1','1');
INSERT INTO Asignacion VALUES ('1','2');
INSERT INTO Asignacion VALUES ('1','3');
INSERT INTO Asignacion VALUES ('2','4');
INSERT INTO Asignacion VALUES ('3','5');
INSERT INTO Asignacion VALUES ('4','6');
INSERT INTO Asignacion VALUES ('5','7');
INSERT INTO Asignacion VALUES ('5','8');

-- Create Ruta
INSERT INTO Ruta (id_usuario,nombre_ruta,distancia_km,estado,origen,destino,tiempo_trafico_min,temperatura_C,humedad,presion_atm) VALUES ('1','Casa','5.0','Hecho','Av. Javier Prado Este, San Isidro','Av. Larco, Miraflores','20','22','75','1');
INSERT INTO Ruta (id_usuario,nombre_ruta,distancia_km,estado,origen,destino,tiempo_trafico_min,temperatura_C,humedad,presion_atm) VALUES ('1','Trabajo','15.0','Hecho','Av. La Molina, La Molina','Av. Aviación, San Borja','40','25','60','0.98');
INSERT INTO Ruta (id_usuario,nombre_ruta,distancia_km,estado,origen,destino,tiempo_trafico_min,temperatura_C,humedad,presion_atm) VALUES ('1','Instituto','20.0','Hecho','Av. Oscar R. Benavides, Callao','Av. Larco, Miraflores','60','20','80','1.02');
INSERT INTO Ruta (id_usuario,nombre_ruta,distancia_km,estado,origen,destino,tiempo_trafico_min,temperatura_C,humedad,presion_atm) VALUES ('2','Casa','10.0','Hecho','Av. Grau, Barranco','Av. Primavera, Surco','30','24','70','1');
INSERT INTO Ruta (id_usuario,nombre_ruta,distancia_km,estado,origen,destino,tiempo_trafico_min,temperatura_C,humedad,presion_atm) VALUES ('2','Trabajo','8.0','Hecho','Av. Próceres de la Independencia, San Juan de Lurigancho','Av. Los Quechuas, Santa Anita','45','21','65','1.01');
INSERT INTO Ruta (id_usuario,nombre_ruta,distancia_km,estado,origen,destino,tiempo_trafico_min,temperatura_C,humedad,presion_atm) VALUES ('2','Universidad','10.0','Hecho','Avenida Javier Prado, San Isidro','Avenida Grau, Barranco','25','20','75','1');
INSERT INTO Ruta (id_usuario,nombre_ruta,distancia_km,estado,origen,destino,tiempo_trafico_min,temperatura_C,humedad,presion_atm) VALUES ('3','Ruta 1','8.0','Hecho','Avenida Brasil, Jesús María','Avenida La Marina, San Miguel','20','22','70','1');
INSERT INTO Ruta (id_usuario,nombre_ruta,distancia_km,estado,origen,destino,tiempo_trafico_min,temperatura_C,humedad,presion_atm) VALUES ('3','Ruta 2','15.0','Hecho','Avenida Aviación, San Borja','Avenida Guardia Civil, Chorrillos','40','18','80','1');
INSERT INTO Ruta (id_usuario,nombre_ruta,distancia_km,estado,origen,destino,tiempo_trafico_min,temperatura_C,humedad,presion_atm) VALUES ('3','Ruta 3','12.0','Hecho','Avenida República de Panamá, Miraflores','Avenida Colonial, Callao','35','23','65','1');
INSERT INTO Ruta (id_usuario,nombre_ruta,distancia_km,estado,origen,destino,tiempo_trafico_min,temperatura_C,humedad,presion_atm) VALUES ('4','Ruta 1','7.0','Hecho','Avenida Arequipa, Lince','Avenida Los Proceres, San Juan de Lurigancho','30','19','70','1');
INSERT INTO Ruta (id_usuario,nombre_ruta,distancia_km,estado,origen,destino,tiempo_trafico_min,temperatura_C,humedad,presion_atm) VALUES ('4','Ruta 2','10.0','Hecho','Avenida Javier Prado, San Isidro','Avenida Brasil, Magdalena del Mar','30','23','70','1');
INSERT INTO Ruta (id_usuario,nombre_ruta,distancia_km,estado,origen,destino,tiempo_trafico_min,temperatura_C,humedad,presion_atm) VALUES ('4','Ruta 3','15.0','Hecho','Avenida Los Héroes, San Juan de Lurigancho','Avenida República de Chile, Jesús María','45','19','65','0.98');
INSERT INTO Ruta (id_usuario,nombre_ruta,distancia_km,estado,origen,destino,tiempo_trafico_min,temperatura_C,humedad,presion_atm) VALUES ('5','Ruta 1','7.0','Hecho','Avenida La Marina, Pueblo Libre','Avenida Benavides, Miraflores','25','22','68','1.02');
INSERT INTO Ruta (id_usuario,nombre_ruta,distancia_km,estado,origen,destino,tiempo_trafico_min,temperatura_C,humedad,presion_atm) VALUES ('5','Ruta 2','12.0','Hecho','Avenida Universitaria, San Miguel','Avenida La Paz, Miraflores','40','20','72','0.99');
INSERT INTO Ruta (id_usuario,nombre_ruta,distancia_km,estado,origen,destino,tiempo_trafico_min,temperatura_C,humedad,presion_atm) VALUES ('5','Ruta 3','8.0','Hecho','Avenida Grau, Barranco','Avenida Angamos, Surquillo','30','24','75','1.01');


--Create Tanqueo
INSERT INTO Tanqueo (id_vehiculo,id_ruta,lugar_llenado,pago_total,fecha_llenado,cantidad_llenada_gal,temperatura_C,humedad,presion_atm) VALUES ('1','1','Petroperú','40.0','2023-05-02','2','20','75','1');
INSERT INTO Tanqueo (id_vehiculo,id_ruta,lugar_llenado,pago_total,fecha_llenado,cantidad_llenada_gal,temperatura_C,humedad,presion_atm) VALUES ('1','3','Repsol','39.5','2023-05-03','2','25','60','0.98');
INSERT INTO Tanqueo (id_vehiculo,id_ruta,lugar_llenado,pago_total,fecha_llenado,cantidad_llenada_gal,temperatura_C,humedad,presion_atm) VALUES ('1','1','Primax','51.2','2023-05-15','2','21','67','1.01');
INSERT INTO Tanqueo (id_vehiculo,id_ruta,lugar_llenado,pago_total,fecha_llenado,cantidad_llenada_gal,temperatura_C,humedad,presion_atm) VALUES ('2','2','Petroperú','25.0','2023-04-29','2','20','73','1');
INSERT INTO Tanqueo (id_vehiculo,id_ruta,lugar_llenado,pago_total,fecha_llenado,cantidad_llenada_gal,temperatura_C,humedad,presion_atm) VALUES ('2','3','Pecsa','35.3','2023-04-30','4','20','70','1');
INSERT INTO Tanqueo (id_vehiculo,id_ruta,lugar_llenado,pago_total,fecha_llenado,cantidad_llenada_gal,temperatura_C,humedad,presion_atm) VALUES ('2','1','Repsol','26.5','2023-05-15','3','22','70','1');
INSERT INTO Tanqueo (id_vehiculo,id_ruta,lugar_llenado,pago_total,fecha_llenado,cantidad_llenada_gal,temperatura_C,humedad,presion_atm) VALUES ('3','3','Primax','35.6','2023-03-31','5','26','60','0.98');
INSERT INTO Tanqueo (id_vehiculo,id_ruta,lugar_llenado,pago_total,fecha_llenado,cantidad_llenada_gal,temperatura_C,humedad,presion_atm) VALUES ('3','1','Petroperú','20.2','2023-04-30','2','24','65','1');
INSERT INTO Tanqueo (id_vehiculo,id_ruta,lugar_llenado,pago_total,fecha_llenado,cantidad_llenada_gal,temperatura_C,humedad,presion_atm) VALUES ('3','2','Repsol','35.2','2023-05-15','3','25','60','0.98');
INSERT INTO Tanqueo (id_vehiculo,id_ruta,lugar_llenado,pago_total,fecha_llenado,cantidad_llenada_gal,temperatura_C,humedad,presion_atm) VALUES ('4','6','Primax','31.6','2023-04-26','5','20','72','0.99');
INSERT INTO Tanqueo (id_vehiculo,id_ruta,lugar_llenado,pago_total,fecha_llenado,cantidad_llenada_gal,temperatura_C,humedad,presion_atm) VALUES ('4','5','Pecsa','38.6','2023-05-09','3','19','66','0.98');
INSERT INTO Tanqueo (id_vehiculo,id_ruta,lugar_llenado,pago_total,fecha_llenado,cantidad_llenada_gal,temperatura_C,humedad,presion_atm) VALUES ('4','4','Repsol','49.5','2023-05-15','4','18','79','1');
INSERT INTO Tanqueo (id_vehiculo,id_ruta,lugar_llenado,pago_total,fecha_llenado,cantidad_llenada_gal,temperatura_C,humedad,presion_atm) VALUES ('5','7','Petroperú','39.6','2023-04-29','4','24','72','0.99');
INSERT INTO Tanqueo (id_vehiculo,id_ruta,lugar_llenado,pago_total,fecha_llenado,cantidad_llenada_gal,temperatura_C,humedad,presion_atm) VALUES ('5','8','Pecsa','25.6','2023-05-08','2','24','70','1');
INSERT INTO Tanqueo (id_vehiculo,id_ruta,lugar_llenado,pago_total,fecha_llenado,cantidad_llenada_gal,temperatura_C,humedad,presion_atm) VALUES ('5','9','Repsol','40.1','2023-05-15','4','21','72','0.99');
INSERT INTO Tanqueo (id_vehiculo,id_ruta,lugar_llenado,pago_total,fecha_llenado,cantidad_llenada_gal,temperatura_C,humedad,presion_atm) VALUES ('6','10','Primax','36.9','2023-05-02','2','25','61','0.98');
INSERT INTO Tanqueo (id_vehiculo,id_ruta,lugar_llenado,pago_total,fecha_llenado,cantidad_llenada_gal,temperatura_C,humedad,presion_atm) VALUES ('6','11','Petroperú','48.5','2023-05-10','2','20','65','0.98');
INSERT INTO Tanqueo (id_vehiculo,id_ruta,lugar_llenado,pago_total,fecha_llenado,cantidad_llenada_gal,temperatura_C,humedad,presion_atm) VALUES ('6','12','Pecsa','37.2','2023-05-15','3','25','60','0.98');
INSERT INTO Tanqueo (id_vehiculo,id_ruta,lugar_llenado,pago_total,fecha_llenado,cantidad_llenada_gal,temperatura_C,humedad,presion_atm) VALUES ('7','13','Petroperú','32.6','2023-04-29','3','22','75','1.01');
INSERT INTO Tanqueo (id_vehiculo,id_ruta,lugar_llenado,pago_total,fecha_llenado,cantidad_llenada_gal,temperatura_C,humedad,presion_atm) VALUES ('7','14','Repsol','50.1','2023-05-05','5','19','80','1');
INSERT INTO Tanqueo (id_vehiculo,id_ruta,lugar_llenado,pago_total,fecha_llenado,cantidad_llenada_gal,temperatura_C,humedad,presion_atm) VALUES ('7','15','Petroperú','23.3','2023-05-15','2','25','71','1');
INSERT INTO Tanqueo (id_vehiculo,id_ruta,lugar_llenado,pago_total,fecha_llenado,cantidad_llenada_gal,temperatura_C,humedad,presion_atm) VALUES ('8','13','Primax','36.4','2023-05-01','5','20','79','1.01');
INSERT INTO Tanqueo (id_vehiculo,id_ruta,lugar_llenado,pago_total,fecha_llenado,cantidad_llenada_gal,temperatura_C,humedad,presion_atm) VALUES ('8','15','Repsol','24.3','2023-05-09','2','22','75','1');
INSERT INTO Tanqueo (id_vehiculo,id_ruta,lugar_llenado,pago_total,fecha_llenado,cantidad_llenada_gal,temperatura_C,humedad,presion_atm) VALUES ('8','14','Petroperú','37.6','2023-05-15','6','23','68','1.02');
