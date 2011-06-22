import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import net.minecraft.server.MinecraftServer;

public class OEntityPlayerMP extends OEntityPlayer implements OICrafting {
    public ONetServerHandler   a;
    public MinecraftServer     b;
    public OItemInWorldManager c;
    public double              d;
    public double              e;
    public List                f  = new LinkedList();

    public Set                 g  = new HashSet();

    private int                bK = -99999999;
    private int                bL = 60;

    private OItemStack[]       bM = { null, null, null, null, null };

    private int                bN = 0;
    public boolean             h;
    // CanaryMod: Player storage
    private Player             player;

    
    public OEntityPlayerMP(MinecraftServer paramMinecraftServer, OWorld paramOWorld, String paramString, OItemInWorldManager paramOItemInWorldManager) {
        super(paramOWorld);
        paramOItemInWorldManager.a = this;
        c = paramOItemInWorldManager;

        OChunkCoordinates localOChunkCoordinates = paramOWorld.n();
        int i = localOChunkCoordinates.a;
        int j = localOChunkCoordinates.c;
        int k = localOChunkCoordinates.b;

        if (!paramOWorld.t.e) {
            i += bv.nextInt(etc.getInstance().getSpawnProtectionSize() * 2 + 1) - etc.getInstance().getSpawnProtectionSize();
            k = paramOWorld.e(i, j);
            j += bv.nextInt(etc.getInstance().getSpawnProtectionSize() * 2 + 1) - etc.getInstance().getSpawnProtectionSize();
        }
        c(i + 0.5D, k, j + 0.5D, 0.0F, 0.0F);

        b = paramMinecraftServer;
        bs = 0.0F;
        r = paramString;
        bi = 0.0F;

        // CanaryMod: So we don't conflict with runecraft
        c = new Digging((OWorldServer) paramOWorld, this);

        // CanaryMod: Store player
        player = etc.getDataSource().getPlayer(paramString);
        player.setUser(this);

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

    @Override
    public void a(OWorld ow) {
        super.a(ow);
        // CanaryMod: We got a new world, so we get a new item manager
        c = new Digging((OWorldServer) ow, this);
    }
        
    public void o() {
        // CanaryMod: Make sure this gets cast correctly, or mutant puppies will
        // spawn and eat your items.
        k.a((OICrafting) this);

    }

    @Override
    public OItemStack[] i_() {
        return bM;
    }

    @Override
    protected void j_() {
        bi = 0.0F;
    }

    @Override
    public float s() {
        return 1.62F;
    }

    @Override
    public void o_() {
        c.a();

        bL -= 1;
        k.a();

        for (int i = 0; i < 5; i++) {
            OItemStack localOItemStack = b_(i);
            if (localOItemStack != bM[i]) {
                b.b(s).a(this, new OPacket5PlayerInventory(aG, i, localOItemStack));
                bM[i] = localOItemStack;
            }
        }
    }

    public OItemStack b_(int paramInt) {
        if (paramInt == 0)
            return i.b();
        return i.b[(paramInt - 1)];
    }

    @Override
    public void a(OEntity paramOEntity) {
        // CanaryMod: drops inventory on death.
        if (etc.getInstance().isHealthEnabled())
            i.h();
    }

    @Override
    public boolean a(OEntity paramOEntity, int paramInt) {
        if (bL > 0)
            return false;

        if (!b.n) {
            if ((paramOEntity instanceof OEntityPlayer))
                return false;
            if ((paramOEntity instanceof OEntityArrow)) {
                OEntityArrow localOEntityArrow = (OEntityArrow) paramOEntity;
                if ((localOEntityArrow.c instanceof OEntityPlayer))
                    return false;
            }
        }
        return super.a(paramOEntity, paramInt);
    }

    @Override
    protected boolean t() {
        return b.n;
    }

    @Override
    public void b(int paramInt) {
        super.b(paramInt);
    }

    public void a(boolean paramBoolean) {
        super.o_();

        for (int var2 = 0; var2 < this.i.a(); ++var2) {
            OItemStack var3 = this.i.c_(var2);
            if (var3 != null && OItem.c[var3.c].b() && this.a.b() <= 2) {
                OPacket var4 = ((OItemMapBase) OItem.c[var3.c]).b(var3, this.aL, this);
                if (var4 != null)
                    this.a.b(var4);
            }
        }


        if ((paramBoolean) && (!f.isEmpty())) {
            OChunkCoordIntPair localOChunkCoordIntPair = (OChunkCoordIntPair) f.get(0);

            if (localOChunkCoordIntPair != null) {
                int i = 0;

                if (a.b() < 4)
                    i = 1;

                if (i != 0) {
                    OWorldServer ows = b.a(s);
                    f.remove(localOChunkCoordIntPair);
                    a.b(new OPacket51MapChunk(localOChunkCoordIntPair.a * 16, 0, localOChunkCoordIntPair.b * 16, 16, 128, 16, ows));
                    List localList = ows.d(localOChunkCoordIntPair.a * 16, 0, localOChunkCoordIntPair.b * 16, localOChunkCoordIntPair.a * 16 + 16, 128, localOChunkCoordIntPair.b * 16 + 16);
                    for (int j = 0; j < localList.size(); j++)
                        a((OTileEntity) localList.get(j));
                }
            }
        }

        if (this.E) {
            if (this.b.d.a("allow-nether", true)) {
                if (this.aK != null)
                    this.b(this.aK);
                else {
                    this.F += 0.0125F;
                    if (this.F >= 1.0F) {
                        this.F = 1.0F;
                        this.D = 10;
                        this.b.f.f(this);
                    }
                }

                this.E = false;
            }
        } else {
            if (this.F > 0.0F)
                this.F -= 0.05F;

            if (this.F < 0.0F)
                this.F = 0.0F;
        }

        if (D > 0)
            D--;

        // CanaryMod: Update the health
        if (ab != bK) {
         // updates your health when it is changed.
            if (!etc.getInstance().isHealthEnabled()) {
                ab = 20;
                ak = false;
            } else if ((Boolean) manager.callHook(PluginLoader.Hook.HEALTH_CHANGE, getPlayer(), bK, ab))
                ab = bK;
            else
                a.b(new OPacket8UpdateHealth(ab));
            bK = ab;

        }
    }

    private void a(OTileEntity paramOTileEntity) {
        if (paramOTileEntity != null) {
            // CanaryMod: Let plugins know we're showing a sign
            if (paramOTileEntity instanceof OTileEntitySign) {
                Sign sign = new Sign((OTileEntitySign) paramOTileEntity);
                manager.callHook(PluginLoader.Hook.SIGN_SHOW, getPlayer(), sign);
            }

            OPacket localOPacket = paramOTileEntity.e();
            if (localOPacket != null)
                a.b(localOPacket);
        }
    }

    @Override
    public void u() {
        super.u();
    }

    @Override
    public void b(OEntity paramOEntity, int paramInt) {
        if (!paramOEntity.bh) {
            OEntityTracker oet = b.b(s);
            if ((paramOEntity instanceof OEntityItem))
                oet.a(paramOEntity, new OPacket22Collect(paramOEntity.aG, aG));
            if ((paramOEntity instanceof OEntityArrow))
                oet.a(paramOEntity, new OPacket22Collect(paramOEntity.aG, aG));
        }
        super.b(paramOEntity, paramInt);
        k.a();
    }

    @Override
    public void k_() {
        if (!p) {
            q = -1;
            p = true;
            b.b(s).a(this, new OPacket18Animation(this, 1));
        }
    }

    public void w() {
    }

    @Override
    public OEnumStatus a(int paramInt1, int paramInt2, int paramInt3) {
        OEnumStatus localOEnumStatus = super.a(paramInt1, paramInt2, paramInt3);
        if (localOEnumStatus == OEnumStatus.a) {
            OPacket17Sleep packet = new OPacket17Sleep(this, 0, paramInt1, paramInt2, paramInt3);
            b.b(s).a(this, packet);
            a.a(aP, aQ, aR, aV, aW);
            a.b(packet);
        }
        return localOEnumStatus;
    }

    @Override
    public void a(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) {
        if (K())
            b.b(s).b(this, new OPacket18Animation(this, 3));
        super.a(paramBoolean1, paramBoolean2, paramBoolean3);
        if (a != null)
            a.a(aP, aQ, aR, aV, aW);
    }

    @Override
    public void b(OEntity paramOEntity) {
        super.b(paramOEntity);
        a.b(new OPacket39AttachEntity(this, aK));
        a.a(aP, aQ, aR, aV, aW);
    }

    @Override
    protected void a(double paramDouble, boolean paramBoolean) {
    }

    public void b(double paramDouble, boolean paramBoolean) {
        super.a(paramDouble, paramBoolean);
    }

    private void ah() {
        bN = (bN % 100 + 1);
    }

    @Override
    public void b(int paramInt1, int paramInt2, int paramInt3) {
        ah();
        a.b(new OPacket100OpenWindow(bN, 1, "Crafting", 9));
        k = new OContainerWorkbench(i, aL, paramInt1, paramInt2, paramInt3);
        k.f = bN;
        // CanaryMod: Make sure this gets cast correctly, or mutant puppies will
        // spawn and eat your items.
        k.a((OICrafting) this);
    }

    @Override
    public void a(OIInventory paramOIInventory) {
        // CanaryMod: Check if we can open this
        Inventory inv = null;
        String name = paramOIInventory.c();
        if (paramOIInventory instanceof OTileEntityChest) {
            inv = new Chest((OTileEntityChest) paramOIInventory);
            if ((Boolean) manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, getPlayer(), inv))
                return;
        } else if (paramOIInventory instanceof OInventoryLargeChest) {
            inv = new DoubleChest((OInventoryLargeChest) paramOIInventory);
            if ((Boolean) manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, getPlayer(), inv))
                return;
        }

