import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
	
	public void saveToDisk(String path) {
		XMLEncoder encoder = null;
		try {
			encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(path)));
		} catch (FileNotFoundException e) {
			return;
		}
		encoder.writeObject(this);
		encoder.close();
	}
	
	public static Zoo loadFromFile(String path) {
		XMLDecoder decoder=null;
		try {
			decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(path)));
		} catch (FileNotFoundException e) {
			return null;
		}
		Zoo zoo = (Zoo)decoder.readObject();
		decoder.close();
		if (zoo == null) {
			zoo = new Zoo();
		}
		return zoo;
		
	}
}
