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
        bi = (bk / 2.0F);
        a(paramDouble1, paramDouble2, paramDouble3);
        a = paramOItemStack;

        aV = (float) (Math.random() * 360.0D);

        aS = (float) (Math.random() * 0.2000000029802322D - 0.1000000014901161D);
        aT = 0.2D;
        aU = (float) (Math.random() * 0.2000000029802322D - 0.1000000014901161D);
    }

    @Override
    protected boolean n() {
        return false;
    }

    public OEntityItem(OWorld paramOWorld) {
        super(paramOWorld);
        b(0.25F, 0.25F);
        bi = (bk / 2.0F);
    }

    @Override
    protected void b() {
    }

    //private long lastcall = System.currentTimeMillis();
    //private static int ceil(double d) { int rt = (int) d; return rt > d ? rt : rt + 1; }
    @Override
    public void o_() {
        super.o_();
        if (c > 0) {
            /*c -= ceil((lastcall - System.currentTimeMillis())/25);
            lastcall = System.currentTimeMillis();*/
            c--;
        }
        aM = aP;
        aN = aQ;
        aO = aR;

        aT -= 0.04D;
        if (aL.c(OMathHelper.b(aP), OMathHelper.b(aQ), OMathHelper.b(aR)) == OMaterial.h) {
            aT = 0.2D;
            aS = ((bv.nextFloat() - bv.nextFloat()) * 0.2F);
            aU = ((bv.nextFloat() - bv.nextFloat()) * 0.2F);
            aL.a(this, "random.fizz", 0.4F, 2.0F + bv.nextFloat() * 0.4F);
        }
        g(aP, (aZ.b + aZ.e) / 2.0D, aR);
        c(aS, aT, aU);

        float f1 = 0.98F;
        if (ba) {
            f1 = 0.588F;
            int i = aL.a(OMathHelper.b(aP), OMathHelper.b(aZ.b) - 1, OMathHelper.b(aR));
            if (i > 0)
                f1 = OBlock.m[i].bB * 0.98F;
        }

        aS *= f1;
        aT *= 0.98D;
        aU *= f1;

        if (ba)
            aT *= -0.5D;

        e += 1;
        b += 1;
        if (b >= 6000)
            I();
    }

    @Override
    public boolean f_() {
        return aL.a(aZ, OMaterial.g, this);
    }

    @Override
    protected void a(int paramInt) {
        a((OEntity)null, paramInt);
    }

    @Override
    public boolean a(OEntity paramOEntity, int paramInt) {
        ae();
        f -= paramInt;
        if (f <= 0)
            I();
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
        if (aL.B)
            return;

        int i = a.a;
        if ((c == 0) && (paramOEntityPlayer.i.a(a))) {
            // CanaryMod: allow item pickups
            Item item = new Item(a.c, i);
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_PICK_UP, ((OEntityPlayerMP) paramOEntityPlayer).getPlayer(), item)) {
                if (this.a.c == OBlock.K.bn)
                    paramOEntityPlayer.a((OStatBase) OAchievementList.g);

                if (this.a.c == OItem.aD.be)
                    paramOEntityPlayer.a((OStatBase) OAchievementList.t);

                this.aL.a(this, "random.pop", 0.2F, ((this.bv.nextFloat() - this.bv.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                paramOEntityPlayer.b(this, i);
                if (a.a <= 0)
                    I();
            }
        }
    }
}
