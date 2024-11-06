import java.util.Random;

class Fish implements Runnable {
    private final String gender;
    private final int lifespan;
    private boolean isAlive = true;
    private final Aquarium aquarium;
    private int x;
    private int y;

    public Fish(String gender, int lifespan, Aquarium aquarium, int x, int y) {
        this.gender = gender;
        this.lifespan = lifespan;
        this.aquarium = aquarium;
        this.x = x;
        this.y = y;
    }

    private void move() {
        Random random = new Random();
        x = random.nextInt(Aquarium.SIZE * 2 + 1) - Aquarium.SIZE;
        y = random.nextInt(Aquarium.SIZE * 2 + 1) - Aquarium.SIZE;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < lifespan; i++) {
                if (!isAlive) break;
                System.out.println("==============================");
                System.out.println(gender + " fish at (" + x + ", " + y + ") is living. " + (lifespan - i) + " seconds left.");
                System.out.println("==============================");
                Thread.sleep(1000);
                move();
            }
            die();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void die() {
        isAlive = false;
        System.out.println("------------------------");
        System.out.println(gender + " fish died.");
        System.out.println("------------------------");
        aquarium.removeFish(this);
    }

    public String getGender() {
        return gender;
    }
}