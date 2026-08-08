package crud_usuarios_ryudevcode.dto;

import io.swagger.v3.oas.annotations.media.Schema;
//clase utilizada para responder informacion al cliente
@Schema(
        description = "Información del usuario retornada por la API"
)
public class UsuarioResponse {

    //Indentificador del usuario
    @Schema(
            description = "Identificador único del usuario",
            example = "1"
    )
    private Long id;
    //Nombre del usuario
    @Schema(
            description = "Nombre completo del usuario",
            example = "Carlos Eduardo"
    )
    private String nombre;
    //Correo electronico
    @Schema(
            description = "Correo electrónico del usuario",
            example = "carlos@gmail.com"
    )
    private String correo;
    //edad
    @Schema(
            description = "Edad del usuario",
            example = "25"
    )
    private Integer edad;

    //constructor vacio

    public UsuarioResponse (){
    }
    public UsuarioResponse (Long id, String nombre, String correo, Integer edad){
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.edad = edad;
    }

    //get y set

    public  Long getId(){
        return  id;
    }

    //modificar
    public void setId(Long id){
        this.id = id;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }
}
