package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CountType;
import com.example.demo.model.Task;
import com.example.demo.service.TaskService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class TaskController {

    @Autowired 
    TaskService taskService; 
    
    
    //http://localhost:8080/api/v1/task
    @GetMapping("/task")
	public List<Task> getTask(){
		return taskService.getTasks();
	} 
    //http://localhost:8080/api/v1/task/vData/percentcounttype

    @GetMapping("/task/vData/percentcounttype")
	public List<CountType> getPercentageGroupByType(){
		return taskService.getPercentageGroupByType();
	}
    
    
    //http://localhost:8080/api/v1/task
    @PostMapping("/task")
	public Task addTask(@RequestBody Task task) {
		return taskService.save(task);
	} 
    
    @GetMapping("/task/{id}")
	public Task getById(@PathVariable Long id) {
		return taskService.getTaskById(id).orElseThrow(()->new EntityNotFoundException("Requested Task not found"));
	} 
    
    @PutMapping("/task/{id}")
	public ResponseEntity<?> addTask(@RequestBody Task taskPara,@PathVariable Long id) {
		if(taskService.existById(id)) {
			Task task=taskService.getTaskById(id).orElseThrow(()->new EntityNotFoundException("Requested Task not found"));
			task.setTitle(taskPara.getTitle());
			task.setDueDate(taskPara.getDueDate());
			task.setType(taskPara.getType());
			task.setDescription(taskPara.getDescription());
			taskService.save(task);
			return ResponseEntity.ok().body(task);
		}else {
			HashMap<String, String>message= new HashMap<>();
			message.put("message", id + " task not found or matched");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
		}
	} 
    
    @DeleteMapping("/task/{id}")
	public ResponseEntity<?> deleteTask(@PathVariable Long id) {
		if(taskService.existById(id)) {
			taskService.delete(id);
			HashMap<String, String>message= new HashMap<>();
			message.put("message", id + " task removed");
			return ResponseEntity.status(HttpStatus.OK).body(message);
		}else {
			HashMap<String, String>message= new HashMap<>();
			message.put("message", id + " task not found or matched");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
		}
	}
}
