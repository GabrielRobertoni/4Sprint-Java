package br.com.fiap.bo;

import br.com.fiap.dao.MedicoDAO;
import br.com.fiap.to.MedicoTO;
import java.util.ArrayList;

public class MedicoBO {
    private MedicoDAO dao = new MedicoDAO();

    public ArrayList<MedicoTO> findAll() {
        return dao.findAll();
    }

    public MedicoTO findByCodigo(Long crm) {
        return dao.findByCodigo(crm);
    }

    public MedicoTO save(MedicoTO medico) {
        return dao.save(medico);
    }

    public boolean delete(Long crm) {
        return dao.delete(crm);
    }

    public MedicoTO update(MedicoTO medico) {
        return dao.update(medico);
    }
}
