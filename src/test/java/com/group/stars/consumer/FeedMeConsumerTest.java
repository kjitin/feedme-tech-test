package com.group.stars.consumer;

import com.group.stars.transformer.Transformer;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class FeedMeConsumerTest {


    private FeedMeConsumer feedMeConsumer;

    private BufferedReader bufferedReader;

    private Transformer transformer;

    public final static String INPUT_EVENT = "|1|create|event|1571155550834|ffccd4b8-38cd-4c75-9952-428a79310101|Football|Sky Bet Championship|\\|Norwich\\| vs \\|Aston Villa\\||1571155583774|0|1|";

    public final static String INPUT_MARKET = "|2|create|market|1571155550836|ffccd4b8-38cd-4c75-9952-428a79310101|f2782fd7-c6eb-46f1-b84b-4520335dd09f|Full Time Result|0|1|";

    public final static String INPUT_OUTCOME1 = "3|create|outcome|1571155550836|f2782fd7-c6eb-46f1-b84b-4520335dd09f|238db4ca-7350-4eca-a38e-feaa969ac1ab|\\|Norwich\\||4/6|0|1|";
    public final static String INPUT_OUTCOME2 = "4|create|outcome|1571155550836|f2782fd7-c6eb-46f1-b84b-4520335dd09f|08fc938d-a246-484d-8035-d7656bd2a442|Draw|7/2|0|1|";

    @Before
    public void setUp() {
        bufferedReader = mock(BufferedReader.class);
        transformer = mock(Transformer.class);
        feedMeConsumer = new FeedMeConsumer();
        feedMeConsumer.setBufferedReader(bufferedReader);
        feedMeConsumer.setTransformer(transformer);
    }


    @Test
    public void testFeedMeConsumerWorks() throws IOException {


        when(bufferedReader.readLine())
                .thenReturn(INPUT_EVENT)
                .thenReturn(INPUT_MARKET)
                .thenReturn(INPUT_OUTCOME1)
                .thenReturn(INPUT_OUTCOME2)
                .thenReturn(null);

        feedMeConsumer.read();

        verify(transformer, times(1)).transformMessageToEvent(any());
        verify(transformer, times(1)).transformMessageToMarket(any());
        verify(transformer, times(2)).transformMessgeToOutcome(any());

    }

}