package br.com.fiap.dao;

import br.com.fiap.to.AgendamentoConsultaTO;
import java.sql.*;
import java.util.ArrayList;


public class AgendamentoConsultaDAO {

    // 🔹 LISTAR TODOS
    public ArrayList<AgendamentoConsultaTO> findAll() {
        ArrayList<AgendamentoConsultaTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM ddd_agendamento_consulta ORDER BY cd_agendamento";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AgendamentoConsultaTO a = new AgendamentoConsultaTO();
                a.setCdAgendamento(rs.getLong("cd_agendamento"));
                a.setDsProntuario(rs.getString("ds_prontuario"));
                Timestamp ts = rs.getTimestamp("dt_hora_consulta");
                if (ts != null) a.setDtHoraConsulta(ts.toLocalDateTime());
                a.setDsStatusAgendamento(rs.getString("ds_status_agendamento"));
                a.setCdAtendimento(rs.getLong("cd_atendimento"));
                a.setIdPaciente(rs.getLong("id_paciente"));
                a.setNrCrm(rs.getLong("nr_crm"));
                a.setCdEspecialidade(rs.getLong("cd_especialidade"));
                lista.add(a);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar consultas: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return lista;
    }

    // 🔹 BUSCAR POR CÓDIGO
    public AgendamentoConsultaTO findByCodigo(Long codigo) {
        AgendamentoConsultaTO a = null;
        String sql = "SELECT * FROM ddd_agendamento_consulta WHERE cd_agendamento = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                a = new AgendamentoConsultaTO();
                a.setCdAgendamento(rs.getLong("cd_agendamento"));
                a.setDsProntuario(rs.getString("ds_prontuario"));
                Timestamp ts = rs.getTimestamp("dt_hora_consulta");
                if (ts != null) a.setDtHoraConsulta(ts.toLocalDateTime());
                a.setDsStatusAgendamento(rs.getString("ds_status_agendamento"));
                a.setCdAtendimento(rs.getLong("cd_atendimento"));
                a.setIdPaciente(rs.getLong("id_paciente"));
                a.setNrCrm(rs.getLong("nr_crm"));
                a.setCdEspecialidade(rs.getLong("cd_especialidade"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return a;
    }

    // 🔹 SALVAR (INSERT)
    public AgendamentoConsultaTO save(AgendamentoConsultaTO a) {
        String sql = """
            INSERT INTO ddd_agendamento_consulta
            (ds_prontuario, dt_hora_consulta, ds_status_agendamento,
             cd_atendimento, id_paciente, nr_crm, cd_especialidade)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (PreparedStatement ps = ConnectionFactory.getConnection()
                .prepareStatement(sql, new String[]{"cd_agendamento"})) {

            ps.setString(1, a.getDsProntuario());
            ps.setTimestamp(2, Timestamp.valueOf(a.getDtHoraConsulta()));
            ps.setString(3, a.getDsStatusAgendamento());
            ps.setLong(4, a.getCdAtendimento());
            ps.setLong(5, a.getIdPaciente());
            ps.setLong(6, a.getNrCrm());
            ps.setLong(7, a.getCdEspecialidade());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                a.setCdAgendamento(rs.getLong(1));
            }
            return a;

        } catch (SQLException e) {
            System.out.println("Erro ao salvar consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    // 🔹 EXCLUIR
    public boolean delete(Long codigo) {
        String sql = "DELETE FROM ddd_agendamento_consulta WHERE cd_agendamento = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, codigo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    // 🔹 ATUALIZAR
    public AgendamentoConsultaTO update(AgendamentoConsultaTO a) {
        String sql = """
            UPDATE ddd_agendamento_consulta
            SET ds_prontuario=?, dt_hora_consulta=?, ds_status_agendamento=?,
                cd_atendimento=?, id_paciente=?, nr_crm=?, cd_especialidade=?
            WHERE cd_agendamento=?
        """;

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, a.getDsProntuario());
            ps.setTimestamp(2, Timestamp.valueOf(a.getDtHoraConsulta()));
            ps.setString(3, a.getDsStatusAgendamento());
            ps.setLong(4, a.getCdAtendimento());
            ps.setLong(5, a.getIdPaciente());
            ps.setLong(6, a.getNrCrm());
            ps.setLong(7, a.getCdEspecialidade());
            ps.setLong(8, a.getCdAgendamento());

            if (ps.executeUpdate() > 0) return a;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }
}
