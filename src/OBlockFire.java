import java.util.Random;

public class OBlockFire extends OBlock {
    private int[] a = new int[256];
    private int[] b = new int[256];

    protected OBlockFire(int paramInt1, int paramInt2) {
        super(paramInt1, paramInt2, OMaterial.m);
        a(true);
    }

    @Override
    public void f() {
        this.a(OBlock.y.bn, 5, 20);
        this.a(OBlock.ba.bn, 5, 20);
        this.a(OBlock.au.bn, 5, 20);
        this.a(OBlock.K.bn, 5, 5);
        this.a(OBlock.L.bn, 30, 60);
        this.a(OBlock.ao.bn, 30, 20);
        this.a(OBlock.an.bn, 15, 100);
        this.a(OBlock.Y.bn, 60, 100);
        this.a(OBlock.ac.bn, 30, 60);
    }

    private void a(int paramInt1, int paramInt2, int paramInt3) {
        a[paramInt1] = paramInt2;
        b[paramInt1] = paramInt3;
    }

    @Override
    public OAxisAlignedBB d(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        return null;
    }

    @Override
    public boolean b() {
        return false;
    }
    
    @Override
    public boolean a() {
        return false;
    }

    @Override
    public int a(Random paramRandom) {
        return 0;
    }

    @Override
    public int c() {
        return 40;
    }

