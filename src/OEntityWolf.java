
import java.util.Iterator;
import java.util.List;

public class OEntityWolf extends OEntityAnimal {

    private boolean a = false;
    private float b;
    private float c;
    private boolean f;
    private boolean g;
    private float h;
    private float i;

    public OEntityWolf(OWorld var1) {
        super(var1);
        this.N = "/mob/wolf.png";
        this.b(0.8F, 0.8F);
        this.aA = 1.1F;
        this.X = 8;
    }

    protected void b() {
        super.b();
        this.bA.a(16, Byte.valueOf((byte) 0));
        this.bA.a(17, "");
        this.bA.a(18, new Integer(this.X));
    }

    protected boolean n() {
        return false;
    }

    public void b(ONBTTagCompound var1) {
        super.b(var1);
        var1.a("Angry", this.z());
        var1.a("Sitting", this.y());
        if (this.x() == null) {
            var1.a("Owner", "");
        } else {
            var1.a("Owner", this.x());
        }

    }

    public void a(ONBTTagCompound var1) {
        super.a(var1);
        this.c(var1.m("Angry"));
        this.b(var1.m("Sitting"));
        String var2 = var1.i("Owner");
        if (var2.length() > 0) {
            this.a(var2);
            this.d(true);
        }

    }

    protected boolean l_() {
        return !this.m_();
    }

    protected String g() {
        return this.z() ? "mob.wolf.growl" : (this.br.nextInt(3) == 0 ? (this.m_() && this.bA.b(18) < 10 ? "mob.wolf.whine" : "mob.wolf.panting") : "mob.wolf.bark");
    }

    protected String h() {
        return "mob.wolf.hurt";
    }

    protected String i() {
        return "mob.wolf.death";
    }

    protected float k() {
        return 0.4F;
    }

    protected int j() {
        return -1;
    }

    protected void c_() {
        super.c_();
        if (!this.e && !this.C() && this.m_() && this.aG == null) {
            OEntityPlayer var3 = this.aH.a(this.x());
            if (var3 != null) {
                float var2 = var3.f(this);
                if (var2 > 5.0F) {
                    this.b(var3, var2);
                }
            } else if (!this.Z()) {
                this.b(true);
            }
        } else if (this.d == null && !this.C() && !this.m_() && this.aH.m.nextInt(100) == 0) {
            List var1 = this.aH.a(OEntitySheep.class, OAxisAlignedBB.b(this.aL, this.aM, this.aN, this.aL + 1.0D, this.aM + 1.0D, this.aN + 1.0D).b(16.0D, 4.0D, 16.0D));
            if (!var1.isEmpty()) {
                this.c((OEntity) var1.get(this.aH.m.nextInt(var1.size())));
            }
        }

        if (this.Z()) {
            this.b(false);
        }

        if (!this.aH.v) {
            this.bA.b(18, Integer.valueOf(this.X));
        }

    }

    public void u() {
        super.u();
        this.a = false;
        if (this.R() && !this.C() && !this.z()) {
            OEntity var1 = this.S();
            if (var1 instanceof OEntityPlayer) {
                OEntityPlayer var2 = (OEntityPlayer) var1;
                OItemStack var3 = var2.i.b();
                if (var3 != null) {
                    if (!this.m_() && var3.c == OItem.aV.bd) {
                        this.a = true;
                    } else if (this.m_() && OItem.c[var3.c] instanceof OItemFood) {
                        this.a = ((OItemFood) OItem.c[var3.c]).k();
                    }
                }
            }
        }

        if (!this.U && this.f && !this.g && !this.C() && this.aW) {
            this.g = true;
            this.h = 0.0F;
            this.i = 0.0F;
            this.aH.a(this, (byte) 8);
        }

    }

