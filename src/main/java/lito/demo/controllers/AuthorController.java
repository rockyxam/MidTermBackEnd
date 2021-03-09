package lito.demo.controllers;


import lito.demo.models.Author;
import lito.demo.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/")
public class AuthorController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String redirect() {
		return "redirect:greeting";
	}
	public static final Logger logger = LoggerFactory.getLogger(AuthorController.class);

	@Autowired
	private AuthorService authorService;


	@RequestMapping(value="/list_authors" , method = RequestMethod.GET)
	public ModelAndView ListAuthors(ModelAndView model) throws IOException {
		List<Author> listAuthors = authorService.getAllAuthors();
		model.addObject("listAuthors", listAuthors);
		model.setViewName("authors");

		return model;
	}

	@RequestMapping(value = "/add_author", method = RequestMethod.GET)
	public ModelAndView newAuthor(ModelAndView model) {
		Author author = new Author();
		model.addObject("author", author);
		model.setViewName("addAuthor");

		return model;
	}
	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}



	@RequestMapping(value = "/save_author", method = RequestMethod.POST)
	public ModelAndView saveAuthor(@ModelAttribute Author author) {
		if (author.getId() == 0) {
			authorService.addAuthor(author);
		} else {
			authorService.updateAuthor(author.getId() , author);
		}
		return new ModelAndView("redirect:/list_authors");
	}

	@RequestMapping(value = "/edit_author", method = RequestMethod.GET)
	public ModelAndView updateAuthor(HttpServletRequest request) {
		int authorId = Integer.parseInt(request.getParameter("id"));
		Author author=authorService.getAuthor(authorId);
		ModelAndView model = new ModelAndView("addAuthor");
		model.addObject("author",author);

		return model;
	}

	@RequestMapping(value = "/delete_author", method = RequestMethod.GET)
	public ModelAndView deleteAuthor(HttpServletRequest request) {
		int authorId = Integer.parseInt(request.getParameter("id"));
		authorService.deleteAuthor(authorId);
		return new ModelAndView("redirect:/list_authors");
	}
}
