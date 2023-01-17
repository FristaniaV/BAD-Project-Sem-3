package main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableSelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import jfxtras.labs.scene.control.window.CloseIcon;
import jfxtras.labs.scene.control.window.MinimizeIcon;
import jfxtras.labs.scene.control.window.Window;
import main.CheckOut;
import main.Product;

public class Main extends Application implements EventHandler<ActionEvent> {

	Stage stage = new Stage();

	Random rand = new Random();
	
	private Connect connect = Connect.getInstance();
	
	ArrayList<Product> productList = new ArrayList<>();
	ArrayList<CheckOut> COList = new ArrayList<>();
	
	String loginUserID;

	// Login Form
	Scene sceneLogin;
	BorderPane bpLogin;
	GridPane gpLogin;
	FlowPane fpLogin;

	TextField unameTfLogin;

	PasswordField passTfLogin;

	Button loginButtonLogin;

	Label titleLabelLogin, unameLabelLogin, passLabelLogin;

	Hyperlink regHereLinkLogin;

	// Login Form
	public void initLogin() {

		bpLogin = new BorderPane();
		gpLogin = new GridPane();
		fpLogin = new FlowPane();

		unameTfLogin = new TextField();

		passTfLogin = new PasswordField();

		loginButtonLogin = new Button("Login");
		loginButtonLogin.setOnAction(this);

		titleLabelLogin = new Label("Shoes Station");
		unameLabelLogin = new Label("Username");
		passLabelLogin = new Label("Password");

		regHereLinkLogin = new Hyperlink("Register Here");
		regHereLinkLogin.setOnAction(this);

		sceneLogin = new Scene(bpLogin, 450, 500);
	}

	public void initFormLogin() {

		gpLogin.add(unameLabelLogin, 0, 1);
		gpLogin.add(unameTfLogin, 1, 1);

		gpLogin.add(passLabelLogin, 0, 2);
		gpLogin.add(passTfLogin, 1, 2);

		gpLogin.add(loginButtonLogin, 1, 3);
		loginButtonLogin.setTranslateX(14);

		gpLogin.setVgap(20);
		gpLogin.setHgap(20);

		bpLogin.setCenter(gpLogin);

		gpLogin.setAlignment(Pos.CENTER);
		BorderPane.setAlignment(titleLabelLogin, Pos.CENTER);
		BorderPane.setAlignment(regHereLinkLogin, Pos.CENTER);

		BorderPane.setMargin(titleLabelLogin, new Insets(30));
		BorderPane.setMargin(regHereLinkLogin, new Insets(0, 0, 130, 0)); // arah jarum jam

		bpLogin.setTop(titleLabelLogin);
		bpLogin.setBottom(regHereLinkLogin);
	}

	// ========================================
	// Register Form
	Scene sceneReg;
	BorderPane bpReg;
	GridPane gpReg;
	FlowPane fpReg;

	Label titleLabelReg, unameLabelReg, emailLabelReg, passLabelReg, cfPassLabelReg, genderLabelReg, ageLabelReg;

	TextField unameTfReg, emailTfReg;

	PasswordField passTfReg, cfPassTfReg;

	RadioButton femaleRbReg, maleRbReg;

	ToggleGroup genderGroupReg;

	Spinner<Integer> ageSpinnerReg;

	Button registerButtonReg;

	Hyperlink backToLoginLinkReg;

	public void initReg() {

		bpReg = new BorderPane();
		gpReg = new GridPane();
		fpReg = new FlowPane();

		titleLabelReg = new Label("Shoes Station");
		unameLabelReg = new Label("Username");
		emailLabelReg = new Label("Email");
		passLabelReg = new Label("Password");
		cfPassLabelReg = new Label("Confirm Password");
		genderLabelReg = new Label("Gender");
		ageLabelReg = new Label("Age");

		unameTfReg = new TextField();
		emailTfReg = new TextField();

		passTfReg = new PasswordField();
		cfPassTfReg = new PasswordField();

		genderGroupReg = new ToggleGroup();

		femaleRbReg = new RadioButton("Female");
		maleRbReg = new RadioButton("Male");

		femaleRbReg.setToggleGroup(genderGroupReg);
		maleRbReg.setToggleGroup(genderGroupReg);

		ageSpinnerReg = new Spinner<Integer>(0, 100, 0); // min, max, default

		registerButtonReg = new Button("Register");
		registerButtonReg.setOnAction(this);

		backToLoginLinkReg = new Hyperlink("Back to Login");
		backToLoginLinkReg.setOnAction(this);

		sceneReg = new Scene(bpReg, 550, 600); // width, height

	}

	public void initFormReg() {

		gpReg.add(unameLabelReg, 0, 1);
		gpReg.add(unameTfReg, 1, 1);
		unameTfReg.setMaxWidth(200);

		gpReg.add(emailLabelReg, 0, 2);
		gpReg.add(emailTfReg, 1, 2);
		emailTfReg.setMaxWidth(200);

		gpReg.add(passLabelReg, 0, 3);
		gpReg.add(passTfReg, 1, 3);
		passTfReg.setMaxWidth(200);

		gpReg.add(cfPassLabelReg, 0, 4);
		gpReg.add(cfPassTfReg, 1, 4);
		cfPassTfReg.setMaxWidth(200);

		gpReg.add(genderLabelReg, 0, 5);
		fpReg.getChildren().add(maleRbReg);
		fpReg.getChildren().add(femaleRbReg);
		gpReg.add(fpReg, 1, 5);

		gpReg.add(ageLabelReg, 0, 6);
		gpReg.add(ageSpinnerReg, 1, 6);
		ageSpinnerReg.setMaxWidth(100);

		gpReg.add(registerButtonReg, 1, 7);
		registerButtonReg.setTranslateX(50);

		bpReg.setCenter(gpReg);
		bpReg.setTop(titleLabelReg);
		bpReg.setBottom(backToLoginLinkReg);

		gpReg.setAlignment(Pos.CENTER);
		gpReg.setTranslateX(75);
		gpReg.setTranslateY(-50);
		BorderPane.setAlignment(titleLabelReg, Pos.CENTER);
		BorderPane.setAlignment(backToLoginLinkReg, Pos.CENTER);

		// set prefered size -> variable.setprefered size(width, height)

		BorderPane.setMargin(titleLabelReg, new Insets(40));
		BorderPane.setMargin(backToLoginLinkReg, new Insets(0, 0, 40, 0));

		gpReg.setVgap(20);
		gpReg.setHgap(40);

	}
	
	
	// ====================================================
	// MAIN FORM
	// User main form
	
