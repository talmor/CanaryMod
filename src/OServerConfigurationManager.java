
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import net.minecraft.server.MinecraftServer;

public class OServerConfigurationManager {
   public static Logger a = Logger.getLogger("Minecraft");
   public List b = new ArrayList();
   private MinecraftServer c;
   private OPlayerManager[] d = new OPlayerManager[2];
   private int e;
   private Set f = new HashSet();
   private Set g = new HashSet();
   private Set h = new HashSet();
   private Set i = new HashSet();
   private File j;
   private File k;
   private File l;
   private File m;
   private OIPlayerFileData n;
   private boolean o;

   public OServerConfigurationManager(MinecraftServer var1) {
      // CanaryMod: initialize
      etc.setServer(var1);
      etc.getInstance().loadData();
      a.info("Note: your current classpath is: " + System.getProperty("java.class.path", "*UNKNOWN*"));
      if(!etc.getInstance().getTainted())
         a.info("Canary Crow Build 3.1.9: " + etc.getInstance().getVersion());
      else
         a.info("CanaryMod Build Information: " + etc.getInstance().getVersionStr());

      this.c = var1;
      this.j = var1.a("banned-players.txt");
      this.k = var1.a("banned-ips.txt");
      this.l = var1.a("ops.txt");
      this.m = var1.a("white-list.txt");
      int var2 = var1.d.a("view-distance", 10);
      this.d[0] = new OPlayerManager(var1, 0, var2);
      this.d[1] = new OPlayerManager(var1, -1, var2);
      this.e = var1.d.a("max-players", 20);
      this.o = var1.d.a("white-list", false);
      this.g();
      this.i();
      this.k();
      this.m();
      this.h();
      this.j();
      this.l();
      this.n();
   }

   public void a(OWorldServer[] var1) {
      this.n = var1[0].p().d();
   }

   public void a(OEntityPlayerMP var1) {
      this.d[0].b(var1);
      this.d[1].b(var1);
      this.a(var1.s).a(var1);
      OWorldServer var2 = this.c.a(var1.s);
      var2.C.c((int)var1.aP >> 4, (int)var1.aR >> 4);
   }

   public int a() {
      return this.d[0].c();
   }

   private OPlayerManager a(int var1) {
      return var1 == -1?this.d[1]:this.d[0];
   }

   public void b(OEntityPlayerMP var1) {
      this.n.b(var1);
   }

   public void c(OEntityPlayerMP var1) {
      this.b.add(var1);
      OWorldServer var2 = this.c.a(var1.s);
      var2.C.c((int)var1.aP >> 4, (int)var1.aR >> 4);

      while(var2.a(var1, var1.aZ).size() != 0) {
         var1.c(var1.aP, var1.aQ + 1.0D, var1.aR);
      }

      var2.b(var1);
      this.a(var1.s).a(var1);
      // CanaryMod: Handle login (send MOTD and call hook)
      String[] motd = etc.getInstance().getMotd();
      if(!(motd.length == 1 && motd[0].equals("")))
         for(String str : motd)
            var1.a.b(new OPacket3Chat(str));
      etc.getLoader().callHook(PluginLoader.Hook.LOGIN, var1.getPlayer());
   }

   public void d(OEntityPlayerMP var1) {
      this.a(var1.s).c(var1);
   }

   public void e(OEntityPlayerMP var1) {
      this.n.a(var1);
      this.c.a(var1.s).e(var1);
      this.b.remove(var1);
      this.a(var1.s).b(var1);
   }

