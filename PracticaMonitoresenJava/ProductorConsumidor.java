public class ProductorConsumidor {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(5); // Buffer de tamaño 5

        Productor productor = new Productor(buffer);
        Consumidor consumidor = new Consumidor(buffer);

        productor.start();
        consumidor.start();
    }
}
