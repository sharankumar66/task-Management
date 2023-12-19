package com.project.taskManagement.controller;

import java.time.LocalDate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.taskManagement.dto.Status;
import com.project.taskManagement.dto.Task;
import com.project.taskManagement.dto.User;
import com.project.taskManagement.repository.TaskRepository;
import com.project.taskManagement.reqHelper.TaskRequest;
import com.project.taskManagement.resHelper.API_Response;
import com.project.taskManagement.service.Task_Service;
import com.project.taskManagement.service.User_Service;



@RestController
@RequestMapping("/api/v1/admin/")
public class AdminController {

	@Autowired
	Task_Service taskService;
	@Autowired
	TaskRepository repository;

	@Autowired
	User_Service userService;

	@GetMapping("/")
	public ResponseEntity<List<Task>> getAllTasks(@RequestParam(value = "pageNumber", defaultValue = "1",required = false) Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "5",required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "id",required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc",required = false) String sortDir

) {
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		}else {
			sort = Sort.by(sortBy).descending();
		}
		Pageable pageable = PageRequest.of(pageNo, pageSize,sort);
		Page <Task> pTask = repository.findAll(pageable);
		return new ResponseEntity<List<Task>>(pTask.getContent(), HttpStatus.OK);
	}

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

	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return new ResponseEntity<List<User>>(users, HttpStatus.CREATED);
	}

//	@GetMapping("/search")
//	public ResponseEntity<?> searchTasksByTitleAndDueDate(@PageableDefault(size = 10, page = 0) Pageable pageable,
//			@RequestParam(required = false) String title,
//			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate) {
//		try {
//			if (dueDate != null) {
//				Page <Task> pTask = repository.findAll(pageable);
//				return ResponseEntity.ok(pTask.getSort());
//			} else {
//				Page <Task> pTask = repository.findAll(pageable);
//				return ResponseEntity.ok(pTask.getContent(title));
//			}
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error searching tasks");
//		}
//	}

//	@GetMapping("/pagination")
//	public ResponseEntity<?> getAllTasksWithPagination(@PageableDefault(size = 10, page = 0) Pageable pageable) {
//		try {
//			Page<Task> tasks = repository.getAllTasksWithPagination(pageable);
//			return ResponseEntity.ok(tasks);
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching tasks with pagination");
//		}
//
//	}
}
