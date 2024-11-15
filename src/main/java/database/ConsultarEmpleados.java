package database;

import entities.EmpleadosEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class ConsultarEmpleados {
    public static void leerEmps(){
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

    public static void crearEmpleado(){
        EntityManagerFactory emf = EmfSingleton.getInstance().getEmf();
        //Aquí comienza nuestro contexto de persistencia asociado a nuestro em
        EntityManager em3 = emf.createEntityManager();
        try{
            EntityTransaction transaction = em3.getTransaction();
            //Comenzamos a crear el contexto de persistencia
            transaction.begin();
            EmpleadosEntity empNuevo = new EmpleadosEntity();
            empNuevo.setApellido("Nadal");
            empNuevo.setOficio("Estudiante");
            empNuevo.setDeptNo((byte)40);
            em3.persist(empNuevo);
            transaction.commit();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }finally{
            //Aseguramos que la conexión se cierra y el contexto de persistencia termina
            em3.close();
        }
    }
}
