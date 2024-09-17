public class CondicionDeCarreraEjemploSincronizado {
    private int contador = 0;

    public static void main(String[] args) {
        CondicionDeCarreraEjemploSincronizado ejemplo = new CondicionDeCarreraEjemploSincronizado();

        Thread hilo1 = new Thread(() -> ejemplo.incrementarContador());
        Thread hilo2 = new Thread(() -> ejemplo.incrementarContador());

        hilo1.start();
        hilo2.start();
        
        try {
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e) {
        }

        System.out.println("Valor final del contador: " + ejemplo.contador);
    }

    // Método sincronizado para evitar condiciones de carrera
    public synchronized void incrementarContador() {
        for (int i = 0; i < 10000; i++) {
            contador++;
        }
    }
}
