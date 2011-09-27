
public class OEntityItem extends OEntity {

   public OItemStack a;
   private int e;
   public int b = 0;
   public int c;
   private int f = 5;
   public float d = (float)(Math.random() * 3.141592653589793D * 2.0D);
   // CanaryMod Start
   ItemEntity item = new ItemEntity(this);
   // CanaryMod End

   public OEntityItem(OWorld var1, double var2, double var4, double var6, OItemStack var8) {
      super(var1);
      this.b(0.25F, 0.25F);
      this.by = this.bA / 2.0F;
      this.c(var2, var4, var6);
      this.a = var8;
      this.bl = (float)(Math.random() * 360.0D);
      this.bi = (double)((float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D));
      this.bj = 0.20000000298023224D;
      this.bk = (double)((float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D));
   }

   protected boolean e_() {
      return false;
   }

   public OEntityItem(OWorld var1) {
      super(var1);
      this.b(0.25F, 0.25F);
      this.by = this.bA / 2.0F;
   }

   protected void b() {
   }

   public void s_() {
      super.s_();
      if(this.c > 0) {
         --this.c;
      }

      this.bc = this.bf;
      this.bd = this.bg;
      this.be = this.bh;
      this.bj -= 0.03999999910593033D;
      if(this.bb.d(OMathHelper.b(this.bf), OMathHelper.b(this.bg), OMathHelper.b(this.bh)) == OMaterial.h) {
         this.bj = 0.20000000298023224D;
         this.bi = (double)((this.bL.nextFloat() - this.bL.nextFloat()) * 0.2F);
         this.bk = (double)((this.bL.nextFloat() - this.bL.nextFloat()) * 0.2F);
         this.bb.a(this, "random.fizz", 0.4F, 2.0F + this.bL.nextFloat() * 0.4F);
      }

      this.g(this.bf, (this.bp.b + this.bp.e) / 2.0D, this.bh);
      this.a_(this.bi, this.bj, this.bk);
      float var1 = 0.98F;
      if(this.bq) {
         var1 = 0.58800006F;
         int var2 = this.bb.a(OMathHelper.b(this.bf), OMathHelper.b(this.bp.b) - 1, OMathHelper.b(this.bh));
         if(var2 > 0) {
            var1 = OBlock.m[var2].bO * 0.98F;
         }
      }

      this.bi *= (double)var1;
      this.bj *= 0.9800000190734863D;
      this.bk *= (double)var1;
      if(this.bq) {
         this.bj *= -0.5D;
      }

      ++this.e;
      ++this.b;
      if(this.b >= 6000) {
         //CanaryMod onEntityDespawn
         if(!(Boolean)etc.getLoader().callHook(PluginLoader.Hook.ENTITY_DESPAWN, item)) {
            this.N();
         } else {
            this.b = 0;
         }
      }

   }

   public boolean f_() {
      return this.bb.a(this.bp, OMaterial.g, this);
   }

   protected void a(int var1) {
      this.a(ODamageSource.a, var1);
   }

   public boolean a(ODamageSource var1, int var2) {
      this.aq();
      this.f -= var2;
      if(this.f <= 0) {
         this.N();
      }

      return false;
   }

   public void b(ONBTTagCompound var1) {
      var1.a("Health", (short)((byte)this.f));
      var1.a("Age", (short)this.b);
      var1.a("Item", this.a.b(new ONBTTagCompound()));
   }

   public void a(ONBTTagCompound var1) {
      this.f = var1.d("Health") & 255;
      this.b = var1.d("Age");
      ONBTTagCompound var2 = var1.k("Item");
      this.a = OItemStack.a(var2);
      if(this.a == null) {
         this.N();
      }

   }

   public void a_(OEntityPlayer var1) {
      if(!this.bb.I) {
         int var2 = this.a.a;
         if(this.c == 0) {
            // Clone player inventory because we might need to restore it if onItemPickUp hook is cancelled.
            // we have to clone it because the function that checks if we can pick up items also picks them up...
            OItemStack[] inventoryClone = new OItemStack[var1.j.a.length];
            for(int i = 0; i < inventoryClone.length; i += 1) {
               if(var1.j.a[i] != null) {
                  inventoryClone[i] = var1.j.a[i].j();
                  inventoryClone[i].b = var1.j.a[i].b;
               } else {
                  inventoryClone[i] = null;
               }
            }
            // pick up items if possible
            if(var1.j.a(this.a)) {
               int quantityAfterPickup = this.a.a;
               // if managed to take something
               if(quantityAfterPickup < var2) {
                  // CanaryMod: allow item pickups
                  // for the hook, we need to pass how many items the player actually took.
                  this.a.a = var2 - quantityAfterPickup;
                  boolean result = (Boolean)etc.getLoader().callHook(PluginLoader.Hook.ITEM_PICK_UP, ((OEntityPlayerMP)var1).getPlayer(), item);
                  this.a.a = quantityAfterPickup;
                  // if item pick up cancelled
                  if(result) {
                     // restore the inventory to the previous state
                     for(int i = 0; i < inventoryClone.length; i += 1) {
                        if(inventoryClone[i] != null) {
                           if(var1.j.a[i] == null) {
                              var1.j.a[i] = inventoryClone[i];
                           } else {
                              var1.j.a[i].setFromStack(inventoryClone[i]);
                           }
                        } else {
                           var1.j.a[i] = null;
                        }
                     }
                     return;
                  }
                  if(this.a.c == OBlock.K.bA) {
                     var1.a((OStatBase)OAchievementList.g);
                  }

                  if(this.a.c == OItem.aD.bo) {
                     var1.a((OStatBase)OAchievementList.t);
                  }

                  this.bb.a(this, "random.pop", 0.2F, ((this.bL.nextFloat() - this.bL.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                  var1.a((OEntity)this, var2);
                  if(this.a.a <= 0) {
                     this.N();
                  }
               }
            }
         }
      }
   }

   public String Y() {
      return OStatCollector.a("item." + this.a.k());
   }
}
