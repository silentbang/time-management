package com.duke.passato.comparator;

import java.io.Serializable;
import java.util.Comparator;

import com.duke.passato.model.Task;

public class TaskComparator implements Comparator<Task>, Serializable {

	private static final long serialVersionUID = 6720209118076638210L;

	@Override
	public int compare(Task task1, Task task2) {
		// Compare by deadline ASC and duration DESC
		int result = task1.getDeadline().compareTo(task2.getDeadline());
		if (result == 0) {
			task2.getEstimatedDuration().compareTo(task1.getEstimatedDuration());
		}

		return result;
	}

}
