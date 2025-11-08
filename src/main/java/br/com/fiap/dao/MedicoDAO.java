package br.com.fiap.dao;

import br.com.fiap.to.MedicoTO;
import java.sql.*;
import java.util.ArrayList;

public class MedicoDAO {

    public ArrayList<MedicoTO> findAll() {
        ArrayList<MedicoTO> medicos = new ArrayList<>();
        String sql = "SELECT * FROM DDD_MEDICO ORDER BY NR_CRM";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MedicoTO m = new MedicoTO(
                        rs.getLong("NR_CRM"),
                        rs.getLong("CD_ESPECIALIDADE"),
                        rs.getString("DS_SEGMENTO"),
                        rs.getString("DS_TURNO"),
                        rs.getString("NM_COMPLETO"),
                        rs.getString("DS_EMAIL")
                );
                medicos.add(m);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar médicos: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return medicos;
    }

    public MedicoTO findByCodigo(Long crm) {
        MedicoTO m = null;
        String sql = "SELECT * FROM DDD_MEDICO WHERE NR_CRM = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, crm);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                m = new MedicoTO(
                        rs.getLong("NR_CRM"),
                        rs.getLong("CD_ESPECIALIDADE"),
                        rs.getString("DS_SEGMENTO"),
                        rs.getString("DS_TURNO"),
                        rs.getString("NM_COMPLETO"),
                        rs.getString("DS_EMAIL")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar médico: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return m;
    }

    public MedicoTO save(MedicoTO medico) {
        String sql = "INSERT INTO DDD_MEDICO (CD_ESPECIALIDADE, DS_SEGMENTO, DS_TURNO, NM_COMPLETO, DS_EMAIL) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, medico.getCdEspecialidade());
            ps.setString(2, medico.getDsSegmento());
            ps.setString(3, medico.getDsTurno());
            ps.setString(4, medico.getNmCompleto());
            ps.setString(5, medico.getDsEmail());

            ps.executeUpdate();
            return medico;
        } catch (SQLException e) {
            System.out.println("Erro ao salvar médico: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Long crm) {
        String sql = "DELETE FROM DDD_MEDICO WHERE NR_CRM = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, crm);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir médico: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public MedicoTO update(MedicoTO medico) {
        String sql = "UPDATE DDD_MEDICO SET CD_ESPECIALIDADE=?, DS_SEGMENTO=?, DS_TURNO=?, NM_COMPLETO=?, DS_EMAIL=? WHERE NR_CRM=?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, medico.getCdEspecialidade());
            ps.setString(2, medico.getDsSegmento());
            ps.setString(3, medico.getDsTurno());
            ps.setString(4, medico.getNmCompleto());
            ps.setString(5, medico.getDsEmail());
            ps.setLong(6, medico.getNrCrm());

            ps.executeUpdate();
            return medico;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar médico: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }
}
