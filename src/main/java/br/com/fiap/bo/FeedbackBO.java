package br.com.fiap.bo;

import br.com.fiap.dao.FeedbackDAO;
import br.com.fiap.to.FeedbackTO;
import java.util.ArrayList;


public class FeedbackBO {

    private FeedbackDAO dao;

    public ArrayList<FeedbackTO> findAll() {
        dao = new FeedbackDAO();
        return dao.findAll();
    }

    public FeedbackTO findByCodigo(Long codigo) {
        dao = new FeedbackDAO();
        return dao.findByCodigo(codigo);
    }

    public FeedbackTO save(FeedbackTO f) {
        dao = new FeedbackDAO();
        if (f.getDtEnvio() == null) {
            f.setDtEnvio(java.time.LocalDate.now());
        }
        if (f.getDtEnvio().isAfter(java.time.LocalDate.now())) {
            System.out.println("A data de envio não pode ser futura.");
            return null;
        }
        return dao.save(f);
    }

    public boolean delete(Long codigo) {
        dao = new FeedbackDAO();
        return dao.delete(codigo);
    }

    public FeedbackTO update(FeedbackTO f) {
        dao = new FeedbackDAO();
        return dao.update(f);
    }
}
