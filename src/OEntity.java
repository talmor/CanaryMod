import java.util.List;
import java.util.Random;

public abstract class OEntity {
    private static int          a  = 0;

    public int                  aC = a++;

    public double               aD = 1.0D;

    public boolean              aE = false;
    public OEntity              aF;
    public OEntity              aG;
    public OWorld               aH;
    public double               aI;
    public double               aJ;
    public double               aK;
    public double               aL;
    public double               aM;
    public double               aN;
    public double               aO;
    public double               aP;
    public double               aQ;
    public float                aR;
    public float                aS;
    public float                aT;
    public float                aU;
    public final OAxisAlignedBB aV = OAxisAlignedBB.a(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
    public boolean              aW = false;
    public boolean              aX;
    public boolean              aY;
    public boolean              aZ = false;
    
    public boolean              ba = false;
    public boolean              bb;
    public boolean              bc = true;
    public boolean              bd = false;
    public float                be = 0.0F;

    public float                bf = 0.6F;
    public float                bg = 1.8F;

    public float                bh = 0.0F;
    public float                bi = 0.0F;
    protected float             bj = 0.0F;
    private int                 b  = 1;
    public double               bk;
    public double               bl;
    public double               bm;
    public float                bn = 0.0F;
    public float                bo = 0.0F;
    public boolean              bp = false;
    public float                bq = 0.0F;

    protected Random            br = new Random();
    public int                  bs = 0;
    public int                  bt = 1;

    public int                  bu = 0;

    protected int               bv = 300;
    protected boolean           bw = false;
    public int                  bx = 0;
    public int                  by = 300;

    private boolean             c  = true;

    protected boolean           bz = false;

    protected ODataWatcher      bA = new ODataWatcher();
    private double              d;
    private double              e;
    public boolean              bB = false;
    public int                  bC;
    public int                  bD;
    public int                  bE;

    // CanaryMod Start
    BaseEntity                  entity  = new BaseEntity(this);
    public static PluginLoader  manager = etc.getLoader();

    // CanaryMod end

    
    public OEntity(OWorld paramOWorld) {
        aH = paramOWorld;

        a(0.0D, 0.0D, 0.0D);

        bA.a(0, (byte) 0);
        b();
    }

    protected abstract void b();

    public ODataWatcher W() {
        return bA;
    }

    @Override
    public boolean equals(Object paramObject) {
        if ((paramObject instanceof OEntity))
            return ((OEntity) paramObject).aC == aC;
        return false;
    }

    @Override
    public int hashCode() {
        return aC;
    }

    public void G() {
        bd = true;
    }

    protected void b(float paramFloat1, float paramFloat2) {
        bf = paramFloat1;
        bg = paramFloat2;
    }

    protected void c(float paramFloat1, float paramFloat2) {
        aR = paramFloat1 % 360.0F;
        aS = paramFloat2 % 360.0F;
    }

    public void a(double paramDouble1, double paramDouble2, double paramDouble3) {
        aL = paramDouble1;
        aM = paramDouble2;
        aN = paramDouble3;
        float f1 = bf / 2.0F;
        float f2 = bg;

        aV.c(paramDouble1 - f1, paramDouble2 - be + bn, paramDouble3 - f1, paramDouble1 + f1, paramDouble2 - be + bn + f2, paramDouble3 + f1);
    }

    public void p_() {
        N();
    }

    public void N() {
        if ((aG != null) && (aG.bd))
            aG = null;

        bs += 1;
        bh = bi;
        aI = aL;
        aJ = aM;
        aK = aN;
        aU = aS;
        aT = aR;

        if (f_()) {
            if ((!bw) && (!c)) {
                float f1 = OMathHelper.a(aO * aO * 0.2D + aP * aP + aQ * aQ * 0.2D) * 0.2F;
                if (f1 > 1.0F)
                    f1 = 1.0F;
                aH.a(this, "random.splash", f1, 1.0F + (br.nextFloat() - br.nextFloat()) * 0.4F);
                float f2 = OMathHelper.b(aV.b);
                float f3;
                float f4;
                for (int i = 0; i < 1.0F + bf * 20.0F; i++) {
                    f3 = (br.nextFloat() * 2.0F - 1.0F) * bf;
                    f4 = (br.nextFloat() * 2.0F - 1.0F) * bf;
                    aH.a("bubble", aL + f3, f2 + 1.0F, aN + f4, aO, aP - br.nextFloat() * 0.2F, aQ);
                }
                for (int i = 0; i < 1.0F + bf * 20.0F; i++) {
                    f3 = (br.nextFloat() * 2.0F - 1.0F) * bf;
                    f4 = (br.nextFloat() * 2.0F - 1.0F) * bf;
                    aH.a("splash", aL + f3, f2 + 1.0F, aN + f4, aO, aP, aQ);
                }
            }
            bj = 0.0F;
            bw = true;
            bu = 0;
        } else
            bw = false;

        if (aH.v)
            bu = 0;
        else if (bu > 0)
            if (bz) {
                bu -= 4;
                if (bu < 0)
                    bu = 0;
            } else {
                if (bu % 20 == 0)
                    // CanaryMod Damage hook: Periodic burn damage
                    if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.FIRE_TICK, null, entity, 1))
                        a((OEntity) null, 1);

                bu -= 1;
            }

