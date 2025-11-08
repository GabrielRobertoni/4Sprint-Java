package br.com.fiap.bo;

import br.com.fiap.dao.LoginDAO;
import br.com.fiap.to.LoginTO;
import java.util.List;

public class LoginBO {

    private LoginDAO dao = new LoginDAO();

    public LoginTO save(LoginTO login) {
        return dao.save(login);
    }

    public List<LoginTO> findAll() {
        return dao.findAll();
    }

    public LoginTO findByCodigo(Long id) {
        return dao.findByCodigo(id);
    }

    public LoginTO update(LoginTO login) {
        return dao.update(login);
    }

    public boolean delete(Long id) {
        return dao.delete(id);
    }
}
