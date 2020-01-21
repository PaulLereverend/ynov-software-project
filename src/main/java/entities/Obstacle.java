package entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Obstacle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "effet")
	private Effets effet;
	
	@Column(name = "nom")
	private String nom;
	
	@Column(name = "apparence", columnDefinition="BLOB")
	private byte[] apparence;	
	
	@Column(name = "nbMaxParNiveau")
	private int nbMaxParNiveau;
	
	@Column(name = "nbMinParNiveau")
	private int nbMinParNiveau;
	
	private Obstacles type;

	public Obstacle(int traversable, Effets effet, String nom, byte[] apparence, int nbMaxParNiveau, int nbMinParNiveau, Obstacles type) {
		super();
		this.effet = effet;
		this.nom = nom;
		this.apparence = apparence;
		this.nbMaxParNiveau = nbMaxParNiveau;
		this.nbMinParNiveau = nbMinParNiveau;
		this.type = type;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Effets getEffet() {
		return effet;
	}

	public void setEffet(Effets effet) {
		this.effet = effet;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getNbMaxParNiveau() {
		return nbMaxParNiveau;
	}

	public void setNbMaxParNiveau(int nbMaxParNiveau) {
		this.nbMaxParNiveau = nbMaxParNiveau;
	}

	public int getNbMinParNiveau() {
		return nbMinParNiveau;
	}

	public void setNbMinParNiveau(int nbMinParNiveau) {
		this.nbMinParNiveau = nbMinParNiveau;
	}

	public byte[] getApparence() {
		return apparence;
	}


	public void setApparence(byte[] apparence) {
		this.apparence = apparence;
	}


	public Obstacles getType() {
		return type;
	}


	public void setType(Obstacles type) {
		this.type = type;
	}
}