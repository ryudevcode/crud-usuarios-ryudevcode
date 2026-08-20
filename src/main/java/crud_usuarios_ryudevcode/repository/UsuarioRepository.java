package crud_usuarios_ryudevcode.repository;


//importamos la entidad usurios
import crud_usuarios_ryudevcode.entity.Usuario;

//importamos el JpaRepoitory
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

//query
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

//indicamos la interfaz administrara la entidad usurios
public interface UsuarioRepository  extends  JpaRepository<Usuario, Long>{

     // Existe algun correo
    boolean existsByCorreo(String correo);

    //Busca Usuarios cuyo nombre contenga el texto indicado
    //IgnoreCase permite la busqueda no distinga mayusculas y minusculas
    Page<Usuario> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);


    // Busca usuarios cuyo correo contenga el texto indicado
    Page<Usuario> findByCorreoContainingIgnoreCase(
            String correo,
            Pageable pageable
    );

    // Busca usuarios aplicando filtros opcionales por nombre y correo
    @Query("""
        SELECT u
        FROM Usuario u
        WHERE (:nombre IS NULL OR LOWER(u.nombre) LIKE LOWER(CONCAT('%', :nombre, '%')))
        AND (:correo IS NULL OR LOWER(u.correo) LIKE LOWER(CONCAT('%', :correo, '%')))
        """)
    Page<Usuario> buscarUsuarios(
            @Param("nombre") String nombre,
            @Param("correo") String correo,
            Pageable pageable
    );

}
