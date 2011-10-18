
public class OSlotFurnace extends OSlot {

   private OEntityPlayer e;


   public OSlotFurnace(OEntityPlayer entityplayer, OIInventory iinventory, int i, int j, int k) {
      super(iinventory, i, j, k);
      this.e = entityplayer;
   }

   public boolean b(OItemStack itemstack) {
      return false;
   }

   public void a(OItemStack itemstack) {
      itemstack.c(this.e.bb, this.e);
      if(itemstack.c == OItem.m.bo) {
         this.e.a(OAchievementList.k, 1);
      }

      if(itemstack.c == OItem.aT.bo) {
         this.e.a(OAchievementList.p, 1);
      }

      ModLoader.TakenFromFurnace(this.e, itemstack);
      super.a(itemstack);
   }
}
