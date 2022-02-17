package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.MyThread;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        // Create a VBox (Vertical Box) layout container as root
        VBox root = new VBox();
        // Create labels
        Label label1 = new Label("Das ist ein Label");
        Label label2 = new Label("Das ist ein weiteres Label");
        // Add labels to root node
        root.getChildren().add(label1);
        root.getChildren().add(label2);

        // Set window title
        primaryStage.setTitle("Unser JavaFX Fenster");
        Scene scene = new Scene(root, 300, 275);
        // Set background to transparent
        root.setStyle("-fx-background-color: transparent;");
        // and now set background color to the scene
        scene.setFill(Color.AQUAMARINE);
        primaryStage.setScene(scene);
        // Prevent window from being resized
        //primaryStage.setResizable(false);

        // Get window width from scene and put it into label
        double width = scene.getWidth();
        label2.setText("Breite: " + String.valueOf(width));

        // Exkurs: Threading
        threadTest();
        // Exkurs: Anonyme Methoden: Lambda - Ausdrücke
        lambdaTest();

        // Stop with Platform.exit
        //platformExit();
        // Stop with System.ext
        systemExit();

        // Show the stage
        primaryStage.show();
    }

    public void stop() throws Exception{
        System.out.println("Stop Methode aufgerufen");
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void threadTest() {
        // Klassisch
        MyThread t1 = new MyThread();
        t1.start();
        MyThread t2 = new MyThread();
        t2.start();
        // Mit anonymer Klasse
        Thread t3 = new Thread () {
            @Override
            public void run() {
                System.out.print("Anonymous: Thread ist running");
                for(int i=0; i < 10; i++) {
                    System.out.println("\nDurchlauf: " + (i+1));
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t3.start();
    }

    public void lambdaTest() {
        // Vorher: Klassisch mit Interface und Implementierung
        int ergebnis = Calculator.add(10, 15, new MathInterface() {
            @Override
            public int function(int a, int b) {
                return a + b;
            }
        });
        System.out.println("Ergebnis = " + ergebnis);
        // Neu: Mit Lambda-Ausdruck (kapselt also Interface + Implementierung)
        int ergebnis2 = Calculator.add(10, 15, (a, b) -> { return a + b; });
    }

    public void platformExit() {
        Thread t3 = new Thread () {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Plattform.exit");
                Platform.exit();
            }
        };
        t3.start();
    }

    public void systemExit() {
        Thread t3 = new Thread () {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("System.exit");
                System.exit(-1);
            }
        };
        t3.start();
    }
}
