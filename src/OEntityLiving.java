import java.util.List;

public abstract class OEntityLiving extends OEntity {
    public int        D  = 20;
    public float      E;
    public float      F;
    public float      G  = 0.0F;
    public float      H  = 0.0F;
    protected float   I;
    protected float   J;
    protected float   K;
    protected float   L;
    protected boolean M  = true;
    protected String  N  = "/mob/char.png";
    protected boolean O  = true;
    protected float   P  = 0.0F;
    protected String  Q  = null;
    protected float   R  = 1.0F;
    protected int     S  = 0;
    protected float   T  = 0.0F;
    public boolean    U  = false;
    public float      V;
    public float      W;
    public int        X;
    public int        Y;
    private int       a;
    public int        Z;
    public int        aa;
    public float      ab = 0.0F;
    public int        ac = 0;
    public int        ad = 0;
    public float      ae;
    public float      af;
    protected boolean ag = false;
    public int        ah = -1;
    public float      ai = (float) (Math.random() * 0.8999999761581421D + 0.1000000014901161D);
    public float      aj;
    public float      ak;
    public float      al;
    protected int     am;
    protected double  an;
    protected double  ao;
    protected double  ap;
    protected double  aq;
    protected double  ar;
    float             as = 0.0F;

    protected int     at = 0;

    protected int     au = 0;
    protected float   av;
    protected float   aw;
    protected float   ax;
    protected boolean ay = false;
    protected float   az = 0.0F;
    protected float   aA = 0.7F;
    private OEntity   b;
    // CanaryMod Start
    LivingEntity      entity = new LivingEntity(this);

    // CanaryMod end
    protected int     aB = 0;

    public OEntityLiving(OWorld paramOWorld) {
        super(paramOWorld);
        X = 10;
        aE = true;

        F = ((float) (Math.random() + 1.0D) * 0.01F);
        a(aL, aM, aN);
        E = ((float) Math.random() * 12398.0F);
        aR = (float) (Math.random() * 3.141592741012573D * 2.0D);

        bo = 0.5F;
    }

    @Override
    protected void b() {
    }

    public boolean e(OEntity paramOEntity) {
        return aH.a(OVec3D.b(aL, aM + s(), aN), OVec3D.b(paramOEntity.aL, paramOEntity.aM + paramOEntity.s(), paramOEntity.aN)) == null;
    }

    @Override
    public boolean o_() {
        return !bd;
    }

    @Override
    public boolean d_() {
        return !bd;
    }

    @Override
    public float s() {
        return bg * 0.85F;
    }

    public int e() {
        return 80;
    }

    public void M() {
        String str = g();
        if (str != null)
            aH.a(this, str, k(), (br.nextFloat() - br.nextFloat()) * 0.2F + 1.0F);
    }

