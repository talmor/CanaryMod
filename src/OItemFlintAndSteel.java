public class OItemFlintAndSteel extends OItem {

    public OItemFlintAndSteel(int paramInt) {
        super(paramInt);
        bf = 1;
        d(64);
    }

    @Override
    public boolean a(OItemStack paramOItemStack, OEntityPlayer paramOEntityPlayer, OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        // CanaryMod: Store block data clicked
        Block blockClicked = new Block(paramOWorld.world, paramOWorld.a(paramInt1, paramInt2, paramInt3), paramInt1, paramInt2, paramInt3);
        blockClicked.setFaceClicked(Block.Face.fromId(paramInt4));

        if (paramInt4 == 0)
            paramInt2--;
        if (paramInt4 == 1)
            paramInt2++;
        if (paramInt4 == 2)
            paramInt3--;
        if (paramInt4 == 3)
            paramInt3++;
        if (paramInt4 == 4)
            paramInt1--;
        if (paramInt4 == 5)
            paramInt1++;

        int i = paramOWorld.a(paramInt1, paramInt2, paramInt3);

        if (i == 0) {
            // CanaryMod: Hook to control ignites AND ligther use
            Block blockPlaced = new Block(paramOWorld.world, Block.Type.Fire.getType(), paramInt1, paramInt2, paramInt3);
            Player player = ((OEntityPlayerMP) paramOEntityPlayer).getPlayer();

            Boolean preventLighter = (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, player, blockPlaced, blockClicked, new Item(paramOItemStack));
            blockPlaced.setStatus(2); // Specificly to mark this ignite as from
            // a lighter
            Boolean preventIgnite = (Boolean) etc.getLoader().callHook(PluginLoader.Hook.IGNITE, blockPlaced, player);
            if (preventIgnite || preventLighter)
                return false;
            else {
                paramOWorld.a(paramInt1 + 0.5D, paramInt2 + 0.5D, paramInt3 + 0.5D, "fire.ignite", 1.0F, b.nextFloat() * 0.4F + 0.8F);
                paramOWorld.e(paramInt1, paramInt2, paramInt3, OBlock.as.bn);
            }
        }

        paramOItemStack.a(1, paramOEntityPlayer);
        return true;
    }
}
