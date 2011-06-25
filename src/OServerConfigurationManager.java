import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import net.minecraft.server.MinecraftServer;

public class OServerConfigurationManager {

    public static Logger         a = Logger.getLogger("Minecraft");
    // CanaryMod set list to contain <OEntityPlayerMP> objects.
    public List<OEntityPlayerMP> b = new ArrayList();
    private MinecraftServer      c;
    private OPlayerManager[]     d = new OPlayerManager[2];
    private int                  e;
    // CanaryMod set these to Set<String> to remove errors and warnings.
    private Set<String>          f = new HashSet();
    private Set<String>          g = new HashSet();
    private Set<String>          h = new HashSet();
    private Set<String>          i = new HashSet();
    private File                 j;
    private File                 k;
    private File                 l;
    private File                 m;
    private OIPlayerFileData     n;
    private boolean              o;

    public OServerConfigurationManager(MinecraftServer paramMinecraftServer) {
        etc.setServer(paramMinecraftServer);
        etc.getInstance().loadData();
        a.info("Note: your current classpath is: " + System.getProperty("java.class.path", "*UNKNOWN*"));
        if (!etc.getInstance().getTainted())
            a.info("Canary Build: " + etc.getInstance().getVersion());
        else
            a.info("CanaryMod Build Information: " + etc.getInstance().getVersionStr());
        c = paramMinecraftServer;
        j = paramMinecraftServer.a("banned-players.txt");
        k = paramMinecraftServer.a("banned-ips.txt");
        l = paramMinecraftServer.a("ops.txt");
        m = paramMinecraftServer.a("white-list.txt");
        e = paramMinecraftServer.d.a("max-players", 20);
        o = paramMinecraftServer.d.a("white-list", false);
        int i1 = paramMinecraftServer.d.a("view-distance", 10);
        this.d[0] = new OPlayerManager(paramMinecraftServer, 0, i1);
        this.d[1] = new OPlayerManager(paramMinecraftServer, -1, i1);
        g();
        i();
        k();
        m();
        h();
        j();
        l();
        n();
    }

    public void a(OWorldServer[] paramArrayOfOWorldServer) {
        n = paramArrayOfOWorldServer[0].p().d();
    }

    public void a(OEntityPlayerMP paramOEntityPlayerMP) {
        this.d[0].b(paramOEntityPlayerMP);
        this.d[1].b(paramOEntityPlayerMP);
        a(paramOEntityPlayerMP.s).a(paramOEntityPlayerMP);

        OWorldServer localOWorldServer = this.c.a(paramOEntityPlayerMP.s);
        localOWorldServer.C.c((int)paramOEntityPlayerMP.aP >> 4, (int)paramOEntityPlayerMP.aR >> 4);
      }

    public int a() {
        return d[0].c();
    }
    
    private OPlayerManager a(int paramInt) {
        return paramInt == -1 ? this.d[1] : this.d[0];
    }

    public void b(OEntityPlayerMP paramOEntityPlayerMP) {
        this.n.b(paramOEntityPlayerMP);
    }
    
    public void c(OEntityPlayerMP paramOEntityPlayerMP) {
        b.add(paramOEntityPlayerMP);
        OWorldServer localOWorldServer = c.a(paramOEntityPlayerMP.s);
        localOWorldServer.C.c((int)paramOEntityPlayerMP.aP >> 4, (int)paramOEntityPlayerMP.aR >> 4);

        while (!localOWorldServer.a(paramOEntityPlayerMP, paramOEntityPlayerMP.aZ).isEmpty()) {
          paramOEntityPlayerMP.a(paramOEntityPlayerMP.aP, paramOEntityPlayerMP.aQ + 1.0D, paramOEntityPlayerMP.aR);
        }
        localOWorldServer.b(paramOEntityPlayerMP);
        a(paramOEntityPlayerMP.s).a(paramOEntityPlayerMP);
    	// CanaryMod: Handle login (send MOTD and call hook)
    	String[] motd = etc.getInstance().getMotd();
    	if (!(motd.length == 1 && motd[0].equals("")))
    		for (String str : motd)
    	        paramOEntityPlayerMP.a.b(new OPacket3Chat(str));
    	etc.getLoader().callHook(PluginLoader.Hook.LOGIN, paramOEntityPlayerMP.getPlayer());
    }

