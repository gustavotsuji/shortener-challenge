package com.neueda.challenge.shortenerUrl.util;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.powermock.reflect.Whitebox;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(PowerMockRunner.class)
@PrepareForTest(Base62Converter.class)
public class Base62ConverterTest {

    @Mock
    Base62Converter encoder;

    @Before
    public void setUp() {
        encoder = new Base62Converter();
    }

    @Test
    public void testEncode0() throws Exception {
        String result = Whitebox.invokeMethod(encoder,
                "mapNumberToCharacter", 0l);

        assertEquals("0", result);
    }

    @Test
    public void testEncode10() throws Exception {
        String result = Whitebox.invokeMethod(encoder,
                "mapNumberToCharacter", 10l);

        assertEquals("A", result);
    }

    @Test
    public void testEncode37() throws Exception {
        String result = Whitebox.invokeMethod(encoder,
                "mapNumberToCharacter",36l);

        assertEquals("a", result);
    }
    @Test
    public void testEncode62() throws Exception {
        String result = encoder.encode(62l);

        assertEquals("10", result);
    }

    @Test
    public void testEncode3846() throws Exception {
        String result = encoder.encode(3846l);

        assertEquals("102", result);
    }


    @Test
    public void testDecode0() throws Exception {
        long result = encoder.decode("0");

        assertEquals(0l, result);
    }

    @Test
    public void testDecode62() throws Exception {
        long result = encoder.decode("10");

        assertEquals(62l, result);
    }

    @Test
    public void testDecode10a() throws Exception {
        long result = encoder.decode("10a");

        assertEquals(3880l, result);
    }
}