	Scene sceneUserMain;
	
	MenuBar mBarUserMain;
	
	Menu mUserUserMain;
	Menu mTransactionUserMain;
	
	MenuItem mItemLogOutUserMain;
	MenuItem mItemBuyProductUserMain;
	MenuItem mItemViewTransactionUserMain;
	
	Pane paneAreaUserMain;
	
	public void initUserMain() {
		
		mBarUserMain = new MenuBar();
		mUserUserMain = new Menu("User");
		mTransactionUserMain = new Menu("Transaction");
		
		mItemLogOutUserMain = new MenuItem("Log Out");
		mItemLogOutUserMain.setOnAction(this);
		
		mItemBuyProductUserMain = new MenuItem("Buy Product");
		mItemBuyProductUserMain.setOnAction(this);
		
		mItemViewTransactionUserMain = new MenuItem("View Transaction");
		mItemViewTransactionUserMain.setOnAction(this);
		
		paneAreaUserMain = new Pane();
	}
	
	public void initUserMainForm() {
		
		paneAreaUserMain.getChildren().add(mBarUserMain);
		
		mBarUserMain.getMenus().addAll(mUserUserMain, mTransactionUserMain);
		mUserUserMain.getItems().addAll(mItemLogOutUserMain);
		mTransactionUserMain.getItems().addAll(mItemBuyProductUserMain, mItemViewTransactionUserMain);
	
		sceneUserMain = new Scene(paneAreaUserMain, 800, 350);
		
	}
	
	// Admin main form
	
	Scene sceneAdminMain;
	
	MenuBar mBarAdminMain;
	
	Menu mUserAdminMain;
	Menu mManageAdminMain;
	
	MenuItem mItemSignOutAdminMain;
	MenuItem mItemManageProductAdminMain;
	MenuItem mItemManageProductTypeAdminMain;

	Pane paneAreaAdminMain;
	
	public void initAdminMain() {
		mBarAdminMain = new MenuBar();
		
		mUserAdminMain = new Menu("User");
		mManageAdminMain = new Menu("Manage");
		
		mItemSignOutAdminMain = new MenuItem("Sign Out");
		mItemSignOutAdminMain.setOnAction(this);
		
		mItemManageProductAdminMain = new MenuItem("Manage Product");
		mItemManageProductAdminMain.setOnAction(this);
		
		mItemManageProductTypeAdminMain = new MenuItem("Manage Product Type");
		mItemManageProductTypeAdminMain.setOnAction(this);
		
		paneAreaAdminMain = new Pane();
	}
	
	public void initAdminMainForm() {
		paneAreaAdminMain.getChildren().add(mBarAdminMain);
		
		mBarAdminMain.getMenus().addAll(mUserAdminMain, mManageAdminMain);
		mUserAdminMain.getItems().addAll(mItemSignOutAdminMain);
		mManageAdminMain.getItems().addAll(mItemManageProductAdminMain, mItemManageProductTypeAdminMain);
	
		sceneAdminMain = new Scene(paneAreaAdminMain, 800, 350);
	}
	
	// =====================================
	// Buy Product Page
	
	Scene sceneBuyProduct;
	BorderPane bpBuyProduct;
	GridPane gpBuyProduct;
	GridPane gpWindowBuyProduct;
	FlowPane fp1BuyProduct;
	FlowPane fp2BuyProduct;
	Pane paneAreaBuyProduct;
	Window windowBuyProduct;
	
	MenuBar mBarBuyProduct;
	
	Menu mUserBuyProduct;
	Menu mTransactionBuyProduct;
	
	MenuItem mItemLogOutBuyProduct;
	MenuItem mItemBuyProductBuyProduct;
	MenuItem mItemViewTransactionBuyProduct;
	
	Label lblProductIDBuyProduct;
	Label lblProductStockBuyProduct;
	Label lblProductNameBuyProduct;
	Label lblProductTypeIDBuyProduct;
	Label lblProductPriceBuyProduct;
	
	Label lblIDBuyProduct;
	Label lblNameBuyProduct;
	Label lblQuantityBuyProduct;
	Label lblStockBuyProduct;
	Label lblTypeIDBuyProduct;
	Label lblPriceBuyProduct;
	
	TextField tfIDBuyProduct;
	TextField tfNameBuyProduct;
	TextField tfStockBuyProduct;
	TextField tfTypeIDBuyProduct;
	TextField tfPriceBuyProduct;
	
	Spinner <Integer> qtySpinnerBuyProduct;
	
	Button btnAddToCartBuyProduct;
	Button btnCheckOutBuyProduct;
	
	TableView<Product> productTableBuyProduct;
	TableView<CheckOut> checkOutTableBuyProduct;
	
	ScrollPane spBuyProduct;
	
	HBox hbBuyProduct;
	
