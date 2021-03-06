/**
 * Created By: cfloersch
 * Date: 6/6/2014
 * Copyright 2013 XpertSoftware
 */
package org.xpertss.json.types;

import xpertss.json.JSONString;
import xpertss.json.MarshallingException;
import xpertss.json.spi.JSONUserType;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import static xpertss.json.JSON.string;

// NOTE: This date format is only supported in Java 7+
public class DateType implements JSONUserType<Date, JSONString> {

   private DateFormat format = createFormat();


   public JSONString marshall(Date entity)
   {
      return string(format.format(entity));
   }

   public Date unmarshall(JSONString object)
   {
      try {
         return new Date(format.parse(object.getString()).getTime());
      } catch(ParseException e) {
         throw new MarshallingException("invalid date format", e);
      }
   }

   public Class<Date> getReturnedClass()
   {
      return Date.class;
   }


   private static DateFormat createFormat()
   {
      DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
      format.setTimeZone(TimeZone.getTimeZone("UTC"));
      return format;
   }

}