    public void p_() {
        super.p_();
        this.c = this.b;
        if (this.a) {
            this.b += (1.0F - this.b) * 0.4F;
        } else {
            this.b += (0.0F - this.b) * 0.4F;
        }

        if (this.a) {
            this.aB = 10;
        }

        if (this.Y()) {
            this.f = true;
            this.g = false;
            this.h = 0.0F;
            this.i = 0.0F;
        } else if ((this.f || this.g) && this.g) {
            if (this.h == 0.0F) {
                this.aH.a(this, "mob.wolf.shake", this.k(), (this.br.nextFloat() - this.br.nextFloat()) * 0.2F + 1.0F);
            }

            this.i = this.h;
            this.h += 0.05F;
            if (this.i >= 2.0F) {
                this.f = false;
                this.g = false;
                this.i = 0.0F;
                this.h = 0.0F;
            }

            if (this.h > 0.4F) {
                float var1 = (float) this.aV.b;
                int var2 = (int) (OMathHelper.a((this.h - 0.4F) * 3.1415927F) * 7.0F);

                for (int var3 = 0; var3 < var2; ++var3) {
                    float var4 = (this.br.nextFloat() * 2.0F - 1.0F) * this.bf * 0.5F;
                    float var5 = (this.br.nextFloat() * 2.0F - 1.0F) * this.bf * 0.5F;
                    this.aH.a("splash", this.aL + (double) var4, (double) (var1 + 0.8F), this.aN + (double) var5, this.aO, this.aP, this.aQ);
                }
            }
        }

    }

    public float s() {
        return this.bg * 0.8F;
    }

    protected int v() {
        return this.y() ? 20 : super.v();
    }

    private void b(OEntity var1, float var2) {
        OPathEntity var3 = this.aH.a(this, var1, 16.0F);
        if (var3 == null && var2 > 12.0F) {
            int var4 = OMathHelper.b(var1.aL) - 2;
            int var5 = OMathHelper.b(var1.aN) - 2;
            int var6 = OMathHelper.b(var1.aV.b);

            for (int var7 = 0; var7 <= 4; ++var7) {
                for (int var8 = 0; var8 <= 4; ++var8) {
                    if ((var7 < 1 || var8 < 1 || var7 > 3 || var8 > 3) && this.aH.d(var4 + var7, var6 - 1, var5 + var8) && !this.aH.d(var4 + var7, var6, var5 + var8) && !this.aH.d(var4 + var7, var6 + 1, var5 + var8)) {
                        this.c((double) ((float) (var4 + var7) + 0.5F), (double) var6, (double) ((float) (var5 + var8) + 0.5F), this.aR, this.aS);
                        return;
                    }
                }
            }
        } else {
            this.a(var3);
        }

    }

    protected boolean w() {
        return this.y() || this.g;
    }

    public boolean a(OEntity var1, int var2) {
        this.b(false);
        if (var1 != null && !(var1 instanceof OEntityPlayer) && !(var1 instanceof OEntityArrow)) {
            var2 = (var2 + 1) / 2;
        }

        if (!super.a((OEntity) var1, var2)) {
            return false;
        } else {
            if (!this.m_() && !this.z()) {
                if (var1 instanceof OEntityPlayer) {
                    this.c(true);
                    this.d = (OEntity) var1;
                }

                if (var1 instanceof OEntityArrow && ((OEntityArrow) var1).b != null) {
                    var1 = ((OEntityArrow) var1).b;
                }

                if (var1 instanceof OEntityLiving) {
                    List var3 = this.aH.a(OEntityWolf.class, OAxisAlignedBB.b(this.aL, this.aM, this.aN, this.aL + 1.0D, this.aM + 1.0D, this.aN + 1.0D).b(16.0D, 4.0D, 16.0D));
                    Iterator var4 = var3.iterator();

                    while (var4.hasNext()) {
                        OEntity var5 = (OEntity) var4.next();
                        OEntityWolf var6 = (OEntityWolf) var5;
                        if (!var6.m_() && var6.d == null) {
                            var6.d = (OEntity) var1;
                            if (var1 instanceof OEntityPlayer) {
                                var6.c(true);
                            }
                        }
                    }
                }
            } else if (var1 != this && var1 != null) {
                if (this.m_() && var1 instanceof OEntityPlayer && ((OEntityPlayer) var1).r.equals(this.x())) {
                    return true;
                }

                this.d = (OEntity) var1;
            }

            return true;
        }
    }

    protected OEntity o() {
        return this.z() ? this.aH.a(this, 16.0D) : null;
    }

