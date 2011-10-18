
import java.util.logging.Logger;


public abstract class BaseModMp extends BaseMod {

   public BaseModMp() {
      super();
   }

   public final int getId() {
      return this.toString().hashCode();
   }

   public void ModsLoaded() {
      ModLoaderMp.InitModLoaderMp();
   }

   public void HandlePacket(Packet230ModLoader packet230modloader, OEntityPlayerMP entityplayermp) {}

   public void HandleLogin(OEntityPlayerMP entityplayermp) {}

   public void HandleSendKey(OEntityPlayerMP entityplayermp, int i) {}

   public void GetCommandInfo(OICommandListener icommandlistener) {}

   public boolean HandleCommand(String command, String username, Logger logger, boolean isOp) {
      return false;
   }

   public boolean hasClientSide() {
      return true;
   }
}
