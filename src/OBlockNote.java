
public class OBlockNote extends OBlockContainer {

    public OBlockNote(int paramInt) {
        super(paramInt, 74, OMaterial.d);
    }

    @Override
    public int a(int paramInt) {
        return bm;
    }

    @Override
    public void a(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        if ((paramInt4 > 0) && (OBlock.m[paramInt4].d())) {
            boolean bool = paramOWorld.q(paramInt1, paramInt2, paramInt3);
            OTileEntityNote localOTileEntityNote = (OTileEntityNote) paramOWorld.n(paramInt1, paramInt2, paramInt3);
            if (localOTileEntityNote.b != bool) {
                if (bool) {
                    localOTileEntityNote.a(paramOWorld, paramInt1, paramInt2, paramInt3);
                }
                localOTileEntityNote.b = bool;
            }
        }
    }

    @Override
    public boolean a(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, OEntityPlayer paramOEntityPlayer) {
        if (paramOWorld.B) {
            return true;
        }
        OTileEntityNote localOTileEntityNote = (OTileEntityNote) paramOWorld.n(paramInt1, paramInt2, paramInt3);
        localOTileEntityNote.a();
        localOTileEntityNote.a(paramOWorld, paramInt1, paramInt2, paramInt3);
        return true;
    }

    @Override
    public void b(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, OEntityPlayer paramOEntityPlayer) {
        if (paramOWorld.B) {
            return;
        }
        OTileEntityNote localOTileEntityNote = (OTileEntityNote) paramOWorld.n(paramInt1, paramInt2, paramInt3);
        localOTileEntityNote.a(paramOWorld, paramInt1, paramInt2, paramInt3);
    }

    @Override
    protected OTileEntity a_() {
        return new OTileEntityNote();
    }

    @Override
    public void a(OWorld paramOWorld, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
        float f = (float) Math.pow(2.0D, (paramInt5 - 12) / 12.0D);

        // CanaryMod: bd gets jarjar'd, reverse here
        String str = "harp";
        if (paramInt4 == 1) {
            str = "bd";
        }
        if (paramInt4 == 2) {
            str = "snare";
        }
        if (paramInt4 == 3) {
            str = "hat";
        }
        if (paramInt4 == 4) {
            str = "bassattack";
        }

        paramOWorld.a(paramInt1 + 0.5D, paramInt2 + 0.5D, paramInt3 + 0.5D, "note." + str, 3.0F, f);
        paramOWorld.a("note", paramInt1 + 0.5D, paramInt2 + 1.2D, paramInt3 + 0.5D, paramInt5 / 24.0D, 0.0D, 0.0D);
    }
}