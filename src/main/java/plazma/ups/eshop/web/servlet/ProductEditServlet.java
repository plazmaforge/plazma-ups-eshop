package plazma.ups.eshop.web.servlet;

import plazma.ups.eshop.entity.Product;
import plazma.ups.eshop.service.ProductService;
import plazma.ups.eshop.service.ServiceLocator;
import plazma.ups.eshop.web.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProductEditServlet extends HttpServlet {

    private PageGenerator pageGenerator = PageGenerator.instance();
    private ProductService productService = ServiceLocator.getService(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

        long id = Long.valueOf(req.getParameter("id"));

        Product product = productService.findById(id);

        Map<String, Object> parametersMap = new HashMap<>();
        parametersMap.put("product", product);

        String page = pageGenerator.getPage("product_edit.html", parametersMap);
        res.getWriter().println(page);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        long id = Long.valueOf(req.getParameter("id"));
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        double price = Double.parseDouble(req.getParameter("price"));

        Product product = new Product(id, name, description, price);
        productService.update(product);

        res.sendRedirect("/products");
    }

}
