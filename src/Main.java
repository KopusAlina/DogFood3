import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {

    // Список потенциально аллергенных ингредиентов
    private static final Set<String> ALLERGENS = Set.of("пшеница", "кукуруза", "соя", "молоко", "яйца", "рыба", "курица");

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

        System.out.println("Совпадающие ингредиенты:");
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

    // Метод для поиска аллергенов в составах
    private static Set<String> findAllergens(List<String> ingredients1, List<String> ingredients2) {
        Set<String> allergens = new HashSet<>();

        for (String ingredient : ingredients1) {
            if (ALLERGENS.contains(ingredient)) {
                allergens.add(ingredient);
            }
        }

        for (String ingredient : ingredients2) {
            if (ALLERGENS.contains(ingredient)) {
                allergens.add(ingredient);
            }
        }

        return allergens;
    }

    // Метод для поиска совпадающих ингредиентов
    private static Set<String> findCommonIngredients(List<String> ingredients1, List<String> ingredients2) {
        Set<String> commonIngredients = new HashSet<>(ingredients1);
        commonIngredients.retainAll(ingredients2);
        return commonIngredients;
    }
}
