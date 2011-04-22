import java.util.List;

public class OEntityMinecart extends OEntity implements OIInventory, Container<OItemStack> {
    private OItemStack[]           h = new OItemStack[36];

    public int                     a = 0;
    public int                     b = 0;
    public int                     c = 1;
    private boolean                i = false;
    public int                     d;
    public int                     e;
    public double                  f;
    public double                  g;
    private static final int[][][] j = { { { 0, 0, -1 }, { 0, 0, 1 } }, { { -1, 0, 0 }, { 1, 0, 0 } }, { { -1, -1, 0 }, { 1, 0, 0 } }, { { -1, 0, 0 }, { 1, -1, 0 } }, { { 0, 0, -1 }, { 0, -1, 1 } }, { { 0, -1, -1 }, { 0, 0, 1 } }, { { 0, 0, 1 }, { 1, 0, 0 } }, { { 0, 0, 1 }, { -1, 0, 0 } }, { { 0, 0, -1 }, { -1, 0, 0 } }, { { 0, 0, -1 }, { 1, 0, 0 } } };
    private int                    k;
    private double                 l;
    private double                 m;
    private double                 n;
    private double                 o;
    private double                 p;
    private String                 name = "Minecart";

    // CanaryMod start
    Minecart                       cart = new Minecart(this);

    // CanaryMod end

    public void setContents(OItemStack[] values) {
        h = values;
    }
    
