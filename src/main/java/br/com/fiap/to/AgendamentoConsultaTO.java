package br.com.fiap.to;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

/**
 * Classe TO (Transfer Object) para Agendamento de Consultas.
 * Padrão idêntico ao utilizado em AcompanhanteTO.
 */
public class AgendamentoConsultaTO {

    private Long cdAgendamento; // Gerado automaticamente pelo Oracle

    @NotBlank(message = "O prontuário é obrigatório.")
    private String dsProntuario;

    @NotNull(message = "A data e hora da consulta são obrigatórias.")
    @FutureOrPresent(message = "A data/hora da consulta deve ser no presente ou futuro.")
    private LocalDateTime dtHoraConsulta;

    @NotBlank(message = "O status do agendamento é obrigatório.")
    private String dsStatusAgendamento;

    @NotNull(message = "O código do atendimento é obrigatório.")
    private Long cdAtendimento;

    @NotNull(message = "O ID do paciente é obrigatório.")
    private Long idPaciente;

    @NotNull(message = "O CRM do médico é obrigatório.")
    private Long nrCrm;

    @NotNull(message = "O código da especialidade é obrigatório.")
    private Long cdEspecialidade;


    public AgendamentoConsultaTO() {
    }

    public AgendamentoConsultaTO(Long cdAgendamento, String dsProntuario, LocalDateTime dtHoraConsulta,
                                 String dsStatusAgendamento, Long cdAtendimento, Long idPaciente,
                                 Long nrCrm, Long cdEspecialidade) {
        this.cdAgendamento = cdAgendamento;
        this.dsProntuario = dsProntuario;
        this.dtHoraConsulta = dtHoraConsulta;
        this.dsStatusAgendamento = dsStatusAgendamento;
        this.cdAtendimento = cdAtendimento;
        this.idPaciente = idPaciente;
        this.nrCrm = nrCrm;
        this.cdEspecialidade = cdEspecialidade;
    }

    public Long getCdAgendamento() {
        return cdAgendamento;
    }

    public void setCdAgendamento(Long cdAgendamento) {
        this.cdAgendamento = cdAgendamento;
    }

    public String getDsProntuario() {
        return dsProntuario;
    }

    public void setDsProntuario(String dsProntuario) {
        this.dsProntuario = dsProntuario;
    }

    public LocalDateTime getDtHoraConsulta() {
        return dtHoraConsulta;
    }

    public void setDtHoraConsulta(LocalDateTime dtHoraConsulta) {
        this.dtHoraConsulta = dtHoraConsulta;
    }

    public String getDsStatusAgendamento() {
        return dsStatusAgendamento;
    }

    public void setDsStatusAgendamento(String dsStatusAgendamento) {
        this.dsStatusAgendamento = dsStatusAgendamento;
    }

    public Long getCdAtendimento() {
        return cdAtendimento;
    }

    public void setCdAtendimento(Long cdAtendimento) {
        this.cdAtendimento = cdAtendimento;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
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
