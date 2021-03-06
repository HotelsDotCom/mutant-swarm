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
package com.hotels.mutantswarm.report;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hotels.mutantswarm.model.MutantSwarmScript;

@ExtendWith(MockitoExtension.class)
public class ReportScriptTest {

  @Mock
  private MutantSwarmScript delegate;
  @Mock
  private LineFactory lineFactory;
  
  @Test
  public void checkGetSql() {
    when(delegate.getSql()).thenReturn("this is an SQL query");
    ReportScript reportScript = new ReportScript(delegate, lineFactory);
    String result = reportScript.getSql();
    assertThat(result,is("this is an SQL query"));
  }
  
  @Test
  public void checkGetPath() {
    when(delegate.getPath()).thenReturn(Paths.get("/this/is/a/path/to/a/file.sql"));
    ReportScript reportScript = new ReportScript(delegate, lineFactory);
    Path result = reportScript.getPath();
    assertThat(result,is(Paths.get("/this/is/a/path/to/a/file.sql")));
  }
}
