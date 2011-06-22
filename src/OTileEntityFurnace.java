public class OTileEntityFurnace extends OTileEntity implements OIInventory, Container<OItemStack> {

    private OItemStack[] h    = new OItemStack[3];
    public int           a    = 0;
    public int           b    = 0;
    public int           c    = 0;
    private String       name = "Furnace";

    public int a() {
        return h.length;
    }

    public OItemStack[] getContents() {
        int size = getContentsSize();
        OItemStack[] result = new OItemStack[size];

        for (int i = 0; i < size; i++)
            result[i] = getContentsAt(i);
        return result;
    }

    public void setContents(OItemStack[] values) {
        int size = getContentsSize();

        for (int i = 0; i < size; i++)
            setContentsAt(i, values[i]);
    }

    public OItemStack getContentsAt(int index) {
        return c_(index);
    }

    public void setContentsAt(int index, OItemStack value) {
        a(index, value);
    }

    public int getContentsSize() {
        return a();
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        name = value;
    }

    public OItemStack c_(int paramInt) {
        return h[paramInt];
    }

    public OItemStack a(int paramInt1, int paramInt2) {
        if (h[paramInt1] != null) {
            if (h[paramInt1].a <= paramInt2) {
                OItemStack localOItemStack = h[paramInt1];
                h[paramInt1] = null;
                return localOItemStack;
            }
            OItemStack localOItemStack = h[paramInt1].a(paramInt2);
            if (h[paramInt1].a == 0)
                h[paramInt1] = null;
            return localOItemStack;
        }

        return null;
    }

    public void a(int paramInt, OItemStack paramOItemStack) {
        h[paramInt] = paramOItemStack;
        if ((paramOItemStack != null) && (paramOItemStack.a > d()))
            paramOItemStack.a = d();
    }

    public String c() {
        return name;
    }

    @Override
    public void a(ONBTTagCompound paramONBTTagCompound) {
        super.a(paramONBTTagCompound);
        ONBTTagList localONBTTagList = paramONBTTagCompound.l("Items");
        h = new OItemStack[a()];
        for (int i = 0; i < localONBTTagList.c(); i++) {
            ONBTTagCompound localONBTTagCompound = (ONBTTagCompound) localONBTTagList.a(i);
            int j = localONBTTagCompound.c("Slot");
            if ((j < 0) || (j >= h.length))
                continue;
            h[j] = new OItemStack(localONBTTagCompound);
        }

        a = paramONBTTagCompound.d("BurnTime");
        c = paramONBTTagCompound.d("CookTime");
        b = a(h[1]);
    }

    @Override
    public void b(ONBTTagCompound paramONBTTagCompound) {
        super.b(paramONBTTagCompound);
        paramONBTTagCompound.a("BurnTime", (short) a);
        paramONBTTagCompound.a("CookTime", (short) c);
        ONBTTagList localONBTTagList = new ONBTTagList();

        for (int i = 0; i < h.length; i++)
            if (h[i] != null) {
                ONBTTagCompound localONBTTagCompound = new ONBTTagCompound();
                localONBTTagCompound.a("Slot", (byte) i);
                h[i].a(localONBTTagCompound);
                localONBTTagList.a(localONBTTagCompound);
            }
        paramONBTTagCompound.a("Items", localONBTTagList);
    }

    public int d() {
        return 64;
    }

    public boolean f() {
        return a > 0;
    }

    @Override
    public void g_() {
        boolean i = a > 0;
        boolean j = false;
        if (a > 0)
            a -= 1;

        if (!d.B) {
            if ((a == 0) && (h())) {
                b = (a = a(h[1]));
                if (a > 0) {
                    j = true;
                    if (h[1] != null) {
                        h[1].a -= 1;
                        if (h[1].a == 0)
                            h[1] = null;
                    }
                }
            }

            if ((f()) && (h())) {
                c += 1;
                if (c == 200) {
                    c = 0;
                    g();
                    j = true;
                }
            } else
                c = 0;

            if (i != a > 0) {
                j = true;
                OBlockFurnace.a(a > 0, d, e, f, g);
            }
        }

        if (j)
            i();
    }

    private boolean h() {
        if (h[0] == null)
            return false;
        OItemStack localOItemStack = OFurnaceRecipes.a().a(h[0].a().be);
        if (localOItemStack == null)
            return false;
        if (h[2] == null)
            return true;
        if (!h[2].a(localOItemStack))
            return false;
        if ((h[2].a < d()) && (h[2].a < h[2].b()))
            return true;
        return h[2].a < localOItemStack.b();
    }

    public void g() {
        if (!h())
            return;

        OItemStack localOItemStack = OFurnaceRecipes.a().a(h[0].a().be);
        if (h[2] == null)
            h[2] = localOItemStack.j();
        else if (h[2].c == localOItemStack.c)
            h[2].a += 1;

        h[0].a -= 1;
        if (h[0].a <= 0)
            h[0] = null;
    }

    private int a(OItemStack paramOItemStack) {
        if (paramOItemStack == null)
            return 0;
        int i = paramOItemStack.a().be;

        if ((i < 256) && (OBlock.m[i].bA == OMaterial.d))
            return 300;

        if (i == OItem.B.be)
            return 100;

        if (i == OItem.k.be)
            return 1600;

        if (i == OItem.aw.be)
            return 20000;

        if (i == OBlock.z.bn)
            return 100;

        return 0;
    }

    @Override
    public boolean a_(OEntityPlayer paramOEntityPlayer) {
        if (d.n(e, f, g) != this)
            return false;
        return paramOEntityPlayer.d(e + 0.5D, f + 0.5D, g + 0.5D) <= 64.0D;
    }
}
