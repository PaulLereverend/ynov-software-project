package entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import model.Plateau;

public class Niveau {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "nom")
	private String nom;
	
	@Column(name = "createur")
	private String createur;	
	
	@Column(name = "dateDeCreation")
	private Date dateDeCreation;
	
	@Column(name = "dateDeModification")
	private Date dateDeModification;
	
	@Column(name = "composition")
	private Plateau composition;
	
	@OneToMany(mappedBy = "niveau", cascade = CascadeType.ALL)
    private Set<Historique> historique;

	public Niveau(int id, String nom, String createur, Date dateDeCreation, Date dateDeModification,
			Plateau composition) {
		super();
		this.id = id;
		this.nom = nom;
		this.createur = createur;
		this.dateDeCreation = dateDeCreation;
		this.dateDeModification = dateDeModification;
		this.composition = composition;
	}
	
	public Niveau() {
		// TODO Auto-generated constructor stub
	}


	public Set<Historique> getHistorique() {
		return historique;
	}

	public void setHistorique(Set<Historique> historique) {
		this.historique = historique;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCreateur() {
		return createur;
	}

	public void setCreateur(String createur) {
		this.createur = createur;
	}

	public Date getDateDeCreation() {
		return dateDeCreation;
	}

	public void setDateDeCreation(Date dateDeCreation) {
		this.dateDeCreation = dateDeCreation;
	}

	public Date getDateDeModification() {
		return dateDeModification;
	}

	public void setDateDeModification(Date dateDeModification) {
		this.dateDeModification = dateDeModification;
	}

	public Plateau getComposition() {
		return composition;
	}

	public void setComposition(Plateau composition) {
		this.composition = composition;
	}
	
}
