import java.util.ArrayList;

public class OBlockPistonBase extends OBlock {

   private boolean a;
   private boolean b;
// CanaryMod member.
   // Used to signal wether to retract the block attached to the stick piston.
   private boolean attemptRetractBlock;

   public OBlockPistonBase(int var1, int var2, boolean var3) {
      super(var1, var2, OMaterial.C);
      this.a = var3;
      this.attemptRetractBlock = true;
      this.a(h);
      this.c(0.5F);
   }

   public int a(int var1, int var2) {
      int var3 = c(var2);
      return var3 > 5?this.bz:(var1 == var3?(!d(var2) && this.bF <= 0.0D && this.bG <= 0.0D && this.bH <= 0.0D && this.bI >= 1.0D && this.bJ >= 1.0D && this.bK >= 1.0D?this.bz:110):(var1 == OPistonBlockTextures.a[var3]?109:108));
   }

   public boolean a() {
      return false;
   }

   public boolean a(OWorld var1, int var2, int var3, int var4, OEntityPlayer var5) {
      return false;
   }

   public void a(OWorld var1, int var2, int var3, int var4, OEntityLiving var5) {
      int var6 = c(var1, var2, var3, var4, (OEntityPlayer)var5);
      var1.c(var2, var3, var4, var6);
      if(!var1.I) {
         this.g(var1, var2, var3, var4);
      }

   }

   public void a(OWorld var1, int var2, int var3, int var4, int var5) {
      if(!var1.I && !this.b) {
         this.g(var1, var2, var3, var4);
      }

   }

   public void a(OWorld var1, int var2, int var3, int var4) {
      if(!var1.I && var1.b(var2, var3, var4) == null) {
         this.g(var1, var2, var3, var4);
      }

   }

   private void g(OWorld var1, int var2, int var3, int var4) {
      int var5 = var1.c(var2, var3, var4);
      int var6 = c(var5);
      boolean var7 = this.f(var1, var2, var3, var4, var6);
      if(var5 != 7) {
         if(var7 && !d(var5)) {
            if(h(var1, var2, var3, var4, var6)) {
               // CanaryMod hook onPistonExtend
               boolean allowExtension = !(Boolean)etc.getLoader().callHook(PluginLoader.Hook.PISTON_EXTEND, new Block(var1.world, (this.a) ? Block.Type.StickyPiston.getType() : Block.Type.Piston.getType(), var2, var3, var4, var5));
               if(allowExtension) {
                  var1.d(var2, var3, var4, var6 | 8);
                  var1.d(var2, var3, var4, 0, var6);
               }
            }
         } else if(!var7 && d(var5)) {
            // CanaryMod hook onPistonRetract
            // hook result is saved in attemptRetractBlock because later in the code the block is actually moved,
            // and only there we should deny retraction.
            this.attemptRetractBlock = !(Boolean)etc.getLoader().callHook(PluginLoader.Hook.PISTON_RETRACT, new Block(var1.world, (this.a) ? Block.Type.StickyPiston.getType() : Block.Type.Piston.getType(), var2, var3, var4, var5));
            var1.d(var2, var3, var4, var6);
            var1.d(var2, var3, var4, 1, var6);
         }

      }
   }

   private boolean f(OWorld var1, int var2, int var3, int var4, int var5) {
      return var5 != 0 && var1.j(var2, var3 - 1, var4, 0)?true:(var5 != 1 && var1.j(var2, var3 + 1, var4, 1)?true:(var5 != 2 && var1.j(var2, var3, var4 - 1, 2)?true:(var5 != 3 && var1.j(var2, var3, var4 + 1, 3)?true:(var5 != 5 && var1.j(var2 + 1, var3, var4, 5)?true:(var5 != 4 && var1.j(var2 - 1, var3, var4, 4)?true:(var1.j(var2, var3, var4, 0)?true:(var1.j(var2, var3 + 2, var4, 1)?true:(var1.j(var2, var3 + 1, var4 - 1, 2)?true:(var1.j(var2, var3 + 1, var4 + 1, 3)?true:(var1.j(var2 - 1, var3 + 1, var4, 4)?true:var1.j(var2 + 1, var3 + 1, var4, 5)))))))))));
   }

