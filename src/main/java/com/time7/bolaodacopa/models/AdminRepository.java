package com.time7.bolaodacopa.models;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository<AdminModel, Long> {
    @Query(value="select * from admins where email = :email and password = :password", nativeQuery = true)
    public AdminModel Login(String email, String password);
}
