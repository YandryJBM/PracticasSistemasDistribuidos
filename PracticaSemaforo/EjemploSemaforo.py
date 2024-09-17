import threading
import time

class EjemploSemaforo:
    def __init__(self):
        self.semaforo = threading.Semaphore(2)

    def tarea(self, nombre):
        print(f"{nombre} está intentando acceder al recurso...")
        with self.semaforo:  # Adquiere un permiso
            print(f"{nombre} ha accedido al recurso.")
            time.sleep(2)  # Simulamos que el hilo está utilizando el recurso
            print(f"{nombre} ha liberado el recurso.")

def main():
    ejemplo = EjemploSemaforo()

    # Crear 5 hilos que intentarán acceder al recurso limitado
    hilos = []
    for i in range(1, 6):
        hilo = threading.Thread(target=ejemplo.tarea, args=(f"Hilo {i}",))
        hilos.append(hilo)
        hilo.start()

    # Esperar a que todos los hilos terminen
    for hilo in hilos:
        hilo.join()

if __name__ == "__main__":
    main()
