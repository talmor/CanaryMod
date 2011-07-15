

public class OInventoryCraftResult implements OIInventory, Container<OItemStack> {

   private OItemStack[] a = new OItemStack[1];
   // CanaryMod
   private String name = "Result";


   public int a() {
      return 1;
   }

   public OItemStack d_(int var1) {
      return this.a[var1];
   }

   public String c() {
      return name;
   }

   public OItemStack a(int var1, int var2) {
      if(this.a[var1] != null) {
         OItemStack var3 = this.a[var1];
         this.a[var1] = null;
         return var3;
      } else {
         return null;
      }
   }

   public void a(int var1, OItemStack var2) {
      this.a[var1] = var2;
   }

   public int d() {
      return 64;
   }

   public void i() {}

   public boolean a_(OEntityPlayer var1) {
      return true;
   }

    @Override
    public OItemStack[] getContents() {
        return a;
    }

    @Override
    public void setContents(OItemStack[] values) {
        a = values;
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
