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
 * 
 * 
 * @author Roger Parkinson
 * @version $Revision: 1.2 $
 */
public class Mondayised implements Holiday
{
    public enum Policy { CLOSEST, FOLLOWING, PREVIOUS }
    private int m_day;
    private int m_month;
    private Policy m_policy=Policy.CLOSEST;
    private String m_name;
    private Set<SimpleDate> m_cache;
    
    /* (non-Javadoc)
     * @see nz.co.senanque.madura.datetime.Exclusion#isExcluded(java.util.Date)
     */
    public boolean isHoliday(Date date)
    {
        int offset = 0;
        switch (m_policy)
        {
        case CLOSEST:
            offset = figureMonday(date,1);
            int previous = figureMonday(date,1);
            if (previous < offset)
                offset = -previous;
            break;
        case FOLLOWING:
            offset = figureMonday(date,1);
            break;
        case PREVIOUS:
            offset = figureMonday(date,-1);
            break;
        }
        
        Calendar param = new GregorianCalendar();
        param.setTime(date);
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, param.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, m_month);
        calendar.set(Calendar.DAY_OF_MONTH, m_day);
        calendar.add(Calendar.DAY_OF_YEAR, offset);
        if (param.get(Calendar.DAY_OF_YEAR)==calendar.get(Calendar.DAY_OF_YEAR))
        {
            return true;
        }
        return false;
    }
    private int figureMonday(Date date, int j)
    {
        Calendar calendar = new GregorianCalendar();
        int count=0;
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, m_month);
        calendar.set(Calendar.DAY_OF_MONTH, m_day);
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY)
        {
            calendar.add(Calendar.DAY_OF_YEAR, j);
            count++;
        }
        return count;
    }

    public int getDay()
    {
        return m_day;
    }

    public void setDay(int day)
    {
        m_day = day;
    }

    public void setDay(String day)
    {
        m_day = Integer.valueOf(day);
    }

    public int getMonth()
    {
        return m_month;
    }

    public void setMonth(int month)
    {
        m_month = month;
    }
    public void setMonth(String month)
    {
        int[] ret = Translation.getMonth(month);
        m_month = ret[0];
    }

    public Policy getPolicy()
    {
        return m_policy;
    }

    public void setPolicy(String policy)
    {
        m_policy = Policy.valueOf(policy);
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
    public String toString() {
    	return m_name;
    }
}
