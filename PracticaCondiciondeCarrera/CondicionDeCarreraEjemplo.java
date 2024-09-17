public class CondicionDeCarreraEjemplo {
    private int contador = 0;

    public static void main(String[] args) {
        CondicionDeCarreraEjemplo ejemplo = new CondicionDeCarreraEjemplo();

        // Crear dos hilos que intentarán incrementar el contador
        Thread hilo1 = new Thread(() -> ejemplo.incrementarContador());
        Thread hilo2 = new Thread(() -> ejemplo.incrementarContador());

        hilo1.start();
        hilo2.start();
        
        try {
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e) {
        }

        // Al final, el contador debería ser 20000, pero debido a la condición de carrera puede no serlo
        System.out.println("Valor final del contador: " + ejemplo.contador);
    }

    public void incrementarContador() {
        for (int i = 0; i < 10000; i++) {
            contador++;  // Incremento no sincronizado
        }
    }
}
