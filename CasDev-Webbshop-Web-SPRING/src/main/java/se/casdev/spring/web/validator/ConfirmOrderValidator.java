package se.casdev.spring.web.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import se.casdev.spring.web.form.ConfirmOrderForm;

public class ConfirmOrderValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return ConfirmOrderForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		ConfirmOrderForm form = (ConfirmOrderForm) target;

		if (supports(form.getClass())) {

			boolean idOK = form.getUserId().startsWith("CUS");
			if (idOK == false) {
				form.setUserOK(false);
				errors.rejectValue("order", "order.been.cancelled");
			}

		}

	}

}
