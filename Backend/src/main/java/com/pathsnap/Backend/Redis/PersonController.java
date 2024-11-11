package com.pathsnap.Backend.Redis;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/redis")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    // 새로운 Person 생성
    @PostMapping("/person")
    public ResponseEntity<Person> createPerson(@RequestBody PersonRequestDto requestDto) {
        Person savedPerson = personService.savePerson(requestDto.getName(), requestDto.getAge());
        return new ResponseEntity<>(savedPerson, HttpStatus.CREATED);
    }

    // ID로 Person 조회
    @GetMapping("/person/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable String id) {
        Optional<Person> person = personService.getPersonById(id);
        return person.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ID로 Person 삭제
    @DeleteMapping("/person/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable String id) {
        personService.deletePersonById(id);
        return ResponseEntity.noContent().build();
    }
}
