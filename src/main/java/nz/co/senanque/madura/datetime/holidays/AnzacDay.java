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

import nz.co.senanque.madura.datetime.BusinessHours;
import nz.co.senanque.madura.datetime.BusinessHoursImpl;
import nz.co.senanque.madura.datetime.Holiday;

/**
 * Anzac day is observed on 25th April in New Zealand and Australia. The New Zealand holiday is only observed until
 * 1pm, not the whole day, as shown here. Australia observes it the whole day. The date itself was when, in 1916, the
 * combined forces of Australia and New Zealand and, often forgotten, Tonga landed at Gallipoli in what is now Turkey.
 * This was part of WWI. 
 * 
 * @author Roger Parkinson
 * @version $Revision: 1.3 $
 */
public class AnzacDay implements Holiday
{
    private int m_day=25;
    private int m_month=03;
    private String m_name;
    private BusinessHours m_businessHours = new BusinessHoursImpl(new Integer[]{13,14,15,16,17});

    
    /* (non-Javadoc)
     * @see nz.co.senanque.madura.datetime.Exclusion#isExcluded(java.util.Date)
     */
    public boolean isHoliday(Date date)
    {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        
        if (m_month==calendar.get(Calendar.MONTH) 
                && m_day==calendar.get(Calendar.DAY_OF_MONTH))
        {
            if (m_businessHours.isBusinessHours(date))
            {
                return false;
            }
            return true;
        }
        return false;
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

    public String getName()
    {
        return m_name;
    }

    public void setName(String name)
    {
        m_name = name;
    }
    public String toString() {
    	return m_name;
    }
}
