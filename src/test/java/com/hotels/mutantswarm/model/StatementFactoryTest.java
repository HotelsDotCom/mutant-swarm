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

import static java.util.Collections.singletonList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.TokenRewriteStream;
import org.apache.hadoop.hive.ql.parse.ASTNode;
import org.apache.hadoop.hive.ql.parse.MutantSwarmParseDriver;
import org.apache.hadoop.hive.ql.parse.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hotels.mutantswarm.model.MutantSwarmStatement.Factory;

@ExtendWith(MockitoExtension.class)
public class StatementFactoryTest {

  @Mock
  private MutantSwarmParseDriver parseDriver;
  @Mock
  private ASTNode tree;
  @Mock
  private CommonToken token;
  @Mock
  private TokenRewriteStream tokenStream;

  private Factory factory;

  @BeforeEach
  public void setupMocks() throws ParseException {
    factory = new MutantSwarmStatement.Factory(parseDriver);
    when(parseDriver.lex("SELECT * FROM x WHERE a = 1")).thenReturn(tokenStream);
    when(parseDriver.parse(tokenStream)).thenReturn(tree);
    when(parseDriver.extractTokens(tokenStream)).thenReturn(singletonList(token));
  }

  @Test
  public void statement() {
    MutantSwarmStatement statement = factory.newInstance(0, 1, "SELECT * FROM x WHERE a = 1");
    assertThat(statement.getIndex(), is(1));
    assertThat(statement.getTokens(), is(singletonList(token)));
    assertThat(statement.getTree(), is(tree));
    assertThat(statement.getSql(), is("SELECT * FROM x WHERE a = 1"));
  }

  @Test
  public void equalsSame() {
    MutantSwarmStatement statement = factory.newInstance(0, 1, "SELECT * FROM x WHERE a = 1");
    MutantSwarmStatement statement2 = factory.newInstance(0, 1, "SELECT * FROM x WHERE a = 1");
    assertThat(statement.equals(statement2), is(true));
  }

}
