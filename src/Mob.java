
/**
 * Mob.java - Interface for mobs
 * 
 * @author James
 */
public class Mob extends LivingEntity {
    /**
     * Creates a mob interface
     * 
     * @param locallb
     *            name of mob
     */
    public Mob(OEntityLiving locallb) {
        super(locallb);
    }

    /**
     * Creates a mob interface
     * 
     * @param mob
     *            name of mob
     */
    public Mob(String mob) {
        this((OEntityLiving) OEntityList.a(mob, etc.getMCServer().a(0)));
    }

    /**
     * Creates a mob interface
     * 
     * @param mobName
     *            name of mob
     * @param location
     *            location of mob
     */
    public Mob(String mobName, Location location) {
        this(mobName);
        teleportTo(location);
    }

    /**
     * Spawns this mob
     */
    public void spawn() {
        spawn(null);
    }

    /**
     * Spawns this mob with a rider
     * 
     * @param rider
     */
    public void spawn(Mob rider) {
        OWorld world = etc.getMCServer().a(0);

        entity.c(getX() + 0.5d, getY(), getZ() + 0.5d, getRotation(), 0f);
        world.b(entity);

        if (rider != null) {
            OEntityLiving mob2 = rider.getMob();
            mob2.c(getX(), getY(), getZ(), getRotation(), 0f);
            world.b(mob2);
            mob2.b(entity);
        }
    }

    /**
     * Returns this mob's name
     * 
     * @return name
     */
    public String getName() {
        return OEntityList.b(entity);
    }

    /**
     * Drops this mob's loot. Automatically called if health is set to 0.
     */
    public void dropLoot() {
        // Forced cast to get to the intended method
        getEntity().a((OEntity) null);
    }

    @Override
    public void setHealth(int health) {
        super.setHealth(health);
        if (health <= 0)
            dropLoot();
    }

    /**
     * Returns the actual mob
     * 
     * @return
     */
    public OEntityLiving getMob() {
        return getEntity();
    }

    /**
     * Checks to see if the mob is a valid mob
     * 
     * @param mob
     *            the mob to check
     * @return true of mob is valid
     */
    public static boolean isValid(String mob) {
        if (mob == null)
            return false;
        OEntity c = OEntityList.a(mob, etc.getMCServer().a(0));
        return c instanceof OIMob || c instanceof OIAnimals;
    }

}