   public OEntityPlayerMP a(ONetLoginHandler var1, String var2) {
      // TODO: add reasons, expire tempbans
      if(!etc.getLoader().isLoaded())
         var1.a("The server is not finished loading yet!");
      // CanaryMod: whole section below is modified to handle whitelists etc
      OEntityPlayerMP temp = new OEntityPlayerMP(c, c.a(0), var2, new OItemInWorldManager(c.a(0)));
      Player player = temp.getPlayer();

      if(this.f.contains(var2.trim().toLowerCase())) {
         var1.a("You are banned from this server!");
         return null;
      } else if(!this.g(var2) || (etc.getInstance().isWhitelistEnabled() && !(etc.getDataSource().isUserOnWhitelist(var2) || player.isAdmin()))) {
         var1.a(etc.getInstance().getWhitelistMessage());
         return null;
      } else {
         String var3 = var1.b.c().toString();
         var3 = var3.substring(var3.indexOf("/") + 1);
         var3 = var3.substring(0, var3.indexOf(":"));
         if(this.g.contains(var3)) {
            var1.a("Your IP address is banned from this server!");
            return null;
         } else if(this.b.size() >= this.e && (!etc.getInstance().isReservelistEnabled() || !etc.getDataSource().isUserOnReserveList(var2))) {
            var1.a("The server is full!");
            return null;
         } else if(!player.getIps()[0].equals("")) {
            boolean kick = true;
            for(int i = 0; i < player.getIps().length; i++)
               if(!player.getIps()[i].equals("") && var3.equals(player.getIps()[i]))
                  kick = false;
            if(kick) {
               var1.a("IP doesn't match specified IP.");
               return null;
            }
         } else {
            for(int var4 = 0; var4 < this.b.size(); ++var4) {
               OEntityPlayerMP var5 = (OEntityPlayerMP)this.b.get(var4);
               if(var5.r.equalsIgnoreCase(var2)) {
                  var5.a.a("You logged in from another location");
               }
            }
         }
      }

      // CanaryMod: user passed basic login check, inform plugins.
      Object obj = etc.getLoader().callHook(PluginLoader.Hook.LOGINCHECK, var2);
      if(obj instanceof String) {
         String result = (String)obj;
         if(result != null && !result.equals("")) {
            var1.a(result);
            return null;
         }
      }
      return temp;
   }

   public OEntityPlayerMP a(OEntityPlayerMP var1, int var2) {
      this.c.b(var1.s).a(var1);
      this.c.b(var1.s).b(var1);
      this.a(var1.s).b(var1);
      this.b.remove(var1);
      this.c.a(var1.s).f(var1);
      OChunkCoordinates var3 = var1.N();
      var1.s = var2;
      OEntityPlayerMP var4 = new OEntityPlayerMP(this.c, this.c.a(var1.s), var1.r, new OItemInWorldManager(this.c.a(var1.s)));
      var4.aG = var1.aG;
      var4.a = var1.a;
      OWorldServer var5 = this.c.a(var1.s);
      if(var3 != null) {
         OChunkCoordinates var6 = OEntityPlayer.a(this.c.a(var1.s), var3);
         if(var6 != null) {
            var4.c((double)((float)var6.a + 0.5F), (double)((float)var6.b + 0.1F), (double)((float)var6.c + 0.5F), 0.0F, 0.0F);
            var4.a(var3);
         } else {
            var4.a.b((OPacket)(new OPacket70Bed(0)));
         }
      }

      var5.C.c((int)var4.aP >> 4, (int)var4.aR >> 4);

      while(var5.a(var4, var4.aZ).size() != 0) {
         var4.c(var4.aP, var4.aQ + 1.0D, var4.aR);
      }

      var4.a.b((OPacket)(new OPacket9Respawn((byte)var4.s)));
      var4.a.a(var4.aP, var4.aQ, var4.aR, var4.aV, var4.aW);
      this.a(var4, var5);
      this.a(var4.s).a(var4);
      var5.b(var4);
      this.b.add(var4);
      var4.o();
      var4.x();
      return var4;
   }

   // Canary: disable the creation of portals when switching worlds
   public void f(OEntityPlayerMP var1, boolean createPortal) {
      OWorldServer var2 = this.c.a(var1.s);
      boolean var3 = false;
      byte var11;
      if(var1.s == -1) {
         var11 = 0;
      } else {
         var11 = -1;
      }

      var1.s = var11;
      OWorldServer var4 = this.c.a(var1.s);
      var1.a.b((OPacket)(new OPacket9Respawn((byte)var1.s)));
      var2.f(var1);
      var1.bh = false;
      double var5 = var1.aP;
      double var7 = var1.aR;
      double var9 = 8.0D;
      if(var1.s == -1) {
         var5 /= var9;
         var7 /= var9;
         var1.c(var5, var1.aQ, var7, var1.aV, var1.aW);
         if(var1.T()) {
            var2.a(var1, false);
         }
      } else {
         var5 *= var9;
         var7 *= var9;
         var1.c(var5, var1.aQ, var7, var1.aV, var1.aW);
         if(var1.T()) {
            var2.a(var1, false);
         }
      }

      if(var1.T()) {
         var4.b(var1);
         var1.c(var5, var1.aQ, var7, var1.aV, var1.aW);
         var4.a(var1, false);
         var4.C.a = true;
         if(createPortal)
            (new OTeleporter()).a(var4, var1);
         var4.C.a = false;
      }

      this.a(var1);
      var1.a.a(var1.aP, var1.aQ, var1.aR, var1.aV, var1.aW);
      var1.a((OWorld)var4);
      this.a(var1, var4);
      this.g(var1);
   }

