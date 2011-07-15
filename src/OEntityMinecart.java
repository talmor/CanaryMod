
import java.util.List;

public class OEntityMinecart extends OEntity implements OIInventory, Container<OItemStack> {

   private OItemStack[] h;
   public int a;
   public int b;
   public int c;
   private boolean i;
   public int d;
   public int e;
   public double f;
   public double g;
   private static final int[][][] j = new int[][][]{{{0, 0, -1}, {0, 0, 1}}, {{-1, 0, 0}, {1, 0, 0}}, {{-1, -1, 0}, {1, 0, 0}}, {{-1, 0, 0}, {1, -1, 0}}, {{0, 0, -1}, {0, -1, 1}}, {{0, -1, -1}, {0, 0, 1}}, {{0, 0, 1}, {1, 0, 0}}, {{0, 0, 1}, {-1, 0, 0}}, {{0, 0, -1}, {-1, 0, 0}}, {{0, 0, -1}, {1, 0, 0}}};
   private int k;
   private double l;
   private double m;
   private double n;
   private double o;
   private double p;
   // CanaryMod start
   private String name = "Minecart";
   Minecart cart = new Minecart(this);
   // CanaryMod end


   public OEntityMinecart(OWorld var1) {
      super(var1);
      this.h = new OItemStack[36];
      this.a = 0;
      this.b = 0;
      this.c = 1;
      this.i = false;
      this.aI = true;
      this.b(0.98F, 0.7F);
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
      return null;
   }

   public boolean d_() {
      return true;
   }

   public OEntityMinecart(OWorld var1, double var2, double var4, double var6, int var8) {
      this(var1);
      this.c(var2, var4 + (double)this.bi, var6);
      this.aS = 0.0D;
      this.aT = 0.0D;
      this.aU = 0.0D;
      this.aM = var2;
      this.aN = var4;
      this.aO = var6;
      this.d = var8;
      // CanaryMod: Creation of the cart
      manager.callHook(PluginLoader.Hook.VEHICLE_CREATE, cart);
   }

   public double m() {
      return (double)this.bk * 0.0D - 0.30000001192092896D;
   }

   public boolean a(OEntity var1, int var2) {
      // CanaryMod: Attack of the cart
      if ((Boolean) manager.callHook(PluginLoader.Hook.VEHICLE_DAMAGE, cart, var1 == null ? null : var1.entity, var2))
         return true;

      if(!this.aL.B && !this.bh) {
         this.c = -this.c;
         this.b = 10;
         this.af();
         this.a += var2 * 10;
         if(this.a > 40) {
            if(this.aJ != null) {
               this.aJ.b((OEntity)this);
            }

            this.J();
            this.a(OItem.ax.bf, 1, 0.0F);
            if(this.d == 1) {
               OEntityMinecart var3 = this;

               for(int var4 = 0; var4 < var3.a(); ++var4) {
                  OItemStack var5 = var3.d_(var4);
                  if(var5 != null) {
                     float var6 = this.bv.nextFloat() * 0.8F + 0.1F;
                     float var7 = this.bv.nextFloat() * 0.8F + 0.1F;
                     float var8 = this.bv.nextFloat() * 0.8F + 0.1F;

                     while(var5.a > 0) {
                        int var9 = this.bv.nextInt(21) + 10;
                        if(var9 > var5.a) {
                           var9 = var5.a;
                        }

                        var5.a -= var9;
                        OEntityItem var10 = new OEntityItem(this.aL, this.aP + (double)var6, this.aQ + (double)var7, this.aR + (double)var8, new OItemStack(var5.c, var9, var5.h()));
                        float var11 = 0.05F;
                        var10.aS = (double)((float)this.bv.nextGaussian() * var11);
                        var10.aT = (double)((float)this.bv.nextGaussian() * var11 + 0.2F);
                        var10.aU = (double)((float)this.bv.nextGaussian() * var11);
                        this.aL.b((OEntity)var10);
                     }
                  }
               }

               this.a(OBlock.av.bn, 1, 0.0F);
            } else if(this.d == 2) {
               this.a(OBlock.aC.bn, 1, 0.0F);
            }
         }

         return true;
      } else {
         return true;
      }
   }

   public boolean l_() {
      return !this.bh;
   }

