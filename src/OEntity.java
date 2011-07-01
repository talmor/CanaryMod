
import java.util.List;
import java.util.Random;

public abstract class OEntity {

   private static int a = 0;
   public int aG;
   public double aH;
   public boolean aI;
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
   public final OAxisAlignedBB aZ;
   public boolean ba;
   public boolean bb;
   public boolean bc;
   public boolean bd;
   public boolean be;
   public boolean bf;
   public boolean bg;
   public boolean bh;
   public float bi;
   public float bj;
   public float bk;
   public float bl;
   public float bm;
   protected float bn;
   private int b;
   public double bo;
   public double bp;
   public double bq;
   public float br;
   public float bs;
   public boolean bt;
   public float bu;
   protected Random bv;
   public int bw;
   public int bx;
   public int by;
   protected int bz;
   protected boolean bA;
   public int bB;
   public int bC;
   private boolean c;
   protected boolean bD;
   protected ODataWatcher bE;
   private double d;
   private double e;
   public boolean bF;
   public int bG;
   public int bH;
   public int bI;
   public boolean bJ;
   // CanaryMod Start
   BaseEntity entity = new BaseEntity(this);
   public static PluginLoader manager = etc.getLoader();
   // CanaryMod end

   public OEntity(OWorld var1) {
      this.aG = a++;
      this.aH = 1.0D;
      this.aI = false;
      this.aZ = OAxisAlignedBB.a(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
      this.ba = false;
      this.bd = false;
      this.be = false;
      this.bg = true;
      this.bh = false;
      this.bi = 0.0F;
      this.bj = 0.6F;
      this.bk = 1.8F;
      this.bl = 0.0F;
      this.bm = 0.0F;
      this.bn = 0.0F;
      this.b = 1;
      this.br = 0.0F;
      this.bs = 0.0F;
      this.bt = false;
      this.bu = 0.0F;
      this.bv = new Random();
      this.bw = 0;
      this.bx = 1;
      this.by = 0;
      this.bz = 300;
      this.bA = false;
      this.bB = 0;
      this.bC = 300;
      this.c = true;
      this.bD = false;
      this.bE = new ODataWatcher();
      this.bF = false;
      this.aL = var1;
      this.a(0.0D, 0.0D, 0.0D);
      this.bE.a(0, Byte.valueOf((byte)0));
      this.b();
   }

   protected abstract void b();

   public ODataWatcher Z() {
      return this.bE;
   }

   public boolean equals(Object var1) {
      return var1 instanceof OEntity?((OEntity)var1).aG == this.aG:false;
   }

   public int hashCode() {
      return this.aG;
   }

   public void I() {
      this.bh = true;
   }

   protected void b(float var1, float var2) {
      this.bj = var1;
      this.bk = var2;
   }

   protected void c(float var1, float var2) {
      this.aV = var1 % 360.0F;
      this.aW = var2 % 360.0F;
   }

   public void a(double var1, double var3, double var5) {
      this.aP = var1;
      this.aQ = var3;
      this.aR = var5;
      float var7 = this.bj / 2.0F;
      float var8 = this.bk;
      this.aZ.c(var1 - (double)var7, var3 - (double)this.bi + (double)this.br, var5 - (double)var7, var1 + (double)var7, var3 - (double)this.bi + (double)this.br + (double)var8, var5 + (double)var7);
   }

   public void o_() {
      this.Q();
   }

   public void Q() {
      if(this.aK != null && this.aK.bh) {
         this.aK = null;
      }

      ++this.bw;
      this.bl = this.bm;
      this.aM = this.aP;
      this.aN = this.aQ;
      this.aO = this.aR;
      this.aY = this.aW;
      this.aX = this.aV;
      if(this.f_()) {
         if(!this.bA && !this.c) {
            float var1 = OMathHelper.a(this.aS * this.aS * 0.20000000298023224D + this.aT * this.aT + this.aU * this.aU * 0.20000000298023224D) * 0.2F;
            if(var1 > 1.0F) {
               var1 = 1.0F;
            }

            this.aL.a(this, "random.splash", var1, 1.0F + (this.bv.nextFloat() - this.bv.nextFloat()) * 0.4F);
            float var2 = (float)OMathHelper.b(this.aZ.b);

            int var3;
            float var4;
            float var5;
            for(var3 = 0; (float)var3 < 1.0F + this.bj * 20.0F; ++var3) {
               var4 = (this.bv.nextFloat() * 2.0F - 1.0F) * this.bj;
               var5 = (this.bv.nextFloat() * 2.0F - 1.0F) * this.bj;
               this.aL.a("bubble", this.aP + (double)var4, (double)(var2 + 1.0F), this.aR + (double)var5, this.aS, this.aT - (double)(this.bv.nextFloat() * 0.2F), this.aU);
            }

            for(var3 = 0; (float)var3 < 1.0F + this.bj * 20.0F; ++var3) {
               var4 = (this.bv.nextFloat() * 2.0F - 1.0F) * this.bj;
               var5 = (this.bv.nextFloat() * 2.0F - 1.0F) * this.bj;
               this.aL.a("splash", this.aP + (double)var4, (double)(var2 + 1.0F), this.aR + (double)var5, this.aS, this.aT, this.aU);
            }
         }

         this.bn = 0.0F;
         this.bA = true;
         this.by = 0;
      } else {
         this.bA = false;
      }

      if(this.aL.B) {
         this.by = 0;
      } else if(this.by > 0) {
         if(this.bD) {
            this.by -= 4;
            if(this.by < 0) {
               this.by = 0;
            }
         } else {
            if(this.by % 20 == 0) {
               // CanaryMod Damage hook: Periodic burn damage
               if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.FIRE_TICK, null, entity, 1))
                  this.a((OEntity)null, 1);
            }

            --this.by;
         }
      }

