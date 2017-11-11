package com.karel.video.rental.repository;

import com.karel.video.rental.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {

    User findByName(String name);
    User findById(UUID id);
}
