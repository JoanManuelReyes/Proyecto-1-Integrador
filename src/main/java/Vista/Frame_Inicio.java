/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Coneccion.Coneccion;
import Controlador.Controlador_Inicio;
import Controlador.Controlador_Tanqueo;
import Fuentes.Fuentes;
import Modelo.Ruta;
import Modelo.Vehiculo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Arice
 */
public final class Frame_Inicio extends javax.swing.JFrame {
    public static String tipo;
    public static String correo;
    public static String id_usuario;
    public static String id_empresa;
    /**
     * Creates new form Frame_Inicio
     */
    Controlador_Tanqueo controller = new Controlador_Tanqueo();
    Controlador_Inicio controlador=new Controlador_Inicio();
    DateFormat formateador= new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
    Calendar  fecha_actual=new GregorianCalendar();
    
    SimpleDateFormat sdf = new SimpleDateFormat("EEEE", new Locale("es", "ES"));
    DateFormatSymbols dfs = new DateFormatSymbols(new Locale("es"));
    SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm a");
    Fuentes tipoFuente;
     DefaultTableModel dt=new DefaultTableModel();
    public Frame_Inicio() {
        initComponents();
        this.setExtendedState(6);
         dt = (DefaultTableModel)tablaViajes.getModel();
        tipoFuente = new Fuentes();
        jLabel3.setFont(tipoFuente.fuente(tipoFuente.INT, 0, 14));
        txtFechaActual.setFont(tipoFuente.fuente(tipoFuente.INT, 0, 12));
        jLabel5.setFont(tipoFuente.fuente(tipoFuente.INT, 0, 18));
        showLineChart();
        jDateChooserFecha.setCalendar(fecha_actual);
        ahorros();
        gastos();
        fecha_actual();
        cargar_rutas();
        cargar_vehiculos();
        flechas();
        llenar_tabla();
        llenar_combobox_filtro();
        showPiePanel();
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
    private void flechas(){
        Icon icono;
        if(controlador.gastohoy(id_usuario)>=0){
            icono = new ImageIcon(getClass().getResource("/Imagenes/up (1).png"));
            arrow1.setIcon(icono);
        }else{
            icono = new ImageIcon(getClass().getResource("/Imagenes/down.png"));
            arrow1.setIcon(icono);
        }
        
        if(controlador.gastohoy(id_usuario)>=0){
            icono = new ImageIcon(getClass().getResource("/Imagenes/up.png"));
            arrow2.setIcon(icono);
        }else{
            icono = new ImageIcon(getClass().getResource("/Imagenes/down (1).png"));
            arrow2.setIcon(icono);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnComenzar = new Vista.PanelRound();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jDateChooserFecha = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        panelRound9 = new Vista.PanelRound();
        Logout = new Vista.PanelRound();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        panelInicio = new Vista.PanelRound();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jVehiculo = new javax.swing.JLabel();
        jRuta = new javax.swing.JLabel();
        jReporte = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTanqueo = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtFechaActual = new javax.swing.JLabel();
        panelRound1 = new Vista.PanelRound();
        txtGastoSemanaPasada = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtGastoHoy = new javax.swing.JLabel();
        arrow1 = new javax.swing.JLabel();
        panelRound2 = new Vista.PanelRound();
        panelRound3 = new Vista.PanelRound();
        jLabel9 = new javax.swing.JLabel();
        txtAhorroHoy = new javax.swing.JLabel();
        txtAhorroSemanaPasada = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        arrow2 = new javax.swing.JLabel();
        panelRound4 = new Vista.PanelRound();
        panelRound6 = new Vista.PanelRound();
        jLabel29 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        panelRound7 = new Vista.PanelRound();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        panelBarChart = new javax.swing.JPanel();
        panelRound5 = new Vista.PanelRound();
        jLabel32 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        cbFiltroTabla = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaViajes = new javax.swing.JTable();
        panelLineChart = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        panelRound14 = new Vista.PanelRound();
        cbRuta = new javax.swing.JComboBox<>();
        jLabel37 = new javax.swing.JLabel();
        panelRound15 = new Vista.PanelRound();
        cbVehiculo = new javax.swing.JComboBox<>();
        jLabel42 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(6);
        setIconImage(getIconImage());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnComenzar.setBackground(new java.awt.Color(0, 106, 255));
        btnComenzar.setRoundBottomLeft(10);
        btnComenzar.setRoundBottomRight(10);
        btnComenzar.setRoundTopLeft(10);
        btnComenzar.setRoundTopRight(10);
        btnComenzar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnComenzarMouseClicked(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(242, 242, 242));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("Comenzar");
        jLabel33.setAlignmentY(0.0F);
        jLabel33.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout btnComenzarLayout = new javax.swing.GroupLayout(btnComenzar);
        btnComenzar.setLayout(btnComenzarLayout);
        btnComenzarLayout.setHorizontalGroup(
            btnComenzarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnComenzarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                .addContainerGap())
        );
        btnComenzarLayout.setVerticalGroup(
            btnComenzarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel1.add(btnComenzar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 120, 121, 40));

        jLabel34.setText("Estado de viajes");
        jPanel1.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 220, -1, -1));

