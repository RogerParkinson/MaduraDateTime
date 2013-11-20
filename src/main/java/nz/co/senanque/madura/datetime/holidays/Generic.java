/*******************************************************************************
 * Copyright (c)2013 Prometheus Consulting
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package nz.co.senanque.madura.datetime.holidays;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;

import nz.co.senanque.madura.datetime.Holiday;
import nz.co.senanque.madura.datetime.SimpleDate;

/**
 * Checks if the date given falls on this date
 * 
 * @author Roger Parkinson
 * @version $Revision: 1.2 $
 */
public class Generic implements Holiday
{
    private int[] m_month;
    private int[] m_day;
    private int[] m_year;
    private int[] m_dayOfWeek;
    private Set<SimpleDate> m_cache;
    
    /* (non-Javadoc)
     * @see nz.co.senanque.madura.datetime.Exclusion#isExcluded(java.util.Date)
     */
    public boolean isHoliday(Date date)
    {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(date.getTime());
        if (!test(m_month,calendar.get(Calendar.MONTH))) return false;
        if (!test(m_day,calendar.get(Calendar.DAY_OF_MONTH))) return false;
        if (!test(m_year,calendar.get(Calendar.YEAR))) return false;
        if (!test(m_dayOfWeek,calendar.get(Calendar.DAY_OF_WEEK))) return false;
        m_cache.add(new SimpleDate(date.getTime()));
        return true;
    }
    private boolean test(int[] list, int value)
    {
        for (int i: m_month)
        {
            if (i == value) return true;
        }
        return false;
    }
    public int[] getDay()
    {
        return m_day;
    }
    public void setDay(int[] day)
    {
        m_day = day;
    }
    public int[] getDayOfWeek()
    {
        return m_dayOfWeek;
    }
    public void setDayOfWeek(String daysOfWeek)
    {
        m_dayOfWeek = Translation.getDayOfWeek(daysOfWeek);
    }
    public int[] getMonth()
    {
        return m_month;
    }
    public void setMonth(int[] month)
    {
        m_month = month;
    }
    public void setMonth(String months)
    {
        m_month = Translation.getMonth(months);
    }
    public int[] getYear()
    {
        return m_year;
    }
    public void setYear(int[] year)
    {
        m_year = year;
    }
    public void setCache(Set<SimpleDate> cache)
    {
        m_cache = cache;
    }


}
