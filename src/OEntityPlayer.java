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
    private OChunkCoordinates   a;
    private int                 b;
    public float                A;
    public float                B;
    private OChunkCoordinates   c;
    private OChunkCoordinates   d;
    private int                 e = 0;
    public OEntityFish          C = null;

    // CanaryMod start
    HumanEntity                 entity = new HumanEntity(this);

    // CanaryMod end

    
    public OEntityPlayer(OWorld paramOWorld) {
        super(paramOWorld);

        j = new OContainerPlayer(i, !paramOWorld.v);

        k = j;

        be = 1.62F;
        OChunkCoordinates localOChunkCoordinates = paramOWorld.n();
        c(localOChunkCoordinates.a + 0.5D, localOChunkCoordinates.b + 1, localOChunkCoordinates.c + 0.5D, 0.0F, 0.0F);

        X = 20;
        Q = "humanoid";
        P = 180.0F;
        bt = 20;

        N = "/mob/char.png";
    }

    @Override
    protected void b() {
        super.b();

        bA.a(16, (byte) 0);
    }

    @Override
    public void p_() {
        if (I()) {
            b += 1;
            if (b > 100)
                b = 100;
            if (!o())
                a(true, true, false);
            else if ((!aH.v) && (aH.d()))
                a(false, true, true);
        } else if (b > 0) {
            b += 1;
            if (b >= 110)
                b = 0;
        }

        super.p_();

        if ((!aH.v) && (k != null) && (!k.b(this))) {
            x();
            k = j;
        }

        t = w;
        u = x;
        v = y;

        double d1 = aL - w;
        double d2 = aM - x;
        double d3 = aN - y;

        double d4 = 10.0D;
        if (d1 > d4)
            t = (w = aL);
        if (d3 > d4)
            v = (y = aN);
        if (d2 > d4)
            u = (x = aM);
        if (d1 < -d4)
            t = (w = aL);
        if (d3 < -d4)
            v = (y = aN);
        if (d2 < -d4)
            u = (x = aM);

        w += d1 * 0.25D;
        y += d3 * 0.25D;
        x += d2 * 0.25D;

        a(OStatList.k, 1);
        
        if (aG == null)
            d = null;
    }

    @Override
    protected boolean A() {
        return (X <= 0) || (I());
    }

    protected void x() {
        k = j;
    }

    @Override
    public void B() {
        double _aL = aL, _aM = aM, _aN = aN;
        super.B();
        n = o;
        o = 0.0F;
        h(aL - _aL, aM - _aM, aN - _aN);
    }

    @Override
    protected void c_() {
        if (p) {
            q += 1;
            if (q == 8) {
                q = 0;
                p = false;
            }
        } else
            q = 0;

        W = (q / 8.0F);
    }

    @Override
    public void u() {
         // CanaryMod: adjust 'healing over time' independent of
         // monster-spawn=true/false (nice notchup!)
         PluginLoader.HookResult autoHeal = etc.getInstance().autoHeal();
         if ((aH.l == 0) && (autoHeal == PluginLoader.HookResult.DEFAULT_ACTION) || autoHeal == PluginLoader.HookResult.ALLOW_ACTION)
             if ((X < 20) && (bs % 20 * 12 == 0))
                 b(1);

        i.f();
        n = o;

        super.u();

        float f1 = OMathHelper.a(aO * aO + aQ * aQ);
        float f2 = (float) Math.atan(-aP * 0.2D) * 15.0F;
        if (f1 > 0.1F)
            f1 = 0.1F;
        if ((!aW) || (X <= 0))
            f1 = 0.0F;
        if ((aW) || (X <= 0))
            f2 = 0.0F;
        o += (f1 - o) * 0.4F;
        af += (f2 - af) * 0.8F;

        if (X > 0) {
            List localList = aH.b(this, aV.b(1.0D, 0.0D, 1.0D));
            if (localList != null)
                for (int i1 = 0; i1 < localList.size(); i1++) {
                    OEntity localOEntity = (OEntity) localList.get(i1);
                    if (!localOEntity.bd)
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
        a(aL, aM, aN);
        aP = 0.1D;

        if (r.equals("Notch"))
            a(new OItemStack(OItem.h, 1), true);
        i.h();

        if (paramOEntity != null) {
            aO = (-OMathHelper.b((ab + aR) * 3.141593F / 180.0F) * 0.1F);
            aQ = (-OMathHelper.a((ab + aR) * 3.141593F / 180.0F) * 0.1F);
        } else
            aO = (aQ = 0.0D);
        be = 0.1F;

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

    public void C() {
        a(i.a(i.c, 1), false);
    }

    public void b(OItemStack paramOItemStack) {
        a(paramOItemStack, false);
    }

    public void a(OItemStack paramOItemStack, boolean paramBoolean) {
        if (paramOItemStack == null)
            return;

        OEntityItem localOEntityItem = new OEntityItem(aH, aL, aM - 0.3D + s(), aN, paramOItemStack);
        localOEntityItem.c = 40;

        float f1 = 0.1F;
        float f2;
        if (paramBoolean) {
            f2 = br.nextFloat() * 0.5F;
            float f3 = br.nextFloat() * 3.141593F * 2.0F;
            localOEntityItem.aO = (-OMathHelper.a(f3) * f2);
            localOEntityItem.aQ = (OMathHelper.b(f3) * f2);
            localOEntityItem.aP = 0.2D;
        } else {
            f1 = 0.3F;
            localOEntityItem.aO = (-OMathHelper.a(aR / 180.0F * 3.141593F) * OMathHelper.b(aS / 180.0F * 3.141593F) * f1);
            localOEntityItem.aQ = (OMathHelper.b(aR / 180.0F * 3.141593F) * OMathHelper.b(aS / 180.0F * 3.141593F) * f1);
            localOEntityItem.aP = (-OMathHelper.a(aS / 180.0F * 3.141593F) * f1 + 0.1F);
            f1 = 0.02F;

            f2 = br.nextFloat() * 3.141593F * 2.0F;
            f1 *= br.nextFloat();
            localOEntityItem.aO += Math.cos(f2) * f1;
            localOEntityItem.aP += (br.nextFloat() - br.nextFloat()) * 0.1F;
            localOEntityItem.aQ += Math.sin(f2) * f1;
        }

        if (!(Boolean) manager.callHook(PluginLoader.Hook.ITEM_DROP, ((OEntityPlayerMP) this).getPlayer(), new Item(paramOItemStack))){
            a(localOEntityItem);
            a(OStatList.v, 1);
        // return the item to the inventory.
        } else
            i.a(paramOItemStack);

    }

    protected void a(OEntityItem paramOEntityItem) {
        aH.b(paramOEntityItem);
    }

    public float a(OBlock paramOBlock) {
        float f = i.a(paramOBlock);
        if (a(OMaterial.f))
            f /= 5.0F;
        if (!aW)
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
        b = paramONBTTagCompound.d("SleepTimer");

        if (z) {
            a = new OChunkCoordinates(OMathHelper.b(aL), OMathHelper.b(aM), OMathHelper.b(aN));
            a(true, true, false);
        }

        if ((paramONBTTagCompound.b("SpawnX")) && (paramONBTTagCompound.b("SpawnY")) && (paramONBTTagCompound.b("SpawnZ")))
            c = new OChunkCoordinates(paramONBTTagCompound.e("SpawnX"), paramONBTTagCompound.e("SpawnY"), paramONBTTagCompound.e("SpawnZ"));
    }

    @Override
    public void b(ONBTTagCompound paramONBTTagCompound) {
        super.b(paramONBTTagCompound);
        paramONBTTagCompound.a("Inventory", i.a(new ONBTTagList()));
        paramONBTTagCompound.a("Dimension", s);
        paramONBTTagCompound.a("Sleeping", z);
        paramONBTTagCompound.a("SleepTimer", (short) b);

        if (c != null) {
            paramONBTTagCompound.a("SpawnX", c.a);
            paramONBTTagCompound.a("SpawnY", c.b);
            paramONBTTagCompound.a("SpawnZ", c.c);
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
        be = 1.62F;
    }

    @Override
    public boolean a(OEntity paramOEntity, int paramInt) {
        au = 0;
        if (X <= 0)
            return false;

        if (I())
            a(true, true, false);

        if (((paramOEntity instanceof OEntityMob)) || ((paramOEntity instanceof OEntityArrow))) {
            if (aH.l == 0)
                paramInt = 0;
            if (aH.l == 1)
                paramInt = paramInt / 3 + 1;
            if (aH.l == 3)
                paramInt = paramInt * 3 / 2;
        }

        if (paramInt == 0)
            return false;

        Object localObject = paramOEntity;
        if (((localObject instanceof OEntityArrow)) && (((OEntityArrow) localObject).b != null))
            localObject = ((OEntityArrow) localObject).b;

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
            if ((((OEntityWolf) localObject).m_()) && (r.equals(((OEntityWolf) localObject).x())))
                return;

        }

        if (!(paramOEntityLiving instanceof OEntityPlayer) || t()) {
            List<OEntity> localListOEntity = aH.a(OEntityWolf.class, OAxisAlignedBB.b(aL, aM, aN, aL + 1.0D, aM + 1.0D, aN + 1.0D).b(16.0D, 4.0D, 16.0D));
            for (OEntity localOEntity : localListOEntity) {
                OEntityWolf localOEntityWolf = (OEntityWolf) localOEntity;
                if ((localOEntityWolf.m_()) && (localOEntityWolf.D() == null) && (r.equals(localOEntityWolf.x())) && ((!paramBoolean) || (!localOEntityWolf.y()))) {
                    localOEntityWolf.b(false);
                    localOEntityWolf.c(paramOEntityLiving);
                }
            }
        }
    }

    @Override
    protected void c(int paramInt) {
        int i1 = 25 - i.g();
        int i2 = paramInt * i1 + e;
        i.c(paramInt);
        paramInt = i2 / 25;
        e = (i2 % 25);
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
        OItemStack localOItemStack = D();
        if ((localOItemStack != null) && ((paramOEntity instanceof OEntityLiving))) {
            localOItemStack.a((OEntityLiving) paramOEntity);
            if (localOItemStack.a <= 0) {
                localOItemStack.a(this);
                E();
            }
        }
    }

    public OItemStack D() {
        return i.b();
    }

    public void E() {
        i.a(i.c, null);
    }

    @Override
    public double F() {
        return be - 0.5F;
    }

    public void k_() {
        q = -1;
        p = true;
    }

    public void d(OEntity paramOEntity) {
        int i1 = i.a(paramOEntity);
        if (i1 > 0) {
            if (aP < 0.0D)
                i1++;
            paramOEntity.a(this, i1);
            OItemStack localOItemStack = D();
            if ((localOItemStack != null) && ((paramOEntity instanceof OEntityLiving))) {
                localOItemStack.a((OEntityLiving) paramOEntity, this);
                if (localOItemStack.a <= 0) {
                    localOItemStack.a(this);
                    E();
                }
            }
            if ((paramOEntity instanceof OEntityLiving)) {
                if (paramOEntity.P())
                    a((OEntityLiving) paramOEntity, true);
                a(OStatList.w, i1);
            }
        }
    }

    public void a(OItemStack paramOItemStack) {
    }

    @Override
    public void G() {
        super.G();
        j.a(this);
        if (k != null)
            k.a(this);
    }

    @Override
    public boolean H() {
        return (!z) && (super.H());
    }

    public OEnumStatus a(int paramInt1, int paramInt2, int paramInt3) {
        if ((I()) || (!P()))
            return OEnumStatus.e;

        if (aH.o.c)
            return OEnumStatus.b;
        if (aH.d())
            return OEnumStatus.c;
        if ((Math.abs(aL - paramInt1) > 3.0D) || (Math.abs(aM - paramInt2) > 2.0D) || (Math.abs(aN - paramInt3) > 3.0D))
            return OEnumStatus.d;

        b(0.2F, 0.2F);
        be = 0.2F;
        if (aH.f(paramInt1, paramInt2, paramInt3)) {
            int i1 = aH.b(paramInt1, paramInt2, paramInt3);
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
        b = 0;
        a = new OChunkCoordinates(paramInt1, paramInt2, paramInt3);
        aO = (aQ = aP = 0.0D);

        if (!aH.v)
            aH.r();

        return OEnumStatus.a;
    }

    private void e(int paramInt) {
        A = 0.0F;
        B = 0.0F;

        switch (paramInt) {
            case 0:
                B = -1.8F;
                break;
            case 2:
                B = 1.8F;
                break;
            case 1:
                A = 1.8F;
                break;
            case 3:
                A = -1.8F;
        }
    }

    public void a(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) {
        b(0.6F, 1.8F);
        j_();

        OChunkCoordinates localOChunkCoordinates1 = a;
        OChunkCoordinates localOChunkCoordinates2 = a;
        if ((localOChunkCoordinates1 != null) && (aH.a(localOChunkCoordinates1.a, localOChunkCoordinates1.b, localOChunkCoordinates1.c) == OBlock.S.bl)) {
            OBlockBed.a(aH, localOChunkCoordinates1.a, localOChunkCoordinates1.b, localOChunkCoordinates1.c, false);

            localOChunkCoordinates2 = OBlockBed.f(aH, localOChunkCoordinates1.a, localOChunkCoordinates1.b, localOChunkCoordinates1.c, 0);
            if (localOChunkCoordinates2 == null)
                localOChunkCoordinates2 = new OChunkCoordinates(localOChunkCoordinates1.a, localOChunkCoordinates1.b + 1, localOChunkCoordinates1.c);
            a(localOChunkCoordinates2.a + 0.5F, localOChunkCoordinates2.b + be + 0.1F, localOChunkCoordinates2.c + 0.5F);
        }

        z = false;
        if ((!aH.v) && (paramBoolean2))
            aH.r();
        if (paramBoolean1)
            b = 0;
        else
            b = 100;
        if (paramBoolean3)
            a(a);
    }

    private boolean o() {
        return aH.a(a.a, a.b, a.c) == OBlock.S.bl;
    }

    public static OChunkCoordinates a(OWorld paramOWorld, OChunkCoordinates paramOChunkCoordinates) {
        OIChunkProvider localOIChunkProvider = paramOWorld.o();
        localOIChunkProvider.c(paramOChunkCoordinates.a - 3 >> 4, paramOChunkCoordinates.c - 3 >> 4);
        localOIChunkProvider.c(paramOChunkCoordinates.a + 3 >> 4, paramOChunkCoordinates.c - 3 >> 4);
        localOIChunkProvider.c(paramOChunkCoordinates.a - 3 >> 4, paramOChunkCoordinates.c + 3 >> 4);
        localOIChunkProvider.c(paramOChunkCoordinates.a + 3 >> 4, paramOChunkCoordinates.c + 3 >> 4);

        if (paramOWorld.a(paramOChunkCoordinates.a, paramOChunkCoordinates.b, paramOChunkCoordinates.c) != OBlock.S.bl)
            return null;

        OChunkCoordinates localOChunkCoordinates = OBlockBed.f(paramOWorld, paramOChunkCoordinates.a, paramOChunkCoordinates.b, paramOChunkCoordinates.c, 0);
        return localOChunkCoordinates;
    }

    @Override
    public boolean I() {
        return z;
    }

    public boolean J() {
        return (z) && (b >= 100);
    }

    public void a(String paramString) {
    }

    public OChunkCoordinates K() {
        return c;
    }

    public void a(OChunkCoordinates paramOChunkCoordinates) {
        if (paramOChunkCoordinates != null)
            c = new OChunkCoordinates(paramOChunkCoordinates);
        else
            c = null;
    }

    public void a(OStatBase var1) {
        a(var1, 1);
    }

    public void a(OStatBase paramOStatBasic, int paramInt) {
    }

    @Override
    protected void L() {
        super.L();
        a(OStatList.u, 1);
    }

    @Override
    public void a(float paramFloat1, float paramFloat2) {
        double d1 = aL;
        double d2 = aM;
        double d3 = aN;

        super.a(paramFloat1, paramFloat2);

        g(aL - d1, aM - d2, aN - d3);
    }

    private void g(double var1, double var3, double var5) {
        if (this.aG == null) {
            int var7;
            if (this.a(OMaterial.f)) {
                var7 = Math.round(OMathHelper.a(var1 * var1 + var3 * var3 + var5 * var5) * 100.0F);
                if (var7 > 0) {
                    this.a(OStatList.q, var7);
                }
            } else if (this.Z()) {
                var7 = Math.round(OMathHelper.a(var1 * var1 + var5 * var5) * 100.0F);
                if (var7 > 0) {
                    this.a(OStatList.m, var7);
                }
            } else if (this.p()) {
                if (var3 > 0.0D) {
                    this.a(OStatList.o, (int) Math.round(var3 * 100.0D));
                }
            } else if (this.aW) {
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

    private void h(double var1, double var3, double var5) {
        if (this.aG != null) {
            int var7 = Math.round(OMathHelper.a(var1 * var1 + var3 * var3 + var5 * var5) * 100.0F);
            if (var7 > 0) {
                if (this.aG instanceof OEntityMinecart) {
                    this.a(OStatList.r, var7);
                    if (this.d == null) {
                        this.d = new OChunkCoordinates(OMathHelper.b(this.aL), OMathHelper.b(this.aM), OMathHelper.b(this.aN));
                    } else if (this.d.a(OMathHelper.b(this.aL), OMathHelper.b(this.aM), OMathHelper.b(this.aN)) >= 1000.0D) {
                        this.a((OStatBase) OAchievementList.q, 1);
                    }
                } else if (this.aG instanceof OEntityBoat) {
                    this.a(OStatList.s, var7);
                } else if (this.aG instanceof OEntityPig) {
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
    
    public void a(OEntityLiving var1) {
        if (var1 instanceof OEntityMob) {
            this.a((OStatBase) OAchievementList.s);
        }
    }

}
