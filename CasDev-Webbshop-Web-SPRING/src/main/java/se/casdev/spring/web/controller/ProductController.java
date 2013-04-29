package se.casdev.spring.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import se.casdev.order.Order;
import se.casdev.product.Product;
import se.casdev.spring.web.form.AddProductForm;
import se.casdev.spring.web.form.LoginForm;

@Controller
public class ProductController {

	@Autowired
	private HttpSession session;

	private List<Product> list = addProductToFront();

	private List<Product> addProductToFront() {

		List<Product> all = se.casdev.spring.web.controller.Controller.link
				.getProductLink().getAll();

		return all;

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

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ModelAndView getAllProducts(Model model) {

		ModelAndView mav = new ModelAndView();

		if (!session.isNew()) {
			if (session.getAttribute("user") != null) {
				mav = setModelAndView(mav, "allproducts");
			} else {
				String emailOK = "";
				String passwordOK = "";
				mav.addObject("emailOK", emailOK);
				mav.addObject("passwordOK", passwordOK);
				mav = setModelAndView(mav, "products");
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

}
