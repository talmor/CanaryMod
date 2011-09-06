
import java.util.Iterator;
import java.util.List;

public abstract class OEntityPlayer extends OEntityLiving {
   public OInventoryPlayer i = new OInventoryPlayer(this);
   public OContainer j;
   public OContainer k;
   public byte l = 0;
   public int m = 0;
   public float n;
   public float o;
   public boolean p = false;
   public int q = 0;
   public String r;
   public int s;
   public double t;
   public double u;
   public double v;
   public double w;
   public double x;
   public double y;
   protected boolean z;
   public OChunkCoordinates A;
   private int a;
   public float B;
   public float C;
   private OChunkCoordinates b;
   private OChunkCoordinates c;
   public int D = 20;
   protected boolean E = false;
   public float F;
   private int d = 0;
   public OEntityFish G = null;
   // CanaryMod start
   @SuppressWarnings("FieldNameHidesFieldInSuperclass")
   HumanEntity entity = new HumanEntity(this);
   // CanaryMod end

   public OEntityPlayer(OWorld var1) {
      super(var1);
      this.j = new OContainerPlayer(this.i, !var1.B);
      this.k = this.j;
      this.bi = 1.62F;
      OChunkCoordinates var2 = var1.n();
      this.c((double)var2.a + 0.5D, (double)(var2.b + 1), (double)var2.c + 0.5D, 0.0F, 0.0F);
      this.ab = 20;
      this.U = "humanoid";
      this.T = 180.0F;
      this.bx = 20;
      this.R = "/mob/char.png";
   }

   protected void b() {
      super.b();
      this.bE.a(16, Byte.valueOf((byte)0));
   }

   public void m_() {
      if(this.L()) {
         ++this.a;
         if(this.a > 100) {
            this.a = 100;
         }

         if(!this.aL.B) {
            if(!this.o()) {
               this.a(true, true, false);
            } else if(this.aL.d()) {
               this.a(false, true, true);
            }
         }
      } else if(this.a > 0) {
         ++this.a;
         if(this.a >= 110) {
            this.a = 0;
         }
      }

      super.m_();
      if(!this.aL.B && this.k != null && !this.k.b(this)) {
         this.y();
         this.k = this.j;
      }

      this.t = this.w;
      this.u = this.x;
      this.v = this.y;
      double var1 = this.aP - this.w;
      double var3 = this.aQ - this.x;
      double var5 = this.aR - this.y;
      double var7 = 10.0D;
      if(var1 > var7) {
         this.t = this.w = this.aP;
      }

      if(var5 > var7) {
         this.v = this.y = this.aR;
      }

      if(var3 > var7) {
         this.u = this.x = this.aQ;
      }

      if(var1 < -var7) {
         this.t = this.w = this.aP;
      }

      if(var5 < -var7) {
         this.v = this.y = this.aR;
      }

      if(var3 < -var7) {
         this.u = this.x = this.aQ;
      }

      this.w += var1 * 0.25D;
      this.y += var5 * 0.25D;
      this.x += var3 * 0.25D;
      this.a(OStatList.k, 1);
      if(this.aK == null) {
         this.c = null;
      }

   }

   protected boolean D() {
      return this.ab <= 0 || this.L();
   }

   protected void y() {
      this.k = this.j;
   }

   public void E() {
      double var1 = this.aP;
      double var3 = this.aQ;
      double var5 = this.aR;
      super.E();
      this.n = this.o;
      this.o = 0.0F;
      this.i(this.aP - var1, this.aQ - var3, this.aR - var5);
   }

   protected void c_() {
      if(this.p) {
         ++this.q;
         if(this.q >= 8) {
            this.q = 0;
            this.p = false;
         }
      } else {
         this.q = 0;
      }

      this.aa = (float)this.q / 8.0F;
   }

