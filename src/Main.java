import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
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

        // CONSUMER y BICONSUMER: reciben algo, no regresan nada (para imprimir)
        Consumer<String> imprimir = texto -> System.out.println(texto);
        BiConsumer<String, Object> mostrar =
                (titulo, valor) -> System.out.println(titulo + ": " + valor);

        // NIVEL 1
        BinaryOperator<Integer> suma = (a, b) -> a + b;
        Comparator<Integer> comparador = (a, b) -> Integer.compare(a, b);
        Function<List<Integer>, Integer> sumar = lista -> lista.stream().reduce(0, suma);
        Function<List<Integer>, Integer> maximo = lista -> lista.stream().max(comparador).get();
        Function<List<Integer>, Integer> minimo = lista -> lista.stream().min(comparador).get();
        Function<List<Integer>, Long> contar = lista -> lista.stream().count();

        imprimir.accept("====== Nivel 1: Operaciones básicas ======");
        mostrar.accept("1. Suma", sumar.apply(numeros));
        mostrar.accept("2. Máximo", maximo.apply(numeros));
        mostrar.accept("3. Mínimo", minimo.apply(numeros));
        mostrar.accept("4. Cantidad", contar.apply(numeros));

        // NIVEL 2
        Predicate<Integer> esPar = n -> n % 2 == 0;
        Predicate<Integer> mayorQue50 = n -> n > 50;
        Predicate<Integer> esPositivo = n -> n > 0;
        BiPredicate<Integer, Integer> enRango = (n, limite) -> n >= 10 && n <= limite;

        imprimir.accept("\n====== Nivel 2: filter ======");
        mostrar.accept("5. Pares", numeros.stream().filter(esPar).toList());
        mostrar.accept("6. Mayores que 50", numeros.stream().filter(mayorQue50).toList());
        mostrar.accept("7. Positivos", numeros.stream().filter(esPositivo).count());
        mostrar.accept("8. Entre 10 y 60", numeros.stream().filter(n -> enRango.test(n, 60)).toList());

        // NIVEL 3
        Function<Integer, Integer> alCuadrado = n -> n * n;
        Function<Integer, Integer> porDiez = n -> n * 10;
        Function<Double, Double> aFahrenheit = c -> c * 9 / 5 + 32;

        imprimir.accept("\n====== Nivel 3: map ======");
        mostrar.accept("9. Al cuadrado", numeros.stream().map(alCuadrado).toList());
        mostrar.accept("10. Por 10", numeros.stream().map(porDiez).toList());
        mostrar.accept("11. Fahrenheit", celsius.stream().map(aFahrenheit).toList());

        // NIVEL 4
        imprimir.accept("\n====== Nivel 4: Combinar operaciones ======");
        mostrar.accept("12. Pares al cuadrado", numeros.stream().filter(esPar).map(alCuadrado).toList());
        mostrar.accept("13. Suma de pares", numeros.stream().filter(esPar).reduce(0, suma));
        mostrar.accept("14. Promedio de mayores que 50",
                numeros.stream().filter(mayorQue50).mapToInt(Integer::intValue).average().orElse(0));
        mostrar.accept("15. Máximo de pares", numeros.stream().filter(esPar).max(comparador).get());

        // NIVEL 5
        imprimir.accept("\n====== Nivel 5: Ordenamiento ======");
        mostrar.accept("16. Menor a mayor", numeros.stream().sorted(comparador).toList());
        mostrar.accept("17. Mayor a menor", numeros.stream().sorted(comparador.reversed()).toList());
        mostrar.accept("18. Tres más grandes", numeros.stream().sorted(comparador.reversed()).limit(3).toList());

        // NIVEL 6
        Predicate<String> empiezaConA = s -> s.startsWith("A");
        Predicate<String> masDe5 = s -> s.length() > 5;
        Function<String, String> aMayusculas = s -> s.toUpperCase();
        Comparator<String> porAlfabeto = (a, b) -> a.compareTo(b);

        imprimir.accept("\n====== Nivel 6: Strings ======");
        mostrar.accept("19. Empiezan con A", nombres.stream().filter(empiezaConA).toList());
        mostrar.accept("20. Más de 5 caracteres", nombres.stream().filter(masDe5).toList());
        mostrar.accept("21. Mayúsculas", nombres.stream().map(aMayusculas).toList());
        mostrar.accept("22. Ordenados", nombres.stream().sorted(porAlfabeto).toList());

        // NIVEL 7
        BiPredicate<Integer, Integer> esIgual = (n, buscado) -> n.equals(buscado);
        Predicate<Integer> mayorQue80 = n -> n > 80;

        imprimir.accept("\n====== Nivel 7: Búsqueda y condiciones ======");
        mostrar.accept("23. Buscar el 45", numeros.stream().filter(n -> esIgual.test(n, 45)).findFirst().isPresent());
        mostrar.accept("24. ¿Todos positivos?", numeros.stream().allMatch(esPositivo));
        mostrar.accept("25. ¿Alguno mayor que 80?", numeros.stream().anyMatch(mayorQue80));
    }
}