	public void initBuyProduct() {
		bpBuyProduct = new BorderPane();
		gpBuyProduct = new GridPane();
		fp1BuyProduct = new FlowPane();
		fp2BuyProduct = new FlowPane();
		gpWindowBuyProduct = new GridPane();
		paneAreaBuyProduct = new Pane();
		
		mBarBuyProduct = new MenuBar();
		mUserBuyProduct = new Menu("User");
		mTransactionBuyProduct = new Menu("Transaction");
		
		mItemLogOutBuyProduct = new MenuItem("Log Out");
		mItemBuyProductBuyProduct = new MenuItem("Buy Product");
		mItemViewTransactionBuyProduct = new MenuItem("View Transaction");
				
		windowBuyProduct = new Window("Buy Product");
		
//		HBox hb = new HBox();
//		VBox vb = new VBox();
		
		productTableBuyProduct = new TableView<>();
		checkOutTableBuyProduct = new TableView<>();

		lblProductIDBuyProduct = new Label("productID");
		lblProductStockBuyProduct = new Label("productStock");
		lblProductNameBuyProduct = new Label("productName");
		lblProductTypeIDBuyProduct = new Label("productTypeID");
		lblProductPriceBuyProduct = new Label("productPrice");
		
		lblIDBuyProduct = new Label("ID");
		lblNameBuyProduct = new Label("Name");
		lblQuantityBuyProduct = new Label("Quantity");
		lblStockBuyProduct = new Label("Stock");
		lblTypeIDBuyProduct = new Label("Type ID");
		lblPriceBuyProduct = new Label("Price");
		
		tfIDBuyProduct = new TextField();
		tfNameBuyProduct = new TextField();
		tfStockBuyProduct = new TextField();
		tfTypeIDBuyProduct = new TextField();
		tfPriceBuyProduct = new TextField();
		
		tfIDBuyProduct.setEditable(false);
		tfNameBuyProduct.setEditable(false);
		tfStockBuyProduct.setEditable(false);
		tfTypeIDBuyProduct.setEditable(false);
		tfPriceBuyProduct.setEditable(false);
		
		qtySpinnerBuyProduct = new Spinner <>(0, 100, 0);
		
		btnAddToCartBuyProduct = new Button("Add to Cart");
		btnCheckOutBuyProduct = new Button("Check Out");
		
		spBuyProduct = new ScrollPane();
		spBuyProduct.setContent(productTableBuyProduct);
		spBuyProduct.setFitToHeight(true);
		
//		bp.setTop(paneArea);
//		bp.setCenter(window);
		
		sceneBuyProduct = new Scene(bpBuyProduct, 750, 600);
	}
	
	public void initBuyProductForm() {
		
		paneAreaBuyProduct.getChildren().add(mBarBuyProduct);
		
//		hb.getChildren().add(productTable);
		
//		vb.getChildren().add(0, sp);
//		vb.getChildren().add(1, gp);
				
		mBarBuyProduct.getMenus().addAll(mUserBuyProduct, mTransactionBuyProduct);
		mUserBuyProduct.getItems().addAll(mItemLogOutBuyProduct);
		mTransactionBuyProduct.getItems().addAll(mItemBuyProductBuyProduct, mItemViewTransactionBuyProduct);
			
		gpBuyProduct.add(lblIDBuyProduct, 0, 0);
		gpBuyProduct.add(lblNameBuyProduct, 0, 1);
		gpBuyProduct.add(lblQuantityBuyProduct, 0, 2);
		gpBuyProduct.add(lblStockBuyProduct, 3, 0);
		gpBuyProduct.add(lblTypeIDBuyProduct, 3, 1);
		gpBuyProduct.add(lblPriceBuyProduct, 3, 2);
		
		gpBuyProduct.add(tfIDBuyProduct, 2, 0);
		gpBuyProduct.add(tfNameBuyProduct, 2, 1);
		gpBuyProduct.add(qtySpinnerBuyProduct, 2, 2);
		gpBuyProduct.add(tfStockBuyProduct, 4, 0);
		gpBuyProduct.add(tfTypeIDBuyProduct, 4, 1);
		gpBuyProduct.add(tfPriceBuyProduct, 4, 2);
		
		fp1BuyProduct.getChildren().add(btnAddToCartBuyProduct);
		fp1BuyProduct.setAlignment(Pos.CENTER);
		fp1BuyProduct.setPadding(new Insets (10));
		
		fp2BuyProduct.getChildren().add(btnCheckOutBuyProduct);
		fp2BuyProduct.setAlignment(Pos.CENTER);
		fp2BuyProduct.setPadding(new Insets (10));
						
		gpBuyProduct.setVgap(10);
		gpBuyProduct.setHgap(10);
		gpBuyProduct.setPadding(new Insets (10));
		
		gpBuyProduct.setAlignment(Pos.CENTER);
		
		gpWindowBuyProduct.add(productTableBuyProduct, 0, 0); //tabelProduk
		gpWindowBuyProduct.add(gpBuyProduct, 0, 1); //textfield
		gpWindowBuyProduct.add(fp1BuyProduct, 0, 2);
		gpWindowBuyProduct.add(checkOutTableBuyProduct, 0, 3);
		gpWindowBuyProduct.add(fp2BuyProduct, 0, 4);
		
		gpWindowBuyProduct.setAlignment(Pos.CENTER); 
		
		windowBuyProduct.getRightIcons().add(new CloseIcon(windowBuyProduct));
		windowBuyProduct.getLeftIcons().add(new MinimizeIcon(windowBuyProduct));
		
		windowBuyProduct.getContentPane().getChildren().add(gpWindowBuyProduct);		
	
		bpBuyProduct.setTop(paneAreaBuyProduct);
		bpBuyProduct.setCenter(windowBuyProduct);
	
	}
	
