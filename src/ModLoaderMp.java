
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

public class ModLoaderMp {

   public static final String NAME = "ModLoaderMP";
   public static final String VERSION = "Beta 1.8.1";
   private static boolean hasInit = false;
   private static Map entityTrackerMap = new HashMap();
   private static Map entityTrackerEntryMap = new HashMap();
   private static List bannedMods = new ArrayList();


   public ModLoaderMp() {
      super();
   }

   public static void InitModLoaderMp() {
      if(!hasInit) {
         init();
      }

   }

   public static void RegisterEntityTracker(Class class1, int i, int j) {
      if(!hasInit) {
         init();
      }

      if(entityTrackerMap.containsKey(class1)) {
         System.out.println("RegisterEntityTracker error: entityClass already registered.");
      } else {
         entityTrackerMap.put(class1, new Pair(Integer.valueOf(i), Integer.valueOf(j)));
      }

   }

   public static void RegisterEntityTrackerEntry(Class class1, int i) {
      RegisterEntityTrackerEntry(class1, false, i);
   }

   public static void RegisterEntityTrackerEntry(Class class1, boolean flag, int i) {
      if(!hasInit) {
         init();
      }

      if(i > 255) {
         System.out.println("RegisterEntityTrackerEntry error: entityId cannot be greater than 255.");
      }

      if(entityTrackerEntryMap.containsKey(class1)) {
         System.out.println("RegisterEntityTrackerEntry error: entityClass already registered.");
      } else {
         entityTrackerEntryMap.put(class1, new EntityTrackerEntry2(i, flag));
      }

   }

   public static void HandleAllLogins(OEntityPlayerMP entityplayermp) {
      if(!hasInit) {
         init();
      }

      sendModCheck(entityplayermp);

      for(int i = 0; i < ModLoader.getLoadedMods().size(); ++i) {
         BaseMod basemod = (BaseMod)ModLoader.getLoadedMods().get(i);
         if(basemod instanceof BaseModMp) {
            ((BaseModMp)basemod).HandleLogin(entityplayermp);
         }
      }

   }

   public static void HandleAllPackets(Packet230ModLoader packet230modloader, OEntityPlayerMP entityplayermp) {
      if(!hasInit) {
         init();
      }

      if(packet230modloader.modId == "ModLoaderMP".hashCode()) {
         switch(packet230modloader.packetType) {
         case 0:
            handleModCheckResponse(packet230modloader, entityplayermp);
            break;
         case 1:
            handleSendKey(packet230modloader, entityplayermp);
         }
      } else {
         for(int i = 0; i < ModLoader.getLoadedMods().size(); ++i) {
            BaseMod basemod = (BaseMod)ModLoader.getLoadedMods().get(i);
            if(basemod instanceof BaseModMp) {
               BaseModMp basemodmp = (BaseModMp)basemod;
               if(basemodmp.getId() == packet230modloader.modId) {
                  basemodmp.HandlePacket(packet230modloader, entityplayermp);
                  break;
               }
            }
         }
      }

   }

   public static void HandleEntityTrackers(OEntityTracker entitytracker, OEntity entity) {
      if(!hasInit) {
         init();
      }

      Iterator iterator = entityTrackerMap.entrySet().iterator();

      Entry entry;
      do {
         if(!iterator.hasNext()) {
            return;
         }

         entry = (Entry)iterator.next();
      } while(!((Class)entry.getKey()).isInstance(entity));

      entitytracker.a(entity, ((Integer)((Pair)entry.getValue()).getLeft()).intValue(), ((Integer)((Pair)entry.getValue()).getRight()).intValue(), true);
   }

   public static EntityTrackerEntry2 HandleEntityTrackerEntries(OEntity entity) {
      if(!hasInit) {
         init();
      }

      return entityTrackerEntryMap.containsKey(entity.getClass())?(EntityTrackerEntry2)entityTrackerEntryMap.get(entity.getClass()):null;
   }

   public static void SendPacketToAll(BaseModMp basemodmp, Packet230ModLoader packet230modloader) {
      if(!hasInit) {
         init();
      }

      if(basemodmp == null) {
         IllegalArgumentException illegalargumentexception = new IllegalArgumentException("baseModMp cannot be null.");
         ModLoader.getLogger().throwing("ModLoaderMP", "SendPacketToAll", illegalargumentexception);
         ModLoader.ThrowException("baseModMp cannot be null.", illegalargumentexception);
      } else {
         packet230modloader.modId = basemodmp.getId();
         sendPacketToAll(packet230modloader);
      }
   }

