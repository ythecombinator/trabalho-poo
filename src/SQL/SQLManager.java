package SQL;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;

import Classes.*;

public class SQLManager {
	
	private static Connection conn = null;
    private static String url = "jdbc:sqlite:database.db";
	
	public static void connect() {
		try {
			conn = DriverManager.getConnection(url);
			System.out.println("Connection to SQLite has been estiblished.");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	
	
	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
				System.out.println("Connection closed");
			} catch (SQLException e) {
				System.out.println(e);
			}
			conn = null;
		}
	}
	
	
	
	public static void deleteTableNamed(String name) {
		String sql = "DROP TABLE IF EXISTS " + name;
		
		try {
        	conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	
	
	
	public static void createClientTable() {
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS clients (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	address text NOT NULL,\n"
                + "	addressReference text NOT NULL,\n"
                + "	phone text NOT NULL,\n"
                + "	active bit NOT NULL\n"
                + " );";
        
        try {
        	conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	
	
	public static void insertNewClient(String name, String address, String addressReference, String phone) {
        String sql = "INSERT INTO clients(name,address,addressReference,phone,active) VALUES(?,?,?,?,?)";
 
        try {
        	SQLManager.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, address);
            pstmt.setString(3, addressReference);
            pstmt.setString(4, phone);
            pstmt.setBoolean(5, true);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	
	
	public static ArrayList<Client> selectAllClients(){
        String sql = "SELECT * FROM clients";
        ArrayList<Client> clients = new ArrayList<Client>();
        
        try {
        	SQLManager.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            while (rs.next()) {
            	Client client = new Client(
            			rs.getInt("id"), 
            			rs.getString("name"), 
            			rs.getString("address"), 
            			rs.getString("addressReference"), 
            			rs.getString("phone"),
            			rs.getBoolean("active"));
            	clients.add(client);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return clients;
    }
	
	
	
	public static String[][] selectAllClientsAsLog(){
        String sql = "SELECT * FROM clients";
        ArrayList<String[]> clients = new ArrayList<String[]>();
        
        try {
        	SQLManager.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            while (rs.next()) {
            	ArrayList<String> client = new ArrayList<String>();
            	client.add(String.valueOf(rs.getInt("id")));
            	client.add(rs.getString("name"));
            	client.add(rs.getString("address"));
            	client.add(rs.getString("addressReference"));
            	client.add(rs.getString("phone"));
            	client.add(String.valueOf(rs.getBoolean("active")));
            	
            	clients.add(client.stream().toArray(String[]::new));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return clients.stream().toArray(String[][]::new);
    }
	
	
	
	public static Client selectClientAtIndex(int index) {
		String sql = "SELECT * FROM clients WHERE id = " + index;

		Client client = new Client();
		
        try {
        	SQLManager.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            
            while (rs.next()) {
            	client = new Client(
            			rs.getInt("id"), 
            			rs.getString("name"), 
            			rs.getString("address"), 
            			rs.getString("addressReference"), 
            			rs.getString("phone"),
            			rs.getBoolean("active"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    	return client;
	}
	
	
	
	public static Client selectClientNamed(String name) {
		String sql = "SELECT * FROM clients WHERE name = \"" + name + "\"";

		Client client = new Client();
		
        try {
        	SQLManager.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            
            while (rs.next()) {
            	client = new Client(
            			rs.getInt("id"), 
            			rs.getString("name"), 
            			rs.getString("address"), 
            			rs.getString("addressReference"), 
            			rs.getString("phone"),
            			rs.getBoolean("active"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    	return client;
	}	
	
	
	
	public static void createDeliveryMenTable() {
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS deliverymen (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	vehiclePlate text NOT NULL\n"
                + " );";
        
        try {
        	conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	
	
	public static void insertNewDeliveryMan(String name, String vehiclePlate) {
        String sql = "INSERT INTO deliverymen(name,vehiclePlate) VALUES(?,?)";
 
        try {
        	SQLManager.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, vehiclePlate);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	
	
	public static ArrayList<DeliveryMan> selectAllDeliveryMen(){
        String sql = "SELECT * FROM deliveryMen";
        ArrayList<DeliveryMan> deliveryMen = new ArrayList<DeliveryMan>();
        
        try {
        	SQLManager.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            while (rs.next()) {
            	DeliveryMan deliveryMan = new DeliveryMan(
            			rs.getInt("id"), 
            			rs.getString("name"), 
            			rs.getString("vehiclePlate"), 
            			SQLManager.selectAllOrdersOfDeliveryManAtIndex(rs.getInt("id")));
            	deliveryMen.add(deliveryMan);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return deliveryMen;
    }
	
	
	
	public static String[][] selectAllDeliveryMenAsLog(){
        String sql = "SELECT * FROM deliverymen";
        ArrayList<String[]> deliveryMen = new ArrayList<String[]>();
        
        try {
        	SQLManager.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            while (rs.next()) {
            	ArrayList<String> deliveryMan = new ArrayList<String>();
            	deliveryMan.add(String.valueOf(rs.getInt("id")));
            	deliveryMan.add(rs.getString("name"));
            	deliveryMan.add(rs.getString("vehiclePlate"));
            	
            	deliveryMen.add(deliveryMan.stream().toArray(String[]::new));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return deliveryMen.stream().toArray(String[][]::new);
    }
	
	
	
	public static String selectNameOfDeliveryMenAtIndex(int index) {
		String sql = "SELECT * FROM deliverymen WHERE id = " + index;
		
		String name = "";
		
        try {
        	SQLManager.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            
            while (rs.next()) {
            	name = rs.getString("name");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    	return name;
	}	
	
	
	
	public static void createMenuItemsTable() {
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS menuitems (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	price REAL NOT NULL,\n"
                + "	type text NOT NULL,\n"
                + "	quantity integer NOT NULL\n"
                + " );";
        
        try {
        	conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	
	
	public static void insertNewMenuItem(String name, Float price, String type, int quantity) {
        String sql = "INSERT INTO menuitems(name,price,type,quantity) VALUES(?,?,?,?)";
 
        try {
        	SQLManager.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setFloat(2, price);
            pstmt.setString(3, type);
            pstmt.setInt(4, quantity);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	
	
	public static ArrayList<MenuItem> selectAllItemsInMenu(){
        String sql = "SELECT * FROM menuitems";
        ArrayList<MenuItem> itemsInMenu = new ArrayList<MenuItem>();
        
        try {
        	SQLManager.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            while (rs.next()) {
            	MenuItem itemInMenu;
            	String type = rs.getString("type");
            	
            	if (type.equals("Snack")) {
            		itemInMenu = new Snack(
            				rs.getInt("id"), 
            				rs.getString("name"), 
            				rs.getFloat("price"));
            	} else if (type.equals("Dish")) {
            		itemInMenu = new Dish(
            				rs.getInt("id"), 
            				rs.getString("name"), 
            				rs.getFloat("price"));
            		
            	} else if (type.equals("Drink")) {
            		itemInMenu = new Drink(
            				rs.getInt("id"), 
            				rs.getString("name"), 
            				rs.getFloat("price"), 
            				rs.getInt("quantity"));
            	} else {
            		itemInMenu = new Dessert(
            				rs.getInt("id"), 
            				rs.getString("name"), 
            				rs.getFloat("price"));
            	}
            	itemsInMenu.add(itemInMenu);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return itemsInMenu;
    }
	
	
	
	public static MenuItem selectItemsInMenuAtIndex(int index){
        String sql = "SELECT * FROM menuitems WHERE id = " + index;
        MenuItem itemInMenu = new MenuItem();
        
        try {
        	SQLManager.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            while (rs.next()) {
            	String type = rs.getString("type");
            	
            	if (type.equals("Snack")) {
            		itemInMenu = new Snack(
            				rs.getInt("id"), 
            				rs.getString("name"), 
            				rs.getFloat("price"));
            	} else if (type.equals("Dish")) {
            		itemInMenu = new Dish(
            				rs.getInt("id"), 
            				rs.getString("name"), 
            				rs.getFloat("price"));
            		
            	} else if (type.equals("Drink")) {
            		itemInMenu = new Drink(
            				rs.getInt("id"), 
            				rs.getString("name"), 
            				rs.getFloat("price"), 
            				rs.getInt("quantity"));
            	} else {
            		itemInMenu = new Dessert(
            				rs.getInt("id"), 
            				rs.getString("name"), 
            				rs.getFloat("price"));
            	}
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return itemInMenu;
    }
	
	
	
	public static MenuItem selectItemsInMenuWithName(String name){
        String sql = "SELECT * FROM menuitems WHERE name = \"" + name + "\"";
        MenuItem itemInMenu = new MenuItem();
        
        try {
        	SQLManager.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            while (rs.next()) {
            	String type = rs.getString("type");
            	
            	if (type.equals("Snack")) {
            		itemInMenu = new Snack(
            				rs.getInt("id"), 
            				rs.getString("name"), 
            				rs.getFloat("price"));
            	} else if (type.equals("Dish")) {
            		itemInMenu = new Dish(
            				rs.getInt("id"), 
            				rs.getString("name"), 
            				rs.getFloat("price"));
            		
            	} else if (type.equals("Drink")) {
            		itemInMenu = new Drink(
            				rs.getInt("id"), 
            				rs.getString("name"), 
            				rs.getFloat("price"), 
            				rs.getInt("quantity"));
            	} else {
            		itemInMenu = new Dessert(
            				rs.getInt("id"), 
            				rs.getString("name"), 
            				rs.getFloat("price"));
            	}
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return itemInMenu;
    }
	
	
	
	public static String[][] selectAllItemsInMenuAsLog(){
        String sql = "SELECT * FROM menuitems";
        ArrayList<String[]> itemsInMenu = new ArrayList<String[]>();
        
        try {
        	SQLManager.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            while (rs.next()) {
            	ArrayList<String> itemInMenu = new ArrayList<String>();
            	itemInMenu.add(String.valueOf(rs.getInt("id")));
            	itemInMenu.add(rs.getString("name"));
            	itemInMenu.add(String.valueOf(rs.getFloat("price")));
            	itemInMenu.add(rs.getString("type"));
            	itemInMenu.add(String.valueOf(rs.getInt("quantity")));
            	
            	itemsInMenu.add(itemInMenu.stream().toArray(String[]::new));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return itemsInMenu.stream().toArray(String[][]::new);
    }
	
	
	
	public static void createOrdersTable() {
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS orders (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	client integer NOT NULL,\n"
                + "	status text NOT NULL,\n"
                + "	date text NOT NULL,\n"
                + "	deliveryManID integer NOT NULL\n"
                + " );";
        
        try {
        	conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	
	
	public static void insertNewOrder(Client client, String date) {
        String sql = "INSERT INTO orders(client,status,date,deliveryManID) VALUES(?,?,?,?)";

        try {
        	SQLManager.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, client.id);
            pstmt.setString(2, "Processing");
            pstmt.setString(3, date);
            pstmt.setInt(4, -1);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	
	
	public static ArrayList<Order> selectAllOrders() {
		String sql = "SELECT * FROM orders";
		ArrayList<Order> orders = new ArrayList<Order>();
        
        try {
        	SQLManager.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            while (rs.next()) {
            	Order order = new Order(
            			rs.getInt("id"), 
            			selectClientAtIndex(rs.getInt("client")), 
            			rs.getString("status"), 
            			rs.getString("date"),
            			rs.getInt("deliveryManID"),
            			SQLManager.selectAllItemsInMenuOnOrderWithIndex(rs.getInt("id")));
            	orders.add(order);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		return orders;
	}
	
	
	
	public static Order selectOrdersWithDate(String date) {
		String sql = "SELECT * FROM orders WHERE date = \"" + date + "\"";
		Order order = new Order();
        
        try {
        	SQLManager.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            while (rs.next()) {
            	order = new Order(
            			rs.getInt("id"), 
            			selectClientAtIndex(rs.getInt("client")), 
            			rs.getString("status"), 
            			rs.getString("date"),
            			rs.getInt("deliveryManID"),
            			SQLManager.selectAllItemsInMenuOnOrderWithIndex(rs.getInt("id")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		return order;
	}
	
	
	
	public static String[][] selectAllOrdersAsLogWithMod(String mod){
        String sql = "SELECT * FROM orders " + mod;
        ArrayList<String[]> ItemsInMenu = new ArrayList<String[]>();
        
        try {
        	SQLManager.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            while (rs.next()) {
            	ArrayList<String> ItemInMenu = new ArrayList<String>();
            	ItemInMenu.add(String.valueOf(rs.getInt("id")));
            	ItemInMenu.add(SQLManager.selectClientAtIndex(rs.getInt("client")).name);
            	ItemInMenu.add(rs.getString("status"));
            	ItemInMenu.add(rs.getString("date"));
            	if (rs.getInt("deliveryManID") < 1) {
            		ItemInMenu.add("---");
            	} else {
                	ItemInMenu.add(SQLManager.selectNameOfDeliveryMenAtIndex(rs.getInt("deliveryManID")));
            	}
            	
            	ItemsInMenu.add(ItemInMenu.stream().toArray(String[]::new));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return ItemsInMenu.stream().toArray(String[][]::new);
    }
	
	
	
	public static ArrayList<Order> selectAllOrdersOfClientAtIndex(int index) {
		String sql = "SELECT * FROM orders where client = " + index;
		ArrayList<Order> orders = new ArrayList<Order>();

        try {
        	SQLManager.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            while (rs.next()) {
            	Order order = new Order(
            			rs.getInt("id"), 
            			selectClientAtIndex(rs.getInt("client")), 
            			rs.getString("status"), 
            			rs.getString("date"),
            			rs.getInt("deliveryManID"),
            			SQLManager.selectAllItemsInMenuOnOrderWithIndex(rs.getInt("id")));
            	orders.add(order);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		return orders;
	}
	
	
	
	public static Order selectOrderAtIndex(int index) {
		String sql = "SELECT * FROM orders where id = " + index;
		Order order = new Order();

        try {
        	SQLManager.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            while (rs.next()) {
            	order = new Order(
            			rs.getInt("id"), 
            			selectClientAtIndex(rs.getInt("client")), 
            			rs.getString("status"), 
            			rs.getString("date"),
            			rs.getInt("deliveryManID"),
            			SQLManager.selectAllItemsInMenuOnOrderWithIndex(rs.getInt("id")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		return order;
	}
	
	
	
	public static ArrayList<Order> selectAllOrdersOfDeliveryManAtIndex(int index) {
		String sql = "SELECT * FROM orders where deliveryManID = " + index;
		ArrayList<Order> orders = new ArrayList<Order>();

        try {
        	SQLManager.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            while (rs.next()) {
            	Order order = new Order(
            			rs.getInt("id"), 
            			selectClientAtIndex(rs.getInt("client")), 
            			rs.getString("status"), 
            			rs.getString("date"),
            			rs.getInt("deliveryManID"),
            			SQLManager.selectAllItemsInMenuOnOrderWithIndex(rs.getInt("id")));
            	orders.add(order);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		return orders;
	}
	
	
	
	public static void createOrdersItemsTable() {
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS ordersitems (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	orderID integer NOT NULL,\n"
                + "	quantity integer NOT NULL,\n"
                + "	menuItemID integer NOT NULL\n"
                + " );";
        
        try {
        	conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	
	
	public static void insertNewOrderItem(int orderID, int quantity, int menuItemID) {
        String sql = "INSERT INTO ordersitems(orderID,quantity,menuItemID) VALUES(?,?,?)";
        
        try {
        	SQLManager.connect();
        	
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, orderID);
            pstmt.setInt(2, quantity);
            pstmt.setInt(3, menuItemID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	
	
	public static ArrayList<OrderItem> selectAllItemsInMenuOnOrderWithIndex(int index){
        String sql = "SELECT * FROM ordersitems WHERE orderID = " + index;
        ArrayList<OrderItem> itemsInOrder = new ArrayList<OrderItem>();
        
        try {
        	SQLManager.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);
            while (rs.next()) {
            	OrderItem itemInOrder = new OrderItem(
            			rs.getInt("id"),
            			rs.getInt("orderID"), 
            			rs.getInt("quantity"), 
            			SQLManager.selectItemsInMenuAtIndex(rs.getInt("menuItemID")));
            	
            	itemsInOrder.add(itemInOrder);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return itemsInOrder;
    }
}
