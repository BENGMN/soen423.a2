package common.BoxOfficePackage;

/**
* common/BoxOfficePackage/invalid_customerHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from /home/ben/workspace/soen423.a2/src/idl/boxoffice.idl
* Tuesday, November 8, 2011 3:54:34 PM EST
*/

public final class invalid_customerHolder implements org.omg.CORBA.portable.Streamable
{
  public common.BoxOfficePackage.invalid_customer value = null;

  public invalid_customerHolder ()
  {
  }

  public invalid_customerHolder (common.BoxOfficePackage.invalid_customer initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = common.BoxOfficePackage.invalid_customerHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    common.BoxOfficePackage.invalid_customerHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return common.BoxOfficePackage.invalid_customerHelper.type ();
  }

}
