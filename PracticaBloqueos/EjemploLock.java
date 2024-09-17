
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class EjemploLock {
    private final Lock lock = new ReentrantLock();  // Creamos un ReentrantLock
    private int contador = 0;

    public static void main(String[] args) {
        EjemploLock ejemplo = new EjemploLock();

        // Crear 3 hilos que incrementarán el contador
        for (int i = 1; i <= 3; i++) {
            new Thread(() -> ejemplo.incrementar()).start();
        }
    }

    public void incrementar() {
        lock.lock();  // Adquirimos el lock antes de modificar el recurso compartido
        try {
            contador++;
            System.out.println(Thread.currentThread().getName() + " ha incrementado el contador a: " + contador);
        } finally {
            lock.unlock();  // Aseguramos liberar el lock al final
        }
    }
}
