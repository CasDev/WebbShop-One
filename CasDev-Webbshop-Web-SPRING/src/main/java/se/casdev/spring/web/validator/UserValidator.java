package se.casdev.spring.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import se.casdev.spring.web.form.UserCreateForm;

@Component
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UserCreateForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		UserCreateForm form = (UserCreateForm) target;

		if (supports(form.getClass())) {

			boolean addressOK = se.casdev.repository.database.repository.link.Validator
					.validateAddress(form.getCountry(), form.getArea(),
							form.getZip(), form.getStreet(), form.getCity());
			boolean birthOK = se.casdev.repository.database.repository.link.Validator
					.validateBirthdate(form.getBirthdate());
			boolean emailOK = se.casdev.repository.database.repository.link.Validator
					.validateEmail(form.getEmail());
			boolean passwordOK = se.casdev.repository.database.repository.link.Validator
					.validatePassword(form.getPassword());
			boolean verifyOK = form.isPasswordVerified();
			boolean firstnameOK = (form.getFirstname().length() > 0);
			boolean lastnameOK = (form.getLastname().length() > 0);

			if (addressOK == false) {
				form.setAddressOK(false);
				errors.rejectValue("country", "country.may.need.change");
				errors.rejectValue("city", "city.may.need.change");
				errors.rejectValue("area", "area.may.need.change");
				errors.rejectValue("zip", "zip.may.need.change");
				errors.rejectValue("street", "street.may.need.change");
			}
			if (birthOK == false) {
				form.setBirthdateOK(false);
				errors.rejectValue("birthdate", "birthdate.wrong.formulated");
			}
			if (emailOK == false) {
				form.setEmailOK(false);
				errors.rejectValue("email", "email.wrong.formulated");
			}
			if (passwordOK == false) {
				form.setPasswordOK(false);
				errors.rejectValue("password", "password.not.allowed");
			}
			if (verifyOK == false) {
				form.setPasswordVerified(false);
				errors.rejectValue("verifyPassword", "password.not.verified");
			}
			if (firstnameOK == false) {
				form.setFirstnameOK(false);
				errors.rejectValue("firstname", "firstname.too.short");
			}
			if (lastnameOK == false) {
				form.setLastnameOK(false);
				errors.rejectValue("lastname", "lastname.too.short");
			}

		}

	}

}
