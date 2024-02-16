package com.example.demoapi.Controlador;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoapi.Services.PersonaService;
import com.example.demoapi.persona.persona;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController //Publicar los endpoints
@RequestMapping("/persona")
@RequiredArgsConstructor
public class PersonaController {
     @Autowired
    private PersonaService personaService;

    @PostMapping ("/crear")     //http://localhost:8080/persona/crear
    @ApiResponses(
        {
            @ApiResponse(responseCode =  "200" ,description = "Persona creada Correctamente.",content = {
                @Content(schema = @Schema(implementation = persona.class),mediaType = "application/json")
            })

        }
    )

    public persona insertarPersona(@RequestBody persona person){
        return personaService.crearPersona(person);
    }

    @PutMapping("borrarPersona/{id}")
    @ApiResponses(
        {
            @ApiResponse(responseCode =  "200" ,description = "Persona borrada Correctamente."),
            @ApiResponse(responseCode =  "201" ,description = "Persona no borrado porque no existe.")

        }
    )
    public ResponseEntity<String> borrarPersona (@PathVariable String id) {
        
        if (personaService.borrarPersona(Integer.parseInt(id))) {
            return ResponseEntity.ok("Se ha borrado la persona correctamente.");
        } else {
            //status devuelve el codigo de estado 
            return ResponseEntity.status(201).body("No se ha borrado la persona correctamente.");
        }
    }

    @GetMapping("obtenerPesona/{id}")
    public ResponseEntity<Optional<persona>> obtenerPersona(@PathVariable String id) {
        return ResponseEntity.ok(personaService.obtenePersona(Integer.parseInt(id)));
    }
    
    @PostMapping("actualizarPersona/{id}")
    public ResponseEntity<Optional<persona>> actualizarPersona(@PathVariable String id,@RequestBody persona pers) {

        return ResponseEntity.ok(personaService.actualizarPersona(Integer.parseInt(id),pers));
    }
}