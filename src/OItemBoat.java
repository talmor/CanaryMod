
public class OItemBoat extends OItem {

   public OItemBoat(int var1) {
      super(var1);
      this.bp = 1;
   }

   public OItemStack a(OItemStack var1, OWorld var2, OEntityPlayer var3) {
      float var4 = 1.0F;
      float var5 = var3.bo + (var3.bm - var3.bo) * var4;
      float var6 = var3.bn + (var3.bl - var3.bn) * var4;
      double var7 = var3.bc + (var3.bf - var3.bc) * (double)var4;
      double var9 = var3.bd + (var3.bg - var3.bd) * (double)var4 + 1.62D - (double)var3.by;
      double var11 = var3.be + (var3.bh - var3.be) * (double)var4;
      OVec3D var13 = OVec3D.b(var7, var9, var11);
      float var14 = OMathHelper.b(-var6 * 0.017453292F - 3.1415927F);
      float var15 = OMathHelper.a(-var6 * 0.017453292F - 3.1415927F);
      float var16 = -OMathHelper.b(-var5 * 0.017453292F);
      float var17 = OMathHelper.a(-var5 * 0.017453292F);
      float var18 = var15 * var16;
      float var20 = var14 * var16;
      double var21 = 5.0D;
      OVec3D var23 = var13.c((double)var18 * var21, (double)var17 * var21, (double)var20 * var21);
      OMovingObjectPosition var24 = var2.a(var13, var23, true);
      if(var24 == null) {
         return var1;
      } else {
         if(var24.a == OEnumMovingObjectType.a) {
            int var25 = var24.b;
            int var26 = var24.c;
            int var27 = var24.d;
            if(!var2.I) {
               if(var2.a(var25, var26, var27) == OBlock.aT.bA) {
                  --var26;
               }
               // CanaryMod: placing of a boat
               Block blockClicked = new Block(var2.world, var2.a(var25, var26, var27), var25, var26, var27);
               blockClicked.setFaceClicked(Block.Face.fromId(var24.e));
               Block blockPlaced = new Block(var2.world, 0, var25, var26, var27);
               // CanaryMod: Call hook
               if (var3 instanceof OEntityPlayerMP && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) var3).getPlayer(), blockPlaced, blockClicked, new Item(var1)))
                  return var1;
               
               var2.b((OEntity)(new OEntityBoat(var2, (double)((float)var25 + 0.5F), (double)((float)var26 + 1.0F), (double)((float)var27 + 0.5F))));
            }

            --var1.a;
         }

         return var1;
      }
   }
}
