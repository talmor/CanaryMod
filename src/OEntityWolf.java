import java.util.Iterator;
import java.util.List;

public class OEntityWolf extends OEntityAnimal {

   private boolean a = false;
   private float b;
   private float c;
   private boolean g;
   private boolean h;
   private float i;
   private float j;


   public OEntityWolf(OWorld var1) {
      super(var1);
      this.ab = "/mob/wolf.png";
      this.b(0.8F, 0.8F);
      this.aU = 1.1F;
      this.an = 8;
   }

   protected void b() {
      super.b();
      this.bU.a(16, Byte.valueOf((byte)0));
      this.bU.a(17, "");
      this.bU.a(18, new Integer(this.an));
   }

   protected boolean e_() {
      return false;
   }

   public void b(ONBTTagCompound var1) {
      super.b(var1);
      var1.a("Angry", this.y());
      var1.a("Sitting", this.x());
      if(this.w() == null) {
         var1.a("Owner", "");
      } else {
         var1.a("Owner", this.w());
      }

   }

   public void a(ONBTTagCompound var1) {
      super.a(var1);
      this.d(var1.m("Angry"));
      this.c(var1.m("Sitting"));
      String var2 = var1.i("Owner");
      if(var2.length() > 0) {
         this.a(var2);
         this.e(true);
      }

   }

   protected boolean d_() {
      return !this.z();
   }

   protected String h() {
      return this.y()?"mob.wolf.growl":(this.bL.nextInt(3) == 0?(this.z() && this.bU.b(18) < 10?"mob.wolf.whine":"mob.wolf.panting"):"mob.wolf.bark");
   }

   protected String i() {
      return "mob.wolf.hurt";
   }

   protected String j() {
      return "mob.wolf.death";
   }

   protected float l() {
      return 0.4F;
   }

   protected int k() {
      return -1;
   }

   protected void c_() {
      super.c_();
      if(!this.e && !this.B() && this.z() && this.ba == null) {
         OEntityPlayer var3 = this.bb.a(this.w());
         if(var3 != null) {
            float var2 = var3.g(this);
            if(var2 > 5.0F) {
               this.c(var3, var2);
            }
         } else if(!this.ao()) {
            this.c(true);
         }
      } else if(this.d == null && !this.B() && !this.z() && this.bb.w.nextInt(100) == 0) {
         List var1 = this.bb.a(OEntitySheep.class, OAxisAlignedBB.b(this.bf, this.bg, this.bh, this.bf + 1.0D, this.bg + 1.0D, this.bh + 1.0D).b(16.0D, 4.0D, 16.0D));
         if(!var1.isEmpty()) {
            this.d((OEntity)var1.get(this.bb.w.nextInt(var1.size())));
         }
      }

      if(this.ao()) {
         this.c(false);
      }

      if(!this.bb.I) {
         this.bU.b(18, Integer.valueOf(this.an));
      }

   }

   public void s() {
      super.s();
      this.a = false;
      if(this.ae() && !this.B() && !this.y()) {
         OEntity var1 = this.af();
         if(var1 instanceof OEntityPlayer) {
            OEntityPlayer var2 = (OEntityPlayer)var1;
            OItemStack var3 = var2.j.b();
            if(var3 != null) {
               if(!this.z() && var3.c == OItem.aV.bo) {
                  this.a = true;
               } else if(this.z() && OItem.c[var3.c] instanceof OItemFood) {
                  this.a = ((OItemFood)OItem.c[var3.c]).m();
               }
            }
         }
      }

      if(!this.ai && this.g && !this.h && !this.B() && this.bq) {
         this.h = true;
         this.i = 0.0F;
         this.j = 0.0F;
         this.bb.a(this, (byte)8);
      }

   }

