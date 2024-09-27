import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {

    // Список потенциально аллергенных ингредиентов
    // Используем Set для хранения аллергенов, так как это позволяет избежать дублирования и ускоряет поиск
    private static final Set<String> ALLERGENS = Set.of("пшеница", "кукуруза", "соя", "молоко", "яйца",
            "курица", "конина", "белок птицы", "ячмень", "свинина", "белок кукурузы", "жир птицы",
            "индейка", "цыпленок", "утка", "кролик", "телятина", "ягненок", "птица", "куриный");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ввод составов двух кормов
        System.out.println("Введите состав первого корма (ингредиенты через запятую):");
        String food1 = scanner.nextLine().toLowerCase();
        System.out.println("Введите состав второго корма (ингредиенты через запятую):");
        String food2 = scanner.nextLine().toLowerCase();

        // Преобразование составов в списки ингредиентов
        List<String> ingredients1 = parseIngredients(food1);
        List<String> ingredients2 = parseIngredients(food2);

        // Нахождение аллергенных ингредиентов и совпадений
        Set<String> allergensInBoth = findAllergens(ingredients1, ingredients2);
        Set<String> commonIngredients = findCommonIngredients(ingredients1, ingredients2);

        // Вывод результатов
        System.out.println("Аллергенные ингредиенты в описанных составах:");
        if (allergensInBoth.isEmpty()) {
            System.out.println("Аллергенов не найдено.");
        } else {
            System.out.println(allergensInBoth);
        }

        System.out.println("Совпадающие ингредиенты в данных кормах:");
        if (commonIngredients.isEmpty()) {
            System.out.println("Совпадений не найдено.");
        } else {
            System.out.println(commonIngredients);
        }

        scanner.close();
    }

    // Метод для разбора строки состава на отдельные ингредиенты
    private static List<String> parseIngredients(String food) {
        String[] ingredientsArray = food.split(",");
        List<String> ingredients = new ArrayList<>();
        for (String ingredient : ingredientsArray) {
            ingredients.add(ingredient.trim());
        }
        return ingredients;
    }

    // Метод для поиска аллергенов
    private static Set<String> findAllergens(List<String> ingredients1, List<String> ingredients2) {
        Set<String> allergens = new HashSet<>();

        // Проверяем ингредиенты первого корма
        for (String ingredient : ingredients1) {
            if (containsAllergen(ingredient)) {
                allergens.add(ingredient);
            }
        }

        // Проверяем ингредиенты второго корма
        for (String ingredient : ingredients2) {
            if (containsAllergen(ingredient)) {
                allergens.add(ingredient);
            }
        }

        return allergens;
    }

    // Метод для проверки, содержит ли ингредиент какой-либо из аллергенов
    private static boolean containsAllergen(String ingredient) {
        for (String allergen : ALLERGENS) {
            if (ingredient.contains(allergen)) {  // Поиск подстроки аллергена в ингредиенте
                return true;
            }
        }
        return false;
    }

    // Метод для поиска совпадающих ингредиентов
    private static Set<String> findCommonIngredients(List<String> ingredients1, List<String> ingredients2) {
        Set<String> commonIngredients = new HashSet<>(ingredients1);
        commonIngredients.retainAll(ingredients2);
        return commonIngredients;
    }
}
