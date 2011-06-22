
public class OItemBlock extends OItem {

    private int a;

    public OItemBlock(int paramInt) {
        super(paramInt);
        a = (paramInt + 256);
        b(OBlock.m[(paramInt + 256)].a(2));
    }

    @Override
    public boolean a(OItemStack paramOItemStack, OEntityPlayer paramOEntityPlayer, OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        // CanaryMod: Bail if we have nothing of the items in hand
        if (paramOItemStack.a == 0)
            return false;
        // CanaryMod: Store blockInfo of the one we clicked
        int blockClickedId = paramOWorld.a(paramInt1, paramInt2, paramInt3);
        Block blockClicked = new Block(paramOWorld.world, blockClickedId, paramInt1, paramInt2, paramInt3);

        if (paramOWorld.a(paramInt1, paramInt2, paramInt3) == OBlock.aT.bn)
            paramInt4 = 0;
        else {
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
        }

        if (paramOItemStack.a == 0)
            return false;

        // CanaryMod: Store faceClicked (must be here to have the 'snow' special
        // case).
        blockClicked.setFaceClicked(Block.Face.fromId(paramInt4));

        // CanaryMod: And the block we're about to place
        Block blockPlaced = new Block(paramOWorld.world, a, paramInt1, paramInt2, paramInt3);

        // CanaryMod Store all the old settings 'externally' in case someone changes
        // blockPlaced.
        int oldMaterial = paramOWorld.a(paramInt1, paramInt2, paramInt3);
        int oldData = paramOWorld.b(paramInt1, paramInt2, paramInt3);

        if (paramInt2 == 127 && OBlock.m[a].bA.a())
            return false;
        if (paramOWorld.a(a, paramInt1, paramInt2, paramInt3, false, paramInt4)) {
            OBlock localOBlock = OBlock.m[a];
            // CanaryMod: take over block placement
            if (paramOWorld.b(paramInt1, paramInt2, paramInt3, a, a(paramOItemStack.h())))
                // CanaryMod: Check if this was playerPlaced and call the hook
                if (paramOEntityPlayer instanceof OEntityPlayerMP && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PLACE, ((OEntityPlayerMP) paramOEntityPlayer).getPlayer(), blockPlaced, blockClicked, new Item(paramOItemStack))) {
                    // CanaryMod: Undo!

                    // Specialcase iceblocks, replace with 'glass' first (so it
                    // doesnt explode into water)
                    if (a == 79)
                        paramOWorld.b(paramInt1, paramInt2, paramInt3, 20);
                    paramOWorld.b(paramInt1, paramInt2, paramInt3, oldMaterial);
                    paramOWorld.d(paramInt1, paramInt2, paramInt3, oldData);

                    // CanaryMod: Refund the item the player lost >.>
                    // or not, this occasionally dupes items! we'lm do this when
                    // notch implements serverside invs.
                    // ((fi)paramgp).a.b(new fh(paramhn, 1));
                    return false;
                } else {
                    OBlock.m[a].d(paramOWorld, paramInt1, paramInt2, paramInt3, paramInt4);
                    OBlock.m[a].a(paramOWorld, paramInt1, paramInt2, paramInt3, (OEntityLiving) paramOEntityPlayer);
                    paramOWorld.a(paramInt1 + 0.5F, paramInt2 + 0.5F, paramInt3 + 0.5F, localOBlock.by.c(), (localOBlock.by.a() + 1.0F) / 2.0F, localOBlock.by.b() * 0.8F);
                    paramOItemStack.a -= 1;
                }

        }

        return true;
    }

    @Override
    public String a() {
        return OBlock.m[a].h();
    }
}
