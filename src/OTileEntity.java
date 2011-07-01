
import java.util.HashMap;
import java.util.Map;

public class OTileEntity {

   private static Map a = new HashMap();
   private static Map b = new HashMap();
   public OWorld d;
   public int e;
   public int f;
   public int g;


   private static void a(Class var0, String var1) {
      if(b.containsKey(var1)) {
         throw new IllegalArgumentException("Duplicate id: " + var1);
      } else {
         a.put(var1, var0);
         b.put(var0, var1);
      }
   }

   public void a(ONBTTagCompound var1) {
      // CanaryMod: fix jarjar
      this.e = var1.e("x");
      this.f = var1.e("y");
      this.g = var1.e("z");
   }

   public void b(ONBTTagCompound var1) {
      String var2 = (String)b.get(this.getClass());
      if(var2 == null) {
         throw new RuntimeException(this.getClass() + " is missing a mapping! This is a bug!");
      } else {
         // CanaryMod: fix jarjar
         var1.a("id", var2);
         var1.a("x", this.e);
         var1.a("y", this.f);
         var1.a("z", this.g);
      }
   }

   public void g_() {
   }

   public static OTileEntity c(ONBTTagCompound var0) {
      OTileEntity var1 = null;

      try {
         // CanaryMod: fix jarjar
         Class var2 = (Class)a.get(var0.i("id"));
         if(var2 != null) {
            var1 = (OTileEntity)var2.newInstance();
         }
      } catch (Exception var3) {
         var3.printStackTrace();
      }

      if(var1 != null) {
         var1.a(var0);
      } else {
         // CanaryMod: fix jarjar
         System.out.println("Skipping TileEntity with id " + var0.i("id"));
      }

      return var1;
   }

   public void i() {
      if(this.d != null) {
         this.d.b(this.e, this.f, this.g, this);
      }

   }

   public OPacket e() {
      return null;
   }

   static {
      a(OTileEntityFurnace.class, "Furnace");
      a(OTileEntityChest.class, "Chest");
      a(OTileEntityRecordPlayer.class, "RecordPlayer");
      a(OTileEntityDispenser.class, "Trap");
      a(OTileEntitySign.class, "Sign");
      a(OTileEntityMobSpawner.class, "MobSpawner");
      a(OTileEntityNote.class, "Music");
   }
}