   private static void sendPacketToAll(OPacket packet) {
      if(packet != null) {
         for(int i = 0; i < ModLoader.getMinecraftServerInstance().f.b.size(); ++i) {
            ((OEntityPlayerMP)ModLoader.getMinecraftServerInstance().f.b.get(i)).a.b(packet);
         }
      }

   }

   public static void SendPacketTo(BaseModMp basemodmp, OEntityPlayerMP entityplayermp, Packet230ModLoader packet230modloader) {
      if(!hasInit) {
         init();
      }

      if(basemodmp == null) {
         IllegalArgumentException illegalargumentexception = new IllegalArgumentException("baseModMp cannot be null.");
         ModLoader.getLogger().throwing("ModLoaderMP", "SendPacketTo", illegalargumentexception);
         ModLoader.ThrowException("baseModMp cannot be null.", illegalargumentexception);
      } else {
         packet230modloader.modId = basemodmp.getId();
         sendPacketTo(entityplayermp, packet230modloader);
      }
   }

   public static void Log(String s) {
      MinecraftServer.a.info(s);
      ModLoader.getLogger().fine(s);
      System.out.println(s);
   }

   public static OWorld GetPlayerWorld(OEntityPlayer entityplayer) {
      OWorldServer[] aworldserver = ModLoader.getMinecraftServerInstance().e;

      for(int i = 0; i < aworldserver.length; ++i) {
         if(aworldserver[i].i.contains(entityplayer)) {
            return aworldserver[i];
         }
      }

      return null;
   }

   private static void init() {
      hasInit = true;

      try {
         Method ioexception;
         try {
            ioexception = OPacket.class.getDeclaredMethod("OPacket7UseEntity", new Class[]{Integer.TYPE, Boolean.TYPE, Boolean.TYPE, Class.class});
         } catch (NoSuchMethodException var3) {
 //           ioexception = OPacket.class.getDeclaredMethod("addIdClassMapping", new Class[]{Integer.TYPE, Boolean.TYPE, Boolean.TYPE, Class.class});
            ioexception = OPacket.class.getDeclaredMethod("a", new Class[]{Integer.TYPE, Boolean.TYPE, Boolean.TYPE, Class.class});
        }

         ioexception.setAccessible(true);
         ioexception.invoke((Object)null, new Object[]{Integer.valueOf(230), Boolean.valueOf(true), Boolean.valueOf(true), Packet230ModLoader.class});
      } catch (IllegalAccessException var4) {
         ModLoader.getLogger().throwing("ModLoaderMP", "AddCustomPacketMapping", var4);
         ModLoader.ThrowException("ModLoaderMP", var4);
         return;
      } catch (IllegalArgumentException var5) {
         ModLoader.getLogger().throwing("ModLoaderMP", "init", var5);
         ModLoader.ThrowException("ModLoaderMP", var5);
         return;
      } catch (InvocationTargetException var6) {
         ModLoader.getLogger().throwing("ModLoaderMP", "init", var6);
         ModLoader.ThrowException("ModLoaderMP", var6);
         return;
      } catch (NoSuchMethodException var7) {
         ModLoader.getLogger().throwing("ModLoaderMP", "init", var7);
         ModLoader.ThrowException("ModLoaderMP", var7);
         return;
      } catch (SecurityException var8) {
         ModLoader.getLogger().throwing("ModLoaderMP", "init", var8);
         ModLoader.ThrowException("ModLoaderMP", var8);
         return;
      }

      try {
         File ioexception1 = ModLoader.getMinecraftServerInstance().a("banned-mods.txt");
         if(!ioexception1.exists()) {
            ioexception1.createNewFile();
         }

         BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(new FileInputStream(ioexception1)));

         String s;
         while((s = bufferedreader.readLine()) != null) {
            bannedMods.add(s);
         }
      } catch (FileNotFoundException var9) {
         ModLoader.getLogger().throwing("ModLoader", "init", var9);
         ModLoader.ThrowException("ModLoaderMultiplayer", var9);
         return;
      } catch (IOException var10) {
         ModLoader.getLogger().throwing("ModLoader", "init", var10);
         ModLoader.ThrowException("ModLoaderMultiplayer", var10);
         return;
      }

