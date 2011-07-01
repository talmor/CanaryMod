
import java.util.List;

public abstract class OEntityLiving extends OEntity {

   public int H = 20;
   public float I;
   public float J = (float)(Math.random() + 1.0D) * 0.01F;
   public float K = 0.0F;
   public float L = 0.0F;
   protected float M;
   protected float N;
   protected float O;
   protected float P;
   protected boolean Q = true;
   protected String R = "/mob/char.png";
   protected boolean S = true;
   protected float T = 0.0F;
   protected String U = null;
   protected float V = 1.0F;
   protected int W = 0;
   protected float X = 0.0F;
   public boolean Y = false;
   public float Z;
   public float aa;
   public int ab = 10;
   public int ac;
   private int a;
   public int ad;
   public int ae;
   public float af = 0.0F;
   public int ag = 0;
   public int ah = 0;
   public float ai;
   public float aj;
   protected boolean ak = false;
   public int al = -1;
   public float am = (float)(Math.random() * 0.8999999761581421D + 0.10000000149011612D);
   public float an;
   public float ao;
   public float ap;
   protected int aq;
   protected double ar;
   protected double as;
   protected double at;
   protected double au;
   protected double av;
   float aw = 0.0F;
   protected int ax = 0;
   protected int ay = 0;
   protected float az;
   protected float aA;
   protected float aB;
   protected boolean aC = false;
   protected float aD = 0.0F;
   protected float aE = 0.7F;
   private OEntity b;
   protected int aF = 0;
   // CanaryMod Start
   @SuppressWarnings("FieldNameHidesFieldInSuperclass")
   LivingEntity      entity = new LivingEntity(this);
   // CanaryMod end


   public OEntityLiving(OWorld var1) {
      super(var1);
      this.a(this.aP, this.aQ, this.aR);
      this.I = (float)Math.random() * 12398.0F;
      this.aV = (float)(Math.random() * 3.1415927410125732D * 2.0D);
      this.bs = 0.5F;
   }

   protected void b() {
   }

   public boolean e(OEntity var1) {
      return this.aL.a(OVec3D.b(this.aP, this.aQ + (double)this.s(), this.aR), OVec3D.b(var1.aP, var1.aQ + (double)var1.s(), var1.aR)) == null;
   }

   public boolean n_() {
      return !this.bh;
   }

   public boolean d_() {
      return !this.bh;
   }

   public float s() {
      return this.bk * 0.85F;
   }

   public int e() {
      return 80;
   }

   public void P() {
      String var1 = this.g();
      if(var1 != null) {
         this.aL.a(this, var1, this.k(), (this.bv.nextFloat() - this.bv.nextFloat()) * 0.2F + 1.0F);
      }

   }

