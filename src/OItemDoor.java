

public class OItemDoor extends OItem {

   private OMaterial a;


   public OItemDoor(int var1, OMaterial var2) {
      super(var1);
      this.a = var2;
      this.bf = 1;
   }

   public boolean a(OItemStack var1, OEntityPlayer var2, OWorld var3, int var4, int var5, int var6, int var7) {
      if(var7 != 1) {
         return false;
      } else {
         ++var5;
         OBlock var8;
         if(this.a == OMaterial.d) {
            var8 = OBlock.aF;
         } else {
            var8 = OBlock.aM;
         }

         if(!var8.a(var3, var4, var5, var6)) {
            return false;
         } else {
            Block blockClicked = new Block(var3.world, var3.a(var4, var5, var6), var4, var5, var6);
            blockClicked.setFaceClicked(Block.Face.fromId(var7));
            Block blockPlaced = new Block(var3.world, var3.a(var4, var5 + 1, var6), var4, var5 + 1, var6);

            // Call the hook
            if (var2 instanceof OEntityPlayerMP) {
               Player player = ((OEntityPlayerMP) var2).getPlayer();
               if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, player, blockPlaced, blockClicked, new Item(var1)))
                  return false;
            }
            
            int var9 = OMathHelper.b((double)((var2.aV + 180.0F) * 4.0F / 360.0F) - 0.5D) & 3;
            byte var10 = 0;
            byte var11 = 0;
            if(var9 == 0) {
               var11 = 1;
            }

            if(var9 == 1) {
               var10 = -1;
            }

            if(var9 == 2) {
               var11 = -1;
            }

            if(var9 == 3) {
               var10 = 1;
            }

            int var12 = (var3.d(var4 - var10, var5, var6 - var11)?1:0) + (var3.d(var4 - var10, var5 + 1, var6 - var11)?1:0);
            int var13 = (var3.d(var4 + var10, var5, var6 + var11)?1:0) + (var3.d(var4 + var10, var5 + 1, var6 + var11)?1:0);
            boolean var14 = var3.a(var4 - var10, var5, var6 - var11) == var8.bn || var3.a(var4 - var10, var5 + 1, var6 - var11) == var8.bn;
            boolean var15 = var3.a(var4 + var10, var5, var6 + var11) == var8.bn || var3.a(var4 + var10, var5 + 1, var6 + var11) == var8.bn;
            boolean var16 = false;
            if(var14 && !var15) {
               var16 = true;
            } else if(var13 > var12) {
               var16 = true;
            }

            if(var16) {
               var9 = var9 - 1 & 3;
               var9 += 4;
            }

            var3.o = true;
            var3.b(var4, var5, var6, var8.bn, var9);
            var3.b(var4, var5 + 1, var6, var8.bn, var9 + 8);
            var3.o = false;
            var3.h(var4, var5, var6, var8.bn);
            var3.h(var4, var5 + 1, var6, var8.bn);
            --var1.a;
            return true;
         }
      }
   }
}
