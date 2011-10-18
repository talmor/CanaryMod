
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.TreeMap;

public class ForgeConfiguration {

   private boolean[] configBlocks = null;
   public static final int GENERAL_PROPERTY = 0;
   public static final int BLOCK_PROPERTY = 1;
   public static final int ITEM_PROPERTY = 2;
   File file;
   public TreeMap<String, ForgeProperty> blockProperties = new TreeMap();
   public TreeMap<String, ForgeProperty> itemProperties = new TreeMap();
   public TreeMap<String, ForgeProperty> generalProperties = new TreeMap();


   public ForgeConfiguration(File file) {
      super();
      this.file = file;
   }

   public ForgeProperty getOrCreateBlockIdProperty(String key, int defaultId) {
      if(this.configBlocks == null) {
         this.configBlocks = new boolean[OBlock.m.length];

         for(int property = 0; property < this.configBlocks.length; ++property) {
            this.configBlocks[property] = false;
         }
      }

      ForgeProperty var5;
      if(this.blockProperties.containsKey(key)) {
         var5 = this.getOrCreateIntProperty(key, 1, defaultId);
         this.configBlocks[Integer.parseInt(var5.value)] = true;
         return var5;
      } else {
         var5 = new ForgeProperty();
         this.blockProperties.put(key, var5);
         var5.name = key;
         if(OBlock.m[defaultId] == null && !this.configBlocks[defaultId]) {
            var5.value = Integer.toString(defaultId);
            this.configBlocks[defaultId] = true;
            return var5;
         } else {
            for(int j = OBlock.m.length - 1; j >= 0; --j) {
               if(OBlock.m[j] == null && !this.configBlocks[j]) {
                  var5.value = Integer.toString(j);
                  this.configBlocks[j] = true;
                  return var5;
               }
            }

            throw new RuntimeException("No more block ids available for " + key);
         }
      }
   }

   public ForgeProperty getOrCreateIntProperty(String key, int kind, int defaultValue) {
      ForgeProperty prop = this.getOrCreateProperty(key, kind, Integer.toString(defaultValue));

      try {
         Integer.parseInt(prop.value);
         return prop;
      } catch (NumberFormatException var6) {
         prop.value = Integer.toString(defaultValue);
         return prop;
      }
   }

   public ForgeProperty getOrCreateBooleanProperty(String key, int kind, boolean defaultValue) {
      ForgeProperty prop = this.getOrCreateProperty(key, kind, Boolean.toString(defaultValue));
      if(!"true".equals(prop.value.toLowerCase()) && !"false".equals(prop.value.toLowerCase())) {
         prop.value = Boolean.toString(defaultValue);
         return prop;
      } else {
         return prop;
      }
   }

   public ForgeProperty getOrCreateProperty(String key, int kind, String defaultValue) {
      TreeMap source = null;
      switch(kind) {
      case 0:
         source = this.generalProperties;
         break;
      case 1:
         source = this.blockProperties;
         break;
      case 2:
         source = this.itemProperties;
      }

      if(source.containsKey(key)) {
         return (ForgeProperty)source.get(key);
      } else if(defaultValue != null) {
         ForgeProperty property = new ForgeProperty();
         source.put(key, property);
         property.name = key;
         property.value = defaultValue;
         return property;
      } else {
         return null;
      }
   }

   public void load() {
      try {
         if(this.file.getParentFile() != null) {
            this.file.getParentFile().mkdirs();
         }

         if(!this.file.exists() && !this.file.createNewFile()) {
            return;
         }

         if(this.file.canRead()) {
            FileInputStream e = new FileInputStream(this.file);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(e, "8859_1"));
            TreeMap currentMap = null;

            while(true) {
               String line = buffer.readLine();
               if(line == null) {
                  break;
               }

               int nameStart = -1;
               int nameEnd = -1;
               boolean skip = false;

               for(int i = 0; i < line.length() && !skip; ++i) {
                  if(!Character.isLetterOrDigit(line.charAt(i)) && line.charAt(i) != 46) {
                     if(!Character.isWhitespace(line.charAt(i))) {
                        switch(line.charAt(i)) {
                        case 35:
                           skip = true;
                           break;
                        case 61:
                           String propertyName = line.substring(nameStart, nameEnd + 1);
                           if(currentMap == null) {
                              throw new RuntimeException("property " + propertyName + " has no scope");
                           }

                           ForgeProperty prop = new ForgeProperty();
                           prop.name = propertyName;
                           prop.value = line.substring(i + 1);
                           i = line.length();
                           currentMap.put(propertyName, prop);
                           break;
                        case 123:
                           String scopeName = line.substring(nameStart, nameEnd + 1);
                           if(scopeName.equals("general")) {
                              currentMap = this.generalProperties;
                           } else if(scopeName.equals("block")) {
                              currentMap = this.blockProperties;
                           } else {
                              if(!scopeName.equals("item")) {
                                 throw new RuntimeException("unknown section " + scopeName);
                              }

                              currentMap = this.itemProperties;
                           }
                           break;
                        case 125:
                           currentMap = null;
                           break;
                        default:
                           throw new RuntimeException("unknown character " + line.charAt(i));
                        }
                     }
                  } else {
                     if(nameStart == -1) {
                        nameStart = i;
                     }

                     nameEnd = i;
                  }
               }
            }
         }
      } catch (IOException var12) {
         var12.printStackTrace();
      }

   }

   public void save() {
      try {
         if(this.file.getParentFile() != null) {
            this.file.getParentFile().mkdirs();
         }

         if(!this.file.exists() && !this.file.createNewFile()) {
            return;
         }

         if(this.file.canWrite()) {
            FileOutputStream e = new FileOutputStream(this.file);
            BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(e, "8859_1"));
            buffer.write("# Configuration file\r\n");
            buffer.write("# Generated on " + DateFormat.getInstance().format(new Date()) + "\r\n");
            buffer.write("\r\n");
            buffer.write("###########\r\n");
            buffer.write("# General #\r\n");
            buffer.write("###########\r\n\r\n");
            buffer.write("general {\r\n");
            this.writeProperties(buffer, this.generalProperties.values());
            buffer.write("}\r\n\r\n");
            buffer.write("#########\r\n");
            buffer.write("# Block #\r\n");
            buffer.write("#########\r\n\r\n");
            buffer.write("block {\r\n");
            this.writeProperties(buffer, this.blockProperties.values());
            buffer.write("}\r\n\r\n");
            buffer.write("########\r\n");
            buffer.write("# Item #\r\n");
            buffer.write("########\r\n\r\n");
            buffer.write("item {\r\n");
            this.writeProperties(buffer, this.itemProperties.values());
            buffer.write("}\r\n\r\n");
            buffer.close();
            e.close();
         }
      } catch (IOException var3) {
         var3.printStackTrace();
      }

   }

   private void writeProperties(BufferedWriter buffer, Collection<ForgeProperty> props) throws IOException {
      Iterator i$ = props.iterator();

      while(i$.hasNext()) {
         ForgeProperty property = (ForgeProperty)i$.next();
         if(property.comment != null) {
            buffer.write("   # " + property.comment + "\r\n");
         }

         buffer.write("   " + property.name + "=" + property.value);
         buffer.write("\r\n");
      }

   }
}
