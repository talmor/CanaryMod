import java.util.List;

public abstract class OEntityPlayer extends OEntityLiving {
    public OInventoryPlayer     i = new OInventoryPlayer(this);
    public OContainer           j;
    public OContainer           k;
    public byte                 l = 0;
    public int                  m = 0;
    public float                n;
    public float                o;
    public boolean              p = false;
    public int                  q = 0;
    public String               r;
    public int                  s;
    public double               t;
    public double               u;
    public double               v;
    public double               w;
    public double               x;
    public double               y;
    protected boolean           z;
    private OChunkCoordinates   A;
    private int                 a;
    public float                B;
    public float                C;
    private OChunkCoordinates   b;
    private OChunkCoordinates   c;
    public int                  D = 20;
    protected boolean           E = false;
    public float                F;
    private int                 d = 0;
    public OEntityFish          G = null;

    // CanaryMod start
    HumanEntity                 entity = new HumanEntity(this);

    // CanaryMod end

    
    public OEntityPlayer(OWorld paramOWorld) {
        super(paramOWorld);

        j = new OContainerPlayer(i, !paramOWorld.B);

        k = j;

        bi = 1.62F;
        OChunkCoordinates localOChunkCoordinates = paramOWorld.n();
        c(localOChunkCoordinates.a + 0.5D, localOChunkCoordinates.b + 1, localOChunkCoordinates.c + 0.5D, 0.0F, 0.0F);

        ab = 20;
        U = "humanoid";
        T = 180.0F;
        bx = 20;

        R = "/mob/char.png";
    }

    @Override
    protected void b() {
        super.b();

        bE.a(16, (byte) 0);
    }

    @Override
    public void o_() {
        if (K()) {
            a += 1;
            if (a > 100)
                a = 100;
            if (!aL.B)
                if (!o())
                    a(true, true, false);
                else if (aL.d())
                    a(false, true, true);
        } else if (a > 0) {
            a += 1;
            if (a >= 110)
                a = 0;
        }

        super.o_();

        if ((!aL.B) && (k != null) && (!k.b(this))) {
            x();
            k = j;
        }

        t = w;
        u = x;
        v = y;

        double d1 = aP - w;
        double d2 = aQ - x;
        double d3 = aR - y;

        double d4 = 10.0D;
        if (d1 > d4)
            t = (w = aP);
        if (d3 > d4)
            v = (y = aR);
        if (d2 > d4)
            u = (x = aQ);
        if (d1 < -d4)
            t = (w = aP);
        if (d3 < -d4)
            v = (y = aR);
        if (d2 < -d4)
            u = (x = aQ);

        w += d1 * 0.25D;
        y += d3 * 0.25D;
        x += d2 * 0.25D;

        a(OStatList.k, 1);
        
        if (aK == null)
            c = null;
    }

    @Override
    protected boolean C() {
        return (ab <= 0) || (K());
    }

    protected void x() {
        k = j;
    }

    @Override
    public void D() {
        double oldX = aP, oldY = aQ, oldZ = aR;
        super.D();
        n = o;
        o = 0.0F;
        i(aP - oldX, aQ - oldY, aR - oldZ);
    }

    @Override
    protected void c_() {
        if (p) {
            q += 1;
            if (q >= 8) {
                q = 0;
                p = false;
            }
        } else
            q = 0;

        aa = (q / 8.0F);
    }

    @Override
    public void u() {
         // CanaryMod: adjust 'healing over time' independent of
         // monster-spawn=true/false (nice notchup!)
         PluginLoader.HookResult autoHeal = etc.getInstance().autoHeal();
         if ((aL.q == 0) && (autoHeal == PluginLoader.HookResult.DEFAULT_ACTION) || autoHeal == PluginLoader.HookResult.ALLOW_ACTION)
             if ((ab < 20) && (bw % 20 * 12 == 0))
                 b(1);

        i.f();
        n = o;

        super.u();

        float f1 = OMathHelper.a(aS * aS + aU * aU);
        float f2 = (float) Math.atan(-aT * 0.2D) * 15.0F;
        if (f1 > 0.1F)
            f1 = 0.1F;
        if ((!ba) || (ab <= 0))
            f1 = 0.0F;
        if ((ba) || (ab <= 0))
            f2 = 0.0F;
        o += (f1 - o) * 0.4F;
        aj += (f2 - aj) * 0.8F;

        if (ab > 0) {
            List localList = aL.b(this, aZ.b(1.0D, 0.0D, 1.0D));
            if (localList != null)
                for (int i1 = 0; i1 < localList.size(); i1++) {
                    OEntity localOEntity = (OEntity) localList.get(i1);
                    if (!localOEntity.bh)
                        i(localOEntity);
                }
        }
    }