   public void v() {
      // CanaryMod: adjust 'healing over time' independent of
      // monster-spawn=true/false (nice notchup!)
      PluginLoader.HookResult autoHeal = etc.getInstance().autoHeal();
      if(this.aL.q == 0 && autoHeal == PluginLoader.HookResult.DEFAULT_ACTION || autoHeal == PluginLoader.HookResult.ALLOW_ACTION) {
         if(this.ab < 20 && this.bw % 20 * 12 == 0)
            this.b(1);
      }

      this.i.f();
      this.n = this.o;
      super.v();
      float var1 = OMathHelper.a(this.aS * this.aS + this.aU * this.aU);
      float var2 = (float)Math.atan(-this.aT * 0.20000000298023224D) * 15.0F;
      if(var1 > 0.1F) {
         var1 = 0.1F;
      }

      if(!this.ba || this.ab <= 0) {
         var1 = 0.0F;
      }

      if(this.ba || this.ab <= 0) {
         var2 = 0.0F;
      }

      this.o += (var1 - this.o) * 0.4F;
      this.aj += (var2 - this.aj) * 0.8F;
      if(this.ab > 0) {
         List var3 = this.aL.b((OEntity)this, this.aZ.b(1.0D, 0.0D, 1.0D));
         if(var3 != null) {
            for(int var4 = 0; var4 < var3.size(); ++var4) {
               OEntity var5 = (OEntity)var3.get(var4);
               if(!var5.bh) {
                  this.i(var5);
               }
            }
         }
      }

   }

   private void i(OEntity var1) {
      var1.b(this);
   }

   public void a(OEntity var1) {
      super.a(var1);
      this.b(0.2F, 0.2F);
      this.c(this.aP, this.aQ, this.aR);
      this.aT = 0.10000000149011612D;
      if(this.r.equals("Notch")) {
         this.a(new OItemStack(OItem.h, 1), true);
      }

      this.i.h();
      if(var1 != null) {
         this.aS = (double)(-OMathHelper.b((this.af + this.aV) * 3.1415927F / 180.0F) * 0.1F);
         this.aU = (double)(-OMathHelper.a((this.af + this.aV) * 3.1415927F / 180.0F) * 0.1F);
      } else {
         this.aS = this.aU = 0.0D;
      }

      this.bi = 0.1F;
      this.a(OStatList.y, 1);
   }

   public void c(OEntity var1, int var2) {
      this.m += var2;
      if(var1 instanceof OEntityPlayer) {
         this.a(OStatList.A, 1);
      } else {
         this.a(OStatList.z, 1);
      }

   }

   public void F() {
      this.a(this.i.a(this.i.c, 1), false);
   }

   public void b(OItemStack var1) {
      this.a(var1, false);
   }

   public void a(OItemStack var1, boolean var2) {
      if(var1 != null) {
         OEntityItem var3 = new OEntityItem(this.aL, this.aP, this.aQ - 0.30000001192092896D + (double)this.t(), this.aR, var1);
         var3.c = 40;
         float var4 = 0.1F;
         float var5;
         if(var2) {
            var5 = this.bv.nextFloat() * 0.5F;
            float var6 = this.bv.nextFloat() * 3.1415927F * 2.0F;
            var3.aS = (double)(-OMathHelper.a(var6) * var5);
            var3.aU = (double)(OMathHelper.b(var6) * var5);
            var3.aT = 0.20000000298023224D;
         } else {
            var4 = 0.3F;
            var3.aS = (double)(-OMathHelper.a(this.aV / 180.0F * 3.1415927F) * OMathHelper.b(this.aW / 180.0F * 3.1415927F) * var4);
            var3.aU = (double)(OMathHelper.b(this.aV / 180.0F * 3.1415927F) * OMathHelper.b(this.aW / 180.0F * 3.1415927F) * var4);
            var3.aT = (double)(-OMathHelper.a(this.aW / 180.0F * 3.1415927F) * var4 + 0.1F);
            var4 = 0.02F;
            var5 = this.bv.nextFloat() * 3.1415927F * 2.0F;
            var4 *= this.bv.nextFloat();
            var3.aS += Math.cos((double)var5) * (double)var4;
            var3.aT += (double)((this.bv.nextFloat() - this.bv.nextFloat()) * 0.1F);
            var3.aU += Math.sin((double)var5) * (double)var4;
         }

         if(!(Boolean)manager.callHook(PluginLoader.Hook.ITEM_DROP, ((OEntityPlayerMP)this).getPlayer(), var3.item)) {
            Item droppedItem = var3.item.getItem();
            if(droppedItem.getAmount() < 0) {
               droppedItem.setAmount(1);
               droppedItem.update();
            }
            this.a(var3);
            this.a(OStatList.v, 1);
            // return the item to the inventory.
         } else
            i.a(var1);
      }
   }