   public void J() {
      // CanaryMod: Destruction of the cart
      manager.callHook(PluginLoader.Hook.VEHICLE_DESTROYED, cart);

      for(int var1 = 0; var1 < this.a(); ++var1) {
         OItemStack var2 = this.d_(var1);
         if(var2 != null) {
            float var3 = this.bv.nextFloat() * 0.8F + 0.1F;
            float var4 = this.bv.nextFloat() * 0.8F + 0.1F;
            float var5 = this.bv.nextFloat() * 0.8F + 0.1F;

            while(var2.a > 0) {
               int var6 = this.bv.nextInt(21) + 10;
               if(var6 > var2.a) {
                  var6 = var2.a;
               }

               var2.a -= var6;
               OEntityItem var7 = new OEntityItem(this.aL, this.aP + (double)var3, this.aQ + (double)var4, this.aR + (double)var5, new OItemStack(var2.c, var6, var2.h()));
               float var8 = 0.05F;
               var7.aS = (double)((float)this.bv.nextGaussian() * var8);
               var7.aT = (double)((float)this.bv.nextGaussian() * var8 + 0.2F);
               var7.aU = (double)((float)this.bv.nextGaussian() * var8);
               this.aL.b((OEntity)var7);
            }
         }
      }

      super.J();
   }

