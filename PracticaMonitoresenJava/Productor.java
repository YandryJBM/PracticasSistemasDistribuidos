class Productor extends Thread {
    private final Buffer buffer;

    public Productor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                buffer.producir(i);
                // Simular un pequeño retraso en la producción
                sleep(500);
            }
        } catch (InterruptedException e) {
        }
    }
}