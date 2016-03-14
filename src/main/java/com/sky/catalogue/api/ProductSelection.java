package com.sky.catalogue.api;

import com.sky.catalogue.facade.CatalogueService;
import com.sky.catalogue.product.vo.ProductVO;
import com.sky.customer.service.CustomerLocationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/catalogue/products")
final class ProductSelection {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductSelection.class);
    private final CatalogueService catalogueService;

    private final CustomerLocationService customerLocationService;

    @Autowired
    private ProductSelection(CatalogueService catalogueService, CustomerLocationService customerLocationService) {
        this.catalogueService = catalogueService;
        this.customerLocationService = customerLocationService;
    }

    @ApiOperation(value = "Load all available products", notes = "Load all products for specific customer.")
    @RequestMapping(method = RequestMethod.GET)
    List<ProductVO> findProducts(
            @ApiParam(value = "Customer id fetched from cookie", required = false)
            @CookieValue("customerId") String customerId) {
        String locationId = customerLocationService.findLocationId(customerId);
        return catalogueService.getProducts(locationId);
    }

    @ApiOperation(value = "Add a new product", notes = "This is utility method can be deleted after DB population strategy is defined.")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    ProductVO add(@RequestBody @Valid ProductVO productVO) {
        return catalogueService.save(productVO);
    }


    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public ModelAndView handleNotFound(Exception exception) {
        LOGGER.error("Some exception occurred ", exception);
        return new ModelAndView("/error");
    }
}
