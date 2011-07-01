

public class OItemBucket extends OItem {

   private int a;


   public OItemBucket(int var1, int var2) {
      super(var1);
      this.a = var2;
   }

   public OItemStack a(OItemStack var1, OWorld var2, OEntityPlayer var3) {
      float var4 = 1.0F;
      float var5 = var3.aY + (var3.aW - var3.aY) * var4;
      float var6 = var3.aX + (var3.aV - var3.aX) * var4;
      double var7 = var3.aM + (var3.aP - var3.aM) * (double)var4;
      double var9 = var3.aN + (var3.aQ - var3.aN) * (double)var4 + 1.62D - (double)var3.bi;
      double var11 = var3.aO + (var3.aR - var3.aO) * (double)var4;
      OVec3D var13 = OVec3D.b(var7, var9, var11);
      float var14 = OMathHelper.b(-var6 * 0.017453292F - 3.1415927F);
      float var15 = OMathHelper.a(-var6 * 0.017453292F - 3.1415927F);
      float var16 = -OMathHelper.b(-var5 * 0.017453292F);
      float var17 = OMathHelper.a(-var5 * 0.017453292F);
      float var18 = var15 * var16;
      float var20 = var14 * var16;
      double var21 = 5.0D;
      OVec3D var23 = var13.c((double)var18 * var21, (double)var17 * var21, (double)var20 * var21);
      OMovingObjectPosition var24 = var2.a(var13, var23, this.a == 0);
      if(var24 == null) {
         return var1;
      } else {
         if(var24.a == OEnumMovingObjectType.a) {
            int var25 = var24.b;
            int var26 = var24.c;
            int var27 = var24.d;
            if(!var2.a(var3, var25, var26, var27)) {
               return var1;
            }
            // CanaryMod: Click == placed when handling an empty bukkit!
            Block blockClicked = new Block(var2.world, var2.a(var25, var26, var27), var25, var26, var27);
            blockClicked.setFaceClicked(Block.Face.fromId(var24.e));
            Block blockPlaced = new Block(var2.world, 0, var25, var26, var27);

            if(this.a == 0) {
               if(var2.c(var25, var26, var27) == OMaterial.g && var2.b(var25, var26, var27) == 0) {
                  // Filling a bucket with water!
                  if (var3 instanceof OEntityPlayerMP && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) var3).getPlayer(), blockPlaced, blockClicked, new Item(var1)))
                     return var1;

                  var2.e(var25, var26, var27, 0);
                  return new OItemStack(OItem.av);
               }

               if(var2.c(var25, var26, var27) == OMaterial.h && var2.b(var25, var26, var27) == 0) {
                  // Filling a bucket with lava!
                  if (var3 instanceof OEntityPlayerMP && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) var3).getPlayer(), blockPlaced, blockClicked, new Item(var1)))
                     return var1;

                  var2.e(var25, var26, var27, 0);
                  return new OItemStack(OItem.aw);
               }
            } else {
               if(this.a < 0) {
                  return new OItemStack(OItem.au);
               }

               if(var24.e == 0) {
                  --var26;
               }

               if(var24.e == 1) {
                  ++var26;
               }

               if(var24.e == 2) {
                  --var27;
               }

               if(var24.e == 3) {
                  ++var27;
               }

               if(var24.e == 4) {
                  --var25;
               }

               if(var24.e == 5) {
                  ++var25;
               }

               if(var2.e(var25, var26, var27) || !var2.c(var25, var26, var27).a()) {
                  if(var2.t.d && this.a == OBlock.B.bn) {
                     var2.a(var7 + 0.5D, var9 + 0.5D, var11 + 0.5D, "random.fizz", 0.5F, 2.6F + (var2.r.nextFloat() - var2.r.nextFloat()) * 0.8F);

                     for(int var28 = 0; var28 < 8; ++var28) {
                        var2.a("largesmoke", (double)var25 + Math.random(), (double)var26 + Math.random(), (double)var27 + Math.random(), 0.0D, 0.0D, 0.0D);
                     }
                  } else {
                     // CanaryMod: bucket empty
                     blockPlaced = new Block(var2.world, a, var25, var26, var27);
                     if (var3 instanceof OEntityPlayerMP && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) var3).getPlayer(), blockPlaced, blockClicked, new Item(var1)))
                        return var1;
                     var2.b(var25, var26, var27, this.a, 0);
                  }

                  return new OItemStack(OItem.au);
               }
            }
         } else if(this.a == 0 && var24.g instanceof OEntityCow) {
            return new OItemStack(OItem.aE);
         }

         return var1;
      }
   }
}
