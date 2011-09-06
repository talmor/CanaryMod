
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class OChunk {
   public static boolean a;
   public byte[] b;
   public boolean c;
   public OWorld d;
   public ONibbleArray e;
   public ONibbleArray f;
   public ONibbleArray g;
   public byte[] h;
   public int i;
   public final int j;
   public final int k;
   public Map l;
   public List[] m;
   public boolean n;
   public boolean o;
   public boolean p;
   public boolean q;
   public long r;
   // CanaryMod
   public final Chunk chunk = new Chunk(this);

   public OChunk(OWorld var1, int var2, int var3) {
      this.l = new HashMap();
      this.m = new List[8];
      this.n = false;
      this.o = false;
      this.q = false;
      this.r = 0L;
      this.d = var1;
      this.j = var2;
      this.k = var3;
      this.h = new byte[256];

      for(int var4 = 0; var4 < this.m.length; ++var4) {
         this.m[var4] = new ArrayList();
      }

   }

   public OChunk(OWorld var1, byte[] var2, int var3, int var4) {
      this(var1, var3, var4);
      this.b = var2;
      this.e = new ONibbleArray(var2.length);
      this.f = new ONibbleArray(var2.length);
      this.g = new ONibbleArray(var2.length);
   }

   public boolean a(int var1, int var2) {
      return var1 == this.j && var2 == this.k;
   }

   public int b(int var1, int var2) {
      return this.h[var2 << 4 | var1] & 255;
   }

   public void a() {
   }

   public void b() {
      int var1 = 127;

      int var2;
      int var3;
      for(var2 = 0; var2 < 16; ++var2) {
         for(var3 = 0; var3 < 16; ++var3) {
            int var4 = 127;

            int var5;
            for(var5 = var2 << 11 | var3 << 7; var4 > 0 && OBlock.q[this.b[var5 + var4 - 1] & 255] == 0; --var4) {
               ;
            }

            this.h[var3 << 4 | var2] = (byte)var4;
            if(var4 < var1) {
               var1 = var4;
            }

            if(!this.d.t.e) {
               int var6 = 15;
               int var7 = 127;

               do {
                  var6 -= OBlock.q[this.b[var5 + var7] & 255];
                  if(var6 > 0) {
                     this.f.a(var2, var7, var3, var6);
                  }

                  --var7;
               } while(var7 > 0 && var6 > 0);
            }
         }
      }

      this.i = var1;

      for(var2 = 0; var2 < 16; ++var2) {
         for(var3 = 0; var3 < 16; ++var3) {
            this.c(var2, var3);
         }
      }

      this.o = true;
   }

   public void c() {
   }

   private void c(int var1, int var2) {
      int var3 = this.b(var1, var2);
      int var4 = this.j * 16 + var1;
      int var5 = this.k * 16 + var2;
      this.f(var4 - 1, var5, var3);
      this.f(var4 + 1, var5, var3);
      this.f(var4, var5 - 1, var3);
      this.f(var4, var5 + 1, var3);
   }

   private void f(int var1, int var2, int var3) {
      int var4 = this.d.d(var1, var2);
      if(var4 > var3) {
         this.d.a(OEnumSkyBlock.a, var1, var3, var2, var1, var4, var2);
         this.o = true;
      } else if(var4 < var3) {
         this.d.a(OEnumSkyBlock.a, var1, var4, var2, var1, var3, var2);
         this.o = true;
      }

   }

   private void g(int var1, int var2, int var3) {
      int var4 = this.h[var3 << 4 | var1] & 255;
      int var5 = var4;
      if(var2 > var4) {
         var5 = var2;
      }

      for(int var6 = var1 << 11 | var3 << 7; var5 > 0 && OBlock.q[this.b[var6 + var5 - 1] & 255] == 0; --var5) {
         ;
      }

      if(var5 != var4) {
         this.d.g(var1, var3, var5, var4);
         this.h[var3 << 4 | var1] = (byte)var5;
         int var7;
         int var8;
         int var9;
         if(var5 < this.i) {
            this.i = var5;
         } else {
            var7 = 127;

            for(var8 = 0; var8 < 16; ++var8) {
               for(var9 = 0; var9 < 16; ++var9) {
                  if((this.h[var9 << 4 | var8] & 255) < var7) {
                     var7 = this.h[var9 << 4 | var8] & 255;
                  }
               }
            }

            this.i = var7;
         }

         var7 = this.j * 16 + var1;
         var8 = this.k * 16 + var3;
         if(var5 < var4) {
            for(var9 = var5; var9 < var4; ++var9) {
               this.f.a(var1, var9, var3, 15);
            }
         } else {
            this.d.a(OEnumSkyBlock.a, var7, var4, var8, var7, var5, var8);

            for(var9 = var4; var9 < var5; ++var9) {
               this.f.a(var1, var9, var3, 0);
            }
         }

         var9 = 15;

         int var10;
         for(var10 = var5; var5 > 0 && var9 > 0; this.f.a(var1, var5, var3, var9)) {
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

         while(var5 > 0 && OBlock.q[this.a(var1, var5 - 1, var3)] == 0) {
            --var5;
         }

         if(var5 != var10) {
            this.d.a(OEnumSkyBlock.a, var7 - 1, var5, var8 - 1, var7 + 1, var10, var8 + 1);
         }

         this.o = true;
      }
   }

   public int a(int var1, int var2, int var3) {
      return this.b[var1 << 11 | var3 << 7 | var2] & 255;
   }

   public boolean a(int var1, int var2, int var3, int var4, int var5) {
      return a(var1, var2, var3, var4, var5, true);
   }

   public boolean a(int var1, int var2, int var3, int var4, int var5, boolean checkPortal) {
      byte var6 = (byte)var4;
      int var7 = this.h[var3 << 4 | var1] & 255;
      int var8 = this.b[var1 << 11 | var3 << 7 | var2] & 255;
      if(var8 == var4 && this.e.a(var1, var2, var3) == var5) {
         return false;
      } else {
         int var9 = this.j * 16 + var1;
         int var10 = this.k * 16 + var3;
         if(checkPortal == true) {
            // CanaryMod check if removed block is portal block
            int portalId = Block.Type.Portal.getType();
            if(chunk.getWorld().getBlockIdAt(var9, var2, var10) == portalId) {
               // These will be equal 1 if the portal is defined on their
               // axis
               // and 0 if not.
               int portalXOffset = (chunk.getWorld().getBlockIdAt(var9 - 1, var2, var10) == portalId || chunk.getWorld().getBlockIdAt(var9 + 1, var2, var10) == portalId)?1:0;
               int portalZOffset = (chunk.getWorld().getBlockIdAt(var9, var2, var10 - 1) == portalId || chunk.getWorld().getBlockIdAt(var9, var2, var10 + 1) == portalId)?1:0;
               // If the portal is either x aligned or z aligned but not
               // both
               // (has neighbor portal in x or z plane but not both)
               if(portalXOffset != portalZOffset) {
                  // Get the edge of the portal.
                  int portalX = var9 - ((chunk.getWorld().getBlockIdAt(var9 - 1, var2, var10) == portalId)?1:0);
                  int portalZ = var10 - ((chunk.getWorld().getBlockIdAt(var9, var2, var10 - 1) == portalId)?1:0);
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
         this.b[var1 << 11 | var3 << 7 | var2] = (byte)(var6 & 255);
         if(var8 != 0 && !this.d.B) {
            OBlock.m[var8].b(this.d, var9, var2, var10);
         }

         this.e.a(var1, var2, var3, var5);
         if(!this.d.t.e) {
            if(OBlock.q[var6 & 255] != 0) {
               if(var2 >= var7) {
                  this.g(var1, var2 + 1, var3);
               }
            } else if(var2 == var7 - 1) {
               this.g(var1, var2, var3);
            }

            this.d.a(OEnumSkyBlock.a, var9, var2, var10, var9, var2, var10);
         }

         this.d.a(OEnumSkyBlock.b, var9, var2, var10, var9, var2, var10);
         this.c(var1, var3);
         this.e.a(var1, var2, var3, var5);
         if(var4 != 0) {
            OBlock.m[var4].c(this.d, var9, var2, var10);
         }

         this.o = true;
         return true;
      }
   }

   public boolean a(int var1, int var2, int var3, int var4) {
      return a(var1, var2, var3, var4, true);
   }

   public boolean a(int var1, int var2, int var3, int var4, boolean checkPortal) {
      byte var5 = (byte)var4;
      int var6 = this.h[var3 << 4 | var1] & 255;
      int var7 = this.b[var1 << 11 | var3 << 7 | var2] & 255;
      if(var7 == var4) {
         return false;
      } else {
         int var8 = this.j * 16 + var1;
         int var9 = this.k * 16 + var3;
         if(checkPortal == true) {
            // CanaryMod check if removed block is portal block
            int portalId = Block.Type.Portal.getType();
            if(chunk.getWorld().getBlockIdAt(var8, var2, var9) == portalId) {
               // These will be equal 1 if the portal is defined on their
               // axis
               // and 0 if not.
               int portalXOffset = (chunk.getWorld().getBlockIdAt(var8 - 1, var2, var9) == portalId || chunk.getWorld().getBlockIdAt(var8 + 1, var2, var9) == portalId)?1:0;
               int portalZOffset = (chunk.getWorld().getBlockIdAt(var8, var2, var9 - 1) == portalId || chunk.getWorld().getBlockIdAt(var8, var2, var9 + 1) == portalId)?1:0;
               // If the portal is either x aligned or z aligned but not
               // both
               // (has neighbor portal in x or z plane but not both)
               if(portalXOffset != portalZOffset) {
                  // Get the edge of the portal.
                  int portalX = var8 - ((chunk.getWorld().getBlockIdAt(var8 - 1, var2, var9) == portalId)?1:0);
                  int portalZ = var9 - ((chunk.getWorld().getBlockIdAt(var8, var2, var9 - 1) == portalId)?1:0);
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
                        // portal's frame to make the portal valid.
                        // Problem is we don't want to reconstruct it
                        // right away because more blocks might be
                        // deleted (for example on explosion).
                        // In order to avoid spamming the hook for each
                        // destroyed block, I'm queuing the
                        // reconstruction of the portal instead.
                        etc.getServer().addToServerQueue(new PortalReconstructJob(chunk.getWorld(), portalX, portalY, portalZ, (portalXOffset == 1)));
                     }
                  }
               }
            }
         }
         this.b[var1 << 11 | var3 << 7 | var2] = (byte)(var5 & 255);
         if(var7 != 0) {
            OBlock.m[var7].b(this.d, var8, var2, var9);
         }

         this.e.a(var1, var2, var3, 0);
         if(OBlock.q[var5 & 255] != 0) {
            if(var2 >= var6) {
               this.g(var1, var2 + 1, var3);
            }
         } else if(var2 == var6 - 1) {
            this.g(var1, var2, var3);
         }

         this.d.a(OEnumSkyBlock.a, var8, var2, var9, var8, var2, var9);
         this.d.a(OEnumSkyBlock.b, var8, var2, var9, var8, var2, var9);
         this.c(var1, var3);
         if(var4 != 0 && !this.d.B) {
            OBlock.m[var4].c(this.d, var8, var2, var9);
         }

         this.o = true;
         return true;
      }
   }

   public int b(int var1, int var2, int var3) {
      return this.e.a(var1, var2, var3);
   }

   public void b(int var1, int var2, int var3, int var4) {
      this.o = true;
      this.e.a(var1, var2, var3, var4);
   }

   public int a(OEnumSkyBlock var1, int var2, int var3, int var4) {
      return var1 == OEnumSkyBlock.a?this.f.a(var2, var3, var4):(var1 == OEnumSkyBlock.b?this.g.a(var2, var3, var4):0);
   }

   public void a(OEnumSkyBlock var1, int var2, int var3, int var4, int var5) {
      this.o = true;
      if(var1 == OEnumSkyBlock.a) {
         this.f.a(var2, var3, var4, var5);
      } else {
         if(var1 != OEnumSkyBlock.b) {
            return;
         }

         this.g.a(var2, var3, var4, var5);
      }

   }

   public int c(int var1, int var2, int var3, int var4) {
      int var5 = this.f.a(var1, var2, var3);
      if(var5 > 0) {
         a = true;
      }

      var5 -= var4;
      int var6 = this.g.a(var1, var2, var3);
      if(var6 > var5) {
         var5 = var6;
      }

      return var5;
   }

   public void a(OEntity var1) {
      this.q = true;
      int var2 = OMathHelper.b(var1.aP / 16.0D);
      int var3 = OMathHelper.b(var1.aR / 16.0D);
      if(var2 != this.j || var3 != this.k) {
         System.out.println("Wrong location! " + var1);
         Thread.dumpStack();
      }

      int var4 = OMathHelper.b(var1.aQ / 16.0D);
      if(var4 < 0) {
         var4 = 0;
      }

      if(var4 >= this.m.length) {
         var4 = this.m.length - 1;
      }

      var1.bG = true;
      var1.bH = this.j;
      var1.bI = var4;
      var1.bJ = this.k;
      this.m[var4].add(var1);
   }

   public void b(OEntity var1) {
      this.a(var1, var1.bI);
   }

   public void a(OEntity var1, int var2) {
      if(var2 < 0) {
         var2 = 0;
      }

      if(var2 >= this.m.length) {
         var2 = this.m.length - 1;
      }

      this.m[var2].remove(var1);
   }

   public boolean c(int var1, int var2, int var3) {
      return var2 >= (this.h[var3 << 4 | var1] & 255);
   }

   public OTileEntity d(int var1, int var2, int var3) {
      OChunkPosition var4 = new OChunkPosition(var1, var2, var3);
      OTileEntity var5 = (OTileEntity)this.l.get(var4);
      if(var5 == null) {
         int var6 = this.a(var1, var2, var3);
         if(!OBlock.p[var6]) {
            return null;
         }

         OBlockContainer var7 = (OBlockContainer)OBlock.m[var6];
         var7.c(this.d, this.j * 16 + var1, var2, this.k * 16 + var3);
         var5 = (OTileEntity)this.l.get(var4);
      }

      if(var5 != null && var5.g()) {
         this.l.remove(var4);
         return null;
      } else {
         return var5;
      }
   }

   public void a(OTileEntity var1) {
      int var2 = var1.e - this.j * 16;
      int var3 = var1.f;
      int var4 = var1.g - this.k * 16;
      this.a(var2, var3, var4, var1);
      if(this.c) {
         this.d.c.add(var1);
      }

   }

   public void a(int var1, int var2, int var3, OTileEntity var4) {
      OChunkPosition var5 = new OChunkPosition(var1, var2, var3);
      var4.d = this.d;
      var4.e = this.j * 16 + var1;
      var4.f = var2;
      var4.g = this.k * 16 + var3;
      if(this.a(var1, var2, var3) != 0 && OBlock.m[this.a(var1, var2, var3)] instanceof OBlockContainer) {
         var4.j();
         this.l.put(var5, var4);
      } else {
         System.out.println("Attempted to place a tile entity where there was no entity tile!");
      }
   }

   public void e(int var1, int var2, int var3) {
      OChunkPosition var4 = new OChunkPosition(var1, var2, var3);
      if(this.c) {
         OTileEntity var5 = (OTileEntity)this.l.remove(var4);
         if(var5 != null) {
            var5.h();
         }
      }

   }

   public void d() {
      this.c = true;
      this.d.a(this.l.values());

      for(int var1 = 0; var1 < this.m.length; ++var1) {
         this.d.a(this.m[var1]);
      }

   }

   public void e() {
      this.c = false;
      Iterator var1 = this.l.values().iterator();

      while(var1.hasNext()) {
         OTileEntity var2 = (OTileEntity)var1.next();
         var2.h();
      }

      for(int var3 = 0; var3 < this.m.length; ++var3) {
         this.d.b(this.m[var3]);
      }

   }

   public void f() {
      this.o = true;
   }

   public void a(OEntity var1, OAxisAlignedBB var2, List var3) {
      int var4 = OMathHelper.b((var2.b - 2.0D) / 16.0D);
      int var5 = OMathHelper.b((var2.e + 2.0D) / 16.0D);
      if(var4 < 0) {
         var4 = 0;
      }

      if(var5 >= this.m.length) {
         var5 = this.m.length - 1;
      }

      for(int var6 = var4; var6 <= var5; ++var6) {
         List var7 = this.m[var6];

         for(int var8 = 0; var8 < var7.size(); ++var8) {
            OEntity var9 = (OEntity)var7.get(var8);
            if(var9 != var1 && var9.aZ.a(var2)) {
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

      if(var5 >= this.m.length) {
         var5 = this.m.length - 1;
      }

      for(int var6 = var4; var6 <= var5; ++var6) {
         List var7 = this.m[var6];

         for(int var8 = 0; var8 < var7.size(); ++var8) {
            OEntity var9 = (OEntity)var7.get(var8);
            if(var1.isAssignableFrom(var9.getClass()) && var9.aZ.a(var2)) {
               var3.add(var9);
            }
         }
      }

   }

   public boolean a(boolean var1) {
      if(this.p) {
         return false;
      } else {
         if(var1) {
            if(this.q && this.d.m() != this.r) {
               return true;
            }
         } else if(this.q && this.d.m() >= this.r + 600L) {
            return true;
         }

         return this.o;
      }
   }

   public int a(byte[] var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      int var9 = var5 - var2;
      int var10 = var6 - var3;
      int var11 = var7 - var4;
      if(var9 * var10 * var11 == this.b.length) {
         System.arraycopy(this.b, 0, var1, var8, this.b.length);
         var8 += this.b.length;
         System.arraycopy(this.e.a, 0, var1, var8, this.e.a.length);
         var8 += this.e.a.length;
         System.arraycopy(this.g.a, 0, var1, var8, this.g.a.length);
         var8 += this.g.a.length;
         System.arraycopy(this.f.a, 0, var1, var8, this.f.a.length);
         var8 += this.f.a.length;
         return var8;
      } else {
         int var12;
         int var13;
         int var14;
         int var15;
         for(var12 = var2; var12 < var5; ++var12) {
            for(var13 = var4; var13 < var7; ++var13) {
               var14 = var12 << 11 | var13 << 7 | var3;
               var15 = var6 - var3;
               System.arraycopy(this.b, var14, var1, var8, var15);
               var8 += var15;
            }
         }

         for(var12 = var2; var12 < var5; ++var12) {
            for(var13 = var4; var13 < var7; ++var13) {
               var14 = (var12 << 11 | var13 << 7 | var3) >> 1;
               var15 = (var6 - var3) / 2;
               System.arraycopy(this.e.a, var14, var1, var8, var15);
               var8 += var15;
            }
         }

         for(var12 = var2; var12 < var5; ++var12) {
            for(var13 = var4; var13 < var7; ++var13) {
               var14 = (var12 << 11 | var13 << 7 | var3) >> 1;
               var15 = (var6 - var3) / 2;
               System.arraycopy(this.g.a, var14, var1, var8, var15);
               var8 += var15;
            }
         }

         for(var12 = var2; var12 < var5; ++var12) {
            for(var13 = var4; var13 < var7; ++var13) {
               var14 = (var12 << 11 | var13 << 7 | var3) >> 1;
               var15 = (var6 - var3) / 2;
               System.arraycopy(this.f.a, var14, var1, var8, var15);
               var8 += var15;
            }
         }

         return var8;
      }
   }

   public Random a(long var1) {
      return new Random(this.d.l() + (long)(this.j * this.j * 4987142) + (long)(this.j * 5947611) + (long)(this.k * this.k) * 4392871L + (long)(this.k * 389711) ^ var1);
   }

   public boolean g() {
      return false;
   }

   public void h() {
      OChunkBlockMap.a(this.b);
   }

}
