import java.util.List;
import java.util.Random;

public abstract class OEntity {

   private static int a = 0;
   public int aW;
   public double aX;
   public boolean aY;
   public OEntity aZ;
   public OEntity ba;
   public OWorld bb;
   public double bc;
   public double bd;
   public double be;
   public double bf;
   public double bg;
   public double bh;
   public double bi;
   public double bj;
   public double bk;
   public float bl;
   public float bm;
   public float bn;
   public float bo;
   public final OAxisAlignedBB bp;
   public boolean bq;
   public boolean br;
   public boolean bs;
   public boolean bt;
   public boolean bu;
   protected boolean bv;
   public boolean bw;
   public boolean bx;
   public float by;
   public float bz;
   public float bA;
   public float bB;
   public float bC;
   protected float bD;
   private int b;
   public double bE;
   public double bF;
   public double bG;
   public float bH;
   public float bI;
   public boolean bJ;
   public float bK;
   protected Random bL;
   public int bM;
   public int bN;
   public int bO;
   protected int bP;
   protected boolean bQ;
   public int bR;
   public int bS;
   private boolean c;
   protected boolean bT;
   protected ODataWatcher bU;
   private double d;
   private double e;
   public boolean bV;
   public int bW;
   public int bX;
   public int bY;
   public boolean bZ;
   public boolean ca;
   // CanaryMod Start
   BaseEntity entity = new BaseEntity(this);
   public static PluginLoader manager = etc.getLoader();
   // CanaryMod end


