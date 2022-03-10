/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


/**
 * FXML Controller class
 *
 * @author Dhia
 */
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Hebergement;
import entities.Transport;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import services.ServiceHebergement;
import services.ServiceTransport;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.MyDB;
import utils.NavigationEntreInterfaces;


/**
 * FXML Controller class
 *
 * @author user
 */
public class BackTransportController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private Button voyage_btn;
    @FXML
    private Button heb_btn;
    @FXML
    private Button transport_btn;
    @FXML
    private Button prom_btn;
    @FXML
    private Button reserv_btn;
    @FXML
    private Button sondage_btn;
    @FXML
    private Button prod_btn;
    @FXML
    private Button cmd_btn;
    @FXML
    private Button forum_btn;
    @FXML
    private Button reclam_btn;
    @FXML
    private AnchorPane Transports;
    @FXML
    private AnchorPane ap;
    @FXML
    private GridPane grid;
    @FXML
    private TextField ImageField;
    @FXML
    private TextField chercher;
    @FXML
    private TextField DescriptionField;
    @FXML
    private TextField DisponibiliteField;
    @FXML
    private TableView<Transport> Table_transport;
    @FXML
    private TableColumn<?, ?> descriptionTransport;
    @FXML
    private TableColumn<?, ?> disponibiliteTransport;
    @FXML
    private TableColumn<?, ?> priceTransport;
    @FXML
    private TableColumn<?, ?> typeTransport;
    @FXML
    private Button Modifier_Transport;
    @FXML
    private TextField PriceField;
    @FXML
    private TextField TypeField;
    @FXML
    private ImageView user_image;
    @FXML
    private Button profile_btn;
    @FXML
    private Button mdp_btn;
    @FXML
    private Button logout;
    int index = -1;
    FilteredList<Transport> filter = new FilteredList<>(getlistASC(), e -> true);
    SortedList<Transport> sort = new SortedList<>(filter);
    @FXML
    private TextField heb;
    @FXML
    private TableColumn<?, ?> ImageTransport;
private FileChooser filechooser;
    private File file;
    private String filePath;
    @FXML
    private Button parcourir;
    @FXML
    private TextField ImageeField;
    @FXML
    private ImageView Img;
     private javafx.scene.image.Image image;
    @FXML
    private TableColumn<?, ?> HebIdTransport;
    @FXML
    private ImageView image_vi;
    @FXML
    private ImageView testimg;
    @FXML
    private Button trier;
    @FXML
    private Button qr;
    public BackTransportController() throws SQLException {
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            AfficherTable();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        // TODO
    }

    public void AfficherTable() throws SQLException {
        ObservableList<Transport> list = getlistASC();
        System.out.println(list);
        Table_transport.setItems(list);
        //idTransport.setCellValueFactory(new PropertyValueFactory<>("id"));
        descriptionTransport.setCellValueFactory(new PropertyValueFactory<>("description"));
        disponibiliteTransport.setCellValueFactory(new PropertyValueFactory<>("disponibilite"));
        priceTransport.setCellValueFactory(new PropertyValueFactory<>("price"));
        typeTransport.setCellValueFactory(new PropertyValueFactory<>("type"));
        ImageTransport.setCellValueFactory(new PropertyValueFactory<>("image"));
       

    }

    public static ObservableList<Transport> getlistASC() throws SQLException {
        ServiceTransport ts = new ServiceTransport();
        ObservableList<Transport> listTransport = FXCollections.observableArrayList(ts.afficher());
        return listTransport;
    }

    @FXML
    public void AfficherTableASC() throws SQLException {
        ObservableList<Transport> list = getlistASC();
        System.out.println(list);
        Table_transport.setItems(list);
        //idTransport.setCellValueFactory(new PropertyValueFactory<>("id"));
        descriptionTransport.setCellValueFactory(new PropertyValueFactory<>("description"));
        disponibiliteTransport.setCellValueFactory(new PropertyValueFactory<>("disponibilité"));
        priceTransport.setCellValueFactory(new PropertyValueFactory<>("prix"));
        typeTransport.setCellValueFactory(new PropertyValueFactory<>("type"));
        ImageTransport.setCellValueFactory(new PropertyValueFactory<>("image"));
       HebIdTransport.setCellValueFactory(new PropertyValueFactory<>("Hebergement_id"));

    }

    @FXML
    private void Hebergement(ActionEvent event) throws IOException {
        NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
      nav.navigate(event, "Hebergement", "/gui/Hebergement.fxml");
   }

    @FXML
    private void Transport(ActionEvent event) {
    }

 @FXML
    private void STAT (ActionEvent event) throws IOException {
        NavigationEntreInterfaces nav = new NavigationEntreInterfaces();
      nav.navigate(event, "STAT", "/gui/STATTRANSPORT.fxml");
   }
        @FXML
