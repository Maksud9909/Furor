import java.util.Random;

public class AquariumSimulation {
    public static void main(String[] args) {
        Aquarium aquarium = new Aquarium();
        Random random = new Random();

        int males = random.nextInt(5) + 1;
        int females = random.nextInt(5) + 1;

        for (int i = 0; i < males; i++) {
            int lifespan = random.nextInt(5) + 5;
            aquarium.addFish(new Fish("Male", lifespan, aquarium));
        }

        for (int i = 0; i < females; i++) {
            int lifespan = random.nextInt(5) + 5;
            aquarium.addFish(new Fish("Female", lifespan, aquarium));
        }

        aquarium.startLifeCycle();
    }
}