        if (inv != null)
            name = inv.getName();

        ah();
        a.b(new OPacket100OpenWindow(bN, 0, name, paramOIInventory.a()));
        k = new OContainerChest(i, paramOIInventory);
        k.f = bN;
        // CanaryMod: Make sure this gets cast correctly, or mutant puppies will
        // spawn and eat your items.
        k.a((OICrafting) this);
    }

    @Override
    public void a(OTileEntityFurnace paramOTileEntityFurnace) {
        // CanaryMod: Check if we can open this
        Inventory inv = new Furnace(paramOTileEntityFurnace);
        String name = paramOTileEntityFurnace.c();
        if ((Boolean) manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, getPlayer(), inv))
            return;

        if (inv != null)
            name = inv.getName();


        
        ah();
        a.b(new OPacket100OpenWindow(bN, 2, name, paramOTileEntityFurnace.a()));
        k = new OContainerFurnace(i, paramOTileEntityFurnace);
        k.f = bN;
        // CanaryMod: Make sure this gets cast correctly, or mutant puppies will
        // spawn and eat your items.
        k.a((OICrafting) this);
    }

    @Override
    public void a(OTileEntityDispenser paramOTileEntityDispenser) {
        Dispenser dis = new Dispenser(paramOTileEntityDispenser);
        String name = paramOTileEntityDispenser.c();
        if ((Boolean) manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, getPlayer(), dis))
            return;

        if (dis != null)
            name = dis.getName();

        ah();
        a.b(new OPacket100OpenWindow(bN, 3, name, paramOTileEntityDispenser.a()));
        k = new OContainerDispenser(i, paramOTileEntityDispenser);
        k.f = bN;
        // CanaryMod: Make sure this gets cast correctly, or mutant puppies will
        // spawn and eat your items.
        k.a((OICrafting) this);
    }

    public void a(OContainer paramOCraftingInventoryCB, int paramInt, OItemStack paramOItemStack) {
        if ((paramOCraftingInventoryCB.b(paramInt) instanceof OSlotCrafting))
            return;

        if (h)
            return;

        a.b(new OPacket103SetSlot(paramOCraftingInventoryCB.f, paramInt, paramOItemStack));
    }

    public void a(OContainer oc) {
        a(oc, oc.b());
    }

    public void a(OContainer paramOCraftingInventoryCB, List paramList) {
        a.b(new OPacket104WindowItems(paramOCraftingInventoryCB.f, paramList));
        a.b(new OPacket103SetSlot(-1, -1, i.j()));
    }

    public void a(OContainer paramOCraftingInventoryCB, int paramInt1, int paramInt2) {
        a.b(new OPacket105UpdateProgressbar(paramOCraftingInventoryCB.f, paramInt1, paramInt2));
    }

    @Override
    public void a(OItemStack paramOItemStack) {
    }

    @Override
    public void x() {
        a.b(new OPacket101CloseWindow(k.f));
        z();
    }

    public void y() {
        if (h)
            return;
        a.b(new OPacket103SetSlot(-1, -1, i.j()));
    }

    public void z() {
        k.a((OEntityPlayer)this);
        k = j;
    }

    public void a(float paramFloat1, float paramFloat2, boolean paramBoolean1, boolean paramBoolean2, float paramFloat3, float paramFloat4) {
        az = paramFloat1;
        aA = paramFloat2;
        aC = paramBoolean1;
        e(paramBoolean2);
        aW = paramFloat3;
        aV = paramFloat4;
    }

    public void a(OStatBase var1, int var2) {
        if (var1 != null) {
            if (!var1.g) {
                while (var2 > 100) {
                    this.a.b((OPacket) (new OPacket200Statistic(var1.e, 100)));
                    var2 -= 100;
                }
                this.a.b((OPacket) (new OPacket200Statistic(var1.e, var2)));
            }

        }
    }

    public void A() {
        if (this.aK != null)
            this.b(this.aK);

        if (this.aJ != null)
            this.aJ.b((OEntity) this);

        if (this.z)
            this.a(true, false, false);

    }

    public void B() {
        this.bK = -99999999;
    }

    public void a(String var1) {
        OStringTranslate var2 = OStringTranslate.a();
        String var3 = var2.a(var1);
        this.a.b((OPacket) (new OPacket3Chat(var3)));
    }
}
