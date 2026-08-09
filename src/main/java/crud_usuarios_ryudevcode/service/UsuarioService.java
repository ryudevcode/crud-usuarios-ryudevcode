package crud_usuarios_ryudevcode.service;

//importamos la entity

import crud_usuarios_ryudevcode.dto.UsuarioRequest;
import crud_usuarios_ryudevcode.dto.UsuarioResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

// Seguimos el principio de progrmacion orientada a interfaces , si cambia la implementacion el controller no necesita modificarse

//Define las operaciones que podra realizar el servicio
public interface UsuarioService {
    //guarda el nuevo usurios
    UsuarioResponse guardarUsuario(UsuarioRequest UsuarioRequest);

    //obtiene todos lo usurios
    List<UsuarioResponse> obtenerUsuarios();

    //busca un usuario por su id
    UsuarioResponse obtenerUsuarioPorId(Long id);

    // actulzia un  usuarios existente
    UsuarioResponse actulizarUsuario(Long id, UsuarioRequest UsuarioRequest);

    //eliminar un usurios por su id
    void eliminarUsuario(Long id);

   // Obtiene los usuarios utilizando paginacion
    Page<UsuarioResponse> obtenerUsuariosPaginados(Pageable pageable);

    //Busca usuarios por nombre utilizado paginacion
    Page<UsuarioResponse> buscarPorNombre (String nombre, Pageable pageable);

    //Busca usuarios por correo utilizado paginacion
     Page<UsuarioResponse> buscarPorCorreo(
       String correo,
       Pageable pageable
     );


}
