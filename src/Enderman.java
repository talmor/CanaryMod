public class Enderman extends Mob {

    public Enderman(OEntityEnderman entity) {
        super(entity);
    }
    
    /**
     * Returns block id in endermans's hand
     * 
     * @return Block
     */
    public Block getBlockInHand() {
        return new Block(Block.Type.fromId(((OEntityEnderman)getEntity()).x()), ((OEntityEnderman)getEntity()).y());
    }
    
    /**
     *  Sets the block the enderman is holding.
     *  @param blockID - the block ID that the enderman should hold.
     * @return True if the enderman can hold the block or not.
     */
    public boolean setBlockInHand(int blockID){
        if (((OEntityEnderman)getEntity()).canHoldItem(blockID))
        {
            ((OEntityEnderman)getEntity()).b(blockID);
            ((OEntityEnderman)getEntity()).d(0);
        }
        return false;
    }
    
    /**
     *  Sets the block the enderman is holding.
     *  @param blockID - the block ID that the enderman should hold.
     *  @param blockData - the data of the block the enderman should hold.
     * @return True if the enderman can hold the block or not.
     */
    public boolean setBlockInHand(int blockID, int blockData){
        if (((OEntityEnderman)getEntity()).canHoldItem(blockID))
        {
            ((OEntityEnderman)getEntity()).b(blockID);
            ((OEntityEnderman)getEntity()).d(blockData);
        }
        return false;
    }
    
    /**
     *  Sets the block the enderman is holding.
     *  @param Block - the block the enderman should hold
     * @return True if the enderman can hold the block or not.
     */
    public boolean setBlockInHand(Block block){
        if (((OEntityEnderman)getEntity()).canHoldItem(block.getType()))
        {
            ((OEntityEnderman)getEntity()).b(block.getType());
            ((OEntityEnderman)getEntity()).d(block.getData());
        }
        return false;
    }
    
    /**
     *  @param blockID - the block ID to check if the enderman can hold
     * @return True if the enderman can hold the block or not.
     */
    public void getHoldable(int blockID)
    {
        ((OEntityEnderman)getEntity()).getHoldable(blockID);
    }
    
    /**
     *  Allows or prevents the enderman from picking up a specific block
     *  @param blockID - the block to allow or prevent the enderman from picking up.
     */
    public void setHoldable(int blockID, boolean holdable)
    {
        ((OEntityEnderman)getEntity()).setHoldable(blockID, holdable);
    }

}