    public void d(OEntityPlayerMP oemp) {
        a(oemp.s).c(oemp);
    }

    public void e(OEntityPlayerMP paramOEntityPlayerMP) {
        n.a(paramOEntityPlayerMP);
        c.a(paramOEntityPlayerMP.s).e(paramOEntityPlayerMP);
        b.remove(paramOEntityPlayerMP);
        a(paramOEntityPlayerMP.s).b(paramOEntityPlayerMP);
    }

    public OEntityPlayerMP a(ONetLoginHandler paramONetLoginHandler, String paramString1) {
        // TODO: add reasons, expire tempbans
        if (!etc.getLoader().isLoaded())
            paramONetLoginHandler.a("The server is not finished loading yet!");

        if (f.contains(paramString1.trim().toLowerCase())) {
            paramONetLoginHandler.a("You are banned from this server!");
            return null;
        }
        // CanaryMod: whole section below is modified to handle whitelists etc
        OEntityPlayerMP temp = new OEntityPlayerMP(c, c.a(0), paramString1, new OItemInWorldManager(c.a(0)));
        Player player = temp.getPlayer();

        String str = paramONetLoginHandler.b.c().toString();
        str = str.substring(str.indexOf("/") + 1);
        str = str.substring(0, str.indexOf(":"));
        if (g.contains(str)) {
            paramONetLoginHandler.a("Your IP address is banned from this server!");
            return null;
        }

        if (!g(paramString1) || (etc.getInstance().isWhitelistEnabled() && !(etc.getDataSource().isUserOnWhitelist(paramString1) || player.isAdmin()))) {
            paramONetLoginHandler.a(etc.getInstance().getWhitelistMessage());
            return null;
        } else if (b.size() >= e && (!etc.getInstance().isReservelistEnabled() || !etc.getDataSource().isUserOnReserveList(paramString1))) {
            paramONetLoginHandler.a("The server is full!");
            return null;
        }

        if (!player.getIps()[0].equals("")) {
            boolean kick = true;
            for (int i = 0; i < player.getIps().length; i++)
                if (!player.getIps()[i].equals("") && str.equals(player.getIps()[i]))
                    kick = false;
            if (kick) {
                paramONetLoginHandler.a("IP doesn't match specified IP.");
                return null;
            }
        }

        for (int i1 = 0; i1 < b.size(); i1++) {
            OEntityPlayerMP localOEntityPlayerMP = b.get(i1);
            if (localOEntityPlayerMP.r.equalsIgnoreCase(paramString1))
                localOEntityPlayerMP.a.a("You logged in from another location");
        }

        // CanaryMod: user passed basic login check, inform plugins.
        Object obj = etc.getLoader().callHook(PluginLoader.Hook.LOGINCHECK, paramString1);
        if (obj instanceof String) {
            String result = (String) obj;
            if (result != null && !result.equals("")) {
                paramONetLoginHandler.a(result);
                return null;
            }
        }
        return temp;
    }

    /**
     * Returns the list of bans
     * 
     * @return
     */
    public String getBans() {
        StringBuilder builder = new StringBuilder();
        int l = 0;
        for (Object o : f) {
            if (l > 0)
                builder.append(", ");
            builder.append(o);
            l++;
        }
        return builder.toString();
    }

    /**
     * Returns the list of IP bans
     * 
     * @return
     */
    public String getIpBans() {
        StringBuilder builder = new StringBuilder();
        int l = 0;
        for (Object o : g) {
            if (l > 0)
                builder.append(", ");
            builder.append(o);
            l++;
        }
        return builder.toString();
    }