   protected void a(OEntityItem var1) {
      this.aL.b((OEntity)var1);
   }

   public float a(OBlock var1) {
      float var2 = this.i.a(var1);
      if(this.a(OMaterial.g)) {
         var2 /= 5.0F;
      }

      if(!this.ba) {
         var2 /= 5.0F;
      }

      return var2;
   }

   public boolean b(OBlock var1) {
      return this.i.b(var1);
   }

   public void a(ONBTTagCompound var1) {
      super.a(var1);
      ONBTTagList var2 = var1.l("Inventory");
      this.i.b(var2);
      this.s = var1.e("Dimension");
      this.z = var1.m("Sleeping");
      this.a = var1.d("SleepTimer");
      if(this.z) {
         this.A = new OChunkCoordinates(OMathHelper.b(this.aP), OMathHelper.b(this.aQ), OMathHelper.b(this.aR));
         this.a(true, true, false);
      }

      if(var1.b("SpawnX") && var1.b("SpawnY") && var1.b("SpawnZ")) {
         this.b = new OChunkCoordinates(var1.e("SpawnX"), var1.e("SpawnY"), var1.e("SpawnZ"));
      }

   }

   public void b(ONBTTagCompound var1) {
      super.b(var1);
      var1.a("Inventory", (ONBTBase)this.i.a(new ONBTTagList()));
      var1.a("Dimension", this.s);
      var1.a("Sleeping", this.z);
      var1.a("SleepTimer", (short)this.a);
      if(this.b != null) {
         var1.a("SpawnX", this.b.a);
         var1.a("SpawnY", this.b.b);
         var1.a("SpawnZ", this.b.c);
      }

   }

   public void a(OIInventory var1) {
   }

   public void b(int var1, int var2, int var3) {
   }

   public void b(OEntity var1, int var2) {
   }

   public float t() {
      return 0.12F;
   }

   protected void s() {
      this.bi = 1.62F;
   }

   public boolean a(OEntity var1, int var2) {
      this.ay = 0;
      if(this.ab <= 0) {
         return false;
      } else {
         if(this.L() && !this.aL.B) {
            this.a(true, true, false);
         }

         if(var1 instanceof OEntityMob || var1 instanceof OEntityArrow) {
            if(this.aL.q == 0) {
               var2 = 0;
            }

            if(this.aL.q == 1) {
               var2 = var2 / 3 + 1;
            }

            if(this.aL.q == 3) {
               var2 = var2 * 3 / 2;
            }
         }

         if(var2 == 0) {
            return false;
         } else {
            Object var3 = var1;
            if(var1 instanceof OEntityArrow && ((OEntityArrow)var1).c != null) {
               var3 = ((OEntityArrow)var1).c;
            }

            if(var3 instanceof OEntityLiving) {
               this.a((OEntityLiving)var3, false);
            }

            this.a(OStatList.x, var2);
            return super.a(var1, var2);
         }
      }
   }

   protected boolean j_() {
      return false;
   }

   protected void a(OEntityLiving var1, boolean var2) {
      if(!(var1 instanceof OEntityCreeper) && !(var1 instanceof OEntityGhast)) {
         if(var1 instanceof OEntityWolf) {
            OEntityWolf var3 = (OEntityWolf)var1;
            if(var3.A() && this.r.equals(var3.x())) {
               return;
            }
         }

         if(!(var1 instanceof OEntityPlayer) || this.j_()) {
            List var7 = this.aL.a(OEntityWolf.class, OAxisAlignedBB.b(this.aP, this.aQ, this.aR, this.aP + 1.0D, this.aQ + 1.0D, this.aR + 1.0D).b(16.0D, 4.0D, 16.0D));
            Iterator var4 = var7.iterator();

            while(var4.hasNext()) {
               OEntity var5 = (OEntity)var4.next();
               OEntityWolf var6 = (OEntityWolf)var5;
               if(var6.A() && var6.F() == null && this.r.equals(var6.x()) && (!var2 || !var6.y())) {
                  var6.b(false);
                  var6.c(var1);
               }
            }

         }
      }
   }