      if(this.ad()) {
         this.aa();
      }

      if(this.aQ < -64.0D) {
         this.X();
      }

      if(!this.aL.B) {
         this.a(0, this.by > 0);
         this.a(2, this.aK != null);
      }

      this.c = false;
   }

   protected void aa() {
      if(!this.bD) {
         // CanaryMod Damage hook: Lava
         if (this instanceof OEntityLiving)
            if ((Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.LAVA, null, entity, 4))
               return;

         this.a((OEntity)null, 4);
         this.by = 600;
      }

   }

   protected void X() {
      this.I();
   }

   public boolean b(double var1, double var3, double var5) {
      OAxisAlignedBB var7 = this.aZ.c(var1, var3, var5);
      List var8 = this.aL.a(this, var7);
      return var8.size() > 0?false:!this.aL.c(var7);
   }

   public void c(double var1, double var3, double var5) {
      if(this.bt) {
         this.aZ.d(var1, var3, var5);
         this.aP = (this.aZ.a + this.aZ.d) / 2.0D;
         this.aQ = this.aZ.b + (double)this.bi - (double)this.br;
         this.aR = (this.aZ.c + this.aZ.f) / 2.0D;
      } else {
         this.br *= 0.4F;
         double var7 = this.aP;
         double var9 = this.aR;
         if(this.bf) {
            this.bf = false;
            var1 *= 0.25D;
            var3 *= 0.05000000074505806D;
            var5 *= 0.25D;
            this.aS = 0.0D;
            this.aT = 0.0D;
            this.aU = 0.0D;
         }

         double var11 = var1;
         double var13 = var3;
         double var15 = var5;
         OAxisAlignedBB var17 = this.aZ.b();
         boolean var18 = this.ba && this.ag();
         if(var18) {
            double var19;
            for(var19 = 0.05D; var1 != 0.0D && this.aL.a(this, this.aZ.c(var1, -1.0D, 0.0D)).size() == 0; var11 = var1) {
               if(var1 < var19 && var1 >= -var19) {
                  var1 = 0.0D;
               } else if(var1 > 0.0D) {
                  var1 -= var19;
               } else {
                  var1 += var19;
               }
            }

            for(; var5 != 0.0D && this.aL.a(this, this.aZ.c(0.0D, -1.0D, var5)).size() == 0; var15 = var5) {
               if(var5 < var19 && var5 >= -var19) {
                  var5 = 0.0D;
               } else if(var5 > 0.0D) {
                  var5 -= var19;
               } else {
                  var5 += var19;
               }
            }
         }

         List var21 = this.aL.a(this, this.aZ.a(var1, var3, var5));

         for(int var22 = 0; var22 < var21.size(); ++var22) {
            var3 = ((OAxisAlignedBB)var21.get(var22)).b(this.aZ, var3);
         }

         this.aZ.d(0.0D, var3, 0.0D);
         if(!this.bg && var13 != var3) {
            var5 = 0.0D;
            var3 = 0.0D;
            var1 = 0.0D;
         }

         boolean var43 = this.ba || var13 != var3 && var13 < 0.0D;

         int var23;
         for(var23 = 0; var23 < var21.size(); ++var23) {
            var1 = ((OAxisAlignedBB)var21.get(var23)).a(this.aZ, var1);
         }

         this.aZ.d(var1, 0.0D, 0.0D);
         if(!this.bg && var11 != var1) {
            var5 = 0.0D;
            var3 = 0.0D;
            var1 = 0.0D;
         }

         for(var23 = 0; var23 < var21.size(); ++var23) {
            var5 = ((OAxisAlignedBB)var21.get(var23)).c(this.aZ, var5);
         }

         this.aZ.d(0.0D, 0.0D, var5);
         if(!this.bg && var15 != var5) {
            var5 = 0.0D;
            var3 = 0.0D;
            var1 = 0.0D;
         }

         double var24;
         double var26;
         int var31;
         if(this.bs > 0.0F && var43 && (var18 || this.br < 0.05F) && (var11 != var1 || var15 != var5)) {
            var24 = var1;
            var26 = var3;
            double var28 = var5;
            var1 = var11;
            var3 = (double)this.bs;
            var5 = var15;
            OAxisAlignedBB var30 = this.aZ.b();
            this.aZ.b(var17);
            var21 = this.aL.a(this, this.aZ.a(var11, var3, var15));

            for(var31 = 0; var31 < var21.size(); ++var31) {
               var3 = ((OAxisAlignedBB)var21.get(var31)).b(this.aZ, var3);
            }

            this.aZ.d(0.0D, var3, 0.0D);
            if(!this.bg && var13 != var3) {
               var5 = 0.0D;
               var3 = 0.0D;
               var1 = 0.0D;
            }

            for(var31 = 0; var31 < var21.size(); ++var31) {
               var1 = ((OAxisAlignedBB)var21.get(var31)).a(this.aZ, var1);
            }

            this.aZ.d(var1, 0.0D, 0.0D);
            if(!this.bg && var11 != var1) {
               var5 = 0.0D;
               var3 = 0.0D;
               var1 = 0.0D;
            }

            for(var31 = 0; var31 < var21.size(); ++var31) {
               var5 = ((OAxisAlignedBB)var21.get(var31)).c(this.aZ, var5);
            }

            this.aZ.d(0.0D, 0.0D, var5);
            if(!this.bg && var15 != var5) {
               var5 = 0.0D;
               var3 = 0.0D;
               var1 = 0.0D;
            }

            if(!this.bg && var13 != var3) {
               var5 = 0.0D;
               var3 = 0.0D;
               var1 = 0.0D;
            } else {
               var3 = (double)(-this.bs);

               for(var31 = 0; var31 < var21.size(); ++var31) {
                  var3 = ((OAxisAlignedBB)var21.get(var31)).b(this.aZ, var3);
               }

               this.aZ.d(0.0D, var3, 0.0D);
            }

            if(var24 * var24 + var28 * var28 >= var1 * var1 + var5 * var5) {
               var1 = var24;
               var3 = var26;
               var5 = var28;
               this.aZ.b(var30);
            } else {
               double var32 = this.aZ.b - (double)((int)this.aZ.b);
               if(var32 > 0.0D) {
                  this.br = (float)((double)this.br + var32 + 0.01D);
               }
            }
         }

         this.aP = (this.aZ.a + this.aZ.d) / 2.0D;
         this.aQ = this.aZ.b + (double)this.bi - (double)this.br;
         this.aR = (this.aZ.c + this.aZ.f) / 2.0D;
         this.bb = var11 != var1 || var15 != var5;
         this.bc = var13 != var3;
         this.ba = var13 != var3 && var13 < 0.0D;
         this.bd = this.bb || this.bc;
         this.a(var3, this.ba);
         if(var11 != var1) {
            this.aS = 0.0D;
         }

         if(var13 != var3) {
            this.aT = 0.0D;
         }

         if(var15 != var5) {
            this.aU = 0.0D;
         }

         var24 = this.aP - var7;
         var26 = this.aR - var9;
         int var34;
         int var35;
         int var45;
         if(this.n() && !var18 && this.aK == null) {
            this.bm = (float)((double)this.bm + (double)OMathHelper.a(var24 * var24 + var26 * var26) * 0.6D);
            var34 = OMathHelper.b(this.aP);
            var35 = OMathHelper.b(this.aQ - 0.20000000298023224D - (double)this.bi);
            var45 = OMathHelper.b(this.aR);
            var31 = this.aL.a(var34, var35, var45);
            if(this.aL.a(var34, var35 - 1, var45) == OBlock.ba.bn) {
               var31 = this.aL.a(var34, var35 - 1, var45);
            }

            if(this.bm > (float)this.b && var31 > 0) {
               ++this.b;
               OStepSound var36 = OBlock.m[var31].by;
               if(this.aL.a(var34, var35 + 1, var45) == OBlock.aT.bn) {
                  var36 = OBlock.aT.by;
                  this.aL.a(this, var36.c(), var36.a() * 0.15F, var36.b());
               } else if(!OBlock.m[var31].bA.d()) {
                  this.aL.a(this, var36.c(), var36.a() * 0.15F, var36.b());
               }

               OBlock.m[var31].b(this.aL, var34, var35, var45, this);
            }
         }

         var34 = OMathHelper.b(this.aZ.a + 0.0010D);
         var35 = OMathHelper.b(this.aZ.b + 0.0010D);
         var45 = OMathHelper.b(this.aZ.c + 0.0010D);
         var31 = OMathHelper.b(this.aZ.d - 0.0010D);
         int var42 = OMathHelper.b(this.aZ.e - 0.0010D);
         int var37 = OMathHelper.b(this.aZ.f - 0.0010D);
         if(this.aL.a(var34, var35, var45, var31, var42, var37)) {
            for(int var38 = var34; var38 <= var31; ++var38) {
               for(int var39 = var35; var39 <= var42; ++var39) {
                  for(int var40 = var45; var40 <= var37; ++var40) {
                     int var41 = this.aL.a(var38, var39, var40);
                     if(var41 > 0) {
                        OBlock.m[var41].a(this.aL, var38, var39, var40, this);
                     }
                  }
               }
            }
         }

         boolean var44 = this.ab();
         if(this.aL.d(this.aZ.e(0.0010D, 0.0010D, 0.0010D))) {
            this.a(1);
            if(!var44) {
               ++this.by;
               if(this.by == 0) {
                  this.by = 300;
               }
            }
         } else if(this.by <= 0) {
            this.by = -this.bx;
         }

         if(var44 && this.by > 0) {
            this.aL.a(this, "random.fizz", 0.7F, 1.6F + (this.bv.nextFloat() - this.bv.nextFloat()) * 0.4F);
            this.by = -this.bx;
         }

      }
   }

   protected boolean n() {
      return true;
   }

   protected void a(double var1, boolean var3) {
      if(var3) {
         if(this.bn > 0.0F) {
            this.a(this.bn);
            this.bn = 0.0F;
         }
      } else if(var1 < 0.0D) {
         this.bn = (float)((double)this.bn - var1);
      }

   }

   public OAxisAlignedBB e_() {
      return null;
   }

   protected void a(int var1) {
      if(!this.bD) {
         // CanaryMod Damage Hook: Fire
         if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.FIRE, null, entity, var1))
            this.a((OEntity)null, var1);
      }

   }

   protected void a(float var1) {
      if(this.aJ != null) {
         this.aJ.a(var1);
      }

   }

   public boolean ab() {
      return this.bA || this.aL.s(OMathHelper.b(this.aP), OMathHelper.b(this.aQ), OMathHelper.b(this.aR));
   }

   public boolean ac() {
      return this.bA;
   }

   public boolean f_() {
      return this.aL.a(this.aZ.b(0.0D, -0.4000000059604645D, 0.0D).e(0.0010D, 0.0010D, 0.0010D), OMaterial.g, this);
   }

   public boolean a(OMaterial var1) {
      double var2 = this.aQ + (double)this.s();
      int var4 = OMathHelper.b(this.aP);
      int var5 = OMathHelper.d((float)OMathHelper.b(var2));
      int var6 = OMathHelper.b(this.aR);
      int var7 = this.aL.a(var4, var5, var6);
      if(var7 != 0 && OBlock.m[var7].bA == var1) {
         float var8 = OBlockFluid.c(this.aL.b(var4, var5, var6)) - 0.11111111F;
         float var9 = (float)(var5 + 1) - var8;
         return var2 < (double)var9;
      } else {
         return false;
      }
   }

   public float s() {
      return 0.0F;
   }

   public boolean ad() {
      return this.aL.a(this.aZ.b(-0.10000000149011612D, -0.4000000059604645D, -0.10000000149011612D), OMaterial.h);
   }

   public void a(float var1, float var2, float var3) {
      float var4 = OMathHelper.c(var1 * var1 + var2 * var2);
      if(var4 >= 0.01F) {
         if(var4 < 1.0F) {
            var4 = 1.0F;
         }

         var4 = var3 / var4;
         var1 *= var4;
         var2 *= var4;
         float var5 = OMathHelper.a(this.aV * 3.1415927F / 180.0F);
         float var6 = OMathHelper.b(this.aV * 3.1415927F / 180.0F);
         this.aS += (double)(var1 * var6 - var2 * var5);
         this.aU += (double)(var2 * var6 + var1 * var5);
      }
   }

   public float c(float var1) {
      int var2 = OMathHelper.b(this.aP);
      double var3 = (this.aZ.e - this.aZ.b) * 0.66D;
      int var5 = OMathHelper.b(this.aQ - (double)this.bi + var3);
      int var6 = OMathHelper.b(this.aR);
      return this.aL.a(OMathHelper.b(this.aZ.a), OMathHelper.b(this.aZ.b), OMathHelper.b(this.aZ.c), OMathHelper.b(this.aZ.d), OMathHelper.b(this.aZ.e), OMathHelper.b(this.aZ.f))?this.aL.m(var2, var5, var6):0.0F;
   }

   public void a(OWorld var1) {
      this.aL = var1;
   }

   public void b(double var1, double var3, double var5, float var7, float var8) {
      this.aM = this.aP = var1;
      this.aN = this.aQ = var3;
      this.aO = this.aR = var5;
      this.aX = this.aV = var7;
      this.aY = this.aW = var8;
      this.br = 0.0F;
      double var9 = (double)(this.aX - var7);
      if(var9 < -180.0D) {
         this.aX += 360.0F;
      }

      if(var9 >= 180.0D) {
         this.aX -= 360.0F;
      }

      this.a(this.aP, this.aQ, this.aR);
      this.c(var7, var8);
   }

   public void c(double var1, double var3, double var5, float var7, float var8) {
      this.bo = this.aM = this.aP = var1;
      this.bp = this.aN = this.aQ = var3 + (double)this.bi;
      this.bq = this.aO = this.aR = var5;
      this.aV = var7;
      this.aW = var8;
      this.a(this.aP, this.aQ, this.aR);
   }

   public float f(OEntity var1) {
      float var2 = (float)(this.aP - var1.aP);
      float var3 = (float)(this.aQ - var1.aQ);
      float var4 = (float)(this.aR - var1.aR);
      return OMathHelper.c(var2 * var2 + var3 * var3 + var4 * var4);
   }

   public double d(double var1, double var3, double var5) {
      double var7 = this.aP - var1;
      double var9 = this.aQ - var3;
      double var11 = this.aR - var5;
      return var7 * var7 + var9 * var9 + var11 * var11;
   }

   public double e(double var1, double var3, double var5) {
      double var7 = this.aP - var1;
      double var9 = this.aQ - var3;
      double var11 = this.aR - var5;
      return (double)OMathHelper.a(var7 * var7 + var9 * var9 + var11 * var11);
   }

   public double g(OEntity var1) {
      double var2 = this.aP - var1.aP;
      double var4 = this.aQ - var1.aQ;
      double var6 = this.aR - var1.aR;
      return var2 * var2 + var4 * var4 + var6 * var6;
   }

   public void b(OEntityPlayer var1) {
   }

   public void h(OEntity var1) {
      if(var1.aJ != this && var1.aK != this) {
         double var2 = var1.aP - this.aP;
         double var4 = var1.aR - this.aR;
         double var6 = OMathHelper.a(var2, var4);
         if(var6 >= 0.009999999776482582D) {
            var6 = (double)OMathHelper.a(var6);
            var2 /= var6;
            var4 /= var6;
            double var8 = 1.0D / var6;
            if(var8 > 1.0D) {
               var8 = 1.0D;
            }

            var2 *= var8;
            var4 *= var8;
            var2 *= 0.05000000074505806D;
            var4 *= 0.05000000074505806D;
            var2 *= (double)(1.0F - this.bu);
            var4 *= (double)(1.0F - this.bu);
            this.f(-var2, 0.0D, -var4);
            var1.f(var2, 0.0D, var4);
         }

      }
   }

   public void f(double var1, double var3, double var5) {
      this.aS += var1;
      this.aT += var3;
      this.aU += var5;
   }

   protected void ae() {
      this.be = true;
   }

   public boolean a(OEntity var1, int var2) {
      this.ae();
      return false;
   }

   public boolean n_() {
      return false;
   }

   public boolean d_() {
      return false;
   }

   public void c(OEntity var1, int var2) {
   }

   public boolean c(ONBTTagCompound var1) {
      String var2 = this.af();
      if(!this.bh && var2 != null) {
         // CanaryMod: fix jarjar
         var1.a("id", var2);
         this.d(var1);
         return true;
      } else {
         return false;
      }
   }

   public void d(ONBTTagCompound var1) {
      var1.a("Pos", this.a(new double[]{this.aP, this.aQ + (double)this.br, this.aR}));
      var1.a("Motion", this.a(new double[]{this.aS, this.aT, this.aU}));
      var1.a("Rotation", this.a(new float[]{this.aV, this.aW}));
      var1.a("FallDistance", this.bn);
      var1.a("Fire", (short)this.by);
      var1.a("Air", (short)this.bC);
      var1.a("OnGround", this.ba);
      this.b(var1);
   }

   public void e(ONBTTagCompound var1) {
      ONBTTagList var2 = var1.l("Pos");
      ONBTTagList var3 = var1.l("Motion");
      ONBTTagList var4 = var1.l("Rotation");
      this.aS = ((ONBTTagDouble)var3.a(0)).a;
      this.aT = ((ONBTTagDouble)var3.a(1)).a;
      this.aU = ((ONBTTagDouble)var3.a(2)).a;
      if(Math.abs(this.aS) > 10.0D) {
         this.aS = 0.0D;
      }

      if(Math.abs(this.aT) > 10.0D) {
         this.aT = 0.0D;
      }

      if(Math.abs(this.aU) > 10.0D) {
         this.aU = 0.0D;
      }

      this.aM = this.bo = this.aP = ((ONBTTagDouble)var2.a(0)).a;
      this.aN = this.bp = this.aQ = ((ONBTTagDouble)var2.a(1)).a;
      this.aO = this.bq = this.aR = ((ONBTTagDouble)var2.a(2)).a;
      this.aX = this.aV = ((ONBTTagFloat)var4.a(0)).a;
      this.aY = this.aW = ((ONBTTagFloat)var4.a(1)).a;
      this.bn = var1.g("FallDistance");
      this.by = var1.d("Fire");
      this.bC = var1.d("Air");
      this.ba = var1.m("OnGround");
      this.a(this.aP, this.aQ, this.aR);
      this.c(this.aV, this.aW);
      this.a(var1);
   }

   protected final String af() {
      return OEntityList.b(this);
   }

   protected abstract void a(ONBTTagCompound var1);

   protected abstract void b(ONBTTagCompound var1);

   protected ONBTTagList a(double ... var1) {
      ONBTTagList var2 = new ONBTTagList();
      double[] var3 = var1;
      int var4 = var1.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         double var6 = var3[var5];
         var2.a(new ONBTTagDouble(var6));
      }

      return var2;
   }

   protected ONBTTagList a(float ... var1) {
      ONBTTagList var2 = new ONBTTagList();
      float[] var3 = var1;
      int var4 = var1.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         float var6 = var3[var5];
         var2.a(new ONBTTagFloat(var6));
      }

      return var2;
   }

   public OEntityItem b(int var1, int var2) {
      return this.a(var1, var2, 0.0F);
   }

   public OEntityItem a(int var1, int var2, float var3) {
      return this.a(new OItemStack(var1, var2, 0), var3);
   }

   public OEntityItem a(OItemStack var1, float var2) {
      OEntityItem var3 = new OEntityItem(this.aL, this.aP, this.aQ + (double)var2, this.aR, var1);
      var3.c = 10;
      this.aL.b(var3);
      return var3;
   }

   public boolean S() {
      return !this.bh;
   }

   public boolean J() {
      for(int var1 = 0; var1 < 8; ++var1) {
         float var2 = ((float)((var1 >> 0) % 2) - 0.5F) * this.bj * 0.9F;
         float var3 = ((float)((var1 >> 1) % 2) - 0.5F) * 0.1F;
         float var4 = ((float)((var1 >> 2) % 2) - 0.5F) * this.bj * 0.9F;
         int var5 = OMathHelper.b(this.aP + (double)var2);
         int var6 = OMathHelper.b(this.aQ + (double)this.s() + (double)var3);
         int var7 = OMathHelper.b(this.aR + (double)var4);
         if(this.aL.d(var5, var6, var7)) {
            return true;
         }
      }

      return false;
   }

   public boolean a(OEntityPlayer var1) {
      return false;
   }

   public OAxisAlignedBB a_(OEntity var1) {
      return null;
   }

   public void D() {
      if(this.aK.bh) {
         this.aK = null;
      } else {
         this.aS = 0.0D;
         this.aT = 0.0D;
         this.aU = 0.0D;
         this.o_();
         if(this.aK != null) {
            this.aK.f();
            this.e += (double)(this.aK.aV - this.aK.aX);

            for(this.d += (double)(this.aK.aW - this.aK.aY); this.e >= 180.0D; this.e -= 360.0D) {
               ;
            }

            while(this.e < -180.0D) {
               this.e += 360.0D;
            }

            while(this.d >= 180.0D) {
               this.d -= 360.0D;
            }

            while(this.d < -180.0D) {
               this.d += 360.0D;
            }

            double var1 = this.e * 0.5D;
            double var3 = this.d * 0.5D;
            float var5 = 10.0F;
            if(var1 > (double)var5) {
               var1 = (double)var5;
            }

            if(var1 < (double)(-var5)) {
               var1 = (double)(-var5);
            }

            if(var3 > (double)var5) {
               var3 = (double)var5;
            }

            if(var3 < (double)(-var5)) {
               var3 = (double)(-var5);
            }

            this.e -= var1;
            this.d -= var3;
            this.aV = (float)((double)this.aV + var1);
            this.aW = (float)((double)this.aW + var3);
         }
      }
   }

   public void f() {
      this.aJ.a(this.aP, this.aQ + this.m() + this.aJ.H(), this.aR);
   }

   public double H() {
      return (double)this.bi;
   }

   public double m() {
      return (double)this.bk * 0.75D;
   }

   public void b(OEntity var1) {
      this.d = 0.0D;
      this.e = 0.0D;
      if(var1 == null) {
         if(this.aK != null) {
            this.c(this.aK.aP, this.aK.aZ.b + (double)this.aK.bk, this.aK.aR, this.aV, this.aW);
            this.aK.aJ = null;
         }

         this.aK = null;
      } else if(this.aK == var1) {
         this.aK.aJ = null;
         this.aK = null;
         this.c(var1.aP, var1.aZ.b + (double)var1.bk, var1.aR, this.aV, this.aW);
      } else {
         if(this.aK != null) {
            this.aK.aJ = null;
         }

         if(var1.aJ != null) {
            var1.aJ.aK = null;
         }

         this.aK = var1;
         var1.aJ = this;
      }
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
      return this.d(1);
   }

   public void e(boolean var1) {
      this.a(1, var1);
   }

   protected boolean d(int var1) {
      return (this.bE.a(0) & 1 << var1) != 0;
   }

   protected void a(int var1, boolean var2) {
      byte var3 = this.bE.a(0);
      if(var2) {
         this.bE.b(0, Byte.valueOf((byte)(var3 | 1 << var1)));
      } else {
         this.bE.b(0, Byte.valueOf((byte)(var3 & ~(1 << var1))));
      }

   }

   public void a(OEntityLightningBolt var1) {
      // CanaryMod Damage Hook: Lightning
      if ((Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.LIGHTNING, null, entity, 5))
         return;
      this.a(5);
      ++this.by;
      if(this.by == 0) {
         this.by = 300;
      }

   }

   public void a(OEntityLiving var1) {
   }

   protected boolean g(double var1, double var3, double var5) {
      int var7 = OMathHelper.b(var1);
      int var8 = OMathHelper.b(var3);
      int var9 = OMathHelper.b(var5);
      double var10 = var1 - (double)var7;
      double var12 = var3 - (double)var8;
      double var14 = var5 - (double)var9;
      if(this.aL.d(var7, var8, var9)) {
         boolean var16 = !this.aL.d(var7 - 1, var8, var9);
         boolean var17 = !this.aL.d(var7 + 1, var8, var9);
         boolean var18 = !this.aL.d(var7, var8 - 1, var9);
         boolean var19 = !this.aL.d(var7, var8 + 1, var9);
         boolean var20 = !this.aL.d(var7, var8, var9 - 1);
         boolean var21 = !this.aL.d(var7, var8, var9 + 1);
         byte var22 = -1;
         double var23 = 9999.0D;
         if(var16 && var10 < var23) {
            var23 = var10;
            var22 = 0;
         }

         if(var17 && 1.0D - var10 < var23) {
            var23 = 1.0D - var10;
            var22 = 1;
         }

         if(var18 && var12 < var23) {
            var23 = var12;
            var22 = 2;
         }

         if(var19 && 1.0D - var12 < var23) {
            var23 = 1.0D - var12;
            var22 = 3;
         }

         if(var20 && var14 < var23) {
            var23 = var14;
            var22 = 4;
         }

         if(var21 && 1.0D - var14 < var23) {
            var23 = 1.0D - var14;
            var22 = 5;
         }

         float var25 = this.bv.nextFloat() * 0.2F + 0.1F;
         if(var22 == 0) {
            this.aS = (double)(-var25);
         }

         if(var22 == 1) {
            this.aS = (double)var25;
         }

         if(var22 == 2) {
            this.aT = (double)(-var25);
         }

         if(var22 == 3) {
            this.aT = (double)var25;
         }

         if(var22 == 4) {
            this.aU = (double)(-var25);
         }

         if(var22 == 5) {
            this.aU = (double)var25;
         }
      }

      return false;
   }

}
