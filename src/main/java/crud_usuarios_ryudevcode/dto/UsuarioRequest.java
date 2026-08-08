package crud_usuarios_ryudevcode.dto;


//clase utilizada para recibir informacion del cliente
public class UsuarioRequest {

    //Nombre del usuario
    private String nombre;

    //Correo electronico
    private String correo;

    //Edad
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