    @Override
    public OItemStack[] getContents() {
        return h;
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

    public OEntityMinecart(OWorld paramOWorld) {
        super(paramOWorld);
        aE = true;
        b(0.98F, 0.7F);
        be = (bg / 2.0F);
        

    }

    @Override
    protected boolean n() {
        return false;
    }

    @Override
    protected void b() {
    }

    @Override
    public OAxisAlignedBB a_(OEntity paramOEntity) {
        return paramOEntity.aV;
    }

    @Override
    public OAxisAlignedBB e_() {
        return null;
    }

    @Override
    public boolean d_() {
        return true;
    }

    public OEntityMinecart(OWorld paramOWorld, double paramDouble1, double paramDouble2, double paramDouble3, int paramInt) {
        this(paramOWorld);
        a(paramDouble1, paramDouble2 + be, paramDouble3);

        aO = 0.0D;
        aP = 0.0D;
        aQ = 0.0D;

        aI = paramDouble1;
        aJ = paramDouble2;
        aK = paramDouble3;
        d = paramInt;
        // CanaryMod: Creation of the cart
        manager.callHook(PluginLoader.Hook.VEHICLE_CREATE, cart);
    }

    @Override
    public double m() {
        return bg * 0.0D - 0.3D;
    }

    @Override
    public boolean a(OEntity paramOEntity, int paramInt) {
        // CanaryMod: Attack of the cart
        if ((Boolean) manager.callHook(PluginLoader.Hook.VEHICLE_DAMAGE, cart, paramOEntity == null ? null : paramOEntity.entity, paramInt))
            return true;

        
        if ((aH.v) || (bd))
            return true;
        c = (-c);
        b = 10;
        ab();
        a += paramInt * 10;
        if (a > 40) {
            a(OItem.ax.bd, 1, 0.0F);
            if (d == 1)
                a(OBlock.au.bl, 1, 0.0F);
            else if (d == 2)
                a(OBlock.aB.bl, 1, 0.0F);
            G();
        }
        return true;
    }

    @Override
    public boolean o_() {
        return !bd;
    }

    @Override
    public void G() {
        // CanaryMod: Destruction of the cart
        manager.callHook(PluginLoader.Hook.VEHICLE_DESTROYED, cart);

        
        for (int i1 = 0; i1 < a(); i1++) {
            OItemStack localOItemStack = c_(i1);
            if (localOItemStack != null) {
                float f1 = br.nextFloat() * 0.8F + 0.1F;
                float f2 = br.nextFloat() * 0.8F + 0.1F;
                float f3 = br.nextFloat() * 0.8F + 0.1F;

                while (localOItemStack.a > 0) {
                    int i2 = br.nextInt(21) + 10;
                    if (i2 > localOItemStack.a)
                        i2 = localOItemStack.a;
                    localOItemStack.a -= i2;

                    OEntityItem localOEntityItem = new OEntityItem(aH, aL + f1, aM + f2, aN + f3, new OItemStack(localOItemStack.c, i2, localOItemStack.h()));
                    float f4 = 0.05F;
                    localOEntityItem.aO = ((float) br.nextGaussian() * f4);
                    localOEntityItem.aP= ((float) br.nextGaussian() * f4 + 0.2F);
                    localOEntityItem.aQ = ((float) br.nextGaussian() * f4);
                    aH.a(localOEntityItem);
                }
            }
        }
        super.G();
    }

    @Override
    public void p_() {
        // CanaryMod: Update of the cart
        manager.callHook(PluginLoader.Hook.VEHICLE_UPDATE, cart);

        
        if (b > 0)
            b -= 1;
        if (a > 0)
            a -= 1;

        if ((aH.v) && (k > 0)) {
            if (k > 0) {
                double d1 = aL + (l - aL) / k;
                double d2 = aM + (m - aM) / k;
                double d3 = aN + (n - aN) / k;

                double d4 = o - aR;
                while (d4 < -180.0D)
                    d4 += 360.0D;
                while (d4 >= 180.0D)
                    d4 -= 360.0D;
                aR = (float) (aR + d4 / k);
                aS = (float) (aS + (p - aS) / k);

                k -= 1;
                a(d1, d2, d3);
                c(aR, aS);
            } else {
                a(aL, aM, aN);
                c(aR, aS);
            }

            return;
        }
        aI = aL;
        aJ = aM;
        aK = aN;

        aP -= 0.04D;

        int i1 = OMathHelper.b(aL);
        int i2 = OMathHelper.b(aM);
        int i3 = OMathHelper.b(aN);
        // CanaryMod: Change of the cart
        manager.callHook(PluginLoader.Hook.VEHICLE_POSITIONCHANGE, cart, i1, i2, i3);

        if (OBlockMinecartTrack.g(this.aH, i1, i2 - 1, i3))
            i2--;

        double d5 = 0.4D;
        int i4 = 0;

        double d4 = 0.0078125D;
        int var15 = aH.a(i1, i2, i3);
        if (OBlockMinecartTrack.c(var15)) {
            OVec3D localOVec3D1 = g(aL, aM, aN);
            int i5 = aH.b(i1, i2, i3);
            aM = i2;
            
            boolean var18 = false, var19 = false;
            if (var15 == OBlock.T.bl) {
                var18 = (i5 & 8) != 0;
                var19 = !var18;
            }
            
            if (((OBlockMinecartTrack) OBlock.m[var15]).d()) {
                i5 &= 7;
            }

            
            if ((i5 >= 2) && (i5 <= 5))
                aM = (i2 + 1);

            if (i5 == 2)
                aO -= d4;
            if (i5 == 3)
                aO += d4;
            if (i5 == 4)
                aQ += d4;
            if (i5 == 5)
                aQ -= d4;

            int[][] arrayOfInt = j[i5];

            double d6 = arrayOfInt[1][0] - arrayOfInt[0][0];
            double d7 = arrayOfInt[1][2] - arrayOfInt[0][2];
            double d8 = Math.sqrt(d6 * d6 + d7 * d7);

            double d9 = aO * d6 + aQ * d7;
            if (d9 < 0.0D) {
                d6 = -d6;
                d7 = -d7;
            }

            double d10 = Math.sqrt(aO * aO + aQ * aQ);

            aO = (d10 * d6 / d8);
            aQ = (d10 * d7 / d8);

            double d11 = 0.0D;
            double d12 = i1 + 0.5D + arrayOfInt[0][0] * 0.5D;
            double d13 = i3 + 0.5D + arrayOfInt[0][2] * 0.5D;
            double d14 = i1 + 0.5D + arrayOfInt[1][0] * 0.5D;
            double d15 = i3 + 0.5D + arrayOfInt[1][2] * 0.5D;

            d6 = d14 - d12;
            d7 = d15 - d13;
            double d18;
            if (d6 == 0.0D) {
                aL = (i1 + 0.5D);
                d11 = aN - i3;
            } else if (d7 == 0.0D) {
                aN = (i3 + 0.5D);
                d11 = aL - i1;
            } else {
                double d16 = aL - d12;
                double d17 = aN - d13;

                d18 = (d16 * d6 + d17 * d7) * 2.0D;
                d11 = d18;
            }

            aL = (d12 + d6 * d11);
            aN = (d13 + d7 * d11);

            a(aL, aM + be, aN);

            double d16 = aO;
            double d17 = aQ;
            if (aF != null) {
                d16 *= 0.75D;
                d17 *= 0.75D;
            }
            if (d16 < -d5)
                d16 = -d5;
            if (d16 > d5)
                d16 = d5;
            if (d17 < -d5)
                d17 = -d5;
            if (d17 > d5)
                d17 = d5;
            c(d16, 0.0D, d17);

            if ((arrayOfInt[0][1] != 0) && (OMathHelper.b(aL) - i1 == arrayOfInt[0][0]) && (OMathHelper.b(aN) - i3 == arrayOfInt[0][2]))
                a(aL, aM + arrayOfInt[0][1], aN);
            else if ((arrayOfInt[1][1] != 0) && (OMathHelper.b(aL) - i1 == arrayOfInt[1][0]) && (OMathHelper.b(aN) - i3 == arrayOfInt[1][2]))
                a(aL, aM + arrayOfInt[1][1], aN);

            if (aF != null) {
                aO *= 0.997D;
                aP *= 0.0D;
                aQ *= 0.997D;
            } else {
                if (d == 2) {
                    d18 = OMathHelper.a(f * f + g * g);
                    if (d18 > 0.01D) {
                        i4 = 1;
                        f /= d18;
                        g /= d18;
                        double d19 = 0.04D;
                        aO *= 0.8D;
                        aP *= 0.0D;
                        aQ *= 0.8D;
                        aO += f * d19;
                        aQ += g * d19;
                    } else {
                        aO *= 0.9D;
                        aP *= 0.0D;
                        aQ *= 0.9D;
                    }
                }
                aO *= 0.96D;
                aP *= 0.0D;
                aQ *= 0.96D;
            }

            OVec3D localOVec3D2 = g(aL, aM, aN);
            if ((localOVec3D2 != null) && (localOVec3D1 != null)) {
                double d20 = (localOVec3D1.b - localOVec3D2.b) * 0.05D;

                d10 = Math.sqrt(aO * aO + aQ * aQ);
                if (d10 > 0.0D) {
                    aO = (aO / d10 * (d10 + d20));
                    aQ = (aQ / d10 * (d10 + d20));
                }
                a(aL, localOVec3D2.b, aN);
            }

            int i6 = OMathHelper.b(aL);
            int i7 = OMathHelper.b(aN);
            if ((i6 != i1) || (i7 != i3)) {
                d10 = Math.sqrt(aO * aO + aQ * aQ);

                aO = (d10 * (i6 - i1));
                aQ = (d10 * (i7 - i3));
            }

            if (d == 2) {
                double d21 = OMathHelper.a(f * f + g * g);
                if ((d21 > 0.01D) && (aO * aO + aQ * aQ > 0.001D)) {
                    f /= d21;
                    g /= d21;

                    if (f * aO + g * aQ < 0.0D) {
                        f = 0.0D;
                        g = 0.0D;
                    } else {
                        f = aO;
                        g = aQ;
                    }
                }
            }
        } else {
            if (aO < -d5)
                aO = (-d5);
            if (aO > d5)
                aO = d5;
            if (aQ < -d5)
                aQ = (-d5);
            if (aQ > d5)
                aQ = d5;
            if (aW) {
                aO *= 0.5D;
                aP *= 0.5D;
                aQ *= 0.5D;
            }
            c(aO, aP, aQ);

            if (!aW) {
                aO *= 0.95D;
                aP *= 0.95D;
                aQ *= 0.95D;
            }
        }

        aS = 0.0F;
        double d22 = aI - aL;
        double d23 = aK - aN;
        if (d22 * d22 + d23 * d23 > 0.001D) {
            aR = (float) (Math.atan2(d23, d22) * 180.0D / 3.141592653589793D);
            if (i)
                aR += 180.0F;
        }

        double d24 = aR - aT;
        while (d24 >= 180.0D)
            d24 -= 360.0D;
        while (d24 < -180.0D)
            d24 += 360.0D;
        if ((d24 < -170.0D) || (d24 >= 170.0D)) {
            aR += 180.0F;
            i = (!i);
        }
        c(aR, aS);

        List localList = aH.b(this, aV.b(0.2D, 0.0D, 0.2D));
        if ((localList != null) && (localList.size() > 0))
            for (int i8 = 0; i8 < localList.size(); i8++) {
                OEntity localOEntity = (OEntity) localList.get(i8);
                if ((localOEntity != aF) && (localOEntity.d_()) && ((localOEntity instanceof OEntityMinecart)))
                    localOEntity.h(this);
            }

        if ((aF != null) && (aF.bd))
            aF = null;

        if ((i4 != 0) && (br.nextInt(4) == 0)) {
            e -= 1;
            if (e < 0)
                f = (g = 0.0D);
            aH.a("largesmoke", aL, aM + 0.8D, aN, 0.0D, 0.0D, 0.0D);
        }
    }

    public OVec3D g(double paramDouble1, double paramDouble2, double paramDouble3) {
        int i1 = OMathHelper.b(paramDouble1);
        int i2 = OMathHelper.b(paramDouble2);
        int i3 = OMathHelper.b(paramDouble3);
        // CanaryMod: Change of the cart
        manager.callHook(PluginLoader.Hook.VEHICLE_POSITIONCHANGE, cart, i1, i2, i3);

        if (OBlockMinecartTrack.g(aH, i1, i2 - 1, i3))
            i2--;

        int var10 = aH.a(i1, i2, i3);
        if (OBlockMinecartTrack.c(var10)) {
            int i4 = aH.b(i1, i2, i3);
            paramDouble2 = i2;

            if (((OBlockMinecartTrack) OBlock.m[var10]).d()) {
                i4 &= 7;
            }

            if ((i4 >= 2) && (i4 <= 5))
                paramDouble2 = i2 + 1;
            if (i4 > 9) {
                i4 = 9;
            }

            int[][] arrayOfInt = j[i4];

            double d1 = 0.0D;
            double d2 = i1 + 0.5D + arrayOfInt[0][0] * 0.5D;
            double d3 = i2 + 0.5D + arrayOfInt[0][1] * 0.5D;
            double d4 = i3 + 0.5D + arrayOfInt[0][2] * 0.5D;
            double d5 = i1 + 0.5D + arrayOfInt[1][0] * 0.5D;
            double d6 = i2 + 0.5D + arrayOfInt[1][1] * 0.5D;
            double d7 = i3 + 0.5D + arrayOfInt[1][2] * 0.5D;

            double d8 = d5 - d2;
            double d9 = (d6 - d3) * 2.0D;
            double d10 = d7 - d4;

            if (d8 == 0.0D) {
                paramDouble1 = i1 + 0.5D;
                d1 = paramDouble3 - i3;
            } else if (d10 == 0.0D) {
                paramDouble3 = i3 + 0.5D;
                d1 = paramDouble1 - i1;
            } else {
                double d11 = paramDouble1 - d2;
                double d12 = paramDouble3 - d4;

                double d13 = (d11 * d8 + d12 * d10) * 2.0D;
                d1 = d13;
            }

            paramDouble1 = d2 + d8 * d1;
            paramDouble2 = d3 + d9 * d1;
            paramDouble3 = d4 + d10 * d1;
            if (d9 < 0.0D)
                paramDouble2 += 1.0D;
            if (d9 > 0.0D)
                paramDouble2 += 0.5D;
            return OVec3D.b(paramDouble1, paramDouble2, paramDouble3);
        }
        return null;
    }

    @Override
    protected void b(ONBTTagCompound paramONBTTagCompound) {
        paramONBTTagCompound.a("Type", d);

        if (d == 2) {
            paramONBTTagCompound.a("PushX", f);
            paramONBTTagCompound.a("PushZ", g);
            paramONBTTagCompound.a("Fuel", (short) e);
        } else if (d == 1) {
            ONBTTagList localONBTTagList = new ONBTTagList();

            for (int i1 = 0; i1 < h.length; i1++)
                if (h[i1] != null) {
                    ONBTTagCompound localONBTTagCompound = new ONBTTagCompound();
                    localONBTTagCompound.a("Slot", (byte) i1);
                    h[i1].a(localONBTTagCompound);
                    localONBTTagList.a(localONBTTagCompound);
                }
            paramONBTTagCompound.a("Items", localONBTTagList);
        }
    }

    @Override
    protected void a(ONBTTagCompound paramONBTTagCompound) {
        d = paramONBTTagCompound.e("Type");
        if (d == 2) {
            f = paramONBTTagCompound.h("PushX");
            g = paramONBTTagCompound.h("PushZ");
            e = paramONBTTagCompound.d("Fuel");
        } else if (d == 1) {
            ONBTTagList localONBTTagList = paramONBTTagCompound.l("Items");
            h = new OItemStack[a()];
            for (int i1 = 0; i1 < localONBTTagList.c(); i1++) {
                ONBTTagCompound localONBTTagCompound = (ONBTTagCompound) localONBTTagList.a(i1);
                int i2 = localONBTTagCompound.c("Slot") & 0xFF;
                if ((i2 < 0) || (i2 >= h.length))
                    continue;
                h[i2] = new OItemStack(localONBTTagCompound);
            }
        }
    }

    @Override
    public void h(OEntity paramOEntity) {
        if (aH.v)
            return;

        if (paramOEntity == aF)
            return;
        // CanaryMod: Collision of a cart
        if ((Boolean) manager.callHook(PluginLoader.Hook.VEHICLE_COLLISION, cart, paramOEntity.entity))
            return;

        if (((paramOEntity instanceof OEntityLiving)) && (!(paramOEntity instanceof OEntityPlayer)) && (d == 0) && (aO * aO + aQ * aQ > 0.01D) && (aF == null) && (paramOEntity.aG == null))
            paramOEntity.b(this);

        double d1 = paramOEntity.aL - aL;
        double d2 = paramOEntity.aN - aN;

        double d3 = d1 * d1 + d2 * d2;
        if (d3 >= 0.0001D) {
            d3 = OMathHelper.a(d3);
            d1 /= d3;
            d2 /= d3;
            double d4 = 1.0D / d3;
            if (d4 > 1.0D)
                d4 = 1.0D;
            d1 *= d4;
            d2 *= d4;
            d1 *= 0.1D;
            d2 *= 0.1D;

            d1 *= (1.0F - bq);
            d2 *= (1.0F - bq);
            d1 *= 0.5D;
            d2 *= 0.5D;

            if ((paramOEntity instanceof OEntityMinecart)) {
                double d5 = paramOEntity.aO + aO;
                double d6 = paramOEntity.aQ + aQ;

                if ((((OEntityMinecart) paramOEntity).d == 2) && (d != 2)) {
                    aO *= 0.2D;
                    aQ *= 0.2D;
                    f(paramOEntity.aO - d1, 0.0D, paramOEntity.aQ - d2);
                    paramOEntity.aO *= 0.7D;
                    paramOEntity.aQ *= 0.7D;
                } else if ((((OEntityMinecart) paramOEntity).d != 2) && (d == 2)) {
                    paramOEntity.aO *= 0.2D;
                    paramOEntity.aQ *= 0.2D;
                    paramOEntity.f(aO + d1, 0.0D, aR + d2);
                    aO *= 0.7D;
                    aQ *= 0.7D;
                } else {
                    d5 /= 2.0D;
                    d6 /= 2.0D;
                    aO *= 0.2D;
                    aQ *= 0.2D;
                    f(d5 - d1, 0.0D, d6 - d2);
                    paramOEntity.aO *= 0.2D;
                    paramOEntity.aQ *= 0.2D;
                    paramOEntity.f(d5 + d1, 0.0D, d6 + d2);
                }
            } else {
                f(-d1, 0.0D, -d2);
                paramOEntity.f(d1 / 4.0D, 0.0D, d2 / 4.0D);
            }
        }
    }

    public int a() {
        return 27;
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
        return getName();
    }

    public int d() {
        return 64;
    }

    public void i() {
    }

    @Override
    public boolean a(OEntityPlayer paramOEntityPlayer) {
        // CanaryMod: Entering the cart
        manager.callHook(PluginLoader.Hook.VEHICLE_ENTERED, cart, paramOEntityPlayer.entity);

        
        if (d == 0) {
            if ((aF != null) && ((aF instanceof OEntityPlayer)) && (aF != paramOEntityPlayer))
                return true;
            if (!aH.v)
                paramOEntityPlayer.b(this);
        } else if (d == 1) {
            if (!aH.v) {
                // CanaryMod cast this down to fix decompiler error.
                paramOEntityPlayer.a((OIInventory) this);
            }
        } else if (d == 2) {
            OItemStack localOItemStack = paramOEntityPlayer.i.b();
            if ((localOItemStack != null) && (localOItemStack.c == OItem.k.bd)) {
                if (--localOItemStack.a == 0)
                    paramOEntityPlayer.i.a(paramOEntityPlayer.i.c, null);
                e += 1200;
            }

            f = (aL - paramOEntityPlayer.aL);
            g = (aN - paramOEntityPlayer.aN);
        }
        return true;
    }

    public boolean a_(OEntityPlayer paramOEntityPlayer) {
        if (bd)
            return false;
        return paramOEntityPlayer.g(this) <= 64.0D;
    }

   
}
