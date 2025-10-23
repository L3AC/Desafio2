package MainFolder.dao;
import MainFolder.modelos.*;
import MainFolder.util.ConexionDB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterialObjeto {
    private static final Logger logger = LogManager.getLogger(MaterialObjeto.class);

    public boolean agregarMaterial(Material material) {
        String sqlMaterial = "INSERT INTO material (codigo, titulo, tipo, unidades_disponibles) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlMaterial, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, material.getCodigo());
            ps.setString(2, material.getTitulo());
            ps.setString(3, material.getTipo());
            ps.setInt(4, material.getUnidadesDisponibles());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    material.setId(id);
                    insertarDetalle(material, conn);
                    return true;
                }
            }
        } catch (SQLException e) {
            logger.error("Error al agregar material", e);
        }
        return false;
    }

    private void insertarDetalle(Material material, Connection conn) throws SQLException {
        switch (material.getTipo()) {
            case "libro" -> {
                Libro l = (Libro) material;
                String sql = "INSERT INTO libro VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, l.getId());
                    ps.setString(2, l.getAutor());
                    ps.setInt(3, l.getNumPaginas());
                    ps.setString(4, l.getEditorial());
                    ps.setString(5, l.getIsbn());
                    ps.setInt(6, l.getAñoPublicacion());
                    ps.executeUpdate();
                }
            }
            // Agrega casos para revista, cd_audio, dvd (similar)
        }
    }

    public List<Material> listarMateriales() {
        List<Material> lista = new ArrayList<>();
        String sql = "SELECT * FROM material ORDER BY id";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String codigo = rs.getString("codigo");
                String titulo = rs.getString("titulo");
                String tipo = rs.getString("tipo");
                int unidades = rs.getInt("unidades_disponibles");
                Timestamp fecha = rs.getTimestamp("fecha_registro");

                Material m = switch (tipo) {
                    case "libro" -> obtenerLibro(id, codigo, titulo, unidades, fecha);
                    case "revista" -> obtenerRevista(id, codigo, titulo, unidades, fecha);
                    case "cd_audio" -> obtenerCDAudio(id, codigo, titulo, unidades, fecha);
                    case "dvd" -> obtenerDVD(id, codigo, titulo, unidades, fecha);
                    default -> null;
                };
                if (m != null) lista.add(m);
            }
        } catch (SQLException e) {
            logger.error("Error al listar", e);
        }
        return lista;
    }

    // Métodos privados: obtenerLibro(), obtenerRevista(), etc. (similar a insertarDetalle)

    public boolean eliminarMaterial(int id) {
        String sql = "DELETE FROM material WHERE id = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error al eliminar", e);
            return false;
        }
    }

    public Material buscarPorCodigo(String codigo) {
        // Implementación similar a listar, pero con WHERE
        return null; // Simplificado
    }
}
