
/**
 * MobSpawner.java - Wrapper for mob spawners.
 * 
 * @author James
 */
public class MobSpawner implements ComplexBlock {

    OTileEntityMobSpawner spawner;

    /**
     * Creates an interface for the spawner.
     * 
     * @param spawner
     */
    public MobSpawner(OTileEntityMobSpawner spawner) {
        this.spawner = spawner;
    }

    @Override
    public int getX() {
        return spawner.e;
    }

    @Override
    public int getY() {
        return spawner.f;
    }

    @Override
    public int getZ() {
        return spawner.g;
    }

    @Override
    public Block getBlock() {
        return getWorld().getBlockAt(getX(), getY(), getZ());
    }

    @Override
    public World getWorld() {
        return spawner.d.world;
    }

    @Override
    public void update() {
        spawner.i();
    }

    /**
     * Allows what to spawn to change on-the-fly
     * 
     * @param spawn
     */
    public void setSpawn(String spawn) {
        spawner.h = spawn;
        update();
    }

    /**
     * Returns the spawn used.
     * 
     * @return
     */
    public String getSpawn() {
        return spawner.h;
    }

    /**
     * Allows delay of what to spawn to change on-the-fly Modification of this
     * is near-useless as delays get randomized after spawn. See:
     * Block.setSpawnData() if you want to adjust this value.
     * 
     * @param delay
     */
    public void setDelay(int delay) {
        spawner.a = delay;
    }
}
