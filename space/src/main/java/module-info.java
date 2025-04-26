// filepath: c:\Users\nayra.orocha\Downloads\space\src\main\java\module-info.java
module br.com.joaocarloslima {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens br.com.joaocarloslima to javafx.fxml;
    exports br.com.joaocarloslima;
}