   protected void c(int var1) {
      int var2 = 25 - this.i.g();
      int var3 = var1 * var2 + this.d;
      this.i.c(var1);
      var1 = var3 / 25;
      this.d = var3 % 25;
      super.c(var1);
   }

   public void a(OTileEntityFurnace var1) {
   }

   public void a(OTileEntityDispenser var1) {
   }

   public void a(OTileEntitySign var1) {
   }

   public void c(OEntity var1) {
      if(!var1.a(this)) {
         OItemStack var2 = this.G();
         if(var2 != null && var1 instanceof OEntityLiving) {
            var2.a((OEntityLiving)var1);
            if(var2.a <= 0) {
               var2.a(this);
               this.H();
            }
         }

      }
   }

   public OItemStack G() {
      return this.i.b();
   }

   public void H() {
      this.i.a(this.i.c, (OItemStack)null);
   }

   public double I() {
      return (double)(this.bi - 0.5F);
   }

   public void w() {
      this.q = -1;
      this.p = true;
   }

   public void d(OEntity var1) {
      int var2 = this.i.a(var1);
      if(var2 > 0) {
         if(this.aT < 0.0D) {
            ++var2;
         }

         var1.a(this, var2);
         OItemStack var3 = this.G();
         if(var3 != null && var1 instanceof OEntityLiving) {
            var3.a((OEntityLiving)var1, this);
            if(var3.a <= 0) {
               var3.a(this);
               this.H();
            }
         }

         if(var1 instanceof OEntityLiving) {
            if(var1.T()) {
               this.a((OEntityLiving)var1, true);
            }

            this.a(OStatList.w, var2);
         }
      }

   }

   public void a(OItemStack var1) {
   }

   public void J() {
      super.J();
      this.j.a(this);
      if(this.k != null) {
         this.k.a(this);
      }

   }

   public boolean K() {
      return !this.z && super.K();
   }

   public OEnumStatus a(int var1, int var2, int var3) {
      if(!this.aL.B) {
         if(this.L() || !this.T()) {
            return OEnumStatus.e;
         }

         if(this.aL.t.c) {
            return OEnumStatus.b;
         }

         if(this.aL.d()) {
            return OEnumStatus.c;
         }

         if(Math.abs(this.aP - (double)var1) > 3.0D || Math.abs(this.aQ - (double)var2) > 2.0D || Math.abs(this.aR - (double)var3) > 3.0D) {
            return OEnumStatus.d;
         }
      }

      this.b(0.2F, 0.2F);
      this.bi = 0.2F;
      if(this.aL.g(var1, var2, var3)) {
         int var4 = this.aL.c(var1, var2, var3);
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

         this.e(var5);
         this.c((double)((float)var1 + var6), (double)((float)var2 + 0.9375F), (double)((float)var3 + var7));
      } else {
         this.c((double)((float)var1 + 0.5F), (double)((float)var2 + 0.9375F), (double)((float)var3 + 0.5F));
      }

      this.z = true;
      this.a = 0;
      this.A = new OChunkCoordinates(var1, var2, var3);
      this.aS = this.aU = this.aT = 0.0D;
      if(!this.aL.B) {
         this.aL.r();
      }

      return OEnumStatus.a;
   }

   private void e(int var1) {
      this.B = 0.0F;
      this.C = 0.0F;
      switch(var1) {
         case 0:
            this.C = -1.8F;
            break;
         case 1:
            this.B = 1.8F;
            break;
         case 2:
            this.C = 1.8F;
            break;
         case 3:
            this.B = -1.8F;
      }

   }

   public void a(boolean var1, boolean var2, boolean var3) {
      this.b(0.6F, 1.8F);
      this.s();
      OChunkCoordinates var4 = this.A;
      OChunkCoordinates var5 = this.A;
      if(var4 != null && this.aL.a(var4.a, var4.b, var4.c) == OBlock.T.bn) {
         OBlockBed.a(this.aL, var4.a, var4.b, var4.c, false);
         var5 = OBlockBed.f(this.aL, var4.a, var4.b, var4.c, 0);
         if(var5 == null) {
            var5 = new OChunkCoordinates(var4.a, var4.b + 1, var4.c);
         }

         this.c((double)((float)var5.a + 0.5F), (double)((float)var5.b + this.bi + 0.1F), (double)((float)var5.c + 0.5F));
      }

      this.z = false;
      if(!this.aL.B && var2) {
         this.aL.r();
      }

      if(var1) {
         this.a = 0;
      } else {
         this.a = 100;
      }

      if(var3) {
         this.a(this.A);
      }

   }

