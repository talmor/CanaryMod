import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ONetworkManager
{
  public static final Object a = new Object();
  public static int b;
  public static int c;
  private Object g = new Object();
  private Socket h;
  private final SocketAddress i;
  private DataInputStream j;
  private DataOutputStream k;
  private boolean l = true;

  private List m = Collections.synchronizedList(new ArrayList());
  private List n = Collections.synchronizedList(new ArrayList());
  private List o = Collections.synchronizedList(new ArrayList());
  private ONetHandler p;
  private boolean q = false;
  private Thread r;
  private Thread s;
  private boolean t = false;
  private String u = "";
  private Object[] v;
  private int w = 0;
  private int x = 0;

  public static int[] d = new int[256];
  public static int[] e = new int[256];

  public int f = 0;

  private int y = 50;

  public ONetworkManager(Socket paramSocket, String paramString, ONetHandler paramONetHandler) throws IOException
  {
    this.h = paramSocket;
    this.i = paramSocket.getRemoteSocketAddress();
    this.p = paramONetHandler;
    try {
      paramSocket.setSoTimeout(30000);
      paramSocket.setTrafficClass(24);
    }
    catch (SocketException localSocketException)
    {
      System.err.println(localSocketException.getMessage());
    }

    this.j = new DataInputStream(paramSocket.getInputStream());
    this.k = new DataOutputStream(new BufferedOutputStream(paramSocket.getOutputStream(), 5120));

    this.s = new ONetworkReaderThread(this, paramString + " read thread");

    this.r = new ONetworkWriterThread(this, paramString + " write thread");

    this.s.start();
    this.r.start();
  }

  public void a(ONetHandler paramONetHandler) {
    this.p = paramONetHandler;
  }

  public void a(OPacket paramOPacket) {
    if (this.q) return;
    synchronized (this.g) {
      this.x += paramOPacket.a() + 1;
      if (paramOPacket.k)
        this.o.add(paramOPacket);
      else
        this.n.add(paramOPacket);
    }
  }

  private boolean f()
  {
    boolean i1 = false;
    try
    {
      OPacket localOPacket;
      if ((!this.n.isEmpty()) && ((this.f == 0) || (System.currentTimeMillis() - ((OPacket)this.n.get(0)).j >= this.f)))
      {
        synchronized (this.g) {
          localOPacket = (OPacket)this.n.remove(0);
          this.x -= localOPacket.a() + 1;
        }
        OPacket.a(localOPacket, this.k);
        e[localOPacket.b()] += localOPacket.a() + 1;
        i1 = true;
      }
	//Begin CanaryMod Chunk Packet fix.
      if ((i1) || ((this.y-- <= 0) && (!this.o.isEmpty()) && ((this.n.isEmpty()) || (((OPacket)this.n.get(0)).j > ((OPacket)this.o.get(0)).j))))
      //End CanaryMod Chunk Packet fix.
      {
        synchronized (this.g) {
          localOPacket = (OPacket)this.o.remove(0);
          this.x -= localOPacket.a() + 1;
        }
        OPacket.a(localOPacket, this.k);
        e[localOPacket.b()] += localOPacket.a() + 1;
        this.y = 0;
        i1 = true;
      }
    } catch (Exception localException) {
      if (!this.t) a(localException);
      return false;
    }
    return i1;
  }

  public void a() {
    this.s.interrupt();
    this.r.interrupt();
  }

  private boolean g() {
    boolean i1 = false;
    try {
      OPacket localOPacket = OPacket.a(this.j, this.p.c());

      if (localOPacket != null) {
        d[localOPacket.b()] += localOPacket.a() + 1;
        this.m.add(localOPacket);
        i1 = true; } else {
        a("disconnect.endOfStream", new Object[0]);
      }
    } catch (Exception localException) {
      if (!this.t) a(localException);
      return false;
    }
    return i1;
  }

  private void a(Exception paramException) {
    paramException.printStackTrace();
    a("disconnect.genericReason", new Object[] { "Internal exception: " + paramException.toString() });
  }

  public void a(String paramString, Object[] paramArrayOfObject) {
    if (!this.l) return;
    this.t = true;
    this.u = paramString;
    this.v = paramArrayOfObject;

    new ONetworkMasterThread(this).start();

    this.l = false;
    try {
      this.j.close();
      this.j = null;
    } catch (Throwable localThrowable1) {
    }
    try {
      this.k.close();
      this.k = null;
    } catch (Throwable localThrowable2) {
    }
    try {
      this.h.close();
      this.h = null;
    } catch (Throwable localThrowable3) {
    }
  }

  public void b() {
    if (this.x > 1048576) {
      a("disconnect.overflow", new Object[0]);
    }
    if (this.m.isEmpty()) {
      if (this.w++ == 1200)
        a("disconnect.timeout", new Object[0]);
    }
    else {
      this.w = 0;
    }

    int i1 = 100;
    while ((!this.m.isEmpty()) && (i1-- >= 0)) {
      OPacket localOPacket = (OPacket)this.m.remove(0);
      localOPacket.a(this.p);
    }

    a();

    if ((this.t) && (this.m.isEmpty()))
      this.p.a(this.u, this.v);
  }

  public SocketAddress c()
  {
    return this.i;
  }

  public void d() {
    a();
    this.q = true;
    this.s.interrupt();
    new OThreadMonitorConnection(this).start();
  }

  public int e()
  {
    return this.o.size();
  }
}