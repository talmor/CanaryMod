
public class OTileEntityMobSpawner extends OTileEntity {

   public int a = -1;
   public String d = "Pig";
   public double b;
   public double c = 0.0D;


   public OTileEntityMobSpawner() {
      super();
      this.a = 20;
   }

   public void a(String var1) {
      this.d = var1;
   }

   public boolean a() {
      return this.i.a((double)this.j + 0.5D, (double)this.k + 0.5D, (double)this.l + 0.5D, 16.0D) != null;
   }

   public void h_() {
      this.c = this.b;
      if(this.a()) {
         double var1 = (double)((float)this.j + this.i.w.nextFloat());
         double var3 = (double)((float)this.k + this.i.w.nextFloat());
         double var5 = (double)((float)this.l + this.i.w.nextFloat());
         this.i.a("smoke", var1, var3, var5, 0.0D, 0.0D, 0.0D);
         this.i.a("flame", var1, var3, var5, 0.0D, 0.0D, 0.0D);

         for(this.b += (double)(1000.0F / ((float)this.a + 200.0F)); this.b > 360.0D; this.c -= 360.0D) {
            this.b -= 360.0D;
         }

         if(!this.i.I) {
            if(this.a == -1) {
               this.c();
            }

            if(this.a > 0) {
               --this.a;
               return;
            }

            byte var7 = 4;

            for(int var8 = 0; var8 < var7; ++var8) {
               OEntityLiving var9 = (OEntityLiving)((OEntityLiving)OEntityList.a(this.d, this.i));
               if(var9 == null) {
                  return;
               }

               int var10 = this.i.a(var9.getClass(), OAxisAlignedBB.b((double)this.j, (double)this.k, (double)this.l, (double)(this.j + 1), (double)(this.k + 1), (double)(this.l + 1)).b(8.0D, 4.0D, 8.0D)).size();
               if(var10 >= 6) {
                  this.c();
                  return;
               }

               if(var9 != null) {
                  double var11 = (double)this.j + (this.i.w.nextDouble() - this.i.w.nextDouble()) * 4.0D;
                  double var13 = (double)(this.k + this.i.w.nextInt(3) - 1);
                  double var15 = (double)this.l + (this.i.w.nextDouble() - this.i.w.nextDouble()) * 4.0D;
                  var9.c(var11, var13, var15, this.i.w.nextFloat() * 360.0F, 0.0F);
                  if(var9.d()) {
                     this.i.b((OEntity)var9);

                     for(int var17 = 0; var17 < 20; ++var17) {
                        var1 = (double)this.j + 0.5D + ((double)this.i.w.nextFloat() - 0.5D) * 2.0D;
                        var3 = (double)this.k + 0.5D + ((double)this.i.w.nextFloat() - 0.5D) * 2.0D;
                        var5 = (double)this.l + 0.5D + ((double)this.i.w.nextFloat() - 0.5D) * 2.0D;
                        this.i.a("smoke", var1, var3, var5, 0.0D, 0.0D, 0.0D);
                        this.i.a("flame", var1, var3, var5, 0.0D, 0.0D, 0.0D);
                     }

                     var9.ab();
                     this.c();
                  }
               }
            }
         }

         super.h_();
      }
   }

   private void c() {
      this.a = 200 + this.i.w.nextInt(600);
   }

   public void a(ONBTTagCompound var1) {
      super.a(var1);
      this.d = var1.i("EntityId");
      this.a = var1.d("Delay");
   }

   public void b(ONBTTagCompound var1) {
      super.b(var1);
      var1.a("EntityId", this.d);
      var1.a("Delay", (short)this.a);
   }
}
