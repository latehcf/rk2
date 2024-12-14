package com.example.paginationdata.controller;

import com.example.paginationdata.model.Task;
import com.example.paginationdata.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.paginationdata.model.Student;


import com.example.paginationdata.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/tasks")
    public String getTasks(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "3") int size,
            Model model
    ) {
        Page<Task> taskPage = taskService.getTasks(page, size);

        model.addAttribute("tasks", taskPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", taskPage.getTotalPages());
        model.addAttribute("totalItems", taskPage.getTotalElements());

        return "tasks";
    }

    @GetMapping("/students")
    public String getStudents(Model model) {
        List<Student> students = Arrays.asList(
                new Student(1L, "Amir", "latehcf@mail.ru"),
                new Student(2L, "Alikh", "latehcf@mail.ru"),
                new Student(3L, "Mukhammed", "latehcf@mail.ru"),
                new Student(4L, "Assanali", "latehcf@mail.ru"),
                new Student(5L, "Artyom", "latehcf@mail.ru")
        );

        model.addAttribute("students", students);
        return "students";
    }

    @GetMapping("/sendEmail")
    public String sendEmail() {
        List<String> studentsEmails = Arrays.asList(
                "latehcf@mail.ru",
                "latehcf@mail.ru",
                "latehcf@mail.ru",
                "latehcf@mail.ru",
                "latehcf@mail.ru"
        );

        String subject = "Important";
        String message = "<p>hi!</p>";

        try {
            emailService.sendEmail(studentsEmails, subject, message);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "emailSent";
    }

}