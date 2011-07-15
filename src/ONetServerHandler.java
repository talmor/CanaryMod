
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import net.minecraft.server.MinecraftServer;

public class ONetServerHandler extends ONetHandler implements OICommandListener {

   public static Logger a = Logger.getLogger("Minecraft");
   public ONetworkManager b;
   public boolean c = false;
   private MinecraftServer d;
   private OEntityPlayerMP e;
   private int f;
   private int g;
   private int h;
   private boolean i;
   private double j;
   private double k;
   private double l;
   private boolean m = true;
   private Map n = new HashMap();


   public ONetServerHandler(MinecraftServer var1, ONetworkManager var2, OEntityPlayerMP var3) {
      this.d = var1;
      this.b = var2;
      var2.a((ONetHandler)this);
      this.e = var3;
      var3.a = this;
   }

   public void a() {
      this.i = false;
      this.b.b();
      if(this.f - this.g > 20) {
         this.b((OPacket)(new OPacket0KeepAlive()));
      }

   }

   public void a(String var1) {
      this.e.B();
      this.b((OPacket)(new OPacket255KickDisconnect(var1)));
      this.b.d();
      this.d.f.a((OPacket)(new OPacket3Chat("§e" + this.e.r + " left the game.")));
      this.d.f.e(this.e);
      this.c = true;
   }

   public void a(OPacket27Position var1) {
      this.e.a(var1.c(), var1.e(), var1.g(), var1.h(), var1.d(), var1.f());
   }

