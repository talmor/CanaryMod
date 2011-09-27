import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public abstract class OEntityLiving extends OEntity {

   public int R = 20;
   public float S;
   public float T;
   public float U = 0.0F;
   public float V = 0.0F;
   protected float W;
   protected float X;
   protected float Y;
   protected float Z;
   protected boolean aa = true;
   protected String ab = "/mob/char.png";
   protected boolean ac = true;
   protected float ad = 0.0F;
   protected String ae = null;
   protected float af = 1.0F;
   protected int ag = 0;
   protected float ah = 0.0F;
   public boolean ai = false;
   public float aj = 0.1F;
   public float ak = 0.02F;
   public float al;
   public float am;
   public int an = 10;
   public int ao;
   private int a;
   public int ap;
   public int aq;
   public float ar = 0.0F;
   public int as = 0;
   public int at = 0;
   public float au;
   public float av;
   protected boolean aw = false;
   protected int ax;
   public int ay = -1;
   public float az = (float)(Math.random() * 0.8999999761581421D + 0.10000000149011612D);
   public float aA;
   public float aB;
   public float aC;
   private OEntityPlayer b = null;
   private int c = 0;
   public int aD = 0;
   public int aE = 0;
   protected HashMap aF = new HashMap();
   protected int aG;
   protected double aH;
   protected double aI;
   protected double aJ;
   protected double aK;
   protected double aL;
   float aM = 0.0F;
   protected int aN = 0;
   protected int aO = 0;
   protected float aP;
   protected float aQ;
   protected float aR;
   protected boolean aS = false;
   protected float aT = 0.0F;
   protected float aU = 0.7F;
   private OEntity d;
   protected int aV = 0;
   // CanaryMod Start
   LivingEntity entity = new LivingEntity(this);
   // CanaryMod end

   public OEntityLiving(OWorld var1) {
      super(var1);
      this.aY = true;
      this.T = (float)(Math.random() + 1.0D) * 0.01F;
      this.c(this.bf, this.bg, this.bh);
      this.S = (float)Math.random() * 12398.0F;
      this.bl = (float)(Math.random() * 3.1415927410125732D * 2.0D);
      this.bI = 0.5F;
   }

   protected void b() {}

   public boolean f(OEntity var1) {
      return this.bb.a(OVec3D.b(this.bf, this.bg + (double)this.t(), this.bh), OVec3D.b(var1.bf, var1.bg + (double)var1.t(), var1.bh)) == null;
   }

   public boolean r_() {
      return !this.bx;
   }

   public boolean g() {
      return !this.bx;
   }

   public float t() {
      return this.bA * 0.85F;
   }

   public int e() {
      return 80;
   }

   public void Z() {
      String var1 = this.h();
      if(var1 != null) {
         this.bb.a(this, var1, this.l(), (this.bL.nextFloat() - this.bL.nextFloat()) * 0.2F + 1.0F);
      }

   }

   public void aa() {
      this.al = this.am;
      super.aa();
      if(this.bL.nextInt(1000) < this.a++) {
         this.a = -this.e();
         this.Z();
      }

      if(this.ac() && this.O()) {
         // CanaryMod Damage hook: Suffocation
         if(!(Boolean)manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.SUFFOCATION, null, entity, 1))
            this.a(ODamageSource.d, 1);
      }

      if(this.bT || this.bb.I) {
         this.bO = 0;
      }

      int var1;
      if(this.ac() && this.a(OMaterial.g) && !this.b_() && !this.aF.containsKey(Integer.valueOf(OPotion.o.H))) {
         --this.bS;
         if(this.bS == -20) {
            this.bS = 0;

            for(var1 = 0; var1 < 8; ++var1) {
               float var2 = this.bL.nextFloat() - this.bL.nextFloat();
               float var3 = this.bL.nextFloat() - this.bL.nextFloat();
               float var4 = this.bL.nextFloat() - this.bL.nextFloat();
               this.bb.a("bubble", this.bf + (double)var2, this.bg + (double)var3, this.bh + (double)var4, this.bi, this.bj, this.bk);
            }
            
            // CanaryMod Damage hook: Drowning
            if(!(Boolean)manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.WATER, null, entity, 2))
               this.a(ODamageSource.e, 2);
         }

         this.bO = 0;
      } else {
         this.bS = this.bP;
      }

      this.au = this.av;
      if(this.at > 0) {
         --this.at;
      }

      if(this.ap > 0) {
         --this.ap;
      }

      if(this.bR > 0) {
         --this.bR;
      }

      if(this.an <= 0) {
         ++this.as;
         if(this.as > 20) {
            if(this.c > 0 || this.X()) {
               var1 = this.a(this.b);

               while(var1 > 0) {
                  int var11 = OEntityXPOrb.b(var1);
                  var1 -= var11;
                  this.bb.b((OEntity)(new OEntityXPOrb(this.bb, this.bf, this.bg, this.bh, var11)));
               }
            }

            this.ag();
            this.N();

            for(var1 = 0; var1 < 20; ++var1) {
               double var5 = this.bL.nextGaussian() * 0.02D;
               double var7 = this.bL.nextGaussian() * 0.02D;
               double var9 = this.bL.nextGaussian() * 0.02D;
               this.bb.a("explode", this.bf + (double)(this.bL.nextFloat() * this.bz * 2.0F) - (double)this.bz, this.bg + (double)(this.bL.nextFloat() * this.bA), this.bh + (double)(this.bL.nextFloat() * this.bz * 2.0F) - (double)this.bz, var5, var7, var9);
            }
         }
      }

      if(this.c > 0) {
         --this.c;
      } else {
         this.b = null;
      }

      this.aj();
      this.Z = this.Y;
      this.V = this.U;
      this.bn = this.bl;
      this.bo = this.bm;
   }

   protected int a(OEntityPlayer var1) {
      return this.ax;
   }

   protected boolean X() {
      return false;
   }

   public void ab() {
      for(int var1 = 0; var1 < 20; ++var1) {
         double var2 = this.bL.nextGaussian() * 0.02D;
         double var4 = this.bL.nextGaussian() * 0.02D;
         double var6 = this.bL.nextGaussian() * 0.02D;
         double var8 = 10.0D;
         this.bb.a("explode", this.bf + (double)(this.bL.nextFloat() * this.bz * 2.0F) - (double)this.bz - var2 * var8, this.bg + (double)(this.bL.nextFloat() * this.bA) - var4 * var8, this.bh + (double)(this.bL.nextFloat() * this.bz * 2.0F) - (double)this.bz - var6 * var8, var2, var4, var6);
      }

   }

   public void I() {
      super.I();
      this.W = this.X;
      this.X = 0.0F;
   }

   public void s_() {
      super.s_();
      if(this.aD > 0) {
         if(this.aE <= 0) {
            this.aE = 60;
         }

         --this.aE;
         if(this.aE <= 0) {
            --this.aD;
         }
      }

      this.s();
      double var1 = this.bf - this.bc;
      double var3 = this.bh - this.be;
      float var5 = OMathHelper.a(var1 * var1 + var3 * var3);
      float var6 = this.U;
      float var7 = 0.0F;
      this.W = this.X;
      float var8 = 0.0F;
      if(var5 > 0.05F) {
         var8 = 1.0F;
         var7 = var5 * 3.0F;
         var6 = (float)Math.atan2(var3, var1) * 180.0F / 3.1415927F - 90.0F;
      }

      if(this.am > 0.0F) {
         var6 = this.bl;
      }

      if(!this.bq) {
         var8 = 0.0F;
      }

      this.X += (var8 - this.X) * 0.3F;

      float var9;
      for(var9 = var6 - this.U; var9 < -180.0F; var9 += 360.0F) {
         ;
      }

      while(var9 >= 180.0F) {
         var9 -= 360.0F;
      }

      this.U += var9 * 0.3F;

      float var10;
      for(var10 = this.bl - this.U; var10 < -180.0F; var10 += 360.0F) {
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

      this.U = this.bl - var10;
      if(var10 * var10 > 2500.0F) {
         this.U += var10 * 0.2F;
      }

      if(var11) {
         var7 *= -1.0F;
      }

      while(this.bl - this.bn < -180.0F) {
         this.bn -= 360.0F;
      }

      while(this.bl - this.bn >= 180.0F) {
         this.bn += 360.0F;
      }

      while(this.U - this.V < -180.0F) {
         this.V -= 360.0F;
      }

      while(this.U - this.V >= 180.0F) {
         this.V += 360.0F;
      }

      while(this.bm - this.bo < -180.0F) {
         this.bo -= 360.0F;
      }

      while(this.bm - this.bo >= 180.0F) {
         this.bo += 360.0F;
      }

      this.Y += var7;
   }

   protected void b(float var1, float var2) {
      super.b(var1, var2);
   }

   public void c(int var1) {
      if(this.an > 0) {
         this.an += var1;
         if(this.an > 20) {
            this.an = 20;
         }

         this.bR = this.R / 2;
      }
   }

   public boolean a(ODamageSource var1, int var2) {
      if(this.bb.I) {
         return false;
      } else {
         this.aO = 0;
         if(this.an <= 0) {
            return false;
         } else {
            this.aB = 1.5F;
            
            // CanaryMod damage entities.
            LivingEntity attacker = null;
            if (var1 != null && var1 instanceof OEntityDamageSource && ((OEntityDamageSource)var1).a() instanceof OEntityLiving) {
               OEntity o = ((OEntityDamageSource)var1).a();
               attacker = new LivingEntity((OEntityLiving) o);
            }

            // CanaryMod attack by entity, but it might not do damage!
            if(attacker != null && (Boolean)manager.callHook(PluginLoader.Hook.ATTACK, attacker, entity, var2))
               return false;
            
            
            boolean var3 = true;
            if((float)this.bR > (float)this.R / 2.0F) {
               if(var2 <= this.aN) {
                  return false;
               }
               
               // CanaryMod: partial damage
               if(attacker != null && (Boolean)manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.ENTITY, attacker, entity, var2 - aN))
                  return false;
               
               
               this.b(var1, var2 - this.aN);
               this.aN = var2;
               var3 = false;
            } else {
               // CanaryMod: full damage
               if(attacker != null && (Boolean)manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.ENTITY, attacker, entity, var2))
                  return false;
               
               this.aN = var2;
               this.ao = this.an;
               this.bR = this.R;
               this.b(var1, var2);
               this.ap = this.aq = 10;
            }

            this.ar = 0.0F;
            OEntity var4 = var1.a();
            if(var4 != null) {
               if(var4 instanceof OEntityPlayer) {
                  this.c = 60;
                  this.b = (OEntityPlayer)var4;
               } else if(var4 instanceof OEntityWolf) {
                  OEntityWolf var5 = (OEntityWolf)var4;
                  if(var5.z()) {
                     this.c = 60;
                     this.b = null;
                  }
               }
            }

            if(var3) {
               this.bb.a(this, (byte)2);
               this.aq();
               if(var4 != null) {
                  double var6 = var4.bf - this.bf;

                  double var8;
                  for(var8 = var4.bh - this.bh; var6 * var6 + var8 * var8 < 1.0E-4D; var8 = (Math.random() - Math.random()) * 0.01D) {
                     var6 = (Math.random() - Math.random()) * 0.01D;
                  }

                  this.ar = (float)(Math.atan2(var8, var6) * 180.0D / 3.1415927410125732D) - this.bl;
                  this.a(var4, var2, var6, var8);
               } else {
                  this.ar = (float)((int)(Math.random() * 2.0D) * 180);
               }
            }

            if(this.an <= 0) {
               if(var3) {
                  this.bb.a(this, this.j(), this.l(), (this.bL.nextFloat() - this.bL.nextFloat()) * 0.2F + 1.0F);
               }

               this.a(var1);
            } else if(var3) {
               this.bb.a(this, this.i(), this.l(), (this.bL.nextFloat() - this.bL.nextFloat()) * 0.2F + 1.0F);
            }

            return true;
         }
      }
   }

   protected void b(ODamageSource var1, int var2) {
      this.an -= var2;
   }

   protected float l() {
      return 1.0F;
   }

   protected String h() {
      return null;
   }

   protected String i() {
      return "random.hurt";
   }

   protected String j() {
      return "random.hurt";
   }

   public void a(OEntity var1, int var2, double var3, double var5) {
      this.ca = true;
      float var7 = OMathHelper.a(var3 * var3 + var5 * var5);
      float var8 = 0.4F;
      this.bi /= 2.0D;
      this.bj /= 2.0D;
      this.bk /= 2.0D;
      this.bi -= var3 / (double)var7 * (double)var8;
      this.bj += 0.4000000059604645D;
      this.bk -= var5 / (double)var7 * (double)var8;
      if(this.bj > 0.4000000059604645D) {
         this.bj = 0.4000000059604645D;
      }

   }

   public void a(ODamageSource var1) {
      OEntity var2 = var1.a();
      if(this.ag >= 0 && var2 != null) {
         var2.b(this, this.ag);
      }

      if(var2 != null) {
         var2.a(this);
      }

      this.aw = true;
      if(!this.bb.I) {
         this.a(this.c > 0);
      }

      this.bb.a(this, (byte)3);
   }

   protected void a(boolean var1) {
      int var2 = this.k();
      if(var2 > 0) {
         int var3 = this.bL.nextInt(3);

         for(int var4 = 0; var4 < var3; ++var4) {
            this.b(var2, 1);
         }
      }

   }

   protected int k() {
      return 0;
   }

   protected void a(float var1) {
      super.a(var1);
      int var2 = (int)Math.ceil((double)(var1 - 3.0F));
      if(var2 > 0) {
      // CanaryMod Damage hook: Falling
         if(!(Boolean)manager.callHook(PluginLoader.Hook.DAMAGE, PluginLoader.DamageType.FALL, null, entity, var2))
            this.a(ODamageSource.h, var2);
         
         int var3 = this.bb.a(OMathHelper.b(this.bf), OMathHelper.b(this.bg - 0.20000000298023224D - (double)this.by), OMathHelper.b(this.bh));
         if(var3 > 0) {
            OStepSound var4 = OBlock.m[var3].bL;
            this.bb.a(this, var4.c(), var4.a() * 0.5F, var4.b() * 0.75F);
         }
      }

   }

   public void a(float var1, float var2) {
      double var3;
      if(this.ao()) {
         var3 = this.bg;
         this.a(var1, var2, 0.02F);
         this.a_(this.bi, this.bj, this.bk);
         this.bi *= 0.800000011920929D;
         this.bj *= 0.800000011920929D;
         this.bk *= 0.800000011920929D;
         this.bj -= 0.02D;
         if(this.br && this.d(this.bi, this.bj + 0.6000000238418579D - this.bg + var3, this.bk)) {
            this.bj = 0.30000001192092896D;
         }
      } else if(this.ap()) {
         var3 = this.bg;
         this.a(var1, var2, 0.02F);
         this.a_(this.bi, this.bj, this.bk);
         this.bi *= 0.5D;
         this.bj *= 0.5D;
         this.bk *= 0.5D;
         this.bj -= 0.02D;
         if(this.br && this.d(this.bi, this.bj + 0.6000000238418579D - this.bg + var3, this.bk)) {
            this.bj = 0.30000001192092896D;
         }
      } else {
         float var5 = 0.91F;
         if(this.bq) {
            var5 = 0.54600006F;
            int var6 = this.bb.a(OMathHelper.b(this.bf), OMathHelper.b(this.bp.b) - 1, OMathHelper.b(this.bh));
            if(var6 > 0) {
               var5 = OBlock.m[var6].bO * 0.91F;
            }
         }

         float var12 = 0.16277136F / (var5 * var5 * var5);
         float var7 = this.bq?this.aj * var12:this.ak;
         this.a(var1, var2, var7);
         var5 = 0.91F;
         if(this.bq) {
            var5 = 0.54600006F;
            int var8 = this.bb.a(OMathHelper.b(this.bf), OMathHelper.b(this.bp.b) - 1, OMathHelper.b(this.bh));
            if(var8 > 0) {
               var5 = OBlock.m[var8].bO * 0.91F;
            }
         }

         if(this.p()) {
            float var13 = 0.15F;
            if(this.bi < (double)(-var13)) {
               this.bi = (double)(-var13);
            }

            if(this.bi > (double)var13) {
               this.bi = (double)var13;
            }

            if(this.bk < (double)(-var13)) {
               this.bk = (double)(-var13);
            }

            if(this.bk > (double)var13) {
               this.bk = (double)var13;
            }

            this.bD = 0.0F;
            if(this.bj < -0.15D) {
               this.bj = -0.15D;
            }

            if(this.as() && this.bj < 0.0D) {
               this.bj = 0.0D;
            }
         }

         this.a_(this.bi, this.bj, this.bk);
         if(this.br && this.p()) {
            this.bj = 0.2D;
         }

         this.bj -= 0.08D;
         this.bj *= 0.9800000190734863D;
         this.bi *= (double)var5;
         this.bk *= (double)var5;
      }

      this.aA = this.aB;
      var3 = this.bf - this.bc;
      double var9 = this.bh - this.be;
      float var11 = OMathHelper.a(var3 * var3 + var9 * var9) * 4.0F;
      if(var11 > 1.0F) {
         var11 = 1.0F;
      }

      this.aB += (var11 - this.aB) * 0.4F;
      this.aC += this.aB;
   }

   public boolean p() {
      int var1 = OMathHelper.b(this.bf);
      int var2 = OMathHelper.b(this.bp.b);
      int var3 = OMathHelper.b(this.bh);
      return this.bb.a(var1, var2, var3) == OBlock.aG.bA;
   }

   public void b(ONBTTagCompound var1) {
      var1.a("Health", (short)this.an);
      var1.a("HurtTime", (short)this.ap);
      var1.a("DeathTime", (short)this.as);
      var1.a("AttackTime", (short)this.at);
      if(!this.aF.isEmpty()) {
         ONBTTagList var2 = new ONBTTagList();
         Iterator var3 = this.aF.values().iterator();

         while(var3.hasNext()) {
            OPotionEffect var4 = (OPotionEffect)var3.next();
            ONBTTagCompound var5 = new ONBTTagCompound();
            var5.a("Id", (byte)var4.a());
            var5.a("Amplifier", (byte)var4.c());
            var5.a("Duration", var4.b());
            var2.a((ONBTBase)var5);
         }

         var1.a("ActiveEffects", (ONBTBase)var2);
      }

   }

   public void a(ONBTTagCompound var1) {
      this.an = var1.d("Health");
      if(!var1.b("Health")) {
         this.an = 10;
      }

      this.ap = var1.d("HurtTime");
      this.as = var1.d("DeathTime");
      this.at = var1.d("AttackTime");
      if(var1.b("ActiveEffects")) {
         ONBTTagList var2 = var1.l("ActiveEffects");

         for(int var3 = 0; var3 < var2.c(); ++var3) {
            ONBTTagCompound var4 = (ONBTTagCompound)var2.a(var3);
            byte var5 = var4.c("Id");
            byte var6 = var4.c("Amplifier");
            int var7 = var4.e("Duration");
            this.aF.put(Integer.valueOf(var5), new OPotionEffect(var5, var7, var6));
         }
      }

   }

   public boolean ac() {
      return !this.bx && this.an > 0;
   }

   public boolean b_() {
      return false;
   }

   public void s() {
      if(this.aG > 0) {
         double var1 = this.bf + (this.aH - this.bf) / (double)this.aG;
         double var3 = this.bg + (this.aI - this.bg) / (double)this.aG;
         double var5 = this.bh + (this.aJ - this.bh) / (double)this.aG;

         double var7;
         for(var7 = this.aK - (double)this.bl; var7 < -180.0D; var7 += 360.0D) {
            ;
         }

         while(var7 >= 180.0D) {
            var7 -= 360.0D;
         }

         this.bl = (float)((double)this.bl + var7 / (double)this.aG);
         this.bm = (float)((double)this.bm + (this.aL - (double)this.bm) / (double)this.aG);
         --this.aG;
         this.c(var1, var3, var5);
         this.c(this.bl, this.bm);
         List var9 = this.bb.a((OEntity)this, this.bp.e(0.03125D, 0.0D, 0.03125D));
         if(var9.size() > 0) {
            double var10 = 0.0D;

            for(int var12 = 0; var12 < var9.size(); ++var12) {
               OAxisAlignedBB var13 = (OAxisAlignedBB)var9.get(var12);
               if(var13.e > var10) {
                  var10 = var13.e;
               }
            }

            var3 += var10 - this.bp.b;
            this.c(var1, var3, var5);
         }
      }

      if(this.H()) {
         this.aS = false;
         this.aP = 0.0F;
         this.aQ = 0.0F;
         this.aR = 0.0F;
      } else if(!this.ai) {
         this.c_();
      }

      boolean var14 = this.ao();
      boolean var15 = this.ap();
      if(this.aS) {
         if(var14) {
            this.bj += 0.03999999910593033D;
         } else if(var15) {
            this.bj += 0.03999999910593033D;
         } else if(this.bq) {
            this.S();
         }
      }

      this.aP *= 0.98F;
      this.aQ *= 0.98F;
      this.aR *= 0.9F;
      float var16 = this.aj;
      this.aj *= this.D();
      this.a(this.aP, this.aQ);
      this.aj = var16;
      List var17 = this.bb.b((OEntity)this, this.bp.b(0.20000000298023224D, 0.0D, 0.20000000298023224D));
      if(var17 != null && var17.size() > 0) {
         for(int var18 = 0; var18 < var17.size(); ++var18) {
            OEntity var19 = (OEntity)var17.get(var18);
            if(var19.g()) {
               var19.i(this);
            }
         }
      }

   }

   protected boolean H() {
      return this.an <= 0;
   }

   public boolean G() {
      return false;
   }

   protected void S() {
      this.bj = 0.41999998688697815D;
      if(this.at()) {
         float var1 = this.bl * 0.017453292F;
         this.bi -= (double)(OMathHelper.a(var1) * 0.2F);
         this.bk += (double)(OMathHelper.b(var1) * 0.2F);
      }

      this.ca = true;
   }

   protected boolean d_() {
      return true;
   }

   protected void ad() {
      OEntityPlayer var1 = this.bb.a(this, -1.0D);
      if(this.d_() && var1 != null) {
         double var2 = var1.bf - this.bf;
         double var4 = var1.bg - this.bg;
         double var6 = var1.bh - this.bh;
         double var8 = var2 * var2 + var4 * var4 + var6 * var6;
         if(var8 > 16384.0D) {
            // CanaryMod hook onEntityDespawn
            if(!(Boolean)OEntityLiving.manager.callHook(PluginLoader.Hook.ENTITY_DESPAWN, this.entity)) {
               this.N();
            }
         }

         if(this.aO > 600 && this.bL.nextInt(800) == 0) {
            if(var8 < 1024.0D) {
               this.aO = 0;
            } else {
               // CanaryMod hook onEntityDespawn
               if(!(Boolean)OEntityLiving.manager.callHook(PluginLoader.Hook.ENTITY_DESPAWN, this.entity)) {
                  this.N();
               } else {
                  this.aO = 0;
               }
            }
         }
      }

   }

   protected void c_() {
      ++this.aO;
      OEntityPlayer var1 = this.bb.a(this, -1.0D);
      this.ad();
      this.aP = 0.0F;
      this.aQ = 0.0F;
      float var2 = 8.0F;
      if(this.bL.nextFloat() < 0.02F) {
         var1 = this.bb.a(this, (double)var2);
         if(var1 != null) {
            this.d = var1;
            this.aV = 10 + this.bL.nextInt(20);
         } else {
            this.aR = (this.bL.nextFloat() - 0.5F) * 20.0F;
         }
      }

      if(this.d != null) {
         this.a(this.d, 10.0F, (float)this.u());
         if(this.aV-- <= 0 || this.d.bx || this.d.h(this) > (double)(var2 * var2)) {
            this.d = null;
         }
      } else {
         if(this.bL.nextFloat() < 0.05F) {
            this.aR = (this.bL.nextFloat() - 0.5F) * 20.0F;
         }

         this.bl += this.aR;
         this.bm = this.aT;
      }

      boolean var3 = this.ao();
      boolean var4 = this.ap();
      if(var3 || var4) {
         this.aS = this.bL.nextFloat() < 0.8F;
      }

   }

   protected int u() {
      return 40;
   }

   public void a(OEntity var1, float var2, float var3) {
      double var4 = var1.bf - this.bf;
      double var6 = var1.bh - this.bh;
      double var9;
      if(var1 instanceof OEntityLiving) {
         OEntityLiving var8 = (OEntityLiving)var1;
         var9 = this.bg + (double)this.t() - (var8.bg + (double)var8.t());
      } else {
         var9 = (var1.bp.b + var1.bp.e) / 2.0D - (this.bg + (double)this.t());
      }

      double var11 = (double)OMathHelper.a(var4 * var4 + var6 * var6);
      float var13 = (float)(Math.atan2(var6, var4) * 180.0D / 3.1415927410125732D) - 90.0F;
      float var14 = (float)(-(Math.atan2(var9, var11) * 180.0D / 3.1415927410125732D));
      this.bm = -this.b(this.bm, var14, var3);
      this.bl = this.b(this.bl, var13, var2);
   }

   public boolean ae() {
      return this.d != null;
   }

   public OEntity af() {
      return this.d;
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

   public void ag() {
   }

   public boolean d() {
      return this.bb.a(this.bp) && this.bb.a((OEntity)this, this.bp).size() == 0 && !this.bb.c(this.bp);
   }

   protected void ah() {
      this.a(ODamageSource.i, 4);
   }

   public OVec3D ai() {
      return this.c(1.0F);
   }

   public OVec3D c(float var1) {
      float var2;
      float var3;
      float var4;
      float var5;
      if(var1 == 1.0F) {
         var2 = OMathHelper.b(-this.bl * 0.017453292F - 3.1415927F);
         var3 = OMathHelper.a(-this.bl * 0.017453292F - 3.1415927F);
         var4 = -OMathHelper.b(-this.bm * 0.017453292F);
         var5 = OMathHelper.a(-this.bm * 0.017453292F);
         return OVec3D.b((double)(var3 * var4), (double)var5, (double)(var2 * var4));
      } else {
         var2 = this.bo + (this.bm - this.bo) * var1;
         var3 = this.bn + (this.bl - this.bn) * var1;
         var4 = OMathHelper.b(-var3 * 0.017453292F - 3.1415927F);
         var5 = OMathHelper.a(-var3 * 0.017453292F - 3.1415927F);
         float var6 = -OMathHelper.b(-var2 * 0.017453292F);
         float var7 = OMathHelper.a(-var2 * 0.017453292F);
         return OVec3D.b((double)(var5 * var6), (double)var7, (double)(var4 * var6));
      }
   }

   public int m() {
      return 4;
   }

   public boolean P() {
      return false;
   }

   protected void aj() {
      Iterator var1 = this.aF.keySet().iterator();

      while(var1.hasNext()) {
         Integer var2 = (Integer)var1.next();
         OPotionEffect var3 = (OPotionEffect)this.aF.get(var2);
         if(!var3.a(this) && !this.bb.I) {
            var1.remove();
            this.c(var3);
         }
      }

   }

   public Collection ak() {
      return this.aF.values();
   }

   public boolean a(OPotion var1) {
      return this.aF.containsKey(Integer.valueOf(var1.H));
   }

   public OPotionEffect b(OPotion var1) {
      return (OPotionEffect)this.aF.get(Integer.valueOf(var1.H));
   }

   public void d(OPotionEffect var1) {
      if(this.aF.containsKey(Integer.valueOf(var1.a()))) {
         ((OPotionEffect)this.aF.get(Integer.valueOf(var1.a()))).a(var1);
         this.b((OPotionEffect)this.aF.get(Integer.valueOf(var1.a())));
      } else {
         this.aF.put(Integer.valueOf(var1.a()), var1);
         this.a(var1);
      }

   }

   protected void a(OPotionEffect var1) {}

   protected void b(OPotionEffect var1) {}

   protected void c(OPotionEffect var1) {}

   protected float D() {
      float var1 = 1.0F;
      if(this.a(OPotion.c)) {
         var1 *= 1.0F + 0.2F * (float)(this.b(OPotion.c).c() + 1);
      }

      if(this.a(OPotion.d)) {
         var1 *= 1.0F - 0.15F * (float)(this.b(OPotion.d).c() + 1);
      }

      return var1;
   }
}