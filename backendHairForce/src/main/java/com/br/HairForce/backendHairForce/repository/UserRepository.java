package com.br.HairForce.backendHairForce.repository;

import com.br.HairForce.backendHairForce.entity.User;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
@ComponentScan(basePackages = "com.br.HairForce.backendHairForce")
public interface UserRepository extends JpaRepository<User, Long> {
}
