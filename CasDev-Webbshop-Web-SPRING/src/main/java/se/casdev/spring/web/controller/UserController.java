package se.casdev.spring.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import se.casdev.order.Order;
import se.casdev.product.Product;
import se.casdev.product.ProductType;
import se.casdev.product.StockType;
import se.casdev.repository.database.repository.link.MainLink;
import se.casdev.repository.database.repository.link.Validator;
import se.casdev.spring.web.form.AddProductForm;
import se.casdev.spring.web.form.LoginForm;
import se.casdev.spring.web.form.UpdateUserForm;
import se.casdev.spring.web.form.UserCreateForm;
import se.casdev.spring.web.validator.UpdateUserValidator;
import se.casdev.spring.web.validator.UserValidator;
import se.casdev.userInfo.Customer;

@Controller
public class UserController {

	@Autowired
	private HttpSession session;

	private List<Product> list = addProductToFront();

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView validateUser(@PathVariable String id) {
		ModelAndView mav = new ModelAndView();

		boolean startOK = id.startsWith("CUS");
		if (startOK) {

			se.casdev.spring.web.controller.Controller.link.getUserLink()
					.activate(id);
			se.casdev.spring.web.controller.Controller.link.getUserLink()
					.updateUserReadOnly(false, id);

			String emailOK = "";
			String passwordOK = "";
			mav.addObject("emailOK", emailOK);
			mav.addObject("passwordOK", passwordOK);
			mav = setModelAndView(mav, "index");

			List<Product> list = (ArrayList<Product>) mav.getModel().get(
					"products");
			if (list.size() == 1) {
				mav.setViewName("noIndex");
			}

			mav.addObject("form", new LoginForm());
		} else {
			String emailOK = "";
			String passwordOK = "";
			mav.addObject("emailOK", emailOK);
			mav.addObject("passwordOK", passwordOK);
			mav = setModelAndView(mav, "index");

			List<Product> list = (ArrayList<Product>) mav.getModel().get(
					"products");
			if (list.size() == 1) {
				mav.setViewName("noIndex");
			}

			mav.addObject("form", new LoginForm());
		}

		return mav;
	}