   public void Q() {
      this.Z = this.aa;
      super.Q();
      if(this.bv.nextInt(1000) < this.a++) {
         this.a = -this.e();
         this.P();
      }

      if(this.S() && this.J()) {
         // CanaryMod Damage hook: Suffocation
         if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.SUFFOCATION, null, entity, 1))
            this.a((OEntity)null, 1);
      }

      if(this.bD || this.aL.B) {
         this.by = 0;
      }

      int var1;
      if(this.S() && this.a(OMaterial.g) && !this.b_()) {
         --this.bC;
         if(this.bC == -20) {
            this.bC = 0;

            for(var1 = 0; var1 < 8; ++var1) {
               float var2 = this.bv.nextFloat() - this.bv.nextFloat();
               float var3 = this.bv.nextFloat() - this.bv.nextFloat();
               float var4 = this.bv.nextFloat() - this.bv.nextFloat();
               this.aL.a("bubble", this.aP + (double)var2, this.aQ + (double)var3, this.aR + (double)var4, this.aS, this.aT, this.aU);
            }

            // CanaryMod Damage hook: Drowning
            if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.WATER, null, entity, 2))
               this.a((OEntity)null, 2);
         }

         this.by = 0;
      } else {
         this.bC = this.bz;
      }

      this.ai = this.aj;
      if(this.ah > 0) {
         --this.ah;
      }

      if(this.ad > 0) {
         --this.ad;
      }

      if(this.bB > 0) {
         --this.bB;
      }

      if(this.ab <= 0) {
         ++this.ag;
         if(this.ag > 20) {
            this.W();
            this.I();

            for(var1 = 0; var1 < 20; ++var1) {
               double var5 = this.bv.nextGaussian() * 0.02D;
               double var7 = this.bv.nextGaussian() * 0.02D;
               double var9 = this.bv.nextGaussian() * 0.02D;
               this.aL.a("explode", this.aP + (double)(this.bv.nextFloat() * this.bj * 2.0F) - (double)this.bj, this.aQ + (double)(this.bv.nextFloat() * this.bk), this.aR + (double)(this.bv.nextFloat() * this.bj * 2.0F) - (double)this.bj, var5, var7, var9);
            }
         }
      }

      this.P = this.O;
      this.L = this.K;
      this.aX = this.aV;
      this.aY = this.aW;
   }

   public void R() {
      for(int var1 = 0; var1 < 20; ++var1) {
         double var2 = this.bv.nextGaussian() * 0.02D;
         double var4 = this.bv.nextGaussian() * 0.02D;
         double var6 = this.bv.nextGaussian() * 0.02D;
         double var8 = 10.0D;
         this.aL.a("explode", this.aP + (double)(this.bv.nextFloat() * this.bj * 2.0F) - (double)this.bj - var2 * var8, this.aQ + (double)(this.bv.nextFloat() * this.bk) - var4 * var8, this.aR + (double)(this.bv.nextFloat() * this.bj * 2.0F) - (double)this.bj - var6 * var8, var2, var4, var6);
      }

   }

   public void D() {
      super.D();
      this.M = this.N;
      this.N = 0.0F;
   }

   public void o_() {
      super.o_();
      this.u();
      double var1 = this.aP - this.aM;
      double var3 = this.aR - this.aO;
      float var5 = OMathHelper.a(var1 * var1 + var3 * var3);
      float var6 = this.K;
      float var7 = 0.0F;
      this.M = this.N;
      float var8 = 0.0F;
      if(var5 > 0.05F) {
         var8 = 1.0F;
         var7 = var5 * 3.0F;
         var6 = (float)Math.atan2(var3, var1) * 180.0F / 3.1415927F - 90.0F;
      }

      if(this.aa > 0.0F) {
         var6 = this.aV;
      }

      if(!this.ba) {
         var8 = 0.0F;
      }

      this.N += (var8 - this.N) * 0.3F;

      float var9;
      for(var9 = var6 - this.K; var9 < -180.0F; var9 += 360.0F) {
         ;
      }

      while(var9 >= 180.0F) {
         var9 -= 360.0F;
      }

      this.K += var9 * 0.3F;

      float var10;
      for(var10 = this.aV - this.K; var10 < -180.0F; var10 += 360.0F) {
         ;
      }

      while(var10 >= 180.0F) {
         var10 -= 360.0F;
      }

      boolean var11 = var10 < -90.0F || var10 >= 90.0F;
      if(var10 < -75.0F) {
         var10 = -75.0F;
      }

      if(var10 >= 75.0F) {
         var10 = 75.0F;
      }

      this.K = this.aV - var10;
      if(var10 * var10 > 2500.0F) {
         this.K += var10 * 0.2F;
      }

      if(var11) {
         var7 *= -1.0F;
      }

      while(this.aV - this.aX < -180.0F) {
         this.aX -= 360.0F;
      }

      while(this.aV - this.aX >= 180.0F) {
         this.aX += 360.0F;
      }

      while(this.K - this.L < -180.0F) {
         this.L -= 360.0F;
      }

      while(this.K - this.L >= 180.0F) {
         this.L += 360.0F;
      }

      while(this.aW - this.aY < -180.0F) {
         this.aY -= 360.0F;
      }

      while(this.aW - this.aY >= 180.0F) {
         this.aY += 360.0F;
      }

      this.O += var7;
   }

   protected void b(float var1, float var2) {
      super.b(var1, var2);
   }

   public void b(int var1) {
      if(this.ab > 0) {
         this.ab += var1;
         if(this.ab > 20) {
            this.ab = 20;
         }

         this.bB = this.H / 2;
      }
   }

   public boolean a(OEntity var1, int var2) {
      if(this.aL.B) {
         return false;
      } else {
         this.ay = 0;
         if(this.ab <= 0) {
            return false;
         } else {
            this.ao = 1.5F;

            // CanaryMod damage entities.
            LivingEntity attacker = (var1 != null && var1 instanceof OEntityLiving) ? new LivingEntity((OEntityLiving) var1) : null;

            // CanaryMod attack by entity, but it might not do damage!
            if (attacker != null && (Boolean) manager.callHook(PluginLoader.Hook.ATTACK, attacker, entity, var2))
               return false;

            boolean var3 = true;
            if((float)this.bB > (float)this.H / 2.0F) {
               if(var2 <= this.ax) {
                  return false;
               }

               // CanaryMod: partial damage
               if (attacker != null && (Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.ENTITY, attacker, entity, var2 - ax))
                  return false;

               this.c(var2 - this.ax);
               this.ax = var2;
               var3 = false;
            } else {
               // CanaryMod: full damage
               if (attacker != null && (Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.ENTITY, attacker, entity, var2))
                  return false;

               this.ax = var2;
               this.ac = this.ab;
               this.bB = this.H;
               this.c(var2);
               this.ad = this.ae = 10;
            }

            this.af = 0.0F;
            if(var3) {
               this.aL.a(this, (byte)2);
               this.ae();
               if(var1 != null) {
                  double var4 = var1.aP - this.aP;

                  double var6;
                  for(var6 = var1.aR - this.aR; var4 * var4 + var6 * var6 < 1.0E-4D; var6 = (Math.random() - Math.random()) * 0.01D) {
                     var4 = (Math.random() - Math.random()) * 0.01D;
                  }

                  this.af = (float)(Math.atan2(var6, var4) * 180.0D / 3.1415927410125732D) - this.aV;
                  this.a(var1, var2, var4, var6);
               } else {
                  this.af = (float)((int)(Math.random() * 2.0D) * 180);
               }
            }

            if(this.ab <= 0) {
               if(var3) {
                  this.aL.a(this, this.i(), this.k(), (this.bv.nextFloat() - this.bv.nextFloat()) * 0.2F + 1.0F);
               }

               this.a(var1);
            } else if(var3) {
               this.aL.a(this, this.h(), this.k(), (this.bv.nextFloat() - this.bv.nextFloat()) * 0.2F + 1.0F);
            }

            return true;
         }
      }
   }

   protected void c(int var1) {
      this.ab -= var1;
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

   public void a(OEntity var1, int var2, double var3, double var5) {
      float var7 = OMathHelper.a(var3 * var3 + var5 * var5);
      float var8 = 0.4F;
      this.aS /= 2.0D;
      this.aT /= 2.0D;
      this.aU /= 2.0D;
      this.aS -= var3 / (double)var7 * (double)var8;
      this.aT += 0.4000000059604645D;
      this.aU -= var5 / (double)var7 * (double)var8;
      if(this.aT > 0.4000000059604645D) {
         this.aT = 0.4000000059604645D;
      }

   }

   public void a(OEntity var1) {
      if(this.W >= 0 && var1 != null) {
         var1.c(this, this.W);
      }

      if(var1 != null) {
         var1.a(this);
      }

      this.ak = true;
      if(!this.aL.B) {
         this.r();
      }

      this.aL.a(this, (byte)3);
   }

   protected void r() {
      int var1 = this.j();
      if(var1 > 0) {
         int var2 = this.bv.nextInt(3);

         for(int var3 = 0; var3 < var2; ++var3) {
            this.b(var1, 1);
         }
      }

   }

   protected int j() {
      return 0;
   }

   protected void a(float var1) {
      super.a(var1);
      int var2 = (int)Math.ceil((double)(var1 - 3.0F));
      if(var2 > 0) {
         // CanaryMod Damage hook: Falling
         if (!(Boolean) manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.FALL, null, entity, var2))
            this.a((OEntity)null, var2);

         int var3 = this.aL.a(OMathHelper.b(this.aP), OMathHelper.b(this.aQ - 0.20000000298023224D - (double)this.bi), OMathHelper.b(this.aR));
         if(var3 > 0) {
            OStepSound var4 = OBlock.m[var3].by;
            this.aL.a(this, var4.c(), var4.a() * 0.5F, var4.b() * 0.75F);
         }
      }

   }

   public void a(float var1, float var2) {
      double var3;
      if(this.ac()) {
         var3 = this.aQ;
         this.a(var1, var2, 0.02F);
         this.c(this.aS, this.aT, this.aU);
         this.aS *= 0.800000011920929D;
         this.aT *= 0.800000011920929D;
         this.aU *= 0.800000011920929D;
         this.aT -= 0.02D;
         if(this.bb && this.b(this.aS, this.aT + 0.6000000238418579D - this.aQ + var3, this.aU)) {
            this.aT = 0.30000001192092896D;
         }
      } else if(this.ad()) {
         var3 = this.aQ;
         this.a(var1, var2, 0.02F);
         this.c(this.aS, this.aT, this.aU);
         this.aS *= 0.5D;
         this.aT *= 0.5D;
         this.aU *= 0.5D;
         this.aT -= 0.02D;
         if(this.bb && this.b(this.aS, this.aT + 0.6000000238418579D - this.aQ + var3, this.aU)) {
            this.aT = 0.30000001192092896D;
         }
      } else {
         float var5 = 0.91F;
         if(this.ba) {
            var5 = 0.54600006F;
            int var6 = this.aL.a(OMathHelper.b(this.aP), OMathHelper.b(this.aZ.b) - 1, OMathHelper.b(this.aR));
            if(var6 > 0) {
               var5 = OBlock.m[var6].bB * 0.91F;
            }
         }

         float var11 = 0.16277136F / (var5 * var5 * var5);
         this.a(var1, var2, this.ba?0.1F * var11:0.02F);
         var5 = 0.91F;
         if(this.ba) {
            var5 = 0.54600006F;
            int var7 = this.aL.a(OMathHelper.b(this.aP), OMathHelper.b(this.aZ.b) - 1, OMathHelper.b(this.aR));
            if(var7 > 0) {
               var5 = OBlock.m[var7].bB * 0.91F;
            }
         }

         if(this.p()) {
            float var12 = 0.15F;
            if(this.aS < (double)(-var12)) {
               this.aS = (double)(-var12);
            }

            if(this.aS > (double)var12) {
               this.aS = (double)var12;
            }

            if(this.aU < (double)(-var12)) {
               this.aU = (double)(-var12);
            }

            if(this.aU > (double)var12) {
               this.aU = (double)var12;
            }

            this.bn = 0.0F;
            if(this.aT < -0.15D) {
               this.aT = -0.15D;
            }

            if(this.ag() && this.aT < 0.0D) {
               this.aT = 0.0D;
            }
         }

         this.c(this.aS, this.aT, this.aU);
         if(this.bb && this.p()) {
            this.aT = 0.2D;
         }

         this.aT -= 0.08D;
         this.aT *= 0.9800000190734863D;
         this.aS *= (double)var5;
         this.aU *= (double)var5;
      }

      this.an = this.ao;
      var3 = this.aP - this.aM;
      double var8 = this.aR - this.aO;
      float var10 = OMathHelper.a(var3 * var3 + var8 * var8) * 4.0F;
      if(var10 > 1.0F) {
         var10 = 1.0F;
      }

      this.ao += (var10 - this.ao) * 0.4F;
      this.ap += this.ao;
   }

   public boolean p() {
      int var1 = OMathHelper.b(this.aP);
      int var2 = OMathHelper.b(this.aZ.b);
      int var3 = OMathHelper.b(this.aR);
      return this.aL.a(var1, var2, var3) == OBlock.aG.bn;
   }

   public void b(ONBTTagCompound var1) {
      var1.a("Health", (short)this.ab);
      var1.a("HurtTime", (short)this.ad);
      var1.a("DeathTime", (short)this.ag);
      var1.a("AttackTime", (short)this.ah);
   }

   public void a(ONBTTagCompound var1) {
      this.ab = var1.d("Health");
      if(!var1.b("Health")) {
         this.ab = 10;
      }

      this.ad = var1.d("HurtTime");
      this.ag = var1.d("DeathTime");
      this.ah = var1.d("AttackTime");
   }

   public boolean S() {
      return !this.bh && this.ab > 0;
   }

   public boolean b_() {
      return false;
   }

   public void u() {
      if(this.aq > 0) {
         double var1 = this.aP + (this.ar - this.aP) / (double)this.aq;
         double var3 = this.aQ + (this.as - this.aQ) / (double)this.aq;
         double var5 = this.aR + (this.at - this.aR) / (double)this.aq;

         double var7;
         for(var7 = this.au - (double)this.aV; var7 < -180.0D; var7 += 360.0D) {
            ;
         }

         while(var7 >= 180.0D) {
            var7 -= 360.0D;
         }

         this.aV = (float)((double)this.aV + var7 / (double)this.aq);
         this.aW = (float)((double)this.aW + (this.av - (double)this.aW) / (double)this.aq);
         --this.aq;
         this.a(var1, var3, var5);
         this.c(this.aV, this.aW);
         List var9 = this.aL.a(this, this.aZ.e(0.03125D, 0.0D, 0.03125D));
         if(var9.size() > 0) {
            double var10 = 0.0D;

            for(int var12 = 0; var12 < var9.size(); ++var12) {
               OAxisAlignedBB var13 = (OAxisAlignedBB)var9.get(var12);
               if(var13.e > var10) {
                  var10 = var13.e;
               }
            }

            var3 += var10 - this.aZ.b;
            this.a(var1, var3, var5);
         }
      }

      if(this.C()) {
         this.aC = false;
         this.az = 0.0F;
         this.aA = 0.0F;
         this.aB = 0.0F;
      } else if(!this.Y) {
         this.c_();
      }

      boolean var14 = this.ac();
      boolean var15 = this.ad();
      if(this.aC) {
         if(var14) {
            this.aT += 0.03999999910593033D;
         } else if(var15) {
            this.aT += 0.03999999910593033D;
         } else if(this.ba) {
            this.N();
         }
      }

      this.az *= 0.98F;
      this.aA *= 0.98F;
      this.aB *= 0.9F;
      this.a(this.az, this.aA);
      List var16 = this.aL.b(this, this.aZ.b(0.20000000298023224D, 0.0D, 0.20000000298023224D));
      if(var16 != null && var16.size() > 0) {
         for(int var17 = 0; var17 < var16.size(); ++var17) {
            OEntity var18 = (OEntity)var16.get(var17);
            if(var18.d_()) {
               var18.h(this);
            }
         }
      }

   }

   protected boolean C() {
      return this.ab <= 0;
   }

   protected void N() {
      this.aT = 0.41999998688697815D;
   }

   protected boolean l_() {
      return true;
   }

   protected void T() {
      OEntityPlayer var1 = this.aL.a(this, -1.0D);
      if(this.l_() && var1 != null) {
         double var2 = var1.aP - this.aP;
         double var4 = var1.aQ - this.aQ;
         double var6 = var1.aR - this.aR;
         double var8 = var2 * var2 + var4 * var4 + var6 * var6;
         if(var8 > 16384.0D) {
            this.I();
         }

         if(this.ay > 600 && this.bv.nextInt(800) == 0) {
            if(var8 < 1024.0D) {
               this.ay = 0;
            } else {
               this.I();
            }
         }
      }

   }

   protected void c_() {
      ++this.ay;
      OEntityPlayer var1 = this.aL.a(this, -1.0D);
      this.T();
      this.az = 0.0F;
      this.aA = 0.0F;
      float var2 = 8.0F;
      if(this.bv.nextFloat() < 0.02F) {
         var1 = this.aL.a(this, (double)var2);
         if(var1 != null) {
            this.b = var1;
            this.aF = 10 + this.bv.nextInt(20);
         } else {
            this.aB = (this.bv.nextFloat() - 0.5F) * 20.0F;
         }
      }

      if(this.b != null) {
         this.a(this.b, 10.0F, (float)this.v());
         if(this.aF-- <= 0 || this.b.bh || this.b.g(this) > (double)(var2 * var2)) {
            this.b = null;
         }
      } else {
         if(this.bv.nextFloat() < 0.05F) {
            this.aB = (this.bv.nextFloat() - 0.5F) * 20.0F;
         }

         this.aV += this.aB;
         this.aW = this.aD;
      }

      boolean var3 = this.ac();
      boolean var4 = this.ad();
      if(var3 || var4) {
         this.aC = this.bv.nextFloat() < 0.8F;
      }

   }

   protected int v() {
      return 40;
   }

   public void a(OEntity var1, float var2, float var3) {
      double var4 = var1.aP - this.aP;
      double var6 = var1.aR - this.aR;
      double var9;
      if(var1 instanceof OEntityLiving) {
         OEntityLiving var8 = (OEntityLiving)var1;
         var9 = this.aQ + (double)this.s() - (var8.aQ + (double)var8.s());
      } else {
         var9 = (var1.aZ.b + var1.aZ.e) / 2.0D - (this.aQ + (double)this.s());
      }

      double var11 = (double)OMathHelper.a(var4 * var4 + var6 * var6);
      float var13 = (float)(Math.atan2(var6, var4) * 180.0D / 3.1415927410125732D) - 90.0F;
      float var14 = (float)(-(Math.atan2(var9, var11) * 180.0D / 3.1415927410125732D));
      this.aW = -this.b(this.aW, var14, var3);
      this.aV = this.b(this.aV, var13, var2);
   }

   public boolean U() {
      return this.b != null;
   }

   public OEntity V() {
      return this.b;
   }

   private float b(float var1, float var2, float var3) {
      float var4;
      for(var4 = var2 - var1; var4 < -180.0F; var4 += 360.0F) {
         ;
      }

      while(var4 >= 180.0F) {
         var4 -= 360.0F;
      }

      if(var4 > var3) {
         var4 = var3;
      }

      if(var4 < -var3) {
         var4 = -var3;
      }

      return var1 + var4;
   }

   public void W() {
   }

   public boolean d() {
      return this.aL.a(this.aZ) && this.aL.a(this, this.aZ).size() == 0 && !this.aL.c(this.aZ);
   }

   protected void X() {
      this.a((OEntity)null, 4);
   }

   public OVec3D Y() {
      return this.b(1.0F);
   }

   public OVec3D b(float var1) {
      float var2;
      float var3;
      float var4;
      float var5;
      if(var1 == 1.0F) {
         var2 = OMathHelper.b(-this.aV * 0.017453292F - 3.1415927F);
         var3 = OMathHelper.a(-this.aV * 0.017453292F - 3.1415927F);
         var4 = -OMathHelper.b(-this.aW * 0.017453292F);
         var5 = OMathHelper.a(-this.aW * 0.017453292F);
         return OVec3D.b((double)(var3 * var4), (double)var5, (double)(var2 * var4));
      } else {
         var2 = this.aY + (this.aW - this.aY) * var1;
         var3 = this.aX + (this.aV - this.aX) * var1;
         var4 = OMathHelper.b(-var3 * 0.017453292F - 3.1415927F);
         var5 = OMathHelper.a(-var3 * 0.017453292F - 3.1415927F);
         float var6 = -OMathHelper.b(-var2 * 0.017453292F);
         float var7 = OMathHelper.a(-var2 * 0.017453292F);
         return OVec3D.b((double)(var5 * var6), (double)var7, (double)(var4 * var6));
      }
   }

   public int l() {
      return 4;
   }

   public boolean K() {
      return false;
   }
}
