package br.com.fiap.to;

import jakarta.validation.constraints.*;
import java.time.LocalDate;


public class FeedbackTO {

    private Long cdFeedback;

    @NotBlank(message = "O prontuário é obrigatório.")
    private String dsProntuario;

    @NotNull(message = "A data de envio é obrigatória.")
    @PastOrPresent(message = "A data de envio não pode ser futura.")
    private LocalDate dtEnvio;

    @NotBlank(message = "A referência é obrigatória.")
    private String dsReferencia;

    @NotBlank(message = "O comentário é obrigatório.")
    private String dsComentario;

    @NotBlank(message = "A avaliação é obrigatória.")
    private String dsAvaliacao;

    @NotNull(message = "O código do atendimento é obrigatório.")
    @Positive(message = "O código do atendimento deve ser positivo.")
    private Long cdAtendimento;

    @NotNull(message = "O ID do paciente é obrigatório.")
    @Positive(message = "O ID do paciente deve ser positivo.")
    private Long idPaciente;

    @NotNull(message = "O número de CRM é obrigatório.")
    @Positive(message = "O número de CRM deve ser positivo.")
    private Long nrCrm;

    @NotNull(message = "O código da especialidade é obrigatório.")
    @Positive(message = "O código da especialidade deve ser positivo.")
    private Long cdEspecialidade;

    public FeedbackTO() {}

    public FeedbackTO(Long cdFeedback, String dsProntuario, LocalDate dtEnvio, String dsReferencia,
                      String dsComentario, String dsAvaliacao, Long cdAtendimento, Long idPaciente,
                      Long nrCrm, Long cdEspecialidade) {
        this.cdFeedback = cdFeedback;
        this.dsProntuario = dsProntuario;
        this.dtEnvio = dtEnvio;
        this.dsReferencia = dsReferencia;
        this.dsComentario = dsComentario;
        this.dsAvaliacao = dsAvaliacao;
        this.cdAtendimento = cdAtendimento;
        this.idPaciente = idPaciente;
        this.nrCrm = nrCrm;
        this.cdEspecialidade = cdEspecialidade;
    }

    public Long getCdFeedback() {
        return cdFeedback;
    }
    public void setCdFeedback(Long cdFeedback) {
        this.cdFeedback = cdFeedback;
    }

    public String getDsProntuario() {
        return dsProntuario;
    }
    public void setDsProntuario(String dsProntuario) {
        this.dsProntuario = dsProntuario;
    }

    public LocalDate getDtEnvio() {
        return dtEnvio;
    }
    public void setDtEnvio(LocalDate dtEnvio) {
        this.dtEnvio = dtEnvio;
    }

    public String getDsReferencia() {
        return dsReferencia;
    }
    public void setDsReferencia(String dsReferencia) {
        this.dsReferencia = dsReferencia;
    }

    public String getDsComentario() {
        return dsComentario;
    }
    public void setDsComentario(String dsComentario) {
        this.dsComentario = dsComentario;
    }

    public String getDsAvaliacao() {
        return dsAvaliacao;
    }
    public void setDsAvaliacao(String dsAvaliacao) {
        this.dsAvaliacao = dsAvaliacao;
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
