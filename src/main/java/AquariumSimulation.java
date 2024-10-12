import java.util.Random;

public class AquariumSimulation {
    public static void main(String[] args) {
        Aquarium aquarium = new Aquarium();
        Random random = new Random();

        // Генерация случайного количества самцов и самок
        int males = random.nextInt(5) + 1;
        int females = random.nextInt(5) + 1;

        // Добавление рыб в аквариум
        for (int i = 0; i < males; i++) {
            int lifespan = random.nextInt(5); // Продолжительность жизни от 5 до 15 секунд
            aquarium.addFish(new Fish("Male", lifespan, aquarium));
        }

        for (int i = 0; i < females; i++) {
            int lifespan = random.nextInt(5); // Продолжительность жизни от 5 до 15 секунд
            aquarium.addFish(new Fish("Female", lifespan, aquarium));
        }

        // Запуск жизненного цикла аквариума
        aquarium.startLifeCycle();

        // Ждем, пока все рыбы не умрут
        while (aquarium.hasFishes()) {
            try {
                Thread.sleep(1000); // Проверяем каждую секунду
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Завершение работы аквариума
        aquarium.stop();
        System.out.println("Aquarium simulation ended.");
    }
}