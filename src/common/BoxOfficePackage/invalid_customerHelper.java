package common.BoxOfficePackage;


/**
* common/BoxOfficePackage/invalid_customerHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from /home/ben/workspace/soen423.a2/src/idl/boxoffice.idl
* Tuesday, November 8, 2011 3:54:34 PM EST
*/

abstract public class invalid_customerHelper
{
  private static String  _id = "IDL:common/BoxOffice/invalid_customer:1.0";

  public static void insert (org.omg.CORBA.Any a, common.BoxOfficePackage.invalid_customer that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static common.BoxOfficePackage.invalid_customer extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  private static boolean __active = false;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      synchronized (org.omg.CORBA.TypeCode.class)
      {
        if (__typeCode == null)
        {
          if (__active)
          {
            return org.omg.CORBA.ORB.init().create_recursive_tc ( _id );
          }
          __active = true;
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [0];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          __typeCode = org.omg.CORBA.ORB.init ().create_exception_tc (common.BoxOfficePackage.invalid_customerHelper.id (), "invalid_customer", _members0);
          __active = false;
        }
      }
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static common.BoxOfficePackage.invalid_customer read (org.omg.CORBA.portable.InputStream istream)
  {
    common.BoxOfficePackage.invalid_customer value = new common.BoxOfficePackage.invalid_customer ();
    // read and discard the repository ID
    istream.read_string ();
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, common.BoxOfficePackage.invalid_customer value)
  {
    // write the repository ID
    ostream.write_string (id ());
  }

}