   public void m_() {
      // CanaryMod: Update of the cart
      manager.callHook(PluginLoader.Hook.VEHICLE_UPDATE, cart);

      if(this.b > 0) {
         --this.b;
      }

      if(this.a > 0) {
         --this.a;
      }

      double var7;
      if(this.aL.B && this.k > 0) {
         if(this.k > 0) {
            double var1 = this.aP + (this.l - this.aP) / (double)this.k;
            double var3 = this.aQ + (this.m - this.aQ) / (double)this.k;
            double var5 = this.aR + (this.n - this.aR) / (double)this.k;

            for(var7 = this.o - (double)this.aV; var7 < -180.0D; var7 += 360.0D) {
               ;
            }

            while(var7 >= 180.0D) {
               var7 -= 360.0D;
            }

            this.aV = (float)((double)this.aV + var7 / (double)this.k);
            this.aW = (float)((double)this.aW + (this.p - (double)this.aW) / (double)this.k);
            --this.k;
            this.c(var1, var3, var5);
            this.c(this.aV, this.aW);
         } else {
            this.c(this.aP, this.aQ, this.aR);
            this.c(this.aV, this.aW);
         }

      } else {
         this.aM = this.aP;
         this.aN = this.aQ;
         this.aO = this.aR;
         this.aT -= 0.03999999910593033D;
         int var9 = OMathHelper.b(this.aP);
         int var10 = OMathHelper.b(this.aQ);
         int var11 = OMathHelper.b(this.aR);
         // CanaryMod: Change of the cart
         manager.callHook(PluginLoader.Hook.VEHICLE_POSITIONCHANGE, cart, var9, var10, var11);

         if(OBlockRail.g(this.aL, var9, var10 - 1, var11)) {
            --var10;
         }

         double var12 = 0.4D;
         boolean var14 = false;
         var7 = 0.0078125D;
         int var15 = this.aL.a(var9, var10, var11);
         if(OBlockRail.c(var15)) {
            OVec3D var16 = this.h(this.aP, this.aQ, this.aR);
            int var17 = this.aL.c(var9, var10, var11);
            this.aQ = (double)var10;
            boolean var18 = false;
            boolean var19 = false;
            if(var15 == OBlock.U.bn) {
               var18 = (var17 & 8) != 0;
               var19 = !var18;
            }

            if(((OBlockRail)OBlock.m[var15]).f()) {
               var17 &= 7;
            }

            if(var17 >= 2 && var17 <= 5) {
               this.aQ = (double)(var10 + 1);
            }

            if(var17 == 2) {
               this.aS -= var7;
            }

            if(var17 == 3) {
               this.aS += var7;
            }

            if(var17 == 4) {
               this.aU += var7;
            }

            if(var17 == 5) {
               this.aU -= var7;
            }

            int[][] var20 = j[var17];
            double var21 = (double)(var20[1][0] - var20[0][0]);
            double var23 = (double)(var20[1][2] - var20[0][2]);
            double var25 = Math.sqrt(var21 * var21 + var23 * var23);
            double var27 = this.aS * var21 + this.aU * var23;
            if(var27 < 0.0D) {
               var21 = -var21;
               var23 = -var23;
            }

            double var29 = Math.sqrt(this.aS * this.aS + this.aU * this.aU);
            this.aS = var29 * var21 / var25;
            this.aU = var29 * var23 / var25;
            double var31;
            if(var19) {
               var31 = Math.sqrt(this.aS * this.aS + this.aU * this.aU);
               if(var31 < 0.03D) {
                  this.aS *= 0.0D;
                  this.aT *= 0.0D;
                  this.aU *= 0.0D;
               } else {
                  this.aS *= 0.5D;
                  this.aT *= 0.0D;
                  this.aU *= 0.5D;
               }
            }

            var31 = 0.0D;
            double var33 = (double)var9 + 0.5D + (double)var20[0][0] * 0.5D;
            double var35 = (double)var11 + 0.5D + (double)var20[0][2] * 0.5D;
            double var37 = (double)var9 + 0.5D + (double)var20[1][0] * 0.5D;
            double var39 = (double)var11 + 0.5D + (double)var20[1][2] * 0.5D;
            var21 = var37 - var33;
            var23 = var39 - var35;
            double var43;
            double var41;
            double var45;
            if(var21 == 0.0D) {
               this.aP = (double)var9 + 0.5D;
               var31 = this.aR - (double)var11;
            } else if(var23 == 0.0D) {
               this.aR = (double)var11 + 0.5D;
               var31 = this.aP - (double)var9;
            } else {
               var41 = this.aP - var33;
               var43 = this.aR - var35;
               var45 = (var41 * var21 + var43 * var23) * 2.0D;
               var31 = var45;
            }

            this.aP = var33 + var21 * var31;
            this.aR = var35 + var23 * var31;
            this.c(this.aP, this.aQ + (double)this.bi, this.aR);
            var41 = this.aS;
            var43 = this.aU;
            if(this.aJ != null) {
               var41 *= 0.75D;
               var43 *= 0.75D;
            }

            if(var41 < -var12) {
               var41 = -var12;
            }

            if(var41 > var12) {
               var41 = var12;
            }

            if(var43 < -var12) {
               var43 = -var12;
            }

            if(var43 > var12) {
               var43 = var12;
            }

            this.a(var41, 0.0D, var43);
            if(var20[0][1] != 0 && OMathHelper.b(this.aP) - var9 == var20[0][0] && OMathHelper.b(this.aR) - var11 == var20[0][2]) {
               this.c(this.aP, this.aQ + (double)var20[0][1], this.aR);
            } else if(var20[1][1] != 0 && OMathHelper.b(this.aP) - var9 == var20[1][0] && OMathHelper.b(this.aR) - var11 == var20[1][2]) {
               this.c(this.aP, this.aQ + (double)var20[1][1], this.aR);
            }

            if(this.aJ != null) {
               this.aS *= 0.996999979019165D;
               this.aT *= 0.0D;
               this.aU *= 0.996999979019165D;
            } else {
               if(this.d == 2) {
                  var45 = (double)OMathHelper.a(this.f * this.f + this.g * this.g);
                  if(var45 > 0.01D) {
                     var14 = true;
                     this.f /= var45;
                     this.g /= var45;
                     double var47 = 0.04D;
                     this.aS *= 0.800000011920929D;
                     this.aT *= 0.0D;
                     this.aU *= 0.800000011920929D;
                     this.aS += this.f * var47;
                     this.aU += this.g * var47;
                  } else {
                     this.aS *= 0.8999999761581421D;
                     this.aT *= 0.0D;
                     this.aU *= 0.8999999761581421D;
                  }
               }

               this.aS *= 0.9599999785423279D;
               this.aT *= 0.0D;
               this.aU *= 0.9599999785423279D;
            }

            OVec3D var49 = this.h(this.aP, this.aQ, this.aR);
            if(var49 != null && var16 != null) {
               double var50 = (var16.b - var49.b) * 0.05D;
               var29 = Math.sqrt(this.aS * this.aS + this.aU * this.aU);
               if(var29 > 0.0D) {
                  this.aS = this.aS / var29 * (var29 + var50);
                  this.aU = this.aU / var29 * (var29 + var50);
               }

               this.c(this.aP, var49.b, this.aR);
            }

            int var52 = OMathHelper.b(this.aP);
            int var53 = OMathHelper.b(this.aR);
            if(var52 != var9 || var53 != var11) {
               var29 = Math.sqrt(this.aS * this.aS + this.aU * this.aU);
               this.aS = var29 * (double)(var52 - var9);
               this.aU = var29 * (double)(var53 - var11);
            }

            double var54;
            if(this.d == 2) {
               var54 = (double)OMathHelper.a(this.f * this.f + this.g * this.g);
               if(var54 > 0.01D && this.aS * this.aS + this.aU * this.aU > 0.001D) {
                  this.f /= var54;
                  this.g /= var54;
                  if(this.f * this.aS + this.g * this.aU < 0.0D) {
                     this.f = 0.0D;
                     this.g = 0.0D;
                  } else {
                     this.f = this.aS;
                     this.g = this.aU;
                  }
               }
            }

            if(var18) {
               var54 = Math.sqrt(this.aS * this.aS + this.aU * this.aU);
               if(var54 > 0.01D) {
                  double var56 = 0.06D;
                  this.aS += this.aS / var54 * var56;
                  this.aU += this.aU / var54 * var56;
               } else if(var17 == 1) {
                  if(this.aL.e(var9 - 1, var10, var11)) {
                     this.aS = 0.02D;
                  } else if(this.aL.e(var9 + 1, var10, var11)) {
                     this.aS = -0.02D;
                  }
               } else if(var17 == 0) {
                  if(this.aL.e(var9, var10, var11 - 1)) {
                     this.aU = 0.02D;
                  } else if(this.aL.e(var9, var10, var11 + 1)) {
                     this.aU = -0.02D;
                  }
               }
            }
         } else {
            if(this.aS < -var12) {
               this.aS = -var12;
            }

            if(this.aS > var12) {
               this.aS = var12;
            }

            if(this.aU < -var12) {
               this.aU = -var12;
            }

            if(this.aU > var12) {
               this.aU = var12;
            }

            if(this.ba) {
               this.aS *= 0.5D;
               this.aT *= 0.5D;
               this.aU *= 0.5D;
            }

            this.a(this.aS, this.aT, this.aU);
            if(!this.ba) {
               this.aS *= 0.949999988079071D;
               this.aT *= 0.949999988079071D;
               this.aU *= 0.949999988079071D;
            }
         }

         this.aW = 0.0F;
         double var58 = this.aM - this.aP;
         double var60 = this.aO - this.aR;
         if(var58 * var58 + var60 * var60 > 0.001D) {
            this.aV = (float)(Math.atan2(var60, var58) * 180.0D / 3.141592653589793D);
            if(this.i) {
               this.aV += 180.0F;
            }
         }

         double var62;
         for(var62 = (double)(this.aV - this.aX); var62 >= 180.0D; var62 -= 360.0D) {
            ;
         }

         while(var62 < -180.0D) {
            var62 += 360.0D;
         }

         if(var62 < -170.0D || var62 >= 170.0D) {
            this.aV += 180.0F;
            this.i = !this.i;
         }

         this.c(this.aV, this.aW);
         List var64 = this.aL.b((OEntity)this, this.aZ.b(0.20000000298023224D, 0.0D, 0.20000000298023224D));
         if(var64 != null && var64.size() > 0) {
            for(int var65 = 0; var65 < var64.size(); ++var65) {
               OEntity var66 = (OEntity)var64.get(var65);
               if(var66 != this.aJ && var66.d_() && var66 instanceof OEntityMinecart) {
                  var66.h(this);
               }
            }
         }

         if(this.aJ != null && this.aJ.bh) {
            this.aJ = null;
         }

         if(var14 && this.bv.nextInt(4) == 0) {
            --this.e;
            if(this.e < 0) {
               this.f = this.g = 0.0D;
            }

            this.aL.a("largesmoke", this.aP, this.aQ + 0.8D, this.aR, 0.0D, 0.0D, 0.0D);
         }

      }
   }

