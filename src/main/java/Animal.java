
public class Animal {
	
	private String nomAnimal;
	private TypeAnimal typeAnimal;
	
	public Animal(String nom, TypeAnimal type) {
		this.nomAnimal = nom;
		this.typeAnimal = type;
	}
	
	public String getNomAnimal() {
		return this.nomAnimal;
	}
	public TypeAnimal getTypeAnimal() {
		return this.typeAnimal;
	}

}
