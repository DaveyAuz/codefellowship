package repositories;

package com.codefellowship.repositories;

import com.codefellowship.models.CodeUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeUserRepository extends JpaRepository<CodeUser, Long> {
    public CodeUser findByUsername(String username);