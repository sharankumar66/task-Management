package com.project.taskManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.taskManagement.dto.Status;
import com.project.taskManagement.dto.Task;
import com.project.taskManagement.reqHelper.TaskRequest;
import com.project.taskManagement.resHelper.API_Response;
import com.project.taskManagement.service.Task_Service;

@RestController
@RequestMapping("/api/v1/users/task")
public class TaskController {
	@Autowired
	Task_Service taskService;

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Task>> getTaskByUserId(@PathVariable Integer userId) {
		return new ResponseEntity<List<Task>>(taskService.getTaskByUser(userId), HttpStatus.OK);
	}

	@GetMapping("/{taskId}")
	public ResponseEntity<Task> getTaskByTaskId(@PathVariable Integer taskId) {
		return new ResponseEntity<Task>(taskService.getTaskById(taskId), HttpStatus.OK);
	}

	@PutMapping("/")
	public ResponseEntity<API_Response> updateTask(@RequestBody TaskRequest req) {

		taskService.updateTask(req);
		return new ResponseEntity<API_Response>(new API_Response("Task Updated Successfully", true),
				HttpStatus.CREATED);
	}

	@PostMapping("/")
	public ResponseEntity<API_Response> createTask(@RequestBody TaskRequest req) {
		Task task = new Task();
		task.setStatus(Status.PENDING);
		task.setDescription(req.getDescription());
		task.setTitle(req.getTitle());
		task.setDue_date(req.getDue_date());
		task.setUserId(req.getUserId());
		taskService.createTask(task);
		return new ResponseEntity<API_Response>(new API_Response("Task Created Successfully", true), HttpStatus.OK);
	}

}
