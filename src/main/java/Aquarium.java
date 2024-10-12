import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

class Aquarium {
    private final List<Fish> fishes = new ArrayList<>();
    private final Random random = new Random();
    private final ExecutorService executor = Executors.newCachedThreadPool();
    private volatile boolean running = true;

    public synchronized void addFish(Fish fish) {
        fishes.add(fish);
        System.out.println("This " + fish.getGender() + " fish has been added to the aquarium.");
        executor.execute(fish);
    }

    public synchronized void removeFish(Fish fish) {
        fishes.remove(fish);
        System.out.println("This " + fish.getGender() + " fish has been removed.");
    }

    public synchronized void reproduction() {
        int males = (int) fishes.stream().filter(f -> f.getGender().equals("Male")).count();
        int females = (int) fishes.stream().filter(f -> f.getGender().equals("Female")).count();

        if (males > 0 && females > 0) {
            int newFishCount = random.nextInt(3) + 1;
            for (int i = 0; i < newFishCount; i++) {
                String gender = random.nextBoolean() ? "Male" : "Female";
                int lifespan = random.nextInt(10) + 5;
                addFish(new Fish(gender, lifespan, this));
            }
            System.out.println("Fishes have reproduced! Added " + newFishCount + " new fishes.");
        }
    }

    public void startLifeCycle() {
        new Thread(() -> {
            while (running) {
                try {
                    Thread.sleep(5000);
                    reproduction();
                    if (!hasFishes()) {
                        System.out.println("All fish have died. Stopping reproduction.");
                        running = false;
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }).start();
    }

    public synchronized boolean hasFishes() {
        return !fishes.isEmpty();
    }

    public void stop() {
        running = false;
        executor.shutdownNow();
    }
}
