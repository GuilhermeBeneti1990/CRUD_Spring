package com.beneti.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.beneti.api.models.Message;
import com.beneti.api.models.Person;
import com.beneti.api.repositories.PersonRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PersonService {

    @Autowired
    private Message message;

    @Autowired
    private PersonRepository repository;

    public ResponseEntity<?> createPerson(Person person) {

        if(person.getName().equals("")) {
            message.setMessage("Name must be fill!");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else if(person.getAge() < 0) {
            message.setMessage("Age must be valid!");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else {
            log.info("Create person: ", person);
            repository.save(person);
            return new ResponseEntity<>(person, HttpStatus.CREATED);
        }

    }

    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> findById(int id) {
        if(repository.countById(id) == 0) {
            message.setMessage("Person not found!");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            log.info("Find person by id:", id);
            return new ResponseEntity<>(repository.findById(id), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> update(Person person) {
        if(repository.countById(person.getId()) == 0) {
            message.setMessage("Person not found!");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else if(person.getName().equals("")) {
            message.setMessage("Name must be fill!");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else if(person.getAge() < 0) {
            message.setMessage("Age must be valid!");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        } else {
            log.info("Update person: ", person);
            return new ResponseEntity<>(repository.save(person), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> remove(int id) {
        if(repository.countById(id) == 0) {
            message.setMessage("Person not found!");
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        } else {
            Person personToRemove = repository.findById(id);
            repository.delete(personToRemove);
            message.setMessage("Person removed successfully!");
            log.info("Removing person by id: ", id);
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
    }
    
    public long counter() {
        return repository.count();
    }

    public List<Person> orderByName() {
        return repository.findByOrderByNameAsc();
    }

    public List<Person> findByNameOrderByAge(String name) {
        return repository.findByNameOrderByAgeAsc(name);
    }

    public List<Person> findByNameContain(String text) {
        return repository.findByNameContaining(text);
    }

    public List<Person> findByNameStartsWith(String text) {
        return repository.findByNameStartsWith(text);
    }

    public List<Person> findByNameEndsWith(String text) {
        return repository.findByNameEndsWith(text);
    }

    // public int sumAges() {
    //     return repository.sumAges();
    // }

    // public List<Person> ageHigherOrEqual(int age) {
    //     return repository.ageHigherOrEqual(age);
    // }

}
