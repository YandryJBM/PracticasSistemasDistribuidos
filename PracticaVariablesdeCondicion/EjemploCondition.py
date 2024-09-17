import threading
import time

class EjemploCondition:
    def __init__(self):
        self.lock = threading.Lock()
        self.condicion = threading.Condition(self.lock)
        self.listo = False

    def esperar(self):
        with self.condicion:  # Adquirimos el lock y la condición
            while not self.listo:
                print(f"{threading.current_thread().name} esperando...")
                self.condicion.wait()  # Espera a que se cumpla la condición
            print(f"{threading.current_thread().name} ha continuado.")

    def senalar(self):
        with self.condicion:  # Adquirimos el lock y la condición
            time.sleep(2)  # Simulamos alguna operación
            self.listo = True
            print(f"{threading.current_thread().name} señalando condición.")
            self.condicion.notify()  # Señalamos que la condición se ha cumplido

def main():
    ejemplo = EjemploCondition()

    # Crear un hilo que espera la condición y otro que la señala
    hilo1 = threading.Thread(target=ejemplo.esperar, name="Hilo Espera")
    hilo2 = threading.Thread(target=ejemplo.senalar, name="Hilo Señal")

    hilo1.start()
    hilo2.start()

    hilo1.join()
    hilo2.join()

if __name__ == "__main__":
    main()
