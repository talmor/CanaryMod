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
   private int p = 0;


   public OServerConfigurationManager(MinecraftServer var1) {
      // CanaryMod: initialize
      etc.setServer(var1);
      etc.getInstance().loadData();
      a.info("Note: your current classpath is: " + System.getProperty("java.class.path", "*UNKNOWN*"));
      if (!etc.getInstance().getTainted())
         a.info("Crow Test Build 3.2." + etc.getInstance().getVersion());
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
      this.i();
      this.k();
      this.m();
      this.o();
      this.j();
      this.l();
      this.n();
      this.p();
   }

   public void a(OWorldServer[] var1) {
      this.n = var1[0].o().d();
   }

   public void a(OEntityPlayerMP var1) {
      this.d[0].b(var1);
      this.d[1].b(var1);
      this.a(var1.v).a(var1);
      OWorldServer var2 = this.c.a(var1.v);
      var2.M.c((int)var1.bf >> 4, (int)var1.bh >> 4);
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
      this.a((OPacket)(new OPacket201PlayerInfo(var1.u, true, 1000)));
      this.b.add(var1);
      OWorldServer var2 = this.c.a(var1.v);
      var2.M.c((int)var1.bf >> 4, (int)var1.bh >> 4);

      while(var2.a(var1, var1.bp).size() != 0) {
         var1.c(var1.bf, var1.bg + 1.0D, var1.bh);
      }

      var2.b(var1);
      this.a(var1.v).a(var1);

      for(int var3 = 0; var3 < this.b.size(); ++var3) {
         OEntityPlayerMP var4 = (OEntityPlayerMP)this.b.get(var3);
         var1.a.b((OPacket)(new OPacket201PlayerInfo(var4.u, true, var4.i)));
         
      }
      
      // CanaryMod: Handle login (send MOTD and call hook)
      etc.getInstance().getMotd(var1.getPlayer());
      etc.getLoader().callHook(PluginLoader.Hook.LOGIN, var1.getPlayer());

   }

   public void d(OEntityPlayerMP var1) {
      this.a(var1.v).c(var1);
   }

   public void e(OEntityPlayerMP var1) {
      this.n.a(var1);
      this.c.a(var1.v).e(var1);
      this.b.remove(var1);
      this.a(var1.v).b(var1);
      this.a((OPacket)(new OPacket201PlayerInfo(var1.u, false, 9999)));
   }

   public OEntityPlayerMP a(ONetLoginHandler var1, String var2) {
	   // TODO: add reasons, expire tempbans
	   if (!etc.getLoader().isLoaded())
		   var1.a("The server is not finished loading yet!");
	   // CanaryMod: whole section below is modified to handle whitelists etc
	   OEntityPlayerMP temp = new OEntityPlayerMP(c, c.a(0), var2, new OItemInWorldManager(c.a(0)));
	   Player player = temp.getPlayer();
      if(this.f.contains(var2.trim().toLowerCase())) {
         var1.a("You are banned from this server!");
         return null;
      } else if(!this.g(var2)) {
         var1.a("You are not white-listed on this server!");
         return null;
      } else {
         String var3 = var1.b.c().toString();
         var3 = var3.substring(var3.indexOf("/") + 1);
         var3 = var3.substring(0, var3.indexOf(":"));
         if(this.g.contains(var3)) {
            var1.a("Your IP address is banned from this server!");
            return null;
         } else if(this.b.size() >= this.e) {
            var1.a("The server is full!");
            return null;
         } else {
            for(int var4 = 0; var4 < this.b.size(); ++var4) {
               OEntityPlayerMP var5 = (OEntityPlayerMP)this.b.get(var4);
               if(var5.u.equalsIgnoreCase(var2)) {
                  var5.a.a("You logged in from another location");
               }
            }

            return new OEntityPlayerMP(this.c, this.c.a(0), var2, new OItemInWorldManager(this.c.a(0)));
         }
      }
      /*Object obj = etc.getLoader().callHook(PluginLoader.Hook.LOGINCHECK, var2);
      if (obj instanceof String) {
         String result = (String) obj;
         if (result != null && !result.equals("")) {
            var1.a(result);
            return null;
         }
      }
      return temp;*/
   }

   public OEntityPlayerMP a(OEntityPlayerMP var1, int var2) {
      this.c.b(var1.v).a(var1);
      this.c.b(var1.v).b(var1);
      this.a(var1.v).b(var1);
      this.b.remove(var1);
      this.c.a(var1.v).f(var1);
      OChunkCoordinates var3 = var1.R();
      var1.v = var2;
      OEntityPlayerMP var4 = new OEntityPlayerMP(this.c, this.c.a(var1.v), var1.u, new OItemInWorldManager(this.c.a(var1.v)));
      var4.aW = var1.aW;
      var4.a = var1.a;
      OWorldServer var5 = this.c.a(var1.v);
      var4.c.a(var1.c.a());
      var4.c.b(var5.p().n());
      if(var3 != null) {
         OChunkCoordinates var6 = OEntityPlayer.a(this.c.a(var1.v), var3);
         if(var6 != null) {
            var4.c((double)((float)var6.a + 0.5F), (double)((float)var6.b + 0.1F), (double)((float)var6.c + 0.5F), 0.0F, 0.0F);
            var4.a(var3);
         } else {
            var4.a.b((OPacket)(new OPacket70Bed(0, 0)));
         }
      }

      var5.M.c((int)var4.bf >> 4, (int)var4.bh >> 4);

      while(var5.a(var4, var4.bp).size() != 0) {
         var4.c(var4.bf, var4.bg + 1.0D, var4.bh);
      }

      ONetServerHandler var10000 = var4.a;
      byte var10003 = (byte)var4.v;
      byte var10004 = (byte)var4.bb.v;
      long var10005 = var4.bb.k();
      OPacket9Respawn var10001 = new OPacket9Respawn(var10003, var10004, var10005, 128, var4.c.a());
      var4.bb.getClass();
      var10000.b((OPacket)var10001);
      var4.a.a(var4.bf, var4.bg, var4.bh, var4.bl, var4.bm);
      this.a(var4, var5);
      this.a(var4.v).a(var4);
      var5.b(var4);
      this.b.add(var4);
      var4.o();
      var4.w();
      return var4;
   }
   
   // Canary: disable the creation of portals when switching worlds
   public void f(OEntityPlayerMP var1, boolean createPortal) {
	   OWorldServer var2 = this.c.a(var1.v);
	   boolean var3 = false;
	   byte var11;
	   if(var1.v == -1) {
		   var11 = 0;
	   } else {
		   var11 = -1;
	   }
	   
	   var1.v = var11;
	   OWorldServer var4 = this.c.a(var1.v);
	   var1.a.b((OPacket)(new OPacket9Respawn((byte)var1.v, (byte)var1.bb.v, var1.bb.k(), 128, var1.c.a())));
	   var2.f(var1);
	   var1.bx = false;
	   double var5 = var1.aP;
	   double var7 = var1.aR;
	   double var9 = 8.0D;
	   if(var1.v == -1) {
		   var5 /= var9;
		   var7 /= var9;
		   var1.c(var5, var1.aQ, var7, var1.aV, var1.aW);
		   if(var1.ac()) {
			   var2.a(var1, false);
		   }
	   } else {
		   var5 *= var9;
		   var7 *= var9;
		   var1.c(var5, var1.aQ, var7, var1.aV, var1.aW);
		   if(var1.ac()) {
			   var2.a(var1, false);
		   }
	   }
	   
	   if(var1.ac()) {
		   var4.b(var1);
		   var1.c(var5, var1.aQ, var7, var1.aV, var1.aW);
		   var4.a(var1, false);
		   var4.M.a = true;
		   if (createPortal) (new OTeleporter()).a(var4, var1);
		   var4.M.a = false;
	   }
	   
	   this.a(var1);
	   var1.a.a(var1.aP, var1.aQ, var1.aR, var1.aV, var1.aW);
	   var1.a((OWorld)var4);
	   this.a(var1, var4);
	   this.g(var1);
   	}
   
   public void f(OEntityPlayerMP var1) {
      OWorldServer var2 = this.c.a(var1.v);
      boolean var3 = false;
      byte var11;
      if(var1.v == -1) {
         var11 = 0;
      } else {
         var11 = -1;
      }

      var1.v = var11;
      OWorldServer var4 = this.c.a(var1.v);
      ONetServerHandler var10000 = var1.a;
      byte var10003 = (byte)var1.v;
      byte var10004 = (byte)var1.bb.v;
      long var10005 = var4.k();
      OPacket9Respawn var10001 = new OPacket9Respawn(var10003, var10004, var10005, 128, var1.c.a());
      var4.getClass();
      var10000.b((OPacket)var10001);
      var2.f(var1);
      var1.bx = false;
      double var5 = var1.bf;
      double var7 = var1.bh;
      double var9 = 8.0D;
      if(var1.v == -1) {
         var5 /= var9;
         var7 /= var9;
         var1.c(var5, var1.bg, var7, var1.bl, var1.bm);
         if(var1.ac()) {
            var2.a(var1, false);
         }
      } else {
         var5 *= var9;
         var7 *= var9;
         var1.c(var5, var1.bg, var7, var1.bl, var1.bm);
         if(var1.ac()) {
            var2.a(var1, false);
         }
      }

      if(var1.ac()) {
         var4.b(var1);
         var1.c(var5, var1.bg, var7, var1.bl, var1.bm);
         var4.a(var1, false);
         var4.M.a = true;
         (new OTeleporter()).a(var4, var1);
         var4.M.a = false;
      }

      this.a(var1);
      var1.a.a(var1.bf, var1.bg, var1.bh, var1.bl, var1.bm);
      var1.a((OWorld)var4);
      var1.c.a(var4);
      this.a(var1, var4);
      this.g(var1);
   }

   public void b() {
      int var1;
      if(this.p-- <= 0) {
         for(var1 = 0; var1 < this.b.size(); ++var1) {
            OEntityPlayerMP var2 = (OEntityPlayerMP)this.b.get(var1);
            this.a((OPacket)(new OPacket201PlayerInfo(var2.u, true, var2.i)));
         }
      }

      for(var1 = 0; var1 < this.d.length; ++var1) {
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
         if(var4.v == var2) {
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

         var1 = var1 + ((OEntityPlayerMP)this.b.get(var2)).u;
      }

      return var1;
   }

   public void a(String var1) {
      this.f.add(var1.toLowerCase());
      this.j();
   }

   public void b(String var1) {
      this.f.remove(var1.toLowerCase());
      this.j();
   }

   private void i() {
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

   private void j() {
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
      this.l();
   }

   public void d(String var1) {
      this.g.remove(var1.toLowerCase());
      this.l();
   }

   private void k() {
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

   private void l() {
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
      this.n();
   }

   public void f(String var1) {
      this.h.remove(var1.toLowerCase());
      this.n();
   }

   private void m() {
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

   private void n() {
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

   private void o() {
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

   private void p() {
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
         if(var3.u.equalsIgnoreCase(var1)) {
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
         if(var13 != var1 && var13.v == var10) {
            double var14 = var2 - var13.bf;
            double var16 = var4 - var13.bg;
            double var18 = var6 - var13.bh;
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
         if(this.h(var4.u)) {
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

   public void a(int var1, int var2, int var3, OTileEntity var4) {}

   public void k(String var1) {
      this.i.add(var1);
      this.p();
   }

   public void l(String var1) {
      this.i.remove(var1);
      this.p();
   }

   public Set e() {
      return this.i;
   }

   public void f() {
      this.o();
   }

   public void a(OEntityPlayerMP var1, OWorldServer var2) {
      var1.a.b((OPacket)(new OPacket4UpdateTime(var2.l())));
      if(var2.u()) {
         var1.a.b((OPacket)(new OPacket70Bed(1, 0)));
      }

   }

   public void g(OEntityPlayerMP var1) {
      var1.a(var1.k);
      var1.B();
   }

   public int g() {
      return this.b.size();
   }

   public int h() {
      return this.e;
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
