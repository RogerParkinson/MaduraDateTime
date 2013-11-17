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

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Contains a list of holidays that can be compared against the current date
 * The holidays are not simple dates but algorithms that can return a date based on the
 * date being compared. Once a holiday is established it is cached to speed future calculations.
 * 
 * @author Roger Parkinson
 * @version $Revision: 1.2 $
 */
public class HolidayCalendarImpl implements Holiday
{
    List<Holiday> m_list;
    Set<SimpleDate> m_cache = Collections.synchronizedSet(new HashSet<SimpleDate>());

    public boolean isHoliday(Date date)
    {
        SimpleDate simpleDate = new SimpleDate(date.getTime());
        if (m_cache.contains(simpleDate))
        {
            return true;
        }
        for (Holiday holiday: m_list)
        {
            if (holiday.isHoliday(date))
            {
                return true;
            }
        }
        return false;
    }

    public List<Holiday> getList()
    {
        return m_list;
    }

    public void setList(List<Holiday> list)
    {
        m_list = list;
    }
    
    public void init()
    {
        for (Holiday holiday: m_list)
        {
            try
            {
                Method method = holiday.getClass().getMethod("setCache", new Class[]{Set.class});
                method.invoke(holiday, m_cache);
            }
            catch (Exception e)
            {
            }
        }
        
    }
    

}
