import java.util.ArrayList;
import java.util.List;

public class Secteur implements Comparable<Secteur> {
	private TypeAnimal typeAnimauxDansSecteur;
	private List<Animal> animauxDansSecteur;
	
	Secteur(TypeAnimal type) {
		this.typeAnimauxDansSecteur = type;
		this.animauxDansSecteur = new ArrayList<Animal>();
	}
	
	public void ajouterAnimal(Animal animal) throws AnimalDansMauvaisSecteurException {
		if (animal.getTypeAnimal() != this.obtenirType()) {
			throw new AnimalDansMauvaisSecteurException();
		}
		this.animauxDansSecteur.add(animal);
	}
	public int getNombreAnimaux() {
		return this.animauxDansSecteur.size();
	}
	public TypeAnimal obtenirType() {
		return this.typeAnimauxDansSecteur;
	}

	public int compareTo(Secteur arg0) {
		return this.getNombreAnimaux() - arg0.getNombreAnimaux();
	}
}
