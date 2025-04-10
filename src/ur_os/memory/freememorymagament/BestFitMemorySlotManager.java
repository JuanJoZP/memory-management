/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ur_os.memory.freememorymagament;

public class BestFitMemorySlotManager extends FreeMemorySlotManager{
    
    public BestFitMemorySlotManager(int memSize){
        super(memSize);
    }
    
    @Override
    public MemorySlot getSlot(int size) {
        MemorySlot m = null;
        MemorySlot candidate = null;
        
        // Se recorre la lista de slots libres para determinar el mejor ajuste.
        for (MemorySlot slot : list) {
            if (slot.canContain(size)) {
                // Si el slot tiene exactamente el tamaño solicitado, se elimina y se retorna.
                if (slot.getSize() == size) {
                    m = slot;
                    list.remove(slot);
                    return m;
                }
                // Se selecciona el slot que tenga el tamaño mínimo entre los que puedan contener la solicitud.
                if (candidate == null || slot.getSize() < candidate.getSize()) {
                    candidate = slot;
                }
            }
        }
        
        // Si se encontró un candidato, se asigna la cantidad solicitada y se retorna.
        if (candidate != null) {
            m = candidate.assignMemory(size);
            return m;
        }
        
        System.out.println("Error - Memory cannot allocate a slot big enough for the requested memory");
        return m; // m será null en este caso
    }
}
