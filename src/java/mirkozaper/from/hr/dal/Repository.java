/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirkozaper.from.hr.dal;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
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
public interface Repository {
    
    List<Category> selectCategories() throws Exception;
    List<Product> selectProducts(int categoryID) throws Exception;
    Product selectProduct(int idProduct) throws Exception;
    Category selectCategory(int idCategory) throws Exception;
    boolean userLogin(String username,String password) throws Exception;
    User selectUser(int idUser)throws Exception;
    User selectUser(String username)throws Exception;
    boolean userReg(User user) throws Exception;
    int createOrder(int paymentType, int userId, String shippingFirstName, 
            String shippingLastName, String shippingAddress, String shippingZipCode, 
            String shippingCity, String shippingCountry, List<Item> cart) throws Exception;
    List<UserOrderVM> selectOrdersByUserId(int userId) throws Exception;
    List<OrderItemVM> selectOrderItems(int orderId) throws Exception;
    List<ProductVM> selectProductsByAdmin() throws Exception;
    
    List<AdminOrderVM> selectOrdersByAdmin() throws Exception;
    List<AdminOrderVM> selectOrdersByAdmin(String username) throws Exception;
    List<AdminOrderVM> selectOrdersByAdminFrom(Date dateFrom) throws Exception;
    List<AdminOrderVM> selectOrdersByAdminTo(Date dateTo) throws Exception;
    List<AdminOrderVM> selectOrdersByAdmin(Date dateFrom, Date dateTo) throws Exception;
    List<AdminOrderVM> selectOrdersByAdminFrom(String username, Date dateFrom) throws Exception;
    List<AdminOrderVM> selectOrdersByAdminTo(String username, Date dateTo) throws Exception;
    List<AdminOrderVM> selectOrdersByAdmin(String username, Date dateFrom, Date dateTo) throws Exception;
    
    
    void createCategory(Category category) throws Exception;
    void createProduct(Product product) throws Exception;
    void updateCategory(Category category) throws Exception;
    void updateProduct(Product product) throws Exception;
    void deleteCategory(int idCategory) throws Exception;
    void deleteProduct(int idProduct) throws Exception;
    List<LoginLogVM> selectLoginLogs() throws Exception;
    void createLoginLog(LoginLog loginLog) throws Exception;
}
