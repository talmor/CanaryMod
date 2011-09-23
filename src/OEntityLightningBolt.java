import java.util.List;

public class OEntityLightningBolt extends OEntityWeatherEffect {

   private int b;
   public long a = 0L;
   private int c;


   public OEntityLightningBolt(OWorld var1, double var2, double var4, double var6) {
      super(var1);
      this.c(var2, var4, var6, 0.0F, 0.0F);
      this.b = 2;
      this.a = this.bL.nextLong();
      this.c = this.bL.nextInt(3) + 1;
      if(var1.v >= 2 && var1.a(OMathHelper.b(var2), OMathHelper.b(var4), OMathHelper.b(var6), 10)) {
         int var8 = OMathHelper.b(var2);
         int var9 = OMathHelper.b(var4);
         int var10 = OMathHelper.b(var6);
         if(var1.a(var8, var9, var10) == 0 && OBlock.as.c(var1, var8, var9, var10)) {
            // CanaryMod: Ignite hook
            Block block = this.bb.world.getBlockAt(var8, var9, var10);
            block.setStatus(5); // lightning
            if (!(Boolean) manager.callHook(PluginLoader.Hook.IGNITE, block, null))
               var1.e(var8, var9, var10, OBlock.as.bA);
         }

         for(var8 = 0; var8 < 4; ++var8) {
            var9 = OMathHelper.b(var2) + this.bL.nextInt(3) - 1;
            var10 = OMathHelper.b(var4) + this.bL.nextInt(3) - 1;
            int var11 = OMathHelper.b(var6) + this.bL.nextInt(3) - 1;
            if(var1.a(var9, var10, var11) == 0 && OBlock.as.c(var1, var9, var10, var11)) {
               // CanaryMod: Ignite hook
               Block block = bb.world.getBlockAt(var9, var10, var11);
               block.setStatus(5); // lightning
               if (!(Boolean) manager.callHook(PluginLoader.Hook.IGNITE, block, null))
                  var1.e(var9, var10, var11, OBlock.as.bA);
            }
         }
      }

   }

   public void s_() {
      super.s_();
      if(this.b == 2) {
         this.bb.a(this.bf, this.bg, this.bh, "ambient.weather.thunder", 10000.0F, 0.8F + this.bL.nextFloat() * 0.2F);
         this.bb.a(this.bf, this.bg, this.bh, "random.explode", 2.0F, 0.5F + this.bL.nextFloat() * 0.2F);
      }

      --this.b;
      if(this.b < 0) {
         if(this.c == 0) {
            this.N();
         } else if(this.b < -this.bL.nextInt(10)) {
            --this.c;
            this.b = 1;
            this.a = this.bL.nextLong();
            if(this.bb.a(OMathHelper.b(this.bf), OMathHelper.b(this.bg), OMathHelper.b(this.bh), 10)) {
               int var1 = OMathHelper.b(this.bf);
               int var2 = OMathHelper.b(this.bg);
               int var3 = OMathHelper.b(this.bh);
               if(this.bb.a(var1, var2, var3) == 0 && OBlock.as.c(this.bb, var1, var2, var3)) {
                  // CanaryMod: Ignite hook
                  Block block = bb.world.getBlockAt(var1, var2, var3);
                  block.setStatus(5); // lightning
                  if (!(Boolean) manager.callHook(PluginLoader.Hook.IGNITE, block, null))
                     this.bb.e(var1, var2, var3, OBlock.as.bA);
               }
            }
         }
      }

      if(this.b >= 0) {
         double var4 = 3.0D;
         List var8 = this.bb.b((OEntity)this, OAxisAlignedBB.b(this.bf - var4, this.bg - var4, this.bh - var4, this.bf + var4, this.bg + 6.0D + var4, this.bh + var4));

         for(int var6 = 0; var6 < var8.size(); ++var6) {
            OEntity var7 = (OEntity)var8.get(var6);
            // CanaryMod: Lightning strike hook
            if (!(Boolean) manager.callHook(PluginLoader.Hook.LIGHTNING_STRIKE, new BaseEntity(var7)))
               var7.a(this);
         }

         this.bb.s = 2;
      }

   }

   protected void b() {}

   protected void a(ONBTTagCompound var1) {}

   protected void b(ONBTTagCompound var1) {}
}
