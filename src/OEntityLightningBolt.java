import java.util.List;

public class OEntityLightningBolt extends OEntityWeatherEffect {

   private int b;
   public long a = 0L;
   private int c;


   public OEntityLightningBolt(OWorld var1, double var2, double var4, double var6) {
      super(var1);
      this.c(var2, var4, var6, 0.0F, 0.0F);
      this.b = 2;
      this.a = this.br.nextLong();
      this.c = this.br.nextInt(3) + 1;
      if(var1.l >= 2 && var1.a(OMathHelper.b(var2), OMathHelper.b(var4), OMathHelper.b(var6), 10)) {
         int var8 = OMathHelper.b(var2);
         int var9 = OMathHelper.b(var4);
         int var10 = OMathHelper.b(var6);
         if(var1.a(var8, var9, var10) == 0 && OBlock.ar.a(var1, var8, var9, var10)) {
            var1.e(var8, var9, var10, OBlock.ar.bl);
         }

         for(var8 = 0; var8 < 4; ++var8) {
            var9 = OMathHelper.b(var2) + this.br.nextInt(3) - 1;
            var10 = OMathHelper.b(var4) + this.br.nextInt(3) - 1;
            int var11 = OMathHelper.b(var6) + this.br.nextInt(3) - 1;
            if(var1.a(var9, var10, var11) == 0 && OBlock.ar.a(var1, var9, var10, var11)) {
               var1.e(var9, var10, var11, OBlock.ar.bl);
            }
         }
      }

   }

   public void p_() {
      super.p_();
      if(this.b == 2) {
         this.aH.a(this.aL, this.aM, this.aN, "ambient.weather.thunder", 10000.0F, 0.8F + this.br.nextFloat() * 0.2F);
         this.aH.a(this.aL, this.aM, this.aN, "random.explode", 2.0F, 0.5F + this.br.nextFloat() * 0.2F);
      }

      --this.b;
      if(this.b < 0) {
         if(this.c == 0) {
            this.G();
         } else if(this.b < -this.br.nextInt(10)) {
            --this.c;
            this.b = 1;
            this.a = this.br.nextLong();
            if(this.aH.a(OMathHelper.b(this.aL), OMathHelper.b(this.aM), OMathHelper.b(this.aN), 10)) {
               int var1 = OMathHelper.b(this.aL);
               int var2 = OMathHelper.b(this.aM);
               int var3 = OMathHelper.b(this.aN);
               if(this.aH.a(var1, var2, var3) == 0 && OBlock.ar.a(this.aH, var1, var2, var3)) {
                  this.aH.e(var1, var2, var3, OBlock.ar.bl);
               }
            }
         }
      }

      if(this.b >= 0) {
         double var4 = 3.0D;
         List var8 = this.aH.b((OEntity)this, OAxisAlignedBB.b(this.aL - var4, this.aM - var4, this.aN - var4, this.aL + var4, this.aM + 6.0D + var4, this.aN + var4));

         for(int var6 = 0; var6 < var8.size(); ++var6) {
            OEntity var7 = (OEntity)var8.get(var6);
            if (!(Boolean) manager.callHook(PluginLoader.Hook.LIGHTNING_STRIKE, entity))
                var7.a(this);
         }

         this.aH.i = 2;
      }

   }

   protected void b() {}

   protected void a(ONBTTagCompound var1) {}

   protected void b(ONBTTagCompound var1) {}
}