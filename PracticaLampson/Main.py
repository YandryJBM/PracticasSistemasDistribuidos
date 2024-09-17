import threading

class CuentaBancaria:
    def __init__(self, saldo_inicial):
        self.saldo = saldo_inicial
        self.lock = threading.Lock()

    def depositar(self, monto):
        with self.lock:  # Adquiere el lock antes de modificar el saldo
            self.saldo += monto
            print(f"Se depositaron ${monto}. Saldo actual: ${self.saldo}")

    def retirar(self, monto):
        with self.lock:  # Adquiere el lock antes de modificar el saldo
            if self.saldo >= monto:
                self.saldo -= monto
                print(f"Se retiraron ${monto}. Saldo actual: ${self.saldo}")
            else:
                raise Exception("Fondos insuficientes.")

    def obtener_saldo(self):
        with self.lock:  # Adquiere el lock antes de leer el saldo
            return self.saldo

class Banco:
    def __init__(self):
        self.cuenta1 = CuentaBancaria(1000)
        self.cuenta2 = CuentaBancaria(500)
        self.lock = threading.Lock()

    def transferir_dinero(self, monto):
        with self.lock:  # Adquiere el lock para sincronizar la transferencia
            try:
                print(f"Iniciando transferencia de ${monto}")

                # Aquí se maneja la transferencia entre cuentas
                self.cuenta1.retirar(monto)  # Primer monitor invocado (CuentaBancaria)
                self.cuenta2.depositar(monto)  # Segundo monitor invocado (CuentaBancaria)

                print("Transferencia completada. Saldo final:")
                print(f"Cuenta 1: ${self.cuenta1.obtener_saldo()}")
                print(f"Cuenta 2: ${self.cuenta2.obtener_saldo()}")
            except Exception as e:
                print(f"Error en la transferencia: {e}")

def main():
    banco = Banco()

    # Ejecutamos la transferencia desde el hilo principal
    banco.transferir_dinero(300)

if __name__ == "__main__":
    main()