    public OEntityPlayerMP a(OEntityPlayerMP paramOEntityPlayerMP, int var2) {
        c.b(paramOEntityPlayerMP.s).a(paramOEntityPlayerMP);
        c.b(paramOEntityPlayerMP.s).b(paramOEntityPlayerMP);
        a(paramOEntityPlayerMP.s).b(paramOEntityPlayerMP);
        b.remove(paramOEntityPlayerMP);
        c.a(paramOEntityPlayerMP.s).f(paramOEntityPlayerMP);

        OChunkCoordinates localOChunkCoordinates1 = paramOEntityPlayerMP.M();
        paramOEntityPlayerMP.s = var2;
        OEntityPlayerMP localOEntityPlayerMP = new OEntityPlayerMP(c, c.a(paramOEntityPlayerMP.s), paramOEntityPlayerMP.r, new OItemInWorldManager(c.a(paramOEntityPlayerMP.s)));
        localOEntityPlayerMP.aG = paramOEntityPlayerMP.aG;
        localOEntityPlayerMP.a = paramOEntityPlayerMP.a;

        OWorldServer ows = c.a(paramOEntityPlayerMP.s);
        if (localOChunkCoordinates1 != null) {
            OChunkCoordinates localOChunkCoordinates2 = OEntityPlayer.a(c.a(paramOEntityPlayerMP.s), localOChunkCoordinates1);
            if (localOChunkCoordinates2 != null) {
                localOEntityPlayerMP.c(localOChunkCoordinates2.a + 0.5F, localOChunkCoordinates2.b + 0.1F, localOChunkCoordinates2.c + 0.5F, 0.0F, 0.0F);
                localOEntityPlayerMP.a(localOChunkCoordinates1);
            } else
                localOEntityPlayerMP.a.b(new OPacket70Bed(0));

        }

        ows.C.c((int) localOEntityPlayerMP.aP >> 4, (int) localOEntityPlayerMP.aR >> 4);

        while (!ows.a(localOEntityPlayerMP, localOEntityPlayerMP.aZ).isEmpty())
            localOEntityPlayerMP.a(localOEntityPlayerMP.aP, localOEntityPlayerMP.aQ + 1.0D, localOEntityPlayerMP.aR);

        localOEntityPlayerMP.a.b(new OPacket9Respawn((byte) localOEntityPlayerMP.s));
        localOEntityPlayerMP.a.a(localOEntityPlayerMP.aP, localOEntityPlayerMP.aQ, localOEntityPlayerMP.aR, localOEntityPlayerMP.aV, localOEntityPlayerMP.aW);

        a(localOEntityPlayerMP, ows);
        a(localOEntityPlayerMP.s).a(localOEntityPlayerMP);
        ows.b(localOEntityPlayerMP);
        b.add(localOEntityPlayerMP);

        localOEntityPlayerMP.o();
        localOEntityPlayerMP.w();
        return localOEntityPlayerMP;
    }

    public void f(OEntityPlayerMP var1) {
        OWorldServer var2 = this.c.a(var1.s);
        boolean var3 = false;
        byte var11;
        if (var1.s == -1)
            var11 = 0;
        else
            var11 = -1;

        var1.s = var11;
        OWorldServer var4 = this.c.a(var1.s);
        var1.a.b((OPacket) (new OPacket9Respawn((byte) var1.s)));
        var2.f(var1);
        var1.bh = false;
        double var5 = var1.aP;
        double var7 = var1.aR;
        double var9 = 8.0D;
        if (var1.s == -1) {
            var5 /= var9;
            var7 /= var9;
            var1.c(var5, var1.aQ, var7, var1.aV, var1.aW);
            if (var1.S())
                var2.a(var1, false);
        } else {
            var5 *= var9;
            var7 *= var9;
            var1.c(var5, var1.aQ, var7, var1.aV, var1.aW);
            if (var1.S())
                var2.a(var1, false);
        }

        if (var1.S()) {
            var4.b(var1);
            var1.c(var5, var1.aQ, var7, var1.aV, var1.aW);
            var4.a(var1, false);
            var4.C.a = true;
            (new OTeleporter()).a(var4, var1);
            var4.C.a = false;
        }

        this.a(var1);
        var1.a.a(var1.aP, var1.aQ, var1.aR, var1.aV, var1.aW);
        var1.a((OWorld) var4);
        this.a(var1, var4);
        this.g(var1);
    }

