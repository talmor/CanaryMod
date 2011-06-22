import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class OExplosion {

    public boolean a = false;
    private Random h = new Random();
    private OWorld i;
    public double  b;
    public double  c;
    public double  d;
    public OEntity e;
    public float   f;
    public Set     g = new HashSet();

    public OExplosion(OWorld paramOWorld, OEntity paramOEntity, double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat) {
        i = paramOWorld;
        e = paramOEntity;
        f = paramFloat;
        b = paramDouble1;
        c = paramDouble2;
        d = paramDouble3;
    }

    public void a() {
        // CanaryMod: allow explosion
        Block block = new Block(i.world, i.a((int) Math.floor(b), (int) Math.floor(c), (int) Math.floor(d)), (int) Math.floor(b), (int) Math.floor(c), (int) Math.floor(d));

        // CanaryMod: preserve source through blockstatus.
        if (e == null)
            block.setStatus(1); // TNT
        else if (e instanceof OEntityCreeper)
            block.setStatus(2); // Creeper

        // CanaryMod: call explode hook.
        if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.EXPLODE, block))
            return;

        float f1 = f;

        int j = 16;
        double d5;
        double d6;
        double d7;
        for (int k = 0; k < j; k++)
            for (int m = 0; m < j; m++)
                for (int n = 0; n < j; n++) {
                    if ((k != 0) && (k != j - 1) && (m != 0) && (m != j - 1) && (n != 0) && (n != j - 1))
                        continue;
                    double d1 = k / (j - 1.0F) * 2.0F - 1.0F;
                    double d2 = m / (j - 1.0F) * 2.0F - 1.0F;
                    double d3 = n / (j - 1.0F) * 2.0F - 1.0F;
                    double d4 = Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);

                    d1 /= d4;
                    d2 /= d4;
                    d3 /= d4;

                    float f2 = f * (0.7F + i.r.nextFloat() * 0.6F);
                    d5 = b;
                    d6 = c;
                    d7 = d;

                    float f3 = 0.3F;
                    while (f2 > 0.0F) {
                        int i2 = OMathHelper.b(d5);
                        int i3 = OMathHelper.b(d6);
                        int i4 = OMathHelper.b(d7);
                        int i5 = i.a(i2, i3, i4);
                        if (i5 > 0)
                            f2 -= (OBlock.m[i5].a(e) + 0.3F) * f3;
                        if (f2 > 0.0F)
                            g.add(new OChunkPosition(i2, i3, i4));

                        d5 += d1 * f3;
                        d6 += d2 * f3;
                        d7 += d3 * f3;
                        f2 -= f3 * 0.75F;
                    }
                }

        f *= 2.0F;
        int k = OMathHelper.b(b - f - 1.0D);
        int m = OMathHelper.b(b + f + 1.0D);
        int n = OMathHelper.b(c - f - 1.0D);
        int i6 = OMathHelper.b(c + f + 1.0D);
        int i7 = OMathHelper.b(d - f - 1.0D);
        int i8 = OMathHelper.b(d + f + 1.0D);
        List localList = i.b(e, OAxisAlignedBB.b(k, n, i7, m, i6, i8));
        OVec3D localOVec3D = OVec3D.b(b, c, d);
        for (int i9 = 0; i9 < localList.size(); i9++) {
            OEntity localOEntity = (OEntity) localList.get(i9);
            double d8 = localOEntity.e(b, c, d) / f;
            if (d8 <= 1.0D) {
                d5 = localOEntity.aP - b;
                d6 = localOEntity.aQ - c;
                d7 = localOEntity.aR - d;

                double d9 = OMathHelper.a(d5 * d5 + d6 * d6 + d7 * d7);

                d5 /= d9;
                d6 /= d9;
                d7 /= d9;

                double d10 = i.a(localOVec3D, localOEntity.aZ);
                double d11 = (1.0D - d8) * d10;
                // CanaryMod Damage hook: Explosions
                int damage = (int) ((d11 * d11 + d11) / 2.0D * 8.0D * f + 1.0D);
                PluginLoader.DamageType dmgType = (e instanceof OEntityCreeper) ? PluginLoader.DamageType.CREEPER_EXPLOSION : PluginLoader.DamageType.EXPLOSION;
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, dmgType, null, localOEntity.entity, damage))
                    localOEntity.a(e, (int) ((d11 * d11 + d11) / 2.0D * 8.0D * f + 1.0D));

                double d12 = d11;
                localOEntity.aS += d5 * d12;
                localOEntity.aT += d6 * d12;
                localOEntity.aU += d7 * d12;
            }
        }
        f = f1;

        ArrayList localArrayList = new ArrayList();
        localArrayList.addAll(g);

        if (a)
            for (int i10 = localArrayList.size() - 1; i10 >= 0; i10--) {
                OChunkPosition localOChunkPosition = (OChunkPosition) localArrayList.get(i10);
                int i1 = localOChunkPosition.a;
                int i11 = localOChunkPosition.b;
                int i12 = localOChunkPosition.c;
                int i13 = i.a(i1, i11, i12);
                int i14 = i.a(i1, i11 - 1, i12);
                if ((i13 == 0) && OBlock.o[i14] && (h.nextInt(3) == 0))
                    i.e(i1, i11, i12, OBlock.as.bn);
            }
    }

    public void a(boolean var1) {
        this.i.a(this.b, this.c, this.d, "random.explode", 4.0F, (1.0F + (this.i.r.nextFloat() - this.i.r.nextFloat()) * 0.2F) * 0.7F);
        ArrayList var2 = new ArrayList();
        var2.addAll(this.g);

        for (int var3 = var2.size() - 1; var3 >= 0; --var3) {
            OChunkPosition var4 = (OChunkPosition) var2.get(var3);
            int var5 = var4.a;
            int var6 = var4.b;
            int var7 = var4.c;
            int var8 = this.i.a(var5, var6, var7);
            if (var1) {
                double var9 = (double) ((float) var5 + this.i.r.nextFloat());
                double var11 = (double) ((float) var6 + this.i.r.nextFloat());
                double var13 = (double) ((float) var7 + this.i.r.nextFloat());
                double var15 = var9 - this.b;
                double var17 = var11 - this.c;
                double var19 = var13 - this.d;
                double var21 = (double) OMathHelper.a(var15 * var15 + var17 * var17 + var19 * var19);
                var15 /= var21;
                var17 /= var21;
                var19 /= var21;
                double var23 = 0.5D / (var21 / (double) this.f + 0.1D);
                var23 *= (double) (this.i.r.nextFloat() * this.i.r.nextFloat() + 0.3F);
                var15 *= var23;
                var17 *= var23;
                var19 *= var23;
                this.i.a("explode", (var9 + this.b * 1.0D) / 2.0D, (var11 + this.c * 1.0D) / 2.0D, (var13 + this.d * 1.0D) / 2.0D, var15, var17, var19);
                this.i.a("smoke", var9, var11, var13, var15, var17, var19);
            }

            if (var8 > 0) {
                OBlock.m[var8].a(this.i, var5, var6, var7, this.i.b(var5, var6, var7), 0.3F);
                this.i.e(var5, var6, var7, 0);
                OBlock.m[var8].c(this.i, var5, var6, var7);
            }
        }

    }

}
