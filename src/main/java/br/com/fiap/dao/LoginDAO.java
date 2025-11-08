package br.com.fiap.dao;

import br.com.fiap.to.LoginTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoginDAO {

    // 🔹 Inserir novo login (Oracle Identity)
    public LoginTO save(LoginTO login) {
        String sqlInsert = "INSERT INTO DDD_LOGIN (DS_EMAIL, DS_SENHA) VALUES (?, ?)";
        String sqlReturnId = "SELECT ID_LOGIN FROM DDD_LOGIN WHERE DS_EMAIL = ? AND DS_SENHA = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlInsert);
             PreparedStatement ps2 = conn.prepareStatement(sqlReturnId)) {

            ps.setString(1, login.getDsEmail());
            ps.setString(2, login.getDsSenha());
            int linhas = ps.executeUpdate();

            if (linhas > 0) {
                ps2.setString(1, login.getDsEmail());
                ps2.setString(2, login.getDsSenha());
                try (ResultSet rs = ps2.executeQuery()) {
                    if (rs.next()) {
                        login.setIdLogin(rs.getLong("ID_LOGIN"));
                    }
                }
                System.out.println("✅ Login salvo com sucesso! ID: " + login.getIdLogin());
                return login;
            } else {
                System.out.println("⚠️ Nenhuma linha inserida.");
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("❌ Erro ao salvar login: " + e.getMessage());
            return null;
        }
    }

    // 🔹 Listar todos
    public List<LoginTO> findAll() {
        List<LoginTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM DDD_LOGIN ORDER BY ID_LOGIN";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new LoginTO(
                        rs.getLong("ID_LOGIN"),
                        rs.getString("DS_EMAIL"),
                        rs.getString("DS_SENHA")
                ));
            }

        } catch (SQLException e) {
            System.out.println("❌ Erro ao listar logins: " + e.getMessage());
        }
        return lista;
    }

    // 🔹 Buscar por ID
    public LoginTO findByCodigo(Long id) {
        LoginTO login = null;
        String sql = "SELECT * FROM DDD_LOGIN WHERE ID_LOGIN = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                login = new LoginTO(
                        rs.getLong("ID_LOGIN"),
                        rs.getString("DS_EMAIL"),
                        rs.getString("DS_SENHA")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Erro ao buscar login: " + e.getMessage());
        }
        return login;
    }

    // 🔹 Atualizar login
    public LoginTO update(LoginTO login) {
        String sql = "UPDATE DDD_LOGIN SET DS_EMAIL = ?, DS_SENHA = ? WHERE ID_LOGIN = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, login.getDsEmail());
            ps.setString(2, login.getDsSenha());
            ps.setLong(3, login.getIdLogin());
            ps.executeUpdate();

            System.out.println("✅ Login atualizado com sucesso! ID: " + login.getIdLogin());
            return login;

        } catch (SQLException e) {
            System.out.println("❌ Erro ao atualizar login: " + e.getMessage());
            return null;
        }
    }

    // 🔹 Excluir login
    public boolean delete(Long id) {
        String sql = "DELETE FROM DDD_LOGIN WHERE ID_LOGIN = ?";
        boolean sucesso = false;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            int linhas = ps.executeUpdate();

            sucesso = linhas > 0;
            if (sucesso) {
                System.out.println("🗑️ Login excluído com sucesso! ID: " + id);
            }

        } catch (SQLException e) {
            System.out.println("❌ Erro ao excluir login: " + e.getMessage());
        }

        return sucesso;
    }
}
