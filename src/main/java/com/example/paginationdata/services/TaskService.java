package com.example.paginationdata.services;

import com.example.paginationdata.model.Task;
import com.example.paginationdata.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Page<Task> getTasks(int page, int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        return taskRepository.findAll(pageable);
    }
}