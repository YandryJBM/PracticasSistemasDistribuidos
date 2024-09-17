public class Buffer {
    private final int[] buffer;
    private int count, in, out;
    private final int SIZE;
    
    public Buffer(int size) {
        SIZE = size;
        buffer = new int[SIZE];
        count = in = out = 0;
    }

    // El método synchronized asegura que solo un hilo pueda producir a la vez
    public synchronized void producir(int item) throws InterruptedException {
        while (count == SIZE) {
            // Esperar si el buffer está lleno
            System.out.println("Buffer lleno. Productor esperando...");
            wait();
        }
        
        // Producir: insertar el item en el buffer
        buffer[in] = item;
        in = (in + 1) % SIZE;
        count++;
        
        System.out.println("Producido: " + item);
        
        // Notificar al consumidor que puede consumir
        notify();
    }

    // El método synchronized asegura que solo un hilo pueda consumir a la vez
    public synchronized int consumir() throws InterruptedException {
        while (count == 0) {
            // Esperar si el buffer está vacío
            System.out.println("Buffer vacío. Consumidor esperando...");
            wait();
        }
        
        // Consumir: retirar el item del buffer
        int item = buffer[out];
        out = (out + 1) % SIZE;
        count--;
        
        System.out.println("Consumido: " + item);
        
        // Notificar al productor que puede producir
        notify();
        
        return item;
    }
}