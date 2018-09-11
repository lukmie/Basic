package entity;

import api.ProductDao;
import api.UserDao;
import dao.ProductDaoImpl;
import dao.UserDaoImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        List<Product> products = new ArrayList<Product>();

//        User user = new User(11, "admin", "admin");
//        Cloth cloth = new Cloth(11, "T-shirt", 35.9f, 0.3f, "Black", 10,"XL", "Cotton");
//        Boots boots = new Boots(11, "High heels", 99.9f, .5f, "Red", 12, 35, true);
//
//        System.out.println(user.toString());
//        System.out.println(cloth.toString());
//        System.out.println(boots.toString());
//
//        List<Integer> numbers = new ArrayList<Integer>();
//        numbers.add(5);
//        numbers.add(7);
//        numbers.add(3);
//
//        numbers.remove(1);
//        numbers.size();


//        Cloth cloth = new Cloth(1, "T-shirt", 35.9f, 0.3f, "Black", 10,"XL", "Cotton");
//        Cloth cloth2 = new Cloth(2, "T-shirt", 3225.9f, 220.2223f, "Black", 10,"XL", "Cotton");
//        Cloth cloth3 = new Cloth(3, "T-shirt", 32233335.9f, 2233330.2223f, "Black", 10,"XL", "Cotton");
//        Boots boots = new Boots(11, "High heels", 99.9, .5f, "Red", 12, 35, true);
//
//        ProductDao productClothDao = new ProductDaoImpl("clothes", "CLOTH");
//        products.add(cloth);
//        products.add(cloth2);
//        products.add(cloth3);
//        productClothDao.saveProducts(products);
//        productClothDao.removeProductById(4);
//        productClothDao.getAllProducts();

//        ProductDao productBootsDao = new ProductDaoImpl("boots", "BOOTS");
//        productBootsDao.saveProduct(boots);
        List<User> users = new ArrayList<User>();
//        productClothDao.getAllProducts();
        User user = new User(1, "admin", "admin");
        UserDao userDao = new UserDaoImpl("users");
        users.add(user);
        userDao.saveUsers(users);


    }
}
