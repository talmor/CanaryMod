
import java.util.ArrayList;

public class OBlockPistonBase extends OBlock {
   private boolean a;
   private boolean b;
   // CanaryMod member.
   // Used to signal wether to retract the block attached to the stick piston.
   private boolean attemptRetractBlock;

   public OBlockPistonBase(int paramInt1, int paramInt2, boolean paramBoolean) {
      super(paramInt1, paramInt2, OMaterial.B);
      this.a = paramBoolean;
      this.attemptRetractBlock = true;
      a(h);
      c(0.5F);
   }

   public int a(int paramInt1, int paramInt2) {
      int i = c(paramInt2);

      if(i > 5) {
         return this.bm;
      }

      if(paramInt1 == i) {
         if((d(paramInt2)) || (this.bs > 0.0D) || (this.bt > 0.0D) || (this.bu > 0.0D) || (this.bv < 1.0D) || (this.bw < 1.0D) || (this.bx < 1.0D)) {
            return 110;
         }
         return this.bm;
      }
      if(paramInt1 == OPistonBlockTextures.a[i]) {
         return 109;
      }

      return 108;
   }

   public boolean a() {
      return false;
   }

   public boolean a(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, OEntityPlayer paramOEntityPlayer) {
      return false;
   }

   public void a(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, OEntityLiving paramOEntityLiving) {
      int i = c(paramOWorld, paramInt1, paramInt2, paramInt3, (OEntityPlayer)paramOEntityLiving);
      paramOWorld.c(paramInt1, paramInt2, paramInt3, i);
      if(!paramOWorld.B)
         g(paramOWorld, paramInt1, paramInt2, paramInt3);
   }

   public void b(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
      if((!paramOWorld.B) && (!this.b)) {
         g(paramOWorld, paramInt1, paramInt2, paramInt3);
      }
   }

   public void c(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
      if((!paramOWorld.B) && (paramOWorld.b(paramInt1, paramInt2, paramInt3) == null))
         g(paramOWorld, paramInt1, paramInt2, paramInt3);
   }

   private void g(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3) {
      int i = paramOWorld.c(paramInt1, paramInt2, paramInt3);
      int j = c(i);
      boolean bool = f(paramOWorld, paramInt1, paramInt2, paramInt3, j);

      if(i == 7) {
         return;
      }

      if((bool) && (!d(i))) {
         if(h(paramOWorld, paramInt1, paramInt2, paramInt3, j)) {
            // CanaryMod hook onPistonExtend
            boolean allowExtension = !(Boolean)etc.getLoader().callHook(PluginLoader.Hook.PISTON_EXTEND, new Block(paramOWorld.world, (this.a)?Block.Type.StickyPiston.getType():Block.Type.Piston.getType(), paramInt1, paramInt2, paramInt3, i));
            if(allowExtension) {
               paramOWorld.d(paramInt1, paramInt2, paramInt3, j | 0x8);
               paramOWorld.d(paramInt1, paramInt2, paramInt3, 0, j);
            }
         }
      } else if((!bool) && (d(i))) {
         // CanaryMod hook onPistonRetract
         // hook result is saved in attemptRetractBlock because later in the code the block is actually moved,
         // and only there we should deny retraction.
         this.attemptRetractBlock = !(Boolean)etc.getLoader().callHook(PluginLoader.Hook.PISTON_RETRACT, new Block(paramOWorld.world, (this.a)?Block.Type.StickyPiston.getType():Block.Type.Piston.getType(), paramInt1, paramInt2, paramInt3, i));
         paramOWorld.d(paramInt1, paramInt2, paramInt3, j);
         paramOWorld.d(paramInt1, paramInt2, paramInt3, 1, j);
      }
   }

   private boolean f(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
      if((paramInt4 != 0) && (paramOWorld.j(paramInt1, paramInt2 - 1, paramInt3, 0)))
         return true;
      if((paramInt4 != 1) && (paramOWorld.j(paramInt1, paramInt2 + 1, paramInt3, 1)))
         return true;
      if((paramInt4 != 2) && (paramOWorld.j(paramInt1, paramInt2, paramInt3 - 1, 2)))
         return true;
      if((paramInt4 != 3) && (paramOWorld.j(paramInt1, paramInt2, paramInt3 + 1, 3)))
         return true;
      if((paramInt4 != 5) && (paramOWorld.j(paramInt1 + 1, paramInt2, paramInt3, 5)))
         return true;
      if((paramInt4 != 4) && (paramOWorld.j(paramInt1 - 1, paramInt2, paramInt3, 4)))
         return true;

      if(paramOWorld.j(paramInt1, paramInt2, paramInt3, 0))
         return true;
      if(paramOWorld.j(paramInt1, paramInt2 + 2, paramInt3, 1))
         return true;
      if(paramOWorld.j(paramInt1, paramInt2 + 1, paramInt3 - 1, 2))
         return true;
      if(paramOWorld.j(paramInt1, paramInt2 + 1, paramInt3 + 1, 3))
         return true;
      if(paramOWorld.j(paramInt1 - 1, paramInt2 + 1, paramInt3, 4))
         return true;
      return paramOWorld.j(paramInt1 + 1, paramInt2 + 1, paramInt3, 5);
   }

