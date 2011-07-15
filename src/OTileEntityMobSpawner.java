

public class OTileEntityMobSpawner extends OTileEntity {

   public int a = -1;
   // CanaryMod: make public to allow reading/writing
   public String i = "Pig";
   public double b;
   public double c = 0.0D;


   public OTileEntityMobSpawner() {
      this.a = 20;
   }

   public void a(String var1) {
      this.i = var1;
   }

   public boolean a() {
      return this.d.a((double)this.e + 0.5D, (double)this.f + 0.5D, (double)this.g + 0.5D, 16.0D) != null;
   }

   public void g_() {
      this.c = this.b;
      if(this.a()) {
         double var1 = (double)((float)this.e + this.d.r.nextFloat());
         double var3 = (double)((float)this.f + this.d.r.nextFloat());
         double var5 = (double)((float)this.g + this.d.r.nextFloat());
         this.d.a("smoke", var1, var3, var5, 0.0D, 0.0D, 0.0D);
         this.d.a("flame", var1, var3, var5, 0.0D, 0.0D, 0.0D);

         for(this.b += (double)(1000.0F / ((float)this.a + 200.0F)); this.b > 360.0D; this.c -= 360.0D) {
            this.b -= 360.0D;
         }

         if(!this.d.B) {
            if(this.a == -1) {
               this.c();
            }

            if(this.a > 0) {
               --this.a;
               return;
            }

            byte var7 = 4;

            for(int var8 = 0; var8 < var7; ++var8) {
               OEntityLiving var9 = (OEntityLiving)((OEntityLiving)OEntityList.a(this.i, this.d));
               if(var9 == null) {
                  return;
               }

               int var10 = this.d.a(var9.getClass(), OAxisAlignedBB.b((double)this.e, (double)this.f, (double)this.g, (double)(this.e + 1), (double)(this.f + 1), (double)(this.g + 1)).b(8.0D, 4.0D, 8.0D)).size();
               if(var10 >= 6) {
                  this.c();
                  return;
               }

               if(var9 != null) {
                  double var11 = (double)this.e + (this.d.r.nextDouble() - this.d.r.nextDouble()) * 4.0D;
                  double var13 = (double)(this.f + this.d.r.nextInt(3) - 1);
                  double var15 = (double)this.g + (this.d.r.nextDouble() - this.d.r.nextDouble()) * 4.0D;
                  var9.c(var11, var13, var15, this.d.r.nextFloat() * 360.0F, 0.0F);
                  if(var9.d()) {
                     this.d.b((OEntity)var9);

                     for(int var17 = 0; var17 < 20; ++var17) {
                        var1 = (double)this.e + 0.5D + ((double)this.d.r.nextFloat() - 0.5D) * 2.0D;
                        var3 = (double)this.f + 0.5D + ((double)this.d.r.nextFloat() - 0.5D) * 2.0D;
                        var5 = (double)this.g + 0.5D + ((double)this.d.r.nextFloat() - 0.5D) * 2.0D;
                        this.d.a("smoke", var1, var3, var5, 0.0D, 0.0D, 0.0D);
                        this.d.a("flame", var1, var3, var5, 0.0D, 0.0D, 0.0D);
                     }

                     var9.S();
                     this.c();
                  }
               }
            }
         }

         super.g_();
      }
   }

   private void c() {
      this.a = 200 + this.d.r.nextInt(600);
   }

   public void a(ONBTTagCompound var1) {
      super.a(var1);
      this.i = var1.i("EntityId");
      this.a = var1.d("Delay");
   }

   public void b(ONBTTagCompound var1) {
      super.b(var1);
      var1.a("EntityId", this.i);
      var1.a("Delay", (short)this.a);
   }
}
