import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Класс аквариума
class Aquarium {
    private final List<Fish> fishes = new ArrayList<>();
    private final Random random = new Random();
    private final ExecutorService executor = Executors.newCachedThreadPool();
    private volatile boolean running = true; // Условие для управления жизненным циклом аквариума

    // Метод для добавления рыбы в аквариум
    public synchronized void addFish(Fish fish) {
        fishes.add(fish);
        System.out.println("This " + fish.getGender() + " fish has been added to the aquarium.");
        executor.execute(fish);
    }

    // Метод для удаления рыбы из аквариума
    public synchronized void removeFish(Fish fish) {
        fishes.remove(fish);
        System.out.println("This " + fish.getGender() + " fish has been removed.");
    }

    // Метод для размножения рыб
    public synchronized void reproduction() {
        int males = (int) fishes.stream().filter(f -> f.getGender().equals("Male")).count();
        int females = (int) fishes.stream().filter(f -> f.getGender().equals("Female")).count();

        if (males > 0 && females > 0) {
            int newFishCount = random.nextInt(3) + 1; // Количество новорожденных рыб от 1 до 3
            for (int i = 0; i < newFishCount; i++) {
                String gender = random.nextBoolean() ? "Male" : "Female";
                int lifespan = random.nextInt(10) + 5; // Продолжительность жизни от 5 до 15 секунд
                addFish(new Fish(gender, lifespan, this));
            }
            System.out.println("Fishes have reproduced! Added " + newFishCount + " new fishes.");
        }
    }

    // Метод для запуска жизни аквариума
    public void startLifeCycle() {
        new Thread(() -> {
            while (running) {
                try {
                    Thread.sleep(5000); // Попытка размножения каждые 5 секунд
                    reproduction();
                    // Проверка на наличие рыб для продолжения размножения
                    if (!hasFishes()) {
                        System.out.println("All fish have died. Stopping reproduction.");
                        running = false; // Остановить аквариум, если рыб больше нет
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
        running = false; // Остановить аквариум
        executor.shutdownNow(); // Остановить все потоки
    }
}
