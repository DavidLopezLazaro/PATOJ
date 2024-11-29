import java.util.ArrayList;
import java.util.List;

    /**
     * La clase RegistroObjetos es el contador que realizamos para ir viendo cuantos objetos va recogiendo el jugador
     */

public class RegistroObjetos {
    private final List<Objeto> objetosRecogidos;

    /**
     * Crea una lista que guarda los objetos recogidos
     */

    public RegistroObjetos() {
        objetosRecogidos = new ArrayList<>();
    }

    /**
     * Registra un objeto en la lista de objetos recogidos
     * @param objeto el objeto que se va a añadir en la lista
     */

    public void registrarObjeto(Objeto objeto) {
        objetosRecogidos.add(objeto);
    }

    /**
     * Obtiene la lista del numero de objetos recogidos
     * @return esta lista
     */

    public List<Objeto> getObjetosRecogidos() {
        return objetosRecogidos;
    }

}
