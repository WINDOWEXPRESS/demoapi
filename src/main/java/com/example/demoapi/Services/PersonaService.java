package com.example.demoapi.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demoapi.Repository.PersonaRepository;
import com.example.demoapi.persona.persona;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository ;

    public persona crearPersona(persona person){//puede ser void
        return personaRepository.save(person);
    }

    public boolean borrarPersona(Integer id){
        if(personaRepository.findById(id).isPresent()){
            personaRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
            
    }

    public Optional<persona> obtenePersona (Integer id){
        return personaRepository.findById(id);
    }

    public Optional<persona> actualizarPersona (Integer id,persona persona){
        Optional<persona> pers = personaRepository.findById(id);
        if(pers.isPresent()){
            pers.get().setApellidos(persona.getApellidos());
            pers.get().setNombre(persona.getNombre());
            pers.get().setEmail(persona.getEmail());
            personaRepository.save(pers.get());
        }
        return pers;
    }
}
