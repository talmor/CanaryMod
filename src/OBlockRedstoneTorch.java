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
         b.add(new ORedstoneUpdateInfo(var2, var3, var4, var1.l()));
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

   public void a(OWorld var1, int var2, int var3, int var4) {
      if(var1.c(var2, var3, var4) == 0) {
         super.a(var1, var2, var3, var4);
      }

      if(this.a) {
         var1.h(var2, var3 - 1, var4, this.bA);
         var1.h(var2, var3 + 1, var4, this.bA);
         var1.h(var2 - 1, var3, var4, this.bA);
         var1.h(var2 + 1, var3, var4, this.bA);
         var1.h(var2, var3, var4 - 1, this.bA);
         var1.h(var2, var3, var4 + 1, this.bA);
      }

   }

   public void d(OWorld var1, int var2, int var3, int var4) {
      if(this.a) {
         var1.h(var2, var3 - 1, var4, this.bA);
         var1.h(var2, var3 + 1, var4, this.bA);
         var1.h(var2 - 1, var3, var4, this.bA);
         var1.h(var2 + 1, var3, var4, this.bA);
         var1.h(var2, var3, var4 - 1, this.bA);
         var1.h(var2, var3, var4 + 1, this.bA);
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

   public void a(OWorld var1, int var2, int var3, int var4, Random var5) {
      boolean var6 = this.g(var1, var2, var3, var4);

      while(b.size() > 0 && var1.l() - ((ORedstoneUpdateInfo)b.get(0)).d > 100L) {
         b.remove(0);
      }

      if(this.a) {
         if(var6) {
            var1.b(var2, var3, var4, OBlock.aQ.bA, var1.c(var2, var3, var4));
            if(this.a(var1, var2, var3, var4, true)) {
               var1.a((double)((float)var2 + 0.5F), (double)((float)var3 + 0.5F), (double)((float)var4 + 0.5F), "random.fizz", 0.5F, 2.6F + (var1.w.nextFloat() - var1.w.nextFloat()) * 0.8F);

               for(int var7 = 0; var7 < 5; ++var7) {
                  double var8 = (double)var2 + var5.nextDouble() * 0.6D + 0.2D;
                  double var10 = (double)var3 + var5.nextDouble() * 0.6D + 0.2D;
                  double var12 = (double)var4 + var5.nextDouble() * 0.6D + 0.2D;
                  var1.a("smoke", var8, var10, var12, 0.0D, 0.0D, 0.0D);
               }
            }
         }
      } else if(!var6 && !this.a(var1, var2, var3, var4, false)) {
         var1.b(var2, var3, var4, OBlock.aR.bA, var1.c(var2, var3, var4));
      }

   }

   public void a(OWorld var1, int var2, int var3, int var4, int var5) {
      super.a(var1, var2, var3, var4, var5);
      var1.c(var2, var3, var4, this.bA, this.c());
   }

   public boolean d(OWorld var1, int var2, int var3, int var4, int var5) {
      return var5 == 0?this.a((OIBlockAccess)var1, var2, var3, var4, var5):false;
   }

   public int a(int var1, Random var2) {
      return OBlock.aR.bA;
   }

   public boolean d() {
      return true;
   }

}