   public void a(OPacket10Flying var1) {
      OWorldServer var2 = this.d.a(this.e.s);
      this.i = true;
      double var3;
      if(!this.m) {
         var3 = var1.b - this.k;
         if(var1.a == this.j && var3 * var3 < 0.01D && var1.c == this.l) {
            this.m = true;
         }
      }
      
      // CanaryMod: Notice player movement
      if (etc.floor(j) != etc.floor(getPlayer().getX()) || etc.floor(k) != etc.floor(getPlayer().getY()) || etc.floor(l) != etc.floor(getPlayer().getZ())) {
         Location from = new Location();
         from.x = etc.floor(j);
         from.y = etc.floor(k);
         from.z = etc.floor(l);
         from.rotX = getPlayer().getRotation();
         from.rotY = getPlayer().getPitch();

         Location to = new Location();
         to.x = etc.floor(e.aP);
         to.y = etc.floor(e.aQ);
         to.z = etc.floor(e.aR);
         to.rotX = getPlayer().getRotation();
         to.rotY = getPlayer().getPitch();

         OEntity.manager.callHook(PluginLoader.Hook.PLAYER_MOVE, getPlayer(), from, to);
      }

      if(this.m) {
         double var7;
         double var9;
         double var11;
         double var15;
         if(this.e.aK != null) {
            float var5 = this.e.aV;
            float var6 = this.e.aW;
            this.e.aK.f();
            var7 = this.e.aP;
            var9 = this.e.aQ;
            var11 = this.e.aR;
            double var13 = 0.0D;
            var15 = 0.0D;
            if(var1.i) {
               var5 = var1.e;
               var6 = var1.f;
            }

            if(var1.h && var1.b == -999.0D && var1.d == -999.0D) {
               var13 = var1.a;
               var15 = var1.c;
            }

            this.e.ba = var1.g;
            this.e.a(true);
            this.e.a(var13, 0.0D, var15);
            this.e.b(var7, var9, var11, var5, var6);
            this.e.aS = var13;
            this.e.aU = var15;
            if(this.e.aK != null) {
               var2.b(this.e.aK, true);
            }

            if(this.e.aK != null) {
               this.e.aK.f();
            }

            this.d.f.d(this.e);
            this.j = this.e.aP;
            this.k = this.e.aQ;
            this.l = this.e.aR;
            var2.g(this.e);
            return;
         }

         if(this.e.L()) {
            this.e.a(true);
            this.e.b(this.j, this.k, this.l, this.e.aV, this.e.aW);
            var2.g(this.e);
            return;
         }

         var3 = this.e.aQ;
         this.j = this.e.aP;
         this.k = this.e.aQ;
         this.l = this.e.aR;
         var7 = this.e.aP;
         var9 = this.e.aQ;
         var11 = this.e.aR;
         float var17 = this.e.aV;
         float var18 = this.e.aW;
         if(var1.h && var1.b == -999.0D && var1.d == -999.0D) {
            var1.h = false;
         }

         if(var1.h) {
            var7 = var1.a;
            var9 = var1.b;
            var11 = var1.c;
            var15 = var1.d - var1.b;
            if(!this.e.L() && (var15 > 1.65D || var15 < 0.1D)) {
               this.a("Illegal stance");
               a.warning(this.e.r + " had an illegal stance: " + var15);
               return;
            }

            if(Math.abs(var1.a) > 3.2E7D || Math.abs(var1.c) > 3.2E7D) {
               this.a("Illegal position");
               return;
            }
         }

         if(var1.i) {
            var17 = var1.e;
            var18 = var1.f;
         }

         this.e.a(true);
         this.e.br = 0.0F;
         this.e.b(this.j, this.k, this.l, var17, var18);
         if(!this.m) {
            return;
         }

         var15 = var7 - this.e.aP;
         double var19 = var9 - this.e.aQ;
         double var21 = var11 - this.e.aR;
         double var23 = var15 * var15 + var19 * var19 + var21 * var21;
         if(var23 > 100.0D) {
            a.warning(this.e.r + " moved too quickly!");
            this.a("You moved too quickly :( (Hacking?)");
            return;
         }

         float var25 = 0.0625F;
         boolean var26 = var2.a(this.e, this.e.aZ.b().e((double)var25, (double)var25, (double)var25)).isEmpty();
         this.e.a(var15, var19, var21);
         var15 = var7 - this.e.aP;
         if (var15 > -0.5d || var15 < 0.5d)
             var15 = 0.0d;
         var19 = var9 - this.e.aQ;
         if(var19 > -0.5D || var19 < 0.5D) {
            var19 = 0.0D;
         }

         var21 = var11 - this.e.aR;
         if (var21 > -0.5d || var21 < 0.5d)
             var21 = 0.0d;
         var23 = var15 * var15 + var19 * var19 + var21 * var21;
         boolean var27 = false;
         if(var23 > 0.0625D && !this.e.L()) {
            var27 = true;
            a.warning(this.e.r + " moved wrongly!");
            System.out.println("Got position " + var7 + ", " + var9 + ", " + var11);
            System.out.println("Expected " + this.e.aP + ", " + this.e.aQ + ", " + this.e.aR);
         }

         this.e.b(var7, var9, var11, var17, var18);
         boolean var28 = var2.a(this.e, this.e.aZ.b().e((double)var25, (double)var25, (double)var25)).size() == 0;
         if(var26 && (var27 || !var28) && !this.e.L()) {
            this.a(this.j, this.k, this.l, var17, var18);
            return;
         }

         OAxisAlignedBB var29 = this.e.aZ.b().b((double)var25, (double)var25, (double)var25).a(0.0D, -0.55D, 0.0D);
         // CanaryMod: Allow ops and people with ignoreRestrictions to fly
         if(!this.d.o && !var2.b(var29) && !d.f.h(e.r) && !getPlayer().canIgnoreRestrictions()) {
            if(var19 >= -0.03125D) {
               ++this.h;
               if(this.h > 80) {
                  a.warning(this.e.r + " was kicked for floating too long!");
                  this.a("Flying is not enabled on this server");
                  return;
               }
            }
         } else {
            this.h = 0;
         }

         this.e.ba = var1.g;
         this.d.f.d(this.e);
         this.e.b(this.e.aQ - var3, var1.g);
      }

   }

