public class OEntityItem extends OEntity {
    public OItemStack a;
    private int       e;
    public int        b = 0;
    public int        c;
    private int       f = 5;

    public float      d = (float) (Math.random() * 3.141592653589793D * 2.0D);

    public OEntityItem(OWorld paramOWorld, double paramDouble1, double paramDouble2, double paramDouble3, OItemStack paramOItemStack) {
        super(paramOWorld);
        b(0.25F, 0.25F);
        be = (bg / 2.0F);
        a(paramDouble1, paramDouble2, paramDouble3);
        a = paramOItemStack;

        aR = (float) (Math.random() * 360.0D);

        aO = (float) (Math.random() * 0.2000000029802322D - 0.1000000014901161D);
        aP = 0.2D;
        aQ = (float) (Math.random() * 0.2000000029802322D - 0.1000000014901161D);
    }

    @Override
    protected boolean n() {
        return false;
    }

    public OEntityItem(OWorld paramOWorld) {
        super(paramOWorld);
        b(0.25F, 0.25F);
        be = (bg / 2.0F);
    }

    @Override
    protected void b() {
    }

    //private long lastcall = System.currentTimeMillis();
    //private static int ceil(double d) { int rt = (int) d; return rt > d ? rt : rt + 1; }
    @Override
    public void p_() {
        super.p_();
        if (c > 0) {
            /*c -= ceil((lastcall - System.currentTimeMillis())/25);
            lastcall = System.currentTimeMillis();*/
            c--;
        }
        aI = aL;
        aJ = aM;
        aK = aN;

        aP -= 0.04D;
        if (aH.c(OMathHelper.b(aL), OMathHelper.b(aM), OMathHelper.b(aN)) == OMaterial.g) {
            aP = 0.2D;
            aO = ((br.nextFloat() - br.nextFloat()) * 0.2F);
            aQ = ((br.nextFloat() - br.nextFloat()) * 0.2F);
            aH.a(this, "random.fizz", 0.4F, 2.0F + br.nextFloat() * 0.4F);
        }
        g(aL, aM, aN);
        c(aO, aP, aQ);

        float f1 = 0.98F;
        if (aW) {
            f1 = 0.588F;
            int i = aH.a(OMathHelper.b(aL), OMathHelper.b(aV.b) - 1, OMathHelper.b(aN));
            if (i > 0)
                f1 = OBlock.m[i].bz * 0.98F;
        }

        aO *= f1;
        aP *= 0.98D;
        aQ *= f1;

        if (aW)
            aP *= -0.5D;

        e += 1;
        b += 1;
        if (b >= 6000)
            G();
    }

    @Override
    public boolean f_() {
        return aH.a(aV, OMaterial.f, this);
    }

    private boolean g(double paramDouble1, double paramDouble2, double paramDouble3) {
        int i = OMathHelper.b(paramDouble1);
        int j = OMathHelper.b(paramDouble2);
        int k = OMathHelper.b(paramDouble3);

        double d1 = paramDouble1 - i;
        double d2 = paramDouble2 - j;
        double d3 = paramDouble3 - k;

        if (OBlock.o[aH.a(i, j, k)]) {
            boolean m = !OBlock.o[aH.a(i - 1, j, k)];
            boolean n = !OBlock.o[aH.a(i + 1, j, k)];
            boolean i1 = !OBlock.o[aH.a(i, j - 1, k)];
            boolean i2 = !OBlock.o[aH.a(i, j + 1, k)];
            boolean i3 = !OBlock.o[aH.a(i, j, k - 1)];
            boolean i4 = !OBlock.o[aH.a(i, j, k + 1)];

            int i5 = -1;
            double d4 = 9999.0D;
            if (m && (d1 < d4)) {
                d4 = d1;
                i5 = 0;
            }
            if (n && (1.0D - d1 < d4)) {
                d4 = 1.0D - d1;
                i5 = 1;
            }
            if (i1 && (d2 < d4)) {
                d4 = d2;
                i5 = 2;
            }
            if (i2 && (1.0D - d2 < d4)) {
                d4 = 1.0D - d2;
                i5 = 3;
            }
            if (i3 && (d3 < d4)) {
                d4 = d3;
                i5 = 4;
            }
            if (i4 && (1.0D - d3 < d4)) {
                d4 = 1.0D - d3;
                i5 = 5;
            }

            float f1 = br.nextFloat() * 0.2F + 0.1F;
            if (i5 == 0)
                aO = (-f1);
            if (i5 == 1)
                aO = f1;
            if (i5 == 2)
                aP = (-f1);
            if (i5 == 3)
                aP = f1;
            if (i5 == 4)
                aQ = (-f1);
            if (i5 == 5)
                aQ = f1;
        }

        return false;
    }

    @Override
    protected void a(int paramInt) {
        a((OEntity)null, paramInt);
    }

    @Override
    public boolean a(OEntity paramOEntity, int paramInt) {
        ab();
        f -= paramInt;
        if (f <= 0)
            G();
        return false;
    }

    @Override
    public void b(ONBTTagCompound paramONBTTagCompound) {
        paramONBTTagCompound.a("Health", (short) (byte) f);
        paramONBTTagCompound.a("Age", (short) b);
        paramONBTTagCompound.a("Item", a.a(new ONBTTagCompound()));
    }

    @Override
    public void a(ONBTTagCompound paramONBTTagCompound) {
        f = (paramONBTTagCompound.d("Health") & 0xFF);
        b = paramONBTTagCompound.d("Age");
        ONBTTagCompound localONBTTagCompound = paramONBTTagCompound.k("Item");
        a = new OItemStack(localONBTTagCompound);
    }

    @Override
    public void b(OEntityPlayer paramOEntityPlayer) {
        if (aH.v)
            return;

        int i = a.a;
        if ((c == 0) && (paramOEntityPlayer.i.a(a))) {
            // CanaryMod: allow item pickups
            Item item = new Item(a.c, i);
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_PICK_UP, ((OEntityPlayerMP) paramOEntityPlayer).getPlayer(), item)) {
                if (this.a.c == OBlock.J.bl)
                    paramOEntityPlayer.a((OStatBasic) OAchievementList.g);

                if (this.a.c == OItem.aD.bd)
                    paramOEntityPlayer.a((OStatBasic) OAchievementList.t);

                this.aH.a(this, "random.pop", 0.2F, ((this.br.nextFloat() - this.br.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                paramOEntityPlayer.b(this, i);
                G();
            }
        }
    }
}
