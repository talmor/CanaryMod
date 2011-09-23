import java.util.Random;

public class OTileEntityDispenser extends OTileEntity implements OIInventory {

   private OItemStack[] a = new OItemStack[9];
   private Random b = new Random();


   public OTileEntityDispenser() {
      super();
   }

   public int a() {
      return 9;
   }

   public OItemStack b_(int var1) {
      return this.a[var1];
   }

   public OItemStack a(int var1, int var2) {
      if(this.a[var1] != null) {
         OItemStack var3;
         if(this.a[var1].a <= var2) {
            var3 = this.a[var1];
            this.a[var1] = null;
            this.k();
            return var3;
         } else {
            var3 = this.a[var1].a(var2);
            if(this.a[var1].a == 0) {
               this.a[var1] = null;
            }

            this.k();
            return var3;
         }
      } else {
         return null;
      }
   }

   public OItemStack b() {
      int var1 = -1;
      int var2 = 1;

      for(int var3 = 0; var3 < this.a.length; ++var3) {
         if(this.a[var3] != null && this.b.nextInt(var2++) == 0) {
            var1 = var3;
         }
      }

      if(var1 >= 0) {
         return this.a(var1, 1);
      } else {
         return null;
      }
   }

   public void a(int var1, OItemStack var2) {
      this.a[var1] = var2;
      if(var2 != null && var2.a > this.d()) {
         var2.a = this.d();
      }

      this.k();
   }

   public String c() {
      return "Trap";
   }

   public void a(ONBTTagCompound var1) {
      super.a(var1);
      ONBTTagList var2 = var1.l("Items");
      this.a = new OItemStack[this.a()];

      for(int var3 = 0; var3 < var2.c(); ++var3) {
         ONBTTagCompound var4 = (ONBTTagCompound)var2.a(var3);
         int var5 = var4.c("Slot") & 255;
         if(var5 >= 0 && var5 < this.a.length) {
            this.a[var5] = OItemStack.a(var4);
         }
      }

   }

   public void b(ONBTTagCompound var1) {
      super.b(var1);
      ONBTTagList var2 = new ONBTTagList();

      for(int var3 = 0; var3 < this.a.length; ++var3) {
         if(this.a[var3] != null) {
            ONBTTagCompound var4 = new ONBTTagCompound();
            var4.a("Slot", (byte)var3);
            this.a[var3].b(var4);
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

   public void e() {}

   public void t_() {}
}
