package br.com.fiap.bo;

import br.com.fiap.dao.AcompanhamentoDAO;
import br.com.fiap.to.AcompanhamentoTO;

import java.util.ArrayList;

public class AcompanhamentoBO {

    private AcompanhamentoDAO dao;

    public ArrayList<AcompanhamentoTO> findAll() {
        dao = new AcompanhamentoDAO();
        return dao.findAll();
    }

    public AcompanhamentoTO findOne(Long idPaciente, Long cdAcompanhante) {
        dao = new AcompanhamentoDAO();
        return dao.findOne(idPaciente, cdAcompanhante);
    }

    public AcompanhamentoTO save(AcompanhamentoTO a) {
        dao = new AcompanhamentoDAO();
        return dao.save(a);
    }

    public AcompanhamentoTO update(AcompanhamentoTO a) {
        dao = new AcompanhamentoDAO();
        return dao.update(a);
    }

    public boolean delete(Long idPaciente, Long cdAcompanhante) {
        dao = new AcompanhamentoDAO();
        return dao.delete(idPaciente, cdAcompanhante);
    }
}
