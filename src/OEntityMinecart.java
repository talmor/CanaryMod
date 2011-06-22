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

    @Override
    public void setContents(OItemStack[] values) {
        h = values;
    }
    
    @Override
    public OItemStack[] getContents() {
        return h;
    }
    
    @Override
    public OItemStack getContentsAt(int index) {
        return c_(index);
    }

    @Override
    public void setContentsAt(int index, OItemStack value) {
        a(index, value);
    }

    @Override
    public int getContentsSize() {
        return a();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String value) {
        name = value;
    }

    public OEntityMinecart(OWorld paramOWorld) {
        super(paramOWorld);
        aI = true;
        b(0.98F, 0.7F);
        bi = (bk / 2.0F);
        

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
        return paramOEntity.aZ;
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
        a(paramDouble1, paramDouble2 + bi, paramDouble3);

        aS = 0.0D;
        aT = 0.0D;
        aU = 0.0D;

        aM = paramDouble1;
        aN = paramDouble2;
        aO = paramDouble3;
        d = paramInt;
        // CanaryMod: Creation of the cart
        manager.callHook(PluginLoader.Hook.VEHICLE_CREATE, cart);
    }

    @Override
    public double m() {
        return bk * 0.0D - 0.3D;
    }

    @Override
    public boolean a(OEntity paramOEntity, int paramInt) {
        // CanaryMod: Attack of the cart
        if ((Boolean) manager.callHook(PluginLoader.Hook.VEHICLE_DAMAGE, cart, paramOEntity == null ? null : paramOEntity.entity, paramInt))
            return true;


        if ((aL.B) || (bh))
            return true;
        c = (-c);
        b = 10;
        ae();
        a += paramInt * 10;
        if (a > 40) {
            if (aJ != null)
                aJ.b((OEntity) this);
            I();
            a(OItem.ax.be, 1, 0.0F);
            if (d == 1) {
                OEntityMinecart var3 = this;

                for (int var4 = 0; var4 < var3.a(); ++var4) {
                    OItemStack var5 = var3.c_(var4);
                    if (var5 != null) {
                        float var6 = this.bv.nextFloat() * 0.8F + 0.1F;
                        float var7 = this.bv.nextFloat() * 0.8F + 0.1F;
                        float var8 = this.bv.nextFloat() * 0.8F + 0.1F;

                        while (var5.a > 0) {
                            int var9 = this.bv.nextInt(21) + 10;
                            if (var9 > var5.a)
                                var9 = var5.a;

                            var5.a -= var9;
                            OEntityItem var10 = new OEntityItem(this.aL, this.aP + (double) var6, this.aQ + (double) var7, this.aR + (double) var8, new OItemStack(var5.c, var9, var5.h()));
                            float var11 = 0.05F;
                            var10.aS = (double) ((float) this.bv.nextGaussian() * var11);
                            var10.aT = (double) ((float) this.bv.nextGaussian() * var11 + 0.2F);
                            var10.aU = (double) ((float) this.bv.nextGaussian() * var11);
                            this.aL.b((OEntity) var10);
                        }
                    }
                }
                a(OBlock.av.bn, 1, 0.0F);
            } else if (d == 2)
                a(OBlock.aC.bn, 1, 0.0F);
        }
        return true;
    }

    @Override
    public boolean n_() {
        return !bh;
    }

    @Override
    public void I() {
        // CanaryMod: Destruction of the cart
        manager.callHook(PluginLoader.Hook.VEHICLE_DESTROYED, cart);

        
        for (int i1 = 0; i1 < a(); i1++) {
            OItemStack localOItemStack = c_(i1);
            if (localOItemStack != null) {
                float f1 = bv.nextFloat() * 0.8F + 0.1F;
                float f2 = bv.nextFloat() * 0.8F + 0.1F;
                float f3 = bv.nextFloat() * 0.8F + 0.1F;

                while (localOItemStack.a > 0) {
                    int i2 = bv.nextInt(21) + 10;
                    if (i2 > localOItemStack.a)
                        i2 = localOItemStack.a;
                    localOItemStack.a -= i2;

                    OEntityItem localOEntityItem = new OEntityItem(aL, aP + f1, aQ + f2, aR + f3, new OItemStack(localOItemStack.c, i2, localOItemStack.h()));
                    float f4 = 0.05F;
                    localOEntityItem.aS = ((float) bv.nextGaussian() * f4);
                    localOEntityItem.aT= ((float) bv.nextGaussian() * f4 + 0.2F);
                    localOEntityItem.aU = ((float) bv.nextGaussian() * f4);
                    aL.b(localOEntityItem);
                }
            }
        }
        super.I();
    }

    @Override
    public void o_() {
        // CanaryMod: Update of the cart
        manager.callHook(PluginLoader.Hook.VEHICLE_UPDATE, cart);

        
        if (b > 0)
            b -= 1;
        if (a > 0)
            a -= 1;

        if ((aL.B) && (k > 0)) {
            if (k > 0) {
                double d1 = aP + (l - aP) / k;
                double d2 = aQ + (m - aQ) / k;
                double d3 = aR + (n - aR) / k;

                double d4 = o - aV;
                while (d4 < -180.0D)
                    d4 += 360.0D;
                while (d4 >= 180.0D)
                    d4 -= 360.0D;
                aV = (float) (aV + d4 / k);
                aW = (float) (aW + (p - aW) / k);

                k -= 1;
                a(d1, d2, d3);
                c(aV, aW);
            } else {
                a(aP, aQ, aR);
                c(aV, aW);
            }

            return;
        }
        aM = aP;
        aN = aQ;
        aO = aR;

        aT -= 0.04D;

        int i1 = OMathHelper.b(aP);
        int i2 = OMathHelper.b(aQ);
        int i3 = OMathHelper.b(aR);
        // CanaryMod: Change of the cart
        manager.callHook(PluginLoader.Hook.VEHICLE_POSITIONCHANGE, cart, i1, i2, i3);

        if (OBlockRail.g(this.aL, i1, i2 - 1, i3))
            i2--;

        double d5 = 0.4D;
        int i4 = 0;

        double d4 = 0.0078125D;
        int var15 = aL.a(i1, i2, i3);
        if (OBlockRail.c(var15)) {
            OVec3D localOVec3D1 = h(aP, aQ, aR);
            int i5 = aL.b(i1, i2, i3);
            aQ = i2;
            
            boolean var18 = false, var19 = false;
            if (var15 == OBlock.U.bn) {
                var18 = (i5 & 8) != 0;
                var19 = !var18;
            }
            
            if (((OBlockRail) OBlock.m[var15]).e()) {
                i5 &= 7;
            }

            
            if ((i5 >= 2) && (i5 <= 5))
                aQ = (i2 + 1);

            if (i5 == 2)
                aS -= d4;
            if (i5 == 3)
                aS += d4;
            if (i5 == 4)
                aU += d4;
            if (i5 == 5)
                aU -= d4;

            int[][] arrayOfInt = j[i5];

            double d6 = arrayOfInt[1][0] - arrayOfInt[0][0];
            double d7 = arrayOfInt[1][2] - arrayOfInt[0][2];
            double d8 = Math.sqrt(d6 * d6 + d7 * d7);

            double d9 = aS * d6 + aU * d7;
            if (d9 < 0.0D) {
                d6 = -d6;
                d7 = -d7;
            }

            double d10 = Math.sqrt(aS * aS + aU * aU);

            aS = (d10 * d6 / d8);
            aU = (d10 * d7 / d8);
            
            if (var19) {
                double d11 = Math.sqrt(aS * aS + aU * aU);
                if (d11 < 0.03D) {
                    aS *= 0.0D;
                    aT *= 0.0D;
                    aU *= 0.0D;
                } else {
                    aS *= 0.5D;
                    aT *= 0.0D;
                    aU *= 0.5D;
                }
            }

            double d11 = 0.0D;
            double d12 = i1 + 0.5D + arrayOfInt[0][0] * 0.5D;
            double d13 = i3 + 0.5D + arrayOfInt[0][2] * 0.5D;
            double d14 = i1 + 0.5D + arrayOfInt[1][0] * 0.5D;
            double d15 = i3 + 0.5D + arrayOfInt[1][2] * 0.5D;

            d6 = d14 - d12;
            d7 = d15 - d13;
            double d18;
            if (d6 == 0.0D) {
                aP = (i1 + 0.5D);
                d11 = aR - i3;
            } else if (d7 == 0.0D) {
                aR = (i3 + 0.5D);
                d11 = aP - i1;
            } else {
                double d16 = aP - d12;
                double d17 = aR - d13;

                d18 = (d16 * d6 + d17 * d7) * 2.0D;
                d11 = d18;
            }

            aP = (d12 + d6 * d11);
            aR = (d13 + d7 * d11);

            a(aP, aQ + bi, aR);

            double d16 = aS;
            double d17 = aU;
            if (aJ != null) {
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

            if ((arrayOfInt[0][1] != 0) && (OMathHelper.b(aP) - i1 == arrayOfInt[0][0]) && (OMathHelper.b(aR) - i3 == arrayOfInt[0][2]))
                a(aP, aQ + arrayOfInt[0][1], aR);
            else if ((arrayOfInt[1][1] != 0) && (OMathHelper.b(aP) - i1 == arrayOfInt[1][0]) && (OMathHelper.b(aR) - i3 == arrayOfInt[1][2]))
                a(aP, aQ + arrayOfInt[1][1], aR);

            if (aJ != null) {
                aS *= 0.997D;
                aT *= 0.0D;
                aU *= 0.997D;
            } else {
                if (d == 2) {
                    d18 = OMathHelper.a(f * f + g * g);
                    if (d18 > 0.01D) {
                        i4 = 1;
                        f /= d18;
                        g /= d18;
                        double d19 = 0.04D;
                        aS *= 0.8D;
                        aT *= 0.0D;
                        aU *= 0.8D;
                        aS += f * d19;
                        aU += g * d19;
                    } else {
                        aS *= 0.9D;
                        aT *= 0.0D;
                        aU *= 0.9D;
                    }
                }
                aS *= 0.96D;
                aT *= 0.0D;
                aU *= 0.96D;
            }

            OVec3D localOVec3D2 = h(aP, aQ, aR);
            if ((localOVec3D2 != null) && (localOVec3D1 != null)) {
                double d20 = (localOVec3D1.b - localOVec3D2.b) * 0.05D;

                d10 = Math.sqrt(aS * aS + aU * aU);
                if (d10 > 0.0D) {
                    aS = (aS / d10 * (d10 + d20));
                    aU = (aU / d10 * (d10 + d20));
                }
                a(aP, localOVec3D2.b, aR);
            }

            int i6 = OMathHelper.b(aP);
            int i7 = OMathHelper.b(aR);
            if ((i6 != i1) || (i7 != i3)) {
                d10 = Math.sqrt(aS * aS + aU * aU);

                aS = (d10 * (i6 - i1));
                aU = (d10 * (i7 - i3));
            }

            if (d == 2) {
                double d21 = OMathHelper.a(f * f + g * g);
                if ((d21 > 0.01D) && (aS * aS + aU * aU > 0.001D)) {
                    f /= d21;
                    g /= d21;

                    if (f * aS + g * aU < 0.0D) {
                        f = 0.0D;
                        g = 0.0D;
                    } else {
                        f = aS;
                        g = aU;
                    }
                }
            }
            if (var18) {
                double d21 = Math.sqrt(aS * aS + aU * aU);
                if (d21 > 0.01D) {
                    double d22 = 0.06D;
                    aS += aS / d21 * d22;
                    aU += aU / d21 * d22;
                } else if (i5 == 1) {
                    if (aL.d(i1 - 1, i2, i3)) {
                        aS = 0.02D;
                    } else if (aL.d(i1 + 1, i2, i3)) {
                        aS = -0.02D;
                    }
                } else if (i5 == 0) {
                    if (aL.d(i1, i2, i3 - 1)) {
                        aU = 0.02D;
                    } else if (aL.d(i1, i2, i3 + 1)) {
                        aU = -0.02D;
                    }
                }
            }
        } else {
            if (aS < -d5)
                aS = (-d5);
            if (aS > d5)
                aS = d5;
            if (aU < -d5)
                aU = (-d5);
            if (aU > d5)
                aU = d5;
            if (ba) {
                aS *= 0.5D;
                aT *= 0.5D;
                aU *= 0.5D;
            }
            c(aS, aT, aU);

            if (!ba) {
                aS *= 0.95D;
                aT *= 0.95D;
                aU *= 0.95D;
            }
        }

        aW = 0.0F;
        double d22 = aM - aP;
        double d23 = aO - aR;
        if (d22 * d22 + d23 * d23 > 0.001D) {
            aV = (float) (Math.atan2(d23, d22) * 180.0D / 3.141592653589793D);
            if (i)
                aV += 180.0F;
        }

        double d24 = aV - aX;
        while (d24 >= 180.0D)
            d24 -= 360.0D;
        while (d24 < -180.0D)
            d24 += 360.0D;
        if ((d24 < -170.0D) || (d24 >= 170.0D)) {
            aV += 180.0F;
            i = (!i);
        }
        c(aV, aW);

        List localList = aL.b(this, aZ.b(0.2D, 0.0D, 0.2D));
        if ((localList != null) && (localList.size() > 0))
            for (int i8 = 0; i8 < localList.size(); i8++) {
                OEntity localOEntity = (OEntity) localList.get(i8);
                if ((localOEntity != aJ) && (localOEntity.d_()) && ((localOEntity instanceof OEntityMinecart)))
                    localOEntity.h(this);
            }

        if ((aJ != null) && (aJ.bh))
            aJ = null;

        if ((i4 != 0) && (bv.nextInt(4) == 0)) {
            e -= 1;
            if (e < 0)
                f = (g = 0.0D);
            aL.a("largesmoke", aP, aQ + 0.8D, aR, 0.0D, 0.0D, 0.0D);
        }
    }

    public OVec3D h(double paramDouble1, double paramDouble2, double paramDouble3) {
        int i1 = OMathHelper.b(paramDouble1);
        int i2 = OMathHelper.b(paramDouble2);
        int i3 = OMathHelper.b(paramDouble3);
        // CanaryMod: Change of the cart
        manager.callHook(PluginLoader.Hook.VEHICLE_POSITIONCHANGE, cart, i1, i2, i3);

        if (OBlockRail.g(aL, i1, i2 - 1, i3))
            i2--;

        int var10 = aL.a(i1, i2, i3);
        if (OBlockRail.c(var10)) {
            int i4 = aL.b(i1, i2, i3);
            paramDouble2 = i2;

            if (((OBlockRail) OBlock.m[var10]).e()) {
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
        if (aL.B)
            return;

        if (paramOEntity == aJ)
            return;
        // CanaryMod: Collision of a cart
        if ((Boolean) manager.callHook(PluginLoader.Hook.VEHICLE_COLLISION, cart, paramOEntity.entity))
            return;

        if (((paramOEntity instanceof OEntityLiving)) && (!(paramOEntity instanceof OEntityPlayer)) && (d == 0) && (aS * aS + aU * aU > 0.01D) && (aJ == null) && (paramOEntity.aK == null))
            paramOEntity.b(this);

        double d1 = paramOEntity.aP - aP;
        double d2 = paramOEntity.aR - aR;

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

            d1 *= (1.0F - bu);
            d2 *= (1.0F - bu);
            d1 *= 0.5D;
            d2 *= 0.5D;

            if ((paramOEntity instanceof OEntityMinecart)) {
                double var10 = paramOEntity.aP - this.aP;
                double var12 = paramOEntity.aR - this.aR;
                double var14 = var10 * paramOEntity.aU + var12 * paramOEntity.aM;
                var14 *= var14;
                if (var14 > 5.0D)
                    return;

                double d5 = paramOEntity.aS + aS;
                double d6 = paramOEntity.aU + aU;

                if ((((OEntityMinecart) paramOEntity).d == 2) && (d != 2)) {
                    aS *= 0.2D;
                    aU *= 0.2D;
                    f(paramOEntity.aS - d1, 0.0D, paramOEntity.aU - d2);
                    paramOEntity.aS *= 0.7D;
                    paramOEntity.aU *= 0.7D;
                } else if ((((OEntityMinecart) paramOEntity).d != 2) && (d == 2)) {
                    paramOEntity.aS *= 0.2D;
                    paramOEntity.aU *= 0.2D;
                    paramOEntity.f(aS + d1, 0.0D, aU + d2);
                    aS *= 0.7D;
                    aU *= 0.7D;
                } else {
                    d5 /= 2.0D;
                    d6 /= 2.0D;
                    aS *= 0.2D;
                    aU *= 0.2D;
                    f(d5 - d1, 0.0D, d6 - d2);
                    paramOEntity.aS *= 0.2D;
                    paramOEntity.aU *= 0.2D;
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
            if ((aJ != null) && ((aJ instanceof OEntityPlayer)) && (aJ != paramOEntityPlayer))
                return true;
            if (!aL.B)
                paramOEntityPlayer.b(this);
        } else if (d == 1) {
            if (!aL.B) {
                // CanaryMod cast this down to fix decompiler error.
                paramOEntityPlayer.a((OIInventory) this);
            }
        } else if (d == 2) {
            OItemStack localOItemStack = paramOEntityPlayer.i.b();
            if ((localOItemStack != null) && (localOItemStack.c == OItem.k.be)) {
                if (--localOItemStack.a == 0)
                    paramOEntityPlayer.i.a(paramOEntityPlayer.i.c, null);
                e += 1200;
            }

            f = (aP - paramOEntityPlayer.aP);
            g = (aR - paramOEntityPlayer.aR);
        }
        return true;
    }

    public boolean a_(OEntityPlayer paramOEntityPlayer) {
        if (bh)
            return false;
        return paramOEntityPlayer.g(this) <= 64.0D;
    }

   
}
