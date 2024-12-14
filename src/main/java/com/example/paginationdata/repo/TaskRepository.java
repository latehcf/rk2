package com.example.paginationdata.repo;

import com.example.paginationdata.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // Pagination
    Page<Task> findAll(Pageable pageable);
}