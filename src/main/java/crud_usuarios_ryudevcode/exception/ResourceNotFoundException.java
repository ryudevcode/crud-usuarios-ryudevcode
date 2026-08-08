package crud_usuarios_ryudevcode.exception;

//Excepcion utilziada cuando un recurso noe xiste
public class ResourceNotFoundException extends RuntimeException{

    //constructor que recibe el mensaje del error
    public ResourceNotFoundException(String mensaje){
        super(mensaje);
    }

}
