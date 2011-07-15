
import java.util.List;

public class OEntityBoat extends OEntity {

   public int a;
   public int b;
   public int c;
   private int d;
   private double e;
   private double f;
   private double g;
   private double h;
   private double i;
   // CanaryMod Start
   Boat boat = new Boat(this);
   // CanaryMod end


   public OEntityBoat(OWorld var1) {
      super(var1);
      this.a = 0;
      this.b = 0;
      this.c = 1;
      this.aI = true;
      this.b(1.5F, 0.6F);
      this.bi = this.bk / 2.0F;
   }

   protected boolean n() {
      return false;
   }

   protected void b() {}

   public OAxisAlignedBB a_(OEntity var1) {
      return var1.aZ;
   }

   public OAxisAlignedBB e_() {
      return this.aZ;
   }

   public boolean d_() {
      return true;
   }

   public OEntityBoat(OWorld var1, double var2, double var4, double var6) {
      this(var1);
      this.c(var2, var4 + (double)this.bi, var6);
      this.aS = 0.0D;
      this.aT = 0.0D;
      this.aU = 0.0D;
      this.aM = var2;
      this.aN = var4;
      this.aO = var6;

      // CanaryMod: Creation of the boat
      manager.callHook(PluginLoader.Hook.VEHICLE_CREATE, boat);
   }

   public double m() {
      return (double)this.bk * 0.0D - 0.30000001192092896D;
   }

   public boolean a(OEntity var1, int var2) {
      // CanaryMod: Attack of the boat
      if ((Boolean) manager.callHook(PluginLoader.Hook.VEHICLE_DAMAGE, boat, var1 == null ? null : var1.entity, var2))
         return true;

      if(!this.aL.B && !this.bh) {
         this.c = -this.c;
         this.b = 10;
         this.a += var2 * 10;
         this.af();
         if(this.a > 40) {
            if(this.aJ != null) {
               this.aJ.b((OEntity)this);
            }

            int var3;
            for(var3 = 0; var3 < 3; ++var3) {
               this.a(OBlock.y.bn, 1, 0.0F);
            }

            for(var3 = 0; var3 < 2; ++var3) {
               this.a(OItem.B.bf, 1, 0.0F);
            }

            this.J();
         }

         return true;
      } else {
         return true;
      }
   }

   public boolean l_() {
      return !this.bh;
   }