   // Canary: Create a portal on default calls
   public void f(OEntityPlayerMP var1) {
      this.f(var1, true);
   }

   public void b() {
      for(int var1 = 0; var1 < this.d.length; ++var1) {
         this.d[var1].b();
      }

   }

   public void a(int var1, int var2, int var3, int var4) {
      this.a(var4).a(var1, var2, var3);
   }

   public void a(OPacket var1) {
      for(int var2 = 0; var2 < this.b.size(); ++var2) {
         OEntityPlayerMP var3 = (OEntityPlayerMP)this.b.get(var2);
         var3.a.b(var1);
      }

   }

   public void a(OPacket var1, int var2) {
      for(int var3 = 0; var3 < this.b.size(); ++var3) {
         OEntityPlayerMP var4 = (OEntityPlayerMP)this.b.get(var3);
         if(var4.s == var2) {
            var4.a.b(var1);
         }
      }

   }

   public String c() {
      String var1 = "";

      for(int var2 = 0; var2 < this.b.size(); ++var2) {
         if(var2 > 0) {
            var1 = var1 + ", ";
         }

         var1 = var1 + ((OEntityPlayerMP)this.b.get(var2)).r;
      }

      return var1;
   }

   public void a(String var1) {
      this.f.add(var1.toLowerCase());
      this.h();
   }

   public void b(String var1) {
      this.f.remove(var1.toLowerCase());
      this.h();
   }

   private void g() {
      try {
         this.f.clear();
         BufferedReader var1 = new BufferedReader(new FileReader(this.j));
         String var2 = "";

         while((var2 = var1.readLine()) != null) {
            this.f.add(var2.trim().toLowerCase());
         }

         var1.close();
      } catch (Exception var3) {
         a.warning("Failed to load ban list: " + var3);
      }

   }

   private void h() {
      try {
         PrintWriter var1 = new PrintWriter(new FileWriter(this.j, false));
         Iterator var2 = this.f.iterator();

         while(var2.hasNext()) {
            String var3 = (String)var2.next();
            var1.println(var3);
         }

         var1.close();
      } catch (Exception var4) {
         a.warning("Failed to save ban list: " + var4);
      }

   }

   public void c(String var1) {
      this.g.add(var1.toLowerCase());
      this.j();
   }

   public void d(String var1) {
      this.g.remove(var1.toLowerCase());
      this.j();
   }

   private void i() {
      try {
         this.g.clear();
         BufferedReader var1 = new BufferedReader(new FileReader(this.k));
         String var2 = "";

         while((var2 = var1.readLine()) != null) {
            this.g.add(var2.trim().toLowerCase());
         }

         var1.close();
      } catch (Exception var3) {
         a.warning("Failed to load ip ban list: " + var3);
      }

   }

   private void j() {
      try {
         PrintWriter var1 = new PrintWriter(new FileWriter(this.k, false));
         Iterator var2 = this.g.iterator();

         while(var2.hasNext()) {
            String var3 = (String)var2.next();
            var1.println(var3);
         }

         var1.close();
      } catch (Exception var4) {
         a.warning("Failed to save ip ban list: " + var4);
      }

   }

   public void e(String var1) {
      this.h.add(var1.toLowerCase());
      this.l();
   }

   public void f(String var1) {
      this.h.remove(var1.toLowerCase());
      this.l();
   }

   private void k() {
      try {
         this.h.clear();
         BufferedReader var1 = new BufferedReader(new FileReader(this.l));
         String var2 = "";

         while((var2 = var1.readLine()) != null) {
            this.h.add(var2.trim().toLowerCase());
         }

         var1.close();
      } catch (Exception var3) {
         a.warning("Failed to load ip ban list: " + var3);
      }

   }

   private void l() {
      try {
         PrintWriter var1 = new PrintWriter(new FileWriter(this.l, false));
         Iterator var2 = this.h.iterator();

         while(var2.hasNext()) {
            String var3 = (String)var2.next();
            var1.println(var3);
         }

         var1.close();
      } catch (Exception var4) {
         a.warning("Failed to save ip ban list: " + var4);
      }

   }