   public void a(double var1, double var3, double var5, float var7, float var8) {
      // CanaryMod: Teleportation hook
      Location from = new Location();
      from.x = var1;
      from.y = var3;
      from.z = var5;
      from.rotX = var7;
      from.rotY = var8;
      Player player = getPlayer();
      if ((Boolean) OEntity.manager.callHook(PluginLoader.Hook.TELEPORT, player, player.getLocation(), from))
         return;

      this.m = false;
      this.j = var1;
      this.k = var3;
      this.l = var5;
      this.e.b(var1, var3, var5, var7, var8);
      this.e.a.b((OPacket)(new OPacket13PlayerLookMove(var1, var3 + 1.6200000047683716D, var3, var5, var7, var8, false)));
   }

   // CanaryMod: Store x/y/z
   int x, y, z, type;
   
   public void a(OPacket14BlockDig var1) {
      OWorldServer var2 = this.d.a(this.e.s);
      if(var1.e == 4) {
         this.e.F();
      } else {
         // CanaryMod: We allow admins and ops to dig!
         boolean var3 = var2.D = var2.t.g != 0 || this.d.f.h(this.e.r) || getPlayer().isAdmin();
         boolean var4 = false;
         if(var1.e == 0) {
            var4 = true;
         }

         if(var1.e == 2) {
            var4 = true;
         }

         int var5 = var1.a;
         int var6 = var1.b;
         int var7 = var1.c;
         if(var4) {
            double var8 = this.e.aP - ((double)var5 + 0.5D);
            double var10 = this.e.aQ - ((double)var6 + 0.5D);
            double var12 = this.e.aR - ((double)var7 + 0.5D);
            double var14 = var8 * var8 + var10 * var10 + var12 * var12;
            if(var14 > 36.0D) {
               return;
            }
         }

         OChunkCoordinates var16 = var2.n();
         int var17 = (int)OMathHelper.e((float)(var5 - var16.a));
         int var18 = (int)OMathHelper.e((float)(var7 - var16.c));
         if(var17 > var18) {
            var18 = var17;
         }

         // CanaryMod: the player
         Player player = getPlayer();
         
         if(var1.e == 0) {
            // CanaryMod: Start digging
            // No buildrights
            if (!getPlayer().canBuild())
                return;
            // CanaryMod: Custom spawn prot size
            if(var18 <= etc.getInstance().getSpawnProtectionSize() && !var3) {
               this.e.a.b((OPacket)(new OPacket53BlockChange(var5, var6, var7, var2)));
            } else {
               // CanaryMod: Dig hooks
               Block block = var2.world.getBlockAt(var5, var6, var7);
               block.setStatus(0); // Started digging
               x = block.getX();
               y = block.getY();
               z = block.getZ();
               type = block.getType();
               if (!(Boolean) OEntity.manager.callHook(PluginLoader.Hook.BLOCK_DESTROYED, player, block))
                  this.e.c.a(var5, var6, var7, var1.d);
               else
                  this.e.a.b((OPacket)(new OPacket53BlockChange(var5, var6, var7, var2)));
            }
         } else if(var1.e == 2) {
            // CanaryMod: Break block
            Block block = var2.world.getBlockAt(var5, var6, var7);
            block.setStatus(2); // Block broken
            OEntity.manager.callHook(PluginLoader.Hook.BLOCK_DESTROYED, player, block);

            this.e.c.a(var5, var6, var7);
            if(var2.a(var5, var6, var7) != 0) {
               this.e.a.b((OPacket)(new OPacket53BlockChange(var5, var6, var7, var2)));
            }
         } else if(var1.e == 3) {
            // CanaryMod: Send block update
            Block block = new Block(var2.world, type, x, y, z);
            block.setStatus(3); // Send update for block
            OEntity.manager.callHook(PluginLoader.Hook.BLOCK_DESTROYED, player, block);

            double var19 = this.e.aP - ((double)var5 + 0.5D);
            double var21 = this.e.aQ - ((double)var6 + 0.5D);
            double var23 = this.e.aR - ((double)var7 + 0.5D);
            double var25 = var19 * var19 + var21 * var21 + var23 * var23;
            if(var25 < 256.0D) {
               this.e.a.b((OPacket)(new OPacket53BlockChange(var5, var6, var7, var2)));
            }
         }

         var2.D = false;
      }
   }

