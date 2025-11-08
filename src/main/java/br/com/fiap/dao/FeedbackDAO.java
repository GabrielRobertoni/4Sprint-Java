package br.com.fiap.dao;

import br.com.fiap.to.FeedbackTO;
import java.sql.*;
import java.util.ArrayList;


public class FeedbackDAO {

    public ArrayList<FeedbackTO> findAll() {
        ArrayList<FeedbackTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM DDD_FEEDBACK ORDER BY CD_FEEDBACK";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                FeedbackTO f = new FeedbackTO();
                f.setCdFeedback(rs.getLong("CD_FEEDBACK"));
                f.setDsProntuario(rs.getString("DS_PRONTUARIO"));
                f.setDtEnvio(rs.getDate("DT_ENVIO").toLocalDate());
                f.setDsReferencia(rs.getString("DS_REFERENCIA"));
                f.setDsComentario(rs.getString("DS_COMENTARIO"));
                f.setDsAvaliacao(rs.getString("DS_AVALIACAO"));
                f.setCdAtendimento(rs.getLong("CD_ATENDIMENTO"));
                f.setIdPaciente(rs.getLong("ID_PACIENTE"));
                f.setNrCrm(rs.getLong("NR_CRM"));
                f.setCdEspecialidade(rs.getLong("CD_ESPECIALIDADE"));
                lista.add(f);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar feedbacks: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return lista;
    }

    public FeedbackTO findByCodigo(Long codigo) {
        FeedbackTO f = null;
        String sql = "SELECT * FROM DDD_FEEDBACK WHERE CD_FEEDBACK = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                f = new FeedbackTO();
                f.setCdFeedback(rs.getLong("CD_FEEDBACK"));
                f.setDsProntuario(rs.getString("DS_PRONTUARIO"));
                f.setDtEnvio(rs.getDate("DT_ENVIO").toLocalDate());
                f.setDsReferencia(rs.getString("DS_REFERENCIA"));
                f.setDsComentario(rs.getString("DS_COMENTARIO"));
                f.setDsAvaliacao(rs.getString("DS_AVALIACAO"));
                f.setCdAtendimento(rs.getLong("CD_ATENDIMENTO"));
                f.setIdPaciente(rs.getLong("ID_PACIENTE"));
                f.setNrCrm(rs.getLong("NR_CRM"));
                f.setCdEspecialidade(rs.getLong("CD_ESPECIALIDADE"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar feedback: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return f;
    }

    public FeedbackTO save(FeedbackTO f) {
        String sql = """
            INSERT INTO DDD_FEEDBACK
            (DS_PRONTUARIO, DT_ENVIO, DS_REFERENCIA, DS_COMENTARIO, DS_AVALIACAO,
             CD_ATENDIMENTO, ID_PACIENTE, NR_CRM, CD_ESPECIALIDADE)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, new String[]{"CD_FEEDBACK"})) {

            ps.setString(1, f.getDsProntuario());

            // 🔹 Garante que a data nunca será nula
            if (f.getDtEnvio() != null) {
                ps.setDate(2, Date.valueOf(f.getDtEnvio()));
            } else {
                ps.setDate(2, new java.sql.Date(System.currentTimeMillis()));
            }

            ps.setString(3, f.getDsReferencia());
            ps.setString(4, f.getDsComentario());
            ps.setString(5, f.getDsAvaliacao());
            ps.setLong(6, f.getCdAtendimento());
            ps.setLong(7, f.getIdPaciente());
            ps.setLong(8, f.getNrCrm());
            ps.setLong(9, f.getCdEspecialidade());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    f.setCdFeedback(rs.getLong(1));
                }
                return f;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao salvar feedback: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public FeedbackTO update(FeedbackTO f) {
        String sql = """
            UPDATE DDD_FEEDBACK
            SET DS_PRONTUARIO=?, DT_ENVIO=?, DS_REFERENCIA=?, DS_COMENTARIO=?,
                DS_AVALIACAO=?, CD_ATENDIMENTO=?, ID_PACIENTE=?, NR_CRM=?, CD_ESPECIALIDADE=?
            WHERE CD_FEEDBACK=?
        """;
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, f.getDsProntuario());
            ps.setDate(2, Date.valueOf(f.getDtEnvio()));
            ps.setString(3, f.getDsReferencia());
            ps.setString(4, f.getDsComentario());
            ps.setString(5, f.getDsAvaliacao());
            ps.setLong(6, f.getCdAtendimento());
            ps.setLong(7, f.getIdPaciente());
            ps.setLong(8, f.getNrCrm());
            ps.setLong(9, f.getCdEspecialidade());
            ps.setLong(10, f.getCdFeedback());
            if (ps.executeUpdate() > 0) return f;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar feedback: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Long codigo) {
        String sql = "DELETE FROM DDD_FEEDBACK WHERE CD_FEEDBACK = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, codigo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir feedback: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }
}
