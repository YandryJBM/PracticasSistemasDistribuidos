import threading
import time
from queue import Queue

class Buffer:
    def __init__(self, size):
        self.size = size
        self.buffer = Queue(maxsize=size)
        self.lock = threading.Lock()
        self.condition = threading.Condition(self.lock)

    def producir(self, item):
        with self.condition:
            while self.buffer.full():
                print("Buffer lleno. Productor esperando...")
                self.condition.wait()
            
            self.buffer.put(item)
            print(f"Producido: {item}")
            
            # Notificar al consumidor que puede consumir
            self.condition.notify()

    def consumir(self):
        with self.condition:
            while self.buffer.empty():
                print("Buffer vacío. Consumidor esperando...")
                self.condition.wait()
            
            item = self.buffer.get()
            print(f"Consumido: {item}")
            
            # Notificar al productor que puede producir
            self.condition.notify()
            
            return item

def productor(buffer):
    for i in range(10):
        buffer.producir(i)
        time.sleep(1)

def consumidor(buffer):
    for _ in range(10):
        buffer.consumir()
        time.sleep(2)

def main():
    buffer = Buffer(5)

    # Crear y ejecutar los hilos productor y consumidor
    hilo_productor = threading.Thread(target=productor, args=(buffer,))
    hilo_consumidor = threading.Thread(target=consumidor, args=(buffer,))

    hilo_productor.start()
    hilo_consumidor.start()

    # Esperar a que ambos hilos terminen
    hilo_productor.join()
    hilo_consumidor.join()

if __name__ == "__main__":
    main()
