import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

import net.minecraft.server.MinecraftServer;

public class OWorldServer extends OWorld {

    public OChunkProviderServer w;
    public boolean              x = false;
    public boolean              y;
    private MinecraftServer     z;
    private OMCHashTable        A = new OMCHashTable();

    public OWorldServer(MinecraftServer paramMinecraftServer, OISaveHandler paramOISaveHandler, String paramString, int paramInt, long paramLong) {
        super(paramOISaveHandler, paramString, paramLong, OWorldProvider.a(paramInt));
        z = paramMinecraftServer;
    }

    @Override
    public void a(OEntity paramOEntity, boolean paramBoolean) {
        if ((!z.m) && (((paramOEntity instanceof OEntityAnimals)) || ((paramOEntity instanceof OEntityWaterMob))))
            paramOEntity.G();
        if ((paramOEntity.aF == null) || (!(paramOEntity.aF instanceof OEntityPlayer)))
            super.a(paramOEntity, paramBoolean);
    }

    public void b(OEntity paramOEntity, boolean paramBoolean) {
        super.a(paramOEntity, paramBoolean);
    }

    @Override
    protected OIChunkProvider b() {
        OIChunkLoader localOIChunkLoader = r.a(o);
        w = new OChunkProviderServer(this, localOIChunkLoader, o.c());
        return w;
    }

    public List d(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
        ArrayList localArrayList = new ArrayList();
        for (int i = 0; i < c.size(); i++) {
            OTileEntity localOTileEntity = (OTileEntity) c.get(i);
            if ((localOTileEntity.e >= paramInt1) && (localOTileEntity.f >= paramInt2) && (localOTileEntity.g >= paramInt3) && (localOTileEntity.e < paramInt4) && (localOTileEntity.f < paramInt5) && (localOTileEntity.g < paramInt6))
                localArrayList.add(localOTileEntity);
        }
        return localArrayList;
    }

    @Override
    public boolean a(OEntityPlayer paramOEntityPlayer, int paramInt1, int paramInt2, int paramInt3) {
        int i = (int) OMathHelper.e(paramInt1 - s.c());
        int j = (int) OMathHelper.e(paramInt3 - s.e());
        if (i > j)
            j = i;
        return (j > 16) || (z.f.h(paramOEntityPlayer.r));
    }

    @Override
    protected void c(OEntity paramOEntity) {
        super.c(paramOEntity);
        A.a(paramOEntity.aC, paramOEntity);
    }

    @Override
    protected void d(OEntity paramOEntity) {
        super.d(paramOEntity);
        A.d(paramOEntity.aC);
    }

    public OEntity a(int paramInt) {
        return (OEntity) A.a(paramInt);
    }
    
    @Override
    public boolean a(OEntity var1) {
        if (super.a(var1)) {
            z.f.a(var1.aL, var1.aM, var1.aN, 512.0D, new OPacket71Weather(var1));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void a(OEntity paramOEntity, byte paramByte) {
        OPacket38 localOPacket38 = new OPacket38(paramOEntity.aC, paramByte);
        z.k.b(paramOEntity, localOPacket38);
    }

    @Override
    public OExplosion a(OEntity paramOEntity, double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat, boolean paramBoolean) {
        OExplosion localOExplosion = new OExplosion(this, paramOEntity, paramDouble1, paramDouble2, paramDouble3, paramFloat);
        localOExplosion.a = paramBoolean;
        localOExplosion.a();
        localOExplosion.a(false);
        z.f.a(paramDouble1, paramDouble2, paramDouble3, 64.0D, new OPacket60(paramDouble1, paramDouble2, paramDouble3, paramFloat, localOExplosion.g));
        return localOExplosion;
    }

    @Override
    public void d(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
        super.d(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
        z.f.a(paramInt1, paramInt2, paramInt3, 64.0D, new OPacket54(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5));
    }

    public void w() {
        r.e();
    }
    
    @Override
    protected void i() {
        boolean var1 = this.v();
        super.i();
        if (var1 != this.v()) {
            if (var1) {
                this.z.f.a((OPacket) (new OPacket70(2)));
            } else {
                this.z.f.a((OPacket) (new OPacket70(1)));
            }
        }
    }

    @Override
    public boolean b(OEntity entity) {
        if (entity instanceof OEntityLiving && !(entity instanceof OEntityPlayer))
            // CanaryMod: allow entities to spawn
            if ((etc.getInstance().getMobSpawnRate() < 100 && etc.getInstance().getMobSpawnRate() > 0 && etc.getInstance().getMobSpawnRate() <= m.nextInt(101)) || etc.getInstance().getMobSpawnRate() <= 0 || (Boolean) (etc.getLoader().callHook(PluginLoader.Hook.MOB_SPAWN, new Mob((OEntityLiving) entity))))
                return false;

        return super.b(entity);
    }
}