   public void a(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
      this.b = true;

      int i = paramInt5;

      if(paramInt4 == 0) {
         if(i(paramOWorld, paramInt1, paramInt2, paramInt3, i)) {
            paramOWorld.c(paramInt1, paramInt2, paramInt3, i | 0x8);
            paramOWorld.a(paramInt1 + 0.5D, paramInt2 + 0.5D, paramInt3 + 0.5D, "tile.piston.out", 0.5F, paramOWorld.r.nextFloat() * 0.25F + 0.6F);
         }
      } else if(paramInt4 == 1) {
         OTileEntity localOTileEntity1 = paramOWorld.b(paramInt1 + OPistonBlockTextures.b[i], paramInt2 + OPistonBlockTextures.c[i], paramInt3 + OPistonBlockTextures.d[i]);
         if((localOTileEntity1 != null) && ((localOTileEntity1 instanceof OTileEntityPiston))) {
            ((OTileEntityPiston)localOTileEntity1).k();
         }
         paramOWorld.a(paramInt1, paramInt2, paramInt3, OBlock.ad.bn, i);
         paramOWorld.a(paramInt1, paramInt2, paramInt3, OBlockPistonMoving.a(this.bn, i, i, false, true));

         if(this.a) {
            int j = paramInt1 + OPistonBlockTextures.b[i] * 2;
            int k = paramInt2 + OPistonBlockTextures.c[i] * 2;
            int m = paramInt3 + OPistonBlockTextures.d[i] * 2;
            int n = paramOWorld.a(j, k, m);
            int i1 = paramOWorld.c(j, k, m);
            int i2 = 0;

            if(n == OBlock.ad.bn) {
               OTileEntity localOTileEntity2 = paramOWorld.b(j, k, m);
               if((localOTileEntity2 != null) && ((localOTileEntity2 instanceof OTileEntityPiston))) {
                  OTileEntityPiston localOTileEntityPiston = (OTileEntityPiston)localOTileEntity2;

                  if((localOTileEntityPiston.d() == i) && (localOTileEntityPiston.c())) {
                     localOTileEntityPiston.k();
                     n = localOTileEntityPiston.a();
                     i1 = localOTileEntityPiston.e();
                     i2 = 1;
                  }
               }
            }
            // if onPistonRetract returned true
            if(this.attemptRetractBlock && (i2 == 0) && (n > 0) && (a(n, paramOWorld, j, k, m, false)) && ((OBlock.m[n].e() == 0) || (n == OBlock.aa.bn) || (n == OBlock.W.bn))) {
               this.b = false;
               paramOWorld.e(j, k, m, 0);
               this.b = true;

               paramInt1 += OPistonBlockTextures.b[i];
               paramInt2 += OPistonBlockTextures.c[i];
               paramInt3 += OPistonBlockTextures.d[i];

               paramOWorld.a(paramInt1, paramInt2, paramInt3, OBlock.ad.bn, i1);
               paramOWorld.a(paramInt1, paramInt2, paramInt3, OBlockPistonMoving.a(n, i1, i, false, false));
            } // if retraction fails normally (i2 == 0) OR the onPistonRetract returned false earlier.
            else if(i2 == 0 || !this.attemptRetractBlock) {
               this.b = false;
               paramOWorld.e(paramInt1 + OPistonBlockTextures.b[i], paramInt2 + OPistonBlockTextures.c[i], paramInt3 + OPistonBlockTextures.d[i], 0);
               this.b = true;
            }
         } else {
            this.b = false;
            paramOWorld.e(paramInt1 + OPistonBlockTextures.b[i], paramInt2 + OPistonBlockTextures.c[i], paramInt3 + OPistonBlockTextures.d[i], 0);
            this.b = true;
         }
         this.attemptRetractBlock = true;
         paramOWorld.a(paramInt1 + 0.5D, paramInt2 + 0.5D, paramInt3 + 0.5D, "tile.piston.in", 0.5F, paramOWorld.r.nextFloat() * 0.15F + 0.6F);
      }

      this.b = false;
   }

