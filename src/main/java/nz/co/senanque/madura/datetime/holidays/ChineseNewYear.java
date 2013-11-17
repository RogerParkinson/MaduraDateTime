/*******************************************************************************
 * Copyright 2010 Prometheus Consulting
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package nz.co.senanque.madura.datetime.holidays;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;

import nz.co.senanque.madura.datetime.Holiday;
import nz.co.senanque.madura.datetime.SimpleDate;

/**
 * Fairly crude method of determining if the date is Chinese New Year
 * Valid from 1995 to 2020
 * 
 * @author Roger Parkinson
 * @version $Revision: 1.1 $
 */
public class ChineseNewYear implements Holiday
{
    private Calendar[] m_calendar = new Calendar[]{
            getCalendar("1995-01-31"),
            getCalendar("1996-02-19"),
            getCalendar("1997-02-07"),
            getCalendar("1998-01-28"),
            getCalendar("1999-02-16"),
            getCalendar("2000-02-05"),
            getCalendar("2001-01-24"),
            getCalendar("2002-02-12"),
            getCalendar("2003-02-01"),
            getCalendar("2004-01-22"),
            getCalendar("2005-02-09"),
            getCalendar("2006-01-29"),
            getCalendar("2007-02-18"),
            getCalendar("2008-02-07"),
            getCalendar("2009-01-26"),
            getCalendar("2010-02-10"),
            getCalendar("2011-02-03"),
            getCalendar("2012-01-23"),
            getCalendar("2013-02-10"),
            getCalendar("2014-01-31"),
            getCalendar("2015-02-19"),
            getCalendar("2016-02-09"),
            getCalendar("2017-01-28"),
            getCalendar("2018-02-16"),
            getCalendar("2019-02-05"),
            getCalendar("2020-01-25")
     };
    private String m_name;
    private Set<SimpleDate> m_cache;
    
    /* (non-Javadoc)
     * @see nz.co.senanque.madura.datetime.Exclusion#isExcluded(java.util.Date)
     */
    public boolean isHoliday(Date date)
    {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        
        for (Calendar cal: m_calendar)
        {
            if (cal.get(Calendar.MONTH)==calendar.get(Calendar.MONTH) 
                    && cal.get(Calendar.DAY_OF_MONTH)==calendar.get(Calendar.DAY_OF_MONTH)
                    && cal.get(Calendar.YEAR)==calendar.get(Calendar.YEAR))
            {
                m_cache.add(new SimpleDate(date.getTime()));
                return true;
            }
        }
        return false;
    }

    public String getName()
    {
        return m_name;
    }

    public void setName(String name)
    {
        m_name = name;
    }
    public void setCache(Set<SimpleDate> cache)
    {
        m_cache = cache;
    }
    private Calendar getCalendar(String dateString)
    {
        Calendar ret = new GregorianCalendar();
        DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
        //dateFormat.
        try
        {
            ret.setTime(dateFormat.parse(dateString));
        }
        catch (ParseException e)
        {
            throw new RuntimeException(e);
        }
        return ret;
    }
    public String toString() {
    	return m_name;
    }
}
