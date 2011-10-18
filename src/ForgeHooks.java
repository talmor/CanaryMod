

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class ForgeHooks
{
  static LinkedList<ForgeICraftingHandler> craftingHandlers = new LinkedList();

  static LinkedList<ForgeDestroyToolHandler> destroyToolHandlers = new LinkedList();

  static LinkedList<ForgeIBonemealHandler> bonemealHandlers = new LinkedList();

  static LinkedList<ForgeISleepHandler> sleepHandlers = new LinkedList();

  static List<ProbableItem> plantGrassList = new ArrayList();
  static int plantGrassWeight;
  static List<ProbableItem> seedGrassList;
  static int seedGrassWeight;
  public static final int majorVersion = 1;
  public static final int minorVersion = 1;
  public static final int revisionVersion = 1;
  static boolean toolInit;
  static HashMap toolClasses;
  static HashMap toolHarvestLevels;
  static HashSet toolEffectiveness;

  public static void onTakenFromCrafting(OEntityPlayer player, OItemStack ist, OIInventory craftMatrix)
  {
    for (ForgeICraftingHandler handler : craftingHandlers)
      handler.onTakenFromCrafting(player, ist, craftMatrix);
  }

  public static void onDestroyCurrentItem(OEntityPlayer player, OItemStack orig)
  {
    for (ForgeDestroyToolHandler handler : destroyToolHandlers)
      handler.onDestroyCurrentItem(player, orig);
  }

  public static boolean onUseBonemeal(OWorld world, int bid, int i, int j, int k)
  {
    for (ForgeIBonemealHandler handler : bonemealHandlers) {
      if (handler.onUseBonemeal(world, bid, i, j, k))
        return true;
    }
    return false;
  }

  public static OEnumStatus sleepInBedAt(OEntityPlayer player, int i, int j, int k)
  {
    for (ForgeISleepHandler handler : sleepHandlers) {
      OEnumStatus status = handler.sleepInBedAt(player, i, j, k);
      if (status != null)
        return status;
    }
    return null;
  }

  static ProbableItem getRandomItem(List<ProbableItem> list, int prop)
  {
    int n = Collections.binarySearch(list, Integer.valueOf(prop), new Comparator() {
      public int compare(Object o1, Object o2) {
        ForgeHooks.ProbableItem pi = (ForgeHooks.ProbableItem)o1;
        Integer i1 = (Integer)o2;
        if (i1.intValue() < pi.wstart) return 1;
        if (i1.intValue() >= pi.wend) return -1;
        return 0;
      }
    });
    if (n < 0) return null;
    return (ProbableItem)list.get(n);
  }

  public static void plantGrassPlant(OWorld world, int i, int j, int k)
  {
    int n = world.w.nextInt(plantGrassWeight);
    ProbableItem pi = getRandomItem(plantGrassList, n);
    if (pi == null) return;
    world.b(i, j, k, pi.itemid, pi.meta);
  }

  public static void addPlantGrass(int item, int md, int prop) {
    plantGrassList.add(new ProbableItem(item, md, 1, plantGrassWeight, plantGrassWeight + prop));

    plantGrassWeight += prop;
  }

  public static OItemStack getGrassSeed(OWorld world) {
    int n = world.w.nextInt(seedGrassWeight);
    ProbableItem pi = getRandomItem(seedGrassList, n);
    if (pi == null) return null;
    return new OItemStack(pi.itemid, pi.qty, pi.meta);
  }

  public static void addGrassSeed(int item, int md, int qty, int prop) {
    seedGrassList.add(new ProbableItem(item, md, qty, seedGrassWeight, seedGrassWeight + prop));

    seedGrassWeight += prop;
  }

  public static boolean canHarvestBlock(OBlock bl, OEntityPlayer player, int md)
  {
    if (bl.bN.k())
      return true;
    OItemStack itemstack = player.j.b();
    if (itemstack == null) return false;

    List tc = (List)toolClasses.get(Integer.valueOf(itemstack.c));
    if (tc == null) return itemstack.b(bl);
    Object[] ta = tc.toArray();
    String cls = (String)ta[0]; int hvl = ((Integer)ta[1]).intValue();

    Integer bhl = (Integer)toolHarvestLevels.get(Arrays.asList(new Serializable[] { Integer.valueOf(bl.bA), Integer.valueOf(md), cls }));

    if (bhl == null) return itemstack.b(bl);
    return bhl.intValue() <= hvl;
  }

  public static float blockStrength(OBlock bl, OEntityPlayer player, int md)
  {
    float bh = bl.getHardness(md);
    if (bh < 0.0F) return 0.0F;

    if (!canHarvestBlock(bl, player, md)) {
      return 1.0F / bh / 100.0F;
    }
    return player.getCurrentPlayerStrVsBlock(bl, md) / bh / 30.0F;
  }

  public static boolean isToolEffective(OItemStack ist, OBlock bl, int md)
  {
    List tc = (List)toolClasses.get(Integer.valueOf(ist.c));
    if (tc == null) return false;
    Object[] ta = tc.toArray();
    String cls = (String)ta[0];

    return toolEffectiveness.contains(Arrays.asList(new Serializable[] { Integer.valueOf(bl.bA), Integer.valueOf(md), cls }));
  }

  static void initTools()
  {
    if (toolInit) return;
    toolInit = true;

    ForgeMinecraftForge.setToolClass(OItem.r, "pickaxe", 0);
    ForgeMinecraftForge.setToolClass(OItem.v, "pickaxe", 1);
    ForgeMinecraftForge.setToolClass(OItem.e, "pickaxe", 2);
    ForgeMinecraftForge.setToolClass(OItem.G, "pickaxe", 0);
    ForgeMinecraftForge.setToolClass(OItem.z, "pickaxe", 3);

    ForgeMinecraftForge.setToolClass(OItem.s, "axe", 0);
    ForgeMinecraftForge.setToolClass(OItem.w, "axe", 1);
    ForgeMinecraftForge.setToolClass(OItem.f, "axe", 2);
    ForgeMinecraftForge.setToolClass(OItem.H, "axe", 0);
    ForgeMinecraftForge.setToolClass(OItem.A, "axe", 3);

    ForgeMinecraftForge.setToolClass(OItem.q, "shovel", 0);
    ForgeMinecraftForge.setToolClass(OItem.u, "shovel", 1);
    ForgeMinecraftForge.setToolClass(OItem.d, "shovel", 2);
    ForgeMinecraftForge.setToolClass(OItem.F, "shovel", 0);
    ForgeMinecraftForge.setToolClass(OItem.y, "shovel", 3);

    ForgeMinecraftForge.setBlockHarvestLevel(OBlock.aq, "pickaxe", 3);
    ForgeMinecraftForge.setBlockHarvestLevel(OBlock.ax, "pickaxe", 2);
    ForgeMinecraftForge.setBlockHarvestLevel(OBlock.ay, "pickaxe", 2);
    ForgeMinecraftForge.setBlockHarvestLevel(OBlock.H, "pickaxe", 2);
    ForgeMinecraftForge.setBlockHarvestLevel(OBlock.ai, "pickaxe", 2);
    ForgeMinecraftForge.setBlockHarvestLevel(OBlock.I, "pickaxe", 1);
    ForgeMinecraftForge.setBlockHarvestLevel(OBlock.aj, "pickaxe", 1);
    ForgeMinecraftForge.setBlockHarvestLevel(OBlock.O, "pickaxe", 1);
    ForgeMinecraftForge.setBlockHarvestLevel(OBlock.P, "pickaxe", 1);
    ForgeMinecraftForge.setBlockHarvestLevel(OBlock.aO, "pickaxe", 2);
    ForgeMinecraftForge.setBlockHarvestLevel(OBlock.aP, "pickaxe", 2);
    ForgeMinecraftForge.removeBlockEffectiveness(OBlock.aO, "pickaxe");
    ForgeMinecraftForge.removeBlockEffectiveness(OBlock.aP, "pickaxe");

    OBlock[] pickeff = { OBlock.x, OBlock.ak, OBlock.al, OBlock.u, OBlock.R, OBlock.ap, OBlock.J, OBlock.aU, OBlock.bc, OBlock.O, OBlock.P };

    for (OBlock bl : pickeff)
      ForgeMinecraftForge.setBlockHarvestLevel(bl, "pickaxe", 0);
  }

  static
  {
    plantGrassList.add(new ProbableItem(OBlock.ae.bA, 0, 1, 0, 30));

    plantGrassList.add(new ProbableItem(OBlock.af.bA, 0, 1, 30, 40));

    plantGrassWeight = 40;

    seedGrassList = new ArrayList();
    seedGrassList.add(new ProbableItem(OItem.Q.bo, 0, 1, 0, 10));

    seedGrassWeight = 10;

    System.out.printf("MinecraftForge V%d.%d.%d Initialized\n", new Object[] { Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1) });

    toolInit = false;
    toolClasses = new HashMap();
    toolHarvestLevels = new HashMap();
    toolEffectiveness = new HashSet();
  }

  static class ProbableItem
  {
    int wstart;
    int wend;
    int itemid;
    int meta;
    int qty;

    public ProbableItem(int item, int md, int q, int st, int e)
    {
      this.wstart = st; this.wend = e;
      this.itemid = item; this.meta = md;
      this.qty = q;
    }
  }
}