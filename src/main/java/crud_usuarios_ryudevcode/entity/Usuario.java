package crud_usuarios_ryudevcode.entity;

//Una Entity es una clave de java que representa una tabla de la base de datos.

//importa las anotaciones JPA
import jakarta.persistence.*;

//Indica que la clase representa la tabla
@Entity
//Nombre de la tabla que se creara en h2
@Table(name="usuarios")
public class Usuario {

    //Indica que este atributo es la llave primaria
    @Id
    //Genera automaticamente el id de forma incremental
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    //Define una columna llamada nombre
    // nullable - false significa que no acepta valores nulos
    // length  = 100 inidca el tamano maximo del texto
    @Column(name="nombre", nullable = false, length = 100)
    private String nombre;

    //unique = true evita correos repetidos
    @Column(name ="correo", nullable = false, unique = true,length = 150)
    private String correo;

    // columna edad
    @Column(name = "edad", nullable = false)
    private Integer edad;

    //Constructor vacio
    //JPA lo necesit apara poder crear objetos automaticamente
    public Usuario(){

    }

    //Constructor con parametros
    //Nos servira para crear objetos facilmente
    public Usuario(Long Id, String nombre, String correo, Integer edad){
        this.Id = Id;
        this.nombre = nombre;
        this.correo = correo;
        this.edad = edad;
    }

    // get y set
    // obtener el id
    public Long getId(){
        return  Id;
    }

    //modificar el id
    public void setId(Long Id){
        this.Id=Id;
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

    //Muestra el contenido del objeto
    @Override
    public String toString(){
        return "Usuario{" +
                "id+" +Id+
                ", nombre='"+nombre+'\''+
                ", correo='"+correo+'\''+
                ", edad="+ edad+
                '}';
    }
}
