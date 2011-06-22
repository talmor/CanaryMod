
public final class OItemStack {

    public int  a = 0;
    public int  b;
    public int  c;
    private int d;

    public OItemStack(OBlock paramOBlock) {
        this(paramOBlock, 1);
    }

    public OItemStack(OBlock paramOBlock, int paramInt) {
        this(paramOBlock.bn, paramInt, 0);
    }

    public OItemStack(OBlock paramOBlock, int paramInt1, int paramInt2) {
        this(paramOBlock.bn, paramInt1, paramInt2);
    }

    public OItemStack(OItem paramOItem) {
        this(paramOItem.be, 1, 0);
    }

    public OItemStack(OItem paramOItem, int paramInt) {
        this(paramOItem.be, paramInt, 0);
    }

    public OItemStack(OItem paramOItem, int paramInt1, int paramInt2) {
        this(paramOItem.be, paramInt1, paramInt2);
    }

    public OItemStack(int paramInt1, int paramInt2, int paramInt3) {
        c = paramInt1;
        a = paramInt2;
        d = paramInt3;
    }

    public OItemStack(ONBTTagCompound paramONBTTagCompound) {
        b(paramONBTTagCompound);
    }

    public OItemStack a(int paramInt) {
        a -= paramInt;
        return new OItemStack(c, paramInt, d);
    }

    public OItem a() {
        return OItem.c[c];
    }

    public boolean a(OEntityPlayer paramOEntityPlayer, OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        boolean bool = a().a(this, paramOEntityPlayer, paramOWorld, paramInt1, paramInt2, paramInt3, paramInt4);
        if (bool)
            paramOEntityPlayer.a(OStatList.E[c], 1);
        return bool;

    }

    public float a(OBlock paramOBlock) {
        return a().a(this, paramOBlock);
    }

    public OItemStack a(OWorld paramOWorld, OEntityPlayer paramOEntityPlayer) {
        return a().a(this, paramOWorld, paramOEntityPlayer);
    }

    public ONBTTagCompound a(ONBTTagCompound paramONBTTagCompound) {
        paramONBTTagCompound.a("id", (short) c);
        paramONBTTagCompound.a("Count", (byte) a);
        paramONBTTagCompound.a("Damage", (short) d);
        return paramONBTTagCompound;
    }

    public void b(ONBTTagCompound paramONBTTagCompound) {
        c = paramONBTTagCompound.d("id");
        a = paramONBTTagCompound.c("Count");
        d = paramONBTTagCompound.d("Damage");
    }

    public int b() {
        return a().c();
    }

    public boolean c() {
        return (b() > 1) && ((!d()) || (!f()));
    }

    public boolean d() {
        return OItem.c[c].e() > 0;
    }

    public boolean e() {
        return OItem.c[c].d();
    }

    public boolean f() {
        return (d()) && (d > 0);
    }

    public int g() {
        return d;
    }

    public int h() {
        return d;
    }

    public void b(int var1) {
        this.d = var1;
    }

    public int i() {
        return OItem.c[c].e();
    }

    public void a(int paramInt, OEntity paramOEntity) {
        if (!d())
            return;

        d += paramInt;
        if (d > i()) {
            if ((paramOEntity instanceof OEntityPlayer))
                ((OEntityPlayer) paramOEntity).a(OStatList.F[c], 1);

            a -= 1;
            if (a < 0)
                a = 0;
            d = 0;
        }
    }

    public void a(OEntityLiving paramOEntityLiving, OEntityPlayer paramOEntityPlayer) {
        boolean bool = OItem.c[c].a(this, paramOEntityLiving, paramOEntityPlayer);
        if (bool)
            paramOEntityPlayer.a(OStatList.E[c], 1);

    }

    public void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, OEntityPlayer paramOEntityPlayer) {
        boolean bool = OItem.c[c].a(this, paramInt1, paramInt2, paramInt3, paramInt4, paramOEntityPlayer);
        if (bool)
            paramOEntityPlayer.a(OStatList.E[c], 1);

    }

    public int a(OEntity paramOEntity) {
        return OItem.c[c].a(paramOEntity);
    }

    public boolean b(OBlock paramOBlock) {
        return OItem.c[c].a(paramOBlock);
    }

    public void a(OEntityPlayer paramOEntityPlayer) {
    }

    public void a(OEntityLiving paramOEntityLiving) {
        OItem.c[c].a(this, paramOEntityLiving);
    }

    public OItemStack j() {
        return new OItemStack(c, a, d);
    }

    public static boolean a(OItemStack paramOItemStack1, OItemStack paramOItemStack2) {
        if ((paramOItemStack1 == null) && (paramOItemStack2 == null))
            return true;
        if ((paramOItemStack1 == null) || (paramOItemStack2 == null))
            return false;
        return paramOItemStack1.d(paramOItemStack2);
    }

    private boolean d(OItemStack paramOItemStack) {
        if (a != paramOItemStack.a)
            return false;
        if (c != paramOItemStack.c)
            return false;
        return d == paramOItemStack.d;
    }

    public boolean a(OItemStack paramOItemStack) {
        return (c == paramOItemStack.c) && (d == paramOItemStack.d);
    }

    public static OItemStack b(OItemStack paramOItemStack) {
        return paramOItemStack == null ? null : paramOItemStack.j();
    }

    @Override
    public String toString() {
        return a + "x" + OItem.c[c].a() + "@" + d;
    }

    public void a(OWorld var1, OEntity var2, int var3, boolean var4) {
        if (this.b > 0)
            --this.b;

        OItem.c[this.c].a(this, var1, var2, var3, var4);
    }

    public void b(OWorld var1, OEntityPlayer var2) {
        var2.a(OStatList.D[this.c], this.a);
        OItem.c[this.c].c(this, var1, var2);
    }

    public boolean c(OItemStack var1) {
        return this.c == var1.c && this.a == var1.a && this.d == var1.d;
    }
}
