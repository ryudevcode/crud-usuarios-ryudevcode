package crud_usuarios_ryudevcode.repository;


//importamos la entidad usurios
import crud_usuarios_ryudevcode.entity.Usuario;

//importamos el JpaRepoitory
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

//indicamos la interfaz administrara la entidad usurios
public interface UsuarioRepository  extends  JpaRepository<Usuario, Long>{

     // Existe algun correo
    boolean existsByCorreo(String correo);

    //Busca Usuarios cuyo nombre contenga el texto indicado
    //IgnoreCase permite la busqueda no distinga mayusculas y minusculas
    Page<Usuario> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);


}
