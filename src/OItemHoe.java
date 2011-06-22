public class OItemHoe extends OItem {

    public OItemHoe(int paramInt, OEnumToolMaterial paramOEnumToolMaterial) {
        super(paramInt);
        bf = 1;
        d(paramOEnumToolMaterial.a());
    }

    @Override
    public boolean a(OItemStack paramOItemStack, OEntityPlayer paramOEntityPlayer, OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = paramOWorld.a(paramInt1, paramInt2, paramInt3);
        int i2 = paramOWorld.a(paramInt1, paramInt2 + 1, paramInt3);

        if (((paramInt4 != 0) && i2 == 0 && (i == OBlock.v.bn)) || (i == OBlock.w.bn)) {
            // CanaryMod: Hoes
            Block blockClicked = new Block(paramOWorld.world, i, paramInt1, paramInt2, paramInt3);
            blockClicked.setFaceClicked(Block.Face.fromId(paramInt4));
            Block blockPlaced = new Block(paramOWorld.world, paramOWorld.a(paramInt1, paramInt2 + 1, paramInt3), paramInt1, paramInt2 + 1, paramInt3);

            // Call the hook
            if (paramOEntityPlayer instanceof OEntityPlayerMP) {
                Player player = ((OEntityPlayerMP) paramOEntityPlayer).getPlayer();
                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, player, blockPlaced, blockClicked, new Item(paramOItemStack)))
                    return false;
            }

            OBlock localOBlock = OBlock.aB;
            paramOWorld.a(paramInt1 + 0.5F, paramInt2 + 0.5F, paramInt3 + 0.5F, localOBlock.by.c(), (localOBlock.by.a() + 1.0F) / 2.0F, localOBlock.by.b() * 0.8F);

            if (paramOWorld.B)
                return true;
            paramOWorld.e(paramInt1, paramInt2, paramInt3, localOBlock.bn);
            paramOItemStack.a(1, paramOEntityPlayer);

            return true;
        }

        return false;
    }
}
