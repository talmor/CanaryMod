
import java.util.Random;

public class OBlockSand extends OBlock {

   public static boolean a = false;


   public OBlockSand(int var1, int var2) {
      super(var1, var2, OMaterial.n);
   }

   public void c(OWorld var1, int var2, int var3, int var4) {
      // CanaryMod: Physics
      if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PHYSICS, new Block(var1.world, bn, var2, var3, var4), true))
         var1.c(var2, var3, var4, this.bn, this.c());
   }

   public void b(OWorld var1, int var2, int var3, int var4, int var5) {
      // CanaryMod: Physics
      if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PHYSICS, new Block(var1.world, bn, var2, var3, var4), true))
         var1.c(var2, var3, var4, this.bn, this.c());
   }

   public void a(OWorld var1, int var2, int var3, int var4, Random var5) {
      this.g(var1, var2, var3, var4);
   }

   private void g(OWorld var1, int var2, int var3, int var4) {
      if(c_(var1, var2, var3 - 1, var4) && var3 >= 0) {
         byte var8 = 32;
         if(!a && var1.a(var2 - var8, var3 - var8, var4 - var8, var2 + var8, var3 + var8, var4 + var8)) {
            OEntityFallingSand var9 = new OEntityFallingSand(var1, (double)((float)var2 + 0.5F), (double)((float)var3 + 0.5F), (double)((float)var4 + 0.5F), this.bn);
            var1.b((OEntity)var9);
         } else {
            var1.e(var2, var3, var4, 0);

            while(c_(var1, var2, var3 - 1, var4) && var3 > 0) {
               --var3;
            }

            if(var3 > 0) {
               var1.e(var2, var3, var4, this.bn);
            }
         }
      }

   }

   public int c() {
      return 3;
   }

   public static boolean c_(OWorld var0, int var1, int var2, int var3) {
      int var4 = var0.a(var1, var2, var3);
      if(var4 == 0) {
         return true;
      } else if(var4 == OBlock.as.bn) {
         return true;
      } else {
         OMaterial var5 = OBlock.m[var4].bA;
         return var5 == OMaterial.g?true:var5 == OMaterial.h;
      }
   }

}
