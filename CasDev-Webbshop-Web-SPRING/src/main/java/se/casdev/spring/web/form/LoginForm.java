package se.casdev.spring.web.form;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import se.casdev.repository.database.repository.link.MainLink;
import se.casdev.spring.web.controller.Controller;

@Component
public class LoginForm {

	private String email;
	private boolean emailOK;
	private String password;
	private boolean passwordOK;

	public LoginForm() {
		this.email = "";
		this.password = "";
		this.emailOK = true;
		this.passwordOK = true;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEmailOK() {
		return emailOK;
	}

	public boolean isPasswordOK() {
		return passwordOK;
	}

	public void setEmailOK(boolean emailOK) {
		this.emailOK = emailOK;
	}

	public void setPasswordOK(boolean passwordOK) {
		this.passwordOK = passwordOK;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
