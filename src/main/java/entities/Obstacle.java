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
	
	@Column(name = "traversable")
	private int traversable;
	
	@Column(name = "effet")
	private int effet;
	
	@Column(name = "nom")
	private String nom;
	
	@Column(name = "apparence", columnDefinition="BLOB")
	private byte[] apparence;	
	
	@Column(name = "nbMaxParNiveau")
	private int nbMaxParNiveau;
	
	@Column(name = "nbMinParNiveau")
	private int nbMinParNiveau;
	
	private Obstacles type;
	
	public Obstacle() {
		// TODO Auto-generated constructor stub
	}

	public Obstacle(int traversable, int effet, String nom, byte[] apparence, int nbMaxParNiveau,
			int nbMinParNiveau) {
		super();
		this.traversable = traversable;
		this.effet = effet;
		this.nom = nom;
		this.apparence = apparence;
		this.nbMaxParNiveau = nbMaxParNiveau;
		this.nbMinParNiveau = nbMinParNiveau;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTraversable() {
		return traversable;
	}

	public void setTraversable(int traversable) {
		this.traversable = traversable;
	}

	public int getEffet() {
		return effet;
	}

	public void setEffet(int effet) {
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