package model;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.Obstacle;
//Hibernate JPA With H2 Example

public class ORM {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
	static EntityManager entityManager = emf.createEntityManager();
	
  public static void lancer() {
    ORM example = new ORM();
    System.out.println("After Sucessfully insertion ");
	Obstacle obstacle = new Obstacle(0,0,"Mur", "rouge", 1,1);
	
	
    //Obstacle student2 = example.Obstacle("Anoop");
	example.saveObstacle(obstacle);
    example.listObstacles();
    System.out.println("After Sucessfully modification ");
    //example.listStudent();
    System.out.println("After Sucessfully deletion ");
    //example.deleteStudent(student2.getStudentId());
    //example.listStudent();
   

  }

  public static Obstacle saveObstacle(Obstacle obstacle) {
    try {
      entityManager.getTransaction().begin();
      entityManager.persist(obstacle);
      entityManager.getTransaction().commit();
    } catch (Exception e) {
      entityManager.getTransaction().rollback();
    }
    return obstacle;
  }

  public static void listObstacles() {
    try {
      entityManager.getTransaction().begin();
      @SuppressWarnings("unchecked")
      List<Obstacle> obstacles = entityManager.createQuery("from Obstacle").getResultList();
      for (Obstacle obstacle : obstacles) {
          System.out.println(obstacle.getNom()+"cc");
      } 
      entityManager.getTransaction().commit();
    }catch (Exception e) {
    	e.printStackTrace();
      entityManager.getTransaction().rollback();
    }
  }

  /*public void updateStudent(Long studentId, String studentName) {
    try {
      entityManager.getTransaction().begin();
      Student student = (Student) entityManager.find(Student.class, studentId);
      student.setStudentName(studentName);
      entityManager.getTransaction().commit();
    } catch (Exception e) {
      entityManager.getTransaction().rollback();
    }
  }

  public void deleteStudent(Long studentId) {
    try {
      entityManager.getTransaction().begin();
      Student student = (Student) entityManager.find(Student.class, studentId);
      entityManager.remove(student);
      entityManager.getTransaction().commit();
    } catch (Exception e) {
      entityManager.getTransaction().rollback();
    }
  }*/
}