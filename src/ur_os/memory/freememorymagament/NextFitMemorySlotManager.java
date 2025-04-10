/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ur_os.memory.freememorymagament;

public class NextFitMemorySlotManager extends FreeMemorySlotManager {

    // Puntero que indica desde qué posición continuar la búsqueda
    private int nextFitPointer;

    public NextFitMemorySlotManager(int memSize) {
        super(memSize);
        nextFitPointer = 0;
    }

    @Override
    public MemorySlot getSlot(int size) {
        MemorySlot m = null;

        // Verificamos que la lista de slots libres no esté vacía
        if (list.isEmpty()){
            System.out.println("Error - Memory cannot allocate a slot big enough for the requested memory");
            return m;
        }

        // Se guarda la posición inicial para saber cuándo se ha hecho un ciclo completo
        int startingIndex = nextFitPointer;
        do {
            MemorySlot slot = list.get(nextFitPointer);
            if (slot.canContain(size)) {
                // Si el slot tiene exactamente el tamaño solicitado, se retira de la lista y se asigna
                if (slot.getSize() == size) {
                    m = slot;
                    list.remove(nextFitPointer);
                    // Actualizar el puntero según la nueva lista
                    if(list.isEmpty()){
                        nextFitPointer = 0;
                    } else {
                        nextFitPointer = nextFitPointer % list.size();
                    }
                    return m;
                } else {
                    // Asigna la cantidad solicitada y actualiza el slot original
                    m = slot.assignMemory(size);
                    // Si luego de asignar la memoria el slot queda vacío, se elimina
                    if (slot.inNull()){
                        list.remove(nextFitPointer);
                        if(list.isEmpty()){
                            nextFitPointer = 0;
                        } else {
                            nextFitPointer = nextFitPointer % list.size();
                        }
                    } else {
                        // Si aún queda memoria, el puntero avanza al siguiente slot
                        nextFitPointer = (nextFitPointer + 1) % list.size();
                    }
                    return m;
                }
            }
            // Avanza al siguiente slot de forma circular
            nextFitPointer = (nextFitPointer + 1) % list.size();
        } while (nextFitPointer != startingIndex);

        System.out.println("Error - Memory cannot allocate a slot big enough for the requested memory");
        return m;
    }
}