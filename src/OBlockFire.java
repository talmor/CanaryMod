
import java.util.Random;

public class OBlockFire extends OBlock {

   private int[] a = new int[256];
   private int[] b = new int[256];


   protected OBlockFire(int var1, int var2) {
      super(var1, var2, OMaterial.m);
      this.a(true);
   }

   public void f() {
      this.a(OBlock.y.bn, 5, 20);
      this.a(OBlock.ba.bn, 5, 20);
      this.a(OBlock.au.bn, 5, 20);
      this.a(OBlock.K.bn, 5, 5);
      this.a(OBlock.L.bn, 30, 60);
      this.a(OBlock.ao.bn, 30, 20);
      this.a(OBlock.an.bn, 15, 100);
      this.a(OBlock.Y.bn, 60, 100);
      this.a(OBlock.ac.bn, 30, 60);
   }

   private void a(int var1, int var2, int var3) {
      this.a[var1] = var2;
      this.b[var1] = var3;
   }

   public OAxisAlignedBB d(OWorld var1, int var2, int var3, int var4) {
      return null;
   }

   public boolean a() {
      return false;
   }

   public boolean b() {
      return false;
   }

   public int a(Random var1) {
      return 0;
   }

   public int c() {
      return 40;
   }

   public void a(OWorld var1, int var2, int var3, int var4, Random var5) {
      boolean var6 = var1.a(var2, var3 - 1, var4) == OBlock.bc.bn;
      if(!this.a(var1, var2, var3, var4)) {
         var1.e(var2, var3, var4, 0);
      }

      if(!var6 && var1.v() && (var1.s(var2, var3, var4) || var1.s(var2 - 1, var3, var4) || var1.s(var2 + 1, var3, var4) || var1.s(var2, var3, var4 - 1) || var1.s(var2, var3, var4 + 1))) {
         var1.e(var2, var3, var4, 0);
      } else {
         int var7 = var1.b(var2, var3, var4);
         if(var7 < 15) {
            var1.d(var2, var3, var4, var7 + var5.nextInt(3) / 2);
         }

         var1.c(var2, var3, var4, this.bn, this.c());
         if(!var6 && !this.g(var1, var2, var3, var4)) {
            if(!var1.d(var2, var3 - 1, var4) || var7 > 3) {
               var1.e(var2, var3, var4, 0);
            }

            // CanaryMod: forced casting to OIBlockAccess
         } else if(!var6 && !this.b((OIBlockAccess) var1, var2, var3 - 1, var4) && var7 == 15 && var5.nextInt(4) == 0) {
            var1.e(var2, var3, var4, 0);
         } else {
            this.a(var1, var2 + 1, var3, var4, 300, var5, var7);
            this.a(var1, var2 - 1, var3, var4, 300, var5, var7);
            this.a(var1, var2, var3 - 1, var4, 250, var5, var7);
            this.a(var1, var2, var3 + 1, var4, 250, var5, var7);
            this.a(var1, var2, var3, var4 - 1, 300, var5, var7);
            this.a(var1, var2, var3, var4 + 1, 300, var5, var7);

            for(int var8 = var2 - 1; var8 <= var2 + 1; ++var8) {
               for(int var9 = var4 - 1; var9 <= var4 + 1; ++var9) {
                  for(int var10 = var3 - 1; var10 <= var3 + 4; ++var10) {
                     if(var8 != var2 || var10 != var3 || var9 != var4) {
                        int var11 = 100;
                        if(var10 > var3 + 1) {
                           var11 += (var10 - (var3 + 1)) * 100;
                        }

                        int var12 = this.h(var1, var8, var10, var9);
                        if(var12 > 0) {
                           int var13 = (var12 + 40) / (var7 + 30);
                           if(var13 > 0 && var5.nextInt(var11) <= var13 && (!var1.v() || !var1.s(var8, var10, var9)) && !var1.s(var8 - 1, var10, var4) && !var1.s(var8 + 1, var10, var9) && !var1.s(var8, var10, var9 - 1) && !var1.s(var8, var10, var9 + 1)) {
                              int var14 = var7 + var5.nextInt(5) / 4;
                              if(var14 > 15) {
                                 var14 = 15;
                              }

                              // CanaryMod: dynamic spreading of fire.{
                              // avg call amount per placed block of fire ~ 4
                              Block block = new Block(var1.world, var1.a(var8, var10, var9), var8, var10, var9);
                              block.setStatus(3);
                              if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.IGNITE, block, null))
                                 var1.b(var8, var10, var9, this.bn, var14);
                           }
                        }
                     }
                  }
               }
            }

         }
      }
   }

   private void a(OWorld var1, int var2, int var3, int var4, int var5, Random var6, int var7) {
      int var8 = this.b[var1.a(var2, var3, var4)];
      if(var6.nextInt(var5) < var8) {
         boolean var9 = var1.a(var2, var3, var4) == OBlock.an.bn;
         if(var6.nextInt(var7 + 10) < 5 && !var1.s(var2, var3, var4)) {
            int var10 = var7 + var6.nextInt(5) / 4;
            if(var10 > 15) {
               var10 = 15;
            }

            // CanaryMod: VERY SLOW dynamic spreading of fire.
            Block block = new Block(var1.world, var1.a(var2, var3, var4), var2, var3, var4);
            block.setStatus(3);
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.IGNITE, block, null))
               var1.b(var2, var3, var4, this.bn, var10);
         } else {
            // CanaryMod: fire destroying a block.
            Block block = new Block(var1.world, var1.a(var2, var3, var4), var2, var3, var4);
            block.setStatus(4);
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.IGNITE, block, null))
               var1.e(var2, var3, var4, 0);
         }

         if(var9) {
            OBlock.an.b(var1, var2, var3, var4, 0);
         }
      }

   }

   private boolean g(OWorld var1, int var2, int var3, int var4) {
      // CanaryMod: cast down to fix decompiler error (6 times)
      return this.b((OIBlockAccess) var1, var2 + 1, var3, var4)?true:(this.b((OIBlockAccess) var1, var2 - 1, var3, var4)?true:(this.b((OIBlockAccess) var1, var2, var3 - 1, var4)?true:(this.b((OIBlockAccess) var1, var2, var3 + 1, var4)?true:(this.b((OIBlockAccess) var1, var2, var3, var4 - 1)?true:this.b((OIBlockAccess) var1, var2, var3, var4 + 1)))));
   }

   private int h(OWorld var1, int var2, int var3, int var4) {
      byte var5 = 0;
      if(!var1.e(var2, var3, var4)) {
         return 0;
      } else {
         int var6 = this.g(var1, var2 + 1, var3, var4, var5);
         var6 = this.g(var1, var2 - 1, var3, var4, var6);
         var6 = this.g(var1, var2, var3 - 1, var4, var6);
         var6 = this.g(var1, var2, var3 + 1, var4, var6);
         var6 = this.g(var1, var2, var3, var4 - 1, var6);
         var6 = this.g(var1, var2, var3, var4 + 1, var6);
         return var6;
      }
   }

   public boolean m_() {
      return false;
   }

   public boolean b(OIBlockAccess var1, int var2, int var3, int var4) {
      return this.a[var1.a(var2, var3, var4)] > 0;
   }

   public int g(OWorld var1, int var2, int var3, int var4, int var5) {
      int var6 = this.a[var1.a(var2, var3, var4)];
      return var6 > var5?var6:var5;
   }

   public boolean a(OWorld var1, int var2, int var3, int var4) {
      return var1.d(var2, var3 - 1, var4) || this.g(var1, var2, var3, var4);
   }

   public void a(OWorld var1, int var2, int var3, int var4, int var5) {
      if(!var1.d(var2, var3 - 1, var4) && !this.g(var1, var2, var3, var4)) {
         var1.e(var2, var3, var4, 0);
      }
   }

   public void e(OWorld var1, int var2, int var3, int var4) {
      if(var1.a(var2, var3 - 1, var4) != OBlock.aq.bn || !OBlock.bf.a_(var1, var2, var3, var4)) {
         if(!var1.d(var2, var3 - 1, var4) && !this.g(var1, var2, var3, var4)) {
            var1.e(var2, var3, var4, 0);
         } else {
            var1.c(var2, var3, var4, this.bn, this.c());
         }
      }
   }
}
