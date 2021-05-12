module edu.idatt2001.marcusjohannessen {
    requires javafx.controls;
    requires javafx.fxml;

    opens edu.idatt2001.mappe3.marcusjohannessen to javafx.fxml;
    exports edu.idatt2001.mappe3.marcusjohannessen;
}