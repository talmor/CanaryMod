import java.util.Arrays;


public class OTileEntityChest extends OTileEntity implements OIInventory, Container<OItemStack> {

   private OItemStack[] p = new OItemStack[36];
   public boolean a = false;
   public OTileEntityChest b;
   public OTileEntityChest c;
   public OTileEntityChest d;
   public OTileEntityChest e;
   public float f;
   public float g;
   public int h;
   private int q;
   private String name = "Chest";


   public OTileEntityChest() {
      super();
   }

   public int a() {
      return 27;
   }

   public OItemStack b_(int var1) {
      return this.p[var1];
   }

   public OItemStack a(int var1, int var2) {
      if(this.p[var1] != null) {
         OItemStack var3;
         if(this.p[var1].a <= var2) {
            var3 = this.p[var1];
            this.p[var1] = null;
            this.k();
            return var3;
         } else {
            var3 = this.p[var1].a(var2);
            if(this.p[var1].a == 0) {
               this.p[var1] = null;
            }

            this.k();
            return var3;
         }
      } else {
         return null;
      }
   }

   public void a(int var1, OItemStack var2) {
      this.p[var1] = var2;
      if(var2 != null && var2.a > this.d()) {
         var2.a = this.d();
      }

      this.k();
   }

   public String c() {
      return "Chest";
   }

   public void a(ONBTTagCompound var1) {
      super.a(var1);
      ONBTTagList var2 = var1.l("Items");
      this.p = new OItemStack[this.a()];

      for(int var3 = 0; var3 < var2.c(); ++var3) {
         ONBTTagCompound var4 = (ONBTTagCompound)var2.a(var3);
         int var5 = var4.c("Slot") & 255;
         if(var5 >= 0 && var5 < this.p.length) {
            this.p[var5] = OItemStack.a(var4);
         }
      }

   }

   public void b(ONBTTagCompound var1) {
      super.b(var1);
      ONBTTagList var2 = new ONBTTagList();

      for(int var3 = 0; var3 < this.p.length; ++var3) {
         if(this.p[var3] != null) {
            ONBTTagCompound var4 = new ONBTTagCompound();
            var4.a("Slot", (byte)var3);
            this.p[var3].b(var4);
            var2.a((ONBTBase)var4);
         }
      }

      var1.a("Items", (ONBTBase)var2);
   }

   public int d() {
      return 64;
   }

   public boolean a(OEntityPlayer var1) {
      return this.i.b(this.j, this.k, this.l) != this?false:var1.e((double)this.j + 0.5D, (double)this.k + 0.5D, (double)this.l + 0.5D) <= 64.0D;
   }

   public void g() {
      super.g();
      this.a = false;
   }

   public void h() {
      if(!this.a) {
         this.a = true;
         this.b = null;
         this.c = null;
         this.d = null;
         this.e = null;
         if(this.i.a(this.j - 1, this.k, this.l) == OBlock.av.bA) {
            this.d = (OTileEntityChest)this.i.b(this.j - 1, this.k, this.l);
         }

         if(this.i.a(this.j + 1, this.k, this.l) == OBlock.av.bA) {
            this.c = (OTileEntityChest)this.i.b(this.j + 1, this.k, this.l);
         }

         if(this.i.a(this.j, this.k, this.l - 1) == OBlock.av.bA) {
            this.b = (OTileEntityChest)this.i.b(this.j, this.k, this.l - 1);
         }

         if(this.i.a(this.j, this.k, this.l + 1) == OBlock.av.bA) {
            this.e = (OTileEntityChest)this.i.b(this.j, this.k, this.l + 1);
         }

         if(this.b != null) {
            this.b.g();
         }

         if(this.e != null) {
            this.e.g();
         }

         if(this.c != null) {
            this.c.g();
         }

         if(this.d != null) {
            this.d.g();
         }

      }
   }

   public void h_() {
      super.h_();
      this.h();
      if(++this.q % 20 * 4 == 0) {
         this.i.d(this.j, this.k, this.l, 1, this.h);
      }

      this.g = this.f;
      float var1 = 0.1F;
      double var2;
      double var4;
      if(this.h > 0 && this.f == 0.0F && this.b == null && this.d == null) {
         var2 = (double)this.j + 0.5D;
         var4 = (double)this.l + 0.5D;
         if(this.e != null) {
            var4 += 0.5D;
         }

         if(this.c != null) {
            var2 += 0.5D;
         }

         this.i.a(var2, (double)this.k + 0.5D, var4, "random.door_open", 1.0F, this.i.w.nextFloat() * 0.1F + 0.9F);
      }

      if(this.h == 0 && this.f > 0.0F || this.h > 0 && this.f < 1.0F) {
         if(this.h > 0) {
            this.f += var1;
         } else {
            this.f -= var1;
         }

         if(this.f > 1.0F) {
            this.f = 1.0F;
         }

         if(this.f < 0.0F) {
            this.f = 0.0F;
            if(this.b == null && this.d == null) {
               var2 = (double)this.j + 0.5D;
               var4 = (double)this.l + 0.5D;
               if(this.e != null) {
                  var4 += 0.5D;
               }

               if(this.c != null) {
                  var2 += 0.5D;
               }

               this.i.a(var2, (double)this.k + 0.5D, var4, "random.door_close", 1.0F, this.i.w.nextFloat() * 0.1F + 0.9F);
            }
         }
      }

   }

   public void b(int var1, int var2) {
      if(var1 == 1) {
         this.h = var2;
      }

   }

   public void e() {
      ++this.h;
      this.i.d(this.j, this.k, this.l, 1, this.h);
   }

   public void t_() {
      --this.h;
      this.i.d(this.j, this.k, this.l, 1, this.h);
   }

   public void i() {
      this.g();
      this.h();
      super.i();
   }
   
   @Override
   public OItemStack[] getContents() {
       return Arrays.copyOf(p, getContentsSize());
   }

   @Override
   public void setContents(OItemStack[] values) {
       int size = getContentsSize();

       for (int i = 0; i < size; i++)
           setContentsAt(i, values[i]);
   }

   @Override
   public OItemStack getContentsAt(int index) {
       return b_(index);
   }

   @Override
   public void setContentsAt(int index, OItemStack value) {
       a(index, value);
   }

   @Override
   public int getContentsSize() {
       return a();
   }

   @Override
   public String getName() {
       return name;
   }

   @Override
   public void setName(String value) {
       name = value;
   }
}
