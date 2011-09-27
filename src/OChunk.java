import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class OChunk {

   public static boolean a;
   public byte[] b;
   public int[] c;
   public boolean[] d;
   public boolean e;
   public OWorld f;
   public ONibbleArray g;
   public ONibbleArray h;
   public ONibbleArray i;
   public byte[] j;
   public int k;
   public final int l;
   public final int m;
   public Map n;
   public List[] o;
   public boolean p;
   public boolean q;
   public boolean r;
   public boolean s;
   public long t;
   boolean u;

   // CanaryMod
   public final Chunk chunk = new Chunk(this);

   public OChunk(OWorld var1, int var2, int var3) {
      super();
      this.c = new int[256];
      this.d = new boolean[256];
      this.n = new HashMap();
      this.p = false;
      this.q = false;
      this.s = false;
      this.t = 0L;
      this.u = false;
      var1.getClass();
      this.o = new List[128 / 16];
      this.f = var1;
      this.l = var2;
      this.m = var3;
      this.j = new byte[256];

      for(int var4 = 0; var4 < this.o.length; ++var4) {
         this.o[var4] = new ArrayList();
      }

      Arrays.fill(this.c, -999);
   }

   public OChunk(OWorld var1, byte[] var2, int var3, int var4) {
      this(var1, var3, var4);
      this.b = var2;
      int var10003 = var2.length;
      ONibbleArray var10001 = new ONibbleArray(var10003, 7);
      var1.getClass();
      this.g = var10001;
      var10001 = new ONibbleArray(var10003, 7);
      var10003 = var2.length;
      var1.getClass();
      this.h = var10001;
      var10001 = new ONibbleArray(var10003, 7);
      var10003 = var2.length;
      var1.getClass();
      this.i = var10001;
   }

   public boolean a(int var1, int var2) {
      return var1 == this.l && var2 == this.m;
   }

   public int b(int var1, int var2) {
      return this.j[var2 << 4 | var1] & 255;
   }

   public void a() {}

   public void b() {
      this.f.getClass();
      int var1 = 128 - 1;

      int var2;
      int var3;
      for(var2 = 0; var2 < 16; ++var2) {
         for(var3 = 0; var3 < 16; ++var3) {
            this.f.getClass();
            int var4 = 128 - 1;
            this.f.getClass();
            int var10000 = var2 << 11;
            this.f.getClass();

            int var5;
            for(var5 = var10000 | var3 << 7; var4 > 0 && OBlock.q[this.b[var5 + var4 - 1] & 255] == 0; --var4) {
               ;
            }

            this.j[var3 << 4 | var2] = (byte)var4;
            if(var4 < var1) {
               var1 = var4;
            }

            if(!this.f.y.e) {
               int var6 = 15;
               this.f.getClass();
               int var7 = 128 - 1;

               do {
                  var6 -= OBlock.q[this.b[var5 + var7] & 255];
                  if(var6 > 0) {
                     this.h.a(var2, var7, var3, var6);
                  }

                  --var7;
               } while(var7 > 0 && var6 > 0);
            }
         }
      }

      this.k = var1;

      for(var2 = 0; var2 < 16; ++var2) {
         for(var3 = 0; var3 < 16; ++var3) {
            this.d(var2, var3);
         }
      }

      this.q = true;
   }

   public void c() {}

   private void d(int var1, int var2) {
      this.d[var1 + var2 * 16] = true;
   }

   private void i() {
      OWorld var10000 = this.f;
      int var10001 = this.l * 16 + 8;
      this.f.getClass();
      if(var10000.a(var10001, 128 / 2, this.m * 16 + 8, 16)) {
         for(int var1 = 0; var1 < 16; ++var1) {
            for(int var2 = 0; var2 < 16; ++var2) {
               if(this.d[var1 + var2 * 16]) {
                  this.d[var1 + var2 * 16] = false;
                  int var3 = this.b(var1, var2);
                  int var4 = this.l * 16 + var1;
                  int var5 = this.m * 16 + var2;
                  int var6 = this.f.d(var4 - 1, var5);
                  int var7 = this.f.d(var4 + 1, var5);
                  int var8 = this.f.d(var4, var5 - 1);
                  int var9 = this.f.d(var4, var5 + 1);
                  if(var7 < var6) {
                     var6 = var7;
                  }

                  if(var8 < var6) {
                     var6 = var8;
                  }

                  if(var9 < var6) {
                     var6 = var9;
                  }

                  this.u = true;
                  this.f(var4, var5, var6);
                  this.u = true;
                  this.f(var4 - 1, var5, var3);
                  this.f(var4 + 1, var5, var3);
                  this.f(var4, var5 - 1, var3);
                  this.f(var4, var5 + 1, var3);
               }
            }
         }
      }

   }

   private void f(int var1, int var2, int var3) {
      int var4 = this.f.d(var1, var2);
      if(var4 > var3) {
         this.d(var1, var2, var3, var4 + 1);
      } else if(var4 < var3) {
         this.d(var1, var2, var4, var3 + 1);
      }

   }

   private void d(int var1, int var2, int var3, int var4) {
      if(var4 > var3) {
         OWorld var10000 = this.f;
         this.f.getClass();
         if(var10000.a(var1, 128 / 2, var2, 16)) {
            for(int var5 = var3; var5 < var4; ++var5) {
               this.f.b(OEnumSkyBlock.a, var1, var5, var2);
            }

            this.q = true;
         }
      }

   }

   private void g(int var1, int var2, int var3) {
      int var4 = this.j[var3 << 4 | var1] & 255;
      int var5 = var4;
      if(var2 > var4) {
         var5 = var2;
      }

      this.f.getClass();
      int var10000 = var1 << 11;
      this.f.getClass();

      for(int var6 = var10000 | var3 << 7; var5 > 0 && OBlock.q[this.b[var6 + var5 - 1] & 255] == 0; --var5) {
         ;
      }

      if(var5 != var4) {
         this.f.g(var1, var3, var5, var4);
         this.j[var3 << 4 | var1] = (byte)var5;
         int var7;
         int var8;
         int var9;
         if(var5 < this.k) {
            this.k = var5;
         } else {
            this.f.getClass();
            var7 = 128 - 1;

            for(var8 = 0; var8 < 16; ++var8) {
               for(var9 = 0; var9 < 16; ++var9) {
                  if((this.j[var9 << 4 | var8] & 255) < var7) {
                     var7 = this.j[var9 << 4 | var8] & 255;
                  }
               }
            }

            this.k = var7;
         }

         var7 = this.l * 16 + var1;
         var8 = this.m * 16 + var3;
         if(var5 < var4) {
            for(var9 = var5; var9 < var4; ++var9) {
               this.h.a(var1, var9, var3, 15);
            }
         } else {
            for(var9 = var4; var9 < var5; ++var9) {
               this.h.a(var1, var9, var3, 0);
            }
         }

         for(var9 = 15; var5 > 0 && var9 > 0; this.h.a(var1, var5, var3, var9)) {
            --var5;
            int var11 = OBlock.q[this.a(var1, var5, var3)];
            if(var11 == 0) {
               var11 = 1;
            }

            var9 -= var11;
            if(var9 < 0) {
               var9 = 0;
            }
         }

         byte var15 = this.j[var3 << 4 | var1];
         int var12 = var4;
         int var13 = var15;
         if(var15 < var4) {
            var12 = var15;
            var13 = var4;
         }

         this.d(var7 - 1, var8, var12, var13);
         this.d(var7 + 1, var8, var12, var13);
         this.d(var7, var8 - 1, var12, var13);
         this.d(var7, var8 + 1, var12, var13);
         this.d(var7, var8, var12, var13);
         this.q = true;
      }
   }

   public int a(int var1, int var2, int var3) {
      byte[] var10000 = this.b;
      this.f.getClass();
      int var10001 = var1 << 11;
      this.f.getClass();
      return var10000[var10001 | var3 << 7 | var2] & 255;
   }
   
   public boolean a(int var1, int var2, int var3, int var4, int var5) {
      return a(var1, var2, var3, var4, var5, true);
   }
   
   public boolean a(int var1, int var2, int var3, int var4, int var5, boolean checkPortal) {
      byte var6 = (byte)var4;
      int var7 = var3 << 4 | var1;
      if(var2 >= this.c[var7] - 1) {
         this.c[var7] = -999;
      }

      int var8 = this.j[var3 << 4 | var1] & 255;
      byte[] var10000 = this.b;
      this.f.getClass();
      int var10001 = var1 << 11;
      this.f.getClass();
      int var9 = var10000[var10001 | var3 << 7 | var2] & 255;
      if(var9 == var4 && this.g.a(var1, var2, var3) == var5) {
         return false;
      } else {
         int var10 = this.l * 16 + var1;
         int var11 = this.m * 16 + var3;
         if(checkPortal == true) {
            int portalPointX = var10;
            int portalPointY = var2;
            int portalPointZ = var11;
            // CanaryMod check if removed block is portal block
            int portalId = Block.Type.Portal.getType();
            if(chunk.getWorld().getBlockIdAt(portalPointX, portalPointY, portalPointZ) == portalId) {
               // These will be equal 1 if the portal is defined on their
               // axis
               // and 0 if not.
               int portalXOffset = (chunk.getWorld().getBlockIdAt(portalPointX - 1, portalPointY, portalPointZ) == portalId || chunk.getWorld().getBlockIdAt(portalPointX + 1, portalPointY, portalPointZ) == portalId)?1:0;
               int portalZOffset = (chunk.getWorld().getBlockIdAt(portalPointX, portalPointY, portalPointZ - 1) == portalId || chunk.getWorld().getBlockIdAt(portalPointX, portalPointY, portalPointZ + 1) == portalId)?1:0;
               // If the portal is either x aligned or z aligned but not
               // both
               // (has neighbor portal in x or z plane but not both)
               if(portalXOffset != portalZOffset) {
                  // Get the edge of the portal.
                  int portalX = portalPointX - ((chunk.getWorld().getBlockIdAt(portalPointX - 1, portalPointY, portalPointZ) == portalId)?1:0);
                  int portalZ = portalPointZ - ((chunk.getWorld().getBlockIdAt(portalPointX, portalPointY, portalPointZ - 1) == portalId)?1:0);
                  int portalY = var2;
                  while(chunk.getWorld().getBlockIdAt(portalX, ++portalY, portalZ) == portalId)
                            ;
                  portalY -= 1;
                  // Scan the portal and see if its still all there (2x3
                  // formation)
                  boolean completePortal = true;
                  Block[][] portalBlocks = new Block[3][2];
                  for(int i = 0; i < 3 && completePortal == true; i += 1) {
                     for(int j = 0; j < 2 && completePortal == true; j += 1) {
                        portalBlocks[i][j] = chunk.getWorld().getBlockAt(portalX + j * portalXOffset, portalY - i, portalZ + j * portalZOffset);
                        if(portalBlocks[i][j].getType() != portalId) {
                           completePortal = false;
                        }
                     }
                  }
                  if(completePortal == true) {
                     // CanaryMod hook onPortalDestroy
                     if((Boolean)etc.getLoader().callHook(PluginLoader.Hook.PORTAL_DESTROY, (Object)portalBlocks)) {
                        // Hook returned true = don't destroy the
                        // portal.
                        // in that case we need to reconstruct the
                        // portal's
                        // frame to make the portal valid.
                        // Problem is we don't want to reconstruct it
                        // right
                        // away because more blocks might be deleted
                        // (for
                        // example on explosion).
                        // In order to avoid spamming the hook for each
                        // destroyed block, I'm queuing the
                        // reconstruction
                        // of the portal instead.
                        etc.getServer().addToServerQueue(new PortalReconstructJob(chunk.getWorld(), portalX, portalY, portalZ, (portalXOffset == 1)));
                     }
                  }
               }
            }
         }
         var10000 = this.b;
         this.f.getClass();
         var10001 = var1 << 11;
         this.f.getClass();
         var10000[var10001 | var3 << 7 | var2] = (byte)(var6 & 255);
         if(var9 != 0 && !this.f.I) {
            OBlock.m[var9].d(this.f, var10, var2, var11);
         }

         this.g.a(var1, var2, var3, var5);
         if(!this.f.y.e) {
            if(OBlock.q[var6 & 255] != 0) {
               if(var2 >= var8) {
                  this.g(var1, var2 + 1, var3);
               }
            } else if(var2 == var8 - 1) {
               this.g(var1, var2, var3);
            }

            this.f.a(OEnumSkyBlock.a, var10, var2, var11, var10, var2, var11);
         }

         this.f.a(OEnumSkyBlock.b, var10, var2, var11, var10, var2, var11);
         this.d(var1, var3);
         this.g.a(var1, var2, var3, var5);
         OTileEntity var12;
         if(var4 != 0) {
            if(!this.f.I) {
               OBlock.m[var4].a(this.f, var10, var2, var11);
            }

            if(OBlock.m[var4] instanceof OBlockContainer) {
               var12 = this.d(var1, var2, var3);
               if(var12 == null) {
                  var12 = ((OBlockContainer)OBlock.m[var4]).a_();
                  this.f.a(var1, var2, var3, var12);
               }

               if(var12 != null) {
                  var12.g();
               }
            }
         } else if(var9 > 0 && OBlock.m[var9] instanceof OBlockContainer) {
            var12 = this.d(var1, var2, var3);
            if(var12 != null) {
               var12.g();
            }
         }

         this.q = true;
         return true;
      }
   }
   
   public boolean a(int var1, int var2, int var3, int var4) {
      return a(var1, var2, var3, var4, true);
   }
   
   public boolean a(int var1, int var2, int var3, int var4, boolean checkPortal) {
      byte var5 = (byte)var4;
      int var6 = var3 << 4 | var1;
      if(var2 >= this.c[var6] - 1) {
         this.c[var6] = -999;
      }

      int var7 = this.j[var6] & 255;
      byte[] var10000 = this.b;
      this.f.getClass();
      int var10001 = var1 << 11;
      this.f.getClass();
      int var8 = var10000[var10001 | var3 << 7 | var2] & 255;
      if(var8 == var4) {
         return false;
      } else {
         int var9 = this.l * 16 + var1;
         int var10 = this.m * 16 + var3;
         if(checkPortal == true) {
            int portalPointX = var9;
            int portalPointY = var2;
            int portalPointZ = var10;
            // CanaryMod check if removed block is portal block
            int portalId = Block.Type.Portal.getType();
            if(chunk.getWorld().getBlockIdAt(portalPointX, portalPointY, portalPointZ) == portalId) {
               // These will be equal 1 if the portal is defined on their
               // axis
               // and 0 if not.
               int portalXOffset = (chunk.getWorld().getBlockIdAt(portalPointX - 1, portalPointY, portalPointZ) == portalId || chunk.getWorld().getBlockIdAt(portalPointX + 1, portalPointY, portalPointZ) == portalId)?1:0;
               int portalZOffset = (chunk.getWorld().getBlockIdAt(portalPointX, portalPointY, portalPointZ - 1) == portalId || chunk.getWorld().getBlockIdAt(portalPointX, portalPointY, portalPointZ + 1) == portalId)?1:0;
               // If the portal is either x aligned or z aligned but not
               // both
               // (has neighbor portal in x or z plane but not both)
               if(portalXOffset != portalZOffset) {
                  // Get the edge of the portal.
                  int portalX = portalPointX - ((chunk.getWorld().getBlockIdAt(portalPointX - 1, portalPointY, portalPointZ) == portalId)?1:0);
                  int portalZ = portalPointZ - ((chunk.getWorld().getBlockIdAt(portalPointX, portalPointY, portalPointZ - 1) == portalId)?1:0);
                  int portalY = var2;
                  while(chunk.getWorld().getBlockIdAt(portalX, ++portalY, portalZ) == portalId)
                            ;
                  portalY -= 1;
                  // Scan the portal and see if its still all there (2x3
                  // formation)
                  boolean completePortal = true;
                  Block[][] portalBlocks = new Block[3][2];
                  for(int i = 0; i < 3 && completePortal == true; i += 1) {
                     for(int j = 0; j < 2 && completePortal == true; j += 1) {
                        portalBlocks[i][j] = chunk.getWorld().getBlockAt(portalX + j * portalXOffset, portalY - i, portalZ + j * portalZOffset);
                        if(portalBlocks[i][j].getType() != portalId) {
                           completePortal = false;
                        }
                     }
                  }
                  if(completePortal == true) {
                     // CanaryMod hook onPortalDestroy
                     if((Boolean)etc.getLoader().callHook(PluginLoader.Hook.PORTAL_DESTROY, (Object)portalBlocks)) {
                        // Hook returned true = don't destroy the
                        // portal.
                        // in that case we need to reconstruct the
                        // portal's
                        // frame to make the portal valid.
                        // Problem is we don't want to reconstruct it
                        // right
                        // away because more blocks might be deleted
                        // (for
                        // example on explosion).
                        // In order to avoid spamming the hook for each
                        // destroyed block, I'm queuing the
                        // reconstruction
                        // of the portal instead.
                        etc.getServer().addToServerQueue(new PortalReconstructJob(chunk.getWorld(), portalX, portalY, portalZ, (portalXOffset == 1)));
                     }
                  }
               }
            }
         }
         var10000 = this.b;
         this.f.getClass();
         var10001 = var1 << 11;
         this.f.getClass();
         var10000[var10001 | var3 << 7 | var2] = (byte)(var5 & 255);
         if(var8 != 0) {
            OBlock.m[var8].d(this.f, var9, var2, var10);
         }

         this.g.a(var1, var2, var3, 0);
         if(OBlock.q[var5 & 255] != 0) {
            if(var2 >= var7) {
               this.g(var1, var2 + 1, var3);
            }
         } else if(var2 == var7 - 1) {
            this.g(var1, var2, var3);
         }

         this.f.a(OEnumSkyBlock.a, var9, var2, var10, var9, var2, var10);
         this.f.a(OEnumSkyBlock.b, var9, var2, var10, var9, var2, var10);
         this.d(var1, var3);
         OTileEntity var11;
         if(var4 != 0) {
            if(!this.f.I) {
               OBlock.m[var4].a(this.f, var9, var2, var10);
            }

            if(var4 > 0 && OBlock.m[var4] instanceof OBlockContainer) {
               var11 = this.d(var1, var2, var3);
               if(var11 == null) {
                  var11 = ((OBlockContainer)OBlock.m[var4]).a_();
                  this.f.a(var1, var2, var3, var11);
               }

               if(var11 != null) {
                  var11.g();
               }
            }
         } else if(var8 > 0 && OBlock.m[var8] instanceof OBlockContainer) {
            var11 = this.d(var1, var2, var3);
            if(var11 != null) {
               var11.g();
            }
         }

         this.q = true;
         return true;
      }
   }

   public int b(int var1, int var2, int var3) {
      return this.g.a(var1, var2, var3);
   }

   public void b(int var1, int var2, int var3, int var4) {
      this.q = true;
      this.g.a(var1, var2, var3, var4);
      int var5 = this.a(var1, var2, var3);
      if(var5 > 0 && OBlock.m[var5] instanceof OBlockContainer) {
         OTileEntity var6 = this.d(var1, var2, var3);
         if(var6 != null) {
            var6.g();
            var6.n = var4;
         }
      }

   }

   public int a(OEnumSkyBlock var1, int var2, int var3, int var4) {
      return var1 == OEnumSkyBlock.a?this.h.a(var2, var3, var4):(var1 == OEnumSkyBlock.b?this.i.a(var2, var3, var4):0);
   }

   public void a(OEnumSkyBlock var1, int var2, int var3, int var4, int var5) {
      this.q = true;
      if(var1 == OEnumSkyBlock.a) {
         this.h.a(var2, var3, var4, var5);
      } else {
         if(var1 != OEnumSkyBlock.b) {
            return;
         }

         this.i.a(var2, var3, var4, var5);
      }

   }

   public int c(int var1, int var2, int var3, int var4) {
      int var5 = this.h.a(var1, var2, var3);
      if(var5 > 0) {
         a = true;
      }

      var5 -= var4;
      int var6 = this.i.a(var1, var2, var3);
      if(var6 > var5) {
         var5 = var6;
      }

      return var5;
   }

   public void a(OEntity var1) {
      this.s = true;
      int var2 = OMathHelper.b(var1.bf / 16.0D);
      int var3 = OMathHelper.b(var1.bh / 16.0D);
      if(var2 != this.l || var3 != this.m) {
         System.out.println("Wrong location! " + var1);
         Thread.dumpStack();
      }

      int var4 = OMathHelper.b(var1.bg / 16.0D);
      if(var4 < 0) {
         var4 = 0;
      }

      if(var4 >= this.o.length) {
         var4 = this.o.length - 1;
      }

      var1.bV = true;
      var1.bW = this.l;
      var1.bX = var4;
      var1.bY = this.m;
      this.o[var4].add(var1);
   }

   public void b(OEntity var1) {
      this.a(var1, var1.bX);
   }

   public void a(OEntity var1, int var2) {
      if(var2 < 0) {
         var2 = 0;
      }

      if(var2 >= this.o.length) {
         var2 = this.o.length - 1;
      }

      this.o[var2].remove(var1);
   }

   public boolean c(int var1, int var2, int var3) {
      return var2 >= (this.j[var3 << 4 | var1] & 255);
   }

   public OTileEntity d(int var1, int var2, int var3) {
      OChunkPosition var4 = new OChunkPosition(var1, var2, var3);
      OTileEntity var5 = (OTileEntity)this.n.get(var4);
      if(var5 == null) {
         int var6 = this.a(var1, var2, var3);
         if(!OBlock.p[var6]) {
            return null;
         }

         if(var5 == null) {
            var5 = ((OBlockContainer)OBlock.m[var6]).a_();
            this.f.a(this.l * 16 + var1, var2, this.m * 16 + var3, var5);
         }

         var5 = (OTileEntity)this.n.get(var4);
      }

      if(var5 != null && var5.m()) {
         this.n.remove(var4);
         return null;
      } else {
         return var5;
      }
   }

   public void a(OTileEntity var1) {
      int var2 = var1.j - this.l * 16;
      int var3 = var1.k;
      int var4 = var1.l - this.m * 16;
      this.a(var2, var3, var4, var1);
      if(this.e) {
         this.f.h.add(var1);
      }

   }

   public void a(int var1, int var2, int var3, OTileEntity var4) {
      OChunkPosition var5 = new OChunkPosition(var1, var2, var3);
      var4.i = this.f;
      var4.j = this.l * 16 + var1;
      var4.k = var2;
      var4.l = this.m * 16 + var3;
      if(this.a(var1, var2, var3) != 0 && OBlock.m[this.a(var1, var2, var3)] instanceof OBlockContainer) {
         var4.n();
         this.n.put(var5, var4);
      } else {
         System.out.println("Attempted to place a tile entity where there was no entity tile!");
      }
   }

   public void e(int var1, int var2, int var3) {
      OChunkPosition var4 = new OChunkPosition(var1, var2, var3);
      if(this.e) {
         OTileEntity var5 = (OTileEntity)this.n.remove(var4);
         if(var5 != null) {
            var5.i();
         }
      }

   }

   public void d() {
      this.e = true;
      this.f.a(this.n.values());

      for(int var1 = 0; var1 < this.o.length; ++var1) {
         this.f.a(this.o[var1]);
      }

   }

   public void e() {
      this.e = false;
      Iterator var1 = this.n.values().iterator();

      while(var1.hasNext()) {
         OTileEntity var2 = (OTileEntity)var1.next();
         this.f.a(var2);
      }

      for(int var3 = 0; var3 < this.o.length; ++var3) {
         this.f.b(this.o[var3]);
      }

   }

   public void f() {
      this.q = true;
   }

   public void a(OEntity var1, OAxisAlignedBB var2, List var3) {
      int var4 = OMathHelper.b((var2.b - 2.0D) / 16.0D);
      int var5 = OMathHelper.b((var2.e + 2.0D) / 16.0D);
      if(var4 < 0) {
         var4 = 0;
      }

      if(var5 >= this.o.length) {
         var5 = this.o.length - 1;
      }

      for(int var6 = var4; var6 <= var5; ++var6) {
         List var7 = this.o[var6];

         for(int var8 = 0; var8 < var7.size(); ++var8) {
            OEntity var9 = (OEntity)var7.get(var8);
            if(var9 != var1 && var9.bp.a(var2)) {
               var3.add(var9);
            }
         }
      }

   }

   public void a(Class var1, OAxisAlignedBB var2, List var3) {
      int var4 = OMathHelper.b((var2.b - 2.0D) / 16.0D);
      int var5 = OMathHelper.b((var2.e + 2.0D) / 16.0D);
      if(var4 < 0) {
         var4 = 0;
      }

      if(var5 >= this.o.length) {
         var5 = this.o.length - 1;
      }

      for(int var6 = var4; var6 <= var5; ++var6) {
         List var7 = this.o[var6];

         for(int var8 = 0; var8 < var7.size(); ++var8) {
            OEntity var9 = (OEntity)var7.get(var8);
            if(var1.isAssignableFrom(var9.getClass()) && var9.bp.a(var2)) {
               var3.add(var9);
            }
         }
      }

   }

   public boolean a(boolean var1) {
      if(this.r) {
         return false;
      } else {
         if(var1) {
            if(this.s && this.f.l() != this.t) {
               return true;
            }
         } else if(this.s && this.f.l() >= this.t + 600L) {
            return true;
         }

         return this.q;
      }
   }

   public int a(byte[] var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      int var9 = var5 - var2;
      int var10 = var6 - var3;
      int var11 = var7 - var4;
      if(var9 * var10 * var11 == this.b.length) {
         System.arraycopy(this.b, 0, var1, var8, this.b.length);
         var8 += this.b.length;
         System.arraycopy(this.g.a, 0, var1, var8, this.g.a.length);
         var8 += this.g.a.length;
         System.arraycopy(this.i.a, 0, var1, var8, this.i.a.length);
         var8 += this.i.a.length;
         System.arraycopy(this.h.a, 0, var1, var8, this.h.a.length);
         var8 += this.h.a.length;
         return var8;
      } else {
         int var12;
         int var10000;
         int var13;
         int var14;
         int var15;
         for(var12 = var2; var12 < var5; ++var12) {
            for(var13 = var4; var13 < var7; ++var13) {
               this.f.getClass();
               var10000 = var12 << 11;
               this.f.getClass();
               var14 = var10000 | var13 << 7 | var3;
               var15 = var6 - var3;
               System.arraycopy(this.b, var14, var1, var8, var15);
               var8 += var15;
            }
         }

         for(var12 = var2; var12 < var5; ++var12) {
            for(var13 = var4; var13 < var7; ++var13) {
               this.f.getClass();
               var10000 = var12 << 11;
               this.f.getClass();
               var14 = (var10000 | var13 << 7 | var3) >> 1;
               var15 = (var6 - var3) / 2;
               System.arraycopy(this.g.a, var14, var1, var8, var15);
               var8 += var15;
            }
         }

         for(var12 = var2; var12 < var5; ++var12) {
            for(var13 = var4; var13 < var7; ++var13) {
               this.f.getClass();
               var10000 = var12 << 11;
               this.f.getClass();
               var14 = (var10000 | var13 << 7 | var3) >> 1;
               var15 = (var6 - var3) / 2;
               System.arraycopy(this.i.a, var14, var1, var8, var15);
               var8 += var15;
            }
         }

         for(var12 = var2; var12 < var5; ++var12) {
            for(var13 = var4; var13 < var7; ++var13) {
               this.f.getClass();
               var10000 = var12 << 11;
               this.f.getClass();
               var14 = (var10000 | var13 << 7 | var3) >> 1;
               var15 = (var6 - var3) / 2;
               System.arraycopy(this.h.a, var14, var1, var8, var15);
               var8 += var15;
            }
         }

         return var8;
      }
   }

   public Random a(long var1) {
      return new Random(this.f.k() + (long)(this.l * this.l * 4987142) + (long)(this.l * 5947611) + (long)(this.m * this.m) * 4392871L + (long)(this.m * 389711) ^ var1);
   }

   public void g() {
      OChunkBlockMap.a(this.b);
   }

   public void a(OIChunkProvider var1, OIChunkProvider var2, int var3, int var4) {
      if(!this.p && var1.a(var3 + 1, var4 + 1) && var1.a(var3, var4 + 1) && var1.a(var3 + 1, var4)) {
         var1.a(var2, var3, var4);
      }

      if(var1.a(var3 - 1, var4) && !var1.b(var3 - 1, var4).p && var1.a(var3 - 1, var4 + 1) && var1.a(var3, var4 + 1) && var1.a(var3 - 1, var4 + 1)) {
         var1.a(var2, var3 - 1, var4);
      }

      if(var1.a(var3, var4 - 1) && !var1.b(var3, var4 - 1).p && var1.a(var3 + 1, var4 - 1) && var1.a(var3 + 1, var4 - 1) && var1.a(var3 + 1, var4)) {
         var1.a(var2, var3, var4 - 1);
      }

      if(var1.a(var3 - 1, var4 - 1) && !var1.b(var3 - 1, var4 - 1).p && var1.a(var3, var4 - 1) && var1.a(var3 - 1, var4)) {
         var1.a(var2, var3 - 1, var4 - 1);
      }

   }

   public int c(int var1, int var2) {
      int var3 = var1 | var2 << 4;
      int var4 = this.c[var3];
      if(var4 == -999) {
         this.f.getClass();
         int var5 = 128 - 1;
         var4 = -1;

         while(var5 > 0 && var4 == -1) {
            int var6 = this.a(var1, var5, var2);
            OMaterial var7 = var6 == 0?OMaterial.a:OBlock.m[var6].bN;
            if(!var7.c() && !var7.d()) {
               --var5;
            } else {
               var4 = var5 + 1;
            }
         }

         this.c[var3] = var4;
      }

      return var4;
   }

   public void h() {
      this.i();
   }
}