   public OVec3D h(double var1, double var3, double var5) {
      int var7 = OMathHelper.b(var1);
      int var8 = OMathHelper.b(var3);
      int var9 = OMathHelper.b(var5);
      // CanaryMod: Change of the cart
      manager.callHook(PluginLoader.Hook.VEHICLE_POSITIONCHANGE, cart, var7, var8, var9);

      if(OBlockRail.g(this.aL, var7, var8 - 1, var9)) {
         --var8;
      }

      int var10 = this.aL.a(var7, var8, var9);
      if(OBlockRail.c(var10)) {
         int var11 = this.aL.c(var7, var8, var9);
         var3 = (double)var8;
         if(((OBlockRail)OBlock.m[var10]).f()) {
            var11 &= 7;
         }

         if(var11 >= 2 && var11 <= 5) {
            var3 = (double)(var8 + 1);
         }

         int[][] var12 = j[var11];
         double var13 = 0.0D;
         double var15 = (double)var7 + 0.5D + (double)var12[0][0] * 0.5D;
         double var17 = (double)var8 + 0.5D + (double)var12[0][1] * 0.5D;
         double var19 = (double)var9 + 0.5D + (double)var12[0][2] * 0.5D;
         double var21 = (double)var7 + 0.5D + (double)var12[1][0] * 0.5D;
         double var23 = (double)var8 + 0.5D + (double)var12[1][1] * 0.5D;
         double var25 = (double)var9 + 0.5D + (double)var12[1][2] * 0.5D;
         double var27 = var21 - var15;
         double var29 = (var23 - var17) * 2.0D;
         double var31 = var25 - var19;
         if(var27 == 0.0D) {
            var1 = (double)var7 + 0.5D;
            var13 = var5 - (double)var9;
         } else if(var31 == 0.0D) {
            var5 = (double)var9 + 0.5D;
            var13 = var1 - (double)var7;
         } else {
            double var33 = var1 - var15;
            double var35 = var5 - var19;
            double var37 = (var33 * var27 + var35 * var31) * 2.0D;
            var13 = var37;
         }

         var1 = var15 + var27 * var13;
         var3 = var17 + var29 * var13;
         var5 = var19 + var31 * var13;
         if(var29 < 0.0D) {
            ++var3;
         }

         if(var29 > 0.0D) {
            var3 += 0.5D;
         }

         return OVec3D.b(var1, var3, var5);
      } else {
         return null;
      }
   }

