public class OItemSign extends OItem {

    public OItemSign(int paramInt) {
        super(paramInt);
        bf = 1;
    }

    @Override
    public boolean a(OItemStack paramOItemStack, OEntityPlayer paramOEntityPlayer, OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if (paramInt4 == 0)
            return false;
        if (!paramOWorld.c(paramInt1, paramInt2, paramInt3).a())
            return false;

        // CanaryMod: Store block data clicked
        Block blockClicked = new Block(paramOWorld.world, paramOWorld.a(paramInt1, paramInt2, paramInt3), paramInt1, paramInt2, paramInt3);
        blockClicked.setFaceClicked(Block.Face.fromId(paramInt4));

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

        if (!OBlock.aE.a(paramOWorld, paramInt1, paramInt2, paramInt3))
            return false;

        // CanaryMod: Now we can call itemUse :)
        Block blockPlaced = new Block(paramOWorld.world, (paramInt4 == 1 ? OBlock.aE.bn : OBlock.aJ.bn), paramInt1, paramInt2, paramInt3);
        if (paramOEntityPlayer instanceof OEntityPlayerMP && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) paramOEntityPlayer).getPlayer(), blockPlaced, blockClicked, new Item(paramOItemStack)))
            return false;

        if (paramInt4 == 1)
            paramOWorld.b(paramInt1, paramInt2, paramInt3, OBlock.aE.bn, OMathHelper.b((paramOEntityPlayer.aV + 180.0F) * 16.0F / 360.0F + 0.5D) & 0xF);
        else
            paramOWorld.b(paramInt1, paramInt2, paramInt3, OBlock.aJ.bn, paramInt4);

        paramOItemStack.a -= 1;
        OTileEntitySign localOTileEntitySign = (OTileEntitySign) paramOWorld.n(paramInt1, paramInt2, paramInt3);
        if (localOTileEntitySign != null)
            paramOEntityPlayer.a(localOTileEntitySign);
        return true;
    }
}