	public void setTableProduct() {
		
		TableColumn<Product, Integer> productIDColumn = 
				new TableColumn<Product, Integer>("productID");
		productIDColumn.setCellValueFactory(
				new PropertyValueFactory<Product, Integer>("productID"));
		
		TableColumn<Product, String> productNameColumn = 
				new TableColumn<Product, String>("productName");
		productNameColumn.setCellValueFactory(
				new PropertyValueFactory<Product, String>("productName"));
		
		TableColumn<Product, Integer> productStockColumn = 
				new TableColumn<Product, Integer>("productStock");
		productStockColumn.setCellValueFactory(
				new PropertyValueFactory<Product, Integer>("productStock"));
		
		TableColumn<Product, Integer> productTypeIDColumn = 
				new TableColumn<Product, Integer>("productTypeID");
		productTypeIDColumn.setCellValueFactory(
				new PropertyValueFactory<Product, Integer>("productTypeID"));
		
		TableColumn<Product, Integer> productPriceColumn = 
				new TableColumn<Product, Integer>("productPrice");
		productPriceColumn.setCellValueFactory(
				new PropertyValueFactory<Product, Integer>("productPrice"));
		
		productIDColumn.setMinWidth(150);
		productNameColumn.setMinWidth(150);
		productStockColumn.setMinWidth(150);
		productTypeIDColumn.setMinWidth(150);
		productPriceColumn.setMinWidth(150);
		
		productTableBuyProduct.getColumns().addAll(productIDColumn, productStockColumn, productNameColumn, productTypeIDColumn, productPriceColumn);
	
		getDataProduct();
		refreshTableProduct();
		productTableBuyProduct.setOnMouseClicked(e -> {
			TableSelectionModel<Product> tableSelectionProduct = productTableBuyProduct.getSelectionModel();
			tableSelectionProduct.setSelectionMode(SelectionMode.SINGLE);
			
			Product userSelectedProduct = tableSelectionProduct.getSelectedItem();
			
			tfIDBuyProduct.setText(userSelectedProduct.getProductID().toString());
			tfNameBuyProduct.setText(userSelectedProduct.getProductName());
			tfStockBuyProduct.setText(userSelectedProduct.getProductStock().toString());
			tfTypeIDBuyProduct.setText(userSelectedProduct.getProductTypeID().toString());
			tfPriceBuyProduct.setText(userSelectedProduct.getProductPrice().toString());
			
			if (userSelectedProduct.getProductStock() == 0) {
				Alert stock00Alert = new Alert(AlertType.ERROR);
				stock00Alert.setContentText("Stock empty!!");
				stock00Alert.showAndWait();
			}
			
			// Kalau dia klik tabel
			btnAddToCartBuyProduct.setOnMouseClicked(e1 -> {
				if (qtySpinnerBuyProduct.getValue() == 0) {
					Alert stock0Alert = new Alert(AlertType.ERROR);
					stock0Alert.setContentText("Input the quantity first!!");
					stock0Alert.showAndWait();
				} else if (qtySpinnerBuyProduct.getValue() > userSelectedProduct.getProductStock()) {
					Alert stockShortAlert = new Alert(AlertType.ERROR);
					stockShortAlert.setContentText("Stock low!!");
					stockShortAlert.showAndWait();
				} else {
					
//					product.setProductStock(product.getProductStock() - quantitySpinner.getValue());
//					productList.set(tableSelectionProduct.getSelectedIndex(), product);
//					productTable.getItems().add(tableSelectionProduct.getSelectedIndex(), product);
//					productTable.getItems().remove(tableSelectionProduct.getSelectedIndex() -1);
//					refreshTableProduct();
					
					userSelectedProduct.setProductStock(userSelectedProduct.getProductStock() - qtySpinnerBuyProduct.getValue());
					productList.set(tableSelectionProduct.getSelectedIndex(), userSelectedProduct);
					
					productTableBuyProduct.getItems().add(tableSelectionProduct.getSelectedIndex(), userSelectedProduct);
					
					productTableBuyProduct.getItems().remove(tableSelectionProduct.getSelectedIndex() - 1);
					
					refreshTableProduct();
					
					Integer cOproductID = Integer.parseInt(tfIDBuyProduct.getText());
					// String cOuserID = loginUserID;
					String cOproductName = tfNameBuyProduct.getText();
					String cOproductType = tfStockBuyProduct.getText();
					Integer cOquantity = qtySpinnerBuyProduct.getValue();
					Integer cOproductPrice = Integer.parseInt(tfPriceBuyProduct.getText());
					
					COList.add(new CheckOut(cOproductID, loginUserID, cOproductName, cOproductType, cOquantity, cOproductPrice));
					refreshCOTableProduct();
				}
				
			});

			
		});
		
		// Kalau dia ga ngeklik tabel
		btnAddToCartBuyProduct.setOnMouseClicked(e -> {
			Alert buySelectedProductAlert = new Alert(AlertType.ERROR);
			buySelectedProductAlert.setContentText("Must Select a Product First!!");
			buySelectedProductAlert.showAndWait();
			
		});
	}
	
	public void setTableCheckOut() {
		
		TableColumn<CheckOut, Integer> COproductIDColumn = 
				new TableColumn<CheckOut, Integer>("productID");
		COproductIDColumn.setCellValueFactory(
				new PropertyValueFactory<CheckOut, Integer>("productID"));
		
		TableColumn<CheckOut, String> COuserIDColumn = 
				new TableColumn<CheckOut, String>("userID");
		COuserIDColumn.setCellValueFactory(
				new PropertyValueFactory<CheckOut, String>("userID"));
		
		TableColumn<CheckOut, String> COproductNameColumn = 
				new TableColumn<CheckOut, String>("productName");
		COproductNameColumn.setCellValueFactory(
				new PropertyValueFactory<CheckOut, String>("productName"));
		
		TableColumn<CheckOut, String> COproductTypeColumn = 
				new TableColumn<CheckOut, String>("productType");
		COproductTypeColumn.setCellValueFactory(
				new PropertyValueFactory<CheckOut, String>("productType"));
		
		TableColumn<CheckOut, Integer> COquantityColumn = 
				new TableColumn<CheckOut, Integer>("quantity");
		COquantityColumn.setCellValueFactory(
				new PropertyValueFactory<CheckOut, Integer>("quantity"));
		
		TableColumn<CheckOut, Integer> COproductPriceColumn = 
				new TableColumn<CheckOut, Integer>("productPrice");
		COproductPriceColumn.setCellValueFactory(
				new PropertyValueFactory<CheckOut, Integer>("productPrice"));
		
		COproductIDColumn.setMinWidth(123);
		COuserIDColumn.setMinWidth(123);
		COproductNameColumn.setMinWidth(123);
		COproductTypeColumn.setMinWidth(123);
		COquantityColumn.setMinWidth(123);
		COproductPriceColumn.setMinWidth(123);
		
		checkOutTableBuyProduct.getColumns().addAll(COproductIDColumn, COuserIDColumn, COproductNameColumn, COproductTypeColumn, COquantityColumn, COproductPriceColumn);
		
	}
	
