package org.campusmolndal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ScannerUtilTest {

    private Scanner mockScanner;
    private ScannerUtil scannerUtil;

    @Test
    @BeforeEach
    public void setUp() {
        mockScanner = mock(Scanner.class);
        scannerUtil = ScannerUtil.getTheInput();
        ScannerUtil.setScanner(mockScanner);
    }

    @Test
    public void testNextInt() {
        when(mockScanner.nextInt()).thenThrow(new NoSuchElementException()).thenReturn(5);

        assertEquals(5, scannerUtil.nextInt());
        verify(mockScanner, times(2)).nextInt();
    }

    @Test
    public void testNextDouble() {
        when(mockScanner.nextDouble()).thenThrow(new NoSuchElementException()).thenReturn(5.5);

        assertEquals(5.5, scannerUtil.nextDouble(), 0.0);
        verify(mockScanner, times(2)).nextDouble();
    }

    @Test
    public void testNextLine() {
        when(mockScanner.nextLine()).thenReturn("Test String");

        assertEquals("Test String", scannerUtil.nextLine());
        verify(mockScanner, times(1)).nextLine();
    }
}