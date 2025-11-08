package br.com.fiap.dao;

import br.com.fiap.to.PacienteTO;
import java.sql.*;
import java.util.ArrayList;

public class PacienteDAO {

    public ArrayList<PacienteTO> findAll() {
        ArrayList<PacienteTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM ddd_paciente ORDER BY id_paciente";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PacienteTO p = new PacienteTO();
                p.setIdPaciente(rs.getLong("id_paciente"));
                p.setDsNome(rs.getString("ds_nome"));
                p.setNrIdade(rs.getInt("nr_idade"));
                p.setNrTelefone(rs.getString("nr_telefone"));
                p.setDsEmail(rs.getString("ds_email"));
                p.setDsSexo(rs.getString("ds_sexo"));
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar pacientes: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return lista;
    }

    public PacienteTO findByCodigo(Long id) {
        PacienteTO p = null;
        String sql = "SELECT * FROM ddd_paciente WHERE id_paciente = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                p = new PacienteTO();
                p.setIdPaciente(rs.getLong("id_paciente"));
                p.setDsNome(rs.getString("ds_nome"));
                p.setNrIdade(rs.getInt("nr_idade"));
                p.setNrTelefone(rs.getString("nr_telefone"));
                p.setDsEmail(rs.getString("ds_email"));
                p.setDsSexo(rs.getString("ds_sexo"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar paciente: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return p;
    }

    public PacienteTO save(PacienteTO p) {
        String sql = "INSERT INTO ddd_paciente (ds_nome, nr_idade, nr_telefone, ds_email, ds_sexo) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql, new String[]{"id_paciente"})) {
            ps.setString(1, p.getDsNome());
            ps.setInt(2, p.getNrIdade());
            ps.setString(3, p.getNrTelefone());
            ps.setString(4, p.getDsEmail());
            ps.setString(5, p.getDsSexo());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                p.setIdPaciente(rs.getLong(1));
            }
            return p;
        } catch (SQLException e) {
            System.out.println("Erro ao salvar paciente: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM ddd_paciente WHERE id_paciente = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir paciente: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public PacienteTO update(PacienteTO p) {
        String sql = "UPDATE ddd_paciente SET ds_nome=?, nr_idade=?, nr_telefone=?, ds_email=?, ds_sexo=? WHERE id_paciente=?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, p.getDsNome());
            ps.setInt(2, p.getNrIdade());
            ps.setString(3, p.getNrTelefone());
            ps.setString(4, p.getDsEmail());
            ps.setString(5, p.getDsSexo());
            ps.setLong(6, p.getIdPaciente());
            if (ps.executeUpdate() > 0) return p;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar paciente: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }
}
