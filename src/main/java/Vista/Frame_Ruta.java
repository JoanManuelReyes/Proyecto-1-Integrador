/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Controlador.Controlador_Ruta;
import Modelo.Ruta;
import Modelo.Vehiculo;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Arice
 */
public class Frame_Ruta extends javax.swing.JFrame {

    public static String tipo;
    public static String correo;
    public static String id_usuario;
    public static String id_empresa;
    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
    
    Calendar  fecha_actual=new GregorianCalendar();
    
    SimpleDateFormat sdf = new SimpleDateFormat("EEEE", new Locale("es", "ES"));
    DateFormatSymbols dfs = new DateFormatSymbols(new Locale("es"));
    SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm a");
    
    Controlador_Ruta controlador=new Controlador_Ruta();
    
    DefaultTableModel dt=new DefaultTableModel();
    /**
     * Creates new form Controlador_Ruta
     */
    public Frame_Ruta() {
        initComponents();
        setIconImage(getIconImage());
        this.setLocationRelativeTo(null);
        this.setExtendedState(6);
        dt = (DefaultTableModel)tablaViajes.getModel();
        jDateChooserFecha.setCalendar(fecha_actual);
        fecha_actual();
        cargar_rutas();
        cargar_vehiculos();
        cargar_origen();
        cargar_destino();
        cargar_vehiculos_proyectar();
        cargar_origen_añadirRuta();
        cargar_destino_añadirRuta();
        llenar_tabla();
        llenar_combobox_filtro();
    }
     @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/Frame.png"));
        return retValue;
    }
    
    private void llenar_combobox_filtro(){
        cbFiltroTabla.removeAllItems();
        cbFiltroTabla.addItem("Filtro");
        cbFiltroTabla.addItem("Menor gasto");
        cbFiltroTabla.addItem("Mayor gasto");
    };
    
    private void llenar_tabla(){
        if(tipo.equals("persona")){
            dt.setRowCount(0);
            List<Vehiculo> datos=controlador.llenartablaPersona(id_usuario);
            for (Vehiculo vehiculo : datos) {
                String nombreVehiculo=vehiculo.getMarca()+" "+vehiculo.getModelo();
                Object v[]={vehiculo.getTanqueo().getId_tanqueo(),vehiculo.getRuta().getNombre(),vehiculo.getUsuario().getNombre(),nombreVehiculo,"S/."+vehiculo.getTanqueo().getPagoTotal()};
                dt.addRow(v);
            }
        }else{
            dt.setRowCount(0);
            List<Vehiculo> datos=controlador.llenartablaEmpresa(id_empresa);
            for (Vehiculo vehiculo : datos) {
                String nombreVehiculo=vehiculo.getMarca()+" "+vehiculo.getModelo();
                Object v[]={vehiculo.getTanqueo().getId_tanqueo(),vehiculo.getRuta().getNombre(),vehiculo.getUsuario().getNombre(),nombreVehiculo,"S/."+vehiculo.getTanqueo().getPagoTotal()};
                dt.addRow(v);
            }
        }
    }
    
    private void fecha_actual(){
        String diaHoy=sdf.format(fecha_actual.getTime());
        diaHoy = diaHoy.substring(0, 1).toUpperCase() + diaHoy.substring(1).toLowerCase();
        int mes = fecha_actual.get(Calendar.MONTH);
        String[] meses = dfs.getMonths();
        String mesActual = meses[mes];
        
        txtFechaActual.setText(diaHoy+", "+fecha_actual.get(Calendar.DAY_OF_MONTH)+" de "+mesActual+", "+fecha_actual.get(Calendar.YEAR)+", "+formatoHora.format(fecha_actual.getTime()));
    }
    
    private void cargar_rutas(){
        cbRutaComenzar.removeAllItems();
        cbRutaComenzar.addItem("Ruta");
        for (Ruta ruta : controlador.llenarrutas(id_usuario)) {
            cbRutaComenzar.addItem(ruta.getNombre()); // Agregar el atributo deseado al modelo
        }
    }
    
    private void cargar_vehiculos(){
        cbVehiculoComenzar.removeAllItems();
        cbVehiculoComenzar.addItem("Vehículo");
        for (Vehiculo vehiculo : controlador.nombres_vehiculo(correo)) {
            cbVehiculoComenzar.addItem(vehiculo.getMarca()+" "+vehiculo.getModelo()); // Agregar el atributo deseado al modelo
        }
    }
    
    private void cargar_origen(){
        cbInicioProyeccion.removeAllItems();
        cbInicioProyeccion.addItem("Incio");
        cbInicioProyeccion.addItem("Ancón");
        cbInicioProyeccion.addItem("Ate");
        cbInicioProyeccion.addItem("Barranco");
        cbInicioProyeccion.addItem("Breña");
        cbInicioProyeccion.addItem("Carabayllo");
        cbInicioProyeccion.addItem("Cercado de Lima");
        cbInicioProyeccion.addItem("San Juan de Lurigancho");
        cbInicioProyeccion.addItem("Chorrillos");
        cbInicioProyeccion.addItem("Cieneguilla");
        cbInicioProyeccion.addItem("Comas");
        cbInicioProyeccion.addItem("El Austino");
        cbInicioProyeccion.addItem("San borja");
        cbInicioProyeccion.addItem("San isidro");
        cbInicioProyeccion.addItem("Independencia");
        cbInicioProyeccion.addItem("Jesús María");
        cbInicioProyeccion.addItem("La molina");
        cbInicioProyeccion.addItem("Santa Anita");
    }
    
    private void cargar_destino(){
        cbDestinoProyeccion.removeAllItems();
        cbDestinoProyeccion.addItem("Destino");
        cbDestinoProyeccion.addItem("Ancón");
        cbDestinoProyeccion.addItem("Ate");
        cbDestinoProyeccion.addItem("Barranco");
        cbDestinoProyeccion.addItem("Breña");
        cbDestinoProyeccion.addItem("Carabayllo");
        cbDestinoProyeccion.addItem("Cercado de Lima");
        cbDestinoProyeccion.addItem("San Juan de Lurigancho");
        cbDestinoProyeccion.addItem("Chorrillos");
        cbDestinoProyeccion.addItem("Cieneguilla");
        cbDestinoProyeccion.addItem("Comas");
        cbDestinoProyeccion.addItem("El Austino");
        cbDestinoProyeccion.addItem("San borja");
        cbDestinoProyeccion.addItem("San isidro");
        cbDestinoProyeccion.addItem("Independencia");
        cbDestinoProyeccion.addItem("Jesús María");
        cbDestinoProyeccion.addItem("La molina");
        cbDestinoProyeccion.addItem("Santa Anita");
    }
    
    private void cargar_vehiculos_proyectar(){
        cbVehiculoProyectar.removeAllItems();
        cbVehiculoProyectar.addItem("Vehículo");
        for (Vehiculo vehiculo : controlador.nombres_vehiculo(correo)) {
            cbVehiculoProyectar.addItem(vehiculo.getMarca()+" "+vehiculo.getModelo());
        }
    }
    
        private void cargar_origen_añadirRuta(){
        cbInicioAñadir.removeAllItems();
        cbInicioAñadir.addItem("Incio");
        cbInicioAñadir.addItem("Ancón");
        cbInicioAñadir.addItem("Ate");
        cbInicioAñadir.addItem("Barranco");
        cbInicioAñadir.addItem("Breña");
        cbInicioAñadir.addItem("Carabayllo");
        cbInicioAñadir.addItem("Cercado de Lima");
        cbInicioAñadir.addItem("San Juan de Lurigancho");
        cbInicioAñadir.addItem("Chorrillos");
        cbInicioAñadir.addItem("Cieneguilla");
        cbInicioAñadir.addItem("Comas");
        cbInicioAñadir.addItem("El Austino");
        cbInicioAñadir.addItem("San borja");
        cbInicioAñadir.addItem("San isidro");
        cbInicioAñadir.addItem("Independencia");
        cbInicioAñadir.addItem("Jesús María");
        cbInicioAñadir.addItem("La molina");
        cbInicioAñadir.addItem("Santa Anita");
    }
    
    private void cargar_destino_añadirRuta(){
        cbDestinoAñadir.removeAllItems();
        cbDestinoAñadir.addItem("Destino");
        cbDestinoAñadir.addItem("Ancón");
        cbDestinoAñadir.addItem("Ate");
        cbDestinoAñadir.addItem("Barranco");
        cbDestinoAñadir.addItem("Breña");
        cbDestinoAñadir.addItem("Carabayllo");
        cbDestinoAñadir.addItem("Cercado de Lima");
        cbDestinoAñadir.addItem("San Juan de Lurigancho");
        cbDestinoAñadir.addItem("Chorrillos");
        cbDestinoAñadir.addItem("Cieneguilla");
        cbDestinoAñadir.addItem("Comas");
        cbDestinoAñadir.addItem("El Austino");
        cbDestinoAñadir.addItem("San borja");
        cbDestinoAñadir.addItem("San isidro");
        cbDestinoAñadir.addItem("Independencia");
        cbDestinoAñadir.addItem("Jesús María");
        cbDestinoAñadir.addItem("La molina");
        cbDestinoAñadir.addItem("Santa Anita");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        panelRound18 = new Vista.PanelRound();
        cbInicioProyeccion = new javax.swing.JComboBox<>();
        jLabel44 = new javax.swing.JLabel();
        panelRound17 = new Vista.PanelRound();
        cbDestinoAñadir = new javax.swing.JComboBox<>();
        jLabel43 = new javax.swing.JLabel();
        panelRound19 = new Vista.PanelRound();
        cbDestinoProyeccion = new javax.swing.JComboBox<>();
        jLabel45 = new javax.swing.JLabel();
        panelRound20 = new Vista.PanelRound();
        cbVehiculoProyectar = new javax.swing.JComboBox<>();
        jLabel46 = new javax.swing.JLabel();
        panelRound16 = new Vista.PanelRound();
        cbInicioAñadir = new javax.swing.JComboBox<>();
        jLabel42 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jDateChooserFecha = new com.toedter.calendar.JDateChooser();
        panelRound15 = new Vista.PanelRound();
        cbFiltroTabla = new javax.swing.JComboBox<>();
        jLabel41 = new javax.swing.JLabel();
        txtNombreRuta = new javax.swing.JTextField();
        txtDistancia = new javax.swing.JTextField();
        txtTiempoTrafico = new javax.swing.JTextField();
        txtTiempoTraficoAñadir = new javax.swing.JTextField();
        txtDistanciaAñadir = new javax.swing.JTextField();
        panelRound12 = new Vista.PanelRound();
        btnProyectar = new javax.swing.JLabel();
        panelRound10 = new Vista.PanelRound();
        btnAñadirRuta = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        panelRound8 = new Vista.PanelRound();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        panelRound7 = new Vista.PanelRound();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        panelRound9 = new Vista.PanelRound();
        btnReportes = new javax.swing.JLabel();
        btnVehiculo = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btnTanqueos = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        btnDashboard = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaViajes = new javax.swing.JTable();
        jLabel35 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        txtFechaActual = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        panelRound21 = new Vista.PanelRound();
        cbRutaComenzar = new javax.swing.JComboBox<>();
        jLabel47 = new javax.swing.JLabel();
        panelRound23 = new Vista.PanelRound();
        cbVehiculoComenzar = new javax.swing.JComboBox<>();
        jLabel49 = new javax.swing.JLabel();
        panelRound13 = new Vista.PanelRound();
        btnComenzar = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(6);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setText("Proyeccion de ruta");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 150, -1, -1));

        panelRound18.setBackground(new java.awt.Color(255, 255, 255));
        panelRound18.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelRound18.setRoundBottomLeft(10);
        panelRound18.setRoundBottomRight(10);
        panelRound18.setRoundTopLeft(10);
        panelRound18.setRoundTopRight(10);

        cbInicioProyeccion.setEditable(true);
        cbInicioProyeccion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Inicio", "Item 2", "Item 3", "Item 4" }));
        cbInicioProyeccion.setToolTipText("");
        cbInicioProyeccion.setBorder(null);
        cbInicioProyeccion.setFocusable(false);
        cbInicioProyeccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbInicioProyeccionActionPerformed(evt);
            }
        });

        jLabel44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icon _pin alt_.png"))); // NOI18N

        javax.swing.GroupLayout panelRound18Layout = new javax.swing.GroupLayout(panelRound18);
        panelRound18.setLayout(panelRound18Layout);
        panelRound18Layout.setHorizontalGroup(
            panelRound18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound18Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel44)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbInicioProyeccion, 0, 137, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );
        panelRound18Layout.setVerticalGroup(
            panelRound18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound18Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(panelRound18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel44)
                    .addComponent(cbInicioProyeccion))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(panelRound18, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 180, 190, 40));

        panelRound17.setBackground(new java.awt.Color(255, 255, 255));
        panelRound17.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelRound17.setRoundBottomLeft(10);
        panelRound17.setRoundBottomRight(10);
        panelRound17.setRoundTopLeft(10);
        panelRound17.setRoundTopRight(10);

        cbDestinoAñadir.setEditable(true);
        cbDestinoAñadir.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Destino", "Item 2", "Item 3", "Item 4" }));
        cbDestinoAñadir.setToolTipText("");
        cbDestinoAñadir.setBorder(null);
        cbDestinoAñadir.setFocusable(false);
        cbDestinoAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDestinoAñadirActionPerformed(evt);
            }
        });

        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icon _pin alt_.png"))); // NOI18N

        javax.swing.GroupLayout panelRound17Layout = new javax.swing.GroupLayout(panelRound17);
        panelRound17.setLayout(panelRound17Layout);
        panelRound17Layout.setHorizontalGroup(
            panelRound17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbDestinoAñadir, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelRound17Layout.setVerticalGroup(
            panelRound17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound17Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(panelRound17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel43, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbDestinoAñadir))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(panelRound17, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 300, -1, 40));

        panelRound19.setBackground(new java.awt.Color(255, 255, 255));
        panelRound19.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelRound19.setRoundBottomLeft(10);
        panelRound19.setRoundBottomRight(10);
        panelRound19.setRoundTopLeft(10);
        panelRound19.setRoundTopRight(10);

        cbDestinoProyeccion.setEditable(true);
        cbDestinoProyeccion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Destino", "Item 2", "Item 3", "Item 4" }));
        cbDestinoProyeccion.setToolTipText("");
        cbDestinoProyeccion.setBorder(null);
        cbDestinoProyeccion.setFocusable(false);
        cbDestinoProyeccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDestinoProyeccionActionPerformed(evt);
            }
        });

        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icon _pin alt_.png"))); // NOI18N

        javax.swing.GroupLayout panelRound19Layout = new javax.swing.GroupLayout(panelRound19);
        panelRound19.setLayout(panelRound19Layout);
        panelRound19Layout.setHorizontalGroup(
            panelRound19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound19Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel45)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbDestinoProyeccion, 0, 108, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );
        panelRound19Layout.setVerticalGroup(
            panelRound19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound19Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(panelRound19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel45)
                    .addComponent(cbDestinoProyeccion))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(panelRound19, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 180, 160, 40));

        panelRound20.setBackground(new java.awt.Color(255, 255, 255));
        panelRound20.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelRound20.setRoundBottomLeft(10);
        panelRound20.setRoundBottomRight(10);
        panelRound20.setRoundTopLeft(10);
        panelRound20.setRoundTopRight(10);

        cbVehiculoProyectar.setEditable(true);
        cbVehiculoProyectar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vehiculo", "Item 2", "Item 3", "Item 4" }));
        cbVehiculoProyectar.setToolTipText("");
        cbVehiculoProyectar.setBorder(null);
        cbVehiculoProyectar.setFocusable(false);
        cbVehiculoProyectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbVehiculoProyectarActionPerformed(evt);
            }
        });

        jLabel46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/bx_car.png"))); // NOI18N

        javax.swing.GroupLayout panelRound20Layout = new javax.swing.GroupLayout(panelRound20);
        panelRound20.setLayout(panelRound20Layout);
        panelRound20Layout.setHorizontalGroup(
            panelRound20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound20Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jLabel46)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbVehiculoProyectar, 0, 117, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );
        panelRound20Layout.setVerticalGroup(
            panelRound20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound20Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(panelRound20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel46)
                    .addComponent(cbVehiculoProyectar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(panelRound20, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 180, 180, 40));

        panelRound16.setBackground(new java.awt.Color(255, 255, 255));
        panelRound16.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelRound16.setRoundBottomLeft(10);
        panelRound16.setRoundBottomRight(10);
        panelRound16.setRoundTopLeft(10);
        panelRound16.setRoundTopRight(10);

        cbInicioAñadir.setEditable(true);
        cbInicioAñadir.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Inicio", "Item 2", "Item 3", "Item 4" }));
        cbInicioAñadir.setToolTipText("");
        cbInicioAñadir.setBorder(null);
        cbInicioAñadir.setFocusable(false);
        cbInicioAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbInicioAñadirActionPerformed(evt);
            }
        });

        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icon _pin alt_.png"))); // NOI18N

        javax.swing.GroupLayout panelRound16Layout = new javax.swing.GroupLayout(panelRound16);
        panelRound16.setLayout(panelRound16Layout);
        panelRound16Layout.setHorizontalGroup(
            panelRound16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbInicioAñadir, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        panelRound16Layout.setVerticalGroup(
            panelRound16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound16Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(panelRound16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel42)
                    .addComponent(cbInicioAñadir))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(panelRound16, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 300, 170, 40));

        jLabel15.setText("Anadir ruta");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 270, -1, -1));

        jDateChooserFecha.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.add(jDateChooserFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 60, 220, 40));

        panelRound15.setBackground(new java.awt.Color(255, 255, 255));
        panelRound15.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelRound15.setRoundBottomLeft(10);
        panelRound15.setRoundBottomRight(10);
        panelRound15.setRoundTopLeft(10);
        panelRound15.setRoundTopRight(10);

        cbFiltroTabla.setEditable(true);
        cbFiltroTabla.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Filtro", "Ahorros", "Gatos" }));
        cbFiltroTabla.setBorder(null);
        cbFiltroTabla.setFocusable(false);
        cbFiltroTabla.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbFiltroTablaItemStateChanged(evt);
            }
        });
        cbFiltroTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFiltroTablaActionPerformed(evt);
            }
        });

        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Filter.png"))); // NOI18N

        javax.swing.GroupLayout panelRound15Layout = new javax.swing.GroupLayout(panelRound15);
        panelRound15.setLayout(panelRound15Layout);
        panelRound15Layout.setHorizontalGroup(
            panelRound15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound15Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbFiltroTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        panelRound15Layout.setVerticalGroup(
            panelRound15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound15Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(panelRound15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound15Layout.createSequentialGroup()
                        .addComponent(cbFiltroTabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.add(panelRound15, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 400, 160, 30));

        txtNombreRuta.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(txtNombreRuta, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 290, 230, 40));

        txtDistancia.setText("Distancia (Km)");
        txtDistancia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtDistanciaMousePressed(evt);
            }
        });
        txtDistancia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDistanciaKeyTyped(evt);
            }
        });
        jPanel2.add(txtDistancia, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 150, 160, -1));

        txtTiempoTrafico.setText("Tiempo Trafico (min)");
        txtTiempoTrafico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtTiempoTraficoMousePressed(evt);
            }
        });
        txtTiempoTrafico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTiempoTraficoKeyTyped(evt);
            }
        });
        jPanel2.add(txtTiempoTrafico, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 150, 180, -1));

        txtTiempoTraficoAñadir.setText("Tiempo Trafico (min)");
        txtTiempoTraficoAñadir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtTiempoTraficoAñadirMousePressed(evt);
            }
        });
        txtTiempoTraficoAñadir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTiempoTraficoAñadirKeyTyped(evt);
            }
        });
        jPanel2.add(txtTiempoTraficoAñadir, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 270, 170, -1));

        txtDistanciaAñadir.setText("Distancia (Km)");
        txtDistanciaAñadir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtDistanciaAñadirMousePressed(evt);
            }
        });
        txtDistanciaAñadir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDistanciaAñadirKeyTyped(evt);
            }
        });
        jPanel2.add(txtDistanciaAñadir, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 270, 170, -1));

        panelRound12.setBackground(new java.awt.Color(0, 106, 255));
        panelRound12.setRoundBottomLeft(10);
        panelRound12.setRoundBottomRight(10);
        panelRound12.setRoundTopLeft(10);
        panelRound12.setRoundTopRight(10);

        btnProyectar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnProyectar.setForeground(new java.awt.Color(242, 242, 242));
        btnProyectar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnProyectar.setText("Proyectar");
        btnProyectar.setAlignmentY(0.0F);
        btnProyectar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProyectar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProyectarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelRound12Layout = new javax.swing.GroupLayout(panelRound12);
        panelRound12.setLayout(panelRound12Layout);
        panelRound12Layout.setHorizontalGroup(
            panelRound12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnProyectar, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
        );
        panelRound12Layout.setVerticalGroup(
            panelRound12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnProyectar, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel2.add(panelRound12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 180, 118, 40));

        panelRound10.setBackground(new java.awt.Color(0, 106, 255));
        panelRound10.setRoundBottomLeft(10);
        panelRound10.setRoundBottomRight(10);
        panelRound10.setRoundTopLeft(10);
        panelRound10.setRoundTopRight(10);

        btnAñadirRuta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAñadirRuta.setForeground(new java.awt.Color(242, 242, 242));
        btnAñadirRuta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAñadirRuta.setText("Anadir");
        btnAñadirRuta.setAlignmentY(0.0F);
        btnAñadirRuta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAñadirRuta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAñadirRutaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelRound10Layout = new javax.swing.GroupLayout(panelRound10);
        panelRound10.setLayout(panelRound10Layout);
        panelRound10Layout.setHorizontalGroup(
            panelRound10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAñadirRuta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
        );
        panelRound10Layout.setVerticalGroup(
            panelRound10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAñadirRuta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel2.add(panelRound10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 290, 118, 40));

        jPanel3.setBackground(new java.awt.Color(26, 25, 25));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(81, 35, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Group 10.png"))); // NOI18N
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        panelRound8.setBackground(new java.awt.Color(47, 47, 47));
        panelRound8.setForeground(new java.awt.Color(255, 255, 255));
        panelRound8.setRoundBottomLeft(10);
        panelRound8.setRoundBottomRight(10);
        panelRound8.setRoundTopLeft(10);
        panelRound8.setRoundTopRight(10);
        panelRound8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Logout.png"))); // NOI18N
        jLabel7.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        panelRound8.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 15, 20, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/placeholder.png"))); // NOI18N
        panelRound8.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jPanel3.add(panelRound8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 610, 150, 50));

        panelRound7.setBackground(new java.awt.Color(0, 106, 255));
        panelRound7.setForeground(new java.awt.Color(255, 255, 255));
        panelRound7.setRoundBottomLeft(10);
        panelRound7.setRoundBottomRight(10);
        panelRound7.setRoundTopLeft(10);
        panelRound7.setRoundTopRight(10);
        panelRound7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Rutas");
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        panelRound7.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 13, 80, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Booking.png"))); // NOI18N
        panelRound7.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 13, -1, -1));

        panelRound9.setBackground(new java.awt.Color(255, 255, 255));
        panelRound9.setForeground(new java.awt.Color(255, 255, 255));
        panelRound9.setRoundBottomLeft(5);
        panelRound9.setRoundBottomRight(5);
        panelRound9.setRoundTopLeft(5);
        panelRound9.setRoundTopRight(5);
        panelRound9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelRound7.add(panelRound9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 5, 5, 40));

        jPanel3.add(panelRound7, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 240, 180, 50));

        btnReportes.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnReportes.setForeground(new java.awt.Color(255, 255, 255));
        btnReportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu (3).png"))); // NOI18N
        btnReportes.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnReportes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReportes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReportesMouseClicked(evt);
            }
        });
        jPanel3.add(btnReportes, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 450, 180, -1));

        btnVehiculo.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnVehiculo.setForeground(new java.awt.Color(255, 255, 255));
        btnVehiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu (1).png"))); // NOI18N
        btnVehiculo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnVehiculo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVehiculo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVehiculoMouseClicked(evt);
            }
        });
        jPanel3.add(btnVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 180, 180, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Header.png"))); // NOI18N
        jLabel13.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 330, 180, -1));

        btnTanqueos.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnTanqueos.setForeground(new java.awt.Color(255, 255, 255));
        btnTanqueos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu (2).png"))); // NOI18N
        btnTanqueos.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnTanqueos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTanqueos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTanqueosMouseClicked(evt);
            }
        });
        jPanel3.add(btnTanqueos, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 390, 180, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Split line.png"))); // NOI18N
        jLabel20.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 190, -1));

        btnDashboard.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnDashboard.setForeground(new java.awt.Color(255, 255, 255));
        btnDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu.png"))); // NOI18N
        btnDashboard.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnDashboard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDashboardMouseClicked(evt);
            }
        });
        jPanel3.add(btnDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 120, 180, -1));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 230, 700));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tablaViajes.setAutoCreateRowSorter(true);
        tablaViajes.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tablaViajes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No.", "Ruta", "Conductor", "Vehiculo", "Gasto"
            }
        ));
        tablaViajes.setFillsViewportHeight(true);
        tablaViajes.setFocusCycleRoot(true);
        tablaViajes.setGridColor(new java.awt.Color(255, 255, 255));
        tablaViajes.setSelectionBackground(new java.awt.Color(102, 153, 255));
        tablaViajes.setShowVerticalLines(true);
        jScrollPane1.setViewportView(tablaViajes);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 440, 740, 210));

        jLabel35.setText("Rutas");
        jPanel2.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 410, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Live Car Status.png"))); // NOI18N
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 370, -1, -1));

        jPanel4.setBackground(new java.awt.Color(248, 247, 241));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel32.setForeground(new java.awt.Color(82, 82, 86));
        jLabel32.setText("Viajes recientes");
        jPanel4.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        txtFechaActual.setForeground(new java.awt.Color(163, 163, 163));
        txtFechaActual.setText("Lunes,  22 de Mayo, 2023, 11:30 AM");
        jPanel4.add(txtFechaActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, 270, 700));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Car Availablity.png"))); // NOI18N
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 120, -1, -1));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Car Availablity.png"))); // NOI18N
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 240, -1, -1));

        jLabel17.setText("Comenzar viaje");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 30, -1, -1));

        panelRound21.setBackground(new java.awt.Color(255, 255, 255));
        panelRound21.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelRound21.setRoundBottomLeft(10);
        panelRound21.setRoundBottomRight(10);
        panelRound21.setRoundTopLeft(10);
        panelRound21.setRoundTopRight(10);

        cbRutaComenzar.setEditable(true);
        cbRutaComenzar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ruta", "Item 2", "Item 3", "Item 4" }));
        cbRutaComenzar.setToolTipText("");
        cbRutaComenzar.setBorder(null);
        cbRutaComenzar.setFocusable(false);
        cbRutaComenzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbRutaComenzarActionPerformed(evt);
            }
        });

        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icon _pin alt_.png"))); // NOI18N

        javax.swing.GroupLayout panelRound21Layout = new javax.swing.GroupLayout(panelRound21);
        panelRound21.setLayout(panelRound21Layout);
        panelRound21Layout.setHorizontalGroup(
            panelRound21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound21Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel47)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbRutaComenzar, 0, 108, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );
        panelRound21Layout.setVerticalGroup(
            panelRound21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound21Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(panelRound21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel47)
                    .addComponent(cbRutaComenzar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(panelRound21, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 60, 160, 40));

        panelRound23.setBackground(new java.awt.Color(255, 255, 255));
        panelRound23.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelRound23.setRoundBottomLeft(10);
        panelRound23.setRoundBottomRight(10);
        panelRound23.setRoundTopLeft(10);
        panelRound23.setRoundTopRight(10);

        cbVehiculoComenzar.setEditable(true);
        cbVehiculoComenzar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vehiculo", "Item 2", "Item 3", "Item 4" }));
        cbVehiculoComenzar.setToolTipText("");
        cbVehiculoComenzar.setBorder(null);
        cbVehiculoComenzar.setFocusable(false);
        cbVehiculoComenzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbVehiculoComenzarActionPerformed(evt);
            }
        });

        jLabel49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/bx_car.png"))); // NOI18N

        javax.swing.GroupLayout panelRound23Layout = new javax.swing.GroupLayout(panelRound23);
        panelRound23.setLayout(panelRound23Layout);
        panelRound23Layout.setHorizontalGroup(
            panelRound23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound23Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel49)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbVehiculoComenzar, 0, 108, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );
        panelRound23Layout.setVerticalGroup(
            panelRound23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound23Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(panelRound23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel49)
                    .addComponent(cbVehiculoComenzar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(panelRound23, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 60, -1, 40));

        panelRound13.setBackground(new java.awt.Color(0, 106, 255));
        panelRound13.setRoundBottomLeft(10);
        panelRound13.setRoundBottomRight(10);
        panelRound13.setRoundTopLeft(10);
        panelRound13.setRoundTopRight(10);

        btnComenzar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnComenzar.setForeground(new java.awt.Color(242, 242, 242));
        btnComenzar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnComenzar.setText("Comenzar");
        btnComenzar.setAlignmentY(0.0F);
        btnComenzar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnComenzar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnComenzarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelRound13Layout = new javax.swing.GroupLayout(panelRound13);
        panelRound13.setLayout(panelRound13Layout);
        panelRound13Layout.setHorizontalGroup(
            panelRound13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnComenzar, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
        );
        panelRound13Layout.setVerticalGroup(
            panelRound13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnComenzar, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel2.add(panelRound13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 60, 118, 40));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Car Availablity.png"))); // NOI18N
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbInicioProyeccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbInicioProyeccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbInicioProyeccionActionPerformed

    private void cbDestinoAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDestinoAñadirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbDestinoAñadirActionPerformed

    private void cbDestinoProyeccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDestinoProyeccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbDestinoProyeccionActionPerformed

    private void cbVehiculoProyectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbVehiculoProyectarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbVehiculoProyectarActionPerformed

    private void cbInicioAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbInicioAñadirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbInicioAñadirActionPerformed

    private void cbFiltroTablaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbFiltroTablaItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            // Llamar a la función deseada
            if(tipo.equals("persona")){
                cbFiltroCambioPersona();
            }else{
                cbFiltroCambioEmpresa();
            }
        }
    }//GEN-LAST:event_cbFiltroTablaItemStateChanged

    private void cbFiltroTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFiltroTablaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbFiltroTablaActionPerformed

    private void txtDistanciaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDistanciaMousePressed
        if (txtDistancia.getText().equals("Distancia (Km)")){
            txtDistancia.setText("");
        }
    }//GEN-LAST:event_txtDistanciaMousePressed

    private void txtDistanciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDistanciaKeyTyped
        char key = evt.getKeyChar();

        boolean numero = (key >= '0' && key <= '9') || key == '.';

        if(!numero){
            evt.consume();
        }
    }//GEN-LAST:event_txtDistanciaKeyTyped

    private void txtTiempoTraficoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTiempoTraficoMousePressed
        if (txtTiempoTrafico.getText().equals("Tiempo Trafico (min)")){
            txtTiempoTrafico.setText("");
        }
    }//GEN-LAST:event_txtTiempoTraficoMousePressed

    private void txtTiempoTraficoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTiempoTraficoKeyTyped
        char key = evt.getKeyChar();

        boolean numero = (key >= '0' && key <= '9') || key == '.';

        if(!numero){
            evt.consume();
        }
    }//GEN-LAST:event_txtTiempoTraficoKeyTyped

    private void txtTiempoTraficoAñadirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTiempoTraficoAñadirMousePressed
        if (txtTiempoTraficoAñadir.getText().equals("Tiempo Trafico (min)")){
            txtTiempoTraficoAñadir.setText("");
        }
    }//GEN-LAST:event_txtTiempoTraficoAñadirMousePressed

    private void txtTiempoTraficoAñadirKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTiempoTraficoAñadirKeyTyped
        char key = evt.getKeyChar();

        boolean numero = (key >= '0' && key <= '9') || key == '.';

        if(!numero){
            evt.consume();
        }
    }//GEN-LAST:event_txtTiempoTraficoAñadirKeyTyped

    private void txtDistanciaAñadirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDistanciaAñadirMousePressed
        if (txtDistanciaAñadir.getText().equals("Distancia (Km)")){
            txtDistanciaAñadir.setText("");
        }
    }//GEN-LAST:event_txtDistanciaAñadirMousePressed

    private void txtDistanciaAñadirKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDistanciaAñadirKeyTyped
        char key = evt.getKeyChar();

        boolean numero = (key >= '0' && key <= '9') || key == '.';

        if(!numero){
            evt.consume();
        }
    }//GEN-LAST:event_txtDistanciaAñadirKeyTyped

    private void btnProyectarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProyectarMouseClicked
        if(cbInicioProyeccion.getSelectedItem().equals("Inicio")) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione un inicio");
        }else if(cbDestinoProyeccion.getSelectedItem().equals("Destino")){
            JOptionPane.showMessageDialog(null, "Por favor, seleccione un destino");
        }else if(cbVehiculoProyectar.getSelectedItem().equals("Vehiculo")){
            JOptionPane.showMessageDialog(null, "Por favor, seleccione un vehiculo");
        }else if(txtDistancia.equals("Distancia (Km)")){
            JOptionPane.showMessageDialog(null, "Por favor, ingrese la distancia");
        }else if(txtTiempoTrafico.equals("Tiempo Trafico (min)")){
            JOptionPane.showMessageDialog(null, "Por favor, ingrese el tiempo del trafico");
        }else{
            Double actualCombustible=controlador.cantidadRestante(id_usuario, cbVehiculoProyectar.getSelectedItem().toString());
            Double proyeccion=controlador.proyeccion(id_usuario, cbVehiculoProyectar.getSelectedItem().toString(),txtDistancia.getText(),txtTiempoTrafico.getText());
            if (actualCombustible>proyeccion && (actualCombustible-proyeccion)>0){
                JOptionPane.showMessageDialog(null, "La cantidad de combustible que usaria en el viaje este de: "+proyeccion+" gal"+"\nSu combustible actual es : "+actualCombustible+" gal"+"\nTras el viaje le quedan : "+(Math.round((actualCombustible-proyeccion) * 10.0) / 10.0)+" gal");
            }else{
                String res=""+(proyeccion-actualCombustible);
                if(proyeccion==actualCombustible){
                    res=""+proyeccion;
                }
                JOptionPane.showMessageDialog(null, "No tiene combustible suficiente para el viaje debera hacer un tanqueo de más de "+res+" gal"+"\nLa cantidad de combustible que usaria en el viaje este de: "+proyeccion+" gal"+"\nSu combustible actual es : "+actualCombustible+" gal");
            }
        }
    }//GEN-LAST:event_btnProyectarMouseClicked

    private void btnAñadirRutaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAñadirRutaMouseClicked
        if(txtNombreRuta.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un nombre para la ruta");
        }else if(cbInicioAñadir.getSelectedItem().equals("Inicio")) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione un inicio");
        }else if(cbDestinoAñadir.getSelectedItem().equals("Destino")){
            JOptionPane.showMessageDialog(null, "Por favor, seleccione un destino");
        }else if(txtDistanciaAñadir.equals("Distancia (Km)")){
            JOptionPane.showMessageDialog(null, "Por favor, ingrese la distancia");
        }else if(txtTiempoTraficoAñadir.equals("Tiempo Trafico (min)")){
            JOptionPane.showMessageDialog(null, "Por favor, ingrese el tiempo del trafico");
        }else{
            controlador.añadirRuta(id_usuario, txtNombreRuta.getText(), cbInicioAñadir.getSelectedItem().toString(), cbDestinoAñadir.getSelectedItem().toString(), txtDistanciaAñadir.getText(), txtTiempoTraficoAñadir.getText());
            cargar_rutas();
        }
    }//GEN-LAST:event_btnAñadirRutaMouseClicked

    private void btnReportesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportesMouseClicked
        this.setVisible(false);
        Frame_Reportes reportes = new Frame_Reportes();
        reportes.setLocationRelativeTo(null);
        reportes.setVisible(true);
    }//GEN-LAST:event_btnReportesMouseClicked

    private void btnVehiculoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVehiculoMouseClicked
        this.setVisible(false);
        Frame_Vehiculo vehiculo = new Frame_Vehiculo();
        vehiculo.setLocationRelativeTo(null);
        vehiculo.setVisible(true);
    }//GEN-LAST:event_btnVehiculoMouseClicked

    private void btnTanqueosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTanqueosMouseClicked
        this.setVisible(false);
        Frame_Tanqueo tanqueo = new Frame_Tanqueo();
        tanqueo.setLocationRelativeTo(null);
        tanqueo.setVisible(true);
    }//GEN-LAST:event_btnTanqueosMouseClicked

    private void btnDashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDashboardMouseClicked
        this.setVisible(false);
        Frame_Inicio inicio = new Frame_Inicio();
        inicio.setLocationRelativeTo(null);
        inicio.setVisible(true);
    }//GEN-LAST:event_btnDashboardMouseClicked

    private void cbRutaComenzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbRutaComenzarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbRutaComenzarActionPerformed

    private void cbVehiculoComenzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbVehiculoComenzarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbVehiculoComenzarActionPerformed

    private void btnComenzarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnComenzarMouseClicked
        if(cbRutaComenzar.getSelectedItem().equals("Ruta")) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una ruta");
        }else if(cbVehiculoComenzar.getSelectedItem().equals("Vehículo")){
            JOptionPane.showMessageDialog(null, "Por favor, seleccione su vehiculo");
        }else if(controlador.cantidadRestante(id_usuario, cbVehiculoComenzar.getSelectedItem().toString())>controlador.cantidadHaConsumir(id_usuario, cbVehiculoComenzar.getSelectedItem().toString(), cbRutaComenzar.getSelectedItem().toString()) && (controlador.cantidadRestante(id_usuario, cbVehiculoComenzar.getSelectedItem().toString())-controlador.cantidadHaConsumir(id_usuario, cbVehiculoComenzar.getSelectedItem().toString(), cbRutaComenzar.getSelectedItem().toString()))>0){
            String fecha= ""+formatoFecha.format(jDateChooserFecha.getDate());
            controlador.comenzarviaje(cbRutaComenzar.getSelectedItem().toString(), id_usuario, cbVehiculoComenzar.getSelectedItem().toString(), fecha);
            Frame_Tanqueo.id_ruta=controlador.obteneridruta(cbRutaComenzar.getSelectedItem().toString(), id_usuario);
        }else{
            String res=""+(controlador.cantidadHaConsumir(id_usuario, cbVehiculoComenzar.getSelectedItem().toString(), cbRutaComenzar.getSelectedItem().toString())-controlador.cantidadRestante(id_usuario, cbVehiculoComenzar.getSelectedItem().toString()));
            Frame_Tanqueo.id_ruta=controlador.obteneridruta(cbRutaComenzar.getSelectedItem().toString(), id_usuario);
            if(controlador.cantidadHaConsumir(id_usuario, cbVehiculoComenzar.getSelectedItem().toString(), cbRutaComenzar.getSelectedItem().toString())==controlador.cantidadRestante(id_usuario, cbVehiculoComenzar.getSelectedItem().toString())){
                res=""+controlador.cantidadHaConsumir(id_usuario, cbVehiculoComenzar.getSelectedItem().toString(), cbRutaComenzar.getSelectedItem().toString());
            }
            JOptionPane.showMessageDialog(null, "No tiene combustible suficiente para el viaje debera hacer un tanqueo de más de "+res+" gal");
        }
        llenar_tabla();
    }//GEN-LAST:event_btnComenzarMouseClicked
    private void cbFiltroCambioPersona(){
        if(cbFiltroTabla.getSelectedItem().equals("Filtro")){
            // TODO add your handling code here:
        }
        if(cbFiltroTabla.getSelectedItem().equals("Menor gasto")){
            dt.setRowCount(0);
            List<Vehiculo> datos=controlador.llenartablaPersonaMenor(id_usuario);
            for (Vehiculo vehiculo : datos) {
                String nombreVehiculo=vehiculo.getMarca()+" "+vehiculo.getModelo();
                Object v[]={vehiculo.getTanqueo().getId_tanqueo(),vehiculo.getRuta().getNombre(),vehiculo.getUsuario().getNombre(),nombreVehiculo,"S/."+vehiculo.getTanqueo().getPagoTotal()};
                dt.addRow(v);
            }
        }else if(cbFiltroTabla.getSelectedItem().equals("Mayor gasto")){
            dt.setRowCount(0);
            List<Vehiculo> datos=controlador.llenartablaPersonaMayor(id_usuario);
            for (Vehiculo vehiculo : datos) {
                String nombreVehiculo=vehiculo.getMarca()+" "+vehiculo.getModelo();
                Object v[]={vehiculo.getTanqueo().getId_tanqueo(),vehiculo.getRuta().getNombre(),vehiculo.getUsuario().getNombre(),nombreVehiculo,"S/."+vehiculo.getTanqueo().getPagoTotal()};
                dt.addRow(v);
            }
        }
    }
    
    private void cbFiltroCambioEmpresa(){
        if(cbFiltroTabla.getSelectedItem().equals("Filtro")){
            // TODO add your handling code here:
        }
        if(cbFiltroTabla.getSelectedItem().equals("Menor gasto")){
            dt.setRowCount(0);
            List<Vehiculo> datos=controlador.llenartablaEmpresaMenor(id_empresa);
            for (Vehiculo vehiculo : datos) {
                String nombreVehiculo=vehiculo.getMarca()+" "+vehiculo.getModelo();
                Object v[]={vehiculo.getTanqueo().getId_tanqueo(),vehiculo.getRuta().getNombre(),vehiculo.getUsuario().getNombre(),nombreVehiculo,"S/."+vehiculo.getTanqueo().getPagoTotal()};
                dt.addRow(v);
            }
        }else if(cbFiltroTabla.getSelectedItem().equals("Mayor gasto")){
            dt.setRowCount(0);
            List<Vehiculo> datos=controlador.llenartablaEmpresaMayor(id_empresa);
            for (Vehiculo vehiculo : datos) {
                String nombreVehiculo=vehiculo.getMarca()+" "+vehiculo.getModelo();
                Object v[]={vehiculo.getTanqueo().getId_tanqueo(),vehiculo.getRuta().getNombre(),vehiculo.getUsuario().getNombre(),nombreVehiculo,"S/."+vehiculo.getTanqueo().getPagoTotal()};
                dt.addRow(v);
            }
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Frame_Ruta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame_Ruta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame_Ruta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame_Ruta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame_Ruta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnAñadirRuta;
    private javax.swing.JLabel btnComenzar;
    private javax.swing.JLabel btnDashboard;
    private javax.swing.JLabel btnProyectar;
    private javax.swing.JLabel btnReportes;
    private javax.swing.JLabel btnTanqueos;
    private javax.swing.JLabel btnVehiculo;
    private javax.swing.JComboBox<String> cbDestinoAñadir;
    private javax.swing.JComboBox<String> cbDestinoProyeccion;
    private javax.swing.JComboBox<String> cbFiltroTabla;
    private javax.swing.JComboBox<String> cbInicioAñadir;
    private javax.swing.JComboBox<String> cbInicioProyeccion;
    private javax.swing.JComboBox<String> cbRutaComenzar;
    private javax.swing.JComboBox<String> cbVehiculoComenzar;
    private javax.swing.JComboBox<String> cbVehiculoProyectar;
    private com.toedter.calendar.JDateChooser jDateChooserFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private Vista.PanelRound panelRound10;
    private Vista.PanelRound panelRound12;
    private Vista.PanelRound panelRound13;
    private Vista.PanelRound panelRound15;
    private Vista.PanelRound panelRound16;
    private Vista.PanelRound panelRound17;
    private Vista.PanelRound panelRound18;
    private Vista.PanelRound panelRound19;
    private Vista.PanelRound panelRound20;
    private Vista.PanelRound panelRound21;
    private Vista.PanelRound panelRound23;
    private Vista.PanelRound panelRound7;
    private Vista.PanelRound panelRound8;
    private Vista.PanelRound panelRound9;
    private javax.swing.JTable tablaViajes;
    private javax.swing.JTextField txtDistancia;
    private javax.swing.JTextField txtDistanciaAñadir;
    private javax.swing.JLabel txtFechaActual;
    private javax.swing.JTextField txtNombreRuta;
    private javax.swing.JTextField txtTiempoTrafico;
    private javax.swing.JTextField txtTiempoTraficoAñadir;
    // End of variables declaration//GEN-END:variables
}