    private void i(OEntity paramOEntity) {
        paramOEntity.b(this);
    }

    @Override
    public void a(OEntity paramOEntity) {
        super.a(paramOEntity);
        b(0.2F, 0.2F);
        a(aP, aQ, aR);
        aT = 0.1D;

        if (r.equals("Notch"))
            a(new OItemStack(OItem.h, 1), true);
        i.h();

        if (paramOEntity != null) {
            aS = (-OMathHelper.b((ab + aV) * 3.141593F / 180.0F) * 0.1F);
            aU = (-OMathHelper.a((ab + aV) * 3.141593F / 180.0F) * 0.1F);
        } else
            aS = (aU = 0.0D);
        bi = 0.1F;

        a(OStatList.y, 1);
    }

    @Override
    public void c(OEntity paramOEntity, int paramInt) {
        m += paramInt;

        if ((paramOEntity instanceof OEntityPlayer))
            a(OStatList.A, 1);
        else
            a(OStatList.z, 1);
    }

    public void E() {
        a(i.a(i.c, 1), false);
    }

    public void b(OItemStack paramOItemStack) {
        a(paramOItemStack, false);
    }

    public void a(OItemStack paramOItemStack, boolean paramBoolean) {
        if (paramOItemStack == null)
            return;

        OEntityItem localOEntityItem = new OEntityItem(aL, aP, aQ - 0.3D + s(), aR, paramOItemStack);
        localOEntityItem.c = 40;

        float f1 = 0.1F;
        float f2;
        if (paramBoolean) {
            f2 = bv.nextFloat() * 0.5F;
            float f3 = bv.nextFloat() * 3.141593F * 2.0F;
            localOEntityItem.aS = (-OMathHelper.a(f3) * f2);
            localOEntityItem.aU = (OMathHelper.b(f3) * f2);
            localOEntityItem.aT = 0.2D;
        } else {
            f1 = 0.3F;
            localOEntityItem.aS = (-OMathHelper.a(aV / 180.0F * 3.141593F) * OMathHelper.b(aW / 180.0F * 3.141593F) * f1);
            localOEntityItem.aU = (OMathHelper.b(aV / 180.0F * 3.141593F) * OMathHelper.b(aW / 180.0F * 3.141593F) * f1);
            localOEntityItem.aT = (-OMathHelper.a(aW / 180.0F * 3.141593F) * f1 + 0.1F);
            f1 = 0.02F;

            f2 = bv.nextFloat() * 3.141593F * 2.0F;
            f1 *= bv.nextFloat();
            localOEntityItem.aS += Math.cos(f2) * f1;
            localOEntityItem.aT += (bv.nextFloat() - bv.nextFloat()) * 0.1F;
            localOEntityItem.aU += Math.sin(f2) * f1;
        }

        if (!(Boolean) manager.callHook(PluginLoader.Hook.ITEM_DROP, ((OEntityPlayerMP) this).getPlayer(), new Item(paramOItemStack))){
            a(localOEntityItem);
            a(OStatList.v, 1);
        // return the item to the inventory.
        } else
            i.a(paramOItemStack);

    }

    protected void a(OEntityItem paramOEntityItem) {
        aL.b(paramOEntityItem);
    }

    public float a(OBlock paramOBlock) {
        float f = i.a(paramOBlock);
        if (a(OMaterial.g))
            f /= 5.0F;
        if (!ba)
            f /= 5.0F;

        return f;
    }

    public boolean b(OBlock paramOBlock) {
        return i.b(paramOBlock);
    }

    @Override
    public void a(ONBTTagCompound paramONBTTagCompound) {
        super.a(paramONBTTagCompound);
        ONBTTagList localONBTTagList = paramONBTTagCompound.l("Inventory");
        i.b(localONBTTagList);
        s = paramONBTTagCompound.e("Dimension");
        z = paramONBTTagCompound.m("Sleeping");
        a = paramONBTTagCompound.d("SleepTimer");

        if (z) {
            A = new OChunkCoordinates(OMathHelper.b(aP), OMathHelper.b(aQ), OMathHelper.b(aR));
            a(true, true, false);
        }

        if ((paramONBTTagCompound.b("SpawnX")) && (paramONBTTagCompound.b("SpawnY")) && (paramONBTTagCompound.b("SpawnZ")))
            b = new OChunkCoordinates(paramONBTTagCompound.e("SpawnX"), paramONBTTagCompound.e("SpawnY"), paramONBTTagCompound.e("SpawnZ"));
    }