    protected void a(OEntity var1, float var2) {
        if (var2 > 2.0F && var2 < 6.0F && this.br.nextInt(10) == 0) {
            if (this.aW) {
                double var3 = var1.aL - this.aL;
                double var5 = var1.aN - this.aN;
                float var7 = OMathHelper.a(var3 * var3 + var5 * var5);
                this.aO = var3 / (double) var7 * 0.5D * 0.800000011920929D + this.aO * 0.20000000298023224D;
                this.aQ = var5 / (double) var7 * 0.5D * 0.800000011920929D + this.aQ * 0.20000000298023224D;
                this.aP = 0.4000000059604645D;
            }
        } else if ((double) var2 < 1.5D && var1.aV.e > this.aV.b && var1.aV.b < this.aV.e) {
            this.ad = 20;
            byte var8 = 2;
            if (this.m_()) {
                var8 = 4;
            }

            var1.a(this, var8);
        }

    }

    public boolean a(OEntityPlayer var1) {
        OItemStack var2 = var1.i.b();
        if (!this.m_()) {
            if (var2 != null && var2.c == OItem.aV.bd && !this.z()) {
                --var2.a;
                if (var2.a <= 0) {
                    var1.i.a(var1.i.c, (OItemStack) null);
                }

                if (!this.aH.v) {
                    PluginLoader.HookResult res = (PluginLoader.HookResult) manager.callHook(PluginLoader.Hook.TAME, var1, new Mob(this));
                    if (res == PluginLoader.HookResult.DEFAULT_ACTION && this.br.nextInt(3) == 0 || res == PluginLoader.HookResult.ALLOW_ACTION) {
                        this.d(true);
                        this.a((OPathEntity) null);
                        this.b(true);
                        this.X = 20;
                        this.a(var1.r);
                        this.a(true);
                        this.aH.a(this, (byte) 7);
                    } else {
                        this.a(false);
                        this.aH.a(this, (byte) 6);
                    }
                }

                return true;
            }
        } else {
            if (var2 != null && OItem.c[var2.c] instanceof OItemFood) {
                OItemFood var3 = (OItemFood) OItem.c[var2.c];
                if (var3.k() && this.bA.b(18) < 20) {
                    --var2.a;
                    if (var2.a <= 0) {
                        var1.i.a(var1.i.c, (OItemStack) null);
                    }

                    this.b(((OItemFood) OItem.ao).j());
                    return true;
                }
            }

            if (var1.r.equals(this.x())) {
                if (!this.aH.v) {
                    this.b(!this.y());
                    this.ay = false;
                    this.a((OPathEntity) null);
                }

                return true;
            }
        }

        return false;
    }

    void a(boolean var1) {
        String var2 = "heart";
        if (!var1) {
            var2 = "smoke";
        }

        for (int var3 = 0; var3 < 7; ++var3) {
            double var4 = this.br.nextGaussian() * 0.02D;
            double var6 = this.br.nextGaussian() * 0.02D;
            double var8 = this.br.nextGaussian() * 0.02D;
            this.aH.a(var2, this.aL + (double) (this.br.nextFloat() * this.bf * 2.0F) - (double) this.bf, this.aM + 0.5D + (double) (this.br.nextFloat() * this.bg), this.aN + (double) (this.br.nextFloat() * this.bf * 2.0F) - (double) this.bf, var4, var6, var8);
        }

    }

    public int l() {
        return 8;
    }

    public String x() {
        return this.bA.c(17);
    }

    public void a(String var1) {
        this.bA.b(17, var1);
    }

    public boolean y() {
        return (this.bA.a(16) & 1) != 0;
    }

    public void b(boolean var1) {
        byte var2 = this.bA.a(16);
        if (var1) {
            this.bA.b(16, Byte.valueOf((byte) (var2 | 1)));
        } else {
            this.bA.b(16, Byte.valueOf((byte) (var2 & -2)));
        }

    }

    public boolean z() {
        return (this.bA.a(16) & 2) != 0;
    }

    public void c(boolean var1) {
        byte var2 = this.bA.a(16);
        if (var1) {
            this.bA.b(16, Byte.valueOf((byte) (var2 | 2)));
        } else {
            this.bA.b(16, Byte.valueOf((byte) (var2 & -3)));
        }

    }

    public boolean m_() {
        return (this.bA.a(16) & 4) != 0;
    }

    public void d(boolean var1) {
        byte var2 = this.bA.a(16);
        if (var1) {
            this.bA.b(16, Byte.valueOf((byte) (var2 | 4)));
        } else {
            this.bA.b(16, Byte.valueOf((byte) (var2 & -5)));
        }

    }
}
