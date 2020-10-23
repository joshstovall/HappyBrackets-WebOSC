import net.happybrackets.core.HBAction;
import net.happybrackets.core.OSCUDPSender;
import net.happybrackets.device.HB;
import net.happybrackets.device.sensors.AccelerometerListener;
import java.lang.invoke.MethodHandles;

public class WebOSC implements HBAction {
    @Override
    public void action(HB hb) {
        hb.reset();
        OSCUDPSender oscSend = new OSCUDPSender();


        new AccelerometerListener(hb) {
            @Override
            public void sensorUpdated(float x_val, float y_val, float z_val) {
                oscSend.send(HB.createOSCMessage("/hb/accelData", x_val, y_val, z_val), "192.168.0.100", 7400);
            }
        };
    }


    //<editor-fold defaultstate="collapsed" desc="Debug Start">

    /**
     * This function is used when running sketch in IntelliJ IDE for debugging or testing
     *
     * @param args standard args required
     */
    public static void main(String[] args) {

        try {
            HB.runDebug(MethodHandles.lookup().lookupClass());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //</editor-fold>
}
