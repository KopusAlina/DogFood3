import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите состав первого корма (ингредиенты через запятую):");
        String food1 = scanner.nextLine().toLowerCase();
        System.out.println("Введите состав второго корма (ингредиенты через запятую):");
        String food2 = scanner.nextLine().toLowerCase();
    }


}
