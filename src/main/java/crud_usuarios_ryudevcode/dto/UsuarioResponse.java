package crud_usuarios_ryudevcode.dto;


//clase utilizada para responder informacion al cliente
public class UsuarioResponse {

    //Indentificador del usuario
    private Long id;
    //Nombre del usuario
    private String nombre;
    //Correo electronico
    private String correo;
    //edad
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
