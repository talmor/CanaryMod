
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public final class ModLoader {

   private static File cfgdir;
   private static File cfgfile;
   public static Level cfgLoggingLevel = Level.FINER;
   private static long clock = 0L;
   public static final boolean DEBUG = false;
   private static Field field_modifiers = null;
   private static Map classMap = null;
   private static boolean hasInit = false;
   private static int highestEntityId = 3000;
   private static final Map inGameHooks = new HashMap();
   private static MinecraftServer instance = null;
   private static int itemSpriteIndex = 0;
   private static int itemSpritesLeft = 0;
   private static File logfile;
   private static File modDir;
   private static final Logger logger = Logger.getLogger("ModLoader");
   private static FileHandler logHandler = null;
   private static Method method_RegisterEntityID = null;
   private static Method method_RegisterTileEntity = null;
   private static final LinkedList modList = new LinkedList();
   private static int nextBlockModelID = 1000;
   private static final Map overrides = new HashMap();
   public static final Properties props = new Properties();
   private static OBiomeGenBase[] standardBiomes;
   private static int terrainSpriteIndex = 0;
   private static int terrainSpritesLeft = 0;
   private static final boolean[] usedItemSprites = new boolean[256];
   private static final boolean[] usedTerrainSprites = new boolean[256];
   public static final String VERSION = "ModLoader Server Beta 1.8.1";
   private static Method method_getNextWindowId;
   private static Field field_currentWindowId;


   public static void AddAchievementDesc(OAchievement achievement, String s, String s1) {
      try {
         if(achievement.f.contains(".")) {
            String nosuchfieldexception = achievement.f.split("\\.")[1];
            setPrivateValue(OStatBase.class, achievement, 1, OStatCollector.a("achievement." + nosuchfieldexception));
            setPrivateValue(OAchievement.class, achievement, 3, OStatCollector.a("achievement." + nosuchfieldexception + ".desc"));
         } else {
            setPrivateValue(OStatBase.class, achievement, 1, s);
            setPrivateValue(OAchievement.class, achievement, 3, s1);
         }
      } catch (IllegalArgumentException var4) {
         logger.throwing("ModLoader", "AddAchievementDesc", var4);
         ThrowException(var4);
      } catch (SecurityException var5) {
         logger.throwing("ModLoader", "AddAchievementDesc", var5);
         ThrowException(var5);
      } catch (NoSuchFieldException var6) {
         logger.throwing("ModLoader", "AddAchievementDesc", var6);
         ThrowException(var6);
      }

   }

   public static int AddAllFuel(int i) {
      logger.finest("Finding fuel for " + i);
      int j = 0;

      for(Iterator iterator = modList.iterator(); iterator.hasNext() && j == 0; j = ((BaseMod)iterator.next()).AddFuel(i)) {
         ;
      }

      if(j != 0) {
         logger.finest("Returned " + j);
      }

      return j;
   }

   public static int AddArmor(String s) {
      return -1;
   }

   private static void addMod(ClassLoader classloader, String s) {
      try {
         String throwable = s.split("\\.")[0];
         if(throwable.contains("$")) {
            return;
         }

         if(props.containsKey(throwable) && (props.getProperty(throwable).equalsIgnoreCase("OPacket60Explosion") || props.getProperty(throwable).equalsIgnoreCase("off"))) {
            return;
         }

         Package package1 = ModLoader.class.getPackage();
         if(package1 != null) {
            throwable = package1.getName() + "." + throwable;
         }

         Class class1 = classloader.loadClass(throwable);
         if(!BaseMod.class.isAssignableFrom(class1)) {
            return;
         }

         setupProperties(class1);
         BaseMod basemod = (BaseMod)class1.newInstance();
         if(basemod != null) {
            modList.add(basemod);
            logger.fine("Mod Loaded: \"" + basemod.toString() + "\" from " + s);
            System.out.println("Mod Loaded: " + basemod.toString());
            MinecraftServer.a.info("Mod Loaded: " + basemod.toString());
         }
      } catch (Throwable var6) {
         logger.fine("Failed to load mod from \"" + s + "\"");
         System.out.println("Failed to load mod from \"" + s + "\"");
         logger.throwing("ModLoader", "addMod", var6);
         ThrowException(var6);
      }

   }

   private static void setupProperties(Class class1) throws IllegalArgumentException, IllegalAccessException, IOException, SecurityException, NoSuchFieldException {
      Properties properties = new Properties();
      File file = new File(cfgdir, class1.getName() + ".cfg");
      if(file.exists() && file.canRead()) {
         properties.load(new FileInputStream(file));
      }

      StringBuilder stringbuilder = new StringBuilder();
      Field[] afield;
      int i = (afield = class1.getFields()).length;

      for(int j = 0; j < i; ++j) {
         Field field = afield[j];
         if((field.getModifiers() & 8) != 0 && field.isAnnotationPresent(MLProp.class)) {
            Class class2 = field.getType();
            MLProp mlprop = (MLProp)field.getAnnotation(MLProp.class);
            String s = mlprop.name().length() != 0?mlprop.name():field.getName();
            Object obj = field.get((Object)null);
            StringBuilder stringbuilder1 = new StringBuilder();
            if(mlprop.min() != Double.NEGATIVE_INFINITY) {
               stringbuilder1.append(String.format(",>=%.1f", new Object[]{Double.valueOf(mlprop.min())}));
            }

            if(mlprop.max() != Double.POSITIVE_INFINITY) {
               stringbuilder1.append(String.format(",<=%.1f", new Object[]{Double.valueOf(mlprop.max())}));
            }

            StringBuilder stringbuilder2 = new StringBuilder();
            if(mlprop.info().length() > 0) {
               stringbuilder2.append(" -- ");
               stringbuilder2.append(mlprop.info());
            }

            stringbuilder.append(String.format("%s (%s:%s%s)%s\n", new Object[]{s, class2.getName(), obj, stringbuilder1, stringbuilder2}));
            if(properties.containsKey(s)) {
               String s1 = properties.getProperty(s);
               Object obj1 = null;
               if(class2.isAssignableFrom(String.class)) {
                  obj1 = s1;
               } else if(class2.isAssignableFrom(Integer.TYPE)) {
                  obj1 = Integer.valueOf(Integer.parseInt(s1));
               } else if(class2.isAssignableFrom(Short.TYPE)) {
                  obj1 = Short.valueOf(Short.parseShort(s1));
               } else if(class2.isAssignableFrom(Byte.TYPE)) {
                  obj1 = Byte.valueOf(Byte.parseByte(s1));
               } else if(class2.isAssignableFrom(Boolean.TYPE)) {
                  obj1 = Boolean.valueOf(Boolean.parseBoolean(s1));
               } else if(class2.isAssignableFrom(Float.TYPE)) {
                  obj1 = Float.valueOf(Float.parseFloat(s1));
               } else if(class2.isAssignableFrom(Double.TYPE)) {
                  obj1 = Double.valueOf(Double.parseDouble(s1));
               }

               if(obj1 != null) {
                  if(obj1 instanceof Number) {
                     double d = ((Number)obj1).doubleValue();
                     if(mlprop.min() != Double.NEGATIVE_INFINITY && d < mlprop.min() || mlprop.max() != Double.POSITIVE_INFINITY && d > mlprop.max()) {
                        continue;
                     }
                  }

                  logger.finer(s + " set to " + obj1);
                  if(!obj1.equals(obj)) {
                     field.set((Object)null, obj1);
                  }
               }
            } else {
               logger.finer(s + " not in config, using default: " + obj);
               properties.setProperty(s, obj.toString());
            }
         }
      }

      if(!properties.isEmpty() && (file.exists() || file.createNewFile()) && file.canWrite()) {
         properties.store(new FileOutputStream(file), stringbuilder.toString());
      }

   }

   public static int addOverride(String s, String s1) {
      return 0;
   }

   public static void addOverride(String s, String s1, int i) {
      boolean j = true;
      boolean k = false;
      byte j1;
      int k1;
      if(s.equals("/terrain.png")) {
         j1 = 0;
         k1 = terrainSpritesLeft;
      } else {
         if(!s.equals("/gui/items.png")) {
            return;
         }

         j1 = 1;
         k1 = itemSpritesLeft;
      }

      System.out.println("Overriding " + s + " with " + s1 + " @ " + i + ". " + k1 + " left.");
      logger.finer("addOverride(" + s + "," + s1 + "," + i + "). " + k1 + " left.");
      Object obj = (Map)overrides.get(Integer.valueOf(j1));
      if(obj == null) {
         obj = new HashMap();
         overrides.put(Integer.valueOf(j1), obj);
      }

      ((Map)obj).put(s1, Integer.valueOf(i));
   }

   public static void AddRecipe(OItemStack itemstack, Object[] aobj) {
      OCraftingManager.a().a(itemstack, aobj);
   }

   public static void AddShapelessRecipe(OItemStack itemstack, Object[] aobj) {
      OCraftingManager.a().b(itemstack, aobj);
   }

   public static void AddSmelting(int i, OItemStack itemstack) {
      OFurnaceRecipes.a().a(i, itemstack);
   }

   public static void AddSpawn(Class class1, int i, int j, int k, OEnumCreatureType enumcreaturetype) {
      AddSpawn(class1, i, j, k, enumcreaturetype, (OBiomeGenBase[])null);
   }

   public static void AddSpawn(Class class1, int i, int j, int k, OEnumCreatureType enumcreaturetype, OBiomeGenBase[] abiomegenbase) {
      if(class1 == null) {
         throw new IllegalArgumentException("entityClass cannot be null");
      } else if(enumcreaturetype == null) {
         throw new IllegalArgumentException("spawnList cannot be null");
      } else {
         if(abiomegenbase == null) {
            abiomegenbase = standardBiomes;
         }

         for(int l = 0; l < abiomegenbase.length; ++l) {
            List list = abiomegenbase[l].a(enumcreaturetype);
            if(list != null) {
               boolean flag = false;
               Iterator iterator = list.iterator();

               while(iterator.hasNext()) {
                  OSpawnListEntry spawnlistentry = (OSpawnListEntry)iterator.next();
                  if(spawnlistentry.a == class1) {
                     spawnlistentry.d = i;
                     spawnlistentry.b = j;
                     spawnlistentry.c = k;
                     flag = true;
                     break;
                  }
               }

               if(!flag) {
                  list.add(new OSpawnListEntry(class1, i, j, k));
               }
            }
         }

      }
   }

   public static void AddSpawn(String s, int i, int j, int k, OEnumCreatureType enumcreaturetype) {
      AddSpawn(s, i, j, k, enumcreaturetype, (OBiomeGenBase[])null);
   }

   public static void AddSpawn(String s, int i, int j, int k, OEnumCreatureType enumcreaturetype, OBiomeGenBase[] abiomegenbase) {
      Class class1 = (Class)classMap.get(s);
      if(class1 != null && OEntityLiving.class.isAssignableFrom(class1)) {
         AddSpawn(class1, i, j, k, enumcreaturetype, abiomegenbase);
      }

   }

   public static boolean DispenseEntity(OWorld world, double d, double d1, double d2, int i, int j, OItemStack itemstack) {
      boolean flag = false;

      for(Iterator iterator = modList.iterator(); iterator.hasNext() && !flag; flag = ((BaseMod)iterator.next()).DispenseEntity(world, d, d1, d2, i, j, itemstack)) {
         ;
      }

      return flag;
   }

   public static List getLoadedMods() {
      return Collections.unmodifiableList(modList);
   }

   public static Logger getLogger() {
      return logger;
   }

   public static MinecraftServer getMinecraftServerInstance() {
      return instance;
   }

   public static Object getPrivateValue(Class class1, Object obj, int i) throws IllegalArgumentException, SecurityException, NoSuchFieldException {
      try {
         Field illegalaccessexception = class1.getDeclaredFields()[i];
         illegalaccessexception.setAccessible(true);
         return illegalaccessexception.get(obj);
      } catch (IllegalAccessException var4) {
         logger.throwing("ModLoader", "getPrivateValue", var4);
         ThrowException("An impossible error has occured!", var4);
         return null;
      }
   }

   public static Object getPrivateValue(Class class1, Object obj, String s) throws IllegalArgumentException, SecurityException, NoSuchFieldException {
      try {
         Field illegalaccessexception = class1.getDeclaredField(s);
         illegalaccessexception.setAccessible(true);
         return illegalaccessexception.get(obj);
      } catch (IllegalAccessException var4) {
         logger.throwing("ModLoader", "getPrivateValue", var4);
         ThrowException("An impossible error has occured!", var4);
         return null;
      }
   }

   public static int getUniqueBlockModelID(BaseMod basemod, boolean flag) {
      return nextBlockModelID++;
   }

   public static int getUniqueEntityId() {
      return highestEntityId++;
   }

   private static int getUniqueItemSpriteIndex() {
      while(itemSpriteIndex < usedItemSprites.length) {
         if(!usedItemSprites[itemSpriteIndex]) {
            usedItemSprites[itemSpriteIndex] = true;
            --itemSpritesLeft;
            return itemSpriteIndex++;
         }

         ++itemSpriteIndex;
      }

      Exception exception = new Exception("No more empty item sprite indices left!");
      logger.throwing("ModLoader", "getUniqueItemSpriteIndex", exception);
      ThrowException(exception);
      return 0;
   }

   public static int getUniqueSpriteIndex(String s) {
      if(s.equals("/gui/items.png")) {
         return getUniqueItemSpriteIndex();
      } else if(s.equals("/terrain.png")) {
         return getUniqueTerrainSpriteIndex();
      } else {
         Exception exception = new Exception("No registry for this texture: " + s);
         logger.throwing("ModLoader", "getUniqueItemSpriteIndex", exception);
         ThrowException(exception);
         return 0;
      }
   }

   private static int getUniqueTerrainSpriteIndex() {
      while(terrainSpriteIndex < usedTerrainSprites.length) {
         if(!usedTerrainSprites[terrainSpriteIndex]) {
            usedTerrainSprites[terrainSpriteIndex] = true;
            --terrainSpritesLeft;
            return terrainSpriteIndex++;
         }

         ++terrainSpriteIndex;
      }

      Exception exception = new Exception("No more empty terrain sprite indices left!");
      logger.throwing("ModLoader", "getUniqueItemSpriteIndex", exception);
      ThrowException(exception);
      return 0;
   }

   private static void init() {
      hasInit = true;
      String s = "1111111111111111111111111111111111111101111111011111111111111001111111111111111111111011111010111111100110000011111110000000001111111001100000110000000100000011000000010000001100000000000000110000000000000000000000000000000000000000000000001100000000000000";
      String s1 = "1111111111111111111111111111110111111111111111111111110111111111111111111111000111111011111111111111001111000000111111111111100011111111000010001111011110000000111111000000000011111100000000001111000000000111111000000000001101000000000001111111111111000011";

      for(int throwable = 0; throwable < 256; ++throwable) {
         usedItemSprites[throwable] = s.charAt(throwable) == 49;
         if(!usedItemSprites[throwable]) {
            ++itemSpritesLeft;
         }

         usedTerrainSprites[throwable] = s1.charAt(throwable) == 49;
         if(!usedTerrainSprites[throwable]) {
            ++terrainSpritesLeft;
         }
      }

      try {
         classMap = (Map)getPrivateValue(OEntityList.class, (Object)null, 0);
         field_modifiers = Field.class.getDeclaredField("modifiers");
         field_modifiers.setAccessible(true);
         Field[] var16 = OBiomeGenBase.class.getDeclaredFields();
         LinkedList iterator = new LinkedList();

         for(int basemod = 0; basemod < var16.length; ++basemod) {
            Class class1 = var16[basemod].getType();
            if((var16[basemod].getModifiers() & 8) != 0 && class1.isAssignableFrom(OBiomeGenBase.class)) {
               OBiomeGenBase biomegenbase = (OBiomeGenBase)var16[basemod].get((Object)null);
               if(!(biomegenbase instanceof OBiomeGenHell)) {
                  iterator.add(biomegenbase);
               }
            }
         }

         standardBiomes = (OBiomeGenBase[])((OBiomeGenBase[])iterator.toArray(new OBiomeGenBase[0]));

         try {
            method_RegisterTileEntity = OTileEntity.class.getDeclaredMethod("OPacket7UseEntity", new Class[]{Class.class, String.class});
         } catch (NoSuchMethodException var8) {
            //method_RegisterTileEntity = OTileEntity.class.getDeclaredMethod("addMapping", new Class[]{Class.class, String.class});
            method_RegisterTileEntity = OTileEntity.class.getDeclaredMethod("a", new Class[]{Class.class, String.class});
         }

         method_RegisterTileEntity.setAccessible(true);

         try {
            method_RegisterEntityID = OEntityList.class.getDeclaredMethod("OPacket7UseEntity", new Class[]{Class.class, String.class, Integer.TYPE});
         } catch (NoSuchMethodException var7) {
            //method_RegisterEntityID = OEntityList.class.getDeclaredMethod("addMapping", new Class[]{Class.class, String.class, Integer.TYPE});
            method_RegisterEntityID = OEntityList.class.getDeclaredMethod("a", new Class[]{Class.class, String.class, Integer.TYPE});
         }

         method_RegisterEntityID.setAccessible(true);
      } catch (SecurityException var11) {
         logger.throwing("ModLoader", "init", var11);
         ThrowException(var11);
         throw new RuntimeException(var11);
      } catch (NoSuchFieldException var12) {
         logger.throwing("ModLoader", "init", var12);
         ThrowException(var12);
         throw new RuntimeException(var12);
      } catch (NoSuchMethodException var13) {
         logger.throwing("ModLoader", "init", var13);
         ThrowException(var13);
         throw new RuntimeException(var13);
      } catch (IllegalArgumentException var14) {
         logger.throwing("ModLoader", "init", var14);
         ThrowException(var14);
         throw new RuntimeException(var14);
      } catch (IllegalAccessException var15) {
         logger.throwing("ModLoader", "init", var15);
         ThrowException(var15);
         throw new RuntimeException(var15);
      }

      try {
         try {
            loadConfig();
         } catch (IOException var9) {
            if(!var9.getMessage().contains("No such file or directory")) {
               throw var9;
            }

            String var18 = "Error loading ModLoader config. Check the common problems section in the ModLoaderMP thread.";
            ThrowException(new RuntimeException(var18, var9));
         }

         if(props.containsKey("loggingLevel")) {
            cfgLoggingLevel = Level.parse(props.getProperty("loggingLevel"));
         }

         logger.setLevel(cfgLoggingLevel);
         if((logfile.exists() || logfile.createNewFile()) && logfile.canWrite() && logHandler == null) {
            logHandler = new FileHandler(logfile.getPath());
            logHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(logHandler);
         }

         logger.fine("ModLoader Server Beta 1.8.1 Initializing...");
         System.out.println("ModLoader Server Beta 1.8.1 Initializing...");
         MinecraftServer.a.info("ModLoader Server Beta 1.8.1 Initializing...");
         File var17 = new File(ModLoader.class.getProtectionDomain().getCodeSource().getLocation().toURI());
         modDir.mkdirs();
         readFromModFolder(modDir);
         readFromClassPath(var17);
         System.out.println("Done.");
         props.setProperty("loggingLevel", cfgLoggingLevel.getName());
         Iterator var19 = modList.iterator();

         while(var19.hasNext()) {
            BaseMod var20 = (BaseMod)var19.next();
            var20.ModsLoaded();
            if(!props.containsKey(var20.getClass().getName())) {
               props.setProperty(var20.getClass().getName(), "OBlockRedstoneWire");
            }
         }

         initStats();
         saveConfig();
      } catch (Throwable var10) {
         logger.throwing("ModLoader", "init", var10);
         ThrowException("ModLoader has failed to initialize.", var10);
         if(logHandler != null) {
            logHandler.close();
         }

         throw new RuntimeException(var10);
      }
   }

   private static void initStats() {
      int hashset;
      String obj;
      for(hashset = 0; hashset < OBlock.m.length; ++hashset) {
         if(!OStatList.a.containsKey(Integer.valueOf(16777216 + hashset)) && OBlock.m[hashset] != null && OBlock.m[hashset].m()) {
            obj = OStatCollector.a("stat.mineBlock", new Object[]{OBlock.m[hashset].k()});
            OStatList.C[hashset] = (new OStatCrafting(16777216 + hashset, obj, hashset)).d();
            OStatList.e.add(OStatList.C[hashset]);
         }
      }

      for(hashset = 0; hashset < OItem.c.length; ++hashset) {
         if(!OStatList.a.containsKey(Integer.valueOf(16908288 + hashset)) && OItem.c[hashset] != null) {
            obj = OStatCollector.a("stat.useItem", new Object[]{OItem.c[hashset].j()});
            OStatList.E[hashset] = (new OStatCrafting(16908288 + hashset, obj, hashset)).d();
            if(hashset >= OBlock.m.length) {
               OStatList.d.add(OStatList.E[hashset]);
            }
         }

         if(!OStatList.a.containsKey(Integer.valueOf(16973824 + hashset)) && OItem.c[hashset] != null && OItem.c[hashset].f()) {
            obj = OStatCollector.a("stat.breakItem", new Object[]{OItem.c[hashset].j()});
            OStatList.F[hashset] = (new OStatCrafting(16973824 + hashset, obj, hashset)).d();
         }
      }

      HashSet var6 = new HashSet();
      Iterator obj1 = OCraftingManager.a().b().iterator();

      while(obj1.hasNext()) {
         Object var7 = obj1.next();
         var6.add(Integer.valueOf(((OIRecipe)var7).b().c));
      }

      Iterator iterator2 = OFurnaceRecipes.a().b().values().iterator();

      while(iterator2.hasNext()) {
         Object var8 = iterator2.next();
         var6.add(Integer.valueOf(((OItemStack)var8).c));
      }

      iterator2 = var6.iterator();

      while(iterator2.hasNext()) {
         int k = ((Integer)iterator2.next()).intValue();
         if(!OStatList.a.containsKey(Integer.valueOf(16842752 + k)) && OItem.c[k] != null) {
            String s3 = OStatCollector.a("stat.craftItem", new Object[]{OItem.c[k].j()});
            OStatList.D[k] = (new OStatCrafting(16842752 + k, s3, k)).d();
         }
      }

   }

   public static boolean isModLoaded(String s) {
      Class class1 = null;

      try {
         class1 = Class.forName(s, false, MinecraftServer.class.getClassLoader());
      } catch (ClassNotFoundException var4) {
         return false;
      }

      if(class1 != null) {
         Iterator iterator = modList.iterator();

         while(iterator.hasNext()) {
            BaseMod basemod = (BaseMod)iterator.next();
            if(class1.isInstance(basemod)) {
               return true;
            }
         }
      }

      return false;
   }

   public static void loadConfig() throws IOException {
      cfgdir.mkdir();
      if(cfgfile.exists() || cfgfile.createNewFile()) {
         if(cfgfile.canRead()) {
            FileInputStream fileinputstream = new FileInputStream(cfgfile);
            props.load(fileinputstream);
            fileinputstream.close();
         }

      }
   }

   public static void OnTick(MinecraftServer minecraftserver) {
      if(!hasInit) {
         init();
         logger.fine("Initialized");
      }

      long l = 0L;
      if(minecraftserver.e != null && minecraftserver.e[0] != null) {
         l = minecraftserver.e[0].l();
         Iterator iterator = inGameHooks.entrySet().iterator();

         while(iterator.hasNext()) {
            Entry entry = (Entry)iterator.next();
            if(clock != l || !((Boolean)entry.getValue()).booleanValue()) {
               ((BaseMod)entry.getKey()).OnTickInGame(minecraftserver);
            }
         }
      }

      clock = l;
   }

   public static void PopulateChunk(OIChunkProvider ichunkprovider, int i, int j, OWorld world) {
      if(!hasInit) {
         init();
         logger.fine("Initialized");
      }

      Iterator iterator = modList.iterator();

      while(iterator.hasNext()) {
         BaseMod basemod = (BaseMod)iterator.next();
         if(ichunkprovider instanceof OChunkProviderGenerate) {
            basemod.GenerateSurface(world, world.w, i, j);
         } else if(ichunkprovider instanceof OChunkProviderHell) {
            basemod.GenerateNether(world, world.w, i, j);
         }
      }

   }

   private static void readFromModFolder(File file) throws IOException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException {
      ClassLoader classloader = MinecraftServer.class.getClassLoader();
      Method method = URLClassLoader.class.getDeclaredMethod("addURL", new Class[]{URL.class});
      method.setAccessible(true);
      if(!file.isDirectory()) {
         throw new IllegalArgumentException("folder must be a Directory.");
      } else {
         File[] afile = file.listFiles();

         for(int i = 0; i < afile.length; ++i) {
            File file1 = afile[i];
            if(file1.isDirectory() || file1.isFile() && (file1.getName().endsWith(".jar") || file1.getName().endsWith(".zip"))) {
               if(classloader instanceof URLClassLoader) {
                  method.invoke(classloader, new Object[]{file1.toURI().toURL()});
               }

               logger.finer("Adding mods from " + file1.getCanonicalPath());
               String s2;
               if(file1.isFile()) {
                  logger.finer("Zip found.");
                  FileInputStream package1 = new FileInputStream(file1);
                  ZipInputStream afile1 = new ZipInputStream(package1);

                  while(true) {
                     ZipEntry j = afile1.getNextEntry();
                     if(j == null) {
                        afile1.close();
                        package1.close();
                        break;
                     }

                     s2 = j.getName();
                     if(!j.isDirectory() && s2.startsWith("mod_") && s2.endsWith(".class")) {
                        addMod(classloader, s2);
                     }
                  }
               } else if(file1.isDirectory()) {
                  Package var10 = ModLoader.class.getPackage();
                  if(var10 != null) {
                     String var11 = var10.getName().replace('.', File.separatorChar);
                     file1 = new File(file1, var11);
                  }

                  logger.finer("Directory found.");
                  File[] var12 = file1.listFiles();
                  if(var12 != null) {
                     for(int var13 = 0; var13 < var12.length; ++var13) {
                        s2 = var12[var13].getName();
                        if(var12[var13].isFile() && s2.startsWith("mod_") && s2.endsWith(".class")) {
                           addMod(classloader, s2);
                        }
                     }
                  }
               }
            }
         }

      }
   }

   private static void readFromClassPath(File file) throws FileNotFoundException, IOException {
      logger.finer("Adding mods from " + file.getCanonicalPath());
      ClassLoader classloader = ModLoader.class.getClassLoader();
      String s2;
      if(file.isFile() && (file.getName().endsWith(".jar") || file.getName().endsWith(".zip"))) {
         logger.finer("Zip found.");
         FileInputStream var6 = new FileInputStream(file);
         ZipInputStream var8 = new ZipInputStream(var6);

         while(true) {
            ZipEntry var9 = var8.getNextEntry();
            if(var9 == null) {
               var6.close();
               break;
            }

            s2 = var9.getName();
            if(!var9.isDirectory() && s2.startsWith("mod_") && s2.endsWith(".class")) {
               addMod(classloader, s2);
            }
         }
      } else if(file.isDirectory()) {
         Package package1 = ModLoader.class.getPackage();
         if(package1 != null) {
            String afile = package1.getName().replace('.', File.separatorChar);
            file = new File(file, afile);
         }

         logger.finer("Directory found.");
         File[] var7 = file.listFiles();
         if(var7 != null) {
            for(int i = 0; i < var7.length; ++i) {
               s2 = var7[i].getName();
               if(var7[i].isFile() && s2.startsWith("mod_") && s2.endsWith(".class")) {
                  addMod(classloader, s2);
               }
            }
         }
      }

   }

   public static void RegisterBlock(OBlock block) {
      RegisterBlock(block, (Class)null);
   }

   public static void RegisterBlock(OBlock block, Class class1) {
      try {
         if(block == null) {
            throw new IllegalArgumentException("block parameter cannot be null.");
         }

         int nosuchmethodexception = block.bA;
         OItemBlock itemblock = null;
         if(class1 != null) {
            itemblock = (OItemBlock)class1.getConstructor(new Class[]{Integer.TYPE}).newInstance(new Object[]{Integer.valueOf(nosuchmethodexception - 256)});
         } else {
            itemblock = new OItemBlock(nosuchmethodexception - 256);
         }

         if(OBlock.m[nosuchmethodexception] != null && OItem.c[nosuchmethodexception] == null) {
            OItem.c[nosuchmethodexception] = itemblock;
         }
      } catch (IllegalArgumentException var4) {
         logger.throwing("ModLoader", "RegisterBlock", var4);
         ThrowException(var4);
      } catch (IllegalAccessException var5) {
         logger.throwing("ModLoader", "RegisterBlock", var5);
         ThrowException(var5);
      } catch (SecurityException var6) {
         logger.throwing("ModLoader", "RegisterBlock", var6);
         ThrowException(var6);
      } catch (InstantiationException var7) {
         logger.throwing("ModLoader", "RegisterBlock", var7);
         ThrowException(var7);
      } catch (InvocationTargetException var8) {
         logger.throwing("ModLoader", "RegisterBlock", var8);
         ThrowException(var8);
      } catch (NoSuchMethodException var9) {
         logger.throwing("ModLoader", "RegisterBlock", var9);
         ThrowException(var9);
      }

   }

   public static void RegisterEntityID(Class class1, String s, int i) {
      try {
         method_RegisterEntityID.invoke((Object)null, new Object[]{class1, s, Integer.valueOf(i)});
      } catch (IllegalArgumentException var4) {
         logger.throwing("ModLoader", "RegisterEntityID", var4);
         ThrowException(var4);
      } catch (IllegalAccessException var5) {
         logger.throwing("ModLoader", "RegisterEntityID", var5);
         ThrowException(var5);
      } catch (InvocationTargetException var6) {
         logger.throwing("ModLoader", "RegisterEntityID", var6);
         ThrowException(var6);
      }

   }

   public static void RegisterTileEntity(Class class1, String s) {
      try {
         method_RegisterTileEntity.invoke((Object)null, new Object[]{class1, s});
      } catch (IllegalArgumentException var3) {
         logger.throwing("ModLoader", "RegisterTileEntity", var3);
         ThrowException(var3);
      } catch (IllegalAccessException var4) {
         logger.throwing("ModLoader", "RegisterTileEntity", var4);
         ThrowException(var4);
      } catch (InvocationTargetException var5) {
         logger.throwing("ModLoader", "RegisterTileEntity", var5);
         ThrowException(var5);
      }

   }

   public static void RemoveSpawn(Class class1, OEnumCreatureType enumcreaturetype) {
      RemoveSpawn(class1, enumcreaturetype, (OBiomeGenBase[])null);
   }

   public static void RemoveSpawn(Class class1, OEnumCreatureType enumcreaturetype, OBiomeGenBase[] abiomegenbase) {
      if(class1 == null) {
         throw new IllegalArgumentException("entityClass cannot be null");
      } else if(enumcreaturetype == null) {
         throw new IllegalArgumentException("spawnList cannot be null");
      } else {
         if(abiomegenbase == null) {
            abiomegenbase = standardBiomes;
         }

         for(int i = 0; i < abiomegenbase.length; ++i) {
            List list = abiomegenbase[i].a(enumcreaturetype);
            if(list != null) {
               Iterator iterator = list.iterator();

               while(iterator.hasNext()) {
                  OSpawnListEntry spawnlistentry = (OSpawnListEntry)iterator.next();
                  if(spawnlistentry.a == class1) {
                     list.remove(spawnlistentry);
                     break;
                  }
               }
            }
         }

      }
   }

   public static void RemoveSpawn(String s, OEnumCreatureType enumcreaturetype) {
      RemoveSpawn(s, enumcreaturetype, (OBiomeGenBase[])null);
   }

   public static void RemoveSpawn(String s, OEnumCreatureType enumcreaturetype, OBiomeGenBase[] abiomegenbase) {
      Class class1 = (Class)classMap.get(s);
      if(class1 != null && OEntityLiving.class.isAssignableFrom(class1)) {
         RemoveSpawn(class1, enumcreaturetype, abiomegenbase);
      }

   }

   public static void saveConfig() throws IOException {
      cfgdir.mkdir();
      if(cfgfile.exists() || cfgfile.createNewFile()) {
         if(cfgfile.canWrite()) {
            FileOutputStream fileoutputstream = new FileOutputStream(cfgfile);
            props.store(fileoutputstream, "ModLoader Config");
            fileoutputstream.close();
         }

      }
   }

   public static void SetInGameHook(BaseMod basemod, boolean flag, boolean flag1) {
      if(flag) {
         inGameHooks.put(basemod, Boolean.valueOf(flag1));
      } else {
         inGameHooks.remove(basemod);
      }

   }

   public static void setPrivateValue(Class class1, Object obj, int i, Object obj1) throws IllegalArgumentException, SecurityException, NoSuchFieldException {
      try {
         Field illegalaccessexception = class1.getDeclaredFields()[i];
         illegalaccessexception.setAccessible(true);
         int j = field_modifiers.getInt(illegalaccessexception);
         if((j & 16) != 0) {
            field_modifiers.setInt(illegalaccessexception, j & -17);
         }

         illegalaccessexception.set(obj, obj1);
      } catch (IllegalAccessException var6) {
         logger.throwing("ModLoader", "setPrivateValue", var6);
         ThrowException("An impossible error has occured!", var6);
      }

   }

   public static void setPrivateValue(Class class1, Object obj, String s, Object obj1) throws IllegalArgumentException, SecurityException, NoSuchFieldException {
      try {
         Field illegalaccessexception = class1.getDeclaredField(s);
         int i = field_modifiers.getInt(illegalaccessexception);
         if((i & 16) != 0) {
            field_modifiers.setInt(illegalaccessexception, i & -17);
         }

         illegalaccessexception.setAccessible(true);
         illegalaccessexception.set(obj, obj1);
      } catch (IllegalAccessException var6) {
         logger.throwing("ModLoader", "setPrivateValue", var6);
         ThrowException("An impossible error has occured!", var6);
      }

   }

   public static void TakenFromCrafting(OEntityPlayer entityplayer, OItemStack itemstack) {
      Iterator iterator = modList.iterator();

      while(iterator.hasNext()) {
         BaseMod basemod = (BaseMod)iterator.next();
         basemod.TakenFromCrafting(entityplayer, itemstack);
      }

   }

   public static void TakenFromFurnace(OEntityPlayer entityplayer, OItemStack itemstack) {
      Iterator iterator = modList.iterator();

      while(iterator.hasNext()) {
         BaseMod basemod = (BaseMod)iterator.next();
         basemod.TakenFromFurnace(entityplayer, itemstack);
      }

   }

   public static void OnItemPickup(OEntityPlayer entityplayer, OItemStack itemstack) {
      Iterator iterator = modList.iterator();

      while(iterator.hasNext()) {
         BaseMod basemod = (BaseMod)iterator.next();
         basemod.OnItemPickup(entityplayer, itemstack);
      }

   }

   public static void ThrowException(String s, Throwable throwable) {
      throwable.printStackTrace();
      logger.log(Level.SEVERE, "Unexpected exception", throwable);
      MinecraftServer.a.throwing("ModLoader", s, throwable);
      throw new RuntimeException(s, throwable);
   }

   private static void ThrowException(Throwable throwable) {
      ThrowException("Exception occured in ModLoader", throwable);
   }

   private ModLoader() {
      super();
   }

   public static void Init(MinecraftServer minecraftserver) {
      instance = minecraftserver;

      //try {
         //String nosuchmethodexception1 = ModLoader.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
         //nosuchmethodexception1 = nosuchmethodexception1.substring(0, nosuchmethodexception1.lastIndexOf(47));
         cfgdir = new File( "config/");
         cfgfile = new File( "config/ModLoader.cfg");
         logfile = new File( "ModLoader.txt");
         modDir = new File( "mods/");
      /*} catch (URISyntaxException var6) {
         getLogger().throwing("ModLoader", "Init", var6);
         ThrowException("ModLoader", var6);
         return;
      }
      */

      try {
         try {
            method_getNextWindowId = OEntityPlayerMP.class.getDeclaredMethod("OTileEntityDispenser", (Class[])null);
         } catch (NoSuchMethodException var3) {
            //method_getNextWindowId = OEntityPlayerMP.class.getDeclaredMethod("getNextWidowId", (Class[])null);
            method_getNextWindowId = OEntityPlayerMP.class.getDeclaredMethod("au", (Class[])null);
         }

         method_getNextWindowId.setAccessible(true);

         try {
            field_currentWindowId = OEntityPlayerMP.class.getDeclaredField("OThreadCommandReader");
         } catch (NoSuchFieldException var2) {
//            field_currentWindowId = OEntityPlayerMP.class.getDeclaredField("currentWindowId");
            field_currentWindowId = OEntityPlayerMP.class.getDeclaredField("ch");
         }

         field_currentWindowId.setAccessible(true);
      } catch (NoSuchFieldException var4) {
         getLogger().throwing("ModLoader", "Init", var4);
         ThrowException("ModLoader", var4);
         return;
      } catch (NoSuchMethodException var5) {
         getLogger().throwing("ModLoader", "Init", var5);
         ThrowException("ModLoader", var5);
         return;
      }

      init();
   }


   
   public static void OpenGUI(OEntityPlayer entityplayer, int i, OIInventory iinventory, OContainer container) {
      if(!hasInit) {
         init();
      }

      if(entityplayer instanceof OEntityPlayerMP) {
         OEntityPlayerMP entityplayermp = (OEntityPlayerMP)entityplayer;

         try {
            method_getNextWindowId.invoke(entityplayermp, new Object[0]);
            int illegalaccessexception = field_currentWindowId.getInt(entityplayermp);
            entityplayermp.a.b((OPacket)(new OPacket100OpenWindow(illegalaccessexception, i, iinventory.c(), iinventory.a())));
            entityplayermp.l = container;
            entityplayermp.l.f = illegalaccessexception;
            /*
             * entityplayermp.l.a(entityplayermp);
             */
            container_a(entityplayermp);
            
         } catch (InvocationTargetException var6) {
            getLogger().throwing("ModLoaderMultiplayer", "OpenModGUI", var6);
            ThrowException("ModLoaderMultiplayer", var6);
         } catch (IllegalAccessException var7) {
            getLogger().throwing("ModLoaderMultiplayer", "OpenModGUI", var7);
            ThrowException("ModLoaderMultiplayer", var7);
         }
      }

   }
public static void container_a(OEntityPlayer var1) {
    OInventoryPlayer var2 = var1.j;
    if(var2.l() != null) {
       var1.b(var2.l());
       var2.b((OItemStack)null);
    }
}
}
