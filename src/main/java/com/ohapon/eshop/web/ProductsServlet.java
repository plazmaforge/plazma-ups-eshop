package com.ohapon.eshop.web;

import com.ohapon.eshop.entity.Product;
import com.ohapon.eshop.service.ProductService;
import com.ohapon.eshop.service.ServiceFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductsServlet extends HttpServlet {

    private PageGenerator pageGenerator = PageGenerator.instance();
    private ServiceFactory serviceFactory;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

        ProductService service = serviceFactory.getProductService(); //new ProductService();
        List<Product> products = service.findAll();

        Map<String, Object> parametersMap = new HashMap<>();
        parametersMap.put("products", products);

        String page = pageGenerator.getPage("products.html", parametersMap);
        res.getWriter().println(page);

    }

    public void setServiceFactory(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }
}