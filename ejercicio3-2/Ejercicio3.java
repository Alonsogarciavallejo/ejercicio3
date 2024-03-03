// Ejercicio12 Expresion lambda
//  es una subrutina definida que no está enlazada a un identificador. Las expresiones lambda a menudo son argumentos que se pasan a funciones de orden superior, o se usan para construir el resultado de una función de orden superior que necesita devolver una función

// Ejercicio13
package Ejercicio13;
import java.util.*;
import java.util.function.Function;

public class Ejercicio13 {

  public static void main(String[] args) {
    try (Scanner sc = new Scanner(System.in)) {

      System.out.println("Limite inferior:");
      double limInf = sc.nextDouble();

      System.out.println("Limite superior:");
      double limSup = sc.nextDouble();

      System.out.println("Paso:");
      double paso = sc.nextDouble();

      double integral = integral(limInf, limSup, paso, x -> Math.pow(Math.exp(x), 2));

      System.out.println("La integral de e^x^2 desde " + limInf + " hasta " + limSup +
          " con un paso de " + paso + " es: " + integral);
    }
  }

  public static double integral(double a, double b, double h, Function<Double, Double> funcion) {
    double suma = 0;
    for (double x = a; x < b; x += h) {
      suma += funcion.apply(x) * h;
    }
    return suma;
  }
}
// Ejercicio14
import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        // 1. Generar un stream de números mediante el método estático 'of' de la clase 'IntStream'
        System.out.println("Stream generado con el método 'of':");
        IntStream streamOf = IntStream.of(1, 2, 3, 4, 5);
        streamOf.forEach(System.out::println);

        // 2. Generar un stream de números mediante el método estático 'range' de la clase 'IntStream'
        System.out.println("\nStream generado con el método 'range':");
        IntStream streamRange = IntStream.range(1, 6); // Genera números desde 1 hasta 5
        streamRange.forEach(System.out::println);

        // 3. Generar un stream de números mediante el método estático 'iterate' de la clase 'IntStream'
        System.out.println("\nStream generado con el método 'iterate':");
        IntStream streamIterate = IntStream.iterate(1, n -> n + 1).limit(5); // Genera números empezando desde 1 y sumando 1 en cada iteración, limitado a 5 elementos
        streamIterate.forEach(System.out::println);

        // 4. Generar un stream de números aleatorios utilizando el método 'doubles' de la clase 'Random'
        System.out.println("\nStream de números aleatorios:");
        Random random = new Random();
        DoubleStream streamRandom = random.doubles().limit(5); // Genera 5 números aleatorios
        streamRandom.forEach(System.out::println);
    }
}
// Ejercicio15
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Main {
    public static void main(String[] args) {
        // 1. La suma 0 + 1 + 2 + ... + n
        int n = 5;
        int suma = IntStream.rangeClosed(0, n).sum();
        System.out.println("Suma de los primeros " + n + " números: " + suma);

        // 2. El factorial de un número
        long num = 5;
        long factorial = LongStream.rangeClosed(1, num).reduce(1, (x, y) -> x * y);
        System.out.println("Factorial de " + num + ": " + factorial);

        // 3. La potencia n-ésima de un número natural
        int base = 2;
        int exponente = 3;
        double potencia = Math.pow(base, exponente);
        System.out.println("Potencia de " + base + " elevado a " + exponente + ": " + potencia);

        // 4. La suma de los elementos de una lista de números
        List<Integer> numeros = List.of(1, 2, 3, 4, 5);
        int sumaLista = numeros.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Suma de la lista de números: " + sumaLista);

        // 5. La media aritmética de una lista de números
        double media = numeros.stream().mapToInt(Integer::intValue).average().orElse(0);
        System.out.println("Media de la lista de números: " + media);

        // 6. La desviación típica de una lista de números
        double desviacionTipica = Math.sqrt(numeros.stream().mapToDouble(Double::valueOf).map(x -> Math.pow(x - media, 2)).sum() / numeros.size());
        System.out.println("Desviación típica de la lista de números: " + desviacionTipica);

        // 7. La suma de los primeros números pares hasta n asumiendo n ≥ 2
        int nPares = 9;
        int sumaPares = IntStream.iterate(nPares - 2, i -> i >= 0, i -> i - 2).sum();
        System.out.println("Suma de los primeros números pares hasta " + nPares + ": " + sumaPares);

        // 8. La suma de los elementos pares de una lista de enteros
        int sumaParesLista = numeros.stream().filter(x -> x % 2 == 0).mapToInt(Integer::intValue).sum();
        System.out.println("Suma de los elementos pares de la lista: " + sumaParesLista);

        // 9. Obtener una lista con los números pares de la lista inicial
        List<Integer> listaPares = numeros.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
        System.out.println("Lista de números pares de la lista inicial: " + listaPares);

        // 10. La lista de los primeros números pares hasta n asumiendo n ≥ 2
        List<Integer> listaPrimerosPares = IntStream.iterate(nPares - 2, i -> i >= 0, i -> i - 2).boxed().collect(Collectors.toList());
        System.out.println("Lista de los primeros números pares hasta " + nPares + ": " + listaPrimerosPares);

        // 11. Producto escalar de dos listas de números no vacías y del mismo tamaño
        List<Integer> lista1 = List.of(1, 2, 3);
        List<Integer> lista2 = List.of(2, 4, 6);
        int productoEscalar = IntStream.range(0, lista1.size()).map(i -> lista1.get(i) * lista2.get(i)).sum();
        System.out.println("Producto escalar de las listas: " + productoEscalar);

        // 12. El elemento n-ésimo de la sucesión de Fibonacci
        int nFibonacci = 7;
        long elementoFibonacci = IntStream.iterate(new long[]{0, 1}, f -> new long[]{f[1], f[0] + f[1]})
        .limit(nFibonacci)
        .mapToLong(f -> f[0])
        .skip(nFibonacci - 1)
        .findFirst()
        .orElse(0);
        System.out.println("El elemento " + nFibonacci + "-ésimo de la sucesión de Fibonacci: " + elementoFibonacci);
    }
}
// Ejercicio16
package Ejercicio16;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Ejercicio16 {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {

            System.out.println("Limite inferior:");
            double limInf = sc.nextDouble();

            System.out.println("Limite superior:");
            double limSup = sc.nextDouble();

            System.out.println("Paso:");
            double paso = sc.nextDouble();

            double integral = integral(limInf, limSup, paso, x -> Math.pow(Math.exp(x), 2));

            System.out.println("La integral de e^x^2 desde " + limInf + " hasta " + limSup +
                    " con un paso de " + paso + " es: " + integral);
        }
    }

    public static double integral(double a, double b, double h, Function<Double, Double> funcion) {
        DoubleUnaryOperator unaryOperator = x -> funcion.apply(x); 

        return DoubleStream.iterate(a, x -> x + h)
                .limit((long) ((b - a) / h))
                .map(unaryOperator)
                .sum() * h;
    }
}
// Ejercicio17
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class Persona {
    private String nombre;
    private LocalDate fechaDeNacimiento;

    public Persona(String nombre, LocalDate fechaDeNacimiento) {
        this.nombre = nombre;
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public long calcularEdad() {
        return LocalDate.now().getYear() - fechaDeNacimiento.getYear();
    }

    public String getNombre() {
        return nombre;
    }
}

public class Personas {
    private List<Persona> listaPersonas;

    public Personas() {
        listaPersonas = new ArrayList<>();
    }

    public void annadirPersona(Persona persona) {
        listaPersonas.add(persona);
    }

    public Persona elMasJoven() {
        return listaPersonas.stream()
                           .min((p1, p2) -> p1.getFechaDeNacimiento().compareTo(p2.getFechaDeNacimiento()))
                           .orElse(null);
    }

    public long calcularSumaEdades() {
        return listaPersonas.stream().mapToLong(Persona::calcularEdad).sum();
    }

    public long calcularEdadMinima() {
        return listaPersonas.stream().mapToLong(Persona::calcularEdad).min().orElse(0);
    }

    public double calcularMediaDeEdad() {
        return listaPersonas.stream().mapToLong(Persona::calcularEdad).average().orElse(0);
    }

    public static void main(String[] args) {
        Personas personas = new Personas();
        personas.annadirPersona(new Persona("Juan", LocalDate.of(1990, 5, 15)));
        personas.annadirPersona(new Persona("Maria", LocalDate.of(1985, 8, 20)));
        personas.annadirPersona(new Persona("Pedro", LocalDate.of(1978, 12, 10)));

        System.out.println("Persona más joven: " + personas.elMasJoven().getNombre());
        System.out.println("Suma de las edades: " + personas.calcularSumaEdades());
        System.out.println("Edad mínima: " + personas.calcularEdadMinima());
        System.out.println("Media de edad: " + personas.calcularMediaDeEdad());
    }
}