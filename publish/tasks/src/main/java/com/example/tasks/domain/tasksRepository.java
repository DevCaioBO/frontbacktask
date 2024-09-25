package com.example.tasks.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public  interface tasksRepository extends JpaRepository<Tasks, Integer> {

}
