import java.io.IOException;
import java.net.Socket;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Logger;

public class ONetLoginHandler extends ONetHandler {

    public static Logger a = Logger.getLogger("Minecraft");
    private static Random d = new Random();
    public ONetworkManager b;
    public boolean c = false;
    private MinecraftServer e;
    private int f = 0;
    private String g = null;
    private OPacket1Login h = null;
    private String i = "";

    public ONetLoginHandler(MinecraftServer var1, Socket var2, String var3) throws IOException {
        super();
        this.e = var1;
        this.b = new ONetworkManager(var2, var3, this);
        this.b.f = 0;
    }

    public void a() {
        if (this.h != null) {
            this.b(this.h);
            this.h = null;
        }

        if (this.f++ == 600) {
            this.a("Took too long to log in");
        } else {
            this.b.b();
        }

    }

    public void a(String var1) {
        try {
            a.info("Disconnecting " + this.b() + ": " + var1);
            this.b.a((OPacket) (new OPacket255KickDisconnect(var1)));
            this.b.d();
            this.c = true;
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public void a(OPacket2Handshake var1) {
        if (this.e.l) {
            this.i = Long.toHexString(d.nextLong());
            this.b.a((OPacket) (new OPacket2Handshake(this.i)));
        } else {
            this.b.a((OPacket) (new OPacket2Handshake("-")));
        }

    }

    public void a(OPacket1Login var1) {
        this.g = var1.b;
        if (var1.a != 17) {
            if (var1.a > 17) {
                this.a("Outdated server!");
            } else {
                this.a("Outdated client!");
            }

        } else {
            if (!this.e.l) {
                this.b(var1);
            } else {
                (new OThreadLoginVerifier(this, var1)).start();
            }

        }
    }

    public void b(OPacket1Login var1) {
        OEntityPlayerMP var2 = this.e.f.a(this, var1.b);
        if (var2 != null) {
            this.e.f.b(var2);
            var2.a((OWorld) this.e.a(var2.v));
            var2.c.a((OWorldServer) var2.bb);
            a.info(this.b() + " logged in with entity id " + var2.aW + " at (" + var2.bf + ", " + var2.bg + ", " + var2.bh + ")");
            OWorldServer var3 = this.e.a(var2.v);
            OChunkCoordinates var4 = var3.m();
            var2.c.b(var3.p().n());
            ONetServerHandler var5 = new ONetServerHandler(this.e, this.b, var2);
            // CanaryMod - if seed is hidden send 0 instead.
            OPacket1Login var10001 = new OPacket1Login("", var2.aW, (etc.getInstance().getHideSeed() == true) ? 0 : var3.k(), var2.c.a(), (byte) var3.y.g, (byte) var3.v, (byte) -128, (byte) this.e.f.h());
            var5.b((OPacket) var10001);
            var5.b((OPacket) (new OPacket6SpawnPosition(var4.a, var4.b, var4.c)));
            this.e.f.a(var2, var3);
            // CanaryMod - onConnect Hook
            HookParametersConnect hookResult = new HookParametersConnect(String.format(Colors.Yellow+"%s joined the game.", var2.u),true);
            hookResult = (HookParametersConnect) etc.getLoader().callHook(PluginLoader.Hook.CONNECT, var2.getPlayer(),hookResult);
            if (!hookResult.isHidden()) 
                this.e.f.a((OPacket) (new OPacket3Chat(hookResult.getJoinMessage())));
            this.e.f.c(var2);
            var5.a(var2.bf, var2.bg, var2.bh, var2.bl, var2.bm);
            this.e.c.a(var5);
            var5.b((OPacket) (new OPacket4UpdateTime(var3.l())));
            
            // CanaryMod - enable/disable potion effects on login
            if (hookResult.applyPotionsEffects()) {
                Iterator var6 = var2.ak().iterator();

                while (var6.hasNext()) {
                    OPotionEffect var7 = (OPotionEffect) var6.next();
                    var5.b((OPacket) (new OPacket41EntityEffect(var2.aW, var7)));
                }
            }

            var2.o();
            ModLoaderMp.HandleAllLogins(var2);
        }

        this.c = true;
    }

    public void a(String var1, Object[] var2) {
        a.info(this.b() + " lost connection");
        this.c = true;
    }

    public void a(OPacket254ServerPing var1) {
        try {
            String var2 = this.e.p + "\u00a7" + this.e.f.g() + "\u00a7" + this.e.f.h();
            this.b.a((OPacket) (new OPacket255KickDisconnect(var2)));
            // CanaryMod swapped lines below. The network connection should be terminated AFTER removing the socket from the connection list.
            this.e.c.a(this.b.f());
            this.b.d();

            this.c = true;
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public void a(OPacket var1) {
        this.a("Protocol error");
    }

    public String b() {
        return this.g != null ? this.g + " [" + this.b.c().toString() + "]" : this.b.c().toString();
    }

    public boolean c() {
        return true;
    }

    // $FF: synthetic method
    static String a(ONetLoginHandler var0) {
        return var0.i;
    }

    // $FF: synthetic method
    static OPacket1Login a(ONetLoginHandler var0, OPacket1Login var1) {
        return var0.h = var1;
    }

}
