import java.util.Random;

public class OBlockDispenser extends OBlockContainer {

   private Random a = new Random();


   protected OBlockDispenser(int i) {
      super(i, OMaterial.e);
      this.bz = 45;
   }

   public int c() {
      return 4;
   }

   public int a(int i, Random random) {
      return OBlock.Q.bA;
   }

   public void a(OWorld world, int i, int j, int k) {
      super.a(world, i, j, k);
      this.g(world, i, j, k);
   }

   private void g(OWorld world, int i, int j, int k) {
      if(!world.I) {
         int l = world.a(i, j, k - 1);
         int i1 = world.a(i, j, k + 1);
         int j1 = world.a(i - 1, j, k);
         int k1 = world.a(i + 1, j, k);
         byte byte0 = 3;
         if(OBlock.o[l] && !OBlock.o[i1]) {
            byte0 = 3;
         }

         if(OBlock.o[i1] && !OBlock.o[l]) {
            byte0 = 2;
         }

         if(OBlock.o[j1] && !OBlock.o[k1]) {
            byte0 = 5;
         }

         if(OBlock.o[k1] && !OBlock.o[j1]) {
            byte0 = 4;
         }

         world.c(i, j, k, byte0);
      }
   }

   public int a(int i) {
      return i == 1?this.bz + 17:(i == 0?this.bz + 17:(i == 3?this.bz + 1:this.bz));
   }

   public boolean a(OWorld world, int i, int j, int k, OEntityPlayer entityplayer) {
      if(world.I) {
         return true;
      } else {
         OTileEntityDispenser tileentitydispenser = (OTileEntityDispenser)world.b(i, j, k);
         if(tileentitydispenser != null) {
            entityplayer.a(tileentitydispenser);
         }

         return true;
      }
   }

   private void b(OWorld world, int i, int j, int k, Random random) {
      int l = world.c(i, j, k);
      byte i1 = 0;
      byte j1 = 0;
      if(l == 3) {
         j1 = 1;
      } else if(l == 2) {
         j1 = -1;
      } else if(l == 5) {
         i1 = 1;
      } else {
         i1 = -1;
      }

      OTileEntityDispenser tileentitydispenser = (OTileEntityDispenser)world.b(i, j, k);
      if(tileentitydispenser != null) {
         OItemStack itemstack = tileentitydispenser.b();
         double d = (double)i + (double)i1 * 0.6D + 0.5D;
         double d1 = (double)j + 0.5D;
         double d2 = (double)k + (double)j1 * 0.6D + 0.5D;
         if(itemstack == null) {
            world.e(1001, i, j, k, 0);
         } else {
            boolean flag = ModLoader.DispenseEntity(world, d, d1, d2, i1, j1, itemstack);
            if(!flag) {
               if(itemstack.c == OItem.j.bo) {
                  OEntityArrow entityitem = new OEntityArrow(world, d, d1, d2);
                  entityitem.a((double)i1, 0.10000000149011612D, (double)j1, 1.1F, 6.0F);
                  entityitem.a = true;
                  world.b(entityitem);
                  world.e(1002, i, j, k, 0);
               } else if(itemstack.c == OItem.aN.bo) {
                  OEntityEgg entityitem3 = new OEntityEgg(world, d, d1, d2);
                  entityitem3.a((double)i1, 0.10000000149011612D, (double)j1, 1.1F, 6.0F);
                  world.b(entityitem3);
                  world.e(1002, i, j, k, 0);
               } else if(itemstack.c == OItem.aB.bo) {
                  OEntitySnowball entityitem2 = new OEntitySnowball(world, d, d1, d2);
                  entityitem2.a((double)i1, 0.10000000149011612D, (double)j1, 1.1F, 6.0F);
                  world.b(entityitem2);
                  world.e(1002, i, j, k, 0);
               } else {
                  OEntityItem entityitem1 = new OEntityItem(world, d, d1 - 0.3D, d2, itemstack);
                  double d3 = random.nextDouble() * 0.1D + 0.2D;
                  entityitem1.bi = (double)i1 * d3;
                  entityitem1.bj = 0.20000000298023224D;
                  entityitem1.bk = (double)j1 * d3;
                  entityitem1.bi += random.nextGaussian() * 0.007499999832361937D * 6.0D;
                  entityitem1.bj += random.nextGaussian() * 0.007499999832361937D * 6.0D;
                  entityitem1.bk += random.nextGaussian() * 0.007499999832361937D * 6.0D;
                  world.b(entityitem1);
                  world.e(1000, i, j, k, 0);
               }
            }

            world.e(2000, i, j, k, i1 + 1 + (j1 + 1) * 3);
         }
      }

   }

   public void a(OWorld world, int i, int j, int k, int l) {
      if(l > 0 && OBlock.m[l].d()) {
         boolean flag = world.r(i, j, k) || world.r(i, j + 1, k);
         if(flag) {
            world.c(i, j, k, this.bA, this.c());
         }
      }

   }

   public void a(OWorld world, int i, int j, int k, Random random) {
      if(world.r(i, j, k) || world.r(i, j + 1, k)) {
         this.b(world, i, j, k, random);
      }

   }

   public OTileEntity a_() {
      return new OTileEntityDispenser();
   }

   public void a(OWorld world, int i, int j, int k, OEntityLiving entityliving) {
      int l = OMathHelper.b((double)(entityliving.bl * 4.0F / 360.0F) + 0.5D) & 3;
      if(l == 0) {
         world.c(i, j, k, 2);
      }

      if(l == 1) {
         world.c(i, j, k, 5);
      }

      if(l == 2) {
         world.c(i, j, k, 3);
      }

      if(l == 3) {
         world.c(i, j, k, 4);
      }

   }

   public void d(OWorld world, int i, int j, int k) {
      OTileEntityDispenser tileentitydispenser = (OTileEntityDispenser)world.b(i, j, k);
      if(tileentitydispenser != null) {
         for(int l = 0; l < tileentitydispenser.a(); ++l) {
            OItemStack itemstack = tileentitydispenser.b_(l);
            if(itemstack != null) {
               float f = this.a.nextFloat() * 0.8F + 0.1F;
               float f1 = this.a.nextFloat() * 0.8F + 0.1F;
               float f2 = this.a.nextFloat() * 0.8F + 0.1F;

               while(itemstack.a > 0) {
                  int i1 = this.a.nextInt(21) + 10;
                  if(i1 > itemstack.a) {
                     i1 = itemstack.a;
                  }

                  itemstack.a -= i1;
                  OEntityItem entityitem = new OEntityItem(world, (double)((float)i + f), (double)((float)j + f1), (double)((float)k + f2), new OItemStack(itemstack.c, i1, itemstack.h()));
                  float f3 = 0.05F;
                  entityitem.bi = (double)((float)this.a.nextGaussian() * f3);
                  entityitem.bj = (double)((float)this.a.nextGaussian() * f3 + 0.2F);
                  entityitem.bk = (double)((float)this.a.nextGaussian() * f3);
                  world.b(entityitem);
               }
            }
         }
      }

      super.d(world, i, j, k);
   }
}
