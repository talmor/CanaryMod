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
      this.aY = true;
      this.b(1.5F, 0.6F);
      this.by = this.bA / 2.0F;
   }

   protected boolean e_() {
      return false;
   }

   protected void b() {}

   public OAxisAlignedBB b(OEntity var1) {
      return var1.bp;
   }

   public OAxisAlignedBB f() {
      return this.bp;
   }

   public boolean g() {
      return true;
   }

   public OEntityBoat(OWorld var1, double var2, double var4, double var6) {
      this(var1);
      this.c(var2, var4 + (double)this.by, var6);
      this.bi = 0.0D;
      this.bj = 0.0D;
      this.bk = 0.0D;
      this.bc = var2;
      this.bd = var4;
      this.be = var6;
      
      // CanaryMod: Creation of the boat
      manager.callHook(PluginLoader.Hook.VEHICLE_CREATE, boat);
   }

   public double n() {
      return (double)this.bA * 0.0D - 0.30000001192092896D;
   }

   public boolean a(ODamageSource var1, int var2) {
      // CanaryMod: Attack of the boat
      if ((Boolean) manager.callHook(PluginLoader.Hook.VEHICLE_DAMAGE, boat, var1 == null ? null : var1, var2))
         return true;
      
      if(!this.bb.I && !this.bx) {
         this.c = -this.c;
         this.b = 10;
         this.a += var2 * 10;
         this.aq();
         if(this.a > 40) {
            if(this.aZ != null) {
               this.aZ.a((OEntity)this);
            }

            int var3;
            for(var3 = 0; var3 < 3; ++var3) {
               this.a(OBlock.y.bA, 1, 0.0F);
            }

            for(var3 = 0; var3 < 2; ++var3) {
               this.a(OItem.B.bo, 1, 0.0F);
            }

            this.N();
         }

         return true;
      } else {
         return true;
      }
   }

   public boolean r_() {
      return !this.bx;
   }

   public void s_() {
      super.s_();
      // CanaryMod: Update of the boat
      manager.callHook(PluginLoader.Hook.VEHICLE_UPDATE, boat);
      
      double prevX = bf;
      double prevY = bg;
      double prevZ = bh;
      
      if(this.b > 0) {
         --this.b;
      }

      if(this.a > 0) {
         --this.a;
      }

      this.bc = this.bf;
      this.bd = this.bg;
      this.be = this.bh;
      byte var1 = 5;
      double var2 = 0.0D;

      for(int var4 = 0; var4 < var1; ++var4) {
         double var5 = this.bp.b + (this.bp.e - this.bp.b) * (double)(var4 + 0) / (double)var1 - 0.125D;
         double var7 = this.bp.b + (this.bp.e - this.bp.b) * (double)(var4 + 1) / (double)var1 - 0.125D;
         OAxisAlignedBB var9 = OAxisAlignedBB.b(this.bp.a, var5, this.bp.c, this.bp.d, var7, this.bp.f);
         if(this.bb.b(var9, OMaterial.g)) {
            var2 += 1.0D / (double)var1;
         }
      }

      double var10;
      double var12;
      double var14;
      double var16;
      if(this.bb.I) {
         if(this.d > 0) {
            var10 = this.bf + (this.e - this.bf) / (double)this.d;
            var12 = this.bg + (this.f - this.bg) / (double)this.d;
            var14 = this.bh + (this.g - this.bh) / (double)this.d;

            for(var16 = this.h - (double)this.bl; var16 < -180.0D; var16 += 360.0D) {
               ;
            }

            while(var16 >= 180.0D) {
               var16 -= 360.0D;
            }

            this.bl = (float)((double)this.bl + var16 / (double)this.d);
            this.bm = (float)((double)this.bm + (this.i - (double)this.bm) / (double)this.d);
            --this.d;
            this.c(var10, var12, var14);
            this.c(this.bl, this.bm);
         } else {
            var10 = this.bf + this.bi;
            var12 = this.bg + this.bj;
            var14 = this.bh + this.bk;
            this.c(var10, var12, var14);
            if(this.bq) {
               this.bi *= 0.5D;
               this.bj *= 0.5D;
               this.bk *= 0.5D;
            }

            this.bi *= 0.9900000095367432D;
            this.bj *= 0.949999988079071D;
            this.bk *= 0.9900000095367432D;
         }

      } else {
         if(var2 < 1.0D) {
            var10 = var2 * 2.0D - 1.0D;
            this.bj += 0.03999999910593033D * var10;
         } else {
            if(this.bj < 0.0D) {
               this.bj /= 2.0D;
            }

            this.bj += 0.007000000216066837D;
         }

         if(this.aZ != null) {
            this.bi += this.aZ.bi * 0.2D;
            this.bk += this.aZ.bk * 0.2D;
         }

         var10 = 0.4D;
         if(this.bi < -var10) {
            this.bi = -var10;
         }

         if(this.bi > var10) {
            this.bi = var10;
         }

         if(this.bk < -var10) {
            this.bk = -var10;
         }

         if(this.bk > var10) {
            this.bk = var10;
         }

         if(this.bq) {
            this.bi *= 0.5D;
            this.bj *= 0.5D;
            this.bk *= 0.5D;
         }

         this.a_(this.bi, this.bj, this.bk);
         var12 = Math.sqrt(this.bi * this.bi + this.bk * this.bk);
         if(var12 > 0.15D) {
            var14 = Math.cos((double)this.bl * 3.141592653589793D / 180.0D);
            var16 = Math.sin((double)this.bl * 3.141592653589793D / 180.0D);

            for(int var18 = 0; (double)var18 < 1.0D + var12 * 60.0D; ++var18) {
               double var19 = (double)(this.bL.nextFloat() * 2.0F - 1.0F);
               double var21 = (double)(this.bL.nextInt(2) * 2 - 1) * 0.7D;
               double var23;
               double var25;
               if(this.bL.nextBoolean()) {
                  var23 = this.bf - var14 * var19 * 0.8D + var16 * var21;
                  var25 = this.bh - var16 * var19 * 0.8D - var14 * var21;
                  this.bb.a("splash", var23, this.bg - 0.125D, var25, this.bi, this.bj, this.bk);
               } else {
                  var23 = this.bf + var14 + var16 * var19 * 0.7D;
                  var25 = this.bh + var16 - var14 * var19 * 0.7D;
                  this.bb.a("splash", var23, this.bg - 0.125D, var25, this.bi, this.bj, this.bk);
               }
            }
         }

         if(this.br && var12 > 0.15D) {
            if(!this.bb.I) {
               this.N();

               int var27;
               for(var27 = 0; var27 < 3; ++var27) {
                  this.a(OBlock.y.bA, 1, 0.0F);
               }

               for(var27 = 0; var27 < 2; ++var27) {
                  this.a(OItem.B.bo, 1, 0.0F);
               }
            }
         } else {
            this.bi *= 0.9900000095367432D;
            this.bj *= 0.949999988079071D;
            this.bk *= 0.9900000095367432D;
         }

         this.bm = 0.0F;
         var14 = (double)this.bl;
         var16 = this.bc - this.bf;
         double var28 = this.be - this.bh;
         if(var16 * var16 + var28 * var28 > 0.001D) {
            var14 = (double)((float)(Math.atan2(var28, var16) * 180.0D / 3.141592653589793D));
         }

         double var30;
         for(var30 = var14 - (double)this.bl; var30 >= 180.0D; var30 -= 360.0D) {
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

         this.bl = (float)((double)this.bl + var30);
         this.c(this.bl, this.bm);
         
         // CanaryMod: Change of the boat
         if (bf != prevX || bg != prevY || bh != prevZ)
            manager.callHook(PluginLoader.Hook.VEHICLE_POSITIONCHANGE, boat, (int) bf, (int) bg, (int) bh);
         
         List var32 = this.bb.b((OEntity)this, this.bp.b(0.20000000298023224D, 0.0D, 0.20000000298023224D));
         int var33;
         if(var32 != null && var32.size() > 0) {
            for(var33 = 0; var33 < var32.size(); ++var33) {
               OEntity var34 = (OEntity)var32.get(var33);
               if(var34 != this.aZ && var34.g() && var34 instanceof OEntityBoat) {
                  var34.i(this);
               }
            }
         }

         for(var33 = 0; var33 < 4; ++var33) {
            int var37 = OMathHelper.b(this.bf + ((double)(var33 % 2) - 0.5D) * 0.8D);
            int var35 = OMathHelper.b(this.bg);
            int var36 = OMathHelper.b(this.bh + ((double)(var33 / 2) - 0.5D) * 0.8D);
            if(this.bb.a(var37, var35, var36) == OBlock.aT.bA) {
               this.bb.e(var37, var35, var36, 0);
            }
         }

         if(this.aZ != null && this.aZ.bx) {
            this.aZ = null;
         }

      }
   }

   public void g_() {
      if(this.aZ != null) {
         double var1 = Math.cos((double)this.bl * 3.141592653589793D / 180.0D) * 0.4D;
         double var3 = Math.sin((double)this.bl * 3.141592653589793D / 180.0D) * 0.4D;
         this.aZ.c(this.bf + var1, this.bg + this.n() + this.aZ.M(), this.bh + var3);
      }
   }

   protected void b(ONBTTagCompound var1) {}

   protected void a(ONBTTagCompound var1) {}

   public boolean b(OEntityPlayer var1) {
      // CanaryMod: Entering the boat
      manager.callHook(PluginLoader.Hook.VEHICLE_ENTERED, boat, var1.entity);
      
      if(this.aZ != null && this.aZ instanceof OEntityPlayer && this.aZ != var1) {
         return true;
      } else {
         if(!this.bb.I) {
            var1.a((OEntity)this);
         }

         return true;
      }
   }
}
