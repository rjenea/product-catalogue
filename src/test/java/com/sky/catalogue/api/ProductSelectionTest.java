package com.sky.catalogue.api;

import com.sky.catalogue.facade.CatalogueService;
import com.sky.catalogue.product.vo.ProductVO;
import com.sky.customer.service.CustomerLocationService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

import static com.sky.test.util.ProductMother.produceProductVOLiverpoolTv;
import static com.sky.test.util.ProductMother.produceProductVOSkySportNews;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static junit.framework.Assert.assertEquals;
import static org.assertj.core.util.Lists.emptyList;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductSelectionTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();
    @Mock
    private CatalogueService catalogueService;
    @Mock
    private CustomerLocationService customerLocationService;
    @InjectMocks
    private ProductSelection testee;

    @Test
    public void shouldNotAccept_NullCustomerId() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Customer id is required");
        testee.findProducts(null);
    }

    @Test
    public void shouldReturnEmptyList_WhenNothingFound() {
        when(customerLocationService.findLocationId("Alice")).thenReturn("London");
        when(catalogueService.getProducts("London")).thenReturn(emptyList());
        assertThat(testee.findProducts("Alice"), empty());
        verify(customerLocationService, times(1)).findLocationId("Alice");
        verify(catalogueService, times(1)).getProducts("London");
        verifyNoMoreInteractions(catalogueService, customerLocationService);
    }

    @Test
    public void shouldReturnValidProduct() {
        when(customerLocationService.findLocationId("Alice")).thenReturn("London");
        when(catalogueService.getProducts("London")).thenReturn(singletonList(produceProductVOSkySportNews()));
        assertThat(testee.findProducts("Alice"), hasItem(produceProductVOSkySportNews()));
        verify(customerLocationService, times(1)).findLocationId("Alice");
        verify(catalogueService, times(1)).getProducts("London");
        verifyNoMoreInteractions(catalogueService, customerLocationService);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldNotCallRepository_WhenCustomerLocationServiceIsNotAvailable() {
        thrown.expect(RuntimeException.class);
        when(customerLocationService.findLocationId("Alice")).thenThrow(RuntimeException.class);
        when(catalogueService.getProducts(anyString())).thenReturn(singletonList(produceProductVOSkySportNews()));
        try {
            assertThat(testee.findProducts("Alice"), hasItem(produceProductVOSkySportNews()));
        } finally {
            verify(customerLocationService, times(1)).findLocationId("Alice");
            verify(catalogueService, times(0)).getProducts("London");
            verifyNoMoreInteractions(catalogueService, customerLocationService);
        }
    }

    @Test
    public void shouldNotAllowCreation_WhenProductIsNull() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("Product could not be null");
        testee.add(null);
    }

    @Test
    public void shouldCreateValidObject() {
        ProductVO toBeSaved = produceProductVOLiverpoolTv();
        when(catalogueService.save(toBeSaved)).thenReturn(toBeSaved);
        assertThat(testee.add(toBeSaved), equalTo(toBeSaved));
        verify(catalogueService, times(1)).save(toBeSaved);
        verifyNoMoreInteractions(customerLocationService);
    }

    @Test
    public void shouldRedirectToErrorView_ForAnyInput(){
        assertEquals("/error",testee.handleNotFound(null).getViewName());
    }
}