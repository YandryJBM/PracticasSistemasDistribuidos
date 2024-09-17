import java.util.concurrent.Semaphore;

public class EjemploSemaforo {
    // Creamos un semáforo con 2 permisos, es decir, 2 hilos pueden acceder al recurso al mismo tiempo
    private static final Semaphore semaphore = new Semaphore(2);

    public static void main(String[] args) {
        // Crear 5 hilos que intentarán acceder al recurso limitado
        for (int i = 1; i <= 5; i++) {
            new Thread(new Tarea("Hilo " + i)).start();
        }
    }

    static class Tarea implements Runnable {
        private final String nombre;

        public Tarea(String nombre) {
            this.nombre = nombre;
        }

        @Override
        public void run() {
            try {
                System.out.println(nombre + " esta intentando acceder al recurso...");
                semaphore.acquire();  // Adquiere un permiso
                System.out.println(nombre + " ha accedido al recurso.");
                Thread.sleep(2000);  // Simulamos que el hilo está utilizando el recurso
            } catch (InterruptedException e) {
            } finally {
                System.out.println(nombre + " ha liberado el recurso.");
                semaphore.release();  // Libera el permiso
            }
        }
    }
}
