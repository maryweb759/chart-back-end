package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.dto.CountType;
import com.example.demo.model.Task;

public interface TaskRepository extends JpaRepository<Task,Long> {
	
	@Query(value="SELECT * FROM  task order by due_date desc", nativeQuery = true)
	public List<Task> getAllTaskDueDateDesc();

	
	//@Query("Select new com.example.demo.dto.CountType(COUNT(*), t.type) from Task t") // -> get all task list 
	//@Query("Select new com.example.demo.dto.CountType(COUNT(*), t.type) from Task t GROUP BY type ") // get task count for every type 
	//@Query("Select new com.example.demo.dto.CountType(COUNT(*) /(Select COUNT(*) from Task) * 100, t.type) from Task t GROUP BY type ") 
	
	
	// to get the percentage u need to get the task count by group and then / divide by all the task we have on db and then * on 100
	// we have 10 task , 2 -> done tasks - to get percentage 
	// 2/10*100 = 20
	@Query("Select new com.example.demo.dto.CountType(COUNT(*)/(Select COUNT(*) from Task) * 100, t.type) from Task t GROUP BY type") // -> get all task list 

	List<CountType> getPercentageGroupByType();

}
