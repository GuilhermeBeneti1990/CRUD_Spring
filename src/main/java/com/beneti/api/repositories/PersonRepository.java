package com.beneti.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.beneti.api.models.Person;;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

    List<Person> findAll();

    Person findById(int id);

    List<Person> findByOrderByNameAsc();

    List<Person> findByNameOrderByAgeAsc(String name);

    List<Person> findByNameContaining(String text);

    List<Person> findByNameStartsWith(String text);

    List<Person> findByNameEndsWith(String text);

    int countById(int id);

    // @Query(value = "SELECT SUM(age) FROM tb_person", nativeQuery = true)
    // int sumAges();

    // @Query(value = "SELECT * FROM tb_person WHERE age >= :age", nativeQuery = true)
    // List<Person> ageHigherOrEqual(int age);
    
}