    @Override
    public void b(ONBTTagCompound paramONBTTagCompound) {
        super.b(paramONBTTagCompound);
        paramONBTTagCompound.a("Inventory", i.a(new ONBTTagList()));
        paramONBTTagCompound.a("Dimension", s);
        paramONBTTagCompound.a("Sleeping", z);
        paramONBTTagCompound.a("SleepTimer", (short) a);

        if (b != null) {
            paramONBTTagCompound.a("SpawnX", b.a);
            paramONBTTagCompound.a("SpawnY", b.b);
            paramONBTTagCompound.a("SpawnZ", b.c);
        }
    }

    public void a(OIInventory paramOIInventory) {
    }

    public void b(int paramInt1, int paramInt2, int paramInt3) {
    }

    public void b(OEntity paramOEntity, int paramInt) {
    }

    @Override
    public float s() {
        return 0.12F;
    }

    protected void j_() {
        bi = 1.62F;
    }

    @Override
    public boolean a(OEntity paramOEntity, int paramInt) {
        ay = 0;
        if (ab <= 0)
            return false;

        if (K() && !aL.B)
            a(true, true, false);

        if (((paramOEntity instanceof OEntityMob)) || ((paramOEntity instanceof OEntityArrow))) {
            if (aL.q == 0)
                paramInt = 0;
            if (aL.q == 1)
                paramInt = paramInt / 3 + 1;
            if (aL.q == 3)
                paramInt = paramInt * 3 / 2;
        }

        if (paramInt == 0)
            return false;

        Object localObject = paramOEntity;
        if (((localObject instanceof OEntityArrow)) && (((OEntityArrow) localObject).c != null))
            localObject = ((OEntityArrow) localObject).c;

        if ((localObject instanceof OEntityLiving))
            a((OEntityLiving) localObject, false);

        a(OStatList.x, paramInt);

        return super.a(paramOEntity, paramInt);
    }

    protected boolean t() {
        return false;
    }

    protected void a(OEntityLiving paramOEntityLiving, boolean paramBoolean) {
        if (((paramOEntityLiving instanceof OEntityCreeper)) || ((paramOEntityLiving instanceof OEntityGhast)))
            return;

        if ((paramOEntityLiving instanceof OEntityWolf)) {
            OEntityWolf localObject = (OEntityWolf) paramOEntityLiving;
            if ((((OEntityWolf) localObject).A()) && (r.equals(((OEntityWolf) localObject).x())))
                return;

        }

        if (!(paramOEntityLiving instanceof OEntityPlayer) || t()) {
            List<OEntityWolf> localListOEntity = aL.a(OEntityWolf.class, OAxisAlignedBB.b(aP, aQ, aR, aP + 1.0D, aQ + 1.0D, aR + 1.0D).b(16.0D, 4.0D, 16.0D));
            for (OEntityWolf localOEntityWolf : localListOEntity) {
                if ((localOEntityWolf.A()) && (localOEntityWolf.E() == null) && (r.equals(localOEntityWolf.x())) && ((!paramBoolean) || (!localOEntityWolf.y()))) {
                    localOEntityWolf.b(false);
                    localOEntityWolf.c(paramOEntityLiving);
                }
            }
        }
    }

    @Override
    protected void c(int paramInt) {
        int i1 = 25 - i.g();
        int i2 = paramInt * i1 + d;
        i.c(paramInt);
        paramInt = i2 / 25;
        d = (i2 % 25);
        super.c(paramInt);
    }

    public void a(OTileEntityFurnace paramOTileEntityFurnace) {
    }

    public void a(OTileEntityDispenser paramOTileEntityDispenser) {
    }

    public void a(OTileEntitySign paramOTileEntitySign) {
    }

    public void c(OEntity paramOEntity) {
        if (paramOEntity.a(this))
            return;
        OItemStack localOItemStack = F();
        if ((localOItemStack != null) && ((paramOEntity instanceof OEntityLiving))) {
            localOItemStack.a((OEntityLiving) paramOEntity);
            if (localOItemStack.a <= 0) {
                localOItemStack.a(this);
                G();
            }
        }
    }

    public OItemStack F() {
        return i.b();
    }

    public void G() {
        i.a(i.c, null);
    }

    @Override
    public double H() {
        return bi - 0.5F;
    }

    public void k_() {
        q = -1;
        p = true;
    }

