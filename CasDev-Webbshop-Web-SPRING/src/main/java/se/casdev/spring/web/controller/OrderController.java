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

import se.casdev.order.Order;
import se.casdev.product.Product;
import se.casdev.product.ProductType;
import se.casdev.product.StockType;
import se.casdev.spring.web.form.AddProductForm;
import se.casdev.spring.web.form.ConfirmOrderForm;
import se.casdev.spring.web.form.LoginForm;
import se.casdev.spring.web.model.Payment;
import se.casdev.spring.web.validator.ConfirmOrderValidator;
import se.casdev.userInfo.Customer;

@Controller
public class OrderController {

	@Autowired
	private HttpSession session;

	@RequestMapping(value = "/confirmOrder", method = RequestMethod.GET)
	public ModelAndView redirectIndex(Model model) {
		ModelAndView mav = new ModelAndView();

		String emailOK = "";
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
	}

	@RequestMapping(value = "/confirmOrder", method = RequestMethod.POST)
	public ModelAndView confirmOrder(Model mode, @Valid ConfirmOrderForm form,
			BindingResult results, RedirectAttributes redirects) {
		ModelAndView mav = new ModelAndView();

		if (session.isNew()) {

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

				if (session.getAttribute("order") == null) {

					String emailOK = "";
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

				} else {

					Order order = (Order) session.getAttribute("order");

					if (order.getAmountOfProducts() == 0) {

						String emailOK = "";
						String passwordOK = "";
						mav.addObject("emailOK", emailOK);
						mav.addObject("passwordOK", passwordOK);
						mav = setModelAndView(mav, "index");

						List<Product> list = (ArrayList<Product>) mav
								.getModel().get("products");
						if (list.size() == 1) {
							mav.setViewName("noIndex");
						}

						mav.addObject("form", new LoginForm());

					} else {

						form.setPayment(Integer.valueOf(order.getTotalPrice()));
						Customer user = (Customer) session.getAttribute("user");
						form.setUserId(user);

						List<Product> list = (ArrayList<Product>) mav
								.getModel().get("products");

						ConfirmOrderValidator validator = new ConfirmOrderValidator();
						validator.validate(form, results);

						if (results.hasErrors()) {

							String cancelled = "";
							if (form.isUserOK() == false) {
								cancelled = "Your order wasn't passed";
							}

							mav.addObject("cancelledorder", cancelled);
							mav = setModelAndView(mav, "home");

							list = (ArrayList<Product>) mav.getModel().get(
									"products");
							if (list.size() == 1) {
								mav.setViewName("noHome");
							}

						} else {

							String cancelled = "";
							List<Product> products = order.getProducts();
							boolean isAway = se.casdev.spring.web.controller.Controller.link
									.getOrderLink().saveOrder(form.getUserId(),
											products);
							if (isAway) {
								cancelled = "Your order has been finalized. Please await an confirmation off your order onto your email.";
							} else {
								cancelled = "Your order wasn't passed";
							}

							mav.addObject("cancelledorder", cancelled);
							mav = setModelAndView(mav, "home");

							List<Product> prods = se.casdev.spring.web.controller.Controller.link
									.getProductLink().getAll();
							mav.addObject("products", prods);

							list = (ArrayList<Product>) mav.getModel().get(
									"products");
							if (list.size() == 1) {
								mav.setViewName("noHome");
							}

						}

					}

				}

			}

		}

