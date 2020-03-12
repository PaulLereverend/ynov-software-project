package model;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import entities.Effets;
import entities.Obstacle;
import entities.Obstacles;
//Hibernate JPA With H2 Example

public class ORM {

	//static EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
	//static EntityManager session = emf.createEntityManager();
	
	static Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
	static SessionFactory sf = cfg.buildSessionFactory();
	static Session session = sf.openSession();
	
  public static void lancer() {
    ORM example = new ORM();
    System.out.println("After Sucessfully insertion ");
	Obstacle obstacle = new Obstacle(Effets.PASSANT,"Mur",null, 1,1, Obstacles.BOUE);
	
	
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
      session.getTransaction().begin();
      session.persist(obstacle);
      session.getTransaction().commit();
    } catch (Exception e) {
      session.getTransaction().rollback();
    }
    return obstacle;
  }

  public static void listObstacles() {
    try {
      session.getTransaction().begin();
      List<Obstacle> obstacles = session.createQuery("from Obstacle").list();
      for (Obstacle obstacle : obstacles) {
          System.out.println(obstacle.getNom());
      } 
      session.getTransaction().commit();
    }catch (Exception e) {
    	e.printStackTrace();
      session.getTransaction().rollback();
    }
  }

  /*public void updateStudent(Long studentId, String studentName) {
    try {
      session.getTransaction().begin();
      Student student = (Student) session.find(Student.class, studentId);
      student.setStudentName(studentName);
      session.getTransaction().commit();
    } catch (Exception e) {
      session.getTransaction().rollback();
    }
  }

  public void deleteStudent(Long studentId) {
    try {
      session.getTransaction().begin();
      Student student = (Student) session.find(Student.class, studentId);
      session.remove(student);
      session.getTransaction().commit();
    } catch (Exception e) {
      session.getTransaction().rollback();
    }
  }*/
}