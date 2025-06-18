package com.knf.dev.librarymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knf.dev.librarymanagementsystem.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
