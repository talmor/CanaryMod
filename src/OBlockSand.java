import java.util.Random;

public class OBlockSand extends OBlock {

    public static boolean a = false;

    public OBlockSand(int paramInt1, int paramInt2) {
        super(paramInt1, paramInt2, OMaterial.n);
    }

    @Override
    public void e(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        // CanaryMod: Physics
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PHYSICS, new Block(paramOWorld.world, bn, paramInt1, paramInt2, paramInt3), true))
            paramOWorld.c(paramInt1, paramInt2, paramInt3, bn, c());
    }

    @Override
    public void a(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        // CanaryMod: Physics
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PHYSICS, new Block(paramOWorld.world, bn, paramInt1, paramInt2, paramInt3), true))
            paramOWorld.c(paramInt1, paramInt2, paramInt3, bn, c());
    }

    @Override
    public void a(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        g(paramOWorld, paramInt1, paramInt2, paramInt3);
    }

    private void g(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramInt1;
        int j = paramInt2;
        int k = paramInt3;
        if ((c_(paramOWorld, i, j - 1, k)) && (j >= 0)) {
            int m = 32;
            if ((a) || (!paramOWorld.a(paramInt1 - m, paramInt2 - m, paramInt3 - m, paramInt1 + m, paramInt2 + m, paramInt3 + m))) {
                paramOWorld.e(paramInt1, paramInt2, paramInt3, 0);
                while ((c_(paramOWorld, paramInt1, paramInt2 - 1, paramInt3)) && (paramInt2 > 0))
                    paramInt2--;
                if (paramInt2 > 0)
                    paramOWorld.e(paramInt1, paramInt2, paramInt3, bn);
            } else {
                OEntityFallingSand localOEntityFallingSand = new OEntityFallingSand(paramOWorld, paramInt1 + 0.5F, paramInt2 + 0.5F, paramInt3 + 0.5F, bn);
                paramOWorld.b(localOEntityFallingSand);
            }
        }
    }

    @Override
    public int c() {
        return 3;
    }

    public static boolean c_(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        int i = paramOWorld.a(paramInt1, paramInt2, paramInt3);
        if (i == 0)
            return true;
        if (i == OBlock.as.bn)
            return true;
        OMaterial localOMaterial = OBlock.m[i].bA;
        if (localOMaterial == OMaterial.g)
            return true;
        return localOMaterial == OMaterial.h;
    }
}