   protected void b(ONBTTagCompound var1) {
      var1.a("Type", this.d);
      if(this.d == 2) {
         var1.a("PushX", this.f);
         var1.a("PushZ", this.g);
         var1.a("Fuel", (short)this.e);
      } else if(this.d == 1) {
         ONBTTagList var2 = new ONBTTagList();

         for(int var3 = 0; var3 < this.h.length; ++var3) {
            if(this.h[var3] != null) {
               ONBTTagCompound var4 = new ONBTTagCompound();
               var4.a("Slot", (byte)var3);
               this.h[var3].a(var4);
               var2.a((ONBTBase)var4);
            }
         }

         var1.a("Items", (ONBTBase)var2);
      }

   }

   protected void a(ONBTTagCompound var1) {
      this.d = var1.e("Type");
      if(this.d == 2) {
         this.f = var1.h("PushX");
         this.g = var1.h("PushZ");
         this.e = var1.d("Fuel");
      } else if(this.d == 1) {
         ONBTTagList var2 = var1.l("Items");
         this.h = new OItemStack[this.a()];

         for(int var3 = 0; var3 < var2.c(); ++var3) {
            ONBTTagCompound var4 = (ONBTTagCompound)var2.a(var3);
            int var5 = var4.c("Slot") & 255;
            if(var5 >= 0 && var5 < this.h.length) {
               this.h[var5] = new OItemStack(var4);
            }
         }
      }

   }

