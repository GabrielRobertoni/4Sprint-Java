package br.com.fiap.to;

import jakarta.validation.constraints.*;


public class PacienteTO {

    private Long idPaciente; // agora o ID é opcional

    @NotBlank(message = "O nome do paciente é obrigatório.")
    private String dsNome;

    @NotNull(message = "A idade é obrigatória.")
    @Positive(message = "A idade deve ser positiva.")
    private Integer nrIdade;

    @NotBlank(message = "O telefone é obrigatório.")
    private String nrTelefone;

    @Email(message = "O e-mail deve ser válido.")
    @NotBlank(message = "O e-mail é obrigatório.")
    private String dsEmail;

    @NotBlank(message = "O sexo é obrigatório.")
    private String dsSexo;

    public PacienteTO() {}

    public PacienteTO(Long idPaciente, String dsNome, Integer nrIdade, String nrTelefone, String dsEmail, String dsSexo) {
        this.idPaciente = idPaciente;
        this.dsNome = dsNome;
        this.nrIdade = nrIdade;
        this.nrTelefone = nrTelefone;
        this.dsEmail = dsEmail;
        this.dsSexo = dsSexo;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }
    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }
    public String getDsNome() {
        return dsNome;
    }
    public void setDsNome(String dsNome) {
        this.dsNome = dsNome;
    }
    public Integer getNrIdade() {
        return nrIdade;
    }
    public void setNrIdade(Integer nrIdade) {
        this.nrIdade = nrIdade;
    }
    public String getNrTelefone() {
        return nrTelefone;
    }
    public void setNrTelefone(String nrTelefone) {
        this.nrTelefone = nrTelefone;
    }
    public String getDsEmail() {
        return dsEmail;
    }
    public void setDsEmail(String dsEmail) {
        this.dsEmail = dsEmail;
    }
    public String getDsSexo() {
        return dsSexo;
    }
    public void setDsSexo(String dsSexo) {
        this.dsSexo = dsSexo;
    }
}
