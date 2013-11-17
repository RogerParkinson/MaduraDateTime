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

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import nz.co.senanque.madura.datetime.SimpleDate;

/**
 * Checks if the date given falls on easter day for that year
 * 
 * @author Roger Parkinson
 * @version $Revision: 1.2 $
 */
public class GoodFriday extends EasterSunday
{
    /* (non-Javadoc)
     * @see nz.co.senanque.madura.datetime.Exclusion#isExcluded(java.util.Date)
     */
    public boolean isHoliday(Date date)
    {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(date.getTime());
        int year = calendar.get(Calendar.YEAR);
        Calendar easter = easterSunday(year);
        easter.add(Calendar.DAY_OF_YEAR, -2);
        if (calendar.get(Calendar.DAY_OF_MONTH) == easter.get(Calendar.DAY_OF_MONTH) &&
                calendar.get(Calendar.MONTH) == easter.get(Calendar.MONTH))
        {
            m_cache.add(new SimpleDate(date.getTime()));
            return true;
        }
        return false;
    }
    public String toString() {
    	return "GoodFriday";
    }
}