	// ===========================================================================================================
	// Transaction History
	
//	Scene sceneTransactionHistory; 
//	BorderPane bpTransactionHistory;
//	GridPane gpTransactionHistory;
//	GridPane gpWindowTransactionHistory;
//	FlowPane fp1TransactionHistory;
//	FlowPane fp2TransactionHistory;
//	Pane paneAreaTransactionHistory;
//	Window windowTransactionHistory;
//	
//	MenuBar mBarTransactionHistory;
//	
//	Menu mUserTransactionHistory;
//	Menu mTransactionTransactionHistory;
//	
//	MenuItem mItemLogOutTransactionHistory;
//	MenuItem mItemBuyProductTransactionHistory;
//	MenuItem mItemViewTransactionHistory;
//	
//	Label lblIDTransactionHistory1;
//	Label lblDateTransactionHistory;
//	
//	Label lblIDTransactionHistory2;
//	Label lblProductTransactionHistory;
//	Label lblQuantityTransactionHistory;
//	
//	Label lblCurrentSelectedID;
//	
//	TableView<Product> productTableTransactionHistory;
//	TableView<CheckOut> checkOutTableTransactionHistory;
//	
//	ScrollPane spTransactionHistory;
//	
//	public void initTransactionHistory() {
//		
//		bpTransactionHistory = new BorderPane();
//		gpTransactionHistory = new GridPane();
//		gpWindowTransactionHistory = new GridPane();
//		fp1TransactionHistory = new FlowPane();
//		fp2TransactionHistory = new FlowPane();
//		paneAreaTransactionHistory = new Pane();
//		windowTransactionHistory = new Window();
//		spTransactionHistory = new ScrollPane();
//		
//		mBarTransactionHistory = new MenuBar();
//		
//		mUserTransactionHistory = new Menu("User");
//		mTransactionTransactionHistory = new Menu("Transaction");
//		
//		mItemLogOutTransactionHistory = new MenuItem("Log Out");
//		mItemBuyProductTransactionHistory = new MenuItem("Buy Product");
//		mItemViewTransactionHistory = new MenuItem("View Transaction");
//	
//		lblIDTransactionHistory1 = new Label("transactionID");
//		lblDateTransactionHistory = new Label("transactionDate");
//		
//		lblIDTransactionHistory2 = new Label("transactionID");
//		lblProductTransactionHistory = new Label("productID");
//		lblQuantityTransactionHistory = new Label("Quantity");
//		
//		lblCurrentSelectedID = new Label("Current selected ID: ");
//		
//		productTableTransactionHistory = new TableView<>();
//		checkOutTableTransactionHistory = new TableView<>();
//		
//		sceneTransactionHistory = new Scene(bpTransactionHistory, 750, 600);
//		
//	}
//	
//	public void initTransactionHistoryForm() {
//		
//		paneAreaTransactionHistory.getChildren().add(mBarTransactionHistory);
//		
//		mBarTransactionHistory.getMenus().addAll(mUserTransactionHistory, mTransactionTransactionHistory);
//		
//		mUserTransactionHistory.getItems().addAll(mItemLogOutTransactionHistory);
//		mTransactionTransactionHistory.getItems().addAll(mItemBuyProductTransactionHistory, mItemViewTransactionHistory);
//
//		
//		
////		gpWindowBuyProduct.add(productTableBuyProduct, 0, 0); //tabelProduk
////		gpWindowBuyProduct.add(gpBuyProduct, 0, 1); //textfield
////		gpWindowBuyProduct.add(fp1BuyProduct, 0, 2);
////		gpWindowBuyProduct.add(checkOutTableBuyProduct, 0, 3);
////		gpWindowBuyProduct.add(fp2BuyProduct, 0, 4);
////		
////		gpWindowBuyProduct.setAlignment(Pos.CENTER); 
////		
////		windowBuyProduct.getRightIcons().add(new CloseIcon(windowBuyProduct));
////		windowBuyProduct.getLeftIcons().add(new MinimizeIcon(windowBuyProduct));
////		
////		windowBuyProduct.getContentPane().getChildren().add(gpWindowBuyProduct);		
////	
////		bpBuyProduct.setTop(paneAreaBuyProduct);
////		bpBuyProduct.setCenter(windowBuyProduct);
//		
//	}
	
	 Scene sceneTransactionHistory; 
	 BorderPane bpTransactionHistory;
	 GridPane gpTransactionHistory;
	 GridPane gpWindowTransactionHistory;
	 FlowPane fp1TransactionHistory;
	 FlowPane fp2TransactionHistory;
	 Pane paneAreaTransactionHistory;
	 Window windowTransactionHistory;
	 
	 MenuBar mBarTransactionHistory;
	 
	 Menu mUserTransactionHistory;
	 Menu mTransactionTransactionHistory;
	 
	 MenuItem mItemLogOutTransactionHistory;
	 MenuItem mItemBuyProductTransactionHistory;
	 MenuItem mItemViewTransactionHistory;
	 
	 Label lblIDTransactionHistory1;
	 Label lblDateTransactionHistory;
	 
	 Label lblIDTransactionHistory2;
	 Label lblProductTransactionHistory;
	 Label lblQuantityTransactionHistory;
	 
	 Label lbCurrentSelectedID;
	 
	 TableView<HeaderTransaction> headerTransactionTable;
	 TableView<TransactionHistory> transactionHistoryTable;

	 
	 ScrollPane spTransactionHistory;
	 
	 public void initTransactionHistory() {
	  
	  bpTransactionHistory = new BorderPane();
	  gpTransactionHistory = new GridPane();
	  gpWindowTransactionHistory = new GridPane();
	  fp1TransactionHistory = new FlowPane();
	  fp2TransactionHistory = new FlowPane();
	  paneAreaTransactionHistory = new Pane();
	  windowTransactionHistory = new Window();
	  spTransactionHistory = new ScrollPane();
	  
	  mBarTransactionHistory = new MenuBar();
	  
	  mUserTransactionHistory = new Menu("User");
	  mTransactionTransactionHistory = new Menu("Transaction");
	  
	  mItemLogOutTransactionHistory = new MenuItem("Log Out");
	  mItemBuyProductTransactionHistory = new MenuItem("Buy Product");
	  mItemViewTransactionHistory = new MenuItem("View Transaction");
	 
	  lblIDTransactionHistory1 = new Label("transactionID");
	  lblDateTransactionHistory = new Label("transactionDate");
	  
	  lblIDTransactionHistory2 = new Label("transactionID");
	  lblProductTransactionHistory = new Label("productID");
	  lblQuantityTransactionHistory = new Label("Quantity");
	  
	  lbCurrentSelectedID = new Label ("Current Selected ID");
	  
	  headerTransactionTable = new TableView<>();
	  transactionHistoryTable = new TableView<>();
	  
	  sceneTransactionHistory = new Scene(bpTransactionHistory, 750, 600);
	  
	 }
	 
