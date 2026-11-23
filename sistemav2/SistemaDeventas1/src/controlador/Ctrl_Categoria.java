package controlador;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.Categoria;

/**
 *
 * @author Edison Zambrano - © Programador Fantasma
 */
public class Ctrl_Categoria {

    /**
     * **************************************************
     * metodo para guardar una nueva categoria
     * **************************************************
     */
    public boolean guardar(Categoria objeto) {
        boolean respuesta = false;
        Connection cn = conexion.Conexion.conectar();
        try {

            PreparedStatement consulta = cn.prepareStatement("insert into tb_categoria (idCategoria, descripcion) values(?,?)");
            consulta.setInt(1, 0);
            consulta.setString(2, objeto.getDescripcion());

            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al guardar cartegoria: " + e);
        }

        return respuesta;
    }

    /**
     * ********************************************************************
     * metodo para consultar si la categoria registrado ya existe
     * ********************************************************************
     */
    public boolean existeCategoria(String categoria) {
        boolean respuesta = false;
        String sql = "select descripcion from tb_categoria where descripcion = '" + categoria + "';";
        Statement st;

        try {
            Connection cn = Conexion.conectar();
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                respuesta = true;
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar cartegoria: " + e);
        }
        return respuesta;
    }
    
     /**
     * **************************************************
     * metodo para actualizar una nueva categoria
     * **************************************************
     */
    public boolean actualizar(Categoria objeto, int idCategoria) {
        boolean respuesta = false;
        Connection cn = conexion.Conexion.conectar();
        try {

            PreparedStatement consulta = cn.prepareStatement("update tb_categoria set descripcion=? where idCategoria ='" + idCategoria + "'");
            consulta.setString(1, objeto.getDescripcion());
           
            if (consulta.executeUpdate() > 0) {
                respuesta = true;
            }

            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al actualizar cartegoria: " + e);
        }

        return respuesta;
    }
    
    
    /**
     * Comprueba si existen productos asociados a una categoria.
     * Una categoria en uso no debe eliminarse para evitar dejar productos
     * sin una categoria valida.
     */
    public boolean tieneProductosAsociados(int idCategoria) {
        String sql = "SELECT 1 FROM tb_producto WHERE idCategoria = ? LIMIT 1";

        try (Connection cn = Conexion.conectar();
             PreparedStatement consulta = cn.prepareStatement(sql)) {

            consulta.setInt(1, idCategoria);
            try (ResultSet rs = consulta.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.out.println("Error al comprobar productos de la categoria: " + e);
            // Ante un error no permitimos continuar con la eliminacion.
            return true;
        }
    }

    /**
     * Elimina una categoria solo si no tiene productos asociados.
     * La condicion NOT EXISTS vuelve a validar la relacion en el momento
     * de ejecutar el DELETE.
     */
    public boolean eliminar(int idCategoria) {
        String sql = "DELETE FROM tb_categoria "
                   + "WHERE idCategoria = ? "
                   + "AND NOT EXISTS ("
                   + "SELECT 1 FROM tb_producto WHERE idCategoria = ?"
                   + ")";

        try (Connection cn = Conexion.conectar();
             PreparedStatement consulta = cn.prepareStatement(sql)) {

            consulta.setInt(1, idCategoria);
            consulta.setInt(2, idCategoria);
            return consulta.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar categoria: " + e);
            return false;
        }
    }
}
