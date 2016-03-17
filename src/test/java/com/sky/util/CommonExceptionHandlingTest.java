package com.sky.util;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletResponse;

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
        //when
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        doNothing().when(response).sendRedirect(captor.capture());
        //given
        sut.error(response);
        //then
        assertEquals(ERROR_PAGE_PATH, captor.getValue());
        verify(response, times(1)).sendRedirect(ERROR_PAGE_PATH);
        verifyNoMoreInteractions(response);
    }

    @Test
    public void errorPathShouldPredefinedLocation() throws Exception {
        assertEquals(ERROR_PAGE_PATH, sut.getErrorPath());
    }
}