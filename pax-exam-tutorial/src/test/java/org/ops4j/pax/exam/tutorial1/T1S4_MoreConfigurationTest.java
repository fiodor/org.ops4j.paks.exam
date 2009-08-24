/*
 * Copyright 2009 Toni Menzel.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ops4j.pax.exam.tutorial1;

import java.io.File;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import static org.ops4j.pax.exam.CoreOptions.*;
import org.ops4j.pax.exam.Inject;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.options.ExecutionCustomizer;
import static org.ops4j.pax.exam.container.def.PaxRunnerOptions.*;
import org.ops4j.pax.exam.junit.Configuration;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;

/**
 * @author Toni Menzel (tonit)
 * @since Mar 3, 2009
 */
@RunWith( JUnit4TestRunner.class )
public class T1S4_MoreConfigurationTest
{

    @Inject
    BundleContext bundleContext = null;

    // you get that because you "installed" a log profile in configuration.
    public Log logger = LogFactory.getLog( T1S4_MoreConfigurationTest.class );

    /*
     * You can configure all kinds of stuff.
     * You will learn about most of it on the project wiki.
     * Here's a typical example:
     * - add a log service to your runtime
     * - add custom bundles via the mvn handler
     * - add additional, non bundlized dependencies. (wrapping on the fly)
     */
    @Configuration
    public static Option[] configure()
    {
        return options(
            // install log service using pax runners profile abstraction (there are more profiles, like DS)
            logProfile(),
            // this is how you set the default log level when using pax logging (logProfile)
            systemProperty( "org.ops4j.pax.logging.DefaultServiceLog.level" ).value( "INFO" ),

            // a maven dependency. This must be a bundle already.
            mavenBundle().groupId( "org.ops4j.pax.url" ).artifactId( "pax-url-mvn" ).version( "0.4.0" ),

            // a maven dependency. OSGi meta data (pacakge exports/imports) are being generated by bnd automatically.
            wrappedBundle(
                mavenBundle().groupId( "org.ops4j.base" ).artifactId( "ops4j-base-util" ).version( "0.5.3" )
            ),

            new ExecutionCustomizer()
            {
                @Override
                public void customizeEnvironment( File workingFolder )
                {
                    System.out.println( "Hello World: " + workingFolder.getAbsolutePath() );
                }
            }
        );
    }

    @Test
    public void helloAgain()
    {
        logger.info( "This is running inside Felix. With all configuration set up like you specified. " );
        for( Bundle b : bundleContext.getBundles() )
        {
            logger.info( "Bundle " + b.getBundleId() + " : " + b.getSymbolicName() );
        }

    }
}