   public void h(OEntity var1) {
      if(!this.aL.B) {
         if(var1 != this.aJ) {
            // CanaryMod: Collision of a cart
            if ((Boolean) manager.callHook(PluginLoader.Hook.VEHICLE_COLLISION, cart, var1.entity))
               return;

            if(var1 instanceof OEntityLiving && !(var1 instanceof OEntityPlayer) && this.d == 0 && this.aS * this.aS + this.aU * this.aU > 0.01D && this.aJ == null && var1.aK == null) {
               var1.b((OEntity)this);
            }

            double var2 = var1.aP - this.aP;
            double var4 = var1.aR - this.aR;
            double var6 = var2 * var2 + var4 * var4;
            if(var6 >= 9.999999747378752E-5D) {
               var6 = (double)OMathHelper.a(var6);
               var2 /= var6;
               var4 /= var6;
               double var8 = 1.0D / var6;
               if(var8 > 1.0D) {
                  var8 = 1.0D;
               }

               var2 *= var8;
               var4 *= var8;
               var2 *= 0.10000000149011612D;
               var4 *= 0.10000000149011612D;
               var2 *= (double)(1.0F - this.bu);
               var4 *= (double)(1.0F - this.bu);
               var2 *= 0.5D;
               var4 *= 0.5D;
               if(var1 instanceof OEntityMinecart) {
                  double var10 = var1.aP - this.aP;
                  double var12 = var1.aR - this.aR;
                  double var14 = var10 * var1.aU + var12 * var1.aM;
                  var14 *= var14;
                  if(var14 > 5.0D) {
                     return;
                  }

                  double var16 = var1.aS + this.aS;
                  double var18 = var1.aU + this.aU;
                  if(((OEntityMinecart)var1).d == 2 && this.d != 2) {
                     this.aS *= 0.20000000298023224D;
                     this.aU *= 0.20000000298023224D;
                     this.b(var1.aS - var2, 0.0D, var1.aU - var4);
                     var1.aS *= 0.699999988079071D;
                     var1.aU *= 0.699999988079071D;
                  } else if(((OEntityMinecart)var1).d != 2 && this.d == 2) {
                     var1.aS *= 0.20000000298023224D;
                     var1.aU *= 0.20000000298023224D;
                     var1.b(this.aS + var2, 0.0D, this.aU + var4);
                     this.aS *= 0.699999988079071D;
                     this.aU *= 0.699999988079071D;
                  } else {
                     var16 /= 2.0D;
                     var18 /= 2.0D;
                     this.aS *= 0.20000000298023224D;
                     this.aU *= 0.20000000298023224D;
                     this.b(var16 - var2, 0.0D, var18 - var4);
                     var1.aS *= 0.20000000298023224D;
                     var1.aU *= 0.20000000298023224D;
                     var1.b(var16 + var2, 0.0D, var18 + var4);
                  }
               } else {
                  this.b(-var2, 0.0D, -var4);
                  var1.b(var2 / 4.0D, 0.0D, var4 / 4.0D);
               }
            }

         }
      }
   }

   public int a() {
      return 27;
   }

   public OItemStack d_(int var1) {
      return this.h[var1];
   }

   public OItemStack a(int var1, int var2) {
      if(this.h[var1] != null) {
         OItemStack var3;
         if(this.h[var1].a <= var2) {
            var3 = this.h[var1];
            this.h[var1] = null;
            return var3;
         } else {
            var3 = this.h[var1].a(var2);
            if(this.h[var1].a == 0) {
               this.h[var1] = null;
            }

            return var3;
         }
      } else {
         return null;
      }
   }

   public void a(int var1, OItemStack var2) {
      this.h[var1] = var2;
      if(var2 != null && var2.a > this.d()) {
         var2.a = this.d();
      }

   }

   public String c() {
      return getName();
   }

   public int d() {
      return 64;
   }

   public void i() {}

   public boolean a(OEntityPlayer var1) {
      // CanaryMod: Entering the cart
      manager.callHook(PluginLoader.Hook.VEHICLE_ENTERED, cart, var1.entity);

      if(this.d == 0) {
         if(this.aJ != null && this.aJ instanceof OEntityPlayer && this.aJ != var1) {
            return true;
         }

         if(!this.aL.B) {
            var1.b((OEntity)this);
         }
      } else if(this.d == 1) {
         if(!this.aL.B) {
            var1.a((OIInventory)this);
         }
      } else if(this.d == 2) {
         OItemStack var2 = var1.i.b();
         if(var2 != null && var2.c == OItem.k.bf) {
            if(--var2.a == 0) {
               var1.i.a(var1.i.c, (OItemStack)null);
            }

            this.e += 1200;
         }

         this.f = this.aP - var1.aP;
         this.g = this.aR - var1.aR;
      }

      return true;
   }

   public boolean a_(OEntityPlayer var1) {
      return this.bh?false:var1.g(this) <= 64.0D;
   }

    @Override
    public OItemStack[] getContents() {
        return h;
    }

    @Override
    public void setContents(OItemStack[] values) {
        h = values;
    }

    @Override
    public OItemStack getContentsAt(int index) {
        return d_(index);
    }

    @Override
    public void setContentsAt(int index, OItemStack value) {
        a(index, value);
    }

    @Override
    public int getContentsSize() {
        return a();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String value) {
        name = value;
    }

}
