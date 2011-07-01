

public class OBlockNote extends OBlockContainer {

   public OBlockNote(int var1) {
      super(var1, 74, OMaterial.d);
   }

   public int a(int var1) {
      return this.bm;
   }

   public void a(OWorld var1, int var2, int var3, int var4, int var5) {
      if(var5 > 0 && OBlock.m[var5].d()) {
         boolean var6 = var1.q(var2, var3, var4);
         OTileEntityNote var7 = (OTileEntityNote)var1.n(var2, var3, var4);
         if(var7.b != var6) {
            if(var6) {
               var7.a(var1, var2, var3, var4);
            }

            var7.b = var6;
         }
      }

   }

   public boolean a(OWorld var1, int var2, int var3, int var4, OEntityPlayer var5) {
      if(var1.B) {
         return true;
      } else {
         OTileEntityNote var6 = (OTileEntityNote)var1.n(var2, var3, var4);
         var6.a();
         var6.a(var1, var2, var3, var4);
         return true;
      }
   }

   public void b(OWorld var1, int var2, int var3, int var4, OEntityPlayer var5) {
      if(!var1.B) {
         OTileEntityNote var6 = (OTileEntityNote)var1.n(var2, var3, var4);
         var6.a(var1, var2, var3, var4);
      }
   }

   protected OTileEntity a_() {
      return new OTileEntityNote();
   }

   public void a(OWorld var1, int var2, int var3, int var4, int var5, int var6) {
      float var7 = (float)Math.pow(2.0D, (double)(var6 - 12) / 12.0D);
      String var8 = "harp";
      if(var5 == 1) {
         // CanaryMod: bd gets jarjar'd, reverse here
         var8 = "bd";
      }

      if(var5 == 2) {
         var8 = "snare";
      }

      if(var5 == 3) {
         var8 = "hat";
      }

      if(var5 == 4) {
         var8 = "bassattack";
      }

      var1.a((double)var2 + 0.5D, (double)var3 + 0.5D, (double)var4 + 0.5D, "note." + var8, 3.0F, var7);
      var1.a("note", (double)var2 + 0.5D, (double)var3 + 1.2D, (double)var4 + 0.5D, (double)var6 / 24.0D, 0.0D, 0.0D);
   }
}
