package com.springboot.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.rest.entity.Guild;

@Repository
public interface GuildRepository extends JpaRepository<Guild, Integer>{

}
