

public final class OItemStack {
	
   // stack size
   public int a;
   // animations
   public int b;
   // item id
   public int c;
   // damage
   private int d;


   public OItemStack(OBlock var1) {
      this(var1, 1);
   }

   public OItemStack(OBlock var1, int var2) {
      this(var1.bn, var2, 0);
   }

   public OItemStack(OBlock var1, int var2, int var3) {
      this(var1.bn, var2, var3);
   }

   public OItemStack(OItem var1) {
      this(var1.bf, 1, 0);
   }

   public OItemStack(OItem var1, int var2) {
      this(var1.bf, var2, 0);
   }

   public OItemStack(OItem var1, int var2, int var3) {
      this(var1.bf, var2, var3);
   }

   public OItemStack(int var1, int var2, int var3) {
      this.a = 0;
      this.c = var1;
      this.a = var2;
      this.d = var3;
   }
   
   public void setFromStack(OItemStack itemStack)
   {
	   this.a = itemStack.a;
	   this.b = itemStack.b;
	   this.c = itemStack.c;
	   this.d = itemStack.h();
   }

   public OItemStack(ONBTTagCompound var1) {
      this.a = 0;
      this.b(var1);
   }

   public OItemStack a(int var1) {
      this.a -= var1;
      return new OItemStack(this.c, var1, this.d);
   }

   public OItem a() {
      return OItem.c[this.c];
   }

   public boolean a(OEntityPlayer var1, OWorld var2, int var3, int var4, int var5, int var6) {
      boolean var7 = this.a().a(this, var1, var2, var3, var4, var5, var6);
      if(var7) {
         var1.a(OStatList.E[this.c], 1);
      }

      return var7;
   }

   public float a(OBlock var1) {
      return this.a().a(this, var1);
   }

   public OItemStack a(OWorld var1, OEntityPlayer var2) {
      return this.a().a(this, var1, var2);
   }

   public ONBTTagCompound a(ONBTTagCompound var1) {
      // CanaryMod: fix jarjar
      var1.a("id", (short)this.c);
      var1.a("Count", (byte)this.a);
      var1.a("Damage", (short)this.d);
      return var1;
   }

   public void b(ONBTTagCompound var1) {
      // CanaryMod: fix jarjar
      this.c = var1.d("id");
      this.a = var1.c("Count");
      this.d = var1.d("Damage");
   }

   public int b() {
      return this.a().c();
   }

   public boolean c() {
      return this.b() > 1 && (!this.d() || !this.f());
   }

   public boolean d() {
      return OItem.c[this.c].e() > 0;
   }

   public boolean e() {
      return OItem.c[this.c].d();
   }

   public boolean f() {
      return this.d() && this.d > 0;
   }

   public int g() {
      return this.d;
   }

   public int h() {
      return this.d;
   }

   public void b(int var1) {
      this.d = var1;
   }

   public int i() {
      return OItem.c[this.c].e();
   }

   public void a(int var1, OEntity var2) {
      if(this.d()) {
         this.d += var1;
         if(this.d > this.i()) {
            if(var2 instanceof OEntityPlayer) {
               ((OEntityPlayer)var2).a(OStatList.F[this.c], 1);
            }

            --this.a;
            if(this.a < 0) {
               this.a = 0;
            }

            this.d = 0;
         }

      }
   }

   public void a(OEntityLiving var1, OEntityPlayer var2) {
      boolean var3 = OItem.c[this.c].a(this, var1, (OEntityLiving)var2);
      if(var3) {
         var2.a(OStatList.E[this.c], 1);
      }

   }

   public void a(int var1, int var2, int var3, int var4, OEntityPlayer var5) {
      boolean var6 = OItem.c[this.c].a(this, var1, var2, var3, var4, var5);
      if(var6) {
         var5.a(OStatList.E[this.c], 1);
      }

   }

   public int a(OEntity var1) {
      return OItem.c[this.c].a(var1);
   }

   public boolean b(OBlock var1) {
      return OItem.c[this.c].a(var1);
   }

   public void a(OEntityPlayer var1) {}

   public void a(OEntityLiving var1) {
      OItem.c[this.c].a(this, var1);
   }

   public OItemStack j() {
      return new OItemStack(this.c, this.a, this.d);
   }

   public static boolean a(OItemStack var0, OItemStack var1) {
      return var0 == null && var1 == null?true:(var0 != null && var1 != null?var0.d(var1):false);
   }

   private boolean d(OItemStack var1) {
      return this.a != var1.a?false:(this.c != var1.c?false:this.d == var1.d);
   }

   public boolean a(OItemStack var1) {
      return this.c == var1.c && this.d == var1.d;
   }

   public static OItemStack b(OItemStack var0) {
      return var0 == null?null:var0.j();
   }

   public String toString() {
      // CanaryMod: fix jarjar
      return this.a + "x" + OItem.c[this.c].a() + "@" + this.d;
   }

   public void a(OWorld var1, OEntity var2, int var3, boolean var4) {
      if(this.b > 0) {
         --this.b;
      }

      OItem.c[this.c].a(this, var1, var2, var3, var4);
   }

   public void b(OWorld var1, OEntityPlayer var2) {
      var2.a(OStatList.D[this.c], this.a);
      OItem.c[this.c].c(this, var1, var2);
   }

   public boolean c(OItemStack var1) {
      return this.c == var1.c && this.a == var1.a && this.d == var1.d;
   }
}
