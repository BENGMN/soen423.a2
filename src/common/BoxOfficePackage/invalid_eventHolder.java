package common.BoxOfficePackage;

/**
* common/BoxOfficePackage/invalid_eventHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from /home/ben/workspace/soen423.a2/src/idl/boxoffice.idl
* Sunday, October 30, 2011 10:14:35 PM EDT
*/

public final class invalid_eventHolder implements org.omg.CORBA.portable.Streamable
{
  public common.BoxOfficePackage.invalid_event value = null;

  public invalid_eventHolder ()
  {
  }

  public invalid_eventHolder (common.BoxOfficePackage.invalid_event initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = common.BoxOfficePackage.invalid_eventHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    common.BoxOfficePackage.invalid_eventHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return common.BoxOfficePackage.invalid_eventHelper.type ();
  }

}
