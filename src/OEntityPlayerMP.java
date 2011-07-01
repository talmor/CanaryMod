
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import net.minecraft.server.MinecraftServer;

public class OEntityPlayerMP extends OEntityPlayer implements OICrafting {

   public ONetServerHandler a;
   public MinecraftServer b;
   public OItemInWorldManager c;
   public double d;
   public double e;
   public List f = new LinkedList();
   public Set g = new HashSet();
   private int bK = -99999999;
   private int bL = 60;
   private OItemStack[] bM = new OItemStack[]{null, null, null, null, null};
   private int bN = 0;
   public boolean h;
    // CanaryMod: Player storage
    private Player player;


   public OEntityPlayerMP(MinecraftServer var1, OWorld var2, String var3, OItemInWorldManager var4) {
      super(var2);
      var4.a = this;
      this.c = var4;
      OChunkCoordinates var5 = var2.n();
      int var6 = var5.a;
      int var7 = var5.c;
      int var8 = var5.b;
      if(!var2.t.e) {
         var6 += this.bv.nextInt(20) - 10;
         var8 = var2.f(var6, var7);
         var7 += this.bv.nextInt(20) - 10;
      }

      this.c((double)var6 + 0.5D, (double)var8, (double)var7 + 0.5D, 0.0F, 0.0F);
      this.b = var1;
      this.bs = 0.0F;
      this.r = var3;
      this.bi = 0.0F;
   }

   public void a(OWorld var1) {
      super.a(var1);
      this.c = new OItemInWorldManager((OWorldServer)var1);
      this.c.a = this;
   }

   public void o() {
      // CanaryMod: Make sure this gets cast correctly, or mutant puppies will
      // spawn and eat your items.
      this.k.a((OICrafting) this);
   }

   public OItemStack[] i_() {
      return this.bM;
   }

   protected void j_() {
      this.bi = 0.0F;
   }

   public float s() {
      return 1.62F;
   }

   public void o_() {
      this.c.a();
      --this.bL;
      this.k.a();

      for(int var1 = 0; var1 < 5; ++var1) {
         OItemStack var2 = this.b_(var1);
         if(var2 != this.bM[var1]) {
            this.b.b(this.s).a(this, new OPacket5PlayerInventory(this.aG, var1, var2));
            this.bM[var1] = var2;
         }
      }

   }

   public OItemStack b_(int var1) {
      return var1 == 0?this.i.b():this.i.b[var1 - 1];
   }

   public void a(OEntity var1) {
      // CanaryMod: drops inventory on death.
      if (etc.getInstance().isHealthEnabled())
         this.i.h();
   }

   public boolean a(OEntity var1, int var2) {
      if(this.bL > 0) {
         return false;
      } else {
         if(!this.b.n) {
            if(var1 instanceof OEntityPlayer) {
               return false;
            }

            if(var1 instanceof OEntityArrow) {
               OEntityArrow var3 = (OEntityArrow)var1;
               if(var3.c instanceof OEntityPlayer) {
                  return false;
               }
            }
         }

         return super.a(var1, var2);
      }
   }

   protected boolean t() {
      return this.b.n;
   }

   public void b(int var1) {
      super.b(var1);
   }