   public OEntity(OWorld var1) {
      super();
      this.aW = a++;
      this.aX = 1.0D;
      this.aY = false;
      this.bp = OAxisAlignedBB.a(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
      this.bq = false;
      this.bt = false;
      this.bu = false;
      this.bw = true;
      this.bx = false;
      this.by = 0.0F;
      this.bz = 0.6F;
      this.bA = 1.8F;
      this.bB = 0.0F;
      this.bC = 0.0F;
      this.bD = 0.0F;
      this.b = 1;
      this.bH = 0.0F;
      this.bI = 0.0F;
      this.bJ = false;
      this.bK = 0.0F;
      this.bL = new Random();
      this.bM = 0;
      this.bN = 1;
      this.bO = 0;
      this.bP = 300;
      this.bQ = false;
      this.bR = 0;
      this.bS = 300;
      this.c = true;
      this.bT = false;
      this.bU = new ODataWatcher();
      this.bV = false;
      this.bb = var1;
      this.c(0.0D, 0.0D, 0.0D);
      this.bU.a(0, Byte.valueOf((byte)0));
      this.b();
   }

   protected abstract void b();

   public ODataWatcher al() {
      return this.bU;
   }

   public boolean equals(Object var1) {
      return var1 instanceof OEntity?((OEntity)var1).aW == this.aW:false;
   }

   public int hashCode() {
      return this.aW;
   }

   public void N() {
      this.bx = true;
   }

   protected void b(float var1, float var2) {
      this.bz = var1;
      this.bA = var2;
   }

   protected void c(float var1, float var2) {
      this.bl = var1 % 360.0F;
      this.bm = var2 % 360.0F;
   }

   public void c(double var1, double var3, double var5) {
      this.bf = var1;
      this.bg = var3;
      this.bh = var5;
      float var7 = this.bz / 2.0F;
      float var8 = this.bA;
      this.bp.c(var1 - (double)var7, var3 - (double)this.by + (double)this.bH, var5 - (double)var7, var1 + (double)var7, var3 - (double)this.by + (double)this.bH + (double)var8, var5 + (double)var7);
   }

   public void s_() {
      this.aa();
   }

   public void aa() {
      if(this.ba != null && this.ba.bx) {
         this.ba = null;
      }

      ++this.bM;
      this.bB = this.bC;
      this.bc = this.bf;
      this.bd = this.bg;
      this.be = this.bh;
      this.bo = this.bm;
      this.bn = this.bl;
      int var3;
      if(this.at()) {
         int var1 = OMathHelper.b(this.bf);
         int var2 = OMathHelper.b(this.bg - 0.20000000298023224D - (double)this.by);
         var3 = OMathHelper.b(this.bh);
         int var4 = this.bb.a(var1, var2, var3);
         if(var4 > 0) {
            this.bb.a("tilecrack_" + var4, this.bf + ((double)this.bL.nextFloat() - 0.5D) * (double)this.bz, this.bp.b + 0.1D, this.bh + ((double)this.bL.nextFloat() - 0.5D) * (double)this.bz, -this.bi * 4.0D, 1.5D, -this.bk * 4.0D);
         }
      }

      if(this.f_()) {
         if(!this.bQ && !this.c) {
            float var6 = OMathHelper.a(this.bi * this.bi * 0.20000000298023224D + this.bj * this.bj + this.bk * this.bk * 0.20000000298023224D) * 0.2F;
            if(var6 > 1.0F) {
               var6 = 1.0F;
            }

            this.bb.a(this, "random.splash", var6, 1.0F + (this.bL.nextFloat() - this.bL.nextFloat()) * 0.4F);
            float var7 = (float)OMathHelper.b(this.bp.b);

            float var5;
            float var8;
            for(var3 = 0; (float)var3 < 1.0F + this.bz * 20.0F; ++var3) {
               var8 = (this.bL.nextFloat() * 2.0F - 1.0F) * this.bz;
               var5 = (this.bL.nextFloat() * 2.0F - 1.0F) * this.bz;
               this.bb.a("bubble", this.bf + (double)var8, (double)(var7 + 1.0F), this.bh + (double)var5, this.bi, this.bj - (double)(this.bL.nextFloat() * 0.2F), this.bk);
            }

            for(var3 = 0; (float)var3 < 1.0F + this.bz * 20.0F; ++var3) {
               var8 = (this.bL.nextFloat() * 2.0F - 1.0F) * this.bz;
               var5 = (this.bL.nextFloat() * 2.0F - 1.0F) * this.bz;
               this.bb.a("splash", this.bf + (double)var8, (double)(var7 + 1.0F), this.bh + (double)var5, this.bi, this.bj, this.bk);
            }
         }

         this.bD = 0.0F;
         this.bQ = true;
         this.bO = 0;
      } else {
         this.bQ = false;
      }

      if(this.bb.I) {
         this.bO = 0;
      } else if(this.bO > 0) {
         if(this.bT) {
            this.bO -= 4;
            if(this.bO < 0) {
               this.bO = 0;
            }
         } else {
            if(this.bO % 20 == 0) {
               this.a(ODamageSource.b, 1);
            }

            --this.bO;
         }
      }

      if(this.ap()) {
         this.am();
      }

      if(this.bg < -64.0D) {
         this.ah();
      }

      if(!this.bb.I) {
         this.a(0, this.bO > 0);
         this.a(2, this.ba != null);
      }

      this.c = false;
   }

   protected void am() {
      if(!this.bT) {
         this.a(ODamageSource.c, 4);
         this.bO = 600;
      }

   }

   protected void ah() {
      this.N();
   }

   public boolean d(double var1, double var3, double var5) {
      OAxisAlignedBB var7 = this.bp.c(var1, var3, var5);
      List var8 = this.bb.a(this, var7);
      return var8.size() > 0?false:!this.bb.c(var7);
   }

   public void a_(double var1, double var3, double var5) {
      if(this.bJ) {
         this.bp.d(var1, var3, var5);
         this.bf = (this.bp.a + this.bp.d) / 2.0D;
         this.bg = this.bp.b + (double)this.by - (double)this.bH;
         this.bh = (this.bp.c + this.bp.f) / 2.0D;
      } else {
         this.bH *= 0.4F;
         double var7 = this.bf;
         double var9 = this.bh;
         if(this.bv) {
            this.bv = false;
            var1 *= 0.25D;
            var3 *= 0.05000000074505806D;
            var5 *= 0.25D;
            this.bi = 0.0D;
            this.bj = 0.0D;
            this.bk = 0.0D;
         }

         double var11 = var1;
         double var13 = var3;
         double var15 = var5;
         OAxisAlignedBB var17 = this.bp.b();
         boolean var18 = this.bq && this.as();
         if(var18) {
            double var19;
            for(var19 = 0.05D; var1 != 0.0D && this.bb.a(this, this.bp.c(var1, -1.0D, 0.0D)).size() == 0; var11 = var1) {
               if(var1 < var19 && var1 >= -var19) {
                  var1 = 0.0D;
               } else if(var1 > 0.0D) {
                  var1 -= var19;
               } else {
                  var1 += var19;
               }
            }

            for(; var5 != 0.0D && this.bb.a(this, this.bp.c(0.0D, -1.0D, var5)).size() == 0; var15 = var5) {
               if(var5 < var19 && var5 >= -var19) {
                  var5 = 0.0D;
               } else if(var5 > 0.0D) {
                  var5 -= var19;
               } else {
                  var5 += var19;
               }
            }
         }

         List var21 = this.bb.a(this, this.bp.a(var1, var3, var5));

         for(int var22 = 0; var22 < var21.size(); ++var22) {
            var3 = ((OAxisAlignedBB)var21.get(var22)).b(this.bp, var3);
         }

         this.bp.d(0.0D, var3, 0.0D);
         if(!this.bw && var13 != var3) {
            var5 = 0.0D;
            var3 = 0.0D;
            var1 = 0.0D;
         }

         boolean var42 = this.bq || var13 != var3 && var13 < 0.0D;

         int var23;
         for(var23 = 0; var23 < var21.size(); ++var23) {
            var1 = ((OAxisAlignedBB)var21.get(var23)).a(this.bp, var1);
         }

         this.bp.d(var1, 0.0D, 0.0D);
         if(!this.bw && var11 != var1) {
            var5 = 0.0D;
            var3 = 0.0D;
            var1 = 0.0D;
         }

         for(var23 = 0; var23 < var21.size(); ++var23) {
            var5 = ((OAxisAlignedBB)var21.get(var23)).c(this.bp, var5);
         }

         this.bp.d(0.0D, 0.0D, var5);
         if(!this.bw && var15 != var5) {
            var5 = 0.0D;
            var3 = 0.0D;
            var1 = 0.0D;
         }

         double var24;
         double var26;
         int var31;
         if(this.bI > 0.0F && var42 && (var18 || this.bH < 0.05F) && (var11 != var1 || var15 != var5)) {
            var24 = var1;
            var26 = var3;
            double var28 = var5;
            var1 = var11;
            var3 = (double)this.bI;
            var5 = var15;
            OAxisAlignedBB var30 = this.bp.b();
            this.bp.b(var17);
            var21 = this.bb.a(this, this.bp.a(var11, var3, var15));

            for(var31 = 0; var31 < var21.size(); ++var31) {
               var3 = ((OAxisAlignedBB)var21.get(var31)).b(this.bp, var3);
            }

            this.bp.d(0.0D, var3, 0.0D);
            if(!this.bw && var13 != var3) {
               var5 = 0.0D;
               var3 = 0.0D;
               var1 = 0.0D;
            }

            for(var31 = 0; var31 < var21.size(); ++var31) {
               var1 = ((OAxisAlignedBB)var21.get(var31)).a(this.bp, var1);
            }

            this.bp.d(var1, 0.0D, 0.0D);
            if(!this.bw && var11 != var1) {
               var5 = 0.0D;
               var3 = 0.0D;
               var1 = 0.0D;
            }

            for(var31 = 0; var31 < var21.size(); ++var31) {
               var5 = ((OAxisAlignedBB)var21.get(var31)).c(this.bp, var5);
            }

            this.bp.d(0.0D, 0.0D, var5);
            if(!this.bw && var15 != var5) {
               var5 = 0.0D;
               var3 = 0.0D;
               var1 = 0.0D;
            }

            if(!this.bw && var13 != var3) {
               var5 = 0.0D;
               var3 = 0.0D;
               var1 = 0.0D;
            } else {
               var3 = (double)(-this.bI);

               for(var31 = 0; var31 < var21.size(); ++var31) {
                  var3 = ((OAxisAlignedBB)var21.get(var31)).b(this.bp, var3);
               }

               this.bp.d(0.0D, var3, 0.0D);
            }

            if(var24 * var24 + var28 * var28 >= var1 * var1 + var5 * var5) {
               var1 = var24;
               var3 = var26;
               var5 = var28;
               this.bp.b(var30);
            } else {
               double var32 = this.bp.b - (double)((int)this.bp.b);
               if(var32 > 0.0D) {
                  this.bH = (float)((double)this.bH + var32 + 0.01D);
               }
            }
         }

         this.bf = (this.bp.a + this.bp.d) / 2.0D;
         this.bg = this.bp.b + (double)this.by - (double)this.bH;
         this.bh = (this.bp.c + this.bp.f) / 2.0D;
         this.br = var11 != var1 || var15 != var5;
         this.bs = var13 != var3;
         this.bq = var13 != var3 && var13 < 0.0D;
         this.bt = this.br || this.bs;
         this.a(var3, this.bq);
         if(var11 != var1) {
            this.bi = 0.0D;
         }

         if(var13 != var3) {
            this.bj = 0.0D;
         }

         if(var15 != var5) {
            this.bk = 0.0D;
         }

         var24 = this.bf - var7;
         var26 = this.bh - var9;
         int var34;
         int var35;
         int var43;
         if(this.e_() && !var18 && this.ba == null) {
            this.bC = (float)((double)this.bC + (double)OMathHelper.a(var24 * var24 + var26 * var26) * 0.6D);
            var34 = OMathHelper.b(this.bf);
            var35 = OMathHelper.b(this.bg - 0.20000000298023224D - (double)this.by);
            var43 = OMathHelper.b(this.bh);
            var31 = this.bb.a(var34, var35, var43);
            if(this.bb.a(var34, var35 - 1, var43) == OBlock.ba.bA) {
               var31 = this.bb.a(var34, var35 - 1, var43);
            }

            if(this.bC > (float)this.b && var31 > 0) {
               this.b = (int)this.bC + 1;
               OStepSound var36 = OBlock.m[var31].bL;
               if(this.bb.a(var34, var35 + 1, var43) == OBlock.aT.bA) {
                  var36 = OBlock.aT.bL;
                  this.bb.a(this, var36.c(), var36.a() * 0.15F, var36.b());
               } else if(!OBlock.m[var31].bN.d()) {
                  this.bb.a(this, var36.c(), var36.a() * 0.15F, var36.b());
               }

               OBlock.m[var31].b(this.bb, var34, var35, var43, this);
            }
         }

         var34 = OMathHelper.b(this.bp.a + 0.001D);
         var35 = OMathHelper.b(this.bp.b + 0.001D);
         var43 = OMathHelper.b(this.bp.c + 0.001D);
         var31 = OMathHelper.b(this.bp.d - 0.001D);
         int var44 = OMathHelper.b(this.bp.e - 0.001D);
         int var37 = OMathHelper.b(this.bp.f - 0.001D);
         if(this.bb.a(var34, var35, var43, var31, var44, var37)) {
            for(int var38 = var34; var38 <= var31; ++var38) {
               for(int var39 = var35; var39 <= var44; ++var39) {
                  for(int var40 = var43; var40 <= var37; ++var40) {
                     int var41 = this.bb.a(var38, var39, var40);
                     if(var41 > 0) {
                        OBlock.m[var41].a(this.bb, var38, var39, var40, this);
                     }
                  }
               }
            }
         }

         boolean var45 = this.an();
         if(this.bb.d(this.bp.e(0.001D, 0.001D, 0.001D))) {
            this.a(1);
            if(!var45) {
               ++this.bO;
               if(this.bO == 0) {
                  this.bO = 300;
               }
            }
         } else if(this.bO <= 0) {
            this.bO = -this.bN;
         }

         if(var45 && this.bO > 0) {
            this.bb.a(this, "random.fizz", 0.7F, 1.6F + (this.bL.nextFloat() - this.bL.nextFloat()) * 0.4F);
            this.bO = -this.bN;
         }

      }
   }

   protected boolean e_() {
      return true;
   }

   protected void a(double var1, boolean var3) {
      if(var3) {
         if(this.bD > 0.0F) {
            this.a(this.bD);
            this.bD = 0.0F;
         }
      } else if(var1 < 0.0D) {
         this.bD = (float)((double)this.bD - var1);
      }

   }

   public OAxisAlignedBB f() {
      return null;
   }

   protected void a(int var1) {
      if(!this.bT) {
         this.a(ODamageSource.a, var1);
      }

   }

   protected void a(float var1) {
      if(this.aZ != null) {
         this.aZ.a(var1);
      }

   }

   public boolean an() {
      return this.bQ || this.bb.s(OMathHelper.b(this.bf), OMathHelper.b(this.bg), OMathHelper.b(this.bh));
   }

   public boolean ao() {
      return this.bQ;
   }

   public boolean f_() {
      return this.bb.a(this.bp.b(0.0D, -0.4000000059604645D, 0.0D).e(0.001D, 0.001D, 0.001D), OMaterial.g, this);
   }

   public boolean a(OMaterial var1) {
      double var2 = this.bg + (double)this.t();
      int var4 = OMathHelper.b(this.bf);
      int var5 = OMathHelper.d((float)OMathHelper.b(var2));
      int var6 = OMathHelper.b(this.bh);
      int var7 = this.bb.a(var4, var5, var6);
      if(var7 != 0 && OBlock.m[var7].bN == var1) {
         float var8 = OBlockFluid.c(this.bb.c(var4, var5, var6)) - 0.11111111F;
         float var9 = (float)(var5 + 1) - var8;
         return var2 < (double)var9;
      } else {
         return false;
      }
   }

   public float t() {
      return 0.0F;
   }

   public boolean ap() {
      return this.bb.a(this.bp.b(-0.10000000149011612D, -0.4000000059604645D, -0.10000000149011612D), OMaterial.h);
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
         float var5 = OMathHelper.a(this.bl * 3.1415927F / 180.0F);
         float var6 = OMathHelper.b(this.bl * 3.1415927F / 180.0F);
         this.bi += (double)(var1 * var6 - var2 * var5);
         this.bk += (double)(var2 * var6 + var1 * var5);
      }
   }