   // CanaryMod: Store the blocks between blockPlaced packets
   Block lastRightClicked;

   public void a(OPacket15Place var1) {
      OWorldServer var2 = this.d.a(this.e.s);
      OItemStack var3 = this.e.i.b();

      // CanaryMod: Store block data to call hooks
      // CanaryMod START
      Block blockClicked = null;
      Block blockPlaced = null;

      // We allow admins and ops to build!
      boolean var4 = var2.D = var2.t.g != 0 || this.d.f.h(this.e.r) || getPlayer().isAdmin();
      if (var1.d == 255) {
         // ITEM_USE -- if we have a lastRightClicked then it could be a
         // usable location
         blockClicked = lastRightClicked;
         lastRightClicked = null;
      } else {
         // RIGHTCLICK or BLOCK_PLACE .. or nothing
         blockClicked = new Block(var2.world, var2.world.getBlockIdAt(var1.a, var1.b, var1.c), var1.a, var1.b, var1.c);
         blockClicked.setFaceClicked(Block.Face.fromId(var1.d));
         lastRightClicked = blockClicked;
      }

      // If we clicked on something then we also have a location to place the
      // block
      if (blockClicked != null && var3 != null) {
         blockPlaced = new Block(var2.world, var3.c, blockClicked.getX(), blockClicked.getY(), blockClicked.getZ());
         switch (var1.d) {
            case 0:
               blockPlaced.setY(blockPlaced.getY() - 1);
               break;
            case 1:
               blockPlaced.setY(blockPlaced.getY() + 1);
               break;
            case 2:
               blockPlaced.setZ(blockPlaced.getZ() - 1);
               break;
            case 3:
               blockPlaced.setZ(blockPlaced.getZ() + 1);
               break;
            case 4:
               blockPlaced.setX(blockPlaced.getX() - 1);
               break;
            case 5:
               blockPlaced.setX(blockPlaced.getX() + 1);
               break;
         }
      }
      // CanaryMod: END
      
      if(var1.d == 255) {
         // CanaryMod: call our version with extra blockClicked/blockPlaced
         if (blockPlaced != null)
             // Set the type of block to what it currently is
             blockPlaced.setType(var2.world.getBlockIdAt(blockPlaced.getX(), blockPlaced.getY(), blockPlaced.getZ()));

         if(var3 == null) {
            return;
         }

         ((Digging) this.e.c).a(this.e, var2, var3, blockPlaced, blockClicked);
      } else {
         int var5 = var1.a;
         int var6 = var1.b;
         int var7 = var1.c;
         int var8 = var1.d;
         OChunkCoordinates var9 = var2.n();
         // CanaryMod : Fix stupid buggy spawn protection. (2 times)
         int var10 = (int) OMathHelper.e((var8 == 4 ? var5 - 1 : (var8 == 5 ? (var5 + 1) : var5)) - var9.a);
         int var11 = (int) OMathHelper.e((var8 == 2 ? var7 - 1 : (var8 == 3 ? (var7 + 1) : var7)) - var9.c);

         if(var10 > var11) {
            var11 = var10;
         }

         // CanaryMod: call BLOCK_RIGHTCLICKED
         Item item = (var3 != null) ? new Item(var3) : new Item(Item.Type.Air);
         Player player = getPlayer();
         boolean cancelled = (Boolean) OEntity.manager.callHook(PluginLoader.Hook.BLOCK_RIGHTCLICKED, player, blockClicked, item);

         // CanaryMod: call original BLOCK_CREATED
         OEntity.manager.callHook(PluginLoader.Hook.BLOCK_CREATED, player, blockPlaced, blockClicked, item.getItemId());
         // CanaryMod: If we were building inside spawn, bail! (unless ops/admin)

         if(this.m && this.e.e((double)var5 + 0.5D, (double)var6 + 0.5D, (double)var7 + 0.5D) < 64.0D && (var11 > etc.getInstance().getSpawnProtectionSize() || var4) && player.canBuild() && !cancelled) {
            this.e.c.a(this.e, var2, var3, var5, var6, var7, var8);
         } else {
            // CanaryMod: No point sending the client to update the blocks, you
            // weren't allowed to place!
            var2.D = false;
            return;
         }

         this.e.a.b((OPacket)(new OPacket53BlockChange(var5, var6, var7, var2)));
         if(var8 == 0) {
            --var6;
         }

         if(var8 == 1) {
            ++var6;
         }

         if(var8 == 2) {
            --var7;
         }

         if(var8 == 3) {
            ++var7;
         }

         if(var8 == 4) {
            --var5;
         }

         if(var8 == 5) {
            ++var5;
         }

         this.e.a.b((OPacket)(new OPacket53BlockChange(var5, var6, var7, var2)));
      }

      var3 = this.e.i.b();
      if(var3 != null && var3.a == 0) {
         this.e.i.a[this.e.i.c] = null;
      }

      this.e.h = true;
      this.e.i.a[this.e.i.c] = OItemStack.b(this.e.i.a[this.e.i.c]);
      OSlot var12 = this.e.k.a(this.e.i, this.e.i.c);
      this.e.k.a();
      this.e.h = false;
      if(!OItemStack.a(this.e.i.b(), var1.e)) {
         this.b((OPacket)(new OPacket103SetSlot(this.e.k.f, var12.a, this.e.i.b())));
      }

      var2.D = false;
   }

