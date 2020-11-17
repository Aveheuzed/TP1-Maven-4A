import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Zoo {
	
	private static final Logger logger = LogManager.getLogger(Zoo.class);
	
	private int visiteurs;
	private List<Secteur> secteursAnimaux;
	public Zoo() {
		logger.debug("Initialisatio du zoo");
		this.visiteurs = 0;
		this.secteursAnimaux = new ArrayList<Secteur>();
	}
	
	public void ajouterSecteur(TypeAnimal type) {
		logger.debug("Nouveau secteur : " + type.toString());
		this.secteursAnimaux.add(new Secteur(type));
	}
	public void nouveauVisiteur() throws LimiteVisiteurException {
		if (this.visiteurs >= this.getLimiteVisiteur()) {
			logger.error("Trop de visiteurs !");
			throw new LimiteVisiteurException();
		}
		this.visiteurs++;
	}
	public int getLimiteVisiteur() {
		return 15*this.secteursAnimaux.size();
	}
	public void nouvelAnimal(Animal animal) throws AnimalDansMauvaisSecteurException {
		logger.info("Nouvel animal de type " + animal.getTypeAnimal().toString());
		for (Secteur secteur : this.secteursAnimaux) {
			if (secteur.obtenirType() == animal.getTypeAnimal()) {
				secteur.ajouterAnimal(animal);
				return;
			}
		}
		logger.error("Aucun secteur trouvé pour cet animal ("+animal.getTypeAnimal().toString()+")");
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

	public List<Secteur> getSecteursAnimaux() {
		return secteursAnimaux;
	}
}
