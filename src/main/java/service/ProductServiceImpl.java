package service;

import api.ProductDao;
import api.ProductService;
import dao.ProductDaoImpl;
import entity.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private static ProductServiceImpl instance = null;
    private ProductDao productDao = new ProductDaoImpl("products.txt", "PRODUCT");

    private ProductServiceImpl() {

    }

    public static ProductServiceImpl getInstance(){
        if(instance == null){
            instance = new ProductServiceImpl();
        }
        return instance;
    }

    public List<Product> getAllProducts() throws IOException {
        return productDao.getAllProducts();
    }

    public Integer getCountProducts() throws IOException {
        return productDao.getAllProducts().size();
    }

    public Product getProductByProductName(String productName) throws IOException {
        for(Product product : productDao.getAllProducts()) {
            if (product.getProductName().equals(productName)) {
                return product;
            }
        }

        return null;
    }

    public boolean isProductOnWarehouse(String productName) {
        try {
            for(Product product : productDao.getAllProducts()) {
                if (isProductExist(productName) && product.getProductCount() > 0) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isProductExist(String productName) {
        Product product = null;

        try {
            product = productDao.getProductByProductName(productName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isProductExist(int productId) {
        Product product = null;

        try {
            product = productDao.getProductById(productId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}