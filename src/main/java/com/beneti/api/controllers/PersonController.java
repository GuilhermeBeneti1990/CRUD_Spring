package com.beneti.api.controllers;

import java.util.List;

import javax.validation.Valid;

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

import com.beneti.api.models.Person;
import com.beneti.api.services.PersonService;

@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    private PersonService service;

    @PostMapping("/person")
    public ResponseEntity<?> createPerson(@Valid @RequestBody Person person) {
         return service.createPerson(person);
    }

    @GetMapping("/people")
    public ResponseEntity<?> findAll() {
        return service.findAll();
    }
    
    @GetMapping("/person/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") int id) {
        return service.findById(id);
    }

    @PutMapping("/person")
    public ResponseEntity<?> update(@RequestBody Person person) {
        return service.update(person);
    }

    @DeleteMapping("/person/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") int id) {
        return service.remove(id);
    }

    @GetMapping("/person/count")
    public ResponseEntity<Long> counter() {
        Long counter = service.counter();
        return ResponseEntity.status(HttpStatus.OK).body(counter);
    }

    @GetMapping("/people/orderedByName")
    public ResponseEntity<List<Person>> getPeopleOrderByName() {
        List<Person> list = service.orderByName();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/people/findByNameOrderByAge/{name}")
    public ResponseEntity<List<Person>> getPeopleFindByNameOrderByAge(@PathVariable("name") String name ) {
        List<Person> list = service.findByNameOrderByAge(name);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/people/findByNameContain/{text}")
    public ResponseEntity<List<Person>> findByNameContain(@PathVariable("text") String text) {
        List<Person> list = service.findByNameContain(text);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/people/findByNameStartsWith/{text}")
    public ResponseEntity<List<Person>> findByNameStartsWith(@PathVariable("text") String text) {
        List<Person> list = service.findByNameStartsWith(text);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/people/findByNameEndsWith/{text}")
    public ResponseEntity<List<Person>> findByNameEndsWith(@PathVariable("text") String text) {
        List<Person> list = service.findByNameEndsWith(text);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    // @GetMapping("/people/sumAges")
    // public int sumAges() {
    //     return service.sumAges();
    // }

    // @GetMapping("/people/ageHigherOrEqual/{age}")
    // public List<Person> ageHigherOrEqual(@PathVariable("age") int age) {
    //     return service.ageHigherOrEqual(age);
    // }

}
