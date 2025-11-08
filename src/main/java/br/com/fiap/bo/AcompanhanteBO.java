package br.com.fiap.bo;

import br.com.fiap.dao.AcompanhanteDAO;
import br.com.fiap.to.AcompanhanteTO;
import java.util.ArrayList;

public class AcompanhanteBO {

    private AcompanhanteDAO dao;

    public ArrayList<AcompanhanteTO> findAll() {
        dao = new AcompanhanteDAO();
        return dao.findAll();
    }

    public AcompanhanteTO findByCodigo(Long id) {
        dao = new AcompanhanteDAO();
        return dao.findByCodigo(id);
    }

    public AcompanhanteTO save(AcompanhanteTO a) {
        dao = new AcompanhanteDAO();
        return dao.save(a);
    }

    public AcompanhanteTO update(AcompanhanteTO a) {
        dao = new AcompanhanteDAO();
        return dao.update(a);
    }

    public boolean delete(Long id) {
        dao = new AcompanhanteDAO();
        return dao.delete(id);
    }
}
