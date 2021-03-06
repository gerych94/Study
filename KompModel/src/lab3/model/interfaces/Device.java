package lab3.model.interfaces;

import lab3.model.ChildrenDevice;
import lab3.model.statustable.StatusTable;
import java.util.Collection;

/**
 * Created by byte on 23.10.14.
 *
 */
public interface Device {

    Device putTask();
    boolean deviceReady();
    Device addDevice(Device device, double d);
    Device processingTask(int number_processor);
    int getNumberofResolvedTasks();
    String getDeviseName();
    double getEfficiency(int number_processor);
    void makeTree();

    ChildrenDevice getChildrenDevice();
}
