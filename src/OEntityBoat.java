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
        aI = true;
        b(1.5F, 0.6F);
        bi = (bk / 2.0F);
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
        return paramOEntity.aZ;
    }

    @Override
    public OAxisAlignedBB e_() {
        return aZ;
    }

    @Override
    public boolean d_() {
        return true;
    }

    public OEntityBoat(OWorld paramOWorld, double paramDouble1, double paramDouble2, double paramDouble3) {
        this(paramOWorld);
        a(paramDouble1, paramDouble2 + bi, paramDouble3);

        aS = 0.0D;
        aT = 0.0D;
        aU = 0.0D;

        aM = paramDouble1;
        aN = paramDouble2;
        aO = paramDouble3;
        
        // CanaryMod: Creation of the boat
        manager.callHook(PluginLoader.Hook.VEHICLE_CREATE, boat);
    }

    @Override
    public double m() {
        return bk * 0.0D - 0.3D;
    }

    @Override
    public boolean a(OEntity paramOEntity, int paramInt) {
        // CanaryMod: Attack of the boat
        if ((Boolean) manager.callHook(PluginLoader.Hook.VEHICLE_DAMAGE, boat, paramOEntity == null ? null : paramOEntity.entity, paramInt))
            return true;


        
        if ((aL.B) || (bh))
            return true;
        c = (-c);
        b = 10;
        a += paramInt * 10;
        ae();
        if (a > 40) {
            if (aJ != null)
                aJ.b((OEntity) this);

            for (int j = 0; j < 3; j++)
                a(OBlock.y.bn, 1, 0.0F);
            for (int j = 0; j < 2; j++)
                a(OItem.B.be, 1, 0.0F);
            I();
        }
        return true;
    }

    @Override
    public boolean n_() {
        return !bh;
    }

    @Override
    public void o_() {
        super.o_();
        // CanaryMod: Update of the boat
        manager.callHook(PluginLoader.Hook.VEHICLE_UPDATE, boat);

        double prevX = aP;
        double prevY = aQ;
        double prevZ = aR;
        
        if (b > 0)
            b -= 1;
        if (a > 0)
            a -= 1;
        aM = aP;
        aN = aQ;
        aO = aR;

        int j = 5;
        double d1 = 0.0D;
        for (int k = 0; k < j; k++) {
            double d2 = aZ.b + (aZ.e - aZ.b) * (k + 0) / j - 0.125D;
            double d3 = aZ.b + (aZ.e - aZ.b) * (k + 1) / j - 0.125D;
            OAxisAlignedBB localOAxisAlignedBB = OAxisAlignedBB.b(aZ.a, d2, aZ.c, aZ.d, d3, aZ.f);
            if (aL.b(localOAxisAlignedBB, OMaterial.g))
                d1 += 1.0D / j;
        }

        if (aL.B) {
            if (d > 0) {
                double d4 = aM + (e - aP) / d;
                double d5 = aN + (f - aQ) / d;
                double d6 = aO + (g - aR) / d;

                double d7 = h - aV;
                while (d7 < -180.0D)
                    d7 += 360.0D;
                while (d7 >= 180.0D)
                    d7 -= 360.0D;
                aV = (float) (aV + d7 / d);
                aW = (float) (aW + (i - aW) / d);

                d -= 1;
                a(d4, d5, d6);
                c(aV, aW);
            } else {
                double d4 = aP + aS;
                double d5 = aQ + aT;
                double d6 = aR + aU;
                a(d4, d5, d6);
                if (ba) {
                    aS *= 0.5D;
                    aT *= 0.5D;
                    aU *= 0.5D;
                }
                aS *= 0.99D;
                aT *= 0.95D;
                aU *= 0.99D;
            }
            return;
        }

        if (d1 < 1.0D) {
            double d4 = d1 * 2.0D - 1.0D;
            aT += 0.04D * d4;
        } else {
            if (aT < 0.0D)
                aT /= 2.0D;
            
            aT += 0.007D;
        }
        
        if (aJ != null) {
            aS += aJ.aS * 0.2D;
            aU += aJ.aU * 0.2D;
        }

        double d5 = 0.4D;

        if (aS < -d5)
            aS = (-d5);
        if (aS > d5)
            aS = d5;
        if (aU < -d5)
            aU = (-d5);
        if (aU > d5)
            aU = d5;
        if (ba) {
            aS *= 0.5D;
            aT *= 0.5D;
            aU *= 0.5D;
        }
        c(aS, aT, aU);
        double d6 = Math.sqrt(aS * aS + aU * aU);
        if (d6 > 0.15D) {
            double d7 = Math.cos(aV * 3.141592653589793D / 180.0D);
            double d8 = Math.sin(aV * 3.141592653589793D / 180.0D);

            for (int m = 0; m < 1.0D + d6 * 60.0D; m++) {
                double d9 = bv.nextFloat() * 2.0F - 1.0F;

                double d10 = (bv.nextInt(2) * 2 - 1) * 0.7D;
                double d11;
                double d12;
                if (bv.nextBoolean()) {
                    d11 = aP- d7 * d9 * 0.8D + d8 * d10;
                    d12 = aR - d8 * d9 * 0.8D - d7 * d10;
                    aL.a("splash", d11, aQ - 0.125D, d12, aS, aT, aU);
                } else {
                    d11 = aP + d7 + d8 * d9 * 0.7D;
                    d12 = aR + d8 - d7 * d9 * 0.7D;
                    aL.a("splash", d11, aQ - 0.125D, d12, aS, aT, aU);
                }
            }
        }

        if ((bb) && (d6 > 0.15D)) {
            if (!aL.B) {
                I();
                for (int n = 0; n < 3; n++)
                    a(OBlock.y.bn, 1, 0.0F);
                for (int n = 0; n < 2; n++)
                    a(OItem.B.be, 1, 0.0F);
            }
        } else {
            aS *= 0.99D;
            aT *= 0.95D;
            aU *= 0.99D;
        }

        aW = 0.0F;
        double d7 = aV;
        double d8 = aM - aP;
        double d13 = aO - aR;
        if (d8 * d8 + d13 * d13 > 0.001D)
            d7 = (float) (Math.atan2(d13, d8) * 180.0D / 3.141592653589793D);

        double d14 = d7 - aV;
        while (d14 >= 180.0D)
            d14 -= 360.0D;
        while (d14 < -180.0D)
            d14 += 360.0D;
        if (d14 > 20.0D)
            d14 = 20.0D;
        if (d14 < -20.0D)
            d14 = -20.0D;

        aV = (float) (aV + d14);
        c(aV, aW);

        // CanaryMod: Change of the boat
        if (aP != prevX || aQ != prevY || aR != prevZ)
            manager.callHook(PluginLoader.Hook.VEHICLE_POSITIONCHANGE, boat, (int) aP, (int) aQ, (int) aR);

        
        List localList = aL.b(this, aZ.b(0.2D, 0.0D, 0.2D));
        if ((localList != null) && (localList.size() > 0))
            for (int i1 = 0; i1 < localList.size(); i1++) {
                OEntity localOEntity = (OEntity) localList.get(i1);
                if ((localOEntity != aJ) && (localOEntity.d_()) && ((localOEntity instanceof OEntityBoat)))
                    localOEntity.h(this);
            }
        
        for (int var33 = 0; var33 < 4; ++var33) {
            int var37 = OMathHelper.b(this.aP + ((double) (var33 % 2) - 0.5D) * 0.8D);
            int var35 = OMathHelper.b(this.aQ);
            int var36 = OMathHelper.b(this.aR + ((double) (var33 / 2) - 0.5D) * 0.8D);
            if (this.aL.a(var37, var35, var36) == OBlock.aT.bn)
                this.aL.e(var37, var35, var36, 0);
        }

        if ((aJ != null) && (aJ.bh))
            aJ = null;
    }

    @Override
    public void f() {
        if (aJ == null)
            return;

        double d1 = Math.cos(aV * 3.141592653589793D / 180.0D) * 0.4D;
        double d2 = Math.sin(aV * 3.141592653589793D / 180.0D) * 0.4D;
        aJ.a(aP + d1, aQ + m() + aJ.H(), aR + d2);
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

        
        if ((aJ != null) && ((aJ instanceof OEntityPlayer)) && (aJ != paramOEntityPlayer))
            return true;
        if (!aL.B)
            paramOEntityPlayer.b(this);
        return true;
    }
}
