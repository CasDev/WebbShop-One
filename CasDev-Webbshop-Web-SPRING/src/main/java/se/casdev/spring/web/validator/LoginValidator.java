package se.casdev.spring.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import se.casdev.spring.web.form.LoginForm;

public class LoginValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return LoginForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		LoginForm form = (LoginForm) target;

		if (supports(form.getClass())) {

			boolean emailOK = se.casdev.repository.database.repository.link.Validator
					.validateEmail(form.getEmail());
			boolean passwordOK = se.casdev.repository.database.repository.link.Validator
					.validatePassword(form.getPassword());

			if (emailOK == false) {
				form.setEmailOK(false);
				errors.rejectValue("email", "email.wrong.formulated");
			}
			if (passwordOK == false) {
				form.setPasswordOK(true);
				errors.rejectValue("password", "password.not.verified");
			}
		}

	}

}
