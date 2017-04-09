package com.jack.fiftytwo.models;

import org.junit.Test;

import com.jack.fiftytwo.enums.Rank;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * jackhickey
 **/
public class RankTest {

    //quick test to make sure I remember how enums work
    @Test
    public void rankTest(){
        String expected = "Ace";
        assertThat(Rank.ACE.toString()).isEqualTo(expected);
    }
}