package com.tcrprojectubs.repository;

import com.tcrprojectubs.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
