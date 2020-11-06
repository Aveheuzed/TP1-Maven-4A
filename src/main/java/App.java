
public class App {
	
	private Zoo zoo = new Zoo();

	public static void main(String[] args) throws AnimalDansMauvaisSecteurException, LimiteVisiteurException {
		App app = new App();
		app.zoo.ajouterSecteur(TypeAnimal.CHIEN);
		app.zoo.nouvelAnimal(new Chien("Chien 1"));
		app.zoo.nouvelAnimal(new Chien("Chien 2"));
		app.zoo.nouvelAnimal(new Chien("Chien 3"));
		app.zoo.nouvelAnimal(new Chat("Chat 1"));
		for (int i=0; i<15; i++) {
			app.zoo.nouveauVisiteur();
		}
	}

}
