
public class Main {
    public static void main(String[] args) {

        // 1. Usar la lógica del submódulo 'Suma'
        int resultadoSuma = Suma.sumar(10, 5);
        System.out.println("El resultado de 10 + 5 es: " + resultadoSuma); // Debería ser 15

        // 2. Usar la lógica del submódulo 'Resta'
        int resultadoResta = Resta.restar(10, 5);
        System.out.println("El resultado de 10 - 5 es: " + resultadoResta); // Debería ser 5
    }
}
