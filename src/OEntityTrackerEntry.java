import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class OEntityTrackerEntry {

   public OEntity a;
   public int b;
   public int c;
   public int d;
   public int e;
   public int f;
   public int g;
   public int h;
   public double i;
   public double j;
   public double k;
   public int l = 0;
   private double o;
   private double p;
   private double q;
   private boolean r = false;
   private boolean s;
   private int t = 0;
   public boolean m = false;
   public Set n = new HashSet();


   public OEntityTrackerEntry(OEntity entity, int i, int j, boolean flag) {
      super();
      this.a = entity;
      this.b = i;
      this.c = j;
      this.s = flag;
      this.d = OMathHelper.b(entity.bf * 32.0D);
      this.e = OMathHelper.b(entity.bg * 32.0D);
      this.f = OMathHelper.b(entity.bh * 32.0D);
      this.g = OMathHelper.d(entity.bl * 256.0F / 360.0F);
      this.h = OMathHelper.d(entity.bm * 256.0F / 360.0F);
   }

   public boolean equals(Object obj) {
      return obj instanceof OEntityTrackerEntry?((OEntityTrackerEntry)obj).a.aW == this.a.aW:false;
   }

   public int hashCode() {
      return this.a.aW;
   }

   public void a(List list) {
      this.m = false;
      if(!this.r || this.a.e(this.o, this.p, this.q) > 16.0D) {
         this.o = this.a.bf;
         this.p = this.a.bg;
         this.q = this.a.bh;
         this.r = true;
         this.m = true;
         this.b(list);
      }

      ++this.t;
      if(++this.l % this.c == 0 || this.a.ca) {
         int i = OMathHelper.b(this.a.bf * 32.0D);
         int j = OMathHelper.b(this.a.bg * 32.0D);
         int k = OMathHelper.b(this.a.bh * 32.0D);
         int l = OMathHelper.d(this.a.bl * 256.0F / 360.0F);
         int i1 = OMathHelper.d(this.a.bm * 256.0F / 360.0F);
         int j1 = i - this.d;
         int k1 = j - this.e;
         int l1 = k - this.f;
         Object obj = null;
         boolean flag = Math.abs(i) >= 8 || Math.abs(j) >= 8 || Math.abs(k) >= 8;
         boolean flag1 = Math.abs(l - this.g) >= 8 || Math.abs(i1 - this.h) >= 8;
         if(j1 >= -128 && j1 < 128 && k1 >= -128 && k1 < 128 && l1 >= -128 && l1 < 128 && this.t <= 400) {
            if(flag && flag1) {
               obj = new OPacket33RelEntityMoveLook(this.a.aW, (byte)j1, (byte)k1, (byte)l1, (byte)l, (byte)i1);
            } else if(flag) {
               obj = new OPacket31RelEntityMove(this.a.aW, (byte)j1, (byte)k1, (byte)l1);
            } else if(flag1) {
               obj = new OPacket32EntityLook(this.a.aW, (byte)l, (byte)i1);
            }
         } else {
            this.t = 0;
            this.a.bf = (double)i / 32.0D;
            this.a.bg = (double)j / 32.0D;
            this.a.bh = (double)k / 32.0D;
            obj = new OPacket34EntityTeleport(this.a.aW, i, j, k, (byte)l, (byte)i1);
         }

         if(this.s) {
            double d = this.a.bi - this.i;
            double d1 = this.a.bj - this.j;
            double d2 = this.a.bk - this.k;
            double d3 = 0.02D;
            double d4 = d * d + d1 * d1 + d2 * d2;
            if(d4 > d3 * d3 || d4 > 0.0D && this.a.bi == 0.0D && this.a.bj == 0.0D && this.a.bk == 0.0D) {
               this.i = this.a.bi;
               this.j = this.a.bj;
               this.k = this.a.bk;
               this.a((OPacket)(new OPacket28EntityVelocity(this.a.aW, this.i, this.j, this.k)));
            }
         }

         if(obj != null) {
            this.a((OPacket)((OPacket)obj));
         }

         ODataWatcher datawatcher = this.a.al();
         if(datawatcher.a()) {
            this.b((OPacket)(new OPacket40EntityMetadata(this.a.aW, datawatcher)));
         }

         if(flag) {
            this.d = i;
            this.e = j;
            this.f = k;
         }

         if(flag1) {
            this.g = l;
            this.h = i1;
         }
      }

      this.a.ca = false;
      if(this.a.bu) {
         this.b((OPacket)(new OPacket28EntityVelocity(this.a)));
         this.a.bu = false;
      }

   }

   public void a(OPacket packet) {
      Iterator iterator = this.n.iterator();

      while(iterator.hasNext()) {
         OEntityPlayerMP entityplayermp = (OEntityPlayerMP)iterator.next();
         entityplayermp.a.b(packet);
      }

   }

   public void b(OPacket packet) {
      this.a(packet);
      if(this.a instanceof OEntityPlayerMP) {
         ((OEntityPlayerMP)this.a).a.b(packet);
      }

   }

   public void a() {
      this.a((OPacket)(new OPacket29DestroyEntity(this.a.aW)));
   }

   public void a(OEntityPlayerMP entityplayermp) {
      if(this.n.contains(entityplayermp)) {
         this.n.remove(entityplayermp);
      }

   }

   public void b(OEntityPlayerMP entityplayermp) {
      if(entityplayermp != this.a) {
         double d = entityplayermp.bf - (double)(this.d / 32);
         double d1 = entityplayermp.bh - (double)(this.f / 32);
         if(d >= (double)(-this.b) && d <= (double)this.b && d1 >= (double)(-this.b) && d1 <= (double)this.b) {
            if(!this.n.contains(entityplayermp)) {
               this.n.add(entityplayermp);
               entityplayermp.a.b(this.b());
               if(this.s) {
                  entityplayermp.a.b((OPacket)(new OPacket28EntityVelocity(this.a.aW, this.a.bi, this.a.bj, this.a.bk)));
               }

               OItemStack[] aitemstack = this.a.l_();
               if(aitemstack != null) {
                  for(int entityliving = 0; entityliving < aitemstack.length; ++entityliving) {
                     entityplayermp.a.b((OPacket)(new OPacket5PlayerInventory(this.a.aW, entityliving, aitemstack[entityliving])));
                  }
               }

               if(this.a instanceof OEntityPlayer) {
                  OEntityPlayer var11 = (OEntityPlayer)this.a;
                  if(var11.P()) {
                     entityplayermp.a.b((OPacket)(new OPacket17Sleep(this.a, 0, OMathHelper.b(this.a.bf), OMathHelper.b(this.a.bg), OMathHelper.b(this.a.bh))));
                  }
               }

               if(this.a instanceof OEntityLiving) {
                  OEntityLiving var10 = (OEntityLiving)this.a;
                  Iterator iterator = var10.ak().iterator();

                  while(iterator.hasNext()) {
                     OPotionEffect potioneffect = (OPotionEffect)iterator.next();
                     entityplayermp.a.b((OPacket)(new OPacket41EntityEffect(this.a.aW, potioneffect)));
                  }
               }
            }
         } else if(this.n.contains(entityplayermp)) {
            this.n.remove(entityplayermp);
            entityplayermp.a.b((OPacket)(new OPacket29DestroyEntity(this.a.aW)));
         }

      }
   }

   public void b(List list) {
      for(int i = 0; i < list.size(); ++i) {
         this.b((OEntityPlayerMP)list.get(i));
      }

   }

   private OPacket b() {
      // CanaryMod: Modloader entitytracking if enabled
      EntityTrackerEntry2 entitytrackerentry2 = etc.getInstance().isModLoaderMPEnabled() ? ModLoaderMp.HandleEntityTrackerEntries(this.a):null;
      if(entitytrackerentry2 != null) {
         try {
            if(this.a instanceof ISpawnable) {
               Packet230ModLoader entityfallingsand6 = ((ISpawnable)this.a).getSpawnPacket();
               entityfallingsand6.modId = "Spawn".hashCode();
               if(entitytrackerentry2.entityId > 127) {
                  entityfallingsand6.packetType = entitytrackerentry2.entityId - 256;
               } else {
                  entityfallingsand6.packetType = entitytrackerentry2.entityId;
               }

               return entityfallingsand6;
            } else if(!entitytrackerentry2.entityHasOwner) {
               return new OPacket23VehicleSpawn(this.a, entitytrackerentry2.entityId);
            } else {
               Field entityfallingsand5 = this.a.getClass().getField("owner");
               if(OEntity.class.isAssignableFrom(entityfallingsand5.getType())) {
                  OEntity packet23vehiclespawn2 = (OEntity)entityfallingsand5.get(this.a);
                  return new OPacket23VehicleSpawn(this.a, entitytrackerentry2.entityId, packet23vehiclespawn2 == null?this.a.aW:packet23vehiclespawn2.aW);
               } else {
                  throw new Exception(String.format("Entity\'s owner field must be of type Entity, but it is of type %s.", new Object[]{entityfallingsand5.getType()}));
               }
            }
         } catch (Exception var4) {
            ModLoader.getLogger().throwing("EntityTrackerEntry", "getSpawnPacket", var4);
            ModLoader.ThrowException(String.format("Error sending spawn packet for entity of type %s.", new Object[]{this.a.getClass()}), var4);
            return null;
         }
      } else if(this.a instanceof OEntityItem) {
         OEntityItem entityfallingsand4 = (OEntityItem)this.a;
         OPacket21PickupSpawn packet23vehiclespawn1 = new OPacket21PickupSpawn(entityfallingsand4);
         entityfallingsand4.bf = (double)packet23vehiclespawn1.b / 32.0D;
         entityfallingsand4.bg = (double)packet23vehiclespawn1.c / 32.0D;
         entityfallingsand4.bh = (double)packet23vehiclespawn1.d / 32.0D;
         return packet23vehiclespawn1;
      } else if(this.a instanceof OEntityPlayerMP) {
         return new OPacket20NamedEntitySpawn((OEntityPlayer)this.a);
      } else {
         if(this.a instanceof OEntityMinecart) {
            OEntityMinecart entityfallingsand = (OEntityMinecart)this.a;
            if(entityfallingsand.d == 0) {
               return new OPacket23VehicleSpawn(this.a, 10);
            }

            if(entityfallingsand.d == 1) {
               return new OPacket23VehicleSpawn(this.a, 11);
            }

            if(entityfallingsand.d == 2) {
               return new OPacket23VehicleSpawn(this.a, 12);
            }
         }

         if(this.a instanceof OEntityBoat) {
            return new OPacket23VehicleSpawn(this.a, 1);
         } else if(this.a instanceof OIAnimals) {
            return new OPacket24MobSpawn((OEntityLiving)this.a);
         } else if(this.a instanceof OEntityFish) {
            return new OPacket23VehicleSpawn(this.a, 90);
         } else if(this.a instanceof OEntityArrow) {
            OEntity entityfallingsand3 = ((OEntityArrow)this.a).c;
            return new OPacket23VehicleSpawn(this.a, 60, entityfallingsand3 == null?this.a.aW:entityfallingsand3.aW);
         } else if(this.a instanceof OEntitySnowball) {
            return new OPacket23VehicleSpawn(this.a, 61);
         } else if(this.a instanceof OEntityFireball) {
            OEntityFireball entityfallingsand2 = (OEntityFireball)this.a;
            OPacket23VehicleSpawn packet23vehiclespawn = new OPacket23VehicleSpawn(this.a, 63, ((OEntityFireball)this.a).b.aW);
            packet23vehiclespawn.e = (int)(entityfallingsand2.c * 8000.0D);
            packet23vehiclespawn.f = (int)(entityfallingsand2.d * 8000.0D);
            packet23vehiclespawn.g = (int)(entityfallingsand2.e * 8000.0D);
            return packet23vehiclespawn;
         } else if(this.a instanceof OEntityEgg) {
            return new OPacket23VehicleSpawn(this.a, 62);
         } else if(this.a instanceof OEntityTNTPrimed) {
            return new OPacket23VehicleSpawn(this.a, 50);
         } else {
            if(this.a instanceof OEntityFallingSand) {
               OEntityFallingSand entityfallingsand1 = (OEntityFallingSand)this.a;
               if(entityfallingsand1.a == OBlock.F.bA) {
                  return new OPacket23VehicleSpawn(this.a, 70);
               }

               if(entityfallingsand1.a == OBlock.G.bA) {
                  return new OPacket23VehicleSpawn(this.a, 71);
               }
            }

            if(this.a instanceof OEntityPainting) {
               return new OPacket25EntityPainting((OEntityPainting)this.a);
            } else if(this.a instanceof OEntityXPOrb) {
               return new OPacket26EntityExpOrb((OEntityXPOrb)this.a);
            } else {
               throw new IllegalArgumentException("Don\'t know how to add " + this.a.getClass() + "!");
            }
         }
      }
   }

   public void c(OEntityPlayerMP entityplayermp) {
      if(this.n.contains(entityplayermp)) {
         this.n.remove(entityplayermp);
         entityplayermp.a.b((OPacket)(new OPacket29DestroyEntity(this.a.aW)));
      }

   }
}
