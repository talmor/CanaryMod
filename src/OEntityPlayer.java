import java.util.Iterator;
import java.util.List;

public abstract class OEntityPlayer extends OEntityLiving {

   public OInventoryPlayer j = new OInventoryPlayer(this);
   public OContainer k;
   public OContainer l;
   protected OFoodStats m = new OFoodStats();
   protected int n = 0;
   public float o = 0;
   public int p = 0;
   public float q;
   public String r;
   public boolean s = false;
   public int t = 0;
   public String u;
   public int v;
   public int w = 0;
   public double x;
   public double y;
   public double z;
   public double A;
   public double B;
   public double C;
   protected boolean D;
   public OChunkCoordinates E;
   private int a;
   public float F;
   public float G;
   private OChunkCoordinates b;
   private OChunkCoordinates c;
   public int H = 20;
   protected boolean I = false;
   public float J;
   public OPlayerCapabilities K = new OPlayerCapabilities();
   public int L;
   public int M;
   public int N;
   private OItemStack d;
   private int e;
   protected float O = 0.1F;
   protected float P = 0.02F;
   private int f = 0;
   public OEntityFish Q = null;
   // CanaryMod start
   @SuppressWarnings("FieldNameHidesFieldInSuperclass")
   HumanEntity entity = new HumanEntity(this);
   // CanaryMod end


   public OEntityPlayer(OWorld var1) {
      super(var1);
      this.k = new OContainerPlayer(this.j, !var1.I);
      this.l = this.k;
      this.by = 1.62F;
      OChunkCoordinates var2 = var1.m();
      this.c((double)var2.a + 0.5D, (double)(var2.b + 1), (double)var2.c + 0.5D, 0.0F, 0.0F);
      this.an = 20;
      this.ae = "humanoid";
      this.ad = 180.0F;
      this.bN = 20;
      this.ab = "/mob/char.png";
   }

   protected void b() {
      super.b();
      this.bU.a(16, Byte.valueOf((byte)0));
      this.bU.a(17, Byte.valueOf((byte)0));
   }

   public boolean o_() {
      return this.d != null;
   }

   public void E() {
      if(this.d != null) {
         this.d.a(this.bb, this, this.e);
      }

      this.F();
   }

   public void F() {
      this.d = null;
      this.e = 0;
      if(!this.bb.I) {
         this.h(false);
      }

   }

   public boolean G() {
      return this.o_() && OItem.c[this.d.c].b(this.d) == OEnumAction.c;
   }

   public void s_() {
      if(this.d != null) {
         OItemStack var1 = this.j.b();
         if(var1 != this.d) {
            this.F();
         } else {
            if(this.e <= 25 && this.e % 4 == 0) {
               this.b(var1, 5);
            }

            if(--this.e == 0 && !this.bb.I) {
               this.C();
            }
         }
      }

      if(this.w > 0) {
         --this.w;
      }

      if(this.P()) {
         ++this.a;
         if(this.a > 100) {
            this.a = 100;
         }

         if(!this.bb.I) {
            if(!this.w()) {
               this.a(true, true, false);
            } else if(this.bb.d()) {
               this.a(false, true, true);
            }
         }
      } else if(this.a > 0) {
         ++this.a;
         if(this.a >= 110) {
            this.a = 0;
         }
      }

      super.s_();
      if(!this.bb.I && this.l != null && !this.l.b(this)) {
         this.x();
         this.l = this.k;
      }

      if(this.K.b) {
         for(int var10 = 0; var10 < 8; ++var10) {
            ;
         }
      }

      if(this.bO > 0 && this.K.a) {
         this.bO = 0;
      }

      this.x = this.A;
      this.y = this.B;
      this.z = this.C;
      double var2 = this.bf - this.A;
      double var4 = this.bg - this.B;
      double var6 = this.bh - this.C;
      double var8 = 10.0D;
      if(var2 > var8) {
         this.x = this.A = this.bf;
      }

      if(var6 > var8) {
         this.z = this.C = this.bh;
      }

      if(var4 > var8) {
         this.y = this.B = this.bg;
      }

      if(var2 < -var8) {
         this.x = this.A = this.bf;
      }

      if(var6 < -var8) {
         this.z = this.C = this.bh;
      }

      if(var4 < -var8) {
         this.y = this.B = this.bg;
      }

      this.A += var2 * 0.25D;
      this.C += var6 * 0.25D;
      this.B += var4 * 0.25D;
      this.a(OStatList.k, 1);
      if(this.ba == null) {
         this.c = null;
      }

      if(!this.bb.I) {
         this.m.a(this);
      }

   }

