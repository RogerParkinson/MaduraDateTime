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
 * Checks if the date given falls a Mondayised date that year.
 * Note that it only looks backwards for the first Monday, it never looks for the later Monday
 * 
 * @author Roger Parkinson
 * @version $Revision: 1.2 $
 */
public class Absolute implements Holiday
{
    private Date m_date;
    private Calendar m_calendar;
    private Set<SimpleDate> m_cache;
    
    /* (non-Javadoc)
     * @see nz.co.senanque.madura.datetime.Exclusion#isExcluded(java.util.Date)
     */
    public boolean isHoliday(Date date)
    {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        
        if (m_calendar.get(Calendar.MONTH)==calendar.get(Calendar.MONTH) 
                && m_calendar.get(Calendar.DAY_OF_MONTH)==calendar.get(Calendar.DAY_OF_MONTH)
                && m_calendar.get(Calendar.YEAR)==calendar.get(Calendar.YEAR))
        {
            m_cache.add(new SimpleDate(date.getTime()));
            return true;
        }
        return false;
    }

    public Date getDate()
    {
        return m_date;
    }

    public void setDate(Date date)
    {
        m_date = date;
        m_calendar = new GregorianCalendar();
        m_calendar.setTime(m_date);
    }
    public void setDate(String date)
    {
        m_date = java.sql.Date.valueOf(date);
        m_calendar = new GregorianCalendar();
        m_calendar.setTime(m_date);
    }
    public void setCache(Set<SimpleDate> cache)
    {
        m_cache = cache;
    }
    public String toString() {
    	return "Absolute "+m_date;
    }

}
