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
/*
 * Created on 22/09/2008
 *
 */
package nz.co.senanque.madura.datetime;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * A simple date has no time component. It can be compared to another simple date or a java.util.Date and any time
 * component in the comparison will be ignored. 
 * 
 * @author Roger Parkinson
 * @version $Revision: 1.3 $
 */
public class SimpleDate
{
    private Date m_date;

    public SimpleDate(long date)
    {
        m_date = new Date(stripTime(date));
    }
    public boolean equals(Date date)
    {
        return m_date.getTime() == stripTime(date.getTime());
    }
    private long stripTime(long date)
    {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(date);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime().getTime();
    }
}
