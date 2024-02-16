package com.example.demoapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demoapi.persona.persona;

@Repository
public interface PersonaRepository extends JpaRepository<persona,Integer>{

}
