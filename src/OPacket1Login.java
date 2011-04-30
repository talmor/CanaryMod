// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

import java.io.*;

public class OPacket1Login extends OPacket
{

    public OPacket1Login()
    {
    }

    public OPacket1Login(String s, int i, long l, byte byte0)
    {
        b = s;
        a = i;
        c = l;
        d = 0;
    }

    @Override
    public void a(DataInputStream datainputstream)
    {
    	try
    	{
	        a = datainputstream.readInt();
	        b = a(datainputstream, 16);
	        c = datainputstream.readLong();
	        d = datainputstream.readByte();
    	}
    	catch(IOException e)
    	{
    		
    	}
    }

    @Override
    public void a(DataOutputStream dataoutputstream)
    {
    	try
    	{
	        dataoutputstream.writeInt(a);
	        a(b, dataoutputstream);
	        dataoutputstream.writeLong(c);
	        dataoutputstream.writeByte(d);
    	}
    	catch(IOException e)
    	{
    	}
    }

    public void a(ONetHandler onethandler)
    {
        onethandler.a(this);
    }

    public int a()
    {
        return 4 + b.length() + 4 + 5;
    }

    public int a;
    public String b;
    public long c;
    public byte d;
}
