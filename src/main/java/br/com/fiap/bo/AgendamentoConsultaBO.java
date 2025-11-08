package br.com.fiap.bo;

import br.com.fiap.dao.AgendamentoConsultaDAO;
import br.com.fiap.to.AgendamentoConsultaTO;
import java.util.ArrayList;

public class AgendamentoConsultaBO {

    private AgendamentoConsultaDAO dao;

    public ArrayList<AgendamentoConsultaTO> findAll() {
        dao = new AgendamentoConsultaDAO();
        return dao.findAll();
    }

    public AgendamentoConsultaTO findByCodigo(Long codigo) {
        dao = new AgendamentoConsultaDAO();
        return dao.findByCodigo(codigo);
    }

    public AgendamentoConsultaTO save(AgendamentoConsultaTO a) {
        dao = new AgendamentoConsultaDAO();
        return dao.save(a);
    }

    public boolean delete(Long codigo) {
        dao = new AgendamentoConsultaDAO();
        return dao.delete(codigo);
    }

    public AgendamentoConsultaTO update(AgendamentoConsultaTO a) {
        dao = new AgendamentoConsultaDAO();
        return dao.update(a);
    }
}
