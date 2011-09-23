import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;
import net.minecraft.server.MinecraftServer;

public class OConsoleCommandHandler {

   private static Logger a = Logger.getLogger("Minecraft");
   private MinecraftServer b;


   public OConsoleCommandHandler(MinecraftServer var1) {
      super();
      this.b = var1;
   }

   public void a(OServerCommand var1) {
      String var2 = var1.a;
      OICommandListener var3 = var1.b;
      String var4 = var3.d();
      OServerConfigurationManager var5 = this.b.f;
      if(!var2.toLowerCase().startsWith("help") && !var2.toLowerCase().startsWith("?")) {
         if(var2.toLowerCase().startsWith("list")) {
            var3.b("Connected players: " + var5.c());
         } else if(var2.toLowerCase().startsWith("stop")) {
            this.a(var4, "Stopping the server..");
            this.b.a();
         } else {
            int var6;
            OWorldServer var7;
            if(var2.toLowerCase().startsWith("save-all")) {
               this.a(var4, "Forcing save..");
               if(var5 != null) {
                  var5.d();
               }

               for(var6 = 0; var6 < this.b.e.length; ++var6) {
                  var7 = this.b.e[var6];
                  var7.a(true, (OIProgressUpdate)null);
               }

               this.a(var4, "Save complete.");
            } else if(var2.toLowerCase().startsWith("save-off")) {
               this.a(var4, "Disabling level saving..");

               for(var6 = 0; var6 < this.b.e.length; ++var6) {
                  var7 = this.b.e[var6];
                  var7.O = true;
               }
            } else if(var2.toLowerCase().startsWith("save-on")) {
               this.a(var4, "Enabling level saving..");

               for(var6 = 0; var6 < this.b.e.length; ++var6) {
                  var7 = this.b.e[var6];
                  var7.O = false;
               }
            } else {
               String var14;
               if(var2.toLowerCase().startsWith("op ")) {
                  var14 = var2.substring(var2.indexOf(" ")).trim();
                  var5.e(var14);
                  this.a(var4, "Opping " + var14);
                  var5.a(var14, "\u00a7eYou are now op!");
               } else if(var2.toLowerCase().startsWith("deop ")) {
                  var14 = var2.substring(var2.indexOf(" ")).trim();
                  var5.f(var14);
                  var5.a(var14, "\u00a7eYou are no longer op!");
                  this.a(var4, "De-opping " + var14);
               } else if(var2.toLowerCase().startsWith("ban-ip ")) {
                  var14 = var2.substring(var2.indexOf(" ")).trim();
                  var5.c(var14);
                  this.a(var4, "Banning ip " + var14);
               } else if(var2.toLowerCase().startsWith("pardon-ip ")) {
                  var14 = var2.substring(var2.indexOf(" ")).trim();
                  var5.d(var14);
                  this.a(var4, "Pardoning ip " + var14);
               } else {
                  OEntityPlayerMP var15;
                  if(var2.toLowerCase().startsWith("ban ")) {
                     var14 = var2.substring(var2.indexOf(" ")).trim();
                     var5.a(var14);
                     this.a(var4, "Banning " + var14);
                     var15 = var5.i(var14);
                     if(var15 != null) {
                        var15.a.a("Banned by admin");
                     }
                  } else if(var2.toLowerCase().startsWith("pardon ")) {
                     var14 = var2.substring(var2.indexOf(" ")).trim();
                     var5.b(var14);
                     this.a(var4, "Pardoning " + var14);
                  } else {
                     int var8;
                     if(var2.toLowerCase().startsWith("kick ")) {
                        var14 = var2.substring(var2.indexOf(" ")).trim();
                        var15 = null;

                        for(var8 = 0; var8 < var5.b.size(); ++var8) {
                           OEntityPlayerMP var9 = (OEntityPlayerMP)var5.b.get(var8);
                           if(var9.u.equalsIgnoreCase(var14)) {
                              var15 = var9;
                           }
                        }

                        if(var15 != null) {
                           var15.a.a("Kicked by admin");
                           this.a(var4, "Kicking " + var15.u);
                        } else {
                           var3.b("Can\'t find user " + var14 + ". No kick.");
                        }
                     } else {
                        OEntityPlayerMP var16;
                        String[] var19;
                        if(var2.toLowerCase().startsWith("tp ")) {
                           var19 = var2.split(" ");
                           if(var19.length == 3) {
                              var15 = var5.i(var19[1]);
                              var16 = var5.i(var19[2]);
                              if(var15 == null) {
                                 var3.b("Can\'t find user " + var19[1] + ". No tp.");
                              } else if(var16 == null) {
                                 var3.b("Can\'t find user " + var19[2] + ". No tp.");
                              } else if(var15.v != var16.v) {
                                 var3.b("User " + var19[1] + " and " + var19[2] + " are in different dimensions. No tp.");
                              } else {
                                 var15.a.a(var16.bf, var16.bg, var16.bh, var16.bl, var16.bm);
                                 this.a(var4, "Teleporting " + var19[1] + " to " + var19[2] + ".");
                              }
                           } else {
                              var3.b("Syntax error, please provice a source and a target.");
                           }
                        } else {
                           int var17;
                           String var18;
                           if(var2.toLowerCase().startsWith("give ")) {
                              var19 = var2.split(" ");
                              if(var19.length != 3 && var19.length != 4) {
                                 return;
                              }

                              var18 = var19[1];
                              var16 = var5.i(var18);
                              if(var16 != null) {
                                 try {
                                    var17 = Integer.parseInt(var19[2]);
                                    if(OItem.c[var17] != null) {
                                       this.a(var4, "Giving " + var16.u + " some " + var17);
                                       int var10 = 1;
                                       if(var19.length > 3) {
                                          var10 = this.a(var19[3], 1);
                                       }

                                       if(var10 < 1) {
                                          var10 = 1;
                                       }

                                       if(var10 > 64) {
                                          var10 = 64;
                                       }

                                       var16.b(new OItemStack(var17, var10, 0));
                                    } else {
                                       var3.b("There\'s no item with id " + var17);
                                    }
                                 } catch (NumberFormatException var12) {
                                    var3.b("There\'s no item with id " + var19[2]);
                                 }
                              } else {
                                 var3.b("Can\'t find user " + var18);
                              }
                           } else if(var2.toLowerCase().startsWith("gamemode ")) {
                              var19 = var2.split(" ");
                              if(var19.length != 3) {
                                 return;
                              }

                              var18 = var19[1];
                              var16 = var5.i(var18);
                              if(var16 != null) {
                                 try {
                                    var17 = Integer.parseInt(var19[2]);
                                    var17 = OWorldSettings.a(var17);
                                    if(var16.c.a() != var17) {
                                       this.a(var4, "Setting " + var16.u + " to game mode " + var17);
                                       var16.c.a(var17);
                                       var16.a.b((OPacket)(new OPacket70Bed(3, var17)));
                                    } else {
                                       this.a(var4, var16.u + " already has game mode " + var17);
                                    }
                                 } catch (NumberFormatException var11) {
                                    var3.b("There\'s no game mode with id " + var19[2]);
                                 }
                              } else {
                                 var3.b("Can\'t find user " + var18);
                              }
                           } else if(var2.toLowerCase().startsWith("time ")) {
                              var19 = var2.split(" ");
                              if(var19.length != 3) {
                                 return;
                              }

                              var18 = var19[1];

                              try {
                                 var8 = Integer.parseInt(var19[2]);
                                 OWorldServer var20;
                                 if("add".equalsIgnoreCase(var18)) {
                                    for(var17 = 0; var17 < this.b.e.length; ++var17) {
                                       var20 = this.b.e[var17];
                                       var20.b(var20.l() + (long)var8);
                                    }

                                    this.a(var4, "Added " + var8 + " to time");
                                 } else if("set".equalsIgnoreCase(var18)) {
                                    for(var17 = 0; var17 < this.b.e.length; ++var17) {
                                       var20 = this.b.e[var17];
                                       var20.b((long)var8);
                                    }

                                    this.a(var4, "Set time to " + var8);
                                 } else {
                                    var3.b("Unknown method, use either \"add\" or \"set\"");
                                 }
                              } catch (NumberFormatException var13) {
                                 var3.b("Unable to convert time value, " + var19[2]);
                              }
                           } else if(var2.toLowerCase().startsWith("say ")) {
                              var2 = var2.substring(var2.indexOf(" ")).trim();
                              a.info("[" + var4 + "] " + var2);
                              var5.a((OPacket)(new OPacket3Chat("\u00a7d[Server] " + var2)));
                           } else if(var2.toLowerCase().startsWith("tell ")) {
                              var19 = var2.split(" ");
                              if(var19.length >= 3) {
                                 var2 = var2.substring(var2.indexOf(" ")).trim();
                                 var2 = var2.substring(var2.indexOf(" ")).trim();
                                 a.info("[" + var4 + "->" + var19[1] + "] " + var2);
                                 var2 = "\u00a77" + var4 + " whispers " + var2;
                                 a.info(var2);
                                 if(!var5.a(var19[1], (OPacket)(new OPacket3Chat(var2)))) {
                                    var3.b("There\'s no player by that name online.");
                                 }
                              }
                           } else if(var2.toLowerCase().startsWith("whitelist ")) {
                              this.a(var4, var2, var3);
                           } else {
                              a.info("Unknown console command. Type \"help\" for help.");
                           }
                        }
                     }
                  }
               }
            }
         }
      } else {
         this.a(var3);
      }

   }

