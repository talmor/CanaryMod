
public class OTileEntitySign extends OTileEntity {

   public String[] a = new String[]{"", "", "", ""};
   public int b = -1;
   private boolean c = true;


   public OTileEntitySign() {
      super();
   }

   public void b(ONBTTagCompound var1) {
      super.b(var1);
      var1.a("Text1", this.a[0]);
      var1.a("Text2", this.a[1]);
      var1.a("Text3", this.a[2]);
      var1.a("Text4", this.a[3]);
   }

   public void a(ONBTTagCompound var1) {
      this.c = false;
      super.a(var1);

      for(int var2 = 0; var2 < 4; ++var2) {
         this.a[var2] = var1.i("Text" + (var2 + 1));
         if(this.a[var2].length() > 15) {
            this.a[var2] = this.a[var2].substring(0, 15);
         }
      }

   }

   public OPacket l() {
      String[] var1 = new String[4];

      for(int var2 = 0; var2 < 4; ++var2) {
         var1[var2] = this.a[var2];
      }

      return new OPacket130UpdateSign(this.j, this.k, this.l, var1);
   }

   public boolean a() {
      return this.c;
   }
   
   public void a(boolean var1) {
      this.c = var1;
   }
}
