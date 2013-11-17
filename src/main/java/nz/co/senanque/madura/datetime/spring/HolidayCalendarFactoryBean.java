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
package nz.co.senanque.madura.datetime.spring;

import java.util.ArrayList;
import java.util.List;

import nz.co.senanque.madura.datetime.BeanUtils;
import nz.co.senanque.madura.datetime.Holiday;
import nz.co.senanque.madura.datetime.HolidayCalendarImpl;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.core.io.Resource;


/**
 * Delivers a HolidayCalendarImpl from a jdom document
 * 
 * @author Roger Parkinson
 * @version $Revision: 1.3 $
 */
public class HolidayCalendarFactoryBean implements FactoryBean<Holiday>
{
	Resource m_document;

	public Holiday getObject() throws Exception
    {
		SAXBuilder saxBuilder = new SAXBuilder();
		Document document = saxBuilder.build(m_document.getInputStream());
        List<Holiday> holidayList = new ArrayList<Holiday>();
        @SuppressWarnings("unchecked")
		List<Element> children = document.getRootElement().getChildren();
        for (Element element: children)
        {
            holidayList.add(getHoliday(element));
        }
        HolidayCalendarImpl ret = new HolidayCalendarImpl();
        ret.setList(holidayList);
        ret.init();
        return ret;
    }
    
    private Holiday getHoliday(Element element)
    {
        String className = null;
        if (className == null)
            className = element.getAttributeValue("class");
        if (className == null)
        {
            className = "nz.co.senanque.madura.datetime.holidays."+element.getName();
        }
        Holiday ret;
        try
        {
            Class<?> holidayClass = Class.forName(className);
            ret = (Holiday)holidayClass.newInstance();
        }
        catch (Exception e)
        {
            throw new HolidayCalendarFactoryException(e);
        }
        @SuppressWarnings("unchecked")
		List<Attribute> attributes = element.getAttributes();
        for (Attribute attribute: attributes)
        {
            String name = attribute.getName();
            if ("class".equals(name)) continue;
            try
            {
                BeanUtils.setProperty(ret,name,attribute.getValue());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return ret;
    }

    public Class<Holiday> getObjectType()
    {
        return Holiday.class;
    }

    public boolean isSingleton()
    {
        return true;
    }

    public Resource getDocument() {
		return m_document;
	}

	public void setDocument(Resource document) {
		m_document = document;
	}

}
