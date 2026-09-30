package com.yourorg.app.service;

import com.yourorg.app.model.Task;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {

    private final Map<Long, Task> tasks = new ConcurrentHashMap<>();
    private final AtomicLong idSequence = new AtomicLong(0);

    public TaskService() {
        // seed with a couple of example tasks so the UI isn't empty on first load
        add(new Task(null, "Set up Backstage pipeline", true));
        add(new Task(null, "Wire up Argo deployment", false));
    }

    public Collection<Task> findAll() {
        return tasks.values();
    }

    public Task add(Task task) {
        long id = idSequence.incrementAndGet();
        task.setId(id);
        tasks.put(id, task);
        return task;
    }

    public void toggleDone(Long id) {
        Task task = tasks.get(id);
        if (task != null) {
            task.setDone(!task.isDone());
        }
    }

    public void delete(Long id) {
        tasks.remove(id);
    }

}
