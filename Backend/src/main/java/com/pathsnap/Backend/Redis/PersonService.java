package com.pathsnap.Backend.Redis;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRedisRepository personRedisRepository;

    // Person 저장
    public Person savePerson(String name, Integer age) {
        String id = UUID.randomUUID().toString();
        LocalDateTime createdAt = LocalDateTime.now();
        Person person = new Person(id, name, age, createdAt);
        return personRedisRepository.save(person);
    }

    // ID로 Person 조회
    public Optional<Person> getPersonById(String id) {
        return personRedisRepository.findById(id);
    }

    // ID로 Person 삭제
    public void deletePersonById(String id) {
        personRedisRepository.deleteById(id);
    }
}
