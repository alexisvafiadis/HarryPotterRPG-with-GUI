module alexis.isep.harrypotter.GUI {
    requires javafx.controls;
    requires javafx.fxml;

    opens alexis.isep.harrypotter.GUI to javafx.fxml;
    exports alexis.isep.harrypotter.GUI;
}