   private void m() {
      try {
         this.i.clear();
         BufferedReader var1 = new BufferedReader(new FileReader(this.m));
         String var2 = "";

         while((var2 = var1.readLine()) != null) {
            this.i.add(var2.trim().toLowerCase());
         }

         var1.close();
      } catch (Exception var3) {
         a.warning("Failed to load white-list: " + var3);
      }

   }

   private void n() {
      try {
         PrintWriter var1 = new PrintWriter(new FileWriter(this.m, false));
         Iterator var2 = this.i.iterator();

         while(var2.hasNext()) {
            String var3 = (String)var2.next();
            var1.println(var3);
         }

         var1.close();
      } catch (Exception var4) {
         a.warning("Failed to save white-list: " + var4);
      }

   }

   public boolean g(String var1) {
      var1 = var1.trim().toLowerCase();
      return !this.o || this.h.contains(var1) || this.i.contains(var1);
   }

   public boolean h(String var1) {
      return this.h.contains(var1.trim().toLowerCase());
   }

   public OEntityPlayerMP i(String var1) {
      for(int var2 = 0; var2 < this.b.size(); ++var2) {
         OEntityPlayerMP var3 = (OEntityPlayerMP)this.b.get(var2);
         if(var3.r.equalsIgnoreCase(var1)) {
            return var3;
         }
      }

      return null;
   }

   public void a(String var1, String var2) {
      OEntityPlayerMP var3 = this.i(var1);
      if(var3 != null) {
         var3.a.b((OPacket)(new OPacket3Chat(var2)));
      }

   }

   public void a(double var1, double var3, double var5, double var7, int var9, OPacket var10) {
      this.a((OEntityPlayer)null, var1, var3, var5, var7, var9, var10);
   }

   public void a(OEntityPlayer var1, double var2, double var4, double var6, double var8, int var10, OPacket var11) {
      for(int var12 = 0; var12 < this.b.size(); ++var12) {
         OEntityPlayerMP var13 = (OEntityPlayerMP)this.b.get(var12);
         if(var13 != var1 && var13.s == var10) {
            double var14 = var2 - var13.aP;
            double var16 = var4 - var13.aQ;
            double var18 = var6 - var13.aR;
            if(var14 * var14 + var16 * var16 + var18 * var18 < var8 * var8) {
               var13.a.b(var11);
            }
         }
      }

   }

   public void j(String var1) {
      OPacket3Chat var2 = new OPacket3Chat(var1);

      for(int var3 = 0; var3 < this.b.size(); ++var3) {
         OEntityPlayerMP var4 = (OEntityPlayerMP)this.b.get(var3);
         if(this.h(var4.r)) {
            var4.a.b((OPacket)var2);
         }
      }

   }

   public boolean a(String var1, OPacket var2) {
      OEntityPlayerMP var3 = this.i(var1);
      if(var3 != null) {
         var3.a.b(var2);
         return true;
      } else {
         return false;
      }
   }

   public void d() {
      for(int var1 = 0; var1 < this.b.size(); ++var1) {
         this.n.a((OEntityPlayer)this.b.get(var1));
      }

   }

   public void a(int var1, int var2, int var3, OTileEntity var4) {
   }

   public void k(String var1) {
      this.i.add(var1);
      this.n();
   }

   public void l(String var1) {
      this.i.remove(var1);
      this.n();
   }

   public Set e() {
      return this.i;
   }

   public void f() {
      this.m();
   }

   public void a(OEntityPlayerMP var1, OWorldServer var2) {
      var1.a.b((OPacket)(new OPacket4UpdateTime(var2.m())));
      if(var2.v()) {
         var1.a.b((OPacket)(new OPacket70Bed(1)));
      }

   }

   public void g(OEntityPlayerMP var1) {
      var1.a(var1.j);
      var1.C();
   }

   /**
    * Returns the list of bans
    *
    * @return bans
    */
   public String getBans() {
      StringBuilder builder = new StringBuilder();
      int l = 0;
      for(Object o : f) {
         if(l > 0)
            builder.append(", ");
         builder.append(o);
         l++;
      }
      return builder.toString();
   }

   /**
    * Returns the list of IP bans
    *
    * @return ip bans
    */
   public String getIpBans() {
      StringBuilder builder = new StringBuilder();
      int l = 0;
      for(Object o : g) {
         if(l > 0)
            builder.append(", ");
         builder.append(o);
         l++;
      }
      return builder.toString();
   }

}