   private void a(String var1, String var2, OICommandListener var3) {
      String[] var4 = var2.split(" ");
      if(var4.length >= 2) {
         String var5 = var4[1].toLowerCase();
         if("on".equals(var5)) {
            this.a(var1, "Turned on white-listing");
            this.b.d.b("white-list", true);
         } else if("off".equals(var5)) {
            this.a(var1, "Turned off white-listing");
            this.b.d.b("white-list", false);
         } else if("list".equals(var5)) {
            Set var6 = this.b.f.e();
            String var7 = "";

            String var9;
            for(Iterator var8 = var6.iterator(); var8.hasNext(); var7 = var7 + var9 + " ") {
               var9 = (String)var8.next();
            }

            var3.b("White-listed players: " + var7);
         } else {
            String var10;
            if("add".equals(var5) && var4.length == 3) {
               var10 = var4[2].toLowerCase();
               this.b.f.k(var10);
               this.a(var1, "Added " + var10 + " to white-list");
            } else if("remove".equals(var5) && var4.length == 3) {
               var10 = var4[2].toLowerCase();
               this.b.f.l(var10);
               this.a(var1, "Removed " + var10 + " from white-list");
            } else if("reload".equals(var5)) {
               this.b.f.f();
               this.a(var1, "Reloaded white-list from file");
            }
         }

      }
   }

