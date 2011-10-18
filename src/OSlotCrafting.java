
public class OSlotCrafting extends OSlot {

   private final OIInventory e;
   private OEntityPlayer f;


   public OSlotCrafting(OEntityPlayer entityplayer, OIInventory iinventory, OIInventory iinventory1, int i, int j, int k) {
      super(iinventory1, i, j, k);
      this.f = entityplayer;
      this.e = iinventory;
   }

   public boolean b(OItemStack itemstack) {
      return false;
   }

   public void a(OItemStack itemstack) {
      itemstack.c(this.f.bb, this.f);
      if(itemstack.c == OBlock.az.bA) {
         this.f.a(OAchievementList.h, 1);
      } else if(itemstack.c == OItem.r.bo) {
         this.f.a(OAchievementList.i, 1);
      } else if(itemstack.c == OBlock.aC.bA) {
         this.f.a(OAchievementList.j, 1);
      } else if(itemstack.c == OItem.L.bo) {
         this.f.a(OAchievementList.l, 1);
      } else if(itemstack.c == OItem.S.bo) {
         this.f.a(OAchievementList.m, 1);
      } else if(itemstack.c == OItem.aX.bo) {
         this.f.a(OAchievementList.n, 1);
      } else if(itemstack.c == OItem.v.bo) {
         this.f.a(OAchievementList.o, 1);
      } else if(itemstack.c == OItem.p.bo) {
         this.f.a(OAchievementList.r, 1);
      }

      // CanaryMod - Modloader, call crafting if enabled
      if (etc.getInstance().isModLoaderMPEnabled()) {
          ModLoader.TakenFromCrafting(this.f, itemstack);
      }

      for(int i = 0; i < this.e.a(); ++i) {
         OItemStack itemstack1 = this.e.b_(i);
         if(itemstack1 != null) {
            this.e.a(i, 1);
            if(itemstack1.a().i()) {
               this.e.a(i, new OItemStack(itemstack1.a().h()));
            }
         }
      }

   }
}
