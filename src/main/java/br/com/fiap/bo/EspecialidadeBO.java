package br.com.fiap.bo;

import br.com.fiap.dao.EspecialidadeDAO;
import br.com.fiap.to.EspecialidadeTO;
import java.util.ArrayList;


public class EspecialidadeBO {

    private EspecialidadeDAO dao;

    public ArrayList<EspecialidadeTO> findAll() {
        dao = new EspecialidadeDAO();
        return dao.findAll();
    }

    public EspecialidadeTO findByCodigo(Long codigo) {
        dao = new EspecialidadeDAO();
        return dao.findByCodigo(codigo);
    }

    public EspecialidadeTO save(EspecialidadeTO e) {
        dao = new EspecialidadeDAO();
        return dao.save(e);
    }

    public boolean delete(Long codigo) {
        dao = new EspecialidadeDAO();
        return dao.delete(codigo);
    }

    public EspecialidadeTO update(EspecialidadeTO e) {
        dao = new EspecialidadeDAO();
        return dao.update(e);
    }
}
