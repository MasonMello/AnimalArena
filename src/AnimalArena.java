import java.util.Random;

public class AnimalArena {
    public static Random rand = new Random();
    public static final int MAXHEALTH = 20;
    public static final int MAXSTRENGTH = 10;
    public static final int MILIDELAY = 500;

    public static void main(String[] args) {
        Animal BiliApe = createAnimalWithDefaultConstructor("BiliApe");
        Animal Sloth = createAnimalWithParameterizedConstructor("Sloth");

        fight(BiliApe, Sloth);
    }

    public static Animal createAnimalWithDefaultConstructor(String type){
        Animal a = new Animal(); //THIS IS USING THE DEFAULT CONSTRUCTOR
        a.setType(type);
        int startingHealth = rand.nextInt((MAXHEALTH / 2) + 1) + 10;
        int startingStrength = rand.nextInt((MAXSTRENGTH) + 1) + 1;
        a.setStrength(startingStrength);
        a.setHealth(startingHealth);
        return a;
    }

    public static Animal createAnimalWithParameterizedConstructor(String type){
        int startingHealth = rand.nextInt((MAXHEALTH / 2) + 1) + 10;
        int startingStrength = rand.nextInt((MAXSTRENGTH) + 1) + 1;
        return new Animal(type, startingStrength, startingHealth);
    }

    public static void fight(Animal a1, Animal a2){
        while (!a1.isDead() && !a2.isDead()){
            animalAttack(a1, a2);
            pause(MILIDELAY);
            if (a2.isDead()){
                System.out.println(a2.getType() + " is dead. " + a1.getType() + " won!");
            }else {
                animalAttack(a2, a1);
                if (a1.isDead()){
                    System.out.println(a1.getType() + " is dead. " + a2.getType() + " won!");
                }
            }
        }
    }

    public static void animalAttack(Animal attacker, Animal defender){
        int attack = rand.nextInt(attacker.getHealth());
        System.out.println(attacker.getType() + " attacks " + defender.getType() + " delivering " + attack + " damage!");
        defender.setHealth(defender.getHealth()-attack);
        System.out.println(defender.getType() + " has " + defender.getHealth() + " health remaining");
    }

    public static void pause(int milliseconds){
        try{
          Thread.sleep(milliseconds);
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }

    }
}
