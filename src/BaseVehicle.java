/**
 * BaseVehicle - Base class for interfacing boats and minecarts
 * 
 * @author James
 */
public class BaseVehicle extends BaseEntity {
    /**
     * Creates an interface for a vehicle
     * 
     * @param entity
     */
    public BaseVehicle(OEntity entity) {
        this.entity = entity;
    }

    /**
     * Interface for vehicles.
     */
    public BaseVehicle() {
    }

    /**
     * Returns the x-motion of this vehicle
     * 
     * @return x-motion
     */
    public double getMotionX() {
        return entity.aO;
    }

    /**
     * Returns the y-motion of this vehicle
     * 
     * @return y-motion
     */
    public double getMotionY() {
        return entity.aP;
    }

    /**
     * Returns the z-motion of this vehicle
     * 
     * @return z-motion
     */
    public double getMotionZ() {
        return entity.aQ;
    }

    /**
     * Sets the x-motion of this vehicle
     * 
     * @param motion
     *            motion to set
     */
    public void setMotionX(double motion) {
        entity.aO = motion;
    }

    /**
     * Sets the y-motion of this vehicle
     * 
     * @param motion
     *            motion to set
     */
    public void setMotionY(double motion) {
        entity.aP = motion;
    }

    /**
     * Sets the z-motion of this vehicle
     * 
     * @param motion
     *            motion to set
     */
    public void setMotionZ(double motion) {
        entity.aQ = motion;
    }

    /**
     * Set vehicle motion
     * 
     * @param motionX
     * @param motionY
     * @param motionZ
     */
    public void setMotion(double motionX, double motionY, double motionZ) {
        setMotionX(motionX);
        setMotionY(motionY);
        setMotionZ(motionZ);
    }

    /**
     * Destroys this vehicle
     */
    public void destroy() {
        entity.G();
    }

    /**
     * Checks if this vehicle is empty (unoccupied)
     * 
     * @return true if unoccupied.
     */
    public boolean isEmpty() {
        if (entity.aF == null)
            return true;
        else
            return false;
    }

    /**
     * Returns the passenger. If there is no passenger this function returns
     * null.
     * 
     * @return passenger
     */
    public Player getPassenger() {
        if (entity.aF != null)
            if (isPlayer(entity.aF))
                return ((OEntityPlayerMP) entity.aF).getPlayer();

        return null;
    }
}
