public class OEntityEnderman extends OEntityMob {

    private static boolean[] b      = new boolean[256];
    public boolean           a      = false;
    private int              g      = 0;
    private int              h      = 0;
    // CanaryMod Start
    Enderman                 entity = new Enderman(this);
    // CanaryMod End

    public OEntityEnderman(OWorld var1) {
        super(var1);
        this.ab = "/mob/enderman.png";
        this.aU = 0.2F;
        this.c = 5;
        this.b(0.6F, 2.9F);
        this.bI = 1.0F;
    }

    protected void b() {
        super.b();
        this.bU.a(16, new Byte((byte) 0));
        this.bU.a(17, new Byte((byte) 0));
    }

    public void b(ONBTTagCompound var1) {
        super.b(var1);
        var1.a("carried", (short) this.x());
        var1.a("carriedData", (short) this.y());
    }

    public void a(ONBTTagCompound var1) {
        super.a(var1);
        this.b(var1.d("carried"));
        this.d(var1.d("carryingData"));
    }

    protected OEntity o() {
        OEntityPlayer var1 = this.bb.a(this, 64.0D);
        if (var1 != null) {
            if (this.c(var1)) {
                if (this.h++ == 5) {
                    this.h = 0;
                    return var1;
                }
            } else {
                this.h = 0;
            }
        }

        return null;
    }

    public float a_(float var1) {
        return super.a_(var1);
    }

    private boolean c(OEntityPlayer var1) {
        OItemStack var2 = var1.j.b[3];
        if (var2 != null && var2.c == OBlock.bb.bA) {
            return false;
        } else {
            OVec3D var3 = var1.c(1.0F).b();
            OVec3D var4 = OVec3D.b(this.bf - var1.bf, this.bp.b + (double) (this.bA / 2.0F) - var1.bg + (double) var1.t(), this.bh - var1.bh);
            double var5 = var4.c();
            var4 = var4.b();
            double var7 = var3.a(var4);
            return var7 > 1.0D - 0.025D / var5 ? var1.f(this) : false;
        }
    }

    public void s() {
        if (this.an()) {
            this.a(ODamageSource.e, 1);
        }

        this.a = this.d != null;
        this.aU = this.d != null ? 4.5F : 0.3F;
        int var1;
        if (!this.bb.I) {
            int var2;
            int var3;
            int var4;
            if (this.x() == 0) {
                if (this.bL.nextInt(20) == 0) {
                    var1 = OMathHelper.b(this.bf - 2.0D + this.bL.nextDouble() * 4.0D);
                    var2 = OMathHelper.b(this.bg + this.bL.nextDouble() * 3.0D);
                    var3 = OMathHelper.b(this.bh - 2.0D + this.bL.nextDouble() * 4.0D);
                    var4 = this.bb.a(var1, var2, var3);
                    if (b[var4]) {
                        //CanaryMod onEndermanPickup
                        if (!(Boolean)etc.getLoader().callHook(PluginLoader.Hook.ENDERMAN_PICKUP, entity, new Block(Block.Type.fromId(var4), this.bb.c(var1, var2, var3)))) {
                            this.b(this.bb.a(var1, var2, var3));
                            this.d(this.bb.c(var1, var2, var3));
                            this.bb.e(var1, var2, var3, 0);
                        }
                    }
                }
            } else if (this.bL.nextInt(2000) == 0) {
                var1 = OMathHelper.b(this.bf - 1.0D + this.bL.nextDouble() * 2.0D);
                var2 = OMathHelper.b(this.bg + this.bL.nextDouble() * 2.0D);
                var3 = OMathHelper.b(this.bh - 1.0D + this.bL.nextDouble() * 2.0D);
                var4 = this.bb.a(var1, var2, var3);
                int var5 = this.bb.a(var1, var2 - 1, var3);
                if (var4 == 0 && var5 > 0 && OBlock.m[var5].b()) {
                    //CanaryMod onEndermanDrop
                    if (!(Boolean)etc.getLoader().callHook(PluginLoader.Hook.ENDERMAN_DROP, entity, new Block(Block.Type.fromId(var5), this.bb.c(var1, var2, var3)))) {
                    this.bb.b(var1, var2, var3, this.x(), this.y());
                    this.b(0);
                    }
                }
            }
        }

        for (var1 = 0; var1 < 2; ++var1) {
            this.bb.a("portal", this.bf + (this.bL.nextDouble() - 0.5D) * (double) this.bz, this.bg + this.bL.nextDouble() * (double) this.bA - 0.25D, this.bh + (this.bL.nextDouble() - 0.5D) * (double) this.bz, (this.bL.nextDouble() - 0.5D) * 2.0D, -this.bL.nextDouble(), (this.bL.nextDouble() - 0.5D) * 2.0D);
        }

        if (this.bb.d() && !this.bb.I) {
            float var6 = this.a_(1.0F);
            if (var6 > 0.5F && this.bb.j(OMathHelper.b(this.bf), OMathHelper.b(this.bg), OMathHelper.b(this.bh)) && this.bL.nextFloat() * 30.0F < (var6 - 0.4F) * 2.0F) {
                this.bO = 300;
            }
        }

        this.aS = false;
        if (this.d != null) {
            this.a(this.d, 100.0F, 100.0F);
        }

        if (!this.bb.I) {
            if (this.d != null) {
                if (this.d instanceof OEntityPlayer && this.c((OEntityPlayer) this.d)) {
                    this.aP = this.aQ = 0.0F;
                    this.aU = 0.0F;
                    if (this.d.h(this) < 16.0D) {
                        this.w();
                    }

                    this.g = 0;
                } else if (this.d.h(this) > 256.0D && this.g++ >= 30 && this.e(this.d)) {
                    this.g = 0;
                }
            } else {
                this.g = 0;
            }
        }

        super.s();
    }

