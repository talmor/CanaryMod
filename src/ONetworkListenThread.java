
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraft.server.MinecraftServer;

public class ONetworkListenThread {

   public static Logger a = Logger.getLogger("Minecraft");
   private ServerSocket d;
   private Thread e;
   public volatile boolean b = false;
   private int f = 0;
   private ArrayList g = new ArrayList();
   private ArrayList h = new ArrayList();
   public MinecraftServer c;


   public ONetworkListenThread(MinecraftServer var1, InetAddress var2, int var3) {
        try {
            this.c = var1;
            this.d = new ServerSocket(var3, 0, var2);
            this.d.setPerformancePreferences(0, 2, 1);
            this.b = true;
            this.e = new ONetworkAcceptThread(this, "Listen thread", var1);
            this.e.start();
        } catch (IOException ex) {
            a.log(Level.SEVERE, "Could not listen on socket, CanaryMod will now exit", ex);
            System.exit(1);
        }
   }

   public void a(ONetServerHandler var1) {
      this.h.add(var1);
   }

   private void a(ONetLoginHandler var1) {
      if(var1 == null) {
         throw new IllegalArgumentException("Got null pendingconnection!");
      } else {
         this.g.add(var1);
      }
   }

   public void a() {
      int var1;
      for(var1 = 0; var1 < this.g.size(); ++var1) {
         ONetLoginHandler var2 = (ONetLoginHandler)this.g.get(var1);

         try {
            var2.a();
         } catch (Exception var5) {
            var2.a("Internal server error");
            a.log(Level.WARNING, "Failed to handle packet: " + var5, var5);
         }

         if(var2.c) {
            this.g.remove(var1--);
         }

         var2.b.a();
      }

      for(var1 = 0; var1 < this.h.size(); ++var1) {
         ONetServerHandler var6 = (ONetServerHandler)this.h.get(var1);

         try {
            var6.a();
         } catch (Exception var4) {
            a.log(Level.WARNING, "Failed to handle packet: " + var4, var4);
            var6.a("Internal server error");
         }

         if(var6.c) {
            this.h.remove(var1--);
         }

         var6.b.a();
      }

   }

   // $FF: synthetic method
   static ServerSocket a(ONetworkListenThread var0) {
      return var0.d;
   }

   // $FF: synthetic method
   static int b(ONetworkListenThread var0) {
      return var0.f++;
   }

   // $FF: synthetic method
   static void a(ONetworkListenThread var0, ONetLoginHandler var1) {
      var0.a(var1);
   }

}
