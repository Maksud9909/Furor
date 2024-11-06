import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class AquariumTest {

    private Aquarium aquarium;
    private Random random;
    private int x;
    private int y;

    @BeforeEach
    void setUp() {
        aquarium = new Aquarium();
        random = new Random();
        x = random.nextInt(20);
        y = random.nextInt(20);
    }

    @Test
    void testAddFish() {
        Fish fish = new Fish("Male", 5, aquarium, x, y);
        aquarium.addFish(fish);
        assertTrue(aquarium.hasFishes(), "Fish should be added to the aquarium.");
    }

    @Test
    void testRemoveFish() {
        int x = random.nextInt(20);
        int y = random.nextInt(20);
        Fish fish = new Fish("Male", 5, aquarium, x, y);
        aquarium.addFish(fish);
        aquarium.removeFish(fish);
        assertFalse(aquarium.hasFishes(), "Fish should be removed from the aquarium.");
    }

    @Test
    void testFishLifecycle() throws InterruptedException {
        int x = random.nextInt(20);
        int y = random.nextInt(20);
        Fish fish = new Fish("Male", 2, aquarium, x, y);
        aquarium.addFish(fish);

        TimeUnit.SECONDS.sleep(3);
        assertFalse(aquarium.hasFishes(), "Fish should be dead and removed after its lifespan.");
    }

    @Test
    void testReproduction() throws InterruptedException {
        Fish maleFish = new Fish("Male", 5, aquarium, x, y);
        Fish femaleFish = new Fish("Female", 5, aquarium, x, y);
        aquarium.addFish(maleFish);
        aquarium.addFish(femaleFish);

        aquarium.startLifeCycle();

        TimeUnit.SECONDS.sleep(6);

        assertTrue(aquarium.hasFishes(), "New fish should be added through reproduction.");
    }

    @Test
    void testAquariumStopsAfterAllFishDie() throws InterruptedException {
        Fish fish = new Fish("Male", 1, aquarium, x, y);
        aquarium.addFish(fish);

        aquarium.startLifeCycle();

        TimeUnit.SECONDS.sleep(2);

        assertFalse(aquarium.hasFishes(), "Aquarium should have no fish after all have died.");
    }
}
