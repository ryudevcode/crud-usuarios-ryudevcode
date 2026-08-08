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

//documetnacion de controller
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


//indicamos que esta clase es un controlador rest responde peticiones http y devolvera JSON

@RestController
@Tag(
        name = "Usuarios",
        description = "Operaciones CRUD para la gestión de usuarios"
)
//definimos la ruta principal para todo los endpoints
@RequestMapping("/usuarios")
public class UsuarioController {

    //servicio que contiene la logica del negocio
    private final UsuarioService usuarioService;

    //Inyeccion de dependencias mediante constuctor
    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @Operation(
            summary = "Crear usuario",
            description = "Crea un nuevo usuario en la base de datos."
    )
    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "201",
                    description = "Usuario creado correctamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioResponse.class),
                            examples = @ExampleObject(
                                    value = """
                                {
                                    "id": 1,
                                    "nombre": "Carlos",
                                    "correo": "carlos@gmail.com",
                                    "edad": 25
                                }
                                """
                            )
                    )
            ),

            @ApiResponse(
                    responseCode = "400",
                    description = "Los datos enviados no son válidos"
            ),

            @ApiResponse(
                    responseCode = "409",
                    description = "El correo ya está registrado"
            )
    })
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

    @Operation(
            summary = "Obtener todos los usuarios",
            description = "Obtiene la lista completa de usuarios registrados."
    )
    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "200",
                    description = "Usuarios obtenidos correctamente"
            )
    })
    // obtenemos todos los usuarios
    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> obtenerUsuarios(){

        //Obtenermos los usuarios desde el service
        List<UsuarioResponse> usuarios = usuarioService.obtenerUsuarios();
        //Retornamos el HTTP 200 ok
        return  ResponseEntity.ok(usuarios);

    }


    @Operation(
            summary = "Obtener usuario por ID",
            description = "Busca un usuario específico utilizando su identificador."
    )
    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "200",
                    description = "Usuario encontrado"
            ),

            @ApiResponse(
                    responseCode = "404",
                    description = "El usuario no existe"
            )
    })
    // Busca un usuario por su id
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> obtenerUsuarioPorId(

            // ID que se utilizará para buscar el usuario
            @Parameter(
                    description = "ID del usuario que se desea consultar",
                    example = "1"
            )
            @PathVariable Long id) {

        //El service devuelve usuarioResponse
        UsuarioResponse usuario = usuarioService.obtenerUsuarioPorId(id);

        //Retornamos HTTP 200 ok
        return ResponseEntity.ok(usuario);

    }

    @Operation(
            summary = "Actualizar usuario",
            description = "Actualiza los datos de un usuario existente."
    )
    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "200",
                    description = "Usuario actualizado correctamente"
            ),

            @ApiResponse(
                    responseCode = "400",
                    description = "Los datos enviados no son válidos"
            ),

            @ApiResponse(
                    responseCode = "404",
                    description = "El usuario no existe"
            ),

            @ApiResponse(
                    responseCode = "409",
                    description = "El correo ya está registrado"
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> actualizarUsuario(

            // ID del usuario
            @Parameter(
                    description = "ID del usuario que se desea actualizar",
                    example = "1"
            )
            @PathVariable Long id,

            // Datos nuevos del usuario
            @Valid @RequestBody UsuarioRequest UsuarioRequest) {
        //Actulizamos el usurio meidnate el service
        UsuarioResponse usuarioActulizado =  usuarioService.actulizarUsuario(id, UsuarioRequest);

        //Retornamos HTTP 200 ok
        return ResponseEntity.ok(usuarioActulizado);

    }

    //Elimina un usuario por su id
    // Elimina un usuario
    @Operation(
            summary = "Eliminar usuario",
            description = "Elimina un usuario existente utilizando su ID."
    )
    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "204",
                    description = "Usuario eliminado correctamente"
            ),

            @ApiResponse(
                    responseCode = "404",
                    description = "El usuario no existe"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(

            // ID del usuario que será eliminado
            @Parameter(
                    description = "ID del usuario que se desea eliminar",
                    example = "1"
            )
            @PathVariable Long id) {

        usuarioService.eliminarUsuario(id);

      return  ResponseEntity.noContent().build();
    }

    //optiene usuarios utilizando paginacion
    @Operation( summary =  "Obtener usuarios paginados",
    description = "obteiene una pagina de usuarios utilizando paginacion y ordenamiento")
    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "200",
                    description = "Usuarios obtenidos correctamente"
            )
    })
    @GetMapping("/pagina")
    public ResponseEntity<Page<UsuarioResponse>> obtenerUsuariosPaginados(Pageable pageable){
        //Obtener la pagina desde el service
        Page<UsuarioResponse> pagina = usuarioService.obtenerUsuariosPaginados(pageable);
        return ResponseEntity.ok(pagina);

    }


    //Busca usuario por nombre
    // Busca usuarios por nombre
    @Operation(
            summary = "Buscar usuarios por nombre",
            description = "Busca usuarios cuyo nombre contenga el texto indicado. Permite paginación y ordenamiento."
    )
    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "200",
                    description = "Búsqueda realizada correctamente"
            )
    })
    @GetMapping("/buscar")
    public ResponseEntity<Page<UsuarioResponse>> buscarPorNombre(
            //Texto que se utilizara para buscar
            @Parameter(
                    description = "Texto  que sea desea buscar dentro del nombre",
                    example = "Brandon"
            )
            @RequestParam String nombre, Pageable pageable
    ){

        //Ejecutamos la busqueda mediante el service
        Page<UsuarioResponse> pagina = usuarioService.buscarPorNombre(nombre,pageable);

        //retornamos HTTP 200 ok
        return ResponseEntity.ok(pagina);

    }

}
