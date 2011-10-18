import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Packet230ModLoader extends OPacket {

    private static final int MAX_DATA_LENGTH = 65535;
    public int               modId;
    public int               packetType;
    public int[]             dataInt         = new int[0];
    public float[]           dataFloat       = new float[0];
    public String[]          dataString      = new String[0];
    private static Map       playerMap       = new HashMap();

    public Packet230ModLoader() {
        super();
    }

    public void a(DataInputStream datainputstream) {
        try {
            this.modId = datainputstream.readInt();
            this.packetType = datainputstream.readInt();
            int i = datainputstream.readInt();
            if (i > '\uffff') {
                throw new IOException(String.format("Integer data size of %d is higher than the max (%d).", new Object[] { Integer.valueOf(i), Integer.valueOf('\uffff') }));
            } else {
                this.dataInt = new int[i];

                int k;
                for (k = 0; k < i; ++k) {
                    this.dataInt[k] = datainputstream.readInt();
                }

                k = datainputstream.readInt();
                if (k > '\uffff') {
                    throw new IOException(String.format("Float data size of %d is higher than the max (%d).", new Object[] { Integer.valueOf(k), Integer.valueOf('\uffff') }));
                } else {
                    this.dataFloat = new float[k];

                    int i1;
                    for (i1 = 0; i1 < k; ++i1) {
                        this.dataFloat[i1] = datainputstream.readFloat();
                    }

                    i1 = datainputstream.readInt();
                    if (i1 > '\uffff') {
                        throw new IOException(String.format("String data size of %d is higher than the max (%d).", new Object[] { Integer.valueOf(i1), Integer.valueOf('\uffff') }));
                    } else {
                        this.dataString = new String[i1];

                        for (int j1 = 0; j1 < i1; ++j1) {
                            int k1 = datainputstream.readInt();
                            if (k1 > '\uffff') {
                                throw new IOException(String.format("String length of %d is higher than the max (%d).", new Object[] { Integer.valueOf(k1), Integer.valueOf('\uffff') }));
                            }

                            byte[] abyte0 = new byte[k1];
                            datainputstream.read(abyte0, 0, k1);
                            this.dataString[j1] = new String(abyte0);
                        }

                    }
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void a(DataOutputStream dataoutputstream) {
        try {
            if (this.dataInt != null && this.dataInt.length > '\uffff') {
                throw new IOException(String.format("Integer data size of %d is higher than the max (%d).", new Object[] { Integer.valueOf(this.dataInt.length), Integer.valueOf('\uffff') }));
            } else if (this.dataFloat != null && this.dataFloat.length > '\uffff') {
                throw new IOException(String.format("Float data size of %d is higher than the max (%d).", new Object[] { Integer.valueOf(this.dataFloat.length), Integer.valueOf('\uffff') }));
            } else if (this.dataString != null && this.dataString.length > '\uffff') {
                throw new IOException(String.format("String data size of %d is higher than the max (%d).", new Object[] { Integer.valueOf(this.dataString.length), Integer.valueOf('\uffff') }));
            } else {
                dataoutputstream.writeInt(this.modId);
                dataoutputstream.writeInt(this.packetType);
                int k;
                if (this.dataInt == null) {
                    dataoutputstream.writeInt(0);
                } else {
                    dataoutputstream.writeInt(this.dataInt.length);

                    for (k = 0; k < this.dataInt.length; ++k) {
                        dataoutputstream.writeInt(this.dataInt[k]);
                    }
                }

                if (this.dataFloat == null) {
                    dataoutputstream.writeInt(0);
                } else {
                    dataoutputstream.writeInt(this.dataFloat.length);

                    for (k = 0; k < this.dataFloat.length; ++k) {
                        dataoutputstream.writeFloat(this.dataFloat[k]);
                    }
                }

                if (this.dataString == null) {
                    dataoutputstream.writeInt(0);
                } else {
                    dataoutputstream.writeInt(this.dataString.length);

                    for (k = 0; k < this.dataString.length; ++k) {
                        if (this.dataString[k].length() > '\uffff') {
                            throw new IOException(String.format("String length of %d is higher than the max (%d).", new Object[] { Integer.valueOf(this.dataString[k].length()), Integer.valueOf('\uffff') }));
                        }

                        dataoutputstream.writeInt(this.dataString[k].length());
                        dataoutputstream.writeBytes(this.dataString[k]);
                    }
                }

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void a(ONetHandler nethandler) {
        OEntityPlayerMP entityplayermp = null;
        if (playerMap.containsKey(nethandler)) {
            entityplayermp = (OEntityPlayerMP) playerMap.get(nethandler);
        } else if (nethandler instanceof ONetServerHandler) {
            try {
                entityplayermp = (OEntityPlayerMP) ModLoader.getPrivateValue(ONetServerHandler.class, nethandler, 4);
            } catch (NoSuchFieldException var4) {
                System.out.println("Error getting player from NetServerHandler.");
                var4.printStackTrace();
            }
        }

        ModLoaderMp.HandleAllPackets(this, entityplayermp);
    }

    public int a() {
        byte i = 1;
        int var3 = i + 1;
        ++var3;
        var3 += this.dataInt == null ? 0 : this.dataInt.length * 32;
        ++var3;
        var3 += this.dataFloat == null ? 0 : this.dataFloat.length * 32;
        ++var3;
        if (this.dataString != null) {
            for (int j = 0; j < this.dataString.length; ++j) {
                ++var3;
                var3 += this.dataString[j].length();
            }
        }

        return var3;
    }

}
