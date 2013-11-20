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
 * Checks if the date given falls on easter day for that year
 * 
 * @author Roger Parkinson
 * @version $Revision: 1.3 $
 */
public class EasterSunday implements Holiday
{
    protected Set<SimpleDate> m_cache;
    /* (non-Javadoc)
     * @see nz.co.senanque.madura.datetime.Exclusion#isExcluded(java.util.Date)
     */
    public boolean isHoliday(Date date)
    {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(date.getTime());
        int year = calendar.get(Calendar.YEAR);
        Calendar easter = easterSunday(year);
        if (calendar.get(Calendar.DAY_OF_MONTH) == easter.get(Calendar.DAY_OF_MONTH) &&
                calendar.get(Calendar.MONTH) == easter.get(Calendar.MONTH))
        {
            m_cache.add(new SimpleDate(date.getTime()));
            return true;
        }
        return false;
    }
    public void setCache(Set<SimpleDate> cache)
    {
        m_cache = cache;
    }
    /**
     * Calculate Easter Sunday<br/><br/>
     * 
     * Written by Gregory N. Mirsky<br/>
     * <br/>
     * Source: 2nd Edition by Peter Duffett-Smith. It was originally from<br/>
     * Butcher's Ecclesiastical Calendar, published in 1876. This<br/>
     * algorithm has also been published in the 1922 book General<br/>
     * Astronomy by Spencer Jones; in The Journal of the British<br/>
     * Astronomical Association (Vol.88, page 91, December 1977); and in<br/>
     * Astronomical Algorithms (1991) by Jean Meeus.<br/>
     * <br/>
     * This algorithm holds for any year in the Gregorian Calendar, which<br/>
     * (of course) means years including and after 1583.<br/>
     * <br/>
     * a=year%19<br/>
     * b=year/100<br/>
     * c=year%100<br/>
     * d=b/4<br/>
     * e=b%4<br/>
     * f=(b+8)/25<br/>
     * g=(b-f+1)/3<br/>
     * h=(19*a+b-d-g+15)%30<br/>
     * i=c/4<br/>
     * k=c%4<br/>
     * l=(32+2*e+2*i-h-k)%7<br/>
     * m=(a+11*h+22*l)/451<br/>
     * Easter Month =(h+l-7*m+114)/31  [3=March, 4=April]<br/>
     * p=(h+l-7*m+114)%31<br/>
     * Easter Date=p+1     (date in Easter Month)<br/>
     * <br/>
     * Note: Integer truncation is already factored into the<br/>
     * calculations. Using higher percision variables will cause<br/>
     * inaccurate calculations. <br/>
     *      
     */
    protected Calendar easterSunday(int nYear1)
    { 
/*  Calculate Easter Sunday

    Written by Gregory N. Mirsky

    Source: 2nd Edition by Peter Duffett-Smith. It was originally from
    Butcher's Ecclesiastical Calendar, published in 1876. This
    algorithm has also been published in the 1922 book General
    Astronomy by Spencer Jones; in The Journal of the British
    Astronomical Association (Vol.88, page 91, December 1977); and in
    Astronomical Algorithms (1991) by Jean Meeus.

    This algorithm holds for any year in the Gregorian Calendar, which
    (of course) means years including and after 1583.

        a=year%19
        b=year/100
        c=year%100
        d=b/4
        e=b%4
        f=(b+8)/25
        g=(b-f+1)/3
        h=(19*a+b-d-g+15)%30
        i=c/4
        k=c%4
        l=(32+2*e+2*i-h-k)%7
        m=(a+11*h+22*l)/451
        Easter Month =(h+l-7*m+114)/31  [3=March, 4=April]
        p=(h+l-7*m+114)%31
        Easter Date=p+1     (date in Easter Month)

    Note: Integer truncation is already factored into the
    calculations. Using higher percision variables will cause
    inaccurate calculations. 
*/

    int nA          = 0;
    int nB          = 0;
    int nC          = 0;    
    int nD          = 0;
    int nE          = 0;
    int nF          = 0;
    int nG          = 0;
    int nH          = 0;
    int nI          = 0;
    int nK          = 0;
    int nL          = 0;
    int nM          = 0;
    int nP          = 0;
    int nYY         = 0;
    int nEasterMonth    = 0;
    int nEasterDay      = 0;

    // Calculate Easter
    int nYear = nYear1;
    nYY = nYear;
    if (nYear < 1900) 
        { 
        // if year is in java format put it into standard
        // format for the calculation
        nYear += 1900; 
        }
    nA = nYear % 19;
    nB = nYear / 100;
    nC = nYear % 100;
    nD = nB / 4;
    nE = nB % 4;
    nF = (nB + 8) / 25;
    nG = (nB - nF + 1) / 3;
    nH = (19 * nA + nB - nD - nG + 15) % 30;
    nI = nC / 4;
    nK = nC % 4;
    nL = (32 + 2 * nE + 2 * nI - nH - nK) % 7;
    nM=  (nA + 11 * nH + 22 * nL) / 451;

    //  [3=March, 4=April]
    nEasterMonth = (nH + nL - 7 * nM + 114) / 31;
    --nEasterMonth;
    nP = (nH + nL - 7 * nM + 114) % 31;

    // Date in Easter Month.
    nEasterDay = nP + 1;

    // Uncorrect for our earlier correction.
    nYear -= 1900;

    // Populate the date object...
    Calendar calendar = new GregorianCalendar();
    calendar.set(Calendar.YEAR,nYear);
    calendar.set(Calendar.MONTH,nEasterMonth);
    calendar.set(Calendar.DAY_OF_MONTH,nEasterDay);
    return calendar;
    } 
    public String toString() {
    	return "EasterSunday";
    }


}
