

public class OEntityItem extends OEntity {

   public OItemStack a;
   private int e;
   public int b = 0;
   public int c;
   private int f = 5;
   public float d = (float)(Math.random() * 3.141592653589793D * 2.0D);


   public OEntityItem(OWorld var1, double var2, double var4, double var6, OItemStack var8) {
      super(var1);
      this.b(0.25F, 0.25F);
      this.bi = this.bk / 2.0F;
      this.a(var2, var4, var6);
      this.a = var8;
      this.aV = (float)(Math.random() * 360.0D);
      this.aS = (double)((float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D));
      this.aT = 0.20000000298023224D;
      this.aU = (double)((float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D));
   }

   protected boolean n() {
      return false;
   }

   public OEntityItem(OWorld var1) {
      super(var1);
      this.b(0.25F, 0.25F);
      this.bi = this.bk / 2.0F;
   }

   protected void b() {
   }

   public void o_() {
      super.o_();
      if(this.c > 0) {
         --this.c;
      }

      this.aM = this.aP;
      this.aN = this.aQ;
      this.aO = this.aR;
      this.aT -= 0.03999999910593033D;
      if(this.aL.c(OMathHelper.b(this.aP), OMathHelper.b(this.aQ), OMathHelper.b(this.aR)) == OMaterial.h) {
         this.aT = 0.20000000298023224D;
         this.aS = (double)((this.bv.nextFloat() - this.bv.nextFloat()) * 0.2F);
         this.aU = (double)((this.bv.nextFloat() - this.bv.nextFloat()) * 0.2F);
         this.aL.a(this, "random.fizz", 0.4F, 2.0F + this.bv.nextFloat() * 0.4F);
      }

      this.g(this.aP, (this.aZ.b + this.aZ.e) / 2.0D, this.aR);
      this.c(this.aS, this.aT, this.aU);
      float var1 = 0.98F;
      if(this.ba) {
         var1 = 0.58800006F;
         int var2 = this.aL.a(OMathHelper.b(this.aP), OMathHelper.b(this.aZ.b) - 1, OMathHelper.b(this.aR));
         if(var2 > 0) {
            var1 = OBlock.m[var2].bB * 0.98F;
         }
      }

      this.aS *= (double)var1;
      this.aT *= 0.9800000190734863D;
      this.aU *= (double)var1;
      if(this.ba) {
         this.aT *= -0.5D;
      }

      ++this.e;
      ++this.b;
      if(this.b >= 6000) {
         this.I();
      }

   }

   public boolean f_() {
      return this.aL.a(this.aZ, OMaterial.g, this);
   }

   protected void a(int var1) {
      this.a((OEntity)null, var1);
   }

   public boolean a(OEntity var1, int var2) {
      this.ae();
      this.f -= var2;
      if(this.f <= 0) {
         this.I();
      }

      return false;
   }

   public void b(ONBTTagCompound var1) {
      var1.a("Health", (short)((byte)this.f));
      var1.a("Age", (short)this.b);
      var1.a("Item", this.a.a(new ONBTTagCompound()));
   }

   public void a(ONBTTagCompound var1) {
      this.f = var1.d("Health") & 255;
      this.b = var1.d("Age");
      ONBTTagCompound var2 = var1.k("Item");
      this.a = new OItemStack(var2);
   }

   public void b(OEntityPlayer var1) {
      if(!this.aL.B) {
         int var2 = this.a.a;
         if(this.c == 0 && var1.i.a(this.a)) {
            // CanaryMod: allow item pickups
            Item item = new Item(a.c, var2);
            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_PICK_UP, ((OEntityPlayerMP) var1).getPlayer(), item))
               return;
            if(this.a.c == OBlock.K.bn) {
               var1.a(OAchievementList.g);
            }

            if(this.a.c == OItem.aD.be) {
               var1.a(OAchievementList.t);
            }

            this.aL.a(this, "random.pop", 0.2F, ((this.bv.nextFloat() - this.bv.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            var1.b(this, var2);
            if(this.a.a <= 0) {
               this.I();
            }
         }

      }
   }
}