   protected void b(OItemStack var1, int var2) {
      if(var1.m() == OEnumAction.b) {
         for(int var3 = 0; var3 < var2; ++var3) {
            OVec3D var4 = OVec3D.b(((double)this.bL.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
            var4.a(-this.bm * 3.1415927F / 180.0F);
            var4.b(-this.bl * 3.1415927F / 180.0F);
            OVec3D var5 = OVec3D.b(((double)this.bL.nextFloat() - 0.5D) * 0.3D, (double)(-this.bL.nextFloat()) * 0.6D - 0.3D, 0.6D);
            var5.a(-this.bm * 3.1415927F / 180.0F);
            var5.b(-this.bl * 3.1415927F / 180.0F);
            var5 = var5.c(this.bf, this.bg + (double)this.t(), this.bh);
            this.bb.a("iconcrack_" + var1.a().bo, var5.a, var5.b, var5.c, var4.a, var4.b + 0.05D, var4.c);
         }

         this.bb.a(this, "mob.eat", 0.5F + 0.5F * (float)this.bL.nextInt(2), (this.bL.nextFloat() - this.bL.nextFloat()) * 0.2F + 1.0F);
      }

   }

   protected void C() {
      if(this.d != null) {
         this.b(this.d, 16);
         int var1 = this.d.a;
         OItemStack var2 = this.d.b(this.bb, this);
         if(var2 != this.d || var2 != null && var2.a != var1) {
            this.j.a[this.j.c] = var2;
            if(var2.a == 0) {
               this.j.a[this.j.c] = null;
            }
         }

         this.F();
      }

   }

   protected boolean H() {
      return this.an <= 0 || this.P();
   }

   protected void x() {
      this.l = this.k;
   }

   public void I() {
      double var1 = this.bf;
      double var3 = this.bg;
      double var5 = this.bh;
      super.I();
      this.q = this.o;
      this.o = 0.0F;
      this.h(this.bf - var1, this.bg - var3, this.bh - var5);
   }

   private int o() {
      return this.a(OPotion.e)?6 - (1 + this.b(OPotion.e).c()) * 1:(this.a(OPotion.f)?6 + (1 + this.b(OPotion.f).c()) * 2:6);
   }

   protected void c_() {
      int var1 = this.o();
      if(this.s) {
         ++this.t;
         if(this.t >= var1) {
            this.t = 0;
            this.s = false;
         }
      } else {
         this.t = 0;
      }

      this.am = (float)this.t / (float)var1;
   }

   public void s() {
      // CanaryMod: adjust 'healing over time' independent of
      // monster-spawn=true/false (nice notchup!)
      PluginLoader.HookResult autoHeal = etc.getInstance().autoHeal();
      if(this.bb.q == 0 && autoHeal == PluginLoader.HookResult.DEFAULT_ACTION || autoHeal == PluginLoader.HookResult.ALLOW_ACTION) {
         if (this.an < 20 && this.bM % 20 * 12 == 0)
            this.b(1);
      }


      if(this.n > 0) {
         --this.n;
      }

      if(this.bb.v == 0 && this.an < 20 && this.bM % 20 * 12 == 0) {
         this.c(1);
      }

      this.j.h();
      this.q = this.o;
      super.s();
      this.aj = this.O;
      this.ak = this.P;
      if(this.at()) {
         this.aj = (float)((double)this.aj + (double)this.O * 0.3D);
         this.ak = (float)((double)this.ak + (double)this.P * 0.3D);
      }

      float var1 = OMathHelper.a(this.bi * this.bi + this.bk * this.bk);
      float var2 = (float)Math.atan(-this.bj * 0.20000000298023224D) * 15.0F;
      if(var1 > 0.1F) {
         var1 = 0.1F;
      }

      if(!this.bq || this.an <= 0) {
         var1 = 0.0F;
      }

      if(this.bq || this.an <= 0) {
         var2 = 0.0F;
      }

      this.r += (var1 - this.o) * 0.4F;
      this.av += (var2 - this.aj) * 0.8F;
      if(this.an > 0) {
         List var3 = this.bb.b((OEntity)this, this.bp.b(1.0D, 0.0D, 1.0D));
         if(var3 != null) {
            for(int var4 = 0; var4 < var3.size(); ++var4) {
               OEntity var5 = (OEntity)var3.get(var4);
               if(!var5.bx) {
                  this.j(var5);
               }
            }
         }
      }

   }

   private void j(OEntity var1) {
      var1.a_(this);
   }

   public void a(ODamageSource var1) {
      super.a(var1);
      this.b(0.2F, 0.2F);
      this.c(this.bf, this.bg, this.bh);
      this.bj = 0.10000000149011612D;
      if(this.u.equals("Notch")) {
         this.a(new OItemStack(OItem.h, 1), true);
      }

      this.j.j();
      if(var1 != null) {
         this.bi = (double)(-OMathHelper.b((this.ar + this.bl) * 3.1415927F / 180.0F) * 0.1F);
         this.bk = (double)(-OMathHelper.a((this.ar + this.bl) * 3.1415927F / 180.0F) * 0.1F);
      } else {
         this.bi = this.bk = 0.0D;
      }

      this.by = 0.1F;
      this.a(OStatList.y, 1);
   }

   public void b(OEntity var1, int var2) {
      this.p += var2;
      if(var1 instanceof OEntityPlayer) {
         this.a(OStatList.A, 1);
      } else {
         this.a(OStatList.z, 1);
      }

   }

   public void J() {
      this.a(this.j.a(this.j.c, 1), false);
   }

   public void b(OItemStack var1) {
      this.a(var1, false);
   }

   public void a(OItemStack var1, boolean var2) {
      if(var1 != null) {
         OEntityItem var3 = new OEntityItem(this.bb, this.bf, this.bg - 0.30000001192092896D + (double)this.t(), this.bh, var1);
         var3.c = 40;
         float var4 = 0.1F;
         float var5;
         if(var2) {
            var5 = this.bL.nextFloat() * 0.5F;
            float var6 = this.bL.nextFloat() * 3.1415927F * 2.0F;
            var3.bi = (double)(-OMathHelper.a(var6) * var5);
            var3.bk = (double)(OMathHelper.b(var6) * var5);
            var3.bj = 0.20000000298023224D;
         } else {
            var4 = 0.3F;
            var3.bi = (double)(-OMathHelper.a(this.bl / 180.0F * 3.1415927F) * OMathHelper.b(this.bm / 180.0F * 3.1415927F) * var4);
            var3.bk = (double)(OMathHelper.b(this.bl / 180.0F * 3.1415927F) * OMathHelper.b(this.bm / 180.0F * 3.1415927F) * var4);
            var3.bj = (double)(-OMathHelper.a(this.bm / 180.0F * 3.1415927F) * var4 + 0.1F);
            var4 = 0.02F;
            var5 = this.bL.nextFloat() * 3.1415927F * 2.0F;
            var4 *= this.bL.nextFloat();
            var3.bi += Math.cos((double)var5) * (double)var4;
            var3.bj += (double)((this.bL.nextFloat() - this.bL.nextFloat()) * 0.1F);
            var3.bk += Math.sin((double)var5) * (double)var4;
         }
         
         if (!(Boolean) manager.callHook(PluginLoader.Hook.ITEM_DROP, ((OEntityPlayerMP) this).getPlayer(), new Item(var1))){
            Item droppedItem = var3.item.getItem();
            if(droppedItem.getAmount() < 0) {
               droppedItem.setAmount(1);
               droppedItem.update();
            }
            this.a(var3);
            this.a(OStatList.v, 1);
          // return the item to the inventory.
          } else
             j.a(var1);
      }
   }

   protected void a(OEntityItem var1) {
      this.bb.b((OEntity)var1);
   }

   public float a(OBlock var1) {
      float var2 = this.j.a(var1);
      if(this.a(OMaterial.g)) {
         var2 /= 5.0F;
      }

      if(!this.bq) {
         var2 /= 5.0F;
      }

      if(this.a(OPotion.e)) {
         var2 *= 1.0F + (float)(this.b(OPotion.e).c() + 1) * 0.2F;
      }

      if(this.a(OPotion.f)) {
         var2 *= 1.0F - (float)(this.b(OPotion.f).c() + 1) * 0.2F;
      }

      return var2;
   }

   public boolean b(OBlock var1) {
      return this.j.b(var1);
   }

   public void a(ONBTTagCompound var1) {
      super.a(var1);
      ONBTTagList var2 = var1.l("Inventory");
      this.j.b(var2);
      this.v = var1.e("Dimension");
      this.D = var1.m("Sleeping");
      this.a = var1.d("SleepTimer");
      this.L = var1.e("Xp");
      this.M = var1.e("XpLevel");
      this.N = var1.e("XpTotal");
      if(this.D) {
         this.E = new OChunkCoordinates(OMathHelper.b(this.bf), OMathHelper.b(this.bg), OMathHelper.b(this.bh));
         this.a(true, true, false);
      }

      if(var1.b("SpawnX") && var1.b("SpawnY") && var1.b("SpawnZ")) {
         this.b = new OChunkCoordinates(var1.e("SpawnX"), var1.e("SpawnY"), var1.e("SpawnZ"));
      }

      this.m.a(var1);
   }

   public void b(ONBTTagCompound var1) {
      super.b(var1);
      var1.a("Inventory", (ONBTBase)this.j.a(new ONBTTagList()));
      var1.a("Dimension", this.v);
      var1.a("Sleeping", this.D);
      var1.a("SleepTimer", (short)this.a);
      var1.a("Xp", this.L);
      var1.a("XpLevel", this.M);
      var1.a("XpTotal", this.N);
      if(this.b != null) {
         var1.a("SpawnX", this.b.a);
         var1.a("SpawnY", this.b.b);
         var1.a("SpawnZ", this.b.c);
      }

      this.m.b(var1);
   }

   public void a(OIInventory var1) {}

   public void b(int var1, int var2, int var3) {}

   public void a(OEntity var1, int var2) {}

   public float t() {
      return 0.12F;
   }

   protected void m_() {
      this.by = 1.62F;
   }

   public boolean a(ODamageSource var1, int var2) {
      if(this.K.a && !var1.d()) {
         return false;
      } else {
         this.aO = 0;
         if(this.an <= 0) {
            return false;
         } else {
            if(this.P() && !this.bb.I) {
               this.a(true, true, false);
            }

            OEntity var3 = var1.a();
            if(var3 instanceof OEntityMob || var3 instanceof OEntityArrow) {
               if(this.bb.v == 0) {
                  var2 = 0;
               }

               if(this.bb.v == 1) {
                  var2 = var2 / 3 + 1;
               }

               if(this.bb.v == 3) {
                  var2 = var2 * 3 / 2;
               }
            }

            if(var2 == 0) {
               return false;
            } else {
               OEntity var4 = var3;
               if(var3 instanceof OEntityArrow && ((OEntityArrow)var3).c != null) {
                  var4 = ((OEntityArrow)var3).c;
               }

               if(var4 instanceof OEntityLiving) {
                  this.a((OEntityLiving)var4, false);
               }

               this.a(OStatList.x, var2);
               return super.a(var1, var2);
            }
         }
      }
   }

   protected boolean n_() {
      return false;
   }

   protected void a(OEntityLiving var1, boolean var2) {
      if(!(var1 instanceof OEntityCreeper) && !(var1 instanceof OEntityGhast)) {
         if(var1 instanceof OEntityWolf) {
            OEntityWolf var3 = (OEntityWolf)var1;
            if(var3.z() && this.u.equals(var3.w())) {
               return;
            }
         }

         if(!(var1 instanceof OEntityPlayer) || this.n_()) {
            List var7 = this.bb.a(OEntityWolf.class, OAxisAlignedBB.b(this.bf, this.bg, this.bh, this.bf + 1.0D, this.bg + 1.0D, this.bh + 1.0D).b(16.0D, 4.0D, 16.0D));
            Iterator var4 = var7.iterator();

            while(var4.hasNext()) {
               OEntity var5 = (OEntity)var4.next();
               OEntityWolf var6 = (OEntityWolf)var5;
               if(var6.z() && var6.C() == null && this.u.equals(var6.w()) && (!var2 || !var6.x())) {
                  var6.c(false);
                  var6.d(var1);
               }
            }

         }
      }
   }

   protected void b(ODamageSource var1, int var2) {
      if(!var1.b() && this.G()) {
         var2 = 1 + var2 >> 1;
      }

      if(!var1.b()) {
         int var3 = 25 - this.j.i();
         int var4 = var2 * var3 + this.f;
         this.j.d(var2);
         var2 = var4 / 25;
         this.f = var4 % 25;
      }

      this.b(var1.c());
      super.b(var1, var2);
   }

   public void a(OTileEntityFurnace var1) {}

   public void a(OTileEntityDispenser var1) {}

   public void a(OTileEntitySign var1) {}

   public void c(OEntity var1) {
      if(!var1.b(this)) {
         OItemStack var2 = this.K();
         if(var2 != null && var1 instanceof OEntityLiving) {
            var2.a((OEntityLiving)var1);
            if(var2.a <= 0) {
               var2.a(this);
               this.L();
            }
         }

      }
   }

   public OItemStack K() {
      return this.j.b();
   }

   public void L() {
      this.j.a(this.j.c, (OItemStack)null);
   }

   public double M() {
      return (double)(this.by - 0.5F);
   }

   public void v() {
      if(!this.s || this.t >= this.o() / 2 || this.t < 0) {
         this.t = -1;
         this.s = true;
      }

   }

   public void d(OEntity var1) {
      int var2 = this.j.a(var1);
      if(var2 > 0) {
         boolean var3 = this.bj < 0.0D && !this.bq && !this.p() && !this.ao();
         if(var3) {
            var2 = var2 * 3 / 2 + 1;
         }

         boolean var4 = var1.a(ODamageSource.b(this), var2);
         if(var4) {
            if(this.at()) {
               var1.b((double)(-OMathHelper.a(this.bl * 3.1415927F / 180.0F) * 1.0F), 0.1D, (double)(OMathHelper.b(this.bl * 3.1415927F / 180.0F) * 1.0F));
               this.bi *= 0.6D;
               this.bk *= 0.6D;
               this.g(false);
            }

            if(var3) {
               this.e(var1);
            }
         }

         OItemStack var5 = this.K();
         if(var5 != null && var1 instanceof OEntityLiving) {
            var5.a((OEntityLiving)var1, this);
            if(var5.a <= 0) {
               var5.a(this);
               this.L();
            }
         }

         if(var1 instanceof OEntityLiving) {
            if(var1.ac()) {
               this.a((OEntityLiving)var1, true);
            }

            this.a(OStatList.w, var2);
         }

         this.b(0.3F);
      }

   }

   public void e(OEntity var1) {}

   public void a(OItemStack var1) {}

   public void N() {
      super.N();
      this.k.a(this);
      if(this.l != null) {
         this.l.a(this);
      }

   }

   public boolean O() {
      return !this.D && super.O();
   }

   public OEnumStatus a(int var1, int var2, int var3) {
      if(!this.bb.I) {
         if(this.P() || !this.ac()) {
            return OEnumStatus.e;
         }

         if(this.bb.y.c) {
            return OEnumStatus.b;
         }

         if(this.bb.d()) {
            return OEnumStatus.c;
         }

         if(Math.abs(this.bf - (double)var1) > 3.0D || Math.abs(this.bg - (double)var2) > 2.0D || Math.abs(this.bh - (double)var3) > 3.0D) {
            return OEnumStatus.d;
         }
      }

      this.b(0.2F, 0.2F);
      this.by = 0.2F;
      if(this.bb.g(var1, var2, var3)) {
         int var4 = this.bb.c(var1, var2, var3);
         int var5 = OBlockBed.c(var4);
         float var6 = 0.5F;
         float var7 = 0.5F;
         switch(var5) {
         case 0:
            var7 = 0.9F;
            break;
         case 1:
            var6 = 0.1F;
            break;
         case 2:
            var7 = 0.1F;
            break;
         case 3:
            var6 = 0.9F;
         }

         this.b(var5);
         this.c((double)((float)var1 + var6), (double)((float)var2 + 0.9375F), (double)((float)var3 + var7));
      } else {
         this.c((double)((float)var1 + 0.5F), (double)((float)var2 + 0.9375F), (double)((float)var3 + 0.5F));
      }

      this.D = true;
      this.a = 0;
      this.E = new OChunkCoordinates(var1, var2, var3);
      this.bi = this.bk = this.bj = 0.0D;
      if(!this.bb.I) {
         this.bb.q();
      }

      return OEnumStatus.a;
   }

   private void b(int var1) {
      this.F = 0.0F;
      this.G = 0.0F;
      switch(var1) {
      case 0:
         this.G = -1.8F;
         break;
      case 1:
         this.F = 1.8F;
         break;
      case 2:
         this.G = 1.8F;
         break;
      case 3:
         this.F = -1.8F;
      }

   }

   public void a(boolean var1, boolean var2, boolean var3) {
      this.b(0.6F, 1.8F);
      this.m_();
      OChunkCoordinates var4 = this.E;
      OChunkCoordinates var5 = this.E;
      if(var4 != null && this.bb.a(var4.a, var4.b, var4.c) == OBlock.T.bA) {
         OBlockBed.a(this.bb, var4.a, var4.b, var4.c, false);
         var5 = OBlockBed.f(this.bb, var4.a, var4.b, var4.c, 0);
         if(var5 == null) {
            var5 = new OChunkCoordinates(var4.a, var4.b + 1, var4.c);
         }

         this.c((double)((float)var5.a + 0.5F), (double)((float)var5.b + this.by + 0.1F), (double)((float)var5.c + 0.5F));
      }

      this.D = false;
      if(!this.bb.I && var2) {
         this.bb.q();
      }

      if(var1) {
         this.a = 0;
      } else {
         this.a = 100;
      }

      if(var3) {
         this.a(this.E);
      }

   }

   private boolean w() {
      return this.bb.a(this.E.a, this.E.b, this.E.c) == OBlock.T.bA;
   }

   public static OChunkCoordinates a(OWorld var0, OChunkCoordinates var1) {
      OIChunkProvider var2 = var0.n();
      var2.c(var1.a - 3 >> 4, var1.c - 3 >> 4);
      var2.c(var1.a + 3 >> 4, var1.c - 3 >> 4);
      var2.c(var1.a - 3 >> 4, var1.c + 3 >> 4);
      var2.c(var1.a + 3 >> 4, var1.c + 3 >> 4);
      if(var0.a(var1.a, var1.b, var1.c) != OBlock.T.bA) {
         return null;
      } else {
         OChunkCoordinates var3 = OBlockBed.f(var0, var1.a, var1.b, var1.c, 0);
         return var3;
      }
   }

   public boolean P() {
      return this.D;
   }

   public boolean Q() {
      return this.D && this.a >= 100;
   }

   public void a(String var1) {}

   public OChunkCoordinates R() {
      return this.b;
   }

   public void a(OChunkCoordinates var1) {
      if(var1 != null) {
         this.b = new OChunkCoordinates(var1);
      } else {
         this.b = null;
      }

   }

   public void a(OStatBase var1) {
      this.a(var1, 1);
   }

   public void a(OStatBase var1, int var2) {}

   protected void S() {
      super.S();
      this.a(OStatList.u, 1);
      if(this.at()) {
         this.b(0.8F);
      } else {
         this.b(0.2F);
      }

   }

   public void a(float var1, float var2) {
      double var3 = this.bf;
      double var5 = this.bg;
      double var7 = this.bh;
      if(this.K.b) {
         double var9 = this.bj;
         float var11 = this.ak;
         this.ak = 0.05F;
         super.a(var1, var2);
         this.bj = var9 * 0.6D;
         this.ak = var11;
      } else {
         super.a(var1, var2);
      }

      this.a(this.bf - var3, this.bg - var5, this.bh - var7);
   }

   public void a(double var1, double var3, double var5) {
      if(this.ba == null) {
         int var7;
         if(this.a(OMaterial.g)) {
            var7 = Math.round(OMathHelper.a(var1 * var1 + var3 * var3 + var5 * var5) * 100.0F);
            if(var7 > 0) {
               this.a(OStatList.q, var7);
               this.b(0.015F * (float)var7 * 0.01F);
            }
         } else if(this.ao()) {
            var7 = Math.round(OMathHelper.a(var1 * var1 + var5 * var5) * 100.0F);
            if(var7 > 0) {
               this.a(OStatList.m, var7);
               this.b(0.015F * (float)var7 * 0.01F);
            }
         } else if(this.p()) {
            if(var3 > 0.0D) {
               this.a(OStatList.o, (int)Math.round(var3 * 100.0D));
            }
         } else if(this.bq) {
            var7 = Math.round(OMathHelper.a(var1 * var1 + var5 * var5) * 100.0F);
            if(var7 > 0) {
               this.a(OStatList.l, var7);
               if(this.at()) {
                  this.b(0.099999994F * (float)var7 * 0.01F);
               } else {
                  this.b(0.01F * (float)var7 * 0.01F);
               }
            }
         } else {
            var7 = Math.round(OMathHelper.a(var1 * var1 + var5 * var5) * 100.0F);
            if(var7 > 25) {
               this.a(OStatList.p, var7);
            }
         }

      }
   }

   private void h(double var1, double var3, double var5) {
      if(this.ba != null) {
         int var7 = Math.round(OMathHelper.a(var1 * var1 + var3 * var3 + var5 * var5) * 100.0F);
         if(var7 > 0) {
            if(this.ba instanceof OEntityMinecart) {
               this.a(OStatList.r, var7);
               if(this.c == null) {
                  this.c = new OChunkCoordinates(OMathHelper.b(this.bf), OMathHelper.b(this.bg), OMathHelper.b(this.bh));
               } else if(this.c.a(OMathHelper.b(this.bf), OMathHelper.b(this.bg), OMathHelper.b(this.bh)) >= 1000.0D) {
                  this.a((OStatBase)OAchievementList.q, 1);
               }
            } else if(this.ba instanceof OEntityBoat) {
               this.a(OStatList.s, var7);
            } else if(this.ba instanceof OEntityPig) {
               this.a(OStatList.t, var7);
            }
         }
      }

   }

   protected void a(float var1) {
      if(!this.K.c) {
         if(var1 >= 2.0F) {
            this.a(OStatList.n, (int)Math.round((double)var1 * 100.0D));
         }

         super.a(var1);
      }
   }

   public void a(OEntityLiving var1) {
      if(var1 instanceof OEntityMob) {
         this.a((OStatBase)OAchievementList.s);
      }

   }

   public void T() {
      if(this.H > 0) {
         this.H = 10;
      } else {
         this.I = true;
      }
   }

   public void d(int var1) {
      this.L += var1;
      this.N += var1;

      while(this.L >= this.U()) {
         this.L -= this.U();
         this.y();
      }

   }

   public int U() {
      return (this.M + 1) * 10;
   }

   private void y() {
      ++this.M;
   }

   public void b(float var1) {
      if(!this.K.a) {
         if(!this.bb.I) {
            this.m.a(var1);
         }

      }
   }

   public OFoodStats V() {
      return this.m;
   }

   public boolean c(boolean var1) {
      return (var1 || this.m.b()) && !this.K.a;
   }

   public boolean W() {
      return this.an > 0 && this.an < 20;
   }

   public void a(OItemStack var1, int var2) {
      if(var1 != this.d) {
         this.d = var1;
         this.e = var2;
         if(!this.bb.I) {
            this.h(true);
         }

      }
   }

   public boolean c(int var1, int var2, int var3) {
      return true;
   }

   protected int a(OEntityPlayer var1) {
      return this.N >> 1;
   }

   protected boolean X() {
      return true;
   }

   public String Y() {
      return this.u;
   }
}