   private void a(OICommandListener var1) {
      var1.b("To run the server without a gui, start it like this:");
      var1.b("   java -Xmx1024M -Xms1024M -jar minecraft_server.jar nogui");
      var1.b("Console commands:");
      var1.b("   help  or  ?               shows this message");
      var1.b("   kick <player>             removes a player from the server");
      var1.b("   ban <player>              bans a player from the server");
      var1.b("   pardon <player>           pardons a banned player so that they can connect again");
      var1.b("   ban-ip <ip>               bans an IP address from the server");
      var1.b("   pardon-ip <ip>            pardons a banned IP address so that they can connect again");
      var1.b("   op <player>               turns a player into an op");
      var1.b("   deop <player>             removes op status from a player");
      var1.b("   tp <player1> <player2>    moves one player to the same location as another player");
      var1.b("   give <player> <id> [num]  gives a player a resource");
      var1.b("   tell <player> <message>   sends a private message to a player");
      var1.b("   stop                      gracefully stops the server");
      var1.b("   save-all                  forces a server-wide level save");
      var1.b("   save-off                  disables terrain saving (useful for backup scripts)");
      var1.b("   save-on                   re-enables terrain saving");
      var1.b("   list                      lists all currently connected players");
      var1.b("   say <message>             broadcasts a message to all players");
      var1.b("   time <add|set> <amount>   adds to or sets the world time (0-24000)");
      var1.b("   gamemode <player> <mode>  sets player\'s game mode (0 or 1)");
   }

   private void a(String var1, String var2) {
      String var3 = var1 + ": " + var2;
      this.b.f.j("\u00a77(" + var3 + ")");
      a.info(var3);
   }

   private int a(String var1, int var2) {
      try {
         return Integer.parseInt(var1);
      } catch (NumberFormatException var4) {
         return var2;
      }
   }

}
