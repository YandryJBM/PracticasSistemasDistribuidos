class Consumidor extends Thread {
    private final Buffer buffer;

    public Consumidor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                buffer.consumir();
                // Simular un pequeño retraso en la consumición
                sleep(1000);
            }
        } catch (InterruptedException e) {
        }
    }
}