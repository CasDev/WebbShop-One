package se.casdev.spring.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import se.casdev.spring.web.form.UpdateUserForm;

public class UpdateUserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UpdateUserForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		UpdateUserForm form = (UpdateUserForm) target;

		if (!form.getEmail().equals("")) {
			boolean emailSame = form.getEmail().equals(
					form.getUser().getEmail());

			if (emailSame) {

				form.setEmailOK(false);
				errors.rejectValue("email", "email.wrong.formulated");

			}

		}

		if (!form.getArea().equals("")) {

			boolean areaOK = se.casdev.repository.database.repository.link.Validator
					.validateArea(form.getArea());

			if (areaOK == false) {

				form.setAreaOK(false);
				errors.rejectValue("area", "area.may.need.change");

			}

		}

		if (!form.getCity().equals("")) {

			boolean cityOK = se.casdev.repository.database.repository.link.Validator
					.validateCity(form.getCity());

			if (cityOK == false) {

				form.setCityOK(false);
				errors.rejectValue("city", "city.may.need.change");

			}

		}

		if (!form.getCountry().equals("")) {
			boolean countryOK = se.casdev.repository.database.repository.link.Validator
					.validateCountry(form.getCountry());

			if (countryOK == false) {

				form.setCountryOK(false);
				errors.rejectValue("country", "country.may.need.change");

			}
		}

		if (!form.getStreet().equals("")) {

			boolean streetOK = se.casdev.repository.database.repository.link.Validator
					.validateStreet(form.getStreet());

			if (streetOK == false) {

				form.setStreetOK(false);
				errors.rejectValue("street", "street.may.need.change");

			}

		}

		if (!form.getZip().equals("")) {
			boolean zipOK = se.casdev.repository.database.repository.link.Validator
					.validateZip(form.getZip());

			if (zipOK == false) {

				form.setZipOK(false);
				errors.rejectValue("zip", "zip.may.need.change");

			}
		}

		if (!form.getLastname().equals("")) {
			boolean lastnameTheSame = form.getLastname().equals(
					form.getUser().getLastname());
			boolean lastnameOK = se.casdev.repository.database.repository.link.Validator
					.validateLastname(form.getLastname());

			if (lastnameTheSame && lastnameOK == false) {

				form.setLastnameOK(false);
				errors.rejectValue("lastname", "lastname.too.short");

			}
		}

		if (!form.getPassword().equals("")) {
			boolean passwordOK = se.casdev.repository.database.repository.link.Validator
					.validatePassword(form.getPassword());

			if (passwordOK == false) {

				form.setPasswordOK(false);
				errors.rejectValue("password", "password.not.verified");

			}
		}

	}

}