	@RequestMapping(value = "/user-update", method = RequestMethod.POST)
	public ModelAndView updateUser(Model model, @Valid UpdateUserForm form,
			BindingResult results, RedirectAttributes redirects) {
		ModelAndView mav = new ModelAndView();

		if (!session.isNew()) {

			if (session.getAttribute("user") == null) {
				String emailOK = "";
				String passwordOK = "";
				mav.addObject("emailOK", emailOK);
				mav.addObject("passwordOK", passwordOK);
				mav = setModelAndView(mav, "index");

				List<Product> list = (ArrayList<Product>) mav.getModel().get(
						"products");
				if (list.size() == 1) {
					mav.setViewName("noIndex");
				}

				mav.addObject("form", new LoginForm());
			} else {

				UpdateUserValidator valid = new UpdateUserValidator();

				Customer user = (Customer) session.getAttribute("user");
				form.setUser(user);

				valid.validate(form, results);

				if (results.hasErrors()) {

					String emailOK = "";
					String lastnameOK = "";
					String passwordOK = "";
					String cityOK = "";
					String countryOK = "";
					String zipOK = "";
					String areaOK = "";
					String streetOK = "";

					if (form.isEmailOK() == false) {
						emailOK = "Email wrongly formulated for changing. Something like yourname@mail.com";
					}

					if (form.isLastnameOK() == false) {
						lastnameOK = "Lastname is to short";
					}

					if (form.isPasswordOK() == false) {
						passwordOK = "Password can't be verified.";
					}

					if (form.isCityOK() == false) {
						cityOK = "City must not be empty";
					}

					if (form.isCountryOK() == false) {
						countryOK = "Country must not be empty";
					}

					if (form.isAreaOK() == false) {
						areaOK = "Area must not be empty";
					}

					if (form.isStreetOK() == false) {
						streetOK = "Street must not be empty";
					}

					if (form.isZipOK() == false) {
						zipOK = "Zip has to be 5 digits";
					}

					mav.addObject("emailOK", emailOK);
					mav.addObject("lastnameOK", lastnameOK);
					mav.addObject("passwordOK", passwordOK);
					mav.addObject("areaOK", areaOK);
					mav.addObject("cityOK", cityOK);
					mav.addObject("countryOK", countryOK);
					mav.addObject("zipOK", zipOK);
					mav.addObject("streetOK", streetOK);
					mav = setModelAndView(mav, "updateUser");
					mav.addObject("form", form);

					return mav;
				} else {
					MainLink link = se.casdev.spring.web.controller.Controller.link;

					boolean emailIsSame = (form.getEmail().equals(form
							.getUser().getEmail()));

					if (!emailIsSame) {

						boolean isOK = Validator.validateEmail(form.getEmail());

						if (isOK) {

							boolean isChanged = link.getUserLink()
									.updateUserEmail(form.getEmail(),
											form.getUser().getUserId());

							if (isChanged) {

								Customer cus = (Customer) session
										.getAttribute("user");
								cus.setEmail(form.getEmail());
								session.setAttribute("user", cus);

							}

						}

					}

					boolean isOK = Validator.validatePassword(form
							.getPassword());

					if (isOK) {

						boolean isChanged = link.getUserLink()
								.updateUserPassword(form.getPassword(),
										form.getUser().getPassword(),
										form.getUser().getUserId());

						if (isChanged) {

							Customer cus = (Customer) session
									.getAttribute("user");
							cus.setPassword(form.getPassword());
							session.setAttribute("user", cus);

						}

					}

					boolean lastnameIsSame = (form.getLastname().equals(form
							.getUser().getLastname()));

					if (!form.getLastname().equals("") && !lastnameIsSame) {

						boolean usernameOK = (form.getLastname().length() > 1);

						if (usernameOK && !lastnameIsSame) {

							boolean changed = link.getUserLink()
									.updateUserLastname(form.getLastname(),
											form.getUser().getUserId());

							if (changed) {

								user = (Customer) session.getAttribute("user");
								user.setLastname(form.getLastname());
								session.setAttribute("user", user);

							}

						}

					}

					boolean addressIsOK = (Validator.validateAddress(
							form.getCountry(), form.getArea(), form.getZip(),
							form.getStreet(), form.getCity()));
					boolean addressIsSame = (form.getStreet().equals(
							user.getAddress().getStreet())
							&& form.getArea().equals(
									user.getAddress().getArea())
							&& form.getCity().equals(
									user.getAddress().getCity()) && form
							.getZip().equals(user.getAddress().getZip()));

					// TODO: validate them separatley
					if (addressIsOK && !addressIsSame) {

						boolean isChanged = link.getUserLink()
								.updateUserAddress(form.getCountry(),
										form.getCity(), form.getZip(),
										form.getArea(), form.getStreet(),
										form.getUser().getUserId());

						if (isChanged) {

							user = (Customer) session.getAttribute("user");
							session.setAttribute("user", user);

						}

					}

				}
				mav = setModelAndView(mav, "home");
				mav.addObject("cancelledorder", "");

				List<Product> list = (ArrayList<Product>) mav.getModel().get(
						"products");
				if (list.size() == 1) {
					mav.setViewName("noHome");
				}

			}

		} else {

			String emailOK = "";
			String passwordOK = "";
			mav.addObject("emailOK", emailOK);
			mav.addObject("passwordOK", passwordOK);
			mav = setModelAndView(mav, "index");

			List<Product> list = (ArrayList<Product>) mav.getModel().get(
					"products");
			if (list.size() == 1) {
				mav.setViewName("noIndex");
			}

			mav.addObject("form", new LoginForm());
		}

		return mav;

	}

