/*
 * Copyright 2009 Toni Menzel
 *
 * Licensed  under the  Apache License,  Version 2.0  (the "License");
 * you may not use  this file  except in  compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed  under the  License is distributed on an "AS IS" BASIS,
 * WITHOUT  WARRANTIES OR CONDITIONS  OF ANY KIND, either  express  or
 * implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ops4j.pax.exam.it;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import org.ops4j.pax.exam.junit.Configuration;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;
import static org.ops4j.pax.exam.junit.JUnitOptions.*;
import org.ops4j.pax.exam.Option;
import static org.ops4j.pax.exam.CoreOptions.*;

/**
 * This tests the mockitoBundles option (since PAXEXAM-25)
 * 
 * @author Toni Menzel (tonit)
 * @since Mar 14, 2009
 */
@RunWith( JUnit4TestRunner.class )
public class MockitoOptionsTest
{

    @Configuration
    public static Option[] rootConfig()
    {
        return options(
            mockitoBundles()
        );
    }

    @Test
    public void usage()
    {
        List<String> mockedList = mock( List.class );
        mockedList.add( "ONE" );
        mockedList.clear();

        verify( mockedList ).add( "ONE" );
        verify( mockedList ).clear();

    }
}