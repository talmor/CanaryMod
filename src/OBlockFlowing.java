
import java.util.Random;

public class OBlockFlowing extends OBlockFluid {

   int a = 0;
   boolean[] b = new boolean[4];
   int[] c = new int[4];


   protected OBlockFlowing(int var1, OMaterial var2) {
      super(var1, var2);
   }

   private void i(OWorld var1, int var2, int var3, int var4) {
      int var5 = var1.b(var2, var3, var4);
      var1.a(var2, var3, var4, this.bn + 1, var5);
      var1.b(var2, var3, var4, var2, var3, var4);
      var1.g(var2, var3, var4);
   }

   public void a(OWorld var1, int var2, int var3, int var4, Random var5) {
      // CanaryMod: Store originating block
      World world = var1.world;
      Block blockFrom = new Block(world, bn, var2, var3, var4);

      int var6 = this.g(var1, var2, var3, var4);
      byte var7 = 1;
      if(this.bA == OMaterial.h && !var1.t.d) {
         var7 = 2;
      }

      boolean var8 = true;
      int var10;
      if(var6 > 0) {
         byte var9 = -100;
         this.a = 0;
         int var12 = this.f(var1, var2 - 1, var3, var4, var9);
         var12 = this.f(var1, var2 + 1, var3, var4, var12);
         var12 = this.f(var1, var2, var3, var4 - 1, var12);
         var12 = this.f(var1, var2, var3, var4 + 1, var12);
         var10 = var12 + var7;
         if(var10 >= 8 || var12 < 0) {
            var10 = -1;
         }

         if(this.g(var1, var2, var3 + 1, var4) >= 0) {
            int var11 = this.g(var1, var2, var3 + 1, var4);
            if(var11 >= 8) {
               var10 = var11;
            } else {
               var10 = var11 + 8;
            }
         }

         if(this.a >= 2 && this.bA == OMaterial.g) {
            if(var1.c(var2, var3 - 1, var4).a()) {
               var10 = 0;
            } else if(var1.c(var2, var3 - 1, var4) == this.bA && var1.b(var2, var3, var4) == 0) {
               var10 = 0;
            }
         }

         if(this.bA == OMaterial.h && var6 < 8 && var10 < 8 && var10 > var6 && var5.nextInt(4) != 0) {
            var10 = var6;
            var8 = false;
         }

         if(var10 != var6) {
            var6 = var10;
            if(var10 < 0) {
               var1.e(var2, var3, var4, 0);
            } else {
               var1.c(var2, var3, var4, var10);
               var1.c(var2, var3, var4, this.bn, this.c());
               var1.h(var2, var3, var4, this.bn);
            }
         } else if(var8) {
            this.i(var1, var2, var3, var4);
         }
      } else {
         this.i(var1, var2, var3, var4);
      }

      if(this.l(var1, var2, var3 - 1, var4)) {
         // CanaryMod: downwards flow.
         Block blockTo = new Block(world, 0, var2, var3 - 1, var4);
         if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.FLOW, blockFrom, blockTo))
            if(var6 >= 8) {
               var1.b(var2, var3 - 1, var4, this.bn, var6);
            } else {
               var1.b(var2, var3 - 1, var4, this.bn, var6 + 8);
            }
      } else if(var6 >= 0 && (var6 == 0 || this.k(var1, var2, var3 - 1, var4))) {
         boolean[] var13 = this.j(var1, var2, var3, var4);
         var10 = var6 + var7;
         if(var6 >= 8) {
            var10 = 1;
         }

         if(var10 >= 8) {
            return;
         }
         // CanaryMod: sidewards flow.
         if(var13[0]) {
            Block blockTo = new Block(world, 0, var2 - 1, var3, var4);
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.FLOW, blockFrom, blockTo))
               this.g(var1, var2 - 1, var3, var4, var10);
         }

         if(var13[1]) {
            Block blockTo = new Block(world, 0, var2 + 1, var3, var4);
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.FLOW, blockFrom, blockTo))
               this.g(var1, var2 + 1, var3, var4, var10);
         }

         if(var13[2]) {
            Block blockTo = new Block(world, 0, var2, var3, var4 - 1);
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.FLOW, blockFrom, blockTo))
               this.g(var1, var2, var3, var4 - 1, var10);
         }

         if(var13[3]) {
            Block blockTo = new Block(world, 0, var2, var3, var4 + 1);
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.FLOW, blockFrom, blockTo))
               this.g(var1, var2, var3, var4 + 1, var10);
         }
      }

   }

   private void g(OWorld var1, int var2, int var3, int var4, int var5) {
      if(this.l(var1, var2, var3, var4)) {
         int var6 = var1.a(var2, var3, var4);
         if(var6 > 0) {
            if(this.bA == OMaterial.h) {
               this.h(var1, var2, var3, var4);
            } else {
               OBlock.m[var6].b_(var1, var2, var3, var4, var1.b(var2, var3, var4));
            }
         }

         var1.b(var2, var3, var4, this.bn, var5);
      }

   }

   private int b(OWorld var1, int var2, int var3, int var4, int var5, int var6) {
      int var7 = 1000;

      for(int var8 = 0; var8 < 4; ++var8) {
         if((var8 != 0 || var6 != 1) && (var8 != 1 || var6 != 0) && (var8 != 2 || var6 != 3) && (var8 != 3 || var6 != 2)) {
            int var9 = var2;
            int var11 = var4;
            if(var8 == 0) {
               var9 = var2 - 1;
            }

            if(var8 == 1) {
               ++var9;
            }

            if(var8 == 2) {
               var11 = var4 - 1;
            }

            if(var8 == 3) {
               ++var11;
            }

            if(!this.k(var1, var9, var3, var11) && (var1.c(var9, var3, var11) != this.bA || var1.b(var9, var3, var11) != 0)) {
               if(!this.k(var1, var9, var3 - 1, var11)) {
                  return var5;
               }

               if(var5 < 4) {
                  int var12 = this.b(var1, var9, var3, var11, var5 + 1, var8);
                  if(var12 < var7) {
                     var7 = var12;
                  }
               }
            }
         }
      }

      return var7;
   }

   private boolean[] j(OWorld var1, int var2, int var3, int var4) {
      int var5;
      int var6;
      for(var5 = 0; var5 < 4; ++var5) {
         this.c[var5] = 1000;
         var6 = var2;
         int var8 = var4;
         if(var5 == 0) {
            var6 = var2 - 1;
         }

         if(var5 == 1) {
            ++var6;
         }

         if(var5 == 2) {
            var8 = var4 - 1;
         }

         if(var5 == 3) {
            ++var8;
         }

         if(!this.k(var1, var6, var3, var8) && (var1.c(var6, var3, var8) != this.bA || var1.b(var6, var3, var8) != 0)) {
            if(!this.k(var1, var6, var3 - 1, var8)) {
               this.c[var5] = 0;
            } else {
               this.c[var5] = this.b(var1, var6, var3, var8, 1, var5);
            }
         }
      }

      var5 = this.c[0];

      for(var6 = 1; var6 < 4; ++var6) {
         if(this.c[var6] < var5) {
            var5 = this.c[var6];
         }
      }

      for(var6 = 0; var6 < 4; ++var6) {
         this.b[var6] = this.c[var6] == var5;
      }

      return this.b;
   }

   private boolean k(OWorld var1, int var2, int var3, int var4) {
      int var5 = var1.a(var2, var3, var4);
      if(var5 != OBlock.aF.bn && var5 != OBlock.aM.bn && var5 != OBlock.aE.bn && var5 != OBlock.aG.bn && var5 != OBlock.aY.bn) {
         if(var5 == 0) {
            return false;
         } else {
            OMaterial var6 = OBlock.m[var5].bA;
            return var6.c();
         }
      } else {
         return true;
      }
   }

   protected int f(OWorld var1, int var2, int var3, int var4, int var5) {
      int var6 = this.g(var1, var2, var3, var4);
      if(var6 < 0) {
         return var5;
      } else {
         if(var6 == 0) {
            ++this.a;
         }

         if(var6 >= 8) {
            var6 = 0;
         }

         return var5 >= 0 && var6 >= var5?var5:var6;
      }
   }

   private boolean l(OWorld var1, int var2, int var3, int var4) {
      // CanaryMod: See if this liquid can destroy this block.
      Block block = new Block(var1.world, var1.a(var2, var3, var4), var2, var3, var4);
      PluginLoader.HookResult ret = (PluginLoader.HookResult) etc.getLoader().callHook(PluginLoader.Hook.LIQUID_DESTROY, bn, block);
      if (ret == PluginLoader.HookResult.PREVENT_ACTION)
         return false;
      else if (ret == PluginLoader.HookResult.ALLOW_ACTION)
         return true;

      OMaterial var5 = var1.c(var2, var3, var4);
      return var5 == this.bA?false:(var5 == OMaterial.h?false:!this.k(var1, var2, var3, var4));
   }

   public void e(OWorld var1, int var2, int var3, int var4) {
      super.e(var1, var2, var3, var4);
      if(var1.a(var2, var3, var4) == this.bn) {
         var1.c(var2, var3, var4, this.bn, this.c());
      }

   }
}