	@RequestMapping(value = "/user-create", method = RequestMethod.POST)
	public ModelAndView createUser(@Valid UserCreateForm form, Model model,
			BindingResult results, RedirectAttributes redirects) {
		ModelAndView mav = new ModelAndView();

		if (!session.isNew()) {

			if (session.getAttribute("user") != null) {

				String emailOK = "";
				String passwordOK = "";
				mav.addObject("emailOK", emailOK);
				mav.addObject("passwordOK", passwordOK);
				mav = setModelAndView(mav, "index");

				List<Product> list = (ArrayList<Product>) mav.getModel().get(
						"products");
				if (list.size() == 1) {
					mav.setViewName("noIndex");
				}

				mav.addObject("form", new LoginForm());
				return mav;
			} else {

				UserValidator valid = new UserValidator();

				valid.validate(form, results);

				if (results.hasErrors()) {
					String firstnameOK = "";
					String lastnameOK = "";
					String birthdateOK = "";
					String passwordOK = "";
					String passwordVerifiedOK = "";
					String emailOK = "";
					String zipOK = "";
					String areaOK = "";
					String streetOK = "";
					String countryOK = "";
					String cityOK = "";

					if (form.isFirstnameOK() == false) {
						firstnameOK = "Firstname is to short. Please, maybe enter an middle-name?";
					}
					if (form.isLastnameOK() == false) {
						lastnameOK = "Lastname is to short. Can't comprehend";
					}
					if (form.isVerified() == false && form.isPasswordOK()) {
						passwordVerifiedOK = "Password could not be verified";
					}
					if (form.isPasswordOK() == false) {
						passwordOK = "Password is too short. Pleasem try a longer one";
					}
					if (form.isEmailOK() == false) {
						emailOK = "Email is wrongly formulated. Example: yourname@mail.com";
					}
					if (form.isBirthdateOK() == false) {
						birthdateOK = "Formate your birthdate as following: YY-MM-DD";
					}
					if (form.isAddressOK() == false) {
						zipOK = "Zip may be wrongly formulated. Make sure it is 5 digits";
						cityOK = "City mustn't be empty";
						countryOK = "Country musn't be empty";
						areaOK = "Area musn't be empty";
						streetOK = "Street mustn't be empty";
					}

					mav.addObject("zipOK", zipOK);
					mav.addObject("countryOK", countryOK);
					mav.addObject("areaOK", areaOK);
					mav.addObject("streetOK", streetOK);
					mav.addObject("cityOK", cityOK);
					mav.addObject("firstnameOK", firstnameOK);
					mav.addObject("lastnameOK", lastnameOK);
					mav.addObject("birthdateOK", birthdateOK);
					mav.addObject("passwordOK", passwordOK);
					mav.addObject("passwordVerifiedOK", passwordVerifiedOK);
					mav.addObject("emailOK", emailOK);
					mav = setModelAndView(mav, "create");
					mav.addObject("form", form);

					return mav;
				} else {
					MainLink link = se.casdev.spring.web.controller.Controller.link;

					boolean isOK = link.getUserLink().signup(
							form.getFirstname(), form.getLastname(),
							form.getEmail(), form.getPassword(),
							form.getBirthdate(), form.getCountry(),
							form.getArea(), form.getZip(), form.getStreet(),
							form.getCity());

					if (isOK) {

						Customer customer = link.getUserLink().login(
								form.getEmail(), form.getPassword());
						session.setAttribute("user", customer);

					}

				}
				mav.addObject("cancelledorder", "");
				mav = setModelAndView(mav, "home");

				List<Product> list = (ArrayList<Product>) mav.getModel().get(
						"products");
				if (list.size() == 1) {
					mav.setViewName("noHome");
				}

			}

		} else {
			String emailOK = "";
			String passwordOK = "";
			mav.addObject("emailOK", emailOK);
			mav.addObject("passwordOK", passwordOK);
			mav = setModelAndView(mav, "index");

			List<Product> list = (ArrayList<Product>) mav.getModel().get(
					"products");
			if (list.size() == 1) {
				mav.setViewName("noIndex");
			}

			mav.addObject("form", new LoginForm());
		}

		return mav;
	}

	@RequestMapping(value = "/user-create", method = RequestMethod.GET)
	public ModelAndView createForm(Model model) {
		ModelAndView mav = new ModelAndView();
		if (!session.isNew()) {
			mav = setModelAndView(mav, "create");
			mav.setViewName("create");
			mav.addObject("form", new UserCreateForm());
		} else {
			String emailOK = "";
			String passwordOK = "";
			mav.addObject("emailOK", emailOK);
			mav.addObject("passwordOK", passwordOK);
			mav = setModelAndView(mav, "index");

			List<Product> list = (ArrayList<Product>) mav.getModel().get(
					"products");
			if (list.size() == 1) {
				mav.setViewName("noIndex");
			}

			mav.addObject("form", new LoginForm());
		}

		return mav;

	}

