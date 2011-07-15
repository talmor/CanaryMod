
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class OWorld implements OIBlockAccess {

   public boolean a = false;
   private List C = new ArrayList();
   public List b = new ArrayList();
   private List D = new ArrayList();
   private TreeSet E = new TreeSet();
   private Set F = new HashSet();
   public List c = new ArrayList();
   private List G = new ArrayList();
   public List d = new ArrayList();
   public List e = new ArrayList();
   private long H = 16777215L;
   public int f = 0;
   protected int g = (new Random()).nextInt();
   protected final int h = 1013904223;
   protected float i;
   protected float j;
   protected float k;
   protected float l;
   protected int m = 0;
   public int n = 0;
   public boolean o = false;
   private long I = System.currentTimeMillis();
   protected int p = 40;
   public int q;
   public Random r = new Random();
   public boolean s = false;
   public final OWorldProvider t;
   protected List u = new ArrayList();
   protected OIChunkProvider v;
   protected final OISaveHandler w;
   protected OWorldInfo x;
   public boolean y;
   private boolean J;
   public OMapStorage z;
   private ArrayList K = new ArrayList();
   private boolean L;
   private int M = 0;
   private boolean N = true;
   private boolean O = true;
   static int A = 0;
   private Set P = new HashSet();
   private int Q;
   private List R;
   public boolean B;

   // CanaryMod
   public final World world = new World((OWorldServer) this);


   public OWorldChunkManager a() {
      return this.t.b;
   }

   public OWorld(OISaveHandler var1, String var2, long var3, OWorldProvider var5) {
      this.Q = this.r.nextInt(12000);
      this.R = new ArrayList();
      this.B = false;
      this.w = var1;
      this.z = new OMapStorage(var1);
      this.x = var1.c();
      this.s = this.x == null;
      if(var5 != null) {
         this.t = var5;
      } else if(this.x != null && this.x.h() == -1) {
         this.t = OWorldProvider.a(-1);
      } else {
         this.t = OWorldProvider.a(0);
      }

      boolean var6 = false;
      if(this.x == null) {
         this.x = new OWorldInfo(var3, var2);
         var6 = true;
      } else {
         this.x.a(var2);
      }

      this.t.a(this);
      this.v = this.b();
      if(var6) {
         this.c();
      }

      this.g();
      this.x();
   }

   protected OIChunkProvider b() {
      OIChunkLoader var1 = this.w.a(this.t);
      return new OChunkProvider(this, var1, this.t.b());
   }

   protected void c() {
      this.y = true;
      int var1 = 0;
      byte var2 = 64;

      int var3;
      for(var3 = 0; !this.t.a(var1, var3); var3 += this.r.nextInt(64) - this.r.nextInt(64)) {
         var1 += this.r.nextInt(64) - this.r.nextInt(64);
      }

      this.x.a(var1, var2, var3);
      this.y = false;
   }

   public int a(int var1, int var2) {
      int var3;
      for(var3 = 63; !this.f(var1, var3 + 1, var2); ++var3) {
         ;
      }

      return this.a(var1, var3, var2);
   }

   public void a(boolean var1, OIProgressUpdate var2) {
      if(this.v.b()) {
         if(var2 != null) {
            var2.a("Saving level");
         }

         this.w();
         if(var2 != null) {
            var2.b("Saving chunks");
         }

         this.v.a(var1, var2);
      }
   }

   private void w() {
      this.k();
      this.w.a(this.x, this.d);
      this.z.a();
   }

   public int a(int var1, int var2, int var3) {
      return var1 >= -32000000 && var3 >= -32000000 && var1 < 32000000 && var3 <= 32000000?(var2 < 0?0:(var2 >= 128?0:this.c(var1 >> 4, var3 >> 4).a(var1 & 15, var2, var3 & 15))):0;
   }

   public boolean f(int var1, int var2, int var3) {
      return this.a(var1, var2, var3) == 0;
   }

   public boolean g(int var1, int var2, int var3) {
      return var2 >= 0 && var2 < 128?this.g(var1 >> 4, var3 >> 4):false;
   }

   public boolean a(int var1, int var2, int var3, int var4) {
      return this.a(var1 - var4, var2 - var4, var3 - var4, var1 + var4, var2 + var4, var3 + var4);
   }

   public boolean a(int var1, int var2, int var3, int var4, int var5, int var6) {
      if(var5 >= 0 && var2 < 128) {
         var1 >>= 4;
         var2 >>= 4;
         var3 >>= 4;
         var4 >>= 4;
         var5 >>= 4;
         var6 >>= 4;

         for(int var7 = var1; var7 <= var4; ++var7) {
            for(int var8 = var3; var8 <= var6; ++var8) {
               if(!this.g(var7, var8)) {
                  return false;
               }
            }
         }

         return true;
      } else {
         return false;
      }
   }

   private boolean g(int var1, int var2) {
      return this.v.a(var1, var2);
   }

   public OChunk b(int var1, int var2) {
      return this.c(var1 >> 4, var2 >> 4);
   }

   public OChunk c(int var1, int var2) {
      return this.v.b(var1, var2);
   }

   public boolean a(int var1, int var2, int var3, int var4, int var5) {
      if(var1 >= -32000000 && var3 >= -32000000 && var1 < 32000000 && var3 <= 32000000) {
         if(var2 < 0) {
            return false;
         } else if(var2 >= 128) {
            return false;
         } else {
            OChunk var6 = this.c(var1 >> 4, var3 >> 4);
            return var6.a(var1 & 15, var2, var3 & 15, var4, var5);
         }
      } else {
         return false;
      }
   }

   public boolean b(int var1, int var2, int var3, int var4) {
      if(var1 >= -32000000 && var3 >= -32000000 && var1 < 32000000 && var3 <= 32000000) {
         if(var2 < 0) {
            return false;
         } else if(var2 >= 128) {
            return false;
         } else {
            OChunk var5 = this.c(var1 >> 4, var3 >> 4);
            return var5.a(var1 & 15, var2, var3 & 15, var4);
         }
      } else {
         return false;
      }
   }

   public OMaterial d(int var1, int var2, int var3) {
      int var4 = this.a(var1, var2, var3);
      return var4 == 0?OMaterial.a:OBlock.m[var4].bA;
   }

   public int c(int var1, int var2, int var3) {
      if(var1 >= -32000000 && var3 >= -32000000 && var1 < 32000000 && var3 <= 32000000) {
         if(var2 < 0) {
            return 0;
         } else if(var2 >= 128) {
            return 0;
         } else {
            OChunk var4 = this.c(var1 >> 4, var3 >> 4);
            var1 &= 15;
            var3 &= 15;
            return var4.b(var1, var2, var3);
         }
      } else {
         return 0;
      }
   }

   public void c(int var1, int var2, int var3, int var4) {
      if(this.d(var1, var2, var3, var4)) {
         int var5 = this.a(var1, var2, var3);
         if(OBlock.t[var5 & 255]) {
            this.f(var1, var2, var3, var5);
         } else {
            this.h(var1, var2, var3, var5);
         }
      }

   }

   public boolean d(int var1, int var2, int var3, int var4) {
      if(var1 >= -32000000 && var3 >= -32000000 && var1 < 32000000 && var3 <= 32000000) {
         if(var2 < 0) {
            return false;
         } else if(var2 >= 128) {
            return false;
         } else {
            OChunk var5 = this.c(var1 >> 4, var3 >> 4);
            var1 &= 15;
            var3 &= 15;
            var5.b(var1, var2, var3, var4);
            return true;
         }
      } else {
         return false;
      }
   }

   public boolean e(int var1, int var2, int var3, int var4) {
      if(this.b(var1, var2, var3, var4)) {
         this.f(var1, var2, var3, var4);
         return true;
      } else {
         return false;
      }
   }

   public boolean b(int var1, int var2, int var3, int var4, int var5) {
      if(this.a(var1, var2, var3, var4, var5)) {
         this.f(var1, var2, var3, var4);
         return true;
      } else {
         return false;
      }
   }

   public void h(int var1, int var2, int var3) {
      for(int var4 = 0; var4 < this.u.size(); ++var4) {
         ((OIWorldAccess)this.u.get(var4)).a(var1, var2, var3);
      }

   }

   protected void f(int var1, int var2, int var3, int var4) {
      this.h(var1, var2, var3);
      this.h(var1, var2, var3, var4);
   }

   public void g(int var1, int var2, int var3, int var4) {
      if(var3 > var4) {
         int var5 = var4;
         var4 = var3;
         var3 = var5;
      }

      this.b(var1, var3, var2, var1, var4, var2);
   }

   public void i(int var1, int var2, int var3) {
      for(int var4 = 0; var4 < this.u.size(); ++var4) {
         ((OIWorldAccess)this.u.get(var4)).a(var1, var2, var3, var1, var2, var3);
      }

   }

   public void b(int var1, int var2, int var3, int var4, int var5, int var6) {
      for(int var7 = 0; var7 < this.u.size(); ++var7) {
         ((OIWorldAccess)this.u.get(var7)).a(var1, var2, var3, var4, var5, var6);
      }

   }

   public void h(int var1, int var2, int var3, int var4) {
      this.k(var1 - 1, var2, var3, var4);
      this.k(var1 + 1, var2, var3, var4);
      this.k(var1, var2 - 1, var3, var4);
      this.k(var1, var2 + 1, var3, var4);
      this.k(var1, var2, var3 - 1, var4);
      this.k(var1, var2, var3 + 1, var4);
   }

   private void k(int var1, int var2, int var3, int var4) {
      if(!this.o && !this.B) {
         OBlock var5 = OBlock.m[this.a(var1, var2, var3)];
         if(var5 != null) {
            var5.b(this, var1, var2, var3, var4);
         }

      }
   }

   public boolean j(int var1, int var2, int var3) {
      return this.c(var1 >> 4, var3 >> 4).c(var1 & 15, var2, var3 & 15);
   }

   public int k(int var1, int var2, int var3) {
      if(var2 < 0) {
         return 0;
      } else {
         if(var2 >= 128) {
            var2 = 127;
         }

         return this.c(var1 >> 4, var3 >> 4).c(var1 & 15, var2, var3 & 15, 0);
      }
   }

   public int l(int var1, int var2, int var3) {
      return this.a(var1, var2, var3, true);
   }

   public int a(int var1, int var2, int var3, boolean var4) {
      if(var1 >= -32000000 && var3 >= -32000000 && var1 < 32000000 && var3 <= 32000000) {
         if(var4) {
            int var5 = this.a(var1, var2, var3);
            if(var5 == OBlock.al.bn || var5 == OBlock.aB.bn || var5 == OBlock.aI.bn || var5 == OBlock.au.bn) {
               int var6 = this.a(var1, var2 + 1, var3, false);
               int var7 = this.a(var1 + 1, var2, var3, false);
               int var8 = this.a(var1 - 1, var2, var3, false);
               int var9 = this.a(var1, var2, var3 + 1, false);
               int var10 = this.a(var1, var2, var3 - 1, false);
               if(var7 > var6) {
                  var6 = var7;
               }

               if(var8 > var6) {
                  var6 = var8;
               }

               if(var9 > var6) {
                  var6 = var9;
               }

               if(var10 > var6) {
                  var6 = var10;
               }

               return var6;
            }
         }

         if(var2 < 0) {
            return 0;
         } else {
            if(var2 >= 128) {
               var2 = 127;
            }

            OChunk var11 = this.c(var1 >> 4, var3 >> 4);
            var1 &= 15;
            var3 &= 15;
            return var11.c(var1, var2, var3, this.f);
         }
      } else {
         return 15;
      }
   }

   public boolean m(int var1, int var2, int var3) {
      if(var1 >= -32000000 && var3 >= -32000000 && var1 < 32000000 && var3 <= 32000000) {
         if(var2 < 0) {
            return false;
         } else if(var2 >= 128) {
            return true;
         } else if(!this.g(var1 >> 4, var3 >> 4)) {
            return false;
         } else {
            OChunk var4 = this.c(var1 >> 4, var3 >> 4);
            var1 &= 15;
            var3 &= 15;
            return var4.c(var1, var2, var3);
         }
      } else {
         return false;
      }
   }

   public int d(int var1, int var2) {
      if(var1 >= -32000000 && var2 >= -32000000 && var1 < 32000000 && var2 <= 32000000) {
         if(!this.g(var1 >> 4, var2 >> 4)) {
            return 0;
         } else {
            OChunk var3 = this.c(var1 >> 4, var2 >> 4);
            return var3.b(var1 & 15, var2 & 15);
         }
      } else {
         return 0;
      }
   }

   public void a(OEnumSkyBlock var1, int var2, int var3, int var4, int var5) {
      if(!this.t.e || var1 != OEnumSkyBlock.a) {
         if(this.g(var2, var3, var4)) {
            if(var1 == OEnumSkyBlock.a) {
               if(this.m(var2, var3, var4)) {
                  var5 = 15;
               }
            } else if(var1 == OEnumSkyBlock.b) {
               int var6 = this.a(var2, var3, var4);
               if(OBlock.s[var6] > var5) {
                  var5 = OBlock.s[var6];
               }
            }

            if(this.a(var1, var2, var3, var4) != var5) {
               this.a(var1, var2, var3, var4, var2, var3, var4);
            }

         }
      }
   }

   public int a(OEnumSkyBlock var1, int var2, int var3, int var4) {
      if(var3 < 0) {
         var3 = 0;
      }

      if(var3 >= 128) {
         var3 = 127;
      }

      if(var3 >= 0 && var3 < 128 && var2 >= -32000000 && var4 >= -32000000 && var2 < 32000000 && var4 <= 32000000) {
         int var5 = var2 >> 4;
         int var6 = var4 >> 4;
         if(!this.g(var5, var6)) {
            return 0;
         } else {
            OChunk var7 = this.c(var5, var6);
            return var7.a(var1, var2 & 15, var3, var4 & 15);
         }
      } else {
         return var1.c;
      }
   }

   public void b(OEnumSkyBlock var1, int var2, int var3, int var4, int var5) {
      if(var2 >= -32000000 && var4 >= -32000000 && var2 < 32000000 && var4 <= 32000000) {
         if(var3 >= 0) {
            if(var3 < 128) {
               if(this.g(var2 >> 4, var4 >> 4)) {
                  OChunk var6 = this.c(var2 >> 4, var4 >> 4);
                  var6.a(var1, var2 & 15, var3, var4 & 15, var5);

                  for(int var7 = 0; var7 < this.u.size(); ++var7) {
                     ((OIWorldAccess)this.u.get(var7)).a(var2, var3, var4);
                  }

               }
            }
         }
      }
   }

   public float n(int var1, int var2, int var3) {
      return this.t.f[this.l(var1, var2, var3)];
   }

   public boolean d() {
      return this.f < 4;
   }

   public OMovingObjectPosition a(OVec3D var1, OVec3D var2) {
      return this.a(var1, var2, false, false);
   }

   public OMovingObjectPosition a(OVec3D var1, OVec3D var2, boolean var3) {
      return this.a(var1, var2, var3, false);
   }

   public OMovingObjectPosition a(OVec3D var1, OVec3D var2, boolean var3, boolean var4) {
      if(!Double.isNaN(var1.a) && !Double.isNaN(var1.b) && !Double.isNaN(var1.c)) {
         if(!Double.isNaN(var2.a) && !Double.isNaN(var2.b) && !Double.isNaN(var2.c)) {
            int var5 = OMathHelper.b(var2.a);
            int var6 = OMathHelper.b(var2.b);
            int var7 = OMathHelper.b(var2.c);
            int var8 = OMathHelper.b(var1.a);
            int var9 = OMathHelper.b(var1.b);
            int var10 = OMathHelper.b(var1.c);
            int var11 = this.a(var8, var9, var10);
            int var12 = this.c(var8, var9, var10);
            OBlock var13 = OBlock.m[var11];
            if((!var4 || var13 == null || var13.e(this, var8, var9, var10) != null) && var11 > 0 && var13.a(var12, var3)) {
               OMovingObjectPosition var14 = var13.a(this, var8, var9, var10, var1, var2);
               if(var14 != null) {
                  return var14;
               }
            }

            var11 = 200;

            while(var11-- >= 0) {
               if(Double.isNaN(var1.a) || Double.isNaN(var1.b) || Double.isNaN(var1.c)) {
                  return null;
               }

               if(var8 == var5 && var9 == var6 && var10 == var7) {
                  return null;
               }

               boolean var39 = true;
               boolean var40 = true;
               boolean var41 = true;
               double var15 = 999.0D;
               double var17 = 999.0D;
               double var19 = 999.0D;
               if(var5 > var8) {
                  var15 = (double)var8 + 1.0D;
               } else if(var5 < var8) {
                  var15 = (double)var8 + 0.0D;
               } else {
                  var39 = false;
               }

               if(var6 > var9) {
                  var17 = (double)var9 + 1.0D;
               } else if(var6 < var9) {
                  var17 = (double)var9 + 0.0D;
               } else {
                  var40 = false;
               }

               if(var7 > var10) {
                  var19 = (double)var10 + 1.0D;
               } else if(var7 < var10) {
                  var19 = (double)var10 + 0.0D;
               } else {
                  var41 = false;
               }

               double var21 = 999.0D;
               double var23 = 999.0D;
               double var25 = 999.0D;
               double var27 = var2.a - var1.a;
               double var29 = var2.b - var1.b;
               double var31 = var2.c - var1.c;
               if(var39) {
                  var21 = (var15 - var1.a) / var27;
               }

               if(var40) {
                  var23 = (var17 - var1.b) / var29;
               }

               if(var41) {
                  var25 = (var19 - var1.c) / var31;
               }

               boolean var33 = false;
               byte var42;
               if(var21 < var23 && var21 < var25) {
                  if(var5 > var8) {
                     var42 = 4;
                  } else {
                     var42 = 5;
                  }

                  var1.a = var15;
                  var1.b += var29 * var21;
                  var1.c += var31 * var21;
               } else if(var23 < var25) {
                  if(var6 > var9) {
                     var42 = 0;
                  } else {
                     var42 = 1;
                  }

                  var1.a += var27 * var23;
                  var1.b = var17;
                  var1.c += var31 * var23;
               } else {
                  if(var7 > var10) {
                     var42 = 2;
                  } else {
                     var42 = 3;
                  }

                  var1.a += var27 * var25;
                  var1.b += var29 * var25;
                  var1.c = var19;
               }

               OVec3D var34 = OVec3D.b(var1.a, var1.b, var1.c);
               var8 = (int)(var34.a = (double)OMathHelper.b(var1.a));
               if(var42 == 5) {
                  --var8;
                  ++var34.a;
               }

               var9 = (int)(var34.b = (double)OMathHelper.b(var1.b));
               if(var42 == 1) {
                  --var9;
                  ++var34.b;
               }

               var10 = (int)(var34.c = (double)OMathHelper.b(var1.c));
               if(var42 == 3) {
                  --var10;
                  ++var34.c;
               }

               int var35 = this.a(var8, var9, var10);
               int var36 = this.c(var8, var9, var10);
               OBlock var37 = OBlock.m[var35];
               if((!var4 || var37 == null || var37.e(this, var8, var9, var10) != null) && var35 > 0 && var37.a(var36, var3)) {
                  OMovingObjectPosition var38 = var37.a(this, var8, var9, var10, var1, var2);
                  if(var38 != null) {
                     return var38;
                  }
               }
            }

            return null;
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   public void a(OEntity var1, String var2, float var3, float var4) {
      for(int var5 = 0; var5 < this.u.size(); ++var5) {
         ((OIWorldAccess)this.u.get(var5)).a(var2, var1.aP, var1.aQ - (double)var1.bi, var1.aR, var3, var4);
      }

   }

   public void a(double var1, double var3, double var5, String var7, float var8, float var9) {
      for(int var10 = 0; var10 < this.u.size(); ++var10) {
         ((OIWorldAccess)this.u.get(var10)).a(var7, var1, var3, var5, var8, var9);
      }

   }

   public void a(String var1, int var2, int var3, int var4) {
      for(int var5 = 0; var5 < this.u.size(); ++var5) {
         ((OIWorldAccess)this.u.get(var5)).a(var1, var2, var3, var4);
      }

   }

   public void a(String var1, double var2, double var4, double var6, double var8, double var10, double var12) {
      for(int var14 = 0; var14 < this.u.size(); ++var14) {
         ((OIWorldAccess)this.u.get(var14)).a(var1, var2, var4, var6, var8, var10, var12);
      }

   }

   public boolean a(OEntity var1) {
      this.e.add(var1);
      return true;
   }

   public boolean b(OEntity var1) {
      // CanaryMod: mob spawn hook
      if (var1 instanceof OEntityLiving && !(var1 instanceof OEntityPlayer))
         if ((etc.getInstance().getMobSpawnRate() < 100 && etc.getInstance().getMobSpawnRate() > 0 && etc.getInstance().getMobSpawnRate() <= r.nextInt(101)) || etc.getInstance().getMobSpawnRate() <= 0 || (Boolean) (etc.getLoader().callHook(PluginLoader.Hook.MOB_SPAWN, new Mob((OEntityLiving) var1))))
            return false;

      int var2 = OMathHelper.b(var1.aP / 16.0D);
      int var3 = OMathHelper.b(var1.aR / 16.0D);
      boolean var4 = false;
      if(var1 instanceof OEntityPlayer) {
         var4 = true;
      }

      if(!var4 && !this.g(var2, var3)) {
         return false;
      } else {
         if(var1 instanceof OEntityPlayer) {
            OEntityPlayer var5 = (OEntityPlayer)var1;
            this.d.add(var5);
            this.r();
         }

         this.c(var2, var3).a(var1);
         this.b.add(var1);
         this.c(var1);
         return true;
      }
   }

   protected void c(OEntity var1) {
      for(int var2 = 0; var2 < this.u.size(); ++var2) {
         ((OIWorldAccess)this.u.get(var2)).a(var1);
      }

   }

   protected void d(OEntity var1) {
      for(int var2 = 0; var2 < this.u.size(); ++var2) {
         ((OIWorldAccess)this.u.get(var2)).b(var1);
      }

   }

   public void e(OEntity var1) {
      if(var1.aJ != null) {
         var1.aJ.b((OEntity)null);
      }

      if(var1.aK != null) {
         var1.b((OEntity)null);
      }

      var1.J();
      if(var1 instanceof OEntityPlayer) {
         this.d.remove((OEntityPlayer)var1);
         this.r();
      }

   }

   public void f(OEntity var1) {
      var1.J();
      if(var1 instanceof OEntityPlayer) {
         this.d.remove((OEntityPlayer)var1);
         this.r();
      }

      int var2 = var1.bH;
      int var3 = var1.bJ;
      if(var1.bG && this.g(var2, var3)) {
         this.c(var2, var3).b(var1);
      }

      this.b.remove(var1);
      this.d(var1);
   }

   public void a(OIWorldAccess var1) {
      this.u.add(var1);
   }

   public List a(OEntity var1, OAxisAlignedBB var2) {
      this.K.clear();
      int var3 = OMathHelper.b(var2.a);
      int var4 = OMathHelper.b(var2.d + 1.0D);
      int var5 = OMathHelper.b(var2.b);
      int var6 = OMathHelper.b(var2.e + 1.0D);
      int var7 = OMathHelper.b(var2.c);
      int var8 = OMathHelper.b(var2.f + 1.0D);

      for(int var9 = var3; var9 < var4; ++var9) {
         for(int var10 = var7; var10 < var8; ++var10) {
            if(this.g(var9, 64, var10)) {
               for(int var11 = var5 - 1; var11 < var6; ++var11) {
                  OBlock var12 = OBlock.m[this.a(var9, var11, var10)];
                  if(var12 != null) {
                     var12.a(this, var9, var11, var10, var2, this.K);
                  }
               }
            }
         }
      }

      double var13 = 0.25D;
      List var17 = this.b(var1, var2.b(var13, var13, var13));

      for(int var16 = 0; var16 < var17.size(); ++var16) {
         OAxisAlignedBB var15 = ((OEntity)var17.get(var16)).e_();
         if(var15 != null && var15.a(var2)) {
            this.K.add(var15);
         }

         var15 = var1.a_((OEntity)var17.get(var16));
         if(var15 != null && var15.a(var2)) {
            this.K.add(var15);
         }
      }

      return this.K;
   }

   public int a(float var1) {
      float var2 = this.b(var1);
      float var3 = 1.0F - (OMathHelper.b(var2 * 3.1415927F * 2.0F) * 2.0F + 0.5F);
      if(var3 < 0.0F) {
         var3 = 0.0F;
      }

      if(var3 > 1.0F) {
         var3 = 1.0F;
      }

      var3 = 1.0F - var3;
      var3 = (float)((double)var3 * (1.0D - (double)(this.d(var1) * 5.0F) / 16.0D));
      var3 = (float)((double)var3 * (1.0D - (double)(this.c(var1) * 5.0F) / 16.0D));
      var3 = 1.0F - var3;
      return (int)(var3 * 11.0F);
   }

   public float b(float var1) {
      return this.t.a(this.x.f(), var1);
   }

   public int e(int var1, int var2) {
      OChunk var3 = this.b(var1, var2);
      int var4 = 127;
      var1 &= 15;

      for(var2 &= 15; var4 > 0; --var4) {
         int var5 = var3.a(var1, var4, var2);
         OMaterial var6 = var5 == 0?OMaterial.a:OBlock.m[var5].bA;
         if(var6.c() || var6.d()) {
            return var4 + 1;
         }
      }

      return -1;
   }

   public int f(int var1, int var2) {
      OChunk var3 = this.b(var1, var2);
      int var4 = 127;
      var1 &= 15;

      for(var2 &= 15; var4 > 0; --var4) {
         int var5 = var3.a(var1, var4, var2);
         if(var5 != 0 && OBlock.m[var5].bA.c()) {
            return var4 + 1;
         }
      }

      return -1;
   }

   public void c(int var1, int var2, int var3, int var4, int var5) {
      ONextTickListEntry var6 = new ONextTickListEntry(var1, var2, var3, var4);
      byte var7 = 8;
      if(this.a) {
         if(this.a(var6.a - var7, var6.b - var7, var6.c - var7, var6.a + var7, var6.b + var7, var6.c + var7)) {
            int var8 = this.a(var6.a, var6.b, var6.c);
            if(var8 == var6.d && var8 > 0) {
               OBlock.m[var8].a(this, var6.a, var6.b, var6.c, this.r);
            }
         }

      } else {
         if(this.a(var1 - var7, var2 - var7, var3 - var7, var1 + var7, var2 + var7, var3 + var7)) {
            if(var4 > 0) {
               var6.a((long)var5 + this.x.f());
            }

            if(!this.F.contains(var6)) {
               this.F.add(var6);
               this.E.add(var6);
            }
         }

      }
   }

   public void e() {
      int var1;
      OEntity var2;
      for(var1 = 0; var1 < this.e.size(); ++var1) {
         var2 = (OEntity)this.e.get(var1);
         var2.m_();
         if(var2.bh) {
            this.e.remove(var1--);
         }
      }

      this.b.removeAll(this.D);

      int var3;
      int var4;
      for(var1 = 0; var1 < this.D.size(); ++var1) {
         var2 = (OEntity)this.D.get(var1);
         var3 = var2.bH;
         var4 = var2.bJ;
         if(var2.bG && this.g(var3, var4)) {
            this.c(var3, var4).b(var2);
         }
      }

      for(var1 = 0; var1 < this.D.size(); ++var1) {
         this.d((OEntity)this.D.get(var1));
      }

      this.D.clear();

      for(var1 = 0; var1 < this.b.size(); ++var1) {
         var2 = (OEntity)this.b.get(var1);
         if(var2.aK != null) {
            if(!var2.aK.bh && var2.aK.aJ == var2) {
               continue;
            }

            var2.aK.aJ = null;
            var2.aK = null;
         }

         if(!var2.bh) {
            this.g(var2);
         }

         if(var2.bh) {
            var3 = var2.bH;
            var4 = var2.bJ;
            if(var2.bG && this.g(var3, var4)) {
               this.c(var3, var4).b(var2);
            }

            this.b.remove(var1--);
            this.d(var2);
         }
      }

      this.L = true;
      Iterator var10 = this.c.iterator();

      while(var10.hasNext()) {
         OTileEntity var5 = (OTileEntity)var10.next();
         if(!var5.g()) {
            var5.g_();
         }

         if(var5.g()) {
            var10.remove();
            OChunk var7 = this.c(var5.e >> 4, var5.g >> 4);
            if(var7 != null) {
               var7.e(var5.e & 15, var5.f, var5.g & 15);
            }
         }
      }

      this.L = false;
      if(!this.G.isEmpty()) {
         Iterator var6 = this.G.iterator();

         while(var6.hasNext()) {
            OTileEntity var8 = (OTileEntity)var6.next();
            if(!var8.g()) {
               if(!this.c.contains(var8)) {
                  this.c.add(var8);
               }

               OChunk var9 = this.c(var8.e >> 4, var8.g >> 4);
               if(var9 != null) {
                  var9.a(var8.e & 15, var8.f, var8.g & 15, var8);
               }

               this.h(var8.e, var8.f, var8.g);
            }
         }

         this.G.clear();
      }

   }

   public void a(Collection var1) {
      if(this.L) {
         this.G.addAll(var1);
      } else {
         this.c.addAll(var1);
      }

   }

   public void g(OEntity var1) {
      this.a(var1, true);
   }

   public void a(OEntity var1, boolean var2) {
      int var3 = OMathHelper.b(var1.aP);
      int var4 = OMathHelper.b(var1.aR);
      byte var5 = 32;
      if(!var2 || this.a(var3 - var5, 0, var4 - var5, var3 + var5, 128, var4 + var5)) {
         var1.bo = var1.aP;
         var1.bp = var1.aQ;
         var1.bq = var1.aR;
         var1.aX = var1.aV;
         var1.aY = var1.aW;
         if(var2 && var1.bG) {
            if(var1.aK != null) {
               var1.E();
            } else {
               var1.m_();
            }
         }

         if(Double.isNaN(var1.aP) || Double.isInfinite(var1.aP)) {
            var1.aP = var1.bo;
         }

         if(Double.isNaN(var1.aQ) || Double.isInfinite(var1.aQ)) {
            var1.aQ = var1.bp;
         }

         if(Double.isNaN(var1.aR) || Double.isInfinite(var1.aR)) {
            var1.aR = var1.bq;
         }

         if(Double.isNaN((double)var1.aW) || Double.isInfinite((double)var1.aW)) {
            var1.aW = var1.aY;
         }

         if(Double.isNaN((double)var1.aV) || Double.isInfinite((double)var1.aV)) {
            var1.aV = var1.aX;
         }

         int var6 = OMathHelper.b(var1.aP / 16.0D);
         int var7 = OMathHelper.b(var1.aQ / 16.0D);
         int var8 = OMathHelper.b(var1.aR / 16.0D);
         if(!var1.bG || var1.bH != var6 || var1.bI != var7 || var1.bJ != var8) {
            if(var1.bG && this.g(var1.bH, var1.bJ)) {
               this.c(var1.bH, var1.bJ).a(var1, var1.bI);
            }

            if(this.g(var6, var8)) {
               var1.bG = true;
               this.c(var6, var8).a(var1);
            } else {
               var1.bG = false;
            }
         }

         if(var2 && var1.bG && var1.aJ != null) {
            if(!var1.aJ.bh && var1.aJ.aK == var1) {
               this.g(var1.aJ);
            } else {
               var1.aJ.aK = null;
               var1.aJ = null;
            }
         }

      }
   }

   public boolean a(OAxisAlignedBB var1) {
      List var2 = this.b((OEntity)null, var1);

      for(int var3 = 0; var3 < var2.size(); ++var3) {
         OEntity var4 = (OEntity)var2.get(var3);
         if(!var4.bh && var4.aI) {
            return false;
         }
      }

      return true;
   }

   public boolean b(OAxisAlignedBB var1) {
      int var2 = OMathHelper.b(var1.a);
      int var3 = OMathHelper.b(var1.d + 1.0D);
      int var4 = OMathHelper.b(var1.b);
      int var5 = OMathHelper.b(var1.e + 1.0D);
      int var6 = OMathHelper.b(var1.c);
      int var7 = OMathHelper.b(var1.f + 1.0D);
      if(var1.a < 0.0D) {
         --var2;
      }

      if(var1.b < 0.0D) {
         --var4;
      }

      if(var1.c < 0.0D) {
         --var6;
      }

      for(int var8 = var2; var8 < var3; ++var8) {
         for(int var9 = var4; var9 < var5; ++var9) {
            for(int var10 = var6; var10 < var7; ++var10) {
               OBlock var11 = OBlock.m[this.a(var8, var9, var10)];
               if(var11 != null) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public boolean c(OAxisAlignedBB var1) {
      int var2 = OMathHelper.b(var1.a);
      int var3 = OMathHelper.b(var1.d + 1.0D);
      int var4 = OMathHelper.b(var1.b);
      int var5 = OMathHelper.b(var1.e + 1.0D);
      int var6 = OMathHelper.b(var1.c);
      int var7 = OMathHelper.b(var1.f + 1.0D);
      if(var1.a < 0.0D) {
         --var2;
      }

      if(var1.b < 0.0D) {
         --var4;
      }

      if(var1.c < 0.0D) {
         --var6;
      }

      for(int var8 = var2; var8 < var3; ++var8) {
         for(int var9 = var4; var9 < var5; ++var9) {
            for(int var10 = var6; var10 < var7; ++var10) {
               OBlock var11 = OBlock.m[this.a(var8, var9, var10)];
               if(var11 != null && var11.bA.d()) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public boolean d(OAxisAlignedBB var1) {
      int var2 = OMathHelper.b(var1.a);
      int var3 = OMathHelper.b(var1.d + 1.0D);
      int var4 = OMathHelper.b(var1.b);
      int var5 = OMathHelper.b(var1.e + 1.0D);
      int var6 = OMathHelper.b(var1.c);
      int var7 = OMathHelper.b(var1.f + 1.0D);
      if(this.a(var2, var4, var6, var3, var5, var7)) {
         for(int var8 = var2; var8 < var3; ++var8) {
            for(int var9 = var4; var9 < var5; ++var9) {
               for(int var10 = var6; var10 < var7; ++var10) {
                  int var11 = this.a(var8, var9, var10);
                  if(var11 == OBlock.as.bn || var11 == OBlock.D.bn || var11 == OBlock.E.bn) {
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   public boolean a(OAxisAlignedBB var1, OMaterial var2, OEntity var3) {
      int var4 = OMathHelper.b(var1.a);
      int var5 = OMathHelper.b(var1.d + 1.0D);
      int var6 = OMathHelper.b(var1.b);
      int var7 = OMathHelper.b(var1.e + 1.0D);
      int var8 = OMathHelper.b(var1.c);
      int var9 = OMathHelper.b(var1.f + 1.0D);
      if(!this.a(var4, var6, var8, var5, var7, var9)) {
         return false;
      } else {
         boolean var10 = false;
         OVec3D var11 = OVec3D.b(0.0D, 0.0D, 0.0D);

         for(int var12 = var4; var12 < var5; ++var12) {
            for(int var13 = var6; var13 < var7; ++var13) {
               for(int var14 = var8; var14 < var9; ++var14) {
                  OBlock var15 = OBlock.m[this.a(var12, var13, var14)];
                  if(var15 != null && var15.bA == var2) {
                     double var16 = (double)((float)(var13 + 1) - OBlockFluid.c(this.c(var12, var13, var14)));
                     if((double)var7 >= var16) {
                        var10 = true;
                        var15.a(this, var12, var13, var14, var3, var11);
                     }
                  }
               }
            }
         }

         if(var11.c() > 0.0D) {
            var11 = var11.b();
            double var18 = 0.014D;
            var3.aS += var11.a * var18;
            var3.aT += var11.b * var18;
            var3.aU += var11.c * var18;
         }

         return var10;
      }
   }

   public boolean a(OAxisAlignedBB var1, OMaterial var2) {
      int var3 = OMathHelper.b(var1.a);
      int var4 = OMathHelper.b(var1.d + 1.0D);
      int var5 = OMathHelper.b(var1.b);
      int var6 = OMathHelper.b(var1.e + 1.0D);
      int var7 = OMathHelper.b(var1.c);
      int var8 = OMathHelper.b(var1.f + 1.0D);

      for(int var9 = var3; var9 < var4; ++var9) {
         for(int var10 = var5; var10 < var6; ++var10) {
            for(int var11 = var7; var11 < var8; ++var11) {
               OBlock var12 = OBlock.m[this.a(var9, var10, var11)];
               if(var12 != null && var12.bA == var2) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   public boolean b(OAxisAlignedBB var1, OMaterial var2) {
      int var3 = OMathHelper.b(var1.a);
      int var4 = OMathHelper.b(var1.d + 1.0D);
      int var5 = OMathHelper.b(var1.b);
      int var6 = OMathHelper.b(var1.e + 1.0D);
      int var7 = OMathHelper.b(var1.c);
      int var8 = OMathHelper.b(var1.f + 1.0D);

      for(int var9 = var3; var9 < var4; ++var9) {
         for(int var10 = var5; var10 < var6; ++var10) {
            for(int var11 = var7; var11 < var8; ++var11) {
               OBlock var12 = OBlock.m[this.a(var9, var10, var11)];
               if(var12 != null && var12.bA == var2) {
                  int var13 = this.c(var9, var10, var11);
                  double var14 = (double)(var10 + 1);
                  if(var13 < 8) {
                     var14 = (double)(var10 + 1) - (double)var13 / 8.0D;
                  }

                  if(var14 >= var1.b) {
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   public OExplosion a(OEntity var1, double var2, double var4, double var6, float var8) {
      return this.a(var1, var2, var4, var6, var8, false);
   }

   public OExplosion a(OEntity var1, double var2, double var4, double var6, float var8, boolean var9) {
      OExplosion var10 = new OExplosion(this, var1, var2, var4, var6, var8);
      var10.a = var9;
      var10.a();
      var10.a(true);
      return var10;
   }

   public float a(OVec3D var1, OAxisAlignedBB var2) {
      double var3 = 1.0D / ((var2.d - var2.a) * 2.0D + 1.0D);
      double var5 = 1.0D / ((var2.e - var2.b) * 2.0D + 1.0D);
      double var7 = 1.0D / ((var2.f - var2.c) * 2.0D + 1.0D);
      int var9 = 0;
      int var10 = 0;

      for(float var11 = 0.0F; var11 <= 1.0F; var11 = (float)((double)var11 + var3)) {
         for(float var12 = 0.0F; var12 <= 1.0F; var12 = (float)((double)var12 + var5)) {
            for(float var13 = 0.0F; var13 <= 1.0F; var13 = (float)((double)var13 + var7)) {
               double var14 = var2.a + (var2.d - var2.a) * (double)var11;
               double var16 = var2.b + (var2.e - var2.b) * (double)var12;
               double var18 = var2.c + (var2.f - var2.c) * (double)var13;
               if(this.a(OVec3D.b(var14, var16, var18), var1) == null) {
                  ++var9;
               }

               ++var10;
            }
         }
      }

      return (float)var9 / (float)var10;
   }

   public void a(OEntityPlayer var1, int var2, int var3, int var4, int var5) {
      if(var5 == 0) {
         --var3;
      }

      if(var5 == 1) {
         ++var3;
      }

      if(var5 == 2) {
         --var4;
      }

      if(var5 == 3) {
         ++var4;
      }

      if(var5 == 4) {
         --var2;
      }

      if(var5 == 5) {
         ++var2;
      }

      if(this.a(var2, var3, var4) == OBlock.as.bn) {
         this.a(var1, 1004, var2, var3, var4, 0);
         this.e(var2, var3, var4, 0);
      }

   }

   public OTileEntity b(int var1, int var2, int var3) {
      OChunk var4 = this.c(var1 >> 4, var3 >> 4);
      return var4 != null?var4.d(var1 & 15, var2, var3 & 15):null;
   }

   public void a(int var1, int var2, int var3, OTileEntity var4) {
      if(!var4.g()) {
         if(this.L) {
            var4.e = var1;
            var4.f = var2;
            var4.g = var3;
            this.G.add(var4);
         } else {
            this.c.add(var4);
            OChunk var5 = this.c(var1 >> 4, var3 >> 4);
            if(var5 != null) {
               var5.a(var1 & 15, var2, var3 & 15, var4);
            }
         }
      }

   }

   public void o(int var1, int var2, int var3) {
      OTileEntity var4 = this.b(var1, var2, var3);
      if(var4 != null && this.L) {
         var4.h();
      } else {
         if(var4 != null) {
            this.c.remove(var4);
         }

         OChunk var5 = this.c(var1 >> 4, var3 >> 4);
         if(var5 != null) {
            var5.e(var1 & 15, var2, var3 & 15);
         }
      }

   }

   public boolean p(int var1, int var2, int var3) {
      OBlock var4 = OBlock.m[this.a(var1, var2, var3)];
      return var4 == null?false:var4.a();
   }

   public boolean e(int var1, int var2, int var3) {
      OBlock var4 = OBlock.m[this.a(var1, var2, var3)];
      return var4 == null?false:var4.bA.h() && var4.b();
   }

   public boolean f() {
      if(this.M >= 50) {
         return false;
      } else {
         ++this.M;

         boolean var2 = false;
         try {
            int var1 = 500;

            while(this.C.size() > 0) {
               --var1;
               if(var1 <= 0) {
                  var2 = true;
                  return var2;
               }

               ((OMetadataChunkBlock)this.C.remove(this.C.size() - 1)).a(this);
            }

            var2 = false;
         } catch (NullPointerException e) {System.out.println("Null pointers? In my OWorld?");} finally {
            --this.M;
         }

         return var2;
      }
   }

   public void a(OEnumSkyBlock var1, int var2, int var3, int var4, int var5, int var6, int var7) {
      this.a(var1, var2, var3, var4, var5, var6, var7, true);
   }

   public void a(OEnumSkyBlock var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8) {
      if(!this.t.e || var1 != OEnumSkyBlock.a) {
         ++A;

         try {
            if(A != 50) {
               int var9 = (var5 + var2) / 2;
               int var10 = (var7 + var4) / 2;
               if(this.g(var9, 64, var10)) {
                  if(!this.b(var9, var10).g()) {
                     int var11 = this.C.size();
                     int var12;
                     if(var8) {
                        var12 = 5;
                        if(var12 > var11) {
                           var12 = var11;
                        }

                        for(int var13 = 0; var13 < var12; ++var13) {
                           OMetadataChunkBlock var14 = (OMetadataChunkBlock)this.C.get(this.C.size() - var13 - 1);
                           if(var14.a == var1 && var14.a(var2, var3, var4, var5, var6, var7)) {
                              return;
                           }
                        }
                     }

                     this.C.add(new OMetadataChunkBlock(var1, var2, var3, var4, var5, var6, var7));
                     var12 = 1000000;
                     if(this.C.size() > 1000000) {
                        System.out.println("More than " + var12 + " updates, aborting lighting updates");
                        this.C.clear();
                     }

                  }
               }
            }
         } finally {
            --A;
         }
      }
   }

   public void g() {
      int var1 = this.a(1.0F);
      if(var1 != this.f) {
         this.f = var1;
      }

   }

   public void a(boolean var1, boolean var2) {
      this.N = var1;
      this.O = var2;
   }

   public void h() {
      this.i();
      long var2;
      if(this.t()) {
         boolean var1 = false;
         if(this.N && this.q >= 1) {
            var1 = OSpawnerAnimals.a(this, this.d);
         }

         if(!var1) {
            var2 = this.x.f() + 24000L;
            this.x.a(var2 - var2 % 24000L);
            this.s();
         }
      }

      OSpawnerAnimals.a(this, this.N, this.O);
      this.v.a();
      int var5 = this.a(1.0F);
      if(var5 != this.f) {
         this.f = var5;

         for(int var4 = 0; var4 < this.u.size(); ++var4) {
            ((OIWorldAccess)this.u.get(var4)).a();
         }
      }

      var2 = this.x.f() + 1L;
      if(var2 % (long)this.p == 0L) {
         this.a(false, (OIProgressUpdate)null);
      }
      // CanaryMod: Time hook
      if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.TIME_CHANGE, world, var2))
        this.x.a(var2);
      this.a(false);
      this.j();
   }

   private void x() {
      if(this.x.l()) {
         this.j = 1.0F;
         if(this.x.j()) {
            this.l = 1.0F;
         }
      }

   }

   protected void i() {
      if(!this.t.e) {
         if(this.m > 0) {
            --this.m;
         }

         int var1 = this.x.k();
         if(var1 <= 0) {
            if(this.x.j()) {
               this.x.b(this.r.nextInt(12000) + 3600);
            } else {
               this.x.b(this.r.nextInt(168000) + 12000);
            }
         } else {
            --var1;
            this.x.b(var1);
            if(var1 <= 0) {
               // CanaryMod: Thunder hook
               if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.THUNDER_CHANGE, world, !this.x.j()))
                  this.x.a(!this.x.j());
            }
         }

         int var2 = this.x.m();
         if(var2 <= 0) {
            if(this.x.l()) {
               this.x.c(this.r.nextInt(12000) + 12000);
            } else {
               this.x.c(this.r.nextInt(168000) + 12000);
            }
         } else {
            --var2;
            this.x.c(var2);
            if(var2 <= 0) {
               // CanaryMod: Weather hook
               if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.WEATHER_CHANGE, world, !this.x.l()))
                  this.x.b(!this.x.l());
            }
         }

         this.i = this.j;
         if(this.x.l()) {
            this.j = (float)((double)this.j + 0.01D);
         } else {
            this.j = (float)((double)this.j - 0.01D);
         }

         if(this.j < 0.0F) {
            this.j = 0.0F;
         }

         if(this.j > 1.0F) {
            this.j = 1.0F;
         }

         this.k = this.l;
         if(this.x.j()) {
            this.l = (float)((double)this.l + 0.01D);
         } else {
            this.l = (float)((double)this.l - 0.01D);
         }

         if(this.l < 0.0F) {
            this.l = 0.0F;
         }

         if(this.l > 1.0F) {
            this.l = 1.0F;
         }

      }
   }

   private void y() {
      // CanaryMod: Weather hook
      if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.WEATHER_CHANGE, world, !this.x.l())) {
         this.x.c(0);
         this.x.b(false);
      }
      // CanaryMod: Thunder hook
      if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.THUNDER_CHANGE, world, !this.x.j())) {
         this.x.b(0);
         this.x.a(false);
      }
   }

   protected void j() {
      this.P.clear();

      int var3;
      int var4;
      int var6;
      int var7;
      for(int var1 = 0; var1 < this.d.size(); ++var1) {
         OEntityPlayer var2 = (OEntityPlayer)this.d.get(var1);
         var3 = OMathHelper.b(var2.aP / 16.0D);
         var4 = OMathHelper.b(var2.aR / 16.0D);
         byte var5 = 9;

         for(var6 = -var5; var6 <= var5; ++var6) {
            for(var7 = -var5; var7 <= var5; ++var7) {
               this.P.add(new OChunkCoordIntPair(var6 + var3, var7 + var4));
            }
         }
      }

      if(this.Q > 0) {
         --this.Q;
      }

      Iterator var12 = this.P.iterator();

      while(var12.hasNext()) {
         OChunkCoordIntPair var13 = (OChunkCoordIntPair)var12.next();
         var3 = var13.a * 16;
         var4 = var13.b * 16;
         OChunk var14 = this.c(var13.a, var13.b);
         int var8;
         int var9;
         int var10;
         if(this.Q == 0) {
            this.g = this.g * 3 + 1013904223;
            var6 = this.g >> 2;
            var7 = var6 & 15;
            var8 = var6 >> 8 & 15;
            var9 = var6 >> 16 & 127;
            var10 = var14.a(var7, var9, var8);
            var7 += var3;
            var8 += var4;
            if(var10 == 0 && this.k(var7, var9, var8) <= this.r.nextInt(8) && this.a(OEnumSkyBlock.a, var7, var9, var8) <= 0) {
               OEntityPlayer var11 = this.a((double)var7 + 0.5D, (double)var9 + 0.5D, (double)var8 + 0.5D, 8.0D);
               if(var11 != null && var11.e((double)var7 + 0.5D, (double)var9 + 0.5D, (double)var8 + 0.5D) > 4.0D) {
                  this.a((double)var7 + 0.5D, (double)var9 + 0.5D, (double)var8 + 0.5D, "ambient.cave.cave", 0.7F, 0.8F + this.r.nextFloat() * 0.2F);
                  this.Q = this.r.nextInt(12000) + 6000;
               }
            }
         }

         if(this.r.nextInt(100000) == 0 && this.v() && this.u()) {
            this.g = this.g * 3 + 1013904223;
            var6 = this.g >> 2;
            var7 = var3 + (var6 & 15);
            var8 = var4 + (var6 >> 8 & 15);
            var9 = this.e(var7, var8);
            if(this.s(var7, var9, var8)) {
               this.a((OEntity)(new OEntityLightningBolt(this, (double)var7, (double)var9, (double)var8)));
               this.m = 2;
            }
         }

         int var15;
         if(this.r.nextInt(16) == 0) {
            this.g = this.g * 3 + 1013904223;
            var6 = this.g >> 2;
            var7 = var6 & 15;
            var8 = var6 >> 8 & 15;
            var9 = this.e(var7 + var3, var8 + var4);
            if(this.a().a(var7 + var3, var8 + var4).c() && var9 >= 0 && var9 < 128 && var14.a(OEnumSkyBlock.b, var7, var9, var8) < 10) {
               var10 = var14.a(var7, var9 - 1, var8);
               var15 = var14.a(var7, var9, var8);
               if(this.v() && var15 == 0 && OBlock.aT.a(this, var7 + var3, var9, var8 + var4) && var10 != 0 && var10 != OBlock.aU.bn && OBlock.m[var10].bA.c()) {
                  this.e(var7 + var3, var9, var8 + var4, OBlock.aT.bn);
               }

               if(var10 == OBlock.C.bn && var14.b(var7, var9 - 1, var8) == 0) {
                  this.e(var7 + var3, var9 - 1, var8 + var4, OBlock.aU.bn);
               }
            }
         }

         for(var6 = 0; var6 < 80; ++var6) {
            this.g = this.g * 3 + 1013904223;
            var7 = this.g >> 2;
            var8 = var7 & 15;
            var9 = var7 >> 8 & 15;
            var10 = var7 >> 16 & 127;
            var15 = var14.b[var8 << 11 | var9 << 7 | var10] & 255;
            if(OBlock.n[var15]) {
               OBlock.m[var15].a(this, var8 + var3, var10, var9 + var4, this.r);
            }
         }
      }

   }

   public boolean a(boolean var1) {
      int var2 = this.E.size();
      if(var2 != this.F.size()) {
         throw new IllegalStateException("TickNextTick list out of synch");
      } else {
         if(var2 > 1000) {
            var2 = 1000;
         }

         for(int var3 = 0; var3 < var2; ++var3) {
            ONextTickListEntry var4 = (ONextTickListEntry)this.E.first();
            if(!var1 && var4.e > this.x.f()) {
               break;
            }

            this.E.remove(var4);
            this.F.remove(var4);
            byte var5 = 8;
            if(this.a(var4.a - var5, var4.b - var5, var4.c - var5, var4.a + var5, var4.b + var5, var4.c + var5)) {
               int var6 = this.a(var4.a, var4.b, var4.c);
               if(var6 == var4.d && var6 > 0) {
                  OBlock.m[var6].a(this, var4.a, var4.b, var4.c, this.r);
               }
            }
         }

         return this.E.size() != 0;
      }
   }

   public List b(OEntity var1, OAxisAlignedBB var2) {
      this.R.clear();
      int var3 = OMathHelper.b((var2.a - 2.0D) / 16.0D);
      int var4 = OMathHelper.b((var2.d + 2.0D) / 16.0D);
      int var5 = OMathHelper.b((var2.c - 2.0D) / 16.0D);
      int var6 = OMathHelper.b((var2.f + 2.0D) / 16.0D);

      for(int var7 = var3; var7 <= var4; ++var7) {
         for(int var8 = var5; var8 <= var6; ++var8) {
            if(this.g(var7, var8)) {
               this.c(var7, var8).a(var1, var2, this.R);
            }
         }
      }

      return this.R;
   }

   public List a(Class var1, OAxisAlignedBB var2) {
      int var3 = OMathHelper.b((var2.a - 2.0D) / 16.0D);
      int var4 = OMathHelper.b((var2.d + 2.0D) / 16.0D);
      int var5 = OMathHelper.b((var2.c - 2.0D) / 16.0D);
      int var6 = OMathHelper.b((var2.f + 2.0D) / 16.0D);
      ArrayList var7 = new ArrayList();

      for(int var8 = var3; var8 <= var4; ++var8) {
         for(int var9 = var5; var9 <= var6; ++var9) {
            if(this.g(var8, var9)) {
               this.c(var8, var9).a(var1, var2, var7);
            }
         }
      }

      return var7;
   }

   public void b(int var1, int var2, int var3, OTileEntity var4) {
      if(this.g(var1, var2, var3)) {
         this.b(var1, var3).f();
      }

      for(int var5 = 0; var5 < this.u.size(); ++var5) {
         ((OIWorldAccess)this.u.get(var5)).a(var1, var2, var3, var4);
      }

   }

   public int a(Class var1) {
      int var2 = 0;

      for(int var3 = 0; var3 < this.b.size(); ++var3) {
         OEntity var4 = (OEntity)this.b.get(var3);
         if(var1.isAssignableFrom(var4.getClass())) {
            ++var2;
         }
      }

      return var2;
   }

   public void a(List var1) {
      this.b.addAll(var1);

      for(int var2 = 0; var2 < var1.size(); ++var2) {
         this.c((OEntity)var1.get(var2));
      }

   }

   public void b(List var1) {
      this.D.addAll(var1);
   }

   public boolean a(int var1, int var2, int var3, int var4, boolean var5, int var6) {
      int var7 = this.a(var2, var3, var4);
      OBlock var8 = OBlock.m[var7];
      OBlock var9 = OBlock.m[var1];
      OAxisAlignedBB var10 = var9.e(this, var2, var3, var4);
      if(var5) {
         var10 = null;
      }

      if(var10 != null && !this.a(var10)) {
         return false;
      } else {
         if(var8 == OBlock.B || var8 == OBlock.C || var8 == OBlock.D || var8 == OBlock.E || var8 == OBlock.as || var8 == OBlock.aT) {
            var8 = null;
         }

         return var1 > 0 && var8 == null && var9.a(this, var2, var3, var4, var6);
      }
   }

   public OPathEntity a(OEntity var1, OEntity var2, float var3) {
      int var4 = OMathHelper.b(var1.aP);
      int var5 = OMathHelper.b(var1.aQ);
      int var6 = OMathHelper.b(var1.aR);
      int var7 = (int)(var3 + 16.0F);
      int var8 = var4 - var7;
      int var9 = var5 - var7;
      int var10 = var6 - var7;
      int var11 = var4 + var7;
      int var12 = var5 + var7;
      int var13 = var6 + var7;
      OChunkCache var14 = new OChunkCache(this, var8, var9, var10, var11, var12, var13);
      return (new OPathfinder(var14)).a(var1, var2, var3);
   }

   public OPathEntity a(OEntity var1, int var2, int var3, int var4, float var5) {
      int var6 = OMathHelper.b(var1.aP);
      int var7 = OMathHelper.b(var1.aQ);
      int var8 = OMathHelper.b(var1.aR);
      int var9 = (int)(var5 + 8.0F);
      int var10 = var6 - var9;
      int var11 = var7 - var9;
      int var12 = var8 - var9;
      int var13 = var6 + var9;
      int var14 = var7 + var9;
      int var15 = var8 + var9;
      OChunkCache var16 = new OChunkCache(this, var10, var11, var12, var13, var14, var15);
      return (new OPathfinder(var16)).a(var1, var2, var3, var4, var5);
   }

   public boolean i(int var1, int var2, int var3, int var4) {
      int var5 = this.a(var1, var2, var3);
      return var5 == 0?false:OBlock.m[var5].d(this, var1, var2, var3, var4);
   }

   public boolean q(int var1, int var2, int var3) {
      return this.i(var1, var2 - 1, var3, 0)?true:(this.i(var1, var2 + 1, var3, 1)?true:(this.i(var1, var2, var3 - 1, 2)?true:(this.i(var1, var2, var3 + 1, 3)?true:(this.i(var1 - 1, var2, var3, 4)?true:this.i(var1 + 1, var2, var3, 5)))));
   }

   public boolean j(int var1, int var2, int var3, int var4) {
      if(this.e(var1, var2, var3)) {
         return this.q(var1, var2, var3);
      } else {
         int var5 = this.a(var1, var2, var3);
         return var5 == 0?false:OBlock.m[var5].a((OIBlockAccess)this, var1, var2, var3, var4);
      }
   }

   public boolean r(int var1, int var2, int var3) {
      return this.j(var1, var2 - 1, var3, 0)?true:(this.j(var1, var2 + 1, var3, 1)?true:(this.j(var1, var2, var3 - 1, 2)?true:(this.j(var1, var2, var3 + 1, 3)?true:(this.j(var1 - 1, var2, var3, 4)?true:this.j(var1 + 1, var2, var3, 5)))));
   }

   public OEntityPlayer a(OEntity var1, double var2) {
      return this.a(var1.aP, var1.aQ, var1.aR, var2);
   }

   public OEntityPlayer a(double var1, double var3, double var5, double var7) {
      double var9 = -1.0D;
      OEntityPlayer var11 = null;

      for(int var12 = 0; var12 < this.d.size(); ++var12) {
         OEntityPlayer var13 = (OEntityPlayer)this.d.get(var12);
         double var14 = var13.e(var1, var3, var5);
         if((var7 < 0.0D || var14 < var7 * var7) && (var9 == -1.0D || var14 < var9)) {
            var9 = var14;
            var11 = var13;
         }
      }

      return var11;
   }

   public OEntityPlayer a(String var1) {
      for(int var2 = 0; var2 < this.d.size(); ++var2) {
         if(var1.equals(((OEntityPlayer)this.d.get(var2)).r)) {
            return (OEntityPlayer)this.d.get(var2);
         }
      }

      return null;
   }

   public byte[] c(int var1, int var2, int var3, int var4, int var5, int var6) {
      byte[] var7 = new byte[var4 * var5 * var6 * 5 / 2];
      int var8 = var1 >> 4;
      int var9 = var3 >> 4;
      int var10 = var1 + var4 - 1 >> 4;
      int var11 = var3 + var6 - 1 >> 4;
      int var12 = 0;
      int var13 = var2;
      int var14 = var2 + var5;
      if(var2 < 0) {
         var13 = 0;
      }

      if(var14 > 128) {
         var14 = 128;
      }

      for(int var15 = var8; var15 <= var10; ++var15) {
         int var16 = var1 - var15 * 16;
         int var17 = var1 + var4 - var15 * 16;
         if(var16 < 0) {
            var16 = 0;
         }

         if(var17 > 16) {
            var17 = 16;
         }

         for(int var18 = var9; var18 <= var11; ++var18) {
            int var19 = var3 - var18 * 16;
            int var20 = var3 + var6 - var18 * 16;
            if(var19 < 0) {
               var19 = 0;
            }

            if(var20 > 16) {
               var20 = 16;
            }

            var12 = this.c(var15, var18).a(var7, var16, var13, var19, var17, var14, var20, var12);
         }
      }

      return var7;
   }

   public void k() {
      this.w.b();
   }

   public void a(long var1) {
      this.x.a(var1);
   }

   public void b(long var1) {
      long var3 = var1 - this.x.f();

      ONextTickListEntry var6;
      for(Iterator var5 = this.F.iterator(); var5.hasNext(); var6.e += var3) {
         var6 = (ONextTickListEntry)var5.next();
      }

      this.a(var1);
   }

   public long l() {
      return this.x.b();
   }

   public long m() {
      return this.x.f();
   }

   public OChunkCoordinates n() {
      return new OChunkCoordinates(this.x.c(), this.x.d(), this.x.e());
   }

   public boolean a(OEntityPlayer var1, int var2, int var3, int var4) {
      return true;
   }

   public void a(OEntity var1, byte var2) {}

   public OIChunkProvider o() {
      return this.v;
   }

   public void d(int var1, int var2, int var3, int var4, int var5) {
      int var6 = this.a(var1, var2, var3);
      if(var6 > 0) {
         OBlock.m[var6].a(this, var1, var2, var3, var4, var5);
      }

   }

   public OISaveHandler p() {
      return this.w;
   }

   public OWorldInfo q() {
      return this.x;
   }

   public void r() {
      this.J = !this.d.isEmpty();
      Iterator var1 = this.d.iterator();

      while(var1.hasNext()) {
         OEntityPlayer var2 = (OEntityPlayer)var1.next();
         if(!var2.L()) {
            this.J = false;
            break;
         }
      }

   }

   protected void s() {
      this.J = false;
      Iterator var1 = this.d.iterator();

      while(var1.hasNext()) {
         OEntityPlayer var2 = (OEntityPlayer)var1.next();
         if(var2.L()) {
            var2.a(false, false, true);
         }
      }

      this.y();
   }

   public boolean t() {
      if(this.J && !this.B) {
         Iterator var1 = this.d.iterator();

         OEntityPlayer var2;
         do {
            if(!var1.hasNext()) {
               return true;
            }

            var2 = (OEntityPlayer)var1.next();
         } while(var2.M());

         return false;
      } else {
         return false;
      }
   }

   public float c(float var1) {
      return (this.k + (this.l - this.k) * var1) * this.d(var1);
   }

   public float d(float var1) {
      return this.i + (this.j - this.i) * var1;
   }

   public boolean u() {
      return (double)this.c(1.0F) > 0.9D;
   }

   public boolean v() {
      return (double)this.d(1.0F) > 0.2D;
   }

   public boolean s(int var1, int var2, int var3) {
      if(!this.v()) {
         return false;
      } else if(!this.j(var1, var2, var3)) {
         return false;
      } else if(this.e(var1, var3) > var2) {
         return false;
      } else {
         OBiomeGenBase var4 = this.a().a(var1, var3);
         return var4.c()?false:var4.d();
      }
   }

   public void a(String var1, OMapDataBase var2) {
      this.z.a(var1, var2);
   }

   public OMapDataBase a(Class var1, String var2) {
      return this.z.a(var1, var2);
   }

   public int b(String var1) {
      return this.z.a(var1);
   }

   public void e(int var1, int var2, int var3, int var4, int var5) {
      this.a((OEntityPlayer)null, var1, var2, var3, var4, var5);
   }

   public void a(OEntityPlayer var1, int var2, int var3, int var4, int var5, int var6) {
      for(int var7 = 0; var7 < this.u.size(); ++var7) {
         ((OIWorldAccess)this.u.get(var7)).a(var1, var2, var3, var4, var5, var6);
      }

   }

}
