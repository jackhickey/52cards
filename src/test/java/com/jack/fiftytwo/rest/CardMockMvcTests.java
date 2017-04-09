/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jack.fiftytwo.rest;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.jack.fiftytwo.models.Constants.MAX_DECK_SIZE;
import static org.springframework.http.ResponseEntity.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CardMockMvcTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getDeckTest() throws Exception {
        mockMvc.perform(get("/deck"))
            .andExpect((jsonPath("$.deck", Matchers.hasSize(MAX_DECK_SIZE))));
    }

    @Test
    public void getFreshDeckTest() throws Exception{
        mockMvc.perform(get("/deck?refresh=true"))
                .andExpect((jsonPath("$.deck",Matchers.hasSize(MAX_DECK_SIZE))));
    }

    @Test
    public void getSingleCard() throws Exception {
        //init deck
        mockMvc.perform(get("/deck"));

        mockMvc.perform(get("/deck/0"))
                .andExpect(jsonPath("$.rank",Matchers.equalTo("ACE")));
    }

    @Test
    public void removeSingleCard() throws Exception {
        //init deck
        mockMvc.perform(get("/deck"));
        mockMvc.perform(delete("/deck/0"))
                .andExpect(MockMvcResultMatchers.status().isAccepted());
    }

}