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
   private int cb = -99999999;
   private int cc = -99999999;
   private boolean cd = true;
   private int ce = -99999999;
   private int cf = 60;
   private OItemStack[] cg = new OItemStack[] { null, null, null, null, null };
   private int ch = 0;
   public boolean h;
   public int i;
   // CanaryMod: Player storage
   private Player player;

   public OEntityPlayerMP(MinecraftServer var1, OWorld var2, String var3, OItemInWorldManager var4) {
      super(var2);
      var4.b = this;
      this.c = var4;
      OChunkCoordinates var5 = var2.m();
      int var6 = var5.a;
      int var7 = var5.c;
      int var8 = var5.b;
      if(!var2.y.e) {
         var6 += this.bL.nextInt(20) - 10;
         var8 = var2.f(var6, var7);
         var7 += this.bL.nextInt(20) - 10;
      }

      this.c((double)var6 + 0.5D, (double)var8, (double)var7 + 0.5D, 0.0F, 0.0F);
      this.b = var1;
      this.bI = 0.0F;
      this.u = var3;
      this.by = 0.0F;

      // CanaryMod: So we don't conflict with runecraft
      c = new Digging((OWorldServer)var2, this);

      // CanaryMod: Store player
      player = etc.getDataSource().getPlayer(var3);
      player.setUser(this);
   }

   public void a(ONBTTagCompound var1) {
      super.a(var1);
      if(var1.b("playerGameType")) {
         this.c.a(var1.e("playerGameType"));
      }
   }

   public void b(ONBTTagCompound var1) {
      super.b(var1);
      var1.a("playerGameType", this.c.a());
   }

   public void a(OWorld var1) {
      super.a(var1);
      // CanaryMod: New world, new manager.
      this.c = new Digging((OWorldServer)var1, this);
   }

   public void o() {
      this.l.a((OICrafting)this);
   }

   public OItemStack[] l_() {
      return this.cg;
   }

   protected void m_() {
      this.by = 0.0F;
   }

   public float t() {
      return 1.62F;
   }

   public void s_() {
      this.c.c();
      --this.cf;
      this.l.a();

      for(int var1 = 0; var1 < 5; ++var1) {
         OItemStack var2 = this.b(var1);
         if(var2 != this.cg[var1]) {
            this.b.b(this.v).a(this, new OPacket5PlayerInventory(this.aW, var1, var2));
            this.cg[var1] = var2;
         }
      }

   }

   public OItemStack b(int var1) {
      return var1 == 0?this.j.b():this.j.b[var1 - 1];
   }

   public void a(ODamageSource var1) {
      this.b.f.a((OPacket)(new OPacket3Chat(var1.a((OEntityPlayer)this))));
      this.j.j();
   }

   public boolean a(ODamageSource var1, int var2) {
      if(this.cf > 0) {
         return false;
      } else {
         if(!this.b.n && var1 instanceof OEntityDamageSource) {
            OEntity var3 = var1.a();
            if(var3 instanceof OEntityPlayer) {
               return false;
            }

            if(var3 instanceof OEntityArrow) {
               OEntityArrow var4 = (OEntityArrow)var3;
               if(var4.c instanceof OEntityPlayer) {
                  return false;
               }
            }
         }

         return super.a(var1, var2);
      }
   }

   protected boolean n_() {
      return this.b.n;
   }

   public void c(int var1) {
      super.c(var1);
   }

   public void b(boolean var1) {
      super.s_();

      for(int var2 = 0; var2 < this.j.a(); ++var2) {
         OItemStack var3 = this.j.b_(var2);
         if(var3 != null && OItem.c[var3.c].i_() && this.a.b() <= 2) {
            OPacket var4 = ((OItemMapBase)OItem.c[var3.c]).c(var3, this.bb, this);
            if(var4 != null) {
               this.a.b(var4);
            }
         }
      }

      if(var1 && !this.f.isEmpty()) {
         OChunkCoordIntPair var7 = (OChunkCoordIntPair)this.f.get(0);
         if(var7 != null) {
            boolean var9 = false;
            if(this.a.b() < 4) {
               var9 = true;
            }

            if(var9) {
               OWorldServer var10 = this.b.a(this.v);
               this.f.remove(var7);
               ONetServerHandler var10000 = this.a;
               int var10003 = var7.a * 16;
               int var10005 = var7.b * 16;
               OPacket51MapChunk var10001 = new OPacket51MapChunk(var10003, 0, var10005, 16, 128, 16, var10);
               var10.getClass();
               var10000.b((OPacket)var10001);
               int var8 = var7.a * 16;
               var10003 = var7.b * 16;
               int var10004 = var7.a * 16 + 16;
               var10.getClass();
               List var5 = var10.d(var8, 0, var10003, var10004, 128, var7.b * 16 + 16);

               for(int var6 = 0; var6 < var5.size(); ++var6) {
                  this.a((OTileEntity)var5.get(var6));
               }
            }
         }
      }

      if(this.I) {
         if(this.b.d.a("allow-nether", true)) {
            if(this.l != this.k) {
               this.x();
            }

            if(this.ba != null) {
               this.a(this.ba);
            } else {
               this.J += 0.0125F;
               if(this.J >= 1.0F) {
                  this.J = 1.0F;
                  this.H = 10;
                  // CanaryMod onPortalUse
                  if(!(Boolean)etc.getLoader().callHook(PluginLoader.Hook.PORTAL_USE, player, player.getWorld())) {
                     this.b.f.f(this);
                     this.ce = -1;
                     this.cb = -1;
                     this.cc = -1;
                  }
               }
            }
            this.I = false;
         } else {
            this.I = false;
         }
      } else {
         if(this.J > 0.0F) {
            this.J -= 0.05F;
         }

         if(this.J < 0.0F) {
            this.J = 0.0F;
         }
      }

      if(this.H > 0) {
         --this.H;
      }


      /*// CanaryMod: Update the health
      if(this.an != this.cb) {
         // updates your health when it is changed.
         if(!etc.getInstance().isHealthEnabled()) {
            an = 20;
            aw = false;
         } else if((Boolean)manager.callHook(PluginLoader.Hook.HEALTH_CHANGE, getPlayer(), cb, an))
            an = cb;
         else if(this.an != this.cb || this.cc != this.m.a() || this.m.c() == 0.0F != this.cd) {
            this.a.b((OPacket)(new OPacket8UpdateHealth(this.an, this.m.a(), this.m.c())));
            this.cb = this.an;
            this.cc = this.m.a();
            this.cd = this.m.c() == 0.0F;
         }
         this.cb = this.an;
      }*/
      
      // CanaryMod: Update the health
      if(this.an != this.cb) {
         // updates your health when it is changed.
         if(!etc.getInstance().isHealthEnabled()) {
            an = 20;
            aw = false;
         } else if((Boolean)manager.callHook(PluginLoader.Hook.HEALTH_CHANGE, getPlayer(), cb, an))
            an = cb;
      }

      // CanaryMod: Update the health or Hunger bar
      if((this.an != this.cb) || (this.cc != this.m.a()) || ((this.m.c() == 0.0F) != this.cd)) {
         this.a.b((OPacket)(new OPacket8UpdateHealth(this.an, this.m.a(), this.m.c())));
         this.cb = this.an;
         this.cc = this.m.a();
         this.cd = this.m.c() == 0.0F;
      }
      
      if(this.N != this.ce) {
         this.ce = this.N;
         this.a.b((OPacket)(new OPacket43Experience(this.L, this.N, this.M)));
      }

   }

   private void a(OTileEntity var1) {
      if(var1 != null) {
         // CanaryMod: Let plugins know we're showing a sign
         if(var1 instanceof OTileEntitySign) {
            Sign sign = new Sign((OTileEntitySign)var1);
            manager.callHook(PluginLoader.Hook.SIGN_SHOW, getPlayer(), sign);
         }

         OPacket var2 = var1.l();
         if(var2 != null) {
            this.a.b(var2);
         }
      }

   }

   public void a(OEntity var1, int var2) {
      if(!var1.bx) {
         OEntityTracker var3 = this.b.b(this.v);
         if(var1 instanceof OEntityItem) {
            var3.a(var1, new OPacket22Collect(var1.aW, this.aW));
         }

         if(var1 instanceof OEntityArrow) {
            var3.a(var1, new OPacket22Collect(var1.aW, this.aW));
         }

         if(var1 instanceof OEntityXPOrb) {
            var3.a(var1, new OPacket22Collect(var1.aW, this.aW));
         }
      }

      super.a(var1, var2);
      this.l.a();
   }

   public void v() {
      if(!this.s) {
         this.t = -1;
         this.s = true;
         OEntityTracker var1 = this.b.b(this.v);
         var1.a(this, new OPacket18Animation(this, 1));
      }

   }

   public void w() {
   }

   public OEnumStatus a(int var1, int var2, int var3) {
      OEnumStatus var4 = super.a(var1, var2, var3);
      if(var4 == OEnumStatus.a) {
         OEntityTracker var5 = this.b.b(this.v);
         OPacket17Sleep var6 = new OPacket17Sleep(this, 0, var1, var2, var3);
         var5.a(this, var6);
         this.a.a(this.bf, this.bg, this.bh, this.bl, this.bm);
         this.a.b((OPacket)var6);
      }

      return var4;
   }

   public void a(boolean var1, boolean var2, boolean var3) {
      if(this.P()) {
         OEntityTracker var4 = this.b.b(this.v);
         var4.b(this, new OPacket18Animation(this, 3));
      }

      super.a(var1, var2, var3);
      if(this.a != null) {
         this.a.a(this.bf, this.bg, this.bh, this.bl, this.bm);
      }

   }

   public void a(OEntity var1) {
      super.a(var1);
      this.a.b((OPacket)(new OPacket39AttachEntity(this, this.ba)));
      this.a.a(this.bf, this.bg, this.bh, this.bl, this.bm);
      if(etc.getInstance().isHealthEnabled())
         this.j.h();
   }

   protected void a(double var1, boolean var3) {
   }

   public void b(double var1, boolean var3) {
      super.a(var1, var3);
   }

   private void au() {
      this.ch = this.ch % 100 + 1;
   }

   public void b(int var1, int var2, int var3) {
      this.au();
      this.a.b((OPacket)(new OPacket100OpenWindow(this.ch, 1, "Crafting", 9)));
      this.l = new OContainerWorkbench(this.j, this.bb, var1, var2, var3);
      this.l.f = this.ch;
      this.l.a((OICrafting)this);
   }

   public void a(OIInventory var1) {
      // CanaryMod: Check if we can open this
      Inventory inv = null;
      String name = var1.c();
      if(var1 instanceof OTileEntityChest) {
         inv = new Chest((OTileEntityChest)var1);
         if((Boolean)manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, getPlayer(), inv))
            return;
      } else if(var1 instanceof OInventoryLargeChest) {
         inv = new DoubleChest((OInventoryLargeChest)var1);
         if((Boolean)manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, getPlayer(), inv))
            return;
      }

      if(inv != null)
         name = inv.getName();

      this.au();
      this.a.b((OPacket)(new OPacket100OpenWindow(this.ch, 0, var1.c(), var1.a())));
      this.l = new OContainerChest(this.j, var1);
      this.l.f = this.ch;
      this.l.a((OICrafting)this);
   }

   public void a(OTileEntityFurnace var1) {
      Inventory inv = new Furnace(var1);
      String name = var1.c();
      if((Boolean)manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, getPlayer(), inv))
         return;
      if(inv != null)
         name = inv.getName();
      this.au();
      this.a.b((OPacket)(new OPacket100OpenWindow(this.ch, 2, var1.c(), var1.a())));
      this.l = new OContainerFurnace(this.j, var1);
      this.l.f = this.ch;
      this.l.a((OICrafting)this);
   }

   public void a(OTileEntityDispenser var1) {
      Dispenser dis = new Dispenser(var1);
      String name = var1.c();
      if((Boolean)manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, getPlayer(), dis))
         return;

      if(dis != null)
         name = dis.getName();

      this.au();
      this.a.b((OPacket)(new OPacket100OpenWindow(this.ch, 3, var1.c(), var1.a())));
      this.l = new OContainerDispenser(this.j, var1);
      this.l.f = this.ch;
      this.l.a((OICrafting)this);
   }

   public void a(OContainer var1, int var2, OItemStack var3) {
      if(!(var1.b(var2) instanceof OSlotCrafting)) {
         if(!this.h) {
            this.a.b((OPacket)(new OPacket103SetSlot(var1.f, var2, var3)));
         }
      }
   }

   public void a(OContainer var1) {
      this.a(var1, var1.b());
   }

   public void a(OContainer var1, List var2) {
      this.a.b((OPacket)(new OPacket104WindowItems(var1.f, var2)));
      this.a.b((OPacket)(new OPacket103SetSlot(-1, -1, this.j.l())));
   }

   public void a(OContainer var1, int var2, int var3) {
      this.a.b((OPacket)(new OPacket105UpdateProgressbar(var1.f, var2, var3)));
   }

   public void a(OItemStack var1) {
   }

   public void x() {
      this.a.b((OPacket)(new OPacket101CloseWindow(this.l.f)));
      this.z();
   }

   public void y() {
      if(!this.h) {
         this.a.b((OPacket)(new OPacket103SetSlot(-1, -1, this.j.l())));
      }
   }

   public void z() {
      this.l.a((OEntityPlayer)this);
      this.l = this.k;
   }

   public void a(float var1, float var2, boolean var3, boolean var4, float var5, float var6) {
      this.aP = var1;
      this.aQ = var2;
      this.aS = var3;
      this.f(var4);
      this.bm = var5;
      this.bl = var6;
   }

   public void a(OStatBase var1, int var2) {
      if(var1 != null) {
         if(!var1.g) {
            while(var2 > 100) {
               this.a.b((OPacket)(new OPacket200Statistic(var1.e, 100)));
               var2 -= 100;
            }

            this.a.b((OPacket)(new OPacket200Statistic(var1.e, var2)));
         }

      }
   }

   public void A() {
      if(this.ba != null) {
         this.a(this.ba);
      }

      if(this.aZ != null) {
         this.aZ.a((OEntity)this);
      }

      if(this.D) {
         this.a(true, false, false);
      }

   }

   public void B() {
      this.cb = -99999999;
   }

   public void a(String var1) {
      OStringTranslate var2 = OStringTranslate.a();
      String var3 = var2.a(var1);
      this.a.b((OPacket)(new OPacket3Chat(var3)));
   }

   protected void C() {
      this.a.b((OPacket)(new OPacket38EntityStatus(this.aW, (byte)9)));
      super.C();
   }

   public void a(OItemStack var1, int var2) {
      super.a(var1, var2);
      if(var1 != null && var1.a() != null && var1.a().b(var1) == OEnumAction.b) {
         OEntityTracker var3 = this.b.b(this.v);
         var3.b(this, new OPacket18Animation(this, 5));
      }

   }

   protected void a(OPotionEffect var1) {
      super.a(var1);
      this.a.b((OPacket)(new OPacket41EntityEffect(this.aW, var1)));
   }

   protected void b(OPotionEffect var1) {
      super.b(var1);
      this.a.b((OPacket)(new OPacket41EntityEffect(this.aW, var1)));
   }

   protected void c(OPotionEffect var1) {
      super.c(var1);
      this.a.b((OPacket)(new OPacket42RemoveEntityEffect(this.aW, var1)));
   }

   public Player getPlayer() {
      return player;
   }

   /**
    * Reloads the player
    */
   public void reloadPlayer() {
      player = etc.getDataSource().getPlayer(u);
      player.setUser(this);
   }
}