   public void a(boolean var1) {
      super.o_();

      for(int var2 = 0; var2 < this.i.a(); ++var2) {
         OItemStack var3 = this.i.c_(var2);
         if(var3 != null && OItem.c[var3.c].b() && this.a.b() <= 2) {
            OPacket var4 = ((OItemMapBase)OItem.c[var3.c]).b(var3, this.aL, this);
            if(var4 != null) {
               this.a.b(var4);
            }
         }
      }

      if(var1 && !this.f.isEmpty()) {
         OChunkCoordIntPair var9 = (OChunkCoordIntPair)this.f.get(0);
         if(var9 != null) {
            boolean var7 = false;
            if(this.a.b() < 4) {
               var7 = true;
            }

            if(var7) {
               OWorldServer var8 = this.b.a(this.s);
               this.f.remove(var9);
               this.a.b(new OPacket51MapChunk(var9.a * 16, 0, var9.b * 16, 16, 128, 16, var8));
               List var5 = var8.d(var9.a * 16, 0, var9.b * 16, var9.a * 16 + 16, 128, var9.b * 16 + 16);

               for(int var6 = 0; var6 < var5.size(); ++var6) {
                  this.a((OTileEntity)var5.get(var6));
               }
            }
         }
      }

      if(this.E) {
         if(this.b.d.a("allow-nether", true)) {
            if(this.aK != null) {
               this.b(this.aK);
            } else {
               this.F += 0.0125F;
               if(this.F >= 1.0F) {
                  this.F = 1.0F;
                  this.D = 10;
                  this.b.f.f(this);
               }
            }

            this.E = false;
         }
      } else {
         if(this.F > 0.0F) {
            this.F -= 0.05F;
         }

         if(this.F < 0.0F) {
            this.F = 0.0F;
         }
      }

      if(this.D > 0) {
         --this.D;
      }

      // CanaryMod: Update the health
      if(this.ab != this.bK) {
         // updates your health when it is changed.
         if (!etc.getInstance().isHealthEnabled()) {
            ab = 20;
            ak = false;
         } else if ((Boolean) manager.callHook(PluginLoader.Hook.HEALTH_CHANGE, getPlayer(), bK, ab))
            ab = bK;
         else
            this.a.b(new OPacket8UpdateHealth(this.ab));
         this.bK = this.ab;
      }

   }

   private void a(OTileEntity var1) {
      if(var1 != null) {
         // CanaryMod: Let plugins know we're showing a sign
         if (var1 instanceof OTileEntitySign) {
            Sign sign = new Sign((OTileEntitySign) var1);
            manager.callHook(PluginLoader.Hook.SIGN_SHOW, getPlayer(), sign);
         }

         OPacket var2 = var1.e();
         if(var2 != null) {
            this.a.b(var2);
         }
      }

   }

   public void u() {
      super.u();
   }

   public void b(OEntity var1, int var2) {
      if(!var1.bh) {
         OEntityTracker var3 = this.b.b(this.s);
         if(var1 instanceof OEntityItem) {
            var3.a(var1, new OPacket22Collect(var1.aG, this.aG));
         }

         if(var1 instanceof OEntityArrow) {
            var3.a(var1, new OPacket22Collect(var1.aG, this.aG));
         }
      }

      super.b(var1, var2);
      this.k.a();
   }

   public void k_() {
      if(!this.p) {
         this.q = -1;
         this.p = true;
         OEntityTracker var1 = this.b.b(this.s);
         var1.a(this, new OPacket18Animation(this, 1));
      }

   }

   public void w() {
   }

   public OEnumStatus a(int var1, int var2, int var3) {
      OEnumStatus var4 = super.a(var1, var2, var3);
      if(var4 == OEnumStatus.a) {
         OEntityTracker var5 = this.b.b(this.s);
         OPacket17Sleep var6 = new OPacket17Sleep(this, 0, var1, var2, var3);
         var5.a(this, var6);
         this.a.a(this.aP, this.aQ, this.aR, this.aV, this.aW);
         this.a.b(var6);
      }

      return var4;
   }

   public void a(boolean var1, boolean var2, boolean var3) {
      if(this.K()) {
         OEntityTracker var4 = this.b.b(this.s);
         var4.b(this, new OPacket18Animation(this, 3));
      }

      super.a(var1, var2, var3);
      if(this.a != null) {
         this.a.a(this.aP, this.aQ, this.aR, this.aV, this.aW);
      }

   }

   public void b(OEntity var1) {
      super.b(var1);
      this.a.b(new OPacket39AttachEntity(this, this.aK));
      this.a.a(this.aP, this.aQ, this.aR, this.aV, this.aW);
   }

   protected void a(double var1, boolean var3) {
   }

   public void b(double var1, boolean var3) {
      super.a(var1, var3);
   }

   private void ah() {
      this.bN = this.bN % 100 + 1;
   }

   public void b(int var1, int var2, int var3) {
      this.ah();
      this.a.b(new OPacket100OpenWindow(this.bN, 1, "Crafting", 9));
      this.k = new OContainerWorkbench(this.i, this.aL, var1, var2, var3);
      this.k.f = this.bN;
      // CanaryMod: Make sure this gets cast correctly, or mutant puppies will
      // spawn and eat your items.
      this.k.a((OICrafting) this);
   }

