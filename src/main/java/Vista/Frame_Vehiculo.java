/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Coneccion.Coneccion;
import Controlador.Controlador_Ruta;
import Controlador.Controlador_Vehiculo;
import Modelo.Vehiculo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Arice
 */
public class Frame_Vehiculo extends javax.swing.JFrame {
    public static String tipo;
    public static String correo;
    public static String id_usuario;
    public static String id_vehiculo;
    public static String vehiculo;
    
    
    DefaultTableModel dt=new DefaultTableModel();

    Controlador_Vehiculo controlador=new Controlador_Vehiculo();
    DateFormat formateador= new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
    
    Calendar  fecha_actual=new GregorianCalendar();
    
    SimpleDateFormat sdf = new SimpleDateFormat("EEEE", new Locale("es", "ES"));
    DateFormatSymbols dfs = new DateFormatSymbols(new Locale("es"));
    SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm a");
    
    public Frame_Vehiculo() {        
        initComponents();
        setIconImage(getIconImage());
        this.setLocationRelativeTo(null);
        this.setExtendedState(6);
        dt = (DefaultTableModel)tablaViajes.getModel();
        datos_iniciales();
        llenar_combobox();
        ahorros();
        gastos();
        fecha_actual();
        llenar_combobox_filtro();
        showLineChart();
    }
    