   public void a(String var1, Object[] var2) {
      // CanaryMod: disconnect!
      OEntity.manager.callHook(PluginLoader.Hook.DISCONNECT, getPlayer());
      a.info(this.e.r + " lost connection: " + var1);
      this.d.f.a((OPacket)(new OPacket3Chat("§e" + this.e.r + " left the game.")));
      this.d.f.e(this.e);
      this.c = true;
   }

   public void a(OPacket var1) {
      a.warning(this.getClass() + " wasn\'t prepared to deal with a " + var1.getClass());
      this.a("Protocol error, unexpected packet");
   }

   public void b(OPacket var1) {
      this.b.a(var1);
      this.g = this.f;
   }

   public void a(OPacket16BlockItemSwitch var1) {
      if(var1.a >= 0 && var1.a <= OInventoryPlayer.e()) {
         this.e.i.c = var1.a;
      } else {
         a.warning(this.e.r + " tried to set an invalid carried item");
      }
   }

   public void a(OPacket3Chat var1) {
      String var2 = var1.a;
      // CanaryMod: redirect chathandling to player class
      getPlayer().chat(var2);
   }

   private void c(String var1) {
      //Handled by PlayerCommands class
   }

   public void a(OPacket18Animation var1) {
      if(var1.b == 1) {
         // CanaryMod: Swing the arm!
         OEntity.manager.callHook(PluginLoader.Hook.ARM_SWING, getPlayer());
         this.e.w();
      }

   }

   public void a(OPacket19EntityAction var1) {
      if(var1.b == 1) {
         this.e.e(true);
      } else if(var1.b == 2) {
         this.e.e(false);
      } else if(var1.b == 3) {
         this.e.a(false, true, true);
         this.m = false;
      }

   }

   public void a(OPacket255KickDisconnect var1) {
      this.b.a("disconnect.quitting", new Object[0]);
   }

   public int b() {
      return this.b.e();
   }

   public void b(String var1) {
      this.b((OPacket)(new OPacket3Chat("§7" + var1)));
   }

   public String d() {
      return this.e.r;
   }

   public void a(OPacket7UseEntity var1) {
      OWorldServer var2 = this.d.a(this.e.s);
      OEntity var3 = var2.a(var1.b);
      if(var3 != null && this.e.e(var3) && this.e.g(var3) < 36.0D) {
         if(var1.c == 0) {
            this.e.c(var3);
         } else if(var1.c == 1) {
            this.e.d(var3);
         }
      }

   }

   public void a(OPacket9Respawn var1) {
      if(this.e.ab <= 0) {
         this.e = this.d.f.a(this.e, 0);
      }
   }

   public void a(OPacket101CloseWindow var1) {
      this.e.A();
   }

