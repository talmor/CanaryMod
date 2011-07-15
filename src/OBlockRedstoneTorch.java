
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OBlockRedstoneTorch extends OBlockTorch {

   private boolean a = false;
   private static List b = new ArrayList();


   public int a(int var1, int var2) {
      return var1 == 1?OBlock.aw.a(var1, var2):super.a(var1, var2);
   }

   private boolean a(OWorld var1, int var2, int var3, int var4, boolean var5) {
      if(var5) {
         b.add(new ORedstoneUpdateInfo(var2, var3, var4, var1.m()));
      }

      int var6 = 0;

      for(int var7 = 0; var7 < b.size(); ++var7) {
         ORedstoneUpdateInfo var8 = (ORedstoneUpdateInfo)b.get(var7);
         if(var8.a == var2 && var8.b == var3 && var8.c == var4) {
            ++var6;
            if(var6 >= 8) {
               return true;
            }
         }
      }

      return false;
   }

   protected OBlockRedstoneTorch(int var1, int var2, boolean var3) {
      super(var1, var2);
      this.a = var3;
      this.a(true);
   }

   public int c() {
      return 2;
   }

   public void c(OWorld var1, int var2, int var3, int var4) {
      if(var1.c(var2, var3, var4) == 0) {
         super.c(var1, var2, var3, var4);
      }

      if(this.a) {
         var1.h(var2, var3 - 1, var4, this.bn);
         var1.h(var2, var3 + 1, var4, this.bn);
         var1.h(var2 - 1, var3, var4, this.bn);
         var1.h(var2 + 1, var3, var4, this.bn);
         var1.h(var2, var3, var4 - 1, this.bn);
         var1.h(var2, var3, var4 + 1, this.bn);
      }

   }

   public void b(OWorld var1, int var2, int var3, int var4) {
      if(this.a) {
         var1.h(var2, var3 - 1, var4, this.bn);
         var1.h(var2, var3 + 1, var4, this.bn);
         var1.h(var2 - 1, var3, var4, this.bn);
         var1.h(var2 + 1, var3, var4, this.bn);
         var1.h(var2, var3, var4 - 1, this.bn);
         var1.h(var2, var3, var4 + 1, this.bn);
      }

   }

   public boolean a(OIBlockAccess var1, int var2, int var3, int var4, int var5) {
      if(!this.a) {
         return false;
      } else {
         int var6 = var1.c(var2, var3, var4);
         return var6 == 5 && var5 == 1?false:(var6 == 3 && var5 == 3?false:(var6 == 4 && var5 == 2?false:(var6 == 1 && var5 == 5?false:var6 != 2 || var5 != 4)));
      }
   }

   private boolean g(OWorld var1, int var2, int var3, int var4) {
      int var5 = var1.c(var2, var3, var4);
      return var5 == 5 && var1.j(var2, var3 - 1, var4, 0)?true:(var5 == 3 && var1.j(var2, var3, var4 - 1, 2)?true:(var5 == 4 && var1.j(var2, var3, var4 + 1, 3)?true:(var5 == 1 && var1.j(var2 - 1, var3, var4, 4)?true:var5 == 2 && var1.j(var2 + 1, var3, var4, 5))));
   }

   //Skye's attempt at making sure redstone torches stay lit forever.
   private int counter = 0;
   
   public void a(OWorld var1, int var2, int var3, int var4, Random var5) {
      boolean var6 = this.g(var1, var2, var3, var4);

      while(b.size() > 0 && var1.m() - ((ORedstoneUpdateInfo)b.get(0)).d > 100L) {
         b.remove(0);
      }

      if(this.a) {
         if(var6) {
            var1.b(var2, var3, var4, OBlock.aQ.bn, var1.c(var2, var3, var4));
            // CanaryMod: Allow redstone torches to provide power
            int current = (Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Block(var1.world, bn, var2, var3, var4), 1, 0);
            if (current == 0)
               if(this.a(var1, var2, var3, var4, true)) {
                  var1.a((double)((float)var2 + 0.5F), (double)((float)var3 + 0.5F), (double)((float)var4 + 0.5F), "random.fizz", 0.5F, 2.6F + (var1.r.nextFloat() - var1.r.nextFloat()) * 0.8F);

                  for(int var7 = 0; var7 < 5; ++var7) {
                     double var8 = (double)var2 + var5.nextDouble() * 0.6D + 0.2D;
                     double var10 = (double)var3 + var5.nextDouble() * 0.6D + 0.2D;
                     double var12 = (double)var4 + var5.nextDouble() * 0.6D + 0.2D;
                     var1.a("smoke", var8, var10, var12, 0.0D, 0.0D, 0.0D);
                  }
               }
         }
      } else if(!var6 && !this.a(var1, var2, var3, var4, false)) {
         // CanaryMod: Allow redstone torches to provide power
         int current = (Integer) etc.getLoader().callHook(PluginLoader.Hook.REDSTONE_CHANGE, new Block(var1.world, bn, var2, var3, var4), 0, 1);
         if (current > 0)
            var1.b(var2, var3, var4, OBlock.aR.bn, var1.c(var2, var3, var4));
      } else {counter++;
      if (counter < 5) a(var1, var2, var3, var4, var5);
      else counter = 0;
      }

   }

   public void b(OWorld var1, int var2, int var3, int var4, int var5) {
      super.b(var1, var2, var3, var4, var5);
      var1.c(var2, var3, var4, this.bn, this.c());
   }

   public boolean d(OWorld var1, int var2, int var3, int var4, int var5) {
      // CanaryMod: forced casting to OIBlockAccess
      return var5 == 0?this.a((OIBlockAccess) var1, var2, var3, var4, var5):false;
   }

   public int a(int var1, Random var2) {
      return OBlock.aR.bn;
   }

   public boolean d() {
      return true;
   }

}
