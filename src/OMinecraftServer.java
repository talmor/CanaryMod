import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class OMinecraftServer implements Runnable, OICommandListener {

    public static Logger a = Logger.getLogger("Minecraft");
    public static HashMap b = new HashMap();
    public ONetworkListenThread c;
    public OPropertyManager d;
    public OWorldServer[] e;
    public OServerConfigurationManager f;
    private OConsoleCommandHandler p;
    private boolean q = true;
    public boolean g = false;
    int h = 0;
    public String i;
    public int j;
    private List r = new ArrayList();
    private List s = Collections.synchronizedList(new ArrayList());
    public OEntityTracker[] k = new OEntityTracker[2];
    public boolean l;
    public boolean m;
    public boolean n;
    public boolean o;

    public OMinecraftServer() {
        new OThreadSleepForever(this);
    }

    private boolean c() {
        this.p = new OConsoleCommandHandler(this);
        OThreadCommandReader var1 = new OThreadCommandReader(this);

        var1.setDaemon(true);
        var1.start();
        OConsoleLogManager.a();
        a.info("Starting minecraft server version Beta 1.7.3");
        if (Runtime.getRuntime().maxMemory() / 1024L / 1024L < 512L) {
            a.warning("**** NOT ENOUGH RAM!");
            a.warning("To start the server with more ram, launch it as \"java -Xmx1024M -Xms1024M -jar minecraft_server.jar\"");
        }

        a.info("Loading properties");
        this.d = new OPropertyManager(new File("server.properties"));
        String var2 = this.d.a("server-ip", "");

        this.l = this.d.a("online-mode", true);
        this.m = this.d.a("spawn-animals", true);
        this.n = this.d.a("pvp", true);
        this.o = this.d.a("allow-flight", false);
        InetAddress var3 = null;

        

        int var4 = this.d.a("server-port", 25565);

        a.info("Starting Minecraft server on " + (var2.length() == 0 ? "*" : var2) + ":" + var4);

        try {
            if (var2.length() > 0) {
                var3 = InetAddress.getByName(var2);
            }
            this.c = new ONetworkListenThread(this, var3, var4);
        } catch (IOException var14) {
            a.warning("**** FAILED TO BIND TO PORT!");
            a.log(Level.WARNING, "The exception was: " + var14.toString());
            a.warning("Perhaps a server is already running on that port?");
            return false;
        }

        if (!this.l) {
            a.warning("**** SERVER IS RUNNING IN OFFLINE/INSECURE MODE!");
            a.warning("The server will make no attempt to authenticate usernames. Beware.");
            a.warning("While this makes the game possible to play without internet access, it also opens up the ability for hackers to connect with any username they choose.");
            a.warning("To change this, set \"online-mode\" to \"true\" in the server.settings file.");
        }

        this.f = new OServerConfigurationManager(this);
        this.k[0] = new OEntityTracker(this, 0);
        this.k[1] = new OEntityTracker(this, -1);
        long var6 = System.nanoTime();
        String var8 = this.d.a("level-name", "world");
        String var9 = this.d.a("level-seed", "");
        long var10 = (new Random()).nextLong();

        if (var9.length() > 0) {
            try {
                var10 = Long.parseLong(var9);
            } catch (NumberFormatException var13) {
                var10 = (long) var9.hashCode();
            }
        }

        a.info("Preparing level \"" + var8 + "\"");
        this.a(new OSaveConverterMcRegion(new File(".")), var8, var10);
        a.info("Done (" + (System.nanoTime() - var6) + "ns)! For help, type \"help\" or \"?\"");
        return true;
    }

    private void a(OISaveFormat var1, String var2, long var3) {
        if (var1.a(var2)) {
            a.info("Converting map!");
            var1.a(var2, new OConvertProgressUpdater(this));
        }

        this.e = new OWorldServer[2];
        OSaveOldDir var5 = new OSaveOldDir(new File("."), var2, true);

        for (int var6 = 0; var6 < this.e.length; ++var6) {
            if (var6 == 0) {
                this.e[var6] = new OWorldServer(this, var5, var2, var6 == 0 ? 0 : -1, var3);
            } else {
                this.e[var6] = new OWorldServerMulti(this, var5, var2, var6 == 0 ? 0 : -1, var3, this.e[0]);
            }

            this.e[var6].a(new OWorldManager(this, this.e[var6]));
            this.e[var6].q = this.d.a("spawn-monsters", true) ? 1 : 0;
            this.e[var6].a(this.d.a("spawn-monsters", true), this.m);
            this.f.a(this.e);
        }

        short var18 = 196;
        long var7 = System.currentTimeMillis();

        for (int var9 = 0; var9 < this.e.length; ++var9) {
            a.info("Preparing start region for level " + var9);
            if (var9 == 0 || this.d.a("allow-nether", true)) {
                OWorldServer var10 = this.e[var9];
                OChunkCoordinates var11 = var10.n();

                for (int var12 = -var18; var12 <= var18 && this.q; var12 += 16) {
                    for (int var13 = -var18; var13 <= var18 && this.q; var13 += 16) {
                        long var14 = System.currentTimeMillis();

                        if (var14 < var7) {
                            var7 = var14;
                        }

                        if (var14 > var7 + 1000L) {
                            int var16 = (var18 * 2 + 1) * (var18 * 2 + 1);
                            int var17 = (var12 + var18) * (var18 * 2 + 1) + var13 + 1;

                            this.a("Preparing spawn area", var17 * 100 / var16);
                            var7 = var14;
                        }

                        var10.C.c(var11.a + var12 >> 4, var11.c + var13 >> 4);

                        while (var10.f() && this.q) {
                            ;
                        }
                    }
                }
            }
        }

        this.e();
    }

    private void a(String var1, int var2) {
        this.i = var1;
        this.j = var2;
        a.info(var1 + ": " + var2 + "%");
    }

    private void e() {
        this.i = null;
        this.j = 0;
    }

    private void f() {
        a.info("Saving chunks");

        for (int var1 = 0; var1 < this.e.length; ++var1) {
            OWorldServer var2 = this.e[var1];

            var2.a(true, (OIProgressUpdate) null);
            var2.w();
        }

    }

    private void g() {
        a.info("Stopping server");
        if (this.f != null) {
            this.f.d();
        }

        for (int var1 = 0; var1 < this.e.length; ++var1) {
            OWorldServer var2 = this.e[var1];

            if (var2 != null) {
                this.f();
            }
        }

    }

    public void a() {
        this.q = false;
    }

    public void run() {
        try {
            if (this.c()) {
                long var1 = System.currentTimeMillis();

                for (long var3 = 0L; this.q; Thread.sleep(1L)) {
                    long var5 = System.currentTimeMillis();
                    long var7 = var5 - var1;

                    if (var7 > 2000L) {
                        a.warning("Can\'t keep up! Did the system time change, or is the server overloaded?");
                        var7 = 2000L;
                    }

                    if (var7 < 0L) {
                        a.warning("Time ran backwards! Did the system time change?");
                        var7 = 0L;
                    }

                    var3 += var7;
                    var1 = var5;
                    if (this.e[0].t()) {
                        this.h();
                        var3 = 0L;
                    } else {
                        while (var3 > 50L) {
                            var3 -= 50L;
                            this.h();
                        }
                    }
                }
            } else {
                while (this.q) {
                    this.b();

                    try {
                        Thread.sleep(10L);
                    } catch (InterruptedException var59) {
                        var59.printStackTrace();
                    }
                }
            }
        } catch (Throwable var60) {
            var60.printStackTrace();
            a.log(Level.SEVERE, "Unexpected exception", var60);

            while (this.q) {
                this.b();

                try {
                    Thread.sleep(10L);
                } catch (InterruptedException var58) {
                    var58.printStackTrace();
                }
            }
        } finally {
            try {
                this.g();
                this.g = true;
            } catch (Throwable var56) {
                var56.printStackTrace();
            } finally {
                System.exit(0);
            }

        }

    }

    private void h() {
        ArrayList var1 = new ArrayList();
        Iterator var2 = b.keySet().iterator();

        while (var2.hasNext()) {
            String var3 = (String) var2.next();
            int var4 = ((Integer) b.get(var3)).intValue();

            if (var4 > 0) {
                b.put(var3, Integer.valueOf(var4 - 1));
            } else {
                var1.add(var3);
            }
        }

        int var6;

        for (var6 = 0; var6 < var1.size(); ++var6) {
            b.remove(var1.get(var6));
        }

        OAxisAlignedBB.a();
        OVec3D.a();
        ++this.h;

        for (var6 = 0; var6 < this.e.length; ++var6) {
            if (var6 == 0 || this.d.a("allow-nether", true)) {
                OWorldServer var7 = this.e[var6];

                if (this.h % 20 == 0) {
                    this.f.a((OPacket) (new OPacket4UpdateTime(var7.m())), var7.t.g);
                }

                var7.h();

                while (var7.f()) {
                    ;
                }

                var7.e();
            }
        }

        this.c.a();
        this.f.b();

        for (var6 = 0; var6 < this.k.length; ++var6) {
            this.k[var6].a();
        }

        for (var6 = 0; var6 < this.r.size(); ++var6) {
            ((OIUpdatePlayerListBox) this.r.get(var6)).a();
        }

        try {
            this.b();
        } catch (Exception var5) {
            a.log(Level.WARNING, "Unexpected exception while parsing console command", var5);
        }

    }

    public void a(String var1, OICommandListener var2) {
        this.s.add(new OServerCommand(var1, var2));
    }

    public void b() {
        while (this.s.size() > 0) {
            OServerCommand var1 = (OServerCommand) this.s.remove(0);

            this.p.a(var1);
        }

    }

    public void a(OIUpdatePlayerListBox var1) {
        this.r.add(var1);
    }

    public static void main(String[] var0) {
        OStatList.a();

        try {
            OMinecraftServer var1 = new OMinecraftServer();

            if (!GraphicsEnvironment.isHeadless() && (var0.length <= 0 || !var0[0].equals("nogui"))) {
                OServerGUI.a(var1);
            }

            (new OThreadServerApplication("Server thread", var1)).start();
        } catch (Exception var2) {
            a.log(Level.SEVERE, "Failed to start the minecraft server", var2);
        }

    }

    public File a(String var1) {
        return new File(var1);
    }

    public void b(String var1) {
        a.info(var1);
    }

    public void c(String var1) {
        a.warning(var1);
    }

    public String d() {
        return "CONSOLE";
    }

    public OWorldServer a(int var1) {
        return var1 == -1 ? this.e[1] : this.e[0];
    }

    public OEntityTracker b(int var1) {
        return var1 == -1 ? this.k[1] : this.k[0];
    }

    // $FF: synthetic method
    public static boolean a(OMinecraftServer var0) {
        return var0.q;
    }

}