	 public void initTransactionHistoryForm() {
	  
	  paneAreaBuyProduct.getChildren().add(mBarTransactionHistory);
	  
	  mBarBuyProduct.getMenus().addAll(mUserBuyProduct, mTransactionBuyProduct);
	  mUserBuyProduct.getItems().addAll(mItemLogOutBuyProduct);
	  mTransactionBuyProduct.getItems().addAll(mItemBuyProductBuyProduct, mItemViewTransactionBuyProduct);
	 
	  gpTransactionHistory.add(lbCurrentSelectedID, 0, 0);
	  
	  gpWindowTransactionHistory.add(headerTransactionTable, 0, 0);
	  gpWindowTransactionHistory.add(gpTransactionHistory, 0, 1);
	  gpWindowTransactionHistory.add(transactionHistoryTable, 0, 2);
	    
	  windowTransactionHistory.getRightIcons().add(new CloseIcon(windowTransactionHistory));
	  windowTransactionHistory.getLeftIcons().add(new MinimizeIcon(windowTransactionHistory));
	  
	  windowTransactionHistory.getContentPane().getChildren().add(windowTransactionHistory);  
	 
	  bpTransactionHistory.setTop(paneAreaTransactionHistory);
	  bpTransactionHistory.setCenter(windowTransactionHistory);
	  
	 }
	
	 // =============================================================
	 // Manage Product Form
	 
	 Scene sceneManageProduct;
	 BorderPane bpManageProduct;
	 GridPane gpManageProduct;
	 GridPane gpWindowManageProduct;
	 FlowPane fp1ManageProduct;
	 FlowPane fp2ManageProduct;
	 FlowPane fp3ManageProduct;
	 Pane paneAreaManageProduct;
	 Window windowManageProduct;
	 
	 MenuBar mBarManageProduct;
	 
	 Menu mUserManageProduct;
	 Menu mManageProduct;
	  
	 Label lblProductIDManageProduct;
	 Label lblProductStockManageProduct;
	 Label lblProductNameManageProduct;
	 Label lblProductTypeManageProduct;
	 Label lblProductPriceManageProduct;
	 
	 Label lblNameManageProduct;
	 Label lblAddNewStockManageProduct;
	 Label lblNewTypeManageProduct;
	 Label lblPriceManageProduct;
	 
	 TextField tfNameManageProduct;
	 TextField tfPriceManageProduct;
	 
	 Spinner <Integer> qtySpinnerManageProduct;
	 
	 ComboBox<String> cbNewTypeManageProduct;
	 
//	 HBox hbRemoveManageProduct;
//	 HBox hbUpdateManageProduct;
//	 HBox hbInsertManageProduct;
	 
	 ScrollPane spManageProduct;
	 
	 public void initManageProduct() {
	  bpManageProduct = new BorderPane();
	  gpManageProduct = new GridPane();
	  fp1ManageProduct = new FlowPane();
	  fp2ManageProduct = new FlowPane();
	  fp3ManageProduct = new FlowPane();
	  gpWindowManageProduct = new GridPane();
	  paneAreaManageProduct = new Pane();
	  
	  mBarManageProduct = new MenuBar();
	  mUserManageProduct = new Menu("User");
	  mManageProduct = new Menu("Manage");
	    
	  windowManageProduct = new Window("Manage Product");
	    
	  lblProductIDManageProduct = new Label("productID");
	  lblProductStockManageProduct = new Label("productStock");
	  lblProductNameManageProduct = new Label("productName");
	  lblProductTypeManageProduct = new Label("productTypeID");
	  lblProductPriceManageProduct = new Label("productPrice");
	  
	  lblNameManageProduct = new Label("Name");
	  lblAddNewStockManageProduct = new Label("Add/New Stock");
	  lblNewTypeManageProduct = new Label("New Type");
	  lblPriceManageProduct = new Label("Price");
	  
	  tfNameManageProduct = new TextField();
	  tfPriceManageProduct = new TextField();
	  
	  tfNameManageProduct.setEditable(false);
	  tfPriceManageProduct.setEditable(false);
	  
	  qtySpinnerManageProduct = new Spinner <>(0, 100, 0);
	  
	  cbNewTypeManageProduct = new ComboBox<>();
	  cbNewTypeManageProduct.setItems(FXCollections.observableArrayList(
	    "Futsal", "Badminton", "PinkPonk4", "Volley Ball"));
	  
//	  HBox hbRemoveManageProduct = new HBox(10, hbUpdateManageProduct, hbInsertManageProduct);
//	  HBox hbUpdateManageProduct = new HBox(10, hbRemoveManageProduct, hbInsertManageProduct);
//	  HBox hbInsertManageProduct = new HBox(10, hbRemoveManageProduct, hbUpdateManageProduct);
	  
	  spManageProduct = new ScrollPane();
	  spManageProduct.setFitToHeight(true);
	    
	  sceneManageProduct = new Scene(bpManageProduct, 750, 600);
	 }
	 
