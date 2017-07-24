# Lighting

Lighting is a lightful framework for JavaWeb .



## Feature

###  **A Lightful Framework for JavaWeb**


 * Lighting offer some Features IOC / MVC / AOP / Transaction. 
 * It is baesd on Servlet 3.0 spec.
 * Use **Annotation** instead of XML for Configuration.
 
 
### **Enable to separate Front-End and Server-side.**
 
 * Use Jsp or HTML as View's template
 * MVC style in Server-side

###  **For the medium-sized Web-Baesd applications**
 
 * It is friendly-using 
 * deploy and configure by **Maven**

 
 
## Guide

### 1. Download and install

	git clone https://github.com/Yisaer/Lighting.git	
Step into the `Lighting` folder and you can see the `pom.xml`

	mvn install
	
### 2. Deploy

Create a Web app Project By Maven & you will have the following list

```
sample/
　　┗ src/
　　　　┗ main/
　　　　　　┗ java/
　　　　　　┗ resources/
　　　　　　┗ webapp/
　　┗ pom.xml
```

Create following folders in `java` folder

```
com/
　　┗ yisaer/
　　　　┗ sample/
　　　　　　┗ action/
　　　　　　┗ entity/
　　　　　　┗ service/
```

So the Base Package is `com.yisaer.sample`. It will be useful sooner.

Append dependency in your `pom.xml`

```xml
<dependency>
    <groupId>com.yisaer</groupId>
    <artifactId>lighting-framework</artifactId>
    <version>1.1.2</version>
</dependency>
```

### fill Configuration

Create `lighting.properties`  in `resources` folder and fill the following configuration. Here is one example.

```

lighting.framework.jdbc.driver=com.mysql.jdbc.Driver
lighting.framework.jdbc.url=jdbc:mysql://localhost:3306/Test
lighting.framework.jdbc.username=root
lighting.framework.jdbc.password=root
lighting.framework.app.base_package=com.yisaer.testproject
lighting.framework.app.jsp_path=/WEB-INF/view/
lighting.framework.app.asset_path=/asset/
```


### Model folder

You can put Entity in model folder which depends in situation.


### Service Impl

Let Service Imple In `service` folder and use `@Service` to let  know. This also means I havent finish to separate the Interface and Implement of Service .

Here was one example.

```java
@Service
public class CustomerService {

    /**
     *  Get CustomerList
     */
    public List<Customer> getCustomerList() {
        String sql = "SELECT * FROM customer";
        return DatabaseHelper.queryEntityList(Customer.class, sql);
    }

    /**
     *  Get Customer
     */
    public Customer getCustomer(long id) {
        String sql = "SELECT * FROM customer WHERE id = ?";
        return DatabaseHelper.queryEntity(Customer.class, sql, id);
    }

    /**
     *  Create Customer
     */
    public boolean createCustomer(Map<String, Object> fieldMap) {
        return DatabaseHelper.insertEntity(Customer.class, fieldMap);
    }

    /**
     *  Update Customer
     */
    public boolean updateCustomer(long id, Map<String, Object> fieldMap) {
        return DatabaseHelper.updateEntity(Customer.class, id, fieldMap);
    }

    /**
     *  Delete Customer
     */
    public boolean deleteCustomer(long id) {
        return DatabaseHelper.deleteEntity(Customer.class, id);
    }
}

```
As you can see, you can use `DatabaseHelper` to interact with database which is Mysql compulsively.

### Controller

The `Controller` will Deal with the requests and return the Data（like json) or View (like jsp) by Path. You should also use `@Controller` and `@Action` to configure.

Use `Inject` to get a instance of your service.

Here is one example: 

```java
@Controller
public class CustomerController {

    @Inject
    private CustomerService customerService;

    /**
     * Enter into Customer View
     */
    @Action("get:/customer")
    public View index(Param param) {
        List<Customer> customerList = customerService.getCustomerList();
        return new View("customer.jsp").addModel("customerList", customerList);
    }

    /**
     *  show Info
     */
    @Action("get:/customer_show")
    public View show(Param param) {
        long id = param.getLong("id");
        Customer customer = customerService.getCustomer(id);
        return new View("customer_show.jsp").addModel("customer", customer);
    }

    /**
     * 进入 创建客户 界面
     */
    @Action("get:/customer_create")
    public View create(Param param) {
        return new View("customer_create.jsp");
    }


    /**
     * Deal Edit Request
     */
    @Action("put:/customer_edit")
    public Data editSubmit(Param param) {
        long id = param.getLong("id");
        Map<String, Object> fieldMap = param.getFieldMap();
        boolean result = customerService.updateCustomer(id, fieldMap);
        return new Data(result);
    }

    /**
     * Deal Delete Request
     */
    @Action("delete:/customer_edit")
    public Data delete(Param param) {
        long id = param.getLong("id");
        boolean result = customerService.deleteCustomer(id);
        return new Data(result);
    }
}

```



## Libraries Depended

TODO



## TODO

* More Hence and Improve
