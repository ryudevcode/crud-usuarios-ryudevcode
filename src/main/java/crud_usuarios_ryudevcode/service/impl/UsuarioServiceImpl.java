package crud_usuarios_ryudevcode.service.impl;

import crud_usuarios_ryudevcode.entity.Usuario;
import crud_usuarios_ryudevcode.repository.UsuarioRepository;
import crud_usuarios_ryudevcode.service.UsuarioService;
import org.springframework.stereotype.Service;

import crud_usuarios_ryudevcode.dto.UsuarioRequest;
import crud_usuarios_ryudevcode.dto.UsuarioResponse;


import crud_usuarios_ryudevcode.exception.ResourceNotFoundException;
import crud_usuarios_ryudevcode.exception.DuplicateResourceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.nio.file.LinkOption;
import java.util.List;
import java.util.Optional;

//Indica que esta clase pertence a la cada de servicio

@Service
public class UsuarioServiceImpl implements  UsuarioService {

    //Repositorio para acceder a la base  de datos
    private final UsuarioRepository UsuarioRepository;

    // inyection de dependencias mediente constructor
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository){
        this.UsuarioRepository = usuarioRepository;
    }

    //crear un nuevo usuario
    @Override
    public UsuarioResponse guardarUsuario(UsuarioRequest usuarioRequest){
        //verificamos si el correo ya esta registrado
        // Verificamos si el correo ya está registrado
        if (UsuarioRepository.existsByCorreo(usuarioRequest.getCorreo())) {

            throw new DuplicateResourceException(
                    "El correo ya está registrado: "
                            + usuarioRequest.getCorreo()
            );
        }


        // convertimos el DTO recibido en un entity
        Usuario usuario = new Usuario();

        usuario.setNombre(usuarioRequest.getNombre());
        usuario.setEdad(usuarioRequest.getEdad());
        usuario.setCorreo(usuarioRequest.getCorreo());

        // guardamos la entity en la abse  de datos
        Usuario usuarioGuardado = UsuarioRepository.save(usuario);

        // convertimos la entity guarda en un DTO de repuesta
        return  convertirAResponse(usuarioGuardado);
    }

    //buscar usuario por id
    @Override
    public List<UsuarioResponse> obtenerUsuarios(){

        //Obtener todas las entidades de la base  de datos
        List<Usuario> usuarios = UsuarioRepository.findAll();

        // convertirmo cada entity en un UsuarioReponse
        return  usuarios.stream().map(this::convertirAResponse).toList();

    }

    // Buscamos usuario por id
    @Override
    public UsuarioResponse obtenerUsuarioPorId(Long id){

        //Buscamos el usurio en la base de datos
        Usuario usuario = UsuarioRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Usuario no encontrado con id: "+id));
        //convertirmo la entity en DTO
        return convertirAResponse(usuario);
    }

    // Actulizar Usuario Existente
    @Override
    public UsuarioResponse actulizarUsuario (Long id, UsuarioRequest usuarioRequest){
        //Buscamos el usurio que queremos actulizar

        Usuario usuario =UsuarioRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Usuario no encotrado "+id));

        //Actualziamo el usurio
        usuario.setEdad(usuarioRequest.getEdad());
        usuario.setNombre(usuarioRequest.getNombre());
        usuario.setCorreo(usuarioRequest.getCorreo());

        //Guardamos los cambios
        Usuario usuarioActulizado = UsuarioRepository.save(usuario);

        // convertirmo la entiry actulizada en DTO

        return  convertirAResponse(usuarioActulizado);
    }


    // Eliminar el usuario
    @Override
    public void eliminarUsuario(Long id){

        //verificamos que el usurio exista
        if(!UsuarioRepository.existsById(id)){
            throw new ResourceNotFoundException("Usuario no encontrado id "+id);
        }
        UsuarioRepository.deleteById(id);

    }

    @Override
    public Page<UsuarioResponse> obtenerUsuariosPaginados (Pageable pageable){

        //Obtenemos una pagina de usuarios desde la abse  de datos
        Page<Usuario> pagina  = UsuarioRepository.findAll(pageable);

        // convertimos a string
        return pagina.map(this::convertirAResponse);
    }

    @Override
    public Page<UsuarioResponse> buscarPorNombre(String nombre, Pageable pageable){

        //Buscamos los usuarios mediante el repository
        Page<Usuario> pagina = UsuarioRepository.findByNombreContainingIgnoreCase(nombre, pageable);

        //Convertimos cada entity en usurioreponse
        return pagina.map(this::convertirAResponse);

    }

    ///  convertir una entity usuairo en usuarioResponse
    private UsuarioResponse convertirAResponse(Usuario usuario){
        return  new UsuarioResponse(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreo(),
                usuario.getEdad()
        );
    }


    @Override
    public Page<UsuarioResponse> buscarPorCorreo(
            String correo, Pageable pageable
    ){
        //Buscamos usurio por correo
        Page<Usuario> pagina = UsuarioRepository.findByCorreoContainingIgnoreCase(correo,pageable);

        //convertirmos a entity DTO
        return pagina.map(this::convertirAResponse);
    }


}
