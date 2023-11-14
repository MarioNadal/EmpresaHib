import database.EmfSingleton;
import jakarta.persistence.EntityManagerFactory;

import static database.ConsultarEmpleados.*;

public class Main {
    //Contexto de persistencia haciendo uso del singleton

    public static void main(String[] args){
        leerEmps();
        //modificarEmpleado();
        //insertarEmpleado();
        crearEmpleado();
        desconectar();
    }

    private static void desconectar() {
        EntityManagerFactory emf = EmfSingleton.getInstance().getEmf();
        emf.close();
    }
}
