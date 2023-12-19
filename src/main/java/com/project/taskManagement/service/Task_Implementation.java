package com.project.taskManagement.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.taskManagement.dto.Status;
import com.project.taskManagement.dto.Task;
import com.project.taskManagement.exception.SourceNotFoundException;
import com.project.taskManagement.repository.TaskRepository;
import com.project.taskManagement.reqHelper.TaskRequest;

@Service
public class Task_Implementation implements Task_Service {

	@Autowired
	TaskRepository taskRepository;

	@Override
	public Task createTask(Task task) {

		return taskRepository.save(task);
	}

	
//	public Page<Task> getTasks(Pageable pageable) {
//
//		return taskRepository.findAll(pageable);
//	}

	@Override
	public List<Task> getTaskByUser(Integer userId) {

		return taskRepository.findTaskByUserId(userId);
	}

	@Override
	public Task getTaskById(Integer taskId) {
		// TODO Auto-generated method stub
		return taskRepository.findById(taskId).orElseThrow(() -> new SourceNotFoundException("Task", "Id", taskId));
	}

	@Override
	public Task updateTask(TaskRequest req) {
		Task task = getTaskById(req.getId());
		if (req.getStatus() != null && req.getStatus().toLowerCase().equals("inprogress"))
			task.setStatus(Status.INPROGRESS);
		else if (req.getStatus() != null && req.getStatus().toLowerCase().equals("completed"))
			task.setStatus(Status.COMPLETED);

		task.setDescription(req.getDescription());
		task.setTitle(req.getTitle());
		task.setDue_date(req.getDue_date());

		return taskRepository.save(task);
	}

}
