import com.toedter.calendar.JDateChooser;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.*;

public class MyFrame extends JFrame {
    Connection connection = null;
    PreparedStatement state = null;
    ResultSet result = null;
    int idfruit = -1;
    int idcustomer = -1;
    int idorder = -1;
    private static final long serialVersionUID = 4L;

    JTabbedPane tabPane = new JTabbedPane();

    JPanel ordersPanel = new JPanel();
    JPanel ordPanel1= new JPanel();
    JPanel ordPanel2= new JPanel();
    JPanel ordPanel3= new JPanel();
    JLabel customerNameL = new JLabel("Customer Name:");
    JLabel fruitL = new JLabel("Fruit:");
    JLabel dateL = new JLabel("Date:");
    JLabel quantityL = new JLabel("Quantity:");
    JTextField quantityTF = new JTextField();
    JDateChooser dateChooser = new JDateChooser();
    JComboBox<String> comboCustomer = new JComboBox<>();
    JComboBox<String> comboFruit = new JComboBox<>();
    JButton orderAddB = new JButton("Add");
    JButton orderDelB = new JButton("Delete");
    JButton orderEditB = new JButton("Edit");
    JTable orderTable = new JTable();
    JScrollPane orderScroll = new JScrollPane(orderTable);

    JPanel fruitPanel = new JPanel();
    JPanel frPanel1 = new JPanel();
    JPanel frPanel2 = new JPanel();
    JPanel frPanel3 = new JPanel();
    JLabel fruitNameL = new JLabel("Fruit Name:");
    JLabel vendorNameL = new JLabel("Vendor Name:");
    JLabel priceL = new JLabel("Price per pound:");
    JTextField fruitNameTF = new JTextField();
    JTextField priceTF = new JTextField();
    JTextField vendorNameTF = new JTextField();
    JButton fruitAddB = new JButton("Add");
    JButton fruitDelB = new JButton("Delete");
    JButton fruitEditB = new JButton("Edit");
    JTable fruitTable = new JTable();
    JScrollPane fruitScroll = new JScrollPane(fruitTable);

    JPanel customerPanel = new JPanel();
    JPanel customPanel1 = new JPanel();
    JPanel customPanel2 = new JPanel();
    JPanel customPanel3 = new JPanel();
    JLabel custNameL = new JLabel("Name:");
    JLabel phoneNumbL = new JLabel("Phone Number:");
    JTextField custNameTF= new JTextField();
    JTextField  phoneNumbTF = new JTextField();
    JButton customerAddB = new JButton("Add");
    JButton customerDelB = new JButton("Delete");
    JButton customerEditB = new JButton("Edit");
    JTable customerTable = new JTable();
    JScrollPane customerScroll = new JScrollPane(customerTable);

    JPanel searchPanel = new JPanel();
    JPanel searchPanel1 = new JPanel();
    JPanel searchPanel2 = new JPanel();
    JPanel searchPanel3 = new JPanel();
    JLabel searchQuantityL = new JLabel("Please choose quantity:");
    JLabel searchDateL = new JLabel("Please choose date:");
    JComboBox<String> comboQuantity = new JComboBox<>();
    JComboBox<String> comboDate = new JComboBox<>();
    JButton search = new JButton("Search");
    JButton searchDate = new JButton("Search by date");
    JButton searchQuantity = new JButton("Search by quantity");
    JTable searchTable = new JTable();
    JScrollPane searchScroll = new JScrollPane(searchTable);


