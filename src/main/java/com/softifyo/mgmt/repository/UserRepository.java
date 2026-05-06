package com.softifyo.mgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.softifyo.mgmt.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "from User u where u.email=?1")
    User findByEmail(String email);
}
