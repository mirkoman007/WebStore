/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirkozaper.from.hr.dal.sql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import mirkozaper.from.hr.dal.Repository;
import mirkozaper.from.hr.model.Category;
import mirkozaper.from.hr.model.Item;
import mirkozaper.from.hr.model.LoginLog;
import mirkozaper.from.hr.model.Product;
import mirkozaper.from.hr.model.User;
import mirkozaper.from.hr.model.vm.AdminOrderVM;
import mirkozaper.from.hr.model.vm.LoginLogVM;
import mirkozaper.from.hr.model.vm.OrderItemVM;
import mirkozaper.from.hr.model.vm.ProductVM;
import mirkozaper.from.hr.model.vm.UserOrderVM;

/**
 *
 * @author mirko
 */
public class SqlRepository implements Repository{

    private static final String ID_CATEGORY="IDCategory";
    private static final String NAME="Name";
    private static final String IMAGE_URL="ImageUrl";
    private static final String ID_PRODUCT="IDProduct";
    private static final String PRICE="Price";
    private static final String DESCRIPTION="Description";
    private static final String CATEGORY_ID="CategoryID";
    private static final String RESULT="Result";
    private static final String CATEGORY="Category";
    
    private static final String SELECT_CATEGORIES="{ CALL selectCategories }";
    private static final String SELECT_PRODUCTS = "{ CALL selectProducts (?) }";
    private static final String SELECT_PRODUCT = "{ CALL selectProduct (?) }";
    private static final String SELECT_CATEGORY = "{ CALL selectCategory (?) }";
    private static final String USER_LOGIN = "{ CALL userLogin (?,?) }";
    private static final String SELECT_USER = "{ CALL selectUser (?) }";
    private static final String SELECT_USER_WITH_USERNAME = "{ CALL selectUserWithUsername (?) }";
    private static final String USER_REGISTRATION = "{ CALL userRegistration (?,?,?,?,?,?,?,?) }";
    private static final String CREATE_ORDER = "{ CALL createOrder (?,?,?,?,?,?,?,?,?,?) }";
    private static final String CREATE_ITEM = "{ CALL createItem (?,?,?,?,?,?,?) }";
    private static final String SELECT_ORDERS_BY_USER_ID = "{ CALL selectOrdersByUserId (?) }";
    private static final String SELECT_ORDER_ITEMS = "{ CALL selectOrderItems (?) }";
    private static final String SELECT_PRODUCTS_BY_ADMIN = "{ CALL selectProductsByAdmin }";
    private static final String SELECT_ORDERS_BY_ADMIN = "{ CALL selectOrdersByAdmin (?,?,?) }";
    private static final String SELECT_LOGIN_LOGS="{ CALL selectLoginLog }";
    private static final String CREATE_LOGIN_LOG="{ CALL createLoginLog (?,?,?) }";

    
    
            
    @Override
    public List<Category> selectCategories() throws Exception {
        List<Category> categories=new ArrayList<>();
        DataSource dataSource=DataSourceSingleton.getInstance();
        try (Connection con=dataSource.getConnection();
            CallableStatement stmt = con.prepareCall(SELECT_CATEGORIES);
            ResultSet rs = stmt.executeQuery()){
                while(rs.next()){
                    categories.add(new Category(
                            rs.getInt(ID_CATEGORY), 
                            rs.getString(NAME),
                            rs.getString(IMAGE_URL)));
                }
        }
        return categories;
    }

    
    @Override
    public List<Product> selectProducts(int categoryID) throws Exception {
        List<Product> products=new ArrayList<>();
        DataSource dataSource=DataSourceSingleton.getInstance();
        try(Connection con=dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_PRODUCTS)){
            stmt.setInt(1, categoryID);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                products.add(new Product(
                        rs.getInt(ID_PRODUCT),
                        rs.getString(NAME),
                        rs.getString(IMAGE_URL),
                        rs.getFloat(PRICE),
                        rs.getString(DESCRIPTION),
                        rs.getInt(CATEGORY_ID)));
            }
            
        }
        return products;
    }

    @Override
    public Product selectProduct(int idProduct) throws Exception {
        DataSource dataSource=DataSourceSingleton.getInstance();
        try(Connection con=dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_PRODUCT)){
            stmt.setInt(1, idProduct);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                return new Product(
                        rs.getInt(ID_PRODUCT),
                        rs.getString(NAME),
                        rs.getString(IMAGE_URL),
                        rs.getFloat(PRICE),
                        rs.getString(DESCRIPTION),
                        rs.getInt(CATEGORY_ID));
            }
            
        }
        return null;
    }

    @Override
    public Category selectCategory(int idCategory) throws Exception {
        DataSource dataSource=DataSourceSingleton.getInstance();
        try(Connection con=dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_CATEGORY)){
            stmt.setInt(1, idCategory);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                return new Category(
                        rs.getInt(ID_CATEGORY),
                        rs.getString(NAME),
                        rs.getString(IMAGE_URL));
            }
            
        }
        return null;
    }

    @Override
    public boolean userLogin(String username, String password) throws Exception {
        DataSource dataSource=DataSourceSingleton.getInstance();
        try(Connection con=dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(USER_LOGIN)){
            stmt.setString(1, username);
            stmt.setString(2, password);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                return rs.getBoolean(RESULT);
            }
            
        }
        return false;
    }

    @Override
    public User selectUser(int idUser) throws Exception {
        DataSource dataSource=DataSourceSingleton.getInstance();
        try(Connection con=dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_USER)){
            stmt.setInt(1, idUser);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                return new User(
                        rs.getInt("IDUser"),
                        rs.getString("Username"),
                        rs.getInt("UserType"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Address"),
                        rs.getString("ZipCode"),
                        rs.getString("City"),
                        rs.getString("Country")
                );
            }
            
        }
        return null;
    }
    
    @Override
    public User selectUser(String username) throws Exception {
        DataSource dataSource=DataSourceSingleton.getInstance();
        try(Connection con=dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_USER_WITH_USERNAME)){
            stmt.setString(1, username);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                return new User(
                        rs.getInt("IDUser"),
                        rs.getString("Username"),
                        rs.getInt("UserType"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Address"),
                        rs.getString("ZipCode"),
                        rs.getString("City"),
                        rs.getString("Country")
                );
            }
            
        }
        return null;
    }

    @Override
    public boolean userReg(User user) throws Exception {
        DataSource dataSource=DataSourceSingleton.getInstance();
        try(Connection con=dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(USER_REGISTRATION)){
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getFirstName());
            stmt.setString(4, user.getLastName());
            stmt.setString(5, user.getAddress());
            stmt.setString(6, user.getZipCode());
            stmt.setString(7, user.getCity());
            stmt.setString(8, user.getCountry());
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                return rs.getBoolean(RESULT);
            }
        }
        return false;
    }

    @Override
    public int createOrder(int paymentType, int userId, String shippingFirstName, String shippingLastName, String shippingAddress, String shippingZipCode, String shippingCity, String shippingCountry, List<Item> cart) throws Exception {
        DataSource dataSource=DataSourceSingleton.getInstance();
        int orderID;
        try(Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_ORDER)){
            stmt.setInt(1, paymentType);
            stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setInt(3, userId);
            stmt.setString(4, shippingFirstName);
            stmt.setString(5, shippingLastName);
            stmt.setString(6, shippingAddress);
            stmt.setString(7, shippingZipCode);
            stmt.setString(8, shippingCity);
            stmt.setString(9, shippingCountry);
            stmt.registerOutParameter(10,Types.INTEGER);
            
            stmt.executeUpdate();
            orderID=stmt.getInt(10);
        }
        
        for (int i = 0; i < cart.size(); i++) {
            try(Connection con = dataSource.getConnection();
                    CallableStatement stmt = con.prepareCall(CREATE_ITEM)){
                stmt.setInt(1, orderID);
                stmt.setInt(2, cart.get(i).getId());
                stmt.setString(3, cart.get(i).getName());
                stmt.setString(4, cart.get(i).getImageUrl());
                stmt.setString(5, cart.get(i).getDescription());
                stmt.setFloat(6, cart.get(i).getPrice());
                stmt.setInt(7, cart.get(i).getQuantity());

                stmt.executeUpdate();
            }
            
        }
        
        
        
        return orderID;
    }

    @Override
    public List<UserOrderVM> selectOrdersByUserId(int userId) throws Exception {
        List<UserOrderVM> orders=new ArrayList<>();
        DataSource dataSource=DataSourceSingleton.getInstance();
        try(Connection con=dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_ORDERS_BY_USER_ID)){
            stmt.setInt(1, userId);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                orders.add(new UserOrderVM(
                        rs.getInt("IDOrder"),
                        rs.getInt("PaymentType"),
                        rs.getTimestamp("DateTime").toLocalDateTime()));
            }
        }
        return orders;
    }

    @Override
    public List<OrderItemVM> selectOrderItems(int orderId) throws Exception {
        List<OrderItemVM> items=new ArrayList<>();
        DataSource dataSource=DataSourceSingleton.getInstance();
        try(Connection con=dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_ORDER_ITEMS)){
            stmt.setInt(1, orderId);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                items.add(new OrderItemVM(
                        rs.getInt("ProductID"),
                        rs.getFloat("Price"),
                        rs.getInt("Quantity"),
                        rs.getString("Name"),
                        rs.getString("ImageUrl"),
                        rs.getString("Description")
                ));
            }
            
        }
        return items;
    }

    @Override
    public List<ProductVM> selectProductsByAdmin() throws Exception {
        List<ProductVM> products=new ArrayList<>();
        DataSource dataSource=DataSourceSingleton.getInstance();
        try(Connection con=dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_PRODUCTS_BY_ADMIN)){
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                products.add(new ProductVM(
                        rs.getInt(ID_PRODUCT),
                        rs.getString(NAME),
                        rs.getString(IMAGE_URL),
                        rs.getFloat(PRICE),
                        rs.getString(CATEGORY)));
            }
            
        }
        return products;
    }

    @Override
    public List<AdminOrderVM> selectOrdersByAdmin() throws Exception {
        List<AdminOrderVM> orders=new ArrayList<>();
        DataSource dataSource=DataSourceSingleton.getInstance();
        try(Connection con=dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_ORDERS_BY_ADMIN)){
            stmt.setString(1, null);
            stmt.setDate(2, null);
            stmt.setDate(3, null);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                orders.add(new AdminOrderVM(
                        rs.getInt("IDOrder"),
                        rs.getString("Username"),
                        rs.getInt("PaymentType"),
                        rs.getTimestamp("DateTime").toLocalDateTime()));
            }
            
        }
        return orders;
    }

    @Override
    public List<AdminOrderVM> selectOrdersByAdmin(String username) throws Exception {
        List<AdminOrderVM> orders=new ArrayList<>();
        DataSource dataSource=DataSourceSingleton.getInstance();
        try(Connection con=dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_ORDERS_BY_ADMIN)){
            stmt.setString(1, username);
            stmt.setDate(2, null);
            stmt.setDate(3, null);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                orders.add(new AdminOrderVM(
                        rs.getInt("IDOrder"),
                        rs.getString("Username"),
                        rs.getInt("PaymentType"),
                        rs.getTimestamp("DateTime").toLocalDateTime()));
            }
        }
        return orders;
    }

    @Override
    public List<AdminOrderVM> selectOrdersByAdminFrom(Date dateFrom) throws Exception {
        List<AdminOrderVM> orders=new ArrayList<>();
        DataSource dataSource=DataSourceSingleton.getInstance();
        try(Connection con=dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_ORDERS_BY_ADMIN)){
            stmt.setString(1, null);
            stmt.setDate(2, dateFrom);
            stmt.setDate(3, null);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                orders.add(new AdminOrderVM(
                        rs.getInt("IDOrder"),
                        rs.getString("Username"),
                        rs.getInt("PaymentType"),
                        rs.getTimestamp("DateTime").toLocalDateTime()));
            }
        }
        return orders;
    }

    @Override
    public List<AdminOrderVM> selectOrdersByAdminTo(Date dateTo) throws Exception {
        List<AdminOrderVM> orders=new ArrayList<>();
        DataSource dataSource=DataSourceSingleton.getInstance();
        try(Connection con=dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_ORDERS_BY_ADMIN)){
            stmt.setString(1, null);
            stmt.setDate(2, null);
            stmt.setDate(3, dateTo);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                orders.add(new AdminOrderVM(
                        rs.getInt("IDOrder"),
                        rs.getString("Username"),
                        rs.getInt("PaymentType"),
                        rs.getTimestamp("DateTime").toLocalDateTime()));
            }
        }
        return orders;
    }

    @Override
    public List<AdminOrderVM> selectOrdersByAdmin(Date dateFrom, Date dateTo) throws Exception {
        List<AdminOrderVM> orders=new ArrayList<>();
        DataSource dataSource=DataSourceSingleton.getInstance();
        try(Connection con=dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_ORDERS_BY_ADMIN)){
            stmt.setString(1, null);
            stmt.setDate(2, dateFrom);
            stmt.setDate(3, dateTo);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                orders.add(new AdminOrderVM(
                        rs.getInt("IDOrder"),
                        rs.getString("Username"),
                        rs.getInt("PaymentType"),
                        rs.getTimestamp("DateTime").toLocalDateTime()));
            }
        }
        return orders;
    }

    @Override
    public List<AdminOrderVM> selectOrdersByAdminFrom(String username, Date dateFrom) throws Exception {
        List<AdminOrderVM> orders=new ArrayList<>();
        DataSource dataSource=DataSourceSingleton.getInstance();
        try(Connection con=dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_ORDERS_BY_ADMIN)){
            stmt.setString(1, username);
            stmt.setDate(2, dateFrom);
            stmt.setDate(3, null);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                orders.add(new AdminOrderVM(
                        rs.getInt("IDOrder"),
                        rs.getString("Username"),
                        rs.getInt("PaymentType"),
                        rs.getTimestamp("DateTime").toLocalDateTime()));
            }
        }
        return orders;
    }

    @Override
    public List<AdminOrderVM> selectOrdersByAdminTo(String username, Date dateTo) throws Exception {
        List<AdminOrderVM> orders=new ArrayList<>();
        DataSource dataSource=DataSourceSingleton.getInstance();
        try(Connection con=dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_ORDERS_BY_ADMIN)){
            stmt.setString(1, username);
            stmt.setDate(2, null);
            stmt.setDate(3, dateTo);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                orders.add(new AdminOrderVM(
                        rs.getInt("IDOrder"),
                        rs.getString("Username"),
                        rs.getInt("PaymentType"),
                        rs.getTimestamp("DateTime").toLocalDateTime()));
            }
        }
        return orders;
    }

    @Override
    public List<AdminOrderVM> selectOrdersByAdmin(String username, Date dateFrom, Date dateTo) throws Exception {
        List<AdminOrderVM> orders=new ArrayList<>();
        DataSource dataSource=DataSourceSingleton.getInstance();
        try(Connection con=dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_ORDERS_BY_ADMIN)){
            stmt.setString(1, username);
            stmt.setDate(2, dateFrom);
            stmt.setDate(3, dateTo);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                orders.add(new AdminOrderVM(
                        rs.getInt("IDOrder"),
                        rs.getString("Username"),
                        rs.getInt("PaymentType"),
                        rs.getTimestamp("DateTime").toLocalDateTime()));
            }
        }
        return orders;
    }
    
    @Override
    public void createCategory(Category category) throws Exception {
        DataSource dataSource=DataSourceSingleton.getInstance();
        try(Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall("{ CALL createCategory (?,?) }")){
            stmt.setString(1, category.getName());
            stmt.setString(2, category.getImageUrl());
            
            stmt.executeUpdate();
        }
    }
    
    @Override
    public void createProduct(Product product) throws Exception {
        DataSource dataSource=DataSourceSingleton.getInstance();
        try(Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall("{ CALL createProduct (?,?,?,?,?) }")){
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getImageUrl());
            stmt.setFloat(3, product.getPrice());
            stmt.setString(4, product.getDescription());
            stmt.setInt(5, product.getCategoryId());
            
            stmt.executeUpdate();
        }
    }
  
    @Override
    public void updateCategory(Category category) throws Exception {
        DataSource dataSource=DataSourceSingleton.getInstance();
        try(Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall("{ CALL updateCategory (?,?,?) }")){
            stmt.setInt(1, category.getId());
            stmt.setString(2, category.getName());
            stmt.setString(3, category.getImageUrl());
            
            stmt.executeUpdate();
        }
    }

    @Override
    public void updateProduct(Product product) throws Exception {
        DataSource dataSource=DataSourceSingleton.getInstance();
        try(Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall("{ CALL updateProduct (?,?,?,?,?,?) }")){
            stmt.setInt(1, product.getId());
            stmt.setString(2, product.getName());
            stmt.setString(3, product.getImageUrl());
            stmt.setFloat(4, product.getPrice());
            stmt.setString(5, product.getDescription());
            stmt.setInt(6, product.getCategoryId());
            
            stmt.executeUpdate();
        }
    }    
    
    @Override
    public void deleteCategory(int idCategory) throws Exception {
        DataSource dataSource=DataSourceSingleton.getInstance();
        try(Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall("{ CALL deleteCategory (?) }")){
            stmt.setInt(1, idCategory);
            

            
            stmt.executeUpdate();
        }
    }
    
    @Override
    public void deleteProduct(int idProduct) throws Exception {
        DataSource dataSource=DataSourceSingleton.getInstance();
        try(Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall("{ CALL deleteProduct (?) }")){
            stmt.setInt(1, idProduct);
            
            stmt.executeUpdate();
        }
    }

    @Override
    public List<LoginLogVM> selectLoginLogs() throws Exception {
        List<LoginLogVM> loginLogs=new ArrayList<>();
        DataSource dataSource=DataSourceSingleton.getInstance();
        try (Connection con=dataSource.getConnection();
            CallableStatement stmt = con.prepareCall(SELECT_LOGIN_LOGS);
            ResultSet rs = stmt.executeQuery()){
                while(rs.next()){
                    loginLogs.add(new LoginLogVM(
                            rs.getInt("IDLoginLog"), 
                            rs.getString("Username"),
                            rs.getTimestamp("DateTime").toLocalDateTime(),
                            rs.getString("IPAddress")
                    ));
                }
        }
        return loginLogs;
    }

    
    @Override
    public void createLoginLog(LoginLog loginLog) throws Exception {
        DataSource dataSource=DataSourceSingleton.getInstance();
        try(Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_LOGIN_LOG)){
            stmt.setInt(1, loginLog.getUserID());
            stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setString(3, loginLog.getIpAddress());
            
            stmt.executeUpdate();
        }
    }

    
    
}
