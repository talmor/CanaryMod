
public class OInventoryCraftResult implements OIInventory {

   private OItemStack[] a = new OItemStack[1];
   // CanaryMod
   private String name = "Result";

   
   public OInventoryCraftResult() {
      super();
   }

   public int a() {
      return 1;
   }

   public OItemStack b_(int var1) {
      return this.a[var1];
   }

   public String c() {
      return this.name;
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

   public void k() {}

   public boolean a(OEntityPlayer var1) {
      return true;
   }

   public void e() {}

   public void t_() {}
}
