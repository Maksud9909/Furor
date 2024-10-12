
class Fish implements Runnable {
    private final String gender;
    private final int lifespan; // Продолжительность жизни в секундах
    private boolean isAlive = true;
    private final Aquarium aquarium;

    public Fish(String gender, int lifespan, Aquarium aquarium) {
        this.gender = gender;
        this.lifespan = lifespan;
        this.aquarium = aquarium;
    }

    @Override
    public void run() {
        try {
            // Жизненный цикл рыбы
            for (int i = 0; i < lifespan; i++) {
                if (!isAlive) break;
                System.out.println(gender + " fish is living. " + (lifespan - i) + " seconds left.");
                Thread.sleep(1000); // Симуляция одной секунды жизни
            }
            die();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Умереть
    private void die() {
        isAlive = false;
        System.out.println(gender + " fish died.");
        aquarium.removeFish(this);
    }

    public String getGender() {
        return gender;
    }
}