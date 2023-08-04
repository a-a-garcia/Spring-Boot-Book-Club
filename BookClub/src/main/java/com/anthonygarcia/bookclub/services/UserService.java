package com.anthonygarcia.bookclub.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.anthonygarcia.bookclub.models.LoginUser;
import com.anthonygarcia.bookclub.models.User;
import com.anthonygarcia.bookclub.repositories.UserRepository;
	    
	@Service
	public class UserService {
	    
	    @Autowired
	    private UserRepository userRepo;
	    
	    public boolean register(
	    		User newUser, 
	    		BindingResult result) {
	    	
	        Optional<User> potentialUser = userRepo.findByEmail(newUser.getEmail());
	        
	        if(potentialUser.isPresent()  == true) {
	        	result.rejectValue("email", "emailError", "Acccount using this email already exists.");
	        }
	        
	        if(!newUser.getPassword().equals(newUser.getConfirm())) {
	        	result.rejectValue("confirm", "confirmError", "Password fields do not match.");
	        }
	        
	        if(result.hasErrors()) {
	        	return false;
	        }
	        
	        String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
	        newUser.setPassword(hashed);
	        userRepo.save(newUser);
	        return true;
	    }
	    
	    public User login(
	    		LoginUser newLoginObject, 
	    		BindingResult result) {
	    	if (result.hasErrors()) {
	    		return null;
	    	}
	        
	    	Optional<User> potentialUser = userRepo.findByEmail(newLoginObject.getEmail());
	        
	    	if (!potentialUser.isPresent()) {
	    		result.rejectValue("email", "emailError", "Account with this email does not exist.");
	    		return null;
	    	}
	        
	    	User user = potentialUser.get();
	       
	    	if (!BCrypt.checkpw(newLoginObject.getPassword(), user.getPassword())) {
	    		result.rejectValue("password", "Matches", "Invalid Password!");
	    		return null;
	    	}
	        return user;
	    }
	    
	    public Optional<User> getUserById(Long id) {
	    	return userRepo.findById(id);
	    }
	}
