package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;


public class AcompanhanteTO {

    private Long cdAcompanhante;

    @NotBlank(message = "O nome do acompanhante é obrigatório.")
    private String dsNomeAcompanhante;

    @NotBlank(message = "O CPF é obrigatório.")
    private String nrCpf;

    @NotBlank(message = "O telefone é obrigatório.")
    private String nrTelefone;

    @NotBlank(message = "O parentesco é obrigatório.")
    private String dsParentesco;


    public AcompanhanteTO() {
    }


    public AcompanhanteTO(Long cdAcompanhante, String dsNomeAcompanhante, String nrCpf, String nrTelefone, String dsParentesco) {
        this.cdAcompanhante = cdAcompanhante;
        this.dsNomeAcompanhante = dsNomeAcompanhante;
        this.nrCpf = nrCpf;
        this.nrTelefone = nrTelefone;
        this.dsParentesco = dsParentesco;
    }


    public Long getCdAcompanhante() {
        return cdAcompanhante;
    }

    public void setCdAcompanhante(Long cdAcompanhante) {
        this.cdAcompanhante = cdAcompanhante;
    }

    public String getDsNomeAcompanhante() {
        return dsNomeAcompanhante;
    }

    public void setDsNomeAcompanhante(String dsNomeAcompanhante) {
        this.dsNomeAcompanhante = dsNomeAcompanhante;
    }

    public String getNrCpf() {
        return nrCpf;
    }

    public void setNrCpf(String nrCpf) {
        this.nrCpf = nrCpf;
    }

    public String getNrTelefone() {
        return nrTelefone;
    }

    public void setNrTelefone(String nrTelefone) {
        this.nrTelefone = nrTelefone;
    }

    public String getDsParentesco() {
        return dsParentesco;
    }

    public void setDsParentesco(String dsParentesco) {
        this.dsParentesco = dsParentesco;
    }
}