    @Override
    public void a(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, Random paramRandom) {
        boolean i = paramOWorld.a(paramInt1, paramInt2 - 1, paramInt3) == OBlock.bc.bn;
        if (!a(paramOWorld, paramInt1, paramInt2, paramInt3))
            paramOWorld.e(paramInt1, paramInt2, paramInt3, 0);
        if(!i && paramOWorld.v() && (paramOWorld.s(paramInt1, paramInt2, paramInt3) || paramOWorld.s(paramInt1 - 1, paramInt2, paramInt3) || paramOWorld.s(paramInt1 + 1, paramInt2, paramInt3) || paramOWorld.s(paramInt1, paramInt2, paramInt3 - 1) || paramOWorld.s(paramInt1, paramInt2, paramInt3 + 1))) {
            paramOWorld.e(paramInt1, paramInt2, paramInt3, 0);
            return;
        }

        int j = paramOWorld.b(paramInt1, paramInt2, paramInt3);
        if (j < 15) {
            paramOWorld.d(paramInt1, paramInt2, paramInt3, j + paramRandom.nextInt(3) / 2);
        }
        paramOWorld.c(paramInt1, paramInt2, paramInt3, bn, c());
        if (!i && (!g(paramOWorld, paramInt1, paramInt2, paramInt3))) {
            if ((!paramOWorld.d(paramInt1, paramInt2 - 1, paramInt3)) || (j > 3))
                paramOWorld.e(paramInt1, paramInt2, paramInt3, 0);
            return;
        }

        if (!i && (!b((OIBlockAccess)paramOWorld, paramInt1, paramInt2 - 1, paramInt3)) && (j == 15) && (paramRandom.nextInt(4) == 0)) {
            paramOWorld.e(paramInt1, paramInt2, paramInt3, 0);
            return;
        }

        a(paramOWorld, paramInt1 + 1, paramInt2, paramInt3, 300, paramRandom, j);
        a(paramOWorld, paramInt1 - 1, paramInt2, paramInt3, 300, paramRandom, j);
        a(paramOWorld, paramInt1, paramInt2 - 1, paramInt3, 250, paramRandom, j);
        a(paramOWorld, paramInt1, paramInt2 + 1, paramInt3, 250, paramRandom, j);
        a(paramOWorld, paramInt1, paramInt2, paramInt3 - 1, 300, paramRandom, j);
        a(paramOWorld, paramInt1, paramInt2, paramInt3 + 1, 300, paramRandom, j);

        for (int k = paramInt1 - 1; k <= paramInt1 + 1; k++)
            for (int m = paramInt3 - 1; m <= paramInt3 + 1; m++)
                for (int n = paramInt2 - 1; n <= paramInt2 + 4; n++) {
                    if ((k == paramInt1) && (n == paramInt2) && (m == paramInt3))
                        continue;
                    int i1 = 100;
                    if (n > paramInt2 + 1)
                        i1 += (n - (paramInt2 + 1)) * 100;

                    int i2 = h(paramOWorld, k, n, m);
                    if (i2 > 0) {
                        int i3 = (i2 + 40) / (j + 30);
                        if (i3 <= 0 || (paramRandom.nextInt(i1) > i2) || ((paramOWorld.v()) && (paramOWorld.s(k, n, m))) || (paramOWorld.s(k - 1, n, paramInt3)) || (paramOWorld.s(k + 1, n, m)) || (paramOWorld.s(k, n, m - 1)) || (paramOWorld.s(k, n, m + 1)))
                            continue;
                        int var14 = j + paramRandom.nextInt(5) / 4;
                        if (var14 > 15)
                            var14 = 15;

                        // CanaryMod: dynamic spreading of fire.{
                        // avg call amount per placed block of fire ~ 4
                        Block block = new Block(paramOWorld.world, paramOWorld.a(k, n, m), k, n, m);
                        block.setStatus(3);
                        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.IGNITE, block, null))
                            paramOWorld.b(k, n, m, bn, var14);
                    }
                }
    }

    private void a(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, Random paramRandom, int paramInt5) {
        int i = b[paramOWorld.a(paramInt1, paramInt2, paramInt3)];
        if (paramRandom.nextInt(paramInt4) < i) {
            int j = paramOWorld.a(paramInt1, paramInt2, paramInt3) == OBlock.an.bn ? 1 : 0;
            if (paramRandom.nextInt(paramInt5 + 10) < 5 && !paramOWorld.s(paramInt1, paramInt2, paramInt3)) {
                int k = paramInt5 + paramRandom.nextInt(5) / 4;
                if (k > 15)
                    k = 15;

                // CanaryMod: VERY SLOW dynamic spreading of fire.
                Block block = new Block(paramOWorld.world, paramOWorld.a(paramInt1, paramInt2, paramInt3), paramInt1, paramInt2, paramInt3);
                block.setStatus(3);
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.IGNITE, block, null))
                    paramOWorld.b(paramInt1, paramInt2, paramInt3, bn, k);
            } else {
                // CanaryMod: fire destroying a block.
                Block block = new Block(paramOWorld.world, paramOWorld.a(paramInt1, paramInt2, paramInt3), paramInt1, paramInt2, paramInt3);
                block.setStatus(4);
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.IGNITE, block, null))
                    paramOWorld.e(paramInt1, paramInt2, paramInt3, 0);
            }
            if (j != 0)
                OBlock.an.b(paramOWorld, paramInt1, paramInt2, paramInt3, 0);
        }
    }

    private boolean g(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        // CanaryMod: cast down to fix decompiler error.(6 times)
        if (b((OIBlockAccess)paramOWorld, paramInt1 + 1, paramInt2, paramInt3))
            return true;
        if (b((OIBlockAccess)paramOWorld, paramInt1 - 1, paramInt2, paramInt3))
            return true;
        if (b((OIBlockAccess)paramOWorld, paramInt1, paramInt2 - 1, paramInt3))
            return true;
        if (b((OIBlockAccess)paramOWorld, paramInt1, paramInt2 + 1, paramInt3))
            return true;
        if (b((OIBlockAccess)paramOWorld, paramInt1, paramInt2, paramInt3 - 1))
            return true;
        return b((OIBlockAccess)paramOWorld, paramInt1, paramInt2, paramInt3 + 1);
    }

    private int h(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        int i = 0;
        if (!paramOWorld.e(paramInt1, paramInt2, paramInt3))
            return 0;

        i = g(paramOWorld, paramInt1 + 1, paramInt2, paramInt3, i);
        i = g(paramOWorld, paramInt1 - 1, paramInt2, paramInt3, i);
        i = g(paramOWorld, paramInt1, paramInt2 - 1, paramInt3, i);
        i = g(paramOWorld, paramInt1, paramInt2 + 1, paramInt3, i);
        i = g(paramOWorld, paramInt1, paramInt2, paramInt3 - 1, i);
        i = g(paramOWorld, paramInt1, paramInt2, paramInt3 + 1, i);

        return i;
    }

    @Override
    public boolean m_() {
        return false;
    }

    public boolean b(OIBlockAccess paramOIBlockAccess, int paramInt1, int paramInt2, int paramInt3) {
        return a[paramOIBlockAccess.a(paramInt1, paramInt2, paramInt3)] > 0;
    }

    public int g(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = a[paramOWorld.a(paramInt1, paramInt2, paramInt3)];
        if (i > paramInt4)
            return i;
        return paramInt4;
    }

    @Override
    public boolean a(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        return (paramOWorld.d(paramInt1, paramInt2 - 1, paramInt3)) || (g(paramOWorld, paramInt1, paramInt2, paramInt3));
    }

    @Override
    public void a(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if ((!paramOWorld.d(paramInt1, paramInt2 - 1, paramInt3)) && (!g(paramOWorld, paramInt1, paramInt2, paramInt3))) {
            paramOWorld.e(paramInt1, paramInt2, paramInt3, 0);
            return;
        }
    }

    @Override
    public void e(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
        if ((paramOWorld.a(paramInt1, paramInt2 - 1, paramInt3) == OBlock.aq.bn) && (OBlock.bf.a_(paramOWorld, paramInt1, paramInt2, paramInt3)))
            return;

        if ((!paramOWorld.d(paramInt1, paramInt2 - 1, paramInt3)) && (!g(paramOWorld, paramInt1, paramInt2, paramInt3))) {
            paramOWorld.e(paramInt1, paramInt2, paramInt3, 0);
            return;
        }
        paramOWorld.c(paramInt1, paramInt2, paramInt3, bn, c());
    }
}
