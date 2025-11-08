package br.com.fiap.dao;

import br.com.fiap.to.AcompanhanteTO;
import java.sql.*;
import java.util.ArrayList;

public class AcompanhanteDAO {

    public ArrayList<AcompanhanteTO> findAll() {
        ArrayList<AcompanhanteTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM ddd_acompanhante ORDER BY cd_acompanhante";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AcompanhanteTO a = new AcompanhanteTO();
                a.setCdAcompanhante(rs.getLong("cd_acompanhante"));
                a.setDsNomeAcompanhante(rs.getString("ds_nome_acompanhante"));
                a.setNrCpf(rs.getString("nr_cpf"));
                a.setNrTelefone(rs.getString("nr_telefone"));
                a.setDsParentesco(rs.getString("ds_parentesco"));
                lista.add(a);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar acompanhantes: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return lista;
    }

    public AcompanhanteTO findByCodigo(Long id) {
        AcompanhanteTO a = null;
        String sql = "SELECT * FROM ddd_acompanhante WHERE cd_acompanhante = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                a = new AcompanhanteTO();
                a.setCdAcompanhante(rs.getLong("cd_acompanhante"));
                a.setDsNomeAcompanhante(rs.getString("ds_nome_acompanhante"));
                a.setNrCpf(rs.getString("nr_cpf"));
                a.setNrTelefone(rs.getString("nr_telefone"));
                a.setDsParentesco(rs.getString("ds_parentesco"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar acompanhante: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return a;
    }

    public AcompanhanteTO save(AcompanhanteTO a) {
        String sql = "INSERT INTO ddd_acompanhante (ds_nome_acompanhante, nr_cpf, nr_telefone, ds_parentesco) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql, new String[]{"cd_acompanhante"})) {
            ps.setString(1, a.getDsNomeAcompanhante());
            ps.setString(2, a.getNrCpf());
            ps.setString(3, a.getNrTelefone());
            ps.setString(4, a.getDsParentesco());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                a.setCdAcompanhante(rs.getLong(1));
            }
            return a;
        } catch (SQLException e) {
            System.out.println("Erro ao salvar acompanhante: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM ddd_acompanhante WHERE cd_acompanhante = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir acompanhante: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public AcompanhanteTO update(AcompanhanteTO a) {
        String sql = "UPDATE ddd_acompanhante SET ds_nome_acompanhante=?, nr_cpf=?, nr_telefone=?, ds_parentesco=? WHERE cd_acompanhante=?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, a.getDsNomeAcompanhante());
            ps.setString(2, a.getNrCpf());
            ps.setString(3, a.getNrTelefone());
            ps.setString(4, a.getDsParentesco());
            ps.setLong(5, a.getCdAcompanhante());
            if (ps.executeUpdate() > 0) return a;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar acompanhante: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }
}