		return mav;

	}

	@RequestMapping(value = "/orderOnward", method = RequestMethod.GET)
	public ModelAndView orderYourOrder(Model model) {
		ModelAndView mav = new ModelAndView();

		if (session.isNew()) {

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

				if (session.getAttribute("order") == null) {

					String emailOK = "";
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

				} else {

					Order order = (Order) session.getAttribute("order");

					if (order.getAmountOfProducts() == 0) {

						String emailOK = "";
						String passwordOK = "";
						mav.addObject("emailOK", emailOK);
						mav.addObject("passwordOK", passwordOK);
						mav = setModelAndView(mav, "index");

						List<Product> list = (ArrayList<Product>) mav
								.getModel().get("products");
						if (list.size() == 1) {
							mav.setViewName("noIndex");
						}

						mav.addObject("form", new LoginForm());

					} else {

						mav = setModelAndView(mav, "setOrder");

						List<Product> list = (ArrayList<Product>) mav
								.getModel().get("products");
						if (list.size() == 1) {
							mav.setViewName("noIndex");
						}

						mav.addObject("confirm", new ConfirmOrderForm());
						mav.addObject("payment", Payment.getWaysOfPayment());
						mav.addObject("total", order.getTotalPrice());

					}

				}

			}

		}

		return mav;
	}

	@RequestMapping(value = "/dropIndex", method = RequestMethod.GET)
	public ModelAndView goToIndex(Model mode) {
		ModelAndView mav = new ModelAndView();

		if (session.isNew()) {

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

			if (session.getAttribute("user") == null) {
				String emailOK = "";
				String passwordOK = "";
				mav.addObject("emailOK", emailOK);
				mav.addObject("passwordOK", passwordOK);
				Order order = (Order) session.getAttribute("order");
				if (order == null) {

					order = setOrder();

				}

				mav = setModelAndView(mav, "seeOrder");

				List<Product> list = (ArrayList<Product>) mav.getModel().get(
						"products");
				if (list.size() == 1) {
					mav.setViewName("seeNoOrder");
				}

				mav.addObject("form", new LoginForm());

			} else {
				Order order = (Order) session.getAttribute("order");

				if (order == null) {

					order = setOrder();

				}

				mav = setModelAndView(mav, "orderOrder");

				List<Product> list = (ArrayList<Product>) mav.getModel().get(
						"products");
				if (list.size() == 1) {
					mav.setViewName("orderNoOrder");
				}

			}

		}

		return mav;

	}

	private Order setOrder() {
		Order order = new Order();
		order.setProducts(new ArrayList<Product>());

		return order;
	}

	@RequestMapping(value = "/dropIndex", method = RequestMethod.POST)
	public ModelAndView dropItemFromOrder(Model model,
			@Valid AddProductForm form) {
		ModelAndView mav = new ModelAndView();

		if (session.isNew()) {

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

			String id = form.getId();
			Order order = (Order) session.getAttribute("order");
			List<Product> products = order.getProducts();
			List<Product> nProducts = dropProduct(id, products);
			order.setProducts(nProducts);
			session.setAttribute("order", order);

			if (session.getAttribute("user") == null) {
				String emailOK = "";
				String passwordOK = "";
				mav.addObject("emailOK", emailOK);
				mav.addObject("passwordOK", passwordOK);
				mav = setModelAndView(mav, "seeOrder");

				List<Product> list = (ArrayList<Product>) mav.getModel().get(
						"products");
				if (list.size() == 1) {
					mav.setViewName("seeNoOrder");
				}

				mav.addObject("form", new LoginForm());
			} else {
				mav = setModelAndView(mav, "orderOrder");

				List<Product> list = (ArrayList<Product>) mav.getModel().get(
						"products");
				if (list.size() == 1) {
					mav.setViewName("orderNoOrder");
				}

			}

		}

		return mav;

	}

	private List<Product> dropProduct(String id, List<Product> products) {
		List<Product> list = new ArrayList<Product>();

		for (Product product : products) {

			if (product.getProdctId().equals(id)) {
				// do nothing
			} else {
				list.add(product);
			}

		}

		return list;
	}

	@RequestMapping(value = "/goOrder", method = RequestMethod.GET)
	public ModelAndView lookAtOrder(Model model) {
		ModelAndView mav = new ModelAndView();

		if (session.isNew()) {

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

			if (session.getAttribute("user") == null) {
				String emailOK = "";
				String passwordOK = "";
				mav.addObject("emailOK", emailOK);
				mav.addObject("passwordOK", passwordOK);
				Order order = (Order) session.getAttribute("order");

				if (order == null) {

					order = setOrder();

				}

				mav = setModelAndView(mav, "seeOrder");

				List<Product> list = (ArrayList<Product>) mav.getModel().get(
						"products");
				if (list.size() == 1) {
					mav.setViewName("seeNoOrder");
				}

				mav.addObject("form", new LoginForm());
			} else {
				Order order = (Order) session.getAttribute("order");

				if (order == null) {

					order = setOrder();

				}

				mav = setModelAndView(mav, "orderOrder");

				List<Product> list = (ArrayList<Product>) mav.getModel().get(
						"products");
				if (list.size() == 1) {
					mav.setViewName("orderNoOrder");
				}

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
		List<Product> list = addProductToFront();
		mav.addObject("products", list);
		mav.addObject("add", new AddProductForm());
		mav.addObject("productInbox", order.getAmountOfProducts());

		return mav;

	}

	private List<Product> addProductToFront() {
		Order order = (Order) session.getAttribute("order");
		List<Product> lists = order.getProducts();
		List<Product> list = new ArrayList<Product>();

		if (lists.size() > 0) {

			list = lists;

		} else if (lists.size() == 0) {
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
