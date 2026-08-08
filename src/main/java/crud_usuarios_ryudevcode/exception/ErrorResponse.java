package crud_usuarios_ryudevcode.exception;

//Representa la estructura de una respuesta de error
public class ErrorResponse {


    //codigo http del error
    private int status;

    //Mensaje general del error
    private String mensaje;

    //constructor
    public  ErrorResponse(int status, String mensaje){
        this.status= status;
        this.mensaje = mensaje;

    }

    //get y set

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
