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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Check various dates to ensure they give the expected results
 * 
 * @author Roger Parkinson
 * @version $Revision: 1.4 $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/HolidayCalendarImplTest-spring.xml"})
public class HolidayCalendarImpl2Test
{
    @Autowired
    private Holiday m_holiday;

    /**
     * Test method for {@link nz.co.senanque.madura.datetime.HolidayCalendarImpl#isHoliday(java.util.Date)}.
     */
    @Test
    public void testIsHoliday() throws Exception
    {
        Holiday holiday = m_holiday;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date anzac = df.parse("2008-04-25T09:00:00.00");
        assertTrue(holiday.isHoliday(anzac));
        anzac = df.parse("2010-04-25T15:00:00");
        assertFalse(holiday.isHoliday(anzac));
        Date easterMonday = java.sql.Date.valueOf("2008-03-24");
        assertTrue(holiday.isHoliday(easterMonday));
        Date goodFriday = java.sql.Date.valueOf("2008-03-21");
        assertTrue(holiday.isHoliday(goodFriday));
        Date christmas = java.sql.Date.valueOf("2008-12-25");
        assertTrue(holiday.isHoliday(christmas));
        Date boxing = java.sql.Date.valueOf("2008-12-26");
        assertTrue(holiday.isHoliday(boxing));
        Date labour = java.sql.Date.valueOf("2008-10-27");
        assertTrue(holiday.isHoliday(labour));
        Date notlabour = java.sql.Date.valueOf("2008-10-25");
        assertFalse(holiday.isHoliday(notlabour));
    }


}
