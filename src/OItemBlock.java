

public class OItemBlock extends OItem {

   private int a;


   public OItemBlock(int var1) {
      super(var1);
      this.a = var1 + 256;
      this.b(OBlock.m[var1 + 256].a(2));
   }

   public boolean a(OItemStack var1, OEntityPlayer var2, OWorld var3, int var4, int var5, int var6, int var7) {
      // CanaryMod: Bail if we have nothing of the items in hand
      if (var1.a == 0)
         return false;
      // CanaryMod: Store blockInfo of the one we clicked
      int blockClickedId = var3.a(var4, var5, var6);
      Block blockClicked = new Block(var3.world, blockClickedId, var4, var5, var6);


       if(var3.a(var4, var5, var6) == OBlock.aT.bn) {
         var7 = 0;
      } else {
         if(var7 == 0) {
            --var5;
         }

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
      }

      if(var1.a == 0) {
         return false;
      }

      // CanaryMod: Store faceClicked (must be here to have the 'snow' special
      // case).
      blockClicked.setFaceClicked(Block.Face.fromId(var7));

      // CanaryMod: And the block we're about to place
      Block blockPlaced = new Block(var3.world, a, var4, var5, var6);

      // CanaryMod Store all the old settings 'externally' in case someone changes
      // blockPlaced.
      int oldMaterial = var3.a(var4, var5, var6);
      int oldData = var3.b(var4, var5, var6);

      if(var5 == 127 && OBlock.m[this.a].bA.a()) {
         return false;
      } else if(var3.a(this.a, var4, var5, var6, false, var7)) {
         OBlock var8 = OBlock.m[this.a];
         // CanaryMod: take over block placement
         if(var3.b(var4, var5, var6, this.a, this.a(var1.h())))
            // CanaryMod: Check if this was playerPlaced and call the hook
            if (var2 instanceof OEntityPlayerMP && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_PLACE, ((OEntityPlayerMP) var2).getPlayer(), blockPlaced, blockClicked, new Item(var1))) {
               // CanaryMod: Undo!

               // Specialcase iceblocks, replace with 'glass' first (so it
               // doesnt explode into water)
               if (a == 79)
                  var3.b(var4, var5, var6, 20);
               var3.b(var4, var5, var6, oldMaterial);
               var3.d(var4, var5, var6, oldData);
            } else {
               OBlock.m[this.a].d(var3, var4, var5, var6, var7);
               OBlock.m[this.a].a(var3, var4, var5, var6, var2);
               var3.a((double)((float)var4 + 0.5F), (double)((float)var5 + 0.5F), (double)((float)var6 + 0.5F), var8.by.c(), (var8.by.a() + 1.0F) / 2.0F, var8.by.b() * 0.8F);
               --var1.a;
         }

         return true;
      } else {
         return false;
      }
   }

   public String a() {
      return OBlock.m[this.a].h();
   }
}
