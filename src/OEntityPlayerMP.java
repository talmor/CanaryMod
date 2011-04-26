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

    private int                bF = -99999999;
    private int                bG = 60;

    private OItemStack[]       bH = { null, null, null, null, null };

    private int                bI = 0;
    public boolean             h;
    // CanaryMod: Player storage
    private Player             player;

    
    public OEntityPlayerMP(MinecraftServer paramMinecraftServer, OWorld paramOWorld, String paramString, OItemInWorldManager paramOItemInWorldManager) {
        super(paramOWorld);

        OChunkCoordinates localOChunkCoordinates = paramOWorld.n();
        int i = localOChunkCoordinates.a;
        int j = localOChunkCoordinates.c;
        int k = localOChunkCoordinates.b;

        if (!paramOWorld.o.e) {
            i += br.nextInt(etc.getInstance().getSpawnProtectionSize() * 2 + 1) - etc.getInstance().getSpawnProtectionSize();
            k = paramOWorld.e(i, j);
            j += br.nextInt(etc.getInstance().getSpawnProtectionSize() * 2 + 1) - etc.getInstance().getSpawnProtectionSize();
        }
        c(i + 0.5D, k, j + 0.5D, 0.0F, 0.0F);

        b = paramMinecraftServer;
        bo = 0.0F;
        paramOItemInWorldManager.a = this;
        r = paramString;
        c = paramOItemInWorldManager;
        be = 0.0F;

        // CanaryMod: So we don't conflict with runecraft
        c = new Digging(paramOWorld, this);

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
        
    public void o() {
        // CanaryMod: Make sure this gets cast correctly, or mutant puppies will
        // spawn and eat your items.
        k.a((OICrafting) this);

    }

    @Override
    public OItemStack[] i_() {
        return bH;
    }

    @Override
    protected void j_() {
        be = 0.0F;
    }

    @Override
    public float s() {
        return 1.62F;
    }

    @Override
    public void p_() {
        c.a();

        bG -= 1;
        k.a();

        for (int i = 0; i < 5; i++) {
            OItemStack localOItemStack = b_(i);
            if (localOItemStack != bH[i]) {
                b.k.a(this, new OPacket5PlayerInventory(aC, i, localOItemStack));
                bH[i] = localOItemStack;
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
        if (bG > 0)
            return false;

        if (!b.n) {
            if ((paramOEntity instanceof OEntityPlayer))
                return false;
            if ((paramOEntity instanceof OEntityArrow)) {
                OEntityArrow localOEntityArrow = (OEntityArrow) paramOEntity;
                if ((localOEntityArrow.b instanceof OEntityPlayer))
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
        super.p_();

        if ((paramBoolean) && (!f.isEmpty())) {
            OChunkCoordIntPair localOChunkCoordIntPair = (OChunkCoordIntPair) f.get(0);

            if (localOChunkCoordIntPair != null) {
                int i = 0;

                if (a.b() < 2)
                    i = 1;

                if (i != 0) {
                    f.remove(localOChunkCoordIntPair);
                    a.b(new OPacket51MapChunk(localOChunkCoordIntPair.a * 16, 0, localOChunkCoordIntPair.b * 16, 16, 128, 16, b.e));
                    List localList = b.e.d(localOChunkCoordIntPair.a * 16, 0, localOChunkCoordIntPair.b * 16, localOChunkCoordIntPair.a * 16 + 16, 128, localOChunkCoordIntPair.b * 16 + 16);
                    for (int j = 0; j < localList.size(); j++)
                        a((OTileEntity) localList.get(j));
                }
            }
        }

        // CanaryMod: Update the health
        if (X != bF) {
         // updates your health when it is changed.
            if (!etc.getInstance().isHealthEnabled()) {
                X = 20;
                ag = false;
            } else if ((Boolean) manager.callHook(PluginLoader.Hook.HEALTH_CHANGE, getPlayer(), bF, X))
                X = bF;
            else
                a.b(new OPacket8UpdateHealth(X));
            bF = X;

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
        if (!paramOEntity.bd) {
            if ((paramOEntity instanceof OEntityItem))
                b.k.a(paramOEntity, new OPacket22Collect(paramOEntity.aC, aC));
            if ((paramOEntity instanceof OEntityArrow))
                b.k.a(paramOEntity, new OPacket22Collect(paramOEntity.aC, aC));
        }
        super.b(paramOEntity, paramInt);
        k.a();
    }

    @Override
    public void k_() {
        if (!p) {
            q = -1;
            p = true;
            b.k.a(this, new OPacket18Animation(this, 1));
        }
    }

    public void w() {
    }

    @Override
    public OEnumStatus a(int paramInt1, int paramInt2, int paramInt3) {
        OEnumStatus localOEnumStatus = super.a(paramInt1, paramInt2, paramInt3);
        if (localOEnumStatus == OEnumStatus.a)
            b.k.a(this, new OPacket17Sleep(this, 0, paramInt1, paramInt2, paramInt3));
        return localOEnumStatus;
    }

    @Override
    public void a(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) {
        if (I())
            b.k.b(this, new OPacket18Animation(this, 3));
        super.a(paramBoolean1, paramBoolean2, paramBoolean3);
        a.a(aL, aM, aN, aR, aS);
    }

    @Override
    public void b(OEntity paramOEntity) {
        super.b(paramOEntity);
        a.b(new OPacket39AttachEntity(this, aG));
        a.a(aL, aM, aN, aR, aS);
    }

    @Override
    protected void a(double paramDouble, boolean paramBoolean) {
    }

    public void b(double paramDouble, boolean paramBoolean) {
        super.a(paramDouble, paramBoolean);
    }

    private void af() {
        bI = (bI % 100 + 1);
    }

    @Override
    public void b(int paramInt1, int paramInt2, int paramInt3) {
        af();
        a.b(new OPacket100OpenWindow(bI, 1, "Crafting", 9));
        k = new OContainerWorkbench(i, aH, paramInt1, paramInt2, paramInt3);
        k.f = bI;
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

        af();
        a.b(new OPacket100OpenWindow(bI, 0, name, paramOIInventory.a()));
        k = new OContainerChest(i, paramOIInventory);
        k.f = bI;
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


        
        af();
        a.b(new OPacket100OpenWindow(bI, 2, name, paramOTileEntityFurnace.a()));
        k = new OContainerFurnace(i, paramOTileEntityFurnace);
        k.f = bI;
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

        af();
        a.b(new OPacket100OpenWindow(bI, 3, name, paramOTileEntityDispenser.a()));
        k = new OContainerDispenser(i, paramOTileEntityDispenser);
        k.f = bI;
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
        av = paramFloat1;
        aw = paramFloat2;
        ay = paramBoolean1;
        e(paramBoolean2);
        aS = paramFloat3;
        aR = paramFloat4;
    }

    public void a(OStatBase var1, int var2) {
        if (var1 != null) {
            if (!var1.g) {
                while (var2 > 100) {
                    this.a.b((OPacket) (new OPacket200IncrementStatistic(var1.e, 100)));
                    var2 -= 100;
                }
                this.a.b((OPacket) (new OPacket200IncrementStatistic(var1.e, var2)));
            }

        }
    }
}