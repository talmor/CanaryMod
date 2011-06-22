
public class OItemBoat extends OItem {

    public OItemBoat(int paramInt) {
        super(paramInt);
        bf = 1;
    }

    @Override
    public OItemStack a(OItemStack paramOItemStack, OWorld paramOWorld, OEntityPlayer paramOEntityPlayer) {
        float f1 = 1.0F;

        float f2 = paramOEntityPlayer.aY + (paramOEntityPlayer.aW - paramOEntityPlayer.aY) * f1;
        float f3 = paramOEntityPlayer.aX + (paramOEntityPlayer.aV - paramOEntityPlayer.aX) * f1;


        double d1 = paramOEntityPlayer.aM + (paramOEntityPlayer.aP - paramOEntityPlayer.aM) * f1;
        double d2 = paramOEntityPlayer.aN + (paramOEntityPlayer.aQ - paramOEntityPlayer.aN) * f1 + 1.62D - paramOEntityPlayer.bi;
        double d3 = paramOEntityPlayer.aO + (paramOEntityPlayer.aR - paramOEntityPlayer.aO) * f1;


        OVec3D localOVec3D1 = OVec3D.b(d1, d2, d3);

        float f4 = OMathHelper.b(-f3 * 0.01745329F - 3.141593F);
        float f5 = OMathHelper.a(-f3 * 0.01745329F - 3.141593F);
        float f6 = -OMathHelper.b(-f2 * 0.01745329F);
        float f7 = OMathHelper.a(-f2 * 0.01745329F);

        float f8 = f5 * f6;
        float f9 = f7;
        float f10 = f4 * f6;

        double d4 = 5.0D;
        OVec3D localOVec3D2 = localOVec3D1.c(f8 * d4, f9 * d4, f10 * d4);
        OMovingObjectPosition localOMovingObjectPosition = paramOWorld.a(localOVec3D1, localOVec3D2, true);
        if (localOMovingObjectPosition == null)
            return paramOItemStack;

        if (localOMovingObjectPosition.a == OEnumMovingObjectType.a) {
            int i = localOMovingObjectPosition.b;
            int j = localOMovingObjectPosition.c;
            int k = localOMovingObjectPosition.d;

            if (!paramOWorld.B) {
                if (paramOWorld.a(i, j, k) == OBlock.aT.bn)
                    j--;
                // CanaryMod: placing of a boat
                Block blockClicked = new Block(paramOWorld.world, paramOWorld.a(i, j, k), i, j, k);
                blockClicked.setFaceClicked(Block.Face.fromId(localOMovingObjectPosition.e));
                Block blockPlaced = new Block(paramOWorld.world, 0, i, j, k);
                // CanaryMod: Call hook
                if (paramOEntityPlayer instanceof OEntityPlayerMP && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) paramOEntityPlayer).getPlayer(), blockPlaced, blockClicked, new Item(paramOItemStack)))
                    return paramOItemStack;
                paramOWorld.b(new OEntityBoat(paramOWorld, i + 0.5F, j + 1.5F, k + 0.5F));
            }
            paramOItemStack.a -= 1;
        }

        return paramOItemStack;
    }
}
