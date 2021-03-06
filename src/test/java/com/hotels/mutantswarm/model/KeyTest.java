/*
 * Copyright (C) 2018-2021 Expedia, Inc.
 * Copyright (C) 2021 The HiveRunner Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hotels.mutantswarm.model;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hotels.mutantswarm.plan.gene.Gene;
import com.hotels.mutantswarm.plan.gene.Locus;

@ExtendWith(MockitoExtension.class)
public class KeyTest {

  @Mock
  private Locus locus;
  @Mock
  private Gene gene;
  
  //A key for each Key constructor
  private Key key1;
  private Key key2 = new Key(4,5);
  
  @BeforeEach
  public void setUpMocks() {
    when(gene.getScriptIndex()).thenReturn(2);
    when(gene.getStatementIndex()).thenReturn(3);
    key1 = new Key(gene);
  }
  
  @Test
  public void checkGetScriptIndex() {
    int result1 = key1.getScriptIndex();
    int result2 = key2.getScriptIndex();
    assertThat(result1, is(2));
    assertThat(result2, is(4));
  }
  
  @Test
  public void checkGetStatementIndex() {
    int result1 = key1.getStatementIndex();
    int result2 = key2.getStatementIndex();
    assertThat(result1, is(3));
    assertThat(result2, is(5));
  }

}
