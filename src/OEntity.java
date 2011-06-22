
import java.util.List;
import java.util.Random;

public abstract class OEntity {

    private static int a = 0;
    public int aG = a++;
    public double aH = 1.0D;
    public boolean aI = false;
    public OEntity aJ;
    public OEntity aK;
    public OWorld aL;
    public double aM;
    public double aN;
    public double aO;
    public double aP;
    public double aQ;
    public double aR;
    public double aS;
    public double aT;
    public double aU;
    public float aV;
    public float aW;
    public float aX;
    public float aY;
    public final OAxisAlignedBB aZ = OAxisAlignedBB.a(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
    public boolean ba = false;
    public boolean bb;
    public boolean bc;
    public boolean bd = false;
    public boolean be = false;
    public boolean bf;
    public boolean bg = true;
    public boolean bh = false;
    public float bi = 0.0F;
    public float bj = 0.6F;
    public float bk = 1.8F;
    public float bl = 0.0F;
    public float bm = 0.0F;
    protected float bn = 0.0F;
    private int b = 1;
    public double bo;
    public double bp;
    public double bq;
    public float br = 0.0F;
    public float bs = 0.0F;
    public boolean bt = false;
    public float bu = 0.0F;
    protected Random bv = new Random();
    public int bw = 0;
    public int bx = 1;
    public int by = 0;
    protected int bz = 300;
    protected boolean bA = false;
    public int bB = 0;
    public int bC = 300;
    private boolean c = true;
    protected boolean bD = false;
    protected ODataWatcher bE = new ODataWatcher();
    private double d;
    private double e;
    public boolean bF = false;
    public int bG;
    public int bH;
    public int bI;
    public boolean bJ;
    // CanaryMod Start
    BaseEntity entity = new BaseEntity(this);
    public static PluginLoader manager = etc.getLoader();

    // CanaryMod end
    public OEntity(OWorld paramOWorld) {
        aL = paramOWorld;

        a(0.0D, 0.0D, 0.0D);

        bE.a(0, (byte) 0);
        b();
    }

    protected abstract void b();

    public ODataWatcher Z() {
        return bE;
    }

    @Override
    public boolean equals(Object paramObject) {
        if ((paramObject instanceof OEntity))
            return ((OEntity) paramObject).aG == aG;
        return false;
    }

    @Override
    public int hashCode() {
        return aG;
    }

    public void I() {
        bh = true;
    }

    protected void b(float paramFloat1, float paramFloat2) {
        bj = paramFloat1;
        bk = paramFloat2;
    }

    protected void c(float paramFloat1, float paramFloat2) {
        aV = paramFloat1 % 360.0F;
        aW = paramFloat2 % 360.0F;
    }

    public void a(double paramDouble1, double paramDouble2, double paramDouble3) {
        aP = paramDouble1;
        aQ = paramDouble2;
        aR = paramDouble3;
        float f1 = bj / 2.0F;
        float f2 = bk;

        aZ.c(paramDouble1 - f1, paramDouble2 - bi + br, paramDouble3 - f1, paramDouble1 + f1, paramDouble2 - bi + br + f2, paramDouble3 + f1);
    }

    public void o_() {
        Q();
    }

    public void Q() {
        if ((aK != null) && (aK.bh))
            aK = null;

        bw += 1;
        bl = bm;
        aM = aP;
        aN = aQ;
        aO = aR;
        aY = aW;
        aX = aV;

        if (f_()) {
            if ((!bA) && (!c)) {
                float f1 = OMathHelper.a(aS * aS * 0.2D + aT * aT + aU * aU * 0.2D) * 0.2F;
                if (f1 > 1.0F)
                    f1 = 1.0F;
                aL.a(this, "random.splash", f1, 1.0F + (bv.nextFloat() - bv.nextFloat()) * 0.4F);
                float f2 = OMathHelper.b(aZ.b);
                float f3;
                float f4;
                for (int i = 0; i < 1.0F + bj * 20.0F; i++) {
                    f3 = (bv.nextFloat() * 2.0F - 1.0F) * bj;
                    f4 = (bv.nextFloat() * 2.0F - 1.0F) * bj;
                    aL.a("bubble", aP + f3, f2 + 1.0F, aR + f4, aS, aT - bv.nextFloat() * 0.2F, aU);
                }
                for (int i = 0; i < 1.0F + bj * 20.0F; i++) {
                    f3 = (bv.nextFloat() * 2.0F - 1.0F) * bj;
                    f4 = (bv.nextFloat() * 2.0F - 1.0F) * bj;
                    aL.a("splash", aP + f3, f2 + 1.0F, aR + f4, aS, aT, aU);
                }
            }
            bn = 0.0F;
            bA = true;
            by = 0;
        } else
            bA = false;

        if (aL.B)
            by = 0;
        else if (by > 0)
            if (bD) {
                by -= 4;
                if (by < 0)
                    by = 0;
            } else {
                if (by % 20 == 0)
                    // CanaryMod Damage hook: Periodic burn damage
                    if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.FIRE_TICK, null, entity, 1))
                        a((OEntity) null, 1);

                by -= 1;
            }

