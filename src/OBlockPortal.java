
import java.util.Random;

public class OBlockPortal extends OBlockBreakable {

   public OBlockPortal(int var1, int var2) {
      super(var1, var2, OMaterial.y, false);
   }

   public OAxisAlignedBB e(OWorld var1, int var2, int var3, int var4) {
      return null;
   }

   public void a(OIBlockAccess var1, int var2, int var3, int var4) {
      float var5;
      float var6;
      if(var1.a(var2 - 1, var3, var4) != this.bn && var1.a(var2 + 1, var3, var4) != this.bn) {
         var5 = 0.125F;
         var6 = 0.5F;
         this.a(0.5F - var5, 0.0F, 0.5F - var6, 0.5F + var5, 1.0F, 0.5F + var6);
      } else {
         var5 = 0.5F;
         var6 = 0.125F;
         this.a(0.5F - var5, 0.0F, 0.5F - var6, 0.5F + var5, 1.0F, 0.5F + var6);
      }

   }

   public boolean a() {
      return false;
   }

   public boolean b() {
      return false;
   }

   public boolean a_(OWorld var1, int var2, int var3, int var4) {
      byte var5 = 0;
      byte var6 = 0;
      if(var1.a(var2 - 1, var3, var4) == OBlock.aq.bn || var1.a(var2 + 1, var3, var4) == OBlock.aq.bn) {
         var5 = 1;
      }

      if(var1.a(var2, var3, var4 - 1) == OBlock.aq.bn || var1.a(var2, var3, var4 + 1) == OBlock.aq.bn) {
         var6 = 1;
      }

      if(var5 == var6) {
         return false;
      } else {
         if(var1.a(var2 - var5, var3, var4 - var6) == 0) {
            var2 -= var5;
            var4 -= var6;
         }

         int var7;
         int var8;
         for(var7 = -1; var7 <= 2; ++var7) {
            for(var8 = -1; var8 <= 3; ++var8) {
               boolean var9 = var7 == -1 || var7 == 2 || var8 == -1 || var8 == 3;
               if(var7 != -1 && var7 != 2 || var8 != -1 && var8 != 3) {
                  int var10 = var1.a(var2 + var5 * var7, var3 + var8, var4 + var6 * var7);
                  if(var9) {
                     if(var10 != OBlock.aq.bn) {
                        return false;
                     }
                  } else if(var10 != 0 && var10 != OBlock.as.bn) {
                     return false;
                  }
               }
            }
         }

         var1.o = true;

         for(var7 = 0; var7 < 2; ++var7) {
            for(var8 = 0; var8 < 3; ++var8) {
               var1.e(var2 + var5 * var7, var3 + var8, var4 + var6 * var7, OBlock.bf.bn);
            }
         }

         var1.o = false;
         return true;
      }
   }

   public void b(OWorld var1, int var2, int var3, int var4, int var5) {
      byte var6 = 0;
      byte var7 = 1;
      if(var1.a(var2 - 1, var3, var4) == this.bn || var1.a(var2 + 1, var3, var4) == this.bn) {
         var6 = 1;
         var7 = 0;
      }

      if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PHYSICS, new Block(var1.world, bn, var2, var3, var4), false))
          return;

      int var8;
      for(var8 = var3; var1.a(var2, var8 - 1, var4) == this.bn; --var8) {
         ;
      }

      if(var1.a(var2, var8 - 1, var4) != OBlock.aq.bn) {
         var1.e(var2, var3, var4, 0);
      } else {
         int var9;
         for(var9 = 1; var9 < 4 && var1.a(var2, var8 + var9, var4) == this.bn; ++var9) {
            ;
         }

         if(var9 == 3 && var1.a(var2, var8 + var9, var4) == OBlock.aq.bn) {
            boolean var10 = var1.a(var2 - 1, var3, var4) == this.bn || var1.a(var2 + 1, var3, var4) == this.bn;
            boolean var11 = var1.a(var2, var3, var4 - 1) == this.bn || var1.a(var2, var3, var4 + 1) == this.bn;
            if(var10 && var11) {
               var1.e(var2, var3, var4, 0);
            } else if((var1.a(var2 + var6, var3, var4 + var7) != OBlock.aq.bn || var1.a(var2 - var6, var3, var4 - var7) != this.bn) && (var1.a(var2 - var6, var3, var4 - var7) != OBlock.aq.bn || var1.a(var2 + var6, var3, var4 + var7) != this.bn)) {
               var1.e(var2, var3, var4, 0);
            }
         } else {
            var1.e(var2, var3, var4, 0);
         }
      }
   }

   public int a(Random var1) {
      return 0;
   }

   public void a(OWorld var1, int var2, int var3, int var4, OEntity var5) {
      if(var5.aK == null && var5.aJ == null) {
         var5.P();
      }

   }
}
