package com.neword.demo.repositories;

import com.neword.demo.models.Article;
import com.neword.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
