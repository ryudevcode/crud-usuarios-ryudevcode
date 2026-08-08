package crud_usuarios_ryudevcode.repository;


//importamos la entidad usurios
import crud_usuarios_ryudevcode.entity.Usuario;

//importamos el JpaRepoitory
import org.springframework.data.jpa.repository.JpaRepository;


//indicamos la interfaz administrara la entidad usurios
public interface UsuarioRepository  extends  JpaRepository<Usuario, Long>{

     // Existe algun correo
    boolean existsByCorreo(String correo);


}