   public void a(OIInventory var1) {
      // CanaryMod: Check if we can open this
      Inventory inv = null;
      String name = var1.c();
      if (var1 instanceof OTileEntityChest) {
         inv = new Chest((OTileEntityChest) var1);
         if ((Boolean) manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, getPlayer(), inv))
            return;
      } else if (var1 instanceof OInventoryLargeChest) {
          inv = new DoubleChest((OInventoryLargeChest) var1);
          if ((Boolean) manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, getPlayer(), inv))
             return;
      }

      if (inv != null)
         name = inv.getName();

      this.ah();
      this.a.b(new OPacket100OpenWindow(this.bN, 0, name, var1.a()));
      this.k = new OContainerChest(this.i, var1);
      this.k.f = this.bN;
      // CanaryMod: Make sure this gets cast correctly, or mutant puppies will
      // spawn and eat your items.
      this.k.a((OICrafting) this);
   }

   public void a(OTileEntityFurnace var1) {
      // CanaryMod: Check if we can open this
      Inventory inv = new Furnace(var1);
      String name = var1.c();
      if ((Boolean) manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, getPlayer(), inv))
         return;

      if (inv != null)
         name = inv.getName();

      this.ah();
      this.a.b(new OPacket100OpenWindow(this.bN, 2, name, var1.a()));
      this.k = new OContainerFurnace(this.i, var1);
      this.k.f = this.bN;
      // CanaryMod: Make sure this gets cast correctly, or mutant puppies will
      // spawn and eat your items.
      this.k.a((OICrafting) this);
   }

   public void a(OTileEntityDispenser var1) {
      Dispenser dis = new Dispenser(var1);
      String name = var1.c();
      if ((Boolean) manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, getPlayer(), dis))
         return;

      if (dis != null)
         name = dis.getName();

      this.ah();
      this.a.b(new OPacket100OpenWindow(this.bN, 3, var1.c(), var1.a()));
      this.k = new OContainerDispenser(this.i, var1);
      this.k.f = this.bN;
      // CanaryMod: Make sure this gets cast correctly, or mutant puppies will
      // spawn and eat your items.
      this.k.a((OICrafting) this);
   }

   public void a(OContainer var1, int var2, OItemStack var3) {
      if(!(var1.b(var2) instanceof OSlotCrafting)) {
         if(!this.h) {
            this.a.b(new OPacket103SetSlot(var1.f, var2, var3));
         }
      }
   }

   public void a(OContainer var1) {
      this.a(var1, var1.b());
   }

   public void a(OContainer var1, List var2) {
      this.a.b(new OPacket104WindowItems(var1.f, var2));
      this.a.b(new OPacket103SetSlot(-1, -1, this.i.j()));
   }

   public void a(OContainer var1, int var2, int var3) {
      this.a.b(new OPacket105UpdateProgressbar(var1.f, var2, var3));
   }

   public void a(OItemStack var1) {
   }

   public void x() {
      this.a.b(new OPacket101CloseWindow(this.k.f));
      this.z();
   }

   public void y() {
      if(!this.h) {
         this.a.b(new OPacket103SetSlot(-1, -1, this.i.j()));
      }
   }

   public void z() {
      // CanaryMod: Forced cast
      this.k.a((OEntityPlayer) this);
      this.k = this.j;
   }

   public void a(float var1, float var2, boolean var3, boolean var4, float var5, float var6) {
      this.az = var1;
      this.aA = var2;
      this.aC = var3;
      this.e(var4);
      this.aW = var5;
      this.aV = var6;
   }

   public void a(OStatBase var1, int var2) {
      if(var1 != null) {
         if(!var1.g) {
            while(var2 > 100) {
               this.a.b(new OPacket200Statistic(var1.e, 100));
               var2 -= 100;
            }

            this.a.b(new OPacket200Statistic(var1.e, var2));
         }

      }
   }

   public void A() {
      if(this.aK != null) {
         this.b(this.aK);
      }

      if(this.aJ != null) {
         this.aJ.b(this);
      }

      if(this.z) {
         this.a(true, false, false);
      }

   }

   public void B() {
      this.bK = -99999999;
   }

   public void a(String var1) {
      OStringTranslate var2 = OStringTranslate.a();
      String var3 = var2.a(var1);
      this.a.b(new OPacket3Chat(var3));
   }

   /**
     * Returns the player
     *
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Reloads the player
     */
    public void reloadPlayer() {
        player = etc.getDataSource().getPlayer(r);
        player.setUser(this);
    }
}
