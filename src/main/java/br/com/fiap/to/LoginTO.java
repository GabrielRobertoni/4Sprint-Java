package br.com.fiap.to;

import jakarta.validation.constraints.*;

public class LoginTO {

    private Long idLogin;

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "O e-mail deve ser válido.")
    private String dsEmail;

    @NotBlank(message = "A senha é obrigatória.")
    private String dsSenha;

    public LoginTO() {}

    public LoginTO(Long idLogin, String dsEmail, String dsSenha) {
        this.idLogin = idLogin;
        this.dsEmail = dsEmail;
        this.dsSenha = dsSenha;
    }

    public Long getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(Long idLogin) {
        this.idLogin = idLogin;
    }

    public String getDsEmail() {
        return dsEmail;
    }

    public void setDsEmail(String dsEmail) {
        this.dsEmail = dsEmail;
    }

    public String getDsSenha() {
        return dsSenha;
    }

    public void setDsSenha(String dsSenha) {
        this.dsSenha = dsSenha;
    }
}