   public void s_() {
      super.s_();
      this.c = this.b;
      if(this.a) {
         this.b += (1.0F - this.b) * 0.4F;
      } else {
         this.b += (0.0F - this.b) * 0.4F;
      }

      if(this.a) {
         this.aV = 10;
      }

      if(this.an()) {
         this.g = true;
         this.h = false;
         this.i = 0.0F;
         this.j = 0.0F;
      } else if((this.g || this.h) && this.h) {
         if(this.i == 0.0F) {
            this.bb.a(this, "mob.wolf.shake", this.l(), (this.bL.nextFloat() - this.bL.nextFloat()) * 0.2F + 1.0F);
         }

         this.j = this.i;
         this.i += 0.05F;
         if(this.j >= 2.0F) {
            this.g = false;
            this.h = false;
            this.j = 0.0F;
            this.i = 0.0F;
         }

         if(this.i > 0.4F) {
            float var1 = (float)this.bp.b;
            int var2 = (int)(OMathHelper.a((this.i - 0.4F) * 3.1415927F) * 7.0F);

            for(int var3 = 0; var3 < var2; ++var3) {
               float var4 = (this.bL.nextFloat() * 2.0F - 1.0F) * this.bz * 0.5F;
               float var5 = (this.bL.nextFloat() * 2.0F - 1.0F) * this.bz * 0.5F;
               this.bb.a("splash", this.bf + (double)var4, (double)(var1 + 0.8F), this.bh + (double)var5, this.bi, this.bj, this.bk);
            }
         }
      }

   }

   public float t() {
      return this.bA * 0.8F;
   }

   protected int u() {
      return this.x()?20:super.u();
   }

   private void c(OEntity var1, float var2) {
      OPathEntity var3 = this.bb.a(this, var1, 16.0F);
      if(var3 == null && var2 > 12.0F) {
         int var4 = OMathHelper.b(var1.bf) - 2;
         int var5 = OMathHelper.b(var1.bh) - 2;
         int var6 = OMathHelper.b(var1.bp.b);

         for(int var7 = 0; var7 <= 4; ++var7) {
            for(int var8 = 0; var8 <= 4; ++var8) {
               if((var7 < 1 || var8 < 1 || var7 > 3 || var8 > 3) && this.bb.e(var4 + var7, var6 - 1, var5 + var8) && !this.bb.e(var4 + var7, var6, var5 + var8) && !this.bb.e(var4 + var7, var6 + 1, var5 + var8)) {
                  this.c((double)((float)(var4 + var7) + 0.5F), (double)var6, (double)((float)(var5 + var8) + 0.5F), this.bl, this.bm);
                  return;
               }
            }
         }
      } else {
         this.a(var3);
      }

   }

   protected boolean v() {
      return this.x() || this.h;
   }

   public boolean a(ODamageSource var1, int var2) {
      OEntity var3 = var1.a();
      this.c(false);
      if(var3 != null && !(var3 instanceof OEntityPlayer) && !(var3 instanceof OEntityArrow)) {
         var2 = (var2 + 1) / 2;
      }

      if(!super.a(var1, var2)) {
         return false;
      } else {
         if(!this.z() && !this.y()) {
            if(var3 instanceof OEntityPlayer) {
               this.d(true);
               this.d = var3;
            }

            if(var3 instanceof OEntityArrow && ((OEntityArrow)var3).c != null) {
               var3 = ((OEntityArrow)var3).c;
            }

            if(var3 instanceof OEntityLiving) {
               List var4 = this.bb.a(OEntityWolf.class, OAxisAlignedBB.b(this.bf, this.bg, this.bh, this.bf + 1.0D, this.bg + 1.0D, this.bh + 1.0D).b(16.0D, 4.0D, 16.0D));
               Iterator var5 = var4.iterator();

               while(var5.hasNext()) {
                  OEntity var6 = (OEntity)var5.next();
                  OEntityWolf var7 = (OEntityWolf)var6;
                  if(!var7.z() && var7.d == null) {
                     var7.d = var3;
                     if(var3 instanceof OEntityPlayer) {
                        var7.d(true);
                     }
                  }
               }
            }
         } else if(var3 != this && var3 != null) {
            if(this.z() && var3 instanceof OEntityPlayer && ((OEntityPlayer)var3).u.equalsIgnoreCase(this.w())) {
               return true;
            }

            this.d = var3;
         }

         return true;
      }
   }

   protected OEntity o() {
      return this.y()?this.bb.a(this, 16.0D):null;
   }

   protected void a(OEntity var1, float var2) {
      if(var2 > 2.0F && var2 < 6.0F && this.bL.nextInt(10) == 0) {
         if(this.bq) {
            double var3 = var1.bf - this.bf;
            double var5 = var1.bh - this.bh;
            float var7 = OMathHelper.a(var3 * var3 + var5 * var5);
            this.bi = var3 / (double)var7 * 0.5D * 0.800000011920929D + this.bi * 0.20000000298023224D;
            this.bk = var5 / (double)var7 * 0.5D * 0.800000011920929D + this.bk * 0.20000000298023224D;
            this.bj = 0.4000000059604645D;
         }
      } else if((double)var2 < 1.5D && var1.bp.e > this.bp.b && var1.bp.b < this.bp.e) {
         this.at = 20;
         byte var8 = 2;
         if(this.z()) {
            var8 = 4;
         }

         var1.a(ODamageSource.a((OEntityLiving)this), var8);
      }

   }

