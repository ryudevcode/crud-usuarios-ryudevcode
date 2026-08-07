package crud_usuarios_ryudevcode.service.impl;

import crud_usuarios_ryudevcode.entity.Usuario;
import crud_usuarios_ryudevcode.repository.UsuarioRepository;
import crud_usuarios_ryudevcode.service.UsuarioService;
import org.springframework.stereotype.Service;

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

    //guardar todo lo usurios
    @Override
    public Usuario guardarUsuario(Usuario usuario){
        return UsuarioRepository.save(usuario);
    }

    //obtener toso lo usuarios
    @Override
    public List<Usuario> obtenerUsuarios(){
        return  UsuarioRepository.findAll();
    }

    //Busca un usurio por su id
    @Override
    public Optional<Usuario> obtenerUsuarioPorId(Long id){
        return UsuarioRepository.findById(id);
    }

    //actulizar un usuario existente
    @Override
    public Usuario actulizarUsuario(Long id,Usuario usuario){
        //buscamos el usurios por su id
        Optional<Usuario> usuarioExistente= UsuarioRepository.findById(id);

        //si existe actulizamos sus datos
        if(usuarioExistente.isPresent()){
            Usuario usuarioActilizar = usuarioExistente.get();
            usuarioActilizar.setNombre(usuario.getNombre());
            usuarioActilizar.setCorreo(usuario.getCorreo());
            usuarioActilizar.setEdad(usuario.getEdad());

            return UsuarioRepository.save(usuarioActilizar);
        }

        //si no existe lanzamos una excepcion
        throw  new RuntimeException("Usuario no encontrado con id: "+id);
    }


     //elimina un usurio
    @Override
    public void eliminarUsuario(Long id){
        UsuarioRepository.deleteById(id);
    }



}
