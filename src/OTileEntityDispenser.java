
import java.util.Arrays;
import java.util.Random;

public class OTileEntityDispenser extends OTileEntity implements OIInventory, Container<OItemStack> {

   private OItemStack[] a = new OItemStack[9];
   private Random b = new Random();
   private String name = "Trap";


   public int a() {
      return 9;
   }

   public OItemStack d_(int var1) {
      return this.a[var1];
   }

   public OItemStack a(int var1, int var2) {
      if(this.a[var1] != null) {
         OItemStack var3;
         if(this.a[var1].a <= var2) {
            var3 = this.a[var1];
            this.a[var1] = null;
            this.i();
            return var3;
         } else {
            var3 = this.a[var1].a(var2);
            if(this.a[var1].a == 0) {
               this.a[var1] = null;
            }

            this.i();
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

      this.i();
   }

   public String c() {
      return name;
   }

   public void a(ONBTTagCompound var1) {
      super.a(var1);
      ONBTTagList var2 = var1.l("Items");
      this.a = new OItemStack[this.a()];

      for(int var3 = 0; var3 < var2.c(); ++var3) {
         ONBTTagCompound var4 = (ONBTTagCompound)var2.a(var3);
         int var5 = var4.c("Slot") & 255;
         if(var5 >= 0 && var5 < this.a.length) {
            this.a[var5] = new OItemStack(var4);
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
            this.a[var3].a(var4);
            var2.a((ONBTBase)var4);
         }
      }

      var1.a("Items", (ONBTBase)var2);
   }

   public int d() {
      return 64;
   }

   public boolean a_(OEntityPlayer var1) {
      return this.d.b(this.e, this.f, this.g) != this?false:var1.e((double)this.e + 0.5D, (double)this.f + 0.5D, (double)this.g + 0.5D) <= 64.0D;
   }

    @Override
    public OItemStack[] getContents() {
        return Arrays.copyOf(a, getContentsSize());
    }

    @Override
    public void setContents(OItemStack[] values) {
        a = Arrays.copyOf(values, getContentsSize());
    }

    @Override
    public OItemStack getContentsAt(int index) {
        return d_(index);
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