   public float a_(float var1) {
      int var2 = OMathHelper.b(this.bf);
      int var3 = OMathHelper.b(this.bh);
      OWorld var10000 = this.bb;
      this.bb.getClass();
      if(var10000.g(var2, 128 / 2, var3)) {
         double var4 = (this.bp.e - this.bp.b) * 0.66D;
         int var6 = OMathHelper.b(this.bg - (double)this.by + var4);
         return this.bb.m(var2, var6, var3);
      } else {
         return 0.0F;
      }
   }

   public void a(OWorld var1) {
      this.bb = var1;
   }

   public void b(double var1, double var3, double var5, float var7, float var8) {
      this.bc = this.bf = var1;
      this.bd = this.bg = var3;
      this.be = this.bh = var5;
      this.bn = this.bl = var7;
      this.bo = this.bm = var8;
      this.bH = 0.0F;
      double var9 = (double)(this.bn - var7);
      if(var9 < -180.0D) {
         this.bn += 360.0F;
      }

      if(var9 >= 180.0D) {
         this.bn -= 360.0F;
      }

      this.c(this.bf, this.bg, this.bh);
      this.c(var7, var8);
   }

   public void c(double var1, double var3, double var5, float var7, float var8) {
      this.bE = this.bc = this.bf = var1;
      this.bF = this.bd = this.bg = var3 + (double)this.by;
      this.bG = this.be = this.bh = var5;
      this.bl = var7;
      this.bm = var8;
      this.c(this.bf, this.bg, this.bh);
   }

