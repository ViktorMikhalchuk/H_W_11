import java.util.concurrent.Semaphore;

public class PetrolStation {
    private double amount;
    private Semaphore refuelSemaphore = new Semaphore(3, true); // Обмеження на 3 запити

    public PetrolStation(double initialAmount) {
        this.amount = initialAmount;
    }

    public void doRefuel(double requestedAmount) throws InterruptedException {
        refuelSemaphore.acquire(); // Захоплюємо ліцензію для запиту

        try {
            // Заправка триває від 3 до 10 секунд
            Thread.sleep((long) (3000 + Math.random() * 7000));

            // Віднімаємо паливо
            if (requestedAmount <= amount) {
                amount -= requestedAmount;
                System.out.println("Заправка виконана. Залишок палива: " + amount);
            } else {
                System.out.println("На станції недостатньо палива.");
            }
        } finally {
            refuelSemaphore.release(); // Звільнюємо ліцензію
        }
    }

    public double getAmount() {
        return amount;
    }
}
