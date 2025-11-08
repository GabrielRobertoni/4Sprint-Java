package br.com.fiap.dao;

import br.com.fiap.to.AcompanhamentoTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class AcompanhamentoDAO {

    public ArrayList<AcompanhamentoTO> findAll() {
        ArrayList<AcompanhamentoTO> lista = new ArrayList<>();
        String sql = "SELECT ID_PACIENTE, CD_ACOMPANHANTE, DS_STATUS_ACOMPANHAMENTO " +
                "FROM DDD_ACOMPANHAMENTO ORDER BY ID_PACIENTE, CD_ACOMPANHANTE";
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cn = ConnectionFactory.getConnection();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                AcompanhamentoTO a = new AcompanhamentoTO(
                        rs.getLong("ID_PACIENTE"),
                        rs.getLong("CD_ACOMPANHANTE"),
                        rs.getString("DS_STATUS_ACOMPANHAMENTO")
                );
                lista.add(a);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar acompanhamentos: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignored) {}
            try { if (ps != null) ps.close(); } catch (Exception ignored) {}
            ConnectionFactory.closeConnection();
        }
        return lista;
    }

    public AcompanhamentoTO findOne(Long idPaciente, Long cdAcompanhante) {
        String sql = "SELECT ID_PACIENTE, CD_ACOMPANHANTE, DS_STATUS_ACOMPANHAMENTO " +
                "FROM DDD_ACOMPANHAMENTO WHERE ID_PACIENTE=? AND CD_ACOMPANHANTE=?";
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cn = ConnectionFactory.getConnection();
            ps = cn.prepareStatement(sql);
            ps.setLong(1, idPaciente);
            ps.setLong(2, cdAcompanhante);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new AcompanhamentoTO(
                        rs.getLong("ID_PACIENTE"),
                        rs.getLong("CD_ACOMPANHANTE"),
                        rs.getString("DS_STATUS_ACOMPANHAMENTO")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar acompanhamento: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignored) {}
            try { if (ps != null) ps.close(); } catch (Exception ignored) {}
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public AcompanhamentoTO save(AcompanhamentoTO a) {
        String sql = "INSERT INTO DDD_ACOMPANHAMENTO " +
                "(ID_PACIENTE, CD_ACOMPANHANTE, DS_STATUS_ACOMPANHAMENTO) VALUES (?, ?, ?)";
        Connection cn = null;
        PreparedStatement ps = null;
        try {
            cn = ConnectionFactory.getConnection();
            ps = cn.prepareStatement(sql);
            ps.setLong(1, a.getIdPaciente());
            ps.setLong(2, a.getCdAcompanhante());
            ps.setString(3, a.getDsStatusAcompanhamento());
            int ok = ps.executeUpdate();
            if (ok > 0) return a;
        } catch (SQLException e) {
            System.out.println("Erro ao salvar acompanhamento: " + e.getMessage());
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception ignored) {}
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public AcompanhamentoTO update(AcompanhamentoTO a) {
        String sql = "UPDATE DDD_ACOMPANHAMENTO " +
                "SET DS_STATUS_ACOMPANHAMENTO=? " +
                "WHERE ID_PACIENTE=? AND CD_ACOMPANHANTE=?";
        Connection cn = null;
        PreparedStatement ps = null;
        try {
            cn = ConnectionFactory.getConnection();
            ps = cn.prepareStatement(sql);
            ps.setString(1, a.getDsStatusAcompanhamento());
            ps.setLong(2, a.getIdPaciente());
            ps.setLong(3, a.getCdAcompanhante());
            int ok = ps.executeUpdate();
            if (ok > 0) return a;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar acompanhamento: " + e.getMessage());
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception ignored) {}
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Long idPaciente, Long cdAcompanhante) {
        String sql = "DELETE FROM DDD_ACOMPANHAMENTO WHERE ID_PACIENTE=? AND CD_ACOMPANHANTE=?";
        Connection cn = null;
        PreparedStatement ps = null;
        try {
            cn = ConnectionFactory.getConnection();
            ps = cn.prepareStatement(sql);
            ps.setLong(1, idPaciente);
            ps.setLong(2, cdAcompanhante);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir acompanhamento: " + e.getMessage());
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception ignored) {}
            ConnectionFactory.closeConnection();
        }
        return false;
    }
}
