package entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Historique {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	@ManyToOne
    @JoinColumn
    private Niveau niveau;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "algorithme")
	private String algorithme;
	
	@Column(name = "resultat")
	private double resultat;

	
	public Historique(Niveau niveau, Date date, String algorithme, double resultat) {
		super();
		this.niveau = niveau;
		this.date = date;
		this.algorithme = algorithme;
		this.resultat = resultat;
	}
	
	public Historique() {
		
	}

	public Niveau getNiveau() {
		return niveau;
	}

	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAlgorithme() {
		return algorithme;
	}

	public void setAlgorithme(String algorithme) {
		this.algorithme = algorithme;
	}

	public double getResultat() {
		return resultat;
	}

	public void setResultat(double resultat) {
		this.resultat = resultat;
	}
	

}
