package br.com.fiap.dao;

import br.com.fiap.to.AtendimentoTO;
import java.sql.*;
import java.util.ArrayList;

public class AtendimentoDAO {

    public ArrayList<AtendimentoTO> findAll() {
        ArrayList<AtendimentoTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM DDD_ATENDIMENTO ORDER BY CD_ATENDIMENTO";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = ConnectionFactory.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                AtendimentoTO a = new AtendimentoTO(
                        rs.getLong("CD_ATENDIMENTO"),
                        rs.getString("DS_PACIENTE"),
                        rs.getString("DS_GRAU_DE_PRIORIDADE"),
                        rs.getTimestamp("DT_DATA_DE_ATENDIMENTO").toLocalDateTime(),
                        rs.getLong("ID_PACIENTE"),
                        rs.getLong("CD_AGENDAMENTO"),
                        rs.getLong("NR_CRM"),
                        rs.getLong("CD_ESPECIALIDADE")
                );
                lista.add(a);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar atendimentos: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignore) {}
            try { if (ps != null) ps.close(); } catch (Exception ignore) {}
            ConnectionFactory.closeConnection();
        }
        return lista;
    }

    // >>>>>>>>>> NOVO: usado pelo BO <<<<<<<<<<
    public AtendimentoTO findByCodigo(Long codigo) {
        AtendimentoTO a = null;
        String sql = "SELECT * FROM DDD_ATENDIMENTO WHERE CD_ATENDIMENTO = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = ConnectionFactory.getConnection().prepareStatement(sql);
            ps.setLong(1, codigo);
            rs = ps.executeQuery();
            if (rs.next()) {
                a = new AtendimentoTO(
                        rs.getLong("CD_ATENDIMENTO"),
                        rs.getString("DS_PACIENTE"),
                        rs.getString("DS_GRAU_DE_PRIORIDADE"),
                        rs.getTimestamp("DT_DATA_DE_ATENDIMENTO").toLocalDateTime(),
                        rs.getLong("ID_PACIENTE"),
                        rs.getLong("CD_AGENDAMENTO"),
                        rs.getLong("NR_CRM"),
                        rs.getLong("CD_ESPECIALIDADE")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar atendimento: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignore) {}
            try { if (ps != null) ps.close(); } catch (Exception ignore) {}
            ConnectionFactory.closeConnection();
        }
        return a;
    }

    public AtendimentoTO save(AtendimentoTO atendimento) {
        String sql = "INSERT INTO DDD_ATENDIMENTO " +
                "(DS_PACIENTE, DS_GRAU_DE_PRIORIDADE, DT_DATA_DE_ATENDIMENTO, " +
                " ID_PACIENTE, CD_AGENDAMENTO, NR_CRM, CD_ESPECIALIDADE) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = ConnectionFactory.getConnection().prepareStatement(sql, new String[]{"CD_ATENDIMENTO"});
            ps.setString(1, atendimento.getDsPaciente());
            ps.setString(2, atendimento.getDsGrauDePrioridade());
            ps.setTimestamp(3, Timestamp.valueOf(atendimento.getDtDataDeAtendimento())); // DATE/TIMESTAMP ok
            ps.setLong(4, atendimento.getIdPaciente());
            ps.setLong(5, atendimento.getCdAgendamento());
            ps.setLong(6, atendimento.getNrCrm());
            ps.setLong(7, atendimento.getCdEspecialidade());

            if (ps.executeUpdate() > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) atendimento.setCdAtendimento(rs.getLong(1));
                try { rs.close(); } catch (Exception ignore) {}
                return atendimento;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar atendimento: " + e.getMessage());
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception ignore) {}
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public AtendimentoTO update(AtendimentoTO atendimento) {
        String sql = "UPDATE DDD_ATENDIMENTO SET " +
                "DS_PACIENTE=?, DS_GRAU_DE_PRIORIDADE=?, DT_DATA_DE_ATENDIMENTO=?, " +
                "ID_PACIENTE=?, CD_AGENDAMENTO=?, NR_CRM=?, CD_ESPECIALIDADE=? " +
                "WHERE CD_ATENDIMENTO=?";
        PreparedStatement ps = null;
        try {
            ps = ConnectionFactory.getConnection().prepareStatement(sql);
            ps.setString(1, atendimento.getDsPaciente());
            ps.setString(2, atendimento.getDsGrauDePrioridade());
            ps.setTimestamp(3, Timestamp.valueOf(atendimento.getDtDataDeAtendimento()));
            ps.setLong(4, atendimento.getIdPaciente());
            ps.setLong(5, atendimento.getCdAgendamento());
            ps.setLong(6, atendimento.getNrCrm());
            ps.setLong(7, atendimento.getCdEspecialidade());
            ps.setLong(8, atendimento.getCdAtendimento());

            if (ps.executeUpdate() > 0) return atendimento;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar atendimento: " + e.getMessage());
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception ignore) {}
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Long codigo) {
        String sql = "DELETE FROM DDD_ATENDIMENTO WHERE CD_ATENDIMENTO = ?";
        PreparedStatement ps = null;
        try {
            ps = ConnectionFactory.getConnection().prepareStatement(sql);
            ps.setLong(1, codigo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir atendimento: " + e.getMessage());
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception ignore) {}
            ConnectionFactory.closeConnection();
        }
        return false;
    }
}