    public void b() {
        for (OPlayerManager opm: d)
            opm.b();
    }

    public void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        a(paramInt4).a(paramInt1, paramInt2, paramInt3);
    }

    public void a(OPacket paramOPacket) {
        for (int i1 = 0; i1 < b.size(); i1++) {
            OEntityPlayerMP localOEntityPlayerMP = b.get(i1);
            localOEntityPlayerMP.a.b(paramOPacket);
        }
    }

    public void a(OPacket paramOPacket, int world) {
        for (int i1 = 0; i1 < b.size(); i1++) {
            OEntityPlayerMP localOEntityPlayerMP = b.get(i1);
            if (localOEntityPlayerMP.s == world)
                localOEntityPlayerMP.a.b(paramOPacket);
        }
    }

    public String c() {
        String str = "";
        for (int i1 = 0; i1 < b.size(); i1++) {
            if (i1 > 0)
                str = str + ", ";
            str = str + b.get(i1).r;
        }
        return str;
    }

    public void a(String paramString) {
        f.add(paramString.toLowerCase());
        h();
    }

    public void b(String paramString) {
        f.remove(paramString.toLowerCase());
        h();
    }

    private void g() {
        try {
            f.clear();
            BufferedReader localBufferedReader = new BufferedReader(new FileReader(j));
            String str = "";
            while ((str = localBufferedReader.readLine()) != null)
                f.add(str.trim().toLowerCase());
            localBufferedReader.close();
        } catch (Exception localException) {
            a.warning("Failed to load ban list: " + localException);
        }
    }

    private void h() {
        try {
            PrintWriter localPrintWriter = new PrintWriter(new FileWriter(j, false));
            for (String str : f)
                localPrintWriter.println(str);
            localPrintWriter.close();
        } catch (Exception localException) {
            a.warning("Failed to save ban list: " + localException);
        }
    }

    public void c(String paramString) {
        g.add(paramString.toLowerCase());
        j();
    }

    public void d(String paramString) {
        g.remove(paramString.toLowerCase());
        j();
    }

    private void i() {
        try {
            g.clear();
            BufferedReader localBufferedReader = new BufferedReader(new FileReader(k));
            String str = "";
            while ((str = localBufferedReader.readLine()) != null)
                g.add(str.trim().toLowerCase());
            localBufferedReader.close();
        } catch (Exception localException) {
            a.warning("Failed to load ip ban list: " + localException);
        }
    }

    private void j() {
        try {
            PrintWriter localPrintWriter = new PrintWriter(new FileWriter(k, false));
            for (String str : g)
                localPrintWriter.println(str);
            localPrintWriter.close();
        } catch (Exception localException) {
            a.warning("Failed to save ip ban list: " + localException);
        }
    }

    public void e(String paramString) {
        h.add(paramString.toLowerCase());
        l();
    }

    public void f(String paramString) {
        h.remove(paramString.toLowerCase());
        l();
    }

    private void k() {
        try {
            h.clear();
            BufferedReader localBufferedReader = new BufferedReader(new FileReader(l));
            String str = "";
            while ((str = localBufferedReader.readLine()) != null)
                h.add(str.trim().toLowerCase());
            localBufferedReader.close();
        } catch (Exception localException) {
            a.warning("Failed to load ip ban list: " + localException);
        }
    }

    private void l() {
        try {
            PrintWriter localPrintWriter = new PrintWriter(new FileWriter(l, false));
            for (String str : h)
                localPrintWriter.println(str);
            localPrintWriter.close();
        } catch (Exception localException) {
            a.warning("Failed to save ip ban list: " + localException);
        }
    }

    private void m() {
        try {
            i.clear();
            BufferedReader localBufferedReader = new BufferedReader(new FileReader(m));
            String str = "";
            while ((str = localBufferedReader.readLine()) != null)
                i.add(str.trim().toLowerCase());
            localBufferedReader.close();
        } catch (Exception localException) {
            a.warning("Failed to load white-list: " + localException);
        }
    }

    private void n() {
        try {
            PrintWriter localPrintWriter = new PrintWriter(new FileWriter(m, false));
            for (String str : i)
                localPrintWriter.println(str);
            localPrintWriter.close();
        } catch (Exception localException) {
            a.warning("Failed to save white-list: " + localException);
        }
    }

    public boolean g(String paramString) {
        paramString = paramString.trim().toLowerCase();
        return (!o) || (h.contains(paramString)) || (i.contains(paramString));
    }

    public boolean h(String paramString) {
        return h.contains(paramString.trim().toLowerCase());
    }

    public OEntityPlayerMP i(String paramString) {
        for (int i1 = 0; i1 < b.size(); i1++) {
            OEntityPlayerMP localOEntityPlayerMP = b.get(i1);
            if (localOEntityPlayerMP.r.equalsIgnoreCase(paramString))
                return localOEntityPlayerMP;
        }
        return null;
    }

    public void a(String paramString1, String paramString2) {
        OEntityPlayerMP localOEntityPlayerMP = i(paramString1);
        if (localOEntityPlayerMP != null)
            localOEntityPlayerMP.a.b(new OPacket3Chat(paramString2));
    }

    public void a(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, int paramInt, OPacket paramOPacket) {
        a((OEntityPlayer) null, paramDouble1, paramDouble2, paramDouble3, paramDouble4, paramInt, paramOPacket);
    }

    public void a(OEntityPlayer oep, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, int paramInt, OPacket paramOPacket) {
        for (int i1 = 0; i1 < b.size(); i1++) {
            OEntityPlayerMP localOEntityPlayerMP = b.get(i1);
            if (localOEntityPlayerMP != oep && localOEntityPlayerMP.s == paramInt) {
                double d1 = paramDouble1 - localOEntityPlayerMP.aP;
                double d2 = paramDouble2 - localOEntityPlayerMP.aQ;
                double d3 = paramDouble3 - localOEntityPlayerMP.aR;
                if (d1 * d1 + d2 * d2 + d3 * d3 < paramDouble4 * paramDouble4)
                    localOEntityPlayerMP.a.b(paramOPacket);
            }
        }
    }

    public void j(String paramString) {
        OPacket3Chat localOPacket3Chat = new OPacket3Chat(paramString);
        for (int i1 = 0; i1 < b.size(); i1++) {
            OEntityPlayerMP localOEntityPlayerMP = b.get(i1);
            if (h(localOEntityPlayerMP.r))
                localOEntityPlayerMP.a.b(localOPacket3Chat);
        }
    }

    public boolean a(String paramString, OPacket paramOPacket) {
        OEntityPlayerMP localOEntityPlayerMP = i(paramString);
        if (localOEntityPlayerMP != null) {
            localOEntityPlayerMP.a.b(paramOPacket);
            return true;
        }
        return false;
    }

    public void d() {
        for (int i1 = 0; i1 < b.size(); i1++)
            n.a(b.get(i1));
    }

    public void a(int paramInt1, int paramInt2, int paramInt3, OTileEntity paramOTileEntity) {
    }

    public void k(String paramString) {
        i.add(paramString);
        n();
    }

    public void l(String paramString) {
        i.remove(paramString);
        n();
    }

    public Set e() {
        return i;
    }

    public void f() {
        m();
    }

    public void a(OEntityPlayerMP var1, OWorldServer var2) {
        var1.a.b((OPacket) (new OPacket4UpdateTime(var2.m())));
        if (var2.v())
            var1.a.b((OPacket) (new OPacket70Bed(1)));

    }

    public void g(OEntityPlayerMP var1) {
        var1.a(var1.j);
        var1.B();
    }
}
