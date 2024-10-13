import java.util.Random;

public class AquariumRun {
    public static void main(String[] args) {
        Aquarium aquarium = new Aquarium();
        Random random = new Random();

        int numberOfMales = random.nextInt(5) + 1;
        int numberOfFemales = random.nextInt(5) + 1;

        for (int i = 0; i < numberOfMales; i++) {
            int lifespan = random.nextInt(5) + 5;
            aquarium.addFish(new Fish("Male", lifespan, aquarium));
        }

        for (int i = 0; i < numberOfFemales; i++) {
            int lifespan = random.nextInt(5) + 5;
            aquarium.addFish(new Fish("Female", lifespan, aquarium));
        }

        aquarium.startLifeCycle();
    }
}