package com.saber.controller;

import com.saber.dao.StudentMapper;
import com.saber.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private StudentMapper studentMapper;

    @GetMapping("/deleteById")
    public String deleteById(@RequestParam("id") int id) {
        studentMapper.deleteByPrimaryKey(id);
        return "success";
    }

    @GetMapping("/getById")
    public Student getById(@RequestParam("id") int id) {
        Student student = studentMapper.selectByPrimaryKey(id);
        return student;
    }

    @GetMapping("/saveStudent")
    public String saveStudent(@RequestParam("name") String name,
                              @RequestParam("age") int age) {
        Student student = new Student(name, age);
        studentMapper.insert(student);
        return "success";
    }

    @GetMapping("/updateStudent")
    public String updateStudent(@RequestParam("id") int id,
                                @RequestParam("name") String name,
                                @RequestParam("age") int age) {
        Student student = new Student(id, name, age);
        studentMapper.updateByPrimaryKey(student);
        return "success";
    }
}
