package entity;
//60x60x85
import api.ProductDao;
import api.ProductService;
import api.UserDao;
import api.UserRegisterLoginFacade;
import dao.ProductDaoImpl;
import dao.UserDaoImpl;
import facade.UserRegisterLoginFacadeImpl;
import service.ProductServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void startMenu() {
        System.out.println("MANAGEMENT MENU");
        System.out.println("1 - Zaloguj się");
        System.out.println("2 - Zarejestruj się");
        System.out.println("0 - Wyjdź");
    }
    public static void loggedMenu() {
        System.out.println("MANAGEMENT MENU");
        System.out.println("1 - Dodaj nowy product");
        System.out.println("0 - Wyloguj się");
    }
    public static void productTypeMenu() {
        System.out.println("1 - Dodaj buty");
        System.out.println("2 - Dodaj ubrania");
        System.out.println("3 - Inne");
    }
    public static Product createOtherProduct() {
        String productName, color;
        Float price, weight;
        Integer count;
        System.out.println("ProductName: ");
        productName = scanner.next();
        System.out.println("Price: ");
        price = scanner.nextFloat();
        System.out.println("Weight: ");
        weight = scanner.nextFloat();
        System.out.println("Color: ");
        color = scanner.next();
        System.out.println("Count: ");
        count = scanner.nextInt();
        return new Product(1, productName, price, weight, color, count);
    }
    public static Product createBootsProduct() {
        String productName, color;
        Float price, weight;
        Integer count, size;
        Boolean isNaturalSkin;
        System.out.println("ProductName: ");
        productName = scanner.next();
        System.out.println("Price: ");
        price = scanner.nextFloat();
        System.out.println("Weight: ");
        weight = scanner.nextFloat();
        System.out.println("Color: ");
        color = scanner.next();
        System.out.println("Count: ");
        count = scanner.nextInt();
        System.out.println("Size: ");
        size = scanner.nextInt();
        System.out.println("Is natural skin: ");
        isNaturalSkin = scanner.nextBoolean();
        return new Boots(1, productName, price, weight, color, count, size, isNaturalSkin);
    }
    public static Product createClothProduct() {
        String productName, color, size, material;
        Float price, weight;
        Integer count;
        System.out.println("ProductName: ");
        productName = scanner.next();
        System.out.println("Price: ");
        price = scanner.nextFloat();
        System.out.println("Weight: ");
        weight = scanner.nextFloat();
        System.out.println("Color: ");
        color = scanner.next();
        System.out.println("Count: ");
        count = scanner.nextInt();
        System.out.println("Size: ");
        size = scanner.next();
        System.out.println("Material: ");
        material = scanner.next();
        return new Cloth(1, productName, price, weight, color, count, size, material);
    }
    public static void main(String[] args) {
        UserRegisterLoginFacade userFacade = UserRegisterLoginFacadeImpl.getInstance();
        ProductService productService = ProductServiceImpl.getInstance();
        boolean appOn = true;
        boolean loggedOn = false;
        int read;
        while (appOn) {
            startMenu();
            read = scanner.nextInt();
            switch (read) {
                case 1:
                    System.out.println("Podaj login:");
                    String loginLog = scanner.next();
                    System.out.println("Podaj hasło:");
                    String passwordLog = scanner.next();
                    if (userFacade.loginUser(loginLog, passwordLog)) {
                        loggedOn = true;
                        System.out.println("Zalogowałeś się!");
                    } else {
                        System.out.println("Niepoprawne dane!");
                    }
                    break;
                case 2:
                    System.out.println("Podaj login:");
                    String loginReg = scanner.next();
                    System.out.println("Podaj hasło:");
                    String passwordReg = scanner.next();
                    User user = new User(1, loginReg, passwordReg);
                    if (userFacade.registerUser(user)) {
                        System.out.println("Zarejestrowałeś się!");
                    } else {
                        System.out.println("Cos poszło nie tak!");
                    }
                    break;
                case 0:
                    appOn = false;
                    break;
            }
            while (loggedOn) {
                loggedMenu();
                read = scanner.nextInt();
                switch (read) {
                    case 1:
                        productTypeMenu();
                        read = scanner.nextInt();
                        Product product = null;
                        switch (read) {
                            case 1:
                                product = createBootsProduct();
                                break;
                            case 2:
                                product = createClothProduct();
                                break;
                            case 3:
                                product = createOtherProduct();
                                break;
                        }
                        if (productService.saveProduct(product)) {
                            System.out.println("Produkt został utworzony");
                        } else {
                            System.out.println("Produkt nie został utworzony.");
                        }
                        break;
                    case 0:
                        loggedOn = false;
                        break;
                }
            }
        }
    }
//    public static void main(String[] args) throws IOException {
//        List<Product> products = new ArrayList<Product>();

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
//        List<User> users = new ArrayList<User>();
//        productClothDao.getAllProducts();
//        User user = new User(1, "admin", "admin");
//        UserDao userDao = new UserDaoImpl("users");
//        users.add(user);
//        userDao.saveUsers(users);


//    }
}
