import java.util.List;

public class OEntityBoat extends OEntity {
    public int     a = 0;
    public int     b = 0;
    public int     c = 1;
    private int    d;
    private double e;
    private double f;
    private double g;
    private double h;
    private double i;
    // CanaryMod Start
    Boat           boat = new Boat(this);

    // CanaryMod end

    public OEntityBoat(OWorld paramOWorld) {
        super(paramOWorld);
        aE = true;
        b(1.5F, 0.6F);
        be = (bg / 2.0F);
    }

    @Override
    protected boolean n() {
        return false;
    }

    @Override
    protected void b() {
    }

    @Override
    public OAxisAlignedBB a_(OEntity paramOEntity) {
        return paramOEntity.aV;
    }

    @Override
    public OAxisAlignedBB e_() {
        return aV;
    }

    @Override
    public boolean d_() {
        return true;
    }

    public OEntityBoat(OWorld paramOWorld, double paramDouble1, double paramDouble2, double paramDouble3) {
        this(paramOWorld);
        a(paramDouble1, paramDouble2 + be, paramDouble3);

        aO = 0.0D;
        aP = 0.0D;
        aQ = 0.0D;

        aI = paramDouble1;
        aJ = paramDouble2;
        aK = paramDouble3;
        
        // CanaryMod: Creation of the boat
        manager.callHook(PluginLoader.Hook.VEHICLE_CREATE, boat);
    }

    @Override
    public double m() {
        return bg * 0.0D - 0.3D;
    }

    @Override
    public boolean a(OEntity paramOEntity, int paramInt) {
        // CanaryMod: Attack of the boat
        if ((Boolean) manager.callHook(PluginLoader.Hook.VEHICLE_DAMAGE, boat, paramOEntity == null ? null : paramOEntity.entity, paramInt))
            return true;


        
        if ((aH.v) || (bd))
            return true;
        c = (-c);
        b = 10;
        a += paramInt * 10;
        ab();
        if (a > 40) {
            for (int j = 0; j < 3; j++)
                a(OBlock.x.bl, 1, 0.0F);
            for (int j = 0; j < 2; j++)
                a(OItem.B.bd, 1, 0.0F);
            G();
        }
        return true;
    }

    @Override
    public boolean o_() {
        return !bd;
    }

