
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
      this.b(0.8F, 0.8F);
      this.aE = 1.1F;
      this.ab = 8;
   }

   protected void b() {
      super.b();
      this.bE.a(16, Byte.valueOf((byte)0));
      this.bE.a(17, "");
      this.bE.a(18, new Integer(this.ab));
   }

   protected boolean n() {
      return false;
   }

   public void b(ONBTTagCompound var1) {
      super.b(var1);
      var1.a("Angry", this.z());
      var1.a("Sitting", this.y());
      if(this.x() == null) {
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
      if(var2.length() > 0) {
         this.a(var2);
         this.d(true);
      }

   }

   protected boolean l_() {
      return !this.A();
   }

   protected String g() {
      return this.z()?"mob.wolf.growl":(this.bv.nextInt(3) == 0?(this.A() && this.bE.b(18) < 10?"mob.wolf.whine":"mob.wolf.panting"):"mob.wolf.bark");
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
      if(!this.e && !this.B() && this.A() && this.aK == null) {
         OEntityPlayer var3 = this.aL.a(this.x());
         if(var3 != null) {
            float var2 = var3.f(this);
            if(var2 > 5.0F) {
               this.c(var3, var2);
            }
         } else if(!this.ac()) {
            this.b(true);
         }
      } else if(this.d == null && !this.B() && !this.A() && this.aL.r.nextInt(100) == 0) {
         List var1 = this.aL.a(OEntitySheep.class, OAxisAlignedBB.b(this.aP, this.aQ, this.aR, this.aP + 1.0D, this.aQ + 1.0D, this.aR + 1.0D).b(16.0D, 4.0D, 16.0D));
         if(!var1.isEmpty()) {
            this.c((OEntity)var1.get(this.aL.r.nextInt(var1.size())));
         }
      }

      if(this.ac()) {
         this.b(false);
      }

      if(!this.aL.B) {
         this.bE.b(18, Integer.valueOf(this.ab));
      }

   }

   public void u() {
      super.u();
      this.a = false;
      if(this.U() && !this.B() && !this.z()) {
         OEntity var1 = this.V();
         if(var1 instanceof OEntityPlayer) {
            OEntityPlayer var2 = (OEntityPlayer)var1;
            OItemStack var3 = var2.i.b();
            if(var3 != null) {
               if(!this.A() && var3.c == OItem.aV.be) {
                  this.a = true;
               } else if(this.A() && OItem.c[var3.c] instanceof OItemFood) {
                  this.a = ((OItemFood)OItem.c[var3.c]).l();
               }
            }
         }
      }

      if(!this.Y && this.f && !this.g && !this.B() && this.ba) {
         this.g = true;
         this.h = 0.0F;
         this.i = 0.0F;
         this.aL.a(this, (byte)8);
      }

   }

   public void o_() {
      super.o_();
      this.c = this.b;
      if(this.a) {
         this.b += (1.0F - this.b) * 0.4F;
      } else {
         this.b += (0.0F - this.b) * 0.4F;
      }

      if(this.a) {
         this.aF = 10;
      }

      if(this.ab()) {
         this.f = true;
         this.g = false;
         this.h = 0.0F;
         this.i = 0.0F;
      } else if((this.f || this.g) && this.g) {
         if(this.h == 0.0F) {
            this.aL.a(this, "mob.wolf.shake", this.k(), (this.bv.nextFloat() - this.bv.nextFloat()) * 0.2F + 1.0F);
         }

         this.i = this.h;
         this.h += 0.05F;
         if(this.i >= 2.0F) {
            this.f = false;
            this.g = false;
            this.i = 0.0F;
            this.h = 0.0F;
         }

         if(this.h > 0.4F) {
            float var1 = (float)this.aZ.b;
            int var2 = (int)(OMathHelper.a((this.h - 0.4F) * 3.1415927F) * 7.0F);

            for(int var3 = 0; var3 < var2; ++var3) {
               float var4 = (this.bv.nextFloat() * 2.0F - 1.0F) * this.bj * 0.5F;
               float var5 = (this.bv.nextFloat() * 2.0F - 1.0F) * this.bj * 0.5F;
               this.aL.a("splash", this.aP + (double)var4, (double)(var1 + 0.8F), this.aR + (double)var5, this.aS, this.aT, this.aU);
            }
         }
      }

   }

   public float s() {
      return this.bk * 0.8F;
   }

   protected int v() {
      return this.y()?20:super.v();
   }

   private void c(OEntity var1, float var2) {
      OPathEntity var3 = this.aL.a(this, var1, 16.0F);
      if(var3 == null && var2 > 12.0F) {
         int var4 = OMathHelper.b(var1.aP) - 2;
         int var5 = OMathHelper.b(var1.aR) - 2;
         int var6 = OMathHelper.b(var1.aZ.b);

         for(int var7 = 0; var7 <= 4; ++var7) {
            for(int var8 = 0; var8 <= 4; ++var8) {
               if((var7 < 1 || var8 < 1 || var7 > 3 || var8 > 3) && this.aL.d(var4 + var7, var6 - 1, var5 + var8) && !this.aL.d(var4 + var7, var6, var5 + var8) && !this.aL.d(var4 + var7, var6 + 1, var5 + var8)) {
                  this.c((double)((float)(var4 + var7) + 0.5F), (double)var6, (double)((float)(var5 + var8) + 0.5F), this.aV, this.aW);
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
      if(var1 != null && !(var1 instanceof OEntityPlayer) && !(var1 instanceof OEntityArrow)) {
         var2 = (var2 + 1) / 2;
      }

      if(!super.a((OEntity)var1, var2)) {
         return false;
      } else {
         if(!this.A() && !this.z()) {
            if(var1 instanceof OEntityPlayer) {
               this.c(true);
               this.d = (OEntity)var1;
            }

            if(var1 instanceof OEntityArrow && ((OEntityArrow)var1).c != null) {
               var1 = ((OEntityArrow)var1).c;
            }

            if(var1 instanceof OEntityLiving) {
               List var3 = this.aL.a(OEntityWolf.class, OAxisAlignedBB.b(this.aP, this.aQ, this.aR, this.aP + 1.0D, this.aQ + 1.0D, this.aR + 1.0D).b(16.0D, 4.0D, 16.0D));
               Iterator var4 = var3.iterator();

               while(var4.hasNext()) {
                  OEntity var5 = (OEntity)var4.next();
                  OEntityWolf var6 = (OEntityWolf)var5;
                  if(!var6.A() && var6.d == null) {
                     var6.d = (OEntity)var1;
                     if(var1 instanceof OEntityPlayer) {
                        var6.c(true);
                     }
                  }
               }
            }
         } else if(var1 != this && var1 != null) {
            if(this.A() && var1 instanceof OEntityPlayer && ((OEntityPlayer)var1).r.equalsIgnoreCase(this.x())) {
               return true;
            }

            this.d = (OEntity)var1;
         }

         return true;
      }
   }

   protected OEntity o() {
      return this.z()?this.aL.a(this, 16.0D):null;
   }

   protected void a(OEntity var1, float var2) {
      if(var2 > 2.0F && var2 < 6.0F && this.bv.nextInt(10) == 0) {
         if(this.ba) {
            double var3 = var1.aP - this.aP;
            double var5 = var1.aR - this.aR;
            float var7 = OMathHelper.a(var3 * var3 + var5 * var5);
            this.aS = var3 / (double)var7 * 0.5D * 0.800000011920929D + this.aS * 0.20000000298023224D;
            this.aU = var5 / (double)var7 * 0.5D * 0.800000011920929D + this.aU * 0.20000000298023224D;
            this.aT = 0.4000000059604645D;
         }
      } else if((double)var2 < 1.5D && var1.aZ.e > this.aZ.b && var1.aZ.b < this.aZ.e) {
         this.ah = 20;
         byte var8 = 2;
         if(this.A()) {
            var8 = 4;
         }

         var1.a(this, var8);
      }

   }

   public boolean a(OEntityPlayer var1) {
      OItemStack var2 = var1.i.b();
      if(!this.A()) {
         if(var2 != null && var2.c == OItem.aV.be && !this.z()) {
            --var2.a;
            if(var2.a <= 0) {
               var1.i.a(var1.i.c, (OItemStack)null);
            }

            if(!this.aL.B) {
               PluginLoader.HookResult res = (PluginLoader.HookResult) manager.callHook(PluginLoader.Hook.TAME, var1, new Mob(this));
               if(this.bv.nextInt(3) == 0 && res == PluginLoader.HookResult.DEFAULT_ACTION || res == PluginLoader.HookResult.ALLOW_ACTION) {
                  this.d(true);
                  this.a((OPathEntity)null);
                  this.b(true);
                  this.ab = 20;
                  this.a(var1.r);
                  this.a(true);
                  this.aL.a(this, (byte)7);
               } else {
                  this.a(false);
                  this.aL.a(this, (byte)6);
               }
            }

            return true;
         }
      } else {
         if(var2 != null && OItem.c[var2.c] instanceof OItemFood) {
            OItemFood var3 = (OItemFood)OItem.c[var2.c];
            if(var3.l() && this.bE.b(18) < 20) {
               --var2.a;
               if(var2.a <= 0) {
                  var1.i.a(var1.i.c, (OItemStack)null);
               }

               this.b(((OItemFood)OItem.ao).k());
               return true;
            }
         }

         if(var1.r.equalsIgnoreCase(this.x())) {
            if(!this.aL.B) {
               this.b(!this.y());
               this.aC = false;
               this.a((OPathEntity)null);
            }

            return true;
         }
      }

      return false;
   }

   void a(boolean var1) {
      String var2 = "heart";
      if(!var1) {
         var2 = "smoke";
      }

      for(int var3 = 0; var3 < 7; ++var3) {
         double var4 = this.bv.nextGaussian() * 0.02D;
         double var6 = this.bv.nextGaussian() * 0.02D;
         double var8 = this.bv.nextGaussian() * 0.02D;
         this.aL.a(var2, this.aP + (double)(this.bv.nextFloat() * this.bj * 2.0F) - (double)this.bj, this.aQ + 0.5D + (double)(this.bv.nextFloat() * this.bk), this.aR + (double)(this.bv.nextFloat() * this.bj * 2.0F) - (double)this.bj, var4, var6, var8);
      }

   }

   public int l() {
      return 8;
   }

   public String x() {
      return this.bE.c(17);
   }

   public void a(String var1) {
      this.bE.b(17, var1);
   }

   public boolean y() {
      return (this.bE.a(16) & 1) != 0;
   }

   public void b(boolean var1) {
      byte var2 = this.bE.a(16);
      if(var1) {
         this.bE.b(16, Byte.valueOf((byte)(var2 | 1)));
      } else {
         this.bE.b(16, Byte.valueOf((byte)(var2 & -2)));
      }

   }

   public boolean z() {
      return (this.bE.a(16) & 2) != 0;
   }

   public void c(boolean var1) {
      byte var2 = this.bE.a(16);
      if(var1) {
         this.bE.b(16, Byte.valueOf((byte)(var2 | 2)));
      } else {
         this.bE.b(16, Byte.valueOf((byte)(var2 & -3)));
      }

   }

   public boolean A() {
      return (this.bE.a(16) & 4) != 0;
   }

   public void d(boolean var1) {
      byte var2 = this.bE.a(16);
      if(var1) {
         this.bE.b(16, Byte.valueOf((byte)(var2 | 4)));
      } else {
         this.bE.b(16, Byte.valueOf((byte)(var2 & -5)));
      }

   }
}