    public void d(OEntity paramOEntity) {
        int i1 = i.a(paramOEntity);
        if (i1 > 0) {
            if (aT < 0.0D)
                i1++;
            paramOEntity.a(this, i1);
            OItemStack localOItemStack = F();
            if ((localOItemStack != null) && ((paramOEntity instanceof OEntityLiving))) {
                localOItemStack.a((OEntityLiving) paramOEntity, this);
                if (localOItemStack.a <= 0) {
                    localOItemStack.a(this);
                    G();
                }
            }
            if ((paramOEntity instanceof OEntityLiving)) {
                if (paramOEntity.S())
                    a((OEntityLiving) paramOEntity, true);
                a(OStatList.w, i1);
            }
        }
    }

    public void a(OItemStack paramOItemStack) {
    }

    @Override
    public void I() {
        super.I();
        j.a(this);
        if (k != null)
            k.a(this);
    }

    @Override
    public boolean J() {
        return (!z) && (super.J());
    }

    public OEnumStatus a(int paramInt1, int paramInt2, int paramInt3) {
        if ((K()) || (!S()))
            return OEnumStatus.e;

        if (aL.t.c)
            return OEnumStatus.b;
        if (aL.d())
            return OEnumStatus.c;
        if ((Math.abs(aP - paramInt1) > 3.0D) || (Math.abs(aQ - paramInt2) > 2.0D) || (Math.abs(aR - paramInt3) > 3.0D))
            return OEnumStatus.d;

        b(0.2F, 0.2F);
        bi = 0.2F;
        if (aL.f(paramInt1, paramInt2, paramInt3)) {
            int i1 = aL.b(paramInt1, paramInt2, paramInt3);
            int i2 = OBlockBed.c(i1);
            float f1 = 0.5F;
            float f2 = 0.5F;

            switch (i2) {
                case 0:
                    f2 = 0.9F;
                    break;
                case 2:
                    f2 = 0.1F;
                    break;
                case 1:
                    f1 = 0.1F;
                    break;
                case 3:
                    f1 = 0.9F;
            }

            e(i2);
            a(paramInt1 + f1, paramInt2 + 0.9375F, paramInt3 + f2);
        } else
            a(paramInt1 + 0.5F, paramInt2 + 0.9375F, paramInt3 + 0.5F);
        z = true;
        a = 0;
        A = new OChunkCoordinates(paramInt1, paramInt2, paramInt3);
        aS = (aU = aT = 0.0D);

        if (!aL.B)
            aL.r();

        return OEnumStatus.a;
    }

    private void e(int paramInt) {
        B = 0.0F;
        C = 0.0F;

        switch (paramInt) {
            case 0:
                C = -1.8F;
                break;
            case 2:
                C = 1.8F;
                break;
            case 1:
                B = 1.8F;
                break;
            case 3:
                B = -1.8F;
        }
    }

    public void a(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) {
        b(0.6F, 1.8F);
        j_();

        OChunkCoordinates localOChunkCoordinates1 = A;
        OChunkCoordinates localOChunkCoordinates2 = A;
        if ((localOChunkCoordinates1 != null) && (aL.a(localOChunkCoordinates1.a, localOChunkCoordinates1.b, localOChunkCoordinates1.c) == OBlock.T.bn)) {
            OBlockBed.a(aL, localOChunkCoordinates1.a, localOChunkCoordinates1.b, localOChunkCoordinates1.c, false);

            localOChunkCoordinates2 = OBlockBed.g(aL, localOChunkCoordinates1.a, localOChunkCoordinates1.b, localOChunkCoordinates1.c, 0);
            if (localOChunkCoordinates2 == null)
                localOChunkCoordinates2 = new OChunkCoordinates(localOChunkCoordinates1.a, localOChunkCoordinates1.b + 1, localOChunkCoordinates1.c);
            a(localOChunkCoordinates2.a + 0.5F, localOChunkCoordinates2.b + bi + 0.1F, localOChunkCoordinates2.c + 0.5F);
        }

        z = false;
        if ((!aL.B) && (paramBoolean2))
            aL.r();
        if (paramBoolean1)
            a = 0;
        else
            a = 100;
        if (paramBoolean3)
            a(A);
    }

    private boolean o() {
        return aL.a(A.a, A.b, A.c) == OBlock.T.bn;
    }

