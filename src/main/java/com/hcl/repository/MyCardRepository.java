package com.hcl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.model.MyCard;

@Repository
public interface MyCardRepository extends JpaRepository<MyCard , Long>{

}
