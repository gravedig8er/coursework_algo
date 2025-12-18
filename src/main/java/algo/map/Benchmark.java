package algo.map;

import algo.map.MyHashMap;
import java.util.Random;

public class Benchmark {

    public static void main(String[] args) {
        System.out.println("=== НАЧАЛО СРАВНИТЕЛЬНОГО АНАЛИЗА ===");

        int elementsCount = 100_000;

        System.out.println("Количество элементов: " + elementsCount);
        System.out.println("-------------------------------------");

        runTest("Линейное пробирование", false, elementsCount);

    }

    private static void runTest(String strategyName, boolean useQuadratic, int count) {
        MyHashMap<Integer, String> map = new MyHashMap<>();
        Random random = new Random(42);

        long startTime = System.nanoTime();

        for (int i = 0; i < count; i++) {
            int key = random.nextInt(count * 10);
            map.insert(key, "Value" + i);
        }

        long endTime = System.nanoTime();
        long durationMs = (endTime - startTime) / 1_000_000;

        System.out.printf("Стратегия: %s\n", strategyName);
        System.out.printf("Время выполнения: %d мс\n", durationMs);
        System.out.printf("Количество коллизий: %d\n", map.getCollisionCount());
        System.out.println("-------------------------------------");
    }
}
