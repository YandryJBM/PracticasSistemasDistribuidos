import threading

class EjemploLock:
    def __init__(self):
        self.lock = threading.Lock()  # Creamos un Lock
        self.contador = 0

    def incrementar(self):
        with self.lock:  # Adquirimos el lock antes de modificar el recurso compartido
            self.contador += 1
            print(f"{threading.current_thread().name} ha incrementado el contador a: {self.contador}")

def main():
    ejemplo = EjemploLock()

    # Crear 3 hilos que incrementarán el contador
    threads = []
    for i in range(1, 4):
        thread = threading.Thread(target=ejemplo.incrementar, name=f"Hilo {i}")
        thread.start()
        threads.append(thread)

    # Esperar a que todos los hilos terminen
    for thread in threads:
        thread.join()

if __name__ == "__main__":
    main()
