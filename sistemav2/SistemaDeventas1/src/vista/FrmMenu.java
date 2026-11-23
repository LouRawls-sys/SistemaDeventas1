package vista;

import controlador.Reportes;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;


public class FrmMenu extends JFrame {

    public static JDesktopPane jDesktopPane_menu;
    private static final Color AZUL = new Color(36, 59, 83);       
    private static final Color AZUL_HOVER = new Color(49, 82, 115);
    private static final Color MARFIL = new Color(245, 243, 239);  
    private static final Color CARBON = new Color(41, 49, 58);     
    private static final Color GRIS = new Color(220, 228, 234);    
    private static final Color ROJO = new Color(198, 93, 93);      
    private JPanel dashboard;

    public FrmMenu() {
        configurarVentana();
        construirInterfaz();
    }

    private void configurarVentana() {
        setTitle("Sistema de Barbería");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1000, 650));
        setSize(1200, 750);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private void construirInterfaz() {
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(MARFIL);

        JPanel sidebar = new JPanel(new BorderLayout());
        sidebar.setBackground(AZUL);
        sidebar.setPreferredSize(new Dimension(235, 0));
        sidebar.add(crearMarca(), BorderLayout.NORTH);

        JPanel opciones = new JPanel();
        opciones.setBackground(AZUL);
        opciones.setBorder(new EmptyBorder(12, 12, 12, 12));
        opciones.setLayout(new javax.swing.BoxLayout(opciones, javax.swing.BoxLayout.Y_AXIS));
        opciones.add(etiquetaSeccion("MENÚ PRINCIPAL"));
        opciones.add(boton("Inicio", "/img/reporte.png", e -> mostrarDashboard()));
        opciones.add(espacio(10));
        opciones.add(etiquetaSeccion("GESTIÓN"));
        opciones.add(boton("Usuarios", "/img/usuario.png", e -> jMenuItem_nuevo_usuarioActionPerformed(e)));
        opciones.add(boton("Productos", "/img/producto.png", e -> jMenuItem3_nuevo_productoActionPerformed(e)));
        opciones.add(boton("Clientes", "/img/cliente.png", e -> jMenuItem_nuevo_clienteActionPerformed(e)));
        opciones.add(boton("Categorías", "/img/categorias.png", e -> jMenuItem_nueva_categoriaActionPerformed(e)));
        opciones.add(boton("Facturación", "/img/carrito.png", e -> jMenuItem_nueva_ventaActionPerformed(e)));
        opciones.add(espacio(10));
        opciones.add(etiquetaSeccion("CONSULTAS"));
        opciones.add(boton("Reportes", "/img/reportes.png", e -> jMenuItem_reportes_ventasActionPerformed(e)));
        opciones.add(boton("Historial", "/img/historial1.png", e -> jMenuItem_ver_historialActionPerformed(e)));
        opciones.add(espacio(10));
        opciones.add(etiquetaSeccion("ADMINISTRACIÓN"));
        opciones.add(boton("Gestionar usuarios", "/img/configuraciones.png", e -> jMenuItem_gestionar_usuarioActionPerformed(e)));
        opciones.add(boton("Gestionar productos", "/img/producto.png", e -> jMenuItem_gestionar_productoActionPerformed(e)));
        opciones.add(boton("Gestionar clientes", "/img/cliente.png", e -> jMenuItem_gestionar_clienteActionPerformed(e)));
        opciones.add(boton("Gestionar categorías", "/img/categorias.png", e -> jMenuItem_gestionar_categoriasActionPerformed(e)));
        opciones.add(boton("Gestionar ventas", "/img/carrito.png", e -> jMenuItem_gestionar_ventasActionPerformed(e)));
        opciones.add(boton("Actualizar stock", "/img/inventario.png", e -> jMenuItem_actualizar_stockActionPerformed(e)));

        JScrollPane scrollOpciones = new JScrollPane(opciones);
        scrollOpciones.setBorder(null);
        scrollOpciones.getViewport().setBackground(AZUL);
        scrollOpciones.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollOpciones.getVerticalScrollBar().setUnitIncrement(16);
        sidebar.add(scrollOpciones, BorderLayout.CENTER);

        JButton salir = boton("Cerrar sesión", "/img/cerrar-sesion.png", e -> jMenuItem_cerrar_sesionActionPerformed(e));
        salir.setForeground(new Color(255, 190, 190));
        JPanel pie = new JPanel(new BorderLayout());
        pie.setBackground(AZUL);
        pie.setBorder(new EmptyBorder(8, 12, 14, 12));
        pie.add(salir, BorderLayout.CENTER);
        sidebar.add(pie, BorderLayout.SOUTH);

        JPanel zonaTrabajo = new JPanel(new BorderLayout());
        zonaTrabajo.setBackground(MARFIL);
        zonaTrabajo.add(crearBarraSuperior(), BorderLayout.NORTH);
        jDesktopPane_menu = new JDesktopPane();
        jDesktopPane_menu.setBackground(MARFIL);
        jDesktopPane_menu.setBorder(null);
        dashboard = crearDashboard();
        dashboard.setBounds(0, 0, 1200, 700);
        jDesktopPane_menu.add(dashboard, Integer.valueOf(Integer.MIN_VALUE));
        zonaTrabajo.add(jDesktopPane_menu, BorderLayout.CENTER);

        getContentPane().add(sidebar, BorderLayout.WEST);
        getContentPane().add(zonaTrabajo, BorderLayout.CENTER);
    }

    private JPanel crearMarca() {
        JPanel marca = new JPanel(new BorderLayout(8, 4));
        marca.setBackground(AZUL);
        marca.setBorder(new EmptyBorder(22, 20, 22, 12));
        JLabel icono = new JLabel("✂", SwingConstants.CENTER);
        icono.setForeground(Color.WHITE);
        icono.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 32));
        icono.setPreferredSize(new Dimension(42, 42));
        JLabel nombre = new JLabel("BARBER");
        nombre.setForeground(Color.WHITE);
        nombre.setFont(new Font("Segoe UI", Font.BOLD, 22));
        JLabel subtitulo = new JLabel("SISTEMA DE GESTIÓN");
        subtitulo.setForeground(new Color(211, 224, 237));
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        JPanel textos = new JPanel();
        textos.setOpaque(false);
        textos.setLayout(new javax.swing.BoxLayout(textos, javax.swing.BoxLayout.Y_AXIS));
        textos.add(nombre);
        textos.add(subtitulo);
        marca.add(icono, BorderLayout.WEST);
        marca.add(textos, BorderLayout.CENTER);
        return marca;
    }

    private JPanel crearBarraSuperior() {
        JPanel barra = new JPanel(new BorderLayout());
        barra.setBackground(Color.WHITE);
        barra.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, GRIS),
                new EmptyBorder(15, 24, 15, 24)));
        JLabel titulo = new JLabel("Panel de trabajo");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titulo.setForeground(CARBON);
        JLabel usuario = new JLabel("Sistema de Barbería   •   Administración");
        usuario.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        usuario.setForeground(new Color(100, 116, 139));
        barra.add(titulo, BorderLayout.WEST);
        barra.add(usuario, BorderLayout.EAST);
        return barra;
    }

    private JPanel crearDashboard() {
        JPanel panel = new JPanel(new BorderLayout(0, 22));
        panel.setBackground(MARFIL);
        panel.setBorder(new EmptyBorder(30, 34, 30, 34));

        JPanel encabezado = new JPanel();
        encabezado.setOpaque(false);
        encabezado.setLayout(new javax.swing.BoxLayout(encabezado, javax.swing.BoxLayout.Y_AXIS));
        JLabel titulo = new JLabel("¡Bienvenido!");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 30));
        titulo.setForeground(CARBON);
        JLabel descripcion = new JLabel("Administra tu barbería desde un solo lugar.");
        descripcion.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        descripcion.setForeground(new Color(100, 116, 139));
        encabezado.add(titulo);
        encabezado.add(javax.swing.Box.createVerticalStrut(6));
        encabezado.add(descripcion);
        panel.add(encabezado, BorderLayout.NORTH);

        JPanel contenido = new JPanel(new BorderLayout(0, 18));
        contenido.setOpaque(false);
        JPanel tarjetas = new JPanel(new java.awt.GridLayout(2, 2, 16, 16));
        tarjetas.setOpaque(false);
        tarjetas.add(tarjeta("CLIENTES", "Registra y consulta clientes", "", e -> jMenuItem_nuevo_clienteActionPerformed(e)));
        tarjetas.add(tarjeta("PRODUCTOS", "Administra el inventario", "", e -> jMenuItem3_nuevo_productoActionPerformed(e)));
        tarjetas.add(tarjeta("NUEVA VENTA", "Registra una venta", "", e -> jMenuItem_nueva_ventaActionPerformed(e)));
        tarjetas.add(tarjeta("REPORTES", "Consulta información del negocio", "", e -> jMenuItem_reportes_ventasActionPerformed(e)));
        contenido.add(tarjetas, BorderLayout.NORTH);

        JPanel nota = new JPanel(new BorderLayout(12, 0));
        nota.setBackground(Color.WHITE);
        nota.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 232, 235)),
                new EmptyBorder(18, 20, 18, 20)));
        JLabel simbolo = new JLabel("✦");
        simbolo.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 27));
        simbolo.setForeground(ROJO);
        JPanel textos = new JPanel();
        textos.setOpaque(false);
        textos.setLayout(new javax.swing.BoxLayout(textos, javax.swing.BoxLayout.Y_AXIS));
        JLabel subtitulo = new JLabel("");
        subtitulo.setFont(new Font("Segoe UI", Font.BOLD, 15));
        subtitulo.setForeground(CARBON);
        JLabel detalle = new JLabel("Selecciona un módulo del menú para comenzar. Puedes mantener varias ventanas abiertas.");
        detalle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        detalle.setForeground(new Color(100, 116, 139));
        textos.add(subtitulo);
        textos.add(javax.swing.Box.createVerticalStrut(5));
        textos.add(detalle);
        nota.add(simbolo, BorderLayout.WEST);
        nota.add(textos, BorderLayout.CENTER);
        contenido.add(nota, BorderLayout.CENTER);
        panel.add(contenido, BorderLayout.CENTER);
        return panel;
    }

    private JPanel tarjeta(String titulo, String descripcion, String icono, java.util.function.Consumer<ActionEvent> accion) {
        JPanel tarjeta = new JPanel(new BorderLayout(0, 12));
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 232, 235)),
                new EmptyBorder(18, 20, 18, 20)));
        JLabel ico = new JLabel(icono);
        ico.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 27));
        ico.setForeground(AZUL);
        JLabel t = new JLabel(titulo);
        t.setFont(new Font("Segoe UI", Font.BOLD, 15));
        t.setForeground(CARBON);
        JLabel d = new JLabel("<html><div style='width:190px'>" + descripcion + "</div></html>");
        d.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        d.setForeground(new Color(100, 116, 139));
        JButton abrir = new JButton("Abrir módulo  →");
        estilizarBoton(abrir, false);
        abrir.addActionListener(e -> accion.accept(e));
        JPanel texto = new JPanel();
        texto.setOpaque(false);
        texto.setLayout(new javax.swing.BoxLayout(texto, javax.swing.BoxLayout.Y_AXIS));
        texto.add(t);
        texto.add(javax.swing.Box.createVerticalStrut(5));
        texto.add(d);
        tarjeta.add(ico, BorderLayout.WEST);
        tarjeta.add(texto, BorderLayout.CENTER);
        tarjeta.add(abrir, BorderLayout.SOUTH);
        return tarjeta;
    }

    private JLabel etiquetaSeccion(String texto) {
        JLabel l = new JLabel(texto);
        l.setForeground(new Color(171, 194, 215));
        l.setFont(new Font("Segoe UI", Font.BOLD, 10));
        l.setBorder(new EmptyBorder(8, 10, 8, 4));
        l.setAlignmentX(LEFT_ALIGNMENT);
        return l;
    }

    private JButton boton(String texto, String rutaIcono, java.util.function.Consumer<ActionEvent> accion) {
        JButton b = new JButton(texto);
        java.net.URL recurso = getClass().getResource(rutaIcono);
        if (recurso != null) {
            ImageIcon original = new ImageIcon(recurso);
            java.awt.Image imagen = original.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
            b.setIcon(new ImageIcon(imagen));
            b.setIconTextGap(14);
        } else {
            System.err.println("No se encontró el icono: " + rutaIcono);
        }
        b.setHorizontalAlignment(SwingConstants.LEFT);
        b.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        b.setPreferredSize(new Dimension(205, 42));
        b.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        b.setForeground(Color.WHITE);
        b.setBackground(AZUL);
        b.setBorder(new EmptyBorder(8, 10, 8, 8));
        b.setFocusPainted(false);
        b.setOpaque(true);
        b.setAlignmentX(LEFT_ALIGNMENT);
        b.addActionListener(accion::accept);
        b.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseEntered(java.awt.event.MouseEvent e) { b.setBackground(AZUL_HOVER); }
            @Override public void mouseExited(java.awt.event.MouseEvent e) { b.setBackground(AZUL); }
        });
        return b;
    }

    private void estilizarBoton(JButton b, boolean peligro) {
        b.setFont(new Font("Segoe UI", Font.BOLD, 12));
        b.setForeground(peligro ? Color.WHITE : AZUL);
        b.setBackground(peligro ? ROJO : GRIS);
        b.setFocusPainted(false);
        b.setBorder(new EmptyBorder(9, 12, 9, 12));
        b.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    private java.awt.Component espacio(int alto) {
        return javax.swing.Box.createVerticalStrut(alto);
    }

    private void mostrarDashboard() {
        dashboard.setBounds(0, 0, jDesktopPane_menu.getWidth(), jDesktopPane_menu.getHeight());
        dashboard.setVisible(true);
        dashboard.repaint();
    }

    @Override
    public void doLayout() {
        super.doLayout();
        if (dashboard != null && jDesktopPane_menu != null) {
            dashboard.setBounds(0, 0, jDesktopPane_menu.getWidth(), jDesktopPane_menu.getHeight());
        }
    }

    private void jMenuItem_reportes_ventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_reportes_ventasActionPerformed
        Reportes reporte = new Reportes();
        reporte.ReportesVentas();
    }

    private void jMenuItem_nueva_categoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_nueva_categoriaActionPerformed
        InterCategoria interCategoria = new InterCategoria();
        jDesktopPane_menu.add(interCategoria);
        interCategoria.setVisible(true);
    }

    private void jMenuItem_gestionar_categoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_gestionar_categoriasActionPerformed
        InterGestionarCategoria interGestionarCategoria = new InterGestionarCategoria();
        jDesktopPane_menu.add(interGestionarCategoria);
        interGestionarCategoria.setVisible(true);
    }

    private void jMenuItem3_nuevo_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3_nuevo_productoActionPerformed
        InterProducto interProducto = new InterProducto();
        jDesktopPane_menu.add(interProducto);
        interProducto.setVisible(true);
    }

    private void jMenuItem_gestionar_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_gestionar_productoActionPerformed
        InterGestionarProducto interGestionarProducto = new InterGestionarProducto();
        jDesktopPane_menu.add(interGestionarProducto);
        interGestionarProducto.setVisible(true);
    }

    private void jMenuItem_nuevo_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_nuevo_clienteActionPerformed
        InterCliente interCliente = new InterCliente();
        jDesktopPane_menu.add(interCliente);
        interCliente.setVisible(true);
    }

    private void jMenuItem_gestionar_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_gestionar_clienteActionPerformed
        InterGestionarCliente interGestionarCliente = new InterGestionarCliente();
        jDesktopPane_menu.add(interGestionarCliente);
        interGestionarCliente.setVisible(true);
    }

    private void jMenuItem_actualizar_stockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_actualizar_stockActionPerformed
        InterActualizarStock interActualizarStock = new InterActualizarStock();
        jDesktopPane_menu.add(interActualizarStock);
        interActualizarStock.setVisible(true);
    }

    private void jMenuItem_cerrar_sesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_cerrar_sesionActionPerformed
        System.exit(0);
    }

    private void jMenuItem_nuevo_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_nuevo_usuarioActionPerformed
        InterUsuario interUsuario = new InterUsuario();
        jDesktopPane_menu.add(interUsuario);
        interUsuario.setVisible(true);
    }

    private void jMenuItem_gestionar_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_gestionar_usuarioActionPerformed
        InterGestionarUsuario interGestionarUsuario = new InterGestionarUsuario();
        jDesktopPane_menu.add(interGestionarUsuario);
        interGestionarUsuario.setVisible(true);
    }

    private void jMenuItem_nueva_ventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_nueva_ventaActionPerformed
        InterFacturacion interFacturacion = new InterFacturacion();
        jDesktopPane_menu.add(interFacturacion);
        interFacturacion.setVisible(true);
    }

    private void jMenuItem_gestionar_ventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_gestionar_ventasActionPerformed
        InterGestionarVentas interGestionarVentas = new InterGestionarVentas();
        jDesktopPane_menu.add(interGestionarVentas);
        interGestionarVentas.setVisible(true);
    }

    private void jMenuItem_reportes_clientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_reportes_clientesActionPerformed
        Reportes reporte = new Reportes();
        reporte.ReportesClientes();
    }

    private void jMenuItem_reportes_productosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_reportes_productosActionPerformed
        Reportes reporte = new Reportes();
        reporte.ReportesProductos();
    }

    private void jMenuItem_reportes_categoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_reportes_categoriasActionPerformed
        Reportes reporte = new Reportes();
        reporte.ReportesCategorias();
    }

    private void jMenuItem_ver_historialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_ver_historialActionPerformed
        InterGraficas interGraficas = new InterGraficas();
        jDesktopPane_menu.add(interGraficas);
        interGraficas.setVisible(true);
    }

    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(FrmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new FrmMenu().setVisible(true));
    }
}
