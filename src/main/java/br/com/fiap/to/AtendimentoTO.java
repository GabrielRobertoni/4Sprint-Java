package br.com.fiap.to;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class AtendimentoTO {

    private Long cdAtendimento; // gerado automaticamente no Oracle

    @NotBlank(message = "O nome do paciente é obrigatório.")
    private String dsPaciente;

    @NotBlank(message = "O grau de prioridade é obrigatório.")
    private String dsGrauDePrioridade;

    @NotNull(message = "A data do atendimento é obrigatória.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dtDataDeAtendimento;

    @NotNull(message = "O ID do paciente é obrigatório.")
    private Long idPaciente;

    @NotNull(message = "O código do agendamento é obrigatório.")
    private Long cdAgendamento;

    @NotNull(message = "O número do CRM é obrigatório.")
    private Long nrCrm;

    @NotNull(message = "O código da especialidade é obrigatório.")
    private Long cdEspecialidade;

    public AtendimentoTO() {}

    public AtendimentoTO(Long cdAtendimento, String dsPaciente, String dsGrauDePrioridade,
                         LocalDateTime dtDataDeAtendimento, Long idPaciente,
                         Long cdAgendamento, Long nrCrm, Long cdEspecialidade) {
        this.cdAtendimento = cdAtendimento;
        this.dsPaciente = dsPaciente;
        this.dsGrauDePrioridade = dsGrauDePrioridade;
        this.dtDataDeAtendimento = dtDataDeAtendimento;
        this.idPaciente = idPaciente;
        this.cdAgendamento = cdAgendamento;
        this.nrCrm = nrCrm;
        this.cdEspecialidade = cdEspecialidade;
    }

    // Getters e Setters
    public Long getCdAtendimento() {
        return cdAtendimento;
    }
    public void setCdAtendimento(Long cdAtendimento) {
        this.cdAtendimento = cdAtendimento;
    }

    public String getDsPaciente() {
        return dsPaciente;
    }
    public void setDsPaciente(String dsPaciente) {
        this.dsPaciente = dsPaciente;
    }

    public String getDsGrauDePrioridade() {
        return dsGrauDePrioridade;
    }
    public void setDsGrauDePrioridade(String dsGrauDePrioridade) {
        this.dsGrauDePrioridade = dsGrauDePrioridade;
    }

    public LocalDateTime getDtDataDeAtendimento() {
        return dtDataDeAtendimento;
    }
    public void setDtDataDeAtendimento(LocalDateTime dtDataDeAtendimento) {
        this.dtDataDeAtendimento = dtDataDeAtendimento;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }
    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Long getCdAgendamento() {
        return cdAgendamento;
    }
    public void setCdAgendamento(Long cdAgendamento) {
        this.cdAgendamento = cdAgendamento;
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
}
