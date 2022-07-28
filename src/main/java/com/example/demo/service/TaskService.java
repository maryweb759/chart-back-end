package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CountType;
import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;

@Service
public class TaskService {
	
    @Autowired 
	private TaskRepository taskRepository; 
    
    public List<Task> getTasks(){
		return taskRepository.getAllTaskDueDateDesc();
	}

	public Task save(Task task) {
		return taskRepository.save(task);
	} 
	
	 public boolean existById(Long id) {
			return taskRepository.existsById(id);
		}

	 public Optional<Task> getTaskById(Long id) {
			return taskRepository.findById(id);
		}
    
	 public void delete(Long id) {
			taskRepository.deleteById(id); 
		}

	public List<CountType> getPercentageGroupByType() {
		return taskRepository.getPercentageGroupByType();
	}
}