        jDateChooserFecha.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jDateChooserFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 120, 180, 40));

        jPanel2.setBackground(new java.awt.Color(26, 25, 25));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(81, 35, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Group 10.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        panelRound9.setBackground(new java.awt.Color(255, 255, 255));
        panelRound9.setForeground(new java.awt.Color(255, 255, 255));
        panelRound9.setRoundBottomLeft(5);
        panelRound9.setRoundBottomRight(5);
        panelRound9.setRoundTopLeft(5);
        panelRound9.setRoundTopRight(5);
        panelRound9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(panelRound9, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 125, 5, 40));

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

        panelInicio.setBackground(new java.awt.Color(0, 106, 255));
        panelInicio.setForeground(new java.awt.Color(255, 255, 255));
        panelInicio.setRoundBottomLeft(10);
        panelInicio.setRoundBottomRight(10);
        panelInicio.setRoundTopLeft(10);
        panelInicio.setRoundTopRight(10);
        panelInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelInicioMouseClicked(evt);
            }
        });
        panelInicio.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Inicio");
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        panelInicio.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(53, 13, 80, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Dashboard.png"))); // NOI18N
        panelInicio.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 13, -1, -1));

        jPanel2.add(panelInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 120, 180, 50));

        jVehiculo.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jVehiculo.setForeground(new java.awt.Color(255, 255, 255));
        jVehiculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu (1).png"))); // NOI18N
        jVehiculo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jVehiculo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jVehiculo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jVehiculoMouseClicked(evt);
            }
        });
        jPanel2.add(jVehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 180, 180, -1));

        jRuta.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jRuta.setForeground(new java.awt.Color(255, 255, 255));
        jRuta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu (4).png"))); // NOI18N
        jRuta.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jRuta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jRuta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRutaMouseClicked(evt);
            }
        });
        jPanel2.add(jRuta, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 240, 180, -1));

        jReporte.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jReporte.setForeground(new java.awt.Color(255, 255, 255));
        jReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu (3).png"))); // NOI18N
        jReporte.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jReporte.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jReporte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jReporteMouseClicked(evt);
            }
        });
        jPanel2.add(jReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 450, 180, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Header.png"))); // NOI18N
        jLabel13.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 330, 180, -1));

        jTanqueo.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jTanqueo.setForeground(new java.awt.Color(255, 255, 255));
        jTanqueo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu (2).png"))); // NOI18N
        jTanqueo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jTanqueo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTanqueo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTanqueoMouseClicked(evt);
            }
        });
        jPanel2.add(jTanqueo, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 390, 180, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Split line.png"))); // NOI18N
        jLabel20.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 190, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 230, 700));

        jLabel3.setText("Comenzar viaje");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 95, -1, -1));

        jPanel3.setBackground(new java.awt.Color(248, 247, 241));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtFechaActual.setForeground(new java.awt.Color(163, 163, 163));
        txtFechaActual.setText("Lunes, 22 de Mayo, 2023, 11:30 AM");
        jPanel3.add(txtFechaActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        panelRound1.setBackground(new java.awt.Color(255, 255, 255));
        panelRound1.setRoundBottomLeft(30);
        panelRound1.setRoundBottomRight(30);
        panelRound1.setRoundTopLeft(30);
        panelRound1.setRoundTopRight(30);
        panelRound1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtGastoSemanaPasada.setForeground(new java.awt.Color(101, 101, 117));
        txtGastoSemanaPasada.setText("Gasto Semana Pasada S/1000.90");
        panelRound1.add(txtGastoSemanaPasada, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        jLabel22.setForeground(new java.awt.Color(101, 101, 117));
        jLabel22.setText("GASTOS");
        panelRound1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel23.setBackground(new java.awt.Color(244, 245, 247));
        jLabel23.setForeground(new java.awt.Color(82, 82, 86));
        jLabel23.setText("Hoy");
        panelRound1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, -1, -1));

        txtGastoHoy.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        txtGastoHoy.setForeground(new java.awt.Color(51, 51, 51));
        txtGastoHoy.setText("S/104.50");
        panelRound1.add(txtGastoHoy, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 90, 50));
        panelRound1.add(arrow1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 30, 30));

        jPanel3.add(panelRound1, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 110, 210, 130));

        panelRound2.setBackground(new java.awt.Color(163, 163, 163, 100));
        panelRound2.setPreferredSize(new java.awt.Dimension(290, 120));
        panelRound2.setRequestFocusEnabled(false);
        panelRound2.setRoundBottomLeft(30);
        panelRound2.setRoundBottomRight(30);
        panelRound2.setRoundTopLeft(30);
        panelRound2.setRoundTopRight(30);
        panelRound2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(panelRound2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 112, 212, 132));

        panelRound3.setBackground(new java.awt.Color(255, 255, 255));
        panelRound3.setRoundBottomLeft(30);
        panelRound3.setRoundBottomRight(30);
        panelRound3.setRoundTopLeft(30);
        panelRound3.setRoundTopRight(30);
        panelRound3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setForeground(new java.awt.Color(101, 101, 117));
        jLabel9.setText("AHORRO");
        panelRound3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        txtAhorroHoy.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        txtAhorroHoy.setForeground(new java.awt.Color(51, 51, 51));
        txtAhorroHoy.setText("S/104.50");
        panelRound3.add(txtAhorroHoy, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 90, 50));

        txtAhorroSemanaPasada.setForeground(new java.awt.Color(101, 101, 117));
        txtAhorroSemanaPasada.setText("Gasto Semana Pasada S/1000.90");
        panelRound3.add(txtAhorroSemanaPasada, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        jLabel31.setBackground(new java.awt.Color(244, 245, 247));
        jLabel31.setForeground(new java.awt.Color(82, 82, 86));
        jLabel31.setText("Hoy");
        panelRound3.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, -1, -1));

        arrow2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/up (1).png"))); // NOI18N
        panelRound3.add(arrow2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 20, 30));

        jPanel3.add(panelRound3, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 258, 210, 130));

        panelRound4.setBackground(new java.awt.Color(163, 163, 163, 100));
        panelRound4.setPreferredSize(new java.awt.Dimension(290, 120));
        panelRound4.setRequestFocusEnabled(false);
        panelRound4.setRoundBottomLeft(30);
        panelRound4.setRoundBottomRight(30);
        panelRound4.setRoundTopLeft(30);
        panelRound4.setRoundTopRight(30);
        panelRound4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(panelRound4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 212, 132));

        panelRound6.setBackground(new java.awt.Color(255, 255, 255));
        panelRound6.setRoundBottomLeft(30);
        panelRound6.setRoundBottomRight(30);
        panelRound6.setRoundTopLeft(30);
        panelRound6.setRoundTopRight(30);
        panelRound6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel29.setBackground(new java.awt.Color(244, 245, 247));
        jLabel29.setForeground(new java.awt.Color(82, 82, 86));
        jLabel29.setText("Anual");
        panelRound6.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, -1, -1));

        jLabel38.setForeground(new java.awt.Color(101, 101, 117));
        jLabel38.setText("Ahorro vs Gastos");
        panelRound6.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        panelRound7.setBackground(new java.awt.Color(255, 255, 255));
        panelRound7.setRoundBottomLeft(30);
        panelRound7.setRoundBottomRight(30);
        panelRound7.setRoundTopLeft(30);
        panelRound7.setRoundTopRight(30);
        panelRound7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel43.setForeground(new java.awt.Color(101, 101, 117));
        jLabel43.setText("Total Gasto");
        panelRound7.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        jLabel44.setBackground(new java.awt.Color(244, 245, 247));
        jLabel44.setForeground(new java.awt.Color(82, 82, 86));
        jLabel44.setText("Anual");
        panelRound7.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, -1, -1));

        jLabel45.setForeground(new java.awt.Color(101, 101, 117));
        jLabel45.setText("Ahorro vs Gastos");
        panelRound7.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel46.setForeground(new java.awt.Color(101, 101, 117));
        jLabel46.setText("Total Ahorro");
        panelRound7.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        panelRound6.add(panelRound7, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 415, 208, 244));

        panelBarChart.setBackground(new java.awt.Color(255, 255, 255));
        panelBarChart.setLayout(new java.awt.BorderLayout());
        panelRound6.add(panelBarChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 50, 250, 170));

        jPanel3.add(panelRound6, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 415, 208, 244));

        panelRound5.setBackground(new java.awt.Color(163, 163, 163, 100));
        panelRound5.setPreferredSize(new java.awt.Dimension(290, 120));
        panelRound5.setRequestFocusEnabled(false);
        panelRound5.setRoundBottomLeft(30);
        panelRound5.setRoundBottomRight(30);
        panelRound5.setRoundTopLeft(30);
        panelRound5.setRoundTopRight(30);
        panelRound5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(panelRound5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 424, 210, 240));

        jLabel32.setForeground(new java.awt.Color(82, 82, 86));
        jLabel32.setText("Estadisticas de hoy");
        jPanel3.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, 270, 700));

        jLabel36.setText("Resumen Anual");
        jPanel1.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 460, -1, -1));

        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Filter.png"))); // NOI18N
        jPanel1.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 220, -1, -1));

        cbFiltroTabla.setEditable(true);
        cbFiltroTabla.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Filtrar", "Item 2", "Item 3", "Item 4" }));
        cbFiltroTabla.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        cbFiltroTabla.setFocusable(false);
        cbFiltroTabla.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbFiltroTablaItemStateChanged(evt);
            }
        });
        jPanel1.add(cbFiltroTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 220, 110, 20));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tablaViajes.setAutoCreateRowSorter(true);
        tablaViajes.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tablaViajes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "No.", "Ruta", "Conductor", "Vehiculo"
            }
        ));
        tablaViajes.setFillsViewportHeight(true);
        tablaViajes.setFocusCycleRoot(true);
        tablaViajes.setGridColor(new java.awt.Color(255, 255, 255));
        tablaViajes.setSelectionBackground(new java.awt.Color(164, 203, 203));
        tablaViajes.setShowVerticalLines(true);
        jScrollPane1.setViewportView(tablaViajes);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 250, 750, 140));

        panelLineChart.setBackground(new java.awt.Color(255, 255, 255));
        panelLineChart.setLayout(new java.awt.BorderLayout());
        jPanel1.add(panelLineChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 490, 750, 150));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Graph.png"))); // NOI18N
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(505, 430, -1, -1));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Graph.png"))); // NOI18N
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(505, 190, -1, -1));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Group 2.png"))); // NOI18N
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(905, 25, -1, -1));

        jTextField1.setForeground(new java.awt.Color(204, 204, 204));
        jTextField1.setText("Busca aqui...");
        jTextField1.setBorder(null);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 30, 100, -1));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Search (1).png"))); // NOI18N
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, -10, -1, -1));

        jLabel35.setText("Estado de viajes");
        jPanel1.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 230, -1, -1));

        panelRound14.setBackground(new java.awt.Color(255, 255, 255));
        panelRound14.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelRound14.setRoundBottomLeft(10);
        panelRound14.setRoundBottomRight(10);
        panelRound14.setRoundTopLeft(10);
        panelRound14.setRoundTopRight(10);

        cbRuta.setEditable(true);
        cbRuta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ruta", "Item 2", "Item 3", "Item 4" }));
        cbRuta.setToolTipText("");
        cbRuta.setBorder(null);
        cbRuta.setFocusable(false);
        cbRuta.setLightWeightPopupEnabled(false);
        cbRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbRutaActionPerformed(evt);
            }
        });

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icon _pin alt_.png"))); // NOI18N

        javax.swing.GroupLayout panelRound14Layout = new javax.swing.GroupLayout(panelRound14);
        panelRound14.setLayout(panelRound14Layout);
        panelRound14Layout.setHorizontalGroup(
            panelRound14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound14Layout.setVerticalGroup(
            panelRound14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound14Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(panelRound14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37)
                    .addComponent(cbRuta))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(panelRound14, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 120, 200, 40));

        panelRound15.setBackground(new java.awt.Color(255, 255, 255));
        panelRound15.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelRound15.setRoundBottomLeft(10);
        panelRound15.setRoundBottomRight(10);
        panelRound15.setRoundTopLeft(10);
        panelRound15.setRoundTopRight(10);

        cbVehiculo.setEditable(true);
        cbVehiculo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "vehiculo", "Item 2", "Item 3", "Item 4" }));
        cbVehiculo.setToolTipText("");
        cbVehiculo.setBorder(null);
        cbVehiculo.setFocusable(false);
        cbVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbVehiculoActionPerformed(evt);
            }
        });

        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/bx_car.png"))); // NOI18N

        javax.swing.GroupLayout panelRound15Layout = new javax.swing.GroupLayout(panelRound15);
        panelRound15.setLayout(panelRound15Layout);
        panelRound15Layout.setHorizontalGroup(
            panelRound15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelRound15Layout.setVerticalGroup(
            panelRound15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound15Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(panelRound15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel42)
                    .addComponent(cbVehiculo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(panelRound15, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 120, 190, 40));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Car Availablity.png"))); // NOI18N
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(505, 65, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 697, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void cbRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbRutaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbRutaActionPerformed

    private void cbVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbVehiculoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbVehiculoActionPerformed

    private void jVehiculoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jVehiculoMouseClicked
        Frame_Vehiculo.tipo=tipo;
        this.setVisible(false);
        Frame_Vehiculo vehiculo = new  Frame_Vehiculo();
        vehiculo.setLocationRelativeTo(null);
        vehiculo.setVisible(true);
    }//GEN-LAST:event_jVehiculoMouseClicked

    private void jRutaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRutaMouseClicked
        Frame_Ruta.tipo=tipo;
        this.setVisible(false);
        Frame_Ruta ruta = new  Frame_Ruta();
        ruta.setLocationRelativeTo(null);
        ruta.setVisible(true);
    }//GEN-LAST:event_jRutaMouseClicked

    private void jTanqueoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTanqueoMouseClicked
        Frame_Tanqueo.tipo=tipo;
        this.setVisible(false);
        Frame_Tanqueo tanqueo = new  Frame_Tanqueo();
        tanqueo.setLocationRelativeTo(null);
        tanqueo.setVisible(true);
    }//GEN-LAST:event_jTanqueoMouseClicked

    private void jReporteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jReporteMouseClicked
        Frame_Reportes.tipo=tipo;
        this.setVisible(false);
        Frame_Reportes reporte = new  Frame_Reportes();
        reporte.setLocationRelativeTo(null);
        reporte.setVisible(true);
    }//GEN-LAST:event_jReporteMouseClicked

    private void panelInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelInicioMouseClicked
       this.setVisible(false);
        Frame_Inicio inicio = new  Frame_Inicio();
        inicio.setLocationRelativeTo(null);
        inicio.setVisible(true);
    }//GEN-LAST:event_panelInicioMouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
       this.setVisible(false);
        Frame_Inicio inicio = new  Frame_Inicio();
        inicio.setLocationRelativeTo(null);
        inicio.setVisible(true);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void btnComenzarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnComenzarMouseClicked
         if(cbRuta.getSelectedItem().equals("Ruta")) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una ruta");
        }else if(cbVehiculo.getSelectedItem().equals("Vehículo")){
            JOptionPane.showMessageDialog(null, "Por favor, seleccione su vehiculo");
        }else if(controlador.cantidadRestante(id_usuario, cbVehiculo.getSelectedItem().toString())>controlador.cantidadHaConsumir(id_usuario, cbVehiculo.getSelectedItem().toString(), cbRuta.getSelectedItem().toString()) && (controlador.cantidadRestante(id_usuario, cbVehiculo.getSelectedItem().toString())-controlador.cantidadHaConsumir(id_usuario, cbVehiculo.getSelectedItem().toString(), cbRuta.getSelectedItem().toString()))>0){
            String fecha= ""+formatoFecha.format(jDateChooserFecha.getDate());
            controlador.comenzarviaje(cbRuta.getSelectedItem().toString(), id_usuario, cbVehiculo.getSelectedItem().toString(), fecha);
        }else{
            String res=""+(controlador.cantidadHaConsumir(id_usuario, cbVehiculo.getSelectedItem().toString(), cbRuta.getSelectedItem().toString())-controlador.cantidadRestante(id_usuario, cbVehiculo.getSelectedItem().toString()));
            if(controlador.cantidadHaConsumir(id_usuario, cbVehiculo.getSelectedItem().toString(), cbRuta.getSelectedItem().toString())==controlador.cantidadRestante(id_usuario, cbVehiculo.getSelectedItem().toString())){
                res=""+controlador.cantidadHaConsumir(id_usuario, cbVehiculo.getSelectedItem().toString(), cbRuta.getSelectedItem().toString());
            }
            JOptionPane.showMessageDialog(null, "No tiene combustible suficiente para el viaje debera hacer un tanqueo de más de "+res+" gal");
        }
        ahorros();
        gastos();
        llenar_tabla();
    }//GEN-LAST:event_btnComenzarMouseClicked

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

    private void LogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LogoutMouseClicked
        this.setVisible(false);
        Frame_usuario usu = new  Frame_usuario();
        usu.setLocationRelativeTo(null);
        usu.setVisible(true);
    }//GEN-LAST:event_LogoutMouseClicked
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
    private void cargar_rutas(){
        cbRuta.removeAllItems();
        cbRuta.addItem("Ruta");
        for (Ruta ruta : controlador.llenarrutas(id_usuario)) {
            cbRuta.addItem(ruta.getNombre()); // Agregar el atributo deseado al modelo
        }
    }
    
    private void cargar_vehiculos(){
        cbVehiculo.removeAllItems();
        cbVehiculo.addItem("Vehículo");
        for (Vehiculo vehiculo : controlador.nombres_vehiculo(correo)) {
            cbVehiculo.addItem(vehiculo.getMarca()+" "+vehiculo.getModelo()); // Agregar el atributo deseado al modelo
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
            java.util.logging.Logger.getLogger(Frame_Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame_Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame_Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame_Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame_Inicio().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Vista.PanelRound Logout;
    private javax.swing.JLabel arrow1;
    private javax.swing.JLabel arrow2;
    private Vista.PanelRound btnComenzar;
    private javax.swing.JComboBox<String> cbFiltroTabla;
    private javax.swing.JComboBox<String> cbRuta;
    private javax.swing.JComboBox<String> cbVehiculo;
    private com.toedter.calendar.JDateChooser jDateChooserFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jReporte;
    private javax.swing.JLabel jRuta;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jTanqueo;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel jVehiculo;
    private javax.swing.JPanel panelBarChart;
    private Vista.PanelRound panelInicio;
    private javax.swing.JPanel panelLineChart;
    private Vista.PanelRound panelRound1;
    private Vista.PanelRound panelRound14;
    private Vista.PanelRound panelRound15;
    private Vista.PanelRound panelRound2;
    private Vista.PanelRound panelRound3;
    private Vista.PanelRound panelRound4;
    private Vista.PanelRound panelRound5;
    private Vista.PanelRound panelRound6;
    private Vista.PanelRound panelRound7;
    private Vista.PanelRound panelRound9;
    private javax.swing.JTable tablaViajes;
    private javax.swing.JLabel txtAhorroHoy;
    private javax.swing.JLabel txtAhorroSemanaPasada;
    private javax.swing.JLabel txtFechaActual;
    private javax.swing.JLabel txtGastoHoy;
    private javax.swing.JLabel txtGastoSemanaPasada;
    // End of variables declaration//GEN-END:variables
    public void showLineChart(){
        Coneccion c = new Coneccion();
        Connection con = c.getConectar();
        ResultSet rs = null;
        PreparedStatement ps= null;
        try {
            ps=con.prepareStatement("SELECT ISNULL(SUM(pago_total) - LAG(SUM(pago_total)) OVER (ORDER BY MONTH(fecha_llenado)), 0) AS ahorro, CASE WHEN MONTH(fecha_llenado) = 1 THEN 'Enero' WHEN MONTH(fecha_llenado) = 2 THEN 'Febrero' WHEN MONTH(fecha_llenado) = 3 THEN 'Marzo' WHEN MONTH(fecha_llenado) = 4 THEN 'Abril' WHEN MONTH(fecha_llenado) = 5 THEN 'Mayo' WHEN MONTH(fecha_llenado) = 6 THEN 'Junio' WHEN MONTH(fecha_llenado) = 7 THEN 'Julio' WHEN MONTH(fecha_llenado) = 8 THEN 'Agosto' WHEN MONTH(fecha_llenado) = 9 THEN 'Septiembre' WHEN MONTH(fecha_llenado) = 10 THEN 'Octubre' WHEN MONTH(fecha_llenado) = 11 THEN 'Noviembre' WHEN MONTH(fecha_llenado) = 12 THEN 'Diciembre' END AS mes_letras FROM Tanqueo t INNER JOIN Ruta r ON t.id_ruta = r.id_ruta INNER JOIN Usuario u ON u.id_usuario = r.id_usuario WHERE u.id_usuario = ? AND YEAR(fecha_llenado) = YEAR(GETDATE()) GROUP BY MONTH(fecha_llenado) ORDER BY MONTH(fecha_llenado);");
            ps.setString(1, id_usuario);
            rs = ps.executeQuery();
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            while(rs.next()){
                dataset.setValue(rs.getDouble(1), "Soles", rs.getString(2));
            }
        
        JFreeChart linechart =ChartFactory.createLineChart("Ahorro por mes","Mes", "Soles", dataset, PlotOrientation.VERTICAL, true, true, true);
        
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
        
        public void showPiePanel() {        
        
        DefaultPieDataset barDataset = new DefaultPieDataset();
        barDataset.setValue("Ahorro", controller.ahorroanio(id_usuario));
        barDataset.setValue("Gasto", controller.ahorrogasto(id_usuario));
        //create chart
        JFreeChart piechart =ChartFactory.createPieChart("",barDataset, true, true, false);
        
        PiePlot piePlot =(PiePlot) piechart.getPlot();
     //changing pie chart blocks colors
        piePlot.setSectionPaint("Ahorro", new Color (10,204, 204));
        piePlot.setSectionPaint ("Gasto", new Color(102, 255, 102));
        piePlot.setBackgroundPaint (Color. white);
        piePlot.setOutlinePaint(Color. white);
        piePlot.setLabelOutlinePaint(Color. white);
        piePlot.setBaseSectionPaint(Color. white);
       //create chartPanel to display chart (gcaph)
       ChartPanel barChartPanel = new ChartPanel (piechart);
       panelBarChart.removeAll();
       panelBarChart.add(barChartPanel, BorderLayout.CENTER);
       panelBarChart.validate();

    }
}
