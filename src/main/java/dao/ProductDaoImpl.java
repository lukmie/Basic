package dao;

import api.ProductDao;
import entity.Product;
import entity.parser.ProductParser;
import utils.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private static final String fileName = "products.data";
    private static ProductDao instance = null;


    private ProductDaoImpl() {
        try {
            FileUtils.createNewFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ProductDao getInstance(){
        if(instance == null){
            instance = new ProductDaoImpl();
        }
        return instance;
    }

    public void saveProduct(Product product) throws IOException {
        List<Product> products = getAllProducts();
        products.add(product);
        saveProducts(products);
    }

    public void saveProducts(List<Product> products) throws FileNotFoundException {
        FileUtils.clearFile(fileName);
        PrintWriter pw = new PrintWriter(new FileOutputStream(fileName, true));
        for(Product product:products){
            pw.write(product.toString() + "\n");
        }
        pw.close();
    }

    public void removeProductById(Integer id) throws IOException {
        List<Product> products = getAllProducts();

        for(Product product : products){
            if(product.getId().equals(id)){
                products.remove(product);
            }
        }
        saveProducts(products);
    }

    public void removeProductByName(String productName) throws IOException {
        List<Product> products = getAllProducts();

        for(Product product : products){
            if(product.getProductName().equals(productName)){
                products.remove(product);
            }
        }
        saveProducts(products);
    }

    public List<Product> getAllProducts() throws IOException {
        List<Product> products = new ArrayList<Product>();
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String readLine = br.readLine();
        while(readLine != null){
            Product product = ProductParser.stringToProduct(readLine);
            System.out.println(product);
            if(product != null){
                products.add(product);
            }
            readLine = br.readLine();
        }
        br.close();

        return products;
    }

}
