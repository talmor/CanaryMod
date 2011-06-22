import java.util.List;

public abstract class OEntityLiving extends OEntity {
    public int        H  = 20;
    public float      I;
    public float      J;
    public float      K  = 0.0F;
    public float      L  = 0.0F;
    protected float   M;
    protected float   N;
    protected float   O;
    protected float   P;
    protected boolean Q  = true;
    protected String  R  = "/mob/char.png";
    protected boolean S  = true;
    protected float   T  = 0.0F;
    protected String  U  = null;
    protected float   V  = 1.0F;
    protected int     W  = 0;
    protected float   X  = 0.0F;
    public boolean    Y  = false;
    public float      Z;
    public float      aa;
    public int        ab;
    public int        ac;
    private int       a;
    public int        ad;
    public int        ae;
    public float      af = 0.0F;
    public int        ag = 0;
    public int        ah = 0;
    public float      ai;
    public float      aj;
    protected boolean ak = false;
    public int        al = -1;
    public float      am = (float) (Math.random() * 0.8999999761581421D + 0.1000000014901161D);
    public float      an;
    public float      ao;
    public float      ap;
    protected int     aq;
    protected double  ar;
    protected double  as;
    protected double  at;
    protected double  au;
    protected double  av;
    float             aw = 0.0F;

    protected int     ax = 0;

    protected int     ay = 0;
    protected float   az;
    protected float   aA;
    protected float   aB;
    protected boolean aC = false;
    protected float   aD = 0.0F;
    protected float   aE = 0.7F;
    private OEntity   b;
    // CanaryMod Start
    LivingEntity      entity = new LivingEntity(this);

    // CanaryMod end
    protected int     aF = 0;

    public OEntityLiving(OWorld paramOWorld) {
        super(paramOWorld);
        ab = 10;
        aI = true;

        J = ((float) (Math.random() + 1.0D) * 0.01F);
        a(aP, aQ, aR);
        I = ((float) Math.random() * 12398.0F);
        aV = (float) (Math.random() * 3.141592741012573D * 2.0D);

        bs = 0.5F;
    }

    @Override
    protected void b() {
    }

    public boolean e(OEntity paramOEntity) {
        return aL.a(OVec3D.b(aP, aQ + s(), aR), OVec3D.b(paramOEntity.aP, paramOEntity.aQ + paramOEntity.s(), paramOEntity.aR)) == null;
    }

    @Override
    public boolean n_() {
        return !bh;
    }

    @Override
    public boolean d_() {
        return !bh;
    }

    @Override
    public float s() {
        return bk * 0.85F;
    }

    public int e() {
        return 80;
    }

    public void P() {
        String str = g();
        if (str != null)
            aL.a(this, str, k(), (bv.nextFloat() - bv.nextFloat()) * 0.2F + 1.0F);
    }

