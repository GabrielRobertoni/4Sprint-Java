package br.com.fiap.to;

import jakarta.validation.constraints.*;


public class MedicoTO {

    @NotNull(message = "O número do CRM é obrigatório.")
    @Positive(message = "O número do CRM deve ser positivo.")
    private Long nrCrm;

    @NotNull(message = "O código da especialidade é obrigatório.")
    @Positive(message = "O código da especialidade deve ser positivo.")
    private Long cdEspecialidade;

    @NotBlank(message = "O segmento é obrigatório.")
    private String dsSegmento;

    @NotBlank(message = "O turno é obrigatório.")
    private String dsTurno;

    @NotBlank(message = "O nome completo é obrigatório.")
    private String nmCompleto;

    @Email(message = "O e-mail deve ser válido.")
    @NotBlank(message = "O e-mail é obrigatório.")
    private String dsEmail;


    public MedicoTO() {}


    public MedicoTO(Long nrCrm, Long cdEspecialidade, String dsSegmento,
                    String dsTurno, String nmCompleto, String dsEmail) {
        this.nrCrm = nrCrm;
        this.cdEspecialidade = cdEspecialidade;
        this.dsSegmento = dsSegmento;
        this.dsTurno = dsTurno;
        this.nmCompleto = nmCompleto;
        this.dsEmail = dsEmail;
    }


    public Long getNrCrm() {
        return nrCrm;
    }
    public void setNrCrm(Long nrCrm) {
        this.nrCrm = nrCrm;
    }

    public Long getCdEspecialidade() {
        return cdEspecialidade;
    }
    public void setCdEspecialidade(Long cdEspecialidade) {
        this.cdEspecialidade = cdEspecialidade;
    }

    public String getDsSegmento() {
        return dsSegmento;
    }
    public void setDsSegmento(String dsSegmento) {
        this.dsSegmento = dsSegmento;
    }

    public String getDsTurno() {
        return dsTurno;
    }
    public void setDsTurno(String dsTurno) {
        this.dsTurno = dsTurno;
    }

    public String getNmCompleto() {
        return nmCompleto;
    }
    public void setNmCompleto(String nmCompleto) {
        this.nmCompleto = nmCompleto;
    }

    public String getDsEmail() {
        return dsEmail;
    }
    public void setDsEmail(String dsEmail) {
        this.dsEmail = dsEmail;
    }
}
