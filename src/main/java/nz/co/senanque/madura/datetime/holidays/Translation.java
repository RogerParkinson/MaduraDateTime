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

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import nz.co.senanque.madura.datetime.ValidationException;

/**
 * Translation of days and months
 * 
 * @author Roger Parkinson
 * @version $Revision: 1.3 $
 */
public class Translation
{
    private static String s_daysOfWeek[] = new String[]{"sun","mon","tue","wed","thu","fri","sat"};
    private static String s_months[] = new String[]{"jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec"};
    public static int[] getMonth(String param)
    {
        return translateToArray(param,s_months);
    }
    public static int[] getDayOfWeek(String param)
    {
        return translateToArray(param,s_daysOfWeek);
    }
    private static int[] translateToArray(String arg, String[] source)
    {
        List<Integer> array = new ArrayList<Integer>();
        StringTokenizer st = new StringTokenizer(arg,",");
        while (st.hasMoreTokens())
        {
            String dayOfWeek = st.nextToken().toLowerCase();
            for (int i=0;i<source.length;i++)
            {
                if (dayOfWeek.startsWith(source[i]))
                {
                    array.add(i);
                    break;
                }
            }
        }
        int[] ret = new int[array.size()];
        int j=0;
        for (Integer i:array)
        {
            ret[j++]=i;
        }
        if (j==0)
            throw new ValidationException("Failed to validate: "+arg);
        return ret;
    }

}
