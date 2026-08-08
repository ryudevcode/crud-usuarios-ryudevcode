package crud_usuarios_ryudevcode.controller;

import crud_usuarios_ryudevcode.dto.UsuarioResponse;
import  crud_usuarios_ryudevcode.dto.UsuarioRequest;
import crud_usuarios_ryudevcode.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


//indicamos que esta clase es un controlador rest responde peticiones http y devolvera JSON

@RestController
//definimos la ruta principal para todo los endpoints
@RequestMapping("/usuarios")
public class UsuarioController {

    //servicio que contiene la logica del negocio
    private final UsuarioService usuarioService;

    //Inyeccion de dependencias mediante constuctor
    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    //guarda un nuevo usuario
    @PostMapping
    public ResponseEntity guardarUsuario(
            @Valid @RequestBody UsuarioRequest UsuarioRequest){

        // Llamamos al Service para guardar el usuario
        UsuarioResponse usuarioResponse =
                usuarioService.guardarUsuario(UsuarioRequest);

        // Retornamos HTTP 201 CREATED
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuarioResponse);

    }


    // obtenemos todos los usuarios
    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> obtenerUsuarios(){

        //Obtenermos los usuarios desde el service
        List<UsuarioResponse> usuarios = usuarioService.obtenerUsuarios();
        //Retornamos el HTTP 200 ok
        return  ResponseEntity.ok(usuarios);

    }



    // Busca un usuario por su id
    @GetMapping("/{id}")
    public ResponseEntity obtenerUsuarioPorId(@PathVariable Long id) {

        //El service devuelve usuarioResponse
        UsuarioResponse usuario = usuarioService.obtenerUsuarioPorId(id);

        //Retornamos HTTP 200 ok
        return ResponseEntity.ok(usuario);

    }


    @PutMapping("/{id}")
    public ResponseEntity actualizarUsuario(@PathVariable Long id,
                                             @Valid @RequestBody UsuarioRequest UsuarioRequest){

        //Actulizamos el usurio meidnate el service
        UsuarioResponse usuarioActulizado =  usuarioService.actulizarUsuario(id, UsuarioRequest);

        //Retornamos HTTP 200 ok
        return ResponseEntity.ok(usuarioActulizado);

    }

    //Elimina un usuario por su id
    @DeleteMapping("/{id}")
    public ResponseEntity <Void > eliminarUsuario(@PathVariable long id){

        usuarioService.eliminarUsuario(id);

      return  ResponseEntity.noContent().build();
    }

}
