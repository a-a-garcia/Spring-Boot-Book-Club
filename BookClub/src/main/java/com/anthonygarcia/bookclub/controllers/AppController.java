package com.anthonygarcia.bookclub.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.anthonygarcia.bookclub.models.Book;
import com.anthonygarcia.bookclub.models.LoginUser;
import com.anthonygarcia.bookclub.models.User;
import com.anthonygarcia.bookclub.services.BookService;
import com.anthonygarcia.bookclub.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class AppController {
    
     @Autowired
     private UserService userServ;
     
     @Autowired
     private BookService bookServ;
    
    @GetMapping("/")
    public String index(
    		Model model, 
    		HttpSession session) {
    	
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index.jsp";
        
    }
    
    @PostMapping("/register")
    public String register(
    		@Valid 
    		@ModelAttribute("newUser") User newUser, 
            BindingResult result, 
            Model model, 
            HttpSession session) {
    	
        	if(!userServ.register(newUser, result)) {
        		model.addAttribute("newLogin", new LoginUser());
        		return "index.jsp";
        	} else {
        		userServ.register(newUser, result);
        		session.setAttribute("userId", newUser.getId());
        		return "redirect:/books";
        	}
    };
    
    @PostMapping("/login")
    public String login(
    		@Valid 
    		@ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, 
            Model model, 
            HttpSession session) {
       
    	User user = userServ.login(newLogin, result);
    
        if (user == null) {
        	model.addAttribute("newUser", new User());
        	return "index.jsp";
        }
        
       session.setAttribute("userId", user.getId());
       
       return "redirect:/books";
    }
    
    @GetMapping("/books")
    public String dashboard(Model model, 
    		HttpSession session, 
    		RedirectAttributes flashAttributes) {
 
    	Long userId = (Long) session.getAttribute("userId");
    	
    	if (userId == null) {
    		flashAttributes.addFlashAttribute("loginError", "You must be logged in to do that.");
    		return "redirect:/";
    	}
    	
    	Optional<User> loggedInUser = userServ.getUserById(userId);
    	
    	if (loggedInUser.isPresent()) {
    		User user = loggedInUser.get();
    		model.addAttribute("user", user);
    		model.addAttribute("allBooks", bookServ.allBook());
    		return "books.jsp";
    	} else {
    		return "redirect:/login";
    	}
    }
    
    @GetMapping("/books/new")
    public String createBook(@ModelAttribute("book") Book book, Model model, HttpSession session, RedirectAttributes flashAttributes) {
    	Long userId = (Long) session.getAttribute("userId");
    	if (userId == null) {
    		flashAttributes.addFlashAttribute("loginError", "You must be logged in to do that.");
    		return "redirect:/";
    	} else {
    	model.addAttribute("book", book);
    	return "create.jsp" ;
    }
    }
    
    @PostMapping("/books/new/submit")
    public String createBook(@Valid @ModelAttribute("book") Book book, BindingResult result, Model model,  HttpSession session) {
    	if (result.hasErrors()) {
    		model.addAttribute("book", book);
    		return "create.jsp";
    	} else {
    		Long userId = (Long) session.getAttribute("userId");
    		Optional<User>loggedInUser = userServ.getUserById(userId);
    		User user = loggedInUser.get();
    		
    		book.setUser(user);
    		bookServ.createBook(book);
    		return "redirect:/books";
    	}
    }
    
    @GetMapping("/books/{book_id}")
    public String showBook(@PathVariable Long book_id, Model model, HttpSession session, RedirectAttributes flashAttributes) {
    	Long userId = (Long) session.getAttribute("userId");
    	if (userId == null) {
    		flashAttributes.addFlashAttribute("loginError", "You must be logged in to do that.");
    		return "redirect:/";
    	} else {
    	Book book = bookServ.findBook(book_id);
    	model.addAttribute("book", book);
    	return "show.jsp";
    }
    }
    
    @PostMapping("/books/{book_id}/delete")
    public String deleteBook(@PathVariable Long book_id,  Model model, HttpSession session, RedirectAttributes flashAttributes) {
    	Book book = bookServ.findBook(book_id);
    	Long userId = (Long) session.getAttribute("userId");
    	if (book.getUser().getId() == userId) {
    		bookServ.deleteBook(book_id);
    		return "redirect:/books";
    	} else {
    		flashAttributes.addFlashAttribute("errorMessage", "**You are not authorized to delete this book.");
    		model.addAttribute("allBooks", bookServ.allBook());
    		return "books.jsp";
    	}
    }
    
    @GetMapping("/books/{id}/edit")
    public String editBook(@PathVariable("id") Long id, Model model, HttpSession session, RedirectAttributes flashAttributes) {
    	Long userId = (Long) session.getAttribute("userId");
    	if (userId == null) {
            flashAttributes.addFlashAttribute("loginError", "You must be logged in to do that.");
            return "redirect:/"; // Redirect to the login page if the user is not logged in
        }
    	Book book = bookServ.findBook(id);
    	if (book.getUser() != null && book.getUser().getId() == userId) {
    		model.addAttribute("book", book);
    		return "edit.jsp";
    	} else {
    		flashAttributes.addFlashAttribute("errorMessage", "**You are not authorized to edit this book!");
    		model.addAttribute("allBooks", bookServ.allBook());
    		return "redirect:/books";
    	}
    }
    
    @RequestMapping(value="/books/{id}/edit/submit", method=RequestMethod.PUT)
    public String editBook(@Valid @ModelAttribute("book") Book book, BindingResult result, Model model) {
    	if (result.hasErrors()) {
    		model.addAttribute("book", book);
    		return "edit.jsp";
    	} else {
    		bookServ.updateBook(book);
    		System.out.println(book.getId());
    		return "redirect:/books";
    	}
    }
    
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
    	return "redirect:/";
    }
}

//YOU CAN USE THIS TO SEE WHAT IS IN SESSION, IF THERES NOTHING THEN IT WILL SAY SESSION IS EMPTY.
//if (session.getAttributeNames().hasMoreElements()) {
//    for (String attributeName : Collections.list(session.getAttributeNames())) {
//        Object attributeValue = session.getAttribute(attributeName);
//        System.out.println(attributeName + " : " + attributeValue);
//    }
//} else {
//    System.out.println("Session is empty.");
//}
