import java.util.List;
import java.util.Random;

public class OBlockPressurePlate extends OBlock {

   private OEnumMobType a;


   protected OBlockPressurePlate(int var1, int var2, OEnumMobType var3, OMaterial var4) {
      super(var1, var2, var4);
      this.a = var3;
      this.a(true);
      float var5 = 0.0625F;
      this.a(var5, 0.0F, var5, 1.0F - var5, 0.03125F, 1.0F - var5);
   }

   public int c() {
      return 20;
   }

   public OAxisAlignedBB e(OWorld var1, int var2, int var3, int var4) {
      return null;
   }

   public boolean a() {
      return false;
   }

   public boolean b() {
      return false;
   }

   public boolean c(OWorld var1, int var2, int var3, int var4) {
      return var1.e(var2, var3 - 1, var4) || var1.a(var2, var3 - 1, var4) == OBlock.ba.bA;
   }

   public void a(OWorld var1, int var2, int var3, int var4) {}

   public void a(OWorld var1, int var2, int var3, int var4, int var5) {
      boolean var6 = false;
      if(!var1.e(var2, var3 - 1, var4) && var1.a(var2, var3 - 1, var4) != OBlock.ba.bA) {
         var6 = true;
      }

      if(var6) {
         this.g(var1, var2, var3, var4, var1.c(var2, var3, var4));
         var1.e(var2, var3, var4, 0);
      }

   }

   public void a(OWorld var1, int var2, int var3, int var4, Random var5) {
      if(!var1.I) {
         if(var1.c(var2, var3, var4) != 0) {
            this.g(var1, var2, var3, var4);
         }
      }
   }

   public void a(OWorld var1, int var2, int var3, int var4, OEntity var5) {
      if(!var1.I) {
         if(var1.c(var2, var3, var4) != 1) {
            this.g(var1, var2, var3, var4);
         }
      }
   }

   private void g(OWorld var1, int var2, int var3, int var4) {
      boolean var5 = var1.c(var2, var3, var4) == 1;
      boolean var6 = false;
      float var7 = 0.125F;
      List var8 = null;
      if(this.a == OEnumMobType.a) {
         var8 = var1.b((OEntity)null, OAxisAlignedBB.b((double)((float)var2 + var7), (double)var3, (double)((float)var4 + var7), (double)((float)(var2 + 1) - var7), (double)var3 + 0.25D, (double)((float)(var4 + 1) - var7)));
      }

      if(this.a == OEnumMobType.b) {
         var8 = var1.a(OEntityLiving.class, OAxisAlignedBB.b((double)((float)var2 + var7), (double)var3, (double)((float)var4 + var7), (double)((float)(var2 + 1) - var7), (double)var3 + 0.25D, (double)((float)(var4 + 1) - var7)));
      }

      if(this.a == OEnumMobType.c) {
         var8 = var1.a(OEntityPlayer.class, OAxisAlignedBB.b((double)((float)var2 + var7), (double)var3, (double)((float)var4 + var7), (double)((float)(var2 + 1) - var7), (double)var3 + 0.25D, (double)((float)(var4 + 1) - var7)));
      }

      if(var8.size() > 0) {
         var6 = true;
      }

      if(var6 && !var5) {
         var1.c(var2, var3, var4, 1);
         var1.h(var2, var3, var4, this.bA);
         var1.h(var2, var3 - 1, var4, this.bA);
         var1.b(var2, var3, var4, var2, var3, var4);
         var1.a((double)var2 + 0.5D, (double)var3 + 0.1D, (double)var4 + 0.5D, "random.click", 0.3F, 0.6F);
      }

      if(!var6 && var5) {
         var1.c(var2, var3, var4, 0);
         var1.h(var2, var3, var4, this.bA);
         var1.h(var2, var3 - 1, var4, this.bA);
         var1.b(var2, var3, var4, var2, var3, var4);
         var1.a((double)var2 + 0.5D, (double)var3 + 0.1D, (double)var4 + 0.5D, "random.click", 0.3F, 0.5F);
      }

      if(var6) {
         var1.c(var2, var3, var4, this.bA, this.c());
      }

   }

   public void d(OWorld var1, int var2, int var3, int var4) {
      int var5 = var1.c(var2, var3, var4);
      if(var5 > 0) {
         var1.h(var2, var3, var4, this.bA);
         var1.h(var2, var3 - 1, var4, this.bA);
      }

      super.d(var1, var2, var3, var4);
   }

   public void a(OIBlockAccess var1, int var2, int var3, int var4) {
      boolean var5 = var1.c(var2, var3, var4) == 1;
      float var6 = 0.0625F;
      if(var5) {
         this.a(var6, 0.0F, var6, 1.0F - var6, 0.03125F, 1.0F - var6);
      } else {
         this.a(var6, 0.0F, var6, 1.0F - var6, 0.0625F, 1.0F - var6);
      }

   }

   public boolean a(OIBlockAccess var1, int var2, int var3, int var4, int var5) {
      return var1.c(var2, var3, var4) > 0;
   }

   public boolean d(OWorld var1, int var2, int var3, int var4, int var5) {
      return var1.c(var2, var3, var4) == 0?false:var5 == 1;
   }

   public boolean d() {
      return true;
   }

   public int e() {
      return 1;
   }
}
