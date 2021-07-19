package com.codingdojo.beltexam.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.beltexam.models.Task;
import com.codingdojo.beltexam.models.User;
import com.codingdojo.beltexam.repositories.TaskRepo;
import com.codingdojo.beltexam.repositories.UserRepo;


@Service
public class TaskService {
	@Autowired
    private TaskRepo taskRepo;
	@Autowired
    private UserRepo userRepo;
	
	
	//create
    public Task newTask(Task task) {
		return taskRepo.save(task);
	}
    
    
    
    public Task save(Task b) {
    	return taskRepo.save(b);
    }
    
    //get all tasks and return a list
    public List<Task> allTasks(){
		return taskRepo.findAll();
	}
    
    
    public List<User> allUsers(){
		return userRepo.findAll();
	}
    
    //find a task by id
    public Task findTask(Long id) {
		Optional<Task> mytask = taskRepo.findById(id);
		if (mytask.isPresent()) {
			return mytask.get();
		}else {
			return null;
		}
	}
    
    public User findUser(Long id) {
        Optional<User> optionalUser = userRepo.findById(id);
        if(optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            return null;
        }
    }
    
    
    //delete a task 
    public void deleteTask(Long myId) {
		taskRepo.deleteById(myId);
	}
    
    
    //update a task
    public void updateTask(Task myTask) {
    	taskRepo.save(myTask);
	}
    
}

