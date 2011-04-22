public class OItemHoe extends OItem {

    public OItemHoe(int paramInt, OEnumToolMaterial paramOEnumToolMaterial) {
        super(paramInt);
        be = 1;
        d(paramOEnumToolMaterial.a());
    }

    @Override
    public boolean a(OItemStack paramOItemStack, OEntityPlayer paramOEntityPlayer, OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = paramOWorld.a(paramInt1, paramInt2, paramInt3);
        OMaterial localOMaterial = paramOWorld.c(paramInt1, paramInt2 + 1, paramInt3);

        if (((!localOMaterial.a()) && (i == OBlock.u.bl)) || (i == OBlock.v.bl)) {
            // CanaryMod: Hoes
            Block blockClicked = new Block(i, paramInt1, paramInt2, paramInt3);
            blockClicked.setFaceClicked(Block.Face.fromId(paramInt4));
            Block blockPlaced = new Block(paramOWorld.a(paramInt1, paramInt2 + 1, paramInt3), paramInt1, paramInt2 + 1, paramInt3);

            // Call the hook
            if (paramOEntityPlayer instanceof OEntityPlayerMP) {
                Player player = ((OEntityPlayerMP) paramOEntityPlayer).getPlayer();
                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, player, blockPlaced, blockClicked, new Item(paramOItemStack)))
                    return false;
            }

            OBlock localOBlock = OBlock.aA;
            paramOWorld.a(paramInt1 + 0.5F, paramInt2 + 0.5F, paramInt3 + 0.5F, localOBlock.bw.c(), (localOBlock.bw.a() + 1.0F) / 2.0F, localOBlock.bw.b() * 0.8F);

            if (paramOWorld.v)
                return true;
            paramOWorld.e(paramInt1, paramInt2, paramInt3, localOBlock.bl);
            paramOItemStack.a(1, paramOEntityPlayer);

            if ((paramOWorld.m.nextInt(8) == 0) && (i == OBlock.u.bl)) {
                int j = 1;
                for (int k = 0; k < j; k++) {
                    float f1 = 0.7F;
                    float f2 = paramOWorld.m.nextFloat() * f1 + (1.0F - f1) * 0.5F;
                    float f3 = 1.2F;
                    float f4 = paramOWorld.m.nextFloat() * f1 + (1.0F - f1) * 0.5F;
                    OEntityItem localOEntityItem = new OEntityItem(paramOWorld, paramInt1 + f2, paramInt2 + f3, paramInt3 + f4, new OItemStack(OItem.Q));
                    localOEntityItem.c = 10;
                    paramOWorld.b(localOEntityItem);
                }
            }

            return true;
        }

        return false;
    }
}
