package br.com.fiap.dao;

import br.com.fiap.to.EspecialidadeTO;
import java.sql.*;
import java.util.ArrayList;

public class EspecialidadeDAO {

    public ArrayList<EspecialidadeTO> findAll() {
        ArrayList<EspecialidadeTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM ddd_especialidade ORDER BY cd_especialidade";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                EspecialidadeTO e = new EspecialidadeTO(
                        rs.getLong("cd_especialidade"),
                        rs.getString("ds_segmento"),
                        rs.getString("ds_turno"),
                        rs.getString("ds_grau_de_prioridade")
                );
                lista.add(e);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar especialidades: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return lista;
    }

    public EspecialidadeTO findByCodigo(Long codigo) {
        EspecialidadeTO e = null;
        String sql = "SELECT * FROM ddd_especialidade WHERE cd_especialidade = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                e = new EspecialidadeTO(
                        rs.getLong("cd_especialidade"),
                        rs.getString("ds_segmento"),
                        rs.getString("ds_turno"),
                        rs.getString("ds_grau_de_prioridade")
                );
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar especialidade: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return e;
    }

    public EspecialidadeTO save(EspecialidadeTO e) {
        String sql = "INSERT INTO ddd_especialidade (ds_segmento, ds_turno, ds_grau_de_prioridade) VALUES (?, ?, ?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql, new String[]{"cd_especialidade"})) {
            ps.setString(1, e.getDsSegmento());
            ps.setString(2, e.getDsTurno());
            ps.setString(3, e.getDsGrauDePrioridade());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                e.setCdEspecialidade(rs.getLong(1));
            }
            rs.close();
            return e;
        } catch (SQLException ex) {
            System.out.println("Erro ao salvar especialidade: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Long codigo) {
        String sql = "DELETE FROM ddd_especialidade WHERE cd_especialidade = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, codigo);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Erro ao excluir especialidade: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public EspecialidadeTO update(EspecialidadeTO e) {
        String sql = "UPDATE ddd_especialidade SET ds_segmento=?, ds_turno=?, ds_grau_de_prioridade=? WHERE cd_especialidade=?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, e.getDsSegmento());
            ps.setString(2, e.getDsTurno());
            ps.setString(3, e.getDsGrauDePrioridade());
            ps.setLong(4, e.getCdEspecialidade());
            ps.executeUpdate();
            return e;
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar especialidade: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }
}