        if (aa())
            X();

        if (aM < -64.0D)
            U();

        if (!aH.v) {
            a(0, bu > 0);
            a(2, aG != null);
        }

        c = false;
    }

    protected void X() {
        if (!bz) {
            // CanaryMod Damage hook: Lava
            if (this instanceof OEntityLiving)
                if ((Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.LAVA, null, entity, 4))
                    return;
            a((OEntity) null, 4);

            bu = 600;
        }
    }

    protected void U() {
        G();
    }

    public boolean b(double paramDouble1, double paramDouble2, double paramDouble3) {
        OAxisAlignedBB localOAxisAlignedBB = aV.c(paramDouble1, paramDouble2, paramDouble3);
        List localList = aH.a(this, localOAxisAlignedBB);
        if (localList.size() > 0)
            return false;
        return !aH.c(localOAxisAlignedBB);
    }

    public void c(double paramDouble1, double paramDouble2, double paramDouble3) {
        if (bp) {
            aV.d(paramDouble1, paramDouble2, paramDouble3);
            aL = ((aV.a + aV.d) / 2.0D);
            aM = (aV.b + be - bn);
            aN = ((aV.c + aV.f) / 2.0D);
            return;
        }

        double d1 = aL;
        double d2 = aN;

        double d3 = paramDouble1;
        double d4 = paramDouble2;
        double d5 = paramDouble3;

        OAxisAlignedBB localOAxisAlignedBB1 = aV.b();

        boolean i = (aW) && (ae());

        if (i) {
            double d6 = 0.05D;
            while ((paramDouble1 != 0.0D) && (aH.a(this, aV.c(paramDouble1, -1.0D, 0.0D)).isEmpty())) {
                if ((paramDouble1 < d6) && (paramDouble1 >= -d6))
                    paramDouble1 = 0.0D;
                else if (paramDouble1 > 0.0D)
                    paramDouble1 -= d6;
                else
                    paramDouble1 += d6;
                d3 = paramDouble1;
            }
            while ((paramDouble3 != 0.0D) && (aH.a(this, aV.c(0.0D, -1.0D, paramDouble3)).isEmpty())) {
                if ((paramDouble3 < d6) && (paramDouble3 >= -d6))
                    paramDouble3 = 0.0D;
                else if (paramDouble3 > 0.0D)
                    paramDouble3 -= d6;
                else
                    paramDouble3 += d6;
                d5 = paramDouble3;
            }
        }

        List localList = aH.a(this, aV.a(paramDouble1, paramDouble2, paramDouble3));

        for (int j = 0; j < localList.size(); j++)
            paramDouble2 = ((OAxisAlignedBB) localList.get(j)).b(aV, paramDouble2);
        aV.d(0.0D, paramDouble2, 0.0D);

        if ((!bc) && (d4 != paramDouble2))
            paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;

        boolean j = (aW) || ((d4 != paramDouble2) && (d4 < 0.0D));

        for (int k = 0; k < localList.size(); k++)
            paramDouble1 = ((OAxisAlignedBB) localList.get(k)).a(aV, paramDouble1);
        aV.d(paramDouble1, 0.0D, 0.0D);

        if ((!bc) && (d3 != paramDouble1))
            paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;

        for (int k = 0; k < localList.size(); k++)
            paramDouble3 = ((OAxisAlignedBB) localList.get(k)).c(aV, paramDouble3);
        aV.d(0.0D, 0.0D, paramDouble3);

        if ((!bc) && (d5 != paramDouble3))
            paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;

        if ((bo > 0.0F) && j && (bn < 0.05F) && ((d3 != paramDouble1) || (d5 != paramDouble3))) {
            double d7 = paramDouble1;
            double d8 = paramDouble2;
            double d9 = paramDouble3;

            paramDouble1 = d3;
            paramDouble2 = bo;
            paramDouble3 = d5;

            OAxisAlignedBB localOAxisAlignedBB2 = aV.b();
            aV.b(localOAxisAlignedBB1);
            localList = aH.a(this, aV.a(paramDouble1, paramDouble2, paramDouble3));

            for (int n = 0; n < localList.size(); n++)
                paramDouble2 = ((OAxisAlignedBB) localList.get(n)).b(aV, paramDouble2);
            aV.d(0.0D, paramDouble2, 0.0D);

            if ((!bc) && (d4 != paramDouble2))
                paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;

            for (int n = 0; n < localList.size(); n++)
                paramDouble1 = ((OAxisAlignedBB) localList.get(n)).a(aV, paramDouble1);
            aV.d(paramDouble1, 0.0D, 0.0D);

            if ((!bc) && (d3 != paramDouble1))
                paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;

            for (int n = 0; n < localList.size(); n++)
                paramDouble3 = ((OAxisAlignedBB) localList.get(n)).c(aV, paramDouble3);
            aV.d(0.0D, 0.0D, paramDouble3);

            if ((!bc) && (d5 != paramDouble3))
                paramDouble1 = paramDouble2 = paramDouble3 = 0.0D;

            if (d7 * d7 + d9 * d9 >= paramDouble1 * paramDouble1 + paramDouble3 * paramDouble3) {
                paramDouble1 = d7;
                paramDouble2 = d8;
                paramDouble3 = d9;
                aV.b(localOAxisAlignedBB2);
            } else
                bn = (float) (bn + 0.5D);

        }

        aL = ((aV.a + aV.d) / 2.0D);
        aM = (aV.b + be - bn);
        aN = ((aV.c + aV.f) / 2.0D);

        aX = ((d3 != paramDouble1) || (d5 != paramDouble3));
        aY = (d4 != paramDouble2);
        aW = ((d4 != paramDouble2) && (d4 < 0.0D));
        aZ = ((aX) || (aY));
        a(paramDouble2, aW);

        if (d3 != paramDouble1)
            aO = 0.0D;
        if (d4 != paramDouble2)
            aP = 0.0D;
        if (d5 != paramDouble3)
            aQ = 0.0D;

        double d7 = aL - d1;
        double d8 = aN - d2;

        if ((n()) && !i) {
            bi = (float) (bi + OMathHelper.a(d7 * d7 + d8 * d8) * 0.6D);
            int i1 = OMathHelper.b(aL);
            int i2 = OMathHelper.b(aM - 0.2D - be);
            int m = OMathHelper.b(aN);
            int n = aH.a(i1, i2, m);
            if ((bi > b) && (n > 0)) {
                b += 1;
                OStepSound localOStepSound = OBlock.m[n].bw;
                if (aH.a(i1, i2 + 1, m) == OBlock.aS.bl) {
                    localOStepSound = OBlock.aS.bw;
                    aH.a(this, localOStepSound.c(), localOStepSound.a() * 0.15F, localOStepSound.b());
                } else if (!OBlock.m[n].by.d())
                    aH.a(this, localOStepSound.c(), localOStepSound.a() * 0.15F, localOStepSound.b());
                OBlock.m[n].b(aH, i1, i2, m, this);
            }

        }

        int i1 = OMathHelper.b(aV.a);
        int i2 = OMathHelper.b(aV.b);
        int m = OMathHelper.b(aV.c);
        int n = OMathHelper.b(aV.d);
        int i3 = OMathHelper.b(aV.e);
        int i4 = OMathHelper.b(aV.f);

        if (aH.a(i1, i2, m, n, i3, i4))
            for (int i5 = i1; i5 <= n; i5++)
                for (int i6 = i2; i6 <= i3; i6++)
                    for (int i7 = m; i7 <= i4; i7++) {
                        int i8 = aH.a(i5, i6, i7);
                        if (i8 > 0)
                            OBlock.m[i8].a(aH, i5, i6, i7, this);
                    }
        bn *= 0.4F;

        boolean bool = Y();
        if (aH.d(aV)) {
            a(1);
            if (!bool) {
                bu += 1;
                if (bu == 0)
                    bu = 300;
            }
        } else if (bu <= 0)
            bu = (-bt);

        if ((bool) && (bu > 0)) {
            aH.a(this, "random.fizz", 0.7F, 1.6F + (br.nextFloat() - br.nextFloat()) * 0.4F);
            bu = (-bt);
        }
    }

    protected boolean n() {
        return true;
    }

    protected void a(double paramDouble, boolean paramBoolean) {
        if (paramBoolean) {
            if (bj > 0.0F) {
                a(bj);
                bj = 0.0F;
            }
        } else if (paramDouble < 0.0D)
            bj = (float) (bj - paramDouble);
    }

    public OAxisAlignedBB e_() {
        return null;
    }

    protected void a(int paramInt) {
        if (!bz)
            if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.FIRE, null, entity, paramInt))
                a((OEntity) null, paramInt);

    }

    protected void a(float paramFloat) {
    }

    public boolean Y() {
        return bw || aH.q(OMathHelper.b(aL), OMathHelper.b(aM), OMathHelper.b(aN));
    }

    public boolean Z() {
        return bw;
    }

    public boolean f_() {
        return aH.a(this.aV.b(0.0D, -0.4000000059604645D, 0.0D), OMaterial.f, this);
    }

    public boolean a(OMaterial paramOMaterial) {
        double d1 = aM + s();
        int i = OMathHelper.b(aL);
        int j = OMathHelper.d(OMathHelper.b(d1));
        int k = OMathHelper.b(aN);
        int m = aH.a(i, j, k);
        if ((m != 0) && (OBlock.m[m].by == paramOMaterial)) {
            float f1 = OBlockFluid.c(aH.b(i, j, k)) - 0.1111111F;
            float f2 = j + 1 - f1;
            return d1 < f2;
        }
        return false;
    }

    public float s() {
        return 0.0F;
    }

    public boolean aa() {
        return aH.a(aV.b(-0.1D, -0.4D, -0.1D), OMaterial.g);
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

        float f2 = OMathHelper.a(aR * 3.141593F / 180.0F);
        float f3 = OMathHelper.b(aR * 3.141593F / 180.0F);

        aO += paramFloat1 * f3 - paramFloat2 * f2;
        aQ += paramFloat2 * f3 + paramFloat1 * f2;
    }

    public float c(float paramFloat) {
        int i = OMathHelper.b(aL);

        double d1 = (aV.e - aV.b) * 0.66D;
        int j = OMathHelper.b(aM - be + d1);
        int k = OMathHelper.b(aN);
        if (aH.a(OMathHelper.b(aV.a), OMathHelper.b(aV.b), OMathHelper.b(aV.c), OMathHelper.b(aV.d), OMathHelper.b(aV.e), OMathHelper.b(aV.f)))
            return aH.l(i, j, k);
        return 0.0F;
    }

    public void b(double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2) {
        aI = (aL = paramDouble1);
        aJ = (aM = paramDouble2);
        aK = (aN = paramDouble3);
        aT = (aR = paramFloat1);
        aU = (aS = paramFloat2);
        bn = 0.0F;

        double d1 = aT - paramFloat1;
        if (d1 < -180.0D)
            aT += 360.0F;
        if (d1 >= 180.0D)
            aT -= 360.0F;
        a(aL, aM, aN);
        c(paramFloat1, paramFloat2);
    }

    public void c(double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2) {
        bk = (aI = aL = paramDouble1);
        bl = (aJ = aM = paramDouble2 + be);
        bm = (aK = aN = paramDouble3);
        aR = paramFloat1;
        aS = paramFloat2;
        a(aL, aM, aN);
    }

    public float f(OEntity paramOEntity) {
        float f1 = (float) (aL - paramOEntity.aL);
        float f2 = (float) (aM - paramOEntity.aM);
        float f3 = (float) (aN - paramOEntity.aN);
        return OMathHelper.c(f1 * f1 + f2 * f2 + f3 * f3);
    }

    public double d(double paramDouble1, double paramDouble2, double paramDouble3) {
        double d1 = aL - paramDouble1;
        double d2 = aM - paramDouble2;
        double d3 = aN - paramDouble3;
        return d1 * d1 + d2 * d2 + d3 * d3;
    }

    public double e(double paramDouble1, double paramDouble2, double paramDouble3) {
        double d1 = aL - paramDouble1;
        double d2 = aM - paramDouble2;
        double d3 = aN - paramDouble3;
        return OMathHelper.a(d1 * d1 + d2 * d2 + d3 * d3);
    }

    public double g(OEntity paramOEntity) {
        double d1 = aL - paramOEntity.aL;
        double d2 = aM - paramOEntity.aM;
        double d3 = aN - paramOEntity.aN;
        return d1 * d1 + d2 * d2 + d3 * d3;
    }

    public void b(OEntityPlayer paramOEntityPlayer) {
    }

    public void h(OEntity paramOEntity) {
        if ((paramOEntity.aF == this) || (paramOEntity.aG == this))
            return;

        double d1 = paramOEntity.aL - aL;
        double d2 = paramOEntity.aN - aN;

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

            d1 *= (1.0F - bq);
            d2 *= (1.0F - bq);

            f(-d1, 0.0D, -d2);
            paramOEntity.f(d1, 0.0D, d2);
        }
    }

    public void f(double paramDouble1, double paramDouble2, double paramDouble3) {
        aO += paramDouble1;
        aP += paramDouble2;
        aQ += paramDouble3;
    }

    protected void ab() {
        ba = true;
    }

    public boolean a(OEntity paramOEntity, int paramInt) {
        ab();
        return false;
    }

    public boolean o_() {
        return false;
    }

    public boolean d_() {
        return false;
    }

    public void c(OEntity paramOEntity, int paramInt) {
    }

    public boolean c(ONBTTagCompound paramONBTTagCompound) {
        String str = ac();
        if ((bd) || (str == null))
            return false;
        paramONBTTagCompound.a("id", str);
        d(paramONBTTagCompound);
        return true;
    }

    public void d(ONBTTagCompound paramONBTTagCompound) {
        paramONBTTagCompound.a("Pos", a(new double[] { aL, aM, aN }));
        paramONBTTagCompound.a("Motion", a(new double[] { aO, aP, aQ }));
        paramONBTTagCompound.a("Rotation", a(new float[] { aR, aS }));

        paramONBTTagCompound.a("FallDistance", bj);
        paramONBTTagCompound.a("Fire", (short) bu);
        paramONBTTagCompound.a("Air", (short) by);
        paramONBTTagCompound.a("OnGround", aW);

        b(paramONBTTagCompound);
    }

    public void e(ONBTTagCompound paramONBTTagCompound) {
        ONBTTagList localONBTTagList1 = paramONBTTagCompound.l("Pos");
        ONBTTagList localONBTTagList2 = paramONBTTagCompound.l("Motion");
        ONBTTagList localONBTTagList3 = paramONBTTagCompound.l("Rotation");
        a(0.0D, 0.0D, 0.0D);

        aO = ((ONBTTagDouble) localONBTTagList2.a(0)).a;
        aP = ((ONBTTagDouble) localONBTTagList2.a(1)).a;
        aQ = ((ONBTTagDouble) localONBTTagList2.a(2)).a;

        if (Math.abs(aO) > 10.0D)
            aO = 0.0D;
        if (Math.abs(aP) > 10.0D)
            aP = 0.0D;
        if (Math.abs(aQ) > 10.0D)
            aQ = 0.0D;

        aI = (bk = aL = ((ONBTTagDouble) localONBTTagList1.a(0)).a);
        aJ = (bl = aM = ((ONBTTagDouble) localONBTTagList1.a(1)).a);
        aK = (bm = aN = ((ONBTTagDouble) localONBTTagList1.a(2)).a);

        aT = (aR = ((ONBTTagFloat) localONBTTagList3.a(0)).a % 6.283186F);
        aU = (aS = ((ONBTTagFloat) localONBTTagList3.a(1)).a % 6.283186F);

        bj = paramONBTTagCompound.g("FallDistance");
        bu = paramONBTTagCompound.d("Fire");
        by = paramONBTTagCompound.d("Air");
        aW = paramONBTTagCompound.m("OnGround");

        a(aL, aM, aN);

        a(paramONBTTagCompound);
    }

    protected final String ac() {
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
        OEntityItem localOEntityItem = new OEntityItem(aH, aL, aM + paramFloat, aN, paramOItemStack);
        localOEntityItem.c = 10;
        aH.b(localOEntityItem);
        return localOEntityItem;
    }

    public boolean P() {
        return !bd;
    }

    public boolean H() {
        int i = OMathHelper.b(aL);
        int j = OMathHelper.b(aM + s());
        int k = OMathHelper.b(aN);
        return aH.d(i, j, k);
    }

    public boolean a(OEntityPlayer paramOEntityPlayer) {
        return false;
    }

    public OAxisAlignedBB a_(OEntity paramOEntity) {
        return null;
    }

    public void B() {
        if (aG.bd) {
            aG = null;
            return;
        }
        aO = 0.0D;
        aP = 0.0D;
        aQ = 0.0D;
        p_();
        aG.f();

        e += aG.aR - aG.aT;
        d += aG.aS - aG.aU;

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

        aR = (float) (aR + d1);
        aS = (float) (aS + d2);
    }

    public void f() {
        aF.a(aL, aM + m() + aF.F(), aN);
    }

    public double F() {
        return be;
    }

    public double m() {
        return bg * 0.75D;
    }

    public void b(OEntity paramOEntity) {
        d = 0.0D;
        e = 0.0D;

        if (paramOEntity == null) {
            if (aG != null) {
                c(aG.aL, aG.aV.b + aG.bg, aG.aN, aR, aS);
                aG.aF = null;
            }
            aG = null;
            return;
        }
        if (aG == paramOEntity) {
            aG.aF = null;
            aG = null;
            c(paramOEntity.aL, paramOEntity.aV.b + paramOEntity.bg, paramOEntity.aN, aR, aS);
            return;
        }
        if (aG != null)
            aG.aF = null;
        if (paramOEntity.aF != null)
            paramOEntity.aF.aG = null;
        aG = paramOEntity;
        paramOEntity.aF = this;
    }

    public OVec3D V() {
        return null;
    }

    public void ad() {
    }

    public OItemStack[] i_() {
        return null;
    }

    public boolean ae() {
        return d(1);
    }

    public void e(boolean paramBoolean) {
        a(1, paramBoolean);
    }

    protected boolean d(int paramInt) {
        return (bA.a(0) & 1 << paramInt) != 0;
    }

    protected void a(int paramInt, boolean paramBoolean) {
        int i = bA.a(0);
        if (paramBoolean)
            bA.b(0, Byte.valueOf((byte) (i | 1 << paramInt)));
        else
            bA.b(0, Byte.valueOf((byte) (i & ~(1 << paramInt))));
    }

    public void a(OEntityLightningBolt var1) {
        this.a(5);
        ++this.bu;
        if (this.bu == 0)
            this.bu = 300;
    }

    public void a(OEntityLiving var1) {
    }

}
