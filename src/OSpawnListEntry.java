
import java.util.HashMap;



public class OSpawnListEntry {

   public Class a;
   public int b;

   // CanaryMod: Have a way to get entries
   public static final HashMap<Class<? extends OEntityLiving>, Integer> chanceMap = new HashMap();
   static {
      chanceMap.put(OEntitySpider.class, 10);
      chanceMap.put(OEntityZombie.class, 10);
      chanceMap.put(OEntitySkeleton.class, 10);
      chanceMap.put(OEntityCreeper.class, 10);
      chanceMap.put(OEntitySlime.class, 10);

      chanceMap.put(OEntityGhast.class, 10);
      chanceMap.put(OEntityPigZombie.class, 10);

      chanceMap.put(OEntitySheep.class, 12);
      chanceMap.put(OEntityPig.class, 10);
      chanceMap.put(OEntityChicken.class, 10);
      chanceMap.put(OEntityCow.class, 8);
      chanceMap.put(OEntityWolf.class, 2);

      chanceMap.put(OEntitySquid.class, 10);
   }

   public OSpawnListEntry(Class var1, int var2) {
      this.a = var1;
      this.b = var2;
   }

   // CanaryMod: actual method to get entries
   @SuppressWarnings("element-type-mismatch")
   public static OSpawnListEntry getSpawnListEntry(Class<?> entityClass) {
      if (!OEntityLiving.class.isAssignableFrom(entityClass))
          throw new IllegalArgumentException(entityClass + " is not an entity class!");
      return new OSpawnListEntry(entityClass, chanceMap.get(entityClass));
   }
}
