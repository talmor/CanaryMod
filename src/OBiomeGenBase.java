
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OBiomeGenBase {

   public static final OBiomeGenBase a = (new OBiomeGenRainforest()).b(588342).a("Rainforest").a(2094168);
   public static final OBiomeGenBase b = (new OBiomeGenSwamp()).b(522674).a("Swampland").a(9154376);
   public static final OBiomeGenBase c = (new OBiomeGenBase()).b(10215459).a("Seasonal Forest");
   public static final OBiomeGenBase d = (new OBiomeGenForest()).b(353825).a("Forest").a(5159473);
   public static final OBiomeGenBase e = (new OBiomeGenDesert()).b(14278691).a("Savanna");
   public static final OBiomeGenBase f = (new OBiomeGenBase()).b(10595616).a("Shrubland");
   public static final OBiomeGenBase g = (new OBiomeGenTaiga()).b(3060051).a("Taiga").b().a(8107825);
   public static final OBiomeGenBase h = (new OBiomeGenDesert()).b(16421912).a("Desert").e();
   public static final OBiomeGenBase i = (new OBiomeGenDesert()).b(16767248).a("Plains");
   public static final OBiomeGenBase j = (new OBiomeGenDesert()).b(16772499).a("Ice Desert").b().e().a(12899129);
   public static final OBiomeGenBase k = (new OBiomeGenBase()).b(5762041).a("Tundra").b().a(12899129);
   public static final OBiomeGenBase l = (new OBiomeGenHell()).b(16711680).a("Hell").e();
   public static final OBiomeGenBase m = (new OBiomeGenSky()).b(8421631).a("Sky").e();
   public String n;
   public int o;
   public byte p;
   public byte q;
   public int r;
   protected List s;
   protected List t;
   protected List u;
   private boolean v;
   private boolean w;
   private static OBiomeGenBase[] x = new OBiomeGenBase[4096];


   protected OBiomeGenBase() {
      this.p = (byte)OBlock.v.bn;
      this.q = (byte)OBlock.w.bn;
      this.r = 5169201;
      this.s = new ArrayList();
      this.t = new ArrayList();
      this.u = new ArrayList();
      this.w = true;
   }

   private OBiomeGenBase e() {
      this.w = false;
      return this;
   }

   public static void a() {
      for(int var0 = 0; var0 < 64; ++var0) {
         for(int var1 = 0; var1 < 64; ++var1) {
            x[var0 + var1 * 64] = a((float)var0 / 63.0F, (float)var1 / 63.0F);
         }
      }

      h.p = h.q = (byte)OBlock.F.bn;
      j.p = j.q = (byte)OBlock.F.bn;
   }

   public OWorldGenerator a(Random var1) {
      return (OWorldGenerator)(var1.nextInt(10) == 0?new OWorldGenBigTree():new OWorldGenTrees());
   }

   protected OBiomeGenBase b() {
      this.v = true;
      return this;
   }

   protected OBiomeGenBase a(String var1) {
      this.n = var1;
      return this;
   }

   protected OBiomeGenBase a(int var1) {
      this.r = var1;
      return this;
   }

   protected OBiomeGenBase b(int var1) {
      this.o = var1;
      return this;
   }

   public static OBiomeGenBase a(double var0, double var2) {
      int var4 = (int)(var0 * 63.0D);
      int var5 = (int)(var2 * 63.0D);
      return x[var4 + var5 * 64];
   }

   public static OBiomeGenBase a(float var0, float var1) {
      var1 *= var0;
      return var0 < 0.1F?k:(var1 < 0.2F?(var0 < 0.5F?k:(var0 < 0.95F?e:h)):(var1 > 0.5F && var0 < 0.7F?b:(var0 < 0.5F?g:(var0 < 0.97F?(var1 < 0.35F?f:d):(var1 < 0.45F?i:(var1 < 0.9F?c:a))))));
   }

   public List a(OEnumCreatureType var1) {
      etc config = etc.getInstance();
      if (var1 == OEnumCreatureType.a)
         return config.getMonstersClass(this);
      if (var1 == OEnumCreatureType.b)
         return config.getAnimalsClass(this);
      if (var1 == OEnumCreatureType.c)
         return config.getWaterAnimalsClass(this);
      return null;
   }

   public boolean c() {
      return this.v;
   }

   public boolean d() {
      return this.v?false:this.w;
   }

   static {
      a();
   }
}
