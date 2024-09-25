package com.example.tasks.controllers;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tasks.domain.Tasks;
import com.example.tasks.domain.tasksRepository;

import jakarta.validation.Valid;
@RestController

@RequestMapping("/tasks")
public class tasksController {
	@SuppressWarnings("unused")
	@Autowired
	private tasksRepository repository;
	
	
	
	
	@PutMapping("/{id}")
	
    public ResponseEntity<?>  putById (@Valid @RequestBody RequestTasks data ,@PathVariable Integer id) throws SQLException {
        String host= "jdbc:mysql://localhost:3306/dbtask";
        String uname= "root";
        String upass ="Toquinho14";
        
        Connection con = DriverManager.getConnection( host, uname, upass );
        String sql ="UPDATE tasks SET nome = ?,stats = ? WHERE id_task = ?";
        PreparedStatement stmt =    con.prepareStatement(sql);
        stmt.setString(1, data.nome()); // Primeiro parâmetro
        stmt.setString(2, data.stats()); // Primeiro parâmetro
        stmt.setInt(3, id);
        int rowsAffected =0;
		
		
		
//		Tasks task = new Tasks();
		 var tarefasPut = repository.findById(id);
		 if(tarefasPut.isPresent() ) {
		try {
			 rowsAffected = stmt.executeUpdate();
//			task.setNome(data.nome());
//			task.setStats(data.stats());
//			repository.saveAndFlush(task);
		return ResponseEntity.status(HttpStatus.CREATED).body(data);
		}catch(Exception e){
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao processar a requisição: " + e.getMessage());
		}
		 }
		 else {
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("usuário ja não existe");

		 }
		
    }
	
	
	
	
	@DeleteMapping("/{id_task}")
	
    public ResponseEntity<?>  deleteById ( @PathVariable Integer id_task) {
		 var tarefasDelete = repository.findById(id_task);
		 if(tarefasDelete.isPresent() ) {
		try {
			repository.deleteById(id_task);
		return ResponseEntity.status(HttpStatus.CREATED).body(tarefasDelete);
		}catch(Exception e){
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao processar a requisição: " + e.getMessage());
		}
		 }
		 else {
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("usuário ja não existe");

		 }
		
    }
	@GetMapping
    public ResponseEntity<?>  getAllTask( ) {
		
		try {
		List<Tasks> tasks = repository.findAll();
			
		return ResponseEntity.status(HttpStatus.CREATED).body(tasks);
		}catch(Exception e){
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar a requisição: " + e.getMessage());
		}
		
       
    }
	

	
	
	
	
	
	

 

	
	@PostMapping
	
    public ResponseEntity<?>  createTask(@Valid @RequestBody  RequestTasks data) {
		Tasks task = new Tasks();
		
		task.setNome(data.nome());
		task.setStats(data.stats());
		
		if(task.getNome() =="") {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não consigo cadastrar com o nome em branco: ");
		}
		try {
		repository.save(task);
		return ResponseEntity.status(HttpStatus.CREATED).body(task);
		}catch(Exception e){
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar a requisição: " + e.getMessage());
		}
		
       
    }
}