    @Override
    public void N() {
        V = W;
        super.N();

        if (br.nextInt(1000) < a++) {
            a = (-e());
            M();
        }

        if ((P()) && (H()))
            // CanaryMod Damage hook: Suffocation
            if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.SUFFOCATION, null, entity, 1))
                a((OEntity) null, 1);


        if ((bz) || (aH.v))
            bu = 0;
        int i;
        if ((P()) && (a(OMaterial.f)) && (!b_())) {
            by -= 1;
            if (by == -20) {
                by = 0;
                for (i = 0; i < 8; i++) {
                    float f1 = br.nextFloat() - br.nextFloat();
                    float f2 = br.nextFloat() - br.nextFloat();
                    float f3 = br.nextFloat() - br.nextFloat();
                    aH.a("bubble", aL + f1, aM + f2, aN + f3, aO, aP, aQ);
                }
                // CanaryMod Damage hook: Drowning
                if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.WATER, null, entity, 2))
                    a((OEntity) null, 2);

            }

            bu = 0;
        } else
            by = bv;

        ae = af;

        if (ad > 0)
            ad -= 1;
        if (Z > 0)
            Z -= 1;
        if (bx > 0)
            bx -= 1;
        if (X <= 0) {
            ac += 1;
            if (ac > 20) {
                T();
                G();
                for (i = 0; i < 20; i++) {
                    double d1 = br.nextGaussian() * 0.02D;
                    double d2 = br.nextGaussian() * 0.02D;
                    double d3 = br.nextGaussian() * 0.02D;
                    aH.a("explode", aL + br.nextFloat() * bf * 2.0F - bf, aM + br.nextFloat() * bg, aN + br.nextFloat() * bf * 2.0F - bf, d1, d2, d3);
                }
            }
        }

        L = K;

        H = G;
        aT = aR;
        aU = aS;
    }

    public void O() {
        for (int i = 0; i < 20; i++) {
            double d1 = br.nextGaussian() * 0.02D;
            double d2 = br.nextGaussian() * 0.02D;
            double d3 = br.nextGaussian() * 0.02D;
            double d4 = 10.0D;
            aH.a("explode", aL + br.nextFloat() * bf * 2.0F - bf - d1 * d4, aM + br.nextFloat() * bg - d2 * d4, aN + br.nextFloat() * bf * 2.0F - bf - d3 * d4, d1, d2, d3);
        }
    }

    @Override
    public void B() {
        super.B();
        I = J;
        J = 0.0F;
    }

    @Override
    public void p_() {
        super.p_();

        u();

        double d1 = aL - aI;
        double d2 = aN - aK;

        float f1 = OMathHelper.a(d1 * d1 + d2 * d2);

        float f2 = G;

        float f3 = 0.0F;
        I = J;
        float f4 = 0.0F;
        if (f1 > 0.05F) {
            f4 = 1.0F;
            f3 = f1 * 3.0F;
            f2 = (float) Math.atan2(d2, d1) * 180.0F / 3.141593F - 90.0F;
        }
        if (W > 0.0F)
            f2 = aR;
        if (!aW)
            f4 = 0.0F;
        J += (f4 - J) * 0.3F;

        float f5 = f2 - G;
        while (f5 < -180.0F)
            f5 += 360.0F;
        while (f5 >= 180.0F)
            f5 -= 360.0F;
        G += f5 * 0.3F;

        float f6 = aR - G;
        while (f6 < -180.0F)
            f6 += 360.0F;
        while (f6 >= 180.0F)
            f6 -= 360.0F;
        int i = (f6 < -90.0F) || (f6 >= 90.0F) ? 1 : 0;
        if (f6 < -75.0F)
            f6 = -75.0F;
        if (f6 >= 75.0F)
            f6 = 75.0F;
        G = (aR - f6);
        if (f6 * f6 > 2500.0F)
            G += f6 * 0.2F;

        if (i != 0)
            f3 *= -1.0F;
        while (aR - aT < -180.0F)
            aT -= 360.0F;
        while (aR - aT >= 180.0F)
            aT += 360.0F;
        while (G - H < -180.0F)
            H -= 360.0F;
        while (G - H >= 180.0F)
            H += 360.0F;
        while (aS - aU < -180.0F)
            aU -= 360.0F;
        while (aS - aU >= 180.0F)
            aU += 360.0F;
        K += f3;
    }

    @Override
    protected void b(float paramFloat1, float paramFloat2) {
        super.b(paramFloat1, paramFloat2);
    }

    public void b(int paramInt) {
        if (X <= 0)
            return;
        X += paramInt;
        if (X > 20)
            X = 20;
        bx = (D / 2);
    }

    @Override
    public boolean a(OEntity paramOEntity, int paramInt) {
        if (aH.v)
            return false;
        au = 0;
        if (X <= 0)
            return false;

        ak = 1.5F;

        // CanaryMod damage entities.
        LivingEntity attacker = (paramOEntity != null && paramOEntity instanceof OEntityLiving) ? new LivingEntity((OEntityLiving) paramOEntity) : null;

        // CanaryMod attack by entity, but it might not do damage!
        if (attacker != null && (Boolean) manager.callHook(PluginLoader.Hook.ATTACK, attacker, entity, paramInt))
            return false;


        int i = 1;
        if (bx > D / 2.0F) {
            if (paramInt <= at)
                return false;
            // CanaryMod: partial damage
            if (attacker != null && (Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.ENTITY, attacker, entity, paramInt - at))
                    return false;

            c(paramInt - at);
            at = paramInt;
            i = 0;
        } else {
            // CanaryMod: full damage
            if (attacker != null && (Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.ENTITY, attacker, entity, paramInt))
                return false;

            at = paramInt;
            Y = X;
            bx = D;
            c(paramInt);
            Z = (aa = 10);
        }

        ab = 0.0F;

        if (i != 0) {
            // CanaryMod: forced cast to send 'damage animation'.
            aH.a(this, (byte)2);
            ab();
            if (paramOEntity != null) {
                double d1 = paramOEntity.aL - aL;
                double d2 = paramOEntity.aN - aN;
                while (d1 * d1 + d2 * d2 < 0.0001D) {
                    d1 = (Math.random() - Math.random()) * 0.01D;
                    d2 = (Math.random() - Math.random()) * 0.01D;
                }
                ab = ((float) (Math.atan2(d2, d1) * 180.0D / 3.141592741012573D) - aR);
                a(paramOEntity, paramInt, d1, d2);
            } else
                ab = ((int) (Math.random() * 2.0D) * 180);
        }

        if (X <= 0) {
            if (i != 0)
                aH.a(this, i(), k(), (br.nextFloat() - br.nextFloat()) * 0.2F + 1.0F);
            a(paramOEntity);
        } else if (i != 0)
            aH.a(this, h(), k(), (br.nextFloat() - br.nextFloat()) * 0.2F + 1.0F);

        return true;
    }

    protected void c(int paramInt) {
        X -= paramInt;
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

        aO /= 2.0D;
        aP /= 2.0D;
        aQ /= 2.0D;

        aO -= paramDouble1 / f1 * f2;
        aP += 0.4D;
        aQ -= paramDouble2 / f1 * f2;

        if (aP > 0.4D)
            aP = 0.4D;
    }

    public void a(OEntity paramOEntity) {
        if ((S >= 0) && (paramOEntity != null))
            paramOEntity.c(this, S);

        if (paramOEntity != null)
            paramOEntity.a(this);

        ag = true;

        if (!aH.v)
            r();
        // CanaryMod: Forced cast to play Death Animations.
        aH.a(this, (byte)3);
    }

    protected void r() {
        int i = j();
        if (i > 0) {
            int j = br.nextInt(3);
            for (int k = 0; k < j; k++)
                b(i, 1);
        }
    }

    protected int j() {
        return 0;
    }

    @Override
    protected void a(float paramFloat) {
        int i = (int) Math.ceil(paramFloat - 3.0F);
        if (i > 0) {
            // CanaryMod Damage hook: Falling
            if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.FALL, null, entity, i))
                a((OEntity) null, i);


            int j = aH.a(OMathHelper.b(aL), OMathHelper.b(aK - 0.2D - be), OMathHelper.b(aN));
            if (j > 0) {
                OStepSound localOStepSound = OBlock.m[j].bw;
                aH.a(this, localOStepSound.c(), localOStepSound.a() * 0.5F, localOStepSound.b() * 0.75F);
            }
        }
    }

    public void a(float paramFloat1, float paramFloat2) {
        if (Z()) {
            double d1 = aM;
            a(paramFloat1, paramFloat2, 0.02F);
            c(aO, aP, aQ);

            aO *= 0.8D;
            aP *= 0.8D;
            aQ *= 0.8D;
            aP -= 0.02D;

            if ((aX) && (b(aO, aP + 0.6D - aM + d1, aQ)))
                aP = 0.3D;
        } else if (aa()) {
            double d1 = aM;
            a(paramFloat1, paramFloat2, 0.02F);
            c(aO, aP, aQ);
            aO *= 0.5D;
            aP *= 0.5D;
            aQ *= 0.5D;
            aP -= 0.02D;

            if ((aX) && (b(aO, aP + 0.6000000238418579D - aM + d1, aQ)))
                aP = 0.300000011920929D;
        } else {
            float f1 = 0.91F;
            if (aW) {
                f1 = 0.546F;
                int i = aH.a(OMathHelper.b(aL), OMathHelper.b(aV.b) - 1, OMathHelper.b(aN));
                if (i > 0)
                    f1 = OBlock.m[i].bz * 0.91F;
            }

            float f2 = 0.1627714F / (f1 * f1 * f1);
            a(paramFloat1, paramFloat2, aW ? 0.1F * f2 : 0.02F);

            f1 = 0.91F;
            if (aW) {
                f1 = 0.546F;
                int j = aH.a(OMathHelper.b(aL), OMathHelper.b(aV.b) - 1, OMathHelper.b(aN));
                if (j > 0)
                    f1 = OBlock.m[j].bz * 0.91F;
            }

            if (p()) {
                bj = 0.0F;
                if (aP < -0.15D)
                    aP = -0.15D;
                if ((ae()) && (aP < 0.0D))
                    aP = 0.0D;

            }

            c(aO, aP, aQ);

            if ((aX) && (p()))
                aP = 0.2D;

            aP -= 0.08D;
            aP *= 0.98D;
            aO *= f1;
            aQ *= f1;
        }
        aj = ak;
        double d1 = aL - aI;
        double d2 = aN - aK;
        float f3 = OMathHelper.a(d1 * d1 + d2 * d2) * 4.0F;
        if (f3 > 1.0F)
            f3 = 1.0F;
        ak += (f3 - ak) * 0.4F;
        al += ak;
    }

    public boolean p() {
        int i = OMathHelper.b(aL);
        int j = OMathHelper.b(aV.b);
        int k = OMathHelper.b(aN);
        return aH.a(i, j, k) == OBlock.aF.bl;
    }

    @Override
    public void b(ONBTTagCompound paramONBTTagCompound) {
        paramONBTTagCompound.a("Health", (short) X);
        paramONBTTagCompound.a("HurtTime", (short) Z);
        paramONBTTagCompound.a("DeathTime", (short) ac);
        paramONBTTagCompound.a("AttackTime", (short) ad);
    }

    @Override
    public void a(ONBTTagCompound paramONBTTagCompound) {
        X = paramONBTTagCompound.d("Health");
        if (!paramONBTTagCompound.b("Health"))
            X = 10;
        Z = paramONBTTagCompound.d("HurtTime");
        ac = paramONBTTagCompound.d("DeathTime");
        ad = paramONBTTagCompound.d("AttackTime");
    }

    @Override
    public boolean P() {
        return (!bd) && (X > 0);
    }

    public boolean b_() {
        return false;
    }

    public void u() {
        if (am > 0) {
            double d1 = aL + (an - aL) / am;
            double d2 = aM + (ao - aM) / am;
            double d3 = aN + (ap - aN) / am;

            double d4 = aq - aR;
            while (d4 < -180.0D)
                d4 += 360.0D;
            while (d4 >= 180.0D)
                d4 -= 360.0D;
            aR = (float) (aR + d4 / am);
            aS = (float) (aS + (ar - aS) / am);

            am -= 1;
            a(d1, d2, d3);
            c(aR, aS);
        }

        if (A()) {
            ay = false;
            av = 0.0F;
            aw = 0.0F;
            ax = 0.0F;
        } else if (!U)
            c_();

        boolean bool1 = Z();
        boolean bool2 = aa();

        if (ay)
            if (bool1)
                aP += 0.04D;
            else if (bool2)
                aP += 0.04D;
            else if (aW)
                L();

        av *= 0.98F;
        aw *= 0.98F;
        ax *= 0.9F;
        a(av, aw);

        List localList = aH.b(this, aV.b(0.2D, 0.0D, 0.2D));
        if ((localList != null) && (localList.size() > 0))
            for (int i = 0; i < localList.size(); i++) {
                OEntity localOEntity = (OEntity) localList.get(i);
                if (!localOEntity.d_())
                    continue;
                localOEntity.h(this);
            }
    }

    protected boolean A() {
        return X <= 0;
    }

    protected void L() {
        aP = 0.42D;
    }

    protected boolean l_() {
        return true;
    }

    protected void Q() {
        OEntityPlayer localOEntityPlayer = aH.a(this, -1.0D);

        if ((l_()) && (localOEntityPlayer != null)) {
            double d1 = localOEntityPlayer.aL - aL;
            double d2 = localOEntityPlayer.aM - aM;
            double d3 = localOEntityPlayer.aN - aN;
            double d4 = d1 * d1 + d2 * d2 + d3 * d3;

            if (d4 > 16384.0D)
                G();

            if ((au > 600) && (br.nextInt(800) == 0))
                if (d4 < 1024.0D)
                    au = 0;
                else
                    G();
        }
    }

    protected void c_() {
        ++au;
        OEntityPlayer localOEntityPlayer = aH.a(this, -1.0D);
        Q();

        av = 0.0F;
        aw = 0.0F;

        float f = 8.0F;
        if (br.nextFloat() < 0.02F) {
            localOEntityPlayer = aH.a(this, f);
            if (localOEntityPlayer != null) {
                b = localOEntityPlayer;
                aB = (10 + br.nextInt(20));
            } else
                ax = ((br.nextFloat() - 0.5F) * 20.0F);
        }

        if (b != null) {
            a(b, 10.0F, v());
            if ((aB-- <= 0) || (b.bd) || (b.g(this) > f * f))
                b = null;
        } else {
            if (br.nextFloat() < 0.05F)
                ax = ((br.nextFloat() - 0.5F) * 20.0F);
            aR += ax;
            aS = az;
        }

        boolean bool1 = Z();
        boolean bool2 = aa();
        if ((bool1) || (bool2))
            ay = (br.nextFloat() < 0.8F);
    }

    protected int v() {
        return 40;
    }

    public void a(OEntity paramOEntity, float paramFloat1, float paramFloat2) {
        double d1 = paramOEntity.aL - aL;

        double d2 = paramOEntity.aN - aN;
        double d3;
        if ((paramOEntity instanceof OEntityLiving)) {
            OEntityLiving localOEntityLiving = (OEntityLiving) paramOEntity;
            d3 = aM + s() - (localOEntityLiving.aM + localOEntityLiving.s());
        } else
            d3 = (paramOEntity.aV.b + paramOEntity.aV.e) / 2.0D - (aM + s());

        double d4 = OMathHelper.a(d1 * d1 + d2 * d2);

        float f1 = (float) (Math.atan2(d2, d1) * 180.0D / 3.141592741012573D) - 90.0F;
        float f2 = (float) (-(Math.atan2(d3, d4) * 180.0D / 3.141592741012573D));
        aS = (-b(aS, f2, paramFloat2));
        aR = b(aR, f1, paramFloat1);
    }

    public boolean R() {
        return b != null;
    }

    public OEntity S() {
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

    public void T() {
    }

    public boolean d() {
        return (aH.a(aV)) && (aH.a(this, aV).size() == 0) && (!aH.c(aV));
    }

    @Override
    protected void U() {
        a((OEntity)null, 4);
    }

    @Override
    public OVec3D V() {
        return b(1.0F);
    }

    public OVec3D b(float paramFloat) {
        if (paramFloat == 1.0F) {
            float f1 = OMathHelper.b(-aR * 0.01745329F - 3.141593F);
            float f2 = OMathHelper.a(-aR * 0.01745329F - 3.141593F);
            float f3 = -OMathHelper.b(-aS * 0.01745329F);
            float f4 = OMathHelper.a(-aS * 0.01745329F);

            return OVec3D.b(f2 * f3, f4, f1 * f3);
        }
        float f1 = aU + (aS - aU) * paramFloat;
        float f2 = aT + (aR - aT) * paramFloat;

        float f3 = OMathHelper.b(-f2 * 0.01745329F - 3.141593F);
        float f4 = OMathHelper.a(-f2 * 0.01745329F - 3.141593F);
        float f5 = -OMathHelper.b(-f1 * 0.01745329F);
        float f6 = OMathHelper.a(-f1 * 0.01745329F);

        return OVec3D.b(f4 * f5, f6, f3 * f5);
    }

    public int l() {
        return 4;
    }

    public boolean I() {
        return false;
    }
}
