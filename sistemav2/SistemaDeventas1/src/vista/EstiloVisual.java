package vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import javax.swing.AbstractButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;
import javax.swing.table.JTableHeader;

/**
 * Estilos de presentación compartidos por las ventanas internas.
 * No modifica el contenido, los nombres, los eventos ni la distribución
 * definida en los archivos .form de NetBeans.
 */
public final class EstiloVisual {
    public static final Color AZUL = new Color(36, 59, 83);          // #243B53
    public static final Color MARFIL = new Color(245, 243, 239);     // #F5F3EF
    public static final Color GRIS_AZULADO = new Color(220, 228, 234);// #DCE4EA
    public static final Color CARBON = new Color(41, 49, 58);        // #29313A
    public static final Color ROJO = new Color(168, 74, 74);         // #A84A4A
    public static final Color BLANCO = Color.WHITE;
    private static final Font TEXTO = new Font("Segoe UI", Font.PLAIN, 13);
    private static final Font ETIQUETA = new Font("Segoe UI", Font.BOLD, 13);
    private static final Font TITULO = new Font("Segoe UI", Font.BOLD, 18);

    private EstiloVisual() { }

    public static void aplicar(javax.swing.JInternalFrame ventana) {
        ventana.getContentPane().setBackground(MARFIL);
        aplicarRecursivo(ventana.getContentPane());
    }

    private static void aplicarRecursivo(Container contenedor) {
        for (Component componente : contenedor.getComponents()) {
            if (componente instanceof JPanel) {
                componente.setBackground(MARFIL);
                ((JPanel) componente).setOpaque(true);
            }

            if (componente instanceof JLabel) {
                JLabel etiqueta = (JLabel) componente;
                // No alterar etiquetas que contienen imágenes de fondo o iconos.
                if (etiqueta.getIcon() == null && etiqueta.getText() != null
                        && !etiqueta.getText().trim().isEmpty()) {
                    etiqueta.setForeground(CARBON);
                    etiqueta.setFont(etiqueta.getFont().getSize() >= 18 ? TITULO : ETIQUETA);
                }
            } else if (componente instanceof JTextField || componente instanceof JPasswordField
                    || componente instanceof JTextArea) {
                JComponent campo = (JComponent) componente;
                campo.setFont(TEXTO);
                campo.setForeground(CARBON);
                campo.setBackground(BLANCO);
                campo.setBorder(new LineBorder(GRIS_AZULADO, 1, true));
                if (componente instanceof JTextField) ((JTextField) componente).setCaretColor(AZUL);
                if (componente instanceof JTextArea) ((JTextArea) componente).setCaretColor(AZUL);
            } else if (componente instanceof JComboBox) {
                JComboBox<?> combo = (JComboBox<?>) componente;
                combo.setFont(TEXTO);
                combo.setForeground(CARBON);
                combo.setBackground(BLANCO);
            } else if (componente instanceof JTable) {
                JTable tabla = (JTable) componente;
                tabla.setFont(TEXTO);
                tabla.setForeground(CARBON);
                tabla.setBackground(BLANCO);
                tabla.setSelectionBackground(GRIS_AZULADO);
                tabla.setSelectionForeground(CARBON);
                tabla.setGridColor(GRIS_AZULADO);
                JTableHeader encabezado = tabla.getTableHeader();
                if (encabezado != null) {
                    encabezado.setBackground(AZUL);
                    encabezado.setForeground(BLANCO);
                    encabezado.setFont(ETIQUETA);
                    encabezado.setOpaque(true);
                }
            } else if (componente instanceof AbstractButton) {
                AbstractButton boton = (AbstractButton) componente;
                String texto = boton.getText() == null ? "" : boton.getText().toLowerCase();
                boolean destructivo = texto.contains("eliminar") || texto.contains("borrar")
                        || texto.contains("cancelar") || texto.contains("salir");
                Color color = destructivo ? ROJO : AZUL;
                boton.setFont(ETIQUETA);
                boton.setForeground(BLANCO);
                boton.setBackground(color);
                boton.setOpaque(true);
                boton.setFocusPainted(false);
                boton.setBorder(new LineBorder(color, 1, true));
            } else if (componente instanceof JScrollPane) {
                JScrollPane scroll = (JScrollPane) componente;
                scroll.setBackground(BLANCO);
                scroll.getViewport().setBackground(BLANCO);
            }

            if (componente instanceof Container) {
                aplicarRecursivo((Container) componente);
            }
        }
    }
}
