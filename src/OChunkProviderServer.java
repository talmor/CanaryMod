import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OChunkProviderServer implements OIChunkProvider {

    public boolean          a = false;
    private Set             b = new HashSet();
    private OChunk          c;
    private OIChunkProvider d;
    private OIChunkLoader   e;
    private Map             f = new HashMap();
    private List            g = new ArrayList();
    private OWorldServer    h;

    public OChunkProviderServer(OWorldServer paramOWorldServer, OIChunkLoader paramOIChunkLoader, OIChunkProvider paramOIChunkProvider) {
        c = new OEmptyChunk(paramOWorldServer, new byte[32768], 0, 0);

        h = paramOWorldServer;
        e = paramOIChunkLoader;
        d = paramOIChunkProvider;
    }

    public boolean a(int paramInt1, int paramInt2) {
        return f.containsKey(Integer.valueOf(OChunkCoordIntPair.a(paramInt1, paramInt2)));
    }

    public void d(int paramInt1, int paramInt2) {
        OChunkCoordinates localOChunkCoordinates = h.n();
        int i = paramInt1 * 16 + 8 - localOChunkCoordinates.a;
        int j = paramInt2 * 16 + 8 - localOChunkCoordinates.c;
        int k = 128;
        if ((i < -k) || (i > k) || (j < -k) || (j > k))
            b.add(Integer.valueOf(OChunkCoordIntPair.a(paramInt1, paramInt2)));
    }

    public OChunk c(int paramInt1, int paramInt2) {
        int i = OChunkCoordIntPair.a(paramInt1, paramInt2);
        b.remove(Integer.valueOf(i));

        OChunk localOChunk = (OChunk) f.get(Integer.valueOf(i));
        if (localOChunk == null) {
            localOChunk = e(paramInt1, paramInt2);
            if (localOChunk == null)
                if (d == null)
                    localOChunk = c;
                else
                    localOChunk = d.b(paramInt1, paramInt2);

            f.put(Integer.valueOf(i), localOChunk);
            g.add(localOChunk);

            if (localOChunk != null) {
                localOChunk.c();
                localOChunk.d();
            }

            if ((!localOChunk.n) && (a(paramInt1 + 1, paramInt2 + 1)) && (a(paramInt1, paramInt2 + 1)) && (a(paramInt1 + 1, paramInt2)))
                a(this, paramInt1, paramInt2);
            if ((a(paramInt1 - 1, paramInt2)) && (!b(paramInt1 - 1, paramInt2).n) && (a(paramInt1 - 1, paramInt2 + 1)) && (a(paramInt1, paramInt2 + 1)) && (a(paramInt1 - 1, paramInt2)))
                a(this, paramInt1 - 1, paramInt2);
            if ((a(paramInt1, paramInt2 - 1)) && (!b(paramInt1, paramInt2 - 1).n) && (a(paramInt1 + 1, paramInt2 - 1)) && (a(paramInt1, paramInt2 - 1)) && (a(paramInt1 + 1, paramInt2)))
                a(this, paramInt1, paramInt2 - 1);
            if ((a(paramInt1 - 1, paramInt2 - 1)) && (!b(paramInt1 - 1, paramInt2 - 1).n) && (a(paramInt1 - 1, paramInt2 - 1)) && (a(paramInt1, paramInt2 - 1)) && (a(paramInt1 - 1, paramInt2)))
                a(this, paramInt1 - 1, paramInt2 - 1);

        }

        return localOChunk;
    }

    public OChunk b(int paramInt1, int paramInt2) {
        OChunk localOChunk = (OChunk) f.get(Integer.valueOf(OChunkCoordIntPair.a(paramInt1, paramInt2)));

        if (localOChunk == null) {
            if (h.y || a)
                return c(paramInt1, paramInt2);
            return c;
        }

        return localOChunk;
    }

    private OChunk e(int paramInt1, int paramInt2) {
        if (e == null)
            return null;
        try {
            OChunk localOChunk = e.a(h, paramInt1, paramInt2);
            if (localOChunk != null)
                localOChunk.r = h.m();
            return localOChunk;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return null;
    }

    private void a(OChunk paramOChunk) {
        if (e == null)
            return;
        try {
            e.b(h, paramOChunk);
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }

    private void b(OChunk paramOChunk) {
        if (e == null)
            return;
        paramOChunk.r = h.m();
        e.a(h, paramOChunk);
    }

    public void a(OIChunkProvider paramOIChunkProvider, int paramInt1, int paramInt2) {
        OChunk localOChunk = b(paramInt1, paramInt2);
        if (!localOChunk.n) {
            localOChunk.n = true;
            if (d != null) {
                d.a(paramOIChunkProvider, paramInt1, paramInt2);
                localOChunk.f();
            }
        }
    }

    // CanaryMod: load status
    boolean loaded = false;

    public boolean a(boolean paramBoolean, OIProgressUpdate paramOIProgressUpdate) {
        // CanaryMod: load once!
        if (!loaded) {
            etc.getLoader().loadPlugins();
            loaded = true;
        }
        int i = 0;
        for (int j = 0; j < g.size(); j++) {
            OChunk localOChunk = (OChunk) g.get(j);
            if ((paramBoolean) && (!localOChunk.p))
                a(localOChunk);
            if (localOChunk.a(paramBoolean)) {
                b(localOChunk);
                localOChunk.o = false;
                i++;
                if ((i == 24) && (!paramBoolean))
                    return false;
            }
        }

        if (paramBoolean) {
            if (e == null)
                return true;
            e.b();
        }
        return true;
    }

    public boolean a() {
        if (!h.E) {
            for (int i = 0; i < 100; i++)
                if (!b.isEmpty()) {
                    Integer localInteger = (Integer) b.iterator().next();

                    OChunk localOChunk = (OChunk) f.get(localInteger);
                    localOChunk.e();
                    b(localOChunk);
                    a(localOChunk);
                    b.remove(localInteger);

                    f.remove(localInteger);
                    g.remove(localOChunk);
                }

            if (e != null)
                e.a();
        }

        return d.a();
    }

    public boolean b() {
        return !h.E;
    }
}