    @Override
    public void Q() {
        Z = aa;
        super.Q();

        if (bv.nextInt(1000) < a++) {
            a = (-e());
            P();
        }

        if ((S()) && (J()))
            // CanaryMod Damage hook: Suffocation
            if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.SUFFOCATION, null, entity, 1))
                a((OEntity) null, 1);


        if ((bD) || (aL.B))
            by = 0;
        int i;
        if ((S()) && (a(OMaterial.g)) && (!b_())) {
            bC -= 1;
            if (bC == -20) {
                bC = 0;
                for (i = 0; i < 8; i++) {
                    float f1 = bv.nextFloat() - bv.nextFloat();
                    float f2 = bv.nextFloat() - bv.nextFloat();
                    float f3 = bv.nextFloat() - bv.nextFloat();
                    aL.a("bubble", aP + f1, aQ + f2, aR + f3, aS, aT, aU);
                }
                // CanaryMod Damage hook: Drowning
                if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.WATER, null, entity, 2))
                    a((OEntity) null, 2);

            }

            by = 0;
        } else
            bC = bz;

        ai = aj;

        if (ah > 0)
            ah -= 1;
        if (ad > 0)
            ad -= 1;
        if (bB > 0)
            bB -= 1;
        if (ab <= 0) {
            ag += 1;
            if (ag > 20) {
                W();
                I();
                for (i = 0; i < 20; i++) {
                    double d1 = bv.nextGaussian() * 0.02D;
                    double d2 = bv.nextGaussian() * 0.02D;
                    double d3 = bv.nextGaussian() * 0.02D;
                    aL.a("explode", aP + bv.nextFloat() * bj * 2.0F - bj, aQ + bv.nextFloat() * bk, aR + bv.nextFloat() * bj * 2.0F - bj, d1, d2, d3);
                }
            }
        }

        P = O;
        L = K;

        aX = aV;
        aY = aW;
    }

    public void R() {
        for (int i = 0; i < 20; i++) {
            double d1 = bv.nextGaussian() * 0.02D;
            double d2 = bv.nextGaussian() * 0.02D;
            double d3 = bv.nextGaussian() * 0.02D;
            double d4 = 10.0D;
            aL.a("explode", aP + bv.nextFloat() * bj * 2.0F - bj - d1 * d4, aQ + bv.nextFloat() * bk - d2 * d4, aR + bv.nextFloat() * bj * 2.0F - bj - d3 * d4, d1, d2, d3);
        }
    }

    @Override
    public void D() {
        super.D();
        M = N;
        N = 0.0F;
    }

    @Override
    public void o_() {
        super.o_();

        u();

        double d1 = aP - aM;
        double d2 = aR - aO;

        float f1 = OMathHelper.a(d1 * d1 + d2 * d2);

        float f2 = K;

        float f3 = 0.0F;
        M = N;
        float f4 = 0.0F;
        if (f1 > 0.05F) {
            f4 = 1.0F;
            f3 = f1 * 3.0F;
            f2 = (float) Math.atan2(d2, d1) * 180.0F / 3.141593F - 90.0F;
        }
        if (aa > 0.0F)
            f2 = aV;
        if (!ba)
            f4 = 0.0F;
        N += (f4 - N) * 0.3F;

        float f5 = f2 - K;
        while (f5 < -180.0F)
            f5 += 360.0F;
        while (f5 >= 180.0F)
            f5 -= 360.0F;
        K += f5 * 0.3F;

        float f6 = aV - K;
        while (f6 < -180.0F)
            f6 += 360.0F;
        while (f6 >= 180.0F)
            f6 -= 360.0F;
        int i = (f6 < -90.0F) || (f6 >= 90.0F) ? 1 : 0;
        if (f6 < -75.0F)
            f6 = -75.0F;
        if (f6 >= 75.0F)
            f6 = 75.0F;
        K = (aV - f6);
        if (f6 * f6 > 2500.0F)
            K += f6 * 0.2F;

        if (i != 0)
            f3 *= -1.0F;
        while (aV - aX < -180.0F)
            aX -= 360.0F;
        while (aV - aX >= 180.0F)
            aX += 360.0F;
        while (K - L < -180.0F)
            L -= 360.0F;
        while (K - L >= 180.0F)
            L += 360.0F;
        while (aW - aY < -180.0F)
            aY -= 360.0F;
        while (aW - aY >= 180.0F)
            aY += 360.0F;
        O += f3;
    }

    @Override
    protected void b(float paramFloat1, float paramFloat2) {
        super.b(paramFloat1, paramFloat2);
    }

    public void b(int paramInt) {
        if (ab <= 0)
            return;
        ab += paramInt;
        if (ab > 20)
            ab = 20;
        bB = (H / 2);
    }

    @Override
    public boolean a(OEntity paramOEntity, int paramInt) {
        if (aL.B)
            return false;
        ay = 0;
        if (ab <= 0)
            return false;

        ao = 1.5F;

        // CanaryMod damage entities.
        LivingEntity attacker = (paramOEntity != null && paramOEntity instanceof OEntityLiving) ? new LivingEntity((OEntityLiving) paramOEntity) : null;

        // CanaryMod attack by entity, but it might not do damage!
        if (attacker != null && (Boolean) manager.callHook(PluginLoader.Hook.ATTACK, attacker, entity, paramInt))
            return false;


        int i = 1;
        if (bB > H / 2.0F) {
            if (paramInt <= ax)
                return false;
            // CanaryMod: partial damage
            if (attacker != null && (Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.ENTITY, attacker, entity, paramInt - ax))
                    return false;

            c(paramInt - ax);
            ax = paramInt;
            i = 0;
        } else {
            // CanaryMod: full damage
            if (attacker != null && (Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.ENTITY, attacker, entity, paramInt))
                return false;

            ax = paramInt;
            ac = ab;
            bB = H;
            c(paramInt);
            ad = (ae = 10);
        }

        af = 0.0F;

        if (i != 0) {
            // CanaryMod: forced cast to send 'damage animation'.
            aL.a(this, (byte)2);
            ae();
            if (paramOEntity != null) {
                double d1 = paramOEntity.aP - aP;
                double d2 = paramOEntity.aR - aR;
                while (d1 * d1 + d2 * d2 < 0.0001D) {
                    d1 = (Math.random() - Math.random()) * 0.01D;
                    d2 = (Math.random() - Math.random()) * 0.01D;
                }
                af = ((float) (Math.atan2(d2, d1) * 180.0D / 3.141592741012573D) - aV);
                a(paramOEntity, paramInt, d1, d2);
            } else
                af = ((int) (Math.random() * 2.0D) * 180);
        }

        if (ab <= 0) {
            if (i != 0)
                aL.a(this, i(), k(), (bv.nextFloat() - bv.nextFloat()) * 0.2F + 1.0F);
            a(paramOEntity);
        } else if (i != 0)
            aL.a(this, h(), k(), (bv.nextFloat() - bv.nextFloat()) * 0.2F + 1.0F);

        return true;
    }

    protected void c(int paramInt) {
        ab -= paramInt;
    }

    protected float k() {
        return 1.0F;
    }

    protected String g() {
        return null;
    }

    protected String h() {
        return "random.hurt";
    }

    protected String i() {
        return "random.hurt";
    }

    public void a(OEntity paramOEntity, int paramInt, double paramDouble1, double paramDouble2) {
        float f1 = OMathHelper.a(paramDouble1 * paramDouble1 + paramDouble2 * paramDouble2);
        float f2 = 0.4F;

        aS /= 2.0D;
        aT /= 2.0D;
        aU /= 2.0D;

        aS -= paramDouble1 / f1 * f2;
        aT += 0.4D;
        aU -= paramDouble2 / f1 * f2;

        if (aT > 0.4D)
            aT = 0.4D;
    }

    public void a(OEntity paramOEntity) {
        if ((W >= 0) && (paramOEntity != null))
            paramOEntity.c(this, W);

        if (paramOEntity != null)
            paramOEntity.a(this);

        ak = true;

        if (!aL.B)
            r();
        // CanaryMod: Forced cast to play Death Animations.
        aL.a(this, (byte)3);
    }

    protected void r() {
        int i = j();
        if (i > 0) {
            int j = bv.nextInt(3);
            for (int k = 0; k < j; k++)
                b(i, 1);
        }
    }

    protected int j() {
        return 0;
    }

    @Override
    protected void a(float paramFloat) {
        super.a(paramFloat);
        int i = (int) Math.ceil(paramFloat - 3.0F);
        if (i > 0) {
            // CanaryMod Damage hook: Falling
            if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.FALL, null, entity, i))
                a((OEntity) null, i);


            int j = aL.a(OMathHelper.b(aP), OMathHelper.b(aQ - 0.2D - bi), OMathHelper.b(aR));
            if (j > 0) {
                OStepSound localOStepSound = OBlock.m[j].by;
                aL.a(this, localOStepSound.c(), localOStepSound.a() * 0.5F, localOStepSound.b() * 0.75F);
            }
        }
    }

    public void a(float paramFloat1, float paramFloat2) {
        if (ac()) {
            double d1 = aQ;
            a(paramFloat1, paramFloat2, 0.02F);
            c(aS, aT, aU);

            aS *= 0.8D;
            aT *= 0.8D;
            aU *= 0.8D;
            aT -= 0.02D;

            if ((bb) && (b(aS, aT + 0.6D - aQ + d1, aU)))
                aT = 0.3D;
        } else if (ad()) {
            double d1 = aQ;
            a(paramFloat1, paramFloat2, 0.02F);
            c(aS, aT, aU);
            aS *= 0.5D;
            aT *= 0.5D;
            aU *= 0.5D;
            aT -= 0.02D;

            if ((bb) && (b(aS, aT + 0.6000000238418579D - aQ + d1, aU)))
                aT = 0.300000011920929D;
        } else {
            float f1 = 0.91F;
            if (ba) {
                f1 = 0.546F;
                int i = aL.a(OMathHelper.b(aP), OMathHelper.b(aZ.b) - 1, OMathHelper.b(aR));
                if (i > 0)
                    f1 = OBlock.m[i].bB * 0.91F;
            }

            float f2 = 0.1627714F / (f1 * f1 * f1);
            a(paramFloat1, paramFloat2, ba ? 0.1F * f2 : 0.02F);

            f1 = 0.91F;
            if (ba) {
                f1 = 0.546F;
                int j = aL.a(OMathHelper.b(aP), OMathHelper.b(aZ.b) - 1, OMathHelper.b(aR));
                if (j > 0)
                    f1 = OBlock.m[j].bB * 0.91F;
            }

            if (p()) {
                float var12 = 0.15F;
                if (this.aS < (double) (-var12))
                    this.aS = (double) (-var12);

                if (this.aS > (double) var12)
                    this.aS = (double) var12;

                if (this.aU < (double) (-var12))
                    this.aU = (double) (-var12);
                if (this.aU > (double) var12)
                    this.aU = (double) var12;

                bn = 0.0F;
                if (aT < -0.15D)
                    aT = -0.15D;
                if ((ag()) && (aT < 0.0D))
                    aT = 0.0D;

            }

            c(aS, aT, aU);

            if ((bb) && (p()))
                aT = 0.2D;

            aT -= 0.08D;
            aT *= 0.98D;
            aS *= f1;
            aU *= f1;
        }
        an = ao;
        double d1 = aP - aM;
        double d2 = aR - aO;
        float f3 = OMathHelper.a(d1 * d1 + d2 * d2) * 4.0F;
        if (f3 > 1.0F)
            f3 = 1.0F;
        ao += (f3 - ao) * 0.4F;
        ap += ao;
    }

    public boolean p() {
        int i = OMathHelper.b(aP);
        int j = OMathHelper.b(aZ.b);
        int k = OMathHelper.b(aR);
        return aL.a(i, j, k) == OBlock.aG.bn;
    }

    @Override
    public void b(ONBTTagCompound paramONBTTagCompound) {
        paramONBTTagCompound.a("Health", (short) ab);
        paramONBTTagCompound.a("HurtTime", (short) ad);
        paramONBTTagCompound.a("DeathTime", (short) ag);
        paramONBTTagCompound.a("AttackTime", (short) ah);
    }

    @Override
    public void a(ONBTTagCompound paramONBTTagCompound) {
        ab = paramONBTTagCompound.d("Health");
        if (!paramONBTTagCompound.b("Health"))
            ab = 10;
        ad = paramONBTTagCompound.d("HurtTime");
        ag = paramONBTTagCompound.d("DeathTime");
        ah = paramONBTTagCompound.d("AttackTime");
    }

    @Override
    public boolean S() {
        return (!bh) && (ab > 0);
    }

    public boolean b_() {
        return false;
    }

    public void u() {
        if (aq > 0) {
            double d1 = aP + (ar - aP) / aq;
            double d2 = aQ + (as - aQ) / aq;
            double d3 = aR + (at - aR) / aq;

            double d4 = au - aV;
            while (d4 < -180.0D)
                d4 += 360.0D;
            while (d4 >= 180.0D)
                d4 -= 360.0D;
            aV = (float) (aV + d4 / aq);
            aW = (float) (aW + (av - aW) / aq);

            aq -= 1;
            a(d1, d2, d3);
            c(aV, aW);

            List var9 = this.aL.a((OEntity) this, this.aZ.e(0.03125D, 0.0D, 0.03125D));
            if (var9.size() > 0) {
                double var10 = 0.0D;

                for (int var12 = 0; var12 < var9.size(); ++var12) {
                    OAxisAlignedBB var13 = (OAxisAlignedBB) var9.get(var12);
                    if (var13.e > var10)
                        var10 = var13.e;
                }

                d2 += var10 - this.aZ.b;
                this.a(d1, d2, d3);
            }

        }

        if (C()) {
            aC = false;
            az = 0.0F;
            aA = 0.0F;
            aB = 0.0F;
        } else if (!Y)
            c_();

        boolean bool1 = ac();
        boolean bool2 = ad();

        if (aC)
            if (bool1)
                aT += 0.04D;
            else if (bool2)
                aT += 0.04D;
            else if (ba)
                N();

        az *= 0.98F;
        aA *= 0.98F;
        aB *= 0.9F;
        a(az, aA);

        List localList = aL.b(this, aZ.b(0.2D, 0.0D, 0.2D));
        if ((localList != null) && (localList.size() > 0))
            for (int i = 0; i < localList.size(); i++) {
                OEntity localOEntity = (OEntity) localList.get(i);
                if (!localOEntity.d_())
                    continue;
                localOEntity.h(this);
            }
    }

    protected boolean C() {
        return ab <= 0;
    }

    protected void N() {
        aT = 0.42D;
    }

    protected boolean l_() {
        return true;
    }

    protected void T() {
        OEntityPlayer localOEntityPlayer = aL.a(this, -1.0D);

        if ((l_()) && (localOEntityPlayer != null)) {
            double d1 = localOEntityPlayer.aP - aP;
            double d2 = localOEntityPlayer.aQ - aQ;
            double d3 = localOEntityPlayer.aR - aR;
            double d4 = d1 * d1 + d2 * d2 + d3 * d3;

            if (d4 > 16384.0D)
                I();

            if ((ay > 600) && (bv.nextInt(800) == 0))
                if (d4 < 1024.0D)
                    ay = 0;
                else
                    I();
        }
    }

    protected void c_() {
        ++ay;
        OEntityPlayer localOEntityPlayer = aL.a(this, -1.0D);
        T();

        az = 0.0F;
        aA = 0.0F;

        float f = 8.0F;
        if (bv.nextFloat() < 0.02F) {
            localOEntityPlayer = aL.a(this, f);
            if (localOEntityPlayer != null) {
                b = localOEntityPlayer;
                aF = (10 + bv.nextInt(20));
            } else
                aB = ((bv.nextFloat() - 0.5F) * 20.0F);
        }

        if (b != null) {
            a(b, 10.0F, v());
            if ((aF-- <= 0) || (b.bh) || (b.g(this) > f * f))
                b = null;
        } else {
            if (bv.nextFloat() < 0.05F)
                aB = ((bv.nextFloat() - 0.5F) * 20.0F);
            aV += aB;
            aW = aD;
        }

        boolean bool1 = ac();
        boolean bool2 = ad();
        if ((bool1) || (bool2))
            aC = (bv.nextFloat() < 0.8F);
    }

    protected int v() {
        return 40;
    }

    public void a(OEntity paramOEntity, float paramFloat1, float paramFloat2) {
        double d1 = paramOEntity.aP - aP;

        double d2 = paramOEntity.aR - aR;
        double d3;
        if ((paramOEntity instanceof OEntityLiving)) {
            OEntityLiving localOEntityLiving = (OEntityLiving) paramOEntity;
            d3 = aQ + s() - (localOEntityLiving.aQ + localOEntityLiving.s());
        } else
            d3 = (paramOEntity.aZ.b + paramOEntity.aZ.e) / 2.0D - (aQ + s());

        double d4 = OMathHelper.a(d1 * d1 + d2 * d2);

        float f1 = (float) (Math.atan2(d2, d1) * 180.0D / 3.141592741012573D) - 90.0F;
        float f2 = (float) (-(Math.atan2(d3, d4) * 180.0D / 3.141592741012573D));
        aW = (-b(aW, f2, paramFloat2));
        aV = b(aV, f1, paramFloat1);
    }

    public boolean U() {
        return b != null;
    }

    public OEntity V() {
        return b;
    }

    private float b(float paramFloat1, float paramFloat2, float paramFloat3) {
        float f = paramFloat2 - paramFloat1;
        while (f < -180.0F)
            f += 360.0F;
        while (f >= 180.0F)
            f -= 360.0F;
        if (f > paramFloat3)
            f = paramFloat3;
        if (f < -paramFloat3)
            f = -paramFloat3;
        return paramFloat1 + f;
    }

    public void W() {
    }

    public boolean d() {
        return (aL.a(aZ)) && (aL.a(this, aZ).isEmpty()) && (!aL.c(aZ));
    }

    @Override
    protected void X() {
        a((OEntity)null, 4);
    }

    @Override
    public OVec3D Y() {
        return b(1.0F);
    }

    public OVec3D b(float paramFloat) {
        if (paramFloat == 1.0F) {
            float f1 = OMathHelper.b(-aV * 0.01745329F - 3.141593F);
            float f2 = OMathHelper.a(-aV * 0.01745329F - 3.141593F);
            float f3 = -OMathHelper.b(-aW * 0.01745329F);
            float f4 = OMathHelper.a(-aW * 0.01745329F);

            return OVec3D.b(f2 * f3, f4, f1 * f3);
        }
        float f1 = aY + (aW - aY) * paramFloat;
        float f2 = aX + (aV - aX) * paramFloat;

        float f3 = OMathHelper.b(-f2 * 0.01745329F - 3.141593F);
        float f4 = OMathHelper.a(-f2 * 0.01745329F - 3.141593F);
        float f5 = -OMathHelper.b(-f1 * 0.01745329F);
        float f6 = OMathHelper.a(-f1 * 0.01745329F);

        return OVec3D.b(f4 * f5, f6, f3 * f5);
    }

    public int l() {
        return 4;
    }

    public boolean K() {
        return false;
    }
}