   public float g(OEntity var1) {
      float var2 = (float)(this.bf - var1.bf);
      float var3 = (float)(this.bg - var1.bg);
      float var4 = (float)(this.bh - var1.bh);
      return OMathHelper.c(var2 * var2 + var3 * var3 + var4 * var4);
   }

   public double e(double var1, double var3, double var5) {
      double var7 = this.bf - var1;
      double var9 = this.bg - var3;
      double var11 = this.bh - var5;
      return var7 * var7 + var9 * var9 + var11 * var11;
   }

   public double f(double var1, double var3, double var5) {
      double var7 = this.bf - var1;
      double var9 = this.bg - var3;
      double var11 = this.bh - var5;
      return (double)OMathHelper.a(var7 * var7 + var9 * var9 + var11 * var11);
   }

   public double h(OEntity var1) {
      double var2 = this.bf - var1.bf;
      double var4 = this.bg - var1.bg;
      double var6 = this.bh - var1.bh;
      return var2 * var2 + var4 * var4 + var6 * var6;
   }

   public void a_(OEntityPlayer var1) {}

   public void i(OEntity var1) {
      if(var1.aZ != this && var1.ba != this) {
         double var2 = var1.bf - this.bf;
         double var4 = var1.bh - this.bh;
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
            var2 *= (double)(1.0F - this.bK);
            var4 *= (double)(1.0F - this.bK);
            this.b(-var2, 0.0D, -var4);
            var1.b(var2, 0.0D, var4);
         }

      }
   }

   public void b(double var1, double var3, double var5) {
      this.bi += var1;
      this.bj += var3;
      this.bk += var5;
      this.ca = true;
   }

   protected void aq() {
      this.bu = true;
   }

   public boolean a(ODamageSource var1, int var2) {
      this.aq();
      return false;
   }

   public boolean r_() {
      return false;
   }

   public boolean g() {
      return false;
   }

   public void b(OEntity var1, int var2) {}

   public boolean c(ONBTTagCompound var1) {
      String var2 = this.ar();
      if(!this.bx && var2 != null) {
         var1.a("id", var2);
         this.d(var1);
         return true;
      } else {
         return false;
      }
   }

   public void d(ONBTTagCompound var1) {
      var1.a("Pos", (ONBTBase)this.a(new double[]{this.bf, this.bg + (double)this.bH, this.bh}));
      var1.a("Motion", (ONBTBase)this.a(new double[]{this.bi, this.bj, this.bk}));
      var1.a("Rotation", (ONBTBase)this.a(new float[]{this.bl, this.bm}));
      var1.a("FallDistance", this.bD);
      var1.a("Fire", (short)this.bO);
      var1.a("Air", (short)this.bS);
      var1.a("OnGround", this.bq);
      this.b(var1);
   }

   public void e(ONBTTagCompound var1) {
      ONBTTagList var2 = var1.l("Pos");
      ONBTTagList var3 = var1.l("Motion");
      ONBTTagList var4 = var1.l("Rotation");
      this.bi = ((ONBTTagDouble)var3.a(0)).a;
      this.bj = ((ONBTTagDouble)var3.a(1)).a;
      this.bk = ((ONBTTagDouble)var3.a(2)).a;
      if(Math.abs(this.bi) > 10.0D) {
         this.bi = 0.0D;
      }

      if(Math.abs(this.bj) > 10.0D) {
         this.bj = 0.0D;
      }

      if(Math.abs(this.bk) > 10.0D) {
         this.bk = 0.0D;
      }

      this.bc = this.bE = this.bf = ((ONBTTagDouble)var2.a(0)).a;
      this.bd = this.bF = this.bg = ((ONBTTagDouble)var2.a(1)).a;
      this.be = this.bG = this.bh = ((ONBTTagDouble)var2.a(2)).a;
      this.bn = this.bl = ((ONBTTagFloat)var4.a(0)).a;
      this.bo = this.bm = ((ONBTTagFloat)var4.a(1)).a;
      this.bD = var1.g("FallDistance");
      this.bO = var1.d("Fire");
      this.bS = var1.d("Air");
      this.bq = var1.m("OnGround");
      this.c(this.bf, this.bg, this.bh);
      this.c(this.bl, this.bm);
      this.a(var1);
   }

   protected final String ar() {
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
         var2.a((ONBTBase)(new ONBTTagDouble(var6)));
      }

      return var2;
   }

   protected ONBTTagList a(float ... var1) {
      ONBTTagList var2 = new ONBTTagList();
      float[] var3 = var1;
      int var4 = var1.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         float var6 = var3[var5];
         var2.a((ONBTBase)(new ONBTTagFloat(var6)));
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
      OEntityItem var3 = new OEntityItem(this.bb, this.bf, this.bg + (double)var2, this.bh, var1);
      var3.c = 10;
      this.bb.b((OEntity)var3);
      return var3;
   }

   public boolean ac() {
      return !this.bx;
   }

   public boolean O() {
      for(int var1 = 0; var1 < 8; ++var1) {
         float var2 = ((float)((var1 >> 0) % 2) - 0.5F) * this.bz * 0.9F;
         float var3 = ((float)((var1 >> 1) % 2) - 0.5F) * 0.1F;
         float var4 = ((float)((var1 >> 2) % 2) - 0.5F) * this.bz * 0.9F;
         int var5 = OMathHelper.b(this.bf + (double)var2);
         int var6 = OMathHelper.b(this.bg + (double)this.t() + (double)var3);
         int var7 = OMathHelper.b(this.bh + (double)var4);
         if(this.bb.e(var5, var6, var7)) {
            return true;
         }
      }

      return false;
   }

   public boolean b(OEntityPlayer var1) {
      return false;
   }

   public OAxisAlignedBB b(OEntity var1) {
      return null;
   }

   public void I() {
      if(this.ba.bx) {
         this.ba = null;
      } else {
         this.bi = 0.0D;
         this.bj = 0.0D;
         this.bk = 0.0D;
         this.s_();
         if(this.ba != null) {
            this.ba.g_();
            this.e += (double)(this.ba.bl - this.ba.bn);

            for(this.d += (double)(this.ba.bm - this.ba.bo); this.e >= 180.0D; this.e -= 360.0D) {
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
            this.bl = (float)((double)this.bl + var1);
            this.bm = (float)((double)this.bm + var3);
         }
      }
   }

   public void g_() {
      this.aZ.c(this.bf, this.bg + this.n() + this.aZ.M(), this.bh);
   }

   public double M() {
      return (double)this.by;
   }

   public double n() {
      return (double)this.bA * 0.75D;
   }

   public void a(OEntity var1) {
      this.d = 0.0D;
      this.e = 0.0D;
      if(var1 == null) {
         if(this.ba != null) {
            this.c(this.ba.bf, this.ba.bp.b + (double)this.ba.bA, this.ba.bh, this.bl, this.bm);
            this.ba.aZ = null;
         }

         this.ba = null;
      } else if(this.ba == var1) {
         this.ba.aZ = null;
         this.ba = null;
         this.c(var1.bf, var1.bp.b + (double)var1.bA, var1.bh, this.bl, this.bm);
      } else {
         if(this.ba != null) {
            this.ba.aZ = null;
         }

         if(var1.aZ != null) {
            var1.aZ.ba = null;
         }

         this.ba = var1;
         var1.aZ = this;
      }
   }

   public OVec3D ai() {
      return null;
   }

   public void T() {}

   public OItemStack[] l_() {
      return null;
   }

   public boolean as() {
      return this.e(1);
   }

   public void f(boolean var1) {
      this.a(1, var1);
   }

   public boolean at() {
      return this.e(3);
   }

   public void g(boolean var1) {
      this.a(3, var1);
   }

   public void h(boolean var1) {
      this.a(4, var1);
   }

   protected boolean e(int var1) {
      return (this.bU.a(0) & 1 << var1) != 0;
   }

   protected void a(int var1, boolean var2) {
      byte var3 = this.bU.a(0);
      if(var2) {
         this.bU.b(0, Byte.valueOf((byte)(var3 | 1 << var1)));
      } else {
         this.bU.b(0, Byte.valueOf((byte)(var3 & ~(1 << var1))));
      }

   }

   public void a(OEntityLightningBolt var1) {
      // CanaryMod Damage Hook: Lightning
      if ((Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.LIGHTNING, null, entity, 5))
         return;
      this.a(5);
      ++this.bO;
      if(this.bO == 0) {
         this.bO = 300;
      }

   }

   public void a(OEntityLiving var1) {}

   protected boolean g(double var1, double var3, double var5) {
      int var7 = OMathHelper.b(var1);
      int var8 = OMathHelper.b(var3);
      int var9 = OMathHelper.b(var5);
      double var10 = var1 - (double)var7;
      double var12 = var3 - (double)var8;
      double var14 = var5 - (double)var9;
      if(this.bb.e(var7, var8, var9)) {
         boolean var16 = !this.bb.e(var7 - 1, var8, var9);
         boolean var17 = !this.bb.e(var7 + 1, var8, var9);
         boolean var18 = !this.bb.e(var7, var8 - 1, var9);
         boolean var19 = !this.bb.e(var7, var8 + 1, var9);
         boolean var20 = !this.bb.e(var7, var8, var9 - 1);
         boolean var21 = !this.bb.e(var7, var8, var9 + 1);
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

         float var25 = this.bL.nextFloat() * 0.2F + 0.1F;
         if(var22 == 0) {
            this.bi = (double)(-var25);
         }

         if(var22 == 1) {
            this.bi = (double)var25;
         }

         if(var22 == 2) {
            this.bj = (double)(-var25);
         }

         if(var22 == 3) {
            this.bj = (double)var25;
         }

         if(var22 == 4) {
            this.bk = (double)(-var25);
         }

         if(var22 == 5) {
            this.bk = (double)var25;
         }
      }

      return false;
   }

   public void q() {
      this.bv = true;
   }

   public String Y() {
      String var1 = OEntityList.b(this);
      if(var1 == null) {
         var1 = "generic";
      }

      return OStatCollector.a("entity." + var1 + ".name");
   }

}
