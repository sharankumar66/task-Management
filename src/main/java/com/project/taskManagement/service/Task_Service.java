package com.project.taskManagement.service;

import java.util.List;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.project.taskManagement.dto.Task;
import com.project.taskManagement.reqHelper.TaskRequest;

public interface Task_Service {
	public Task createTask(Task task);

//	public List<Task> getTasks();

	public List<Task> getTaskByUser(Integer userId);

	public Task getTaskById(Integer taskId);

	public Task updateTask(TaskRequest req);

//	public Page<Task> getTasksByTitleAndDueDate(Pageable pageable, String title, LocalDate due_date);

//	public Page<Task> getAllTasksWithPagination(Pageable pageable);

}