      Log("ModLoaderMP Beta 1.8.1 Initialized");
   }

   private static void sendPacketTo(OEntityPlayerMP entityplayermp, Packet230ModLoader packet230modloader) {
      entityplayermp.a.b((OPacket)packet230modloader);
   }

   private static void sendModCheck(OEntityPlayerMP entityplayermp) {
      Packet230ModLoader packet230modloader = new Packet230ModLoader();
      packet230modloader.modId = "ModLoaderMP".hashCode();
      packet230modloader.packetType = 0;
      sendPacketTo(entityplayermp, packet230modloader);
   }

   private static void handleModCheckResponse(Packet230ModLoader packet230modloader, OEntityPlayerMP entityplayermp) {
      StringBuilder stringbuilder = new StringBuilder();
      if(packet230modloader.dataString.length != 0) {
         for(int arraylist = 0; arraylist < packet230modloader.dataString.length; ++arraylist) {
            if(packet230modloader.dataString[arraylist].lastIndexOf("mod_") != -1) {
               if(stringbuilder.length() != 0) {
                  stringbuilder.append(", ");
               }

               stringbuilder.append(packet230modloader.dataString[arraylist].substring(packet230modloader.dataString[arraylist].lastIndexOf("mod_")));
            }
         }
      } else {
         stringbuilder.append("no mods");
      }

      Log(entityplayermp.u + " joined with " + stringbuilder.toString());
      ArrayList var11 = new ArrayList();

      int stringbuilder2;
      for(int arraylist1 = 0; arraylist1 < bannedMods.size(); ++arraylist1) {
         for(stringbuilder2 = 0; stringbuilder2 < packet230modloader.dataString.length; ++stringbuilder2) {
            if(packet230modloader.dataString[stringbuilder2].lastIndexOf("mod_") != -1 && packet230modloader.dataString[stringbuilder2].substring(packet230modloader.dataString[stringbuilder2].lastIndexOf("mod_")).startsWith((String)bannedMods.get(arraylist1))) {
               var11.add(packet230modloader.dataString[stringbuilder2]);
            }
         }
      }

      ArrayList var12 = new ArrayList();

      for(stringbuilder2 = 0; stringbuilder2 < ModLoader.getLoadedMods().size(); ++stringbuilder2) {
         BaseModMp j1 = (BaseModMp)ModLoader.getLoadedMods().get(stringbuilder2);
         if(j1.hasClientSide() && j1.toString().lastIndexOf("mod_") != -1) {
            String k1 = j1.toString().substring(j1.toString().lastIndexOf("mod_"));
            boolean flag = false;

            for(int l1 = 0; l1 < packet230modloader.dataString.length; ++l1) {
               if(packet230modloader.dataString[l1].lastIndexOf("mod_") != -1) {
                  String s1 = packet230modloader.dataString[l1].substring(packet230modloader.dataString[l1].lastIndexOf("mod_"));
                  if(k1.equals(s1)) {
                     flag = true;
                     break;
                  }
               }
            }

            if(!flag) {
               var12.add(k1);
            }
         }
      }

      int var13;
      StringBuilder var14;
      if(var11.size() != 0) {
         var14 = new StringBuilder();

         for(var13 = 0; var13 < var11.size(); ++var13) {
            if(((String)var11.get(var13)).lastIndexOf("mod_") != -1) {
               if(var14.length() != 0) {
                  var14.append(", ");
               }

               var14.append(((String)var11.get(var13)).substring(((String)var11.get(var13)).lastIndexOf("mod_")));
            }
         }

         Log(entityplayermp.u + " kicked for having " + var14.toString());
         StringBuilder var16 = new StringBuilder();

         for(int var15 = 0; var15 < var11.size(); ++var15) {
            if(((String)var11.get(var15)).lastIndexOf("mod_") != -1) {
               var16.append("\n");
               var16.append(((String)var11.get(var15)).substring(((String)var11.get(var15)).lastIndexOf("mod_")));
            }
         }

         entityplayermp.a.a("The following mods are banned on this server:" + var16.toString());
      } else if(var12.size() != 0) {
         var14 = new StringBuilder();

         for(var13 = 0; var13 < var12.size(); ++var13) {
            if(((String)var12.get(var13)).lastIndexOf("mod_") != -1) {
               var14.append("\n");
               var14.append(((String)var12.get(var13)).substring(((String)var12.get(var13)).lastIndexOf("mod_")));
            }
         }

         entityplayermp.a.a("You are missing the following mods:" + var14.toString());
      }

   }

   private static void handleSendKey(Packet230ModLoader packet230modloader, OEntityPlayerMP entityplayermp) {
      if(packet230modloader.dataInt.length != 2) {
         System.out.println("SendKey packet received with missing data.");
      } else {
         int i = packet230modloader.dataInt[0];
         int j = packet230modloader.dataInt[1];

         for(int k = 0; k < ModLoader.getLoadedMods().size(); ++k) {
            BaseMod basemod = (BaseMod)ModLoader.getLoadedMods().get(k);
            if(basemod instanceof BaseModMp) {
               BaseModMp basemodmp = (BaseModMp)basemod;
               if(basemodmp.getId() == i) {
                  basemodmp.HandleSendKey(entityplayermp, j);
                  break;
               }
            }
         }
      }

   }

   public static void getCommandInfo(OICommandListener icommandlistener) {
      for(int i = 0; i < ModLoader.getLoadedMods().size(); ++i) {
         BaseMod basemod = (BaseMod)ModLoader.getLoadedMods().get(i);
         if(basemod instanceof BaseModMp) {
            BaseModMp basemodmp = (BaseModMp)basemod;
            basemodmp.GetCommandInfo(icommandlistener);
         }
      }

   }

   public static boolean HandleCommand(String command, String username, Logger logger, boolean isOp) {
      boolean flag = false;

      for(int i = 0; i < ModLoader.getLoadedMods().size(); ++i) {
         BaseMod basemod = (BaseMod)ModLoader.getLoadedMods().get(i);
         if(basemod instanceof BaseModMp) {
            BaseModMp basemodmp = (BaseModMp)basemod;
            if(basemodmp.HandleCommand(command, username, logger, isOp)) {
               flag = true;
            }
         }
      }

      return flag;
   }

   public static void sendChatToAll(String s, String s1) {
      String s2 = s + ": " + s1;
      sendChatToAll(s2);
   }

   public static void sendChatToAll(String s) {
      List list = ModLoader.getMinecraftServerInstance().f.b;

      for(int i = 0; i < list.size(); ++i) {
         OEntityPlayerMP entityplayermp = (OEntityPlayerMP)list.get(i);
         entityplayermp.a.b((OPacket)(new OPacket3Chat(s)));
      }

      MinecraftServer.a.info(s);
   }

   public static void sendChatToOps(String s, String s1) {
      String s2 = "\u00a77(" + s + ": " + s1 + ")";
      sendChatToOps(s2);
   }

   public static void sendChatToOps(String s) {
      List list = ModLoader.getMinecraftServerInstance().f.b;

      for(int i = 0; i < list.size(); ++i) {
         OEntityPlayerMP entityplayermp = (OEntityPlayerMP)list.get(i);
         if(ModLoader.getMinecraftServerInstance().f.h(entityplayermp.u)) {
            entityplayermp.a.b((OPacket)(new OPacket3Chat(s)));
         }
      }

      MinecraftServer.a.info(s);
   }

   public static OPacket GetTileEntityPacket(BaseModMp basemodmp, int i, int j, int k, int l, int[] ai, float[] af, String[] as) {
      Packet230ModLoader packet230modloader = new Packet230ModLoader();
      packet230modloader.modId = "ModLoaderMP".hashCode();
      packet230modloader.packetType = 1;
      packet230modloader.k = true;
      int i1 = ai == null?0:ai.length;
      int[] ai1 = new int[i1 + 5];
      ai1[0] = basemodmp.getId();
      ai1[1] = i;
      ai1[2] = j;
      ai1[3] = k;
      ai1[4] = l;
      if(i1 != 0) {
         System.arraycopy(ai, 0, ai1, 5, ai.length);
      }

      packet230modloader.dataInt = ai1;
      packet230modloader.dataFloat = af;
      packet230modloader.dataString = as;
      return packet230modloader;
   }

   public static void SendTileEntityPacket(OTileEntity tileentity) {
      sendPacketToAll(tileentity.l());
   }

   public static BaseModMp GetModInstance(Class class1) {
      for(int i = 0; i < ModLoader.getLoadedMods().size(); ++i) {
         BaseMod basemod = (BaseMod)ModLoader.getLoadedMods().get(i);
         if(basemod instanceof BaseModMp) {
            BaseModMp basemodmp = (BaseModMp)basemod;
            if(class1.isInstance(basemodmp)) {
               return (BaseModMp)ModLoader.getLoadedMods().get(i);
            }
         }
      }

      return null;
   }

}