    protected boolean w() {
        double var1 = this.bf + (this.bL.nextDouble() - 0.5D) * 64.0D;
        double var3 = this.bg + (double) (this.bL.nextInt(64) - 32);
        double var5 = this.bh + (this.bL.nextDouble() - 0.5D) * 64.0D;
        return this.a(var1, var3, var5);
    }

    protected boolean e(OEntity var1) {
        OVec3D var2 = OVec3D.b(this.bf - var1.bf, this.bp.b + (double) (this.bA / 2.0F) - var1.bg + (double) var1.t(), this.bh - var1.bh);
        var2 = var2.b();
        double var3 = 16.0D;
        double var5 = this.bf + (this.bL.nextDouble() - 0.5D) * 8.0D - var2.a * var3;
        double var7 = this.bg + (double) (this.bL.nextInt(16) - 8) - var2.b * var3;
        double var9 = this.bh + (this.bL.nextDouble() - 0.5D) * 8.0D - var2.c * var3;
        return this.a(var5, var7, var9);
    }

    protected boolean a(double var1, double var3, double var5) {
        double var7 = this.bf;
        double var9 = this.bg;
        double var11 = this.bh;
        this.bf = var1;
        this.bg = var3;
        this.bh = var5;
        boolean var13 = false;
        int var14 = OMathHelper.b(this.bf);
        int var15 = OMathHelper.b(this.bg);
        int var16 = OMathHelper.b(this.bh);
        int var18;
        if (this.bb.g(var14, var15, var16)) {
            boolean var17 = false;

            while (!var17 && var15 > 0) {
                var18 = this.bb.a(var14, var15 - 1, var16);
                if (var18 != 0 && OBlock.m[var18].bN.c()) {
                    var17 = true;
                } else {
                    --this.bg;
                    --var15;
                }
            }

            if (var17) {
                this.c(this.bf, this.bg, this.bh);
                if (this.bb.a((OEntity) this, this.bp).size() == 0 && !this.bb.c(this.bp)) {
                    var13 = true;
                }
            }
        }

        if (!var13) {
            this.c(var7, var9, var11);
            return false;
        } else {
            short var30 = 128;

            for (var18 = 0; var18 < var30; ++var18) {
                double var19 = (double) var18 / ((double) var30 - 1.0D);
                float var21 = (this.bL.nextFloat() - 0.5F) * 0.2F;
                float var22 = (this.bL.nextFloat() - 0.5F) * 0.2F;
                float var23 = (this.bL.nextFloat() - 0.5F) * 0.2F;
                double var24 = var7 + (this.bf - var7) * var19 + (this.bL.nextDouble() - 0.5D) * (double) this.bz * 2.0D;
                double var26 = var9 + (this.bg - var9) * var19 + this.bL.nextDouble() * (double) this.bA;
                double var28 = var11 + (this.bh - var11) * var19 + (this.bL.nextDouble() - 0.5D) * (double) this.bz * 2.0D;
                this.bb.a("portal", var24, var26, var28, (double) var21, (double) var22, (double) var23);
            }

            return true;
        }
    }

