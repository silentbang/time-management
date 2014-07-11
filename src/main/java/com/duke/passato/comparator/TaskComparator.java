package com.duke.passato.comparator;

import java.util.Comparator;

import com.duke.passato.model.Task;

public class TaskComparator implements Comparator<Task> {

	@Override
	public int compare(Task task1, Task task2) {
		return task1.getDeadline().compareTo(task2.getDeadline());
	}

}