   public boolean b(OEntityPlayer var1) {
      OItemStack var2 = var1.j.b();
      if(!this.z()) {
         if(var2 != null && var2.c == OItem.aV.bo && !this.y()) {
            --var2.a;
            if(var2.a <= 0) {
               var1.j.a(var1.j.c, (OItemStack)null);
            }

            if(!this.bb.I) {
               // CanaryMod hook: onTame
               // randomize the tame result. if its 0 - tame success.
               int tameResult = this.bL.nextInt(3);
               // Call hook
               PluginLoader.HookResult res = (PluginLoader.HookResult)manager.callHook(PluginLoader.Hook.TAME, manager.getServer().getPlayer(var1.u), new Mob(this), tameResult == 0);
               // if taming succeeded normally (tameResult == 0) or plugin hook result is allow (force taming)
               if(tameResult == 0 && res == PluginLoader.HookResult.DEFAULT_ACTION || res == PluginLoader.HookResult.ALLOW_ACTION) {
                  this.e(true);
                  this.a((OPathEntity)null);
                  this.c(true);
                  this.an = 20;
                  this.a(var1.u);
                  this.b(true);
                  this.bb.a(this, (byte)7);
               } else {
                  this.b(false);
                  this.bb.a(this, (byte)6);
               }
            }

            return true;
         }
      } else {
         if(var2 != null && OItem.c[var2.c] instanceof OItemFood) {
            OItemFood var3 = (OItemFood)OItem.c[var2.c];
            if(var3.m() && this.bU.b(18) < 20) {
               --var2.a;
               this.c(var3.k());
               if(var2.a <= 0) {
                  var1.j.a(var1.j.c, (OItemStack)null);
               }

               return true;
            }
         }

         if(var1.u.equalsIgnoreCase(this.w())) {
            if(!this.bb.I) {
               this.c(!this.x());
               this.aS = false;
               this.a((OPathEntity)null);
            }

            return true;
         }
      }

      return false;
   }

   void b(boolean var1) {
      String var2 = "heart";
      if(!var1) {
         var2 = "smoke";
      }

      for(int var3 = 0; var3 < 7; ++var3) {
         double var4 = this.bL.nextGaussian() * 0.02D;
         double var6 = this.bL.nextGaussian() * 0.02D;
         double var8 = this.bL.nextGaussian() * 0.02D;
         this.bb.a(var2, this.bf + (double)(this.bL.nextFloat() * this.bz * 2.0F) - (double)this.bz, this.bg + 0.5D + (double)(this.bL.nextFloat() * this.bA), this.bh + (double)(this.bL.nextFloat() * this.bz * 2.0F) - (double)this.bz, var4, var6, var8);
      }

   }

   public int m() {
      return 8;
   }

   public String w() {
      return this.bU.c(17);
   }

   public void a(String var1) {
      this.bU.b(17, var1);
   }

   public boolean x() {
      return (this.bU.a(16) & 1) != 0;
   }

   public void c(boolean var1) {
      byte var2 = this.bU.a(16);
      if(var1) {
         this.bU.b(16, Byte.valueOf((byte)(var2 | 1)));
      } else {
         this.bU.b(16, Byte.valueOf((byte)(var2 & -2)));
      }

   }

   public boolean y() {
      return (this.bU.a(16) & 2) != 0;
   }

   public void d(boolean var1) {
      byte var2 = this.bU.a(16);
      if(var1) {
         this.bU.b(16, Byte.valueOf((byte)(var2 | 2)));
      } else {
         this.bU.b(16, Byte.valueOf((byte)(var2 & -3)));
      }

   }

   public boolean z() {
      return (this.bU.a(16) & 4) != 0;
   }

   public void e(boolean var1) {
      byte var2 = this.bU.a(16);
      if(var1) {
         this.bU.b(16, Byte.valueOf((byte)(var2 | 4)));
      } else {
         this.bU.b(16, Byte.valueOf((byte)(var2 & -5)));
      }

   }
}