private void Chercher (ActionEvent event) throws SQLException {
        //FilteredList<Hebergement> filter = new FilteredList<>(getlistASC(), e -> true);
        chercher.textProperty().addListener((observable, oldValue, newValue) -> {
                filter.setPredicate(h -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    
                    String lowerCaseFilter = newValue.toLowerCase();
                    
                    if (h.getImage().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if(h.getDescription().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    } else if(h.getType().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    
                    /*} else if(h.getDate_ajout().toLowerCase().contains(lowerCaseFilter)){
                        return true;*/
                    } else if(String.valueOf(h.getPrix()).contains(lowerCaseFilter)){
                        return true;
                    } else if(String.valueOf(h.getDisponibilité()).contains(lowerCaseFilter)){
                        return true;
                    } else if(String.valueOf(h.getPrix()).contains(lowerCaseFilter)){
                        return true;
                    }else 
                        return false;
                });

            });
        Table_transport.setItems(filter);
    }

    @FXML
    private void getSelected(MouseEvent event) {
        index = Table_transport.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        DescriptionField.setText(String.valueOf(descriptionTransport.getCellData(index)));
        DisponibiliteField.setText(String.valueOf(disponibiliteTransport.getCellData(index)));
        PriceField.setText(String.valueOf(priceTransport.getCellData(index)));
        TypeField.setText(String.valueOf(typeTransport.getCellData(index)));
        heb.setText(String.valueOf(HebIdTransport.getCellData(index)));
        ImageeField.setText(String.valueOf(ImageTransport.getCellData(index)));
     Image imageee = new Image(String.valueOf(ImageTransport.getCellData(index)));
    testimg.setFitHeight(50); //726
    testimg.setFitWidth(60); //500
    testimg.setImage(imageee);
       
        
    }

    @FXML
    private void dut(ZoomEvent event) {
    }

    double ParseDouble(String strNumber) {
        if (strNumber != null && strNumber.length() > 0) {
            try {
                return Double.parseDouble(strNumber);
            } catch (Exception e) {
                return -1;   // or some value to mark this field is wrong. or make a function validates field first ...
            }
        } else {
            return 0;
        }
    }
        int ParseInt(String strNumber) {
        if (strNumber != null && strNumber.length() > 0) {
            try {
                return Integer.parseInt(strNumber);
            } catch (Exception e) {
                return -1;   // or some value to mark this field is wrong. or make a function validates field first ...
            }
        } else {
            return 0;
        }
    }
    @FXML
    private void Ajouter_Transport(ActionEvent event) throws SQLException {
        ServiceTransport ts = new ServiceTransport();

        if (controleTextFieldNonNumerique(DescriptionField) ||  controleTextFieldNonNumerique(TypeField)||  controleTextFieldNumerique(PriceField)); else 
        {
        int id = Integer.parseInt(DisponibiliteField.getText());
             int id2 = Integer.parseInt(PriceField.getText());
 
        ServiceHebergement sh = new ServiceHebergement();
        //Hebergement k4 = new Hebergement(52,"8", "Tdhidhooo", 4, "Adresse", "Image",789);
        //sh.ajout(k4);
        Hebergement k4=sh.get(heb.getText());
            Transport t = new Transport(TypeField.getText(), DescriptionField.getText(),id, id2,ImageeField.getText(),k4);
            if((ts.nbrv(t)==0))
            {ts.ajout(t);
            System.out.println("Transport ajoutééé");
            TrayNotification tr = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Transport");
            tr.setMessage("Created succefully");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));
        }
        AfficherTableASC();

        try {
            javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("BackTransport.fxml"));
            Scene sceneview = new Scene(tableview);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(sceneview);
            window.show();
            AfficherTableASC();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        AfficherTableASC();
    }}

     @FXML
    private void Makepdf(ActionEvent event) throws SQLException {
        try {
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream("C:/Users/Dhia/Desktop/transport.pdf"));
            doc.open();
            doc.add(new Paragraph(" "));
            Font font = new Font(Font.FontFamily.TIMES_ROMAN, 28, Font.UNDERLINE, BaseColor.BLACK);
            Paragraph p = new Paragraph("Transports ", font);
            p.setAlignment(Element.ALIGN_CENTER);
            doc.add(p);
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph(" "));

            PdfPTable tabpdf = new PdfPTable(4);
            tabpdf.setWidthPercentage(100);

            PdfPCell cell;
            cell = new PdfPCell(new Phrase("Description", FontFactory.getFont("Times New Roman")));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            tabpdf.addCell(cell);

            cell = new PdfPCell(new Phrase("Disponibilite", FontFactory.getFont("Times New Roman")));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            tabpdf.addCell(cell);

            cell = new PdfPCell(new Phrase("Prix", FontFactory.getFont("Times New Roman")));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            tabpdf.addCell(cell);
            
            cell = new PdfPCell(new Phrase("Type", FontFactory.getFont("Times New Roman")));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            tabpdf.addCell(cell);
            
            Connection conn;
            conn = MyDB.getInstance().getConnection();
            String req="SELECT * FROM transport order by description ASC";
            PreparedStatement pst = conn.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                cell = new PdfPCell(new Phrase(rs.getString("Description"), FontFactory.getFont("Times New Roman", 11)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                tabpdf.addCell(cell);

                cell = new PdfPCell(new Phrase(rs.getString("disponibilité"), FontFactory.getFont("Times New Roman", 11)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                tabpdf.addCell(cell);

                cell = new PdfPCell(new Phrase(rs.getString("prix"), FontFactory.getFont("Times New Roman", 11)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                tabpdf.addCell(cell);
                
                cell = new PdfPCell(new Phrase(rs.getString("type"), FontFactory.getFont("Times New Roman", 11)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                tabpdf.addCell(cell);
                 
            }
            doc.add(tabpdf);
            doc.close();
            TrayNotification tr=new TrayNotification();
            AnimationType type=AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("PDF File");
            tr.setMessage("Created succefully");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));
            Desktop.getDesktop().open(new File("C:/test/example.pdf"));
        } catch (DocumentException | HeadlessException | IOException e) {
            System.out.println("ERROR PDF");
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println(e.getMessage());
        }
    }

      
    
    
    public boolean controleTextFieldNonNumerique(TextField textField) {
        if (!textField.getText().matches(".*[a-zA-Z].*")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Veuillez saisir des lettres");
            alert.showAndWait();
            return true;
        }
        return false;
    }
public boolean controleTextFieldNumerique(TextField textField) {
     int a = Integer.parseInt(textField.getText());
        if ((!textField.getText().matches(".*[0-9].*"))||(!(a>0))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Veuillez saisir des chiffres positif");
            alert.showAndWait();
            
            return true;
        }
        return false;
    }
    

    @FXML
    private void Modifier_Transport(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation ");
        alert.setHeaderText(null);
        alert.setContentText("Vous voulez vraiment modifier");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
        try {
            
             ServiceTransport ts = new ServiceTransport();
                 ServiceHebergement sh = new ServiceHebergement();
int id = Integer.parseInt(DisponibiliteField.getText());
             int id2 = Integer.parseInt(PriceField.getText());
Hebergement k4=sh.get(Table_transport.getSelectionModel().getSelectedItem().getHebergement().getDescription());
            int idd = Table_transport.getSelectionModel().getSelectedItem().getTransport_id();
            Transport tt = new Transport(idd,TypeField.getText(), DescriptionField.getText(),id, id2,ImageField.getText(),k4);
       
            ts.modifier(tt);
            TrayNotification tr = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Transport");
            tr.setMessage("Updated succefully");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));
            AfficherTable();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        AfficherTable();
    }}
    //}
 @FXML
    private void TRIER(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation ");
        alert.setHeaderText(null);
        alert.setContentText("Vous voulez vraiment modifier");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
        try {
            
             ServiceTransport ts = new ServiceTransport();
             ts.trier_desc();
            AfficherTable();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        AfficherTable();
    }}
    @FXML
    private void Supprimer_Transport(ActionEvent event) throws SQLException {
        
        ServiceTransport ts = new ServiceTransport();
        ts.supprimer(Table_transport.getSelectionModel().getSelectedItem().getTransport_id());
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation ");
        alert.setHeaderText(null);
        alert.setContentText("Vous voulez vraiment supprimer");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            javafx.scene.Parent tableview = FXMLLoader.load(getClass().getResource("BackTransport.fxml"));
            Scene sceneview = new Scene(tableview);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(sceneview);
            window.show();
            AfficherTableASC();
            TrayNotification tr=new TrayNotification();
            AnimationType type=AnimationType.POPUP;
            tr.setAnimationType(type);
            tr.setTitle("Transport");
            tr.setMessage("Deleted succefully");
            tr.setNotificationType(NotificationType.SUCCESS);
            tr.showAndDismiss(Duration.millis(5000));
            AfficherTableASC();}
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        AfficherTableASC();
    }
    @FXML
    private void Parcourir_Transport(ActionEvent event) {
        Stage primaryStage = new Stage();
        primaryStage.onShowingProperty();
        primaryStage.setTitle("selectionner une image !!!");
        filechooser = new FileChooser();
        filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files ", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        parcourir.setOnAction(e -> {
            file = filechooser.showOpenDialog(primaryStage);
            if (file != null) {
                //String s = file.getAbsolutePath();
                String F = file.toURI().toString();
                ImageeField.setText(F);
                  image = new javafx.scene.image.Image(file.toURI().toString(), 150, 100, true, true);
                 Img.setImage(image);

            } else {
                JOptionPane.showMessageDialog(null, "Impossible d'ajouter");
            }
        });
    }
    private void chooseImage(MouseEvent event) {

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        filechooser = new FileChooser();
        filechooser.setTitle("Open Image");
        this.file = filechooser.showOpenDialog(stage);
        String userDirectoryString = System.getProperty("home") + "\\Images";
        File userDirectory = new File(userDirectoryString);
        if (!(userDirectory.canRead())) {
            userDirectory = new File("c:/");
        }
        filechooser.setInitialDirectory(userDirectory);
        this.file = filechooser.showOpenDialog(stage);

        try {
            BufferedImage bi = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bi, null);
            image_vi.setImage(image);
            filePath = file.getAbsolutePath();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void chooseImage(ActionEvent event) {
    }

    @FXML
    private void CodeQr(ActionEvent event) {
        Transport t = Table_transport.getSelectionModel().getSelectedItem();
        String td= t.getDescription();
            ByteArrayOutputStream out = QRCode.from(td)
                .to(ImageType.PNG).stream();
        
        //String Path_name = new File("/").getAbsolutePath();
        try {
            FileOutputStream fout = new FileOutputStream(new File("C:/Users/Dhia/Desktop/Mostfa1.PNG"));
            fout.write(out.toByteArray());
            fout.flush();
            System.out.println("done");

        } catch (Exception e) {
            System.out.println(e);

        }
    }

   

        
    }