        if (ad())
            aa();

        if (aQ < -64.0D)
            X();

        if (!aL.B) {
            a(0, by > 0);
            a(2, aK != null);
        }

        c = false;
    }

    protected void aa() {
        if (!bD) {
            // CanaryMod Damage hook: Lava
            if (this instanceof OEntityLiving)
                if ((Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.LAVA, null, entity, 4))
                    return;
            a((OEntity) null, 4);

            by = 600;
        }
    }

    protected void X() {
        I();
    }

    public boolean b(double paramDouble1, double paramDouble2, double paramDouble3) {
        OAxisAlignedBB localOAxisAlignedBB = aZ.c(paramDouble1, paramDouble2, paramDouble3);
        List localList = aL.a(this, localOAxisAlignedBB);
        if (localList.size() > 0)
            return false;
        return !aL.c(localOAxisAlignedBB);
    }

    public void c(double paramDouble1, double paramDouble2, double paramDouble3) {
        if (bt) {
            aZ.d(paramDouble1, paramDouble2, paramDouble3);
            aP = ((aZ.a + aZ.d) / 2.0D);
            aQ = (aZ.b + bi - br);
            aR = ((aZ.c + aZ.f) / 2.0D);
            return;
        }

        br *= 0.4F;

        double d1 = aP;
        double d2 = aR;

        if (bf) {
            bf = false;
            paramDouble1 *= 0.25D;
            paramDouble2 *= 0.05D;
            paramDouble3 *= 0.25D;
            aS = 0.0D;
            aT = 0.0D;
            aU = 0.0D;
        }

        double d3 = paramDouble1;
        double d4 = paramDouble2;
        double d5 = paramDouble3;

        OAxisAlignedBB localOAxisAlignedBB1 = aZ.b();

        boolean i = (ba) && (ag());

        if (i) {
            double d6 = 0.05D;
            while ((paramDouble1 != 0.0D) && (aL.a(this, aZ.c(paramDouble1, -1.0D, 0.0D)).isEmpty())) {
                if ((paramDouble1 < d6) && (paramDouble1 >= -d6))
                    paramDouble1 = 0.0D;
                else if (paramDouble1 > 0.0D)
                    paramDouble1 -= d6;
                else
                    paramDouble1 += d6;
                d3 = paramDouble1;
            }
            while ((paramDouble3 != 0.0D) && (aL.a(this, aZ.c(0.0D, -1.0D, paramDouble3)).isEmpty())) {
                if ((paramDouble3 < d6) && (paramDouble3 >= -d6))
                    paramDouble3 = 0.0D;
                else if (paramDouble3 > 0.0D)
                    paramDouble3 -= d6;
                else
                    paramDouble3 += d6;
                d5 = paramDouble3;
            }
        }

        List localList = aL.a(this, aZ.a(paramDouble1, paramDouble2, paramDouble3));

        for (int j = 0; j < localList.size(); j++)
            paramDouble2 = ((OAxisAlignedBB) localList.get(j)).b(aZ, paramDouble2);
        aZ.d(0.0D, paramDouble2, 0.0D);

        if ((!bg) && (d4 != paramDouble2))
            paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;

        boolean j = (ba) || ((d4 != paramDouble2) && (d4 < 0.0D));

        for (int k = 0; k < localList.size(); k++)
            paramDouble1 = ((OAxisAlignedBB) localList.get(k)).a(aZ, paramDouble1);
        aZ.d(paramDouble1, 0.0D, 0.0D);

        if ((!bg) && (d3 != paramDouble1))
            paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;

        for (int k = 0; k < localList.size(); k++)
            paramDouble3 = ((OAxisAlignedBB) localList.get(k)).c(aZ, paramDouble3);
        aZ.d(0.0D, 0.0D, paramDouble3);

        if ((!bg) && (d5 != paramDouble3))
            paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;

        if ((bs > 0.0F) && j && (i || br < 0.05F) && ((d3 != paramDouble1) || (d5 != paramDouble3))) {
            double d7 = paramDouble1;
            double d8 = paramDouble2;
            double d9 = paramDouble3;

            paramDouble1 = d3;
            paramDouble2 = bs;
            paramDouble3 = d5;

            OAxisAlignedBB localOAxisAlignedBB2 = aZ.b();
            aZ.b(localOAxisAlignedBB1);
            localList = aL.a(this, aZ.a(paramDouble1, paramDouble2, paramDouble3));

            for (int n = 0; n < localList.size(); n++)
                paramDouble2 = ((OAxisAlignedBB) localList.get(n)).b(aZ, paramDouble2);
            aZ.d(0.0D, paramDouble2, 0.0D);

            if ((!bg) && (d4 != paramDouble2))
                paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;

            for (int n = 0; n < localList.size(); n++)
                paramDouble1 = ((OAxisAlignedBB) localList.get(n)).a(aZ, paramDouble1);
            aZ.d(paramDouble1, 0.0D, 0.0D);

            if ((!bg) && (d3 != paramDouble1))
                paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;

            for (int n = 0; n < localList.size(); n++)
                paramDouble3 = ((OAxisAlignedBB) localList.get(n)).c(aZ, paramDouble3);
            aZ.d(0.0D, 0.0D, paramDouble3);

            if ((!bg) && (d5 != paramDouble3))
                paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;

            if ((!bg) && (d4 != paramDouble2))
                paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;
            else {
                paramDouble2 = -bs;
                for (OAxisAlignedBB oaabb : (List<OAxisAlignedBB>) localList)
                    paramDouble2 = oaabb.b(aZ, paramDouble2);
                aZ.d(0.0D, paramDouble2, 0.0D);
            }

            if (d7 * d7 + d9 * d9 >= paramDouble1 * paramDouble1 + paramDouble3 * paramDouble3) {
                paramDouble1 = d7;
                paramDouble2 = d8;
                paramDouble3 = d9;
                aZ.b(localOAxisAlignedBB2);
            } else {
                double d10 = aZ.b - (int) aZ.b;
                if (d10 > 0.0D)
                    br = (float) (br + d10 + 0.01D);
            }

        }

        aP = ((aZ.a + aZ.d) / 2.0D);
        aQ = (aZ.b + bi - br);
        aR = ((aZ.c + aZ.f) / 2.0D);

        bb = ((d3 != paramDouble1) || (d5 != paramDouble3));
        bc = (d4 != paramDouble2);
        ba = ((d4 != paramDouble2) && (d4 < 0.0D));
        bd = ((bb) || (bc));
        a(paramDouble2, ba);

        if (d3 != paramDouble1)
            aS = 0.0D;
        if (d4 != paramDouble2)
            aT = 0.0D;
        if (d5 != paramDouble3)
            aU = 0.0D;

        double d7 = aP - d1;
        double d8 = aR - d2;

        if ((n()) && !i && aK == null) {
            bm = (float) (bm + OMathHelper.a(d7 * d7 + d8 * d8) * 0.6D);
            int i1 = OMathHelper.b(aP);
            int i2 = OMathHelper.b(aQ - 0.2D - bi);
            int m = OMathHelper.b(aR);
            int n = aL.a(i1, i2, m);
            if ((bm > b) && (n > 0)) {
                b += 1;
                OStepSound localOStepSound = OBlock.m[n].by;
                if (aL.a(i1, i2 + 1, m) == OBlock.aT.bn) {
                    localOStepSound = OBlock.aT.by;
                    aL.a(this, localOStepSound.c(), localOStepSound.a() * 0.15F, localOStepSound.b());
                } else if (!OBlock.m[n].bA.d())
                    aL.a(this, localOStepSound.c(), localOStepSound.a() * 0.15F, localOStepSound.b());
                OBlock.m[n].b(aL, i1, i2, m, this);
            }

        }

        int i1 = OMathHelper.b(aZ.a);
        int i2 = OMathHelper.b(aZ.b);
        int m = OMathHelper.b(aZ.c);
        int n = OMathHelper.b(aZ.d);
        int i3 = OMathHelper.b(aZ.e);
        int i4 = OMathHelper.b(aZ.f);

        if (aL.a(i1, i2, m, n, i3, i4))
            for (int i5 = i1; i5 <= n; i5++)
                for (int i6 = i2; i6 <= i3; i6++)
                    for (int i7 = m; i7 <= i4; i7++) {
                        int i8 = aL.a(i5, i6, i7);
                        if (i8 > 0)
                            OBlock.m[i8].a(aL, i5, i6, i7, this);
                    }

        boolean bool = ab();
        if (aL.d(aZ.e(0.001D, 0.001D, 0.001D))) {
            a(1);
            if (!bool) {
                by += 1;
                if (by == 0)
                    by = 300;
            }
        } else if (by <= 0)
            by = (-bx);

        if ((bool) && (by > 0)) {
            aL.a(this, "random.fizz", 0.7F, 1.6F + (bv.nextFloat() - bv.nextFloat()) * 0.4F);
            by = (-bx);
        }
    }

    protected boolean n() {
        return true;
    }

    protected void a(double paramDouble, boolean paramBoolean) {
        if (paramBoolean) {
            if (bn > 0.0F) {
                a(bn);
                bn = 0.0F;
            }
        } else if (paramDouble < 0.0D)
            bn = (float) (bn - paramDouble);
    }

    public OAxisAlignedBB e_() {
        return null;
    }

    protected void a(int paramInt) {
        if (!bD)
            if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.FIRE, null, entity, paramInt))
                a((OEntity) null, paramInt);

    }

    protected void a(float paramFloat) {
    }

    public boolean ab() {
        return bA || aL.q(OMathHelper.b(aP), OMathHelper.b(aQ), OMathHelper.b(aR));
    }

    public boolean ac() {
        return bA;
    }

    public boolean f_() {
        return aL.a(this.aZ.b(0.0D, -0.4000000059604645D, 0.0D).e(0.001D, 0.001D, 0.001D), OMaterial.g, this);
    }

    public boolean a(OMaterial paramOMaterial) {
        double d1 = aQ + s();
        int i = OMathHelper.b(aP);
        int j = OMathHelper.d(OMathHelper.b(d1));
        int k = OMathHelper.b(aR);
        int m = aL.a(i, j, k);
        if ((m != 0) && (OBlock.m[m].bA == paramOMaterial)) {
            float f1 = OBlockFluid.c(aL.b(i, j, k)) - 0.1111111F;
            float f2 = j + 1 - f1;
            return d1 < f2;
        }
        return false;
    }

    public float s() {
        return 0.0F;
    }

    public boolean ad() {
        return aL.a(aZ.b(-0.1D, -0.4D, -0.1D), OMaterial.h);
    }

    public void a(float paramFloat1, float paramFloat2, float paramFloat3) {
        float f1 = OMathHelper.c(paramFloat1 * paramFloat1 + paramFloat2 * paramFloat2);
        if (f1 < 0.01F)
            return;

        if (f1 < 1.0F)
            f1 = 1.0F;
        f1 = paramFloat3 / f1;
        paramFloat1 *= f1;
        paramFloat2 *= f1;

        float f2 = OMathHelper.a(aV * 3.141593F / 180.0F);
        float f3 = OMathHelper.b(aV * 3.141593F / 180.0F);

        aS += paramFloat1 * f3 - paramFloat2 * f2;
        aU += paramFloat2 * f3 + paramFloat1 * f2;
    }

    public float c(float paramFloat) {
        int i = OMathHelper.b(aP);

        double d1 = (aZ.e - aZ.b) * 0.66D;
        int j = OMathHelper.b(aQ - bi + d1);
        int k = OMathHelper.b(aR);
        if (aL.a(OMathHelper.b(aZ.a), OMathHelper.b(aZ.b), OMathHelper.b(aZ.c), OMathHelper.b(aZ.d), OMathHelper.b(aZ.e), OMathHelper.b(aZ.f)))
            return aL.m(i, j, k);
        return 0.0F;
    }

    public void a(OWorld ow) {
        aL = ow;
    }

    public void b(double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2) {
        aM = (aP = paramDouble1);
        aN = (aQ = paramDouble2);
        aO = (aR = paramDouble3);
        aX = (aV = paramFloat1);
        aY = (aW = paramFloat2);
        br = 0.0F;

        double d1 = aX - paramFloat1;
        if (d1 < -180.0D)
            aX += 360.0F;
        if (d1 >= 180.0D)
            aX -= 360.0F;
        a(aP, aQ, aR);
        c(paramFloat1, paramFloat2);
    }

    public void c(double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2) {
        bo = (aM = aP = paramDouble1);
        bp = (aN = aQ = paramDouble2 + bi);
        bq = (aO = aR = paramDouble3);
        aV = paramFloat1;
        aW = paramFloat2;
        a(aP, aQ, aR);
    }

    public float f(OEntity paramOEntity) {
        float f1 = (float) (aP - paramOEntity.aP);
        float f2 = (float) (aQ - paramOEntity.aQ);
        float f3 = (float) (aR - paramOEntity.aR);
        return OMathHelper.c(f1 * f1 + f2 * f2 + f3 * f3);
    }

    public double d(double paramDouble1, double paramDouble2, double paramDouble3) {
        double d1 = aP - paramDouble1;
        double d2 = aQ - paramDouble2;
        double d3 = aR - paramDouble3;
        return d1 * d1 + d2 * d2 + d3 * d3;
    }

    public double e(double paramDouble1, double paramDouble2, double paramDouble3) {
        double d1 = aP - paramDouble1;
        double d2 = aQ - paramDouble2;
        double d3 = aR - paramDouble3;
        return OMathHelper.a(d1 * d1 + d2 * d2 + d3 * d3);
    }

    public double g(OEntity paramOEntity) {
        double d1 = aP - paramOEntity.aP;
        double d2 = aQ - paramOEntity.aQ;
        double d3 = aR - paramOEntity.aR;
        return d1 * d1 + d2 * d2 + d3 * d3;
    }

    public void b(OEntityPlayer paramOEntityPlayer) {
    }

    public void h(OEntity paramOEntity) {
        if ((paramOEntity.aJ == this) || (paramOEntity.aK == this))
            return;

        double d1 = paramOEntity.aP - aP;
        double d2 = paramOEntity.aR - aR;

        double d3 = OMathHelper.a(d1, d2);

        if (d3 >= 0.01D) {
            d3 = OMathHelper.a(d3);
            d1 /= d3;
            d2 /= d3;

            double d4 = 1.0D / d3;
            if (d4 > 1.0D)
                d4 = 1.0D;
            d1 *= d4;
            d2 *= d4;

            d1 *= 0.05D;
            d2 *= 0.05D;

            d1 *= (1.0F - bu);
            d2 *= (1.0F - bu);

            f(-d1, 0.0D, -d2);
            paramOEntity.f(d1, 0.0D, d2);
        }
    }

    public void f(double paramDouble1, double paramDouble2, double paramDouble3) {
        aS += paramDouble1;
        aT += paramDouble2;
        aU += paramDouble3;
    }

    protected void ae() {
        be = true;
    }

    public boolean a(OEntity paramOEntity, int paramInt) {
        ae();
        return false;
    }

    public boolean n_() {
        return false;
    }

    public boolean d_() {
        return false;
    }

    public void c(OEntity paramOEntity, int paramInt) {
    }

    public boolean c(ONBTTagCompound paramONBTTagCompound) {
        String str = af();
        if ((bh) || (str == null))
            return false;
        paramONBTTagCompound.a("id", str);
        d(paramONBTTagCompound);
        return true;
    }

    public void d(ONBTTagCompound paramONBTTagCompound) {
        paramONBTTagCompound.a("Pos", a(new double[]{aP, aQ, aR}));
        paramONBTTagCompound.a("Motion", a(new double[]{aS, aT, aU}));
        paramONBTTagCompound.a("Rotation", a(new float[]{aV, aW}));

        paramONBTTagCompound.a("FallDistance", bn);
        paramONBTTagCompound.a("Fire", (short) by);
        paramONBTTagCompound.a("Air", (short) bC);
        paramONBTTagCompound.a("OnGround", ba);

        b(paramONBTTagCompound);
    }

    public void e(ONBTTagCompound paramONBTTagCompound) {
        ONBTTagList localONBTTagList1 = paramONBTTagCompound.l("Pos");
        ONBTTagList localONBTTagList2 = paramONBTTagCompound.l("Motion");
        ONBTTagList localONBTTagList3 = paramONBTTagCompound.l("Rotation");

        aS = ((ONBTTagDouble) localONBTTagList2.a(0)).a;
        aT = ((ONBTTagDouble) localONBTTagList2.a(1)).a;
        aU = ((ONBTTagDouble) localONBTTagList2.a(2)).a;

        if (Math.abs(aS) > 10.0D)
            aS = 0.0D;
        if (Math.abs(aT) > 10.0D)
            aT = 0.0D;
        if (Math.abs(aU) > 10.0D)
            aU = 0.0D;

        aM = (bo = aP = ((ONBTTagDouble) localONBTTagList1.a(0)).a);
        aN = (bp = aQ = ((ONBTTagDouble) localONBTTagList1.a(1)).a);
        aO = (bq = aR = ((ONBTTagDouble) localONBTTagList1.a(2)).a);

        aX = (aV = ((ONBTTagFloat) localONBTTagList3.a(0)).a % 6.283186F);
        aY = (aW = ((ONBTTagFloat) localONBTTagList3.a(1)).a % 6.283186F);

        bn = paramONBTTagCompound.g("FallDistance");
        by = paramONBTTagCompound.d("Fire");
        bC = paramONBTTagCompound.d("Air");
        ba = paramONBTTagCompound.m("OnGround");

        a(aP, aQ, aR);
        c(aV, aW);

        a(paramONBTTagCompound);
    }

    protected final String af() {
        return OEntityList.b(this);
    }

    protected abstract void a(ONBTTagCompound paramONBTTagCompound);

    protected abstract void b(ONBTTagCompound paramONBTTagCompound);

    protected ONBTTagList a(double[] paramArrayOfDouble) {
        ONBTTagList localONBTTagList = new ONBTTagList();
        for (double d1 : paramArrayOfDouble)
            localONBTTagList.a(new ONBTTagDouble(d1));
        return localONBTTagList;
    }

    protected ONBTTagList a(float[] paramArrayOfFloat) {
        ONBTTagList localONBTTagList = new ONBTTagList();
        for (float f : paramArrayOfFloat)
            localONBTTagList.a(new ONBTTagFloat(f));
        return localONBTTagList;
    }

    public OEntityItem b(int paramInt1, int paramInt2) {
        return a(paramInt1, paramInt2, 0.0F);
    }

    public OEntityItem a(int paramInt1, int paramInt2, float paramFloat) {
        return a(new OItemStack(paramInt1, paramInt2, 0), paramFloat);
    }

    public OEntityItem a(OItemStack paramOItemStack, float paramFloat) {
        OEntityItem localOEntityItem = new OEntityItem(aL, aP, aQ + paramFloat, aR, paramOItemStack);
        localOEntityItem.c = 10;
        aL.b(localOEntityItem);
        return localOEntityItem;
    }

    public boolean S() {
        return !bh;
    }

    public boolean J() {
        for (int var1 = 0; var1 < 8; ++var1) {
            float var2 = ((float) ((var1 >> 0) % 2) - 0.5F) * this.bj * 0.9F;
            float var3 = ((float) ((var1 >> 1) % 2) - 0.5F) * 0.1F;
            float var4 = ((float) ((var1 >> 2) % 2) - 0.5F) * this.bj * 0.9F;
            int var5 = OMathHelper.b(this.aP + (double) var2);
            int var6 = OMathHelper.b(this.aQ + (double) this.s() + (double) var3);
            int var7 = OMathHelper.b(this.aR + (double) var4);
            if (this.aL.d(var5, var6, var7))
                return true;
        }

        return false;
    }

    public boolean a(OEntityPlayer paramOEntityPlayer) {
        return false;
    }

    public OAxisAlignedBB a_(OEntity paramOEntity) {
        return null;
    }

    public void D() {
        if (aK.bh) {
            aK = null;
            return;
        }
        aS = 0.0D;
        aT = 0.0D;
        aU = 0.0D;
        o_();

        if (aK == null)
            return;
        aK.f();

        e += aK.aV - aK.aX;
        d += aK.aW - aK.aY;

        while (e >= 180.0D)
            e -= 360.0D;
        while (e < -180.0D)
            e += 360.0D;
        while (d >= 180.0D)
            d -= 360.0D;
        while (d < -180.0D)
            d += 360.0D;
        double d1 = e * 0.5D;
        double d2 = d * 0.5D;

        float f = 10.0F;
        if (d1 > f)
            d1 = f;
        if (d1 < -f)
            d1 = -f;
        if (d2 > f)
            d2 = f;
        if (d2 < -f)
            d2 = -f;

        e -= d1;
        d -= d2;

        aV = (float) (aV + d1);
        aW = (float) (aW + d2);
    }

    public void f() {
        aJ.a(aP, aQ + m() + aJ.H(), aR);
    }

    public double H() {
        return bi;
    }

    public double m() {
        return bk * 0.75D;
    }

    public void b(OEntity paramOEntity) {
        d = 0.0D;
        e = 0.0D;

        if (paramOEntity == null) {
            if (aK != null) {
                c(aK.aP, aK.aZ.b + aK.bk, aK.aR, aV, aW);
                aK.aJ = null;
            }
            aK = null;
            return;
        }
        if (aK == paramOEntity) {
            aK.aJ = null;
            aK = null;
            c(paramOEntity.aP, paramOEntity.aZ.b + paramOEntity.bk, paramOEntity.aR, aV, aW);
            return;
        }
        if (aK != null)
            aK.aJ = null;
        if (paramOEntity.aJ != null)
            paramOEntity.aJ.aK = null;
        aK = paramOEntity;
        paramOEntity.aJ = this;
    }

    public OVec3D Y() {
        return null;
    }

    public void O() {
    }

    public OItemStack[] i_() {
        return null;
    }

    public boolean ag() {
        return d(1);
    }

    public void e(boolean paramBoolean) {
        a(1, paramBoolean);
    }

    protected boolean d(int paramInt) {
        return (bE.a(0) & 1 << paramInt) != 0;
    }

    protected void a(int paramInt, boolean paramBoolean) {
        int i = bE.a(0);
        if (paramBoolean)
            bE.b(0, Byte.valueOf((byte) (i | 1 << paramInt)));
        else
            bE.b(0, Byte.valueOf((byte) (i & ~(1 << paramInt))));
    }

    public void a(OEntityLightningBolt var1) {
        if ((Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.LIGHTNING, null, entity, 5))
            return;
        this.a(5);
        ++this.by;
        if (this.by == 0)
            this.by = 300;
    }

    public void a(OEntityLiving var1) {
    }

    protected boolean g(double var1, double var3, double var5) {
        int var7 = OMathHelper.b(var1);
        int var8 = OMathHelper.b(var3);
        int var9 = OMathHelper.b(var5);
        double var10 = var1 - (double) var7;
        double var12 = var3 - (double) var8;
        double var14 = var5 - (double) var9;
        if (this.aL.d(var7, var8, var9)) {
            boolean var16 = !this.aL.d(var7 - 1, var8, var9);
            boolean var17 = !this.aL.d(var7 + 1, var8, var9);
            boolean var18 = !this.aL.d(var7, var8 - 1, var9);
            boolean var19 = !this.aL.d(var7, var8 + 1, var9);
            boolean var20 = !this.aL.d(var7, var8, var9 - 1);
            boolean var21 = !this.aL.d(var7, var8, var9 + 1);
            byte var22 = -1;
            double var23 = 9999.0D;
            if (var16 && var10 < var23) {
                var23 = var10;
                var22 = 0;
            }

            if (var17 && 1.0D - var10 < var23) {
                var23 = 1.0D - var10;
                var22 = 1;
            }

            if (var18 && var12 < var23) {
                var23 = var12;
                var22 = 2;
            }

            if (var19 && 1.0D - var12 < var23) {
                var23 = 1.0D - var12;
                var22 = 3;
            }

            if (var20 && var14 < var23) {
                var23 = var14;
                var22 = 4;
            }

            if (var21 && 1.0D - var14 < var23) {
                var23 = 1.0D - var14;
                var22 = 5;
            }

            float var25 = this.bv.nextFloat() * 0.2F + 0.1F;
            if (var22 == 0)
                this.aS = (double) (-var25);

            if (var22 == 1)
                this.aS = (double) var25;

            if (var22 == 2)
                this.aT = (double) (-var25);

            if (var22 == 3)
                this.aT = (double) var25;

            if (var22 == 4)
                this.aU = (double) (-var25);

            if (var22 == 5)
                this.aU = (double) var25;
        }

        return false;
    }
}
