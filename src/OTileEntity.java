import java.util.HashMap;
import java.util.Map;

public class OTileEntity {

   private static Map a = new HashMap();
   public static Map b = new HashMap();
   public OWorld i;
   public int j;
   public int k;
   public int l;
   protected boolean m;
   public int n = -1;
   public OBlock o;


   public OTileEntity() {
      super();
   }

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
      this.j = var1.e("x");
      this.k = var1.e("y");
      this.l = var1.e("z");
   }

   public void b(ONBTTagCompound var1) {
      String var2 = (String)b.get(this.getClass());
      if(var2 == null) {
         throw new RuntimeException(this.getClass() + " is missing a mapping! This is a bug!");
      } else {
         // CanaryMod: fix jarjar
         var1.a("id", var2);
         var1.a("x", this.j);
         var1.a("y", this.k);
         var1.a("z", this.l);
      }
   }

   public void h_() {}

   public static OTileEntity c(ONBTTagCompound var0) {
      OTileEntity var1 = null;

      try {
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
         System.out.println("Skipping TileEntity with id " + var0.i("id"));
      }

      return var1;
   }

   public int j() {
      if(this.n == -1) {
         this.n = this.i.c(this.j, this.k, this.l);
      }

      return this.n;
   }

   public void k() {
      if(this.i != null) {
         this.n = this.i.c(this.j, this.k, this.l);
         this.i.b(this.j, this.k, this.l, this);
      }

   }

   public OPacket l() {
      return null;
   }

   public boolean m() {
      return this.m;
   }

   public void i() {
      this.m = true;
   }

   public void n() {
      this.m = false;
   }

   public void b(int var1, int var2) {}

   public void g() {
      this.o = null;
      this.n = -1;
   }

   static {
      a(OTileEntityFurnace.class, "Furnace");
      a(OTileEntityChest.class, "Chest");
      a(OTileEntityRecordPlayer.class, "RecordPlayer");
      a(OTileEntityDispenser.class, "Trap");
      a(OTileEntitySign.class, "Sign");
      a(OTileEntityMobSpawner.class, "MobSpawner");
      a(OTileEntityNote.class, "Music");
      a(OTileEntityPiston.class, "Piston");
   }
}
