import threading

class CondicionDeCarreraEjemplo:
    def __init__(self):
        self.contador = 0
        self.lock = threading.Lock()  # Creamos un Lock para evitar la condición de carrera

    def incrementar_contador(self):
        for _ in range(10000):
            with self.lock:  # Adquirimos el lock antes de incrementar
                self.contador += 1

def main():
    ejemplo = CondicionDeCarreraEjemplo()

    # Crear dos hilos que intentarán incrementar el contador
    hilo1 = threading.Thread(target=ejemplo.incrementar_contador)
    hilo2 = threading.Thread(target=ejemplo.incrementar_contador)

    hilo1.start()
    hilo2.start()

    hilo1.join()
    hilo2.join()

    # Al final, el contador debería ser 20000 debido a la sincronización
    print("Valor final del contador:", ejemplo.contador)

if __name__ == "__main__":
    main()
