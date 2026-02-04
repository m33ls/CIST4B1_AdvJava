public class Zoo {
	public static void main(String[] args) {
		Animal a1 = new Cow();
		Animal a2 = new Pig();
		Animal[] animals = {a1, a2};
		for(int i = 0; i <= 1; i++) {
			animals[i].noise();
		}

	}
}

class Animal {
	public String name;
	public String sound;
	public int age;

	public Animal() {
		this.name = "";
		this.age = 0;
	}

	public Animal(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public void noise() {
		System.out.println("I am a " + name + ", and I say " + sound);
	}

}

class Panda extends Animal {
	public String kungfuStyle;
	public String band;

	public Panda(String kungfuStyle, String band, String name, int age) {
		super(name, age);
		this.kungfuStyle = kungfuStyle;
		this.band = band;
	}

	@Override
	public void noise() {
		System.out.println("Roar!");
	}
}

class Shark extends Animal {
	public String breed;
	public String swimmingAbility;

	public Shark(String breed, String swimmingAbility, String name, int age) {
		super(name, age);
		this.breed = breed;
		this.swimmingAbility = swimmingAbility;
	}

	@Override
	public void noise() {
		System.out.println("Roar!");
	}
}