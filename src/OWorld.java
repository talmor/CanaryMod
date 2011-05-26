
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class OWorld implements OIBlockAccess {

    public boolean a = false;
    private List w = new ArrayList();
    public List b = new ArrayList();
    private List x = new ArrayList();
    private TreeSet y = new TreeSet();
    private Set z = new HashSet();
    public List c = new ArrayList();
    public List d = new ArrayList();
    public List e = new ArrayList();
    private long A = 16777215L;
    public int f = 0;
    protected int g = (new Random()).nextInt();
    protected int h = 1013904223;
    private float B;
    private float C;
    private float D;
    private float E;
    private int F = 0;
    public int i = 0;
    public boolean j = false;
    private long G = System.currentTimeMillis();
    protected int k = 40;
    public int l;
    public Random m = new Random();
    public boolean n = false;
    public final OWorldProvider o;
    protected List p = new ArrayList();
    protected OIChunkProvider q;
    protected final OISaveHandler r;
    protected OWorldInfo s;
    public boolean t;
    private boolean H;
    private ArrayList I = new ArrayList();
    private int J = 0;
    private boolean K = true;
    private boolean L = true;
    static int u = 0;
    private Set M = new HashSet();
    private int N;
    private List O;
    public boolean v;

    public OWorldChunkManager a() {
        return this.o.b;
    }

    public OWorld(OISaveHandler var1, String var2, long var3, OWorldProvider var5) {
        this.N = this.m.nextInt(12000);
        this.O = new ArrayList();
        this.v = false;
        this.r = var1;
        this.s = var1.c();
        this.n = this.s == null;
        if (var5 != null) {
            this.o = var5;
        } else if (this.s != null && this.s.h() == -1) {
            this.o = new OWorldProviderHell();
        } else {
            this.o = new OWorldProvider();
        }

        boolean var6 = false;
        if (this.s == null) {
            this.s = new OWorldInfo(var3, var2);
            var6 = true;
        } else {
            this.s.a(var2);
        }

        this.o.a(this);
        this.q = this.b();
        if (var6) {
            this.c();
        }

        this.g();
        this.x();
    }

    protected OIChunkProvider b() {
        OIChunkLoader var1 = this.r.a(this.o);
        return new OChunkProviderLoadOrGenerate(this, var1, this.o.c());
    }

    protected void c() {
        this.t = true;
        int var1 = 0;
        byte var2 = 64;

        int var3;
        for (var3 = 0; !this.o.a(var1, var3); var3 += this.m.nextInt(64) - this.m.nextInt(64)) {
            var1 += this.m.nextInt(64) - this.m.nextInt(64);
        }

        this.s.a(var1, var2, var3);
        this.t = false;
    }

    public int a(int var1, int var2) {
        int var3;
        for (var3 = 63; !this.e(var1, var3 + 1, var2); ++var3) {
            ;
        }

        return this.a(var1, var3, var2);
    }

    public void a(boolean var1, OIProgressUpdate var2) {
        if (this.q.b()) {
            if (var2 != null) {
                var2.a("Saving level");
            }

            this.w();
            if (var2 != null) {
                var2.b("Saving chunks");
            }

            this.q.a(var1, var2);
        }
    }

    private void w() {
        this.k();
        this.r.a(this.s, this.d);
    }

    public int a(int var1, int var2, int var3) {
        return var1 >= -32000000 && var3 >= -32000000 && var1 < 32000000 && var3 <= 32000000 ? (var2 < 0 ? 0 : (var2 >= 128 ? 0 : this.c(var1 >> 4, var3 >> 4).a(var1 & 15, var2, var3 & 15))) : 0;
    }

    public boolean e(int var1, int var2, int var3) {
        return this.a(var1, var2, var3) == 0;
    }

    public boolean f(int var1, int var2, int var3) {
        return var2 >= 0 && var2 < 128 ? this.f(var1 >> 4, var3 >> 4) : false;
    }

    public boolean a(int var1, int var2, int var3, int var4) {
        return this.a(var1 - var4, var2 - var4, var3 - var4, var1 + var4, var2 + var4, var3 + var4);
    }

    public boolean a(int var1, int var2, int var3, int var4, int var5, int var6) {
        if (var5 >= 0 && var2 < 128) {
            var1 >>= 4;
            var2 >>= 4;
            var3 >>= 4;
            var4 >>= 4;
            var5 >>= 4;
            var6 >>= 4;

            for (int var7 = var1; var7 <= var4; ++var7) {
                for (int var8 = var3; var8 <= var6; ++var8) {
                    if (!this.f(var7, var8)) {
                        return false;
                    }
                }
            }

            return true;
        } else {
            return false;
        }
    }

    private boolean f(int var1, int var2) {
        return this.q.a(var1, var2);
    }

    public OChunk b(int var1, int var2) {
        return this.c(var1 >> 4, var2 >> 4);
    }

    public OChunk c(int var1, int var2) {
        return this.q.b(var1, var2);
    }

    public boolean a(int var1, int var2, int var3, int var4, int var5) {
        if (var1 >= -32000000 && var3 >= -32000000 && var1 < 32000000 && var3 <= 32000000) {
            if (var2 < 0) {
                return false;
            } else if (var2 >= 128) {
                return false;
            } else {
                OChunk var6 = this.c(var1 >> 4, var3 >> 4);
                return var6.a(var1 & 15, var2, var3 & 15, var4, var5);
            }
        } else {
            return false;
        }
    }

    public boolean b(int var1, int var2, int var3, int var4) {
        if (var1 >= -32000000 && var3 >= -32000000 && var1 < 32000000 && var3 <= 32000000) {
            if (var2 < 0) {
                return false;
            } else if (var2 >= 128) {
                return false;
            } else {
                OChunk var5 = this.c(var1 >> 4, var3 >> 4);
                return var5.a(var1 & 15, var2, var3 & 15, var4);
            }
        } else {
            return false;
        }
    }

    public OMaterial c(int var1, int var2, int var3) {
        int var4 = this.a(var1, var2, var3);
        return var4 == 0 ? OMaterial.a : OBlock.m[var4].by;
    }

    public int b(int var1, int var2, int var3) {
        if (var1 >= -32000000 && var3 >= -32000000 && var1 < 32000000 && var3 <= 32000000) {
            if (var2 < 0) {
                return 0;
            } else if (var2 >= 128) {
                return 0;
            } else {
                OChunk var4 = this.c(var1 >> 4, var3 >> 4);
                var1 &= 15;
                var3 &= 15;
                return var4.b(var1, var2, var3);
            }
        } else {
            return 0;
        }
    }

    public void c(int var1, int var2, int var3, int var4) {
        if (this.d(var1, var2, var3, var4)) {
            this.f(var1, var2, var3, this.a(var1, var2, var3));
        }

    }

    public boolean d(int var1, int var2, int var3, int var4) {
        if (var1 >= -32000000 && var3 >= -32000000 && var1 < 32000000 && var3 <= 32000000) {
            if (var2 < 0) {
                return false;
            } else if (var2 >= 128) {
                return false;
            } else {
                OChunk var5 = this.c(var1 >> 4, var3 >> 4);
                var1 &= 15;
                var3 &= 15;
                var5.b(var1, var2, var3, var4);
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean e(int var1, int var2, int var3, int var4) {
        if (this.b(var1, var2, var3, var4)) {
            this.f(var1, var2, var3, var4);
            return true;
        } else {
            return false;
        }
    }

    public boolean b(int var1, int var2, int var3, int var4, int var5) {
        if (this.a(var1, var2, var3, var4, var5)) {
            this.f(var1, var2, var3, var4);
            return true;
        } else {
            return false;
        }
    }

    public void g(int var1, int var2, int var3) {
        for (int var4 = 0; var4 < this.p.size(); ++var4) {
            ((OIWorldAccess) this.p.get(var4)).a(var1, var2, var3);
        }

    }

    protected void f(int var1, int var2, int var3, int var4) {
        this.g(var1, var2, var3);
        this.h(var1, var2, var3, var4);
    }

    public void g(int var1, int var2, int var3, int var4) {
        if (var3 > var4) {
            int var5 = var4;
            var4 = var3;
            var3 = var5;
        }

        this.b(var1, var3, var2, var1, var4, var2);
    }

    public void h(int var1, int var2, int var3) {
        for (int var4 = 0; var4 < this.p.size(); ++var4) {
            ((OIWorldAccess) this.p.get(var4)).a(var1, var2, var3, var1, var2, var3);
        }

    }

    public void b(int var1, int var2, int var3, int var4, int var5, int var6) {
        for (int var7 = 0; var7 < this.p.size(); ++var7) {
            ((OIWorldAccess) this.p.get(var7)).a(var1, var2, var3, var4, var5, var6);
        }

    }

    public void h(int var1, int var2, int var3, int var4) {
        this.k(var1 - 1, var2, var3, var4);
        this.k(var1 + 1, var2, var3, var4);
        this.k(var1, var2 - 1, var3, var4);
        this.k(var1, var2 + 1, var3, var4);
        this.k(var1, var2, var3 - 1, var4);
        this.k(var1, var2, var3 + 1, var4);
    }

    private void k(int var1, int var2, int var3, int var4) {
        if (!this.j && !this.v) {
            OBlock var5 = OBlock.m[this.a(var1, var2, var3)];
            if (var5 != null) {
                var5.a(this, var1, var2, var3, var4);
            }

        }
    }

    public boolean i(int var1, int var2, int var3) {
        return this.c(var1 >> 4, var3 >> 4).c(var1 & 15, var2, var3 & 15);
    }

    public int j(int var1, int var2, int var3) {
        return this.a(var1, var2, var3, true);
    }

    public int a(int var1, int var2, int var3, boolean var4) {
        if (var1 >= -32000000 && var3 >= -32000000 && var1 < 32000000 && var3 <= 32000000) {
            int var5;
            if (var4) {
                var5 = this.a(var1, var2, var3);
                if (var5 == OBlock.ak.bl || var5 == OBlock.aA.bl) {
                    int var6 = this.a(var1, var2 + 1, var3, false);
                    int var7 = this.a(var1 + 1, var2, var3, false);
                    int var8 = this.a(var1 - 1, var2, var3, false);
                    int var9 = this.a(var1, var2, var3 + 1, false);
                    int var10 = this.a(var1, var2, var3 - 1, false);
                    if (var7 > var6) {
                        var6 = var7;
                    }

                    if (var8 > var6) {
                        var6 = var8;
                    }

                    if (var9 > var6) {
                        var6 = var9;
                    }

                    if (var10 > var6) {
                        var6 = var10;
                    }

                    return var6;
                }
            }

            if (var2 < 0) {
                return 0;
            } else if (var2 >= 128) {
                var5 = 15 - this.f;
                if (var5 < 0) {
                    var5 = 0;
                }

                return var5;
            } else {
                OChunk var11 = this.c(var1 >> 4, var3 >> 4);
                var1 &= 15;
                var3 &= 15;
                return var11.c(var1, var2, var3, this.f);
            }
        } else {
            return 15;
        }
    }

    public boolean k(int var1, int var2, int var3) {
        if (var1 >= -32000000 && var3 >= -32000000 && var1 < 32000000 && var3 <= 32000000) {
            if (var2 < 0) {
                return false;
            } else if (var2 >= 128) {
                return true;
            } else if (!this.f(var1 >> 4, var3 >> 4)) {
                return false;
            } else {
                OChunk var4 = this.c(var1 >> 4, var3 >> 4);
                var1 &= 15;
                var3 &= 15;
                return var4.c(var1, var2, var3);
            }
        } else {
            return false;
        }
    }

    public int d(int var1, int var2) {
        if (var1 >= -32000000 && var2 >= -32000000 && var1 < 32000000 && var2 <= 32000000) {
            if (!this.f(var1 >> 4, var2 >> 4)) {
                return 0;
            } else {
                OChunk var3 = this.c(var1 >> 4, var2 >> 4);
                return var3.b(var1 & 15, var2 & 15);
            }
        } else {
            return 0;
        }
    }

    public void a(OEnumSkyBlock var1, int var2, int var3, int var4, int var5) {
        if (!this.o.e || var1 != OEnumSkyBlock.a) {
            if (this.f(var2, var3, var4)) {
                if (var1 == OEnumSkyBlock.a) {
                    if (this.k(var2, var3, var4)) {
                        var5 = 15;
                    }
                } else if (var1 == OEnumSkyBlock.b) {
                    int var6 = this.a(var2, var3, var4);
                    if (OBlock.s[var6] > var5) {
                        var5 = OBlock.s[var6];
                    }
                }

                if (this.a(var1, var2, var3, var4) != var5) {
                    this.a(var1, var2, var3, var4, var2, var3, var4);
                }

            }
        }
    }

    public int a(OEnumSkyBlock var1, int var2, int var3, int var4) {
        if (var3 >= 0 && var3 < 128 && var2 >= -32000000 && var4 >= -32000000 && var2 < 32000000 && var4 <= 32000000) {
            int var5 = var2 >> 4;
            int var6 = var4 >> 4;
            if (!this.f(var5, var6)) {
                return 0;
            } else {
                OChunk var7 = this.c(var5, var6);
                return var7.a(var1, var2 & 15, var3, var4 & 15);
            }
        } else {
            return var1.c;
        }
    }

    public void b(OEnumSkyBlock var1, int var2, int var3, int var4, int var5) {
        if (var2 >= -32000000 && var4 >= -32000000 && var2 < 32000000 && var4 <= 32000000) {
            if (var3 >= 0) {
                if (var3 < 128) {
                    if (this.f(var2 >> 4, var4 >> 4)) {
                        OChunk var6 = this.c(var2 >> 4, var4 >> 4);
                        var6.a(var1, var2 & 15, var3, var4 & 15, var5);

                        for (int var7 = 0; var7 < this.p.size(); ++var7) {
                            ((OIWorldAccess) this.p.get(var7)).a(var2, var3, var4);
                        }

                    }
                }
            }
        }
    }

    public float l(int var1, int var2, int var3) {
        return this.o.f[this.j(var1, var2, var3)];
    }

    public boolean d() {
        return this.f < 4;
    }

    public OMovingObjectPosition a(OVec3D var1, OVec3D var2) {
        return this.a(var1, var2, false);
    }

    public OMovingObjectPosition a(OVec3D var1, OVec3D var2, boolean var3) {
        if (!Double.isNaN(var1.a) && !Double.isNaN(var1.b) && !Double.isNaN(var1.c)) {
            if (!Double.isNaN(var2.a) && !Double.isNaN(var2.b) && !Double.isNaN(var2.c)) {
                int var4 = OMathHelper.b(var2.a);
                int var5 = OMathHelper.b(var2.b);
                int var6 = OMathHelper.b(var2.c);
                int var7 = OMathHelper.b(var1.a);
                int var8 = OMathHelper.b(var1.b);
                int var9 = OMathHelper.b(var1.c);
                int var10 = 200;

                while (var10-- >= 0) {
                    if (Double.isNaN(var1.a) || Double.isNaN(var1.b) || Double.isNaN(var1.c)) {
                        return null;
                    }

                    if (var7 == var4 && var8 == var5 && var9 == var6) {
                        return null;
                    }

                    double var11 = 999.0D;
                    double var13 = 999.0D;
                    double var15 = 999.0D;
                    if (var4 > var7) {
                        var11 = (double) var7 + 1.0D;
                    }

                    if (var4 < var7) {
                        var11 = (double) var7 + 0.0D;
                    }

                    if (var5 > var8) {
                        var13 = (double) var8 + 1.0D;
                    }

                    if (var5 < var8) {
                        var13 = (double) var8 + 0.0D;
                    }

                    if (var6 > var9) {
                        var15 = (double) var9 + 1.0D;
                    }

                    if (var6 < var9) {
                        var15 = (double) var9 + 0.0D;
                    }

                    double var17 = 999.0D;
                    double var19 = 999.0D;
                    double var21 = 999.0D;
                    double var23 = var2.a - var1.a;
                    double var25 = var2.b - var1.b;
                    double var27 = var2.c - var1.c;
                    if (var11 != 999.0D) {
                        var17 = (var11 - var1.a) / var23;
                    }

                    if (var13 != 999.0D) {
                        var19 = (var13 - var1.b) / var25;
                    }

                    if (var15 != 999.0D) {
                        var21 = (var15 - var1.c) / var27;
                    }

                    boolean var29 = false;
                    byte var35;
                    if (var17 < var19 && var17 < var21) {
                        if (var4 > var7) {
                            var35 = 4;
                        } else {
                            var35 = 5;
                        }

                        var1.a = var11;
                        var1.b += var25 * var17;
                        var1.c += var27 * var17;
                    } else if (var19 < var21) {
                        if (var5 > var8) {
                            var35 = 0;
                        } else {
                            var35 = 1;
                        }

                        var1.a += var23 * var19;
                        var1.b = var13;
                        var1.c += var27 * var19;
                    } else {
                        if (var6 > var9) {
                            var35 = 2;
                        } else {
                            var35 = 3;
                        }

                        var1.a += var23 * var21;
                        var1.b += var25 * var21;
                        var1.c = var15;
                    }

                    OVec3D var30 = OVec3D.b(var1.a, var1.b, var1.c);
                    var7 = (int) (var30.a = (double) OMathHelper.b(var1.a));
                    if (var35 == 5) {
                        --var7;
                        ++var30.a;
                    }

                    var8 = (int) (var30.b = (double) OMathHelper.b(var1.b));
                    if (var35 == 1) {
                        --var8;
                        ++var30.b;
                    }

                    var9 = (int) (var30.c = (double) OMathHelper.b(var1.c));
                    if (var35 == 3) {
                        --var9;
                        ++var30.c;
                    }

                    int var31 = this.a(var7, var8, var9);
                    int var32 = this.b(var7, var8, var9);
                    OBlock var33 = OBlock.m[var31];
                    if (var31 > 0 && var33.a(var32, var3)) {
                        OMovingObjectPosition var34 = var33.a(this, var7, var8, var9, var1, var2);
                        if (var34 != null) {
                            return var34;
                        }
                    }
                }

                return null;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public void a(OEntity var1, String var2, float var3, float var4) {
        for (int var5 = 0; var5 < this.p.size(); ++var5) {
            ((OIWorldAccess) this.p.get(var5)).a(var2, var1.aL, var1.aM - (double) var1.be, var1.aN, var3, var4);
        }

    }

    public void a(double var1, double var3, double var5, String var7, float var8, float var9) {
        for (int var10 = 0; var10 < this.p.size(); ++var10) {
            ((OIWorldAccess) this.p.get(var10)).a(var7, var1, var3, var5, var8, var9);
        }

    }

    public void a(String var1, int var2, int var3, int var4) {
        for (int var5 = 0; var5 < this.p.size(); ++var5) {
            ((OIWorldAccess) this.p.get(var5)).a(var1, var2, var3, var4);
        }

    }

    public void a(String var1, double var2, double var4, double var6, double var8, double var10, double var12) {
        for (int var14 = 0; var14 < this.p.size(); ++var14) {
            ((OIWorldAccess) this.p.get(var14)).a(var1, var2, var4, var6, var8, var10, var12);
        }

    }

    public boolean a(OEntity var1) {
        this.e.add(var1);
        return true;
    }

    public boolean b(OEntity entity) {
        if (entity instanceof OEntityLiving && !(entity instanceof OEntityPlayer)) // CanaryMod: allow entities to spawn
        {
            if ((etc.getInstance().getMobSpawnRate() < 100 && etc.getInstance().getMobSpawnRate() > 0 && etc.getInstance().getMobSpawnRate() <= m.nextInt(101)) || etc.getInstance().getMobSpawnRate() <= 0 || (Boolean) (etc.getLoader().callHook(PluginLoader.Hook.MOB_SPAWN, new Mob((OEntityLiving) entity)))) {
                return false;
            }
        }

        int var2 = OMathHelper.b(entity.aL / 16.0D);
        int var3 = OMathHelper.b(entity.aN / 16.0D);
        boolean var4 = false;
        if (entity instanceof OEntityPlayer) {
            var4 = true;
        }

        if (!var4 && !this.f(var2, var3)) {
            return false;
        } else {
            if (entity instanceof OEntityPlayer) {
                OEntityPlayer var5 = (OEntityPlayer) entity;
                this.d.add(var5);
                this.r();
            }

            this.c(var2, var3).a(entity);
            this.b.add(entity);
            this.c(entity);
            return true;
        }
    }

    protected void c(OEntity var1) {
        for (int var2 = 0; var2 < this.p.size(); ++var2) {
            ((OIWorldAccess) this.p.get(var2)).a(var1);
        }

    }

    protected void d(OEntity var1) {
        for (int var2 = 0; var2 < this.p.size(); ++var2) {
            ((OIWorldAccess) this.p.get(var2)).b(var1);
        }

    }

    public void e(OEntity var1) {
        if (var1.aF != null) {
            var1.aF.b((OEntity) null);
        }

        if (var1.aG != null) {
            var1.b((OEntity) null);
        }

        var1.G();
        if (var1 instanceof OEntityPlayer) {
            this.d.remove((OEntityPlayer) var1);
            this.r();
        }

    }

    public void f(OEntity var1) {
        var1.G();
        if (var1 instanceof OEntityPlayer) {
            this.d.remove((OEntityPlayer) var1);
            this.r();
        }

        int var2 = var1.bC;
        int var3 = var1.bE;
        if (var1.bB && this.f(var2, var3)) {
            this.c(var2, var3).b(var1);
        }

        this.b.remove(var1);
        this.d(var1);
    }

    public void a(OIWorldAccess var1) {
        this.p.add(var1);
    }

    public List a(OEntity var1, OAxisAlignedBB var2) {
        this.I.clear();
        int var3 = OMathHelper.b(var2.a);
        int var4 = OMathHelper.b(var2.d + 1.0D);
        int var5 = OMathHelper.b(var2.b);
        int var6 = OMathHelper.b(var2.e + 1.0D);
        int var7 = OMathHelper.b(var2.c);
        int var8 = OMathHelper.b(var2.f + 1.0D);

        for (int var9 = var3; var9 < var4; ++var9) {
            for (int var10 = var7; var10 < var8; ++var10) {
                if (this.f(var9, 64, var10)) {
                    for (int var11 = var5 - 1; var11 < var6; ++var11) {
                        OBlock var12 = OBlock.m[this.a(var9, var11, var10)];
                        if (var12 != null) {
                            var12.a(this, var9, var11, var10, var2, this.I);
                        }
                    }
                }
            }
        }

        double var13 = 0.25D;
        List var17 = this.b(var1, var2.b(var13, var13, var13));

        for (int var16 = 0; var16 < var17.size(); ++var16) {
            OAxisAlignedBB var15 = ((OEntity) var17.get(var16)).e_();
            if (var15 != null && var15.a(var2)) {
                this.I.add(var15);
            }

            var15 = var1.a_((OEntity) var17.get(var16));
            if (var15 != null && var15.a(var2)) {
                this.I.add(var15);
            }
        }

        return this.I;
    }

    public int a(float var1) {
        float var2 = this.b(var1);
        float var3 = 1.0F - (OMathHelper.b(var2 * 3.1415927F * 2.0F) * 2.0F + 0.5F);
        if (var3 < 0.0F) {
            var3 = 0.0F;
        }

        if (var3 > 1.0F) {
            var3 = 1.0F;
        }

        var3 = 1.0F - var3;
        var3 = (float) ((double) var3 * (1.0D - (double) (this.d(var1) * 5.0F) / 16.0D));
        var3 = (float) ((double) var3 * (1.0D - (double) (this.c(var1) * 5.0F) / 16.0D));
        var3 = 1.0F - var3;
        return (int) (var3 * 11.0F);
    }

    public float b(float var1) {
        return this.o.a(this.s.f(), var1);
    }

    public int e(int var1, int var2) {
        OChunk var3 = this.b(var1, var2);
        int var4 = 127;
        var1 &= 15;

        for (var2 &= 15; var4 > 0; --var4) {
            int var5 = var3.a(var1, var4, var2);
            if (var5 != 0 && OBlock.m[var5].by.c()) {
                return var4 + 1;
            }
        }

        return -1;
    }

    public void c(int var1, int var2, int var3, int var4, int var5) {
        ONextTickListEntry var6 = new ONextTickListEntry(var1, var2, var3, var4);
        byte var7 = 8;
        if (this.a) {
            if (this.a(var6.a - var7, var6.b - var7, var6.c - var7, var6.a + var7, var6.b + var7, var6.c + var7)) {
                int var8 = this.a(var6.a, var6.b, var6.c);
                if (var8 == var6.d && var8 > 0) {
                    OBlock.m[var8].a(this, var6.a, var6.b, var6.c, this.m);
                }
            }

        } else {
            if (this.a(var1 - var7, var2 - var7, var3 - var7, var1 + var7, var2 + var7, var3 + var7)) {
                if (var4 > 0) {
                    var6.a((long) var5 + this.s.f());
                }

                if (!this.z.contains(var6)) {
                    this.z.add(var6);
                    this.y.add(var6);
                }
            }

        }
    }

    public void e() {
        int var1;
        OEntity var2;
        for (var1 = 0; var1 < this.e.size(); ++var1) {
            var2 = (OEntity) this.e.get(var1);
            var2.p_();
            if (var2.bd) {
                this.e.remove(var1--);
            }
        }

        this.b.removeAll(this.x);

        int var3;
        int var4;
        for (var1 = 0; var1 < this.x.size(); ++var1) {
            var2 = (OEntity) this.x.get(var1);
            var3 = var2.bC;
            var4 = var2.bE;
            if (var2.bB && this.f(var3, var4)) {
                this.c(var3, var4).b(var2);
            }
        }

        for (var1 = 0; var1 < this.x.size(); ++var1) {
            this.d((OEntity) this.x.get(var1));
        }

        this.x.clear();

        for (var1 = 0; var1 < this.b.size(); ++var1) {
            var2 = (OEntity) this.b.get(var1);
            if (var2.aG != null) {
                if (!var2.aG.bd && var2.aG.aF == var2) {
                    continue;
                }

                var2.aG.aF = null;
                var2.aG = null;
            }

            if (!var2.bd) {
                this.g(var2);
            }

            if (var2.bd) {
                var3 = var2.bC;
                var4 = var2.bE;
                if (var2.bB && this.f(var3, var4)) {
                    this.c(var3, var4).b(var2);
                }

                this.b.remove(var1--);
                this.d(var2);
            }
        }

        for (var1 = 0; var1 < this.c.size(); ++var1) {
            OTileEntity var5 = (OTileEntity) this.c.get(var1);
            var5.g_();
        }

    }

    public void g(OEntity var1) {
        this.a(var1, true);
    }

    public void a(OEntity var1, boolean var2) {
        int var3 = OMathHelper.b(var1.aL);
        int var4 = OMathHelper.b(var1.aN);
        byte var5 = 32;
        if (!var2 || this.a(var3 - var5, 0, var4 - var5, var3 + var5, 128, var4 + var5)) {
            var1.bk = var1.aL;
            var1.bl = var1.aM;
            var1.bm = var1.aN;
            var1.aT = var1.aR;
            var1.aU = var1.aS;
            if (var2 && var1.bB) {
                if (var1.aG != null) {
                    var1.B();
                } else {
                    var1.p_();
                }
            }

            if (Double.isNaN(var1.aL) || Double.isInfinite(var1.aL)) {
                var1.aL = var1.bk;
            }

            if (Double.isNaN(var1.aM) || Double.isInfinite(var1.aM)) {
                var1.aM = var1.bl;
            }

            if (Double.isNaN(var1.aN) || Double.isInfinite(var1.aN)) {
                var1.aN = var1.bm;
            }

            if (Double.isNaN((double) var1.aS) || Double.isInfinite((double) var1.aS)) {
                var1.aS = var1.aU;
            }

            if (Double.isNaN((double) var1.aR) || Double.isInfinite((double) var1.aR)) {
                var1.aR = var1.aT;
            }

            int var6 = OMathHelper.b(var1.aL / 16.0D);
            int var7 = OMathHelper.b(var1.aM / 16.0D);
            int var8 = OMathHelper.b(var1.aN / 16.0D);
            if (!var1.bB || var1.bC != var6 || var1.bD != var7 || var1.bE != var8) {
                if (var1.bB && this.f(var1.bC, var1.bE)) {
                    this.c(var1.bC, var1.bE).a(var1, var1.bD);
                }

                if (this.f(var6, var8)) {
                    var1.bB = true;
                    this.c(var6, var8).a(var1);
                } else {
                    var1.bB = false;
                }
            }

            if (var2 && var1.bB && var1.aF != null) {
                if (!var1.aF.bd && var1.aF.aG == var1) {
                    this.g(var1.aF);
                } else {
                    var1.aF.aG = null;
                    var1.aF = null;
                }
            }

        }
    }

    public boolean a(OAxisAlignedBB var1) {
        List var2 = this.b((OEntity) null, var1);

        for (int var3 = 0; var3 < var2.size(); ++var3) {
            OEntity var4 = (OEntity) var2.get(var3);
            if (!var4.bd && var4.aE) {
                return false;
            }
        }

        return true;
    }

    public boolean b(OAxisAlignedBB var1) {
        int var2 = OMathHelper.b(var1.a);
        int var3 = OMathHelper.b(var1.d + 1.0D);
        int var4 = OMathHelper.b(var1.b);
        int var5 = OMathHelper.b(var1.e + 1.0D);
        int var6 = OMathHelper.b(var1.c);
        int var7 = OMathHelper.b(var1.f + 1.0D);
        if (var1.a < 0.0D) {
            --var2;
        }

        if (var1.b < 0.0D) {
            --var4;
        }

        if (var1.c < 0.0D) {
            --var6;
        }

        for (int var8 = var2; var8 < var3; ++var8) {
            for (int var9 = var4; var9 < var5; ++var9) {
                for (int var10 = var6; var10 < var7; ++var10) {
                    OBlock var11 = OBlock.m[this.a(var8, var9, var10)];
                    if (var11 != null) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean c(OAxisAlignedBB var1) {
        int var2 = OMathHelper.b(var1.a);
        int var3 = OMathHelper.b(var1.d + 1.0D);
        int var4 = OMathHelper.b(var1.b);
        int var5 = OMathHelper.b(var1.e + 1.0D);
        int var6 = OMathHelper.b(var1.c);
        int var7 = OMathHelper.b(var1.f + 1.0D);
        if (var1.a < 0.0D) {
            --var2;
        }

        if (var1.b < 0.0D) {
            --var4;
        }

        if (var1.c < 0.0D) {
            --var6;
        }

        for (int var8 = var2; var8 < var3; ++var8) {
            for (int var9 = var4; var9 < var5; ++var9) {
                for (int var10 = var6; var10 < var7; ++var10) {
                    OBlock var11 = OBlock.m[this.a(var8, var9, var10)];
                    if (var11 != null && var11.by.d()) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean d(OAxisAlignedBB var1) {
        int var2 = OMathHelper.b(var1.a);
        int var3 = OMathHelper.b(var1.d + 1.0D);
        int var4 = OMathHelper.b(var1.b);
        int var5 = OMathHelper.b(var1.e + 1.0D);
        int var6 = OMathHelper.b(var1.c);
        int var7 = OMathHelper.b(var1.f + 1.0D);
        if (this.a(var2, var4, var6, var3, var5, var7)) {
            for (int var8 = var2; var8 < var3; ++var8) {
                for (int var9 = var4; var9 < var5; ++var9) {
                    for (int var10 = var6; var10 < var7; ++var10) {
                        int var11 = this.a(var8, var9, var10);
                        if (var11 == OBlock.ar.bl || var11 == OBlock.C.bl || var11 == OBlock.D.bl) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public boolean a(OAxisAlignedBB var1, OMaterial var2, OEntity var3) {
        int var4 = OMathHelper.b(var1.a);
        int var5 = OMathHelper.b(var1.d + 1.0D);
        int var6 = OMathHelper.b(var1.b);
        int var7 = OMathHelper.b(var1.e + 1.0D);
        int var8 = OMathHelper.b(var1.c);
        int var9 = OMathHelper.b(var1.f + 1.0D);
        if (!this.a(var4, var6, var8, var5, var7, var9)) {
            return false;
        } else {
            boolean var10 = false;
            OVec3D var11 = OVec3D.b(0.0D, 0.0D, 0.0D);

            for (int var12 = var4; var12 < var5; ++var12) {
                for (int var13 = var6; var13 < var7; ++var13) {
                    for (int var14 = var8; var14 < var9; ++var14) {
                        OBlock var15 = OBlock.m[this.a(var12, var13, var14)];
                        if (var15 != null && var15.by == var2) {
                            double var16 = (double) ((float) (var13 + 1) - OBlockFluid.c(this.b(var12, var13, var14)));
                            if ((double) var7 >= var16) {
                                var10 = true;
                                var15.a(this, var12, var13, var14, var3, var11);
                            }
                        }
                    }
                }
            }

            if (var11.c() > 0.0D) {
                var11 = var11.b();
                double var18 = 0.014D;
                var3.aO += var11.a * var18;
                var3.aP += var11.b * var18;
                var3.aQ += var11.c * var18;
            }

            return var10;
        }
    }

    public boolean a(OAxisAlignedBB var1, OMaterial var2) {
        int var3 = OMathHelper.b(var1.a);
        int var4 = OMathHelper.b(var1.d + 1.0D);
        int var5 = OMathHelper.b(var1.b);
        int var6 = OMathHelper.b(var1.e + 1.0D);
        int var7 = OMathHelper.b(var1.c);
        int var8 = OMathHelper.b(var1.f + 1.0D);

        for (int var9 = var3; var9 < var4; ++var9) {
            for (int var10 = var5; var10 < var6; ++var10) {
                for (int var11 = var7; var11 < var8; ++var11) {
                    OBlock var12 = OBlock.m[this.a(var9, var10, var11)];
                    if (var12 != null && var12.by == var2) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean b(OAxisAlignedBB var1, OMaterial var2) {
        int var3 = OMathHelper.b(var1.a);
        int var4 = OMathHelper.b(var1.d + 1.0D);
        int var5 = OMathHelper.b(var1.b);
        int var6 = OMathHelper.b(var1.e + 1.0D);
        int var7 = OMathHelper.b(var1.c);
        int var8 = OMathHelper.b(var1.f + 1.0D);

        for (int var9 = var3; var9 < var4; ++var9) {
            for (int var10 = var5; var10 < var6; ++var10) {
                for (int var11 = var7; var11 < var8; ++var11) {
                    OBlock var12 = OBlock.m[this.a(var9, var10, var11)];
                    if (var12 != null && var12.by == var2) {
                        int var13 = this.b(var9, var10, var11);
                        double var14 = (double) (var10 + 1);
                        if (var13 < 8) {
                            var14 = (double) (var10 + 1) - (double) var13 / 8.0D;
                        }

                        if (var14 >= var1.b) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public OExplosion a(OEntity var1, double var2, double var4, double var6, float var8) {
        return this.a(var1, var2, var4, var6, var8, false);
    }

    public OExplosion a(OEntity var1, double var2, double var4, double var6, float var8, boolean var9) {
        OExplosion var10 = new OExplosion(this, var1, var2, var4, var6, var8);
        var10.a = var9;
        var10.a();
        var10.a(true);
        return var10;
    }

    public float a(OVec3D var1, OAxisAlignedBB var2) {
        double var3 = 1.0D / ((var2.d - var2.a) * 2.0D + 1.0D);
        double var5 = 1.0D / ((var2.e - var2.b) * 2.0D + 1.0D);
        double var7 = 1.0D / ((var2.f - var2.c) * 2.0D + 1.0D);
        int var9 = 0;
        int var10 = 0;

        for (float var11 = 0.0F; var11 <= 1.0F; var11 = (float) ((double) var11 + var3)) {
            for (float var12 = 0.0F; var12 <= 1.0F; var12 = (float) ((double) var12 + var5)) {
                for (float var13 = 0.0F; var13 <= 1.0F; var13 = (float) ((double) var13 + var7)) {
                    double var14 = var2.a + (var2.d - var2.a) * (double) var11;
                    double var16 = var2.b + (var2.e - var2.b) * (double) var12;
                    double var18 = var2.c + (var2.f - var2.c) * (double) var13;
                    if (this.a(OVec3D.b(var14, var16, var18), var1) == null) {
                        ++var9;
                    }

                    ++var10;
                }
            }
        }

        return (float) var9 / (float) var10;
    }

    public OTileEntity m(int var1, int var2, int var3) {
        OChunk var4 = this.c(var1 >> 4, var3 >> 4);
        return var4 != null ? var4.d(var1 & 15, var2, var3 & 15) : null;
    }

    public void a(int var1, int var2, int var3, OTileEntity var4) {
        OChunk var5 = this.c(var1 >> 4, var3 >> 4);
        if (var5 != null) {
            var5.a(var1 & 15, var2, var3 & 15, var4);
        }

    }

    public void n(int var1, int var2, int var3) {
        OChunk var4 = this.c(var1 >> 4, var3 >> 4);
        if (var4 != null) {
            var4.e(var1 & 15, var2, var3 & 15);
        }

    }

    public boolean d(int var1, int var2, int var3) {
        OBlock var4 = OBlock.m[this.a(var1, var2, var3)];
        return var4 == null ? false : var4.a();
    }

    public boolean f() {
        if (this.J >= 50) {
            return false;
        } else {
            ++this.J;

            boolean var2;
            try {
                int var1 = 500;

                while (this.w.size() > 0) {
                    --var1;
                    if (var1 <= 0) {
                        var2 = true;
                        return var2;
                    }

                    ((OMetadataChunkBlock) this.w.remove(this.w.size() - 1)).a(this);
                }

                var2 = false;
            } finally {
                --this.J;
            }

            return var2;
        }
    }

    public void a(OEnumSkyBlock var1, int var2, int var3, int var4, int var5, int var6, int var7) {
        this.a(var1, var2, var3, var4, var5, var6, var7, true);
    }

    public void a(OEnumSkyBlock var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8) {
        if (!this.o.e || var1 != OEnumSkyBlock.a) {
            ++u;
            if (u == 50) {
                --u;
            } else {
                int var9 = (var5 + var2) / 2;
                int var10 = (var7 + var4) / 2;
                if (!this.f(var9, 64, var10)) {
                    --u;
                } else if (!this.b(var9, var10).g()) {
                    int var11 = this.w.size();
                    int var12;
                    if (var8) {
                        var12 = 5;
                        if (var12 > var11) {
                            var12 = var11;
                        }

                        for (int var13 = 0; var13 < var12; ++var13) {
                            OMetadataChunkBlock var14 = (OMetadataChunkBlock) this.w.get(this.w.size() - var13 - 1);
                            if (var14.a == var1 && var14.a(var2, var3, var4, var5, var6, var7)) {
                                --u;
                                return;
                            }
                        }
                    }

                    this.w.add(new OMetadataChunkBlock(var1, var2, var3, var4, var5, var6, var7));
                    var12 = 1000000;
                    if (this.w.size() > 1000000) {
                        System.out.println("More than " + var12 + " updates, aborting lighting updates");
                        this.w.clear();
                    }

                    --u;
                }
            }
        }
    }

    public void g() {
        int var1 = this.a(1.0F);
        if (var1 != this.f) {
            this.f = var1;
        }

    }

    public void a(boolean var1, boolean var2) {
        this.K = var1;
        this.L = var2;
    }

    public void h() {
        this.i();
        long var2;
        if (this.t()) {
            boolean var1 = false;
            if (this.K && this.l >= 1) {
                var1 = OSpawnerAnimals.a(this, this.d);
            }

            if (!var1) {
                var2 = this.s.f() + 24000L;
                this.s.a(var2 - var2 % 24000L);
                this.s();
            }
        }

        OSpawnerAnimals.a(this, this.K, this.L);
        this.q.a();
        int var5 = this.a(1.0F);
        if (var5 != this.f) {
            this.f = var5;

            for (int var4 = 0; var4 < this.p.size(); ++var4) {
                ((OIWorldAccess) this.p.get(var4)).a();
            }
        }

        var2 = this.s.f() + 1L;
        if (var2 % (long) this.k == 0L) {
            this.a(false, (OIProgressUpdate) null);
        }

        this.s.a(var2);
        this.a(false);
        this.j();
    }

    private void x() {
        if (this.s.l()) {
            this.C = 1.0F;
            if (this.s.j()) {
                this.E = 1.0F;
            }
        }

    }

    protected void i() {
        if (!this.o.e) {
            if (this.F > 0) {
                --this.F;
            }

            int var1 = this.s.k();
            if (var1 <= 0) {
                if (this.s.j()) {
                    this.s.b(this.m.nextInt(12000) + 3600);
                } else {
                    this.s.b(this.m.nextInt(168000) + 12000);
                }
            } else {
                --var1;
                this.s.b(var1);
                if (var1 <= 0) {
                    if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.THUNDER_CHANGE, !this.s.j())) {
                        this.s.a(!this.s.j());
                    }
                }
            }

            int var2 = this.s.m();
            if (var2 <= 0) {
                if (this.s.l()) {
                    this.s.c(this.m.nextInt(12000) + 12000);
                } else {
                    this.s.c(this.m.nextInt(168000) + 12000);
                }
            } else {
                --var2;
                this.s.c(var2);
                if (var2 <= 0) {
                    if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.WEATHER_CHANGE, !this.s.l())) {
                        this.s.b(!this.s.l());
                    }
                }
            }

            this.B = this.C;
            if (this.s.l()) {
                this.C = (float) ((double) this.C + 0.01D);
            } else {
                this.C = (float) ((double) this.C - 0.01D);
            }

            if (this.C < 0.0F) {
                this.C = 0.0F;
            }

            if (this.C > 1.0F) {
                this.C = 1.0F;
            }

            this.D = this.E;
            if (this.s.j()) {
                this.E = (float) ((double) this.E + 0.01D);
            } else {
                this.E = (float) ((double) this.E - 0.01D);
            }

            if (this.E < 0.0F) {
                this.E = 0.0F;
            }

            if (this.E > 1.0F) {
                this.E = 1.0F;
            }

        }
    }

    private void y() {
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.WEATHER_CHANGE, !this.s.l())) {
            this.s.c(0);
            this.s.b(false);
        }
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.THUNDER_CHANGE, !this.s.j())) {
            this.s.b(0);
            this.s.a(false);
        }
    }

    protected void j() {
        this.M.clear();

        int var3;
        int var4;
        int var6;
        int var7;
        for (int var1 = 0; var1 < this.d.size(); ++var1) {
            OEntityPlayer var2 = (OEntityPlayer) this.d.get(var1);
            var3 = OMathHelper.b(var2.aL / 16.0D);
            var4 = OMathHelper.b(var2.aN / 16.0D);
            byte var5 = 9;

            for (var6 = -var5; var6 <= var5; ++var6) {
                for (var7 = -var5; var7 <= var5; ++var7) {
                    this.M.add(new OChunkCoordIntPair(var6 + var3, var7 + var4));
                }
            }
        }

        if (this.N > 0) {
            --this.N;
        }

        Iterator var12 = this.M.iterator();

        while (var12.hasNext()) {
            OChunkCoordIntPair var13 = (OChunkCoordIntPair) var12.next();
            var3 = var13.a * 16;
            var4 = var13.b * 16;
            OChunk var14 = this.c(var13.a, var13.b);
            int var8;
            int var9;
            int var10;
            if (this.N == 0) {
                this.g = this.g * 3 + this.h;
                var6 = this.g >> 2;
                var7 = var6 & 15;
                var8 = var6 >> 8 & 15;
                var9 = var6 >> 16 & 127;
                var10 = var14.a(var7, var9, var8);
                var7 += var3;
                var8 += var4;
                if (var10 == 0 && this.j(var7, var9, var8) <= this.m.nextInt(8) && this.a(OEnumSkyBlock.a, var7, var9, var8) <= 0) {
                    OEntityPlayer var11 = this.a((double) var7 + 0.5D, (double) var9 + 0.5D, (double) var8 + 0.5D, 8.0D);
                    if (var11 != null && var11.d((double) var7 + 0.5D, (double) var9 + 0.5D, (double) var8 + 0.5D) > 4.0D) {
                        this.a((double) var7 + 0.5D, (double) var9 + 0.5D, (double) var8 + 0.5D, "ambient.cave.cave", 0.7F, 0.8F + this.m.nextFloat() * 0.2F);
                        this.N = this.m.nextInt(12000) + 6000;
                    }
                }
            }

            if (this.m.nextInt(100000) == 0 && this.v() && this.u()) {
                this.g = this.g * 3 + this.h;
                var6 = this.g >> 2;
                var7 = var3 + (var6 & 15);
                var8 = var4 + (var6 >> 8 & 15);
                var9 = this.e(var7, var8);
                if (this.q(var7, var9, var8)) {
                    this.a((OEntity) (new OEntityLightningBolt(this, (double) var7, (double) var9, (double) var8)));
                    this.F = 2;
                }
            }

            int var15;
            if (this.m.nextInt(16) == 0 && this.v()) {
                this.g = this.g * 3 + this.h;
                var6 = this.g >> 2;
                var7 = var6 & 15;
                var8 = var6 >> 8 & 15;
                var9 = this.e(var7 + var3, var8 + var4);
                if (this.a().a(var7 + var3, var8 + var4).c() && var9 >= 0 && var9 < 128 && var14.a(OEnumSkyBlock.b, var7, var9, var8) < 10) {
                    var10 = var14.a(var7, var9 - 1, var8);
                    var15 = var14.a(var7, var9, var8);
                    if (var15 == 0 && OBlock.aS.a(this, var7 + var3, var9, var8 + var4) && var10 != 0 && var10 != OBlock.aT.bl && OBlock.m[var10].by.c()) {
                        this.e(var7 + var3, var9, var8 + var4, OBlock.aS.bl);
                    }

                    if (var10 == OBlock.B.bl && var14.b(var7, var9 - 1, var8) == 0) {
                        this.e(var7 + var3, var9 - 1, var8 + var4, OBlock.aT.bl);
                    }
                }
            }

            for (var6 = 0; var6 < 80; ++var6) {
                this.g = this.g * 3 + this.h;
                var7 = this.g >> 2;
                var8 = var7 & 15;
                var9 = var7 >> 8 & 15;
                var10 = var7 >> 16 & 127;
                var15 = var14.b[var8 << 11 | var9 << 7 | var10] & 255;
                if (OBlock.n[var15]) {
                    OBlock.m[var15].a(this, var8 + var3, var10, var9 + var4, this.m);
                }
            }
        }

    }

    public boolean a(boolean var1) {
        int var2 = this.y.size();
        if (var2 != this.z.size()) {
            throw new IllegalStateException("TickNextTick list out of synch");
        } else {
            if (var2 > 1000) {
                var2 = 1000;
            }

            for (int var3 = 0; var3 < var2; ++var3) {
                ONextTickListEntry var4 = (ONextTickListEntry) this.y.first();
                if (!var1 && var4.e > this.s.f()) {
                    break;
                }

                this.y.remove(var4);
                this.z.remove(var4);
                byte var5 = 8;
                if (this.a(var4.a - var5, var4.b - var5, var4.c - var5, var4.a + var5, var4.b + var5, var4.c + var5)) {
                    int var6 = this.a(var4.a, var4.b, var4.c);
                    if (var6 == var4.d && var6 > 0) {
                        OBlock.m[var6].a(this, var4.a, var4.b, var4.c, this.m);
                    }
                }
            }

            return this.y.size() != 0;
        }
    }

    public List b(OEntity var1, OAxisAlignedBB var2) {
        this.O.clear();
        int var3 = OMathHelper.b((var2.a - 2.0D) / 16.0D);
        int var4 = OMathHelper.b((var2.d + 2.0D) / 16.0D);
        int var5 = OMathHelper.b((var2.c - 2.0D) / 16.0D);
        int var6 = OMathHelper.b((var2.f + 2.0D) / 16.0D);

        for (int var7 = var3; var7 <= var4; ++var7) {
            for (int var8 = var5; var8 <= var6; ++var8) {
                if (this.f(var7, var8)) {
                    this.c(var7, var8).a(var1, var2, this.O);
                }
            }
        }

        return this.O;
    }

    public List a(Class var1, OAxisAlignedBB var2) {
        int var3 = OMathHelper.b((var2.a - 2.0D) / 16.0D);
        int var4 = OMathHelper.b((var2.d + 2.0D) / 16.0D);
        int var5 = OMathHelper.b((var2.c - 2.0D) / 16.0D);
        int var6 = OMathHelper.b((var2.f + 2.0D) / 16.0D);
        ArrayList var7 = new ArrayList();

        for (int var8 = var3; var8 <= var4; ++var8) {
            for (int var9 = var5; var9 <= var6; ++var9) {
                if (this.f(var8, var9)) {
                    this.c(var8, var9).a(var1, var2, var7);
                }
            }
        }

        return var7;
    }

    public void b(int var1, int var2, int var3, OTileEntity var4) {
        if (this.f(var1, var2, var3)) {
            this.b(var1, var3).f();
        }

        for (int var5 = 0; var5 < this.p.size(); ++var5) {
            ((OIWorldAccess) this.p.get(var5)).a(var1, var2, var3, var4);
        }

    }

    public int a(Class var1) {
        int var2 = 0;

        for (int var3 = 0; var3 < this.b.size(); ++var3) {
            OEntity var4 = (OEntity) this.b.get(var3);
            if (var1.isAssignableFrom(var4.getClass())) {
                ++var2;
            }
        }

        return var2;
    }

    public void a(List var1) {
        this.b.addAll(var1);

        for (int var2 = 0; var2 < var1.size(); ++var2) {
            this.c((OEntity) var1.get(var2));
        }

    }

    public void b(List var1) {
        this.x.addAll(var1);
    }

    public boolean a(int var1, int var2, int var3, int var4, boolean var5) {
        int var6 = this.a(var2, var3, var4);
        OBlock var7 = OBlock.m[var6];
        OBlock var8 = OBlock.m[var1];
        OAxisAlignedBB var9 = var8.d(this, var2, var3, var4);
        if (var5) {
            var9 = null;
        }

        if (var9 != null && !this.a(var9)) {
            return false;
        } else {
            if (var7 == OBlock.A || var7 == OBlock.B || var7 == OBlock.C || var7 == OBlock.D || var7 == OBlock.ar || var7 == OBlock.aS) {
                var7 = null;
            }

            return var1 > 0 && var7 == null && var8.a(this, var2, var3, var4);
        }
    }

    public OPathEntity a(OEntity var1, OEntity var2, float var3) {
        int var4 = OMathHelper.b(var1.aL);
        int var5 = OMathHelper.b(var1.aM);
        int var6 = OMathHelper.b(var1.aN);
        int var7 = (int) (var3 + 16.0F);
        int var8 = var4 - var7;
        int var9 = var5 - var7;
        int var10 = var6 - var7;
        int var11 = var4 + var7;
        int var12 = var5 + var7;
        int var13 = var6 + var7;
        OChunkCache var14 = new OChunkCache(this, var8, var9, var10, var11, var12, var13);
        return (new OPathfinder(var14)).a(var1, var2, var3);
    }

    public OPathEntity a(OEntity var1, int var2, int var3, int var4, float var5) {
        int var6 = OMathHelper.b(var1.aL);
        int var7 = OMathHelper.b(var1.aM);
        int var8 = OMathHelper.b(var1.aN);
        int var9 = (int) (var5 + 8.0F);
        int var10 = var6 - var9;
        int var11 = var7 - var9;
        int var12 = var8 - var9;
        int var13 = var6 + var9;
        int var14 = var7 + var9;
        int var15 = var8 + var9;
        OChunkCache var16 = new OChunkCache(this, var10, var11, var12, var13, var14, var15);
        return (new OPathfinder(var16)).a(var1, var2, var3, var4, var5);
    }

    public boolean i(int var1, int var2, int var3, int var4) {
        int var5 = this.a(var1, var2, var3);
        return var5 == 0 ? false : OBlock.m[var5].c(this, var1, var2, var3, var4);
    }

    public boolean o(int var1, int var2, int var3) {
        return this.i(var1, var2 - 1, var3, 0) ? true : (this.i(var1, var2 + 1, var3, 1) ? true : (this.i(var1, var2, var3 - 1, 2) ? true : (this.i(var1, var2, var3 + 1, 3) ? true : (this.i(var1 - 1, var2, var3, 4) ? true : this.i(var1 + 1, var2, var3, 5)))));
    }

    public boolean j(int var1, int var2, int var3, int var4) {
        if (this.d(var1, var2, var3)) {
            return this.o(var1, var2, var3);
        } else {
            int var5 = this.a(var1, var2, var3);
            return var5 == 0 ? false : OBlock.m[var5].b((OIBlockAccess) this, var1, var2, var3, var4);
        }
    }

    public boolean p(int var1, int var2, int var3) {
        return this.j(var1, var2 - 1, var3, 0) ? true : (this.j(var1, var2 + 1, var3, 1) ? true : (this.j(var1, var2, var3 - 1, 2) ? true : (this.j(var1, var2, var3 + 1, 3) ? true : (this.j(var1 - 1, var2, var3, 4) ? true : this.j(var1 + 1, var2, var3, 5)))));
    }

    public OEntityPlayer a(OEntity var1, double var2) {
        return this.a(var1.aL, var1.aM, var1.aN, var2);
    }

    public OEntityPlayer a(double var1, double var3, double var5, double var7) {
        double var9 = -1.0D;
        OEntityPlayer var11 = null;

        for (int var12 = 0; var12 < this.d.size(); ++var12) {
            OEntityPlayer var13 = (OEntityPlayer) this.d.get(var12);
            double var14 = var13.d(var1, var3, var5);
            if ((var7 < 0.0D || var14 < var7 * var7) && (var9 == -1.0D || var14 < var9)) {
                var9 = var14;
                var11 = var13;
            }
        }

        return var11;
    }

    public OEntityPlayer a(String var1) {
        for (int var2 = 0; var2 < this.d.size(); ++var2) {
            if (var1.equals(((OEntityPlayer) this.d.get(var2)).r)) {
                return (OEntityPlayer) this.d.get(var2);
            }
        }

        return null;
    }

    public byte[] c(int var1, int var2, int var3, int var4, int var5, int var6) {
        byte[] var7 = new byte[var4 * var5 * var6 * 5 / 2];
        int var8 = var1 >> 4;
        int var9 = var3 >> 4;
        int var10 = var1 + var4 - 1 >> 4;
        int var11 = var3 + var6 - 1 >> 4;
        int var12 = 0;
        int var13 = var2;
        int var14 = var2 + var5;
        if (var2 < 0) {
            var13 = 0;
        }

        if (var14 > 128) {
            var14 = 128;
        }

        for (int var15 = var8; var15 <= var10; ++var15) {
            int var16 = var1 - var15 * 16;
            int var17 = var1 + var4 - var15 * 16;
            if (var16 < 0) {
                var16 = 0;
            }

            if (var17 > 16) {
                var17 = 16;
            }

            for (int var18 = var9; var18 <= var11; ++var18) {
                int var19 = var3 - var18 * 16;
                int var20 = var3 + var6 - var18 * 16;
                if (var19 < 0) {
                    var19 = 0;
                }

                if (var20 > 16) {
                    var20 = 16;
                }

                var12 = this.c(var15, var18).a(var7, var16, var13, var19, var17, var14, var20, var12);
            }
        }

        return var7;
    }

    public void k() {
        this.r.b();
    }

    public void a(long var1) {
        this.s.a(var1);
    }

    public long l() {
        return this.s.b();
    }

    public long m() {
        return this.s.f();
    }

    public OChunkCoordinates n() {
        return new OChunkCoordinates(this.s.c(), this.s.d(), this.s.e());
    }

    public boolean a(OEntityPlayer var1, int var2, int var3, int var4) {
        return true;
    }

    public void a(OEntity var1, byte var2) {
    }

    public OIChunkProvider o() {
        return this.q;
    }

    public void d(int var1, int var2, int var3, int var4, int var5) {
        int var6 = this.a(var1, var2, var3);
        if (var6 > 0) {
            OBlock.m[var6].a(this, var1, var2, var3, var4, var5);
        }

    }

    public OISaveHandler p() {
        return this.r;
    }

    public OWorldInfo q() {
        return this.s;
    }

    public void r() {
        this.H = !this.d.isEmpty();
        Iterator var1 = this.d.iterator();

        while (var1.hasNext()) {
            OEntityPlayer var2 = (OEntityPlayer) var1.next();
            if (!var2.I()) {
                this.H = false;
                break;
            }
        }

    }

    protected void s() {
        this.H = false;
        Iterator var1 = this.d.iterator();

        while (var1.hasNext()) {
            OEntityPlayer var2 = (OEntityPlayer) var1.next();
            if (var2.I()) {
                var2.a(false, false, true);
            }
        }

        this.y();
    }

    public boolean t() {
        if (this.H && !this.v) {
            Iterator var1 = this.d.iterator();

            OEntityPlayer var2;
            do {
                if (!var1.hasNext()) {
                    return true;
                }

                var2 = (OEntityPlayer) var1.next();
            } while (var2.J());

            return false;
        } else {
            return false;
        }
    }

    public float c(float var1) {
        return (this.D + (this.E - this.D) * var1) * this.d(var1);
    }

    public float d(float var1) {
        return this.B + (this.C - this.B) * var1;
    }

    public boolean u() {
        return (double) this.c(1.0F) > 0.9D;
    }

    public boolean v() {
        return (double) this.d(1.0F) > 0.2D;
    }

    public boolean q(int var1, int var2, int var3) {
        if (!this.v()) {
            return false;
        } else if (!this.i(var1, var2, var3)) {
            return false;
        } else if (this.e(var1, var3) > var2) {
            return false;
        } else {
            OBiomeGenBase var4 = this.a().a(var1, var3);
            return var4.c() ? false : var4.d();
        }
    }
}