    protected String h() {
        return "mob.zombie";
    }

    protected String i() {
        return "mob.zombiehurt";
    }

    protected String j() {
        return "mob.zombiedeath";
    }

    protected int k() {
        return OItem.bl.bo;
    }

    protected void a(boolean var1) {
        int var2 = this.k();
        if (var2 > 0) {
            int var3 = this.bL.nextInt(2);

            for (int var4 = 0; var4 < var3; ++var4) {
                this.b(var2, 1);
            }
        }

    }

    public void b(int var1) {
        this.bU.b(16, Byte.valueOf((byte) (var1 & 255)));
    }

    public int x() {
        return this.bU.a(16);
    }

    public void d(int var1) {
        this.bU.b(17, Byte.valueOf((byte) (var1 & 255)));
    }

    public int y() {
        return this.bU.a(17);
    }

    // CanaryMod start
    public boolean canHoldItem(int blockID) {
        return b[blockID];
    }

    public void setHoldable(int blockID, boolean holdable) {
        b[blockID] = holdable;
    }

    public boolean getHoldable(int blockID) {
        return b[blockID];
    }

    // CanaryMod end

    static {
        b[OBlock.u.bA] = true;
        b[OBlock.v.bA] = true;
        b[OBlock.w.bA] = true;
        b[OBlock.x.bA] = true;
        b[OBlock.y.bA] = true;
        b[OBlock.F.bA] = true;
        b[OBlock.G.bA] = true;
        b[OBlock.H.bA] = true;
        b[OBlock.I.bA] = true;
        b[OBlock.J.bA] = true;
        b[OBlock.K.bA] = true;
        b[OBlock.L.bA] = true;
        b[OBlock.M.bA] = true;
        b[OBlock.N.bA] = true;
        b[OBlock.O.bA] = true;
        b[OBlock.P.bA] = true;
        b[OBlock.R.bA] = true;
        b[OBlock.ac.bA] = true;
        b[OBlock.ae.bA] = true;
        b[OBlock.af.bA] = true;
        b[OBlock.ag.bA] = true;
        b[OBlock.ah.bA] = true;
        b[OBlock.ai.bA] = true;
        b[OBlock.aj.bA] = true;
        b[OBlock.am.bA] = true;
        b[OBlock.an.bA] = true;
        b[OBlock.ao.bA] = true;
        b[OBlock.ap.bA] = true;
        b[OBlock.ax.bA] = true;
        b[OBlock.ay.bA] = true;
        b[OBlock.az.bA] = true;
        b[OBlock.aO.bA] = true;
        b[OBlock.aP.bA] = true;
        b[OBlock.aU.bA] = true;
        b[OBlock.aW.bA] = true;
        b[OBlock.aX.bA] = true;
        b[OBlock.bb.bA] = true;
        b[OBlock.bc.bA] = true;
        b[OBlock.bd.bA] = true;
        b[OBlock.be.bA] = true;
        b[OBlock.bg.bA] = true;
        b[OBlock.bn.bA] = true;
        b[OBlock.bo.bA] = true;
        b[OBlock.bp.bA] = true;
        b[OBlock.bs.bA] = true;
    }
}
