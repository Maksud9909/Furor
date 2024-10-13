import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class AquariumTest {

    private Aquarium aquarium;

    @BeforeEach
    void setUp() {
        aquarium = new Aquarium();
    }

    @Test
    void testAddFish() {
        Fish fish = new Fish("Male", 5, aquarium);
        aquarium.addFish(fish);
        assertTrue(aquarium.hasFishes(), "Fish should be added to the aquarium.");
    }

    @Test
    void testRemoveFish() {
        Fish fish = new Fish("Male", 5, aquarium);
        aquarium.addFish(fish);
        aquarium.removeFish(fish);
        assertFalse(aquarium.hasFishes(), "Fish should be removed from the aquarium.");
    }

    @Test
    void testFishLifecycle() throws InterruptedException {
        Fish fish = new Fish("Male", 2, aquarium);
        aquarium.addFish(fish);

        TimeUnit.SECONDS.sleep(3);
        assertFalse(aquarium.hasFishes(), "Fish should be dead and removed after its lifespan.");
    }

    @Test
    void testReproduction() throws InterruptedException {
        Fish maleFish = new Fish("Male", 5, aquarium);
        Fish femaleFish = new Fish("Female", 5, aquarium);
        aquarium.addFish(maleFish);
        aquarium.addFish(femaleFish);

        aquarium.startLifeCycle();

        TimeUnit.SECONDS.sleep(6);

        assertTrue(aquarium.hasFishes(), "New fish should be added through reproduction.");
    }

    @Test
    void testAquariumStopsAfterAllFishDie() throws InterruptedException {
        Fish fish = new Fish("Male", 1, aquarium);
        aquarium.addFish(fish);

        aquarium.startLifeCycle();

        TimeUnit.SECONDS.sleep(2);

        assertFalse(aquarium.hasFishes(), "Aquarium should have no fish after all have died.");
    }
}
