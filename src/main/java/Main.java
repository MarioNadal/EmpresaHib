import database.EmfSingleton;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import static database.ConsultarEmpleados.*;

public class Main {
    //Contexto de persistencia haciendo uso del singleton
    static EntityManagerFactory emf = EmfSingleton.getInstance().getEmf();
    public static EntityManager em = emf.createEntityManager();

    public static void main(String[] args){
        leerEmps(em);
        //Hola q ase

        //modificarempleados(em);
        desconectar();
    }
    private static void desconectar(){
        em.close();
        emf.close();
    }
}
