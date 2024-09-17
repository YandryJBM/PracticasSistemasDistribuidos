import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class EjemploCondition {
    private final Lock lock = new ReentrantLock();
    private final Condition condicion = lock.newCondition();
    private boolean listo = false;

    public static void main(String[] args) {
        EjemploCondition ejemplo = new EjemploCondition();

        // Crear un hilo que espera la condición y otro que la senala
        new Thread(() -> ejemplo.esperar()).start();
        new Thread(() -> ejemplo.senalar()).start();
    }

    // Método que espera hasta que se cumpla la condición
    public void esperar() {
        lock.lock();
        try {
            while (!listo) {
                System.out.println(Thread.currentThread().getName() + " esperando...");
                condicion.await();  // Espera a que se cumpla la condición
            }
            System.out.println(Thread.currentThread().getName() + " ha continuado.");
        } catch (InterruptedException e) {
            // Asegúrate de manejar la excepción correctamente
            
        } finally {
            lock.unlock();
        }
    }

    // Método que senala cuando la condición se ha cumplido
    public void senalar() {
        lock.lock();
        try {
            Thread.sleep(2000);  // Simulamos alguna operación
            listo = true;
            System.out.println(Thread.currentThread().getName() + " senalando condicion.");
            condicion.signal();  // Senalamos que la condición se ha cumplido
        } catch (InterruptedException e) {
            // Asegúrate de manejar la excepción correctamente
            
        } finally {
            lock.unlock();
        }
    }
}