	 public void initManageProductForm() {
	  
	  paneAreaManageProduct.getChildren().add(mBarManageProduct);
//	    
//	  mBarManageProduct.getMenus().addAll(mUserManageProduct, mManageProduct);
//	  mUserManageProduct.getItems().addAll(mItemSignOutAdminMain);
//	  mManageProduct.getItems().addAll(mItemManageProductAdminMain, mItemManageProductTypeAdminMain);
	  
		paneAreaAdminMain.getChildren().add(mBarAdminMain);
		
		mBarAdminMain.getMenus().addAll(mUserAdminMain, mManageAdminMain);
		mUserAdminMain.getItems().addAll(mItemSignOutAdminMain);
		mManageAdminMain.getItems().addAll(mItemManageProductAdminMain, mItemManageProductTypeAdminMain);
	
		sceneAdminMain = new Scene(paneAreaAdminMain, 800, 350);

	  gpManageProduct.add(lblNameManageProduct, 0, 0);
	  gpManageProduct.add(lblProductStockManageProduct, 0, 1);
	  gpManageProduct.add(lblProductNameManageProduct, 0, 2);
	  gpManageProduct.add(lblProductTypeManageProduct, 0, 3);
	  gpManageProduct.add(lblProductPriceManageProduct, 0, 4);
	  
	  gpManageProduct.add(lblNameManageProduct, 1, 2);
	  gpManageProduct.add(tfNameManageProduct, 1, 4);
	  gpManageProduct.add(lblNameManageProduct, 2, 2);
	  gpManageProduct.add(qtySpinnerManageProduct, 2, 4);
	  gpManageProduct.add(lblNewTypeManageProduct, 3, 2);
	  gpManageProduct.add(cbNewTypeManageProduct, 3, 4);
	  gpManageProduct.add(lblPriceManageProduct, 4, 2);
	  gpManageProduct.add(tfPriceManageProduct, 4, 4);
	  
//	  fp1ManageProduct.getChildren().add(hbRemoveManageProduct);
	  fp1ManageProduct.setAlignment(Pos.CENTER);
	  fp1ManageProduct.setPadding(new Insets (10));
	  
//	  fp2ManageProduct.getChildren().add(hbUpdateManageProduct);
	  fp2ManageProduct.setAlignment(Pos.CENTER);
	  fp2ManageProduct.setPadding(new Insets (10));
	  
//	  fp3ManageProduct.getChildren().add(hbInsertManageProduct);
	  fp3ManageProduct.setAlignment(Pos.CENTER);
	  fp3ManageProduct.setPadding(new Insets (10));
	      
	  gpManageProduct.setVgap(10);
	  gpManageProduct.setHgap(10);
	  gpManageProduct.setPadding(new Insets (10));
	  
	  gpManageProduct.setAlignment(Pos.CENTER);
	  
	  gpWindowManageProduct.add(gpManageProduct, 0, 1);
	  gpWindowManageProduct.add(fp1ManageProduct, 0, 2);
	  gpWindowManageProduct.add(fp2ManageProduct, 0, 3);
	  gpWindowManageProduct.add(fp2ManageProduct, 0, 4);
	  
	  gpWindowManageProduct.setAlignment(Pos.CENTER); 
	    
	  windowManageProduct.getContentPane().getChildren().add(gpWindowManageProduct);  
	 
	  bpManageProduct.setTop(paneAreaManageProduct);
	  bpManageProduct.setCenter(windowManageProduct);
	 
	 }
	 
	 
	// INSERT DATA
	
	public void addDataRegis(String userIDDataReg, String unameDataReg, int ageDataReg, String emailDataReg, String genderDataReg, String passDataReg, String utypeDataReg) {
		String query = String.format("INSERT INTO user VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s')", userIDDataReg, unameDataReg,
				ageDataReg, emailDataReg, genderDataReg, passDataReg, utypeDataReg);
		connect.execUpdate(query);
	}
	
	
	// GET DATA
	
