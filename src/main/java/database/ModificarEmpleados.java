package database;

import entities.DepartamentosEntity;
import entities.EmpleadosEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class ModificarEmpleados {
    public static void leerEmpleados(){
        EntityManagerFactory emf = EmfSingleton.getInstance().getEmf();
        //Aquí comienza nuestro contexto de persistencia asociado a nuestro em
        EntityManager em = emf.createEntityManager();
        try {
            //Cada consulta añade infromación a nuestro contexto de persistencia
            EmpleadosEntity e = em.find(EmpleadosEntity.class, 7499);
            //System.out.println(e.getApellido()+e.getDepartamentosByDeptNo().getDnombre());
            String jpql = "from EmpleadosEntity where deptNo = 20";
            //Mostrar el nombre del departamento también
            List<EmpleadosEntity> es = em.createQuery(jpql, EmpleadosEntity.class).getResultList();
            EmpleadosEntity e2 = em.createQuery("from EmpleadosEntity where empNo=7369", EmpleadosEntity.class).getSingleResult();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            //Aseguramos que la conexión se cierra y el contexto de persistencia termina
            em.close();
        }
    }

    public static void modificarEmpleado(){
        EntityManagerFactory emf = EmfSingleton.getInstance().getEmf();
        //Aquí comienza nuestro contexto de persistencia asociado a nuestro em
        EntityManager em2 = emf.createEntityManager();
        try{
            EntityTransaction transaction = em2.getTransaction();
            //Comenzamos a crear el contexto de peristencia
            transaction.begin();
            //Se añade la realción del objeto o con su registro de la base de datos al contexto de persistencia
            EmpleadosEntity e = em2.createQuery("from EmpleadosEntity where apellido like 'ARROYO'", EmpleadosEntity.class).getSingleResult();
            //A partir de aquí trbajamos sobre el objeto instanciado que representa un registro de la base de datos
            System.out.println("Salario anterior: " + e.getSalario());
            //Las modificaciones del objeto están asociadas al contexto de persistencia pero no están en la base de datos
            e.setSalario(e.getSalario()+1000);
            System.out.println("Salario actual: " + e.getSalario());
            //Al hacer el commit los cambios se pasan a la base de datos
            transaction.commit();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            //Aseguramos que la conexión se cierra y el contexto de persistencia termina
            em2.close();
        }
    }
}
