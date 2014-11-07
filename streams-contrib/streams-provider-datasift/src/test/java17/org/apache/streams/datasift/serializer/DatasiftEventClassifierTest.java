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

package org.apache.streams.datasift.serializer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.apache.streams.data.ActivitySerializer;
import org.apache.streams.datasift.Datasift;
import org.apache.streams.datasift.instagram.Instagram;
import org.apache.streams.datasift.twitter.Twitter;
import org.apache.streams.datasift.util.StreamsDatasiftMapper;
import org.apache.streams.jackson.StreamsJacksonMapper;
import org.apache.streams.twitter.pojo.Delete;
import org.apache.streams.twitter.pojo.Retweet;
import org.apache.streams.twitter.pojo.Tweet;
import org.apache.streams.twitter.pojo.User;
import org.apache.streams.twitter.provider.TwitterEventClassifier;
import org.apache.streams.twitter.serializer.TwitterJsonDeleteActivitySerializer;
import org.apache.streams.twitter.serializer.TwitterJsonRetweetActivitySerializer;
import org.apache.streams.twitter.serializer.TwitterJsonTweetActivitySerializer;
import org.apache.streams.twitter.serializer.TwitterJsonUserActivitySerializer;
import org.junit.Assert;
import org.junit.Test;

import java.util.Scanner;

/**
 * Created by sblackmon on 12/13/13.
 */
public class DatasiftEventClassifierTest {

    private static final ObjectMapper MAPPER = StreamsJacksonMapper.getInstance(Lists.newArrayList(StreamsDatasiftMapper.DATASIFT_FORMAT));

    @Test
    public void testTwitterDetection() throws Exception {
        Scanner scanner = new Scanner(DatasiftActivitySerializerTest.class.getResourceAsStream("/twitter_datasift_json.txt"));
        String line = null;
        while(scanner.hasNextLine()) {
            line = scanner.nextLine();
            Datasift datasift = MAPPER.readValue(line, Datasift.class);
            assert(DatasiftEventClassifier.detectClass(datasift) == Twitter.class);
            assert(DatasiftEventClassifier.bestSerializer(datasift) instanceof DatasiftTweetActivitySerializer);
        }
    }

    @Test
    public void testInstagramDetection() throws Exception {
        Scanner scanner = new Scanner(DatasiftActivitySerializerTest.class.getResourceAsStream("/instagram_datasift_json.txt"));
        String line = null;
        while(scanner.hasNextLine()) {
            line = scanner.nextLine();
            Datasift datasift = MAPPER.readValue(line, Datasift.class);
            assert(DatasiftEventClassifier.detectClass(datasift) == Instagram.class);
            assert(DatasiftEventClassifier.bestSerializer(datasift) instanceof DatasiftInstagramActivitySerializer);
        }
    }
    

}