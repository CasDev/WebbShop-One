package se.casdev.spring.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.org.apache.xerces.internal.util.URI;

import se.casdev.order.Order;
import se.casdev.product.Product;
import se.casdev.product.ProductType;
import se.casdev.product.StockType;
import se.casdev.repository.database.repository.link.MainLink;
import se.casdev.spring.web.form.AddProductForm;
import se.casdev.spring.web.form.LoginForm;
import se.casdev.spring.web.validator.LoginValidator;
import se.casdev.userInfo.Customer;
import se.casdev.userInfo.CustomerAvailability;

@Controller
@RequestMapping(value = "/")
public class IndexController {

	@Autowired
	private HttpSession session;

	private List<Product> list = addProductToFront();

	private ModelAndView setModelAndView(ModelAndView mav, String view) {

		mav.setViewName(view);
		Order order = (Order) session.getAttribute("order");
		if (order == null) {
			order = new Order();
			order.setProducts(new ArrayList<Product>());
			session.setAttribute("order", order);
		}
		if (view.equals("products") || view.equals("allproducts")) {
			List<Product> all = se.casdev.spring.web.controller.Controller.link
					.getProductLink().getAll();

			mav.addObject("products", all);
		} else {
			mav.addObject("products", list);
		}
		mav.addObject("add", new AddProductForm());
		mav.addObject("productInbox", order.getAmountOfProducts());

		return mav;

	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showIndex(Model model) {
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

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView getLogin(Model model) {
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

	@RequestMapping(value = "addIndex", method = RequestMethod.POST)
	public ModelAndView addToShoppingCart(Model model,
			@Valid AddProductForm form) {
		ModelAndView mav = new ModelAndView();

		if (!session.isNew()) {
			Order order = (Order) session.getAttribute("order");
			if (order == null) {
				Order nOrder = new Order();
				nOrder.setProducts(new ArrayList<Product>());
				session.setAttribute("order", nOrder);
				order = nOrder;
			}

			if (order.getAmountOfProducts() == 0) {
				List<Product> products = new ArrayList<Product>();
				Product product = se.casdev.spring.web.controller.Controller.link
						.getProductLink().getProduct(form.getId());
				products.add(product);
				order.setProducts(products);
				session.setAttribute("order", order);
			} else if (order.getAmountOfProducts() > 0) {
				List<Product> products = order.getProducts();
				products.add(se.casdev.spring.web.controller.Controller.link
						.getProductLink().getProduct(form.getId()));
				order.setProducts(products);
				session.setAttribute("order", order);
			}
			if (session.getAttribute("user") == null) {
				String emailOK = "";
				String passwordOK = "";
				mav.addObject("emailOK", emailOK);
				mav.addObject("passwordOK", passwordOK);
				mav = setModelAndView(mav, "products");
				mav.addObject("form", new LoginForm());
			} else {
				mav = setModelAndView(mav, "allproducts");
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

		}

		return mav;
	}

	private ModelAndView addAll() {

		ModelAndView mav = new ModelAndView();
		List<Product> list = se.casdev.spring.web.controller.Controller.link
				.getProductLink().getAll();

		mav.addObject("products", list);

		return null;
	}

	@RequestMapping(value = "addIndex", method = RequestMethod.GET)
	public ModelAndView getShoppingCart(Model model) {
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
				mav.addObject("cancelledorder", "");
				mav = setModelAndView(mav, "home");

				List<Product> list = (ArrayList<Product>) mav.getModel().get(
						"products");
				if (list.size() == 1) {
					mav.setViewName("noHome");
				}

				mav.addObject("form", new LoginForm());
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

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ModelAndView login(@Valid LoginForm form, Model model,
			BindingResult results, RedirectAttributes redirects) {
		ModelAndView mav = new ModelAndView();

		if (!session.isNew()) {
			LoginValidator valid = new LoginValidator();

			valid.validate(form, results);

			if (results.hasErrors()) {

				String emailOK = "";
				String passwordOK = "";

				if (form.isEmailOK() == false) {
					emailOK = "Email is not verified. Please, try changing it.";
				}
				if (form.isPasswordOK() == false) {
					passwordOK = "Password is not verified. Please, try changing it.";
				}

				mav.addObject("emailOK", emailOK);
				mav.addObject("passwordOK", passwordOK);
				mav = setModelAndView(mav, "index");

				List<Product> list = (ArrayList<Product>) mav.getModel().get(
						"products");
				if (list.size() == 1) {
					mav.setViewName("noIndex");
				}

				mav.addObject("form", form);

				return mav;
			} else {
				MainLink link = se.casdev.spring.web.controller.Controller.link;

				Customer user = link.getUserLink().login(form.getEmail(),
						form.getPassword());
				session.setAttribute("user", user);

				if (session.getAttribute("user") == null) {
					String emailOK = "Email and password doesn't fit together";
					String passwordOK = "";
					mav.addObject("emailOK", emailOK);
					mav.addObject("passwordOK", passwordOK);
					mav = setModelAndView(mav, "index");

					List<Product> list = (ArrayList<Product>) mav.getModel()
							.get("products");
					if (list.size() == 1) {
						mav.setViewName("noIndex");
					}

					mav.addObject("form", new LoginForm());

					return mav;

				} else {

					mav.addObject("cancelledorder", "");
					mav = setModelAndView(mav, "home");

					List<Product> list = (ArrayList<Product>) mav.getModel()
							.get("products");
					if (list.size() == 1) {
						mav.setViewName("noHome");
					}

					return mav;

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
