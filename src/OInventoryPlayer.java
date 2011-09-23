public class OInventoryPlayer implements OIInventory, Container<OItemStack>  {

   public OItemStack[] a = new OItemStack[36];
   public OItemStack[] b = new OItemStack[4];
   public int c = 0;
   public OEntityPlayer d;
   private OItemStack f;
   public boolean e = false;
   // CanaryMod
   private String name = "Inventory";


   public OInventoryPlayer(OEntityPlayer var1) {
      super();
      this.d = var1;
   }

   public OItemStack b() {
      return this.c < 9 && this.c >= 0?this.a[this.c]:null;
   }

   public static int g() {
      return 9;
   }

   private int e(int var1) {
      for(int var2 = 0; var2 < this.a.length; ++var2) {
         if(this.a[var2] != null && this.a[var2].c == var1) {
            return var2;
         }
      }

      return -1;
   }

   private int d(OItemStack var1) {
      for(int var2 = 0; var2 < this.a.length; ++var2) {
         if(this.a[var2] != null && this.a[var2].c == var1.c && this.a[var2].c() && this.a[var2].a < this.a[var2].b() && this.a[var2].a < this.d() && (!this.a[var2].e() || this.a[var2].h() == var1.h())) {
            return var2;
         }
      }

      return -1;
   }

   private int m() {
      for(int var1 = 0; var1 < this.a.length; ++var1) {
         if(this.a[var1] == null) {
            return var1;
         }
      }

      return -1;
   }

   private int e(OItemStack var1) {
      int var2 = var1.c;
      int var3 = var1.a;
      int var4 = this.d(var1);
      if(var4 < 0) {
         var4 = this.m();
      }

      if(var4 < 0) {
         return var3;
      } else {
         if(this.a[var4] == null) {
            this.a[var4] = new OItemStack(var2, 0, var1.h());
         }

         int var5 = var3;
         if(var3 > this.a[var4].b() - this.a[var4].a) {
            var5 = this.a[var4].b() - this.a[var4].a;
         }

         if(var5 > this.d() - this.a[var4].a) {
            var5 = this.d() - this.a[var4].a;
         }

         if(var5 == 0) {
            return var3;
         } else {
            var3 -= var5;
            this.a[var4].a += var5;
            this.a[var4].b = 5;
            return var3;
         }
      }
   }

   public void h() {
      for(int var1 = 0; var1 < this.a.length; ++var1) {
         if(this.a[var1] != null) {
            this.a[var1].a(this.d.bb, this.d, var1, this.c == var1);
         }
      }

   }

   public boolean b(int var1) {
      int var2 = this.e(var1);
      if(var2 < 0) {
         return false;
      } else {
         if(--this.a[var2].a <= 0) {
            this.a[var2] = null;
         }

         return true;
      }
   }

   public boolean c(int var1) {
      int var2 = this.e(var1);
      return var2 >= 0;
   }

   public boolean a(OItemStack var1) {
      int var2;
      if(var1.f()) {
         var2 = this.m();
         if(var2 >= 0) {
            this.a[var2] = OItemStack.b(var1);
            this.a[var2].b = 5;
            var1.a = 0;
            return true;
         } else {
            return false;
         }
      } else {
         do {
            var2 = var1.a;
            var1.a = this.e(var1);
         } while(var1.a > 0 && var1.a < var2);

         return var1.a < var2;
      }
   }

   public OItemStack a(int var1, int var2) {
      OItemStack[] var3 = this.a;
      if(var1 >= this.a.length) {
         var3 = this.b;
         var1 -= this.a.length;
      }

      if(var3[var1] != null) {
         OItemStack var4;
         if(var3[var1].a <= var2) {
            var4 = var3[var1];
            var3[var1] = null;
            return var4;
         } else {
            var4 = var3[var1].a(var2);
            if(var3[var1].a == 0) {
               var3[var1] = null;
            }

            return var4;
         }
      } else {
         return null;
      }
   }

   public void a(int var1, OItemStack var2) {
      OItemStack[] var3 = this.a;
      if(var1 >= var3.length) {
         var1 -= var3.length;
         var3 = this.b;
      }

      var3[var1] = var2;
   }

   public float a(OBlock var1) {
      float var2 = 1.0F;
      if(this.a[this.c] != null) {
         var2 *= this.a[this.c].a(var1);
      }

      return var2;
   }

   public ONBTTagList a(ONBTTagList var1) {
      int var2;
      ONBTTagCompound var3;
      for(var2 = 0; var2 < this.a.length; ++var2) {
         if(this.a[var2] != null) {
            var3 = new ONBTTagCompound();
            var3.a("Slot", (byte)var2);
            this.a[var2].b(var3);
            var1.a((ONBTBase)var3);
         }
      }

      for(var2 = 0; var2 < this.b.length; ++var2) {
         if(this.b[var2] != null) {
            var3 = new ONBTTagCompound();
            var3.a("Slot", (byte)(var2 + 100));
            this.b[var2].b(var3);
            var1.a((ONBTBase)var3);
         }
      }

      return var1;
   }

   public void b(ONBTTagList var1) {
      this.a = new OItemStack[36];
      this.b = new OItemStack[4];

      for(int var2 = 0; var2 < var1.c(); ++var2) {
         ONBTTagCompound var3 = (ONBTTagCompound)var1.a(var2);
         int var4 = var3.c("Slot") & 255;
         OItemStack var5 = OItemStack.a(var3);
         if(var5 != null) {
            if(var4 >= 0 && var4 < this.a.length) {
               this.a[var4] = var5;
            }

            if(var4 >= 100 && var4 < this.b.length + 100) {
               this.b[var4 - 100] = var5;
            }
         }
      }

   }

   public int a() {
      return this.a.length + 4;
   }

   public OItemStack b_(int var1) {
      OItemStack[] var2 = this.a;
      if(var1 >= var2.length) {
         var1 -= var2.length;
         var2 = this.b;
      }

      return var2[var1];
   }

   public String c() {
      return "Inventory";
   }

   public int d() {
      return 64;
   }

   public int a(OEntity var1) {
      OItemStack var2 = this.b_(this.c);
      return var2 != null?var2.a(var1):1;
   }

   public boolean b(OBlock var1) {
      if(var1.bN.k()) {
         return true;
      } else {
         OItemStack var2 = this.b_(this.c);
         return var2 != null?var2.b(var1):false;
      }
   }

   public int i() {
      int var1 = 0;
      int var2 = 0;
      int var3 = 0;

      for(int var4 = 0; var4 < this.b.length; ++var4) {
         if(this.b[var4] != null && this.b[var4].a() instanceof OItemArmor) {
            int var5 = this.b[var4].i();
            int var6 = this.b[var4].g();
            int var7 = var5 - var6;
            var2 += var7;
            var3 += var5;
            int var8 = ((OItemArmor)this.b[var4].a()).bu;
            var1 += var8;
         }
      }

      if(var3 == 0) {
         return 0;
      } else {
         return (var1 - 1) * var2 / var3 + 1;
      }
   }

   public void d(int var1) {
      for(int var2 = 0; var2 < this.b.length; ++var2) {
         if(this.b[var2] != null && this.b[var2].a() instanceof OItemArmor) {
            this.b[var2].a(var1, this.d);
            if(this.b[var2].a == 0) {
               this.b[var2].a(this.d);
               this.b[var2] = null;
            }
         }
      }

   }

   public void j() {
      int var1;
      for(var1 = 0; var1 < this.a.length; ++var1) {
         if(this.a[var1] != null) {
            this.d.a(this.a[var1], true);
            this.a[var1] = null;
         }
      }

      for(var1 = 0; var1 < this.b.length; ++var1) {
         if(this.b[var1] != null) {
            this.d.a(this.b[var1], true);
            this.b[var1] = null;
         }
      }

   }

   public void k() {
      this.e = true;
   }

   public void b(OItemStack var1) {
      this.f = var1;
      this.d.a(var1);
   }

   public OItemStack l() {
      return this.f;
   }

   public boolean a(OEntityPlayer var1) {
      return this.d.bx?false:var1.h(this.d) <= 64.0D;
   }

   public boolean c(OItemStack var1) {
      int var2;
      for(var2 = 0; var2 < this.b.length; ++var2) {
         if(this.b[var2] != null && this.b[var2].c(var1)) {
            return true;
         }
      }

      for(var2 = 0; var2 < this.a.length; ++var2) {
         if(this.a[var2] != null && this.a[var2].c(var1)) {
            return true;
         }
      }

      return false;
   }

   public void e() {}

   public void t_() {}

   public OItemStack[] getContents() {
       return a;
   }

   @Override
   public void setContents(OItemStack[] values) {
       a = values;
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