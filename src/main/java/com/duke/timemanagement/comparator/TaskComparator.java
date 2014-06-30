package com.duke.timemanagement.comparator;

import java.util.Comparator;

import com.duke.timemanagement.model.Task;

public class TaskComparator implements Comparator<Task> {

	@Override
	public int compare(Task task1, Task task2) {
		return task1.getDeadline().compareTo(task2.getDeadline());
	}

}
