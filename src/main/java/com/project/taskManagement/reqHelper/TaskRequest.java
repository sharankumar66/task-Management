package com.project.taskManagement.reqHelper;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {
	private Integer id;
	private String title;
	private String description;
	private LocalDate due_date;
	private Integer userId;
	private String status;

}
