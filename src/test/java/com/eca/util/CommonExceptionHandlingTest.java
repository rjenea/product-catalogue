package com.eca.util;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletResponse;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CommonExceptionHandlingTest {

    private static final String ERROR_PAGE_PATH = "/err.html";

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Mock
    private HttpServletResponse response;

    private final CommonExceptionHandling sut = new CommonExceptionHandling();

    @Test
    public void shouldNotAcceptNullParameter() throws Exception {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Response is required");
        sut.error(null);
    }

    @Test
    public void shouldRedirectToErrorPage() throws Exception {
        //given
        sut.error(response);
        //then
        verify(response, times(1)).sendError(SC_BAD_REQUEST);
        verifyNoMoreInteractions(response);
    }

    @Test
    public void errorPathShouldPredefinedLocation() throws Exception {
        assertEquals(ERROR_PAGE_PATH, sut.getErrorPath());
    }
}