    @Override
    public void p_() {
        super.p_();
        // CanaryMod: Update of the boat
        manager.callHook(PluginLoader.Hook.VEHICLE_UPDATE, boat);

        double prevX = aL;
        double prevY = aM;
        double prevZ = aN;
        
        if (b > 0)
            b -= 1;
        if (a > 0)
            a -= 1;
        aI = aL;
        aJ = aM;
        aK = aN;

        int j = 5;
        double d1 = 0.0D;
        for (int k = 0; k < j; k++) {
            double d2 = aV.b + (aV.e - aV.b) * (k + 0) / j - 0.125D;
            double d3 = aV.b + (aV.e - aV.b) * (k + 1) / j - 0.125D;
            OAxisAlignedBB localOAxisAlignedBB = OAxisAlignedBB.b(aV.a, d2, aV.c, aV.d, d3, aV.f);
            if (aH.b(localOAxisAlignedBB, OMaterial.f))
                d1 += 1.0D / j;
        }

        if (aH.v) {
            if (d > 0) {
                double d4 = aL + (e - aL) / d;
                double d5 = aM + (f - aM) / d;
                double d6 = aN + (g - aN) / d;

                double d7 = h - aR;
                while (d7 < -180.0D)
                    d7 += 360.0D;
                while (d7 >= 180.0D)
                    d7 -= 360.0D;
                aR = (float) (aR + d7 / d);
                aS = (float) (aS + (i - aS) / d);

                d -= 1;
                a(d4, d5, d6);
                c(aR, aS);
            } else {
                double d4 = aL + aO;
                double d5 = aM + aP;
                double d6 = aN + aQ;
                a(d4, d5, d6);
                if (aW) {
                    aO *= 0.5D;
                    aP *= 0.5D;
                    aQ *= 0.5D;
                }
                aO *= 0.99D;
                aP *= 0.95D;
                aQ *= 0.99D;
            }
            return;
        }

        double d4 = d1 * 2.0D - 1.0D;
        aP += 0.04D * d4;

        if (aF != null) {
            aO += aF.aO * 0.2D;
            aQ += aF.aQ * 0.2D;
        }

        double d5 = 0.4D;

        if (aO < -d5)
            aO = (-d5);
        if (aO > d5)
            aO = d5;
        if (aQ < -d5)
            aQ = (-d5);
        if (aQ > d5)
            aQ = d5;
        if (aW) {
            aO *= 0.5D;
            aP *= 0.5D;
            aQ *= 0.5D;
        }
        c(aO, aP, aQ);
        double d6 = Math.sqrt(aO * aO + aQ * aQ);
        if (d6 > 0.15D) {
            double d7 = Math.cos(aR * 3.141592653589793D / 180.0D);
            double d8 = Math.sin(aR * 3.141592653589793D / 180.0D);

            for (int m = 0; m < 1.0D + d6 * 60.0D; m++) {
                double d9 = br.nextFloat() * 2.0F - 1.0F;

                double d10 = (br.nextInt(2) * 2 - 1) * 0.7D;
                double d11;
                double d12;
                if (br.nextBoolean()) {
                    d11 = aL - d7 * d9 * 0.8D + d8 * d10;
                    d12 = aN - d8 * d9 * 0.8D - d7 * d10;
                    aH.a("splash", d11, aM - 0.125D, d12, aO, aP, aQ);
                } else {
                    d11 = aL + d7 + d8 * d9 * 0.7D;
                    d12 = aN + d8 - d7 * d9 * 0.7D;
                    aH.a("splash", d11, aM - 0.125D, d12, aO, aP, aQ);
                }
            }
        }

        if ((aX) && (d6 > 0.15D)) {
            if (!aH.v) {
                G();
                for (int n = 0; n < 3; n++)
                    a(OBlock.x.bl, 1, 0.0F);
                for (int n = 0; n < 2; n++)
                    a(OItem.B.bd, 1, 0.0F);
            }
        } else {
            aO *= 0.99D;
            aP *= 0.95D;
            aQ *= 0.99D;
        }

        aS = 0.0F;
        double d7 = aR;
        double d8 = aI - aL;
        double d13 = aK - aN;
        if (d8 * d8 + d13 * d13 > 0.001D)
            d7 = (float) (Math.atan2(d13, d8) * 180.0D / 3.141592653589793D);

        double d14 = d7 - aR;
        while (d14 >= 180.0D)
            d14 -= 360.0D;
        while (d14 < -180.0D)
            d14 += 360.0D;
        if (d14 > 20.0D)
            d14 = 20.0D;
        if (d14 < -20.0D)
            d14 = -20.0D;

        aR = (float) (aR + d14);
        c(aR, aS);

        // CanaryMod: Change of the boat
        if (aL != prevX || aM != prevY || aN != prevZ)
            manager.callHook(PluginLoader.Hook.VEHICLE_POSITIONCHANGE, boat, (int) aL, (int) aM, (int) aN);

        
        List localList = aH.b(this, aV.b(0.2D, 0.0D, 0.2D));
        if ((localList != null) && (localList.size() > 0))
            for (int i1 = 0; i1 < localList.size(); i1++) {
                OEntity localOEntity = (OEntity) localList.get(i1);
                if ((localOEntity != aF) && (localOEntity.d_()) && ((localOEntity instanceof OEntityBoat)))
                    localOEntity.h(this);
            }

        if ((aF != null) && (aF.bd))
            aF = null;
    }

    @Override
    public void f() {
        if (aF == null)
            return;

        double d1 = Math.cos(aR * 3.141592653589793D / 180.0D) * 0.4D;
        double d2 = Math.sin(aR * 3.141592653589793D / 180.0D) * 0.4D;
        aF.a(aL + d1, aM + m() + aF.F(), aN + d2);
    }

    @Override
    protected void b(ONBTTagCompound paramONBTTagCompound) {
    }

    @Override
    protected void a(ONBTTagCompound paramONBTTagCompound) {
    }

    @Override
    public boolean a(OEntityPlayer paramOEntityPlayer) {
        // CanaryMod: Entering the boat
        manager.callHook(PluginLoader.Hook.VEHICLE_ENTERED, boat, paramOEntityPlayer.entity);

        
        if ((aF != null) && ((aF instanceof OEntityPlayer)) && (aF != paramOEntityPlayer))
            return true;
        if (!aH.v)
            paramOEntityPlayer.b(this);
        return true;
    }
}
