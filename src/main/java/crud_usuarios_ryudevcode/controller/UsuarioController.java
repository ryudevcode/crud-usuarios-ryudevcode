package crud_usuarios_ryudevcode.controller;

import crud_usuarios_ryudevcode.dto.UsuarioResponse;
import  crud_usuarios_ryudevcode.dto.UsuarioRequest;
import crud_usuarios_ryudevcode.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public UsuarioResponse guardarUsuario(@RequestBody UsuarioRequest UsuarioRequest){
        return  usuarioService.guardarUsuario(UsuarioRequest);
    }


    // obtenemos todos los usuarios
    @GetMapping
    public List<UsuarioResponse> obtenerUsuarios(){
        return usuarioService.obtenerUsuarios();
    }

    // Busca un usuario por su id
    @GetMapping("/{id}")
    public UsuarioResponse obtenerUsuarioPorId(@PathVariable Long id) {

        return usuarioService.obtenerUsuarioPorId(id);

    }
    @PutMapping("/{id}")
    public UsuarioResponse actualizarUsuario(@PathVariable Long id,
                                             @RequestBody UsuarioRequest UsuarioRequest){
        return usuarioService.actulizarUsuario(id, UsuarioRequest);
    }

    //Elimina un usuario por su id
    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable long id){
        usuarioService.eliminarUsuario(id);
    }

}