     @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/Frame.png"));
        return retValue;
    }
    private void ahorros(){
        
        txtAhorroHoy.setText("S/."+Double.toString(controlador.ahorrohoy(id_usuario)));
        txtAhorroSemanaPasada.setText("Ahorro Semana Pasada S/."+Double.toString(controlador.ahorrosemanapasada(id_usuario)));
    }
    
    private void gastos(){
        txtGastoHoy.setText("S/."+Double.toString(controlador.gastohoy(id_usuario)));
        txtGastoSemanaPasada.setText("Gasto Semana Pasada S/."+Double.toString(controlador.gastosemanapasada(id_usuario)));
    }
    
    private void fecha_actual(){
        String diaHoy=sdf.format(fecha_actual.getTime());
        diaHoy = diaHoy.substring(0, 1).toUpperCase() + diaHoy.substring(1).toLowerCase();
        int mes = fecha_actual.get(Calendar.MONTH);
        String[] meses = dfs.getMonths();
        String mesActual = meses[mes];
        
        txtFechaActual.setText(diaHoy+", "+fecha_actual.get(Calendar.DAY_OF_MONTH)+" de "+mesActual+", "+fecha_actual.get(Calendar.YEAR)+", "+formatoHora.format(fecha_actual.getTime()));
    }
    
    private void datos_iniciales(){
        List<Vehiculo> vehiculos=controlador.datos_generales_vehiculo(correo);
        
        txtMarca.setText(vehiculos.get(0).getMarca());
        txtModelo.setText(vehiculos.get(0).getModelo());
        txtCapacidad.setText(Double.toString(vehiculos.get(0).getCapacidad())+" gal");
        txtPotencia.setText(vehiculos.get(0).getPotencia()+" kw");
        txtTiempoAdquisicion.setText((fecha_actual.get(Calendar.YEAR)-vehiculos.get(0).getAnioAdqui())+" años");
        txtAnioFabricacion.setText(Integer.toString(vehiculos.get(0).getAnioFabri()));
        txtTipoCombustible.setText(Double.toString(vehiculos.get(0).getVelocidad())+" km/h");
        txtTipoCombustible.setText(vehiculos.get(0).getMotor().getTipo());
        txtSistemaIgnicion.setText(vehiculos.get(0).getMotor().getSistCom());
    };
    
    private void llenar_combobox(){
        cbVehiculos.removeAllItems();
        for (Vehiculo vehiculo : controlador.datos_generales_vehiculo(correo)) {
            cbVehiculos.addItem(vehiculo.getMarca()+" "+vehiculo.getModelo()); // Agregar el atributo deseado al modelo
        }
    };
    
    private void llenar_combobox_filtro(){
        cbFiltro.removeAllItems();
        cbFiltro.addItem("Filtro");
        cbFiltro.addItem("Mayores veces");
        cbFiltro.addItem("Menor gasto");
        cbFiltro.addItem("Mayor gasto");
    };

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Logout = new Vista.PanelRound();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        panelRound7 = new Vista.PanelRound();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        panelRound9 = new Vista.PanelRound();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        panelRound1 = new Vista.PanelRound();
        txtMarca = new javax.swing.JTextField();
        txtCapacidad = new javax.swing.JTextField();
        txtModelo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtPotencia = new javax.swing.JTextField();
        txtTiempoAdquisicion = new javax.swing.JTextField();
        txtAnioFabricacion = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtVelocidad = new javax.swing.JTextField();
        txtTipoCombustible = new javax.swing.JTextField();
        txtSistemaIgnicion = new javax.swing.JTextField();
        cbVehiculos = new javax.swing.JComboBox<>();
        Img = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        cbFiltro = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaViajes = new javax.swing.JTable();
        panelLineChart = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        txtAhorroHoy = new javax.swing.JLabel();
        txtAhorroSemanaPasada = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txtFechaActual = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtGastoSemanaPasada = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtGastoHoy = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(6);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel34.setText("Estado de viajes");
        jPanel1.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 260, -1, -1));

        jPanel2.setBackground(new java.awt.Color(26, 25, 25));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(81, 35, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Group 10.png"))); // NOI18N
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        Logout.setBackground(new java.awt.Color(47, 47, 47));
        Logout.setForeground(new java.awt.Color(255, 255, 255));
        Logout.setRoundBottomLeft(10);
        Logout.setRoundBottomRight(10);
        Logout.setRoundTopLeft(10);
        Logout.setRoundTopRight(10);
        Logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LogoutMouseClicked(evt);
            }
        });
        Logout.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Logout.png"))); // NOI18N
        jLabel7.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        Logout.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 15, 20, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/placeholder.png"))); // NOI18N
        Logout.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jPanel2.add(Logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 610, 150, 50));

        panelRound7.setBackground(new java.awt.Color(0, 106, 255));
        panelRound7.setForeground(new java.awt.Color(255, 255, 255));
        panelRound7.setRoundBottomLeft(10);
        panelRound7.setRoundBottomRight(10);
        panelRound7.setRoundTopLeft(10);
        panelRound7.setRoundTopRight(10);
        panelRound7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelRound7MouseClicked(evt);
            }
        });
        panelRound7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Vehiculo");
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        panelRound7.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 13, 80, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Car.png"))); // NOI18N
        panelRound7.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 13, -1, -1));

        panelRound9.setBackground(new java.awt.Color(255, 255, 255));
        panelRound9.setForeground(new java.awt.Color(255, 255, 255));
        panelRound9.setRoundBottomLeft(5);
        panelRound9.setRoundBottomRight(5);
        panelRound9.setRoundTopLeft(5);
        panelRound9.setRoundTopRight(5);
        panelRound9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelRound7.add(panelRound9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 5, 5, 40));

        jPanel2.add(panelRound7, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 180, 180, 50));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu.png"))); // NOI18N
        jLabel10.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 120, 180, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu (4).png"))); // NOI18N
        jLabel11.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 240, 180, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu (3).png"))); // NOI18N
        jLabel12.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 450, 180, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Header.png"))); // NOI18N
        jLabel13.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 330, 180, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu (2).png"))); // NOI18N
        jLabel14.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 390, 180, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Split line.png"))); // NOI18N
        jLabel20.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 190, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 230, 700));

        jPanel3.setBackground(new java.awt.Color(248, 247, 241));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound1.setBackground(new java.awt.Color(15, 14, 14));
        panelRound1.setRoundBottomLeft(20);
        panelRound1.setRoundBottomRight(20);
        panelRound1.setRoundTopLeft(20);
        panelRound1.setRoundTopRight(20);

        txtMarca.setBackground(new java.awt.Color(15, 14, 14));
        txtMarca.setForeground(new java.awt.Color(255, 255, 255));
        txtMarca.setText("jTextField1");
        txtMarca.setBorder(null);
        txtMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMarcaActionPerformed(evt);
            }
        });

        txtCapacidad.setBackground(new java.awt.Color(15, 14, 14));
        txtCapacidad.setForeground(new java.awt.Color(255, 255, 255));
        txtCapacidad.setText("jTextField1");
        txtCapacidad.setBorder(null);
        txtCapacidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCapacidadActionPerformed(evt);
            }
        });

        txtModelo.setBackground(new java.awt.Color(15, 14, 14));
        txtModelo.setForeground(new java.awt.Color(255, 255, 255));
        txtModelo.setText("jTextField1");
        txtModelo.setBorder(null);
        txtModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtModeloActionPerformed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu1.png"))); // NOI18N

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu2.png"))); // NOI18N

        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu3.png"))); // NOI18N

        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu4.png"))); // NOI18N

        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu5.png"))); // NOI18N

        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu6.png"))); // NOI18N

        txtPotencia.setBackground(new java.awt.Color(15, 14, 14));
        txtPotencia.setForeground(new java.awt.Color(255, 255, 255));
        txtPotencia.setText("jTextField1");
        txtPotencia.setBorder(null);
        txtPotencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPotenciaActionPerformed(evt);
            }
        });

        txtTiempoAdquisicion.setBackground(new java.awt.Color(15, 14, 14));
        txtTiempoAdquisicion.setForeground(new java.awt.Color(255, 255, 255));
        txtTiempoAdquisicion.setText("jTextField1");
        txtTiempoAdquisicion.setBorder(null);
        txtTiempoAdquisicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTiempoAdquisicionActionPerformed(evt);
            }
        });

        txtAnioFabricacion.setBackground(new java.awt.Color(15, 14, 14));
        txtAnioFabricacion.setForeground(new java.awt.Color(255, 255, 255));
        txtAnioFabricacion.setText("jTextField1");
        txtAnioFabricacion.setBorder(null);
        txtAnioFabricacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAnioFabricacionActionPerformed(evt);
            }
        });

        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu7.png"))); // NOI18N

        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu8.png"))); // NOI18N

        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu9.png"))); // NOI18N

        txtVelocidad.setBackground(new java.awt.Color(15, 14, 14));
        txtVelocidad.setForeground(new java.awt.Color(255, 255, 255));
        txtVelocidad.setText("jTextField1");
        txtVelocidad.setBorder(null);
        txtVelocidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVelocidadActionPerformed(evt);
            }
        });

        txtTipoCombustible.setBackground(new java.awt.Color(15, 14, 14));
        txtTipoCombustible.setForeground(new java.awt.Color(255, 255, 255));
        txtTipoCombustible.setText("jTextField1");
        txtTipoCombustible.setBorder(null);
        txtTipoCombustible.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTipoCombustibleActionPerformed(evt);
            }
        });

        txtSistemaIgnicion.setBackground(new java.awt.Color(15, 14, 14));
        txtSistemaIgnicion.setForeground(new java.awt.Color(255, 255, 255));
        txtSistemaIgnicion.setText("jTextField1");
        txtSistemaIgnicion.setBorder(null);
        txtSistemaIgnicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSistemaIgnicionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel15)
                            .addComponent(jLabel19)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24))
                        .addGap(28, 28, 28)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSistemaIgnicion, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTipoCombustible, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVelocidad, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAnioFabricacion, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTiempoAdquisicion, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtPotencia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                                .addComponent(txtCapacidad, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))))
                    .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelRound1Layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addGap(28, 28, 28)
                            .addComponent(txtModelo, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
                        .addGroup(panelRound1Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(28, 28, 28)
                            .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtMarca))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtModelo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addComponent(txtCapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(16, 16, 16)
                                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addComponent(txtPotencia, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel19))
                            .addComponent(txtTiempoAdquisicion, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel21)
                            .addComponent(txtAnioFabricacion, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel22))
                    .addComponent(txtVelocidad, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(txtTipoCombustible, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(txtSistemaIgnicion, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel3.add(panelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 410, 500));

        cbVehiculos.setEditable(true);
        cbVehiculos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbVehiculos.setBorder(null);
        cbVehiculos.setFocusable(false);
        cbVehiculos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbVehiculosItemStateChanged(evt);
            }
        });
        cbVehiculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbVehiculosActionPerformed(evt);
            }
        });
        jPanel3.add(cbVehiculos, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 180, 40));

        Img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesVehiculos/Nissan Tiida 1.6 XE.png"))); // NOI18N
        jPanel3.add(Img, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 410, 150));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, 450, 700));

        jLabel36.setText("Cantidad de Gasolina");
        jPanel1.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 460, -1, -1));

        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Filter.png"))); // NOI18N
        jPanel1.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 260, -1, -1));

        cbFiltro.setEditable(true);
        cbFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Filtrar", "Item 2", "Item 3", "Item 4" }));
        cbFiltro.setBorder(null);
        cbFiltro.setFocusable(false);
        cbFiltro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbFiltroItemStateChanged(evt);
            }
        });
        cbFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbFiltroActionPerformed(evt);
            }
        });
        jPanel1.add(cbFiltro, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 260, 110, 20));

        jScrollPane1.setBorder(null);

        tablaViajes.setAutoCreateRowSorter(true);
        tablaViajes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "No.", "Conductor", "N° Veces", "Lugar"
            }
        ));
        tablaViajes.setFillsViewportHeight(true);
        tablaViajes.setFocusCycleRoot(true);
        tablaViajes.setGridColor(new java.awt.Color(255, 255, 255));
        tablaViajes.setSelectionBackground(new java.awt.Color(102, 153, 255));
        tablaViajes.setShowVerticalLines(true);
        jScrollPane1.setViewportView(tablaViajes);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 290, 570, 120));

        panelLineChart.setBackground(new java.awt.Color(255, 255, 255));
        panelLineChart.setLayout(new java.awt.BorderLayout());
        jPanel1.add(panelLineChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 490, 580, 160));

        jLabel33.setForeground(new java.awt.Color(101, 101, 117));
        jLabel33.setText("AHORRO");
        jPanel1.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 100, -1, -1));

        jLabel39.setBackground(new java.awt.Color(244, 245, 247));
        jLabel39.setForeground(new java.awt.Color(82, 82, 86));
        jLabel39.setText("Hoy");
        jPanel1.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 90, -1, -1));

        txtAhorroHoy.setForeground(new java.awt.Color(101, 101, 117));
        txtAhorroHoy.setText("S/104.50");
        jPanel1.add(txtAhorroHoy, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 130, -1, -1));

        txtAhorroSemanaPasada.setForeground(new java.awt.Color(101, 101, 117));
        txtAhorroSemanaPasada.setText("Gasto Semana Pasada S/1000.90");
        jPanel1.add(txtAhorroSemanaPasada, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 190, -1, -1));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Statistics2.png"))); // NOI18N
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 430, 660, -1));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Live Car Status3.png"))); // NOI18N
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 230, 660, -1));

        jLabel32.setForeground(new java.awt.Color(82, 82, 86));
        jLabel32.setText("Estadisticas de hoy");
        jPanel1.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 28, -1, -1));

        txtFechaActual.setForeground(new java.awt.Color(163, 163, 163));
        txtFechaActual.setText("Lunes, 01 de Mayo, 2023, 11:30 AM");
        jPanel1.add(txtFechaActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 48, -1, -1));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Card.png"))); // NOI18N
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 60, -1, -1));

        txtGastoSemanaPasada.setForeground(new java.awt.Color(101, 101, 117));
        txtGastoSemanaPasada.setText("Gasto Semana Pasada S/1000.90");
        jPanel1.add(txtGastoSemanaPasada, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 190, -1, -1));

        jLabel26.setForeground(new java.awt.Color(101, 101, 117));
        jLabel26.setText("GASTOS");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 100, -1, -1));

        txtGastoHoy.setForeground(new java.awt.Color(101, 101, 117));
        txtGastoHoy.setText("S/104.50");
        jPanel1.add(txtGastoHoy, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 140, -1, -1));

        jLabel31.setBackground(new java.awt.Color(244, 245, 247));
        jLabel31.setForeground(new java.awt.Color(82, 82, 86));
        jLabel31.setText("Hoy");
        jPanel1.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 100, -1, 16));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Card.png"))); // NOI18N
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 60, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1354, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 697, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMarcaActionPerformed

    private void txtCapacidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCapacidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCapacidadActionPerformed

    private void txtModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtModeloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtModeloActionPerformed

    private void txtPotenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPotenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPotenciaActionPerformed

    private void txtTiempoAdquisicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTiempoAdquisicionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTiempoAdquisicionActionPerformed

    private void txtAnioFabricacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAnioFabricacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAnioFabricacionActionPerformed

    private void txtVelocidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVelocidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVelocidadActionPerformed

    private void txtTipoCombustibleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTipoCombustibleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoCombustibleActionPerformed

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        this.setVisible(false);
        Frame_Inicio inicio = new  Frame_Inicio();
        inicio.setLocationRelativeTo(null);
        inicio.setVisible(true);
    }//GEN-LAST:event_jLabel10MouseClicked

    private void panelRound7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelRound7MouseClicked
        this.setVisible(false);
        Frame_Vehiculo vehiculo = new  Frame_Vehiculo();
        vehiculo.setLocationRelativeTo(null);
        vehiculo.setVisible(true);
    }//GEN-LAST:event_panelRound7MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        this.setVisible(false);
        Frame_Ruta ruta = new  Frame_Ruta();
        ruta.setLocationRelativeTo(null);
        ruta.setVisible(true);
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
       this.setVisible(false);
        Frame_Tanqueo tanqueo = new  Frame_Tanqueo();
        tanqueo.setLocationRelativeTo(null);
        tanqueo.setVisible(true);
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        this.setVisible(false);
        Frame_Reportes reporte = new  Frame_Reportes();
        reporte.setLocationRelativeTo(null);
        reporte.setVisible(true);
    }//GEN-LAST:event_jLabel12MouseClicked

    private void txtSistemaIgnicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSistemaIgnicionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSistemaIgnicionActionPerformed

    private void cbVehiculosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbVehiculosItemStateChanged
        vehiculo= (String) cbVehiculos.getSelectedItem();
        Frame_Reportes.vehiculo = vehiculo;
        id_vehiculo=controlador.idvehiculo(vehiculo);
        Frame_Tanqueo.id_vehiculo = id_vehiculo;        
        cambiar_datos();
    }//GEN-LAST:event_cbVehiculosItemStateChanged

    private void cbVehiculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbVehiculosActionPerformed
        //        String vehiculo = (String) cbVehiculos.getSelectedItem();
        //
        //        String id_vehiculo=vehiculoDAO.id_vehiculoByNombreVehiculo(vehiculo);
        //        cambiar_datos(id_vehiculo);
    }//GEN-LAST:event_cbVehiculosActionPerformed

    private void cbFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbFiltroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbFiltroActionPerformed

    private void cbFiltroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbFiltroItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            // Llamar a la función deseada
            cbFiltroCambio();
        }
    }//GEN-LAST:event_cbFiltroItemStateChanged

    private void LogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogoutMouseClicked
        this.setVisible(false);
        Frame_usuario usu = new  Frame_usuario();
        usu.setLocationRelativeTo(null);
        usu.setVisible(true);
    }//GEN-LAST:event_LogoutMouseClicked
    
    private void cbFiltroCambio(){
        if(cbFiltro.getSelectedItem().equals("Filtro")){
            // TODO add your handling code here:
        }
        if(cbFiltro.getSelectedItem().equals("Mayores veces")){
            dt.setRowCount(0);
            List<Vehiculo> datos=controlador.datos_viajes_mayor_veces(id_usuario,id_vehiculo);
            for (Vehiculo vehiculo : datos) {
                String lugar=vehiculo.getRuta().getOrigen()+" - "+vehiculo.getRuta().getDestino();
                Object v[]={vehiculo.getTanqueo().getId_tanqueo(),vehiculo.getUsuario().getNombre(),vehiculo.getAnioAdqui(),lugar,vehiculo.getTanqueo().getPagoTotal()};
                dt.addRow(v);
            }
        }else if(cbFiltro.getSelectedItem().equals("Menor gasto")){
            dt.setRowCount(0);
            List<Vehiculo> datos=controlador.datos_viajes_menor_gasto(id_usuario,id_vehiculo);
            for (Vehiculo vehiculo : datos) {
                String lugar=vehiculo.getRuta().getOrigen()+" - "+vehiculo.getRuta().getDestino();
                Object v[]={vehiculo.getTanqueo().getId_tanqueo(),vehiculo.getUsuario().getNombre(),vehiculo.getAnioAdqui(),lugar,vehiculo.getTanqueo().getPagoTotal()};
                dt.addRow(v);
            }
        }else if(cbFiltro.getSelectedItem().equals("Mayor gasto")){
            dt.setRowCount(0);
            List<Vehiculo> datos=controlador.datos_viajes_mayor_gasto(id_usuario,id_vehiculo);
            for (Vehiculo vehiculo : datos) {
                String lugar=vehiculo.getRuta().getOrigen()+" - "+vehiculo.getRuta().getDestino();
                Object v[]={vehiculo.getTanqueo().getId_tanqueo(),vehiculo.getUsuario().getNombre(),vehiculo.getAnioAdqui(),lugar,vehiculo.getTanqueo().getPagoTotal()};
                dt.addRow(v);
            }
        }
    }
    
    private void cambiar_datos(){
        txtMarca.setText(controlador.datos_generales_cambio(id_vehiculo).getMarca());
        txtModelo.setText(controlador.datos_generales_cambio(id_vehiculo).getModelo());
        txtCapacidad.setText(Double.toString(controlador.datos_generales_cambio(id_vehiculo).getCapacidad())+" gal");
        txtPotencia.setText(controlador.datos_generales_cambio(id_vehiculo).getPotencia()+" kw");
        txtTiempoAdquisicion.setText((fecha_actual.get(Calendar.YEAR)-controlador.datos_generales_cambio(id_vehiculo).getAnioAdqui())+" años");
        txtAnioFabricacion.setText(Integer.toString(controlador.datos_generales_cambio(id_vehiculo).getAnioFabri()));
        txtVelocidad.setText(Double.toString(controlador.datos_generales_cambio(id_vehiculo).getVelocidad())+" km/h");
        txtTipoCombustible.setText(controlador.datos_generales_cambio(id_vehiculo).getTipoaxu());
        txtSistemaIgnicion.setText(controlador.datos_generales_cambio(id_vehiculo).getSistemaaux());
        llenar_tablar();
    }
    
    private void llenar_tablar(){
        dt.setRowCount(0);
        List<Vehiculo> datos=controlador.datos_viajes(id_usuario,id_vehiculo);
        for (Vehiculo vehiculo : datos) {
            String lugar=vehiculo.getRuta().getOrigen()+" - "+vehiculo.getRuta().getDestino();
            Object v[]={vehiculo.getTanqueo().getId_tanqueo(),vehiculo.getUsuario().getNombre(),vehiculo.getAnioAdqui(),lugar,vehiculo.getTanqueo().getPagoTotal()};
            dt.addRow(v);
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
            java.util.logging.Logger.getLogger(Frame_Vehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame_Vehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame_Vehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame_Vehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame_Vehiculo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Img;
    private Vista.PanelRound Logout;
    private javax.swing.JComboBox<String> cbFiltro;
    private javax.swing.JComboBox<String> cbVehiculos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelLineChart;
    private Vista.PanelRound panelRound1;
    private Vista.PanelRound panelRound7;
    private Vista.PanelRound panelRound9;
    private javax.swing.JTable tablaViajes;
    private javax.swing.JLabel txtAhorroHoy;
    private javax.swing.JLabel txtAhorroSemanaPasada;
    private javax.swing.JTextField txtAnioFabricacion;
    private javax.swing.JTextField txtCapacidad;
    private javax.swing.JLabel txtFechaActual;
    private javax.swing.JLabel txtGastoHoy;
    private javax.swing.JLabel txtGastoSemanaPasada;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtPotencia;
    private javax.swing.JTextField txtSistemaIgnicion;
    private javax.swing.JTextField txtTiempoAdquisicion;
    private javax.swing.JTextField txtTipoCombustible;
    private javax.swing.JTextField txtVelocidad;
    // End of variables declaration//GEN-END:variables

    public void showLineChart(){
        Controlador_Ruta ruta = new Controlador_Ruta();
        Coneccion c = new Coneccion();
        Connection con = c.getConectar();
        ResultSet rs = null;
        PreparedStatement ps= null;
        try {
            ps=con.prepareStatement("select r.nombre_ruta from Ruta r INNER JOIN Tanqueo t on r.id_ruta=t.id_ruta INNER JOIN Vehiculo v on t.id_vehiculo=v.id_vehiculo INNER JOIN Asignacion a on a.id_vehiculo=v.id_vehiculo where a.id_usuario=? and CONCAT(v.marca, ' ', v.modelo) =? ;");
            ps.setString(1, id_usuario);
            ps.setString(2, vehiculo);
            rs = ps.executeQuery();
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            while(rs.next()){
                dataset.setValue(ruta.cantidadHaConsumir(id_usuario, vehiculo, rs.getString(1)), "Galones", rs.getString(1));
            }
        
        JFreeChart linechart =ChartFactory.createLineChart("Consumo por ruta","Ruta", "Galones", dataset, PlotOrientation.VERTICAL, true, true, true);
        
        CategoryPlot lineCategoryPlot = linechart.getCategoryPlot();
        lineCategoryPlot.setBackgroundPaint(Color.white);
        
        LineAndShapeRenderer lineRenderer = (LineAndShapeRenderer) lineCategoryPlot.getRenderer();
        Color lineChartColor = new Color(204,0,51);
        lineRenderer.setSeriesPaint(0, lineChartColor);
        ChartPanel lineChartPanel = new ChartPanel(linechart);
        panelLineChart.removeAll();
        panelLineChart.add(lineChartPanel, BorderLayout.CENTER);
        panelLineChart.validate();
            
        } catch (Exception e) {
            System.out.println("Error al obtener el ahorro por mes: "+e.getMessage());
        }
    }
}