   public void a(OIBlockAccess paramOIBlockAccess, int paramInt1, int paramInt2, int paramInt3) {
      int i = paramOIBlockAccess.c(paramInt1, paramInt2, paramInt3);

      if(d(i)) {
         switch(c(i)) {
            case 0:
               a(0.0F, 0.25F, 0.0F, 1.0F, 1.0F, 1.0F);
               break;
            case 1:
               a(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
               break;
            case 2:
               a(0.0F, 0.0F, 0.25F, 1.0F, 1.0F, 1.0F);
               break;
            case 3:
               a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.75F);
               break;
            case 4:
               a(0.25F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
               break;
            case 5:
               a(0.0F, 0.0F, 0.0F, 0.75F, 1.0F, 1.0F);
         }
      } else
         a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   public void a(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, OAxisAlignedBB paramOAxisAlignedBB, ArrayList paramArrayList) {
      a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      super.a(paramOWorld, paramInt1, paramInt2, paramInt3, paramOAxisAlignedBB, paramArrayList);
   }

   public boolean b() {
      return false;
   }

   public static int c(int paramInt) {
      return paramInt & 0x7;
   }

   public static boolean d(int paramInt) {
      return (paramInt & 0x8) != 0;
   }

   private static int c(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, OEntityPlayer paramOEntityPlayer) {
      if((OMathHelper.e((float)paramOEntityPlayer.aP - paramInt1) < 2.0F) && (OMathHelper.e((float)paramOEntityPlayer.aR - paramInt3) < 2.0F)) {
         double d = paramOEntityPlayer.aQ + 1.82D - paramOEntityPlayer.bi;
         if(d - paramInt2 > 2.0D) {
            return 1;
         }

         if(paramInt2 - d > 0.0D) {
            return 0;
         }
      }

      int i = OMathHelper.b(paramOEntityPlayer.aV * 4.0F / 360.0F + 0.5D) & 0x3;
      if(i == 0)
         return 2;
      if(i == 1)
         return 5;
      if(i == 2)
         return 3;
      if(i == 3)
         return 4;
      return 0;
   }

   private static boolean a(int paramInt1, OWorld paramOWorld, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean) {
      if(paramInt1 == OBlock.aq.bn) {
         return false;
      }

      if((paramInt1 == OBlock.aa.bn) || (paramInt1 == OBlock.W.bn)) {
         if(d(paramOWorld.c(paramInt2, paramInt3, paramInt4)))
            return false;
      } else {
         if(OBlock.m[paramInt1].j() == -1.0F) {
            return false;
         }

         if(OBlock.m[paramInt1].e() == 2) {
            return false;
         }

         if((!paramBoolean) && (OBlock.m[paramInt1].e() == 1)) {
            return false;
         }
      }

      OTileEntity localOTileEntity = paramOWorld.b(paramInt2, paramInt3, paramInt4);

      return localOTileEntity == null;
   }

   private static boolean h(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
      int i = paramInt1 + OPistonBlockTextures.b[paramInt4];
      int j = paramInt2 + OPistonBlockTextures.c[paramInt4];
      int k = paramInt3 + OPistonBlockTextures.d[paramInt4];

      for(int m = 0; m < 13; m++) {
         if((j <= 0) || (j >= 127)) {
            return false;
         }

         int n = paramOWorld.a(i, j, k);
         if(n == 0) {
            break;
         }
         if(!a(n, paramOWorld, i, j, k, true)) {
            return false;
         }

         if(OBlock.m[n].e() == 1) {
            break;
         }
         if(m == 12) {
            return false;
         }

         i += OPistonBlockTextures.b[paramInt4];
         j += OPistonBlockTextures.c[paramInt4];
         k += OPistonBlockTextures.d[paramInt4];
      }

      return true;
   }

   private boolean i(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
      int i = paramInt1 + OPistonBlockTextures.b[paramInt4];
      int j = paramInt2 + OPistonBlockTextures.c[paramInt4];
      int k = paramInt3 + OPistonBlockTextures.d[paramInt4];
      int n;
      for(int m = 0; m < 13; m++) {
         if((j <= 0) || (j >= 127)) {
            return false;
         }

         n = paramOWorld.a(i, j, k);
         if(n == 0) {
            break;
         }
         if(!a(n, paramOWorld, i, j, k, true)) {
            return false;
         }

         if(OBlock.m[n].e() == 1) {
            OBlock.m[n].g(paramOWorld, i, j, k, paramOWorld.c(i, j, k));

            paramOWorld.e(i, j, k, 0);
            break;
         }

         if(m == 12) {
            return false;
         }

         i += OPistonBlockTextures.b[paramInt4];
         j += OPistonBlockTextures.c[paramInt4];
         k += OPistonBlockTextures.d[paramInt4];
      }

      while((i != paramInt1) || (j != paramInt2) || (k != paramInt3)) {
         int m = i - OPistonBlockTextures.b[paramInt4];
         n = j - OPistonBlockTextures.c[paramInt4];
         int i1 = k - OPistonBlockTextures.d[paramInt4];

         int i2 = paramOWorld.a(m, n, i1);
         int i3 = paramOWorld.c(m, n, i1);

         if((i2 == this.bn) && (m == paramInt1) && (n == paramInt2) && (i1 == paramInt3)) {
            paramOWorld.a(i, j, k, OBlock.ad.bn, paramInt4 | (this.a?8:0));
            paramOWorld.a(i, j, k, OBlockPistonMoving.a(OBlock.ab.bn, paramInt4 | (this.a?8:0), paramInt4, true, false));
         } else {
            paramOWorld.a(i, j, k, OBlock.ad.bn, i3);
            paramOWorld.a(i, j, k, OBlockPistonMoving.a(i2, i3, paramInt4, true, false));
         }

         i = m;
         j = n;
         k = i1;
      }

      return true;
   }

}