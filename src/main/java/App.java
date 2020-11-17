import java.io.IOException;
import java.util.Scanner;

public class App {
		
	private Zoo zoo = new Zoo();
	
	private static TypeAnimal choisirTypeAnimal(Scanner stdin, String header) {
		System.out.println(header);
		for (TypeAnimal type: TypeAnimal.values()) {
			System.out.printf("(%d.) %s\n", type.ordinal(), type.toString());
		}
		return TypeAnimal.values()[stdin.nextInt()];
	}

	public static void main(String[] args) throws AnimalDansMauvaisSecteurException, LimiteVisiteurException, IOException {
		App app = new App();
		Scanner stdin = new Scanner(System.in);
		while (true) {
			System.out.println(app.zoo);
			System.out.println("(0.)Quitter // Ajouter un (1.)secteur;  (2.)animal; (3.)visiteur; : ");
			int choice = stdin.nextInt();
			switch (choice) {
			case 0:
				return;
			case 1:
				app.zoo.ajouterSecteur(choisirTypeAnimal(stdin, "Ajouter un secteur pour quel type d'animal ?"));
				break;
			case 2:
				TypeAnimal choix = choisirTypeAnimal(stdin, "Quel type d'animal ajouter ?");
				System.out.println("Quel nom pour cet animal ? : ");
				String nom = stdin.nextLine();
				Animal animal = null;
				switch (choix) {
				case CHIEN:
					animal = new Chien(nom);
					break;
				case CHAT:
					animal = new Chat(nom);
					break;
				}
				app.zoo.nouvelAnimal(animal);
				break;
			case 3:
				app.zoo.nouveauVisiteur();
				break;
			default:
				break;
			}
		}
	}

}
