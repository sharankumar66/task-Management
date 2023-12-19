package com.project.taskManagement.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.taskManagement.dto.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

	public List<Task> findTaskByUserId(Integer userId);
	

}
