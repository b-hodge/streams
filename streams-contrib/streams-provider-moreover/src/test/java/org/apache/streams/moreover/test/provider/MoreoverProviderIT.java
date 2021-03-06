/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.streams.moreover.test.provider;

import org.apache.streams.jackson.StreamsJacksonMapper;
import org.apache.streams.moreover.MoreoverProvider;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;

/**
 * Integration test for MoreoverProviderIT.
 * @Ignore this is ignored because the project doesn't have credentials to test it with during CI
 */
public class MoreoverProviderIT {

  private static final Logger LOGGER = LoggerFactory.getLogger(MoreoverProviderIT.class);

  private ObjectMapper mapper = StreamsJacksonMapper.getInstance();

  @Test(enabled = false)
  public void testMoreoverProvide() throws Exception {

    String configfile = "./target/test-classes/MoreoverProviderIT.conf";
    String outfile = "./target/test-classes/MoreoverProviderIT.stdout.txt";

    MoreoverProvider.main(new String[]{configfile, outfile});

    File out = new File(outfile);
    assert (out.exists());
    assert (out.canRead());
    assert (out.isFile());

    FileReader outReader = new FileReader(out);
    LineNumberReader outCounter = new LineNumberReader(outReader);

    while (outCounter.readLine() != null) {}

    assert (outCounter.getLineNumber() >= 1);

  }
}