   public void a(OPacket102WindowClick var1) {
      if(this.e.k.f == var1.a && this.e.k.c(this.e)) {
         OItemStack var2 = this.e.k.a(var1.b, var1.c, var1.f, this.e);
         if(OItemStack.a(var1.e, var2)) {
            this.e.a.b((OPacket)(new OPacket106Transaction(var1.a, var1.d, true)));
            this.e.h = true;
            this.e.k.a();
            this.e.z();
            this.e.h = false;
         } else {
            this.n.put(Integer.valueOf(this.e.k.f), Short.valueOf(var1.d));
            this.e.a.b((OPacket)(new OPacket106Transaction(var1.a, var1.d, false)));
            this.e.k.a(this.e, false);
            ArrayList var3 = new ArrayList();

            for(int var4 = 0; var4 < this.e.k.e.size(); ++var4) {
               var3.add(((OSlot)this.e.k.e.get(var4)).a());
            }

            this.e.a(this.e.k, var3);
         }
      }

   }

   public void a(OPacket106Transaction var1) {
      Short var2 = (Short)this.n.get(Integer.valueOf(this.e.k.f));
      if(var2 != null && var1.b == var2.shortValue() && this.e.k.f == var1.a && !this.e.k.c(this.e)) {
         this.e.k.a(this.e, true);
      }

   }

   public void a(OPacket130UpdateSign var1) {
      OWorldServer var2 = this.d.a(this.e.s);
      if(var2.g(var1.a, var1.b, var1.c)) {
         OTileEntity var3 = var2.b(var1.a, var1.b, var1.c);
         if(var3 instanceof OTileEntitySign) {
            OTileEntitySign var4 = (OTileEntitySign)var3;
            if(!var4.a()) {
               this.d.c("Player " + this.e.r + " just tried to change non-editable sign");
               return;
            }
         }

         int var6;
         int var9;
         for(var9 = 0; var9 < 4; ++var9) {
            boolean var5 = true;
            // CanaryMod: Remove the char limit, for plugins.
            //if(var1.d[var10].length() > 15) {
            //   var5 = false;
            //} else {
               for(var6 = 0; var6 < var1.d[var9].length(); ++var6) {
                  if(OChatAllowedCharacters.a.indexOf(var1.d[var9].charAt(var6)) < 0) {
                     var5 = false;
                  }
               }
            //}

            if(!var5) {
               var1.d[var9] = "!?";
            }
         }

         if(var3 instanceof OTileEntitySign) {
            var9 = var1.a;
            int var10 = var1.b;
            var6 = var1.c;
            OTileEntitySign var7 = (OTileEntitySign)var3;
            // CanaryMod: Copy the old line text
            String[] old = Arrays.copyOf(var7.a, var7.a.length);

            for(int var8 = 0; var8 < 4; ++var8) {
               var7.a[var8] = var1.d[var8];
            }
            
            // CanaryMod: Check if we can change it
            Sign sign = new Sign(var7);
            if ((Boolean) OEntity.manager.callHook(PluginLoader.Hook.SIGN_CHANGE, getPlayer(), sign))
               var7.a = Arrays.copyOf(old, old.length);

            var7.a(false);
            var7.i();
            var2.h(var9, var10, var6);
         }
      }

   }

   public boolean c() {
      return true;
   }

   /**
    * Returns the item in player's hand
    *
    * @return item
    */
   public int getItemInHand() {
      if (e.i.b() != null)
         return e.i.b().c;
      return -1;
   }

   /**
    * Returns the player
    *
    * @return player
    */
   public Player getPlayer() {
      return e.getPlayer();
   }

   /**
    * Sends a message to the player
    *
    * @param msg
    */
   public void msg(String msg) {
      if (msg.length() >= 119) {
         String cutMsg = msg.substring(0, 118);
         int finalCut = cutMsg.lastIndexOf(" ");
         String subCut = cutMsg.substring(0, finalCut);
         String newMsg = msg.substring(finalCut + 1);
         b(new OPacket3Chat(subCut));
         msg(newMsg);
      } else {
         b(new OPacket3Chat(msg));
      }
   }

}
