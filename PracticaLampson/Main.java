// Monitor que representa una Cuenta Bancaria
class CuentaBancaria {
    private double saldo;

    public CuentaBancaria(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    // Sincronizar para que solo un hilo acceda a una cuenta a la vez
    public synchronized void depositar(double monto) {
        saldo += monto;
        System.out.println("Se depositaron $" + monto + ". Saldo actual: $" + saldo);
    }

    // Sincronizar para asegurar que solo un hilo acceda a la vez
    public synchronized void retirar(double monto) throws Exception {
        if (saldo >= monto) {
            saldo -= monto;
            System.out.println("Se retiraron $" + monto + ". Saldo actual: $" + saldo);
        } else {
            throw new Exception("Fondos insuficientes.");
        }
    }

    public synchronized double obtenerSaldo() {
        return saldo;
    }
}

// Monitor que representa el Banco
class Banco {
    private final CuentaBancaria cuenta1;
    private final CuentaBancaria cuenta2;

    public Banco() {
        cuenta1 = new CuentaBancaria(1000);
        cuenta2 = new CuentaBancaria(500);
    }

    // Método para transferir dinero entre cuentas
    public synchronized void transferirDinero(double monto) {
        try {
            System.out.println("Iniciando transferencia de $" + monto);
            
            // Aquí ocurre la invocación anidada: llamamos a los métodos sincronizados de las cuentas
            cuenta1.retirar(monto);  // Primer monitor invocado (CuentaBancaria)
            cuenta2.depositar(monto);  // Segundo monitor invocado (CuentaBancaria)
            
            System.out.println("Transferencia completada. Saldo final:");
            System.out.println("Cuenta 1: $" + cuenta1.obtenerSaldo());
            System.out.println("Cuenta 2: $" + cuenta2.obtenerSaldo());
        } catch (Exception e) {
            System.out.println("Error en la transferencia: " + e.getMessage());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco();

        // Ejecutamos la transferencia desde el hilo principal
        banco.transferirDinero(300);
    }
}
