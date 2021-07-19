package com.codingdojo.beltexam.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.beltexam.models.Task;
import com.codingdojo.beltexam.models.User;
import com.codingdojo.beltexam.services.TaskService;
import com.codingdojo.beltexam.services.UserService;
import com.codingdojo.beltexam.validator.UserValidator;

@Controller
public class MainController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserValidator userValidator;
	@Autowired
	private TaskService taskService;
    
    
	@GetMapping("/")
    public String logAndReg(@ModelAttribute("user") User user) {
        return "LogReg.jsp";
    }
    
	@PostMapping("/registerUser")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
    	userValidator.validate(user, result);
    	if(result.hasErrors()) {
    		return "LogReg.jsp";
    	} 
    	User u = userService.registerUser(user);
    	session.setAttribute("userId", u.getId());
    	return "redirect:/tasks";
    }

    
    @PostMapping("/loginprocess")
    public String loginUser(@RequestParam("email") String email, 
    						@RequestParam("password") String password, 
    						HttpSession session, 
    						RedirectAttributes redirectAttributes) {  
    	
    	if(email.equals("")) {
    		redirectAttributes.addFlashAttribute("error", "Invalid Crudentials");
    		return "redirect:/";
    	}
    	if(userService.authenticateUser(email, password)) {
    		User user =userService.findByEmail(email);
    		session.setAttribute("userId", user.getId());
    		return "redirect:/tasks";
    	}
    	else {
    		redirectAttributes.addFlashAttribute("error", "Invalid Crudentials");
    		return "redirect:/";
    	}
    }
    
    
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
    	return "redirect:/";
    }
    
    
//////////////////////////////////////////////////////////////////////////////////////
    
    
    @GetMapping("/tasks")
    public String home(HttpSession session, Model model) {
    	Long userId = (Long) session.getAttribute("userId");
    	if (userId ==null) {
    		return "redirect:/";
    	}else {
    	
	    	User u = userService.findUserById(userId);
	    	model.addAttribute("user", u);
	    	
	    	List<Task> tasks = taskService.allTasks();
	    	model.addAttribute("tasks", tasks);
	 
	    	return "homePage.jsp";
    	}
    }
    
//////////////////////////////////////////////////////////////////////////////////////
   
    	@GetMapping("/tasks/new")
	    public String create(@ModelAttribute("new")Task task, Model model, HttpSession session) {
    		Long userId = (Long) session.getAttribute("userId");
	    	if (userId ==null) {
	    		return "redirect:/";
	    	}else {
		    	List<User> allUsers = userService.allUsers();
				model.addAttribute("users", allUsers);
				
				User u = userService.findUserById(userId);
				model.addAttribute("currentUser", u);
				
				return "new.jsp";
		    	}
	    }
	    
    	@PostMapping("/tasks/new")
        public String create(@Valid @ModelAttribute("new") Task task, BindingResult result,HttpSession session, Model model) {
    		Long userId = (Long) session.getAttribute("userId");
    		User u = userService.findUserById(userId);
//    		model.addAttribute("user", u);
    		if (result.hasErrors()) {
    			List<User> allUsers = userService.allUsers();
				model.addAttribute("users", allUsers);
    			System.out.println("errors");
    			System.out.println(result.toString());

    					return "new.jsp";
    		}else {
    			System.out.println("no errors");

//    			Task newTask = taskService.newTask(task);
    			task.setCreator(u);
    			taskService.newTask(task);
    			return "redirect:/tasks";
    		}
    	}
//////////////////////////////////////////////////////////////////////////////////////
    	
	    @GetMapping("/tasks/{id}")	   
	    public String task(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
			Task task = taskService.findTask(id);
			if(task != null) {
				model.addAttribute("task", task);
				return "task.jsp";
			}else {
				redirectAttributes.addFlashAttribute("error", "that id does not exist");
				return "redirect:/tasks";
			}
		}
	    
	    
//////////////////////////////////////////////////////////////////////////////////////
	    
	    @GetMapping("/tasks/{id}/edit")	   
	    public String editTask(@ModelAttribute("editTask") Task myTask, @PathVariable Long id, Model model,HttpSession session, RedirectAttributes redirectAttributes) {
	    	Long userId = (Long) session.getAttribute("userId");
	    	Task task = taskService.findTask(id);
			if(task != null) {		
				List<User> allUsers = userService.allUsers();
				model.addAttribute("users", allUsers);
				
				User u = userService.findUserById(userId);
				model.addAttribute("currentUser", u);
				
				model.addAttribute("editTask", task);
				return "edit.jsp";
			}else {
				redirectAttributes.addFlashAttribute("error", "that id does not exist");
				return "redirect:/tasks";
			}
		}
	    
	    
	    @RequestMapping(value="/tasks/{id}/edit", method=RequestMethod.POST)
	    public String edit(@Valid @ModelAttribute("editTask") Task myTask, BindingResult result, @PathVariable Long id) {
	    	if (result.hasErrors()) {
	    		return "edit.jsp";
	    	}else {
	    		taskService.updateTask(myTask);
	    		return "redirect:/tasks";
	    	}
	    }
//////////////////////////////////////////////////////////////////////////////////////
    
    
    
    
    
    
    @RequestMapping(value="/tasks/delete/{id}", method=RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id) {
		taskService.deleteTask(id);
		return "redirect:/tasks";
	}
    
    
    
}
