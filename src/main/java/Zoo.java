import java.util.ArrayList;
import java.util.List;

public class Zoo {
	
	private int visiteurs;
	private List<Secteur> secteursAnimaux;
	public Zoo() {
		this.visiteurs = 0;
		this.secteursAnimaux = new ArrayList<Secteur>();
	}
	
	public void ajouterSecteur(TypeAnimal type) {
		this.secteursAnimaux.add(new Secteur(type));
	}
	public void nouveauVisiteur() throws LimiteVisiteurException {
		if (this.visiteurs >= this.getLimiteVisiteur()) {
			throw new LimiteVisiteurException();
		}
		this.visiteurs++;
	}
	public int getLimiteVisiteur() {
		return 15*this.secteursAnimaux.size();
	}
	public void nouvelAnimal(Animal animal) throws AnimalDansMauvaisSecteurException {
		for (Secteur secteur : this.secteursAnimaux) {
			if (secteur.obtenirType() == animal.getTypeAnimal()) {
				secteur.ajouterAnimal(animal);
				return;
			}
		}
		throw new AnimalDansMauvaisSecteurException();
	}
	public int nombreAnimaux() {
		int total = 0;
		for (Secteur secteur : this.secteursAnimaux) {
			total += secteur.getNombreAnimaux();
		}
		return total;
	}
	
	public int getNbVisiteurs() {
		return this.visiteurs;
	}
}
