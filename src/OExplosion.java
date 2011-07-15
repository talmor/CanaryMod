
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class OExplosion {

   public boolean a = false;
   private Random h = new Random();
   private OWorld i;
   public double b;
   public double c;
   public double d;
   public OEntity e;
   public float f;
   public Set g = new HashSet();


   public OExplosion(OWorld var1, OEntity var2, double var3, double var5, double var7, float var9) {
      this.i = var1;
      this.e = var2;
      this.f = var9;
      this.b = var3;
      this.c = var5;
      this.d = var7;
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

      float var1 = this.f;
      byte var2 = 16;

      int var3;
      int var4;
      int var5;
      double var15;
      double var17;
      double var19;
      for(var3 = 0; var3 < var2; ++var3) {
         for(var4 = 0; var4 < var2; ++var4) {
            for(var5 = 0; var5 < var2; ++var5) {
               if(var3 == 0 || var3 == var2 - 1 || var4 == 0 || var4 == var2 - 1 || var5 == 0 || var5 == var2 - 1) {
                  double var6 = (double)((float)var3 / ((float)var2 - 1.0F) * 2.0F - 1.0F);
                  double var8 = (double)((float)var4 / ((float)var2 - 1.0F) * 2.0F - 1.0F);
                  double var10 = (double)((float)var5 / ((float)var2 - 1.0F) * 2.0F - 1.0F);
                  double var12 = Math.sqrt(var6 * var6 + var8 * var8 + var10 * var10);
                  var6 /= var12;
                  var8 /= var12;
                  var10 /= var12;
                  float var14 = this.f * (0.7F + this.i.r.nextFloat() * 0.6F);
                  var15 = this.b;
                  var17 = this.c;
                  var19 = this.d;

                  for(float var21 = 0.3F; var14 > 0.0F; var14 -= var21 * 0.75F) {
                     int var22 = OMathHelper.b(var15);
                     int var23 = OMathHelper.b(var17);
                     int var24 = OMathHelper.b(var19);
                     int var25 = this.i.a(var22, var23, var24);
                     if(var25 > 0) {
                        var14 -= (OBlock.m[var25].a(this.e) + 0.3F) * var21;
                     }

                     if(var14 > 0.0F) {
                        this.g.add(new OChunkPosition(var22, var23, var24));
                     }

                     var15 += var6 * (double)var21;
                     var17 += var8 * (double)var21;
                     var19 += var10 * (double)var21;
                  }
               }
            }
         }
      }

      this.f *= 2.0F;
      var3 = OMathHelper.b(this.b - (double)this.f - 1.0D);
      var4 = OMathHelper.b(this.b + (double)this.f + 1.0D);
      var5 = OMathHelper.b(this.c - (double)this.f - 1.0D);
      int var26 = OMathHelper.b(this.c + (double)this.f + 1.0D);
      int var27 = OMathHelper.b(this.d - (double)this.f - 1.0D);
      int var28 = OMathHelper.b(this.d + (double)this.f + 1.0D);
      List var29 = this.i.b(this.e, OAxisAlignedBB.b((double)var3, (double)var5, (double)var27, (double)var4, (double)var26, (double)var28));
      OVec3D var30 = OVec3D.b(this.b, this.c, this.d);

      for(int var31 = 0; var31 < var29.size(); ++var31) {
         OEntity var32 = (OEntity)var29.get(var31);
         double var33 = var32.f(this.b, this.c, this.d) / (double)this.f;
         if(var33 <= 1.0D) {
            var15 = var32.aP - this.b;
            var17 = var32.aQ - this.c;
            var19 = var32.aR - this.d;
            double var35 = (double)OMathHelper.a(var15 * var15 + var17 * var17 + var19 * var19);
            var15 /= var35;
            var17 /= var35;
            var19 /= var35;
            double var37 = (double)this.i.a(var30, var32.aZ);
            double var39 = (1.0D - var33) * var37;
            
            // CanaryMod Damage hook: Explosions
            int damage = (int) ((var39 * var39 + var39) / 2.0D * 8.0D * f + 1.0D);
            PluginLoader.DamageType dmgType = (e instanceof OEntityCreeper) ? PluginLoader.DamageType.CREEPER_EXPLOSION : PluginLoader.DamageType.EXPLOSION;
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, dmgType, null, var32.entity, damage))
               var32.a(this.e, (int)((var39 * var39 + var39) / 2.0D * 8.0D * (double)this.f + 1.0D));
            
            var32.aS += var15 * var39;
            var32.aT += var17 * var39;
            var32.aU += var19 * var39;
         }
      }

      this.f = var1;
      ArrayList var50 = new ArrayList();
      var50.addAll(this.g);
      if(this.a) {
         for(int var49 = var50.size() - 1; var49 >= 0; --var49) {
            OChunkPosition var43 = (OChunkPosition)var50.get(var49);
            int var48 = var43.a;
            int var44 = var43.b;
            int var45 = var43.c;
            int var46 = this.i.a(var48, var44, var45);
            int var47 = this.i.a(var48, var44 - 1, var45);
            if(var46 == 0 && OBlock.o[var47] && this.h.nextInt(3) == 0) {
               this.i.e(var48, var44, var45, OBlock.as.bn);
            }
         }
      }

   }

   public void a(boolean var1) {
      this.i.a(this.b, this.c, this.d, "random.explode", 4.0F, (1.0F + (this.i.r.nextFloat() - this.i.r.nextFloat()) * 0.2F) * 0.7F);
      ArrayList var2 = new ArrayList();
      var2.addAll(this.g);

      for(int var3 = var2.size() - 1; var3 >= 0; --var3) {
         OChunkPosition var4 = (OChunkPosition)var2.get(var3);
         int var5 = var4.a;
         int var6 = var4.b;
         int var7 = var4.c;
         int var8 = this.i.a(var5, var6, var7);
         if(var1) {
            double var9 = (double)((float)var5 + this.i.r.nextFloat());
            double var11 = (double)((float)var6 + this.i.r.nextFloat());
            double var13 = (double)((float)var7 + this.i.r.nextFloat());
            double var15 = var9 - this.b;
            double var17 = var11 - this.c;
            double var19 = var13 - this.d;
            double var21 = (double)OMathHelper.a(var15 * var15 + var17 * var17 + var19 * var19);
            var15 /= var21;
            var17 /= var21;
            var19 /= var21;
            double var23 = 0.5D / (var21 / (double)this.f + 0.1D);
            var23 *= (double)(this.i.r.nextFloat() * this.i.r.nextFloat() + 0.3F);
            var15 *= var23;
            var17 *= var23;
            var19 *= var23;
            this.i.a("explode", (var9 + this.b * 1.0D) / 2.0D, (var11 + this.c * 1.0D) / 2.0D, (var13 + this.d * 1.0D) / 2.0D, var15, var17, var19);
            this.i.a("smoke", var9, var11, var13, var15, var17, var19);
         }

         if(var8 > 0) {
            OBlock.m[var8].a(this.i, var5, var6, var7, this.i.c(var5, var6, var7), 0.3F);
            this.i.e(var5, var6, var7, 0);
            OBlock.m[var8].d(this.i, var5, var6, var7);
         }
      }

   }
}
