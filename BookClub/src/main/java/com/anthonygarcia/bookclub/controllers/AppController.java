package com.anthonygarcia.bookclub.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.anthonygarcia.bookclub.models.LoginUser;
import com.anthonygarcia.bookclub.models.User;
import com.anthonygarcia.bookclub.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class AppController {
    
     @Autowired
     private UserService userServ;
    
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
        		session.setAttribute("user", newUser.getId());
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
        
       session.setAttribute("user", user.getId());
       
       return "redirect:/books";
    }
    
    @GetMapping("/books")
    public String dashboard(Model model, 
    		HttpSession session, 
    		RedirectAttributes flashAttributes) {
 
    	Long userId = (Long) session.getAttribute("user");
    	
    	if (userId == null) {
    		flashAttributes.addFlashAttribute("loginError", "You must be logged in to do that.");
    		return "redirect:/";
    	}
    	
    	Optional<User> loggedInUser = userServ.getUserById(userId);
    	
    	if (loggedInUser.isPresent()) {
    		User user = loggedInUser.get();
    		model.addAttribute("user", user);
    		return "books.jsp";
    	} else {
    		return "redirect:/login";
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