    public static OChunkCoordinates a(OWorld paramOWorld, OChunkCoordinates paramOChunkCoordinates) {
        OIChunkProvider localOIChunkProvider = paramOWorld.o();
        localOIChunkProvider.c(paramOChunkCoordinates.a - 3 >> 4, paramOChunkCoordinates.c - 3 >> 4);
        localOIChunkProvider.c(paramOChunkCoordinates.a + 3 >> 4, paramOChunkCoordinates.c - 3 >> 4);
        localOIChunkProvider.c(paramOChunkCoordinates.a - 3 >> 4, paramOChunkCoordinates.c + 3 >> 4);
        localOIChunkProvider.c(paramOChunkCoordinates.a + 3 >> 4, paramOChunkCoordinates.c + 3 >> 4);

        if (paramOWorld.a(paramOChunkCoordinates.a, paramOChunkCoordinates.b, paramOChunkCoordinates.c) != OBlock.T.bn)
            return null;

        OChunkCoordinates localOChunkCoordinates = OBlockBed.g(paramOWorld, paramOChunkCoordinates.a, paramOChunkCoordinates.b, paramOChunkCoordinates.c, 0);
        return localOChunkCoordinates;
    }

    @Override
    public boolean K() {
        return z;
    }

    public boolean L() {
        return (z) && (a >= 100);
    }

    public void a(String paramString) {
    }

    public OChunkCoordinates M() {
        return b;
    }

    public void a(OChunkCoordinates paramOChunkCoordinates) {
        if (paramOChunkCoordinates != null)
            b = new OChunkCoordinates(paramOChunkCoordinates);
        else
            b = null;
    }

    public void a(OStatBase var1) {
        a(var1, 1);
    }

    public void a(OStatBase paramOStatBasic, int paramInt) {
    }

    @Override
    protected void N() {
        super.N();
        a(OStatList.u, 1);
    }

    @Override
    public void a(float paramFloat1, float paramFloat2) {
        double d1 = aP;
        double d2 = aQ;
        double d3 = aR;

        super.a(paramFloat1, paramFloat2);

        g(aP - d1, aQ - d2, aR - d3);
    }

    private void h(double var1, double var3, double var5) {
        if (this.aK == null) {
            int var7;
            if (this.a(OMaterial.g)) {
                var7 = Math.round(OMathHelper.a(var1 * var1 + var3 * var3 + var5 * var5) * 100.0F);
                if (var7 > 0) {
                    this.a(OStatList.q, var7);
                }
            } else if (this.ac()) {
                var7 = Math.round(OMathHelper.a(var1 * var1 + var5 * var5) * 100.0F);
                if (var7 > 0) {
                    this.a(OStatList.m, var7);
                }
            } else if (this.p()) {
                if (var3 > 0.0D) {
                    this.a(OStatList.o, (int) Math.round(var3 * 100.0D));
                }
            } else if (this.ba) {
                var7 = Math.round(OMathHelper.a(var1 * var1 + var5 * var5) * 100.0F);
                if (var7 > 0) {
                    this.a(OStatList.l, var7);
                }
            } else {
                var7 = Math.round(OMathHelper.a(var1 * var1 + var5 * var5) * 100.0F);
                if (var7 > 25) {
                    this.a(OStatList.p, var7);
                }
            }
        }
    }

    private void i(double var1, double var3, double var5) {
        if (this.aK != null) {
            int var7 = Math.round(OMathHelper.a(var1 * var1 + var3 * var3 + var5 * var5) * 100.0F);
            if (var7 > 0) {
                if (this.aK instanceof OEntityMinecart) {
                    this.a(OStatList.r, var7);
                    if (this.c == null) {
                        this.c = new OChunkCoordinates(OMathHelper.b(this.aP), OMathHelper.b(this.aQ), OMathHelper.b(this.aR));
                    } else if (this.c.a(OMathHelper.b(this.aP), OMathHelper.b(this.aQ), OMathHelper.b(this.aR)) >= 1000.0D) {
                        this.a((OStatBase) OAchievementList.q, 1);
                    }
                } else if (this.aK instanceof OEntityBoat) {
                    this.a(OStatList.s, var7);
                } else if (this.aK instanceof OEntityPig) {
                    this.a(OStatList.t, var7);
                }
            }
        }
    }

    @Override
    protected void a(float paramFloat) {
        if (paramFloat >= 2.0F)
            a(OStatList.n, (int) Math.round(paramFloat * 100.0D));
        super.a(paramFloat);
    }
    
    @Override
    public void a(OEntityLiving var1) {
        if (var1 instanceof OEntityMob) {
            this.a((OStatBase) OAchievementList.s);
        }
    }

    @Override
    public void O() {
        if (this.D > 0)
            this.D = 10;
        else
            this.E = true;
    }

}