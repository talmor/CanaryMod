import java.util.Random;

public abstract class BaseMod {

   public BaseMod() {
      super();
   }

   public int AddFuel(int i) {
      return 0;
   }

   public boolean DispenseEntity(OWorld world, double d, double d1, double d2, int i, int j, OItemStack itemstack) {
      return false;
   }

   public void GenerateNether(OWorld world, Random random, int i, int j) {}

   public void GenerateSurface(OWorld world, Random random, int i, int j) {}

   public void OnTickInGame(MinecraftServer minecraftserver) {}

   public void ModsLoaded() {}

   public void TakenFromCrafting(OEntityPlayer entityplayer, OItemStack itemstack) {}

   public void TakenFromFurnace(OEntityPlayer entityplayer, OItemStack itemstack) {}

   public void OnItemPickup(OEntityPlayer entityplayer, OItemStack itemstack) {}

   public String toString() {
      return this.getClass().getName() + " " + this.Version();
   }

   public abstract String Version();
}
