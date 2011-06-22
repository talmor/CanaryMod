import java.util.ArrayList;
import java.util.List;

import net.minecraft.server.MinecraftServer;

public class OPlayerManager {

    public List<OEntityPlayerMP> a = new ArrayList();
    private OPlayerHash         b = new OPlayerHash();
    private List<OPlayerInstance> c = new ArrayList();
    private MinecraftServer       d;
    private int e;
    private int f;
    private final int[][]         g = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

    public OPlayerManager(MinecraftServer paramMinecraftServer, int paramInt1, int paramInt2) {
        if (paramInt2 > 15) throw new IllegalArgumentException("Too big view radius!");
        if (paramInt2 < 3) throw new IllegalArgumentException("Too small view radius!");
        f = paramInt2;
        d = paramMinecraftServer;
        e = paramInt1;
    }

    public OWorldServer a() {
        return d.a(e);
    }

    public void b() {
        for (int i = 0; i < c.size(); i++)
            c.get(i).a();
        c.clear();
    }

    private OPlayerInstance a(int paramInt1, int paramInt2, boolean paramBoolean) {
        long l = paramInt1 + 2147483647L | paramInt2 + 2147483647L << 32;
        OPlayerInstance localOPlayerInstance = (OPlayerInstance) b.a(l);
        if ((localOPlayerInstance == null) && (paramBoolean)) {
            localOPlayerInstance = new OPlayerInstance(this, paramInt1, paramInt2);
            b.a(l, localOPlayerInstance);
        }
        return localOPlayerInstance;
    }

    public void a(int paramInt1, int paramInt2, int paramInt3) {
        int i = paramInt1 >> 4;
        int j = paramInt3 >> 4;
        OPlayerInstance localOPlayerInstance = a(i, j, false);
        if (localOPlayerInstance != null)
            localOPlayerInstance.a(paramInt1 & 0xF, paramInt2, paramInt3 & 0xF);
    }

    public void a(OEntityPlayerMP paramOEntityPlayerMP) {
        int i = (int) paramOEntityPlayerMP.aP >> 4;
        int j = (int) paramOEntityPlayerMP.aR >> 4;

        paramOEntityPlayerMP.d = paramOEntityPlayerMP.aP;
        paramOEntityPlayerMP.e = paramOEntityPlayerMP.aR;

        int k = 0;
        int m = f;
        int n = 0;
        int i1 = 0;

        a(i, j, true).a(paramOEntityPlayerMP);

        for (int i2 = 1; i2 <= m * 2; i2++)
            for (int i3 = 0; i3 < 2; i3++) {
                int[] arrayOfInt = g[(k++ % 4)];

                for (int i4 = 0; i4 < i2; i4++) {
                    n += arrayOfInt[0];
                    i1 += arrayOfInt[1];
                    a(i + n, j + i1, true).a(paramOEntityPlayerMP);
                }
            }

        k %= 4;
        for (int i2 = 0; i2 < m * 2; i2++) {
            n += g[k][0];
            i1 += g[k][1];
            a(i + n, j + i1, true).a(paramOEntityPlayerMP);
        }

        a.add(paramOEntityPlayerMP);
    }

    public void b(OEntityPlayerMP paramOEntityPlayerMP) {
        int i = (int) paramOEntityPlayerMP.d >> 4;
        int j = (int) paramOEntityPlayerMP.e >> 4;

        for (int k = i - f; k <= i + f; k++)
            for (int m = j - f; m <= j + f; m++) {
                OPlayerInstance localOPlayerInstance = a(k, m, false);
                if (localOPlayerInstance == null)
                    continue;
                localOPlayerInstance.b(paramOEntityPlayerMP);
            }
        a.remove(paramOEntityPlayerMP);
    }

    private boolean a(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int i = paramInt1 - paramInt3;
        int j = paramInt2 - paramInt4;
        if ((i < -f) || (i > f))
            return false;
        return (j >= -f) && (j <= f);
    }

    public void c(OEntityPlayerMP paramOEntityPlayerMP) {
        int i = (int) paramOEntityPlayerMP.aP >> 4;
        int j = (int) paramOEntityPlayerMP.aR >> 4;

        double d1 = paramOEntityPlayerMP.d - paramOEntityPlayerMP.aP;
        double d2 = paramOEntityPlayerMP.e - paramOEntityPlayerMP.aR;
        double d3 = d1 * d1 + d2 * d2;
        if (d3 < 64.0D)
            return;

        int k = (int) paramOEntityPlayerMP.d >> 4;
        int m = (int) paramOEntityPlayerMP.e >> 4;

        int n = i - k;
        int i1 = j - m;
        if ((n == 0) && (i1 == 0))
            return;

        //CanaryMod speed up teleporting.
        if (n > f || n < -f || i1 > f || i1 < -f) {
            b(paramOEntityPlayerMP);
            a(paramOEntityPlayerMP);
            return;
        }

        for (int i2 = i - f; i2 <= i + f; i2++)
            for (int i3 = j - f; i3 <= j + f; i3++) {
                if (!a(i2, i3, k, m))
                    a(i2, i3, true).a(paramOEntityPlayerMP);
                if (!a(i2 - n, i3 - i1, i, j)) {
                    OPlayerInstance localOPlayerInstance = a(i2 - n, i3 - i1, false);
                    if (localOPlayerInstance == null)
                        continue;
                    localOPlayerInstance.b(paramOEntityPlayerMP);
                }
            }
        paramOEntityPlayerMP.d = paramOEntityPlayerMP.aP;
        paramOEntityPlayerMP.e = paramOEntityPlayerMP.aR;
    }

    public int c() {
        return f * 16 - 16;
    }

    // CanaryMod: OPlayerInstance calls these statically
    static OPlayerHash a(OPlayerManager jh1) {
        return jh1.b;
    }

    static List b(OPlayerManager jh1) {
        return jh1.c;
    }

    // CanaryMod: bring back old "send packet to chunk" method from alpha
    public void sendPacketToChunk(OPacket packetToSend, int globalx, int globaly, int globalz) {
        // Get chunk coordinates
        int chunkx = globalx >> 4;
        int chunkz = globalz >> 4;
        // Get the chunk
        OPlayerInstance localOPlayerInstance = a(chunkx, chunkz, false);
        // if chunk != null, send packet
        if (localOPlayerInstance != null)
            localOPlayerInstance.a(packetToSend);
    }
    // end CanaryMod
}
