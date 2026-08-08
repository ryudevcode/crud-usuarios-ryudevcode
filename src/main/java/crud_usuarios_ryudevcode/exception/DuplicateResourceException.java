package crud_usuarios_ryudevcode.exception;

public class DuplicateResourceException extends  RuntimeException{

    //El contrucutro que recibe el mensaje del error

    public DuplicateResourceException(String mesaje){
        super(mesaje);
    }
}