	public void getDataProduct() {
		productList.removeAll(productList);
		
		String query = "SELECT * FROM product";
		connect.rs = connect.execQuery(query);
		
		try {
			while (connect.rs.next()) {
				Integer productID = connect.rs.getInt(1);
				String productName = connect.rs.getString(2);
				Integer productStock = connect.rs.getInt(3);
				Integer productTypeID = connect.rs.getInt(4);
				Integer productPrice = connect.rs.getInt(5);
				productList.add(new Product(productID, productName, productStock, productTypeID, productPrice));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//	public void setActionBtnAddToCart() {
//		btnAddToCartBuyProduct.setOnAction(new EventHandler<ActionEvent>() {
//			
//			public void handle(ActionEvent event) {
//				String query = "INSERT INTO CheckOut VALUES" (\"" + tfIDBuyProduct.getText() + "\", " 
//					    + ageSpinner.getValue() + ",\"" + majorCB.getValue() + "\")";
//			    Connect con = new Connect();
//			    con.execUpdate(query);
//			    
//			    refreshTable();
//			    
////			    COList.add(new CheckOut(cOproductID, loginUserID, cOproductName, cOproductType, cOquantity, cOproductPrice));
////				refreshCOTableProduct();
//			    
//				Integer cOproductID = Integer.parseInt(tfIDBuyProduct.getText());
//				// String cOuserID = loginUserID;
//				String cOproductName = tfNameBuyProduct.getText();
//				String cOproductType = tfStockBuyProduct.getText();
//				Integer cOquantity = qtySpinnerBuyProduct.getValue();
//				Integer cOproductPrice = Integer.parseInt(tfPriceBuyProduct.getText());
//			}
//		});
//	}
	
	
	
	
	// REFRESH TABLE
	
	private void refreshTableProduct() {
		ObservableList<Product> productObs = FXCollections.observableArrayList(productList);
		productTableBuyProduct.setItems(productObs);
	}
	
	private void refreshCOTableProduct() {
		ObservableList<CheckOut> coproductObs = FXCollections.observableArrayList(COList);
		checkOutTableBuyProduct.setItems(coproductObs);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		initLogin();
		initFormLogin();
		primaryStage.setTitle("Shoes Station");
		primaryStage.setScene(sceneLogin);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void handle(ActionEvent e) {
		// Login
		if (e.getSource() == loginButtonLogin) {
			if (unameTfLogin.getText().isEmpty()) {
				Alert loginErrorAlert = new Alert(AlertType.ERROR);
				loginErrorAlert.setHeaderText("Error");
				loginErrorAlert.setContentText("Incorrect Username");
				loginErrorAlert.showAndWait();
			} else if (passTfLogin.getText().isEmpty()) {
				Alert loginErrorAlert = new Alert(AlertType.ERROR);
				loginErrorAlert.setHeaderText("Error");
				loginErrorAlert.setContentText("Incorrect Password");
				loginErrorAlert.showAndWait();
			} else {
				Alert loginSuccessAlert = new Alert(AlertType.INFORMATION);
				loginSuccessAlert.setHeaderText("Message");
				loginSuccessAlert.setContentText("Login Success!");
				loginSuccessAlert.showAndWait();
				
				String query = "SELECT * FROM user WHERE userName = '" + unameTfLogin.getText() + "' AND Password = '" + passTfLogin.getText() + "' ";
				connect.execQuery(query);
				if (connect.rs == null) {
					Alert loginErrorAlert2 = new Alert(AlertType.ERROR);
					loginErrorAlert2.setHeaderText("Error");
					loginErrorAlert2.setContentText("Your account is not registered");
					loginErrorAlert2.showAndWait();
				} else
					try {
						if (connect.rs.next()) {
							if (unameTfLogin.getText().equals("admin") || passTfLogin.getText().equals("admin")) {
								Alert loginSuccessAlertAdmin = new Alert(AlertType.INFORMATION);
								loginSuccessAlertAdmin.setHeaderText("Message");
								loginSuccessAlertAdmin.setContentText("Login as an Admin Success!");
								loginSuccessAlertAdmin.showAndWait();
								initAdminMain();
								initAdminMainForm();
								stage.setTitle("Shoes Station");
								stage.setScene(sceneAdminMain);
								stage.show();
							} else {
								loginUserID = connect.rs.getString(1);
								Alert loginSuccessAlertUser = new Alert(AlertType.INFORMATION);
								loginSuccessAlertUser.setHeaderText("Message");
								loginSuccessAlertUser.setContentText("Login as an User Success!");
								loginSuccessAlertUser.showAndWait();
								initUserMain();
								initUserMainForm();
								stage.setTitle("Shoes Station");
								stage.setScene(sceneUserMain);
								stage.show();
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
			} 
		}

		if (e.getSource() == backToLoginLinkReg) {
			initLogin();
			initFormLogin();
			stage.setTitle("Shoes Station");
			stage.setScene(sceneLogin);
			stage.show();
		}

		
		
		// Regis
		if (e.getSource() == regHereLinkLogin) {
			initReg();
			initFormReg();
			stage.setScene(sceneReg);
			stage.setTitle("Shoes Station");
			stage.show();
		}

		if (e.getSource() == registerButtonReg) {
			
			boolean boolUnameReg = true, boolEmailReg = true, boolPassReg = true, boolCfPassReg = true, boolGenderReg = true, boolAgeReg = true;
			
			if (unameTfReg.getText().length() < 5 || unameTfReg.getText().length() > 20) {
				boolUnameReg = false;
				Alert unameTfRegAlert = new Alert(AlertType.ERROR);
				unameTfRegAlert.setContentText("Username must be between 5-20 characters");
				unameTfRegAlert.showAndWait();
			}
			
			if (!(emailTfReg.getText().matches("[a-zA-Z0-9_-]+@[a-z]+\\.+[a-z]+"))) {
				boolEmailReg = false;
				Alert emailTfRegAlert = new Alert(AlertType.ERROR);
			    emailTfRegAlert.setContentText("Email must be an email format!");
			    emailTfRegAlert.showAndWait();
			}

			if (passTfReg.getText().length() < 5 || unameTfReg.getText().length() > 15) {
				boolPassReg = false;
				Alert passTfRegAlert = new Alert(AlertType.ERROR);
				passTfRegAlert.setContentText("Password must be between 5-20 characters");
				passTfRegAlert.showAndWait();
			}

			if (!(passTfReg.getText().equals(cfPassTfReg.getText()))) {
				boolCfPassReg = false;
				Alert cfPassTfRegAlert = new Alert(AlertType.ERROR);
				cfPassTfRegAlert.setContentText("Confirm password must be the same as password");
				cfPassTfRegAlert.showAndWait();
			}
			
			if (!(femaleRbReg.isSelected() || maleRbReg.isSelected())) {
				boolGenderReg = false;
				Alert genderRbAlert = new Alert(AlertType.ERROR);
				genderRbAlert.setContentText("You must choose a gender between male and female");
				genderRbAlert.showAndWait();
			}
			
			if (ageSpinnerReg.getValue() < 11) {
				boolAgeReg = false;
				Alert ageSpinnerRegAlert = new Alert(AlertType.ERROR);
				ageSpinnerRegAlert.setContentText("You must be older than 10 y.o");
				ageSpinnerRegAlert.showAndWait();
			}
			
			if (boolUnameReg && boolEmailReg && boolPassReg && boolCfPassReg && boolGenderReg && boolAgeReg) {
				Alert registerSuccess = new Alert(AlertType.INFORMATION);
				registerSuccess.setContentText("User successfully inserted!");
				registerSuccess.showAndWait();
				
				int randomUserIDReg1 = rand.nextInt(10);
				int randomUserIDReg2 = rand.nextInt(10);
				int randomUserIDReg3 = rand.nextInt(10);
				String randomUserRegFinal = "CU" + "" + randomUserIDReg1 + "" +randomUserIDReg2 + "" + randomUserIDReg3;
				
				// Masukin data ke database
				String loginUserID = randomUserRegFinal;
				String unameDataReg = unameTfReg.getText();
				int ageDataReg = ageSpinnerReg.getValue();
				String emailDataReg = emailTfReg.getText();
				RadioButton checkRadioButtonGenderReg = (RadioButton) genderGroupReg.getSelectedToggle();
				String genderDataReg = checkRadioButtonGenderReg.getText();
				String passDataReg = passTfReg.getText();
				String utypeDataReg = "User";
				
				addDataRegis(loginUserID, unameDataReg, ageDataReg, emailDataReg, genderDataReg, passDataReg, utypeDataReg);
				
				// BELOM validasi biar ga sama kek diatabase
				
				initUserMain();
				initUserMainForm();
				stage.setTitle("Shoes Station");
				stage.setScene(sceneUserMain);
				stage.show();

			}

		}
		
		// MAIN FORM
		// User Main Form
		if (e.getSource() == mItemBuyProductUserMain) {
			initBuyProduct();
			initBuyProductForm();
			setTableProduct();
			setTableCheckOut();
			stage.setTitle("Shoes Station");
			stage.setScene(sceneBuyProduct);
			stage.show();
		}
	
		
		if (e.getSource() == mItemViewTransactionBuyProduct) {
			initTransactionHistory();
			initTransactionHistoryForm();
			stage.setTitle("Shoes Station");
			stage.setScene(sceneTransactionHistory);
			stage.show();
		}
		
		
		
		// Admin Main Form
		if (e.getSource() == mItemManageProductAdminMain) {
			initManageProduct();
			initManageProductForm();
			stage.setTitle("Shoes Station");
			stage.setScene(sceneManageProduct);
			stage.show();
		}
		// Belom bikin event dari admin main form

	}

}
