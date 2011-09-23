import java.util.Arrays;


public class OTileEntityFurnace extends OTileEntity implements OIInventory, Container<OItemStack> {

   private OItemStack[] d = new OItemStack[3];
   public int a = 0;
   public int b = 0;
   public int c = 0;
   private String name = "Furnace";


   public OTileEntityFurnace() {
      super();
   }

   public int a() {
      return this.d.length;
   }

   public OItemStack b_(int var1) {
      return this.d[var1];
   }

   public OItemStack a(int var1, int var2) {
      if(this.d[var1] != null) {
         OItemStack var3;
         if(this.d[var1].a <= var2) {
            var3 = this.d[var1];
            this.d[var1] = null;
            return var3;
         } else {
            var3 = this.d[var1].a(var2);
            if(this.d[var1].a == 0) {
               this.d[var1] = null;
            }

            return var3;
         }
      } else {
         return null;
      }
   }

   public void a(int var1, OItemStack var2) {
      this.d[var1] = var2;
      if(var2 != null && var2.a > this.d()) {
         var2.a = this.d();
      }

   }

   public String c() {
      return "Furnace";
   }

   public void a(ONBTTagCompound var1) {
      super.a(var1);
      ONBTTagList var2 = var1.l("Items");
      this.d = new OItemStack[this.a()];

      for(int var3 = 0; var3 < var2.c(); ++var3) {
         ONBTTagCompound var4 = (ONBTTagCompound)var2.a(var3);
         byte var5 = var4.c("Slot");
         if(var5 >= 0 && var5 < this.d.length) {
            this.d[var5] = OItemStack.a(var4);
         }
      }

      this.a = var1.d("BurnTime");
      this.c = var1.d("CookTime");
      this.b = this.a(this.d[1]);
   }

   public void b(ONBTTagCompound var1) {
      super.b(var1);
      var1.a("BurnTime", (short)this.a);
      var1.a("CookTime", (short)this.c);
      ONBTTagList var2 = new ONBTTagList();

      for(int var3 = 0; var3 < this.d.length; ++var3) {
         if(this.d[var3] != null) {
            ONBTTagCompound var4 = new ONBTTagCompound();
            var4.a("Slot", (byte)var3);
            this.d[var3].b(var4);
            var2.a((ONBTBase)var4);
         }
      }

      var1.a("Items", (ONBTBase)var2);
   }

   public int d() {
      return 64;
   }

   public boolean h() {
      return this.a > 0;
   }

   public void h_() {
      boolean var1 = this.a > 0;
      boolean var2 = false;
      if(this.a > 0) {
         --this.a;
      }

      if(!this.i.I) {
         if(this.a == 0 && this.p()) {
            this.b = this.a = this.a(this.d[1]);
            if(this.a > 0) {
               var2 = true;
               if(this.d[1] != null) {
                  --this.d[1].a;
                  if(this.d[1].a == 0) {
                     this.d[1] = null;
                  }
               }
            }
         }

         if(this.h() && this.p()) {
            ++this.c;
            if(this.c == 200) {
               this.c = 0;
               this.o();
               var2 = true;
            }
         } else {
            this.c = 0;
         }

         if(var1 != this.a > 0) {
            var2 = true;
            OBlockFurnace.a(this.a > 0, this.i, this.j, this.k, this.l);
         }
      }

      if(var2) {
         this.k();
      }

   }

   private boolean p() {
      if(this.d[0] == null) {
         return false;
      } else {
         OItemStack var1 = OFurnaceRecipes.a().a(this.d[0].a().bo);
         return var1 == null?false:(this.d[2] == null?true:(!this.d[2].a(var1)?false:(this.d[2].a < this.d() && this.d[2].a < this.d[2].b()?true:this.d[2].a < var1.b())));
      }
   }

   public void o() {
      if(this.p()) {
         OItemStack var1 = OFurnaceRecipes.a().a(this.d[0].a().bo);
         if(this.d[2] == null) {
            this.d[2] = var1.j();
         } else if(this.d[2].c == var1.c) {
            ++this.d[2].a;
         }

         --this.d[0].a;
         if(this.d[0].a <= 0) {
            this.d[0] = null;
         }

      }
   }

   private int a(OItemStack var1) {
      if(var1 == null) {
         return 0;
      } else {
         int var2 = var1.a().bo;
         return var2 < 256 && OBlock.m[var2].bN == OMaterial.d?300:(var2 == OItem.B.bo?100:(var2 == OItem.k.bo?1600:(var2 == OItem.aw.bo?20000:(var2 == OBlock.z.bA?100:0))));
      }
   }

   public boolean a(OEntityPlayer var1) {
      return this.i.b(this.j, this.k, this.l) != this?false:var1.e((double)this.j + 0.5D, (double)this.k + 0.5D, (double)this.l + 0.5D) <= 64.0D;
   }

   public void e() {}

   public void t_() {}

   @Override
   public OItemStack[] getContents() {
       return Arrays.copyOf(d, getContentsSize());
   }

   @Override
   public void setContents(OItemStack[] values) {
       d = Arrays.copyOf(values, getContentsSize());
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
