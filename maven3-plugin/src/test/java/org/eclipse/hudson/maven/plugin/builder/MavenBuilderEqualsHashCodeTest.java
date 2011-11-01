/*******************************************************************************
 *
 * Copyright (c) 2011, Oracle Corporation
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *
 *    Anton Kozak, Nikita Levyankov
 *
 *
 *******************************************************************************/
package org.eclipse.hudson.maven.plugin.builder;

import java.util.Arrays;
import java.util.Collection;
import org.eclipse.hudson.maven.model.config.BuildConfigurationDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

/**
 * Verifies the equals and the hashcode of {@link MavenBuilder}.
 * <p/>
 * Date: 10/5/2011
 *
 * @author Anton Kozak
 */
@RunWith(Parameterized.class)
public class MavenBuilderEqualsHashCodeTest {
    private MavenBuilder builder1;
    private MavenBuilder builder2;
    private boolean expectedResult;

    public MavenBuilderEqualsHashCodeTest(MavenBuilder builder1, MavenBuilder builder2, boolean expectedResult) {
        this.builder1 = builder1;
        this.builder2 = builder2;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection generateData() {
        BuildConfigurationDTO configuration1 = new BuildConfigurationDTO();
        configuration1.setGoals("clean install");
        BuildConfigurationDTO configuration2 = new BuildConfigurationDTO();
        configuration2.setGoals("clean install");
        configuration2.setOffline(true);

        return Arrays.asList(new Object[][]{
            {new MavenBuilder(configuration1), null, false},
            {new MavenBuilder(configuration1), new MavenBuilder(new BuildConfigurationDTO()), false},
            {new MavenBuilder(configuration1), new MavenBuilder(configuration2), false},
            {new MavenBuilder(configuration1), new MavenBuilder(configuration1), true},
            {new MavenBuilder(configuration2), new MavenBuilder(configuration2), true}
        });

    }

    @Test
    public void testEquals() {
        assertEquals(expectedResult, builder1.equals(builder2));
    }

    @Test
    public void testHashCode() {
        assertEquals(expectedResult, builder1.hashCode() == (builder2 == null? 0: builder2).hashCode());
    }
}
