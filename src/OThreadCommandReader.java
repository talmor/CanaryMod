
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import net.minecraft.server.MinecraftServer;

public class OThreadCommandReader extends Thread {

   final MinecraftServer a;


   public OThreadCommandReader(MinecraftServer var1) {
      this.a = var1;
   }

   public void run() {
      BufferedReader var1 = new BufferedReader(new InputStreamReader(System.in));
      String var2 = null;

      try {
         while(!this.a.g && (var2 = var1.readLine()) != null) {
            // CanaryMod: run through our parser first.
            if (!etc.getInstance().parseConsoleCommand(var2, a))
               this.a.a(var2, this.a);
         }
      } catch (IOException var4) {
         var4.printStackTrace();
      }

   }
}
