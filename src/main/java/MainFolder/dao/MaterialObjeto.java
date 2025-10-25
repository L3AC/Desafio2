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

    // === AGREGAR ===
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
            logger.error("Error al agregar material: " + material.getCodigo(), e);
        }
        return false;
    }

    private void insertarDetalle(Material material, Connection conn) throws SQLException {
        switch (material.getTipo()) {
            case "libro" -> {
                Libro l = (Libro) material;
                String sql = "INSERT INTO libro (id_material, autor, num_paginas, editorial, isbn, año_publicacion) VALUES (?, ?, ?, ?, ?, ?)";
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
            case "revista" -> {
                Revista r = (Revista) material;
                String sql = "INSERT INTO revista (id_material, editorial, periodicidad, fecha_publicacion) VALUES (?, ?, ?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, r.getId());
                    ps.setString(2, r.getEditorial());
                    ps.setString(3, r.getPeriodicidad());
                    ps.setDate(4, r.getFechaPublicacion());
                    ps.executeUpdate();
                }
            }
            case "cd_audio" -> {
                CDAudio cd = (CDAudio) material;
                String sql = "INSERT INTO cd_audio (id_material, artista, genero, duracion, num_canciones) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, cd.getId());
                    ps.setString(2, cd.getArtista());
                    ps.setString(3, cd.getGenero());
                    ps.setTime(4, cd.getDuracion());
                    ps.setInt(5, cd.getNumCanciones());
                    ps.executeUpdate();
                }
            }
            case "dvd" -> {
                DVD dvd = (DVD) material;
                String sql = "INSERT INTO dvd (id_material, director, duracion, genero) VALUES (?, ?, ?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, dvd.getId());
                    ps.setString(2, dvd.getDirector());
                    ps.setTime(3, dvd.getDuracion());
                    ps.setString(4, dvd.getGenero());
                    ps.executeUpdate();
                }
            }
        }
    }

    // === LISTAR ===
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

                Material material = switch (tipo) {
                    case "libro" -> obtenerLibro(conn, id, codigo, titulo, unidades, fecha);
                    case "revista" -> obtenerRevista(conn, id, codigo, titulo, unidades, fecha);
                    case "cd_audio" -> obtenerCDAudio(conn, id, codigo, titulo, unidades, fecha);
                    case "dvd" -> obtenerDVD(conn, id, codigo, titulo, unidades, fecha);
                    default -> null;
                };
                if (material != null) lista.add(material);
            }
        } catch (SQLException e) {
            logger.error("Error al listar materiales", e);
        }
        return lista;
    }

    // === MÉTODOS PRIVADOS PARA OBTENER DETALLES ===
    private Libro obtenerLibro(Connection conn, int id, String codigo, String titulo, int unidades, Timestamp fecha) {
        String sql = "SELECT * FROM libro WHERE id_material = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Libro(
                    id, codigo, titulo, unidades,
                    rs.getString("autor"),
                    rs.getInt("num_paginas"),
                    rs.getString("editorial"),
                    rs.getString("isbn"),
                    rs.getInt("año_publicacion")
                );
            }
        } catch (SQLException e) {
            logger.error("Error al obtener libro con ID: " + id, e);
        }
        return null;
    }

    private Revista obtenerRevista(Connection conn, int id, String codigo, String titulo, int unidades, Timestamp fecha) {
        String sql = "SELECT * FROM revista WHERE id_material = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Revista(
                    id, codigo, titulo, unidades,
                    rs.getString("editorial"),
                    rs.getString("periodicidad"),
                    rs.getDate("fecha_publicacion")
                );
            }
        } catch (SQLException e) {
            logger.error("Error al obtener revista con ID: " + id, e);
        }
        return null;
    }

    private CDAudio obtenerCDAudio(Connection conn, int id, String codigo, String titulo, int unidades, Timestamp fecha) {
        String sql = "SELECT * FROM cd_audio WHERE id_material = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new CDAudio(
                    id, codigo, titulo, unidades,
                    rs.getString("artista"),
                    rs.getString("genero"),
                    rs.getTime("duracion"),
                    rs.getInt("num_canciones")
                );
            }
        } catch (SQLException e) {
            logger.error("Error al obtener CD con ID: " + id, e);
        }
        return null;
    }

    private DVD obtenerDVD(Connection conn, int id, String codigo, String titulo, int unidades, Timestamp fecha) {
        String sql = "SELECT * FROM dvd WHERE id_material = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new DVD(
                    id, codigo, titulo, unidades,
                    rs.getString("director"),
                    rs.getTime("duracion"),
                    rs.getString("genero")
                );
            }
        } catch (SQLException e) {
            logger.error("Error al obtener DVD con ID: " + id, e);
        }
        return null;
    }

    // === ELIMINAR ===
    public boolean eliminarMaterial(int id) {
        String sql = "DELETE FROM material WHERE id = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error al eliminar material con ID: " + id, e);
            return false;
        }
    }

    // === BUSCAR POR CÓDIGO (opcional, útil para modificar) ===
    public Material buscarPorCodigo(String codigo) {
        String sql = "SELECT * FROM material WHERE codigo = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String tipo = rs.getString("tipo");
                int unidades = rs.getInt("unidades_disponibles");
                Timestamp fecha = rs.getTimestamp("fecha_registro");

                return switch (tipo) {
                    case "libro" -> obtenerLibro(conn, id, codigo, titulo, unidades, fecha);
                    case "revista" -> obtenerRevista(conn, id, codigo, titulo, unidades, fecha);
                    case "cd_audio" -> obtenerCDAudio(conn, id, codigo, titulo, unidades, fecha);
                    case "dvd" -> obtenerDVD(conn, id, codigo, titulo, unidades, fecha);
                    default -> null;
                };
            }
        } catch (SQLException e) {
            logger.error("Error al buscar material por código: " + codigo, e);
        }
        return null;
    }
}
