import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class OBiomeGenBase {

   public static final OBiomeGenBase[] a = new OBiomeGenBase[256];
   public static final OBiomeGenBase b = (new OBiomeGenOcean(0)).b(112).a("Ocean").b(-1.0F, 0.5F);
   public static final OBiomeGenBase c = (new OBiomeGenPlains(1)).b(9286496).a("Plains").a(0.8F, 0.4F);
   public static final OBiomeGenBase d = (new OBiomeGenDesert(2)).b(16421912).a("Desert").g().a(2.0F, 0.0F).b(0.1F, 0.2F);
   public static final OBiomeGenBase e = (new OBiomeGenHills(3)).b(6316128).a("Extreme Hills").b(0.2F, 1.8F).a(0.2F, 0.3F);
   public static final OBiomeGenBase f = (new OBiomeGenForest(4)).b(353825).a("Forest").a(5159473).a(0.7F, 0.8F);
   public static final OBiomeGenBase g = (new OBiomeGenTaiga(5)).b(747097).a("Taiga").a(5159473).a(0.3F, 0.8F).b(0.1F, 0.4F);
   public static final OBiomeGenBase h = (new OBiomeGenSwamp(6)).b(522674).a("Swampland").a(9154376).b(-0.2F, 0.1F).a(0.8F, 0.9F);
   public static final OBiomeGenBase i = (new OBiomeGenRiver(7)).b(255).a("River").b(-0.5F, 0.0F);
   public static final OBiomeGenBase j = (new OBiomeGenHell(8)).b(16711680).a("Hell").g();
   public static final OBiomeGenBase k = (new OBiomeGenSky(9)).b(8421631).a("Sky").g();
   public String l;
   public int m;
   public byte n;
   public byte o;
   public int p;
   public float q;
   public float r;
   public float s;
   public float t;
   public OBiomeDecorator u;
   protected List v;
   protected List w;
   protected List x;
   private boolean D;
   private boolean E;
   public final int y;
   protected OWorldGenTrees z;
   protected OWorldGenBigTree A;
   protected OWorldGenForest B;
   protected OWorldGenSwamp C;


   protected OBiomeGenBase(int var1) {
      super();
      this.n = (byte)OBlock.v.bA;
      this.o = (byte)OBlock.w.bA;
      this.p = 5169201;
      this.q = 0.1F;
      this.r = 0.3F;
      this.s = 0.5F;
      this.t = 0.5F;
      this.v = new ArrayList();
      this.w = new ArrayList();
      this.x = new ArrayList();
      this.E = true;
      this.z = new OWorldGenTrees();
      this.A = new OWorldGenBigTree();
      this.B = new OWorldGenForest();
      this.C = new OWorldGenSwamp();
      this.y = var1;
      a[var1] = this;
      this.u = this.a();
      this.w.add(new OSpawnListEntry(OEntitySheep.class, 12, 4, 4));
      this.w.add(new OSpawnListEntry(OEntityPig.class, 10, 4, 4));
      this.w.add(new OSpawnListEntry(OEntityChicken.class, 10, 4, 4));
      this.w.add(new OSpawnListEntry(OEntityCow.class, 8, 4, 4));
      this.v.add(new OSpawnListEntry(OEntitySpider.class, 10, 4, 4));
      this.v.add(new OSpawnListEntry(OEntityZombie.class, 10, 4, 4));
      this.v.add(new OSpawnListEntry(OEntitySkeleton.class, 10, 4, 4));
      this.v.add(new OSpawnListEntry(OEntityCreeper.class, 10, 4, 4));
      this.v.add(new OSpawnListEntry(OEntitySlime.class, 10, 4, 4));
      this.v.add(new OSpawnListEntry(OEntityEnderman.class, 2, 4, 4));
      this.x.add(new OSpawnListEntry(OEntitySquid.class, 10, 4, 4));
   }

   protected OBiomeDecorator a() {
      return new OBiomeDecorator(this);
   }

   private OBiomeGenBase a(float var1, float var2) {
      this.s = var1;
      this.t = var2;
      return this;
   }

   private OBiomeGenBase b(float var1, float var2) {
      this.q = var1;
      this.r = var2;
      return this;
   }

   private OBiomeGenBase g() {
      this.E = false;
      return this;
   }

   public OWorldGenerator a(Random var1) {
      return (OWorldGenerator)(var1.nextInt(10) == 0?this.A:this.z);
   }

   protected OBiomeGenBase a(String var1) {
      this.l = var1;
      return this;
   }

   protected OBiomeGenBase a(int var1) {
      this.p = var1;
      return this;
   }

   protected OBiomeGenBase b(int var1) {
      this.m = var1;
      return this;
   }

 //CanaryMod start - responsible for adding spawned monsters to the monster list.
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
   //CanaryMod end

   public boolean b() {
      return this.D;
   }

   public boolean c() {
      return this.D?false:this.E;
   }

   public float d() {
      return 0.1F;
   }

   public final int e() {
      return (int)(this.t * 65536.0F);
   }

   public final int f() {
      return (int)(this.s * 65536.0F);
   }

   public void a(OWorld var1, Random var2, int var3, int var4) {
      this.u.a(var1, var2, var3, var4);
   }

}
