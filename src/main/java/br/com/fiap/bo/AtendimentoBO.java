package br.com.fiap.bo;

import br.com.fiap.dao.AtendimentoDAO;
import br.com.fiap.to.AtendimentoTO;
import java.util.ArrayList;

public class AtendimentoBO {

    private AtendimentoDAO dao;

    public ArrayList<AtendimentoTO> findAll() {
        dao = new AtendimentoDAO();
        return dao.findAll();
    }

    public AtendimentoTO findByCodigo(Long codigo) {
        dao = new AtendimentoDAO();
        return dao.findByCodigo(codigo);
    }

    public AtendimentoTO save(AtendimentoTO a) {
        dao = new AtendimentoDAO();
        return dao.save(a);
    }

    public boolean delete(Long codigo) {
        dao = new AtendimentoDAO();
        return dao.delete(codigo);
    }

    public AtendimentoTO update(AtendimentoTO a) {
        dao = new AtendimentoDAO();
        return dao.update(a);
    }
}
