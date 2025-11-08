package br.com.fiap.to;

import jakarta.validation.constraints.*;


public class EspecialidadeTO {

    private Long cdEspecialidade; // Gerado automaticamente pelo banco

    @NotBlank(message = "O segmento é obrigatório.")
    private String dsSegmento;

    @NotBlank(message = "O turno é obrigatório.")
    private String dsTurno;

    @NotBlank(message = "O grau de prioridade é obrigatório.")
    private String dsGrauDePrioridade;


    public EspecialidadeTO() {}


    public EspecialidadeTO(Long cdEspecialidade, String dsSegmento, String dsTurno, String dsGrauDePrioridade) {
        this.cdEspecialidade = cdEspecialidade;
        this.dsSegmento = dsSegmento;
        this.dsTurno = dsTurno;
        this.dsGrauDePrioridade = dsGrauDePrioridade;
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

    public String getDsGrauDePrioridade() {
        return dsGrauDePrioridade;
    }

    public void setDsGrauDePrioridade(String dsGrauDePrioridade) {
        this.dsGrauDePrioridade = dsGrauDePrioridade;
    }
}
