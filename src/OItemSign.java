

public class OItemSign extends OItem {

   public OItemSign(int var1) {
      super(var1);
      this.bg = 1;
   }

   public boolean a(OItemStack var1, OEntityPlayer var2, OWorld var3, int var4, int var5, int var6, int var7) {
      if(var7 == 0) {
         return false;
      } else if(!var3.d(var4, var5, var6).a()) {
         return false;
      } else {
          // CanaryMod: Store block data clicked
          Block blockClicked = new Block(var3.world, var3.a(var4, var5, var6), var4, var5, var6);
          blockClicked.setFaceClicked(Block.Face.fromId(var7));

         if(var7 == 1) {
            ++var5;
         }

         if(var7 == 2) {
            --var6;
         }

         if(var7 == 3) {
            ++var6;
         }

         if(var7 == 4) {
            --var4;
         }

         if(var7 == 5) {
            ++var4;
         }

         if(!OBlock.aE.a(var3, var4, var5, var6)) {
            return false;
         } else {
            // CanaryMod: Now we can call itemUse :)
            Block blockPlaced = new Block(var3.world, (var7 == 1 ? OBlock.aE.bn : OBlock.aJ.bn), var4, var5, var6);
            if (var2 instanceof OEntityPlayerMP && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) var2).getPlayer(), blockPlaced, blockClicked, new Item(var1)))
               return false;

            if(var7 == 1) {
               var3.b(var4, var5, var6, OBlock.aE.bn, OMathHelper.b((double)((var2.aV + 180.0F) * 16.0F / 360.0F) + 0.5D) & 15);
            } else {
               var3.b(var4, var5, var6, OBlock.aJ.bn, var7);
            }

            --var1.a;
            OTileEntitySign var8 = (OTileEntitySign)var3.b(var4, var5, var6);
            if(var8 != null) {
               var2.a(var8);
            }

            return true;
         }
      }
   }
}
