public class Application {
    public static void main(String[] args) {
// Створюємо об'єкти ThreadSafeList та PetrolStation
        ThreadSafeList<String> threadSafeList = new ThreadSafeList<>();
        PetrolStation petrolStation = new PetrolStation(100.0); // Початкова кількість палива - 100.0 літрів

        // Додаємо елементи до списку
        threadSafeList.add("Елемент 1");
        threadSafeList.add("Елемент 2");
        threadSafeList.add("Елемент 3");

        // Створюємо та запускаємо потоки для тестування
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                // Додаємо, видаляємо та отримуємо елементи зі списку
                threadSafeList.add("Новий елемент");
                threadSafeList.remove("Елемент 1");
                String element = threadSafeList.get(0);
                System.out.println("Отримано елемент: " + element);
            }).start();
        }

        // Створюємо та запускаємо потоки для тестування заправки
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    // Кожен потік викликає метод doRefuel
                    petrolStation.doRefuel(10.0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
