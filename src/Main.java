import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//Cristopher Farid Vázquez Sánchez
//Grupo: 3IM1
public class Main {
    public static void main(String[] args) {
        // Repositorio: https://github.com/crissanzzz07/PP_CFVS_EJE_08

        List<Integer> numeros = Arrays.asList(12, 45, 7, 88, 23, 64, 51, 3, 90, 36);
        List<String> nombres = Arrays.asList("Ana", "Carlos", "Beatriz", "Luis",
                "Fernando", "Sofía", "Alejandra", "Pedro");
        List<Double> celsius = Arrays.asList(0.0, 15.5, 25.0, 37.0, 100.0);

        // NIVEL 1
        System.out.println("====== Nivel 1: Operaciones básicas ======");
        System.out.println("1. Suma: " + numeros.stream().reduce(0, Integer::sum));
        System.out.println("2. Máximo: " + numeros.stream().max(Integer::compare).get());
        System.out.println("3. Mínimo: " + numeros.stream().min(Integer::compare).get());
        System.out.println("4. Cantidad: " + numeros.stream().count());

        // NIVEL 2
        System.out.println("\n====== Nivel 2: filter ======");
        System.out.println("5. Pares: " + numeros.stream().filter(n -> n % 2 == 0).toList());
        System.out.println("6. Mayores que 50: " + numeros.stream().filter(n -> n > 50).toList());
        System.out.println("7. Positivos: " + numeros.stream().filter(n -> n > 0).count());
        System.out.println("8. Entre 10 y 60: " + numeros.stream().filter(n -> n >= 10 && n <= 60).toList());

        // NIVEL 3
        System.out.println("\n====== Nivel 3: map ======");
        System.out.println("9. Al cuadrado: " + numeros.stream().map(n -> n * n).toList());
        System.out.println("10. Por 10: " + numeros.stream().map(n -> n * 10).toList());
        System.out.println("11. Fahrenheit: " + celsius.stream().map(c -> c * 9 / 5 + 32).toList());

        // NIVEL 4
        System.out.println("\n====== Nivel 4: Combinar operaciones ======");
        System.out.println("12. Pares al cuadrado: " + numeros.stream().filter(n -> n % 2 == 0).map(n -> n * n).toList());
        System.out.println("13. Suma de pares: " + numeros.stream().filter(n -> n % 2 == 0).reduce(0, Integer::sum));
        System.out.println("14. Promedio de mayores que 50: " + numeros.stream().filter(n -> n > 50).mapToInt(Integer::intValue).average().orElse(0));
        System.out.println("15. Máximo de pares: " + numeros.stream().filter(n -> n % 2 == 0).max(Integer::compare).get());

        // NIVEL 5
        System.out.println("\n====== Nivel 5: Ordenamiento ======");
        System.out.println("16. Menor a mayor: " + numeros.stream().sorted().toList());
        System.out.println("17. Mayor a menor: " + numeros.stream().sorted(Comparator.reverseOrder()).toList());
        System.out.println("18. Tres más grandes: " + numeros.stream().sorted(Comparator.reverseOrder()).limit(3).toList());

        // NIVEL 6
        System.out.println("\n====== Nivel 6: Strings ======");
        System.out.println("19. Empiezan con A: " + nombres.stream().filter(s -> s.startsWith("A")).toList());
        System.out.println("20. Más de 5 caracteres: " + nombres.stream().filter(s -> s.length() > 5).toList());
        System.out.println("21. Mayúsculas: " + nombres.stream().map(String::toUpperCase).toList());
        System.out.println("22. Ordenados: " + nombres.stream().sorted().toList());

        // NIVEL 7
        System.out.println("\n====== Nivel 7: Búsqueda y condiciones ======");
        System.out.println("23. Buscar el 45: " + numeros.stream().filter(n -> n == 45).findFirst().isPresent());
        System.out.println("24. ¿Todos positivos?: " + numeros.stream().allMatch(n -> n > 0));
        System.out.println("25. ¿Alguno mayor que 80?: " + numeros.stream().anyMatch(n -> n > 80));
    }
}