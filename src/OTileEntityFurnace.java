
import java.util.Arrays;



public class OTileEntityFurnace extends OTileEntity implements OIInventory, Container<OItemStack> {

   private OItemStack[] i = new OItemStack[3];
   public int a = 0;
   public int b = 0;
   public int c = 0;
   private String name = "Furnace";


   public int a() {
      return this.i.length;
   }

   public OItemStack d_(int var1) {
      return this.i[var1];
   }

   public OItemStack a(int var1, int var2) {
      if(this.i[var1] != null) {
         OItemStack var3;
         if(this.i[var1].a <= var2) {
            var3 = this.i[var1];
            this.i[var1] = null;
            return var3;
         } else {
            var3 = this.i[var1].a(var2);
            if(this.i[var1].a == 0) {
               this.i[var1] = null;
            }

            return var3;
         }
      } else {
         return null;
      }
   }

   public void a(int var1, OItemStack var2) {
      this.i[var1] = var2;
      if(var2 != null && var2.a > this.d()) {
         var2.a = this.d();
      }

   }

   public String c() {
      return name;
   }

   public void a(ONBTTagCompound var1) {
      super.a(var1);
      ONBTTagList var2 = var1.l("Items");
      this.i = new OItemStack[this.a()];

      for(int var3 = 0; var3 < var2.c(); ++var3) {
         ONBTTagCompound var4 = (ONBTTagCompound)var2.a(var3);
         byte var5 = var4.c("Slot");
         if(var5 >= 0 && var5 < this.i.length) {
            this.i[var5] = new OItemStack(var4);
         }
      }

      this.a = var1.d("BurnTime");
      this.c = var1.d("CookTime");
      this.b = this.a(this.i[1]);
   }

   public void b(ONBTTagCompound var1) {
      super.b(var1);
      var1.a("BurnTime", (short)this.a);
      var1.a("CookTime", (short)this.c);
      ONBTTagList var2 = new ONBTTagList();

      for(int var3 = 0; var3 < this.i.length; ++var3) {
         if(this.i[var3] != null) {
            ONBTTagCompound var4 = new ONBTTagCompound();
            var4.a("Slot", (byte)var3);
            this.i[var3].a(var4);
            var2.a((ONBTBase)var4);
         }
      }

      var1.a("Items", (ONBTBase)var2);
   }

   public int d() {
      return 64;
   }

   public boolean k() {
      return this.a > 0;
   }

   public void g_() {
      boolean var1 = this.a > 0;
      boolean var2 = false;
      if(this.a > 0) {
         --this.a;
      }

      if(!this.d.B) {
         if(this.a == 0 && this.m()) {
            this.b = this.a = this.a(this.i[1]);
            if(this.a > 0) {
               var2 = true;
               if(this.i[1] != null) {
                  --this.i[1].a;
                  if(this.i[1].a == 0) {
                     this.i[1] = null;
                  }
               }
            }
         }

         if(this.k() && this.m()) {
            ++this.c;
            if(this.c == 200) {
               this.c = 0;
               this.l();
               var2 = true;
            }
         } else {
            this.c = 0;
         }

         if(var1 != this.a > 0) {
            var2 = true;
            OBlockFurnace.a(this.a > 0, this.d, this.e, this.f, this.g);
         }
      }

      if(var2) {
         this.i();
      }

   }

   private boolean m() {
      if(this.i[0] == null) {
         return false;
      } else {
         OItemStack var1 = OFurnaceRecipes.a().a(this.i[0].a().bf);
         return var1 == null?false:(this.i[2] == null?true:(!this.i[2].a(var1)?false:(this.i[2].a < this.d() && this.i[2].a < this.i[2].b()?true:this.i[2].a < var1.b())));
      }
   }

   public void l() {
      if(this.m()) {
         OItemStack var1 = OFurnaceRecipes.a().a(this.i[0].a().bf);
         if(this.i[2] == null) {
            this.i[2] = var1.j();
         } else if(this.i[2].c == var1.c) {
            ++this.i[2].a;
         }

         --this.i[0].a;
         if(this.i[0].a <= 0) {
            this.i[0] = null;
         }

      }
   }

   private int a(OItemStack var1) {
      if(var1 == null) {
         return 0;
      } else {
         int var2 = var1.a().bf;
         return var2 < 256 && OBlock.m[var2].bA == OMaterial.d?300:(var2 == OItem.B.bf?100:(var2 == OItem.k.bf?1600:(var2 == OItem.aw.bf?20000:(var2 == OBlock.z.bn?100:0))));
      }
   }

   public boolean a_(OEntityPlayer var1) {
      return this.d.b(this.e, this.f, this.g) != this?false:var1.e((double)this.e + 0.5D, (double)this.f + 0.5D, (double)this.g + 0.5D) <= 64.0D;
   }

    @Override
    public OItemStack[] getContents() {
        return Arrays.copyOf(i, getContentsSize());
    }

    @Override
    public void setContents(OItemStack[] values) {
        i = Arrays.copyOf(values, getContentsSize());
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
