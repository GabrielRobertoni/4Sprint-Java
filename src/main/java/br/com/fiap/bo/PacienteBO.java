package br.com.fiap.bo;

import br.com.fiap.dao.PacienteDAO;
import br.com.fiap.to.PacienteTO;
import java.util.ArrayList;


public class PacienteBO {

    private PacienteDAO dao;

    public ArrayList<PacienteTO> findAll() {
        dao = new PacienteDAO();
        return dao.findAll();
    }

    public PacienteTO findByCodigo(Long id) {
        dao = new PacienteDAO();
        return dao.findByCodigo(id);
    }

    public PacienteTO save(PacienteTO p) {
        dao = new PacienteDAO();
        if (p.getNrIdade() > 120) {
            System.out.println("Idade inválida.");
            return null;
        }
        return dao.save(p);
    }

    public boolean delete(Long id) {
        dao = new PacienteDAO();
        return dao.delete(id);
    }

    public PacienteTO update(PacienteTO p) {
        dao = new PacienteDAO();
        return dao.update(p);
    }
}