    public MyFrame() {

        connection = JDBC.getConnection();

        this.setSize(800, 700);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Fruit Shop");

        tabPane.add(fruitPanel, "Fruit");
        fruitPanel.setLayout(new GridLayout(3, 1));
        fruitPanel.add(frPanel1);
        frPanel1.setLayout(new GridLayout(5, 2));
        frPanel1.add(fruitNameL);
        frPanel1.add(fruitNameTF);
        frPanel1.add(priceL);
        frPanel1.add(priceTF);
        frPanel1.add(vendorNameL);
        frPanel1.add(vendorNameTF);
        fruitPanel.add(frPanel2);
        frPanel2.add(fruitAddB);
        frPanel2.add(fruitDelB);
        frPanel2.add(fruitEditB);
        fruitAddB.addActionListener(new AddFruitDB());
        fruitDelB.addActionListener(new DeleteFruitDB());
        fruitEditB.addActionListener(new EditFruitDB());
        fruitPanel.add(frPanel3);
        fruitScroll.setPreferredSize(new Dimension(550, 200));
        frPanel3.add(fruitScroll);

        tabPane.add(customerPanel, "Customers");
        customerPanel.setLayout(new GridLayout(3, 1));
        customerPanel.add(customPanel1);
        customPanel1.setLayout(new GridLayout(5, 2));
        customPanel1.add(custNameL);
        customPanel1.add(custNameTF);
        customPanel1.add(phoneNumbL);
        customPanel1.add(phoneNumbTF);
        customerPanel.add(customPanel2);
        customPanel2.add(customerAddB);
        customPanel2.add(customerDelB);
        customPanel2.add(customerEditB);
        customerAddB.addActionListener(new CustomerAddDB());
        customerDelB.addActionListener(new CustomerDelDB());
        customerEditB.addActionListener(new CustomerEditDB());
        customerPanel.add(customPanel3);
        customerScroll.setPreferredSize(new Dimension(550, 200));
        customPanel3.add(customerScroll);

        tabPane.add(ordersPanel, "Order");
        ordersPanel.setLayout(new GridLayout(3, 1));
        ordersPanel.add(ordPanel1);
        ordPanel1.setLayout(new GridLayout(5, 2));
        ordPanel1.add(customerNameL);
        ordPanel1.add(comboCustomer);
        ordPanel1.add(fruitL);
        ordPanel1.add(comboFruit);
        ordPanel1.add(dateL);
        ordPanel1.add(dateChooser);
        ordPanel1.add(quantityL);
        ordPanel1.add(quantityTF);
        ordersPanel.add(ordPanel2);
        ordPanel2.add(orderAddB);
        ordPanel2.add(orderDelB);
        ordPanel2.add(orderEditB);
        orderAddB.addActionListener(new AddOrderDB());
        orderDelB.addActionListener(new DeleteOrderDB());
        orderEditB.addActionListener(new EditOrderDB());
        ordersPanel.add(ordPanel3);
        ordersPanel.add(ordPanel3);
        orderScroll.setPreferredSize(new Dimension(550, 200));
        ordPanel3.add(orderScroll);

        tabPane.add(searchPanel, "Search");
        searchPanel.setLayout(new GridLayout(3, 1));
        searchPanel.add(searchPanel1);
        searchPanel1.setLayout(new GridLayout(2, 2));
        searchPanel1.add(searchDateL);
        searchPanel1.add(comboDate);
        searchPanel1.add(searchQuantityL);
        searchPanel1.add(comboQuantity);
        searchPanel.add(searchPanel2);
        searchPanel2.add(searchQuantity);
        searchPanel2.add(search);
        searchPanel2.add(searchDate);
        search.addActionListener(new SearchDB());
        searchDate.addActionListener(new DateSearchDB());
        searchQuantity.addActionListener(new QuantitySearchDB());
        searchPanel.add(searchPanel3);
        searchScroll.setPreferredSize(new Dimension(550, 200));
        searchPanel3.add(searchScroll);

        this.add(tabPane);
        this.setVisible(true);
        this.refreshComboDate();
        this.refreshComboQuantity();
        this.refreshComboCustomer();
        this.refreshComboFruit();
        this.refreshFruitsTable();
        this.refreshOrdersTable();
        this.refreshCustomersTable("customers", customerTable);
        this.refreshSearchTable();
        fruitTable.addMouseListener(new MouseActionFruitTable());
        customerTable.addMouseListener(new MouseActionCustomerTable());
        orderTable.addMouseListener(new MouseActionOrdersTable());
        // COMBO
        comboCustomer.addActionListener(e -> {
            // TODO Auto-generated method stub
            if (comboCustomer.getSelectedItem() != null && idcustomer > 0) {
                String str = "select * from customers where cust_name=?";
                try {
                    state = connection.prepareStatement(str);
                    state.setString(1, comboCustomer.getSelectedItem().toString());
                    result = state.executeQuery();
                    if (result.next()) { // check if result set is not empty
                        idcustomer = result.getInt("id");
                    }
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        comboFruit.addActionListener(e -> {
            // TODO Auto-generated method stub
            if (comboFruit.getSelectedItem() != null && idfruit > 0) {
                String str = "select * from fruits where fruit_name=?";
                try {
                    state = connection.prepareStatement(str);
                    state.setString(1, comboFruit.getSelectedItem().toString());
                    result = state.executeQuery();
                    if (result.next()) { // check if result set is not empty
                        idfruit = result.getInt("id");
                    }
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        comboQuantity.addActionListener(e -> {
            // TODO Auto-generated method stub
                String str = "select DISTINCT quantity from orders WHERE  quantity='" + comboQuantity.getSelectedItem().toString() + "'";
            try {
                    state = connection.prepareStatement(str);
                    result = state.executeQuery();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            });
        comboDate.addActionListener(e -> {
            // TODO Auto-generated method stub
            String str = "select DISTINCT order_date from orders WHERE  order_date='" + comboDate.getSelectedItem().toString() + "'";
            try {
                state = connection.prepareStatement(str);
                result = state.executeQuery();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
    }
// REFRESH COMBO
   public void refreshComboCustomer(){
        comboCustomer.removeAllItems();
        String sql="select id, cust_name from customers";
        connection=JDBC.getConnection();
        String item;
        try {
            state=connection.prepareStatement(sql);
            result=state.executeQuery();
            while(result.next()) {
                idcustomer=Integer.parseInt(result.getObject(1).toString());
                    item=result.getObject(2).toString();
                    comboCustomer.addItem(item);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void refreshComboFruit(){
        comboFruit.removeAllItems();
        String sql="select id, fruit_name from fruits";
        connection=JDBC.getConnection();
        String item;
        try {
            state=connection.prepareStatement(sql);
            result=state.executeQuery();
            while(result.next()) {
                idfruit=Integer.parseInt(result.getObject(1).toString());
                    item=result.getObject(2).toString();
                    comboFruit.addItem(item);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void refreshComboQuantity() {
        comboQuantity.removeAllItems();
        String sql = "SELECT DISTINCT quantity FROM orders ORDER BY quantity";
        connection = JDBC.getConnection();
        try {
            state = connection.prepareStatement(sql);
            result = state.executeQuery();
            while (result.next()) {
                int item = result.getInt(1);
                comboQuantity.addItem(Integer.toString(item));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void refreshComboDate(){
        comboDate.removeAllItems();
        String sql = "SELECT DISTINCT order_date FROM orders ORDER BY order_date";
        connection = JDBC.getConnection();
        try {
            state = connection.prepareStatement(sql);
            result = state.executeQuery();
            while (result.next()) {
                String date = result.getString("order_date");
                comboDate.addItem(date);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // REFRESH TABLES
    public void refreshOrdersTable() {
        connection = JDBC.getConnection();
        String str = "SELECT orders.id, idcustomer, customers.cust_name, customers.phone, idfruit, fruits.fruit_name, fruits.price, fruits.Vendor, orders.order_date, quantity, total_cost FROM orders, fruits, customers WHERE orders.idcustomer=customers.id AND orders.idfruit=fruits.id;";
        try {
            state = connection.prepareStatement(str);
            result = state.executeQuery();
            orderTable.setModel(new MyModelShop(result));
            orderTable.revalidate();
            orderTable.repaint();
            orderTable.getColumnModel().getColumn(0).setMinWidth(0);
            orderTable.getColumnModel().getColumn(0).setMaxWidth(0);
            orderTable.getColumnModel().getColumn(0).setWidth(0);
            orderTable.getColumnModel().getColumn(1).setMinWidth(0);
            orderTable.getColumnModel().getColumn(1).setMaxWidth(0);
            orderTable.getColumnModel().getColumn(1).setWidth(0);
            orderTable.getColumnModel().getColumn(4).setMinWidth(0);
            orderTable.getColumnModel().getColumn(4).setMaxWidth(0);
            orderTable.getColumnModel().getColumn(4).setWidth(0);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void refreshFruitsTable() {
        connection = JDBC.getConnection();
        String str = "SELECT fruits.id, fruit_name, price, vendor  FROM fruits ";
        try {
            state = connection.prepareStatement(str);
            result = state.executeQuery();
            fruitTable.setModel(new MyModelShop(result));

            fruitTable.getColumnModel().getColumn(0).setMinWidth(0);
            fruitTable.getColumnModel().getColumn(0).setMaxWidth(0);
            fruitTable.getColumnModel().getColumn(0).setWidth(0);

        } catch (SQLException e) {			// TODO Auto-generated catch block

            e.printStackTrace();
        } catch (Exception e) {			// TODO Auto-generated catch block

            e.printStackTrace();
        }
    }
    public void refreshSearchTable() {
        connection = JDBC.getConnection();
        String str = "SELECT orders.id, idcustomer, customers.cust_name, customers.phone, idfruit, fruits.fruit_name, fruits.price, fruits.Vendor, orders.order_date, quantity,total_cost  FROM orders, fruits, customers WHERE orders.idcustomer=customers.id AND orders.idfruit=fruits.id;";
        try {
            state = connection.prepareStatement(str);
            result = state.executeQuery();
            searchTable.setModel(new MyModelShop(result));
            searchTable.getColumnModel().getColumn(0).setMinWidth(0);
            searchTable.getColumnModel().getColumn(0).setMaxWidth(0);
            searchTable.getColumnModel().getColumn(0).setWidth(0);
            searchTable.getColumnModel().getColumn(1).setMinWidth(0);
            searchTable.getColumnModel().getColumn(1).setMaxWidth(0);
            searchTable.getColumnModel().getColumn(1).setWidth(0);
            searchTable.getColumnModel().getColumn(4).setMinWidth(0);
            searchTable.getColumnModel().getColumn(4).setMaxWidth(0);
            searchTable.getColumnModel().getColumn(4).setWidth(0);
        } catch (SQLException e) {			// TODO Auto-generated catch block

            e.printStackTrace();
        } catch (Exception e) {			// TODO Auto-generated catch block

            e.printStackTrace();
        }
    }

    public void refreshCustomersTable( String name, JTable table) {
        connection = JDBC.getConnection();
        String str = "select * from "+name;
        try {
            state = connection.prepareStatement(str);
            result = state.executeQuery();
            table.setModel(new MyModelShop(result));
            table.getColumnModel().getColumn(0).setMinWidth(0);
            table.getColumnModel().getColumn(0).setMaxWidth(0);
            table.getColumnModel().getColumn(0).setWidth(0);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
// Orders BUTTONS
    class AddOrderDB implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            connection = JDBC.getConnection();
            System.out.println("connection");
            java.util.Date utilDate = dateChooser.getDate();
            if (utilDate != null) {
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                String sql = "insert into orders(id, idcustomer, idfruit, order_date, quantity) values(null, ?, ?, ?, ? )";
                try {
                    state = connection.prepareStatement(sql);
                    state.setInt(1, idcustomer);
                    state.setInt(2, idfruit);
                    state.setDate(3, new java.sql.Date(sqlDate.getTime()));
                    state.setInt(4, Integer.parseInt(quantityTF.getText()));
                    state.execute();
                    refreshOrdersTable();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                dateChooser.setDate(null);
                quantityTF.setText("");
            }
        }
    }
    class DeleteOrderDB implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            connection = JDBC.getConnection();
            if (idorder > 0) {
                String sql = "delete from orders where id=? ";
                try {
                    state = connection.prepareStatement(sql);
                    state.setInt(1, idorder);
                    state.execute();
                    refreshOrdersTable();
                    idorder = -1;
                } catch (SQLException e1) {				// TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                dateChooser.setDate(null);
                quantityTF.setText("");
            }
        }
    }
    class EditOrderDB implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            connection = JDBC.getConnection();
            Date date = (Date) dateChooser.getDate();
            System.out.println("error");
            if (date != null && idorder>0) {
                String sql = "UPDATE orders SET idcustomer=?, idfruit=?, order_date=?, quantity=? WHERE id=?";
                System.out.println("here");
                try {
                    state = connection.prepareStatement(sql);
                    state.setInt(1, idcustomer);
                    state.setInt(2, idfruit);
                    state.setDate(3, new java.sql.Date(date.getTime()));
                    state.setInt(4, Integer.parseInt(quantityTF.getText()));
                    state.setInt(5, idorder);
                    state.execute();
                    refreshOrdersTable();
                    System.out.println("no here");
                } catch (SQLException e1) { 					// TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                dateChooser.setDate(null);
                quantityTF.setText("");            }
        }
    }

    // FRUITS BUTTONS
    class AddFruitDB implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            connection = JDBC.getConnection();
            if (!fruitNameTF.getText().isEmpty()) {
                String sql = "insert into fruits values(null,? ,? ,? )";
                try {
                    state = connection.prepareStatement(sql);
                    state.setString(1, fruitNameTF.getText());
                    state.setDouble(2, Double.parseDouble(priceTF.getText()));
                    state.setString(3, vendorNameTF.getText());
                    state.execute();
                    refreshFruitsTable();
                    refreshComboFruit();
                } catch (SQLException e1) {
                    e1.printStackTrace();

                }

                fruitNameTF.setText("");
                priceTF.setText("");
                vendorNameTF.setText("");
                idfruit = -1;
            }
        }
    }
    class DeleteFruitDB implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            connection = JDBC.getConnection();
            if (idfruit > 0) {
                String sql = "delete from fruits where id=?";
                try {
                    state = connection.prepareStatement(sql);
                    state.setInt(1, idfruit);
                    state.execute();
                    refreshFruitsTable();
                    refreshComboFruit();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                fruitNameTF.setText("");
                priceTF.setText("");
                vendorNameTF.setText("");
                idfruit = -1;
            }
        }
    }
    class EditFruitDB implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            connection = JDBC.getConnection();
            if (idfruit > 0) {
                String sql = "UPDATE fruits SET fruit_name=?, price=?, vendor=? WHERE id=?";

                try {
                    state = connection.prepareStatement(sql);
                    state.setString(1, fruitNameTF.getText());
                    state.setDouble(2, Double.parseDouble(priceTF.getText()));
                    state.setString(3, vendorNameTF.getText());
                    state.setInt(4, idfruit);
                    state.execute();
                    refreshFruitsTable();
                    refreshComboFruit();
                    fruitNameTF.setText("");
                    priceTF.setText("");
                    vendorNameTF.setText("");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }


            }
        }
    }

    //Customer Buttons
    class CustomerAddDB implements ActionListener {
        public void actionPerformed (ActionEvent arg0) {
   		connection=JDBC.getConnection();
            if(!custNameTF.getText().isEmpty()) {
                String sql="insert into customers values(null,?,?)";
                try {
                    state=connection.prepareStatement(sql);
                    state.setString(1, custNameTF.getText());
                    state.setInt(2, Integer.parseInt(phoneNumbTF.getText()));
                    state.execute();
                    refreshCustomersTable("customers", customerTable);
                    refreshComboCustomer();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                custNameTF.setText("");
                phoneNumbTF.setText("");
            }
        }
    }
    class CustomerDelDB implements ActionListener {
        public void actionPerformed (ActionEvent arg0) {
   		connection=JDBC.getConnection();
            if (idcustomer>0) {
                String sql="delete from customers where id=?";
                try {
                    state=connection.prepareStatement(sql);
                    state.setInt(1, idcustomer);
                    state.execute();
                    refreshCustomersTable("Customers", customerTable);
                    refreshComboCustomer();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                custNameTF.setText("");
                phoneNumbTF.setText("");           }
        }
    }
    class CustomerEditDB implements ActionListener {
        public void actionPerformed (ActionEvent arg0) {
   		connection=JDBC.getConnection();
            if(idcustomer>0) {
                String sql="update customers set cust_name=?, phone=? where id=?";

                try {
                    state=connection.prepareStatement(sql);
                    state.setString(1, custNameTF.getText());
                    state.setInt(2, Integer.parseInt(phoneNumbTF.getText()));
                    state.setInt(3, idcustomer);
                    state.execute();
                    refreshCustomersTable("Customers", customerTable);
                    refreshComboCustomer();

                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                custNameTF.setText("");
                phoneNumbTF.setText("");               }

        }
    }

    class DateSearchDB implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            String date = (String) comboDate.getSelectedItem();
            String query = "SELECT orders.id, idcustomer, customers.cust_name, customers.phone, idfruit, fruits.fruit_name, fruits.price, fruits.Vendor, orders.order_date, quantity, total_cost FROM orders, fruits, customers WHERE orders.idcustomer=customers.id AND orders.idfruit=fruits.id";
            // If only date is selected
            if (!date.equals("All")) {
                query += " AND orders.order_date='" + date + "'";
            }
            // Execute query and update table with results
            try {
                state = connection.prepareStatement(query);
                result = state.executeQuery();
                searchTable.setModel(new MyModelShop(result));
                searchTable.getColumnModel().getColumn(0).setMinWidth(0);
                searchTable.getColumnModel().getColumn(0).setMaxWidth(0);
                searchTable.getColumnModel().getColumn(0).setWidth(0);
                searchTable.getColumnModel().getColumn(1).setMinWidth(0);
                searchTable.getColumnModel().getColumn(1).setMaxWidth(0);
                searchTable.getColumnModel().getColumn(1).setWidth(0);
                searchTable.getColumnModel().getColumn(4).setMinWidth(0);
                searchTable.getColumnModel().getColumn(4).setMaxWidth(0);
                searchTable.getColumnModel().getColumn(4).setWidth(0);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }
    class QuantitySearchDB implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            String quantity = (String) comboQuantity.getSelectedItem();
            String query = "SELECT orders.id, idcustomer, customers.cust_name, customers.phone, idfruit, fruits.fruit_name, fruits.price, fruits.Vendor, orders.order_date, quantity, total_cost FROM orders, fruits, customers WHERE orders.idcustomer=customers.id AND orders.idfruit=fruits.id";
            // If only quantity is selected
             if (!quantity.equals("All")) {
                query += " AND orders.quantity=" + quantity;
            }
            // Execute query and update table with results
            try {
                state = connection.prepareStatement(query);
                result = state.executeQuery();
                searchTable.setModel(new MyModelShop(result));
                searchTable.getColumnModel().getColumn(0).setMinWidth(0);
                searchTable.getColumnModel().getColumn(0).setMaxWidth(0);
                searchTable.getColumnModel().getColumn(0).setWidth(0);
                searchTable.getColumnModel().getColumn(1).setMinWidth(0);
                searchTable.getColumnModel().getColumn(1).setMaxWidth(0);
                searchTable.getColumnModel().getColumn(1).setWidth(0);
                searchTable.getColumnModel().getColumn(4).setMinWidth(0);
                searchTable.getColumnModel().getColumn(4).setMaxWidth(0);
                searchTable.getColumnModel().getColumn(4).setWidth(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class SearchDB implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            String date = (String) comboDate.getSelectedItem();
            String quantity = (String) comboQuantity.getSelectedItem();
            String query = "SELECT orders.id, idcustomer, customers.cust_name, customers.phone, idfruit, fruits.fruit_name, fruits.price, fruits.Vendor, orders.order_date, quantity, total_cost FROM orders, fruits, customers WHERE orders.idcustomer=customers.id AND orders.idfruit=fruits.id";

            // If both criteria are selected
            if (!date.equals("All") && !quantity.equals("All")) {
                query += " AND orders.order_date='" + date + "' AND orders.quantity=" + quantity;
            }
            // If only date is selected
            else if (!date.equals("All")) {
                query += " AND orders.order_date='" + date + "'";
            }
            // If only quantity is selected
            else if (!quantity.equals("All")) {
                query += " AND orders.quantity=" + quantity;
            }

            // Execute query and update table with results
            try {
                state = connection.prepareStatement(query);
            result = state.executeQuery();
            searchTable.setModel(new MyModelShop(result));
            searchTable.getColumnModel().getColumn(0).setMinWidth(0);
            searchTable.getColumnModel().getColumn(0).setMaxWidth(0);
            searchTable.getColumnModel().getColumn(0).setWidth(0);
            searchTable.getColumnModel().getColumn(1).setMinWidth(0);
            searchTable.getColumnModel().getColumn(1).setMaxWidth(0);
            searchTable.getColumnModel().getColumn(1).setWidth(0);
            searchTable.getColumnModel().getColumn(4).setMinWidth(0);
            searchTable.getColumnModel().getColumn(4).setMaxWidth(0);
            searchTable.getColumnModel().getColumn(4).setWidth(0);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }
    class MouseActionOrdersTable implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            int row = orderTable.getSelectedRow();
            idorder = Integer.parseInt(orderTable.getValueAt(row, orderTable.getColumnModel().getColumnIndex("id")).toString());
            idcustomer = Integer.parseInt(orderTable.getValueAt(row, orderTable.getColumnModel().getColumnIndex("idcustomer")).toString());
            comboCustomer.setSelectedItem(orderTable.getValueAt(row, orderTable.getColumnModel().getColumnIndex("cust_name")).toString());
            phoneNumbTF.setText(orderTable.getValueAt(row, orderTable.getColumnModel().getColumnIndex("phone")).toString());
            idfruit = Integer.parseInt(orderTable.getValueAt(row, orderTable.getColumnModel().getColumnIndex("idfruit")).toString());
            comboFruit.setSelectedItem(orderTable.getValueAt(row, orderTable.getColumnModel().getColumnIndex("fruit_name")).toString());
            // Parse the date string to a java.sql.Date object
            String dateString = orderTable.getValueAt(row, orderTable.getColumnModel().getColumnIndex("order_date")).toString();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.sql.Date sqlDate;
            try {
                java.util.Date utilDate = format.parse(dateString);
                sqlDate = new java.sql.Date(utilDate.getTime());
            } catch (ParseException | IllegalArgumentException ex) {
                System.out.println("Invalid date format: " + dateString);
                ex.printStackTrace();
                return;
            }
            priceTF.setText(orderTable.getValueAt(row, orderTable.getColumnModel().getColumnIndex("price")).toString());
            vendorNameTF.setText(orderTable.getValueAt(row, orderTable.getColumnModel().getColumnIndex("Vendor")).toString());
            quantityTF.setText(orderTable.getValueAt(row, orderTable.getColumnModel().getColumnIndex("quantity")).toString());
        }


        @Override
        public void mousePressed(MouseEvent e) {
            // Empty implementation
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // Empty implementation
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // Empty implementation
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // Empty implementation
        }
    }


    class MouseActionFruitTable implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {

            int row = fruitTable.getSelectedRow();
            idfruit=Integer.parseInt(fruitTable.getValueAt(row, 0).toString());
            fruitNameTF.setText(fruitTable.getValueAt(row, 1).toString());
            priceTF.setText(fruitTable.getValueAt(row, 2).toString());
            vendorNameTF.setText(fruitTable.getValueAt(row, 3).toString());
        }
        @Override
        public void mouseEntered(MouseEvent e) {// TODO Auto-generated method stub
        }
        @Override
        public void mouseExited(MouseEvent e) {// TODO Auto-generated method stub
        }
        @Override
        public void mousePressed(MouseEvent e) {// TODO Auto-generated method stub
        }
        @Override
        public void mouseReleased(MouseEvent e) {// TODO Auto-generated method stub
        }
    }
    class MouseActionCustomerTable implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            int row = customerTable.getSelectedRow();
            idcustomer=Integer.parseInt(customerTable.getValueAt(row, 0).toString());
            custNameTF.setText(customerTable.getValueAt(row, 1).toString());
            phoneNumbTF.setText(customerTable.getValueAt(row, 2).toString());
        }
        @Override
        public void mouseEntered(MouseEvent e) {// TODO Auto-generated method stub
        }
        @Override
        public void mouseExited(MouseEvent e) {// TODO Auto-generated method stub
        }
        @Override
        public void mousePressed(MouseEvent e) {// TODO Auto-generated method stub
        }
        @Override
        public void mouseReleased(MouseEvent e) {// TODO Auto-generated method stub
        }
    }


}