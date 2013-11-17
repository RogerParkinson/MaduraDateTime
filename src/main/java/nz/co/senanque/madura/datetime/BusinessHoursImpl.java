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
package nz.co.senanque.madura.datetime;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Stores the business hours. We have a list of hours and if the hour of the date/time given is one of those
 * hours then we return true. We can only do it to the hour, not the minute.
 * 
 * @author Roger Parkinson
 * @version $Revision: 1.2 $
 */
public class BusinessHoursImpl implements BusinessHours
{
    List<Integer> m_hours;
    
    public BusinessHoursImpl(Integer[] hours)
    {
        m_hours = Arrays.asList(hours);
    }
    
    /* (non-Javadoc)
     * @see nz.co.senanque.madura.datetime.BusinessHours#isBusinessHours(java.util.Date)
     */
    public boolean isBusinessHours(Date d)
    {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(d);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        for (int h:m_hours)
        {
            if (h == hour) return true;
        }
        return false;
    }

    public List<Integer> getHours()
    {
        return m_hours;
    }

    public void setHours(List<Integer> hours)
    {
        m_hours = hours;
    }
}