	@RequestMapping(value = "/signOut", method = RequestMethod.GET)
	public ModelAndView signOut(Model model) {
		ModelAndView mav = new ModelAndView();

		if (!session.isNew()) {

			if (session.getAttribute("user") != null) {
				session.removeAttribute("user");
				session.invalidate();
				session.removeAttribute("order");
				mav = setModelAndView(mav, "index");

				List<Product> list = (ArrayList<Product>) mav.getModel().get(
						"products");
				if (list.size() == 1) {
					mav.setViewName("noIndex");
				}

				Order order = new Order();
				order.setProducts(new ArrayList<Product>());
				session.setAttribute("order", order);
				String emailOK = "";
				String passwordOK = "";
				mav.addObject("emailOK", emailOK);
				mav.addObject("passwordOK", passwordOK);
				mav.addObject("form", new LoginForm());
			} else {
				mav = setModelAndView(mav, "index");

				List<Product> list = (ArrayList<Product>) mav.getModel().get(
						"products");
				if (list.size() == 1) {
					mav.setViewName("noIndex");
				}

				String emailOK = "";
				String passwordOK = "";
				mav.addObject("emailOK", emailOK);
				mav.addObject("passwordOK", passwordOK);
				mav.addObject("form", new LoginForm());
			}

			return mav;
		} else {
			String emailOK = "";
			String passwordOK = "";
			mav.addObject("emailOK", emailOK);
			mav.addObject("passwordOK", passwordOK);
			mav = setModelAndView(mav, "index");

			List<Product> list = (ArrayList<Product>) mav.getModel().get(
					"products");
			if (list.size() == 1) {
				mav.setViewName("noIndex");
			}

			mav.addObject("form", new LoginForm());

			return mav;
		}

	}

	@RequestMapping(value = "/user-update", method = RequestMethod.GET)
	public ModelAndView updateUser(Model model) {

		ModelAndView mav = new ModelAndView();

		if (!session.isNew()) {

			if (session.getAttribute("user") != null) {
				Customer user = (Customer) session.getAttribute("user");
				UpdateUserForm form = new UpdateUserForm();
				form.setUser(user);
				mav.addObject("form", form);
				mav = setModelAndView(mav, "updateUser");
			} else {
				String emailOK = "";
				String passwordOK = "";
				mav.addObject("emailOK", emailOK);
				mav.addObject("passwordOK", passwordOK);
				mav.addObject("form", new LoginForm());
				mav = setModelAndView(mav, "index");

				List<Product> list = (ArrayList<Product>) mav.getModel().get(
						"products");
				if (list.size() == 1) {
					mav.setViewName("noIndex");
				}

			}

		} else {

			String emailOK = "";
			String passwordOK = "";
			mav.addObject("emailOK", emailOK);
			mav.addObject("passwordOK", passwordOK);
			mav.addObject("form", new LoginForm());
			mav.addObject("cancelledorder", "");
			mav = setModelAndView(mav, "home");

			List<Product> list = (ArrayList<Product>) mav.getModel().get(
					"products");
			if (list.size() == 1) {
				mav.setViewName("noHome");
			}

		}

		return mav;

	}

	private ModelAndView setModelAndView(ModelAndView mav, String view) {

		mav.setViewName(view);
		Order order = (Order) session.getAttribute("order");
		if (order == null) {
			order = new Order();
			order.setProducts(new ArrayList<Product>());
			session.setAttribute("order", order);
		}
		mav.addObject("products", list);
		mav.addObject("add", new AddProductForm());
		mav.addObject("productInbox", order.getAmountOfProducts());

		return mav;

	}

	private List<Product> addProductToFront() {
		List<Product> list = new ArrayList<Product>();
		List<Product> all = se.casdev.spring.web.controller.Controller.link
				.getProductLink().getAll();

		if (all.size() > 5) {

			for (int i = 0; i < 5; i++) {
				int total = (int) (Math.random() * all.size());
				Product prod = all.get(total);
				list.add(prod);
			}

		} else {
			Product prod = new Product();
			prod.setArtist("Sorry. No up-front-shows available");
			prod.setCategoryId("xxx000");
			prod.setInStock("0");
			prod.setLabel("No available");
			prod.setPicture(null);
			prod.setPrice("000");
			prod.setProdctId("xxx000");
			prod.setRelease("xxxx");
			prod.setTitle("No available");
			prod.setType(ProductType.COMPACT_DISC);
			prod.setTypeInStock(StockType.NOT_IN);
			list.add(prod);
		}

		return list;

	}

}