   private boolean o() {
      return this.aL.a(this.A.a, this.A.b, this.A.c) == OBlock.T.bn;
   }

   public static OChunkCoordinates a(OWorld var0, OChunkCoordinates var1) {
      OIChunkProvider var2 = var0.o();
      var2.c(var1.a - 3 >> 4, var1.c - 3 >> 4);
      var2.c(var1.a + 3 >> 4, var1.c - 3 >> 4);
      var2.c(var1.a - 3 >> 4, var1.c + 3 >> 4);
      var2.c(var1.a + 3 >> 4, var1.c + 3 >> 4);
      if(var0.a(var1.a, var1.b, var1.c) != OBlock.T.bn) {
         return null;
      } else {
         OChunkCoordinates var3 = OBlockBed.f(var0, var1.a, var1.b, var1.c, 0);
         return var3;
      }
   }

   public boolean L() {
      return this.z;
   }

   public boolean M() {
      return this.z && this.a >= 100;
   }

   public void a(String var1) {
   }

   public OChunkCoordinates N() {
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

   public void a(OStatBase var1, int var2) {
   }

   protected void O() {
      super.O();
      this.a(OStatList.u, 1);
   }

   public void a(float var1, float var2) {
      double var3 = this.aP;
      double var5 = this.aQ;
      double var7 = this.aR;
      super.a(var1, var2);
      this.h(this.aP - var3, this.aQ - var5, this.aR - var7);
   }

   private void h(double var1, double var3, double var5) {
      if(this.aK == null) {
         int var7;
         if(this.a(OMaterial.g)) {
            var7 = Math.round(OMathHelper.a(var1 * var1 + var3 * var3 + var5 * var5) * 100.0F);
            if(var7 > 0) {
               this.a(OStatList.q, var7);
            }
         } else if(this.ad()) {
            var7 = Math.round(OMathHelper.a(var1 * var1 + var5 * var5) * 100.0F);
            if(var7 > 0) {
               this.a(OStatList.m, var7);
            }
         } else if(this.p()) {
            if(var3 > 0.0D) {
               this.a(OStatList.o, (int)Math.round(var3 * 100.0D));
            }
         } else if(this.ba) {
            var7 = Math.round(OMathHelper.a(var1 * var1 + var5 * var5) * 100.0F);
            if(var7 > 0) {
               this.a(OStatList.l, var7);
            }
         } else {
            var7 = Math.round(OMathHelper.a(var1 * var1 + var5 * var5) * 100.0F);
            if(var7 > 25) {
               this.a(OStatList.p, var7);
            }
         }

      }
   }

   private void i(double var1, double var3, double var5) {
      if(this.aK != null) {
         int var7 = Math.round(OMathHelper.a(var1 * var1 + var3 * var3 + var5 * var5) * 100.0F);
         if(var7 > 0) {
            if(this.aK instanceof OEntityMinecart) {
               this.a(OStatList.r, var7);
               if(this.c == null) {
                  this.c = new OChunkCoordinates(OMathHelper.b(this.aP), OMathHelper.b(this.aQ), OMathHelper.b(this.aR));
               } else if(this.c.a(OMathHelper.b(this.aP), OMathHelper.b(this.aQ), OMathHelper.b(this.aR)) >= 1000.0D) {
                  this.a((OStatBase)OAchievementList.q, 1);
               }
            } else if(this.aK instanceof OEntityBoat) {
               this.a(OStatList.s, var7);
            } else if(this.aK instanceof OEntityPig) {
               this.a(OStatList.t, var7);
            }
         }
      }

   }

   protected void a(float var1) {
      if(var1 >= 2.0F) {
         this.a(OStatList.n, (int)Math.round((double)var1 * 100.0D));
      }

      super.a(var1);
   }

   public void a(OEntityLiving var1) {
      if(var1 instanceof OEntityMob) {
         this.a((OStatBase)OAchievementList.s);
      }

   }

   public void P() {
      if(this.D > 0) {
         this.D = 10;
      } else {
         this.E = true;
      }
   }

}
