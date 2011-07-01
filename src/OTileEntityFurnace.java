
import java.util.Arrays;



public class OTileEntityFurnace extends OTileEntity implements OIInventory, Container<OItemStack> {

   private OItemStack[] h = new OItemStack[3];
   public int a = 0;
   public int b = 0;
   public int c = 0;
   private String name = "Furnace";


   public int a() {
      return this.h.length;
   }

   public OItemStack c_(int var1) {
      return this.h[var1];
   }

   public OItemStack a(int var1, int var2) {
      if(this.h[var1] != null) {
         OItemStack var3;
         if(this.h[var1].a <= var2) {
            var3 = this.h[var1];
            this.h[var1] = null;
            return var3;
         } else {
            var3 = this.h[var1].a(var2);
            if(this.h[var1].a == 0) {
               this.h[var1] = null;
            }

            return var3;
         }
      } else {
         return null;
      }
   }

   public void a(int var1, OItemStack var2) {
      this.h[var1] = var2;
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
      this.h = new OItemStack[this.a()];

      for(int var3 = 0; var3 < var2.c(); ++var3) {
         ONBTTagCompound var4 = (ONBTTagCompound)var2.a(var3);
         byte var5 = var4.c("Slot");
         if(var5 >= 0 && var5 < this.h.length) {
            this.h[var5] = new OItemStack(var4);
         }
      }

      this.a = var1.d("BurnTime");
      this.c = var1.d("CookTime");
      this.b = this.a(this.h[1]);
   }

   public void b(ONBTTagCompound var1) {
      super.b(var1);
      var1.a("BurnTime", (short)this.a);
      var1.a("CookTime", (short)this.c);
      ONBTTagList var2 = new ONBTTagList();

      for(int var3 = 0; var3 < this.h.length; ++var3) {
         if(this.h[var3] != null) {
            ONBTTagCompound var4 = new ONBTTagCompound();
            var4.a("Slot", (byte)var3);
            this.h[var3].a(var4);
            var2.a(var4);
         }
      }

      var1.a("Items", var2);
   }

   public int d() {
      return 64;
   }

   public boolean f() {
      return this.a > 0;
   }

   public void g_() {
      boolean var1 = this.a > 0;
      boolean var2 = false;
      if(this.a > 0) {
         --this.a;
      }

      if(!this.d.B) {
         if(this.a == 0 && this.h()) {
            this.b = this.a = this.a(this.h[1]);
            if(this.a > 0) {
               var2 = true;
               if(this.h[1] != null) {
                  --this.h[1].a;
                  if(this.h[1].a == 0) {
                     this.h[1] = null;
                  }
               }
            }
         }

         if(this.f() && this.h()) {
            ++this.c;
            if(this.c == 200) {
               this.c = 0;
               this.g();
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

   private boolean h() {
      if(this.h[0] == null) {
         return false;
      } else {
         OItemStack var1 = OFurnaceRecipes.a().a(this.h[0].a().be);
         return var1 == null?false:(this.h[2] == null?true:(!this.h[2].a(var1)?false:(this.h[2].a < this.d() && this.h[2].a < this.h[2].b()?true:this.h[2].a < var1.b())));
      }
   }

   public void g() {
      if(this.h()) {
         OItemStack var1 = OFurnaceRecipes.a().a(this.h[0].a().be);
         if(this.h[2] == null) {
            this.h[2] = var1.j();
         } else if(this.h[2].c == var1.c) {
            ++this.h[2].a;
         }

         --this.h[0].a;
         if(this.h[0].a <= 0) {
            this.h[0] = null;
         }

      }
   }

   private int a(OItemStack var1) {
      if(var1 == null) {
         return 0;
      } else {
         int var2 = var1.a().be;
         return var2 < 256 && OBlock.m[var2].bA == OMaterial.d?300:(var2 == OItem.B.be?100:(var2 == OItem.k.be?1600:(var2 == OItem.aw.be?20000:(var2 == OBlock.z.bn?100:0))));
      }
   }

   public boolean a_(OEntityPlayer var1) {
      return this.d.n(this.e, this.f, this.g) != this?false:var1.d((double)this.e + 0.5D, (double)this.f + 0.5D, (double)this.g + 0.5D) <= 64.0D;
   }

    @Override
    public OItemStack[] getContents() {
        return Arrays.copyOf(h, getContentsSize());
    }

    @Override
    public void setContents(OItemStack[] values) {
        h = Arrays.copyOf(values, getContentsSize());
    }

    @Override
    public OItemStack getContentsAt(int index) {
        return c_(index);
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
