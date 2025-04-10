/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ur_os.memory.freememorymagament;

/**
 *
 * @author super
 */
public class WorstFitMemorySlotManager extends FreeMemorySlotManager{
    
    public WorstFitMemorySlotManager(int memSize){
        super(memSize);
    }
    
    @Override
    public MemorySlot getSlot(int size) {
        MemorySlot worstFit = null;
    
        for (MemorySlot memorySlot : list) {
            if (memorySlot.canContain(size)) {
                if (worstFit == null || memorySlot.getSize() > worstFit.getSize()) {
                    worstFit = memorySlot;
                }
            }
        }
    
        if (worstFit == null) {
            System.out.println("Error - Memory cannot allocate a slot big enough for the requested memory");
            return null;
        }
    
        if (worstFit.getSize() == size) {
            list.remove(worstFit);
            return worstFit;
        } else {
            return worstFit.assignMemory(size);
        }
    }
    
    
}