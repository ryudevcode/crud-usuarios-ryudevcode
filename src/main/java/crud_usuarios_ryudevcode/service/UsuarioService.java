package crud_usuarios_ryudevcode.service;

//importamos la entity
import crud_usuarios_ryudevcode.entity.Usuario;

import java.util.List;
import java.util.Optional;

// Seguimos el principio de progrmacion orientada a interfaces , si cambia la implementacion el controller no necesita modificarse

//Define las operaciones que podra realizar el servicio
public interface UsuarioService {
    //guarda el nuevo usurios
    Usuario guardarUsuario(Usuario usuario);

    //obtiene todos lo usurios
    List<Usuario> obtenerUsuarios();

    //busca un usuario por su id
    Optional<Usuario> obtenerUsuarioPorId(Long id);

    // actulzia un  usuarios existente
    Usuario actulizarUsuario(Long id, Usuario usuario);

    //eliminar un usurios por su id
    void eliminarUsuario(Long id);


}
