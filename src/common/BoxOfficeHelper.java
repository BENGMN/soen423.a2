package common;


/**
* common/BoxOfficeHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from /home/ben/workspace/soen423.a2/src/idl/boxoffice.idl
* Sunday, October 30, 2011 10:14:35 PM EDT
*/

abstract public class BoxOfficeHelper
{
  private static String  _id = "IDL:common/BoxOffice:1.0";

  public static void insert (org.omg.CORBA.Any a, common.BoxOffice that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static common.BoxOffice extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (common.BoxOfficeHelper.id (), "BoxOffice");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static common.BoxOffice read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_BoxOfficeStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, common.BoxOffice value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static common.BoxOffice narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof common.BoxOffice)
      return (common.BoxOffice)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      common._BoxOfficeStub stub = new common._BoxOfficeStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static common.BoxOffice unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof common.BoxOffice)
      return (common.BoxOffice)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      common._BoxOfficeStub stub = new common._BoxOfficeStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