   public void m_() {
      super.m_();
      // CanaryMod: Update of the boat
      manager.callHook(PluginLoader.Hook.VEHICLE_UPDATE, boat);

      double prevX = aP;
      double prevY = aQ;
      double prevZ = aR;

      if(this.b > 0) {
         --this.b;
      }

      if(this.a > 0) {
         --this.a;
      }

      this.aM = this.aP;
      this.aN = this.aQ;
      this.aO = this.aR;
      byte var1 = 5;
      double var2 = 0.0D;

      for(int var4 = 0; var4 < var1; ++var4) {
         double var5 = this.aZ.b + (this.aZ.e - this.aZ.b) * (double)(var4 + 0) / (double)var1 - 0.125D;
         double var7 = this.aZ.b + (this.aZ.e - this.aZ.b) * (double)(var4 + 1) / (double)var1 - 0.125D;
         OAxisAlignedBB var9 = OAxisAlignedBB.b(this.aZ.a, var5, this.aZ.c, this.aZ.d, var7, this.aZ.f);
         if(this.aL.b(var9, OMaterial.g)) {
            var2 += 1.0D / (double)var1;
         }
      }

      double var10;
      double var12;
      double var14;
      double var16;
      if(this.aL.B) {
         if(this.d > 0) {
            var10 = this.aP + (this.e - this.aP) / (double)this.d;
            var12 = this.aQ + (this.f - this.aQ) / (double)this.d;
            var14 = this.aR + (this.g - this.aR) / (double)this.d;

            for(var16 = this.h - (double)this.aV; var16 < -180.0D; var16 += 360.0D) {
               ;
            }

            while(var16 >= 180.0D) {
               var16 -= 360.0D;
            }

            this.aV = (float)((double)this.aV + var16 / (double)this.d);
            this.aW = (float)((double)this.aW + (this.i - (double)this.aW) / (double)this.d);
            --this.d;
            this.c(var10, var12, var14);
            this.c(this.aV, this.aW);
         } else {
            var10 = this.aP + this.aS;
            var12 = this.aQ + this.aT;
            var14 = this.aR + this.aU;
            this.c(var10, var12, var14);
            if(this.ba) {
               this.aS *= 0.5D;
               this.aT *= 0.5D;
               this.aU *= 0.5D;
            }

            this.aS *= 0.9900000095367432D;
            this.aT *= 0.949999988079071D;
            this.aU *= 0.9900000095367432D;
         }

      } else {
         if(var2 < 1.0D) {
            var10 = var2 * 2.0D - 1.0D;
            this.aT += 0.03999999910593033D * var10;
         } else {
            if(this.aT < 0.0D) {
               this.aT /= 2.0D;
            }

            this.aT += 0.007000000216066837D;
         }

         if(this.aJ != null) {
            this.aS += this.aJ.aS * 0.2D;
            this.aU += this.aJ.aU * 0.2D;
         }

         var10 = 0.4D;
         if(this.aS < -var10) {
            this.aS = -var10;
         }

         if(this.aS > var10) {
            this.aS = var10;
         }

         if(this.aU < -var10) {
            this.aU = -var10;
         }

         if(this.aU > var10) {
            this.aU = var10;
         }

         if(this.ba) {
            this.aS *= 0.5D;
            this.aT *= 0.5D;
            this.aU *= 0.5D;
         }

         this.a(this.aS, this.aT, this.aU);
         var12 = Math.sqrt(this.aS * this.aS + this.aU * this.aU);
         if(var12 > 0.15D) {
            var14 = Math.cos((double)this.aV * 3.141592653589793D / 180.0D);
            var16 = Math.sin((double)this.aV * 3.141592653589793D / 180.0D);

            for(int var18 = 0; (double)var18 < 1.0D + var12 * 60.0D; ++var18) {
               double var19 = (double)(this.bv.nextFloat() * 2.0F - 1.0F);
               double var21 = (double)(this.bv.nextInt(2) * 2 - 1) * 0.7D;
               double var23;
               double var25;
               if(this.bv.nextBoolean()) {
                  var23 = this.aP - var14 * var19 * 0.8D + var16 * var21;
                  var25 = this.aR - var16 * var19 * 0.8D - var14 * var21;
                  this.aL.a("splash", var23, this.aQ - 0.125D, var25, this.aS, this.aT, this.aU);
               } else {
                  var23 = this.aP + var14 + var16 * var19 * 0.7D;
                  var25 = this.aR + var16 - var14 * var19 * 0.7D;
                  this.aL.a("splash", var23, this.aQ - 0.125D, var25, this.aS, this.aT, this.aU);
               }
            }
         }

         if(this.bb && var12 > 0.15D) {
            if(!this.aL.B) {
               this.J();

               int var27;
               for(var27 = 0; var27 < 3; ++var27) {
                  this.a(OBlock.y.bn, 1, 0.0F);
               }

               for(var27 = 0; var27 < 2; ++var27) {
                  this.a(OItem.B.bf, 1, 0.0F);
               }
            }
         } else {
            this.aS *= 0.9900000095367432D;
            this.aT *= 0.949999988079071D;
            this.aU *= 0.9900000095367432D;
         }

         this.aW = 0.0F;
         var14 = (double)this.aV;
         var16 = this.aM - this.aP;
         double var28 = this.aO - this.aR;
         if(var16 * var16 + var28 * var28 > 0.001D) {
            var14 = (double)((float)(Math.atan2(var28, var16) * 180.0D / 3.141592653589793D));
         }

         double var30;
         for(var30 = var14 - (double)this.aV; var30 >= 180.0D; var30 -= 360.0D) {
            ;
         }

         while(var30 < -180.0D) {
            var30 += 360.0D;
         }

         if(var30 > 20.0D) {
            var30 = 20.0D;
         }

         if(var30 < -20.0D) {
            var30 = -20.0D;
         }

         this.aV = (float)((double)this.aV + var30);
         this.c(this.aV, this.aW);


         // CanaryMod: Change of the boat
         if (aP != prevX || aQ != prevY || aR != prevZ)
            manager.callHook(PluginLoader.Hook.VEHICLE_POSITIONCHANGE, boat, (int) aP, (int) aQ, (int) aR);

         List var32 = this.aL.b((OEntity)this, this.aZ.b(0.20000000298023224D, 0.0D, 0.20000000298023224D));
         int var33;
         if(var32 != null && var32.size() > 0) {
            for(var33 = 0; var33 < var32.size(); ++var33) {
               OEntity var34 = (OEntity)var32.get(var33);
               if(var34 != this.aJ && var34.d_() && var34 instanceof OEntityBoat) {
                  var34.h(this);
               }
            }
         }

         for(var33 = 0; var33 < 4; ++var33) {
            int var37 = OMathHelper.b(this.aP + ((double)(var33 % 2) - 0.5D) * 0.8D);
            int var35 = OMathHelper.b(this.aQ);
            int var36 = OMathHelper.b(this.aR + ((double)(var33 / 2) - 0.5D) * 0.8D);
            if(this.aL.a(var37, var35, var36) == OBlock.aT.bn) {
               this.aL.e(var37, var35, var36, 0);
            }
         }

         if(this.aJ != null && this.aJ.bh) {
            this.aJ = null;
         }

      }
   }

   public void f() {
      if(this.aJ != null) {
         double var1 = Math.cos((double)this.aV * 3.141592653589793D / 180.0D) * 0.4D;
         double var3 = Math.sin((double)this.aV * 3.141592653589793D / 180.0D) * 0.4D;
         this.aJ.c(this.aP + var1, this.aQ + this.m() + this.aJ.I(), this.aR + var3);
      }
   }

   protected void b(ONBTTagCompound var1) {}

   protected void a(ONBTTagCompound var1) {}

   public boolean a(OEntityPlayer var1) {
      // CanaryMod: Entering the boat
      manager.callHook(PluginLoader.Hook.VEHICLE_ENTERED, boat, var1.entity);

      if(this.aJ != null && this.aJ instanceof OEntityPlayer && this.aJ != var1) {
         return true;
      } else {
         if(!this.aL.B) {
            var1.b((OEntity)this);
         }

         return true;
      }
   }
}
