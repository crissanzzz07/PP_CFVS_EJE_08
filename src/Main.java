import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

//Cristopher Farid Vázquez Sánchez
//Grupo: 3IM1
public class Main {
    public static void main(String[] args) {
        // Repositorio: https://github.com/crissanzzz07/PP_CFVS_EJE_08

        // SUPPLIER: no recibe nada, regresa algo (las listas base)
        Supplier<List<Integer>> proveedorNumeros =
                () -> Arrays.asList(12, 45, 7, 88, 23, 64, 51, 3, 90, 36);
        Supplier<List<String>> proveedorNombres =
                () -> Arrays.asList("Ana", "Carlos", "Beatriz", "Luis",
                        "Fernando", "Sofía", "Alejandra", "Pedro");
        Supplier<List<Double>> proveedorCelsius =
                () -> Arrays.asList(0.0, 15.5, 25.0, 37.0, 100.0);

        List<Integer> numeros = proveedorNumeros.get();
        List<String> nombres = proveedorNombres.get();
        List<Double> celsius = proveedorCelsius.get();

        // NIVEL 1
        BinaryOperator<Integer> suma = (a, b) -> a + b;
        Comparator<Integer> comparador = (a, b) -> Integer.compare(a, b);
        Function<List<Integer>, Integer> sumar = lista -> lista.stream().reduce(0, suma);
        Function<List<Integer>, Integer> maximo = lista -> lista.stream().max(comparador).get();
        Function<List<Integer>, Integer> minimo = lista -> lista.stream().min(comparador).get();
        Function<List<Integer>, Long> contar = lista -> lista.stream().count();

        System.out.println("====== Nivel 1: Operaciones básicas ======");
        System.out.println("1. Suma: " + sumar.apply(numeros));
        System.out.println("2. Máximo: " + maximo.apply(numeros));
        System.out.println("3. Mínimo: " + minimo.apply(numeros));
        System.out.println("4. Cantidad: " + contar.apply(numeros));

        // NIVEL 2
        Predicate<Integer> esPar = n -> n % 2 == 0;
        Predicate<Integer> mayorQue50 = n -> n > 50;
        Predicate<Integer> esPositivo = n -> n > 0;
        BiPredicate<Integer, Integer> enRango = (n, limite) -> n >= 10 && n <= limite;

        System.out.println("\n====== Nivel 2: filter ======");
        System.out.println("5. Pares: " + numeros.stream().filter(esPar).toList());
        System.out.println("6. Mayores que 50: " + numeros.stream().filter(mayorQue50).toList());
        System.out.println("7. Positivos: " + numeros.stream().filter(esPositivo).count());
        System.out.println("8. Entre 10 y 60: " + numeros.stream().filter(n -> enRango.test(n, 60)).toList());

        // NIVEL 3
        Function<Integer, Integer> alCuadrado = n -> n * n;
        Function<Integer, Integer> porDiez = n -> n * 10;
        Function<Double, Double> aFahrenheit = c -> c * 9 / 5 + 32;

        System.out.println("\n====== Nivel 3: map ======");
        System.out.println("9. Al cuadrado: " + numeros.stream().map(alCuadrado).toList());
        System.out.println("10. Por 10: " + numeros.stream().map(porDiez).toList());
        System.out.println("11. Fahrenheit: " + celsius.stream().map(aFahrenheit).toList());

        // NIVEL 4
        System.out.println("\n====== Nivel 4: Combinar operaciones ======");
        System.out.println("12. Pares al cuadrado: " + numeros.stream().filter(esPar).map(alCuadrado).toList());
        System.out.println("13. Suma de pares: " + numeros.stream().filter(esPar).reduce(0, suma));
        System.out.println("14. Promedio de mayores que 50: "
                + numeros.stream().filter(mayorQue50).mapToInt(Integer::intValue).average().orElse(0));
        System.out.println("15. Máximo de pares: " + numeros.stream().filter(esPar).max(comparador).get());

        // NIVEL 5
        System.out.println("\n====== Nivel 5: Ordenamiento ======");
        System.out.println("16. Menor a mayor: " + numeros.stream().sorted(comparador).toList());
        System.out.println("17. Mayor a menor: " + numeros.stream().sorted(comparador.reversed()).toList());
        System.out.println("18. Tres más grandes: " + numeros.stream().sorted(comparador.reversed()).limit(3).toList());

        // NIVEL 6
        Predicate<String> empiezaConA = s -> s.startsWith("A");
        Predicate<String> masDe5 = s -> s.length() > 5;
        Function<String, String> aMayusculas = s -> s.toUpperCase();
        Comparator<String> porAlfabeto = (a, b) -> a.compareTo(b);

        System.out.println("\n====== Nivel 6: Strings ======");
        System.out.println("19. Empiezan con A: " + nombres.stream().filter(empiezaConA).toList());
        System.out.println("20. Más de 5 caracteres: " + nombres.stream().filter(masDe5).toList());
        System.out.println("21. Mayúsculas: " + nombres.stream().map(aMayusculas).toList());
        System.out.println("22. Ordenados: " + nombres.stream().sorted(porAlfabeto).toList());

        // NIVEL 7
        BiPredicate<Integer, Integer> esIgual = (n, buscado) -> n.equals(buscado);
        Predicate<Integer> mayorQue80 = n -> n > 80;

        System.out.println("\n====== Nivel 7: Búsqueda y condiciones ======");
        System.out.println("23. Buscar el 45: " + numeros.stream().filter(n -> esIgual.test(n, 45)).findFirst().isPresent());
        System.out.println("24. ¿Todos positivos?: " + numeros.stream().allMatch(esPositivo));
        System.out.println("25. ¿Alguno mayor que 80?: " + numeros.stream().anyMatch(mayorQue80));
    }
}
