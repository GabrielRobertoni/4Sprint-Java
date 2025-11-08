package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public class AcompanhamentoTO {

    @NotNull(message = "O ID do paciente é obrigatório.")
    @Positive(message = "O ID do paciente deve ser positivo.")
    private Long idPaciente;

    @NotNull(message = "O código do acompanhante é obrigatório.")
    @Positive(message = "O código do acompanhante deve ser positivo.")
    private Long cdAcompanhante;

    @NotBlank(message = "O status do acompanhamento é obrigatório.")
    private String dsStatusAcompanhamento;

    public AcompanhamentoTO() {}

    public AcompanhamentoTO(Long idPaciente, Long cdAcompanhante, String dsStatusAcompanhamento) {
        this.idPaciente = idPaciente;
        this.cdAcompanhante = cdAcompanhante;
        this.dsStatusAcompanhamento = dsStatusAcompanhamento;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }
    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Long getCdAcompanhante() {
        return cdAcompanhante;
    }
    public void setCdAcompanhante(Long cdAcompanhante) {
        this.cdAcompanhante = cdAcompanhante;
    }

    public String getDsStatusAcompanhamento() {
        return dsStatusAcompanhamento;
    }
    public void setDsStatusAcompanhamento(String dsStatusAcompanhamento) {
        this.dsStatusAcompanhamento = dsStatusAcompanhamento;
    }
}
