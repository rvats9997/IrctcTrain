
public class Dog {
	
	
//	object state
	
	String name="Rocky";
	String breed="Lebra";
	int height=3;
	
	
//	object behaviour
	
	
	public void barking() {
		System.out.print(name + " "+ "is barking");
	}
	
	public void eating() {
		System.out.print(name + " "+ "is eating");
	}

	public static void main(String[] args) {
		
		Dog obj1= new Dog();
		
		System.out.println(obj1.breed);
		obj1.barking();
	}

}
