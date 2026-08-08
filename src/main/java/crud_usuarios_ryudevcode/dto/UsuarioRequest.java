package crud_usuarios_ryudevcode.dto;


import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;
@Schema(
        description = "Datos necesarios para crear o actualizar un usuario"
)
//clase utilizada para recibir informacion del cliente
public class UsuarioRequest {

    //Nombre del usuario
    //No permite valores null, vacios o solamente espacios
    @Schema(
            description = "Nombre completo del usuario",
            example = "Carlos Eduardo",
            minLength = 2,
            maxLength = 100
    )
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max=100, message="El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    //Correo electronico
    @Schema(
            description = "Correo electrónico del usuario",
            example = "carlos@gmail.com"
    )
    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo debe tener un formato válido")
    private String correo;

    //Edad
    @Schema(
            description = "Edad del usuario",
            example = "25",
            minimum = "18"
    )
    @NotNull(message = "La edad es obligatoria")
    @Min(
            value = 18,
            message = "La edad debe ser mayor o igual a 18"
    )
    private Integer edad;

    //constructor vacio
    public UsuarioRequest(){

    }

    //construcutor con parametros
    public UsuarioRequest(String nombre, String correo, Integer edad){
        this.nombre= nombre;
        this.correo = correo;
        this.edad = edad;
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
