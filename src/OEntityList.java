
import java.util.HashMap;
import java.util.Map;

public class OEntityList {

   private static Map a = new HashMap();
   private static Map b = new HashMap();
   private static Map c = new HashMap();
   private static Map d = new HashMap();

   private static void a(Class var0, String var1, int var2) {
      a.put(var1, var0);
      b.put(var0, var1);
      c.put(Integer.valueOf(var2), var0);
      d.put(var0, Integer.valueOf(var2));
   }

   public static OEntity a(String var0, OWorld var1) {
      OEntity var2 = null;

      try {
         Class var3 = (Class)a.get(var0);
         if(var3 != null) {
            var2 = (OEntity)var3.getConstructor(new Class[]{OWorld.class}).newInstance(new Object[]{var1});
         }
      } catch (Exception var4) {
         var4.printStackTrace();
      }

      return var2;
   }

   public static OEntity a(ONBTTagCompound var0, OWorld var1) {
      OEntity var2 = null;

      try {
         // CanaryMod: fix jarjar
         Class var3 = (Class)a.get(var0.i("id"));
         if(var3 != null) {
            var2 = (OEntity)var3.getConstructor(new Class[]{OWorld.class}).newInstance(new Object[]{var1});
         }
      } catch (Exception var4) {
         var4.printStackTrace();
      }

      if(var2 != null) {
         var2.e(var0);
      } else {
         // CanaryMod: fix jarjar
         System.out.println("Skipping Entity with id " + var0.i("id"));
      }

      return var2;
   }

   public static int a(OEntity var0) {
      return ((Integer)d.get(var0.getClass())).intValue();
   }

   public static String b(OEntity var0) {
      return (String)b.get(var0.getClass());
   }

   // CanaryMod: Let us do a name->class lookup for mob spawning
   public static Class<?> getEntity(String name) {
       return (Class<?>) a.get(name);
   }

   static {
      a(OEntityArrow.class, "Arrow", 10);
      a(OEntitySnowball.class, "Snowball", 11);
      a(OEntityItem.class, "Item", 1);
      a(OEntityPainting.class, "Painting", 9);
      a(OEntityLiving.class, "Mob", 48);
      a(OEntityMob.class, "Monster", 49);
      a(OEntityCreeper.class, "Creeper", 50);
      a(OEntitySkeleton.class, "Skeleton", 51);
      a(OEntitySpider.class, "Spider", 52);
      a(OEntityGiantZombie.class, "Giant", 53);
      a(OEntityZombie.class, "Zombie", 54);
      a(OEntitySlime.class, "Slime", 55);
      a(OEntityGhast.class, "Ghast", 56);
      a(OEntityPigZombie.class, "PigZombie", 57);
      a(OEntityPig.class, "Pig", 90);
      a(OEntitySheep.class, "Sheep", 91);
      a(OEntityCow.class, "Cow", 92);
      a(OEntityChicken.class, "Chicken", 93);
      a(OEntitySquid.class, "Squid", 94);
      a(OEntityWolf.class, "Wolf", 95);
      a(OEntityTNTPrimed.class, "PrimedTnt", 20);
      a(OEntityFallingSand.class, "FallingSand", 21);
      a(OEntityMinecart.class, "Minecart", 40);
      a(OEntityBoat.class, "Boat", 41);
   }
}
