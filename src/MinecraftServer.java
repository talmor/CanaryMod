

import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MinecraftServer implements Runnable, OICommandListener {

   public static Logger a = Logger.getLogger("Minecraft");
   public static HashMap b = new HashMap();
   public ONetworkListenThread c;
   public OPropertyManager d;
   public OWorldServer[] e;
   public OServerConfigurationManager f;
   private OConsoleCommandHandler q;
   private boolean r = true;
   public boolean g = false;
   int h = 0;
   public String i;
   public int j;
   private List s = new ArrayList();
   private List t = Collections.synchronizedList(new ArrayList());
   public OEntityTracker[] k = new OEntityTracker[2];
   public boolean l;
   public boolean m;
   public boolean n;
   public boolean o;
   public String p;


   public MinecraftServer() {
      super();
      new OThreadSleepForever(this);
   }

   private boolean c() throws UnknownHostException {
      this.q = new OConsoleCommandHandler(this);
      OThreadCommandReader threadcommandreader = new OThreadCommandReader(this);
      threadcommandreader.setDaemon(true);
      threadcommandreader.start();
      OConsoleLogManager.a();
      ModLoader.Init(this);
      a.info("Starting minecraft server version Beta 1.8.1");
      if(Runtime.getRuntime().maxMemory() / 1024L / 1024L < 512L) {
         a.warning("**** NOT ENOUGH RAM!");
         a.warning("To start the server with more ram, launch it as \"java -Xmx1024M -Xms1024M -jar minecraft_server.jar\"");
      }

      a.info("Loading properties");
      this.d = new OPropertyManager(new File("server.properties"));
      String s = this.d.a("server-ip", "");
      this.l = this.d.a("online-mode", true);
      this.m = this.d.a("spawn-animals", true);
      this.n = this.d.a("pvp", true);
      this.o = this.d.a("allow-flight", false);
      this.p = this.d.a("motd", "A Minecraft Server");
      this.p.replace('\u00a7', '$');
      InetAddress inetaddress = null;
      if(s.length() > 0) {
         inetaddress = InetAddress.getByName(s);
      }

      int i = this.d.a("server-port", 25565);
      a.info("Starting Minecraft server on " + (s.length() != 0?s:"*") + ":" + i);

      try {
         this.c = new ONetworkListenThread(this, inetaddress, i);
      } catch (IOException var14) {
         a.warning("**** FAILED TO BIND TO PORT!");
         a.log(Level.WARNING, "The exception was: " + var14.toString());
         a.warning("Perhaps a server is already running on that port?");
         return false;
      }

      if(!this.l) {
         a.warning("**** SERVER IS RUNNING IN OFFLINE/INSECURE MODE!");
         a.warning("The server will make no attempt to authenticate usernames. Beware.");
         a.warning("While this makes the game possible to play without internet access, it also opens up the ability for hackers to connect with any username they choose.");
         a.warning("To change this, set \"online-mode\" to \"true\" in the server.settings file.");
      }

      this.f = new OServerConfigurationManager(this);
      this.k[0] = new OEntityTracker(this, 0);
      this.k[1] = new OEntityTracker(this, -1);
      long l = System.nanoTime();
      String s1 = this.d.a("level-name", "world");
      String s2 = this.d.a("level-seed", "");
      long l1 = (new Random()).nextLong();
      if(s2.length() > 0) {
         try {
            l1 = Long.parseLong(s2);
         } catch (NumberFormatException var13) {
            l1 = (long)s2.hashCode();
         }
      }

      a.info("Preparing level \"" + s1 + "\"");
      this.a(new OSaveConverterMcRegion(new File(".")), s1, l1);
      a.info("Done (" + (System.nanoTime() - l) + "ns)! For help, type \"help\" or \"?\"");
      return true;
   }

   private void a(OISaveFormat isaveformat, String s, long l) {
      if(isaveformat.a(s)) {
         a.info("Converting map!");
         isaveformat.a(s, new OConvertProgressUpdater(this));
      }

      this.e = new OWorldServer[2];
      int i = this.d.a("gamemode", 0);
      i = OWorldSettings.a(i);
      a.info("Default game type: " + i);
      OWorldSettings worldsettings = new OWorldSettings(l, i, true);
      OSaveOldDir saveolddir = new OSaveOldDir(new File("."), s, true);

      for(int c = 0; c < this.e.length; ++c) {
         if(c == 0) {
            this.e[c] = new OWorldServer(this, saveolddir, s, c != 0?-1:0, worldsettings);
         } else {
            this.e[c] = new OWorldServerMulti(this, saveolddir, s, c != 0?-1:0, worldsettings, this.e[0]);
         }

         this.e[c].a(new OWorldManager(this, this.e[c]));
         this.e[c].v = this.d.a("difficulty", 1);
         this.e[c].a(this.d.a("spawn-monsters", true), this.m);
         this.e[c].p().d(i);
         this.f.a(this.e);
      }

      short var20 = 196;
      long l1 = System.currentTimeMillis();

      for(int k = 0; k < this.e.length; ++k) {
         a.info("Preparing start region for level " + k);
         if(k == 0 || this.d.a("allow-nether", true)) {
            OWorldServer worldserver = this.e[k];
            OChunkCoordinates chunkcoordinates = worldserver.m();

            for(int i1 = -var20; i1 <= var20 && this.r; i1 += 16) {
               for(int j1 = -var20; j1 <= var20 && this.r; j1 += 16) {
                  long l2 = System.currentTimeMillis();
                  if(l2 < l1) {
                     l1 = l2;
                  }

                  if(l2 > l1 + 1000L) {
                     int k1 = (var20 * 2 + 1) * (var20 * 2 + 1);
                     int i2 = (i1 + var20) * (var20 * 2 + 1) + j1 + 1;
                     this.a("Preparing spawn area", i2 * 100 / k1);
                     l1 = l2;
                  }

                  worldserver.M.c(chunkcoordinates.a + i1 >> 4, chunkcoordinates.c + j1 >> 4);

                  while(worldserver.v() && this.r) {
                     ;
                  }
               }
            }
         }
      }

      this.e();
   }

   private void a(String s, int i) {
      this.i = s;
      this.j = i;
      a.info(s + ": " + i + "%");
   }

   private void e() {
      this.i = null;
      this.j = 0;
   }

   private void f() {
      a.info("Saving chunks");

      for(int i = 0; i < this.e.length; ++i) {
         OWorldServer worldserver = this.e[i];
         worldserver.a(true, (OIProgressUpdate)null);
         worldserver.w();
      }

   }

   private void g() {
      a.info("Stopping server");
      if(this.f != null) {
         this.f.d();
      }

      for(int i = 0; i < this.e.length; ++i) {
         OWorldServer worldserver = this.e[i];
         if(worldserver != null) {
            this.f();
         }
      }

   }

   public void a() {
      this.r = false;
   }

   public void run() {
      boolean var59 = false;

      label595: {
         try {
            var59 = true;
            if(this.c()) {
               long var1 = System.currentTimeMillis();

               for(long var3 = 0L; this.r; Thread.sleep(1L)) {
                  ModLoader.OnTick(this);
                  long var5 = System.currentTimeMillis();
                  long var7 = var5 - var1;
                  if(var7 > 2000L) {
                     a.warning("Can\'t keep up! Did the system time change, or is the server overloaded?");
                     var7 = 2000L;
                  }

                  if(var7 < 0L) {
                     a.warning("Time ran backwards! Did the system time change?");
                     var7 = 0L;
                  }

                  var3 += var7;
                  var1 = var5;
                  if(this.e[0].s()) {
                     this.h();
                     var3 = 0L;
                  } else {
                     while(var3 > 50L) {
                        var3 -= 50L;
                        this.h();
                     }
                  }
               }

               var59 = false;
            } else {
               while(this.r) {
                  this.b();

                  try {
                     Thread.sleep(10L);
                  } catch (InterruptedException var61) {
                     var61.printStackTrace();
                  }
               }

               var59 = false;
            }
            break label595;
         } catch (Throwable var68) {
            var68.printStackTrace();
            a.log(Level.SEVERE, "Unexpected exception", var68);

            while(true) {
               if(!this.r) {
                  var59 = false;
                  break;
               }

               this.b();

               try {
                  Thread.sleep(10L);
               } catch (InterruptedException var60) {
                  var60.printStackTrace();
               }
            }
         } finally {
            if(var59) {
               boolean var48 = false;

               label523: {
                  label522: {
                     try {
                        var48 = true;
                        this.g();
                        this.g = true;
                        var48 = false;
                        break label522;
                     } catch (Throwable var62) {
                        var62.printStackTrace();
                        var48 = false;
                     } finally {
                        if(var48) {
                           System.exit(0);
                        }
                     }

                     System.exit(0);
                     break label523;
                  }

                  System.exit(0);
               }

            }
         }

         boolean var37 = false;

         label596: {
            try {
               var37 = true;
               this.g();
               this.g = true;
               var37 = false;
               break label596;
            } catch (Throwable var64) {
               var64.printStackTrace();
               var37 = false;
            } finally {
               if(var37) {
                  System.exit(0);
               }
            }

            System.exit(0);
            return;
         }

         System.exit(0);
         return;
      }

      boolean var26 = false;

      label597: {
         try {
            var26 = true;
            this.g();
            this.g = true;
            var26 = false;
            break label597;
         } catch (Throwable var66) {
            var66.printStackTrace();
            var26 = false;
         } finally {
            if(var26) {
               System.exit(0);
            }
         }

         System.exit(0);
         return;
      }

      System.exit(0);
   }

   private void h() {
      ArrayList arraylist = new ArrayList();
      Iterator exception = b.keySet().iterator();

      while(exception.hasNext()) {
         String worldserver = (String)exception.next();
         int i1 = ((Integer)b.get(worldserver)).intValue();
         if(i1 > 0) {
            b.put(worldserver, Integer.valueOf(i1 - 1));
         } else {
            arraylist.add(worldserver);
         }
      }

      int var6;
      for(var6 = 0; var6 < arraylist.size(); ++var6) {
         b.remove(arraylist.get(var6));
      }

      OAxisAlignedBB.a();
      OVec3D.a();
      ++this.h;

      for(var6 = 0; var6 < this.e.length; ++var6) {
         if(var6 == 0 || this.d.a("allow-nether", true)) {
            OWorldServer var7 = this.e[var6];
            if(this.h % 20 == 0) {
               this.f.a(new OPacket4UpdateTime(var7.l()), var7.y.g);
            }

            var7.g();

            while(var7.v()) {
               ;
            }

            var7.e();
         }
      }

      this.c.a();
      this.f.b();

      for(var6 = 0; var6 < this.k.length; ++var6) {
         this.k[var6].a();
      }

      for(var6 = 0; var6 < this.s.size(); ++var6) {
         ((OIUpdatePlayerListBox)this.s.get(var6)).a();
      }

      try {
         this.b();
      } catch (Exception var5) {
         a.log(Level.WARNING, "Unexpected exception while parsing console command", var5);
      }

   }

   public void a(String s, OICommandListener icommandlistener) {
      this.t.add(new OServerCommand(s, icommandlistener));
   }

   public void b() {
      while(this.t.size() > 0) {
         OServerCommand servercommand = (OServerCommand)this.t.remove(0);
         this.q.a(servercommand);
      }

   }

   public void a(OIUpdatePlayerListBox iupdateplayerlistbox) {
      this.s.add(iupdateplayerlistbox);
   }

   public static void main(String[] args) {
      OStatList.a();

      try {
         MinecraftServer exception = new MinecraftServer();
         if(!GraphicsEnvironment.isHeadless() && (args.length <= 0 || !args[0].equals("nogui"))) {
            OServerGUI.a(exception);
         }

         (new OThreadServerApplication("Server thread", exception)).start();
      } catch (Exception var2) {
         a.log(Level.SEVERE, "Failed to start the minecraft server", var2);
      }

   }

   public File a(String s) {
      return new File(s);
   }

   public void b(String s) {
      a.info(s);
   }

   public void c(String s) {
      a.warning(s);
   }

   public String d() {
      return "CONSOLE";
   }

   public OWorldServer a(int i) {
      return i == -1?this.e[1]:this.e[0];
   }

   public OEntityTracker b(int i) {
      return i == -1?this.k[1]:this.k[0];
   }

   public static boolean a(MinecraftServer minecraftserver) {
      return minecraftserver.r;
   }

}