   public void a(OWorld var1, int var2, int var3, int var4, int var5, int var6) {
      this.b = true;
      if(var5 == 0) {
         if(this.i(var1, var2, var3, var4, var6)) {
            var1.c(var2, var3, var4, var6 | 8);
            var1.a((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "tile.piston.out", 0.5F, var1.w.nextFloat() * 0.25F + 0.6F);
         }
      } else if(var5 == 1) {
         OTileEntity var8 = var1.b(var2 + OPistonBlockTextures.b[var6], var3 + OPistonBlockTextures.c[var6], var4 + OPistonBlockTextures.d[var6]);
         if(var8 != null && var8 instanceof OTileEntityPiston) {
            ((OTileEntityPiston)var8).e();
         }

         var1.a(var2, var3, var4, OBlock.ad.bA, var6);
         var1.a(var2, var3, var4, OBlockPistonMoving.a(this.bA, var6, var6, false, true));
         if(this.a) {
            int var9 = var2 + OPistonBlockTextures.b[var6] * 2;
            int var10 = var3 + OPistonBlockTextures.c[var6] * 2;
            int var11 = var4 + OPistonBlockTextures.d[var6] * 2;
            int var12 = var1.a(var9, var10, var11);
            int var13 = var1.c(var9, var10, var11);
            boolean var14 = false;
            if(var12 == OBlock.ad.bA) {
               OTileEntity var15 = var1.b(var9, var10, var11);
               if(var15 != null && var15 instanceof OTileEntityPiston) {
                  OTileEntityPiston var16 = (OTileEntityPiston)var15;
                  if(var16.d() == var6 && var16.c()) {
                     var16.e();
                     var12 = var16.a();
                     var13 = var16.j();
                     var14 = true;
                  }
               }
            }

            if(this.attemptRetractBlock && !var14 && var12 > 0 && a(var12, var1, var9, var10, var11, false) && (OBlock.m[var12].e() == 0 || var12 == OBlock.aa.bA || var12 == OBlock.W.bA)) {
               this.b = false;
               var1.e(var9, var10, var11, 0);
               this.b = true;
               var2 += OPistonBlockTextures.b[var6];
               var3 += OPistonBlockTextures.c[var6];
               var4 += OPistonBlockTextures.d[var6];
               var1.a(var2, var3, var4, OBlock.ad.bA, var13);
               var1.a(var2, var3, var4, OBlockPistonMoving.a(var12, var13, var6, false, false));
            } // if retraction fails normally (i2 == 0) OR the onPistonRetract returned false earlier.
            else if(!var14 || !this.attemptRetractBlock) {
               this.b = false;
               var1.e(var2 + OPistonBlockTextures.b[var6], var3 + OPistonBlockTextures.c[var6], var4 + OPistonBlockTextures.d[var6], 0);
               this.b = true;
            }
         } else {
            this.b = false;
            var1.e(var2 + OPistonBlockTextures.b[var6], var3 + OPistonBlockTextures.c[var6], var4 + OPistonBlockTextures.d[var6], 0);
            this.b = true;
         }

         var1.a((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "tile.piston.in", 0.5F, var1.w.nextFloat() * 0.15F + 0.6F);
      }

      this.b = false;
   }

   public void a(OIBlockAccess var1, int var2, int var3, int var4) {
      int var5 = var1.c(var2, var3, var4);
      if(d(var5)) {
         switch(c(var5)) {
         case 0:
            this.a(0.0F, 0.25F, 0.0F, 1.0F, 1.0F, 1.0F);
            break;
         case 1:
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
            break;
         case 2:
            this.a(0.0F, 0.0F, 0.25F, 1.0F, 1.0F, 1.0F);
            break;
         case 3:
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.75F);
            break;
         case 4:
            this.a(0.25F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            break;
         case 5:
            this.a(0.0F, 0.0F, 0.0F, 0.75F, 1.0F, 1.0F);
         }
      } else {
         this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      }

   }

   public void a(OWorld var1, int var2, int var3, int var4, OAxisAlignedBB var5, ArrayList var6) {
      this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      super.a(var1, var2, var3, var4, var5, var6);
   }

   public boolean b() {
      return false;
   }

   public static int c(int var0) {
      return var0 & 7;
   }

   public static boolean d(int var0) {
      return (var0 & 8) != 0;
   }

   private static int c(OWorld var0, int var1, int var2, int var3, OEntityPlayer var4) {
      if(OMathHelper.e((float)var4.bf - (float)var1) < 2.0F && OMathHelper.e((float)var4.bh - (float)var3) < 2.0F) {
         double var5 = var4.bg + 1.82D - (double)var4.by;
         if(var5 - (double)var2 > 2.0D) {
            return 1;
         }

         if((double)var2 - var5 > 0.0D) {
            return 0;
         }
      }

      int var7 = OMathHelper.b((double)(var4.bl * 4.0F / 360.0F) + 0.5D) & 3;
      return var7 == 0?2:(var7 == 1?5:(var7 == 2?3:(var7 == 3?4:0)));
   }

   private static boolean a(int var0, OWorld var1, int var2, int var3, int var4, boolean var5) {
      if(var0 == OBlock.aq.bA) {
         return false;
      } else {
         if(var0 != OBlock.aa.bA && var0 != OBlock.W.bA) {
            if(OBlock.m[var0].j() == -1.0F) {
               return false;
            }

            if(OBlock.m[var0].e() == 2) {
               return false;
            }

            if(!var5 && OBlock.m[var0].e() == 1) {
               return false;
            }
         } else if(d(var1.c(var2, var3, var4))) {
            return false;
         }

         OTileEntity var6 = var1.b(var2, var3, var4);
         return var6 == null;
      }
   }

   private static boolean h(OWorld var0, int var1, int var2, int var3, int var4) {
      int var5 = var1 + OPistonBlockTextures.b[var4];
      int var6 = var2 + OPistonBlockTextures.c[var4];
      int var7 = var3 + OPistonBlockTextures.d[var4];
      int var8 = 0;

      while(true) {
         if(var8 < 13) {
            if(var6 > 0) {
               var0.getClass();
               if(var6 < 128 - 1) {
                  int var9 = var0.a(var5, var6, var7);
                  if(var9 != 0) {
                     if(!a(var9, var0, var5, var6, var7, true)) {
                        return false;
                     }

                     if(OBlock.m[var9].e() != 1) {
                        if(var8 == 12) {
                           return false;
                        }

                        var5 += OPistonBlockTextures.b[var4];
                        var6 += OPistonBlockTextures.c[var4];
                        var7 += OPistonBlockTextures.d[var4];
                        ++var8;
                        continue;
                     }
                  }

                  return true;
               }
            }

            return false;
         }

         return true;
      }
   }

   private boolean i(OWorld var1, int var2, int var3, int var4, int var5) {
      int var6 = var2 + OPistonBlockTextures.b[var5];
      int var7 = var3 + OPistonBlockTextures.c[var5];
      int var8 = var4 + OPistonBlockTextures.d[var5];
      int var9 = 0;

      while(true) {
         int var10;
         if(var9 < 13) {
            label63: {
               if(var7 > 0) {
                  var1.getClass();
                  if(var7 < 128 - 1) {
                     var10 = var1.a(var6, var7, var8);
                     if(var10 != 0) {
                        if(!a(var10, var1, var6, var7, var8, true)) {
                           return false;
                        }

                        if(OBlock.m[var10].e() != 1) {
                           if(var9 == 12) {
                              return false;
                           }

                           var6 += OPistonBlockTextures.b[var5];
                           var7 += OPistonBlockTextures.c[var5];
                           var8 += OPistonBlockTextures.d[var5];
                           ++var9;
                           continue;
                        }

                        OBlock.m[var10].g(var1, var6, var7, var8, var1.c(var6, var7, var8));
                        var1.e(var6, var7, var8, 0);
                     }
                     break label63;
                  }
               }

               return false;
            }
         }

         while(var6 != var2 || var7 != var3 || var8 != var4) {
            var9 = var6 - OPistonBlockTextures.b[var5];
            var10 = var7 - OPistonBlockTextures.c[var5];
            int var11 = var8 - OPistonBlockTextures.d[var5];
            int var12 = var1.a(var9, var10, var11);
            int var13 = var1.c(var9, var10, var11);
            if(var12 == this.bA && var9 == var2 && var10 == var3 && var11 == var4) {
               var1.a(var6, var7, var8, OBlock.ad.bA, var5 | (this.a?8:0));
               var1.a(var6, var7, var8, OBlockPistonMoving.a(OBlock.ab.bA, var5 | (this.a?8:0), var5, true, false));
            } else {
               var1.a(var6, var7, var8, OBlock.ad.bA, var13);
               var1.a(var6, var7, var8, OBlockPistonMoving.a(var12, var13, var5, true, false));
            }

            var6 = var9;
            var7 = var10;
            var8 = var11;
         }

         return true;
      }
   }
}
